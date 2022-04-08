package com.document.processing.libreoffice.uno.components.printer;

import com.document.processing.libreoffice.properties.OdtDocumentProperties;

public class PrinterPropertiesSet {
    private OdtDocumentProperties odtDocumentProperties;

    public PrinterPropertiesSet(OdtDocumentProperties odtDocumentProperties) {
        this.odtDocumentProperties = odtDocumentProperties;
    }

    public PrinterPropertiesSet setFileName(String fileName) {
        odtDocumentProperties.addProperty(PrintOptions.FILE_NAME.getValue(), fileName);
        return this;
    }

    public PrinterPropertiesSet setCountOfCopies(int countOfCopies) {
        odtDocumentProperties.addProperty(PrintOptions.COPY_COUNT.getValue(), countOfCopies);
        return this;
    }

    public PrinterPropertiesSet setCollatePages(boolean collatePages) {
        odtDocumentProperties.addProperty(PrintOptions.COLLATE.getValue(), collatePages);
        return this;
    }

    public PrinterPropertiesSet neededPages(String pages) {
        odtDocumentProperties.addProperty(PrintOptions.PAGES.getValue(), pages);
        return this;
    }

    public OdtDocumentProperties getOdtDocumentProperties() {
        return odtDocumentProperties;
    }
}
