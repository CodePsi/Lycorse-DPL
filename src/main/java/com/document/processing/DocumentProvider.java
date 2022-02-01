package com.document.processing;

import java.io.File;

public interface DocumentProvider {
    DocumentManager openDocument(File file);
}
