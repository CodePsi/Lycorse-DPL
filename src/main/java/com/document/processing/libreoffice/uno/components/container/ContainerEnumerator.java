package com.document.processing.libreoffice.uno.components.container;

import com.sun.star.container.NoSuchElementException;
import com.sun.star.container.XEnumeration;
import com.sun.star.lang.WrappedTargetException;

public class ContainerEnumerator {
    private XEnumeration enumeration;

    public ContainerEnumerator(XEnumeration enumeration) {
        this.enumeration = enumeration;
    }

    public boolean hasMoreElements() {
        return enumeration.hasMoreElements();
    }

    public ContainerEnumeratorObject nextElement() throws WrappedTargetException, NoSuchElementException {
        return new ContainerEnumeratorObject(enumeration.nextElement());
    }
}
