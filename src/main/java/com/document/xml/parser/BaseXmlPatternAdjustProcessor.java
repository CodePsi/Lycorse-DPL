package com.document.xml.parser;

import com.document.processing.pattern.Pattern;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseXmlPatternAdjustProcessor implements XmlPatternsAdjustProcessor {
    protected List<String> foundPatterns;

    protected String xmlContent;
    protected Pattern pattern;
    protected int offset = 0;
    protected int startOfPattern;
    protected int endOfPattern;
    protected int startOfPatternOffset;

    public BaseXmlPatternAdjustProcessor(String xmlContent, Pattern pattern) {
        this.xmlContent = xmlContent;
        this.pattern = pattern;
        foundPatterns = new ArrayList<>();
    }

    protected int findStartOfPattern() {
        startOfPattern = xmlContent.indexOf(pattern.getStartPattern(), offset);
        return startOfPattern;
    }

    protected int findEndOfPattern() {
        endOfPattern = xmlContent.indexOf(pattern.getEndPattern(), offset);
        return endOfPattern;
    }

    public String getXmlContent() {
        return xmlContent;
    }

    public void setXmlContent(String xmlContent) {
        this.xmlContent = xmlContent;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public List<String> getFoundPatterns() {
        return foundPatterns;
    }
}
