package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R231;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.LsLms.BlackBoard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.LsLms.ErrorScreen;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyNotes.MyNote;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Resources.AllResources;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by rashmi on 03-02-2016.
 */
public class BlackboardSSOLaunchStudent extends Driver {

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
        Assignments assignments;
        MyNote myNote;

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
        assignments=PageFactory.initElements(driver,Assignments.class);
            myNote=PageFactory.initElements(driver,MyNote.class);




        }

    @Test(priority = 1, enabled = true)
    public void studentSSOLaunchWplsHomepage() {
        try {

            new LoginUsingLTI().ltiLogin("25");// student wpls-homepage launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in TC studentSSOLaunchWplsHomepage of class BlackboardSSOLaunchStudent", e);

        }

    }
    @Test(priority = 2, enabled = true)
    public void studentSSOLaunchWplsChapter() {
        try {
             new LoginUsingLTI().ltiLogin("26");// student wpls-chapter launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(lessonPage.bookmarkLessonIcon.isDisplayed(), true, "wpls-chapter launch failed");// To verify wpls-chapter launch
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(), true, "Main Navigator Blackboard icon is not displayed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen
        } catch (Exception e) {
            Assert.fail("Exception in TC studentSSOLaunchWplsChapter of class BlackboardSSOLaunchStudent", e);

        }

    }
    @Test(priority = 3, enabled = true)
    public void studentSSOLaunchWplseBook() {
        try {

            new LoginUsingLTI().ltiLogin("27");// student wpls-ebook launch
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
            Assert.fail("Exception in TC studentSSOLaunchWplseBook of class BlackboardSSOLaunchStudent", e);

        }

    }
    @Test(priority = 4, enabled = true)
    public void studentSSOLaunchWplsGradebook() {
        try {

            new LoginUsingLTI().ltiLogin("28");// student wpls-gradebook launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(assignments.AllAssignmentsFilter.isDisplayed(),true,"wpls-gradebook launch failed");
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC studentSSOLaunchWplsGradebook of class BlackboardSSOLaunchStudent", e);

        }

    }
    @Test(priority = 5, enabled = true)
    public void studentSSOLaunchWplsCourseStream() {
        try {
            new LoginUsingLTI().ltiLogin("29");// student wpls-coursestream launch
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
            Assert.fail("Exception in TC studentSSOLaunchWplsCourseStream of class BlackboardSSOLaunchStudent", e);

        }

    }
    @Test(priority = 6, enabled = true)
    public void studentSSOLaunchWplsReports() {
        try {
            new LoginUsingLTI().ltiLogin("30");// student wpls-reports launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(proficiencyReport.getProficiencyReportTitleStudentSide().isDisplayed(),true,"wpls-reports launch failed");
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC studentSSOLaunchWplsReports of class BlackboardSSOLaunchStudent", e);

        }

    }
    @Test(priority = 7, enabled = true)
    public void studentSSOLaunchWplsPrepare() {
        try {
            new LoginUsingLTI().ltiLogin("31");// student wpls-prepare launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(errorScreen.myNotesInstructorErrorScreen().isDisplayed(), true, "student error screen not displayed");
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");
            dashboard.getTitle().click();// Click on WPLS header to check whether user is navigated to Dashboard from error screen
            Assert.assertEquals(dashboard.DashboardStudyButton.isDisplayed(),true,"User is not navigated to dashboard from error screen");

        } catch (Exception e) {
            Assert.fail("Exception in TC studentSSOLaunchWplsPrepare of class BlackboardSSOLaunchStudent", e);

        }

    }
    @Test(priority = 8, enabled = true)
    public void studentSSOLaunchWplsTotalScore() {
        try {
            new LoginUsingLTI().ltiLogin("32");// student wpls-totalscore launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(assignments.AllAssignmentsFilter.isDisplayed(),true,"wpls-totalscore launch failed");
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC studentSSOLaunchWplsTotalScore of class BlackboardSSOLaunchStudent", e);

        }

    }
    @Test(priority = 9, enabled = true)
    public void studentSSOLaunchWplsStudentGrades() {
        try {
            new LoginUsingLTI().ltiLogin("33");// student wpls-StudentGrades launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();



            Assert.assertEquals(assignments.AllAssignmentsFilter.isDisplayed(),true,"wpls-StudentGrades launch failed");
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC studentSSOLaunchWplsStudentGrades of class BlackboardSSOLaunchStudent", e);

        }

    }
    //This page is not available for your user role type.
    @Test(priority = 10, enabled = true)
    public void studentSSOLaunchWplsnotes() {
        try {
            new LoginUsingLTI().ltiLogin("34");// student wpls-notes launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(myNote.NoteButton.isDisplayed(), true, "student wpls-notes launch failed");
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen
        } catch (Exception e) {
            Assert.fail("Exception in TC studentSSOLaunchWplsnotes of class BlackboardSSOLaunchStudent", e);

        }

    }
    @Test(priority = 11, enabled = true)
    public void studentSSOLaunchWplscreatescheduleassingments() {
        try {
            new LoginUsingLTI().ltiLogin("35");// student wpls-createscheduleassingments launch

            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(errorScreen.myNotesInstructorErrorScreen().isDisplayed(), true, "student error screen not displayed");
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");
            dashboard.getTitle().click();// Click on WPLS header to check whether user is navigated to Dashboard from error screen
            Assert.assertEquals(dashboard.DashboardStudyButton.isDisplayed(),true,"User is not navigated to dashboard from error screen");

        } catch (Exception e) {
            Assert.fail("Exception in TC studentSSOLaunchWplscreatescheduleassingments of class BlackboardSSOLaunchStudent", e);

        }

    }
    @Test(priority = 12, enabled = true)
    public void studentSSOLaunchWplsAssignments() {
        try {
            new LoginUsingLTI().ltiLogin("36");// student wpls-assignments launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();


            Assert.assertEquals(assignments.AllAssignmentsFilter.isDisplayed(),true,"wpls-assignments launch failed");
            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");
            Assert.assertEquals(dashboard.getTitle().isDisplayed(), false, "WPLS header is displayed");// To verify WPLS should NOT be displayed in any page other than Dashboard and error screen
            Assert.assertEquals(dashboard.CourseNameOnHeader.isDisplayed(),true,"course name on header is not displayed" );// To verify that course name should be displayed in all page except Dashboard and error screen

        } catch (Exception e) {
            Assert.fail("Exception in TC studentSSOLaunchWplsAssignments of class BlackboardSSOLaunchStudent", e);

        }

    }

    @Test(priority = 13, enabled = true)
    public void guidParameterLaunch() {
        try {
            new LoginUsingLTI().ltiLogin("60");// student wpls-homepage launch
            new Navigator().closeWalkMe();
            new Navigator().closeHelp();




            dashboard.getProfileDropDown().click(); // click on My profile drop down
            Assert.assertEquals(blackBoard.getMyBlackBoard().getText(), "My Blackboard", "My Blackboard is not displayed");// To verify whether My blackboard is displayed or not
            Assert.assertEquals(blackBoard.MyProfileBlackboardIcon.isDisplayed(), true, "Blackboard icon is not displayed");
            dashboard.getMainNavigator().click(); //click on main navigator icon
            Assert.assertEquals(blackBoard.goBackToMyBlackBoard.getText(), "Go back to My Blackboard", "Go to My Blackboard is not displayed"); // To verify whether go back to my blackboard is displayed or not
            Assert.assertEquals(blackBoard.MainNavigatorBlackboardIcon.isDisplayed(),true,"Main Navigator Blackboard icon is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in TC guidParameterLaunch of class BlackboardSSOLaunchStudent", e);

        }

    }

}
