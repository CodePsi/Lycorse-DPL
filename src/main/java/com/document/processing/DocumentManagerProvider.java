package com.document.processing;

import com.document.processing.libreoffice.DocumentManagerPropertiesBuilder;
import com.document.processing.libreoffice.OdtDocumentManager;

import java.io.File;

public class DocumentManagerProvider {
    private static DocumentManagerPropertiesBuilder documentManagerPropertiesBuilder;

    public static DocumentManagerPropertiesBuilder createPropertiesBuilder() {
        documentManagerPropertiesBuilder = new DocumentManagerPropertiesBuilder();
        return documentManagerPropertiesBuilder;
    }

    public static DocumentManager createDocumentManager(File file) {
        String extension = file.getName().split("[.]")[1];
        if (documentManagerPropertiesBuilder == null) {
            return new OdtDocumentManager(file);
        }
        return new OdtDocumentManager(file, documentManagerPropertiesBuilder.getPropertyHandler());
    }
}
