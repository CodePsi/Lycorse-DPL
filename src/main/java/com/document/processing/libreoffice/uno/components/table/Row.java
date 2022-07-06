package com.document.processing.libreoffice.uno.components.table;

import com.sun.star.text.XTextTable;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private List<Cell> cells;

    public Row() {
        cells = new ArrayList<>();
    }

    public static List<Row> of(XTextTable textTable) {
        int rowsLength = textTable.getRows().getCount();
        int columnsLength = textTable.getColumns().getCount();
        List<Row> rows = new ArrayList<>();
        String[] cellNames = textTable.getCellNames();
        for (int i = 0; i < rowsLength; i++) {
            Row row = new Row();
            for (int j = i; j < columnsLength + i; j++) {
                String cellName = cellNames[i + j];
                row.cells.add(new Cell(cellName, textTable.getCellByName(cellName)));
            }
            rows.add(row);
        }

        return rows;
    }

    protected void addCell(Cell cell) {
        cells.add(cell);
    }

    public List<Cell> getCells() {
        return cells;
    }
}
