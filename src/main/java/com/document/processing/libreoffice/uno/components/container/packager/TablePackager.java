package com.document.processing.libreoffice.uno.components.container.packager;

import com.document.processing.libreoffice.uno.components.table.Table;
import com.sun.star.text.XTextTable;

public class TablePackager extends BasicClassPackager implements Packager<Table> {

    public TablePackager(Object baseObject) {
        super(baseObject);
    }

    @Override
    public Table getObject() {
        return new Table(getCastedObject(XTextTable.class));
    }
}
