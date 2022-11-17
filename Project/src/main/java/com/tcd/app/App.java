package com.tcd.app;

import com.tcd.app.handler.Indexer;
import com.tcd.app.handler.Parser;
import com.tcd.app.helper.PropertyHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        ArrayList<HashMap<String,String>> lat = Parser.lATParser();
        ArrayList<HashMap<String,String>> fbis = Parser.fBISParser();
        ArrayList<HashMap<String,String>> queries = Parser.queryParser();

        Indexer.createIndex(lat);
        Indexer.createIndex(fbis);
    }
}
