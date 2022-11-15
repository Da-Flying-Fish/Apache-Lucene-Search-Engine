package com.tcd.app.handler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

import java.lang.StringBuilder;
import java.lang.Math;
import java.lang.OutOfMemoryError;

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

		ArrayList<HashMap<String, String>> docMapList = new ArrayList<>();

		File files[] = new File(latDirPath).listFiles();
		int n = files.length;

		System.out.println("Parsing LA Times data...");

		// Iterate each file in the "latimes" directory
		for (int i=0 ; i<n ; i++) {
			progressBar(i, n);

			File f = files[i];
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

					docMapList.add(docMap);
				}
			} catch (IOException ioe) {
				System.err.println(ioe);
			} catch (OutOfMemoryError oome) {
				// System.err.println("Max JVM memory: " + Runtime.getRuntime().maxMemory());
			}
		}

		System.out.println("\nCOMPLETE!");

        return docMapList;
    }
    /**
     *Method To Parse  Query Documents
     */
    public static ArrayList<HashMap<String,String>>queryParser(){

        return new ArrayList<>();
    }

	private static void progressBar(int progress, int goal) {
		int percentage = Math.round(((float)progress/(float)goal)*100);

		StringBuilder complete = new StringBuilder("");
		StringBuilder incomplete = new StringBuilder();

		for (int i=0 ; i<percentage ; i++)
			complete.append("█");

		for (int i=100 ; i>=percentage ; i--)
			incomplete.append("░");

		System.out.print(complete.toString() + incomplete.toString() + " " + percentage + "%" + "\r");
	}
}
