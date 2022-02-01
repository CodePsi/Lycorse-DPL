package com.document.xml.parser;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.net.URI;


@XmlRootElement(name = "document-content", namespace = "urn:oasis:names:tc:opendocument:xmlns:office:1.0")
public class DocumentContent {
    @XmlAttribute(name = "office", namespace = "xmlns")
    public URI office;

}

