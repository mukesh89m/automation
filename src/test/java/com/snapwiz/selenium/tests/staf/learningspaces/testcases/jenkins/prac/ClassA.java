package com.snapwiz.selenium.tests.staf.learningspaces.testcases.jenkins.prac;

import org.openqa.selenium.By;
import org.testng.annotations.*;

import java.net.MalformedURLException;

/**
 * Created by mukesh on 22/7/16.
 */
public class ClassA  {

   /* @Test
    public void test_01() throws InterruptedException, MalformedURLException {
        try{
            getDriver().get("https://www.google.co.in/");
            getDriver().findElement(By.id("lst-ib")).sendKeys("fast");

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @Test
    public void test_02() throws InterruptedException, MalformedURLException {
        try{
            getDriver().get("http://www.flipkart.com/");

        }
        catch(Exception e){
            System.out.println(e);
        }
    }*/


    @Test
    public void testCase1() {
        System.out.println("in test case 1");
    }

    // test case 2
    @Test
    public void testCase2() {
        System.out.println("in test case 2");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("in beforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("in afterMethod");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("in beforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("in afterClass");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("in beforeTest");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("in afterTest");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("in beforeSuite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("in afterSuite");
    }
}
