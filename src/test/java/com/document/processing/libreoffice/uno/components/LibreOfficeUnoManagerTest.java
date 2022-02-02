package com.document.processing.libreoffice.uno.components;

import com.document.processing.TextDocument;
import com.document.processing.DocumentProperties;
import com.document.processing.libreoffice.DocumentConvertTypes;
import com.document.processing.libreoffice.OdtTextDocument;
import com.document.processing.libreoffice.properties.OdtDocumentProperties;
import com.document.resources.manager.ResourcesManager;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;
import org.junit.jupiter.api.Test;

import java.io.File;

class LibreOfficeUnoManagerTest {

    @Test
    void findFirst() throws BootstrapException, Exception {
        File document = ResourcesManager.getResourceFile("TestTemplate.odt");
        LibreOfficeUnoManager libreOfficeUnoManager = new LibreOfficeUnoManager();
        libreOfficeUnoManager.openDocument(document);
        System.out.println(libreOfficeUnoManager.replaceAll("${Search}", "Value"));



    }

    @Test
    void replaceAll() throws BootstrapException, Exception {
        DocumentProperties documentProperties = new OdtDocumentProperties();
        documentProperties.addProperty(OdtDocumentProperties.HIDDEN, true);
        TextDocument textDocument = new OdtTextDocument(ResourcesManager.getResourceFile("T.docx"), documentProperties);
//        textDocument.replace("${Replace}", "Value_1");
        textDocument.saveDocumentAs(new File("C:/Users/hp/IdeaProjects/XmlDocumentProcessing/File1.docx"), DocumentConvertTypes.MS_WORD_2007_XML);
//        textDocument.close();

    }
}