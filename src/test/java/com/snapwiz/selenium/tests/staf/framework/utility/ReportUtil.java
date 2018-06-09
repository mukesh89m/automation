package com.snapwiz.selenium.tests.staf.framework.utility;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.Reporter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by murthis on 16-12-2015.
 */
public class ReportUtil extends Driver{

    private static ExtentReports extent;
    public static Map<Long, ExtentTest> testReportMap = new HashMap<>();
    private static final String ReportDirectory = System.getProperty("user.dir")+"/test-output";

    public static void initReports(){
        String ReportingFile=null;
        //Read extent config file and load it
        String version = System.getProperty("version");
        String token = System.getProperty("token");

        if (version!=null){

            //ReportingFile=ReportDirectory.replaceAll("/staf/test-output","/edulastic-atf/frontend/reports");
            System.out.println(ReportingFile);

           ReportingFile = ReportDirectory+"/../../edulastic-atf/frontend/reports/"+token +"--SeleniumJava.html";

        }else {
             ReportingFile = ReportDirectory + "/test-report.html";
        }
        extent=new ExtentReports(ReportingFile, true, NetworkMode.OFFLINE);
        extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
    }

    public static void startTest(String testCaseName){
          testReportMap.put(Thread.currentThread().getId(),extent.startTest(testCaseName));
    }

    public static void endTest(){
        //write result to report
        extent.endTest(testReportMap.get(Thread.currentThread().getId()));
        extent.flush();
    }

    public static void endReports(){
        extent.close();
    }


    /**
     * This will write the step details to logger and reports
     * @param step
     * @param details
     * @param status -- Possible values are info, pass and fail.
     */
    public static void log(String step,String details,String status){
        ExtentTest extentTest= testReportMap.get(Thread.currentThread().getId());
        Logger logger= Driver.getLogger();
        switch (status.trim().toLowerCase()){
            case "pass":
                logger.log(Level.INFO, "["+Thread.currentThread().getName()+"] "+step+"-"+details);
                extentTest.log(LogStatus.PASS,step,details);
                Reporter.log(step+"-"+details);
                System.out.println(step+"-"+details);
                break;
            case "fail":
                logger.log(Level.INFO,"["+Thread.currentThread().getName()+"] "+step+"-"+details);
                extentTest.log(LogStatus.FAIL,step,details);
                Reporter.log(step+"-"+details);
                System.out.println(step+"-"+details);
                break;
            case "info":
                logger.log(Level.INFO,"["+Thread.currentThread().getName()+"] "+step+"-"+details);
                extentTest.log(LogStatus.INFO,step,details);
                Reporter.log(step+"-"+details);
                System.out.println(step+"-"+details);
                break;
        }
    }

}
