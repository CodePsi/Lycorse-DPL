package com.document.processing.libreoffice.uno.components.frame;

import com.document.processing.DocumentProperties;
import com.document.processing.libreoffice.OdtFilePathHandler;
import com.document.processing.libreoffice.document.TextDocument;
import com.document.processing.libreoffice.properties.OdtDocumentProperties;
import com.document.processing.libreoffice.uno.components.ComponentService;
import com.document.processing.libreoffice.uno.components.FrameSearchFlag;
import com.document.processing.libreoffice.uno.components.TargetFrame;
import com.sun.star.comp.helper.Bootstrap;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.frame.XDesktop;
import com.sun.star.frame.XModel;
import com.sun.star.io.IOException;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.text.XTextDocument;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

import java.io.File;

public class ComponentLoader {
    private XComponentContext componentContext;
    private XMultiComponentFactory multiComponentFactory;
    private XComponentLoader xComponentLoader;
    private XDesktop desktop;
    private XComponent component;

    private Object instanceWithContext;


    public ComponentLoader() {
        this(ComponentService.DESKTOP);
    }

    public ComponentLoader(ComponentService defaultLoadInstanceWithContext) {
        this.initializeContext();
        this.loadComponentLoader(defaultLoadInstanceWithContext);
    }

    private void initializeContext() {
        try {
            componentContext = Bootstrap.bootstrap();
            multiComponentFactory = componentContext.getServiceManager();
        } catch (BootstrapException e) {
            e.printStackTrace();
        }
    }

    private Object createInstanceWithContext(String service) throws Exception {
        return multiComponentFactory.createInstanceWithContext(service, componentContext);
    }

    private Object createInstanceWithContext(ComponentService service) throws Exception {
        return createInstanceWithContext(service.getService());
    }

    private Object loadInstanceWithContext(ComponentService service) throws Exception {
        instanceWithContext = createInstanceWithContext(service);
        desktop = UnoRuntime.queryInterface(XDesktop.class, instanceWithContext);
        return instanceWithContext;
    }

    private void loadComponentLoader(ComponentService defaultLoadInstanceWithContext) {
        Object objectInstanceOfContext = instanceWithContext;
        if (instanceWithContext == null) {
            try {
                objectInstanceOfContext = loadInstanceWithContext(defaultLoadInstanceWithContext);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        xComponentLoader = UnoRuntime.queryInterface(XComponentLoader.class, objectInstanceOfContext);
    }

    private TextDocument loadTextComponent(String filepath, TargetFrame targetFrame, FrameSearchFlag frameSearchFlag, DocumentProperties documentProperties) throws IOException {
        component = xComponentLoader.loadComponentFromURL(filepath, targetFrame.getTargetFrameName(), frameSearchFlag.getFrameSearchFlag(), documentProperties.getPropertyValuesAsArray());
        XTextDocument textDocument = UnoRuntime.queryInterface(XTextDocument.class, component);
        return new TextDocument(textDocument, this);
    }

    private TextDocument loadTextComponent(String filepath, TargetFrame targetFrame, FrameSearchFlag frameSearchFlag) throws IOException {
        return loadTextComponent(filepath, targetFrame, frameSearchFlag, new OdtDocumentProperties());
    }

    private TextDocument loadTextComponent(String filepath, TargetFrame targetFrame) throws IOException {
        return loadTextComponent(filepath, targetFrame, FrameSearchFlag.PARENT);
    }

    private TextDocument loadTextComponent(String filepath, TargetFrame targetFrame, DocumentProperties documentProperties) throws IOException {
        return loadTextComponent(filepath, targetFrame, FrameSearchFlag.PARENT, documentProperties);
    }

    private TextDocument loadTextComponent(String filepath) throws IOException {
        return loadTextComponent(filepath, TargetFrame.BLANK);
    }

    private TextDocument loadTextComponent(String filepath, DocumentProperties documentProperties) throws IOException {
        return loadTextComponent(filepath, TargetFrame.BLANK, documentProperties);
    }

    public TextDocument loadTextDocument(String filepath) throws IOException {
        if (!OdtFilePathHandler.isNormalizedFilepath(filepath)) {
            filepath = OdtFilePathHandler.normalizeFilepath(filepath);
        }

        return loadTextDocument(filepath, new OdtDocumentProperties());
    }

    public TextDocument loadTextDocument(File file) throws IOException {
        return loadTextDocument(OdtFilePathHandler.normalizeFilepath(file), new OdtDocumentProperties());
    }

    public TextDocument loadTextDocument(File file, DocumentProperties documentProperties) throws IOException {
        return loadTextDocument(OdtFilePathHandler.normalizeFilepath(file), documentProperties);
    }

    public TextDocument loadTextDocument(String filepath, DocumentProperties documentProperties) throws IOException {
        return loadTextComponent(filepath, documentProperties);
    }

    public void terminate() {
        desktop.terminate();
    }

    public ComponentModel getComponentModal() {
        return new ComponentModel(UnoRuntime.queryInterface(XModel.class, desktop.getCurrentComponent()));
    }

    public XComponent getComponent() {
        return component;
    }

}
