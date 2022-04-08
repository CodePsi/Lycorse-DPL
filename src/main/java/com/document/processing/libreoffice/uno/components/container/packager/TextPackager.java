package com.document.processing.libreoffice.uno.components.container.packager;

import com.document.processing.libreoffice.uno.components.Text;
import com.sun.star.text.XTextContent;

public class TextPackager extends BasicClassPackager implements Packager<Text> {

    public TextPackager(Object baseObject) {
        super(baseObject);
    }

    @Override
    public Text getObject() {
        return Text.of(getCastedObject(XTextContent.class));
    }
}
