package com.document.processing.libreoffice.properties;

import com.sun.star.beans.PropertyValue;
import com.sun.star.beans.PropertyVetoException;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.beans.XPropertySet;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.uno.UnoRuntime;

public class PropertyValueWrapper {
    private final PropertyValue propertyValue;

    public PropertyValueWrapper(String name, Object value) {
        this.propertyValue = new PropertyValue();
        this.propertyValue.Name = name;
        this.propertyValue.Value = value;
    }

    public String getName() {
        return propertyValue.Name;
    }

    public void setName(String name) {
        this.propertyValue.Name = name;
    }

    public Object getValue() {
        return propertyValue.Value;
    }

    public void setValue(Object value) {
        this.propertyValue.Value = value;
    }

    public PropertyValue getPropertyValue() {
        return propertyValue;
    }

    public static void setPropertyValueForObject(Object object, PropertySetValues propertySetValues, Object value) {
        try {
            XPropertySet propertySet = UnoRuntime.queryInterface(XPropertySet.class, object);
            propertySet.setPropertyValue(propertySetValues.getValue(), value);
        } catch (UnknownPropertyException | PropertyVetoException | WrappedTargetException e) {
            e.printStackTrace();
        }
    }

    public static void setPropertyValueArrayForObject(Object object, OdtDocumentProperties odtDocumentProperties) {
        try {
            XPropertySet propertySet = UnoRuntime.queryInterface(XPropertySet.class, object);
            for (PropertyValueWrapper propertyValueWrapper : odtDocumentProperties.getPropertyValues()) {
                propertySet.setPropertyValue(propertyValueWrapper.getName(), propertyValueWrapper.getValue());
            }
        } catch (UnknownPropertyException | PropertyVetoException | WrappedTargetException e) {
            e.printStackTrace();
        }
    }
}
