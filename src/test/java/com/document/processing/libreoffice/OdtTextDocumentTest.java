package com.document.processing.libreoffice;

import com.document.processing.TextDocument;
import com.document.processing.DocumentManager;
import com.document.processing.DocumentManagerProvider;
import com.document.processing.DocumentProperties;
import com.document.processing.libreoffice.properties.OdtDocumentProperties;
import com.document.resources.manager.ResourcesManager;
import org.junit.jupiter.api.Test;

import java.io.File;

class OdtTextDocumentTest {

    @Test
    void saveDocumentAs() {
        File file = ResourcesManager.getResourceFile("TextDocument.odt");
        DocumentManager documentManager = DocumentManagerProvider.createDocumentManager(file);
        TextDocument textDocument = documentManager.openDocument(file);
        textDocument.replace("{Search}", "Value");
        textDocument.saveDocumentAs(
                new File("C:/TextDocument.docx"),
                DocumentConvertTypes.MS_WORD_2007_XML);
    }

    @Test
    void replace() {
        DocumentManager documentManager = new OdtDocumentManager();
        DocumentProperties documentProperties = new OdtDocumentProperties();
        TextDocument textDocument = documentManager.openDocument(ResourcesManager.getResourceFile("TestTemplate.odt"));
        textDocument.replace("", "");
    }
}