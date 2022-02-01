package com.document.zip;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipArchive {
    private String zipFileName;
    private Map<String, ZipFileContent> entries;

    public ZipArchive(String zipFileName) {
        this.zipFileName = zipFileName;
        entries = new HashMap<>();
    }

    public void loadFiles() throws IOException {
        Path path = Paths.get(zipFileName);
        ZipFile zipFile = new ZipFile(path.toFile());

        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            validateZipEntryPath(path, entry);
            addEntry(zipFile, entry);
        }

        zipFile.close();
    }

    private void validateZipEntryPath(Path path, ZipEntry entry) throws IOException {
        if (isIncorrectPath(path, entry.getName())) {
            throw new IOException("Bad zip entry destination: " + path.toFile());
        }
    }

    public ZipFileContent getFile(String fileName) throws IOException {
        Path path = Paths.get(zipFileName);
        ZipFile zipFile = new ZipFile(path.toFile());
        ZipEntry entry = zipFile.getEntry(fileName);
        validateZipEntryPath(path, entry);
        return addEntry(zipFile, entry);
    }

    private ZipFileContent addEntry(ZipFile zipFile, ZipEntry entry) throws IOException {
        try (InputStream inputStream = zipFile.getInputStream(entry)) {
            String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            ZipFileContent zipFileContent = new ZipFileContent(entry, content);
            this.entries.put(entry.getName(), zipFileContent);
            return zipFileContent;
        }
    }

    /**
     * It is zip slip protection.
     *
     * @param path Path to the zip archive
     * @param entryName Entry name
     * @return true when path is incorrect, otherwise false
     */
    private boolean isIncorrectPath(Path path, String entryName) {
        Path normalizedPath = path.resolve(entryName).normalize();
        return !normalizedPath.startsWith(path);
    }

    public String getZipEntryFileContent(String fileName) {
        return getLoadedEntry(fileName).getFileContent();
    }

    public void setEntryContent(String entry, String content) {
        entries.get(entry).setFileContent(content);
    }

    public ZipFileContent getLoadedEntry(String fileName) {
        return entries.get(fileName);
    }

    public void saveArchive(String fileName) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)) {
            for (Map.Entry<String, ZipFileContent> entry: entries.entrySet()) {
                ZipEntry zipEntry = new ZipEntry(entry.getValue().getZipEntry().getName());
                zipOutputStream.putNextEntry(zipEntry);
                byte[] content = entry.getValue().getFileContent().getBytes();
                int len = content.length;
                zipOutputStream.write(content, 0, len);
                zipOutputStream.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveArchive() {
        saveArchive(zipFileName);
    }

    public void saveArchive(Path file) {
        saveArchive(file.toFile().getAbsolutePath());
    }

    public Map<String, ZipFileContent> getEntries() {
        return entries;
    }

    public String getZipFileName() {
        return zipFileName;
    }
}
