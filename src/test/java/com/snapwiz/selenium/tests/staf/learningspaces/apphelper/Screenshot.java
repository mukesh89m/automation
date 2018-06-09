package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.io.File;

import java.sql.Timestamp;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class Screenshot extends Driver {

    public void captureScreenshotFromAppHelper()
    {
        try
        {
            File scrFile;
            WebDriver augmentedDriver;
            WebDriver driver=Driver.getWebDriver();
            String className = new Exception().getStackTrace()[2].getClassName().substring(57);
            String methodName = new Exception().getStackTrace()[2].getMethodName();
            java.util.Date date= new java.util.Date();
            String datetime = new Timestamp(date.getTime()).toString();
            datetime = datetime.replaceAll("\\:", "-");
            datetime = datetime.replaceAll("\\.", "-");
            if(Config.browser.equals("chrome"))
            {
                augmentedDriver = new Augmenter().augment(driver);
                scrFile = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
            }
            else
                scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/home/screenshot/"+className+"_"+methodName+"_"+datetime+".png"));
        }
        catch(Exception e)
        {
            Assert.fail("Exception while capturing screen shot",e);
        }
    }

    public void captureScreenshotFromTestCase()
    {
        try
        {
            File scrFile;
            WebDriver augmentedDriver;
            WebDriver driver=Driver.getWebDriver();
            String className = new Exception().getStackTrace()[1].getClassName().substring(57);
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            java.util.Date date= new java.util.Date();
            String datetime = new Timestamp(date.getTime()).toString();
            datetime = datetime.replaceAll("\\:", "-");
            datetime = datetime.replaceAll("\\.", "-");
            if(Config.browser.equals("chrome"))
            {
                augmentedDriver = new Augmenter().augment(driver);
                scrFile = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
            }
            else
                scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/home/screenshot/"+className+"_"+methodName+"_"+datetime+"_"+Config.browser+".png"));
        }
        catch(Exception e)
        {
            Assert.fail("Exception while capturing screen shot",e);
        }
    }
}
