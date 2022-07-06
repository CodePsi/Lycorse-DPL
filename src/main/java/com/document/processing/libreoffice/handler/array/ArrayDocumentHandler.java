package com.document.processing.libreoffice.handler.array;

import com.document.processing.libreoffice.uno.components.Text;
import com.document.processing.libreoffice.uno.components.container.ContainerEnumerator;
import com.document.processing.libreoffice.uno.components.container.DocumentContainer;
import com.document.processing.libreoffice.uno.components.container.EnumeratorObject;
import com.document.processing.pattern.Pattern;
import com.sun.star.container.NoSuchElementException;
import com.sun.star.lang.WrappedTargetException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Probably it needs to be refactored to accept List of values and type of render (such as Table, List, etc)
 *
 * */
public class ArrayDocumentHandler {
    private Pattern pattern;
    private List<EnumeratorObject> arrayElements = new ArrayList<>();
    /**
     * General view of for-loop
     * {for/start:variable}
     *      [content]
     * {for/end}
     */
    private String forStartRegex;
    private String forEndRegex;
    private final Text text;


    public ArrayDocumentHandler(Text text, Pattern pattern, String arrayName) throws ArrayNotExistsInDocumentException {
        this.text = text;
        this.pattern = pattern;
        initialize(arrayName);
    }

    private void initialize(String arrayName) throws ArrayNotExistsInDocumentException {
        initializeRegexPattern();
        lookForArrayWith(arrayName);
    }

    private void initializeRegexPattern() {
        String startPattern = checkPattern(pattern.getStartPattern());
        String endPattern = checkPattern(pattern.getEndPattern());
        forStartRegex = startPattern + "for/start:[1-9|a-z|A-Z]+" + endPattern;
        forEndRegex = startPattern + "for/end" + endPattern;
    }

    private String checkPattern(String pattern) {
        if (pattern.contains("$")) {
            return pattern.replace("$", "\\$");
        }
        if (pattern.contains("{")) {
            return pattern.replace("{", "\\{");
        }
        if (pattern.contains("}")) {
            return pattern.replace("}", "\\}");
        }

        return pattern;
    }

    private void lookForArrayWith(String arrayName) throws ArrayNotExistsInDocumentException {
        DocumentContainer documentContainer = new DocumentContainer(text.nativeTextRange().getText());
        ContainerEnumerator containerEnumerator = documentContainer.createEnumeration();
        AtomicBoolean isCollectElement = new AtomicBoolean(false);
        AtomicBoolean shouldBeClosed = new AtomicBoolean(false);

        while (containerEnumerator.hasMoreElements()) {
            try {
                EnumeratorObject enumeratorObject = containerEnumerator.nextElement();
                if (isCollectElement.get()) {
                    arrayElements.add(enumeratorObject);
                }
                enumeratorObject.ifText(textPackager -> {
                    String line = textPackager.getObject().getText();
                    if (line.matches(forStartRegex) && line.contains(arrayName) && !isCollectElement.get()) {
                        isCollectElement.set(true);
                        arrayElements.add(enumeratorObject);
                    } else if (line.matches(forEndRegex) && isCollectElement.get()) {
                        isCollectElement.set(false);
                        shouldBeClosed.set(true);
                    }
                });

                if (shouldBeClosed.get()) {
                    break;
                }
            } catch (WrappedTargetException | NoSuchElementException e) {
                e.printStackTrace();
            }

        }

        if (arrayElements.isEmpty()) {
            throw new ArrayNotExistsInDocumentException("There's not specified array in the document");
        }
    }

    public void apply(List<ArrayRow> arrayRows) {
        for (EnumeratorObject enumeratorObject1: arrayElements) {
            enumeratorObject1.ifText(textPackager -> System.out.println(textPackager.getObject().getText()));
        }
        EnumeratorObject lastObject = arrayElements.get(arrayElements.size() - 2);// Getting last necessary element
        for (ArrayRow arrayRow: arrayRows) {
            copyObjectsAndReplaceValues(lastObject, arrayRow);
        }
    }

    private void copyObjectsAndReplaceValues(EnumeratorObject enumeratorObject, ArrayRow arrayRow) {
        AtomicInteger initialObjectIndex = new AtomicInteger(1);
        for (KeyValuePair keyValuePair: arrayRow.getKeyValuePairs()) {
            enumeratorObject.ifText(textPackager -> {
                EnumeratorObject neededObject = arrayElements.get(initialObjectIndex.get());
                neededObject.ifText(innerTextPackage -> {
                    textPackager.getObject().addNewLine(innerTextPackage.getObject().getText());
                });
                initialObjectIndex.getAndIncrement();

            });
            for (EnumeratorObject enumeratorObject1: arrayElements) {
                enumeratorObject1.ifText(textPackager -> System.out.println(textPackager.getObject().getText()));
            }
        }
    }
}
