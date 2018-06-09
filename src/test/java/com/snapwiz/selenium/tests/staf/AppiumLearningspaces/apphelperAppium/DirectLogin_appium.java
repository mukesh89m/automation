package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelperAppium;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pageFactoryAppium.HomePage_appium;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pageFactoryAppium.LoginPage_appium;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;

/*
 * Created by Dharaneesha on 04/08/15.
 */


public class DirectLogin_appium extends Driver {
    //LoginPage_appium loginPage = PageFactory.initElements(appiumDriver, LoginPage_appium.class);
    //HomePage_appium homePage = PageFactory.initElements(appiumDriver, HomePage_appium.class);

    public void loginAsInstructor()
    {
        try
        {
            loginPage.link_instructor.click();
            String pageTitle = homePage.title_homePage.getText();
            if(!pageTitle.equals("WileyPLUS Learning Space")){
                Assert.fail("Instructor is not Navigated to Home Page");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper 'loginAsInstructor' in class DirectLoginAppium",e);
        }
    }


    public void loginAsMentor()
    {
        try
        {
            loginPage.link_mentor.click();
            String pageTitle = homePage.title_homePage.getText();
            if(!pageTitle.equals("WileyPLUS Learning Space")){
                Assert.fail("Mentor is not Navigated to Home Page");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper 'loginAsMentor' in class DirectLoginAppium",e);
        }
    }



    public void loginAsStudent1()
    {
        try
        {
            loginPage.link_student1.click();
            String pageTitle = homePage.title_homePage.getText();
            if(!pageTitle.equals("WileyPLUS Learning Space")){
                Assert.fail("Student1 is not Navigated to Home Page");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper 'loginAsStudent1' in class DirectLoginAppium",e);
        }
    }


    public void loginAsStudent2()
    {
        try
        {
            loginPage.link_student2.click();
            String pageTitle = homePage.title_homePage.getText();
            if(!pageTitle.equals("WileyPLUS Learning Space")){
                Assert.fail("Student2 is not Navigated to Home Page");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper 'loginAsStudent2' in class DirectLoginAppium",e);
        }
    }


}