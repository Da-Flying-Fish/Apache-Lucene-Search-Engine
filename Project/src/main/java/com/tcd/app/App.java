package com.tcd.app;

import com.tcd.app.handler.Indexer;
import com.tcd.app.handler.Parser;
import com.tcd.app.handler.Searcher;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        ArrayList<HashMap<String,String>> fr94 = Parser.fRParser();
        ArrayList<HashMap<String,String>> ft = Parser.fTParser();
        ArrayList<HashMap<String,String>> lat = Parser.lATParser();
        ArrayList<HashMap<String,String>> fbis = Parser.fBISParser();
        ArrayList<HashMap<String,String>> combined = new ArrayList<>();
        // Combining all the parsed documents before indexing
        combined.addAll(fr94);
        combined.addAll(ft);
        combined.addAll(fbis);
        combined.addAll(lat);
        // Creating index
        Indexer.createIndex(combined);
        // Parsing Query Documents
        ArrayList<HashMap<String,String>> queries = Parser.queryParser();
        System.out.println("Complete");
        Searcher.queryIndex(queries);

    }
}
