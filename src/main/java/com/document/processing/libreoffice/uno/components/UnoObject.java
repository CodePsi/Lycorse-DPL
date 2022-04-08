package com.document.processing.libreoffice.uno.components;

import com.sun.star.uno.Any;

public class UnoObject {
    public final Any unoObject;

    public UnoObject(Object unoObject) {
        this.unoObject = Any.complete(unoObject);
    }

    public boolean is(Class<?> instance) {
        return unoObject.getType().getZClass().equals(instance);
    }
}
