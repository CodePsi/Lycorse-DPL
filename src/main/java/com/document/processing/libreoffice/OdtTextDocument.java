package com.document.processing.libreoffice;

import com.document.processing.AdvancedTextDocument;
import com.document.processing.DocumentProperties;
import com.document.processing.libreoffice.properties.OdtDocumentProperties;
import com.document.processing.libreoffice.uno.components.LibreOfficeUnoManager;
import com.document.processing.libreoffice.uno.components.Text;
import com.document.processing.libreoffice.uno.components.search.TextDocumentSearch;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;

import java.io.File;

public class OdtTextDocument implements AdvancedTextDocument {
    private LibreOfficeUnoManager libreOfficeUnoManager;
    private TextDocumentSearch documentSearch;

    private String filepath;

    public OdtTextDocument(File file) throws BootstrapException, Exception {
        initialize(file);
        libreOfficeUnoManager.openDocument(file);
    }

    public OdtTextDocument(File file, DocumentProperties documentProperties) throws BootstrapException, Exception {
        initialize(file);
        libreOfficeUnoManager.openDocument(file, documentProperties);
    }

    private void initialize(File file) {
        this.libreOfficeUnoManager = new LibreOfficeUnoManager();
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
//        libreOfficeUnoManager.storeDocument(filepath, documentProperties);
    }

    @Override
    public void saveDocumentAs(File file, DocumentConvertTypes convertTo) {
        saveDocumentAs(file.getAbsolutePath(), convertTo);
    }

    @Override
    public void saveDocumentAs(String filepath, DocumentConvertTypes convertTo) {
//        libreOfficeUnoManager.saveDocument(filepath, convertTo);
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

    }

    @Override
    public void close() {
        close(false);
    }

    @Override
    public String findFirst(String search) {
        return documentSearch.findFirst(search);
    }

    @Override
    public Text getAllText() {
        return documentSearch.getAllDocumentText();
    }
}
