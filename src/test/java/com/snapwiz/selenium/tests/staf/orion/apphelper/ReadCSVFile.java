package com.snapwiz.selenium.tests.staf.orion.apphelper;

import au.com.bytecode.opencsv.CSVReader;
import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import org.testng.Assert;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by durgapathi on 29/10/15.
 */
public class ReadCSVFile {
    public static String csvFileTitle= null;
    public static String downloadPath = Config.downloadPath;

    public CSVReader readCSVFilePath()
    {
        CSVReader path = null;
        try
        {
            //String cName = ReadTestData.readDataByTagName("", "course", dataIndex);
            String cName = Config.course;
            System.out.println("CNAme : " + cName);

           /* String csName = null;
            if(contextTitle==null) {
                System.out.println("1");
                csName = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            }
            String cNameWithoutSpecialChar = cName.trim().replaceAll("\\s+", " ").replaceAll("[^\\w]+", "_");
            if(cNameWithoutSpecialChar.length() > 30){
                cNameWithoutSpecialChar = cNameWithoutSpecialChar.substring(0, 30) + "...";
            }
            String csNameWithoutSpecialChar = csName.trim().replaceAll("\\s+", " ").replaceAll("[^\\w]", "_");
            if(csNameWithoutSpecialChar.length() > 30){
                csNameWithoutSpecialChar = csNameWithoutSpecialChar.substring(0, 30) + "...";
            }
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            String today = sdf.format(new Date());*/
            String csvFileTitle= "StudentsCoursePerformanceReport-"+cName+".csv";
            System.out.println("csvFileTitle: "+csvFileTitle);
            FileReader fr = new FileReader(downloadPath+csvFileTitle);
            path = new CSVReader(fr);
            System.out.println("finalpath: "+fr);
        }
        catch (Exception e)
        {
            Assert.fail("readCSVFile fail", e);
        }
        return path;
    }
}
