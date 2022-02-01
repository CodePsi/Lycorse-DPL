package com.document.xml.processing;

import com.document.zip.ZipFileContent;

import java.io.File;

public class DocumentTemplate extends BaseDocument {
    private String xmlContentPath;

    public DocumentTemplate(File zipFile) {
        super(zipFile);
    }

    public DocumentTemplate(String zipFile) {
        super(zipFile);
    }


    public void setXmlContentPath(String xmlContentPath) {
        this.xmlContentPath = xmlContentPath;
    }

}
