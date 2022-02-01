package com.document.processing.libreoffice.uno.components;

public enum FrameSearchFlag {
    PARENT(com.sun.star.frame.FrameSearchFlag.PARENT),
    SELF(com.sun.star.frame.FrameSearchFlag.SELF),
    CHILDREN(com.sun.star.frame.FrameSearchFlag.CHILDREN),
    CREATE(com.sun.star.frame.FrameSearchFlag.CREATE),
    SIBLINGS(com.sun.star.frame.FrameSearchFlag.SIBLINGS),
    TASKS(com.sun.star.frame.FrameSearchFlag.TASKS),
    ALL(com.sun.star.frame.FrameSearchFlag.ALL),
    GLOBAL(com.sun.star.frame.FrameSearchFlag.GLOBAL);

    private int frameSearchFlag;

    FrameSearchFlag(int frameSearchFlag) {
        this.frameSearchFlag = frameSearchFlag;
    }

    public int getFrameSearchFlag() {
        return frameSearchFlag;
    }

    public void setFrameSearchFlag(int frameSearchFlag) {
        this.frameSearchFlag = frameSearchFlag;
    }
}
