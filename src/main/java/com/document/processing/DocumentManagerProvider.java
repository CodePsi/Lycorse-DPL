package com.document.processing;

import com.document.processing.libreoffice.DocumentManagerPropertiesBuilder;
import com.document.processing.libreoffice.document.TextDocument;
import com.document.processing.libreoffice.uno.components.LibreOfficeUnoManager;
import com.sun.star.io.IOException;

import java.io.File;

public class DocumentManagerProvider {
    private DocumentManagerProvider() {}

    public static TextDocument open(File file, DocumentProperties documentProperties) throws IOException {
        LibreOfficeUnoManager libreOfficeUnoManager = new LibreOfficeUnoManager();
        return libreOfficeUnoManager.openDocument(file, documentProperties);
    }

    public static TextDocument open(File file) throws IOException {
        DocumentProperties documentProperties = DocumentManagerPropertiesBuilder.instance().build();
        return open(file, documentProperties);
    }
}
