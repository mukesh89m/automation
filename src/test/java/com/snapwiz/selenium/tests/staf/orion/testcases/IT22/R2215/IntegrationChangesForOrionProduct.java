package com.snapwiz.selenium.tests.staf.orion.testcases.IT22.R2215;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.Dashboard.DashBoard;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by mukesh on 20/10/15.
 */
public class IntegrationChangesForOrionProduct {

    @Test(priority = 1,enabled = true)
    public void activeClassSectionAsPerTheClassSectionState() {

        try {
            //TC ROW NO 23
            Driver.startDriver();
            DashBoard dashBoard = PageFactory.initElements(Driver.driver, DashBoard.class);
            new LoginUsingLTI().ltiLogin("23");//login as instructor
            new LoginUsingLTI().ltiLogin("23_1");//login as instructor
            Assert.assertEquals(dashBoard.orionDashBoardIcon.getAttribute("title"),"ORION Dashboard","not navigated to orion dashboard page");
            dashBoard.classSectionDropDown.get(1).click();//click on class section dropdown
            Assert.assertEquals(dashBoard.getClassName().getText(), "Biology's 101", "Active class section name is not dispalying");

        } catch (Exception e) {
            Assert.fail("Exception in testcase activeClassSectionAsPerTheClassSectionState in class IntegrationChangesForOrionProduct", e);
        }

    }


    @Test(priority = 2,enabled = true)
    public void inActiveClassSectionAsPerTheClassSectionState() {

        try {
            //TC ROW NO 35
            Driver.startDriver();
            DashBoard dashBoard = PageFactory.initElements(Driver.driver, DashBoard.class);
            new LoginUsingLTI().ltiLogin("35_1");//login as instructor
            Assert.assertEquals(dashBoard.orionDashBoardIcon.getAttribute("title"), "ORION Dashboard", "not navigated to orion dashboard page");
            dashBoard.classSectionDropDown.get(1).click();//click on class section dropdown
            Assert.assertEquals(dashBoard.getClassName().getText(), "Biology's 101", "InActive class section name is not dispalying");
            new LoginUsingLTI().ltiLogin("35");//login as instructor
            Assert.assertEquals(dashBoard.defaultclassname.get(1).getText(), "Biology's 101", "Instructor is not able to access InActive class section");
            new LoginUsingLTI().ltiLogin("35_2");//login as student
            Assert.assertEquals(dashBoard.orionDashBoardIcon.getAttribute("title"), "ORION Dashboard", "not navigated to orion dashboard page");

        } catch (Exception e) {
            Assert.fail("Exception in testcase inActiveClassSectionAsPerTheClassSectionState in class IntegrationChangesForOrionProduct", e);
        }

    }

    @Test(priority = 3,enabled = true)
    public void finishedClassSectionAsPerTheClassSectionState() {

        try {
            //TC ROW NO 44
            Driver.startDriver();
            DashBoard dashBoard = PageFactory.initElements(Driver.driver, DashBoard.class);
            new LoginUsingLTI().ltiLogin("44_1");//login as instructor
            Assert.assertEquals(dashBoard.orionDashBoardIcon.getAttribute("title"), "ORION Dashboard", "not navigated to orion dashboard page");
            new LoginUsingLTI().ltiLogin("44");//login as instructor
            Assert.assertEquals(dashBoard.defaultclassname.get(1).getText(), "Biology's 101", "Instructor is not able to access InActive class section");
            new LoginUsingLTI().ltiLogin("44_2");//login as student
            Assert.assertEquals(dashBoard.orionDashBoardIcon.getAttribute("title"), "ORION Dashboard", "not navigated to orion dashboard page");

        } catch (Exception e) {
            Assert.fail("Exception in testcase finishedClassSectionAsPerTheClassSectionState in class IntegrationChangesForOrionProduct", e);
        }

    }

    @Test(priority = 4,enabled = true)
    public void customClassStateField() {

        try {
            //TC ROW NO 52
            Driver.startDriver();
            DashBoard dashBoard = PageFactory.initElements(Driver.driver, DashBoard.class);
            new LoginUsingLTI().ltiLogin("52");//login as instructor


        } catch (Exception e) {
            Assert.fail("Exception in testcase customClassStateField in class IntegrationChangesForOrionProduct", e);
        }

    }
}