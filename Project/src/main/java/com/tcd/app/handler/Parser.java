package com.tcd.app.handler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

import java.io.File;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

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
    public static ArrayList<HashMap<String,String>> lATParser() {
		String latDirPath = "resources/DataSource/latimes/";

		File files[] = new File(latDirPath).listFiles();

		// Iterate each file in the "latimes" directory
		for (File f : files) {
			Path fPath = Paths.get(f.getPath());

			try {
				String content = new String(Files.readAllBytes(fPath));

				// Split to create list of documents in each file
				String[] items = content.split("<DOC>|</DOC>");

				for (String item : items) {
					// Hashmap to store parsed document
					HashMap<String, String> docMap = new HashMap<>();

					// Split and remove end tags, this makes it easier to parse
					String[] itemSep = item.split("</.*>([\r\n]|\r\n)");

					String key = "";
					String value = "";
					boolean keyFlag = false;

					for (String portion : itemSep) {
						// Split at angled brackets to determine keys and values
						String[] portionSep = portion.split("<|>");

						for (String s : portionSep) {
							// Trim to remove spaces and new lines
							s = s.trim();

							// Creating key, value pair
							if (!s.isEmpty() && !s.equals("P")) {
								if (!keyFlag) {
									key = s;
									keyFlag = true;
								} else {
									value = s;
								}
							} else if (s.equals("P")) {
								keyFlag = true;
							}
						}

						// Adding into the hashmap
						if (docMap.containsKey(key)) {
							docMap.put(key, docMap.get(key) + " " + value);
						} else {
							docMap.put(key, value);
						}

						value = "";
						keyFlag = false;
					}

					System.out.println(docMap);

					/*
						TODO: Add the hashmap to a list of hashmaps
					*/
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}

        return new ArrayList<>();
    }
    /**
     *Method To Parse  Query Documents
     */
    public static ArrayList<HashMap<String,String>>queryParser(){

        return new ArrayList<>();
    }

}
