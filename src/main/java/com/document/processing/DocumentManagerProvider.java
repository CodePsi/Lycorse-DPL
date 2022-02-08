package com.document.processing;

import com.document.processing.libreoffice.DocumentManagerPropertiesBuilder;
import com.document.processing.libreoffice.OdtDocumentManager;

import java.io.File;

public class DocumentManagerProvider {
    private static DocumentManagerPropertiesBuilder documentManagerPropertiesBuilder;
    private File file;

    public DocumentManagerProvider createDocumentManager(File file) {
        return new DocumentManagerProvider();
    }

    public DocumentManagerPropertiesBuilder createPropertiesBuilder() {
        documentManagerPropertiesBuilder = new DocumentManagerPropertiesBuilder(this);
        return documentManagerPropertiesBuilder;
    }

    public DocumentManager build() {
        String extension = file.getName().split("[.]")[1];
        if (documentManagerPropertiesBuilder == null) {
            return new OdtDocumentManager(file);
        }
        return new OdtDocumentManager(file, documentManagerPropertiesBuilder.getPropertyHandler());
    }
}
