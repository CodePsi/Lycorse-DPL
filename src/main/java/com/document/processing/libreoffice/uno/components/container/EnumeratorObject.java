package com.document.processing.libreoffice.uno.components.container;

import com.document.processing.libreoffice.uno.components.ComponentService;
import com.document.processing.libreoffice.uno.components.UnoObject;
import com.document.processing.libreoffice.uno.components.container.packager.TablePackager;
import com.document.processing.libreoffice.uno.components.container.packager.TextPackager;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.text.XTextContent;
import com.sun.star.uno.UnoRuntime;

import java.util.function.Consumer;

public class EnumeratorObject {
    private Object element;
    private XServiceInfo serviceInfo;

    public EnumeratorObject(Object element) {
        initialize(element);
    }

    private void initialize(Object element) {
        this.element = element;
        serviceInfo = UnoRuntime.queryInterface(XServiceInfo.class, this.element);
    }

    public String getObjectType() {
        return serviceInfo.getImplementationName().replace("Sw", "");
    }

    public boolean isService(ComponentService componentService) {
        return serviceInfo.supportsService(componentService.getService());
    }

    public void ifText(Consumer<TextPackager> consumer) {
        UnoObject unoObject = new UnoObject(element);
        if (unoObject.is(XTextContent.class) && !isService(ComponentService.TEXT_TABLE)) {
            consumer.accept(new TextPackager(element));
        }
    }

    public void ifTable(Consumer<TablePackager> consumer) {
        UnoObject unoObject = new UnoObject(element);
        if (isService(ComponentService.TEXT_TABLE)) {
            consumer.accept(new TablePackager(element));
        }
    }


}
