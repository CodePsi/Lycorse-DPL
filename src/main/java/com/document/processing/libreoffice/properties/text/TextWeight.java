package com.document.processing.libreoffice.properties.text;

import com.sun.star.awt.FontWeight;

public enum TextWeight {
    NO_FONT_WEIGHT(FontWeight.DONTKNOW),
    THIN(FontWeight.THIN),
    NORMAL(FontWeight.NORMAL),
    BOLD(FontWeight.ULTRABOLD);

    private float value;
    TextWeight(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
