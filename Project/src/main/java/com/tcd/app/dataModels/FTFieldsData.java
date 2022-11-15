package com.tcd.app.dataModels;

public enum FTFieldsData {
    TEXT("<TEXT>"),
    HEADLINE("<HEADLINE>"),
    BYLINE("<BYLINE>"),
    CORRECTION("<CORRECTION>"),
    CORRECTION_DATE("<CORRECTION-DATE>"),
    DOC_NO("<DOCNO>"),
    DOC_ID("<DOCID>"),
    DOC("<DOC>");
    FTFieldsData(String s) {

    }

    public Object getFieldType() {
        return null;
    }
}
