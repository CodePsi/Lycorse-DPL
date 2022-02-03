package com.document.processing.libreoffice;

import com.document.processing.TextDocument;
import com.document.processing.DocumentProperties;
import com.document.processing.libreoffice.properties.OdtDocumentProperties;
import com.document.processing.libreoffice.uno.components.LibreOfficeUnoManager;
import com.document.processing.libreoffice.uno.components.Text;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;

import java.io.File;

public class OdtTextDocument implements TextDocument {
    private LibreOfficeUnoManager libreOfficeUnoManager;

    private String filepath;

    public OdtTextDocument(File file) throws BootstrapException, Exception {
        this.libreOfficeUnoManager = new LibreOfficeUnoManager();
        libreOfficeUnoManager.openDocument(file);
        this.filepath = OdtFilePathHandler.normalizeFilepath(file);
    }

    public OdtTextDocument(File file, DocumentProperties documentProperties) throws BootstrapException, Exception {
        this.libreOfficeUnoManager = new LibreOfficeUnoManager();
        libreOfficeUnoManager.openDocument(file, documentProperties);
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
        libreOfficeUnoManager.saveDocument(filepath, convertTo);
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
    public void close(boolean askBeforeClose) {
        libreOfficeUnoManager.closeDocument(askBeforeClose);
    }

    @Override
    public void close() {
        close(false);
    }

    @Override
    public String findFirst(String search) {
        return libreOfficeUnoManager.findFirst(search);
    }

    @Override
    public Text getAllText() {
        return libreOfficeUnoManager.getAllDocumentText();
    }
}
