package com.document.processing.ms.word.components;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.util.List;

public class WordComponentManager {
    private File file;
    private XWPFDocument document;

    public void loadDocument(File file) throws IOException {
        this.file = file;
        document = new XWPFDocument(new FileInputStream(file));
    }

    private void replaceTextInParagraph(XWPFParagraph paragraph, String search, String replace) {
        List<XWPFRun> runs = paragraph.getRuns();
        for (XWPFRun run: runs) {
            String text = run.text();
            if (text.contains(search)) {
                run.setText(text.replace(search, replace));
            }
        }
    }

    private void replaceAllInParagraphs(String search, String replace) {
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        for (XWPFParagraph paragraph: paragraphs) {
            replaceTextInParagraph(paragraph, search, replace);
        }
    }

    public void replaceAll(String search, String replace) {
        replaceAllInParagraphs(search, replace);
    }

    public void saveDocument() throws IOException {
        document.write(new FileOutputStream(file));
    }
}
