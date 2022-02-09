package com.document.processing.libreoffice.uno.components;

import com.document.processing.DocumentProperties;
import com.document.processing.libreoffice.DocumentConvertTypes;
import com.document.processing.libreoffice.OdtFilePathHandler;
import com.document.processing.libreoffice.properties.OdtDocumentProperties;
import com.document.processing.libreoffice.uno.components.printer.Printer;
import com.sun.star.comp.helper.Bootstrap;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.container.XIndexAccess;
import com.sun.star.frame.*;
import com.sun.star.io.IOException;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.text.XTextDocument;
import com.sun.star.text.XTextRange;
import com.sun.star.text.XTextViewCursor;
import com.sun.star.text.XTextViewCursorSupplier;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import com.sun.star.uno.XInterface;
import com.sun.star.util.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class LibreOfficeUnoManager {
    private XComponentContext componentContext;
    private XMultiComponentFactory multiComponentFactory;
    private XComponentLoader componentLoader;
    private Object instanceWithContext;
    private ComponentService defaultLoadInstanceWithContext = ComponentService.DESKTOP;
    private XComponent component;
    private XTextDocument textDocument;
    private XController controller;
    private XTextViewCursor textViewCursor;
    private XSearchable searchable;
    private XReplaceable replaceable;
    private XSearchDescriptor searchDescriptor;
    private XReplaceDescriptor replaceDescriptor;
    private XDesktop desktop;

    public void initializeContext() throws BootstrapException {
        componentContext = Bootstrap.bootstrap();
        multiComponentFactory = componentContext.getServiceManager();
    }

    public Object createInstanceWithContext(String service) throws Exception {
        return multiComponentFactory.createInstanceWithContext(service, componentContext);
    }

    public Object createInstanceWithContext(ComponentService service) throws Exception {
        return createInstanceWithContext(service.getService());
    }

    public Object loadInstanceWithContext(ComponentService service) throws Exception {
        instanceWithContext = createInstanceWithContext(service);
        desktop = UnoRuntime.queryInterface(XDesktop.class, instanceWithContext);
        return instanceWithContext;
    }

    public XComponentLoader loadComponentLoader() throws Exception {
        if (instanceWithContext == null) {
            return (componentLoader = UnoRuntime.queryInterface(XComponentLoader.class, loadInstanceWithContext(defaultLoadInstanceWithContext)));
        }
        return (componentLoader = UnoRuntime.queryInterface(XComponentLoader.class, instanceWithContext));
    }

    public void setDefaultLoadInstanceWithContext(ComponentService defaultLoadInstanceWithContext) {
        this.defaultLoadInstanceWithContext = defaultLoadInstanceWithContext;
    }

    public XComponent loadComponent(String filepath, TargetFrame targetFrame, FrameSearchFlag frameSearchFlag, DocumentProperties documentProperties) throws Exception {
        if (componentLoader == null) {
            loadComponentLoader();
        }

        if (documentProperties == null) {
            documentProperties = new OdtDocumentProperties();
        }

        component = componentLoader.loadComponentFromURL(filepath, targetFrame.getTargetFrameName(), frameSearchFlag.getFrameSearchFlag(), documentProperties.getPropertyValuesAsArray());
        textDocument = UnoRuntime.queryInterface(XTextDocument.class, component);
        return component;
    }
    public XComponent loadComponent(String filepath, TargetFrame targetFrame, FrameSearchFlag frameSearchFlag) throws Exception {
        return loadComponent(filepath, targetFrame, frameSearchFlag, new OdtDocumentProperties());
    }

    public XComponent loadComponent(String filepath, TargetFrame targetFrame) throws Exception {
        return loadComponent(filepath, targetFrame, FrameSearchFlag.PARENT);
    }

    public XComponent loadComponent(String filepath, TargetFrame targetFrame, DocumentProperties documentProperties) throws Exception {
        return loadComponent(filepath, targetFrame, FrameSearchFlag.PARENT, documentProperties);
    }

    public XComponent loadComponent(String filepath) throws Exception {
        return loadComponent(filepath, TargetFrame.BLANK);
    }

    public XComponent loadComponent(String filepath, DocumentProperties documentProperties) throws Exception {
        return loadComponent(filepath, TargetFrame.BLANK, documentProperties);
    }

    public XController getController() {
        XModel model = UnoRuntime.queryInterface(XModel.class, textDocument);
        return (controller = model.getCurrentController());
    }

    public XComponent openDocument(String filepath) throws BootstrapException, Exception {
        if (!OdtFilePathHandler.isNormalizedFilepath(filepath)) {
            filepath = OdtFilePathHandler.normalizeFilepath(filepath);
        }

        return openDocument(filepath, new OdtDocumentProperties());
    }

    public XComponent openDocument(File file) throws BootstrapException, Exception {
        return openDocument(OdtFilePathHandler.normalizeFilepath(file), new OdtDocumentProperties());
    }

    public XComponent openDocument(File file, DocumentProperties documentProperties) throws BootstrapException, Exception {
        return openDocument(OdtFilePathHandler.normalizeFilepath(file), documentProperties);
    }

    public XComponent openDocument(String filepath, DocumentProperties documentProperties) throws BootstrapException, Exception {
        initializeContext();
        return loadComponent(filepath, documentProperties);
    }

    public XTextViewCursor createTextViewCursor() {
        if (controller == null) {
            getController();
        }

        XTextViewCursorSupplier textViewCursorSupplier = UnoRuntime.queryInterface(XTextViewCursorSupplier.class, controller);
        textViewCursor = textViewCursorSupplier.getViewCursor();
        textViewCursor.gotoStart(false);
        return textViewCursor;
    }

    private XTextRange findFirstInDocument() {
        XInterface xInterface = (XInterface) searchable.findFirst(searchDescriptor);
        if (xInterface != null) {
            XTextRange textRange = UnoRuntime.queryInterface(XTextRange.class, xInterface);
            textViewCursor.gotoRange(textRange, false);
            return textRange;
        }

        return null;
    }

    private List<Text> findAllInDocument() {
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

    private void initializeSearchInDocument() {
        searchable = UnoRuntime.queryInterface(XSearchable.class, textDocument);
        searchDescriptor = searchable.createSearchDescriptor();
    }

    private void setUpFindAction(String search) {
        initializeSearchInDocument();
        createTextViewCursor();
        searchDescriptor.setSearchString(search);
    }

    public XTextRange findFirstTextRange(String search) {
        setUpFindAction(search);
        return findFirstInDocument();
    }

    public String findFirst(String search) {
        return findFirstTextRange(search).getString();
    }

    public List<Text> findAllAsText(String search) {
        setUpFindAction(search);
        return findAllInDocument();
    }

    public List<String> findAll(String search) {
        return findAllAsText(search).stream().map(Text::getText).toList();
    }

    private void initializeReplaceInDocument() {
        replaceable = UnoRuntime.queryInterface(XReplaceable.class, textDocument);
        replaceDescriptor = replaceable.createReplaceDescriptor();
    }

    private void setUpReplaceAction() {
        initializeReplaceInDocument();
    }

    private int replaceStringInDocument(String search, String replace) {
        replaceDescriptor.setSearchString(search);
        replaceDescriptor.setReplaceString(replace);
        return replaceable.replaceAll(replaceDescriptor);
    }

    public int replaceAll(String search, String replace) {
        setUpReplaceAction();
        return replaceStringInDocument(search, replace);
    }

    /**
     * Method for closing the document. It may take a while to terminate the process
     */
    public void closeDocument() {
        closeDocument(false);
    }

    public void closeDocument(boolean askForSaveBeforeClose) {
        if (!askForSaveBeforeClose) {
            textDocument.dispose();
        }
        desktop.terminate();
    }

    public Text getAllDocumentText() {
        return new Text(createTextViewCursor().getText());
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

    public Printer initializePrinter() {
        return new Printer(component);
    }

}
