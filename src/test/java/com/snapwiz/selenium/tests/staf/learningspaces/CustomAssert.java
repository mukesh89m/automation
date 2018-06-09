package com.snapwiz.selenium.tests.staf.learningspaces;

import org.testng.Assert;

import java.util.Objects;

/**
 * Created by mukesh on 14/1/16.
 */
public class CustomAssert extends Driver {

    public static void assertEquals(String actual, String expected, String step, String LogDetail, String assertMessage) {
        if (actual.equals(expected)) {
            log(step, LogDetail, "pass");
        } else {
            log(step, assertMessage, "fail");
        }
        Assert.assertEquals(actual, expected,assertMessage);
    }

    public static void assertNotEquals(String actual, String expected, String step, String LogDetail, String assertMessage) {
        if (!(actual.equals(expected))) {
            log(step, LogDetail, "pass");
        } else {
            log(step, assertMessage, "fail");
        }
        Assert.assertNotEquals(actual, expected, assertMessage);
    }



    public static void assertEquals(int actual, int expected, String step, String LogDetail, String assertMessage) {
        if (actual==(expected)) {
            log(step, LogDetail, "pass");
        } else {
            log(step, assertMessage, "fail");
        }
        Assert.assertEquals(actual, expected, assertMessage);
    }


    public static void assertNotEquals(int actual, int expected, String step, String LogDetail, String assertMessage) {
        if (actual!=(expected)) {
            log(step, LogDetail, "pass");
        } else {
            log(step, assertMessage, "fail");
        }
        Assert.assertNotEquals(actual, expected, assertMessage);
    }


    public static void assertEquals(boolean actual, boolean expected, String step, String LogDetail, String assertMessage) {
        if (actual==expected) {
            log(step, LogDetail, "pass");
        } else {
            log(step, assertMessage, "fail");
        }
        Assert.assertEquals(actual, expected,assertMessage);
    }

    public static void assertNotEquals(boolean  actual, boolean expected, String step, String LogDetail, String assertMessage) {
        if (actual!=(expected)) {
            log(step, LogDetail, "pass");
        } else {
            log(step, assertMessage, "fail");
        }
        Assert.assertNotEquals(actual, expected, assertMessage);
    }


    public static void assertTrue(boolean actual, String step,String LogDetail, String assertMessage) {
        if (actual==true) {
            log(step, LogDetail, "pass");
        } else {
            log(step, assertMessage, "fail");
        }
        Assert.assertTrue(actual, assertMessage);
    }

    public static void assertFalse(boolean actual, String step,String LogDetail, String assertMessage) {
        if (actual==false) {
            log(step, LogDetail, "pass");
        } else {
            log(step, assertMessage, "fail");
        }
        Assert.assertFalse(actual, assertMessage);
    }

    public static void fail(String step, String assertMessage){
        log(step, assertMessage, "fail");
        Assert.fail(assertMessage);
    }
}