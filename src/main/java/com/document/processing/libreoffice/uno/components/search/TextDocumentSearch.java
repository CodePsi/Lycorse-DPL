package com.document.processing.libreoffice.uno.components.search;

import com.document.processing.libreoffice.uno.components.Text;
import com.sun.star.container.XIndexAccess;
import com.sun.star.frame.XController;
import com.sun.star.frame.XModel;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.text.XTextDocument;
import com.sun.star.text.XTextRange;
import com.sun.star.text.XTextViewCursor;
import com.sun.star.text.XTextViewCursorSupplier;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XInterface;
import com.sun.star.util.XSearchDescriptor;
import com.sun.star.util.XSearchable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TextDocumentSearch {
    private final XTextDocument textDocument;
    private XSearchable searchable;
    private XSearchDescriptor searchDescriptor;
    private XTextViewCursor textViewCursor;

    public TextDocumentSearch(XTextDocument textDocument) {
        this.textDocument = textDocument;
        initializeSearchInDocument();
    }

    private void initializeSearchInDocument() {
        searchable = UnoRuntime.queryInterface(XSearchable.class, textDocument);
        searchDescriptor = searchable.createSearchDescriptor();
        createTextViewCursor();
    }

    public void createTextViewCursor() {
        XController controller = UnoRuntime.queryInterface(XModel.class, textDocument).getCurrentController();

        XTextViewCursorSupplier textViewCursorSupplier = UnoRuntime.queryInterface(XTextViewCursorSupplier.class, controller);
        textViewCursor = textViewCursorSupplier.getViewCursor();
        textViewCursor.gotoStart(false);
    }

    public Optional<Text> findFirstInDocument(String search) {
        searchDescriptor.setSearchString(search);
        XInterface xInterface = (XInterface) searchable.findFirst(searchDescriptor);
        Optional<Text> optionalText = Optional.empty();
        if (xInterface != null) {
            XTextRange textRange = UnoRuntime.queryInterface(XTextRange.class, xInterface);
            textViewCursor.gotoRange(textRange, false);
            optionalText = Optional.of(new Text(textRange));
        }

        return optionalText;
    }

    public String findFirst(String search) {
        return findFirstInDocument(search).orElseThrow().getText();
    }

    public Text getAllDocumentText() {
        return new Text(textViewCursor.getText());
    }

    private List<Text> findAllInDocument(String search, XSearchDescriptor searchDescriptor) {
        searchDescriptor.setSearchString(search);
        XIndexAccess indexAccess = searchable.findAll(searchDescriptor);
        return getAllFoundElements(indexAccess);
    }

    private List<Text> getAllFoundElements(XIndexAccess indexAccess) {
        int count = indexAccess.getCount();
        List<Text> textRangeList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            try {
                XTextRange textRange = UnoRuntime.queryInterface(XTextRange.class, indexAccess.getByIndex(i));
                textRangeList.add(new Text(textRange));
            } catch (IndexOutOfBoundsException | WrappedTargetException e) {
                e.printStackTrace();
            }
        }

        return textRangeList;
    }

    public List<Text> findAllAsText(String search) {
        return findAllInDocument(search, searchDescriptor);
    }

    public List<String> findAll(String search) {
        return findAllAsText(search).stream().map(Text::getText).toList();
    }

    public List<Text> findAllByRegex(String regex) {
        XSearchDescriptor localSearchDescriptor = searchable.createSearchDescriptor();
        builder(localSearchDescriptor)
                .setRegularExpressionSearch(true)
                .apply();

        return findAllInDocument(regex, localSearchDescriptor);
    }

    public SearchPropertyBuilder builder(XSearchDescriptor searchDescriptor) {
        return new SearchPropertyBuilder(searchDescriptor);
    }

    public SearchPropertyBuilder builder() {
        return new SearchPropertyBuilder(this.searchDescriptor);
    }

}
