package com.document.processing.libreoffice.uno.components;

import com.sun.star.beans.PropertyValue;
import com.sun.star.frame.XDesktop;
import com.sun.star.lang.XComponent;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.view.XPrintable;

public class Printer {
    private final XPrintable printable;

    public Printer(XComponent component) {
        printable = UnoRuntime.queryInterface(XPrintable.class, component);
    }

    public void printAsPDF() {
        printable.print(new PropertyValue[0]);
    }
}
