package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import au.com.bytecode.opencsv.CSVReader;
import com.snapwiz.selenium.tests.staf.learnon.Config;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import org.testng.Assert;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 10/26/15.
 */
public class ReadFile{
    public static String csvFileTitle= null;
    public static String downloadPath = Config.downloadPath;

    public CSVReader readCSVFilePath(String dataIndex, String contextTitle )
    {
        CSVReader path = null;
        try
        {
            String cName = Config.course;

            String csName = contextTitle;
            String cNameWithoutSpecialChar = cName.trim().replaceAll("\\s+", " ").replaceAll("[^\\w]+", "_");
            if(cNameWithoutSpecialChar.length() > 30){
                cNameWithoutSpecialChar = cNameWithoutSpecialChar.substring(0, 30) + "...";
            }

            String csNameWithoutSpecialChar = csName.trim().replaceAll("\\s+", " ").replaceAll("[^\\w]", "_");
            if(csNameWithoutSpecialChar.length() > 30){
                csNameWithoutSpecialChar = csNameWithoutSpecialChar.substring(0, 30) + "...";
            }
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            String today = sdf.format(new Date());
            String csvFileTitle= "Gradebook-"+cNameWithoutSpecialChar+"-"+csNameWithoutSpecialChar+"-"+today+".csv";
            System.out.println("csvFileTitle: "+csvFileTitle);
            FileReader fr = new FileReader(downloadPath+csvFileTitle);
            path = new CSVReader(fr);
        }
        catch (Exception e)
        {
            Assert.fail("readCSVFile fail" ,e);
        }
        return path;
    }
}
