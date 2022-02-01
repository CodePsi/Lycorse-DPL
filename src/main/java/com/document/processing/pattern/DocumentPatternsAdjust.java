package com.document.processing.pattern;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface DocumentPatternsAdjust {
    String adjustPatterns() throws IOException;
    String adjustPatterns(File archive) throws IOException;
    String adjustPatterns(File archive, Pattern pattern) throws IOException;
    List<String> getFoundPatterns();
}
