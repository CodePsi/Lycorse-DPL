package com.document.zip;

import java.util.zip.ZipEntry;

public class ZipFileContent {
    private ZipEntry zipEntry;
    private String fileContent;


    public ZipFileContent(ZipEntry zipEntry, String fileContent) {
        this.zipEntry = zipEntry;
        this.fileContent = fileContent;
    }


    public ZipEntry getZipEntry() {
        return zipEntry;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setZipEntry(ZipEntry zipEntry) {
        this.zipEntry = zipEntry;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }
}
