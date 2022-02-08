package com.document.processing.libreoffice.uno.components;

import com.document.resources.manager.ResourcesManager;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {

    @Test
    void print() throws BootstrapException, Exception {
        LibreOfficeUnoManager libreOfficeUnoManager = new LibreOfficeUnoManager();
        libreOfficeUnoManager.openDocument(ResourcesManager.getResourceFile("TestDocument.odt"));
        Printer printer = libreOfficeUnoManager.initializePrinter();
        printer.printAsPDF();
    }
}