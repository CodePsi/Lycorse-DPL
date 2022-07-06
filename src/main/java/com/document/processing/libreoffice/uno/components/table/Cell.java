package com.document.processing.libreoffice.uno.components.table;

import com.document.processing.libreoffice.uno.components.Text;
import com.sun.star.table.XCell;
import com.sun.star.text.XText;
import com.sun.star.uno.UnoRuntime;

import java.util.Objects;

public class Cell {
    private String cellName;
    private XCell cell;

    public Cell(String cellName, XCell cell) {
        this.cellName = cellName;
        this.cell = cell;
    }

    public CellType getCellType() {
        return CellType.getFrom(cell.getType());
    }

    public Text getText() {
        XText text = UnoRuntime.queryInterface(XText.class, cell);
        return Text.of(text);
    }

    public void setText(String newText) {
        getText().setText(newText);
    }

    public String getCellName() {
        return cellName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Cell) obj;
        return Objects.equals(this.cellName, that.cellName) &&
                Objects.equals(this.cell, that.cell);
    }

    public static String getCellNameByCoordinates(int row, int cell) {
        char letter = (char) ('A' + cell);
        return letter + "" + (row + 1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellName, cell);
    }

    @Override
    public String toString() {
        return "Cell[" +
                "cellName=" + cellName + ", " +
                "cell=" + cell + ']';
    }

}
