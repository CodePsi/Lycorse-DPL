package com.document.processing.libreoffice.properties;

import com.sun.star.beans.Property;
import com.sun.star.beans.PropertyValue;
import com.sun.star.uno.Type;

import java.util.ArrayList;
import java.util.List;

public class PropertyWrapper {
    private String name;
    private int handle;
    private Type type;
    private short attributes;

    public PropertyWrapper(String name, int handle, Type type, short attributes) {
        this.name = name;
        this.handle = handle;
        this.type = type;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHandle() {
        return handle;
    }

    public void setHandle(int handle) {
        this.handle = handle;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public short getAttributes() {
        return attributes;
    }

    public void setAttributes(short attributes) {
        this.attributes = attributes;
    }

    public static List<PropertyWrapper> createFrom(Property[] properties) {
        List<PropertyWrapper> propertyWrapperList = new ArrayList<>(properties.length);
        for (Property property: properties) {
            propertyWrapperList.add(new PropertyWrapper(property.Name, property.Handle, property.Type, property.Attributes));
        }

        return propertyWrapperList;
    }

    public static PropertyValue[] getEmptyPropertyValueArray() {
        return new PropertyValue[0];
    }

}
