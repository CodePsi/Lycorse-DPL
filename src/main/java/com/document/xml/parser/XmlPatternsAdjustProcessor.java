package com.document.xml.parser;

import com.document.processing.pattern.Pattern;

import java.util.List;

public interface XmlPatternsAdjustProcessor {
    List<String> processXml(Pattern pattern, String xmlContent);
    List<String> processXml();
}
