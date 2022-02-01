package com.document.processing.libreoffice;


// List of filters
// https://wiki.openoffice.org/wiki/Framework/Article/Filter/FilterList_OOo_3_0
// https://github.com/six519/libreoffice_convert/blob/b2599bc62892ce980445d4bc5d08f502b68c4a56/libreoffice_convert/document_types.py
public enum DocumentConvertTypes {
    WRITER_PDF_CONVERT_TYPE("writer_pdf_Export"),
    MS_WORD_2007_XML("MS Word 2007 XML");

    private String convertType;

    DocumentConvertTypes(String convertType) {
        this.convertType = convertType;
    }

    public String getConvertType() {
        return convertType;
    }

    public void setConvertType(String convertType) {
        this.convertType = convertType;
    }
}
