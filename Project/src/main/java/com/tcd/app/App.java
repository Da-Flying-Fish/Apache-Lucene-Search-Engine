package com.tcd.app;
import com.tcd.app.handler.Parser;
import com.tcd.app.helper.PropertyHelper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Properties;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        long start = System.currentTimeMillis();
        String propertyFilePath="Project/src/config.Properties";
        Properties properties = PropertyHelper.readPropFile(propertyFilePath);
        Parser.fBISParser(properties);
        long end = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");



    }
}
