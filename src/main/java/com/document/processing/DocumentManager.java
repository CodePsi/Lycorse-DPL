package com.document.processing;

import java.io.File;

public interface DocumentManager {
    TextDocument openDocument();
    TextDocument openDocument(File file);
}
