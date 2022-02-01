package com.document.processing.libreoffice;

import com.document.processing.libreoffice.properties.OdtDocumentProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OdtDocumentPropertiesTest {

    @Test
    void addProperty() {
        OdtDocumentProperties odtDocumentProperties = new OdtDocumentProperties();
        odtDocumentProperties.addProperty(OdtDocumentProperties.HIDDEN, true);
        assertTrue(odtDocumentProperties.getPropertyValues().size() > 0);
        assertEquals(odtDocumentProperties.getProperty("Hidden").getName(), "Hidden");
        assertTrue((Boolean) odtDocumentProperties.getProperty("Hidden").getValue());
    }
}