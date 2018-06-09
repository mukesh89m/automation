package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.groups;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.group.Group;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by priyanka on 13-05-2016.
 */
public class GroupPage extends Driver {

    Group group;
    WebDriver driver;
    CurrentAssignments currentAssignments;
    CourseStreamPage courseStream;
    AssignmentResponsesPage assignmentResponsesPage;
    Dashboard dashBoard;
    QuestionBank questionBank;
    String actual = "";
    String expected = "";

    @BeforeMethod
    public void inItElement() {
        driver = Driver.getWebDriver();
        group = PageFactory.initElements(driver, Group.class);
        currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
        courseStream = PageFactory.initElements(driver, CourseStreamPage.class);
        assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
        dashBoard = PageFactory.initElements(driver, Dashboard.class);
        questionBank = PageFactory.initElements(driver, QuestionBank.class);

    }

    @Test(priority = 1, enabled = true)
    public void createGroupForClassSection() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");

            for (int i = 1; i <= 12; i++) {
                new LoginUsingLTI().ltiLogin("1_1", Integer.toString(i));
            }
            ReportUtil.log("student login", "12 students logged in successfully", "pass");

            for (int i = 0; i <1; i++) {
                new LoginUsingLTI().ltiLogin("1", Integer.toString(i));
            }
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Groups");
            ReportUtil.log("Navigate to group", "Instructor navigated to group page successfully", "pass");

            CustomAssert.assertEquals(group.groupHeader.getText(), "View and Edit Groups", "Verify group page header", "group page header is displaying as View and Edit Groups", "group page header is not displaying as View and Edit Groups");
            CustomAssert.assertEquals(group.createNewGroup.getText(), "Create new group", "Verify Create new group label", "Create new group label is available", "Create new group label is not available");
            CustomAssert.assertEquals(group.groupName.getText(), "Group Name: No Group Selected", "Verify No Group Selected label", "No Group Selected is available", "No Group Selected is not available");

            String groupName = new AddGroup().addGroup(1);
            ReportUtil.log("Creation of group", "Group created successfully", "pass");

            WebDriverUtil.mouseHover(group.group_Name);
            CustomAssert.assertEquals(driver.findElement(group.group_Name).getAttribute("title").trim(), groupName, "Verify group name in tooltip", "It is displaying full Group name as tooltip.", "It is not displaying full Group name as tooltip.");
            CustomAssert.assertEquals(group.studentName.getText().trim(), "family, givenname", "Verify student name", "all the students name displayed in the format “Last Name, First Name", "all the students name is not displayed in the format “Last Name, First Name");
            CustomAssert.assertEquals(group.studentCheckBox.get(0).isDisplayed(), true, "Verify student check box", "It should displayed CheckBox before name of every student", "It should not displayed CheckBox before name of every student");
            new MouseHover().mouserhoverbywebelement(group.studentName);
            CustomAssert.assertEquals(group.studentName.getAttribute("title").trim(), "family, givenname", "Verify student name in tool tip", "students name displayed in the format “Last Name, First Name in tool tip", "students name is not displayed in the format “Last Name, First Name in tool tip");

            Thread.sleep(2000);
            boolean scroll = false;
            List<WebElement> scrolls = group.tinyScroll;
            for (WebElement ele : scrolls) {
                if (ele.isDisplayed() == true) {
                    scroll = true;
                    break;
                }
            }
            CustomAssert.assertTrue(scroll, "Verify tiny scroll", "Tiny scroll is displayed", "tiny scroll is not displayed");
            group.createNewGroup.click(); //click on the new group link
            group.cancelGroup.click();//click on cancel button
            CustomAssert.assertEquals(group.createNewGroup.getText(), "Create new group", "Verify Create new group label", "Create new group label is available", "Create new group label is not available");

            group.groupBox.get(3).click();//click on created group
            group.studentCheckBox.get(0).click();//click on student check box

            CustomAssert.assertEquals(group.groupIcon.isDisplayed(), true, "Verify group icon", "group icon is displayed", "group icon is not displayed");
            CustomAssert.assertEquals(group.studentCount.getText(), "1", "Verify added student", "student count is increased", "student count is not increased");
            CustomAssert.assertEquals(group.dotIcon.isDisplayed(), true, "Verify dot icon", "Three dot icon is displayed", "Three dot icon is not displayed");
            group.dotIcon.click();//click on 3 dot icon on group card
            CustomAssert.assertEquals(group.editGroupName.getText(), "Edit Group Name", "Verify edit group link", "edit group link is displayed", "edit group link is not displayed");
            CustomAssert.assertEquals(group.deleteGroup.getText(), "Delete Group", "Verify delete group link", "delete group link is displayed", "delete group link is not displayed");

            //Case1:  Notification message "Group names must be at least 3 characters.".

            group.createNewGroup.click(); //click on the new group link
            WebDriverUtil.waitTillVisibilityOfElement(group.groupName_textBox, 50);
            group.groupName_textBox.sendKeys("gr");
            group.done_button.click(); //click on the done button
            String notificationMessage = group.notificationMessage.getText().trim();
            CustomAssert.assertEquals(notificationMessage, "Group names must be at least 3 characters.", "Verify for Notification Message", "Group names must be at least 3 characters.", "Group names must be at least 3 characters. message is not displaying");
            WebDriverUtil.clickOnElementUsingJavascript(group.closeIcon);//click on 'x' icon of notification message

            boolean text = false;
            if (group.notification_Message.size() == 0) {
                text = true;
                CustomAssert.assertEquals(text, true, "Verify notification message", "notification message is not displayed", "notification message is displayed");
            }

            //Case2:  Group_A is already created.
            group.groupBox.get(3).click();//click on created group
            Thread.sleep(5000);
            group.createNewGroup.click(); //click on the new group link
            WebDriverUtil.waitTillVisibilityOfElement(group.groupName_textBox, 50);
            group.groupName_textBox.sendKeys(groupName);
            group.done_button.click(); //click on the done button
            String notificationMessage2 = group.notificationMessage.getText().trim();
            CustomAssert.assertEquals(notificationMessage2, "" + groupName + " is not available. Please choose a different group name.", "Verify for Notification Message", " Group is not available. Please choose a different group name.", "Notification message is not displayed");

            //Case3:  Notification message "Group names must be at least 3 characters.".

            group.groupBox.get(3).click();//click on created group
            Thread.sleep(5000);
            group.createNewGroup.click(); //click on the new group link
            WebDriverUtil.waitTillVisibilityOfElement(group.groupName_textBox, 50);
            group.done_button.click(); //click on the done button
            String notificationMessage1 = group.notificationMessage.getText().trim();
            CustomAssert.assertEquals(notificationMessage1, "Group names must be at least 3 characters.", "Verify for Notification Message", "Group names must be at least 3 characters.", "Group names must be at least 3 characters. message is not displaying");

            //Case4: Added more than six student in group card.

            group.groupBox.get(3).click();//click on created group
            for (int i = 1; i <= 6; i++) {
                group.studentCheckBox.get(i).click();
                Thread.sleep(2000);
                WebDriverUtil.scrollIntoView(group.crossIconOnStudent.get(i), false);
                CustomAssert.assertTrue(group.crossIconOnStudent.get(i).isDisplayed(), "Verify cross icon of each student", "Cross icon is displayed for each student", "Cross icon is not displayed for each student");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case createGroupForClassSection in class GroupPage", e);
        }
  }


    @Test(priority = 2, enabled = true)
    public void sortStudentName() {
        try {

            ReportUtil.log("Description", "Test case validates students name as per ascending and descending order", "info");

            new LoginUsingLTI().ltiLogin("2_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");
            new LoginUsingLTI().ltiLogin("2_2");//log in as student
            ReportUtil.log("Student2 login", "Student2 logged in successfully", "pass");
            new LoginUsingLTI().ltiLogin("2_3");//log in as student
            ReportUtil.log("Student3 login", "Student3 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("2");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Groups");
            ReportUtil.log("Navigate to group", "Instructor navigated to group page in successfully", "pass");

            //Case1 : sort name in descending order

            group.name_Header.click();//click on ascending arrow of name
            List<String> student = new ArrayList<String>();

            List<String> student1 = new ArrayList<String>();
            student1.add("march, month");
            student1.add("jan, month");
            student1.add("feb, month");

            List<WebElement> studentName = group.student_Name;
            for (WebElement we : studentName) {
                student.add(we.getText());
            }

            if (!student.equals(student1)) {
                CustomAssert.fail("Verify student name","student name is not in ascending order");
            }

            //Case2 : sort name in ascending order

            group.name_Header.click();//click on ascending arrow of name
            List<String> student2 = new ArrayList<String>();

            List<String> student3 = new ArrayList<String>();

            student3.add("feb, month");
            student3.add("jan, month");
            student3.add("march, month");

            List<WebElement> studentName1 = group.student_Name;
            for (WebElement we : studentName1) {
                student2.add(we.getText());
            }

            if (!student2.equals(student3)) {
                CustomAssert.fail("Verify student name","student name is not in ascending order");
            }

            //Case3 : Edit group name

            String groupName = new AddGroup().addGroup(2);
            ReportUtil.log("Creation of group", "Group created successfully", "pass");

            group.dotIcon.click();//click on three dot icon
            group.editGroupName.click();//click on edit group name
            group.editTextBox.sendKeys("a");
            driver.findElement(By.xpath("/html/body")).click();//click on body

            WebDriverUtil.mouseHover(group.group_Name);
            CustomAssert.assertEquals(driver.findElement(group.group_Name).getAttribute("title").trim(), "a" + groupName, "Verify group name in tooltip", "It is displaying full Group name as tooltip.", "It is not displaying full Group name as tooltip.");
            CustomAssert.assertEquals(group.groupName.getText(), "Group Name: " + "a" + groupName + "", "Verify No Group Selected label", "No Group Selected is available", "No Group Selected is not available");

            //Case4 : Group: Test-1 is already created and Test-1 group is deleted.try to create a group  with same name

            String groupName1 = new AddGroup().addGroup(2);
            ReportUtil.log("Creation of group", "Group created successfully", "pass");

            Thread.sleep(3000);
            new AddGroup().deleteGroup(1);
            ReportUtil.log("Deletion of group", "Group deleted successfully", "pass");

            Thread.sleep(2000);

            group.createNewGroup.click(); //click on the new group link
            WebDriverUtil.waitTillVisibilityOfElement(group.groupName_textBox, 50);
            group.groupName_textBox.sendKeys(groupName1);
            group.done_button.click(); //click on the done button

            String notificationMessage = group.notificationMessage.getText().trim();
            CustomAssert.assertEquals(notificationMessage, "" + groupName1 + " is not available. Please choose a different group name.", "Verify for Notification Message", " Group is not available. Please choose a different group name.", "Notification message is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in test case sortStudentName in class GroupPage", e);
        }

    }

    @Test(priority = 3, enabled = true)
    public void assignAssignmentToGroup() {
        try {

            ReportUtil.log("Description", "Test case validates assign assignment to groups", "info");

            new Assignment().create(3);
            ReportUtil.log("Assignment creation", "Test case validates creation of assessment with true false question", "info");

            new LoginUsingLTI().ltiLogin("3_1");//log in as student
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");
            new LoginUsingLTI().ltiLogin("3_2");//log in as student
            ReportUtil.log("Student2 login", "Student2 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("3");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            String groupName = new AddGroup().addGroup(3);
            ReportUtil.log("Creation of group", "Group created successfully", "pass");

            new AddGroup().addStudentToParticularGroup(1, 0, 2);
            ReportUtil.log("Adding student to group", "2 students added to group successfully", "pass");

            new Assignment().assignAssignmentToGroup(3);
            ReportUtil.log("Assign assignment to group", "Assignment assign to group successfully", "pass");

            new Navigator().NavigateTo("Groups");
            ReportUtil.log("Navigate to group", "Instructor navigated to group page in successfully", "pass");

            Thread.sleep(1000);
            group.group_container.get(1).click();//click on group card
            group.dotIcon.click();//click on three dot icon
            group.editGroupName.click();//click on edit group name
            group.editTextBox.sendKeys("a");
            driver.findElement(By.xpath("/html/body")).click();//click on body
            String editedGroupName = "a" + groupName;

            new Navigator().NavigateTo("Class Assignments");
            ReportUtil.log("Navigate to Class Assignments", "Instructor navigated to Class Assignments in successfully", "pass");

            currentAssignments.getViewGrade_link().click();//click on Update Assignment at first index
            assignmentResponsesPage.extendDueDateLabel.click();//click on extend due date
            CustomAssert.assertEquals(currentAssignments.getExtendDueDateForField().get(0).getText(), editedGroupName, "Verify edited group name in assign to field", "Edited group name is displayed", "Edited group name is not displayed");
            currentAssignments.closeCard.click();//click on close icon on assign to field

            List<WebElement> ele = driver.findElements(By.className("maininput"));
            for (WebElement e : ele) {
                if (e.isDisplayed()) {
                    e.sendKeys(editedGroupName);
                    CustomAssert.assertEquals(currentAssignments.autoSuggestName.getText(), editedGroupName, "Verify edited group name in auto suggest list.", "Edited group name is displaying in the auto suggest.", "Edited group name is not displaying in the Auto suggest.");
                }
            }
            assignmentResponsesPage.extendDueDateCloseIcon.click();
            new AddGroup().addGroup(3);
            ReportUtil.log("Creation of group", "Group created successfully", "pass");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "Instructor navigated to Course Stream in successfully", "pass");

            String randomText = new RandomString().randomstring(3);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "3");
            new PostMessage().postMessageAndShareToGroup(randomText, shareWithInitialString, "3", "");

            new Navigator().NavigateTo("Groups");
            ReportUtil.log("Navigate to group", "Instructor navigated to group page in successfully", "pass");

            new AddGroup().addStudentToParticularGroup(1, 0, 2);
            ReportUtil.log("Adding student to group", "2 students added to group successfully", "pass");

            new LoginUsingLTI().ltiLogin("3_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "Instructor navigated to Course Stream in successfully", "pass");

            CustomAssert.assertEquals(courseStream.postText.getText(), randomText, "Verify instructor's post", "Instructor post is available", "instructor post is not available");
            CustomAssert.assertEquals(courseStream.postText.getText(), randomText, "Verify instructor's post", "Instructor post is available", "instructor post is not available");

            new LoginUsingLTI().ltiLogin("3_2");//log in as student2
            ReportUtil.log("Student2 login", "Student2 logged in successfully", "pass");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "Instructor navigated to Course Stream in successfully", "pass");

            CustomAssert.assertEquals(courseStream.postText.getText(), randomText, "Verify instructor's post", "Instructor post is available", "instructor post is not available");


        } catch (Exception e) {
            Assert.fail("Exception in test case assignAssignmentToGroup in class GroupPage", e);
        }

    }

    @Test(priority = 4, enabled = true)
    public void deleteGroupFunctionality() {
        try {

            ReportUtil.log("Description", "Test case validates delete group functionality", "info");

            new Assignment().create(4);
            ReportUtil.log("Assignment creation", "Test case validates creation of assessment with true false question", "info");

            new LoginUsingLTI().ltiLogin("4_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("4");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Groups");
            ReportUtil.log("Navigate to group", "Instructor navigated to group page in successfully", "pass");

            new AddGroup().addGroup(4);
            ReportUtil.log("Creation of group", "Group created successfully", "pass");

            new AddGroup().addStudentToParticularGroup(1, 0, 1);
            ReportUtil.log("Adding student to group", "1 students added to group successfully", "pass");

            new Assignment().assignAssignmentToGroup(4);
            ReportUtil.log("Assign assignment to group", "Assignment assign to group successfully", "pass");

            new AddGroup().selectGroup(1);//select group
            group.dotIconHighlightGroup.click();//click on three dot on group
            group.deleteGroup.click();//click on delete group
            CustomAssert.assertEquals(group.deleteMessage.getText(), "Deleting a group will not erase their prior work.\n" + "If you are sure you want to do this, type DELETE* in the space below, and click yes.", "Verify delete message", "Delete message is displayed", "Delete message is not displayed");
            CustomAssert.assertEquals(group.noCaseSensitive.getText(), "* Not case sensitive", "Verify no case sensitive message", "no case sensitive message is displayed", "Delete message is not displayed");
            CustomAssert.assertEquals(group.yesOnDelete.getText(), "Yes", "Verify yes link", "yes link is displayed", "yes link is not displayed");
            CustomAssert.assertEquals(group.noOnDeletePopUp.getText(), "No", "Verify no link", "no link is displayed", "no is not displayed");
            CustomAssert.assertEquals(group.closeIconOnDelete.isDisplayed(), true, "Verify close icon", "close icon is displayed", "close icon is not displayed");

            group.closeIconOnDelete.click();//click on 'x' icon
            boolean deletePopUp = false;
            if (group.deletePopUp.size() == 0) {
                deletePopUp = true;
                CustomAssert.assertEquals(deletePopUp, true, "Verify delete pop up", "delete pop up is not displayed", "delete pop up is displayed");

            }
            group.dotIconHighlightGroup.click();//click on three dot on group
            group.deleteGroup.click();//click on delete group
            group.delete.sendKeys("text");
            group.yesOnDelete.click();//click on yes
            CustomAssert.assertTrue(group.redColourWarning.isDisplayed(), "Verify red colour warning", "red colour warning is displayed", "red colour warning is not displayed");

            group.noOnDeletePopUp.click();//click on 'x' icon
            boolean deletePopUp1 = false;
            if (group.deletePopUp.size() == 0) {
                deletePopUp1 = true;
                CustomAssert.assertEquals(deletePopUp1, true, "Verify delete pop up", "delete pop up is not displayed", "delete pop up is displayed");

            }
            group.dotIconHighlightGroup.click();//click on three dot on group
            group.deleteGroup.click();//click on delete group
            group.yesOnDelete.click();//click on yes
            CustomAssert.assertTrue(group.redColourWarning.isDisplayed(), "Verify red colour warning", "red colour warning is displayed", "red colour warning is not displayed");
            group.noOnDeletePopUp.click();//click on 'x' icon

            new AddGroup().deleteGroup(1);
            ReportUtil.log("Deletion of group", "Instructor deleted group successfully", "pass");

            CustomAssert.assertEquals(group.notificationMessage.getText(), "Group deleted successfully.", "Verify delete notification message", "delete notification message is displayed", "delete notification message is not displayed");
            CustomAssert.assertEquals(group.closeIcon.isDisplayed(), true, "Verify 'x' icon", "'x' icon is displayed", "'x' icon is not displayed");

            new Navigator().NavigateTo("Class Assignments");
            ReportUtil.log("Navigate to Class Assignments", "Instructor navigated to Class Assignments in successfully", "pass");

            CustomAssert.assertTrue(currentAssignments.getAssessmentName().isDisplayed(), "Verify assignment", "Assignment is displayed", "Assignment is not displayed");

            new LoginUsingLTI().ltiLogin("4_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");
            ReportUtil.log("Navigate to Assignments", "Instructor navigated to Assignments in successfully", "pass");

            CustomAssert.assertTrue(currentAssignments.getAssessmentName().isDisplayed(), "Verify assignment", "Assignment is displayed", "Assignment is not displayed");
            String discussionText = new RandomString().randomstring(4);
            new CommentOnPost().commentOnPost(discussionText, 0);

        } catch (Exception e) {
            Assert.fail("Exception in test case deleteGroupFunctionality in class GroupPage", e);
        }

    }

    @Test(priority = 5, enabled = true)
    public void deleteStudentsFromGroup() {
        try {

            ReportUtil.log("Description", "Test case validates deletion of students from group", "info");

            new LoginUsingLTI().ltiLogin("5_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("5");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Groups");
            ReportUtil.log("Navigate to group", "Instructor navigated to group page in successfully", "pass");

            new AddGroup().addGroup(5);
            ReportUtil.log("Creation of group", "Group created successfully", "pass");

            new AddGroup().addStudentToParticularGroup(1, 0, 1);
            ReportUtil.log("Adding student to group", "1 students added to group successfully", "pass");

            group.crossIconOnStudent.get(0).click();//click on 'x' of student in group card
            CustomAssert.assertEquals(group.deleteMessage.getText(), "Removing a student from this group will not erase their prior work.\n" + "If you are sure you want to do this, type DELETE* in the space below, and click yes.", "Verify delete message", "Delete message is displayed", "Delete message is not displayed");
            CustomAssert.assertEquals(group.noCaseSensitive.getText(), "* Not case sensitive", "Verify no case sensitive message", "no case sensitive message is displayed", "Delete message is not displayed");
            CustomAssert.assertEquals(group.yesOnDelete.getText(), "Yes", "Verify yes link", "yes link is displayed", "yes link is not displayed");
            CustomAssert.assertEquals(group.noOnDeletePopUp.getText(), "No", "Verify no link", "no link is displayed", "no is not displayed");
            CustomAssert.assertEquals(group.closeIconOnDelete.isDisplayed(), true, "Verify close icon", "close icon is displayed", "close icon is not displayed");

            group.closeIconOnDelete.click();//click on 'x' icon
            boolean deletePopUp = false;
            if (group.deletePopUp.size() == 0) {
                deletePopUp = true;
                CustomAssert.assertEquals(deletePopUp, true, "Verify delete pop up", "delete pop up is not displayed", "delete pop up is displayed");

            }

            new AddGroup().deleteStudentFromGroup(1, "5_1");
            ReportUtil.log("Deletion of student", "Instructor deleted student1 from group successfully", "pass");

            CustomAssert.assertEquals(group.notificationMessage.getText(), "Student deleted successfully.", "Verify delete notification message", "delete notification message is displayed", "delete notification message is not displayed");
            CustomAssert.assertEquals(group.closeIcon.isDisplayed(), true, "Verify 'x' icon", "'x' icon is displayed", "'x' icon is not displayed");

            boolean deleteStudent = false;
            if (group.crossIconOnStudent.size() == 0) {
                deleteStudent = true;
                CustomAssert.assertEquals(deleteStudent, true, "Verify deleted student in group card", "deleted student is not displayed", "deleted student is displayed");

            }

            CustomAssert.assertEquals(group.studentCount.getText(), "0", "Verify deleted student", "student count is decreased", "student count is not decreased");

            boolean checkedCheckBox = true;
            if (group.studentCheckedBox.size() == 0) {
                checkedCheckBox = false;
                CustomAssert.assertEquals(checkedCheckBox, false, " Verify removed Student name check box in the Class List", "Removed Student name check box is unchecked in the Class List", "Removed Student name check box is not unchecked in the Class List");
            }

            //Removing students by Un checking the checkbox against student name.

            new AddGroup().addStudentToParticularGroup(1, 0, 1);
            ReportUtil.log("Adding student to group", "1 students added to group successfully", "pass");

            group.crossIconOnStudent.get(0).click();//un check student check box in class section list
            CustomAssert.assertEquals(group.deleteMessage.getText(), "Removing a student from this group will not erase their prior work.\n" + "If you are sure you want to do this, type DELETE* in the space below, and click yes.", "Verify delete message", "Delete message is displayed", "Delete message is not displayed");
            CustomAssert.assertEquals(group.noCaseSensitive.getText(), "* Not case sensitive", "Verify no case sensitive message", "no case sensitive message is displayed", "Delete message is not displayed");
            CustomAssert.assertEquals(group.yesOnDelete.getText(), "Yes", "Verify yes link", "yes link is displayed", "yes link is not displayed");
            CustomAssert.assertEquals(group.noOnDeletePopUp.getText(), "No", "Verify no link", "no link is displayed", "no is not displayed");
            CustomAssert.assertEquals(group.closeIconOnDelete.isDisplayed(), true, "Verify close icon", "close icon is displayed", "close icon is not displayed");

            group.closeIconOnDelete.click();//click on 'x' icon
            boolean deletePopUp1 = false;
            if (group.deletePopUp.size() == 0) {
                deletePopUp1 = true;
                CustomAssert.assertEquals(deletePopUp1, true, "Verify delete pop up", "delete pop up is not displayed", "delete pop up is displayed");
            }

            new AddGroup().deleteStudentFromClassList(1);
            ReportUtil.log("Deletion of student from class list", "Deleted students from class list successfully", "pass");

            CustomAssert.assertEquals(group.notificationMessage.getText(), "Student deleted successfully.", "Verify delete notification message", "delete notification message is displayed", "delete notification message is not displayed");
            CustomAssert.assertEquals(group.closeIcon.isDisplayed(), true, "Verify 'x' icon", "'x' icon is displayed", "'x' icon is not displayed");

            boolean deleteStudent1 = false;
            if (group.crossIconOnStudent.size() == 0) {
                deleteStudent1 = true;
                CustomAssert.assertEquals(deleteStudent1, true, "Verify deleted student in group card", "deleted student is not displayed", "deleted student is displayed");
            }
            CustomAssert.assertEquals(group.studentCount.getText(), "0", "Verify deleted student", "student count is decreased", "student count is not decreased");

            boolean checkedCheckBox1 = true;
            if (group.studentCheckedBox.size() == 0) {
                checkedCheckBox1 = false;
                CustomAssert.assertEquals(checkedCheckBox1, false, " Verify removed Student name check box in the Class List", "Removed Student name check box is unchecked in the Class List", "Removed Student name check box is not unchecked in the Class List");
            }

            CustomAssert.assertEquals(group.defaultTextOnGroupCard.getText(), "Select students from the list to the right to add them to this group", "Verify default text on group card", "Default text is displayed", "Default text is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in test case deleteStudentsFromGroup in class GroupPage", e);
        }

    }

    @Test(priority = 6, enabled = true)
     public void assignAssignmentToGroupAndRemoveStudent() {
        try {
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "6");
            ReportUtil.log("Description", "Test case validates assign assignment to group then remove student from group", "info");

            new LoginUsingLTI().ltiLogin("6_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");
            new LoginUsingLTI().ltiLogin("6_2");//log in as student2
            ReportUtil.log("Student2 login", "Student2 logged in successfully", "pass");
            new LoginUsingLTI().ltiLogin("6_3");//log in as student3
            ReportUtil.log("Student3 login", "Student3 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("6");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Groups");
            ReportUtil.log("Navigate to group", "Instructor navigated to group page in successfully", "pass");

            new AddGroup().addGroup(6);
            ReportUtil.log("Creation of group", "Group created successfully", "pass");

            new AddGroup().addStudentToParticularGroup(1, 0, 3);
            ReportUtil.log("Adding student to group", "3 students added to group successfully", "pass");

            new Assignment().assignAssignmentToGroup(6);
            ReportUtil.log("Assign assignment to group", "Assignment assign to group successfully", "pass");

            new Navigator().NavigateTo("Groups");
            ReportUtil.log("Navigate to group", "Instructor navigated to group page in successfully", "pass");

            new AddGroup().deleteStudentFromGroup(1, "6_1");
            ReportUtil.log("Deletion of student from group", "Instructor delete student1 from group successfully", "pass");

            new Navigator().NavigateTo("Class Assignments");
            ReportUtil.log("Navigate to Class Assignments", "Instructor navigated to Class Assignments in successfully", "pass");

            CustomAssert.assertEquals(currentAssignments.notStartedCountBox.getText(), "3", "Verify student count", "student count is same as before", "Student count is decreased");

            currentAssignments.viewStudentResponses.get(0).click();
            CustomAssert.assertEquals(assignmentResponsesPage.student_Name.size(), 3, "Verify deleted student", "Deleted student is available", "Deleted student is not available");
            Thread.sleep(3000);
            String randomText = new PostMessage().postMessageFromAssignmentResponsePage();

            new LoginUsingLTI().ltiLogin("6_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");
            CustomAssert.assertTrue(dashBoard.upComingAssignment.get(0).isDisplayed(), "Verify upcoming assignment", "Upcoming assignment is displayed", "Assignment is not available at upcoming section");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "student1 navigated to Course Stream in successfully", "pass");

            CustomAssert.assertEquals(courseStream.postText.getText().substring(1, 6), randomText, "Verify instructor's post", "Instructor post is available", "instructor post is not available");

            new LoginUsingLTI().ltiLogin("6");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "Instructor navigated to Course Stream in successfully", "pass");

            CustomAssert.assertEquals(courseStream.postText.getText().substring(1, 6), randomText, "Verify instructor's post", "Instructor post is available", "instructor post is not available");
            String randomText1 = new RandomString().randomstring(3);
            new PostMessage().postMessageAndShareToGroup(randomText1, shareWithInitialString, "6", "");

            new LoginUsingLTI().ltiLogin("6_2");//log in as student2
            ReportUtil.log("Student2 login", "Student2 logged in successfully", "pass");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "student2 navigated to Course Stream in successfully", "pass");
            CustomAssert.assertEquals(courseStream.postText.getText(), randomText1, "Verify instructor's post", "Instructor post is available", "instructor post is not available");

            new LoginUsingLTI().ltiLogin("6");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Groups");
            ReportUtil.log("Navigate to group", "Instructor navigated to group page in successfully", "pass");

            new AddGroup().deleteStudentFromGroup(1, "6_2");
            ReportUtil.log("Deletion of student from group", "Instructor delete student2 from group successfully", "pass");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "Instructor navigated to Course Stream in successfully", "pass");

            String randomText2 = new RandomString().randomstring(3);
            new PostMessage().postMessageAndShareToGroup(randomText2, shareWithInitialString, "6", "");

            new LoginUsingLTI().ltiLogin("6_2");//log in as student2
            ReportUtil.log("Student2 login", "Student2 logged in successfully", "pass");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "student2 navigated to Course Stream in successfully", "pass");
            if (courseStream.postText.getText().equals(randomText2)) {
                CustomAssert.fail("Verify instructor's post", "instructor post is not available");
      }

        } catch (Exception e) {
            Assert.fail("Exception in test case assignAssignmentToGroupAndRemoveStudent in class GroupPage", e);
        }

    }

    @Test(priority = 7, enabled = true)
    public void assignAssignmentToGroupAndAddStudent() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");

            new LoginUsingLTI().ltiLogin("7_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");
            new LoginUsingLTI().ltiLogin("7_2");//log in as student2
            ReportUtil.log("Student2 login", "Student2 logged in successfully", "pass");
            new LoginUsingLTI().ltiLogin("7_3");//log in as student3
            ReportUtil.log("Student3 login", "Student3 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("7");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Groups");
            ReportUtil.log("Navigate to group", "Instructor navigated to group page in successfully", "pass");

            new AddGroup().addGroup(7);
            ReportUtil.log("Creation of group", "Group created successfully", "pass");

            new AddGroup().addStudentToParticularGroup(1, 0, 2);
            ReportUtil.log("Adding student to group", "2 students added to group successfully", "pass");

            new Assignment().assignAssignmentToGroup(7);
            CustomAssert.assertEquals(currentAssignments.notStartedCountBox.getText(), "2", "Verify student count", "student count is 2", "Student count is not 2");

            new Navigator().NavigateTo("Groups");
            new AddGroup().addStudentToParticularGroup(1, 2, 3);

            new Navigator().NavigateTo("Class Assignments");
            CustomAssert.assertEquals(currentAssignments.notStartedCountBox.getText(), "3", "Verify student count", "student count is not increased", "Student count is increased");

            currentAssignments.viewStudentResponses.get(0).click();
            CustomAssert.assertEquals(assignmentResponsesPage.student_Name.size(), 3, "Verify added student", "added student is available", "added student is not available");
            String randomText = new PostMessage().postMessageFromAssignmentResponsePage();

            new LoginUsingLTI().ltiLogin("7_3");//log in as student3
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");
            CustomAssert.assertTrue(dashBoard.upComingAssignment.get(0).isDisplayed(), "Verify upcoming assignment", "Upcoming assignment is displayed", "Assignment is not available at upcoming section");
            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "student navigated to Course Stream in successfully", "pass");

            CustomAssert.assertEquals(courseStream.postText.getText().substring(1, 6), randomText, "Verify instructor's post", "Instructor post is available", "instructor post is not available");


        } catch (Exception e) {
            Assert.fail("Exception in test case assignAssignmentToGroupAndAddStudent in class GroupPage", e);
        }

    }

    @Test(priority = 8, enabled = true)
    public void addStudentAfterDueDateExpires() {
        try {

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");

            new LoginUsingLTI().ltiLogin("8_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");
            new LoginUsingLTI().ltiLogin("8_2");//log in as student2
            ReportUtil.log("Student2 login", "Student2 logged in successfully", "pass");
            new LoginUsingLTI().ltiLogin("8_3");//log in as student3
            ReportUtil.log("Student3 login", "Student3 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("8");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Groups");
            new AddGroup().addGroup(8);
            new AddGroup().addStudentToParticularGroup(1, 0, 2);

            new Assignment().assignAssignmentToGroup(8);
            CustomAssert.assertEquals(currentAssignments.notStartedCountBox.getText(), "2", "Verify student count", "student count is 2", "Student count is not 2");
            Thread.sleep(240000);//wait till due date expires

            new Navigator().NavigateTo("Groups");
            new AddGroup().addStudentToParticularGroup(1, 2, 3);

            new Navigator().NavigateTo("Class Assignments");
            CustomAssert.assertEquals(currentAssignments.notStartedCountBox.getText(), "2", "Verify student count", "student count is not increased", "Student count is increased");

            currentAssignments.viewStudentResponses.get(0).click();
            CustomAssert.assertEquals(assignmentResponsesPage.student_Name.size(), 2, "Verify added student", "added student is not available", "added student is available");
            String randomText = new PostMessage().postMessageFromAssignmentResponsePage();

            new LoginUsingLTI().ltiLogin("8_3");//log in as student3
            ReportUtil.log("Student3 login", "Student3 logged in successfully", "pass");

            boolean upComingAssignment = false;
            if (dashBoard.upComingAssignment.size() == 0) {
                upComingAssignment = true;
                CustomAssert.assertEquals(upComingAssignment, true, "Verify upcoming assignment", "Upcoming assignment is not displayed", "Assignment is available at upcoming section");
            }
            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "student navigated to Course Stream in successfully", "pass");

            boolean postedText = false;
            if (courseStream.postedText.size() == 0) {
                postedText = true;
                CustomAssert.assertEquals(postedText, true, "Verify instructor's post", "Instructor post is not available", "instructor post is available");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case addStudentAfterDueDateExpires in class GroupPage", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void verificationOfGroupIconCourseStream() {
        try {
            String context_title = ReadTestData.readDataByTagName("", "context_title", "9");
            String GroupName=ReadTestData.readDataByTagName("", "GroupName","9");

            ReportUtil.log("Description", "Test case validates creation of groups and adding students to groups", "info");

            new LoginUsingLTI().ltiLogin("9_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("9");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Groups");
            String groupName = new AddGroup().addGroup(9);
            new AddGroup().addStudentToParticularGroup(1, 0, 1);

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "Instructor navigated to Course Stream in successfully", "pass");

            String randomText = new RandomString().randomstring(3);

           //Instructor should have posted a post/link/file to Group+CS

            new PostMessage().postMessageAndShareinCs(randomText,"9","");
            WebDriverUtil.clickOnElementUsingJavascript(courseStream.visualIndicatorIcon.get(0)); //click on the visual indicator icon

            actual = courseStream.classSection.get(0).getText().trim();
            expected = context_title;
            CustomAssert.assertEquals(actual, expected, "Verify class section name for selected group in CS page", "class section name is  " + expected + "", "class section name is not " + expected + "");

            actual = courseStream.group_Name.get(0).getText().trim().substring(0,6);
            expected=GroupName;
            CustomAssert.assertEquals(actual, expected, "Verify group name ", "Group name is  " + expected + "", "Group name is not " + expected + "");

            actual = courseStream.group_users.get(0).getText().trim();
            expected = "family, givenname";
            CustomAssert.assertEquals(actual, expected, "Verify instructor name for selected group in CS page", "instructor name is  " + expected + "", "instructor name is not " + expected + "");

            actual = courseStream.group_users.get(1).getText().trim();
            expected = "Instructor";
            CustomAssert.assertEquals(actual, expected, "Verify Instructor tag for selected group in CS page", "Instructor tag is  " + expected + "", "Instructor tag is not " + expected + "");

            actual = courseStream.group_users.get(2).getText().trim();
            expected = "jan, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group in CS page", "student name is  " + expected + "", "student name is not " + expected + "");

            new LoginUsingLTI().ltiLogin("9_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "Instructor navigated to Course Stream in successfully", "pass");

            CustomAssert.assertEquals(courseStream.postedText.get(0).getText(), randomText, "Verify instructor's post", "Instructor post is available", "instructor post is not available");

            //Instructor should have posted a post/link/file to Group+Student(s)

            new LoginUsingLTI().ltiLogin("9");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "Instructor navigated to Course Stream in successfully", "pass");

            String randomText1 = new RandomString().randomstring(3);
            new PostMessage().postMessageAndShareinCs(randomText1,"9_3","");

            WebDriverUtil.clickOnElementUsingJavascript(courseStream.visualIndicatorIcon.get(0)); //click on the visual indicator icon
            actual = courseStream.group_Name.get(0).getText().trim().substring(0,6);
            expected=GroupName;
            CustomAssert.assertEquals(actual, expected, "Verify group name ", "Group name is  " + expected + "", "Group name is not " + expected + "");

            actual = courseStream.group_users.get(0).getText().trim();
            expected = "family, givenname";
            CustomAssert.assertEquals(actual, expected, "Verify instructor name for selected group in CS page", "instructor name is  " + expected + "", "instructor name is not " + expected + "");

            actual = courseStream.group_users.get(1).getText().trim();
            expected = "Instructor";
            CustomAssert.assertEquals(actual, expected, "Verify Instructor tag for selected group in CS page", "Instructor tag is  " + expected + "", "Instructor tag is not " + expected + "");

            actual = courseStream.group_users.get(2).getText().trim();
            expected = "jan, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group in CS page", "student name is  " + expected + "", "student name is not " + expected + "");

            actual = courseStream.group_Name.get(1).getText().trim();
            expected = "Individuals";
            CustomAssert.assertEquals(actual, expected, "Verify individual tag for selected group in CS page", "individual tag is  " + expected + "", "individual tag is not " + expected + "");

            actual = courseStream.group_users.get(3).getText().trim();
            expected = "jan, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group in CS page", "student name is  " + expected + "", "student name is not " + expected + "");

            new LoginUsingLTI().ltiLogin("9_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "student navigated to Course Stream in successfully", "pass");

            CustomAssert.assertEquals(courseStream.postedText.get(0).getText(), randomText1, "Verify instructor's post", "Instructor post is available", "instructor post is not available");

            //Instructor should have posted a post/link/file to Group+CS+Student(s)

            new LoginUsingLTI().ltiLogin("9");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "Instructor navigated to Course Stream in successfully", "pass");

            String randomText2 = new RandomString().randomstring(3);
            new PostMessage().postMessageAndShareinCs(randomText2,"9_4","");

            WebDriverUtil.clickOnElementUsingJavascript(courseStream.visualIndicatorIcon.get(0)); //click on the visual indicator icon

            actual = courseStream.classSection.get(0).getText().trim();
            expected = context_title;
            CustomAssert.assertEquals(actual, expected, "Verify class section name for selected group in CS page", "class section name is  " + expected + "", "class section name is not " + expected + "");

            actual = courseStream.group_Name.get(0).getText().trim().substring(0,6);
            expected=GroupName;
            CustomAssert.assertEquals(actual, expected, "Verify group name ", "Group name is  " + expected + "", "Group name is not " + expected + "");

            actual = courseStream.group_users.get(0).getText().trim();
            expected = "family, givenname";
            CustomAssert.assertEquals(actual, expected, "Verify instructor name for selected group in CS page", "instructor name is  " + expected + "", "instructor name is not " + expected + "");

            actual = courseStream.group_users.get(1).getText().trim();
            expected = "Instructor";
            CustomAssert.assertEquals(actual, expected, "Verify Instructor tag for selected group in CS page", "Instructor tag is  " + expected + "", "Instructor tag is not " + expected + "");

            actual = courseStream.group_users.get(2).getText().trim();
            expected = "jan, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group in CS page", "student name is  " + expected + "", "student name is not " + expected + "");

            actual = courseStream.group_Name.get(1).getText().trim();
            expected = "Individuals";
            CustomAssert.assertEquals(actual, expected, "Verify individual tag for selected group in CS page", "individual tag is  " + expected + "", "individual tag is not " + expected + "");

            actual = courseStream.group_users.get(3).getText().trim();
            expected = "jan, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group in CS page", "student name is  " + expected + "", "student name is not " + expected + "");

            new LoginUsingLTI().ltiLogin("9_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new Navigator().NavigateTo("Course Stream");
            CustomAssert.assertEquals(courseStream.postedText.get(0).getText(), randomText2, "Verify instructor's post", "Instructor post is available", "instructor post is not available");

            //Instructor should have posted a post/link/file to CS+Student(s)

            new LoginUsingLTI().ltiLogin("9");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "Instructor navigated to Course Stream in successfully", "pass");

            String randomText3 = new RandomString().randomstring(3);

            new PostMessage().postMessageAndShareinCs(randomText3,"9_5","");
            WebDriverUtil.clickOnElementUsingJavascript(courseStream.visualIndicatorIcon.get(0)); //click on the visual indicator icon

            actual = courseStream.classSection.get(0).getText().trim();
            expected = context_title;
            CustomAssert.assertEquals(actual, expected, "Verify class section name for selected group in CS page", "class section name is  " + expected + "", "class section name is not " + expected + "");

            actual = courseStream.group_Name.get(0).getText().trim();
            expected = "Individuals";
            CustomAssert.assertEquals(actual, expected, "Verify individual tag for selected group in CS page", "individual tag is  " + expected + "", "individual tag is not " + expected + "");

            actual = courseStream.group_users.get(0).getText().trim();
            expected = "jan, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group in CS page", "student name is  " + expected + "", "student name is not " + expected + "");

            new LoginUsingLTI().ltiLogin("9_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "Instructor navigated to Course Stream in successfully", "pass");

            CustomAssert.assertEquals(courseStream.postedText.get(0).getText(), randomText3, "Verify instructor's post", "Instructor post is available", "instructor post is not available");

            new PostMessage().postMessageAndShareinCs(randomText,"9","");
            WebDriverUtil.clickOnElementUsingJavascript(courseStream.visualIndicatorIcon.get(0)); //click on the visual indicator icon

            actual = courseStream.classSection.get(0).getText().trim();
            expected = context_title;
            CustomAssert.assertEquals(actual, expected, "Verify class section name for selected group in CS page", "class section name is  " + expected + "", "class section name is not " + expected + "");

            actual = courseStream.group_Name.get(0).getText().trim().substring(0,6);
            expected=GroupName;
            CustomAssert.assertEquals(actual, expected, "Verify group name ", "Group name is  " + expected + "", "Group name is not " + expected + "");

            actual = courseStream.group_users.get(0).getText().trim();
            expected = "family, givenname";
            CustomAssert.assertEquals(actual, expected, "Verify instructor name for selected group in CS page", "instructor name is  " + expected + "", "instructor name is not " + expected + "");

            actual = courseStream.group_users.get(1).getText().trim();
            expected = "Instructor";
            CustomAssert.assertEquals(actual, expected, "Verify Instructor tag for selected group in CS page", "Instructor tag is  " + expected + "", "Instructor tag is not " + expected + "");

            actual = courseStream.group_users.get(2).getText().trim();
            expected = "jan, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group in CS page", "student name is  " + expected + "", "student name is not " + expected + "");

        } catch (Exception e) {
            Assert.fail("Exception in test case verificationOfGroupIconCourseStream in class GroupPage", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void deleteGroupAndSearchInAssignField() {
        try {
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "10");
            ReportUtil.log("Description", "Test case validates delete a group and search in assign to field", "info");

            new LoginUsingLTI().ltiLogin("10_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("10");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Groups");
            ReportUtil.log("Navigate to group", "Instructor navigated to group page in successfully", "pass");

            new AddGroup().addGroup(10);
            ReportUtil.log("Creation of group", "Group created successfully", "pass");

            new AddGroup().addStudentToParticularGroup(1, 0, 1);
            ReportUtil.log("Adding student to group", "1 student added to group successfully", "pass");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "Instructor navigated to Course Stream in successfully", "pass");

            String randomText1 = new RandomString().randomstring(3);
            new PostMessage().postMessageAndShareToGroup(randomText1, shareWithInitialString, "10", "");

            new Navigator().NavigateTo("Groups");
            ReportUtil.log("Navigate to group", "Instructor navigated to group page in successfully", "pass");

            new AddGroup().deleteGroup(1);
            ReportUtil.log("Deletion to group", "Instructor deleted a group successfully", "pass");

            new Navigator().NavigateTo("My Assignments");//navigate to my assignments
            ReportUtil.log("Navigate to My Assignments", "Instructor navigated to My Assignments in successfully", "pass");

            questionBank.getQuestionBankTitle().click();//click on question bank
            Thread.sleep(2000);
            List<WebElement> assign = questionBank.assignThisLink;

            for(int i=0;i<assign.size();i++)
            {
                if(assign.get(i).isDisplayed())
                {
                    assign.get(i).click();
                    break;
                }
            }
            Thread.sleep(3000);
            questionBank.assignToTextBox.sendKeys("Thr");
            CustomAssert.assertEquals(questionBank.noResultMessage.getText(),"No results found","verify deleted group in assign to field","Deleted group is not displayed in auto suggestion","Deleted group is displayed in auto suggestion");

            new Navigator().NavigateTo("Course Stream");
            ReportUtil.log("Navigate to Course Stream", "Instructor navigated to Course Stream in successfully", "pass");

            CustomAssert.assertEquals(courseStream.postText.getText(), randomText1, "Verify instructor's post", "Instructor post is available", "instructor post is not available");
            WebDriverUtil.clickOnElementUsingJavascript(courseStream.tab_post);//click on post
            courseStream.classSectionSuggestion_textbox.sendKeys("thr");
            CustomAssert.assertEquals(courseStream.noResultMessage.getText(),"No results found","verify deleted group in assign to field","Deleted group is not displayed in auto suggestion","Deleted group is displayed in auto suggestion");

        } catch (Exception e) {
            Assert.fail("Exception in test case deleteGroupAndSearchInAssignField in class GroupPage", e);
        }

    }

}