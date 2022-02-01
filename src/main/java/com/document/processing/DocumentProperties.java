package com.document.processing;

import com.document.processing.libreoffice.properties.PropertyValueWrapper;
import com.sun.star.beans.PropertyValue;

import java.util.List;

public interface DocumentProperties {
    void addProperty(String name, Object value);
    PropertyValueWrapper getProperty(String name);
    PropertyValue[] getPropertyValuesAsArray();
    List<PropertyValueWrapper> getPropertyValues();

}
