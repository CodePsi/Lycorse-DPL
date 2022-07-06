package com.document.processing.libreoffice;

import com.document.processing.AdvancedTextDocument;
import com.document.processing.DocumentManager;
import com.document.processing.DocumentProperties;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;

import java.io.File;

public class OdtDocumentManager implements DocumentManager {
    private File file;
    private DocumentProperties documentProperties;

    public OdtDocumentManager() {
    }

    public OdtDocumentManager(File file) {
        this.file = file;
    }
    public OdtDocumentManager(File file, DocumentProperties documentProperties) {
        this.file = file;
        this.documentProperties = documentProperties;
    }

    @Override
    public AdvancedTextDocument openDocument() {
        return openDocument(this.file);
    }

    @Override
    public AdvancedTextDocument openDocument(File file) {
        try {
            if (this.documentProperties != null) {
                return new OdtTextDocument(file, this.documentProperties);
            } else {
                return new OdtTextDocument(file);
            }
        } catch (BootstrapException | Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
