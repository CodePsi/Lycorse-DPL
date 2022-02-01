package com.document.xml.processing.odt;

import com.document.xml.processing.BaseDocument;
import com.document.xml.processing.Document;
import com.document.zip.ZipFileContent;

import java.io.File;

public class OdtDocument extends BaseDocument implements Document {

    public OdtDocument(File zipFile) {
        super(zipFile);
    }
}
