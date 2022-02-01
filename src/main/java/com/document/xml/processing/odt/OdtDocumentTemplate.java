package com.document.xml.processing.odt;

import com.document.xml.processing.BaseDocument;
import com.document.xml.processing.Document;
import com.document.xml.processing.DocumentTemplate;

import java.io.File;

public class OdtDocumentTemplate extends DocumentTemplate {

    public OdtDocumentTemplate(File zipFile) {
        super(zipFile);
    }

    public OdtDocumentTemplate(String zipFile) {
        super(zipFile);
    }
}
