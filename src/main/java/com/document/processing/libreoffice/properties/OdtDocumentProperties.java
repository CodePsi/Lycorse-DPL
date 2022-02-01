package com.document.processing.libreoffice.properties;

import com.document.processing.DocumentProperties;
import com.sun.star.beans.PropertyValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OdtDocumentProperties implements DocumentProperties {
    private final List<PropertyValueWrapper> propertyValues;
    public static final String HIDDEN = "Hidden";
    public static final String FILTER_NAME = "FilterName";
    public static final String OVERWRITE = "Overwrite";
    public static final String AS_TEMPLATE = "AsTemplate";
    public static final PropertyValue[] EMPTY_PROPERTY = new PropertyValue[0];


    public OdtDocumentProperties() {
        propertyValues = new ArrayList<>();
    }

    @Override
    public void addProperty(String name, Object value) {
        PropertyValueWrapper propertyValueWrapper = new PropertyValueWrapper(name, value);
        propertyValues.add(propertyValueWrapper);
    }

    @Override
    public PropertyValueWrapper getProperty(String name) {
        Optional<PropertyValueWrapper> property = propertyValues.stream().filter(value -> value.getName().equals(name)).findFirst();
        return property.orElseGet(() -> new PropertyValueWrapper("null", "null"));
    }

    public PropertyValue[] getPropertyValuesAsArray() {
        return propertyValues.stream().map(PropertyValueWrapper::getPropertyValue).toList().toArray(new PropertyValue[0]);
    }

    @Override
    public List<PropertyValueWrapper> getPropertyValues() {
        return propertyValues;
    }
}
