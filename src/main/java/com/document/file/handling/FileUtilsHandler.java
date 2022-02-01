package com.document.file.handling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtilsHandler {
    public static String readFile(File file) {
        try {
            Path path = Paths.get(file.getAbsolutePath());
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static void writeFile(File file, String content) {
        try {
            Path path = Paths.get(file.getAbsolutePath());
            Files.writeString(path, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
