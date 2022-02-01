package com.document.processing.ms.word;

import com.document.processing.Document;
import com.document.processing.DocumentManager;
import com.document.processing.DocumentProperties;

import java.io.File;

public class WordDocumentManager implements DocumentManager {
    @Override
    public Document openDocument(File file) {
        return new WordDocument(file);
    }
}
