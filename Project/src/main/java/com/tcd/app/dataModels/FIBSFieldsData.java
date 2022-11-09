package com.tcd.app.dataModels;


public enum FIBSFieldsData {
    DOC("DOC"),
    DOC_NO("DOCNO"),
    DOC_DATE("Date1"),
    DOC_Title("ti"),
    DOC_CONTENTS("text"),
    DOC_FTAGS("f")
    ;

    String fieldType;

    private FIBSFieldsData(final String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldType() {
        return this.fieldType;
    }
}
