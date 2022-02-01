package com.document.processing.libreoffice.uno.components;

public enum ComponentService {
    DESKTOP("com.sun.star.frame.Desktop"),
    TEXT_TABLE("com.sun.star.text.TextTable");

    private String service;

    ComponentService(String service) {
        this.service = service;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
