package com.document.processing.libreoffice.uno.components;


import com.document.processing.libreoffice.properties.PropertySetValues;
import com.document.processing.libreoffice.properties.PropertyValueWrapper;
import com.document.processing.libreoffice.properties.text.TextWeight;
import com.sun.star.beans.PropertyVetoException;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.beans.XPropertySet;
import com.sun.star.container.NoSuchElementException;
import com.sun.star.container.XEnumeration;
import com.sun.star.container.XEnumerationAccess;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.style.ParagraphAdjust;
import com.sun.star.text.XText;
import com.sun.star.text.XTextContent;
import com.sun.star.text.XTextCursor;
import com.sun.star.text.XTextRange;
import com.sun.star.uno.UnoRuntime;
import org.jfree.layouting.input.style.keys.font.FontWeight;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Text {
    private final XTextRange nativeTextRange;
    private Cursor cursor;


    public Text(XTextRange nativeTextRange) {
        this.nativeTextRange = nativeTextRange;
    }

    public String getText() {
        if (cursor != null) {
            return cursor.getTextCursor().getString();
        }
        XText xText = nativeTextRange.getText();
        XTextCursor xTextCursor = xText.createTextCursorByRange(nativeTextRange);
        return xTextCursor.getString();
    }

    public void setText(String newText) {
        createTextCursor().setString(newText);
    }

    public void addStringBeforeText(String text) {
        nativeTextRange.getStart().setString(text);
    }

    public void addStringAfterText(String text) {
        nativeTextRange.getEnd().setString(text);
    }

    private XTextCursor createTextCursor() {
        return nativeTextRange.getText().createTextCursorByRange(nativeTextRange);
    }

    public Cursor createCursor() {
        return (cursor = new Cursor(createTextCursor()));
    }

    public Cursor getCursor() {
        return cursor;
    }

    public XTextRange nativeTextRange() {
        return nativeTextRange;
    }

    public List<Text> getAllParagraphs() throws WrappedTargetException, NoSuchElementException {
        List<Text> paragraphs = new ArrayList<>();
        XEnumerationAccess enumerationAccess = UnoRuntime.queryInterface(XEnumerationAccess.class, nativeTextRange.getText());
        XEnumeration enumeration = enumerationAccess.createEnumeration();

        while (enumeration.hasMoreElements()) {
            Object element = enumeration.nextElement();
            XServiceInfo serviceInfo = UnoRuntime.queryInterface(XServiceInfo.class, element);
            if (!serviceInfo.supportsService(ComponentService.TEXT_TABLE.getService())) {
                XTextContent textContent = UnoRuntime.queryInterface(XTextContent.class, element);
                paragraphs.add(new Text(textContent.getAnchor()));
            }
        }


        return paragraphs;
    }

    public void setBoldText() {
        setTextWeight(TextWeight.BOLD);
    }

    public void setNormalText() {
        setTextWeight(TextWeight.NORMAL);
    }

    private XTextCursor getCurrentCursorOrCreateNew() {
        return (cursor != null) ? cursor.getTextCursor() : createTextCursor();
    }

    public void setTextWeight(TextWeight weight) {
        PropertyValueWrapper.setPropertyValueForObject(getCurrentCursorOrCreateNew(),
                PropertySetValues.CHAR_WEIGHT,
                weight.getValue());
    }

    public void setCenteredAdjustment() {
        setParagraphAdjustment(ParagraphAdjust.CENTER);
    }

    public void setLeftAdjustment() {
        setParagraphAdjustment(ParagraphAdjust.LEFT);
    }

    public void setRightAdjustment() {
        setParagraphAdjustment(ParagraphAdjust.RIGHT);
    }

    public void setJustifiedAdjustment() {
        setParagraphAdjustment(ParagraphAdjust.STRETCH);
    }

    public void setParagraphAdjustment(ParagraphAdjust paragraphAdjustment) {
        PropertyValueWrapper.setPropertyValueForObject(getCurrentCursorOrCreateNew(),
                PropertySetValues.PARAGRAPH_ADJUSTMENT,
                paragraphAdjustment.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Text) obj;
        return Objects.equals(this.nativeTextRange, that.nativeTextRange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nativeTextRange);
    }

    @Override
    public String toString() {
        return "Text[" +
                "nativeTextRange=" + nativeTextRange + ']';
    }

}
