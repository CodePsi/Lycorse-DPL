package com.document.processing.libreoffice.uno.components;

import com.document.resources.manager.ResourcesManager;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;
import org.junit.jupiter.api.Test;

class TextTest {

    @Test
    void getText() throws BootstrapException, Exception {
        LibreOfficeUnoManager libreOfficeUnoManager = new LibreOfficeUnoManager();
        libreOfficeUnoManager.openDocument(ResourcesManager.getResourceFile("TestDocument.odt"));
        Text allDocumentText = libreOfficeUnoManager.findAllAsText("and").get(0);
allDocumentText.createCursor()
        .gotoStartOfTheSentence()
        .gotoNextSentence()
        .gotoNextWord()
        .gotoPreviousWord();
        allDocumentText.setCenteredAdjustment();
//        System.out.println(allDocumentText.getText());
//        System.out.println();

//        allDocumentText.setBoldText();

//        text.getCursor().goToNextText(libreOfficeUnoManager.findAllAsText("and").get(1), true);
//        libreOfficeUnoManager.closeDocument();
    }
}