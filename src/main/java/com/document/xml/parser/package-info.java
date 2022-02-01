@XmlSchema(
        elementFormDefault= XmlNsForm.QUALIFIED,
        xmlns = {
                @XmlNs(prefix = "office", namespaceURI = "urn:oasis:names:tc:opendocument:xmlns:office:1.0")
        }
)
package com.document.xml.parser;

import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlSchema;