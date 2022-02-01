package com.document.processing.ms.word.components;

import com.document.resources.manager.ResourcesManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WordComponentManagerTest {

    @Test
    void replaceAll() throws IOException {
        WordComponentManager wordComponentManager = new WordComponentManager();
        wordComponentManager.loadDocument(ResourcesManager.getResourceFile("T.docx"));
        wordComponentManager.replaceAll("${Replace}", "Value");
        wordComponentManager.saveDocument();
    }
}