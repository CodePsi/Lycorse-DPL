package com.document.xml.parser;

import com.document.processing.pattern.Pattern;

import java.util.List;

public class DocumentXmlPatternAdjustProcessor extends BaseXmlPatternAdjustProcessor implements XmlPatternsAdjustProcessor {

    public DocumentXmlPatternAdjustProcessor(String xmlContent, Pattern pattern) {
        super(xmlContent, pattern);
    }

    @Override
    public List<String> processXml(Pattern pattern, String xmlContent) {
        while (isNextPatternFound()) {
            String wholePattern = formPatternText();
            foundPatterns.add(wholePattern);
        }
        return foundPatterns;
    }

    private boolean isNextPatternFound() {
        return findStartOfPattern() != -1;
    }

    private int findStartOfNextTag() {
        return (offset = xmlContent.indexOf('<', offset));
    }

    private String getTextBeforeNextTag(int startsFrom) {
        String text = xmlContent.substring(startsFrom, findStartOfNextTag());
        // This condition will be true in case when text before next tag
        // contains more than two patterns, at least, beginning and ending of the pattern
        if (text.contains(pattern.getEndPattern())) {
            String partOfPattern = text.substring(0, text.indexOf(pattern.getEndPattern()) + 1);
            offset -= (text.length() - partOfPattern.length());
            return partOfPattern;
        }
        return text;
    }

    private int findEndOfNextTag() {
        return (offset = xmlContent.indexOf('>', offset) + 1);
    }

    private boolean isTextAfterTag() {
        return xmlContent.charAt(findEndOfNextTag()) != '<';
    }

    private String findAndRemoveNextText() {
        boolean goNext;
        String text = "";
        do {
            goNext = !isTextAfterTag();
            if (!goNext) {
                text = rearrangeTextToPattern();
            }
        } while (goNext);

        return text;
    }

    private void shiftOffsetAfterInsert(int textLength) {
        offset -= textLength;
        startOfPattern -= textLength;
        endOfPattern -= textLength;
        startOfPatternOffset += textLength;
    }

    private String rearrangeTextToPattern() {
        int startsFrom = offset;
        String text = getTextBeforeNextTag(startsFrom);
        int textLength = text.length();
        xmlContent = shiftFoundTextToPattern(startsFrom, text, textLength);
        return text;
    }

    private String shiftFoundTextToPattern(int startsFrom, String text, int textLength) {
        StringBuilder stringBuilder = new StringBuilder(xmlContent);
        stringBuilder.delete(startsFrom, startsFrom + textLength);
        stringBuilder.insert(startOfPatternOffset, text);
        shiftOffsetAfterInsert(textLength);
        return stringBuilder.toString();
    }

    private String formPatternText() {
        startOfPattern = findStartOfPattern();
        endOfPattern = findEndOfPattern();
        offset = startOfPattern;
        StringBuilder result = new StringBuilder(getTextBeforeNextTag(startOfPattern));
        startOfPatternOffset = startOfPattern + result.length();
        while (endOfPattern >= offset) {
            result.append(findAndRemoveNextText());
        }
        return result.toString();
    }

    @Override
    public List<String> processXml() {
        return processXml(getPattern(), getXmlContent());
    }
}
