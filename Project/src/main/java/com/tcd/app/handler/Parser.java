package com.tcd.app.handler;

import com.tcd.app.dataModels.Constants;
import com.tcd.app.dataModels.FTFieldsData;
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
     * Method To Parse Documents realted to financial Times Limited
     */

    public static ArrayList<HashMap<String, String>> fTParser(Properties properties) throws IOException {
        ArrayList<HashMap<String, String>> ftParsedDocCollection = new ArrayList<>();

        // try-catch block to handle exceptions
        File[] files = new File[0];
        // Create a file object
        File ftFileList = new File("/Users/koushikkodukula/IdeaProjects/Apache-Lucene-Search-Engine2/Project/resources/DataSource/ft");

        // Create a FilenameFilter
        FilenameFilter filter = (ftFileList1, name) -> name.startsWith("ft9");

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
                Elements listDocuments = parsedDoc.getElementsByTag("DOC");
                for (Element doc : listDocuments) {
                    HashMap<String, String> documentMapping = new HashMap<>();
                    documentMapping.put(Constants.FT_DOC_NO, doc.getElementsByTag(String.valueOf(FTFieldsData.DOC_NO.getFieldType())).text());
                    documentMapping.put(Constants.FT_PROFILE, doc.getElementsByTag(String.valueOf(FTFieldsData.PROFILE.getFieldType())).text());
                    documentMapping.put(Constants.FT_DATE, doc.getElementsByTag(String.valueOf(FTFieldsData.DATE.getFieldType())).text());
                    documentMapping.put(Constants.FT_HEADLINE, doc.getElementsByTag(String.valueOf(FTFieldsData.HEADLINE.getFieldType())).text());
                    documentMapping.put(Constants.FT_TEXT, doc.getElementsByTag(String.valueOf(FTFieldsData.TEXT.getFieldType())).text());
                    documentMapping.put(Constants.FT_PUB, doc.getElementsByTag(String.valueOf(FTFieldsData.PUB.getFieldType())).text());
                    ftParsedDocCollection.add(documentMapping);
                }
            }
            System.out.println(ftParsedDocCollection.size());

            return ftParsedDocCollection;
        }
        return null;
    }
}









