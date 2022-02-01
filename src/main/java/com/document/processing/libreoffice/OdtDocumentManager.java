package com.document.processing.libreoffice;

import com.document.processing.Document;
import com.document.processing.DocumentManager;
import com.document.processing.DocumentProperties;
import com.document.processing.libreoffice.properties.OdtDocumentProperties;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;

import java.io.File;

public class OdtDocumentManager implements DocumentManager {


    @Override
    public Document openDocument(File file) {
        try {
            DocumentProperties documentProperties = new OdtDocumentProperties();
            documentProperties.addProperty(OdtDocumentProperties.HIDDEN, true);
            documentProperties.addProperty(OdtDocumentProperties.AS_TEMPLATE, true);
            return new OdtDocument(file, documentProperties);
        } catch (BootstrapException | Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
