package com.document.processing;

import com.document.processing.libreoffice.OdtDocumentManager;

import java.io.File;

public class DocumentManagerProvider {
    public static DocumentManager createDocumentManager(File file) {
        String extension = file.getName().split("[.]")[1];
        switch (extension) {
            case "odt":
                return new OdtDocumentManager(file);
            default:
                return new OdtDocumentManager(file);
        }
    }
}
