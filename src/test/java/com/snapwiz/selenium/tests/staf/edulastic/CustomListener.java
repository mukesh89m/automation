package com.snapwiz.selenium.tests.staf.edulastic;

import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Screenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomListener extends TestListenerAdapter {

    private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.edulastic.CustomListener");
    private int m_count = 0;

    @Override
    public void onTestFailure(ITestResult tr) {
        Object currentClass = tr.getInstance();
        WebDriver driver = ((Driver) currentClass).driver;
        try {
            new Screenshot(driver).captureScreenshotOnTestFailure(tr.getInstanceName(), tr.getName(),tr);//capture screenshot
        } catch (Exception e) {
            Assert.fail("Exception while capturing screen shot", e);
        }
        log("--Test method FAILED " + tr.getTestClass() + "Method: " + tr.getName() + "Test Name: "+ tr.getTestContext().getCurrentXmlTest().getName() + "\n");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log("--Test method SKIPPED " + tr.getTestClass() + "Method: " + tr.getName() + "Test Name: "+ tr.getTestContext().getCurrentXmlTest().getName() + "\n");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log("--Test method SUCCESS " + tr.getTestClass() + "Method: " + tr.getName() + "Test Name: "+ tr.getTestContext().getCurrentXmlTest().getName() + "\n");
    }

    private void log(String string) {
        logger.log(Level.INFO, string);
        if (++m_count % 40 == 0) {
            System.out.println("");
        }
    }

}
