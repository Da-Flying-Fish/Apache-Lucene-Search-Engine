package com.tcd.app.dataModels;

public enum FTFieldsData {
    DOC("DOC"),
    DOC_NO("DOCNO"),
    PROFILE("PROFILE"),
    DATE("DATE"),
    HEADLINE("HEADLINE"),
    TEXT("TEXT"),
    PUB("PUB");

    String fieldType;

    private FTFieldsData(final String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldType() {
        return this.fieldType;
    }
}