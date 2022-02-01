package com.document.processing.libreoffice.properties;

public enum OdtProperties {
    HIDDEN("Hidden");

    private String name;

    OdtProperties(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
