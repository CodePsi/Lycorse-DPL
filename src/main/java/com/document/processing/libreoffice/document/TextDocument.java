package com.document.processing.libreoffice.document;

import com.document.processing.DocumentProperties;
import com.document.processing.libreoffice.DocumentConvertTypes;
import com.document.processing.libreoffice.OdtFilePathHandler;
import com.document.processing.libreoffice.properties.OdtDocumentProperties;
import com.document.processing.libreoffice.uno.components.Text;
import com.document.processing.libreoffice.uno.components.frame.ComponentLoader;
import com.document.processing.libreoffice.uno.components.frame.ComponentModel;
import com.document.processing.libreoffice.uno.components.frame.Controller;
import com.document.processing.libreoffice.uno.components.printer.Printer;
import com.document.processing.libreoffice.uno.components.search.TextDocumentSearch;
import com.document.processing.libreoffice.uno.components.table.Table;
import com.document.processing.pattern.Pattern;
import com.sun.star.frame.XStorable;
import com.sun.star.io.IOException;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XComponent;
import com.sun.star.text.XTextDocument;
import com.sun.star.uno.UnoRuntime;

import java.io.File;
import java.util.List;

public class TextDocument {
    private final XTextDocument xTextDocument;
    private final XComponent component;
    private final ComponentLoader componentLoader;
    private final ComponentModel componentModel;
    private Pattern selectedPattern;


    public TextDocument(XTextDocument xTextDocument, ComponentLoader componentLoader) {
        this.componentLoader = componentLoader;
        this.xTextDocument = xTextDocument;
        this.componentModel = componentLoader.getComponentModal();
        this.component = componentLoader.getComponent();
    }

    public ComponentModel getModel() {
        return componentModel;
    }

    public Controller getController() {
        return componentModel.getController();
    }

    /**
     * Method for closing the document. It may take a while to terminate the process
     */
    public void closeDocument() {
        closeDocument(false);
    }

    public void closeDocument(boolean askForSaveBeforeClose) {
        if (!askForSaveBeforeClose) {
            xTextDocument.dispose();
        }
        componentLoader.terminate();
    }

    public List<Table> getAllTables() throws IndexOutOfBoundsException, WrappedTargetException {
        return Table.getAllTables(xTextDocument);
    }

    public Printer printer() {
        return new Printer(component);
    }

    public TextDocumentSearch search() {
        return new TextDocumentSearch(xTextDocument);
    }


    public void saveDocument(String filepath, DocumentConvertTypes convertTo) {
        DocumentProperties documentProperties = new OdtDocumentProperties();
        File file = new File(filepath);

        if (!OdtFilePathHandler.isNormalizedFilepath(filepath)) {
            filepath = OdtFilePathHandler.normalizeFilepath(filepath);
        }
        if (file.exists()) {
            documentProperties.addProperty(OdtDocumentProperties.OVERWRITE, true);
        }
        documentProperties.addProperty(OdtDocumentProperties.FILTER_NAME, convertTo.getConvertType());
        storeDocument(filepath, documentProperties);
    }

    public void storeDocument(String filepath, DocumentProperties documentProperties) {
        try {
            XStorable xStorable = UnoRuntime.queryInterface(XStorable.class, component);

            // In case of export files extensions types, such as pdf, there's need to use storeToUrl, otherwise
            // for using Save As, as in GUI, there's need to use storeAsUrl. More details on
            // https://api.libreoffice.org/docs/idl/ref/interfacecom_1_1sun_1_1star_1_1frame_1_1XStorable.html
            if (documentProperties.getProperty(OdtDocumentProperties.FILTER_NAME).getValue().equals(DocumentConvertTypes.WRITER_PDF_CONVERT_TYPE.getConvertType())) {
                xStorable.storeToURL(filepath, documentProperties.getPropertyValuesAsArray());
            } else {
                xStorable.storeToURL(filepath, documentProperties.getPropertyValuesAsArray());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPattern(Pattern pattern) {
        this.selectedPattern = pattern;
    }

    /**
     *
     * */
    public void replacePattern(String replace, String value) {
        Pattern patternToUse = selectedPattern != null ? selectedPattern : Pattern.DEFAULT_PATTERN;
        Text text = search().findFirstInDocument(patternToUse.getPatternString(replace)).orElseThrow();
        text.setText(value);
    }
}
