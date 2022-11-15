package com.tcd.app.handler;

import com.tcd.app.helper.Utilities;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;

public class Parser {

    /***
     * Method To Parse Documents realted to financial Times Limited
     */

    public static List<File> fTParser(Properties properties) {
        List<File> ftParsedDocCollection = new ArrayList<>();
        // try-catch block to handle exceptions
        File[] files = new File[0];
        try {
            // Create a file object
            File ftFileList = new File("C:\\\\Users\\\\anish\\\\IdeaProjects\\\\Apache-Lucene-Search-Engine\\\\Project\\\\resources\\\\DataSource\\\\ft");
            String rootFolderPath = "C:\\Users\\anish\\IdeaProjects\\Apache-Lucene-Search-Engine\\Project\\resources\\DataSource\\ft\\";

            // Create a FilenameFilter
           FilenameFilter filter = (ftFileList1, name) -> name.startsWith("ft944");

            // Get all the names of the files present
            // in the given directory
            // and whose names start with "ft9"
            files = ftFileList.listFiles(filter);

            System.out.println("Files are:");

            // Display the names of the files
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[i].getName());
                String dirPath = rootFolderPath + files[i].getName();
                System.out.println(dirPath);
                List<File> ftList = Utilities.getFilesInDir(dirPath);
                for (File doc : ftList) {
                    Document parsedDoc = Jsoup.parse(doc);
                    System.out.println(parsedDoc.toString());
                    Elements listDocuments = parsedDoc.getElementsByTag("DOC");
                    System.out.println(listDocuments);

                }

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return ftParsedDocCollection;
    }
}






