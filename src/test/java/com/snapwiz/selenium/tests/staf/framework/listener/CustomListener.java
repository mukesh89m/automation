package com.snapwiz.selenium.tests.staf.framework.listener;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.Properties;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;

/**
 * Created by Murthi on 23/12/15.
 */

public class CustomListener extends TestListenerAdapter {
    private static Logger logger = Logger.getLogger(Driver.class.getName());
    private int m_count = 0;

    @Override
    public void onTestFailure(ITestResult tr) {
        log("[" + Thread.currentThread().getName() + "] " + "--Test method [" + tr.getName() + "] FAILED. \n Location: " + tr.getTestClass() + " \n and the reason is " + tr.getThrowable().getMessage() + "\n");
        ExtentTest extentTest = ReportUtil.testReportMap.get(Thread.currentThread().getId());
        extentTest.log(LogStatus.FAIL, "Test Status", "Test case [" + tr.getName() + "] failed");
        extentTest.log(LogStatus.INFO, "Reason for the failure", tr.getThrowable().getMessage());
        extentTest.log(LogStatus.INFO, "Track Trace Info", tr.getThrowable());
        extentTest.log(LogStatus.INFO, "Screenshot", "See below image for error screenshot " + extentTest.addScreenCapture(takeScreenshot(tr)));

    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log("[" + Thread.currentThread().getName() + "] " + "-- Test method [" + tr.getName() + "] SKIPPED.-- \n Location: [" + tr.getTestClass() + "]\n");
        ExtentTest extentTest = ReportUtil.testReportMap.get(Thread.currentThread().getId());
        extentTest.log(LogStatus.INFO, "Test Status", "Test case [" + tr.getName() + "] skipped");
        if (tr.getTestContext().getFailedConfigurations().getAllResults().iterator().hasNext()) {
            extentTest.log(LogStatus.INFO, "Reason for the skip", tr.getTestContext().getFailedConfigurations().getAllResults().iterator().next().getThrowable().getMessage());
            extentTest.log(LogStatus.INFO, "Reason for the skip", tr.getTestContext().getFailedConfigurations().getAllResults().iterator().next().getThrowable());
        }
        ReportUtil.testReportMap.get(Thread.currentThread().getId()).log(LogStatus.SKIP, "Test Status", "Test Skipped");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log("[" + Thread.currentThread().getName() + "] " + "-- Test method [" + tr.getName() + "] PASSED. --\n Location: [" + tr.getTestClass() + "]\n");
        ReportUtil.testReportMap.get(Thread.currentThread().getId()).log(LogStatus.PASS, "Test Status", "Test Passed");
    }

    @Override
    public void onTestStart(ITestResult tr) {
        log("[" + Thread.currentThread().getName() + "] " + "-- Test case [" + tr.getName() + "] STARTED.-- \n Location = [" + tr.getTestClass() + "]\n");
        ReportUtil.testReportMap.get(Thread.currentThread().getId()).assignCategory(tr.getInstanceName().replace(tr.getName(), ""));
        ReportUtil.testReportMap.get(Thread.currentThread().getId()).log(LogStatus.INFO, "Testcase", tr.getName());
    }

    private void log(String string) {
        logger.log(Level.INFO, string);
        if (++m_count % 40 == 0) {
            System.out.println("");
        }
    }

    /**
     * this will take screenshot and place it under test-output directory.
     *
     * @return filename -- the screenshot file on success else null
     * @author murthi.s
     */
    public String takeScreenshot(ITestResult tr) {
        //Initializing local variable
        WebDriver driver = null;
        String fileName = null;
        String fileNameRelative = null;
        File scrFile = null;
        boolean isLocal = false;

        driver = Driver.webDriverMap.get(Thread.currentThread().getId());
        if(System.getProperty("IsLocal")!=null)
            isLocal=Boolean.parseBoolean(System.getProperty("IsLocal"));
        else
            isLocal = Boolean.parseBoolean(Properties.getPropertyValue("IsLocal"));

        //Take screenshot
        try {
            //fileName=System.getProperty("user.dir")+"/test-output/"+tr.getInstanceName()+tr.getName()+driver.getWindowHandle()+".png";
            fileName = System.getProperty("user.dir") + "/test-output/" + tr.getInstanceName() + tr.getName() + ".png";
            fileNameRelative = "./" + tr.getInstanceName() + tr.getName() + ".png";
            if (isLocal)
                scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            else {
                WebDriver augmentedDriver = new Augmenter().augment(driver);
                scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
            }

            org.apache.commons.io.FileUtils.copyFile(scrFile, new File(fileName));
        } catch (IOException e) {
            System.out.println("Unable to copy the screen shot file to test-output folder. " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("There is a exception in the takeScreenshot function. " + ex.getMessage());
        }
        return fileNameRelative;
    }

}
