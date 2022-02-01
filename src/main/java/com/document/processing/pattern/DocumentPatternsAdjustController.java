package com.document.processing.pattern;

import java.io.File;

public final class DocumentPatternsAdjustController {
    public static DocumentPatternsAdjust initializePatternAdjuster(File document, Pattern pattern) {
        return new TextDocumentPatternsAdjust(document, pattern);
    }
}
