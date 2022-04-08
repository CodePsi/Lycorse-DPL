package com.document.processing.libreoffice.uno.components;

import com.sun.star.text.XParagraphCursor;
import com.sun.star.text.XSentenceCursor;
import com.sun.star.text.XTextCursor;
import com.sun.star.text.XWordCursor;
import com.sun.star.uno.UnoRuntime;

import java.util.Objects;

public final class Cursor {
    private final XTextCursor textCursor;
    private XTextCursor latelyUsedCursor; // May be removed

    public Cursor(XTextCursor textCursor) {
        this.textCursor = textCursor;
    }

    public Cursor gotoNextText(Text text) {
        return gotoNextText(text, true);
    }

    public Cursor gotoNextText(Text text, boolean selectText) {
        textCursor.gotoRange(text.nativeTextRange(), selectText);
        return this;
    }

    public Cursor gotoNextWord() {
        return gotoNextWord(true);
    }

    public Cursor gotoNextWord(boolean selectText) {
        XWordCursor wordCursor = UnoRuntime.queryInterface(XWordCursor.class, textCursor);
        wordCursor.gotoNextWord(selectText);
        latelyUsedCursor = wordCursor;
        return this;
    }

    public Cursor gotoPreviousWord() {
        return gotoPreviousWord(true);
    }

    public Cursor gotoPreviousWord(boolean selectText) {
        XWordCursor wordCursor = UnoRuntime.queryInterface(XWordCursor.class, textCursor);
        wordCursor.gotoPreviousWord(selectText);
        latelyUsedCursor = wordCursor;
        return this;
    }

    public Cursor gotoStartOfTheWord() {
        return gotoStartOfTheWord(true);
    }

    public Cursor gotoStartOfTheWord(boolean selectText) {
        XWordCursor wordCursor = UnoRuntime.queryInterface(XWordCursor.class, textCursor);
        wordCursor.gotoStartOfWord(selectText);
        latelyUsedCursor = wordCursor;
        return this;
    }

    public Cursor gotoEndOfTheWord() {
        return gotoEndOfTheWord(true);
    }

    public Cursor gotoEndOfTheWord(boolean selectText) {
        XWordCursor wordCursor = UnoRuntime.queryInterface(XWordCursor.class, textCursor);
        wordCursor.gotoEndOfWord(selectText);
        latelyUsedCursor = wordCursor;
        return this;
    }

    public boolean isStartOfTheWord() {
        XWordCursor wordCursor = UnoRuntime.queryInterface(XWordCursor.class, textCursor);
        return wordCursor.isStartOfWord();
    }

    public boolean isEndOfTheWord() {
        XWordCursor wordCursor = UnoRuntime.queryInterface(XWordCursor.class, textCursor);
        return wordCursor.isEndOfWord();
    }

    public Cursor gotoNextSentence() {
        return gotoNextSentence(true);
    }

    public Cursor gotoNextSentence(boolean selectText) {
        XSentenceCursor sentenceCursor = UnoRuntime.queryInterface(XSentenceCursor.class, textCursor);
        sentenceCursor.gotoNextSentence(selectText);
        return this;
    }

    public Cursor gotoPreviousSentence() {
        return gotoPreviousSentence(true);
    }

    public Cursor gotoPreviousSentence(boolean selectText) {
        XSentenceCursor sentenceCursor = UnoRuntime.queryInterface(XSentenceCursor.class, textCursor);
        sentenceCursor.gotoPreviousSentence(selectText);
        return this;
    }

    public Cursor gotoStartOfTheSentence() {
        return gotoStartOfTheSentence(true);
    }

    public Cursor gotoStartOfTheSentence(boolean selectText) {
        XSentenceCursor sentenceCursor = UnoRuntime.queryInterface(XSentenceCursor.class, textCursor);
        sentenceCursor.gotoStartOfSentence(selectText);
        return this;
    }

    public Cursor gotoEndOfTheSentence() {
        return gotoEndOfTheSentence(true);
    }

    public Cursor gotoEndOfTheSentence(boolean selectText) {
        XSentenceCursor sentenceCursor = UnoRuntime.queryInterface(XSentenceCursor.class, textCursor);
        sentenceCursor.gotoEndOfSentence(selectText);
        return this;
    }

    public Cursor goLeftBy(short byNumberOfSymbols, boolean selectText) {
        textCursor.goLeft(byNumberOfSymbols, selectText);
        return this;
    }

    public Cursor goRightBy(short byNumberOfSymbols, boolean selectText) {
        textCursor.goRight(byNumberOfSymbols, selectText);
        return this;
    }

    public Cursor gotoNextParagraph(boolean selectText) {
        XParagraphCursor paragraphCursor = UnoRuntime.queryInterface(XParagraphCursor.class, textCursor);
        paragraphCursor.gotoNextParagraph(selectText);

        return this;
    }

    public Cursor gotoNextParagraph() {
        return gotoNextParagraph(true);
    }



    public XTextCursor getTextCursor() {
        return textCursor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Cursor) obj;
        return Objects.equals(this.textCursor, that.textCursor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textCursor);
    }

    @Override
    public String toString() {
        return "Cursor[" +
                "textCursor=" + textCursor + ']';
    }

    public XTextCursor getLatelyUsedCursor() {
        return latelyUsedCursor;
    }
}
