package com.document.processing.libreoffice.uno.components.table;

import com.sun.star.table.CellContentType;

public enum CellType {
    EMPTY("EMPTY"),
    CONSTANT_VALUE("CONSTANT_VALUE"),
    TEXT("TEXT"),
    FORMULA("FORMULA");

    private String type;

    CellType(String type) {
        this.type = type;
    }

    public static CellType getFrom(CellContentType cellContentType) {
        return switch (cellContentType.getValue()) {
            case 1 -> CONSTANT_VALUE;
            case 2 -> TEXT;
            case 3 -> FORMULA;
            default -> EMPTY;
        };
    }

    public String getType() {
        return type;
    }
}
