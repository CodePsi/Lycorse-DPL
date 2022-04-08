package com.document.processing.libreoffice.uno.components.search;

import com.document.processing.libreoffice.uno.components.LibreOfficeUnoManager;
import com.document.processing.libreoffice.uno.components.Text;
import com.document.resources.manager.ResourcesManager;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextDocumentSearchTest {

    @Test
    void findAllByRegex() throws BootstrapException, Exception {
        LibreOfficeUnoManager libreOfficeUnoManager = new LibreOfficeUnoManager();
        libreOfficeUnoManager.openDocument(ResourcesManager.getResourceFile("TestDocument.odt"));
        List<Text> allByRegex = libreOfficeUnoManager.search().findAllByRegex("Test\\d?");
        assertEquals(1, allByRegex.size());
        assertEquals("Test1", allByRegex.get(0).getText());
        libreOfficeUnoManager.closeDocument();
    }
}