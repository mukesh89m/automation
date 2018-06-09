package com.snapwiz.selenium.tests.staf.edulastic.apphelpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.util.logging.Logger;

public class Screenshot extends TestListenerAdapter{

    private final static Logger LOG = Logger.getLogger(Screenshot.class.getName());

    private WebDriver driver;

    public Screenshot(WebDriver driver) {
        this.driver = driver;
    }

    public void captureScreenshotOnTestFailure(String className, String methodName,ITestResult it) {
        try {
            File scrFile;
            WebDriver augmentedDriver;
            if (driver instanceof ChromeDriver) {
                augmentedDriver = new Augmenter().augment(driver);
                scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
            } else {
                scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            }
            File destFile = new File("/home/screenshot/" + className + "_" + methodName +"_"+it.getTestContext().getCurrentXmlTest().getName() +".png");
           // File destFile = File.createTempFile("screenshot", ".png");
            FileUtils.copyFile(scrFile, destFile);
            LOG.info("Screenshot captured : " + destFile.getAbsolutePath());
        } catch (Exception e) {
            Assert.fail("Exception while capturing screen shot", e);
        }
    }

}
