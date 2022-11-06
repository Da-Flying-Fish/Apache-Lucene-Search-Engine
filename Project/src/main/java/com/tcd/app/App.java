package com.tcd.app;

import java.util.ArrayList;
import java.util.HashMap;

import com.tcd.app.handler.Parser;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
		ArrayList<HashMap<String, String>> arr = Parser.lATParser();
    }
}
