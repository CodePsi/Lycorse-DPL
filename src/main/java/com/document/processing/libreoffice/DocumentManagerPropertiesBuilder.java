package com.document.processing.libreoffice;

import com.document.processing.DocumentProperties;
import com.document.processing.libreoffice.properties.OdtDocumentProperties;
import com.document.processing.libreoffice.properties.PropertyValueWrapper;

import java.util.List;

public final class DocumentManagerPropertiesBuilder {
    private final OdtDocumentProperties odtDocumentProperties;

    public DocumentManagerPropertiesBuilder() {
        this.odtDocumentProperties = new OdtDocumentProperties();
    }

    /**
     * This option handles document without opening a GUI.
     *
     * @param value value for property
     */
    public DocumentManagerPropertiesBuilder hidden(boolean value) {
        odtDocumentProperties.addProperty(OdtDocumentProperties.HIDDEN, value);
        return this;
    }

    /**
     * This option handles document as a template. This means that everytime when document has been opened
     * the actual document won't be changed, a copy of the document will be created instead.
     *
     * @param value value for property
     */
    public DocumentManagerPropertiesBuilder asTemplate(boolean value) {
        odtDocumentProperties.addProperty(OdtDocumentProperties.AS_TEMPLATE, value);
        return this;
    }

    public DocumentProperties getPropertyHandler() {
        return odtDocumentProperties;
    }
}