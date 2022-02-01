package com.document.processing.pattern;

import com.document.resources.manager.ResourcesManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class DocumentPatternsAdjustControllerTest {

    @Test
    void initializePatternAdjuster() throws IOException {
//        Instant start = Instant.now();
        DocumentPatternsAdjust documentPatternsAdjust = DocumentPatternsAdjustController.initializePatternAdjuster(
                ResourcesManager.getResourceFile("TestDocument.odt"),
                new Pattern("${", "}"));
        documentPatternsAdjust.adjustPatterns()
        System.out.println(documentPatternsAdjust.getFoundPatterns());
//        Instant end = Instant.now();
//        System.out.println(Duration.between(start, end));
        for (int i = 1; i <= 10000; i++) {
            System.out.println("${project_" + i + "}");
        }
    }
}