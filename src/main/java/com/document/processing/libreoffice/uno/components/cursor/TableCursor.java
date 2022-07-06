package com.document.processing.libreoffice.uno.components.cursor;

import com.sun.star.text.XTextContent;
import com.sun.star.text.XTextTable;
import com.sun.star.text.XTextTableCursor;
import com.sun.star.uno.UnoRuntime;

public class TableCursor {
    private XTextTable textTable;
    private XTextTableCursor textTableCursor;

    public TableCursor(XTextTable textTable) {
        this.textTable = textTable;
        this.textTableCursor = textTable.createCursorByCellName(textTable.getCellNames()[0]);
    }

    public TableCursor gotoCellByCellName(String cellName, boolean selectText) {
        textTableCursor.gotoCellByName(cellName, selectText);
        return this;
    }

    public TableCursor gotoCellByCellName(String cellName) {
        return gotoCellByCellName(cellName, true);
    }

    public TableCursor goLeftBy(short byNumberOfSymbols, boolean selectText) {
        textTableCursor.goLeft(byNumberOfSymbols, selectText);
        return this;
    }

    public TableCursor goLeftBy(short byNumberOfSymbols) {
        return goLeftBy(byNumberOfSymbols, true);
    }

    public TableCursor goRightBy(short byNumberOfSymbols, boolean selectText) {
        textTableCursor.goRight(byNumberOfSymbols, selectText);
        return this;
    }

    public TableCursor goRightBy(short byNumberOfSymbols) {
        return goRightBy(byNumberOfSymbols, true);
    }

    public TableCursor goUpBy(short byNumberOfSymbols, boolean selectText) {
        textTableCursor.goUp(byNumberOfSymbols, selectText);
        return this;
    }

    public TableCursor goUpBy(short byNumberOfSymbols) {
        return goUpBy(byNumberOfSymbols, true);
    }

    public TableCursor goDownBy(short byNumberOfSymbols, boolean selectText) {
        textTableCursor.goDown(byNumberOfSymbols, selectText);
        return this;
    }

    public TableCursor goDownBy(short byNumberOfSymbols) {
        return goDownBy(byNumberOfSymbols, true);
    }

    public TableCursor mergeSelectedCells() {
        textTableCursor.mergeRange();
        return this;
    }

    public TableCursor splitSelectedCells(short amountOfCells) {
        textTableCursor.splitRange(amountOfCells, false);
        return this;
    }

    public String getCellsRangeName() {
        return textTableCursor.getRangeName();
    }

    public XTextTable getCurrentCell() {
        return UnoRuntime.queryInterface(XTextTable.class, UnoRuntime.getCurrentContext());
    }

    public TableCursor gotoEnd(boolean selectText) {
        textTableCursor.gotoEnd(selectText);
        return this;
    }

    public TableCursor gotoEnd() {
        return gotoEnd(true);
    }

    public Cursor getAnchor() {
        return Cursor.of(textTable.getAnchor());
    }

    public XTextContent getTextContent() {
        return textTable;
    }
}
