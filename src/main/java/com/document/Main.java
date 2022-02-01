package com.document;

import com.document.processing.Document;
import com.document.processing.DocumentManager;
import com.document.processing.DocumentManagerProvider;
import com.document.processing.libreoffice.uno.components.LibreOfficeUnoManager;
import com.document.processing.libreoffice.uno.components.Text;
import com.document.resources.manager.ResourcesManager;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.uno.Exception;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class Main {
    public static void main(String[] args) throws BootstrapException, Exception {
File resourceFile = ResourcesManager.getResourceFile("TestDocument.odt");
DocumentManager documentManager = DocumentManagerProvider.createDocumentManager(resourceFile);
Document document = documentManager.openDocument(resourceFile);
document.replace("${Search}", "Value");
document.saveDocument();
    }
}
