package com.document.processing.libreoffice.uno.components;

import com.document.processing.libreoffice.uno.components.printer.Printer;
import com.document.resources.manager.ResourcesManager;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;
import org.junit.jupiter.api.Test;

class PrinterTest {

    @Test
    void print() throws BootstrapException, Exception {
        LibreOfficeUnoManager libreOfficeUnoManager = new LibreOfficeUnoManager();
        libreOfficeUnoManager.openDocument(ResourcesManager.getResourceFile("TestDocument.odt"));
        Printer printer = libreOfficeUnoManager.initializePrinter();
        printer.printAsPDF();
    }
}