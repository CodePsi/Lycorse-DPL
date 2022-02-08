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
        DocumentManagerProvider documentManagerProvider = new DocumentManagerProvider();
        DocumentManager documentManager = documentManagerProvider.createPropertiesBuilder().hidden(true).asTemplate(true).build();
        TextDocument textDocument = documentManager.openDocument(resourceFile);
        textDocument.replace("${Search}", "Value");
        textDocument.saveDocument();
    }
}
