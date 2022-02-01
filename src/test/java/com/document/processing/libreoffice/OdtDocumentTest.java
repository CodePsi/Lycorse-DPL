package com.document.processing.libreoffice;

import com.document.processing.Document;
import com.document.processing.DocumentManager;
import com.document.processing.DocumentManagerProvider;
import com.document.processing.DocumentProperties;
import com.document.processing.libreoffice.properties.OdtDocumentProperties;
import com.document.resources.manager.ResourcesManager;
import org.junit.jupiter.api.Test;

import java.io.File;

class OdtDocumentTest {

    @Test
    void saveDocumentAs() {
        File file = ResourcesManager.getResourceFile("Document.odt");
        DocumentManager documentManager = DocumentManagerProvider.createDocumentManager(file);
        Document document = documentManager.openDocument(file);
        document.replace("{Search}", "Value");
        document.saveDocumentAs(
                new File("C:/Document.docx"),
                DocumentConvertTypes.MS_WORD_2007_XML);
    }

    @Test
    void replace() {
        DocumentManager documentManager = new OdtDocumentManager();
        DocumentProperties documentProperties = new OdtDocumentProperties();
        Document document = documentManager.openDocument(ResourcesManager.getResourceFile("TestTemplate.odt"));
        document.replace("", "");
    }
}