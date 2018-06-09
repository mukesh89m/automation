package com.snapwiz.selenium.tests.staf.learningspaces;
import com.relevantcodes.extentreports.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by murthis on 16-12-2015.
 */
public class TestReports {

    private static ExtentReports extent;
    public static Map<Long, ExtentTest> testReportMap = new HashMap<>();
    private static final String ReportDirectory = System.getProperty("user.dir")+"/test-output";

    public static void initReports(){
            String ReportingFile=ReportDirectory + "/extent-report.html";
            extent=new ExtentReports(ReportingFile, true);
            extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
    }

    public static void startTest(String testCaseName){
          testReportMap.put(Thread.currentThread().getId(),extent.startTest(testCaseName));
    }

    public static void endTest(){
        extent.endTest(testReportMap.get(Thread.currentThread().getId()));
        extent.flush();
    }


    public static void endReports(){

        extent.close();
    }

}
