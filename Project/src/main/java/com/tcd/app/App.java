package com.tcd.app;

import com.tcd.app.handler.Parser;
import com.tcd.app.helper.PropertyHelper;

import java.io.IOException;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {
        String propertyFilePath="Project/src/config.Properties";
        Properties properties = PropertyHelper.readPropFile(propertyFilePath);
        Parser.fTParser(properties);

    }
}
