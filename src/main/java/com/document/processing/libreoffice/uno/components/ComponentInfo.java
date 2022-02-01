package com.document.processing.libreoffice.uno.components;

import com.document.processing.libreoffice.properties.PropertyWrapper;
import com.sun.star.beans.Property;
import com.sun.star.beans.XPropertySet;
import com.sun.star.beans.XPropertySetInfo;
import com.sun.star.uno.UnoRuntime;

import java.util.List;

public final class ComponentInfo {

    public static <T> List<PropertyWrapper> getPropertySetInfo(T object) {
        XPropertySet propertySet = UnoRuntime.queryInterface(XPropertySet.class, object);
        return PropertyWrapper.createFrom(propertySet.getPropertySetInfo().getProperties());
    }

}
