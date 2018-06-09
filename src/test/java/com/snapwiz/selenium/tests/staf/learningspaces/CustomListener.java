package com.snapwiz.selenium.tests.staf.learningspaces;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
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

public class CustomListener extends TestListenerAdapter{
    private static Logger logger = Logger.getLogger(Driver.class.getName());
    private int m_count = 0;

    @Override
    public void onTestFailure(ITestResult tr) {
        log( "--Test method FAILED "+tr.getTestClass()+"Method: "+tr.getName()+" \n and the reason is "+tr.getThrowable().getMessage()+"\n");
        ExtentTest extentTest=TestReports.testReportMap.get(Thread.currentThread().getId());
        extentTest.log(LogStatus.FAIL,"Test Status","Test case ["+tr.getName()+"] failed");
        extentTest.log(LogStatus.INFO,"Reason for the failure",tr.getThrowable().getMessage());
        extentTest.log(LogStatus.INFO,"Track Trace Info",tr.getThrowable());
        extentTest.log(LogStatus.INFO,"Screenshot","See below image for error screenshot "+extentTest.addScreenCapture(takeScreenshot(tr)));

    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log("--Test method SKIPPED "+tr.getTestClass()+"Method: "+tr.getName()+"\n");
        TestReports.testReportMap.get(Thread.currentThread().getId()).log(LogStatus.INFO,"Test Status","Test Skipped");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log("--Test method SUCCESS "+tr.getTestClass()+"Method: "+tr.getName()+"\n");
        TestReports.testReportMap.get(Thread.currentThread().getId()).log(LogStatus.PASS,"Test Status","Test Passed");
    }

    @Override
    public void onTestStart(ITestResult tr) {
        log("--Test case ["+tr.getName()+"] started \n [location = "+tr.getTestClass().getName()+"]\n");
        TestReports.testReportMap.get(Thread.currentThread().getId()).assignCategory(tr.getInstanceName().replace(tr.getName(),""));
        TestReports.testReportMap.get(Thread.currentThread().getId()).log(LogStatus.INFO,"Testcase",tr.getName());
    }

    private void log(String string) {
        logger.log(Level.INFO,string);
        if (++m_count % 40 == 0) {
            System.out.println("");
        }
    }

    /**
     * this will take screenshot and place it under test-output directory.
     * @author murthi.s
     * @return filename -- the screenshot file on success else null
     */
    public String takeScreenshot(ITestResult tr){

        WebDriver driver=new Augmenter().augment(Driver.webDriverMap.get(Thread.currentThread().getId()));
        String fileName=null;
        try {
            fileName=System.getProperty("user.dir")+"/test-output/"+tr.getInstanceName()+tr.getName()+".png";
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            org.apache.commons.io.FileUtils.copyFile(scrFile, new File(fileName));
        }
        catch (IOException e) {
            System.out.println("Unable to copy the screenshot file to test-output folder. "+e.getMessage());
        }
        catch(Exception ex){
            System.out.println("There is a exception in the takeScreenshot function. "+ex.getMessage());
        }
        return fileName;
    }

}
