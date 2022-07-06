package com.document.processing.libreoffice.uno.components;

import com.document.processing.DocumentProperties;
import com.document.processing.libreoffice.document.TextDocument;
import com.document.processing.libreoffice.uno.components.frame.ComponentLoader;
import com.sun.star.io.IOException;
import com.sun.star.lang.XComponent;
import com.sun.star.text.XTextDocument;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.util.XReplaceDescriptor;
import com.sun.star.util.XReplaceable;

import java.io.File;

public final class LibreOfficeUnoManager {
    private final ComponentLoader componentLoader;
    private XComponent component;
    private XTextDocument textDocument;
    private XReplaceable replaceable;
    private XReplaceDescriptor replaceDescriptor;

    public LibreOfficeUnoManager() {
        componentLoader = new ComponentLoader();
    }

    public TextDocument openDocument(File file, DocumentProperties odtDocumentProperties) throws IOException {
        return componentLoader.loadTextDocument(file, odtDocumentProperties);
    }

    public TextDocument openDocument(File file) throws IOException {
        return componentLoader.loadTextDocument(file);
    }

    public LibreOfficeUnoManager(ComponentService defaultLoadInstanceWithContext) {
        componentLoader = new ComponentLoader(defaultLoadInstanceWithContext);
    }

    private void initializeReplaceInDocument() {
        replaceable = UnoRuntime.queryInterface(XReplaceable.class, textDocument);
        replaceDescriptor = replaceable.createReplaceDescriptor();
    }

    private void setUpReplaceAction() {
        initializeReplaceInDocument();
    }

    private int replaceStringInDocument(String search, String replace) {
        replaceDescriptor.setSearchString(search);
        replaceDescriptor.setReplaceString(replace);
        return replaceable.replaceAll(replaceDescriptor);
    }

    public int replaceAll(String search, String replace) {
        setUpReplaceAction();
        return replaceStringInDocument(search, replace);
    }

}
