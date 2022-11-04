package com.tcd.app.handler;
import org.apache.lucene.document.Document;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser {

    /***
     * Method To Parse Documents realted to financial Times Limited
     */

    public static ArrayList<HashMap<String,String>> fTParser(){
        return new ArrayList<Document>();
    }
    /***
     * Method To Parse Documents realted to Federal Register
     */

    public static ArrayList<Document> fRParser(){
        return new ArrayList<Document>();
    }
    /***
     * Method To Parse Documents realted to Foreign Broadcast Information Service
     */

    public static ArrayList<Document> fBISParser(){
        return new ArrayList<Document>();
    }
    /**
     * Method To Parse Documents realted to Los Angeles Times
     */
    public static ArrayList<Document> lATParser(){
        return new ArrayList<Document>();
    }
    /**
     *Method To Parse  Query Documents
     */
    public static ArrayList<String> queryParser(){
        return new ArrayList<String>();
    }

}
