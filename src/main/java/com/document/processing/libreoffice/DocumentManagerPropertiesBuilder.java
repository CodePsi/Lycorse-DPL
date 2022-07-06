package com.document.processing.libreoffice;

import com.document.processing.DocumentProperties;
import com.document.processing.libreoffice.properties.OdtDocumentProperties;

public final class DocumentManagerPropertiesBuilder {
    private final DocumentProperties documentProperties;

    private DocumentManagerPropertiesBuilder() {
        documentProperties = new OdtDocumentProperties();
    }

    public static DocumentManagerPropertiesBuilder instance() {
        return new DocumentManagerPropertiesBuilder();
    }
    /**
     * This option handles document without opening a GUI.
     *
     * @param value value for property
     */
    public DocumentManagerPropertiesBuilder hidden(boolean value) {
        documentProperties.addProperty(OdtDocumentProperties.HIDDEN, value);
        return this;
    }

    /**
     * This option handles document as a template. This means that everytime when document has been opened
     * the actual document won't be changed, a copy of the document will be created instead.
     *
     * @param value value for property
     */
    public DocumentManagerPropertiesBuilder asTemplate(boolean value) {
        documentProperties.addProperty(OdtDocumentProperties.AS_TEMPLATE, value);
        return this;
    }

    public DocumentProperties getPropertyHandler() {
        return documentProperties;
    }

    public DocumentProperties build() {
        return documentProperties;
    }
}
