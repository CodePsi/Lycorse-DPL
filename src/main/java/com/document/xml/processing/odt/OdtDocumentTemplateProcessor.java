package com.document.xml.processing.odt;

import com.document.resources.manager.ResourcesManager;
import com.document.xml.parser.Handler;
import com.document.xml.processing.DocumentTemplate;
import com.document.zip.ZipArchive;
import com.document.zip.ZipFileContent;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class OdtDocumentTemplateProcessor {
    private String content;
    private DocumentTemplate documentTemplate;
    private final String CONTENT_FILE_PATH = "content.xml";

    public OdtDocumentTemplateProcessor(DocumentTemplate documentTemplate) {
        this.documentTemplate = documentTemplate;
    }

    public void loadDocument() throws IOException {
        ZipArchive zipArchive = new ZipArchive(documentTemplate.getFileName());
        ZipFileContent zipFileContent = zipArchive.getFile(CONTENT_FILE_PATH);
        documentTemplate.setZipFileContent(zipFileContent);
    }

    public List<String> findAllTemplatePatterns() throws JAXBException, ParserConfigurationException, SAXException, IOException {
        StringReader stringReader = new StringReader("""
                <?xml version="1.0 " encoding="UTF-8"?>
                <office:document-content xmlns:office="urn:oasis:names:tc:opendocument:xmlns:office:1.0">
                </office:document-content>
                """);
//        JAXBContext jaxbContext = JAXBContext.newInstance(DocumentContent.class);
//        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//        System.out.println(unmarshaller.unmarshal(stringReader));
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.newSAXParser().parse(new File(ResourcesManager.getResourceFilepath("test.xml")), new Handler());
        return null;
    }

}