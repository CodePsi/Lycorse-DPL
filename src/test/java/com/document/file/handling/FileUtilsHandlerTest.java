package com.document.file.handling;

import com.document.resources.manager.ResourcesManager;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsHandlerTest {

    @Test
    void readFile() throws IOException {
        String result = FileUtilsHandler.readFile(ResourcesManager.getResourceFile("content.xml"));
        assertTrue(result.length() > 0);
    }
}