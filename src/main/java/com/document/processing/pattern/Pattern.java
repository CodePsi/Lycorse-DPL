package com.document.processing.pattern;

import java.io.Serializable;
import java.util.Objects;

public class Pattern implements Serializable {
    private String startPattern;
    private String endPattern;

    public Pattern(String startPattern, String endPattern) {
        this.startPattern = startPattern;
        this.endPattern = endPattern;
    }

    public Pattern(String pattern) {
        this.startPattern = pattern;
        this.endPattern = pattern;
    }

    public String getStartPattern() {
        return startPattern;
    }

    public void setStartPattern(String startPattern) {
        this.startPattern = startPattern;
    }

    public String getEndPattern() {
        return endPattern;
    }

    public void setEndPattern(String endPattern) {
        this.endPattern = endPattern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pattern pattern = (Pattern) o;

        if (!Objects.equals(startPattern, pattern.startPattern))
            return false;
        return Objects.equals(endPattern, pattern.endPattern);
    }

    @Override
    public int hashCode() {
        int result = startPattern != null ? startPattern.hashCode() : 0;
        result = 31 * result + (endPattern != null ? endPattern.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pattern{" +
                "startPattern='" + startPattern + '\'' +
                ", endPattern='" + endPattern + '\'' +
                '}';
    }
}
