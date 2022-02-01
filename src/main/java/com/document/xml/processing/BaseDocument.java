package com.document.xml.processing;

import com.document.zip.ZipFileContent;

import java.io.File;

public abstract class BaseDocument {
    private ZipFileContent zipFileContent;
    private File zipFile;

    public BaseDocument(File zipFile) {
        this.zipFile = zipFile;
    }

    public BaseDocument(String zipFile) {
        this.zipFile = new File(zipFile);
    }

    public ZipFileContent getZipFileContent() {
        return zipFileContent;
    }

    public String getFileContent() {
        return zipFileContent.getFileContent();
    }

    public String getFileName() {
        return zipFile.getAbsolutePath();
    }

    public void setZipFileContent(ZipFileContent zipFileContent) {
        this.zipFileContent = zipFileContent;
    }
}
