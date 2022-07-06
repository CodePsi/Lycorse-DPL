package com.document.processing;

import com.document.processing.libreoffice.DocumentConvertTypes;
import com.document.processing.libreoffice.uno.components.Text;

import java.io.File;

public interface AdvancedTextDocument {
    void saveDocument(File file);
    void saveDocument(String filepath);
    void saveDocument();
    void saveDocumentAs(File file, DocumentConvertTypes convertTo);
    void saveDocumentAs(String filepath, DocumentConvertTypes convertTo);
    void saveDocumentAs(DocumentConvertTypes convertTo);
    void replace(String search, String replace);
    void close(boolean askBeforeClose);
    void close();

    String findFirst(String search);
    Text getAllText();
}
