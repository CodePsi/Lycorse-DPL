package com.document.processing.libreoffice.uno.components.frame;

import com.sun.star.frame.XModel;

public class ComponentModel {
    private XModel model;

    public ComponentModel(XModel model) {
        this.model = model;
    }

    public Controller getController() {
        return new Controller(model.getCurrentController());
    }
}
