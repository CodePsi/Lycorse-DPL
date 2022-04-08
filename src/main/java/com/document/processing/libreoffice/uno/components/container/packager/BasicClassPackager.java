package com.document.processing.libreoffice.uno.components.container.packager;

import com.sun.star.uno.UnoRuntime;

public abstract class BasicClassPackager {
    private final Object baseObject;

    protected BasicClassPackager(Object baseObject) {
        this.baseObject = baseObject;
    }

    @SuppressWarnings("unchecked")
    public <T> T getCastedObject(Class<T> tClass) {
        return UnoRuntime.queryInterface(tClass, baseObject);
    }

}
