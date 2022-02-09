package com.document.processing.libreoffice.uno.components.printer;

public enum PrintOptions {
    COPY_COUNT("CopyCount"),
    FILE_NAME("FileName"),
    COLLATE("Collate"),
    PAGES("Pages"),
    WAIT("Wait"),
    DUPLEX_MODE("DuplexMode");

    private String value;

    PrintOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
