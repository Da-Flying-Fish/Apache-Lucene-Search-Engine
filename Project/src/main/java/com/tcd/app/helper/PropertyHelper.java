package com.tcd.app.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyHelper {
    public static Properties propertiesVal = null;

    public static Properties readPropFile(String propertyFilePath) throws IOException {
        FileInputStream propFile = null;

        try {
            if(propFile== null){
                propFile = new FileInputStream(propertyFilePath);
                propertiesVal = new Properties();
                propertiesVal.load(propFile);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
         finally {
            propFile.close();
        }
        return propertiesVal;
    }

    public static void main(String[] args){

        Properties prop = null;
        try {
            prop = readPropFile("config.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
