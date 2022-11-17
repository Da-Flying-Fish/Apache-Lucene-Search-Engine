package com.tcd.app.handler;

import com.tcd.app.dataModels.Constants;
import com.tcd.app.dataModels.FRFieldsData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

public class Parser {
    /***
     * Method To Parse Documents realted to Federal Register
     */

    public static ArrayList<HashMap<String, String>> fRParser(Properties properties) throws IOException {
        ArrayList<HashMap<String, String>> frParsedDocCollection = new ArrayList<>();

        // try-catch block to handle exceptions
        File[] files = new File[0];
        // Create a file object
        File ftFileList = new File("/Users/koushikkodukula/IdeaProjects/Apache-Lucene-Search-Engine2/Project/resources/DataSource/fr94");

        FilenameFilter filter = (ftFileList1, name) -> name.startsWith("0");

        // Get all the names of the files present
        // in the given directory
        // and whose names start with "ft9"
        files = ftFileList.listFiles(filter);
        Object[] listFiles = Arrays.stream(files).toArray();

        for (Object listFile : listFiles) {
            File FileTemp = (File) listFile;
            File[] tempFfileLlist = FileTemp.listFiles();
            for (File tempFile : tempFfileLlist) {

                Document parsedDoc = Jsoup.parse(tempFile);
                System.out.println(parsedDoc.toString());
                Elements listDocuments = parsedDoc.getElementsByTag(FRFieldsData.DOC.getFieldType());

                //System.out.println(listDocuments);

                for (Element doc : listDocuments) {
                    HashMap<String, String> documentMapping = new HashMap<>();
                    documentMapping.put(Constants.FR_DOC_NO,doc.getElementsByTag(String.valueOf(FRFieldsData.DOC_NO.getFieldType())).text());
                    documentMapping.put(Constants.FR_PARENT,doc.getElementsByTag(String.valueOf(FRFieldsData.PARENT.getFieldType())).text());
                    documentMapping.put(Constants.FR_TEXT,doc.getElementsByTag(String.valueOf(FRFieldsData.TEXT.getFieldType())).text());
                    frParsedDocCollection.add(documentMapping);

                }

            }


        }
        System.out.println(frParsedDocCollection);
        return frParsedDocCollection;
    }
}

