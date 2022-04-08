package com.document.processing.libreoffice.uno.components.container;

import com.sun.star.container.XEnumeration;
import com.sun.star.container.XEnumerationAccess;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XInterface;

public class DocumentContainer {
    private XInterface baseObject;

    public DocumentContainer(XInterface baseObject) {
        this.baseObject = baseObject;
    }

    public ContainerEnumerator createEnumeration() {
        XEnumerationAccess enumerationAccess = UnoRuntime.queryInterface(XEnumerationAccess.class, baseObject);
        XEnumeration enumeration = enumerationAccess.createEnumeration();
        return new ContainerEnumerator(enumeration);
    }


}
