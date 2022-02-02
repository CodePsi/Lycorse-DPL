package com.document.processing.libreoffice;

import com.document.processing.TextDocument;
import com.document.processing.DocumentManager;
import com.document.processing.DocumentProperties;
import com.document.processing.libreoffice.properties.OdtDocumentProperties;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;

import java.io.File;

public class OdtDocumentManager implements DocumentManager {
    private File file;

    public OdtDocumentManager() {
    }

    public OdtDocumentManager(File file) {
        this.file = file;
    }

    @Override
    public TextDocument openDocument() {
        return openDocument(this.file);
    }

    @Override
    public TextDocument openDocument(File file) {
        try {
            DocumentProperties documentProperties = new OdtDocumentProperties();
            documentProperties.addProperty(OdtDocumentProperties.HIDDEN, true);
            documentProperties.addProperty(OdtDocumentProperties.AS_TEMPLATE, true);
            return new OdtTextDocument(file, documentProperties);
        } catch (BootstrapException | Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
