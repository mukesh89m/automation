package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.enhancedAssignmentResponsePage;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Perspective;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.*;

import java.util.List;

/**
 * Created by mukesh on 3/8/16.
 */
public class EnhancedAssignmentResponsePage extends Driver {



    WebDriver driver;
    AssignmentResponsesPage assignmentResponsesPage;
    AssessmentResponses assessmentResponses;
    CurrentAssignments currentAssignments;
    NewAssignment newAssignment;
    Assignments assignments;
    LessonPage lessonPage;
    Gradebook gradebook;
    Perspective perspective;
    Dashboard dashBoard;
    AssignmentTab assignmentTab;

    String actual = "";
    String expected = "";
    int actualSize = 0;
    int expectedSize = 0;

    @BeforeMethod
    public void initElement() {
        driver = Driver.getWebDriver();
        assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
        assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);
        currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
        newAssignment = PageFactory.initElements(driver, NewAssignment.class);
        assignments = PageFactory.initElements(driver, Assignments.class);
        lessonPage = PageFactory.initElements(driver, LessonPage.class);
        gradebook = PageFactory.initElements(driver, Gradebook.class);
        lessonPage= PageFactory.initElements(driver, LessonPage.class);
        dashBoard = PageFactory.initElements(driver, Dashboard.class);
        perspective = PageFactory.initElements(driver, Perspective.class);
        assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);

    }


    @Test(priority = 1, enabled = true)
    public void actionLinkInAssignmentResponsePage() {
        try {

            new LoginUsingLTI().ltiLogin("1_1"); //login as student
            new LoginUsingLTI().ltiLogin("1");//log in as instructor

            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Assignment().createFileBasedAssessmentAtInstructorSide(2);
            ReportUtil.log("Create FileBasedAssignment", "FileBasedAssignment created successfully", "pass");
            new Assignment().assignFileBasedAssignmentFromMyQuestionBank(2);
            ReportUtil.log("Assign FileBasedAssignment", "FileBasedAssignment Assigned to class section level successfully", "pass");
            new Assignment().createDiscussionAssignment(3);
            ReportUtil.log("Create DiscussionBasedAssignment", "DiscussionBasedAssignment created successfully", "pass");
            new Assignment().assignDiscussionAssignmentToGroup(3);
            ReportUtil.log("Assign DiscussionBasedAssignment", "DiscussionBasedAssignment Assigned to class section level successfully", "pass");
            Thread.sleep(5000);
            WebDriverUtil.clickOnElementUsingJavascript(dashBoard.toc_drop); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(1);
            ReportUtil.log("Assign OrionAdaptivePractice", "OrionAdaptivePractice Assigned to class section level successfully", "pass");

            new Navigator().NavigateTo("Class Assignments");
            actual =currentAssignments.assignment_action.get(0).getAttribute("title").trim();
            expected = "View Student Responses";
            CustomAssert.assertEquals(actual, expected, "Verify View Student Responses link", "View Student Responses is displaying", "View Student Responses is not displaying");

            actual =currentAssignments.assignment_action.get(1).getAttribute("title").trim();
            expected = "Update Assignment";
            CustomAssert.assertEquals(actual, expected, "Verify Update Assignment link", "Update Assignment is displaying", "Update Assignment is not displaying");

            actual =currentAssignments.assignment_action.get(2).getAttribute("title").trim();
            expected = "Un-assign Assignment";
            CustomAssert.assertEquals(actual, expected, "Verify Un-assign Assignment link", "Un-assign Assignment is displaying", "Un-assign Assignment is not displaying");
            currentAssignments.viewStudentResponses.get(0).click();

            CustomAssert.assertEquals(WebDriverUtil.verifyColor(assignmentResponsesPage.action_link), "#a6a6a6", "Verify Action link disable", "Action link disable ByDefault", "Action link is not disable ByDefault");
            CustomAssert.assertEquals(WebDriverUtil.verifyColor(assignmentResponsesPage.releaseGrade_link),"#a6a6a6","Verify Release Grade For All link disable","Release Grade For All link disable ByDefault","Release Grade For All link is not disable ByDefault");

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.student_checkBox.get(0));
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.menuIcon);
            assignmentResponsesPage.notApplicable.click();
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.notApplicable_No);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.menuIcon);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.postAMessage);
            assignmentResponsesPage.messageTextField.sendKeys("Message");
            assignmentResponsesPage.sendButton.click();

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(5000);
            CustomAssert.assertEquals(WebDriverUtil.verifyColor(assignmentResponsesPage.action_link), "#a6a6a6", "Verify Action link disable", "Action link disable ByDefault", "Action link is not disable ByDefault");
            Thread.sleep(120000);

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.viewStudentResponses.get(0).click();
            try {
                WebDriverUtil.clickOnElementUsingJavascript(assessmentResponses.resumeGrading_button);
                Thread.sleep(5000);
                WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            } catch (Exception e) {

            }
            Thread.sleep(5000);
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all

            currentAssignments.viewStudentResponses.get(0).click();
            try {
                WebDriverUtil.clickOnElementUsingJavascript(assessmentResponses.resumeGrading_button);
                Thread.sleep(5000);
                WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            } catch (Exception e) {

            }
            Thread.sleep(5000);
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all

            currentAssignments.viewStudentResponses.get(0).click();
            try {
                WebDriverUtil.clickOnElementUsingJavascript(assessmentResponses.resumeGrading_button);
                Thread.sleep(5000);
                WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            } catch (Exception e) {

            }
            Thread.sleep(5000);
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all

            CustomAssert.assertEquals(WebDriverUtil.verifyColor(assignmentResponsesPage.action_link), "#a6a6a6", "Verify Action link disable", "Action link disable ByDefault", "Action link is not disable ByDefault");
            CustomAssert.assertEquals(WebDriverUtil.verifyColor(assignmentResponsesPage.releaseGrade),"#a6a6a6","Verify Release Grade For All link disable","Release Grade For All link disable ByDefault","Release Grade For All link is not disable ByDefault");

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.student_checkBox.get(0));
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.menuIcon);
            assignmentResponsesPage.notApplicable.click();
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.notApplicable_No);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.menuIcon);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.postAMessage);
            assignmentResponsesPage.messageTextField.sendKeys("Message");
            assignmentResponsesPage.sendButton.click();

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(5000);
            CustomAssert.assertEquals(WebDriverUtil.verifyColor(assignmentResponsesPage.action_link), "#a6a6a6", "Verify Action link disable", "Action link disable ByDefault", "Action link is not disable ByDefault");


        } catch (Exception e) {
            Assert.fail("Exception in TC actionLinkInAssignmentResponsePage of class  EnhancedAssignmentResponsePage", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void extendDueDateLinkInAssignmentResponsePage() {
        try {
            String assignmentName = ReadTestData.readDataByTagName("", "assessmentname", "5");
            String context_title = ReadTestData.readDataByTagName("", "context_title", "5");

            new LoginUsingLTI().ltiLogin("4_1"); //login as student
            new LoginUsingLTI().ltiLogin("4");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Assignment().createFileBasedAssessmentAtInstructorSide(5);
            ReportUtil.log("Create FileBasedAssignment", "FileBasedAssignment created successfully", "pass");
            new Assignment().assignFileBasedAssignmentFromMyQuestionBank(5);
            ReportUtil.log("Assign FileBasedAssignment", "FileBasedAssignment Assigned to class section level successfully", "pass");
            new Assignment().createDiscussionAssignment(6);
            ReportUtil.log("Create DiscussionBasedAssignment", "DiscussionBasedAssignment created successfully", "pass");
            new Assignment().assignDiscussionAssignmentToGroup(6);
            ReportUtil.log("Assign DiscussionBasedAssignment", "DiscussionBasedAssignment Assigned to class section level successfully", "pass");
            Thread.sleep(5000);
            WebDriverUtil.clickOnElementUsingJavascript(dashBoard.toc_drop); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(4);
            ReportUtil.log("Assign OrionAdaptivePractice", "OrionAdaptivePractice Assigned to class section level successfully", "pass");

            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.viewStudentResponses.get(0).click();
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date link

            actual =assignmentResponsesPage.extendDueDateDialogHeader.get(1).getText().trim();
            expected = "Extend Due Date";
            CustomAssert.assertEquals(actual, expected, "Verify Extend Due Date pop up", "Extend Due Date pop up is  displaying", "Extend Due Date pop up is not displaying");
            CustomAssert.assertTrue(assignmentResponsesPage.extendDueDateDialogHeader.get(0).isDisplayed(),"Verify Extend Due Date with a Time Icon","\"Extend Due Date\" displayed with a \"Time Icon\"","\"Extend Due Date\" not displayed with a \"Time Icon\"");
            CustomAssert.assertTrue(assignmentResponsesPage.extendDueDateDialogHeader.get(2).isDisplayed(),"Verify \"x\" mark","\"X\" mark displayed on top right corner.","\"X\" mark not displayed on top right corner.");

            actual =assignmentResponsesPage.extendDueDate_AssignmentName.getText().trim();
            CustomAssert.assertEquals(actual, assignmentName, "Verify assignment name below of the Extend Due Date label", "Assignment name below of the Extend Due Date label is displaying", "assignment name below of the Extend Due Date label is not displaying");

            actual =assignmentResponsesPage.extendDueDateFor_label.get(0).getText().trim();
            expected = "Extend Due date for:";
            CustomAssert.assertEquals(actual, expected, "Verify Extend Due date for:", "Extend Due date for: label is displaying", "Extend Due date for: label is not displaying");

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateFor_label.get(1)); //click on the help icon
            Thread.sleep(2000);

            actual =assignmentResponsesPage.extendDueDateFor_label.get(2).getText().trim();
            expected = "Enter student or group name(s) to extend for students or groups.";
            CustomAssert.assertEquals(actual, expected, "Verify help text", "help text is displaying as "+expected+"", "Help text is not displaying as "+expected+"");

            actual =assignmentResponsesPage.extendDueDate_classSectionSuggestion.getText().trim();
            CustomAssert.assertEquals(actual, context_title, "Verify default class section name", "Default class section name is displaying", "Default class section name is not displaying");

            actual =assignmentResponsesPage.newDueDate_label.getText().trim();
            expected = "New Due Date:";
            CustomAssert.assertEquals(actual, expected, "Verify New Due Date:", "New Due Date:label is displaying as "+expected+"", "New Due Date:label not displaying as "+expected+"");

            Thread.sleep(4000);
            assignmentResponsesPage.extended_dueDate.click(); //click on the extended due date
            CustomAssert.assertTrue(assignmentResponsesPage.datePicker.isDisplayed(), "Verify Date picker pop up", "Date picker pop up is displaying", "Date picker pop up is not displaying");
            driver.findElement(By.cssSelector("body")).click();
            CustomAssert.assertFalse(assignmentResponsesPage.datePicker.isDisplayed(), "Verify Date picker pop up", "Date picker pop up is not displaying", "Date picker pop up is still displaying after clicked on body");
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.closeIcon);
            CustomAssert.assertFalse(new BooleanValue().presenceOfElement(5, assignmentResponsesPage.extendDueDate_emptyClassSection), "Verify empty class section", "Class section suggestion should not displayed", "After click on the close icon Class section suggestion is still displaying");

            assignmentResponsesPage.updateExtendDueDate.click();
            String color = assignmentResponsesPage.extended_dueTime.getCssValue("border-top-color");
            Color col = Color.fromString(color);
            String hexValue = col.asHex();
            System.out.println("hexValue:"+hexValue);

            Assert.assertEquals(hexValue,"#ff0000");
            assignmentResponsesPage.extended_dueTime_Cancel_button.click(); //click on the cancel button
            CustomAssert.assertFalse(new BooleanValue().presenceOfElement(5, assignmentResponsesPage.extendDueDate_emptyClassSection), "Verify empty class section", "Class section suggestion should not displayed", "After click on the close icon Class section suggestion is still displaying");

            new Assignment().extentDueTimeFromAssignmentResponsePage("5");
            Thread.sleep(4000);
            String [] originalTime=assignmentResponsesPage.originalDueDates.getText().trim().split(",");
            System.out.println(originalTime[2]);
            String [] extendedTime=assignmentResponsesPage.extendedDueDates.get(1).getText().trim().split(",");
            System.out.println(extendedTime[2]);
            CustomAssert.assertFalse(originalTime[2].equals(extendedTime[2]), "Verify updated due date", "Due date is getting updated","Due date is not getting updated");
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date link
            assignmentResponsesPage.updateExtendDueDate.click();
            color = assignmentResponsesPage.extended_dueTime.getCssValue("border-top-color");
            col = Color.fromString(color);
            hexValue = col.asHex();
            Assert.assertEquals(hexValue, "#ff0000");
            new LoginUsingLTI().ltiLogin("4");//log in as instructor
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.viewStudentResponses.get(0).click();

            originalTime=assignmentResponsesPage.originalDueDates.getText().trim().split(",");
            System.out.println(originalTime[2]);
            extendedTime=assignmentResponsesPage.extendedDueDates.get(2).getText().trim().split(",");
            System.out.println(extendedTime[2]);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date link
            expected=WebDriverUtil.executeJavascript("return document.getElementById('extended-due-time').value");
            CustomAssert.assertEquals(originalTime[2].trim(),expected.trim(),"Verify same default values (Date and Time) for “New Due Date”","The same default values (Date and Time) for “New Due Date” populating","The same default values (Date and Time) for “New Due Date” is not populating");


        } catch (Exception e) {
            Assert.fail("Exception in TC extendDueDateLinkInAssignmentResponsePage of class  EnhancedAssignmentResponsePage", e);
        }
    }


    @Test(priority = 3, enabled = true)
    public void extendDueDateAtIndividualGroupLevel() {
        try {

            WebDriver driver = Driver.getWebDriver();
            new LoginUsingLTI().ltiLogin("7_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("7_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("7");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName = AddGroup.addGroup(7); //add group
            System.out.println("groupName:" + groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 2);//add first three student to group

            new LoginUsingLTI().ltiLogin("7");
            new Assignment().createDiscussionAssignment(7);
            new Assignment().assignDiscussionAssignmentToGroup(7);
            new Assignment().extentDueTimeFromAssignmentResponsePage("8");
            Thread.sleep(4000);

            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.viewStudentResponses.get(0).click();
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date link
            actual =assignmentResponsesPage.classSection_list.get(0).getText().trim();
            expected="a, month";
            CustomAssert.assertEquals(actual, expected, "Verify default individual student name", "Default individual student name is displaying", "Default individual student name is not displaying");

            actual =assignmentResponsesPage.classSection_list.get(1).getText().trim();
            expected="b, month";
            CustomAssert.assertEquals(actual, expected, "Verify default individual student name", "Default individual student name is displaying", "Default individual student name is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC extendDueDateAtIndividualGroupLevel of class  EnhancedAssignmentResponsePage", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void extendDueDateAtGroupLevel() {
        try {
            String context_title = ReadTestData.readDataByTagName("", "context_title", "9");

            WebDriver driver = Driver.getWebDriver();
            new LoginUsingLTI().ltiLogin("9_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("9_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("9");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName = AddGroup.addGroup(9); //add group
            System.out.println("groupName:" + groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 2);//add first three student to group

            new LoginUsingLTI().ltiLogin("9");
            new Assignment().createDiscussionAssignment(9);
            new Assignment().assignDiscussionAssignmentToGroup(9);
            new Assignment().extentDueTimeFromAssignmentResponsePageWithGroup("10");
            Thread.sleep(4000);
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.viewStudentResponses.get(0).click();
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date link
            actual =assignmentResponsesPage.extendDueDate_classSectionSuggestion.getText().trim();
            CustomAssert.assertEquals(actual, context_title, "Verify default class section name", "Default class section name is displaying", "Default class section name is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC extendDueDateAtGroupLevel of class  EnhancedAssignmentResponsePage", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void verifyTinyScrollInClassSectionSuggestion() {
        try {

            WebDriver driver = Driver.getWebDriver();
            new LoginUsingLTI().ltiLogin("11_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("11_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("11");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName = AddGroup.addGroup(11); //add group
            System.out.println("groupName:" + groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 2);//add first three student to group

            AddGroup.selectGroup(2);//select group
            AddGroup.addStudentToParticularGroup(2, 0, 2);//add first three student to group*/

            new LoginUsingLTI().ltiLogin("11");
            new Assignment().createDiscussionAssignment(11);
            new Assignment().assignDiscussionAssignmentToGroup(11);

            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.viewStudentResponses.get(0).click();
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date link
            List<WebElement> allClassSection = driver.findElements(By.cssSelector("li[class='bit-box']"));
            for (WebElement classSection : allClassSection) {
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElements(By.className("closebutton")).get(driver.findElements(By.className("closebutton")).size() - 1));//click on close symbol
                String assignToField = new TextFetch().textfetchbyclass("holder");
                if (assignToField.length() == 0) {
                    break;
                }
            }

            assignmentResponsesPage.classSuggestion_textbox.sendKeys("gro");
            new MouseHover().mouserhoverbywebelement(driver.findElement(By.xpath("//ul[@id = 'share-with_feed']//li")));
            CustomAssert.assertTrue(assignmentResponsesPage.thumb.isDisplayed(),"Verify tiny scroll","Tiny scroll is displaying","Tiny Scroll is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC verifyTinyScrollInClassSectionSuggestion of class  EnhancedAssignmentResponsePage", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void moveStudentToDifferentClassSection() {
        try {

            WebDriver driver = Driver.getWebDriver();
            new LoginUsingLTI().ltiLogin("15");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different class section", "info");

            new LoginUsingLTI().ltiLogin("14_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("14_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("14");
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "info");


            String groupName = AddGroup.addGroup(14); //add group
            System.out.println("groupName:" + groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 2);//add first three student to group

            AddGroup.selectGroup(2);//select group
            AddGroup.addStudentToParticularGroup(2, 0, 2);//add first three student to group

            new LoginUsingLTI().ltiLogin("14");
            new Assignment().createDiscussionAssignment(14);
            new Assignment().assignDiscussionAssignmentToGroup(14);

            new LoginUsingLTI().ltiLogin("14_3"); //move to different class section
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");

            new LoginUsingLTI().ltiLogin("14");
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.viewStudentResponses.get(0).click();
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date link
            assignmentResponsesPage.closeIcon.click();
            assignmentResponsesPage.classSuggestion_textbox.sendKeys("gr");

            actual =assignmentResponsesPage.classSuggestion_errorMsg.getText().trim();
            expected="Enter at least 3 characters";
            CustomAssert.assertEquals(actual, expected, "Verify Enter at least 3 characters suggestion message", "Enter at least 3 characters suggestion message is displaying", "Enter at least 3 characters suggestion message is not displaying");

            assignmentResponsesPage.classSuggestion_textbox.clear();
            assignmentResponsesPage.classSuggestion_textbox.sendKeys("b, month");

            actual =assignmentResponsesPage.classSuggestion_noResultMsg.getText().trim();
            expected="No results found";
            CustomAssert.assertEquals(actual, expected, "Verify No results found message", "No results found message is displaying", "No results found message is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC moveStudentToDifferentClassSection of class  EnhancedAssignmentResponsePage", e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void removeStudentFromClassSection() {
        try {

            WebDriver driver = Driver.getWebDriver();

            new LoginUsingLTI().ltiLogin("16_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("16_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("16");
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "info");


            String groupName = AddGroup.addGroup(16); //add group
            System.out.println("groupName:" + groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 2);//add first three student to group

            AddGroup.selectGroup(2);//select group
            AddGroup.addStudentToParticularGroup(2, 0, 2);//add first three student to group

            new LoginUsingLTI().ltiLogin("16");
            new Assignment().createDiscussionAssignment(16);
            new Assignment().assignDiscussionAssignmentToGroup(16);

            new LoginUsingLTI().ltiLogin("19"); //remove student

            new LoginUsingLTI().ltiLogin("16");
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.viewStudentResponses.get(0).click();
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date link
            assignmentResponsesPage.closeIcon.click();
            assignmentResponsesPage.classSuggestion_textbox.sendKeys("gr");

            actual =assignmentResponsesPage.classSuggestion_errorMsg.getText().trim();
            expected="Enter at least 3 characters";
            CustomAssert.assertEquals(actual, expected, "Verify Enter at least 3 characters suggestion message", "Enter at least 3 characters suggestion message is displaying", "Enter at least 3 characters suggestion message is not displaying");

            assignmentResponsesPage.classSuggestion_textbox.clear();
            assignmentResponsesPage.classSuggestion_textbox.sendKeys("b, month");

            actual =assignmentResponsesPage.classSuggestion_noResultMsg.getText().trim();
            expected="No results found";
            CustomAssert.assertEquals(actual, expected, "Verify No results found message", "No results found message is displaying", "No results found message is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in TC removeStudentFromClassSection of class  EnhancedAssignmentResponsePage", e);
        }
    }


    @Test(priority = 8, enabled = true)
    public void moveIndividualStudentToDifferentClassSection() {
        try {

            String context_title = ReadTestData.readDataByTagName("", "context_title", "19");
            WebDriver driver = Driver.getWebDriver();
            new LoginUsingLTI().ltiLogin("18");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different class section", "info");

            new LoginUsingLTI().ltiLogin("17_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("17_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("17");
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "info");


            String groupName = AddGroup.addGroup(17); //add group
            System.out.println("groupName:" + groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 2);//add first three student to group

            AddGroup.selectGroup(2);//select group
            AddGroup.addStudentToParticularGroup(2, 0, 2);//add first three student to group

            new LoginUsingLTI().ltiLogin("17");
            new Assignment().createDiscussionAssignment(17);
            new Assignment().assignDiscussionAssignmentToGroup(17);

            new LoginUsingLTI().ltiLogin("17_3"); //move student to different class section
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");

            new LoginUsingLTI().ltiLogin("17");
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.viewStudentResponses.get(0).click();
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date link

            actual =assignmentResponsesPage.classSection_list.get(0).getText().trim();
            expected="a, month";
            CustomAssert.assertEquals(actual, expected, "Verify default individual student name", "Default individual student name is displaying", "Default individual student name is not displaying");

            int selectedSize=assignmentResponsesPage.classSection_list.size();
            Assert.assertEquals(selectedSize, 1);

            assignmentResponsesPage.closeIcon.click();
            assignmentResponsesPage.classSuggestion_textbox.sendKeys("gr");

            actual =assignmentResponsesPage.classSuggestion_errorMsg.getText().trim();
            expected="Enter at least 3 characters";
            CustomAssert.assertEquals(actual, expected, "Verify Enter at least 3 characters suggestion message", "Enter at least 3 characters suggestion message is displaying", "Enter at least 3 characters suggestion message is not displaying");

            new Assignment().extentDueTimeFromAssignmentResponsePage("19");
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date link
            actual =assignmentResponsesPage.extendDueDate_classSectionSuggestion.getText().trim();
            CustomAssert.assertEquals(actual, context_title, "Verify default class section name", "Default class section name is displaying", "Default class section name is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in TC moveIndividualStudentToDifferentClassSection of class  EnhancedAssignmentResponsePage", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void questionAssignmentWithP3DueDateEnable() {
        try {
            WebDriver driver = Driver.getWebDriver();

            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", "23");

            new Assignment().create(23);
            new Assignment().addQuestions(23, "essay", "");
            ReportUtil.log("create assessment", "Assessment created with manual graded question successfully", "info");

            new LoginUsingLTI().ltiLogin("23_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("23_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("23_3");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyTwo method", "info");
            new LoginUsingLTI().ltiLogin("23");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");
            ReportUtil.log("Create Policy", "policy three created successfully", "pass");
            new Assignment().assignAssignmentWithDueDate(23);//assign to student
            ReportUtil.log("Assign Assignment", "Instructor assigned assignment with due date to complete class section", "pass");

            new LoginUsingLTI().ltiLogin("23_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            new AttemptQuestion().essay(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("23_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();//click on Next
            Thread.sleep(60000);

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyTwo method", "info");
            new LoginUsingLTI().ltiLogin("23");//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            actual =currentAssignments.assignment_action.get(0).getAttribute("title").trim();
            expected = "View Student Responses";
            CustomAssert.assertEquals(actual, expected, "Verify View Student Responses link", "View Student Responses is displaying", "View Student Responses is not displaying");

            actual =currentAssignments.assignment_action.get(1).getAttribute("title").trim();
            expected = "Assignment Details";
            CustomAssert.assertEquals(actual, expected, "Verify Assignment Details link", "Assignment Details is displaying", "Assignment Details is not displaying");

            boolean unAssign=Boolean.FALSE;
            for (WebElement element:currentAssignments.assignment_action){
                if(element.getAttribute("title").trim().equals("Un-assign Assignment")){
                    unAssign=Boolean.TRUE;
                }
            }
            if (unAssign==Boolean.TRUE){
                CustomAssert.fail("Verify UnAssign Assignment link","UnAssign Assignment link is present");
            }
            currentAssignments.viewStudentResponses.get(0).click();

            actual =assignmentResponsesPage.resumeGrading_Text.getText().trim();
            expected = "The grade release process is on hold so you can extend due dates for students.\n" + "If you have no further extensions, click Yes to resume grading.";
            CustomAssert.assertEquals(actual, expected, "Verify resume grading link", "Resume grading link is displaying", "Resume grading link is not displaying");
            CustomAssert.assertFalse(new BooleanValue().presenceOfElement(23, assignmentResponsesPage.byReleaseGradeForAll), "Verify Release grade for all Link", "Release grade for all Link should not be displayed", "Release grade for all Link is displaying");

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date lin
            assignmentResponsesPage.extended_dueTime_Cancel_button.click(); //click on the cancel button

            actual =assignmentResponsesPage.resumeGrading_Text.getText().trim();
            expected = "The grade release process is on hold so you can extend due dates for students.\n" + "If you have no further extensions, click Yes to resume grading.";
            CustomAssert.assertEquals(actual, expected, "Verify resume grading link", "Resume grading link is displaying", "Resume grading link is not displaying");
            CustomAssert.assertFalse(new BooleanValue().presenceOfElement(23, assignmentResponsesPage.byReleaseGradeForAll), "Verify Release grade for all Link", "Release grade for all Link should not be displayed", "Release grade for all Link is displaying");

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date lin
            assignmentResponsesPage.extended_dueDate.click(); //click on the extended due date
            CustomAssert.assertTrue(assignmentResponsesPage.datePicker.isDisplayed(), "Verify Date picker pop up", "Date picker pop up is displaying", "Date picker pop up is not displaying");
            assignmentResponsesPage.extendDueDate_AssignmentName.click();
            CustomAssert.assertFalse(assignmentResponsesPage.datePicker.isDisplayed(), "Verify Date picker pop up", "Date picker pop up is not displaying", "Date picker pop up is still displaying after clicked on body");


            new Assignment().extentDueTimeFromAssignmentResponsePage("23");
            String[] originalTime=assignmentResponsesPage.originalDueDates.getText().trim().split(",");
            System.out.println(originalTime[2]);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date link
            expected=WebDriverUtil.executeJavascript("return document.getElementById('extended-due-time').value");
            CustomAssert.assertEquals(originalTime[2].trim(),expected.trim(),"Verify same default values (Date and Time) for “New Due Date”","The same default values (Date and Time) for “New Due Date” populating","The same default values (Date and Time) for “New Due Date” is not populating");
            Thread.sleep(60000);
            WebDriverUtil.clickOnElementUsingJavascript(assessmentResponses.resumeGrading_button);//click on yes
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getReleaseGradeForAll());


        } catch (Exception e) {
            Assert.fail("Exception in TC questionAssignmentWithP3AndP4DueDateEnable of class  EnhancedAssignmentResponsePage", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void questionAssignmentWithP4DueDateEnable() {
        try {
            WebDriver driver = Driver.getWebDriver();

            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", "25");

            new Assignment().create(25);
            ReportUtil.log("create assessment", "Assessment created with manual graded question successfully", "info");

            new LoginUsingLTI().ltiLogin("25_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("25_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("25_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyTwo method", "info");
            new LoginUsingLTI().ltiLogin("25");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");
            ReportUtil.log("Create Policy", "policy three created successfully", "pass");
            new Assignment().assignAssignmentWithDueDate(25);//assign to student
            ReportUtil.log("Assign Assignment", "Instructor assigned assignment with due date to complete class section", "pass");

            new LoginUsingLTI().ltiLogin("25_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("25_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            new AttemptQuestion().trueFalse(false, "correct", 1);
            Thread.sleep(60000);

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyTwo method", "info");
            new LoginUsingLTI().ltiLogin("25");//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            actual =currentAssignments.assignment_action.get(0).getAttribute("title").trim();
            expected = "View Student Responses";
            CustomAssert.assertEquals(actual, expected, "Verify View Student Responses link", "View Student Responses is displaying", "View Student Responses is not displaying");

            actual =currentAssignments.assignment_action.get(1).getAttribute("title").trim();
            expected = "Assignment Details";
            CustomAssert.assertEquals(actual, expected, "Verify Assignment Details link", "Assignment Details is displaying", "Assignment Details is not displaying");

            boolean unAssign=Boolean.FALSE;
            for (WebElement element:currentAssignments.assignment_action){
                if(element.getAttribute("title").trim().equals("Un-assign Assignment")){
                    unAssign=Boolean.TRUE;
                }
            }
            if (unAssign==Boolean.TRUE){
                CustomAssert.fail("Verify UnAssign Assignment link","UnAssign Assignment link is present");
            }
            currentAssignments.viewStudentResponses.get(0).click();

            actual =assignmentResponsesPage.resumeGrading_Text.getText().trim();
            expected = "The grade release process is on hold so you can extend due dates for students.\n" + "If you have no further extensions, click Yes to resume grading.";
            CustomAssert.assertEquals(actual, expected, "Verify resume grading link", "Resume grading link is displaying", "Resume grading link is not displaying");
            CustomAssert.assertFalse(new BooleanValue().presenceOfElement(25, assignmentResponsesPage.byReleaseGradeForAll), "Verify Release grade for all Link", "Release grade for all Link should not be displayed", "Release grade for all Link is displaying");

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date lin
            assignmentResponsesPage.extended_dueTime_Cancel_button.click(); //click on the cancel button

            actual =assignmentResponsesPage.resumeGrading_Text.getText().trim();
            expected = "The grade release process is on hold so you can extend due dates for students.\n" + "If you have no further extensions, click Yes to resume grading.";
            CustomAssert.assertEquals(actual, expected, "Verify resume grading link", "Resume grading link is displaying", "Resume grading link is not displaying");
            CustomAssert.assertFalse(new BooleanValue().presenceOfElement(25, assignmentResponsesPage.byReleaseGradeForAll), "Verify Release grade for all Link", "Release grade for all Link should not be displayed", "Release grade for all Link is displaying");

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date lin
            assignmentResponsesPage.extended_dueDate.click(); //click on the extended due date
            CustomAssert.assertTrue(assignmentResponsesPage.datePicker.isDisplayed(), "Verify Date picker pop up", "Date picker pop up is displaying", "Date picker pop up is not displaying");
            assignmentResponsesPage.extendDueDate_AssignmentName.click();
            CustomAssert.assertFalse(assignmentResponsesPage.datePicker.isDisplayed(), "Verify Date picker pop up", "Date picker pop up is not displaying", "Date picker pop up is still displaying after clicked on body");


            new Assignment().extentDueTimeFromAssignmentResponsePage("25");
            String[] originalTime=assignmentResponsesPage.originalDueDates.getText().trim().split(",");
            System.out.println(originalTime[2]);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date link
            expected=WebDriverUtil.executeJavascript("return document.getElementById('extended-due-time').value");
            CustomAssert.assertEquals(originalTime[2].trim(),expected.trim(),"Verify same default values (Date and Time) for “New Due Date”","The same default values (Date and Time) for “New Due Date” populating","The same default values (Date and Time) for “New Due Date” is not populating");
            Thread.sleep(60000);

            WebDriverUtil.clickOnElementUsingJavascript(assessmentResponses.resumeGrading_button);//click on yes
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getReleaseGradeForAll());
            CustomAssert.assertFalse(new BooleanValue().presenceOfElement(25, assignmentResponsesPage.byExtendDueDateLabel), "Verify 'Extend Due Date' link", "'Extend Due Date' link  should not be displayed", "'Extend Due Date' link  is displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC questionAssignmentWithP3AndP4DueDateEnable of class  EnhancedAssignmentResponsePage", e);
        }
    }

    @Test(priority = 11, enabled = true)
    public void fbaAssignmentDueDateEnable() {
        try {
            WebDriver driver = Driver.getWebDriver();

            String filename = ReadTestData.readDataByTagName("", "filename", "26");

            new LoginUsingLTI().ltiLogin("26_1"); //login as student
            new LoginUsingLTI().ltiLogin("26");//log in as instructor

            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Assignment().createFileBasedAssessmentAtInstructorSide(26);
            ReportUtil.log("Create FileBasedAssignment", "FileBasedAssignment created successfully", "pass");
            new Assignment().assignFileBasedAssignmentFromMyQuestionBank(26);

            new LoginUsingLTI().ltiLogin("26_1"); //login as student
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getAssessmentName());
            WebDriverUtil.clickOnElementUsingJavascript(newAssignment.uploadFileButton);//click on the upload file link;
            Thread.sleep(3000);
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            assignmentTab.finishButton.click();//click on finish assignment
            Thread.sleep(60000);

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyTwo method", "info");
            new LoginUsingLTI().ltiLogin("26");//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            actual =currentAssignments.assignment_action.get(0).getAttribute("title").trim();
            expected = "View Student Responses";
            CustomAssert.assertEquals(actual, expected, "Verify View Student Responses link", "View Student Responses is displaying", "View Student Responses is not displaying");

            actual =currentAssignments.assignment_action.get(1).getAttribute("title").trim();
            expected = "Assignment Details";
            CustomAssert.assertEquals(actual, expected, "Verify Assignment Details link", "Assignment Details is displaying", "Assignment Details is not displaying");

            boolean unAssign=Boolean.FALSE;
            for (WebElement element:currentAssignments.assignment_action){
                if(element.getAttribute("title").trim().equals("Un-assign Assignment")){
                    unAssign=Boolean.TRUE;
                }
            }
            if (unAssign==Boolean.TRUE){
                CustomAssert.fail("Verify UnAssign Assignment link","UnAssign Assignment link is present");
            }

            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.viewStudentResponses.get(0));
            actual =assignmentResponsesPage.resumeGrading_Text.getText().trim();
            expected = "The grade release process is on hold so you can extend due dates for students.\n" + "If you have no further extensions, click Yes to resume grading.";
            CustomAssert.assertEquals(actual, expected, "Verify resume grading link", "Resume grading link is displaying", "Resume grading link is not displaying");
            CustomAssert.assertFalse(new BooleanValue().presenceOfElement(26, assignmentResponsesPage.byReleaseGradeForAll), "Verify Release grade for all Link", "Release grade for all Link should not be displayed", "Release grade for all Link is displaying");

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date lin
            assignmentResponsesPage.extended_dueTime_Cancel_button.click(); //click on the cancel button

            actual =assignmentResponsesPage.resumeGrading_Text.getText().trim();
            expected = "The grade release process is on hold so you can extend due dates for students.\n" + "If you have no further extensions, click Yes to resume grading.";
            CustomAssert.assertEquals(actual, expected, "Verify resume grading link", "Resume grading link is displaying", "Resume grading link is not displaying");
            CustomAssert.assertFalse(new BooleanValue().presenceOfElement(26, assignmentResponsesPage.byReleaseGradeForAll), "Verify Release grade for all Link", "Release grade for all Link should not be displayed", "Release grade for all Link is displaying");

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date lin
            assignmentResponsesPage.extended_dueDate.click(); //click on the extended due date
            CustomAssert.assertTrue(assignmentResponsesPage.datePicker.isDisplayed(), "Verify Date picker pop up", "Date picker pop up is displaying", "Date picker pop up is not displaying");
            assignmentResponsesPage.extendDueDate_AssignmentName.click();
            CustomAssert.assertFalse(assignmentResponsesPage.datePicker.isDisplayed(), "Verify Date picker pop up", "Date picker pop up is not displaying", "Date picker pop up is still displaying after clicked on body");


            new Assignment().extentDueTimeFromAssignmentResponsePage("26");
            String[] originalTime=assignmentResponsesPage.originalDueDate_notSubmitted.getText().trim().split(",");
            System.out.println(originalTime[2]);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date link
            expected=WebDriverUtil.executeJavascript("return document.getElementById('extended-due-time').value");
            CustomAssert.assertEquals(originalTime[2].trim(),expected.trim(),"Verify same default values (Date and Time) for “New Due Date”","The same default values (Date and Time) for “New Due Date” populating","The same default values (Date and Time) for “New Due Date” is not populating");
            Thread.sleep(60000);
            WebDriverUtil.clickOnElementUsingJavascript(assessmentResponses.resumeGrading_button);//click on yes
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getReleaseGradeForAll());
            CustomAssert.assertFalse(new BooleanValue().presenceOfElement(26, assignmentResponsesPage.byExtendDueDateLabel), "Verify 'Extend Due Date' link", "'Extend Due Date' link  should not be displayed", "'Extend Due Date' link  is displaying");



        } catch (Exception e) {
            Assert.fail("Exception in TC fbaAssignmentDueDateEnable of class  EnhancedAssignmentResponsePage", e);
        }
    }

    @Test(priority = 11, enabled = true)
    public void dueDateDisableAndPolicyOne() {
        try {
            WebDriver driver = Driver.getWebDriver();

            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", "27");

            new Assignment().create(27);
            ReportUtil.log("create assessment", "Assessment created with manual graded question successfully", "info");

            new LoginUsingLTI().ltiLogin("27_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("27_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("27_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyTwo method", "info");
            new LoginUsingLTI().ltiLogin("27");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "","27");//policy one
            ReportUtil.log("Create Policy", "policy three created successfully", "pass");
            new Assignment().assignAssignmentWithDueDate(27);//assign to student
            ReportUtil.log("Assign Assignment", "Instructor assigned assignment with due date to complete class section", "pass");

            new LoginUsingLTI().ltiLogin("27_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("27_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            new AttemptQuestion().trueFalse(false, "correct", 1);

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyTwo method", "info");
            new LoginUsingLTI().ltiLogin("27");//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            actual =currentAssignments.assignment_action.get(0).getAttribute("title").trim();
            expected = "View Student Responses";
            CustomAssert.assertEquals(actual, expected, "Verify View Student Responses link", "View Student Responses is displaying", "View Student Responses is not displaying");

            actual =currentAssignments.assignment_action.get(1).getAttribute("title").trim();
            expected = "Assignment Details";
            CustomAssert.assertEquals(actual, expected, "Verify Assignment Details link", "Assignment Details is displaying", "Assignment Details is not displaying");

            actual =currentAssignments.assignment_action.get(2).getAttribute("title").trim();
            expected = "Un-assign Assignment";
            CustomAssert.assertEquals(actual, expected, "Verify Un-assign Assignment link", "Un-assign Assignment is displaying", "Un-assign Assignment is not displaying");
            currentAssignments.viewStudentResponses.get(0).click();

            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.viewStudentResponses.get(0));
            CustomAssert.assertEquals(WebDriverUtil.verifyColor(assignmentResponsesPage.releaseGrade_link),"#a6a6a6","Verify Release Grade For All link disable","Release Grade For All link disable ByDefault","Release Grade For All link is not disable ByDefault");

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date lin
            assignmentResponsesPage.extended_dueDate.click(); //click on the extended due date
            CustomAssert.assertTrue(assignmentResponsesPage.datePicker.isDisplayed(), "Verify Date picker pop up", "Date picker pop up is displaying", "Date picker pop up is not displaying");
            assignmentResponsesPage.extendDueDate_AssignmentName.click();
            CustomAssert.assertFalse(assignmentResponsesPage.datePicker.isDisplayed(), "Verify Date picker pop up", "Date picker pop up is not displaying", "Date picker pop up is still displaying after clicked on body");


            new Assignment().extentDueTimeFromAssignmentResponsePage("27");
            String[] originalTime=assignmentResponsesPage.originalDueDates.getText().trim().split(",");
            System.out.println(originalTime[2]);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date link
            expected=WebDriverUtil.executeJavascript("return document.getElementById('extended-due-time').value");
            CustomAssert.assertEquals(originalTime[2].trim(),expected.trim(),"Verify same default values (Date and Time) for “New Due Date”","The same default values (Date and Time) for “New Due Date” populating","The same default values (Date and Time) for “New Due Date” is not populating");
            Thread.sleep(60000);

            assignmentResponsesPage.getRefreshButton().click();
            CustomAssert.assertFalse(new BooleanValue().presenceOfElement(27, assignmentResponsesPage.byExtendDueDateLabel), "Verify 'Extend Due Date' link", "'Extend Due Date' link  should not be displayed", "'Extend Due Date' link  is displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC dueDateDisableAndPolicyOne of class  EnhancedAssignmentResponsePage", e);
        }
    }


    @Test(priority = 12, enabled = true)
    public void dueDateDisableAndPolicyTwo() {
        try {
            WebDriver driver = Driver.getWebDriver();

            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", "28");

            new Assignment().create(28);
            ReportUtil.log("create assessment", "Assessment created with manual graded question successfully", "info");

            new LoginUsingLTI().ltiLogin("28_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("28_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("28_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyTwo method", "info");
            new LoginUsingLTI().ltiLogin("28");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description118", "2", null, false, "1", "", "Auto-release on due date", "", "", "","28");//policy 2
            ReportUtil.log("Create Policy", "policy three created successfully", "pass");
            new Assignment().assignAssignmentWithDueDate(28);//assign to student
            ReportUtil.log("Assign Assignment", "Instructor assigned assignment with due date to complete class section", "pass");

            new LoginUsingLTI().ltiLogin("28_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("28_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getAssignmentName());
            new AttemptQuestion().trueFalse(false, "correct", 1);

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyTwo method", "info");
            new LoginUsingLTI().ltiLogin("28");//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            actual =currentAssignments.assignment_action.get(0).getAttribute("title").trim();
            expected = "View Student Responses";
            CustomAssert.assertEquals(actual, expected, "Verify View Student Responses link", "View Student Responses is displaying", "View Student Responses is not displaying");

            actual =currentAssignments.assignment_action.get(1).getAttribute("title").trim();
            expected = "Assignment Details";
            CustomAssert.assertEquals(actual, expected, "Verify Assignment Details link", "Assignment Details is displaying", "Assignment Details is not displaying");

            actual =currentAssignments.assignment_action.get(2).getAttribute("title").trim();
            expected = "Un-assign Assignment";
            CustomAssert.assertEquals(actual, expected, "Verify Un-assign Assignment link", "Un-assign Assignment is displaying", "Un-assign Assignment is not displaying");
            currentAssignments.viewStudentResponses.get(0).click();

            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.viewStudentResponses.get(0));
            CustomAssert.assertEquals(WebDriverUtil.verifyColor(assignmentResponsesPage.releaseGrade_link),"#a6a6a6","Verify Release Grade For All link disable","Release Grade For All link disable ByDefault","Release Grade For All link is not disable ByDefault");

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date lin
            assignmentResponsesPage.extended_dueDate.click(); //click on the extended due date
            CustomAssert.assertTrue(assignmentResponsesPage.datePicker.isDisplayed(), "Verify Date picker pop up", "Date picker pop up is displaying", "Date picker pop up is not displaying");
            assignmentResponsesPage.extendDueDate_AssignmentName.click();
            CustomAssert.assertFalse(assignmentResponsesPage.datePicker.isDisplayed(), "Verify Date picker pop up", "Date picker pop up is not displaying", "Date picker pop up is still displaying after clicked on body");


            new Assignment().extentDueTimeFromAssignmentResponsePage("28");
            String[] originalTime=assignmentResponsesPage.originalDueDates.getText().trim().split(",");
            System.out.println(originalTime[2]);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date link
            expected=WebDriverUtil.executeJavascript("return document.getElementById('extended-due-time').value");
            CustomAssert.assertEquals(originalTime[2].trim(),expected.trim(),"Verify same default values (Date and Time) for “New Due Date”","The same default values (Date and Time) for “New Due Date” populating","The same default values (Date and Time) for “New Due Date” is not populating");
            Thread.sleep(120000);

            assignmentResponsesPage.getRefreshButton().click();
            CustomAssert.assertFalse(new BooleanValue().presenceOfElement(28, assignmentResponsesPage.byExtendDueDateLabel), "Verify 'Extend Due Date' link", "'Extend Due Date' link  should not be displayed", "'Extend Due Date' link  is displaying");
            CustomAssert.assertEquals(assignmentResponsesPage.releaseGrade.getAttribute("title"), "Grade Released", "Verify Release Grade For All link disable", "Release Grade For All link disable ByDefault", "Release Grade For All link is not disable ByDefault");


        } catch (Exception e) {
            Assert.fail("Exception in TC dueDateDisableAndPolicyTwo of class  EnhancedAssignmentResponsePage", e);
        }
    }

    @Test(priority = 13, enabled = true)
    public void nonGradableAssignmentFlow() {
        try {

            WebDriver driver = Driver.getWebDriver();
            String filename = ReadTestData.readDataByTagName("", "filename", "29");

            new LoginUsingLTI().ltiLogin("29_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("29_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("29_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");

            new LoginUsingLTI().ltiLogin("29");//login as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Assignment().createFileBasedAssessmentAtInstructorSide(29);
            ReportUtil.log("Create FileBasedAssignment", "FileBasedAssignment created successfully", "pass");
            new Assignment().assignFileBasedAssignmentFromMyQuestionBank(29);

            new LoginUsingLTI().ltiLogin("29_1"); //login as student
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getAssessmentName());
            WebDriverUtil.clickOnElementUsingJavascript(newAssignment.uploadFileButton);//click on the upload file link;
            Thread.sleep(3000);
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            assignmentTab.finishButton.click();//click on finish assignment

            new LoginUsingLTI().ltiLogin("29_2"); //login as student
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getAssessmentName());
            WebDriverUtil.clickOnElementUsingJavascript(newAssignment.uploadFileButton);//click on the upload file link;
            Thread.sleep(3000);
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            assignmentTab.finishButton.click();//click on finish assignment*/

            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyTwo method", "info");
            new LoginUsingLTI().ltiLogin("29");//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            actual =currentAssignments.assignment_action.get(0).getAttribute("title").trim();
            expected = "View Student Responses";
            CustomAssert.assertEquals(actual, expected, "Verify View Student Responses link", "View Student Responses is displaying", "View Student Responses is not displaying");

            actual =currentAssignments.assignment_action.get(1).getAttribute("title").trim();
            expected = "Assignment Details";
            CustomAssert.assertEquals(actual, expected, "Verify Assignment Details link", "Assignment Details is displaying", "Assignment Details is not displaying");

            actual =currentAssignments.assignment_action.get(2).getAttribute("title").trim();
            expected = "Un-assign Assignment";
            CustomAssert.assertEquals(actual, expected, "Verify Un-assign Assignment link", "Un-assign Assignment is displaying", "Un-assign Assignment is not displaying");
            currentAssignments.viewStudentResponses.get(0).click();

            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.viewStudentResponses.get(0));
            CustomAssert.assertEquals(WebDriverUtil.verifyColor(assignmentResponsesPage.feedback_Box),"#a6a6a6","Verify Release Grade For All link disable","Release Grade For All link disable ByDefault","Release Grade For All link is not disable ByDefault");

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date lin
            assignmentResponsesPage.extended_dueDate.click(); //click on the extended due date
            CustomAssert.assertTrue(assignmentResponsesPage.datePicker.isDisplayed(), "Verify Date picker pop up", "Date picker pop up is displaying", "Date picker pop up is not displaying");
            assignmentResponsesPage.extendDueDate_AssignmentName.click();
            CustomAssert.assertFalse(assignmentResponsesPage.datePicker.isDisplayed(), "Verify Date picker pop up", "Date picker pop up is not displaying", "Date picker pop up is still displaying after clicked on body");

            new Assignment().extentDueTimeFromAssignmentResponsePage("29");
            String[] originalTime=assignmentResponsesPage.originalDueDates.getText().trim().split(",");
            System.out.println(originalTime[2]);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date link
            expected=WebDriverUtil.executeJavascript("return document.getElementById('extended-due-time').value");
            CustomAssert.assertEquals(originalTime[2].trim(),expected.trim(),"Verify same default values (Date and Time) for “New Due Date”","The same default values (Date and Time) for “New Due Date” populating","The same default values (Date and Time) for “New Due Date” is not populating");
            Thread.sleep(60000);

            assignmentResponsesPage.getRefreshButton().click();
            CustomAssert.assertFalse(new BooleanValue().presenceOfElement(29, assignmentResponsesPage.byExtendDueDateLabel), "Verify 'Extend Due Date' link", "'Extend Due Date' link  should not be displayed", "'Extend Due Date' link  is displaying");
            CustomAssert.assertEquals(assignmentResponsesPage.feedback_Box.getAttribute("title"), "Feedback Released", "Verify Feedback Grade For All link disable", "Feedback Grade For All link disable ByDefault", "Release Grade For All link is not disable ByDefault");


        } catch (Exception e) {
            Assert.fail("Exception in TC nonGradableAssignmentFlow of class  EnhancedAssignmentResponsePage", e);
        }
    }

    @Test(priority = 14, enabled = true)
    public void learningActivityWithDueDateDisable() {
        try {

            WebDriver driver = Driver.getWebDriver();
            String filename = ReadTestData.readDataByTagName("", "filename", "30");

            new LoginUsingLTI().ltiLogin("30_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("30_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("30_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");

            new LoginUsingLTI().ltiLogin("30"); //login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(30);
            ReportUtil.log("Login as student", "Login as student for flowForGradableDWAssignment method", "info");

            new LoginUsingLTI().ltiLogin("30_1");//login a student
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);//click on the resources name
            String perspectives = new RandomString().randomstring(4);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspectives); //add perspective to DW assignemnt
            ReportUtil.log("Add perspective","Perspective added successfully","pass");

            new LoginUsingLTI().ltiLogin("30_2");//login a student
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);//click on the resources name
            perspectives = new RandomString().randomstring(4);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspectives); //add perspective to DW assignemnt
            ReportUtil.log("Add perspective","Perspective added successfully","pass");


            ReportUtil.log("Login as instructor", "Login as instructor for gradableAssignmentWithPolicyTwo method", "info");
            new LoginUsingLTI().ltiLogin("30");//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            actual =currentAssignments.assignment_action.get(0).getAttribute("title").trim();
            expected = "View Student Responses";
            CustomAssert.assertEquals(actual, expected, "Verify View Student Responses link", "View Student Responses is displaying", "View Student Responses is not displaying");

            actual =currentAssignments.assignment_action.get(1).getAttribute("title").trim();
            expected = "Assignment Details";
            CustomAssert.assertEquals(actual, expected, "Verify Assignment Details link", "Assignment Details is displaying", "Assignment Details is not displaying");

            actual =currentAssignments.assignment_action.get(2).getAttribute("title").trim();
            expected = "Un-assign Assignment";
            CustomAssert.assertEquals(actual, expected, "Verify Un-assign Assignment link", "Un-assign Assignment is displaying", "Un-assign Assignment is not displaying");
            currentAssignments.viewStudentResponses.get(0).click();

            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.viewStudentResponses.get(0));

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date lin
            assignmentResponsesPage.extended_dueTime_Cancel_button.click(); //click on the cancel button

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date lin
            assignmentResponsesPage.extended_dueDate.click(); //click on the extended due date
            CustomAssert.assertTrue(assignmentResponsesPage.datePicker.isDisplayed(), "Verify Date picker pop up", "Date picker pop up is displaying", "Date picker pop up is not displaying");
            assignmentResponsesPage.extendDueDate_AssignmentName.click();
            CustomAssert.assertFalse(assignmentResponsesPage.datePicker.isDisplayed(), "Verify Date picker pop up", "Date picker pop up is not displaying", "Date picker pop up is still displaying after clicked on body");

            new Assignment().extentDueTimeFromAssignmentResponsePage("30");
            String[] originalTime=assignmentResponsesPage.originalDueDates.getText().trim().split(",");
            System.out.println(originalTime[2]);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.extendDueDateLabel); //click on the extended due date link
            expected=WebDriverUtil.executeJavascript("return document.getElementById('extended-due-time').value");
            CustomAssert.assertEquals(originalTime[2].trim(),expected.trim(),"Verify same default values (Date and Time) for “New Due Date”","The same default values (Date and Time) for “New Due Date” populating","The same default values (Date and Time) for “New Due Date” is not populating");
            Thread.sleep(60000);

            assignmentResponsesPage.getRefreshButton().click();
            CustomAssert.assertFalse(new BooleanValue().presenceOfElement(30, assignmentResponsesPage.byExtendDueDateLabel), "Verify 'Extend Due Date' link", "'Extend Due Date' link  should not be displayed", "'Extend Due Date' link  is displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC learningActivityWithDueDateDisable of class  EnhancedAssignmentResponsePage", e);
        }
    }

}
