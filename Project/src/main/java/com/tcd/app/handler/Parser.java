package com.tcd.app.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class Parser {

    /***
     * Method To Parse Documents realted to financial Times Limited
     */
//
//    public static ArrayList<HashMap<String,String>> fTParser(Properties properties){
//        ArrayList<HashMap<String,String>> ftParsedDocCollection= new ArrayList<>();
//        String dataSourceDir = properties.getProperty("SourceDataFolderPath");
//        String ftFolderName=dataSourceDir+"/"+properties.getProperty("FTFolderName")+"/";
//        List<File> ftFileList= Utilities.getFilesInDir(ftFolderName);
////        for (File fbisFile:fbisFileList) {
////            Document parsedDocuments = null;
//            try {
//                 parsedDocuments = Jsoup.parse(ftFolderName);
//                Elements listDocuments = (Elements) parsedDocuments.getElementsByTag((String) FTFieldsData.DOC.getFieldType());
//                for (Element doc: listDocuments ) {
//                    HashMap<String,String> documentMapping = new HashMap<>();
//                    documentMapping.put(Constants.FT_DOC_TEXT,doc.getElementsByTagName(String.valueOf(FTFieldsData.TEXT.getFieldType())).text());
//                    documentMapping.put(Constants.FT_DOC_HEADLINE,doc.getElementsByTagName(String.valueOf(FTFieldsData.HEADLINE.getFieldType())).text());
//                    documentMapping.put(Constants.FT_DOC_BYLINE,doc.getElementsByTagName(String.valueOf(FTFieldsData.BYLINE.getFieldType())).text());
//                    documentMapping.put(Constants.FT_DOC_CORRECTION,doc.getElementsByTagName(String.valueOf(FTFieldsData.CORRECTION.getFieldType())).text());
//                    documentMapping.put(Constants.FT_DOC_CORRECTION_DATA,doc.getElementsByTagName(String.valueOf(FTFieldsData.CORRECTION_DATE.getFieldType())).text());
//                    documentMapping.put(Constants.FT_DOC_NO,doc.getElementsByTagName(String.valueOf(FTFieldsData.DOC_NO.getFieldType())).text());
//                    documentMapping.put(Constants.FT_DOC_ID,doc.getElementsByTagName(String.valueOf(FTFieldsData.DOC_ID.getFieldType())).text());
//                    ftParsedDocCollection.add(documentMapping);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        System.out.println(ftParsedDocCollection);
//        return ftParsedDocCollection;
//    }
//    }
//
//    public static List<Map<String,String>> fTParser(){
//        List<File> collection = new ArrayList<>();
//        List<Map<String, String>> docList = new ArrayList<>();
//
//        File dir = new File("Project/resources/DataSource/ft");
//        getDocuments(Objects.requireNonNull(dir.listFiles()), collection);
//
//        for(File file: collection){
//            try{
//                FileReader fileReader = new FileReader(file);
//                BufferedReader reader = new BufferedReader(fileReader);
//                String line = "";
//
//                String attribute = "";
//                String content = "";
//                Map<String, String> document = null;
//
//                while ((line = reader.readLine()) != null) {
//                    //Start of the document
//                    if(line.equals("<DOC>")){
//                        attribute = "";
//                        content = "";
//                        document = new HashMap<>();
//                    }
//                    //End of the document
//                    else if(line.equals("</DOC>")){
//                        docList.add(document);
//                    }else{
//                        String[] elements = line.split("<|>");
//                        //TAG start and TAG end in the same line
//                        if(elements.length == 4){
//                            attribute = elements[1];
//                            content = elements[2];
//                            document.put(attribute, content.strip());
//
//                            attribute = "";
//                            content = "";
//                        }
//                        //TAG start + value or value + TAG end
//                        else if(elements.length == 3){
//                            if(elements[2].contains("/")){
//                                content = content.concat(elements[1]);
//                                document.put(attribute, content.strip());
//
//                                attribute = "";
//                                content = "";
//                            }else {
//                                attribute = elements[1];
//                                content = elements[2];
//                            }
//                        }
//                        //TAG start or TAG end
//                        else if(elements.length == 2){
//                            if(elements[1].contains("/")){
//                                document.put(attribute, content.strip());
//
//                                attribute = "";
//                                content = "";
//                            }else{
//                                attribute = elements[1];
//                                content = "";
//                            }
//                        }
//                        //TAG free content
//                        else{
//                            content = content.concat("").concat(elements[0]).concat(" ");
//                        }
//                    }
//                }
//            }catch (Exception e){
//                System.out.println("ERROR PARSING DOCUMENTS - FT_Collection");
//                e.printStackTrace();
//                System.exit(1);
//            }
//        }
//
//        return docList;
//    }
//
//    public static List<File> getDocuments(File[] files, List<File> collection){
//        for(File file : files){
//            if(file.isDirectory()){
//                collection.addAll(Arrays.stream(Objects.requireNonNull(file.listFiles())).toList());
//            }else{
//                collection.add(file);
//            }
//        }
//        return collection;
//    }

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
    public static ArrayList<HashMap<String,String>>queryParser(){

        return new ArrayList<>();
    }

}
