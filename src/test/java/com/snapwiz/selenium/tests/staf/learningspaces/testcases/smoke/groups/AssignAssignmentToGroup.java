package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.groups;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiscussionWidget;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.ClassSectionDropDown;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Perspective;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.group.Group;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by mukesh on 13/5/16.
 */
public class AssignAssignmentToGroup extends Driver {

    Group group;
    NewAssignment newAssignment;
    MyQuestionBank myQuestionBank;
    CurrentAssignments currentAssignments;
    AssessmentResponses assessmentResponses;
    CourseStreamPage courseStreamPage;
    Assignments assignments;
    ClassSectionDropDown classSectionDropDown;
    Dashboard dashboard;
    Perspective perspective;
    AssignmentResponsesPage assignmentResponsesPage;

    String actual = "";
    String expected = "";
    int actualSize=0;
    int expectedSize=0;
    List<WebElement> studentName=null;
    List<String> student=null;
    List<String> groupOneStudent=null;
    int size=0;
    @BeforeMethod
    public void initializeWebElement() {
        WebDriver driver=Driver.getWebDriver();
        group= PageFactory.initElements(driver,Group.class);
        newAssignment = PageFactory.initElements(driver, NewAssignment.class);
        myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
        currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
        assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);
        courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
        assignments = PageFactory.initElements(driver, Assignments.class);
        classSectionDropDown = PageFactory.initElements(driver, ClassSectionDropDown.class);
        dashboard = PageFactory.initElements(driver, Dashboard.class);
        perspective = PageFactory.initElements(driver, Perspective.class);
        assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
    }

    @Test(priority = 1,enabled = false)
    public void assignAssignmentToOneGroup(){
        WebDriver driver=Driver.getWebDriver();
        try {
            new LoginUsingLTI().ltiLogin("1_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("1_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("1_3");
            ReportUtil.log("Login as student3", "student logged successfully for different class section", "info");
            new LoginUsingLTI().ltiLogin("1");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To OneGroup", "info");
            String groupName=new AddGroup().addGroup(1); //add group
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1,0,2);//add student to group
            ReportUtil.log("Description", "Student added successfully to Group", "pass");
            new Navigator().NavigateTo("My Assignments");
            new Navigator().navigateToTab("Question Banks");
            ListIterator<WebElement> listIt=myQuestionBank.getAssignThisList().listIterator();
            while(listIt.hasNext())
            {
                if(listIt.next().isDisplayed())
                {
                    listIt.next().click();
                    break;
                }
            }

            while (myQuestionBank.closeSuggestion.size()>0){
                myQuestionBank.closeIcon_instructorCard.click();
            }

            myQuestionBank.shareTextBox.sendKeys("si");
            actual = myQuestionBank.assignThis_errorMessage.getText().trim();
            expected = "Enter at least 3 characters";
            CustomAssert.assertEquals(actual, expected, "Verify error message in assign this popup", "Error message is  " + expected + "", "Error message is not " + expected + "");
            new Assignment().assignAssignmentToGroup(1); //Assign group to student

            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            currentAssignments.getViewGrade_link().click(); //click on the view response link
            AddGroup.selectGroupInAssignmentResponsePage(0);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(3);//select group
            Thread.sleep(4000);
            WebDriverUtil.waitTillVisibilityOfElement(assessmentResponses.studentName.get(0),50);
            actual = assessmentResponses.studentName.get(0).getText().trim();
            expected = "a, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group", "Student name is  " + expected + "", "Student name is not " + expected + "");

            actual = assessmentResponses.studentName.get(1).getText().trim();
            expected = "b, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group", "Student name is  " + expected + "", "Student name is not " + expected + "");

            int actual1 = assessmentResponses.studentName.size();
            int expected1 = 2;
            CustomAssert.assertEquals(actual1, expected1, "Verify No of student selected group", "Number of student is  " + expected + "", "Number of student is not " + expected + "");

            new Navigator().NavigateTo("Course Stream"); //Navigate to Course Stream
            ReportUtil.log("Description", "Instructor navigated to Course Stream ", "info");
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.visualIndicatorIcon.get(0)); //click on the visual indicator icon

            actual = courseStreamPage.group_users.get(0).getText().trim();
            expected = "a, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group in CS page", "Student name is  " + expected + "", "Student name is not " + expected + "");

            actual = courseStreamPage.group_users.get(1).getText().trim();
            expected = "b, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group in CS page", "Student name is  " + expected + "", "Student name is not " + expected + "");

            actual = courseStreamPage.group_Name.get(0).getText().trim();
            expected=groupName;
            CustomAssert.assertEquals(actual, expected, "Verify group name ", "Group name is  " + expected + "", "Group name is not " + expected + "");


            new LoginUsingLTI().ltiLogin("1_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Course Stream"); //Navigate to Course Stream

            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.visualIndicatorIcon.get(0)); //click on the visual indicator icon
            actual = courseStreamPage.group_users.get(0).getText().trim();
            expected = "a, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group in CS page", "Student name is  " + expected + "", "Student name is not " + expected + "");

            actual = courseStreamPage.group_users.get(1).getText().trim();
            expected = "b, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group in CS page", "Student name is  " + expected + "", "Student name is not " + expected + "");

            actual = courseStreamPage.group_Name.get(0).getText().trim();
            expected=groupName;
            CustomAssert.assertEquals(actual, expected, "Verify group name ", "Group name is  " + expected + "", "Group name is not " + expected + "");

            new Navigator().NavigateTo("Assignments"); //navigate to Assignments page
            CustomAssert.assertTrue(assignments.assignmentName.isDisplayed(), "verify assignment entry","Assigned assignment is present in assignment page","Assigned assignment is not present in assignment page");

            new LoginUsingLTI().ltiLogin("1_3");
            ReportUtil.log("Login as student3", "student logged successfully for different class section", "info");

            new Navigator().NavigateTo("Assignments"); //navigate to Assignments page
            CustomAssert.assertTrue(assignments.noAssignmentEntry.isDisplayed(), "verify assignment entry","Assigned assignment should not present  in assignment page","Assigned assignment is displaying in assignment page");

        } catch (Exception e) {
            Assert.fail("Exception in TC assignAssignmentToOneGroup of class AssignAssignmentToGroup",e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void sameStudentPartOfDifferentGroup(){
        WebDriver driver=Driver.getWebDriver();
        try {
            new LoginUsingLTI().ltiLogin("242_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("242_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("242_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("242_4");
            ReportUtil.log("Login as student4", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("242_5");
            ReportUtil.log("Login as student5", "student logged successfully of same class section", "info");

            new LoginUsingLTI().ltiLogin("242_6");
            ReportUtil.log("Login as student6", "student logged successfully for different class section", "info");
            new LoginUsingLTI().ltiLogin("242");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=new AddGroup().addGroup(242); //add group
            System.out.println("groupName:"+groupName);
            AddGroup.selectGroup(1);//select first group
            AddGroup.addStudentToParticularGroup(1,0,3);//add first three student to group
            AddGroup.selectGroup(2);//select second group
            AddGroup.addStudentToParticularGroup(2,2,5);//add student from third row to group
            new Assignment().assignAssignmentToGroup(242);

            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            actualSize = currentAssignments.getList_assignmentName().size();
            expectedSize = 1;
            CustomAssert.assertEquals(actual, expected, "Verify assignment entry in assignment response page", "assignment entry  is  " + expected + "", "assignment entry  is not " + expected + "");

            currentAssignments.getViewGrade_link().click(); //click on the view response link
            AddGroup.selectGroupInAssignmentResponsePage(0);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(3);//select second group
            Thread.sleep(4000);
            WebDriverUtil.waitTillVisibilityOfElement(assessmentResponses.studentName.get(0),50);
            actual = assessmentResponses.studentName.get(0).getText().trim();
            expected = "c, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group", "Student name is  " + expected + "", "Student name is not " + expected + "");

            actual = assessmentResponses.studentName.get(1).getText().trim();
            expected = "d, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group", "Student name is  " + expected + "", "Student name is not " + expected + "");

            actual = assessmentResponses.studentName.get(2).getText().trim();
            expected = "e, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group", "Student name is  " + expected + "", "Student name is not " + expected + "");

            int actual1 = assessmentResponses.studentName.size();
            int expected1 = 3;
            CustomAssert.assertEquals(actual1, expected1, "Verify No of student selected group", "Number of student is  " + expected + "", "Number of student is not " + expected + "");


            AddGroup.selectGroupInAssignmentResponsePage(0);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(4);//select group
            Thread.sleep(4000);
            WebDriverUtil.waitTillVisibilityOfElement(assessmentResponses.studentName.get(0),50);
            actual = assessmentResponses.studentName.get(0).getText().trim();
            expected = "a, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group", "Student name is  " + expected + "", "Student name is not " + expected + "");

            actual = assessmentResponses.studentName.get(1).getText().trim();
            expected = "b, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group", "Student name is  " + expected + "", "Student name is not " + expected + "");

            actual = assessmentResponses.studentName.get(2).getText().trim();
            expected = "c, month";
            CustomAssert.assertEquals(actual, expected, "Verify student name for selected group", "Student name is  " + expected + "", "Student name is not " + expected + "");

            new LoginUsingLTI().ltiLogin("242_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Course Stream"); //Navigate to Course Stream

            int size=courseStreamPage.assignmentName.size();
            if(size>1){
                CustomAssert.fail("Verify assignment entry in CS page","Assignment entry is getting duplicated.");
            }
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.visualIndicatorIcon.get(0)); //click on the visual indicator icon

            List<String> student = new ArrayList<String>();
            List<String> groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("c, month");
            groupOneStudent.add("d, month");
            groupOneStudent.add("e, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");
            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");
            groupOneStudent.add("c, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");

            List<WebElement> studentName =courseStreamPage.group_users;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }

            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in CS page", "Student name is not " + expected + "");
            }

            actual = courseStreamPage.group_Name.get(0).getText().trim();
            CustomAssert.assertTrue(actual.contains("one"), "Verify first group name ", "Group name one  is  " + expected + "", "Group name is not " + expected + "");

            actual = courseStreamPage.group_Name.get(1).getText().trim();
            CustomAssert.assertTrue(actual.contains("two"), "Verify second group name ", "Group name two is  " + expected + "", "Group name is not " + expected + "");

            new LoginUsingLTI().ltiLogin("242_6");
            ReportUtil.log("Login as student6", "student logged successfully for different class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments page
            CustomAssert.assertTrue(assignments.noAssignmentEntry.isDisplayed(), "verify assignment entry", "Assigned assignment should not present  in assignment page", "Assigned assignment is displaying in assignment page");

        } catch (Exception e) {
            Assert.fail("Exception in TC sameStudentPartOfDifferentGroup of class AssignAssignmentToGroup",e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void assignAssignmentToGroupAndIndividualStudent(){
        WebDriver driver=Driver.getWebDriver();
        try {
            new LoginUsingLTI().ltiLogin("249_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("249_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("249_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("249_4");
            ReportUtil.log("Login as student4", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("249_5");
            ReportUtil.log("Login as student5", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("249_6");
            ReportUtil.log("Login as student6", "student logged successfully for same class section", "info");
            new LoginUsingLTI().ltiLogin("249_7");
            ReportUtil.log("Login as student7", "student logged successfully of same class section", "info");

            new LoginUsingLTI().ltiLogin("249");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=new AddGroup().addGroup(249); //add group
            System.out.println("groupName:"+groupName);
            AddGroup.selectGroup(1);//select first group
            AddGroup.addStudentToParticularGroup(1,0,3);//add first three student to group
            AddGroup.selectGroup(2);//select second group
            AddGroup.addStudentToParticularGroup(2,2,6);//add student from third row to group*/
            new Assignment().assignAssignmentToGroup(249);


            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            actualSize = currentAssignments.getList_assignmentName().size();
            expectedSize = 1;
            CustomAssert.assertEquals(actualSize, expectedSize, "Verify assignment entry in assignment response page", "assignment entry  is  " + expected + "", "assignment entry  is not " + expected + "");
            currentAssignments.getViewGrade_link().click(); //click on the view response link

            AddGroup.selectGroupInAssignmentResponsePage(0);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(3);//select second group
            Thread.sleep(4000);
            WebDriverUtil.waitTillVisibilityOfElement(assessmentResponses.studentName.get(0),50);
            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("c, month");
            groupOneStudent.add("d, month");
            groupOneStudent.add("e, month");
            groupOneStudent.add("f, month");

            studentName =assessmentResponses.studentName;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }

            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in AssignmentResponse page", "Student name is not " + expected + "");
            }

            AddGroup.selectGroupInAssignmentResponsePage(0);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(4);//select group
            Thread.sleep(4000);
            WebDriverUtil.waitTillVisibilityOfElement(assessmentResponses.studentName.get(0), 50);

            student = new ArrayList<String>();
            List<String> groupTwoStudent = new ArrayList<String>();
            groupTwoStudent.add("a, month");
            groupTwoStudent.add("b, month");
            groupTwoStudent.add("c, month");

            studentName =assessmentResponses.studentName;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }

            if (!student.equals(groupTwoStudent)) {
                CustomAssert.fail("Verify student name for second group in AssignmentResponse page", "Student name is not " + expected + "");
            }


            new LoginUsingLTI().ltiLogin("249_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Course Stream"); //Navigate to Course Stream

            int size=courseStreamPage.assignmentName.size();
            if(size>1){
                CustomAssert.fail("Verify assignment entry in CS page","Assignment entry is getting duplicated.");
            }
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.visualIndicatorIcon.get(0)); //click on the visual indicator icon

            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("c, month");
            groupOneStudent.add("d, month");
            groupOneStudent.add("e, month");
            groupOneStudent.add("f, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");
            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");
            groupOneStudent.add("c, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");
            groupOneStudent.add("g, month");

            studentName =courseStreamPage.group_users;
            for (WebElement we : studentName) {
                new ScrollElement().scrollToViewOfElement(we);
                System.out.println(we.getText());
                student.add(we.getText());
            }

            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in CS page", "All Student name is not displaying");
            }

            actual = courseStreamPage.group_Name.get(2).getText().trim();
            expected="Individuals";
            CustomAssert.assertTrue(actual.contains("Individuals"), "Verify Individuals group name ", "Group name two is  " + expected + "", "Group name is not " + expected + "");

            new LoginUsingLTI().ltiLogin("249_7");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Course Stream"); //Navigate to Course Stream

            size=courseStreamPage.assignmentName.size();
            if(size>1){
                CustomAssert.fail("Verify assignment entry in CS page","Assignment entry is getting duplicated.");
            }
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.visualIndicatorIcon.get(0)); //click on the visual indicator icon

            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("c, month");
            groupOneStudent.add("d, month");
            groupOneStudent.add("e, month");
            groupOneStudent.add("f, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");
            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");
            groupOneStudent.add("c, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");
            groupOneStudent.add("g, month");

            studentName =courseStreamPage.group_users;
            for (WebElement we : studentName) {
                new ScrollElement().scrollToViewOfElement(we);
                System.out.println(we.getText());
                student.add(we.getText());
            }

            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in CS page", "All Student name is not displaying");
            }
        } catch (Exception e) {
            Assert.fail("Exception in TC assignAssignmentToGroupAndIndividualStudent of class AssignAssignmentToGroup",e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void assignAssignmentToDifferentClassSectionGroup(){
        WebDriver driver=Driver.getWebDriver();
        try {
            new LoginUsingLTI().ltiLogin("250_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("250_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("250_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("250_4");
            ReportUtil.log("Login as student4", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("250");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=new AddGroup().addGroup(250); //add group
            System.out.println("groupName:"+groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1,0,2);//add first three student to group

            new LoginUsingLTI().ltiLogin("251");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            new AddGroup().addGroup(251); //add group
            AddGroup.selectGroup(1);//select first group
            AddGroup.addStudentToParticularGroup(1,0,2);//add first three student to group
            new Assignment().assignAssignmentToGroup(251);
            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments

            actualSize = currentAssignments.getList_assignmentName().size();
            expectedSize = 1;
            CustomAssert.assertEquals(actualSize, expectedSize, "Verify assignment entry in assignment response page", "assignment entry  is  " + expected + "", "assignment entry  is not " + expected + "");
            currentAssignments.getViewGrade_link().click(); //click on the view response link

            AddGroup.selectGroupInAssignmentResponsePage(0);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(2);//select  group
            Thread.sleep(4000);
            WebDriverUtil.waitTillVisibilityOfElement(assessmentResponses.studentName.get(0),50);
            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("c, month");
            groupOneStudent.add("d, month");

            studentName =assessmentResponses.studentName;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }

            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in AssignmentResponse page", "Student name is not " + expected + "");
            }

            classSectionDropDown.defaultClassSection.click(); //click on the class section dropdown
            classSectionDropDown.classSectionName_list.get(2).click();

            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            currentAssignments.getViewGrade_link().click(); //click on the view response link

            AddGroup.selectGroupInAssignmentResponsePage(0);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(2);//select  group
            Thread.sleep(4000);
            WebDriverUtil.waitTillVisibilityOfElement(assessmentResponses.studentName.get(0),50);
            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");

            studentName =assessmentResponses.studentName;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }

            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in AssignmentResponse page", "Student name is not " + expected + "");
            }

            new LoginUsingLTI().ltiLogin("250_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Course Stream"); //Navigate to Course Stream

            size=courseStreamPage.assignmentName.size();
            if(size>1){
                CustomAssert.fail("Verify assignment entry in CS page","Assignment entry is getting duplicated.");
            }
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.visualIndicatorIcon.get(0)); //click on the visual indicator icon

            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");
            studentName =courseStreamPage.group_users;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }
            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in CS page", "All Student name is not displaying");
            }

            new LoginUsingLTI().ltiLogin("250_3");
            ReportUtil.log("Login as student1", "student logged successfully of different class section", "info");
            new Navigator().NavigateTo("Course Stream"); //Navigate to Course Stream

            size=courseStreamPage.assignmentName.size();
            if(size>1){
                CustomAssert.fail("Verify assignment entry in CS page","Assignment entry is getting duplicated.");
            }
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.visualIndicatorIcon.get(0)); //click on the visual indicator icon

            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("c, month");
            groupOneStudent.add("d, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");
            studentName =courseStreamPage.group_users;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }
            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in CS page", "All Student name is not displaying");
            }

        } catch (Exception e) {
            Assert.fail("Exception in TC assignAssignmentToDifferentClassSectionGroup of class AssignAssignmentToGroup",e);
        }
    }


    @Test(priority = 5,enabled = false)
    public void updateAssignment(){
        WebDriver driver=Driver.getWebDriver();
        try {
            new LoginUsingLTI().ltiLogin("252_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("252_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("252_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("252_4");
            ReportUtil.log("Login as student4", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("252");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=new AddGroup().addGroup(252); //add group
            System.out.println("groupName:"+groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1,0,2);//add first three student to group
            AddGroup.selectGroup(2);//select second group
            AddGroup.addStudentToParticularGroup(2,0,2);//add first three student to group

            new LoginUsingLTI().ltiLogin("253");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            new AddGroup().addGroup(253); //add group
            AddGroup.selectGroup(1);//select first group
            AddGroup.addStudentToParticularGroup(1, 0, 2);//add first three student to group
            AddGroup.selectGroup(2);//select second group
            AddGroup.addStudentToParticularGroup(2, 0, 2);//add first three student to group
            new Assignment().assignAssignmentToGroup(253);
            new LoginUsingLTI().ltiLogin("252");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student responses
            assignmentResponsesPage.extendDueDateLabel.click();//click on extend due date
            CustomAssert.assertTrue(currentAssignments.getExtendDueDateForField().get(0).getText().contains("one"), "Verify Group name to which it was assigned should be displayed by default in 'Assign to' field.","Group name to which it was assigned is displaying by default in 'Assign to' field.","Group name to which it was assigned is not displaying by default in 'Assign to' field.");
            newAssignment.classSection.get(0).sendKeys("two");
            Thread.sleep(2000);
            newAssignment.shareWithClass.click();
            currentAssignments.getNewDueDateField().click();//click on date picker-month
            currentAssignments.Next_Date_picker.click();//click on next month arrow
            Thread.sleep(2000);
            driver.findElement(By.linkText("6")).click();//select date
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.updateExtendDueDate);//click on re-assign button

            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            actualSize = currentAssignments.getList_assignmentName().size();
            expectedSize = 1;
            CustomAssert.assertEquals(actualSize, expectedSize, "Verify assignment entry in assignment response page", "assignment entry  is  " + expected + "", "assignment entry  is not " + expected + "");
            currentAssignments.getViewGrade_link().click(); //click on the view response link

            AddGroup.selectGroupInAssignmentResponsePage(0);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(2);//select  group
            Thread.sleep(4000);
            WebDriverUtil.waitTillVisibilityOfElement(assessmentResponses.studentName.get(0),50);
            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");

            studentName =assessmentResponses.studentName;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }

            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in AssignmentResponse page", "Student name is not " + expected + "");
            }



            new Navigator().NavigateTo("Course Stream"); //Navigate to Course Stream
            size=courseStreamPage.assignmentName.size();
            if(size>1){
                CustomAssert.fail("Verify assignment entry in CS page","Assignment entry is getting duplicated.");
            }
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.visualIndicatorIcon.get(0)); //click on the visual indicator icon

            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");
            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");
            studentName =courseStreamPage.group_users;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }
            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in CS page", "All Student name is not displaying");
            }

            actual = courseStreamPage.group_Name.get(0).getText().trim();
            CustomAssert.assertTrue(actual.contains("one"), "Verify first group name ", "Group name one  is  " + expected + "", "Group name is not " + expected + "");

            actual = courseStreamPage.group_Name.get(1).getText().trim();
            CustomAssert.assertTrue(actual.contains("two"), "Verify second group name ", "Group name two is  " + expected + "", "Group name is not " + expected + "");

        } catch (Exception e) {
            Assert.fail("Exception in TC updateAssignment of class AssignAssignmentToGroup",e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void addStudentToGroupAfterDueDateExpired(){
        WebDriver driver=Driver.getWebDriver();
        try {
            new LoginUsingLTI().ltiLogin("254_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("254_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("254_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("254_4");
            ReportUtil.log("Login as student4", "student logged successfully of same class section", "info");

            new LoginUsingLTI().ltiLogin("254_5");
            ReportUtil.log("Login as student4", "student logged successfully of different class section", "info");
            new LoginUsingLTI().ltiLogin("254_6");
            ReportUtil.log("Login as student4", "student logged successfully of different class section", "info");
            new LoginUsingLTI().ltiLogin("254");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=new AddGroup().addGroup(254); //add group
            System.out.println("groupName:"+groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1,0,2);//add first two student to group
            AddGroup.selectGroup(2);//select second group
            AddGroup.addStudentToParticularGroup(2,0,2);//add first two student to group

            new LoginUsingLTI().ltiLogin("255");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            new AddGroup().addGroup(255); //add group
            AddGroup.selectGroup(1);//select first group
            AddGroup.addStudentToParticularGroup(1,0,2);//add first two student to group
            AddGroup.selectGroup(2);//select second group
            AddGroup.addStudentToParticularGroup(2,0,2);//add first two student to group
            new Assignment().assignAssignmentToGroup(255);


            new LoginUsingLTI().ltiLogin("254");
            AddGroup.selectGroup(2);//select first group
            AddGroup.addStudentToParticularGroup(2,2,3);//add first three student to group
            new Navigator().NavigateTo("Course Stream"); //Navigate to Course Stream
            size=courseStreamPage.assignmentName.size();
            if(size>1){
                CustomAssert.fail("Verify assignment entry in CS page","Assignment entry is getting duplicated.");
            }
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.visualIndicatorIcon.get(0)); //click on the visual indicator icon

            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");
            groupOneStudent.add("c, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");

            studentName =courseStreamPage.group_users;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }
            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in CS page", "newly added student name is not displaying");
            }

            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            actualSize = currentAssignments.getList_assignmentName().size();
            expectedSize = 1;
            CustomAssert.assertEquals(actualSize, expectedSize, "Verify assignment entry in assignment response page", "assignment entry  is  " + expected + "", "assignment entry  is not " + expected + "");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link

            AddGroup.selectGroupInAssignmentResponsePage(0);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(2);//select  group
            Thread.sleep(4000);
            WebDriverUtil.waitTillVisibilityOfElement(assessmentResponses.studentName.get(0), 50);
            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");
            groupOneStudent.add("c, month");

            studentName =assessmentResponses.studentName;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }

            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in AssignmentResponse page", "newly added student should be displayed AssignmentResponse page");
            }

            new LoginUsingLTI().ltiLogin("254_3");
            ReportUtil.log("Login as student4", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments page
            boolean isPresent;
            try {
                WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getAssignmentName());
                isPresent=true;
            } catch (Exception e) {
                isPresent=false;
            }
            CustomAssert.assertEquals(isPresent, true, "Verify Assignment Entry", "Assignment should be displayed in Assignment page", "Assignment should not be displayed in Assignment page");

            new Navigator().NavigateTo("Dashboard");
            String upcomingText=dashboard.upcoming_Text.getText().trim();
            CustomAssert.assertEquals(upcomingText,"Upcoming","Verify Upcoming text","Upcoming Text is visible","Upcoming Text is  not visible");
            WebElement assignment=dashboard.upcoming_assignment;
            CustomAssert.assertTrue(assignment.isDisplayed(), "Verify Assignment Entry", "Assignment should  be displayed in Dashboard", "Assignment name is not displaying in upcoming section");

            Thread.sleep(180000); //wait till due date got expired
            /*AfterDueDateExpired*/
            new LoginUsingLTI().ltiLogin("254");
            AddGroup.selectGroup(2);//select first group
            AddGroup.addStudentToParticularGroup(2,3,4);//add first three student to group
            new Navigator().NavigateTo("Course Stream"); //Navigate to Course Stream
            size=courseStreamPage.assignmentName.size();
            if(size>1){
                CustomAssert.fail("Verify assignment entry in CS page","Assignment entry is getting duplicated.");
            }
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.visualIndicatorIcon.get(0)); //click on the visual indicator icon

            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");
            groupOneStudent.add("c, month");
            groupOneStudent.add("d, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");

            studentName =courseStreamPage.group_users;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }
            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in CS page", "newly added student name is not displaying");
            }

            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            actualSize = currentAssignments.getList_assignmentName().size();
            expectedSize = 1;
            CustomAssert.assertEquals(expectedSize, expectedSize, "Verify assignment entry in assignment response page", "assignment entry  is  " + expected + "", "assignment entry  is not " + expected + "");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link()); //click on the view response link

            AddGroup.selectGroupInAssignmentResponsePage(0);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(2);//select  group
            Thread.sleep(4000);
            WebDriverUtil.waitTillVisibilityOfElement(assessmentResponses.studentName.get(0),50);
            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");
            groupOneStudent.add("c, month");

            studentName =assessmentResponses.studentName;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }

            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in AssignmentResponse page", "newly added student should not displayed");
            }

            new LoginUsingLTI().ltiLogin("254_4");
            ReportUtil.log("Login as student4", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments page
            CustomAssert.assertTrue(assignments.noAssignmentEntry.isDisplayed(), "verify assignment entry", "Assigned assignment should not present in assignment page", "Assigned assignment is displaying in assignment page");
            new Navigator().NavigateTo("Dashboard");
            isPresent=false;
            try {
                WebDriverUtil.clickOnElementUsingJavascript(dashboard.upcoming_assignment);
                isPresent=true;
            } catch (Exception e) {
                isPresent=false;
            }
            CustomAssert.assertEquals(isPresent,false, "Verify Assignment Entry", "Assignment should not be displayed in Dashboard", "Assignment name is displaying in upcoming section for newly added student after due date expired");

        } catch (Exception e) {
            Assert.fail("Exception in TC addStudentToGroupAfterDueDateExpired of class AssignAssignmentToGroup",e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void removeStudentFromGroup(){
        WebDriver driver=Driver.getWebDriver();
        try {


            new Assignment().create(256);
            new LoginUsingLTI().ltiLogin("256_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("256_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("256_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("256_4");
            ReportUtil.log("Login as student4", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("256_5");
            ReportUtil.log("Login as student4", "student logged successfully of different class section", "info");
            new LoginUsingLTI().ltiLogin("256");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=new AddGroup().addGroup(256); //add group
            System.out.println("groupName:"+groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1,0,3);//add first three student to group

            new LoginUsingLTI().ltiLogin("257");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            new AddGroup().addGroup(257); //add group
            AddGroup.selectGroup(1);//select first group
            AddGroup.addStudentToParticularGroup(1,0,1);//add first three student to group
            new Assignment().assignAssignmentToGroup(257);

            new LoginUsingLTI().ltiLogin("256");
            AddGroup.selectGroup(1);//select first group
            AddGroup.deleteStudentFromGroup(1,"256_3");
            new Navigator().NavigateTo("Course Stream"); //Navigate to Course Stream
            size=courseStreamPage.assignmentName.size();
            if(size>1){
                CustomAssert.fail("Verify assignment entry in CS page","Assignment entry is getting duplicated.");
            }
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.visualIndicatorIcon.get(0)); //click on the visual indicator icon

            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");

            studentName =courseStreamPage.group_users;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }
            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in CS page", "After removing Student, student still displaying in course steam group list");
            }

            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            actualSize = currentAssignments.getList_assignmentName().size();
            expectedSize = 1;
            CustomAssert.assertEquals(expectedSize, expectedSize, "Verify assignment entry in assignment response page", "assignment entry  is  " + expected + "", "assignment entry  is not " + expected + "");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link

            Thread.sleep(4000);
            WebDriverUtil.waitTillVisibilityOfElement(assessmentResponses.studentName.get(0), 50);
            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");
            groupOneStudent.add("c, month");

            studentName =assessmentResponses.studentName;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }

            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in AssignmentResponse page", "Removed student is not displaying in all group filter AssignmentResponse page");
            }

            AddGroup.selectGroupInAssignmentResponsePage(0);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(3);//select  group
            Thread.sleep(4000);
            WebDriverUtil.waitTillVisibilityOfElement(assessmentResponses.studentName.get(0), 50);
            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");

            studentName =assessmentResponses.studentName;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }

            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in AssignmentResponse page", "Removed student is still displaying in selected group in AssignmentResponse page");
            }

            new LoginUsingLTI().ltiLogin("256_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getAssessmentName());//click on the assignment name
            Thread.sleep(3000);
            boolean isPresent=false;
            try {
                WebElement dueDateNotification = currentAssignments.finish_button;
                isPresent=dueDateNotification.isDisplayed();
            } catch (Exception e) {
                isPresent=false;
            }
            CustomAssert.assertEquals(isPresent, true, "Verify Assignment Entry", "Assignment should be accessible until the due date has expired ", "Assignment is not accessible before due date has expired");

            Thread.sleep(120000); //wait till due date got expired
            new LoginUsingLTI().ltiLogin("256_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            currentAssignments.getAssessmentName().click(); //click on the assignment name
            isPresent=false;
            try {
                WebElement dueDateNotification = assignments.dueDateNotification;
                isPresent=dueDateNotification.isDisplayed(); //true
            } catch (Exception e) {
                isPresent=false;
            }
            CustomAssert.assertEquals(isPresent, true, "Verify Assignment Entry", "Assignment should not be accessible after the due date has expired ", "Assignment is not accessible after due date has expired");
        } catch (Exception e) {
            Assert.fail("Exception in TC removeStudentFromGroup of class AssignAssignmentToGroup",e);
        }
    }

    @Test(priority = 9,enabled = true)
    public void moveStudentToDifferentClassSection(){
        WebDriver driver=Driver.getWebDriver();
        try {
            new LoginUsingLTI().ltiLogin("259_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("259_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("259_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("259_4");
            ReportUtil.log("Login as student4", "student logged successfully of different class section", "info");
            new LoginUsingLTI().ltiLogin("259");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=new AddGroup().addGroup(259); //add group
            System.out.println("groupName:" + groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 3);//add first three student to group

            new LoginUsingLTI().ltiLogin("260");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            new AddGroup().addGroup(260); //add group
            AddGroup.selectGroup(1);//select first group
            AddGroup.addStudentToParticularGroup(1,0,1);//add first three student to group
            new Assignment().assignAssignmentToGroup(260);

            new LoginUsingLTI().ltiLogin("259_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            currentAssignments.getAssessmentName().click(); //click on the assignment name
            Thread.sleep(3000);
            boolean isPresent=false;
            try {
                WebElement dueDateNotification = currentAssignments.finish_button;
                isPresent=dueDateNotification.isDisplayed();
            } catch (Exception e) {
                isPresent=false;
            }
            CustomAssert.assertEquals(isPresent, true, "Verify Assignment Entry", "Assignment should be accessible until the due date has expired ", "Assignment is not accessible before due date has expired");
            new Navigator().NavigateTo("Dashboard");
            String upcomingText=dashboard.upcoming_Text.getText().trim();
            CustomAssert.assertEquals(upcomingText, "Upcoming", "Verify Upcoming text", "Upcoming Text is visible","Upcoming Text is  not visible");
            WebElement assignment=dashboard.upcoming_assignment;
            CustomAssert.assertTrue(assignment.isDisplayed(), "Verify Assignment Entry", "Assignment should  be displayed in Dashboard", "Assignment name is not displaying in upcoming section");

            new LoginUsingLTI().ltiLogin("259_5");
            ReportUtil.log("Login as student3", "student logged successfully of different class section", "info");
            new Navigator().NavigateTo("Assignments");
            new LoginUsingLTI().ltiLogin("259");
            new Navigator().NavigateTo("Course Stream"); //Navigate to Course Stream
            size=courseStreamPage.assignmentName.size();
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.visualIndicatorIcon.get(0)); //click on the visual indicator icon

            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");

            studentName =courseStreamPage.group_users;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }
            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in CS page", "After removing Student, student still displaying in course steam group list");
            }

            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link
            Thread.sleep(4000);
            AddGroup.selectGroupInAssignmentResponsePage(0);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(3);//select  group
            Thread.sleep(4000);
            WebDriverUtil.waitTillVisibilityOfElement(assessmentResponses.studentName.get(0), 50);
            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");
            studentName =assessmentResponses.studentName;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }

            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in AssignmentResponse page", "Removed student is still displaying in selected group in AssignmentResponse page");
            }
            new LoginUsingLTI().ltiLogin("259_5");
            ReportUtil.log("Login as student3", "student logged successfully of different class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments page
            CustomAssert.assertTrue(assignments.noAssignmentEntry.isDisplayed(), "verify assignment entry", "Assigned assignment should not present  in assignment page", "Assigned assignment is displaying in assignment page");

        } catch (Exception e) {
            Assert.fail("Exception in TC moveStudentToDifferentClassSection of class AssignAssignmentToGroup",e);
        }
    }

    @Test(priority = 10,enabled = true)
    public void groupFilterOnAssignmentResponsePage(){
        WebDriver driver=Driver.getWebDriver();
        try {

            new LoginUsingLTI().ltiLogin("261_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("261_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("261_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("261_4");
            ReportUtil.log("Login as student4", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("261_5");
            ReportUtil.log("Login as student5", "student logged successfully of same class section", "info");

            new LoginUsingLTI().ltiLogin("261");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=new AddGroup().addGroup(261); //add group
            System.out.println("groupName:" + groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 3);//add first three student to group
            AddGroup.selectGroup(2);//select group
            AddGroup.addStudentToParticularGroup(2, 2, 5);//add first three student to group

            new Assignment().assignAssignmentToGroup(261);

            Thread.sleep(180000); //wait till due date got expired
            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link

            try {
                assessmentResponses.resumeGrading_button.click(); //click on the resume grading link
            } catch (Exception e) {
            }

            assignmentResponsesPage.getRefreshButton().click(); //click on the refresh button
            assessmentResponses.getButton_ReleaseGradeForAll().click(); //release grade

            AddGroup.selectGroupInAssignmentResponsePage(1);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(3);//select  group
            Thread.sleep(4000);
            WebDriverUtil.waitTillVisibilityOfElement(assessmentResponses.studentName.get(0), 50);
            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("c, month");
            groupOneStudent.add("d, month");
            groupOneStudent.add("e, month");

            studentName =assessmentResponses.studentName;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }

            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in AssignmentResponse page", "Removed student is still displaying in selected group in AssignmentResponse page");
            }

            new Assignment().enterGradeOnParticularQuestion(0,0,"0.5");
            new CommentOnPost().commentOnPostWithStaticText("ABC");

            new LoginUsingLTI().ltiLogin("261_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score=assignments.getScore().getText();
            expected = "Score (0.5/1)";
            CustomAssert.assertEquals(score, expected, "Verify score", "score is getting updated", "score  is not " + expected + "");
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertTrue(courseStreamPage.postComment_content.get(0).getText().trim().contains("ABC"), "Comment is not getting updated");

            new LoginUsingLTI().ltiLogin("261");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link
            AddGroup.selectGroupInAssignmentResponsePage(1);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(4);//select  group
            new Assignment().enterGradeOnParticularQuestion(2,0,"0.6");
            new CommentOnPost().commentOnPostWithStaticText("CDE");

            new LoginUsingLTI().ltiLogin("261_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            score=assignments.getScore().getText();
            expected = "Score (0.6/1)";
            CustomAssert.assertEquals(score, expected, "Verify score", "score is getting updated", "score  is not " + expected + "");
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertTrue(courseStreamPage.postComment_content.get(0).getText().trim().contains("ABC"), "Comment is not getting updated");
            Assert.assertTrue(courseStreamPage.postComment_content.get(1).getText().trim().contains("CDE"), "Comment is not getting updated");


        } catch (Exception e) {
            Assert.fail("Exception in TC groupFilterOnAssignmentResponsePage of class AssignAssignmentToGroup",e);
        }
    }

    @Test(priority = 11,enabled = true)
    public void emptyGroup(){
        WebDriver driver=Driver.getWebDriver();
        try {

            new LoginUsingLTI().ltiLogin("267_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("267_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("267");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=new AddGroup().addGroup(267); //add group
            System.out.println("groupName:" + groupName);
            new Assignment().assignAssignmentToGroup(267);
            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link

            actual=assessmentResponses.noDataMessage_label.getText().trim();
            expected = "No data available";
            CustomAssert.assertEquals(actual, expected, "Verify No data available message", "No data available message is displaying for empty group", "No data available message is not displaying for empty group");
        } catch (Exception e) {
            Assert.fail("Exception in TC emptyGroup of class AssignAssignmentToGroup",e);
        }
    }

    @Test(priority = 12,enabled = true)
    public void allStudentSubmittedDWAssignment(){
        WebDriver driver=Driver.getWebDriver();
        try {
            new LoginUsingLTI().ltiLogin("268_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("268_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("268");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=AddGroup.addGroup(268); //add group
            System.out.println("groupName:"+groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 2);//add first three student to group*/
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithGroup(268);

            new LoginUsingLTI().ltiLogin("268_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            new DiscussionWidget().addPerspectiveForDWAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));

            new LoginUsingLTI().ltiLogin("268_2");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            new DiscussionWidget().addPerspectiveForDWAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));

            Thread.sleep(120000); //wait till due date got expired

            new LoginUsingLTI().ltiLogin("268");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link
            Thread.sleep(4000);
            AddGroup.selectGroupInAssignmentResponsePage(1);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(3);//select  group*/
/*
            if (System.getProperty("UCHAR") == null) {
                groupName =groupName+LoginUsingLTI.appendChar;
            } else {
                groupName =groupName+System.getProperty("UCHAR");
            }*/
            actual=assessmentResponses.groupLink.getText().trim();
            expected = "View Group Response ("+groupName+")";
            CustomAssert.assertEquals(actual, expected, "Verify View Group Response Link", "New link View Group Response<Group name> should be displayed beside the assignment name button(D1)", "New link View Group Response<Group name> is not displaying beside the assignment name button(D1)");


        } catch (Exception e) {
            Assert.fail("Exception in TC allStudentSubmittedDWAssignment of class AssignAssignmentToGroup",e);
        }
    }


    @Test(priority = 13,enabled = true)
    public void studentHaveNotSubmittedDWAssignment(){
        WebDriver driver=Driver.getWebDriver();
        try {
            new LoginUsingLTI().ltiLogin("269_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("269_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("269");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=AddGroup.addGroup(269); //add group
            System.out.println("groupName:"+groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 2);//add first three student to group
            new LoginUsingLTI().ltiLogin("269");
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithGroup(269);

            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link
            Thread.sleep(4000);
            AddGroup.selectGroupInAssignmentResponsePage(1);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(3);//select  group

            boolean found=new BooleanValue().presenceOfElement(269,assessmentResponses.groupLinks);
            CustomAssert.assertFalse(found,"Verify View Group Response Link", "New link View Group Response<Group name> should not be displayed beside the assignment name button(D1)", "New link View Group Response<Group name> is displaying beside the assignment name button(D1)");

        } catch (Exception e) {
            Assert.fail("Exception in TC studentHaveNotSubmittedDWAssignment of class AssignAssignmentToGroup",e);
        }
    }

    @Test(priority = 14,enabled = true)
    public void perspectiveOfSelectedGroup(){
        WebDriver driver=Driver.getWebDriver();
        try {
            String discussionAssignment = ReadTestData.readDataByTagName("", "discussionAssignment", "270");

            new LoginUsingLTI().ltiLogin("270_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("270_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("270_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("270_4");
            ReportUtil.log("Login as student4", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("270");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=AddGroup.addGroup(270); //add group
            System.out.println("groupName:"+groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 2);//add first three student to group
            AddGroup.selectGroup(2);//select group
            AddGroup.addStudentToParticularGroup(2, 2, 4);//add first three student to group



            new LoginUsingLTI().ltiLogin("270");
            new Assignment().createDiscussionAssignment(270);
            new Assignment().assignDiscussionAssignmentToGroup(270);
            new LoginUsingLTI().ltiLogin("270_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            Thread.sleep(4000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));
            new DiscussionWidget().commentOnPerspectives(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA), 0);

            new LoginUsingLTI().ltiLogin("270_2");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            Thread.sleep(4000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));
            new DiscussionWidget().commentOnPerspectives(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA), 1);

            new LoginUsingLTI().ltiLogin("270_3");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            Thread.sleep(4000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));
            new DiscussionWidget().commentOnPerspectives(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA), 0);

            new LoginUsingLTI().ltiLogin("270_4");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            Thread.sleep(4000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));
            new DiscussionWidget().commentOnPerspectives(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA), 1);

            new LoginUsingLTI().ltiLogin("270");
            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link
            Thread.sleep(4000);
            AddGroup.selectGroupInAssignmentResponsePage(1);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(3);//select  group
            assessmentResponses.groupLink.click();
            Thread.sleep(4000);

            actual=perspective.gradeBook_title.get(0).getText().trim();
            expected =discussionAssignment ;
            CustomAssert.assertEquals(actual, expected, "Verify perspective title", "perspective title  is  " + expected + "", "perspective title  is not " + expected + "");

            actual=perspective.groupName.getText().trim();
            expected ="Group name: one"+LoginUsingLTI.appendChar ;
            CustomAssert.assertEquals(actual, expected, "Verify group title", "group title  is  " + expected + "", "group title  is not " + expected + "");


            actualSize=perspective.lsCommentEntry.size();
            expectedSize =2;
            CustomAssert.assertEquals(actualSize, expectedSize, "Verify Perspective count", "Perspective count should match with the selected group students perspectives", "Perspective count is not matching with the selected group students perspectives");

            actualSize=perspective.allComments.size();
            expectedSize =2;
            CustomAssert.assertEquals(actualSize, expectedSize, "Verify Perspective comment", "Perspective comment should match with the selected group students perspectives", "Perspective comment is not matching with the selected group students perspectives");

            actual=perspective.perspective_dwComment_count.getText().trim();
            expected ="Group comments: 4";
            CustomAssert.assertEquals(actual, expected, "Verify Group comments", "Group comments  is  " + expected + "", "Group comments is not " + expected + "");

            actual=perspective.perspective_word_count.getText().trim();
            expected ="Group Perspective word count: 2";
            CustomAssert.assertEquals(actual, expected, "Verify Group Perspective word count", "Group Perspective word count  is  " + expected + "", "Group Perspective word count  is not " + expected + "");

            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link
            Thread.sleep(4000);
            AddGroup.selectGroupInAssignmentResponsePage(1);//click on the group dropdown
            Thread.sleep(1000);
            AddGroup.selectGroupInAssignmentResponsePage(4);//select  second group
            assessmentResponses.groupLink.click();
            Thread.sleep(3000);
            actual=perspective.gradeBook_title.get(0).getText().trim();
            expected =discussionAssignment ;
            CustomAssert.assertEquals(actual, expected, "Verify perspective title", "perspective title  is  " + expected + "", "perspective title  is not " + expected + "");

            actual=perspective.groupName.getText().trim();
            expected ="Group name: two"+LoginUsingLTI.appendChar ;
            CustomAssert.assertEquals(actual, expected, "Verify group title", "group title  is  " + expected + "", "group title  is not " + expected + "");


            actualSize=perspective.lsCommentEntry.size();
            expectedSize =2;
            CustomAssert.assertEquals(actualSize, expectedSize, "Verify Perspective count", "Perspective count should match with the selected group students perspectives", "Perspective count is not matching with the selected group students perspectives");

            actualSize=perspective.allComments.size();
            expectedSize =2;
            CustomAssert.assertEquals(actualSize, expectedSize, "Verify Perspective comment", "Perspective comment should match with the selected group students perspectives", "Perspective comment is not matching with the selected group students perspectives");

            actual=perspective.perspective_dwComment_count.getText().trim();
            expected ="Group comments: 4";
            CustomAssert.assertEquals(actual, expected, "Verify Group comments", "Group comments  is  " + expected + "", "Group comments is not " + expected + "");

            actual=perspective.perspective_word_count.getText().trim();
            expected ="Group Perspective word count: 2";
            CustomAssert.assertEquals(actual, expected, "Verify Group Perspective word count", "Group Perspective word count  is  " + expected + "", "Group Perspective word count  is not " + expected + "");

            perspective.view_other_perspective_link.click(); //click on the view other perspective link

            actualSize=perspective.hide_perspective.size();
            expectedSize =2;
            CustomAssert.assertEquals(actualSize, expectedSize, "Verify Perspective count", "Perspectives added by other non grouop students should be displayed ", "Perspectives added by other non grouop students is  displaying");
            perspective.view_other_perspective_link.click(); //click on hide non group perspective

            actual=perspective.view_other_perspective_link.getText().trim();
            expected ="View non group perspectives";
            CustomAssert.assertEquals(actual, expected, "Verify View non group perspectives link ", "View non group perspectives link should be replaced with “Hide non group perspectives”link", "View non group perspectives link is not replacing with“Hide non group perspectives”link");


        } catch (Exception e) {
            Assert.fail("Exception in TC perspectiveOfSelectedGroup of class AssignAssignmentToGroup",e);
        }
    }

    @Test(priority = 15,enabled = true)
    public void verifyForTotalScore(){
        WebDriver driver=Driver.getWebDriver();
        try {

            new LoginUsingLTI().ltiLogin("271_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("271_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("271_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("271_4");
            ReportUtil.log("Login as student4", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("271");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=AddGroup.addGroup(271); //add group
            System.out.println("groupName:"+groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 2);//add first three student to group
            AddGroup.selectGroup(2);//select group
            AddGroup.addStudentToParticularGroup(2, 2, 4);//add first three student to group

            new LoginUsingLTI().ltiLogin("271");
            new Assignment().createDiscussionAssignment(271);
            new Assignment().assignDiscussionAssignmentToGroup(271);
            new LoginUsingLTI().ltiLogin("271_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            Thread.sleep(4000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));
            new DiscussionWidget().commentOnPerspectives(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA), 0);

            new LoginUsingLTI().ltiLogin("271_2");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            Thread.sleep(4000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));
            new DiscussionWidget().commentOnPerspectives(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA), 1);

            new LoginUsingLTI().ltiLogin("271_3");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            Thread.sleep(4000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));
            new DiscussionWidget().commentOnPerspectives(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA), 0);

            new LoginUsingLTI().ltiLogin("271_4");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            Thread.sleep(4000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));
            new DiscussionWidget().commentOnPerspectives(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA), 1);

            new LoginUsingLTI().ltiLogin("271");
            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link
            Thread.sleep(4000);
            AddGroup.selectGroupInAssignmentResponsePage(1);//click on the group dropdown
            Thread.sleep(4000);
            AddGroup.selectGroupInAssignmentResponsePage(3);//select  group
            assessmentResponses.groupLink.click();

            actual=perspective.commentPost_count.getText().trim();
            expected ="4";
            CustomAssert.assertEquals(actual, expected, "Verify Group Perspective count", "Group Perspective  count  is  " + expected + "", "Group Perspective  count  is not " + expected + "");
            perspective.score_box.sendKeys("0");
            perspective.save_button.click();
            perspective.yes_link.click();
            switchToTab(1);
            assessmentResponses.refresh_button.click();
            Thread.sleep(5000);
            actual = assessmentResponses.score.get(2).getText().trim();
            expected = "0.0";
            CustomAssert.assertEquals(actual, expected, "Verify score", "score  is  " + expected + "", "score is not " + expected + "");

            actual = assessmentResponses.score.get(3).getText().trim();
            expected = "0.0";
            CustomAssert.assertEquals(actual, expected, "Verify score", "score  is  " + expected + "", "score is not " + expected + "");

            switchToTab(2);
            perspective.score_box.sendKeys("cmc");
            String redColor=verifyColor(perspective.score_box);
            System.out.println("redColor:"+redColor);

            actual = redColor;
            expected = "#ff0000";
            CustomAssert.assertEquals(actual, expected, "Verify Total score should be marked in red", "Total score should be marked in red", "Total score marked is not in red color");

            perspective.score_box.sendKeys("5");
            redColor=verifyColor(perspective.score_box);
            System.out.println("redColor:"+redColor);

            actual = redColor;
            expected = "#ff0000";
            CustomAssert.assertEquals(actual, expected, "Verify valid Total score ", "Total score should be marked in red", "Entered value should not be grater than total score");


            perspective.score_box.clear();
            perspective.score_box.sendKeys("1");
            perspective.save_button.click();
            perspective.yes_link.click();
            switchToTab(1);
            assessmentResponses.refresh_button.click();
            Thread.sleep(5000);
            actual = assessmentResponses.score.get(2).getText().trim();
            expected = "1.0";
            CustomAssert.assertEquals(actual, expected, "Verify score", "score  is  " + expected + "", "score is not " + expected + "");

            actual = assessmentResponses.score.get(3).getText().trim();
            expected = "1.0";
            CustomAssert.assertEquals(actual, expected, "Verify score", "score  is  " + expected + "", "score is not " + expected + "");

            switchToTab(2);
            perspective.score_box.clear();
            perspective.score_box.sendKeys("2");
            perspective.save_button.click();

            actual = group.notificationMessage.getText().trim();
            expected = "Entering a group grade will replace any existing individual grades. Do you want to continue?     Yes   |  No";
            CustomAssert.assertEquals(actual, expected, "Verify Notification message", "Notification message is  " + expected + "", "Notification message is not " + expected + "");

            perspective.no_link.click(); //click on NO link
            switchToTab(1);
            assessmentResponses.refresh_button.click();
            Thread.sleep(5000);
            actual = assessmentResponses.score.get(2).getText().trim();
            expected = "1.0";
            CustomAssert.assertEquals(actual, expected, "Verify score", "score  is  " + expected + "", "score is not " + expected + "");

            actual = assessmentResponses.score.get(3).getText().trim();
            expected = "1.0";
            CustomAssert.assertEquals(actual, expected, "Verify score", "score  is  " + expected + "", "score is not " + expected + "");

            switchToTab(2);
            perspective.score_box.clear();
            perspective.score_box.sendKeys("2");
            perspective.save_button.click();
            actual = assessmentResponses.save_successfully_label.getText().trim();
            System.out.println("actual:"+actual);
            expected = "Saved successfully.";
            CustomAssert.assertEquals(actual, expected, "Verify Saved successfully. Label", "Label Saved successfully.  is  " + expected + "", "Label Saved successfully.is not " + expected + "");

            perspective.yes_link.click();
            switchToTab(1);
            assessmentResponses.refresh_button.click();
            Thread.sleep(5000);
            actual = assessmentResponses.score.get(2).getText().trim();
            expected = "2.0";
            CustomAssert.assertEquals(actual, expected, "Verify score", "score  is  " + expected + "", "score is not " + expected + "");

            actual = assessmentResponses.score.get(3).getText().trim();
            expected = "2.0";
            CustomAssert.assertEquals(actual, expected, "Verify score", "score  is  " + expected + "", "score is not " + expected + "");

            new LoginUsingLTI().ltiLogin("271_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");
            new Assignment().verifyClassAssignmentStatus(272, "Submitted");
            new LoginUsingLTI().ltiLogin("271");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link
            Thread.sleep(4000);
            assessmentResponses.getButton_ReleaseGradeForAll().click(); //release grade
            new LoginUsingLTI().ltiLogin("271_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            String score=assignments.getScore().getText();
            expected = "Score (2/2)";
            CustomAssert.assertEquals(score, expected, "Verify score", "score is getting updated", "score  is not " + expected + "");

            new LoginUsingLTI().ltiLogin("271_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            score=assignments.getScore().getText();
            expected = "Score (0/2)";
            CustomAssert.assertEquals(score, expected, "Verify score", "score is getting updated", "score  is not " + expected + "");


        } catch (Exception e) {
            Assert.fail("Exception in TC verifyForTotalScore of class AssignAssignmentToGroup",e);
        }
    }

    @Test(priority = 16,enabled = true)
    public void studentNotSubmittedAndDueDateExpired(){
        WebDriver driver=Driver.getWebDriver();
        try {

            new LoginUsingLTI().ltiLogin("272_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("272_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("272_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("272");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=AddGroup.addGroup(272); //add group
            System.out.println("groupName:"+groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 3);//add first three student to group

            new LoginUsingLTI().ltiLogin("272");
            new Assignment().createDiscussionAssignment(272);
            new Assignment().assignDiscussionAssignmentToGroup(272);

            new LoginUsingLTI().ltiLogin("272_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            Thread.sleep(4000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));

            new LoginUsingLTI().ltiLogin("272_2");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            Thread.sleep(4000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));
            Thread.sleep(120000);

            new LoginUsingLTI().ltiLogin("272");
            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link
            Thread.sleep(4000);
            AddGroup.selectGroupInAssignmentResponsePage(1);//click on the group dropdown
            Thread.sleep(4000);
            AddGroup.selectGroupInAssignmentResponsePage(3);//select  group
            assessmentResponses.groupLink.click();

            perspective.score_box.sendKeys("2");
            perspective.save_button.click();
            perspective.yes_link.click();
            switchToTab(1);
            assessmentResponses.refresh_button.click();
            Thread.sleep(5000);
            actual = assessmentResponses.score.get(0).getText().trim();
            expected = "2.0";
            CustomAssert.assertEquals(actual, expected, "Verify score", "score  is  " + expected + "", "score is not " + expected + "");

            actual = assessmentResponses.score.get(1).getText().trim();
            expected = "2.0";
            CustomAssert.assertEquals(actual, expected, "Verify score", "score  is  " + expected + "", "score is not " + expected + "");

            boolean notAttemptedStudent=new BooleanValue().presenceOfElement(272,assessmentResponses.notAttemptedStudent);
            CustomAssert.assertTrue(notAttemptedStudent,"Verify score for not added perspective student","Student who has not added any perspectives should get the score as Zero","Student who has not added any perspectives is also getting updated score");

            assessmentResponses.getButton_ReleaseGradeForAll().click(); //release grade

            AddGroup.selectGroupInAssignmentResponsePage(1);//click on the group dropdown
            Thread.sleep(4000);
            AddGroup.selectGroupInAssignmentResponsePage(3);//select  group
            assessmentResponses.groupLink.click();
            actual=assessmentResponses.gradeBlockedMessage.getText().trim();
            expected="Group grading is not available as the grades for this assignment have been released to the class. You can still change individual student grades on the assignment response page.";
            CustomAssert.assertEquals(actual, expected, "Verify feedback grade block message", "feedback grade block message is displaying correct", "feedback grade block message  is not " + expected + "");

            new Navigator().NavigateTo("Groups");
            List<String>score = new ArrayList<String>();
            List<String> groupTwoStudent = new ArrayList<String>();
            groupTwoStudent.add("100%");
            groupTwoStudent.add("100%");
            groupTwoStudent.add("0%");

            List<WebElement> allScore =group.group_score;
            for (WebElement we : allScore) {
                System.out.println(we.getText());
                score.add(we.getText());
            }

            if (!score.equals(groupTwoStudent)) {
                CustomAssert.fail("Verify score for order", "scores are not in descending order");
            }

            group.scoreSorting_tooltip.click();
            Thread.sleep(3000);
            score = new ArrayList<String>();
            groupTwoStudent = new ArrayList<String>();
            groupTwoStudent.add("0%");
            groupTwoStudent.add("100%");
            groupTwoStudent.add("100%");

            allScore =group.groupScore_ascendingOrder;
            for (WebElement we : allScore) {
                System.out.println(we.getText());
                score.add(we.getText());
            }

            if (!score.equals(groupTwoStudent)) {
                CustomAssert.fail("Verify score for order", "scores are not in ascending  order");
            }
        } catch (Exception e) {
            Assert.fail("Exception in TC studentNotSubmittedAndDueDateExpired of class AssignAssignmentToGroup",e);
        }
    }

    @Test(priority = 17,enabled = true)
    public void assignGroupAndCombination(){
        WebDriver driver=Driver.getWebDriver();
        try {

            new LoginUsingLTI().ltiLogin("273_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("273_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("273_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("273");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=AddGroup.addGroup(273); //add group
            System.out.println("groupName:"+groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 2);//add first three student to group*/

            new LoginUsingLTI().ltiLogin("273");
            new Assignment().createDiscussionAssignment(273);
            new Assignment().assignDiscussionAssignmentToGroup(273);

            new LoginUsingLTI().ltiLogin("273_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            Thread.sleep(4000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));

            new LoginUsingLTI().ltiLogin("273_2");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            Thread.sleep(4000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));

            new LoginUsingLTI().ltiLogin("273_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            Thread.sleep(4000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));

            new LoginUsingLTI().ltiLogin("273");
            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link
            Thread.sleep(4000);
            new Assignment().enterGradeOnParticularQuestion(2,0,"2");
            AddGroup.selectGroupInAssignmentResponsePage(1);//click on the group dropdown
            Thread.sleep(4000);
            AddGroup.selectGroupInAssignmentResponsePage(3);//select  group
            assessmentResponses.groupLink.click();


            perspective.score_box.sendKeys("1");
            perspective.save_button.click();
            perspective.yes_link.click();
            switchToTab(1);
            assessmentResponses.refresh_button.click();
            Thread.sleep(5000);
            actual = assessmentResponses.score.get(0).getText().trim();
            expected = "1.0";
            CustomAssert.assertEquals(actual, expected, "Verify score", "score  is  " + expected + "", "score is not " + expected + "");

            actual = assessmentResponses.score.get(1).getText().trim();
            expected = "1.0";
            CustomAssert.assertEquals(actual, expected, "Verify score", "score  is  " + expected + "", "score is not " + expected + "");

            actual = assessmentResponses.score.get(2).getText().trim();
            expected = "2.0";
            CustomAssert.assertEquals(actual, expected, "Verify score for individual student", "individual student score  is  " + expected + "", "individual student score is not " + expected + "");


        } catch (Exception e) {
            Assert.fail("Exception in TC assignGroupAndCombination of class AssignAssignmentToGroup",e);
        }
    }

    @Test(priority = 18,enabled = true)
    public void assignGroupAndIndividualStudentOFSameGroup(){
        WebDriver driver=Driver.getWebDriver();
        try {

            new LoginUsingLTI().ltiLogin("274_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("274_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("274");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=AddGroup.addGroup(274); //add group
            System.out.println("groupName:"+groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 2);//add first three student to group

            new LoginUsingLTI().ltiLogin("274");
            new Assignment().createDiscussionAssignment(274);
            new Assignment().assignDiscussionAssignmentToGroup(274);

            new LoginUsingLTI().ltiLogin("274_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            Thread.sleep(4000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));

            new LoginUsingLTI().ltiLogin("274_2");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            Thread.sleep(4000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));


            new LoginUsingLTI().ltiLogin("274");
            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link
            Thread.sleep(4000);
            new Assignment().enterGradeOnParticularQuestion(1,0,"2");
            AddGroup.selectGroupInAssignmentResponsePage(1);//click on the group dropdown
            Thread.sleep(4000);
            AddGroup.selectGroupInAssignmentResponsePage(3);//select  group
            assessmentResponses.groupLink.click();

            perspective.score_box.sendKeys("1");
            perspective.save_button.click();
            perspective.yes_link.click();
            switchToTab(1);
            assessmentResponses.refresh_button.click();
            Thread.sleep(5000);
            actual = assessmentResponses.score.get(0).getText().trim();
            expected = "1.0";
            CustomAssert.assertEquals(actual, expected, "Verify score", "score  is  " + expected + "", "score is not " + expected + "");

            actual = assessmentResponses.score.get(1).getText().trim();
            expected = "1.0";
            CustomAssert.assertEquals(actual, expected, "Verify score", "score  is  " + expected + "", "score is not " + expected + "");


        } catch (Exception e) {
            Assert.fail("Exception in TC assignGroupAndIndividualStudentOFSameGroup of class AssignAssignmentToGroup",e);
        }
    }


    @Test(priority = 19,enabled = true)
    public void assignDWAssignmentToIndividual(){
        WebDriver driver=Driver.getWebDriver();
        try {

            new LoginUsingLTI().ltiLogin("275_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("275");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");

            new Assignment().createDiscussionAssignment(275);
            new Assignment().assignDiscussionAssignmentToGroup(275);

            new LoginUsingLTI().ltiLogin("275_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            Thread.sleep(4000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));

            new LoginUsingLTI().ltiLogin("275");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link
            Thread.sleep(4000);
            String redColor=verifyColor(assessmentResponses.group_dropdown.get(1));
            System.out.println("redColor:"+redColor);

            actual = redColor;
            expected = "#3d3d3d";
            CustomAssert.assertEquals(actual, expected, "Verify Group filter grayed out", "Group filter should be grayed out", "Group filter color is not in gray");

            boolean found=assessmentResponses.groupLink.isDisplayed();
            CustomAssert.assertFalse(found,"Verify View Group Response Link", "view groups responses\" link should not be displayed", "view grops responses link is still displaying");

        } catch (Exception e) {
            Assert.fail("Exception in TC assignDWAssignmentToIndividual of class AssignAssignmentToGroup",e);
        }
    }

    @Test(priority = 20,enabled = true)
    public void assignDWAssignmentToClassSection(){
        WebDriver driver=Driver.getWebDriver();
        try {

            new LoginUsingLTI().ltiLogin("278_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("278");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");

            new Assignment().createDiscussionAssignment(278);
            String groupName=AddGroup.addGroup(278); //add group
            System.out.println("groupName:"+groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 1);//add first three student to group
            new Assignment().assignDiscussionAssignmentToGroup(278);
            new LoginUsingLTI().ltiLogin("278_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.resourceName);
            Thread.sleep(4000);
            new DiscussionWidget().addPerspectiveOfDiscussionAssignment(StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));

            new LoginUsingLTI().ltiLogin("278");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            new Navigator().NavigateTo("Class Assignments"); //Navigate to Class Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());//click on the view response link
            Thread.sleep(4000);

            // boolean found=new BooleanValue().presenceOfElement(269,assessmentResponses.groupLinks);
            // CustomAssert.assertFalse(found,"Verify View Group Response Link", "view groups responses\" link should not be displayed", "view grops responses link is still displaying");

            boolean found=assessmentResponses.groupLink.isDisplayed();
            CustomAssert.assertFalse(found,"Verify View Group Response Link", "view groups responses\" link should not be displayed", "view grops responses link is still displaying");

        } catch (Exception e) {
            Assert.fail("Exception in TC assignDWAssignmentToClassSection of class AssignAssignmentToGroup",e);
        }
    }




    @Test(priority = 21,enabled = true)
    public void assignAssignmentWithDifferent(){
        WebDriver driver=Driver.getWebDriver();
        try {


            new LoginUsingLTI().ltiLogin("279_1");
            ReportUtil.log("Login as student1", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("279_2");
            ReportUtil.log("Login as student2", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("279_3");
            ReportUtil.log("Login as student3", "student logged successfully of same class section", "info");
            new LoginUsingLTI().ltiLogin("279_5");
            ReportUtil.log("Login as student5", "student logged successfully of different class section", "info");
            new LoginUsingLTI().ltiLogin("279_6");
            ReportUtil.log("Login as student6", "student logged successfully of different class section", "info");
            new LoginUsingLTI().ltiLogin("279");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            String groupName=AddGroup.addGroup(279); //add group
            System.out.println("groupName:"+groupName);
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 2);//add first three student to group
            AddGroup.selectGroup(2);//select group
            AddGroup.addStudentToParticularGroup(2, 1, 3);//add first three student to group
            new LoginUsingLTI().ltiLogin("280");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            new Navigator().NavigateTo("My Assignments");
            new Navigator().navigateToTab("Question Banks");
            AddGroup.addGroup(280); //add group
            AddGroup.selectGroup(1);//select group
            AddGroup.addStudentToParticularGroup(1, 0, 2);//add first three student to group
            AddGroup.selectGroup(2);//select group
            AddGroup.addStudentToParticularGroup(2, 0, 2);//add first three student to group



            new LoginUsingLTI().ltiLogin("280");
            ReportUtil.log("Login as instructor", "Instructor logged successfully for assign Assignment To  Different Group", "info");
            new Navigator().NavigateTo("My Assignments");
            new Navigator().navigateToTab("Question Banks");
            myQuestionBank.assignThis_link.get(0).click();
            myQuestionBank.differentDate_link.click();
            List<WebElement> allClassSection = driver.findElements(By.cssSelector("li[class='bit-box']"));
            for (WebElement classSection : allClassSection) {
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElements(By.className("closebutton")).get(driver.findElements(By.className("closebutton")).size() - 2));//click on close symbol
                String assignToField = new TextFetch().textfetchbyclass("holder");
                if (assignToField.length() == 0) {
                    break;
                }
            }

            myQuestionBank.shareTextBox.sendKeys("three");
            myQuestionBank.groupName_suggestion.click();

            myQuestionBank.shareTextBox.sendKeys("four");
            myQuestionBank.groupName_suggestion.click();

            myQuestionBank.assignThis_TextBox.get(1).sendKeys("BiologyTitle17");
            myQuestionBank.groupName_suggestion.click();

            myQuestionBank.assignThis_TextBox.get(2).sendKeys("one");
            myQuestionBank.groupName_suggestion.click();

            myQuestionBank.assignThis_TextBox.get(2).sendKeys("two");
            myQuestionBank.groupName_suggestion.click();

            myQuestionBank.shareTextBox.sendKeys("one");
            actual= myQuestionBank.noResultMessage.getText().trim();
            expected="No results found";
            CustomAssert.assertEquals(actual, expected, "Verify error message in assign this popup", "Error message is  " + expected + "", "Error message is not " + expected + "");

            myQuestionBank.assignThis_TextBox.get(1).sendKeys("three");
            actual=myQuestionBank.noResultMessage.getText().trim();
            expected="No results found";
            CustomAssert.assertEquals(actual, expected, "Verify error message in assign this popup", "Error message is  " + expected + "", "Error message is not " + expected + "");

            for(int i = 0; i<4 ;i++) {
                driver.findElements(By.cssSelector("input[class='input-filed due-date-classwise hasDatepicker']")).get(i).click();
                driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
                driver.findElement(By.linkText("1")).click();
                Thread.sleep(1000);
                driver.findElements(By.xpath("//input[@id='due-time']")).get(i).click();
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElements(By.xpath("//li[text()='12:30 PM']")).get(i));
                Thread.sleep(1000);

            }

            WebDriverUtil.clickOnElementUsingJavascript(myQuestionBank.gradable_selected);
            WebDriverUtil.clickOnElementUsingJavascript(myQuestionBank.assignPopUp);

            new Navigator().NavigateTo("Course Stream"); //Navigate to Course Stream
            size=courseStreamPage.assignmentName.size();
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.visualIndicatorIcon.get(0)); //click on the visual indicator icon

            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("e, month");
            groupOneStudent.add("f, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");

            groupOneStudent.add("e, month");
            groupOneStudent.add("f, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");

            studentName =courseStreamPage.group_users;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }
            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in CS page", "After removing Student, student still displaying in course steam group list");
            }

            classSectionDropDown.defaultClassSection.click(); //click on the class section dropdown
            classSectionDropDown.classSectionName_list.get(2).click(); //switch to different class section

            new Navigator().NavigateTo("Course Stream"); //Navigate to Course Stream
            size=courseStreamPage.assignmentName.size();
            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.visualIndicatorIcon.get(0)); //click on the visual indicator icon

            student = new ArrayList<String>();
            groupOneStudent = new ArrayList<String>();
            groupOneStudent.add("b, month");
            groupOneStudent.add("c, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");

            groupOneStudent.add("a, month");
            groupOneStudent.add("b, month");
            groupOneStudent.add("family, givenname");
            groupOneStudent.add("Instructor");

            studentName =courseStreamPage.group_users;
            for (WebElement we : studentName) {
                System.out.println(we.getText());
                student.add(we.getText());
            }
            if (!student.equals(groupOneStudent)) {
                CustomAssert.fail("Verify student name for selected group in CS page", "After removing Student, student still displaying in course steam group list");
            }

        } catch (Exception e) {
            Assert.fail("Exception in TC assignAssignmentWithDifferent of class AssignAssignmentToGroup",e);
        }
    }
    public void switchToTab(int tabIndex) throws Exception {
        try {
            WebDriver driver=Driver.getWebDriver();
            driver.findElements(By.className("tab_title")).get(tabIndex).click();
        } catch (Exception e) {
            Assert.fail("Exception in method switchToTab",e);
        }
    }
    public static String verifyColor(WebElement element){
        String color=element.getCssValue("border-top-color");
        Color col= Color.fromString(color);
        String hexValue=col.asHex();
        System.out.println(col.asHex());
        return hexValue;
    }



}
