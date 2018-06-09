package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT24.R2410;

import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by priyanka on 10-12-2015.
 */
public class AutoSubmitReleaseGradeTriggerEntireClassSection extends Driver{

    @Test(priority = 1, enabled = true)
    public  void  entireClassSectionThroughPolicyFour(){
        try {

            //tc row no 53

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "53");
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);

            new Assignment().create(53);
            new LoginUsingLTI().ltiLogin("53");//log in as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description21", "2", null, false, "1", "", "Release explicitly by the instructor", "", "", "");//policy 4
            new Assignment().assignToStudent(53);
            new Assignment().extendDueDateWithDefaultClassSection("53");//extend the due date for whole class
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(),true,"Due date has been extended to <month> <date>, <Year> format message is not appearing in that particular assignment's due date section.");

            new LoginUsingLTI().ltiLogin("53_1");//log in as student1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            new Assignment().submitAssignmentAsStudent(53);//submit assignment

            new LoginUsingLTI().ltiLogin("53_2");//log in as student2
            new Assignment().submitAssignmentAsStudent(53);//submit assignment

            new LoginUsingLTI().ltiLogin("53");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            Assert.assertEquals(assignmentResponsesPage.getReleaseGradeForAll().isEnabled(), true, "Release grades for all button is not enabled");// verify Release grades for all button

        }
        catch (Exception e) {
            Assert.fail("Exception in TC entireClassSectionThroughPolicyFour of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }


    @Test(priority = 2, enabled = true)
    public  void  assignParticularStudentThroughPolicyFour(){
        try {

            //tc row no 56

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "56");
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);

            new LoginUsingLTI().ltiLogin("56_1");//log in as student1
            new LoginUsingLTI().ltiLogin("56");//log in as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description21", "2", null, false, "1", "", "Release explicitly by the instructor", "", "", "");//policy 4
            new Assignment().assignToStudent(56);
            new Assignment().extendDueDate("56_1");//extend the due date for whole class
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(),true,"Due date has been extended to <month> <date>, <Year> format message is not appearing in that particular assignment's due date section.");

            new LoginUsingLTI().ltiLogin("56_2");//log in as student2
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            //Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            new Assignment().submitAssignmentAsStudent(56);//submit assignment

            new LoginUsingLTI().ltiLogin("56");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            boolean elementFound = false;
            try{
                driver.findElement(By.cssSelector("div[title='Release Grade for All']"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,true,"Release Grade for All button is enabled");


            new LoginUsingLTI().ltiLogin("56_1");//log in as student1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            new Assignment().submitAssignmentAsStudent(56);//submit assignment

            new LoginUsingLTI().ltiLogin("56");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            Assert.assertEquals(assignmentResponsesPage.getReleaseGradeForAll().isEnabled(), true, "Release grades for all button is not enabled");// verify Release grades for all button

        }
        catch (Exception e) {
            Assert.fail("Exception in TC assignParticularStudentThroughPolicyFour of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }


    @Test(priority = 3, enabled = true)
    public  void  entireClassSectionThroughPolicyThree(){
        try {

            //tc row no 61

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "61");
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(61));

            new Assignment().create(61);
            new Assignment().addQuestions(61,"essay","");
            new Assignment().addQuestions(61,"writeboard","");

            new LoginUsingLTI().ltiLogin("61");//log in as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//till save policy
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(3);
            new TopicOpen().clickOnStaticAssessmentArrow(assessmentname);
            lessonPage.assignThis_link.click(); //click on the assign this link
            new AssignLesson().assignTOCWithDefaultClassSection(61);//Assign to class
            new Assignment().extendDueDateWithDefaultClassSection("61");//extend the due date for whole class
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has been extended to <month> <date>, <Year> format message is not appearing in that particular assignment's due date section.");

            new LoginUsingLTI().ltiLogin("61_1");//log in as student1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            new Assignment().submitAssignmentAsStudent(61);//submit assignment

            new LoginUsingLTI().ltiLogin("61_2");//log in as student2
            new Assignment().submitAssignmentAsStudent(61);//submit assignment

            new LoginUsingLTI().ltiLogin("61");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            Assert.assertEquals(assignmentResponsesPage.getReleaseGradeForAll().isEnabled(), true, "Release grades for all button is not enabled");// verify Release grades for all button

        }
        catch (Exception e) {
            Assert.fail("Exception in TC entireClassSectionThroughPolicyThree of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }


    @Test(priority = 4, enabled = true)
    public  void  assignParticularStudentThroughPolicyThree(){
        try {

            //tc row no 64

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "64");
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(64));

            new LoginUsingLTI().ltiLogin("64_1");//log in as student1
            new LoginUsingLTI().ltiLogin("64");//log in as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//till save policy
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(3);
            new TopicOpen().clickOnStaticAssessmentArrow(assessmentname);
            lessonPage.assignThis_link.click(); //click on the assign this link
            new AssignLesson().assignTOCWithDefaultClassSection(64);//Assign to class
            new Assignment().extendDueDate("64_1");//extend the due date for whole class
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(),true,"Due date has been extended to <month> <date>, <Year> format message is not appearing in that particular assignment's due date section.");

            new LoginUsingLTI().ltiLogin("64_2");//log in as student2
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            //Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            new Assignment().submitAssignmentAsStudent(64);//submit assignment

            new LoginUsingLTI().ltiLogin("64");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            boolean elementFound = false;
            try{
                driver.findElement(By.cssSelector("div[title='Release Grade for All']"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,true,"Release Grade for All button is enabled");


            new LoginUsingLTI().ltiLogin("64_1");//log in as student1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            new Assignment().submitAssignmentAsStudent(64);//submit assignment

            new LoginUsingLTI().ltiLogin("64");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            Assert.assertEquals(assignmentResponsesPage.getReleaseGradeForAll().isEnabled(), true, "Release grades for all button is not enabled");// verify Release grades for all button

        }
        catch (Exception e) {
            Assert.fail("Exception in TC assignParticularStudentThroughPolicyThree of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }

    @Test(priority = 5, enabled = true)
    public  void  entireClassSectionThroughPolicyOne(){
        try {

            //tc row no 69

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "69");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "69");
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "69");

            new LoginUsingLTI().ltiLogin("69_1");//log in as student1
            new LoginUsingLTI().ltiLogin("69_2");//log in as student2

            new LoginUsingLTI().ltiLogin("69");//log in as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//till save policy
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("69");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(69);//assign assignment
            new Assignment().extendDueDateWithDefaultClassSection("69");//extend the due date for whole class
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has been extended to <month> <date>, <Year> format message is not appearing in that particular assignment's due date section.");

            new LoginUsingLTI().ltiLogin("69_1");//log in as student1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            new Assignment().submitAssignmentAsStudent(69);//submit assignment

            new LoginUsingLTI().ltiLogin("69_2");//log in as student2
            new Assignment().submitAssignmentAsStudent(69);//submit assignment

            new LoginUsingLTI().ltiLogin("69");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            //Assert.assertEquals(assignmentResponsesPage.getReleaseGradeForAll().isEnabled(), true, "Release grades for all button is not enabled");// verify Release grades for all button

        }
        catch (Exception e) {
            Assert.fail("Exception in TC entireClassSectionThroughPolicyOne of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }



    @Test(priority = 6, enabled = true)
    public  void  assignParticularStudentThroughPolicyOne(){
        try {

            //tc row no 73

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "73");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "73");
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "73");
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);

            new LoginUsingLTI().ltiLogin("73_1");//log in as student1
            new LoginUsingLTI().ltiLogin("73_2");//log in as student2

            new LoginUsingLTI().ltiLogin("73");//log in as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//till save policy
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("73");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(73);//assign assignment
            new Assignment().extendDueDate("73_1");//extend the due date for whole class
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(),true,"Due date has been extended to <month> <date>, <Year> format message is not appearing in that particular assignment's due date section.");

            new LoginUsingLTI().ltiLogin("73_2");//log in as student2
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            //Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            new Assignment().submitAssignmentAsStudent(73);//submit assignment

            new LoginUsingLTI().ltiLogin("73");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            boolean elementFound = false;
            try{
                driver.findElement(By.cssSelector("div[title='Release Grade for All']"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,true,"Release Grade for All button is enabled");


            new LoginUsingLTI().ltiLogin("73_1");//log in as student1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            new Assignment().submitAssignmentAsStudent(73);//submit assignment

            new LoginUsingLTI().ltiLogin("73");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            //Assert.assertEquals(assignmentResponsesPage.getReleaseGradeForAll().isEnabled(), true, "Release grades for all button is not enabled");// verify Release grades for all button


        }
        catch (Exception e) {
            Assert.fail("Exception in TC assignParticularStudentThroughPolicyOne of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }



    @Test(priority = 7, enabled = true)
    public  void  entireClassSectionDiscussionWidget(){
        try {

            //tc row no 80

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);

            new LoginUsingLTI().ltiLogin("80_1");//log in as student1
            new LoginUsingLTI().ltiLogin("80_2");//log in as student2

            new LoginUsingLTI().ltiLogin("80");//log in as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(80);
            new Assignment().extendDueDateWithDefaultClassSection("80");//extend the due date for whole class
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has been extended to <month> <date>, <Year> format message is not appearing in that particular assignment's due date section.");

            new LoginUsingLTI().ltiLogin("80_1");//log in as student1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            new Click().clickByclassname("learning-activity-title"); //click on DW
            String perspective = new RandomString().randomstring(2);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignment

            new LoginUsingLTI().ltiLogin("80_2");//log in as student2
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Click().clickByclassname("learning-activity-title"); //click on DW
            String perspective1 = new RandomString().randomstring(2);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective1); //add perspective to DW assignment

            new LoginUsingLTI().ltiLogin("80");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            Assert.assertEquals(assignmentResponsesPage.getReleaseGradeForAll().isEnabled(), true, "Release grades for all button is not enabled");// verify Release grades for all button

        }
        catch (Exception e) {
            Assert.fail("Exception in TC entireClassSectionDiscussionWidget of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }



    @Test(priority = 8, enabled = true)
    public  void assignParticularStudentDiscussionWidget(){
        try {

            //tc row no 83

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);

            new LoginUsingLTI().ltiLogin("83_1");//log in as student1
            new LoginUsingLTI().ltiLogin("83_2");//log in as student2

            new LoginUsingLTI().ltiLogin("83");//log in as instructor
            new Assignment().createFileBasedAssessmentAtInstructorSide(83);
            new Assignment().assignFileBasedAssignmentFromMyQuestionBank(83);
            new Assignment().extendDueDate("83");//extend the due date for whole class
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has been extended to <month> <date>, <Year> format message is not appearing in that particular assignment's due date section.");

            new LoginUsingLTI().ltiLogin("83_2");//log in as student2
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            //Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            currentAssignments.getAssessmentName().click();//click on assignment
            Thread.sleep(10000);
            assignmentTab.finishButton.click();//click on finish assignment
            assignmentTab.continueButton.click();//click on continue

            new LoginUsingLTI().ltiLogin("83");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            boolean elementFound = false;
            try{
                driver.findElement(By.cssSelector("div[title='Release Grade for All']"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,true,"Release Grade for All button is enabled");


            new LoginUsingLTI().ltiLogin("83_1");//log in as student1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            currentAssignments.getAssessmentName().click();//click on assignment
            Thread.sleep(10000);
            assignmentTab.finishButton.click();//click on finish assignment
            assignmentTab.continueButton.click();//click on continue

            new LoginUsingLTI().ltiLogin("83");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            Assert.assertEquals(assignmentResponsesPage.getReleaseGradeForAll().isEnabled(), true, "Release grades for all button is not enabled");// verify Release grades for all button

        }
        catch (Exception e) {
            Assert.fail("Exception in TC assignParticularStudentDiscussionWidget of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }


    @Test(priority = 9, enabled = true)
    public void dueDateLabelChangedToLastDueDate(){
        try {

            //tc row no 121

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin("121");//log in as instructor
            new Assignment().assignToStudent(121);
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click();// click on update assignment link
            currentAssignments.reAssign_link.click();//click on re-assign button.
            Assert.assertEquals(currentAssignments.dueDateLabel.get(5).getText(),"Last Due Date: *","Due date label is not changed to \"Last Due date\"");
            Assert.assertEquals(currentAssignments.lastDueDateHelpIcon.isDisplayed(),true,"Last Due date help icon is not present");
            currentAssignments.lastDueDateHelpIcon.click();//click on last due date help icon
            Assert.assertEquals(currentAssignments.lastDueDateHelpMessage.getText(),"If you changed the due date for the class, or for an individual student, the latest due date set by you for this assignment will be shown here."," help message is not available");
            Thread.sleep(2000);
            driver.findElement(By.id("additional-notes")).clear();
            driver.findElement(By.id("additional-notes")).sendKeys("This is Description");
            currentAssignments.getAssign().click();//click on assign
            String str= currentAssignments.updateNotificationMessage.getText();
            Assert.assertEquals(str,"The changes will be saved for the current tab only. Continue ? Yes | No","Update notification message is not displayed");
            currentAssignments.yesLinkAfterAssign.click();
            Thread.sleep(5000);
            Assert.assertEquals(currentAssignments.additionalNote.getText(), "This is Description", "Changes are not saved");

        }
        catch (Exception e) {
            Assert.fail("Exception in TC dueDateLabelChangedToLastDueDate of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }


    @Test(priority = 10, enabled = true)
      public void instructorViewResponsePage(){
        try {

            //tc row no 114

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            Dashboard dashboard = PageFactory.initElements(driver,Dashboard.class);

            new LoginUsingLTI().ltiLogin("114_1");//log in as student1
            new LoginUsingLTI().ltiLogin("114_2");//log in as student2

            new LoginUsingLTI().ltiLogin("114");//log in as instructor
            new Assignment().assignToStudent(114);
            new LoginUsingLTI().ltiLogin("114_1");//log in as student
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            assignments.assignmentName.click();//click on assignment

            new LoginUsingLTI().ltiLogin("114");//log in as instructor
            new Assignment().extendDueDate("114_1");//extend the due date for student1
            Thread.sleep(2000);
            currentAssignments.getViewGrade_link().click();//click on view student responses
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-extended-due-date-icon")));
            currentAssignments.extendedDueDateIcon.get(2).click();//extended due date icon
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.extendedDueDateTitle.get(2).getText(),"Due date has been extended to"," A visual indicator is not showing against that student name with tooltip as - Due date has been extended to <month> <date>, <Year> format.");
            Assert.assertEquals(currentAssignments.dueDateHasBeenExtendedMessage.get(3).isDisplayed(), true, "Due date has been extended to <month> <date>, <Year> format message is not appearing in that particular assignment's due date section.");

            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TOCShow().tocHide();//click on 'x'
            new Navigator().navigateToTab("Assignments");//navigate to assignment tab
            Assert.assertEquals(currentAssignments.extendedDueDateTitle.get(0).getText(),"Due date has been extended to","Due date has been extended to message is not available");

            new LoginUsingLTI().ltiLogin("114_1");//log in as student1
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TOCShow().tocHide();//click on 'x'
            new Navigator().navigateToTab("Assignments");//navigate to assignment tab
            Assert.assertEquals(currentAssignments.extendedDueDateTitle.get(0).getText(),"Due date has been extended to","Due date has been extended to message is not available");

            new Navigator().NavigateTo("Assignments");//navigate to assignments
            String extendedDueDate=assignments.getExtendedDueDate().getText();
            new Navigator().NavigateTo("Dashboard");//navigate to assignments//navigate to dashboard
            String upcomingDueDate=dashboard.upcomingTimeStamp.getText();
            if(!extendedDueDate.equals(upcomingDueDate)){
                Assert.fail("The upcoming assignment section is not consider the due date of the specific student in Dashboard's 'Upcoming' section.");
            }

        }
        catch (Exception e) {
            Assert.fail("Exception in TC instructorViewResponsePage of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }

    @Test(priority = 11, enabled = true)
    public void extendDueDateUIWhenStudentIsAdded(){
        try {

            //tc row no 107

            new LoginUsingLTI().ltiLogin("108");//log in as student1
            new LoginUsingLTI().ltiLogin("109");//log in as student2
            new LoginUsingLTI().ltiLogin("110");//log in as student3

            new LoginUsingLTI().ltiLogin("107");//log in as instructor
            new Assignment().assignToStudent(108);//assign to student1
            new LoginUsingLTI().ltiLogin("107");//log in as instructor
            new Assignment().assignToStudent(109);//assign to student2
            new Assignment().extendDueDate("110");//extend the due date for student3

            new LoginUsingLTI().ltiLogin("110");//log in as student3
            new Navigator().NavigateTo("Assignments");//navigate to assignments

            boolean elementFound = false;
            try{
                driver.findElement(By.className("ls-assignment-date-block"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,true,"due date extended message is displaying");

        }
        catch (Exception e) {
            Assert.fail("Exception in TC extendDueDateUIWhenStudentIsAdded of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }

    @Test(priority = 11, enabled = true)
    public void assignmentReference(){
        try {

            //tc row no 105
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(105));

            new LoginUsingLTI().ltiLogin("105");//log in as instructor
            new UploadResources().uploadResources("105", false, false, true);//upload chapterlevel resource
            new Assignment().assignToStudent(105);//assign to student
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments

            currentAssignments.getUpdateAssignment_button().click();// click on update assignment link
            currentAssignments.reAssign_link.click();//click on re-assign button.
            currentAssignments.assignmentReferenceDropDown.get(0).click();//click on assignment reference dropdown
            WebElement element = driver.findElement(By.xpath("//a[contains(@title,'"+resoursename+"')]"));
            element.click();//click on assignment reference
            currentAssignments.getAssign().click();//click on assign

            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click();// click on update assignment link
            currentAssignments.reAssign_link.click();//click on re-assign button.
            boolean elementFound = false;
            try{
                driver.findElement(By.cssSelector("span[class='confirm-submit-yes submit-assign']"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"update notification is opened");

            currentAssignments.assignmentReferenceDropDown.get(0).click();//click on assignment reference dropdown
            WebElement element1 = driver.findElement(By.xpath("//a[contains(@title,'"+resoursename+"')]"));
            element1.click();//click on assignment reference
            currentAssignments.getAssign().click();//click on assign
            currentAssignments.yesLinkAfterAssign.click();
            Thread.sleep(5000);
            Assert.assertEquals(currentAssignments.assignmentReferenceFile.isDisplayed(),true,"Assignment reference is not added ");


        }
        catch (Exception e) {
            Assert.fail("Exception in TC assignmentReference of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }

    @Test(priority = 12, enabled = true)
    public void autoSubmitNonGradable(){
        try {

            //tc row no 87
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);

            new LoginUsingLTI().ltiLogin("87");//log in as instructor
            new Assignment().assignToStudent(87);//assign to student
            new Assignment().extendDueDateWithDefaultClassSection("87");//extend the due date for whole class
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(),true,"Due date has been extended to <month> <date>, <Year> format message is not appearing in that particular assignment's due date section.");

            new LoginUsingLTI().ltiLogin("87_1");//log in as student1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            new Assignment().submitAssignmentAsStudent(87);//submit assignment

            new LoginUsingLTI().ltiLogin("87_2");//log in as student2
            new Assignment().submitAssignmentAsStudent(87);//submit assignment

            new LoginUsingLTI().ltiLogin("87");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            Assert.assertEquals(assignmentResponsesPage.getReleaseFeedbackForAll().isEnabled(), true, "Release grades for all button is not enabled");// verify Release grades for all button

        }
        catch (Exception e) {
            Assert.fail("Exception in TC autoSubmitNonGradable of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }

    @Test(priority = 13, enabled = true)
    public  void  assignParticularStudentNonGradable(){
        try {

            //tc row no 90

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);

            new LoginUsingLTI().ltiLogin("90_1");//log in as student1
            new LoginUsingLTI().ltiLogin("90");//log in as instructor
            new Assignment().assignToStudent(90);
            new Assignment().extendDueDate("90_1");//extend the due date for whole class
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(),true,"Due date has been extended to <month> <date>, <Year> format message is not appearing in that particular assignment's due date section.");

            new LoginUsingLTI().ltiLogin("90_2");//log in as student2
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            //Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            new Assignment().submitAssignmentAsStudent(90);//submit assignment

            new LoginUsingLTI().ltiLogin("90");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            boolean elementFound = false;
            try{
                driver.findElement(By.cssSelector("div[title='Release Grade for All']"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"Release Grade for All button is enabled");


            new LoginUsingLTI().ltiLogin("90_1");//log in as student1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(), true, "Due date has not been extended");// verify whether due date has been extended
            new Assignment().submitAssignmentAsStudent(90);//submit assignment

            new LoginUsingLTI().ltiLogin("90");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getViewGrade_link().click();//click on view student response link
            Assert.assertEquals(assignmentResponsesPage.getReleaseFeedbackForAll().isEnabled(), true, "Release grades for all button is not enabled");// verify Release grades for all button

        }
        catch (Exception e) {
            Assert.fail("Exception in TC assignParticularStudentNonGradable of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }

    @Test(priority = 14, enabled = true)
    public  void  changePolicyInUpdateAssignmentDetails(){
        try {

            //tc row no 100

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "100");

            new LoginUsingLTI().ltiLogin("100");//log in as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description21", "2", null, false, "1", "", "Release explicitly by the instructor", "", "", "");//policy 4
            new Assignment().assignToStudent(100);
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getUpdateAssignment_button().click();// click on update assignment link
            currentAssignments.reAssign_link.click();//click on re-assign button.
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Policy100']")));
            new Click().clickbyxpath("//a[@rel='addNewAssignmentPolicy']");
            new AssignmentPolicy().createAssignmentPolicyWhileAssigning(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//till save policy
            currentAssignments.getAssign().click();//click on assign
            currentAssignments.yesLinkAfterAssign.click();//click on yes
            currentAssignments.getUpdateAssignment_button().click();// click on update assignment link
            currentAssignments.reAssign_link.click();//click on re-assign button.
            currentAssignments.getDueDateOnAssignPopUp().click();// update assignment tab should be editable
            Thread.sleep(1000);
            currentAssignments.Next_Date_picker.click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("6")).click();

        }
        catch (Exception e) {
            Assert.fail("Exception in TC changePolicyInUpdateAssignmentDetails of class AutoSubmitReleaseGradeTriggerEntireClassSection", e);

        }
    }



}
