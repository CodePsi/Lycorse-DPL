package com.document.processing.pattern;

import com.document.resources.manager.ResourcesManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

class OdtTextDocumentPatternsAdjustTest {

    @Test
    void adjustPatterns() throws IOException {
        Instant start = Instant.now();
        TextDocumentPatternsAdjust odtDocumentPatternsAdjust = new TextDocumentPatternsAdjust(new Pattern("${", "}"));
        odtDocumentPatternsAdjust.adjustPatterns(ResourcesManager.getResourceFile("TestDocument.docx"));
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));

    }
}