package com.document;

import com.document.processing.TextDocument;
import com.document.processing.DocumentManager;
import com.document.processing.DocumentManagerProvider;
import com.document.resources.manager.ResourcesManager;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File resourceFile = ResourcesManager.getResourceFile("TestDocument.odt");
        DocumentManagerProvider.createPropertiesBuilder().hidden(true).asTemplate(true);
        DocumentManager documentManager = DocumentManagerProvider.createDocumentManager(resourceFile);
        TextDocument textDocument = documentManager.openDocument(resourceFile);
        textDocument.replace("${Search}", "Value");
        textDocument.saveDocument();
    }
}
