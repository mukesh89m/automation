package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R231;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.LsLms.BlackBoard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.LsLms.Canvas;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.LsLms.ErrorScreen;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Resources.AllResources;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by rashmi on 28-01-2016.
 */
public class CanvasSSOLaunchInstructor extends Driver {
    WebDriver driver;
    Dashboard dashboard;
    Canvas canvas;
    LessonPage lessonPage;
    Gradebook gradebook;
    CourseStreamPage courseStreamPage;
    ProficiencyReport proficiencyReport;
    AllResources allResources;
    ErrorScreen errorScreen;
    CurrentAssignments currentAssignments;

    @BeforeMethod
    public void pagefactory(){
        driver=Driver.getWebDriver();
        dashboard= PageFactory.initElements(driver, Dashboard.class);
        canvas=PageFactory.initElements(driver,Canvas.class);
        lessonPage= PageFactory.initElements(driver,LessonPage.class);
        gradebook=PageFactory.initElements(driver, Gradebook.class);
        courseStreamPage=PageFactory.initElements(driver,CourseStreamPage.class);
        proficiencyReport=PageFactory.initElements(driver,ProficiencyReport.class);
        allResources=PageFactory.initElements(driver,AllResources.class);
        errorScreen=PageFactory.initElements(driver,ErrorScreen.class);
        currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);


    }
    @Test(priority = 1, enabled = true)
    public void instructorSSOLaunchWplsHomepage() {
        try {
            new LoginUsingLTI().ltiLogin("39");// instructor wpls-homepage launch
            getWebDriver().switchTo().frame(getWebDriver().findElement(By.id("ls-lti-iframe")));// switching to iframe
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();
            Assert.assertEquals(dashboard.getNewAssignmentButton().isDisplayed(),true,"wpls-homepage launch failed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(),true,"WPLS header is correct");

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsHomepage of class CanvasSSOLaunchInstructor", e);

        }

    }
    @Test(priority = 2, enabled = true)
    public void instructorSSOLaunchWplsChapter() {
        try {
            String Url= driver.getCurrentUrl();
            new LoginUsingLTI().ltiLogin("40");// instructor wpls-chapter launch
            getWebDriver().switchTo().frame(getWebDriver().findElement(By.id("ls-lti-iframe")));// switching to iframe
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(lessonPage.assign_lesson_icon.isDisplayed(), true, "wpls-chapter launch failed");// To verify wpls-chapter launch
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsChapter of class CanvasSSOLaunchInstructor", e);

        }

    }
    @Test(priority = 3, enabled = true)
    public void instructorSSOLaunchWplseBook() {
        try {
            new LoginUsingLTI().ltiLogin("41");// instructor wpls-ebook launch
            getWebDriver().switchTo().frame(getWebDriver().findElement(By.id("ls-lti-iframe")));// switching to iframe
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();

            System.out.println("switched to iframe");
            Assert.assertEquals(lessonPage.searchETextBook.isDisplayed(), true, "wpls-ebook launch failed");// wpls-ebook launch verification
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplseBook of class CanvasSSOLaunchInstructor", e);

        }

    }
    @Test(priority = 4, enabled = true)
    public void instructorSSOLaunchWplsGradebook() {
        try {
            new LoginUsingLTI().ltiLogin("42");// instructor wpls-gradebook launch
            getWebDriver().switchTo().frame(getWebDriver().findElement(By.id("ls-lti-iframe")));// switching to iframe
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();

            Assert.assertEquals(gradebook.getGradebookWeighting().isDisplayed(),true,"wpls-gradebook launch failed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsGradebook of class CanvasSSOLaunchInstructor", e);

        }

    }
    @Test(priority = 5, enabled = true)
    public void instructorSSOLaunchWplsCourseStream() {
        try {
            new LoginUsingLTI().ltiLogin("43");// instructor wpls-coursestream launch
            getWebDriver().switchTo().frame(getWebDriver().findElement(By.id("ls-lti-iframe")));// switching to iframe
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(courseStreamPage.postTextBox.isDisplayed(),true,"wpls-coursestream launch failed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsCourseStream of class CanvasSSOLaunchInstructor", e);

        }

    }
    @Test(priority = 6, enabled = true)
    public void instructorSSOLaunchWplsReports() {
        try {
            new LoginUsingLTI().ltiLogin("44");// instructor wpls-reports launch
            getWebDriver().switchTo().frame(getWebDriver().findElement(By.id("ls-lti-iframe")));// switching to iframe
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(proficiencyReport.getProficiencyReportTitle().isDisplayed(),true,"wpls-reports launch failed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsReports of class CanvasSSOLaunchInstructor", e);

        }

    }
    @Test(priority = 7, enabled = true)
    public void instructorSSOLaunchWplsPrepare() {
        try {
            new LoginUsingLTI().ltiLogin("45");// instructor wpls-prepare launch
            getWebDriver().switchTo().frame(getWebDriver().findElement(By.id("ls-lti-iframe")));// switching to iframe
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(allResources.getAllResources().isDisplayed(),true,"wpls-prepare launch failed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsPrepare of class CanvasSSOLaunchInstructor", e);

        }

    }

    @Test(priority = 8, enabled = true)
    public void instructorSSOLaunchWplsGradeRefresh() {
        try {
            new LoginUsingLTI().ltiLogin("46");// instructor wpls-StudentGrades launch
            getWebDriver().switchTo().frame(getWebDriver().findElement(By.id("ls-lti-iframe")));// switching to iframe
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();

            Assert.assertEquals(gradebook.getGradebookWeighting().isDisplayed(),true,"wpls-StudentGrades launch failed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsGradeRefresh of class CanvasSSOLaunchInstructor", e);

        }

    }
    //This page is not available for your user role type.
    @Test(priority = 9, enabled = true)
    public void instructorSSOLaunchWplsnotes() {
        try {
            new LoginUsingLTI().ltiLogin("47");// instructor wpls-notes launch
            getWebDriver().switchTo().frame(getWebDriver().findElement(By.id("ls-lti-iframe")));// switching to iframe
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();

            Assert.assertEquals(errorScreen.myNotesInstructorErrorScreen().isDisplayed(), true, "My notes for instructor not throwing to error screen");
            dashboard.getTitle().click();// Click on WPLS header to check whether user is navigated to Dashboard from error screen
            Assert.assertEquals(dashboard.getNewAssignmentButton().isDisplayed(),true,"User is not navigated to dashboard from error screen");
        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsnotes of class CanvasSSOLaunchInstructor", e);

        }

    }

    @Test(priority = 10, enabled = true)
    public void instructorSSOLaunchWplsAssignments() {
        try {
            new LoginUsingLTI().ltiLogin("48");// instructor wpls-assignments launch
            getWebDriver().switchTo().frame(getWebDriver().findElement(By.id("ls-lti-iframe")));// switching to iframe
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();

            Assert.assertEquals(currentAssignments.newAssignment_Button.isDisplayed(),true,"wpls-assignments launch failed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsAssignments of class CanvasSSOLaunchInstructor", e);

        }

    }
    @Test(priority = 11, enabled = true)
    public void guidParameterLaunch() {
        try {
            new LoginUsingLTI().ltiLogin("61");// instructor wpls-StudentGrades launch
            getWebDriver().switchTo().frame(getWebDriver().findElement(By.id("ls-lti-iframe")));// switching to iframe
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();

            Assert.assertEquals(gradebook.getGradebookWeighting().isDisplayed(),true,"wpls-StudentGrades launch failed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC guidParameterLaunch of class CanvasSSOLaunchInstructor", e);

        }

    }
}
