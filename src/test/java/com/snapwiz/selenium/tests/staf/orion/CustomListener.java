package com.snapwiz.selenium.tests.staf.orion;

import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Akansh on 23/12/14.
 */


public class CustomListener extends TestListenerAdapter{
    private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.orion.CustomListener");
    private int m_count = 0;

    @Override
    public void onTestFailure(ITestResult tr) {
        log( "--Test method FAILED "+tr.getTestClass()+"Method: "+tr.getName()+"\n");

    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log("--Test method SKIPPED "+tr.getTestClass()+"Method: "+tr.getName()+"\n");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log("--Test method SUCCESS "+tr.getTestClass()+"Method: "+tr.getName()+"\n");
    }

    private void log(String string) {
        logger.log(Level.INFO,string);
    }

}
