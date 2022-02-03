package com.document.processing;

import com.document.processing.libreoffice.DocumentManagerPropertiesBuilder;
import com.document.processing.libreoffice.OdtDocumentManager;

import java.io.File;

public class DocumentManagerProvider {
    private DocumentManagerPropertiesBuilder documentManagerPropertiesBuilder;

    public DocumentManagerProvider createPropertiesBuilder() {
        documentManagerPropertiesBuilder = new DocumentManagerPropertiesBuilder();
        return this;
    }

    public static DocumentManager createDocumentManager(File file) {
        String extension = file.getName().split("[.]")[1];
        return switch (extension) {
            case "odt" -> new OdtDocumentManager(file);
            default -> new OdtDocumentManager(file);
        };
    }
}
