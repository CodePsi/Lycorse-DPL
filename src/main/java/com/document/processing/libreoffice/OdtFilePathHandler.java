package com.document.processing.libreoffice;

import java.io.File;

public class OdtFilePathHandler {
    public static String normalizeFilepath(File file) {
        return "file:///" + file.getAbsolutePath().replace('\\', '/');
    }

    public static String normalizeFilepath(String filepath) {
        return "file:///" + filepath.replace('\\', '/');
    }

    public static boolean isNormalizedFilepath(String filepath) {
        return filepath.startsWith("file:///") && filepath.contains("/") && !filepath.contains("\\");
    }
}
