package com.document.processing.libreoffice;

import com.document.processing.Document;
import com.document.processing.DocumentProperties;
import com.document.processing.libreoffice.properties.OdtDocumentProperties;
import com.document.processing.libreoffice.uno.components.LibreOfficeUnoManager;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.frame.XController;
import com.sun.star.frame.XModel;
import com.sun.star.frame.XStorable;
import com.sun.star.io.IOException;
import com.sun.star.lang.XComponent;
import com.sun.star.text.*;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XInterface;
import com.sun.star.util.CloseVetoException;
import com.sun.star.util.XCloseable;
import com.sun.star.util.XSearchDescriptor;
import com.sun.star.util.XSearchable;

import java.io.File;

public class OdtDocument implements Document {
    private LibreOfficeUnoManager libreOfficeUnoManager;
    private XComponent component;
    private XTextDocument textDocument;

    private String filepath;

    public OdtDocument(File file) throws BootstrapException, Exception {
        this.libreOfficeUnoManager = new LibreOfficeUnoManager();
        this.component = libreOfficeUnoManager.openDocument(file);
        this.filepath = OdtFilePathHandler.normalizeFilepath(file);
    }

    public OdtDocument(File file, DocumentProperties documentProperties) throws BootstrapException, Exception {
        this.libreOfficeUnoManager = new LibreOfficeUnoManager();
        this.component = libreOfficeUnoManager.openDocument(file, documentProperties);
        this.filepath = OdtFilePathHandler.normalizeFilepath(file);
    }

    @Override
    public void saveDocument(File file) {
        storeDocument(OdtFilePathHandler.normalizeFilepath(file), new OdtDocumentProperties());
    }

    @Override
    public void saveDocument(String filepath) {
        storeDocument(filepath, new OdtDocumentProperties());
    }

    @Override
    public void saveDocument() {
        storeDocument(filepath, new OdtDocumentProperties());
    }

    private void storeDocument(String filepath, DocumentProperties documentProperties) {
        libreOfficeUnoManager.storeDocument(filepath, documentProperties);
    }

    @Override
    public void saveDocumentAs(File file, DocumentConvertTypes convertTo) {
        saveDocumentAs(file.getAbsolutePath(), convertTo);
    }

    @Override
    public void saveDocumentAs(String filepath, DocumentConvertTypes convertTo) {
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

    @Override
    public void saveDocumentAs(DocumentConvertTypes convertTo) {
        saveDocumentAs(filepath, convertTo);
    }

    @Override
    public void replace(String search, String replace) {
        libreOfficeUnoManager.replaceAll(search, replace);
    }

    @Override
    public void close() {
        libreOfficeUnoManager.closeDocument();
    }
}
