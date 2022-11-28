package com.tcd.app.dataModels;

import java.util.HashMap;

/**
 * This Class can be used to store string literals
 */

public class Constants {
    // Constants of FIBS Files
    public static String FIBS_DOC_NO="docid";
    public static String FIBS_DOC_TITLE ="title";
    public static String FIBS_DOC_Date="date";
    public static String FIBS_DOC_Contents="words";
    public static String FIBS_DOC_Ftags="ftags";
    // Constants of FT Files
    public static String FT_DOC="doc";
    public static String FT_DOC_NO="docnumber";
    public static String FT_PROFILE="profile";
    public static String FT_DATE="date";
    public static String FT_HEADLINE="headline";
    public static String FT_TEXT="text";
    public static String FT_PUB="headline";
    public static  String FR_DOC_NO = "docno" ;
    public static  String FR_PARENT = "parent" ;
    public static  String FR_TEXT= "text" ;

    public static  String QUERY_TITLE="title";
    public static String  QUERY_DES="description";
//    public static String QUERY_NAR="narrative";
    public static String QUERY_NO="number";
    public static String QUERY_RELAVENT="relevant_narrative";
    public static String QUERY_NO_RELAVENT="not_relevant_narrative";

    public static String DOC_ID="DOC_ID";
    public static String DOC_TITLE="TITLE";
    public static String DOC_DATE="DATE";
    public static String DOC_TEXT="TEXT";
    public static String DOC_OTHER="OTHER";

    public static HashMap<String,String> ConstantKeyMapping ;
    static {
        ConstantKeyMapping = new HashMap<>();
        // Fbis
        ConstantKeyMapping.put("docid", "DOC_ID");
        ConstantKeyMapping.put("words","TEXT");

        // FR
        ConstantKeyMapping.put("docno","DOC_ID");
        ConstantKeyMapping.put("title","TITLE");
        ConstantKeyMapping.put("text","TEXT");
        ConstantKeyMapping.put("Date","DATE");

        //FT
        ConstantKeyMapping.put("docnumber","DOC_ID");
        ConstantKeyMapping.put("headline","TITLE");
        ConstantKeyMapping.put("date","DATE");
        //Latime
        ConstantKeyMapping.put("DOCNO","DOC_ID");
        ConstantKeyMapping.put("HEADLINE","TITLE");
        ConstantKeyMapping.put("DATE","DATE");
        ConstantKeyMapping.put("TEXT","TEXT");

    }

    public static String[] documentFieldList = new String[]{"DOC_ID","TITLE","DATE","TEXT","OTHER"};




}
