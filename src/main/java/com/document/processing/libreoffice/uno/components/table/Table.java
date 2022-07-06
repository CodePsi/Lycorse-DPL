package com.document.processing.libreoffice.uno.components.table;

import com.document.processing.libreoffice.uno.components.container.EnumeratorObject;
import com.document.processing.libreoffice.uno.components.cursor.TableCursor;
import com.sun.star.container.XIndexAccess;
import com.sun.star.container.XNameAccess;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.text.XTextDocument;
import com.sun.star.text.XTextTable;
import com.sun.star.text.XTextTablesSupplier;
import com.sun.star.uno.UnoRuntime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table {
    private final XTextTable textTable;

    public Table(XTextTable textTable) {
        this.textTable = textTable;
    }

    public List<Row> getAllRows() {
        return Row.of(textTable);
    }

    public List<String> getCellNames() {
        return Arrays.stream(textTable.getCellNames()).toList();
    }

    public static List<Table> getAllTables(XTextDocument document) throws IndexOutOfBoundsException, WrappedTargetException {
        XTextTablesSupplier textTablesSupplier = UnoRuntime.queryInterface(XTextTablesSupplier.class, document);
        XNameAccess nameAccess = textTablesSupplier.getTextTables();

        XIndexAccess indexAccess = UnoRuntime.queryInterface(XIndexAccess.class, nameAccess);
        List<Table> tables = new ArrayList<>(indexAccess.getCount());

        for (int i = 0; i < indexAccess.getCount(); i++) {
            Object textTable = indexAccess.getByIndex(i);
            new EnumeratorObject(textTable).ifTable(tablePackager -> tables.add(tablePackager.getObject()));
        }

        return tables;
    }

    public Row insertRow(int position) {
        return insertRow(position, 1);
    }

    public Row insertRow(int position, int amountOfRows) {
        textTable.getRows().insertByIndex(position, amountOfRows);
        return getRowByPosition(position);
    }

    public void copyAndInsertRow(int copyPosition, int insertPosition) {
        Row newRow = insertRow(insertPosition);
        Row copyRow = getRowByPosition(copyPosition);
        List<Cell> newCells = newRow.getCells();
        List<Cell> copyCells = copyRow.getCells();

        for (int i = 0; i < copyCells.size(); i++) {
            String copyText = copyCells.get(i).getText().getText();
            newCells.get(i).setText(copyText);
        }
    }

    public Row getRowByPosition(int row) {
        Row rowByPosition = new Row();
        int length = textTable.getColumns().getCount();
        for (int i = 0; i < length; i++) {
            String cellName = Cell.getCellNameByCoordinates(row, i);
            rowByPosition.addCell(new Cell(cellName, textTable.getCellByName(cellName)));
        }
        return rowByPosition;
    }

    public TableCursor createCursor() {
        return new TableCursor(textTable);
    }

    public XTextTable getNativeTable() {
        return textTable;
    }

}
