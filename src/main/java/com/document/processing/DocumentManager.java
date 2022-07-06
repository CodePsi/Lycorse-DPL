package com.document.processing;

import java.io.File;

public interface DocumentManager {
    AdvancedTextDocument openDocument();
    AdvancedTextDocument openDocument(File file);
}
