package com.document.xml.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        System.out.println(qName);
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        System.out.println(qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println(new String(ch, start, length));
    }

    @Override
    public void endDocument() {
        System.out.println("Stop parse XML...");
    }
}
