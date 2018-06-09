package com.snapwiz.selenium.tests.staf.framework.utility;

import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import org.testng.Assert;

/**
 * Created by murthi on 28/1/2016.
 */
public class CustomAssert {

    public static void assertEquals(String actual, String expected, String step, String LogDetail, String assertMessage) {
        if (actual.equals(expected)) {
            ReportUtil.log(step, LogDetail, "pass");
        } else {
            ReportUtil.log(step, assertMessage, "fail");
        }
        Assert.assertEquals(actual, expected,assertMessage);
    }

    public static void assertNotEquals(String actual, String expected, String step, String LogDetail, String assertMessage) {
        if (!(actual.equals(expected))) {
            ReportUtil.log(step, LogDetail, "pass");
        } else {
            ReportUtil.log(step, assertMessage, "fail");
        }
        Assert.assertNotEquals(actual, expected, assertMessage);
    }



    public static void assertEquals(int actual, int expected, String step, String LogDetail, String assertMessage) {
        if (actual==(expected)) {
            ReportUtil.log(step, LogDetail, "pass");
        } else {
            ReportUtil.log(step, assertMessage, "fail");
        }
        Assert.assertEquals(actual, expected, assertMessage);
    }


    public static void assertNotEquals(int actual, int expected, String step, String LogDetail, String assertMessage) {
        if (actual!=(expected)) {
            ReportUtil.log(step, LogDetail, "pass");
        } else {
            ReportUtil.log(step, assertMessage, "fail");
        }
        Assert.assertNotEquals(actual, expected, assertMessage);
    }


    public static void assertEquals(boolean actual, boolean expected, String step, String LogDetail, String assertMessage) {
        if (actual==expected) {
            ReportUtil.log(step, LogDetail, "pass");
        } else {
            ReportUtil.log(step, assertMessage, "fail");
        }
        Assert.assertEquals(actual, expected,assertMessage);
    }

    public static void assertNotEquals(boolean  actual, boolean expected, String step, String LogDetail, String assertMessage) {
        if (actual!=(expected)) {
            ReportUtil.log(step, LogDetail, "pass");
        } else {
            ReportUtil.log(step, assertMessage, "fail");
        }
        Assert.assertNotEquals(actual, expected, assertMessage);
    }


    public static void assertTrue(boolean actual,String step,String LogDetail, String assertMessage) {
        if (actual==true) {
            ReportUtil.log(step, LogDetail, "pass");
        } else {
            ReportUtil.log(step, assertMessage, "fail");
        }
        Assert.assertTrue(actual, assertMessage);
    }

    public static void assertFalse(boolean actual,String step,String LogDetail, String assertMessage) {
        if (actual==false) {
            ReportUtil.log(step, LogDetail, "pass");
        } else {
            ReportUtil.log(step, assertMessage, "fail");
        }
        Assert.assertFalse(actual, assertMessage);
    }

    public static void fail(String step, String assertMessage){
        ReportUtil.log(step, assertMessage, "fail");
        Assert.fail(assertMessage);
    }
}