package com.tcd.app.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyHelper {
    public static Properties propertiesVal = null;

    public static Properties readPropFile(String propertyFilePath)  {
        FileInputStream propFile = null;

        try {
                propFile = new FileInputStream(propertyFilePath);
                propertiesVal = new Properties();
                propertiesVal.load(propFile);
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

        Properties prop = null;
        try {
            prop = readPropFile("Project/src/config.Properties");
            System.out.println("DataSourcePath= "+prop.getProperty("SourceDataFolderPath"));
            System.out.println("Index Folder ="+prop.get("IndexedDataFolderPath"));
            System.out.println("Results Folder ="+prop.get("ResultDataFolderPath"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
