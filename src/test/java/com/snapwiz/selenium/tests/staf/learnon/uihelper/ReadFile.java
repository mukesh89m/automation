package com.snapwiz.selenium.tests.staf.learnon.uihelper;

import au.com.bytecode.opencsv.CSVReader;
import com.snapwiz.selenium.tests.staf.learnon.Config;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import org.testng.Assert;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 8/12/15.
 */
public class ReadFile {
    public void readCSVFile(String filePath,String dataIndex)
    {
        try
        {
            FileReader fr = new FileReader(filePath);
            CSVReader path = new CSVReader(fr);
        }
        catch (Exception e)
        {
            Assert.fail("readCSVFile fail" + e);
        }
    }
    public CSVReader readCSVFilePath(String dataIndex )
    {
        CSVReader path = null;
        String downloadPath = Config.downloadPath;
        try
        {
            String cName = ReadTestData.readDataByTagName("", "course", dataIndex);
            String csName = ReadTestData.readDataByTagName("", "context_title", dataIndex);
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
            System.out.println("finalpath: "+fr);
        }
        catch (Exception e)
        {
            Assert.fail("readCSVFile fail" + e);
        }
        return path;
    }

}


