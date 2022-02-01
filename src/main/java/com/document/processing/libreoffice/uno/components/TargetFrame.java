package com.document.processing.libreoffice.uno.components;

public enum TargetFrame {
    BLANK("_blank"),
    DEFAULT("_default"),
    SELF("_self"),
    PARENT("_parent"),
    TOP("_top"),
    BEAMER("_beamer");

    private String targetFrameName;

    TargetFrame(String targetFrameName) {
        this.targetFrameName = targetFrameName;
    }

    public String getTargetFrameName() {
        return targetFrameName;
    }

    public void setTargetFrameName(String targetFrameName) {
        this.targetFrameName = targetFrameName;
    }
}
