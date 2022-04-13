package com.document.processing.libreoffice.uno.components.container;

import com.document.processing.libreoffice.uno.components.LibreOfficeUnoManager;
import com.document.processing.libreoffice.uno.components.Text;
import com.document.resources.manager.ResourcesManager;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;
import org.junit.jupiter.api.Test;

class DocumentContainerTest {

    @Test
    void createEnumeration() throws BootstrapException, Exception {
        LibreOfficeUnoManager libreOfficeUnoManager = new LibreOfficeUnoManager();
        libreOfficeUnoManager.openDocument(ResourcesManager.getResourceFile("TestDocument.odt"));
        Text allDocumentText = libreOfficeUnoManager.search().getAllDocumentText();
        DocumentContainer documentContainer = new DocumentContainer(allDocumentText.nativeTextRange());
        ContainerEnumerator enumeration = documentContainer.createEnumeration();

        while (enumeration.hasMoreElements()) {
            EnumeratorObject enumeratorObject = enumeration.nextElement();
            enumeratorObject.ifText(textPackager -> {

            });
        }
    }
}