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
public class BlackboardSSOLaunchInstructor extends Driver {

    Dashboard dashboard;
    BlackBoard blackBoard;
    LessonPage lessonPage;
    Gradebook gradebook;
    CourseStreamPage courseStreamPage;
    ProficiencyReport proficiencyReport;
    AllResources allResources;
    ErrorScreen errorScreen;
    NewAssignment newAssignment;
    QuestionBank questionBank;
    CurrentAssignments currentAssignments;

    @BeforeMethod
    public void pageFactory(){
        WebDriver driver=Driver.getWebDriver();
        dashboard= PageFactory.initElements(driver,Dashboard.class);
        blackBoard=PageFactory.initElements(driver,BlackBoard.class);
        lessonPage= PageFactory.initElements(driver,LessonPage.class);
        gradebook=PageFactory.initElements(driver, Gradebook.class);
        courseStreamPage=PageFactory.initElements(driver,CourseStreamPage.class);
        proficiencyReport=PageFactory.initElements(driver,ProficiencyReport.class);
        allResources=PageFactory.initElements(driver,AllResources.class);
        errorScreen=PageFactory.initElements(driver,ErrorScreen.class);
        questionBank=PageFactory.initElements(driver,QuestionBank.class);
        newAssignment=PageFactory.initElements(driver,NewAssignment.class);
        currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);



    }
    @Test(priority = 1, enabled = true)
    public void instructorSSOLaunchWplsHomepage() {
        try {


            new LoginUsingLTI().ltiLogin("13");// instructor wpls-homepage launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsHomepage of class BlackboardSSOLaunch", e);

        }

    }
    @Test(priority = 2, enabled = true)
    public void instructorSSOLaunchWplsChapter() {
        try {
            WebDriver driver=Driver.getWebDriver();
            String Url= driver.getCurrentUrl();
            new LoginUsingLTI().ltiLogin("14");// instructor wpls-chapter launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(lessonPage.assign_lesson_icon.isDisplayed(), true, "wpls-chapter launch failed");// To verify wpls-chapter launch
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

                               } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsChapter of class BlackboardSSOLaunch", e);

        }

    }
    @Test(priority = 3, enabled = true)
    public void instructorSSOLaunchWplseBook() {
        try {
            new LoginUsingLTI().ltiLogin("15");// instructor wpls-ebook launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(lessonPage.searchETextBook.isDisplayed(), true, "wpls-ebook launch failed");// wpls-ebook launch verification
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplseBook of class BlackboardSSOLaunch", e);

        }

    }
    @Test(priority = 4, enabled = true)
    public void instructorSSOLaunchWplsGradebook() {
        try {
            new LoginUsingLTI().ltiLogin("16");// instructor wpls-gradebook launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();

            Assert.assertEquals(gradebook.getGradebookWeighting().isDisplayed(),true,"wpls-gradebook launch failed");
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsGradebook of class BlackboardSSOLaunch", e);

        }

    }
    @Test(priority = 5, enabled = true)
    public void instructorSSOLaunchWplsCourseStream() {
        try {

            new LoginUsingLTI().ltiLogin("17");// instructor wpls-coursestream launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();

            Assert.assertEquals(courseStreamPage.postTextBox.isDisplayed(),true,"wpls-coursestream launch failed");
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsCourseStream of class BlackboardSSOLaunch", e);

        }

    }
    @Test(priority = 6, enabled = true)
    public void instructorSSOLaunchWplsReports() {
        try {
            new LoginUsingLTI().ltiLogin("18");// instructor wpls-reports launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(proficiencyReport.getProficiencyReportTitle().isDisplayed(),true,"wpls-reports launch failed");
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsReports of class BlackboardSSOLaunch", e);

        }

    }
    @Test(priority = 7, enabled = true)
    public void instructorSSOLaunchWplsPrepare() {
        try {

            new LoginUsingLTI().ltiLogin("19");// instructor wpls-prepare launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(allResources.getAllResources().isDisplayed(),true,"wpls-prepare launch failed");
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsPrepare of class BlackboardSSOLaunch", e);

        }

    }
    @Test(priority = 8, enabled = true)
    public void instructorSSOLaunchWplsTotalScore() {
        try {
            new LoginUsingLTI().ltiLogin("20");// instructor wpls-totalscore launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(gradebook.getGradebookWeighting().isDisplayed(),true,"wpls-totalscore launch failed");
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsTotalScore of class BlackboardSSOLaunch", e);

        }

    }
    @Test(priority = 9, enabled = true)
    public void instructorSSOLaunchWplsStudentGrades() {
        try {
            new LoginUsingLTI().ltiLogin("21");// instructor wpls-StudentGrades launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(gradebook.getGradebookWeighting().isDisplayed(),true,"wpls-StudentGrades launch failed");
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsStudentGrades of class BlackboardSSOLaunch", e);

        }

    }
    //This page is not available for your user role type.
    @Test(priority = 10, enabled = true)
    public void instructorSSOLaunchWplsnotes() {
        try {

            new LoginUsingLTI().ltiLogin("22");// instructor wpls-notes launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(errorScreen.myNotesInstructorErrorScreen().isDisplayed(), true, "My notes for instructor not throwing to error screen");
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");
            dashboard.getTitle().click();// Click on WPLS header to check whether user is navigated to Dashboard from error screen
            Assert.assertEquals(dashboard.getNewAssignmentButton().isDisplayed(),true,"User is not navigated to dashboard from error screen");
        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsnotes of class BlackboardSSOLaunch", e);

        }

    }
    @Test(priority = 11, enabled = true)
    public void instructorSSOLaunchWplscreatescheduleassingments() {
        try {
            new LoginUsingLTI().ltiLogin("23");// instructor wpls-createscheduleassingments launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(blackBoard.getCreateCustomAssingnmentsbutton().isDisplayed(), true, "wpls-createscheduleassingments launch failed");
            Assert.assertEquals(blackBoard.getUsePrecreatedAssignmentbutton().isDisplayed(), true, "wpls-createscheduleassingments launch failed");
            Assert.assertEquals(blackBoard.getCreateFileBasedAssignment().isDisplayed(), true, "wpls-createscheduleassingments launch failed");
            Assert.assertEquals(blackBoard.DashboardDiscussionAssignmentButton.isDisplayed(),true, "wpls-createscheduleassingments launch failed");

            blackBoard.getCreateCustomAssingnmentsbutton().click();// verification of 'creating custom assessment' button.
            Assert.assertEquals(newAssignment.saveForLater_Button.isDisplayed(), true, "create custom assignment button not working");

            new LoginUsingLTI().ltiLogin("23");// instructor wpls-createscheduleassingments launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            blackBoard.getUsePrecreatedAssignmentbutton().click();//verification of 'use pre-created assignment' button.
            Assert.assertEquals(questionBank.getQuestionBankTitle().isDisplayed(), true, "use pre-created assignment button not working");

            new LoginUsingLTI().ltiLogin("23");// instructor wpls-createscheduleassingments launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            blackBoard.getCreateFileBasedAssignment().click();//verification of 'create file based assignment' button.
            Assert.assertEquals(newAssignment.uploadFileButton.isDisplayed(), true, "use pre-created assignment button not working");
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");

            new LoginUsingLTI().ltiLogin("23");// instructor wpls-createscheduleassingments launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();

            blackBoard.DashboardDiscussionAssignmentButton.click(); //click on create discussion assignment button
            Assert.assertEquals(newAssignment.saveForLater_Button.isDisplayed(), true, "create discussion assignment button not working");

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplscreatescheduleassingments of class BlackboardSSOLaunch", e);

        }

    }
    @Test(priority = 12, enabled = true)
    public void instructorSSOLaunchWplsAssignments() {
        try {
            new LoginUsingLTI().ltiLogin("24");// instructor wpls-assignments launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(currentAssignments.newAssignment_Button.isDisplayed(),true,"wpls-assignments launch failed");
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(), true, "Main Navigator Blackboard icon is not displayed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsAssignments of class BlackboardSSOLaunch", e);

        }

    }

    @Test(priority = 13, enabled = true)
    public void testGuidParameter(){
        try {
            new LoginUsingLTI().ltiLogin("59");// instructor wpls-homepage launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorSSOLaunchWplsHomepage of class BlackboardSSOLaunch", e);

        }

    }
}
