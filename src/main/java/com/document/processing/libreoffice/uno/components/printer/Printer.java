package com.document.processing.libreoffice.uno.components.printer;

import com.document.processing.libreoffice.properties.OdtDocumentProperties;
import com.document.processing.libreoffice.properties.PropertyWrapper;
import com.sun.star.beans.PropertyValue;
import com.sun.star.lang.XComponent;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.view.XPrintable;

public class Printer {
    private final XPrintable printable;

    public Printer(XComponent component) {
        printable = UnoRuntime.queryInterface(XPrintable.class, component);
    }

    public void printAsPDF() {
        printable.print(PropertyWrapper.getEmptyPropertyValueArray());
    }

    public void printAsPDF(PrinterPropertiesSet printerPropertiesSet) {
        printable.print(printerPropertiesSet.getOdtDocumentProperties().getPropertyValuesAsArray());
    }
}
