package com.document.processing.libreoffice.handler.array;

import java.util.ArrayList;
import java.util.List;

public class ArrayRow {
    private final List<KeyValuePair> keyValuePairs;

    public ArrayRow() {
        keyValuePairs = new ArrayList<>();
    }

    public ArrayRow add(String key, Object value) {
        keyValuePairs.add(new KeyValuePair(key, value));
        return this;
    }

    public List<KeyValuePair> getKeyValuePairs() {
        return keyValuePairs;
    }
}
