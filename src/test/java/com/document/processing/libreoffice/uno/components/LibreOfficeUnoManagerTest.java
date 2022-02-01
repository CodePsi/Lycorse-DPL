package com.document.processing.libreoffice.uno.components;

import com.document.processing.Document;
import com.document.processing.DocumentProperties;
import com.document.processing.libreoffice.DocumentConvertTypes;
import com.document.processing.libreoffice.OdtDocument;
import com.document.processing.libreoffice.OdtFilePathHandler;
import com.document.processing.libreoffice.properties.OdtDocumentProperties;
import com.document.resources.manager.ResourcesManager;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.lang.XComponent;
import com.sun.star.text.XTextDocument;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

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
        Document document = new OdtDocument(ResourcesManager.getResourceFile("T.docx"), documentProperties);
//        document.replace("${Replace}", "Value_1");
        document.saveDocumentAs(new File("C:/Users/hp/IdeaProjects/XmlDocumentProcessing/File1.docx"), DocumentConvertTypes.MS_WORD_2007_XML);
//        document.close();

    }
}