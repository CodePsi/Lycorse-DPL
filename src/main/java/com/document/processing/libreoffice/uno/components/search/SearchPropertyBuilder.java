package com.document.processing.libreoffice.uno.components.search;

import com.document.processing.libreoffice.properties.OdtDocumentProperties;
import com.document.processing.libreoffice.properties.PropertySetValues;
import com.document.processing.libreoffice.properties.PropertyValueWrapper;
import com.sun.star.util.XSearchDescriptor;

public final class SearchPropertyBuilder {
    private OdtDocumentProperties odtDocumentProperties;
    private XSearchDescriptor searchDescriptor;

    public SearchPropertyBuilder(XSearchDescriptor searchDescriptor) {
        this.odtDocumentProperties = new OdtDocumentProperties();
        this.searchDescriptor = searchDescriptor;
    }

    public SearchPropertyBuilder setRegularExpressionSearch(boolean value) {
        odtDocumentProperties.addProperty(PropertySetValues.SEARCH_REGULAR_EXPRESSION.getValue(), value);
        return this;
    }

    public void apply() {
        PropertyValueWrapper.setPropertyValueArrayForObject(searchDescriptor, odtDocumentProperties);
    }

    public OdtDocumentProperties getOdtDocumentProperties() {
        return odtDocumentProperties;
    }
}
