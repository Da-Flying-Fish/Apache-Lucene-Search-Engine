package com.tcd.app.handler;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import com.tcd.app.helper.Utilities;
import com.tcd.app.dataModels.FIBSFieldsData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.tcd.app.dataModels.Constants;

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

    public static ArrayList<HashMap<String,String>> fBISParser(Properties properties){
        ArrayList<HashMap<String,String>> fbisParsedDocCollection= new ArrayList<>();
        String dataSourceDir = properties.getProperty("SourceDataFolderPath");
        String fbisFolderPath=dataSourceDir+"/"+properties.getProperty("FBISFolderName")+"/";
        List<File> fbisFileList=Utilities.getFilesInDir(fbisFolderPath);
        for (File fbisFile:fbisFileList) {
            Document parsedDocuments = null;
            try {
                parsedDocuments = Jsoup.parse(fbisFile);
                Elements listDocuments =parsedDocuments.getElementsByTag(FIBSFieldsData.DOC.getFieldType());
                for (Element doc: listDocuments ) {
                    HashMap<String,String> documentMapping = new HashMap<>();
                    documentMapping.put(Constants.FIBS_DOC_NO,doc.getElementsByTag(String.valueOf(FIBSFieldsData.DOC_NO)).text());
                    documentMapping.put(Constants.FIBS_DOC_Contents,doc.getElementsByTag(String.valueOf(FIBSFieldsData.DOC_CONTENTS)).text());
                    documentMapping.put(Constants.FIBS_DOC_Date,doc.getElementsByTag(String.valueOf(FIBSFieldsData.DOC_DATE)).text());
                    documentMapping.put(Constants.FIBS_DOC_TITLE,doc.getElementsByTag(String.valueOf(FIBSFieldsData.DOC_Title)).text());
                    documentMapping.put(Constants.FIBS_DOC_Ftags,doc.getElementsByTag(String.valueOf(FIBSFieldsData.DOC_FTAGS)).text());
                    fbisParsedDocCollection.add(documentMapping);
                }

            } catch (Exception e) {
               e.printStackTrace();
            }

        }
        System.out.println(fbisParsedDocCollection.size());
        return fbisParsedDocCollection;
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
    public static ArrayList<HashMap<String,String>>queryParser(){

        return new ArrayList<>();
    }

}
