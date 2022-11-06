package com.tcd.app.handler;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.tcd.app.helper.PropertyHelper;

public class Parser {

    /***
     * Method To Parse Documents realted to financial Times Limited
     */

    public static ArrayList<HashMap<String,String>> fTParser(){
        return new ArrayList<>();

    }
    /***
     * Method To Parse Documents realted to Federal Register
     */

    public static ArrayList<HashMap<String,String>> fRParser(){

        return new ArrayList<>();
    }
    /***
     * Method To Parse Documents realted to Foreign Broadcast Information Service
     */

    public static ArrayList<HashMap<String,String>> fBISParser(){
        return new ArrayList<>();
    }
    /**
     * Method To Parse Documents realted to Los Angeles Times
     */
    public static ArrayList<HashMap<String,String>> lATParser(){

        return new ArrayList<>();
    }
    /**
     *Method To Parse  Query Documents
     */
    public static ArrayList<HashMap<String,String>> queryParser(){
    	ArrayList<HashMap<String, String>> queries = new ArrayList<HashMap<String,String>>();
    	Properties prop = PropertyHelper.readPropFile("src/config.Properties");
    	try {
	    	BufferedReader br = new BufferedReader(new FileReader(prop.getProperty("QueryDataFilePath")));
			String line = br.readLine();
			while (line != null)  
			{  
			   if (line.equals("<top>"))
			   {
				   HashMap<String, String> entry = new HashMap<String, String>();
				   String number = "";
				   String title = "";
				   String description = "";
				   String narrative = "";
				   while (!line.equals("</top>"))
				   {
					   boolean end = false;
					   if (line.contains("<num>"))
					   {
						   String[] split = line.split("\\s+");
						   number += split[2];
					   }
					   else if (line.contains("<title>"))
					   {
						   String[] split = line.split("\\s+");
						   List<String> titleList = new ArrayList<String>();
						   titleList.addAll(Arrays.asList(split));
						   titleList.remove(0);
						   title = String.join(" ", titleList);
					   }
					   else if(line.contains("<desc>"))
					   {
						   line = br.readLine();
						   while(!line.contains("<narr>"))
						   {
							   if(!description.equals(""))
								   description += " ";
							   if (!line.equals(""))
								   description += line.trim();
							   line = br.readLine();
						   }
						   
						   line = br.readLine();
						   while(!line.contains("</top>"))
						   {
							   if(!narrative.equals(""))
								   narrative += " ";
							   if (!line.equals(""))
								   narrative += line.trim();
							   line = br.readLine();
						   }
						   end = true;  
					   }
					   
					   if(!end)
						   line = br.readLine();  
				   }
				   entry.put("number", number);
				   entry.put("title", title);
				   entry.put("description", description);
				   entry.put("narrative", narrative);
				   queries.add(entry);
			   }
			   line = br.readLine();  
			} 
			br.close();
    	} catch (IOException e) {
			e.printStackTrace();
		}  
    	return queries;
    }
}
