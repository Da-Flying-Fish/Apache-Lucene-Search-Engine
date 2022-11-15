package com.tcd.app.helper;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyHelper {
    public static Properties propertiesVal = null;

    public static Properties readPropFile(String propertyFilePath)  {
        FileInputStream propFile = null;

        try {
               if(propertiesVal== null){
                propFile = new FileInputStream(propertyFilePath);
                propertiesVal = new Properties();
                propertiesVal.load(propFile);
               }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return propertiesVal;
    }

    /**
     * To Check The Working of Property helper Function
     * @param args
     */
    public static void main(String[] args){

        Properties prop ;
        try {
            prop = readPropFile("src/config.Properties");
            System.out.println("DataSourcePath= "+prop.getProperty("SourceDataFolderPath"));
            System.out.println("Index Folder ="+prop.get("IndexedDataFolderPath"));
            System.out.println("Results Folder ="+prop.get("ResultDataFolderPath"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
