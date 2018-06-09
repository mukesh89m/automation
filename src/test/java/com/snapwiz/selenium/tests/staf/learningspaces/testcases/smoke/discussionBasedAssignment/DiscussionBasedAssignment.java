package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.discussionBasedAssignment;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Perspective.*;

/**
 * Created by root on 5/18/16.
 */
public class DiscussionBasedAssignment extends Driver {
    @Test(priority = 1, enabled = true)
    public void validateSaveForLaterFeature() {
        try {
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("validate Save for Later feature", "Check all the details of save for later feature", "info");
            String dataIndex = "15";

            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            QuestionPage questionPage = PageFactory.initElements(driver, QuestionPage.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);

            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);

            new LoginUsingLTI().ltiLogin(dataIndex);//login as student1
            ReportUtil.log("Student Login", "Student logged in successfully", "Pass");
            new Navigator().NavigateTo("New Assignment");
            CustomAssert.assertEquals(newAssignment.assignment_title.getText().trim(), "Add New Assignment", "Validating 'Add New Assignment' pop up", "'Add New Assignment' pop up is displayed", "'Add New Assignment' pop up is not displayed");


            //2. The pop up should be closed
            //currentAssignments.close_Icon.click();
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.close_Icon);

            boolean isPopupClosed = true;
            try {
                currentAssignments.close_Icon.click();
                isPopupClosed = false;
            } catch (Exception e) {
            }
            if (isPopupClosed == false) {
                CustomAssert.fail("Validating 'Add New Assignment' pop up", "'Add New Assignment' pop up is not closed");
            }

            /*"3. The pop up should contain the following links
            1) Custom Assignment
            2) Pre-Created Assignment
            3) File based Assignment
            4) Discussion Assignment"*/
            new Navigator().NavigateTo("New Assignment");
            currentAssignments.createCustomAssignmentButton.click();
            newAssignment.close_Icon.click();

            new Navigator().NavigateTo("New Assignment");
            currentAssignments.getUsePreCreatedAssignment_button().click();

            new Navigator().NavigateTo("New Assignment");
            currentAssignments.createFileBasedAssignmentButton.click();
            newAssignment.close_Icon.click();

            new Navigator().NavigateTo("New Assignment");
            currentAssignments.discussionAssignmentButton.click();
            newAssignment.close_Icon.click();


            //4. Click on 'Discussion Assignment' link in the pop up
            //1. A new Tab 'New Assignment' should be displayed
            new Navigator().NavigateTo("New Assignment");
            currentAssignments.discussionAssignmentButton.click();
            if (driver.findElements(By.cssSelector("span[title = 'New Assignment']")).size() == 0) {
                CustomAssert.fail("Validating 'New Assignment' tab", "A new Tab 'New Assignment' is not displayed");
            }


            //2. There should be a text editor in the first row with the label 'Click to enter assignment name...'
            CustomAssert.assertEquals(newAssignment.assessmentNameTextBox.getText().trim(), "Click to enter assignment name...", "Validating label 'Click to enter assignment name...", "Text editor in the first row with the label 'Click to enter assignment name...is available", "text editor in the first row with the label 'Click to enter assignment name... is not available");


            //3. There should be a text Area in the 2nd row with the label 'Click to enter the question for discussion...'
            CustomAssert.assertEquals(newAssignment.assessmentDescriptionTextBox.getText().trim(), "Click to enter the question for discussion...", "Validating label 'Click to enter the question for discussion...", "Text editor in the 2nd row with the label 'Click to enter the question for discussion... is available", "Text editor in the first row with the label 'Click to enter the question for discussion... is not available");

            //4. The tab should contain the two buttons such as 'SAVE FOR LATER' and 'ASSIGN NOW'
            myQuestionBank.getSaveForLater().click();
            CustomAssert.assertEquals(myQuestionBank.getSaveForLater().isDisplayed(), true, "Validating 'SAVE FOR LATER' button", "'SAVE FOR LATER' button is displayed", "'SAVE FOR LATER' button is not displayed");
            CustomAssert.assertEquals(newAssignment.assignNowButton.isDisplayed(), true, "Validating 'ASSIGN NOW' button", "'ASSIGN NOW' button is displayed", "'ASSIGN NOW' button is not displayed");

            //5. Mouse Over on Text Editor 'Click to enter assignment name...'
            //It should show a Custom Image Icon at the end
            new MouseHover().mouserhoverbywebelement(newAssignment.assessmentNameTextBox);
            CustomAssert.assertEquals(newAssignment.pencil_Icon.isDisplayed(), true, "Validating pencil icon", "Pencil icon is displayed", "Pencil icon is not displayed");


            //6. Click on text Editor 'Click to enter assignment name...'
            //7. Enter the Assignment Name
            //8. Click on Text Area 'Click to enter the question for discussion...'
            //9. Enter the Question for discussion
            //10.Click on 'SAVE FOR LATER' button

            //1. A robo pop up with the label 'Saved discussion question successfully.' should be displayed
            newAssignment.assessmentNameTextBox.click();
            Thread.sleep(3000);
            driver.switchTo().activeElement().sendKeys(assessmentName);
            Thread.sleep(2000);
            //WebDriverUtil.clickOnElementUsingJavascript(newAssignment.assessmentDescriptionTextBox);
            newAssignment.assessmentDescriptionTextBox.click();
            Thread.sleep(2000);
            driver.switchTo().activeElement().sendKeys("Discussion Assignment Description");
            myQuestionBank.getSaveForLater().click();
            CustomAssert.assertEquals(newAssignment.notification_message.getText(), "Saved discussion question successfully.", "Validating robo popup", "Robo popup 'Saved discussion question successfully.' is displayed", "Robo popup 'Saved discussion question successfully.' is not displayed");


            //11. Navigate to 'My Assignments' Tab
            //Saved discussion question should be available
            new LoginUsingLTI().ltiLogin(dataIndex);//login as student1
            new Navigator().NavigateTo("My Assignments");
            CustomAssert.assertEquals(questionPage.assignmentName.getText(), assessmentName, "Validating Saved discussion assignment question", "Saved discussion assignment question is available", "Saved discussion assignment question is not available");

            //Row No - 19 : 12. Click on discussion assignment description text
            //It should show the preview page by the discussion assignment name with the assignment name & discussion question
            questionPage.discussionAssignmentDescriptionIcon.click();
            if (driver.findElements(By.cssSelector("span[title = '" + assessmentName + "']")).size() == 0) {
                CustomAssert.fail("Validating preview page by the discussion assignment name", "Assignment name in discussion assignment preview page is not displayed");
            }


            //CustomAssert.assertEquals(currentAssignments.getCurrentAssignmentTitle().getText().trim(),"Discussion Assignment Name","Validating preview page by the discussion assignment name","The preview page by the discussion assignment name is displayed","The preview page by the discussion assignment name is not displayed");
            CustomAssert.assertEquals(currentAssignments.preview_discussionAssignmentName.getText().trim(), "Discussion Assignment Name\n" +
                    "Discussion Assignment Description", "Validating assignment name in discussion assignment preview page", "Assignment name in discussion assignment preview page is displayed", "Assignment name in discussion assignment preview page is not displayed");
            CustomAssert.assertEquals(currentAssignments.preview_discussionAssignmentDescription.getText().trim(), "Discussion Assignment Description", "Validating assignment name description in discussion assignment preview page", "Assignment name description in discussion assignment preview page is displayed", "Assignment name description in discussion assignment preview page is not displayed");


            //13. Click on 'Assign This' button
            //Assign pop up should be displayed
            new Navigator().NavigateTo("My Assignments");
            questionBank.getAssignThisButtton().click();
            CustomAssert.assertEquals(questionBank.getPopupHeader().getText().trim(), "Discussion Assignment Name", "Validating assign popup", "Assign pop up is displayed", "Assign pop up is not displayed");


            //2 . Gradable Check box should be checked by default
            if (questionBank.gradableCheckBoxList.size() == 0) {
                CustomAssert.fail("Validating gradable check box", "Gradable check box is checked by default");
            }


            //17. Assign the assignment with required details by clicking on "assign" Button.
            //1. Assignment should be assigned and it should land on Class assignment page,
            new Navigator().NavigateTo("My Assignments");
            questionBank.getAssignThisButtton().click();
            new Assignment().assignThisFormFillOtherDetails(dataIndex);
            CustomAssert.assertEquals(currentAssignments.getCurrentAssignmentTitle().getText().trim(), "Class Assignments", "Validating 'Class Assignments' Tab", "Assignment is landed to 'Class Assignments'", "Assignment is not landed to 'Class Assignments'");


            //2. System should Display Gradable tag.
            if (driver.findElements(By.cssSelector("span[class='ls-assignment-graded-quiz-icon assessment-block-icon']")).size() == 0) {
                CustomAssert.fail("Validating Gradable icon", "Gradable icon is displayed");
            }


            //3. System should display following options for assignment "View Student Responses", "Update Assignment" and "Un-assign assignment" options only.
            currentAssignments.getViewGrade_link().click();//View Student Responses
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText(), "Assignment Responses", "Validating 'Assignment Responses' Page", "On Clicking on 'View Student Responses' it is landed over to View student responses page", "On Clicking on 'View Student Responses' it is not landed over to View student responses page");

            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getUpdateAssignment_button().click();//Update Assignment
            CustomAssert.assertEquals(currentAssignments.extendDueDatePopUp.isDisplayed(), true, "Validating 'Update Assignment' popup", "'Update Assignment' popup is displayed", "'Update Assignment' popup is not displayed");


            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();//Un-assign assignment
            CustomAssert.assertEquals(currentAssignments.getUnAssignAssignment_popup().isDisplayed(), true, "Validating 'un-assignment' popup", "'Un-assign assignment' popup is displayed", "Un-assign assignment' popup is not displayed");


            /*"18. Navigate to My Assignments page and Verify followings:
            1). Verify the ""Delete This"" option
            2). Verify ""Edit This"" option."*/
            //1). It should not show "Delete This" option.
            boolean isEditThisButtonAvailable = false;
            try {
                currentAssignments.editThisButton.click();
                isEditThisButtonAvailable = true;
            } catch (Exception e) {

            }
            if (isEditThisButtonAvailable == true) {
                CustomAssert.fail("Validating 'Edit This' button", "'Edit This' button is not displayed");
            }


            //2). It should not show "Delete This" option.
            boolean isDeleteThisButtonAvailable = false;
            try {
                currentAssignments.deleteThisButton.click();
                isDeleteThisButtonAvailable = true;
            } catch (Exception e) {

            }
            if (isDeleteThisButtonAvailable == true) {
                CustomAssert.fail("Validating 'Delete This' button", "'Delete This' button is not displayed");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'validateSaveForLaterFeature' in the class 'DiscussionBasedAssignment'", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void validateDiscussionAssignmentEditThisFeature() {
        try {
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate Discussion Assignment Edit This Feature", "Validate Edit This Feature once the discussion assignment is created", "info");
            String dataIndex = "30";
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionPage questionPage = PageFactory.initElements(driver, QuestionPage.class);
            String discussionAssignment = ReadTestData.readDataByTagName("", "discussionAssignment", dataIndex);

            new LoginUsingLTI().ltiLogin(dataIndex);//login as student1
            ReportUtil.log("Instructor Login", "Instructor logged in successfully", "Pass");
            new Assignment().createDiscussionAssignment(Integer.parseInt(dataIndex));

            /*"1. Navigate to 'My Assignments' Tab
            2. Click on 'Edit This' button"*/
            //3. Click on 'My Assignments' Tab and Click on 'Edit This button' again
            //Expected - 1. A robo pop up with the text 'There is an existing custom assignment tab already open. You cannot have 2 custom assignment tabs open at the same time. Please close the existing tab and try again.' should be displayed

            new Navigator().NavigateTo("My Assignments");
            currentAssignments.editThisButton.click();
            driver.findElement(By.cssSelector("span[title = 'My Assignments']")).click();
            currentAssignments.editThisButton.click();
            CustomAssert.assertEquals(newAssignment.notification_message.getText().trim(), "There is an existing custom assignment tab already open. You cannot have 2 custom assignment tabs open at the same time. Please close the existing tab and try again.", "Validating robo pop up", "Robo pop up is displayed", "Robo pop up is not displayed");


            /*"4. Edit the discussion assignment assignment name, discussion text for question
            5. Click on 'SAVE FOR LATER' button"*/
            //Expected - The editted Text should be appeared in the assignment name in the '8My Assignments' Tab
            new Assignment().editDiscussionAssignment(Integer.parseInt(dataIndex));
            new Navigator().NavigateTo("My Assignments");
            CustomAssert.assertEquals(questionPage.assignmentName.getText(), discussionAssignment + discussionAssignment, "Validating editted discussion assignment question", "Editted discussion assignment question is available", "Editted discussion assignment question is not available");

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'validateDiscussionAssignmentEditThisFeature' in the class 'DiscussionBasedAssignment'", e);
        }
    }


    @Test(priority = 3, enabled = true)
    public void validateDiscussionAssignmentDeleteThisFeature() {
        try {
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate Discussion Assignment Delete This Feature", "Validate Delete This Feature once the discussion assignment is created", "info");
            String dataIndex = "34";
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

            String discussionAssignment = ReadTestData.readDataByTagName("", "discussionAssignment", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);//login as student1
            ReportUtil.log("Instructor Login", "Instructor logged in successfully", "Pass");
            new Assignment().createDiscussionAssignment(Integer.parseInt(dataIndex));

            /*18. Navigate to 'My Assignments' Tab
            19. Click on 'Delete This' icon*/
            //Expected - The robo pop up "Are you sure you want to delete this assignment?    Yes      No" should be displayed
            new Navigator().NavigateTo("My Assignments");
            currentAssignments.deleteThisButton.click();
            CustomAssert.assertEquals(myQuestionBank.notification.getText().trim(), "Are you sure you want to delete this assignment?   Yes      No", "Validating robo popup", "Robo popup 'Are you sure you want to delete this assignment?   Yes      No' is displayed", "Robo popup 'Are you sure you want to delete this assignment?   Yes      No' is not displayed");


            //20. Click on 'No' link
            //The Discussion Assignment should not be deleted
            currentAssignments.deleteThisButton.click();
            Thread.sleep(1000);
            //((JavascriptExecutor)driver).executeScript("arguments[0].click();", myQuestionBank.noLink);
            WebDriverUtil.clickOnElementUsingJavascript(myQuestionBank.noLink);

            Thread.sleep(1000);
            if (driver.findElements(By.cssSelector("div[title = '" + discussionAssignment + "']")).size() == 0) {
                CustomAssert.fail("Validating the Discussion Assignment", "The Discussion Assignment is deleted");
            }

            /*21. Click on 'Delete This' icon
            22. Click on 'Yes' link*/
            //Expected - The Discussion Assignment should be deleted
            currentAssignments.deleteThisButton.click();
            Thread.sleep(1000);
            WebDriverUtil.clickOnElementUsingJavascript(myQuestionBank.yesLink);

            Thread.sleep(1000);
            if (driver.findElements(By.cssSelector("div[title = '" + discussionAssignment + "']")).size() != 0) {
                CustomAssert.fail("Validating the Discussion Assignment", "The Discussion Assignment is not deleted");
            }
        } catch (Exception e) {
            Assert.fail("Exception in the test method 'validateDiscussionAssignmentDeleteThisFeature' in the class 'DiscussionBasedAssignment'", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void validateDiscussionAssignmentAssignNowFeature() {
        try {
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate Discussion Assignment Delete This Feature", "Validate Delete This Feature once the discussion assignment is created", "info");
            String dataIndex = "41";
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

            String discussionAssignment = ReadTestData.readDataByTagName("", "discussionAssignment", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);//login as student1
            ReportUtil.log("Instructor Login", "Instructor logged in successfully", "Pass");
            /*new Assignment().ksahj(Integer.parseInt(dataIndex));
            //Start Scripting from here
            new CreateCustomAssignment().assignByCreatingDiscussionAssignment(dataIndex);*/

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'validateDiscussionAssignmentAssignNowFeature' in the class 'DiscussionBasedAssignment'", e);
        }
    }

    //SAVE FOR LATER - Impacted Areas after a discussion assignment is assigned (INSTRUCTOR side)
    @Test(priority = 5, enabled = true)
    public void validateImpactedAreasAfterDAAssignedFromInstructorSide() {
        try {
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate Impacted Areas after DA assigned from instructor side", "Check the Impacted Areas from all modules after DA assigned from instructor side", "info");
            String dataIndex = "54";
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            String discussionAssignment = ReadTestData.readDataByTagName("", "discussionAssignment", dataIndex);
            String discussionDescription = ReadTestData.readDataByTagName("", "description", dataIndex);
            String additionalNote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);


            new LoginUsingLTI().ltiLogin(dataIndex);//login as Instructor
            ReportUtil.log("Instructor Login", "Instructor logged in successfully", "Pass");
            new CreateCustomAssignment().assignByCreatingDiscussionAssignment(dataIndex);


            /*Instructor should be navigated to DASHBOARD
            "2. System should display Discussion assignment entry in Recent Activity tile. Entry should contain following Details.
            1). Assignment name should be Header label for the Assignment entry.
            2) .Description label below the header label should be ""Learning Activity""  with approx timing from when assigned."*/
            new Navigator().NavigateTo("Dashboard");
            CustomAssert.assertEquals(dashboard.recentActivitiesText.getText(), "DiscussionAssignment_54\n" +
                    "LearningActivity a second ago", "Validating Assignment name and Description label in Recent Activities tile", "Assignment name and Description label in Recent Activities tile is displayed", "Assignment name and Description label in Recent Activities tile is not displayed");


            //2. System should display the assignment count in assignment card count accordingly.
            /*Expected - "3.  Verify that assignment count card number is getting updated accordingly in Assignments tile.
            (For Scheduled - Scheduled count should increase and Available - Available For Student cont should get increase)"""*/
            CustomAssert.assertEquals(dashboard.availableForStudentsCount.getText(), "1\n" +
                    "Available for Students", "Verifying the assignment count card number", "Assignment count card number is getting updated accordingly in Assignments tile.", "Assignment count card number is not getting updated accordingly in Assignments tile.");

            //3. System should display the entry of Discussion assignment in Class Activity tile.
            /*Expected - "4. Verify that in Class Activity tile the Discussion assignment entry is there. Entry should contain following Details.
            i. ""Assignment name"" should be there for the Assignment entry.
                    ii. ""Due Date:"" label should come with due date what we have given while assigning  with following format ""Mon dt, year"""*/
            CustomAssert.assertEquals(dashboard.assessment.getText().trim(), discussionAssignment, "Validating 'Assignment name' in the Class Activity tile", "'Assignment name' in the Class Activity tile is displayed", "'Assignment name' in the Class Activity tile is not displayed");


            //4. Navigate to Lesson page and click on Assignments tab.
                    /*i. ""Assignment name"" should be there for the Assignment entry.
                    ii. ""Due Date:"" label should come with due date what we have given while assignment in red color with following format ""Mon dt, year, HH:SS AM/PM""
                   iii. ""Class Status: "" label should be there with the actual status in Green Color eg. ""Available for Student"""*/

            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().lessonOpen(4, 0);//open 1st chapter
            new WebDriverUtil().clickOnElementUsingJavascript(driver.findElement(By.cssSelector("span[title = 'Assignments']")));
            if (driver.findElements(By.linkText(discussionAssignment)).size() == 0) {
                CustomAssert.fail("Validating 'Assignment name' entry in the assignment tab", "'Assignment name' entry in the assignment tab is not displayed");
            }
            CustomAssert.assertEquals(currentAssignments.classStatus.getText(), "Available for Students", "Validating class status in Assignments Tab", "Class Status in Assignments Tab is displayed as 'Available for Students'", "Class Status in Assignments Tab is not displayed as 'Available for Students'");


            //6. Navigate to gradebook page from main Navigator.
            /*Expected - "6. System should display Discussion Question Assignment entry in Gradebook page.
            i. Discussion ""Assignment name"" should come Assignment entry column header.
            ii. Status for that assignment should be ""Grades Not Released"" by default."*/
            new Navigator().NavigateTo("Gradebook");


            //7. Verify that Discussion assignment entry is coming on Course stream page.
            /*Expected - """7. System should show entry of Discussion question assignment in Course stream page. Entry should contain following Details.
            i. """"Assignment name"""" should be there for the Assignment entry. Below this
            ii. """"Discussion Question Text"""" should come. continued with
            iii. calender icon and """"Due Date:"""" label should come with due date what we have given while assignment with following format """"Mon dt, year, HH:MM AM/PM"""""""*/
            //dueDate is not scripted
            new Navigator().NavigateTo("Course Stream");
            CustomAssert.assertEquals(courseStreamPage.discussionAssignmentName.getText().trim(), discussionAssignment, "Validating discussion Assignment name in course stream page", "Discussion Assignment name in course stream page is displayed", "Discussion Assignment name in course stream page is not displayed");
            CustomAssert.assertEquals(courseStreamPage.discussionAssignmentDescription.getText().trim(), "D1 - " + discussionDescription + "", "Validating discussion Assignment description in course stream page", "Discussion Assignment description in course stream page is displayed", "Discussion Assignment description in course stream page is not displayed");
            if (!courseStreamPage.calenderIcon.isDisplayed()) {
                CustomAssert.fail("Validating calender icon", "Calender Icon is displayed in Course Stream Page");
            }


            //8. Navigate to Class assignment page and Verify that Gradable Discussion Question assignment Entry is coming
            /*Expected - "8. System should show the Discussion Question Assignment in Class assignment.
            Entry should contain following Details.
            i. ""Assignment name"" should be there for the Assignment entry with Gradable tag if any. Below this
            ii. ""Discussion question Text"" should come. Below this
            iii. Label ""Description:"" should come, continued with Description provided while assigning the assignment.
            iv. It should show Actions available ""View Student Responses"" | ""Update Assignment"" | ""Un-assign Assignment"""*/

            new Navigator().NavigateTo("Class Assignments");
            CustomAssert.assertEquals(currentAssignments.getDWassignmentName().getText().trim(), discussionAssignment, "Validating \"\"Assignment name\"\"", "Assignment is displayed in Class Assignments Page", "Assignment is not displayed in Class Assignments Page");
            CustomAssert.assertEquals(currentAssignments.gradable_icon.isDisplayed(), true, "Validating Gradable icon", "Gradable icon is displayed along with the assignment name in Class Assignments page", "Gradable icon is displayed along with the assignment name in Class Assignments page");
            String actualDADescription = driver.findElement(By.cssSelector("span[contentdescription = '" + discussionDescription + "']")).getText().trim();
            CustomAssert.assertEquals(actualDADescription, "D1 - " + discussionDescription + "", "Validating discussion assignment description", "Discussion assignment description is displayed in Class Assignments Page", "Discussion assignment description is not displayed in Class Assignments Page");
            CustomAssert.assertEquals(currentAssignments.discussionAssignmentDescription.getText().trim(), "Description: " + additionalNote + "", "Validating additional note", "Additional note is displayed in Class Assignments Page", "Additional note is not displayed in Class Assignments Page");


            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();//Un-assign assignment
            CustomAssert.assertEquals(currentAssignments.getUnAssignAssignment_popup().isDisplayed(), true, "Validating 'un-assignment' popup", "'Un-assign assignment' popup is displayed", "Un-assign assignment' popup is not displayed");


            //9. Click on 'View Student Response' button
            //9. Up on Clicking 'View Student Responses' button, Assignment Responses Page should be displayed
            //currentAssignments.getViewGrade_link().click();//View Student Responses
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText(), "Assignment Responses", "Validating 'Assignment Responses' Page", "On Clicking on 'View Student Responses' it is landed over to View student responses page", "On Clicking on 'View Student Responses' it is not landed over to View student responses page");


            //10. Click on 'Update Assignment' button
            //10. Up on Clicking 'Update Assignment' button, Update Assignment pop window should be displayed
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getUpdateAssignment_button().click();//Update Assignment
            CustomAssert.assertEquals(currentAssignments.extendDueDatePopUp.isDisplayed(), true, "Validating 'Update Assignment' popup", "'Update Assignment' popup is displayed", "'Update Assignment' popup is not displayed");


            //11. Click on 'Un-assign Assignment' button
            //11. Up on Clicking 'Un-assign Assignment' button, robo pop up with the text "You are un-assigning the assignment for ALL students. Are you sure?" should be displayed
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();//Un-assign assignment
            CustomAssert.assertEquals(currentAssignments.getUnAssignAssignment_popup().isDisplayed(), true, "Validating 'un-assignment' popup", "'Un-assign assignment' popup is displayed", "Un-assign assignment' popup is not displayed");
            CustomAssert.assertEquals(currentAssignments.getUnAssignAssignment_popup().getText(), "You are un-assigning the assignment for ALL students. Are you sure?", "Validating 'un-assignment' popup with the text", "'Un-assign assignment' popup with the text is displayed", "Un-assign assignment' popup with the text is not displayed");


            //13. On Class assignment page Verify the Class Status field
            //System should show Class status as "Available for Students" if all students have not submitted.
            CustomAssert.assertEquals(currentAssignments.class_assignmentStatus.get(0).getText().trim(), "Available for Students", "Validating class status", "Class status is displayed as 'Available for Students' in Class Assignments page", "Class status is not displayed as 'Available for Students' in Class Assignments page");


            //12. Click on 'Yes' button in the pop up
            //12. Assignment should be unassigned
            //currentAssignments.getUnAssignButtonOfVersionAssignment().click();//Un-assign assignment
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getUnAssignButtonOfVersionAssignment());//Un-assign assignment
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getYesOnUnAssignPopUp());
            CustomAssert.assertEquals(currentAssignments.noAssignmentExit.getText().trim(), "No Assignment exists.", "Validating Un assign functionality", "Assignment is unassigned", "Assignment is not unassigned");

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'validateImpactedAreasAfterDAAssignedFromInstructorSide' in the class 'DiscussionBasedAssignment'", e);
        }
    }


    @Test(priority = 6, enabled = true)
    public void validateImpactedAreasAfterDAAssignedFromStudentSide() {
        try {
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate Impacted Areas after DA assigned from Student side", "Check the Impacted Areas from all modules after DA assigned from Student side", "info");
            String dataIndex = "69";
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            String discussionAssignment = ReadTestData.readDataByTagName("", "discussionAssignment", dataIndex);
            String discussionDescription = ReadTestData.readDataByTagName("", "description", dataIndex);
            String additionalNote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);


            new LoginUsingLTI().ltiLogin(dataIndex);//login as Instructor
            ReportUtil.log("Instructor Login", "Instructor logged in successfully", "Pass");
            new CreateCustomAssignment().assignByCreatingDiscussionAssignment(dataIndex);

            //"Step 1: Login with the student to whom instructor has assigned  Discussion assignment
            /*Row No - 69 - "Verify the instructor assigned Discussion assignment getting displayed in the UPCOMING tile on dashboard.
            Assignment name should be seen."*/
            new LoginUsingLTI().ltiLogin("70");//login as a student
            Thread.sleep(2000);
            //CustomAssert.assertEquals(dashboard.upcoming_assignment.getText().trim(),discussionAssignment,"Validating the assignment name in Upcoming Tile","The assignment name in the Upcoming tile in discussion tile in dash board is displayed","The assignment name in the Upcoming tile in discussion tile in dash board is not displayed");

            //System should display the same date as given while assigning the assignment
            //due date




            /*"Verify the instructor assigned Discussion assignment getting displayed in the course stream tile of dashboard
                - Assignment name should be seen."*/
            CustomAssert.assertEquals(dashboard.assessment.getText().trim(), discussionAssignment, "Validating 'Assignment name' in the Course Stream tile", "'Assignment name' in the Course Stream tile is displayed", "'Assignment name' in the Course Stream tile is not displayed");


            /* ASSIGNMENTS PAGE*/
            //Step 2: Navigate to  Assignments page.
            /*1. Verify the instructor assigned Discussion assignment getting displayed in the student dashboard
            2. System should display the same Due date as given while assigning by the instructor with valid indicators
            3. By default "Your Status" field should be "Not Started"*/

            new Navigator().NavigateTo("Assignments");
            CustomAssert.assertEquals(courseStreamPage.discussionAssignmentName.getText().trim(), discussionAssignment, "Validating \"\"Assignment name\"\"", "Assignment is displayed in Assignments Page", "Assignment is not displayed in Assignments Page");
            CustomAssert.assertEquals(currentAssignments.gradable_icon.isDisplayed(), true, "Validating Gradable icon", "Gradable icon is displayed along with the assignment name in Class Assignments page", "Gradable icon is displayed along with the assignment name in Class Assignments page");
            String actualDADescription = currentAssignments.getLessonAssignment().getText().trim();
            CustomAssert.assertEquals(actualDADescription, "D1 - " + discussionDescription + "", "Validating discussion assignment description", "Discussion assignment description is displayed in Assignments Page", "Discussion assignment description is not displayed in Assignments Page");
            CustomAssert.assertEquals(currentAssignments.discussionAssignmentDescription.getText().trim(), "Description: " + additionalNote + "", "Validating additional note", "Additional note is displayed in Class Assignments Page", "Additional note is not displayed in Class Assignments Page");
            CustomAssert.assertEquals(assignments.status_inProgress.getText().trim(), "Not Started", "Validating 'Your Status:' Value", "'Your Status:' Value is displayed as 'Not Started' in Assignments Page", "'Your Status:' Value is not displayed as 'Not Started' in Assignments Page");


            //e-TextBook
            //Row No - 77 : Step 3: Navigate to Any of the Lesson Page(Eg. Lesson 5.1)
            //1. System should open the Assignments tab.
            //2.Assignment page should display the Discussion assignemnt assigned
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().lessonOpen(4, 0);//open 1st chapter
            new WebDriverUtil().clickOnElementUsingJavascript(driver.findElement(By.cssSelector("span[title = 'Assignments']")));
            if (driver.findElements(By.linkText(discussionAssignment)).size() == 0) {
                CustomAssert.fail("Validating 'Assignment name' entry in the assignment tab", "'Assignment name' entry in the assignment tab is not displayed");
            }


            //3. Assignment name should be displayed with Gradable lable
            CustomAssert.assertEquals(currentAssignments.iconGradable.isDisplayed(), true, "Validating Gradable icon", "Gradable icon is displayed along with the assignment name in Class Assignments page", "Gradable icon is displayed along with the assignment name in Class Assignments page");

            //Status should be shown as Not started if user has not submited and submited should be displayed if user submits the assignment
            CustomAssert.assertEquals(currentAssignments.classStatus.getText(), "Not Started", "Validating class status in Assignments Tab", "Class Status in Assignments Tab is displayed as 'Available for Students'", "Class Status in Assignments Tab is not displayed as 'Available for Students'");

           /* "System should display the same date as given while assigning the assignment
            Extended due date should be displayed if instructor extends due date"*/
            //Due date


            //Course Stream Page
            //Step 5: Navigate to "Course stream" page.
            /*Expected - "Verify the Discussion assignment getting displayed in the displayed assignments list
                    -- Assignment name should be displayed.
                    -- Assignment question with ellipses should be seen."*/
            new Navigator().NavigateTo("Course Stream");
            CustomAssert.assertEquals(courseStreamPage.discussionAssignmentName.getText().trim(), discussionAssignment, "Validating discussion Assignment name in course stream page", "Discussion Assignment name in course stream page is displayed", "Discussion Assignment name in course stream page is not displayed");
            CustomAssert.assertEquals(courseStreamPage.discussionAssignmentDescription.getText().trim(), "D1 - " + discussionDescription + "", "Validating discussion Assignment description in course stream page", "Discussion Assignment description in course stream page is displayed", "Discussion Assignment description in course stream page is not displayed");
            if (!courseStreamPage.calenderIcon.isDisplayed()) {
                CustomAssert.fail("Validating calender icon", "Calender Icon is displayed in Course Stream Page");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'validateImpactedAreasAfterDAAssignedFromStudentSide' in the class 'DiscussionBasedAssignment'", e);
        }
    }


    @Test(priority = 7, enabled = true)
    public void validateStudentAbleToOpenDA() {
        try {
            String dataIndex = "89";
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate Discussion Assignment from Student Side", "Validate that the student can click DA from Student side", "info");
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);

            String discussionAssignment = ReadTestData.readDataByTagName("", "discussionAssignment", dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex);//login as Instructor
            ReportUtil.log("Instructor Login", "Instructor logged in successfully", "Pass");
            new CreateCustomAssignment().assignByCreatingDiscussionAssignment(dataIndex);

            /*"Step 1: Login with the student to whom instructor has assigned  Discussion assignment
            Step 2: Click on the Discussion assignment displayed in course stream tile of dashboard"*/
            //Expected - System should navigate to course stream page
            new LoginUsingLTI().ltiLogin("90");//login as a student
            Thread.sleep(1000);
            WebDriverUtil.clickOnElementUsingJavascript(dashboard.assessment);
            Thread.sleep(2000);
            if (!driver.getCurrentUrl().contains("/coursestream")) {
                CustomAssert.fail("Validating Course Stream tile of dashboard", "On Clicking Course Stream tile of dashboard, Course Stream page is not displayed");
            }


            //Step 3: Click on the Discussion assignment and verify the assignment getting opened
            //Expected - System should open the assignment in new window
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.assignmentLink);
            CustomAssert.assertEquals(currentAssignments.getCurrentAssignmentTitle().getText(), "Discussion", "Validating DA name in Course Stream Page", "After Clicking DA from Course Stream, new tab is displayed", "After Clicking DA from Course Stream, new tab is not displayed");


            /*"Verify the lesson tab is not displayed as support for author Discussion assignments
            Only the Assignemnt tab shold be displayed for non gradable and Assignemnt tab and About tab both should be displayed for gradable assignment
            -- Assignment name should be seen."*/
            if (!driver.findElement(By.cssSelector("span[title = 'Assignments']")).isDisplayed()) {
                CustomAssert.fail("Validating 'Assignments' tab", "'Assignments' tab' in e-textbook page is not displayed");
            }
            if (!driver.findElement(By.cssSelector("span[title = 'About']")).isDisplayed()) {
                CustomAssert.fail("Validating 'About' tab", "'About' tab' in e-textbook page is not displayed");
            }
            Thread.sleep(2000);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("span[title = 'Assignments']")));
            //driver.findElement(By.cssSelector("span[title = 'Assignments']")).click();
            if (driver.findElements(By.linkText(discussionAssignment)).size() == 0) {
                CustomAssert.fail("Validating assignment name in 'Assignments' tab", "assignment name in 'Assignments' tab is not displayed");
            }

            /*"Step 4: Navigate back to the Dashboard
            Step 5: Click on Discussion assignment displayed in upcoming tile of dashboard"*/
            //Expected - System should open the assignment in new window
            new Navigator().NavigateTo("Dashboard");
            Thread.sleep(2000);
            WebDriverUtil.waitTillVisibilityOfElement(dashboard.upcoming_assignment, 10);
            //dashboard.upcoming_assignment.click();
            WebDriverUtil.clickOnElementUsingJavascript(dashboard.upcoming_assignment);
            CustomAssert.assertEquals(currentAssignments.getCurrentAssignmentTitle().getText(), "Discussion", "Validating DA upcoming tile of dashboard", "After Clicking DA from upcoming tile of dashboard, new tab is displayed", "After Clicking DA from upcoming tile of dashboard, new tab is not displayed");


            //ASSIGNMENTS PAGE
            //Step 1: Navigate - Assignments Page
            //Expected - System should display the Assignment page for student
            new Navigator().NavigateTo("Assignments");
            Thread.sleep(2000);
            if (!driver.getCurrentUrl().contains("/secure/lsStudentDashBoard#/studentAssignment")) {
                CustomAssert.fail("Validating assignments page", "System is not displayed the Assignment page for student");
            }

            //Step 2: Click on the Discussion assignment and verify the assignment getting opened
            //System should open the assignment in new window
            WebDriverUtil.waitTillVisibilityOfElement(currentAssignments.getLessonAssignment(), 10);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getLessonAssignment());
            CustomAssert.assertEquals(currentAssignments.getCurrentAssignmentTitle().getText(), "Discussion", "Validating new tab when DA name in Course Stream Page", "New tab is displayed when DA name in Course Stream Page is clicked", "New tab is not displayed when DA name in Course Stream Page is clicked");


            //e-textBook Page
            //Step 1: Navigate to e-textbook page (Eg. Lesson 5.1)
            //EXpected - System should display the e-textbook page(Eg. Lesson 5.1)
            new Navigator().NavigateTo("e-Textbook");
            Thread.sleep(2000);
            if (!driver.getCurrentUrl().contains("/secure/lsStudentDashBoard#/eTextBook")) {
                CustomAssert.fail("Validating e-text book page", "System is not displayed the e-text book page for student");
            }


            //Step 2: Click on Assignments tab
            //System should display all the assignments for the chapter
            new TopicOpen().lessonOpen(4, 0);//open 1st chapter
            new WebDriverUtil().clickOnElementUsingJavascript(driver.findElement(By.cssSelector("span[title = 'Assignments']")));
            if (driver.findElements(By.linkText(discussionAssignment)).size() == 0) {
                CustomAssert.fail("Validating 'Assignment name' entry in the assignment tab", "'Assignment name' entry in the assignment tab is not displayed");
            }


            //Step 3: Select Discussion assignment and click on Open
            //Expected - "System should open the assignment in new window"
            WebDriverUtil.clickOnElementUsingJavascript(assignmentTab.rightArrow);
            WebDriverUtil.waitTillVisibilityOfElement(assignmentTab.open_button, 20);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentTab.open_button);
            CustomAssert.assertEquals(currentAssignments.getCurrentAssignmentTitle().getText(), "Discussion", "Validating assignment in new window", "assignment in new window is displayed", "assignment in new window is not displayed");


            //Row No - 99 5: Navigate back to E-text book and click on Assignments tab
            //System should display all the assignments for the chapter
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().lessonOpen(4, 0);//open 1st chapter
            new WebDriverUtil().clickOnElementUsingJavascript(driver.findElement(By.cssSelector("span[title = 'Assignments']")));
            if (driver.findElements(By.linkText(discussionAssignment)).size() == 0) {
                CustomAssert.fail("Validating 'Assignment name' entry in the assignment tab", "'Assignment name' entry in the assignment tab is not displayed");
            }

            //Row no - 100 : Step 6: Click on view all link and verify the student assignment getting displayed
            currentAssignments.viewAllLink.click();
            if (!driver.getCurrentUrl().contains("/secure/lsStudentDashBoard#/studentAssignment")) {
                CustomAssert.fail("Validating assignments page", "System is not displayed the Assignment page for student");
            }


            //Row No 101: Step 7: Click on the Discussion assignment
            //"System should open the assignment in new window
            currentAssignments.getLessonAssignment().click();
            CustomAssert.assertEquals(currentAssignments.getCurrentAssignmentTitle().getText(), "Discussion", "Validating new tab when DA name in Course Stream Page", "New tab is displayed when DA name in Course Stream Page is clicked", "New tab is not displayed when DA name in Course Stream Page is clicked");


            //COURSE STREAM PAGE

            //Row No - 103 : Step 1: Navigate to - Course stream page.
            //System should display course stream page for student
            new Navigator().NavigateTo("Course Stream");
            Thread.sleep(2000);
            if (!driver.getCurrentUrl().contains("/secure/lsStudentDashBoard#/coursestream")) {
                CustomAssert.fail("Validating course stream page for student", "Course stream page for student is not displayed");
            }

            //Row No - 104 : Step 2: Click on the Discussion assignment
            //"System should open the assignment in new window
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.assignmentLink);
            CustomAssert.assertEquals(currentAssignments.getCurrentAssignmentTitle().getText(), "Discussion", "Validating DA name in Course Stream Page", "After Clicking DA from Course Stream, new tab is displayed", "After Clicking DA from Course Stream, new tab is not displayed");
        } catch (Exception e) {
            Assert.fail("Exception in the test method 'validateStudentAbleToOpenDA' in the class 'DiscussionBasedAssignment'", e);
        }
    }


    @Test(priority = 8, enabled = true)
    public void validateStudentAbleToAddPerspectives() {
        try {
            String dataIndex = "105";
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("validate Adding Perspectives", "Validate that the student can add Perspective", "info");
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Discussion discussion = PageFactory.initElements(driver, Discussion.class);
            Perspective perspective = PageFactory.initElements(driver, Perspective.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);

            String discussionAssignment = ReadTestData.readDataByTagName("", "discussionAssignment", dataIndex);
            String perspectiveText = ReadTestData.readDataByTagName("", "perspectiveText", dataIndex);

            new LoginUsingLTI().ltiLogin(dataIndex);//login as Instructor
            ReportUtil.log("Instructor Login", "Instructor logged in successfully", "Pass");
            new CreateCustomAssignment().assignByCreatingDiscussionAssignment(dataIndex);

            /*Row No - 106 : "Step 1: Login with the student A to whom instructor has assigned  Discussion assignment
            Step 2: Click on main navigator"*/
            //Expected - System should display the all menu options
            new LoginUsingLTI().ltiLogin("106");//login as a student
            Thread.sleep(5000);
            dashboard.getMainNavigator().click();
            if (!dashboard.mainNavigatorMenu.isDisplayed()) {
                CustomAssert.fail("Validating all menu options from main navigator", "All menu options from main navigator are not displayed");
            }


            //Row No - 107 : Step 3: Click on menu option - Assignments
            //Expected 1 - System should display Assignments page for student
            new Navigator().NavigateTo("Assignments");
            Thread.sleep(2000);
            if (!driver.getCurrentUrl().contains("/studentAssignment")) {
                CustomAssert.fail("Validating assignments page", "The Assignment page for student is not displayed");
            }
            //Expected - 2 : Your Status label should have the value 'Not Started'
            CustomAssert.assertEquals(assignments.status_inProgress.getText().trim(), "Not Started", "Validating 'Your Status:' Value", "'Your Status:' Value is displayed as 'Not Started' in Assignments Page", "'Your Status:' Value is not displayed as 'Not Started' in Assignments Page");


            /*Row No - 109 : Step 4: Locate and click on the Discussion assignment
            Expected - System should open the assignment in new tab*/
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getLessonAssignment());
            CustomAssert.assertEquals(currentAssignments.getCurrentAssignmentTitle().getText(), "Discussion", "Validating new tab when DA name in Course Stream Page", "New tab is displayed when DA name in Course Stream Page is clicked", "New tab is not displayed when DA name in Course Stream Page is clicked");


            /*Row No - 110 : Step 5: Click on "Your perspective..." field
            System should remove the pre-populated(Place-Holder) text and field should be editable*/
            //Row No - 111 :Step 6: Enter the text in the field and click on post button
            //Expected - 1: "System should allow user to enter the text
            //Expected - 2 : System should display the posted perspective of student in the same window
            Thread.sleep(2000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(perspectiveText);
            WebDriverUtil.waitTillVisibilityOfElement(currentAssignments.getDwComment(), 10);
            CustomAssert.assertEquals(currentAssignments.getDwComment().getText().trim(), perspectiveText, "Validating Perspective Comment", "Perspective added by the student is displayed in the same window", "Perspective added by the student is not displayed in the same window");

            //Expected - 3 :System should add count of 1 for each perspective added to Discussion
            CustomAssert.assertEquals(perspective.commentPost_count.getText().trim(), "1", "Validating perspective count", "Perspective count is as per expected in discussion page", "Perspective count is not as per expected in discussion page");

            //Expected -4 : System should display the posted perspective of student in the dashboard
            new Navigator().NavigateTo("Dashboard");
            CustomAssert.assertEquals(dashboard.perspectiveText.getText().trim(), perspectiveText, "Validating perspective comment", "Perspective Comment added by the student is displayed in the dashboard", "Perspective Comment added by the student is not displayed in the dashboard");

            //Expected - 5 : In Assignments Page Your Status label should have the value 'Submitted'
            new Navigator().NavigateTo("Assignments");
            CustomAssert.assertEquals(assignments.status_submitted.getText().trim(), "Submitted", "Validating 'Your Status:' Submitted", "'Your Status:' Value is displayed as 'Submitted' in Assignments Page", "'Your Status:' Value is not displayed as 'Submitted' in Assignments Page");

            //Expected - 6 : System should display the posted perspective of student in the Course Stream Page
            new Navigator().NavigateTo("Course Stream");
            CustomAssert.assertEquals(courseStreamPage.perspectiveComment.getText().trim(), perspectiveText, "Validating perspective comment", "Perspective Comment added by the student is displayed in the Course Stream page", "Perspective Comment added by the student is not displayed in the Course Stream page");



            /*Row No - 121 : Step 7: Logout and login with Instructor
            Expected 1 - System should display the posted perspective of student in the instructor dashboard*/
            new LoginUsingLTI().ltiLogin(dataIndex);//login as Instructor
            CustomAssert.assertEquals(dashboard.perspectiveText.getText().trim(), perspectiveText, "Validating perspective comment", "Perspective Comment added by the student is displayed in the instructor dashboard", "Perspective Comment added by the student is not displayed in the instructor dashboard");

            //Expected - 2 : System should display the posted perspective of student in the instructor Course Stream Page
            new Navigator().NavigateTo("Course Stream");
            CustomAssert.assertEquals(courseStreamPage.perspectiveComment.getText().trim(), perspectiveText, "Validating perspective comment", "Perspective Comment added by the student is displayed in the Course Stream page", "Perspective Comment added by the student is not displayed in the Course Stream page");

            /*Row No - 123 : Step 8: Navigate to Current assignments page from main navigator
            Expected - System should display the current assignment page*/
            new Navigator().NavigateTo("Class Assignments");
            if (!driver.getCurrentUrl().contains("/assignment")) {
                CustomAssert.fail("Validating Current Assignments page", "Current Assignments page is not displayed");
            }


            /*Row No - 124 : Step 9: Click on View student response link of Discussion assignment
            "Expected - System should open the assignment in next tab with heading response + Assignment name*/
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            if (driver.findElements(By.cssSelector("span[title = 'Response - " + discussionAssignment + "']")).size() == 0) {
                CustomAssert.fail("Validating the assignment in next tab", "The assignment in next tab with heading response + Assignment name is not displayed");
            }

            /*Row No - 125 : Step 10: Select the student A and click on View response link(displayed on hovering the correct tick mark)
            "Expected 1 : - System should open the assignment in next tab with heading response + Assignment name*/
            WebDriverUtil.mouseHover(By.className("idb-question-manually-graded"));
            Thread.sleep(3000);
            new WebDriverUtil().clickOnElementUsingJavascript(currentAssignments.getViewResponseLink());
            if (driver.findElements(By.cssSelector("span[title = 'Response - " + discussionAssignment + "']")).size() == 0) {
                CustomAssert.fail("Validating the assignment in next tab", "The assignment in next tab with heading response + Assignment name is not displayed");
            }

            //Expected - 2 : System should display the selected user perspectives in the response page
            CustomAssert.assertEquals(currentAssignments.getDwComment().getText().trim(), perspectiveText, "Validating Perspective Comment", "Perspective added by the student is displayed in the same window", "Perspective added by the student is not displayed in the same window");

            /*Row No - 127 : Step 11: Select student comments and click on comments link
            "Expected - System should NOT allow instructor to enter comments if Due Date has been expired.*/
            currentAssignments.getPerformanceScoreBox().sendKeys("20");
            currentAssignments.getFeedBack_textBox().click();
            driver.switchTo().activeElement().sendKeys("Instructor Feedback");
            currentAssignments.getDwSave_button().click();
            CustomAssert.assertEquals(currentAssignments.getSaveMessage().getText().trim(), "Saved successfully.", "Validating instructor feedback & score", "Marks & feedback is saved successfully", "Marks & feedback is saved successfully");
        } catch (Exception e) {
            Assert.fail("Exception in the test method 'validateStudentAbleToAddPerspectives' in the class 'DiscussionBasedAssignment'", e);
        }
    }


    @Test(priority = 9, enabled = true)
    public void validateStudentAbleToViewPerspectives() {
        try {
            String dataIndex = "140";
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("validate Adding Perspectives", "Validate that the student can add Perspective", "info");
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);

            Discussion discussion = PageFactory.initElements(driver, Discussion.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            String discussionAssignment = ReadTestData.readDataByTagName("", "discussionAssignment", dataIndex);
            String perspectiveText = ReadTestData.readDataByTagName("", "perspectiveText", dataIndex);
            String feedbackText = ReadTestData.readDataByTagName("", "feedbackText", dataIndex);

            new LoginUsingLTI().ltiLogin(dataIndex);//login as Instructor
            ReportUtil.log("Instructor Login", "Instructor logged in successfully", "Pass");
            new CreateCustomAssignment().assignByCreatingDiscussionAssignment(dataIndex);

            new LoginUsingLTI().ltiLogin("141");//login as a student
            Thread.sleep(5000);
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getLessonAssignment());
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(perspectiveText);

            new LoginUsingLTI().ltiLogin(dataIndex);//login as Instructor
            new DiscussionWidget().provideFeedbackAndScoreToStudent(dataIndex);
            CustomAssert.assertEquals(currentAssignments.getSaveMessage().getText().trim(), "Saved successfully.", "Validating message with the text", "Message with the text 'Saved successfully.' is displayed", "Message with the text 'Saved successfully.' is not displayed");
            new Navigator().NavigateTo("Class Assignments");
            Thread.sleep(5000);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            assignmentResponsesPage.getReleaseGradeForAll().click();
            if (!assignmentResponsesPage.getReviewStatus().isDisplayed()) {
                CustomAssert.fail("Validating Class Status value", "The Class Status value is not displayed as 'Graded'");
            }


            /*Row No - 142: 6. Log in as a student
            Expected - The value for 'Your Status' label should contain the Score released by the instructor*/
            new LoginUsingLTI().ltiLogin("141");//login as a student
            Thread.sleep(5000);
            new Navigator().NavigateTo("Assignments");
            CustomAssert.assertEquals(assignments.getScore().getText().trim(), "Score (20/20)", "Validating Score released by the instructor", "The value for 'Your Status' label contains the Score released by the instructor", "The value for 'Your Status' label does not contain the Score released by the instructor");
            assignments.getScore().click();

            /*Row No - 146 : 10. Open the Discussion assignment
            Expected - System should open the assignment in new tab*/
            currentAssignments.getLessonAssignment().click();
            CustomAssert.assertEquals(currentAssignments.getCurrentAssignmentTitle().getText(), "Discussion", "Validating new tab when DA name in Course Stream Page", "New tab is displayed when DA name in Course Stream Page is clicked", "New tab is not displayed when DA name in Course Stream Page is clicked");

            /*Row No - 147 : 11. Verify the instructor review comments
            Expected 1- "System should display label as Instructor Feedback followed by the instructor name*/
            if (!currentAssignments.instructorFeedbackIcon.isDisplayed()) {
                CustomAssert.fail("Validating Feedback followed by the instructor name", "Instructor Feedback followed by the instructor name is displayed");
            }

            //Expected - 2 : "System should display instructor feedback in the above card
            CustomAssert.assertEquals(currentAssignments.teacher_Feedback.getText(), feedbackText, "Validating instructor feedback in the above card", "Instructor feedback in the above card is displayed", "Instructor feedback in the above card is not displayed");


            //Expected - 3 : "System should display About and Assignment tab
            if (!driver.findElement(By.cssSelector("span[title = 'Assignments']")).isDisplayed()) {
                CustomAssert.fail("Validating 'Assignments' tab", "'Assignments' tab' in e-textbook page is not displayed");
            }
            if (!driver.findElement(By.cssSelector("span[title = 'About']")).isDisplayed()) {
                CustomAssert.fail("Validating 'About' tab", "'About' tab' in e-textbook page is not displayed");
            }


            //Expected - 4 : About Tab should be selected by default
            if (!assignments.label_totalPoints.isDisplayed()) {
                CustomAssert.fail("Validating 'About' tab", "About Tab is not selected by default");
            }


        } catch (Exception e) {
            Assert.fail("Exception in the test method 'validateStudentAbleToViewPerspectives' in the class 'DiscussionBasedAssignment'", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void validateNonGradableDiscussionAssignmentFlow() {
        try {
            String dataIndex = "152";
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("validate Adding Perspectives", "Validate that the student can add Perspective", "info");
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Perspective perspective = PageFactory.initElements(driver, Perspective.class);

            Discussion discussion = PageFactory.initElements(driver, Discussion.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            String discussionAssignment = ReadTestData.readDataByTagName("", "discussionAssignment", dataIndex);
            String perspectiveText = ReadTestData.readDataByTagName("", "perspectiveText", dataIndex);
            String feedbackText = ReadTestData.readDataByTagName("", "feedbackText", dataIndex);

            new LoginUsingLTI().ltiLogin(dataIndex);//login as Instructor
            ReportUtil.log("Instructor Login", "Instructor logged in successfully", "Pass");
            new CreateCustomAssignment().assignByCreatingDiscussionAssignment(dataIndex);


            /*Row No - 152 : "1. Login as a student
            2. Navigate to Assignemnts module & click on assignment
            3. Enter the perspective and Post it."*/
            new LoginUsingLTI().ltiLogin("153");//login as a student
            Thread.sleep(5000);
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getLessonAssignment());
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(perspectiveText);
            //Expected - 1 : 1. Perspectives Count should be increased by 1
            if (!driver.findElement(By.cssSelector("a[aria-label = '1 Perspective']")).isDisplayed()) {
                CustomAssert.fail("Validating perspective count", "Perspective count is not as per expected in discussion page");
            }

            //Expected - 2 : 2. Added Perspective should be seen in the same window
            CustomAssert.assertEquals(currentAssignments.getDwComment().getText().trim(), perspectiveText, "Validating Perspective Comment", "Perspective added by the student is displayed in the same window", "Perspective added by the student is not displayed in the same window");

            /*Row No - 154: 3.a Navigate to 'Assignments' page
            Expected - 3. The value for Your Status label should be 'Submitted'*/
            new Navigator().NavigateTo("Assignments");
            CustomAssert.assertEquals(assignments.status_submitted.getText().trim(), "Submitted", "Validating 'Your Status:' Submitted", "'Your Status:' Value is displayed as 'Submitted' in Assignments Page", "'Your Status:' Value is not displayed as 'Submitted' in Assignments Page");


            /*Row No - 155: "4. Login as the instructor
            5. Navigate to Class Assignments Page"*/
            //Expected - 1. The value for Class Status label should be 'Review in Progress'
            new LoginUsingLTI().ltiLogin(dataIndex);//login as Instructor
            ReportUtil.log("Instructor Login", "Instructor logged in successfully", "Pass");
            new Navigator().NavigateTo("Class Assignments");
            CustomAssert.assertEquals(currentAssignments.status_reviewInProgress.getText().trim(), "Review in Progress", "Validating 'Class Status:' Review in Progress", "'Class Status:' Value is displayed as 'Review in Progress' in Assignments Page", "'Class Status:' Value is not displayed as 'Review in Progress' in Assignments Page");

            //Expected - 2 : 2. The value for Submitted tile should be increased by 1
            CustomAssert.assertEquals(currentAssignments.getSubmittedBoxCount().getText().trim(), "1", "Validating The value for Submitted tile", "The value for Submitted tile is increased by 1", "The value for Submitted tile is not increased by 1");


            //3. The value for Due date label should be same as it was entered while assigning the assignment
            //duedate


            /*Row No - 158 : 6. Click on 'View Student Responses' button
            Expected - 1. Assignment Responses Page should be opened*/
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//View Student Responses
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText(), "Assignment Responses", "Validating 'Assignment Responses' Page", "On Clicking on 'View Student Responses' it is landed over to View student responses page", "On Clicking on 'View Student Responses' it is not landed over to View student responses page");


            //Expected - 2 : 2. The value for Class Status label should be 'Review in Progress'
            CustomAssert.assertEquals(assignments.status_inProgress.getText().trim(), "Review in Progress", "Validating 'Class Status:' Review in Progress", "'Class Status:' Value is displayed as 'Review in Progress' in Assignments Page", "'Class Status:' Value is not displayed as 'Review in Progress' in Assignments Page");


            //Expected - 3 : 3. The value for Due date label should be same as it was entered while assigning the assignment
            //duedate


            //Expected - 4 : 4. The value for Submitted tile should be as per the previous page
            CustomAssert.assertEquals(currentAssignments.submitted_boxWithCount.get(1).getText().trim(), "1\n" + "Submitted", "Validating The value for Submitted tile", "The value for Submitted tile is increased by 1", "The value for Submitted tile is not increased by 1");


            /*Row No - 162 : 7. Mouse over on tick mark & Click on 'View Response' link
            1. A new tab with assignment name should be displayed*/
            //Expected - 1. A new tab with assignment name should be displayed
            WebDriverUtil.mouseHover(By.className("idb-question-manually-graded"));
            Thread.sleep(3000);
            new WebDriverUtil().clickOnElementUsingJavascript(currentAssignments.getViewResponseLink());
            if (driver.findElements(By.cssSelector("span[title = 'Response - " + discussionAssignment + "']")).size() == 0) {
                CustomAssert.fail("Validating the assignment in next tab", "The assignment in next tab with heading response + Assignment name is not displayed");
            }

            //2. The label 'Non gradable' should be displayed in the same page
            CustomAssert.assertEquals(currentAssignments.label_nonGradable.getText().trim(), "Non gradable", "Validating the label 'Non gradable'", "The label 'Non gradable' is displayed in the same page", "The label 'Non gradable' is not displayed in the same page");

            /*Row No - 164 : "8. Enter instructor feedback
            Click on Save button"*/
            //Expected - The message with the text 'Saved successfully. ' should be displayed
            currentAssignments.getFeedBack_textBox().click();
            driver.switchTo().activeElement().sendKeys(feedbackText);
            currentAssignments.getDwSave_button().click();
            CustomAssert.assertEquals(currentAssignments.getSaveMessage().getText().trim(), "Saved successfully.", "Validating instructor feedback & score", "Marks & feedback is saved successfully", "Marks & feedback is saved successfully");


            /*Row No - 165 : "9. Login as a student
            10. Navigate to Assignments Page
            Click on discussion assignment"*/
            //Expected - Student should be able to see the feedback provided by the instructor
            new LoginUsingLTI().ltiLogin("153");//login as a student
            Thread.sleep(5000);
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getLessonAssignment());
            CustomAssert.assertEquals(currentAssignments.teacher_Feedback.getText(), feedbackText, "Validating feedback provided by the instructor", "Student is able to see the feedback provided by the instructor", "Student is not able to see the feedback provided by the instructor");

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'validateNonGradableDiscussionAssignmentFlow' in the class 'DiscussionBasedAssignment'", e);
        }
    }
}
