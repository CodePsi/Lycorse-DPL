package com.document.processing.libreoffice.properties;

public enum PropertySetValues {
    CHAR_WEIGHT("CharWeight"),
    PARAGRAPH_ADJUSTMENT("ParaAdjust");

    private String value;
    PropertySetValues(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
