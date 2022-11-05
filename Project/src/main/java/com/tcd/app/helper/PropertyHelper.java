package com.tcd.app.helper;

import java.io.FileInputStream;
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


}
