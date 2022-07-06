package com.document.processing.libreoffice.uno.components.cursor;

import com.document.processing.libreoffice.uno.components.Text;
import com.sun.star.text.XTextRange;

public class TextRange {
    private XTextRange nativeTextRange;

    public TextRange(XTextRange textRange) {
        this.nativeTextRange = textRange;
    }

    public TextRange getStart() {
        return new TextRange(nativeTextRange.getStart());
    }

    public TextRange getEnd() {
        return new TextRange(nativeTextRange.getEnd());
    }

    public Text getText() {
        return new Text(nativeTextRange);
    }

    public Cursor createCursor() {
        return new Cursor(nativeTextRange.getText().createTextCursor());
    }

}
