package com.document.processing.pattern;

import com.document.xml.parser.DocumentXmlPatternAdjustProcessor;
import com.document.zip.ZipArchive;
import com.document.zip.ZipFileContent;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TextDocumentPatternsAdjust implements DocumentPatternsAdjust {
    private Pattern pattern;
    private List<String> foundPatterns;
    private File archive;
    public TextDocumentPatternsAdjust() {}

    public TextDocumentPatternsAdjust(Pattern pattern) {
        this.pattern = pattern;
    }

    public TextDocumentPatternsAdjust(File archive, Pattern pattern) {
        this.archive = archive;
        this.pattern = pattern;
    }

    @Override
    public String adjustPatterns() throws IOException {
        return adjustPatterns(archive);
    }
    @Override
    public String adjustPatterns(File archive) throws IOException {
        return adjustPatterns(archive, pattern);
    }

    @Override
    public String adjustPatterns(File archive, Pattern pattern) throws IOException {
        ZipArchive zipArchive = new ZipArchive(archive.getAbsolutePath());
        zipArchive.loadFiles();
        String documentXmlPath = determineDocumentXmlPath(archive.getName());
        ZipFileContent zipFileContent = zipArchive.getLoadedEntry(documentXmlPath);
        String xmlContent = zipFileContent.getFileContent();
        DocumentXmlPatternAdjustProcessor patternAdjustProcessor = new DocumentXmlPatternAdjustProcessor(xmlContent, pattern);
        foundPatterns = patternAdjustProcessor.processXml();
        String adjustedContent = patternAdjustProcessor.getXmlContent();
        zipArchive.setEntryContent(documentXmlPath, adjustedContent);
        zipArchive.saveArchive();
        return adjustedContent;
    }

    private String determineDocumentXmlPath(String documentName) {
        String documentType = documentName.substring(documentName.lastIndexOf('.') + 1);
        return switch (documentType) {
            case "odt" -> "content.xml";
            case "docx", "doc" -> "word/document.xml";
            default -> "";
        };

    }
    @Override
    public List<String> getFoundPatterns() {
        return foundPatterns;
    }
}
