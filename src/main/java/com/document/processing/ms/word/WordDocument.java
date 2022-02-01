package com.document.processing.ms.word;

import com.document.processing.Document;
import com.document.processing.libreoffice.DocumentConvertTypes;

import java.io.File;

public class WordDocument implements Document {
    private File file;

    public WordDocument(File file) {
        this.file = file;
    }

    @Override
    public void saveDocument(File file) {

    }

    @Override
    public void saveDocument(String filepath) {

    }

    @Override
    public void saveDocument() {

    }

    @Override
    public void saveDocumentAs(File file, DocumentConvertTypes convertTo) {

    }

    @Override
    public void saveDocumentAs(String filepath, DocumentConvertTypes convertTo) {

    }

    @Override
    public void saveDocumentAs(DocumentConvertTypes convertTo) {

    }

    @Override
    public void replace(String search, String replace) {

    }

    @Override
    public void close() {

    }
}
