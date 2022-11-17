package com.tcd.app.dataModels;

public enum FRFieldsData {

    DOC("DOC"),
    DOC_NO("DOCNO"),
    PARENT("PARENT"),
    TEXT("TEXT");

    String fieldType;

    private FRFieldsData(final String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldType() {
        return this.fieldType;
    }

}
