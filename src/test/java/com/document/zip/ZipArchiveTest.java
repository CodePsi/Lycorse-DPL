package com.document.zip;

import com.document.resources.manager.ResourcesManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ZipArchiveTest {
    private String filepath;
    private ZipArchive zipArchive;

    @BeforeEach
    public void init() {
        filepath = ResourcesManager.getResourceFilepath("TestTemplate.odt");
        zipArchive = new ZipArchive(filepath);
    }

    @Test
    public void loadFiles() throws IOException {
        zipArchive.loadFiles();
        assertNotEquals(0, zipArchive.getEntries().size());
    }

    @Test
    public void getFile() throws IOException {
        ZipFileContent zipFileContent = zipArchive.getFile("content.xml");
        assertNotNull(zipFileContent);
        assertFalse(zipFileContent.getFileContent().isEmpty());
    }

    @Test
    public void saveArchive() throws IOException {
        zipArchive.loadFiles();
        ZipFileContent zipFileContent = zipArchive.getFile("content.xml");
        System.out.println(zipFileContent.getFileContent());
        zipArchive.saveArchive();

    }
}