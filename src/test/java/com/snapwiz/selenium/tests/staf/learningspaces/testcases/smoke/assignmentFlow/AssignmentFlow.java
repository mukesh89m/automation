package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.assignmentFlow;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.DiagnosticTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.PerformanceSummary;
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

/**
 * Created by root on 7/21/16.
 */
public class AssignmentFlow extends Driver {
    @Test(priority = 1, enabled = true)
    public void gradableQuestionAssignmentWithGradeReleaseOption1ExtendAfterDueDateDisabled() {
        try {
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate adding a new student after all students submit assignment", "Validate adding a new student after all students submit assignment with grade release policy 1", "info");

            /*Pre condition - "1. Class section should have 2 students S1, S2
            2. Instructor should have assigned gradable question assignment to the class section with grade release option 1 and extend after due date disabled
            3. Due date should not have expired"*/

            String instDataIndex = "15";
            String studDataIndex1 = "15_1";
            String studDataIndex2 = "15_2";
            String studDataIndex3 = "15_3";

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", instDataIndex);

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

            new LoginUsingLTI().ltiLogin(instDataIndex);
            new LoginUsingLTI().ltiLogin(studDataIndex1);
            new LoginUsingLTI().ltiLogin(studDataIndex2);

            new Assignment().create(Integer.parseInt(instDataIndex));//Create an assignment

            new LoginUsingLTI().ltiLogin(instDataIndex);//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "", instDataIndex);//till save policy*//**//*
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(Integer.parseInt(instDataIndex));//assign to student


            /*Row No - 15 : "1. Login as student S1
            2. Navigate to Assignments page
            3. Click on the assignment name
            4. Attempt all the questions and finish the assignment"*/

            //Expected - # Student S1 should be able to finish the assignment
            new LoginUsingLTI().ltiLogin(studDataIndex1);//login as student 1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

            /*Row No - 16 : "5. Login as student S2
            6. Navigate to Assignments page
            7. Click on the assignment name
            8. Attempt all the questions and finish the assignment"*/

            //Expected - # Student S2 should be able to finish the assignment
            new LoginUsingLTI().ltiLogin(studDataIndex2);//login as student 2
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

            /*Row No - 17 : "9. Login as instructor
            10. Navigate to Class assignments page from main navigator"*/
            //Expected - # Instructor should be able to navigate to Class Assignments page

            new LoginUsingLTI().ltiLogin(instDataIndex);//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            if (!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()) {
                CustomAssert.fail("Validate Class assignment page", "Class assignment page is not opened.");
            }

            //Row No - 18 : 11. Observe the Assignment card entry details
            //Expected - 1# View Student Response link should be displayed
            try {
                currentAssignments.getViewGrade_link().click();//click on view student responses
            } catch (Exception e) {
                CustomAssert.fail("Validate View Student Response link", "View Student Response link is not displayed");
            }

            new Navigator().NavigateTo("Class Assignments");
            //Expected - 2 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 3 : The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 4 : ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 5 : Assignment details and Unassign Assignment links should not be displayed
            boolean something = false;
            try {
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.assignmentDetailsLink.click();
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
            } catch (Exception e) {
                something = true;
            }

            if (something == false) {
                CustomAssert.fail("Validate Assignment details and Un-assign assignment links", "Assignment details and Un-assign assignment links is displayed");
            }


            //Expected - 6 : Try it link should be displayed
            CustomAssert.assertEquals(myQuestionBank.tryITLink.isDisplayed(), true, "Validate 'Try it' button", "'Try it' button is displayed", "'Try it' button is not displayed");


            //Row No - 24 : 12. Click on View Response link
            currentAssignments.getViewGrade_link().click();//click on view student responses

            //Expected - # 1Instructor should be navigated to assignment response page
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(), "Assignment Responses", "Validate assignment response page", "Instructor is navigated to assignment response page", "Instructor is not navigated to assignment response page");

            //Expected - 2 : # Extend due date link should not be displayed
            if (assignmentResponsesPage.extendDueDateLabelList.size() != 0) {
                CustomAssert.fail("Validate Extend due date link", "Extend due date link is displayed");
            }
            //Expected - 3 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.availableForStudents_status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");


            //Expected - 3 : # The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.availableForStudents_status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 4 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 5 : #"Release Grade for All" link should be greyed out
            if (!assignmentResponsesPage.gradeBook_Box.isDisplayed()) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link is not greyed out");
            }

            //Expected - 6 : # "Graded" should not be displayed for grade release link


            /*ADDITION OF NEW STUDENT TO THE CLASS SECTION*/


            //Row No - 31 : 13. Add a new student S3 to the class section
            //Expected - # Addition of new student to the class section should be successful
            new LoginUsingLTI().ltiLogin(studDataIndex3);
            String givenName = ReadTestData.readDataByTagName("", "givenname", studDataIndex3);

            if (!dashboard.getUserName().getText().trim().contains(givenName)) {
                CustomAssert.fail("Validate Addition of new student", "Addition of new student to the class section is not successful");
            }


            /*Row No - 32 : "14. Login as instructor
            15. Navigate to Class assignments page from main navigator"*/
            //Expected - # Instructor should be able to navigate to Class Assignments page
            new LoginUsingLTI().ltiLogin(instDataIndex);
            new Navigator().NavigateTo("Class Assignments");
            if (!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()) {
                CustomAssert.fail("Validate Class assignment page", "Class assignment page is not opened.");
            }


            //Row No - 33 : 11. Observe the Assignment card entry details
            //Expected - 1# View Student Response link should be displayed
            try {
                currentAssignments.getViewGrade_link().click();//click on view student responses
            } catch (Exception e) {
                CustomAssert.fail("Validate View Student Response link", "View Student Response link is not displayed");
            }


            //Expected - 2 : Assignment details and Un-assign assignment links should be displayed
            try {
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.assignmentDetailsLink.click();
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
                new Navigator().NavigateTo("Class Assignments");
            } catch (Exception e) {
                CustomAssert.fail("Validate Assignment details and Un-assign assignment links", "Assignment details and Un-assign assignment links is not displayed");
            }


            //Expected - 3 : Try it link should be displayed
            CustomAssert.assertEquals(myQuestionBank.tryITLink.isDisplayed(), true, "Validate 'Try it' button", "'Try it' button is displayed", "'Try it' button is not displayed");

            //Expected - 4 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 5 : # The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");

            //Expected - 6 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Row No - 24 : 17. Click on View Response link

            currentAssignments.getViewGrade_link().click();//click on view student responses

            //Expected - 1: # Instructor should be navigated to assignment response page
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(), "Assignment Responses", "Validate assignment response page", "Instructor is navigated to assignment response page", "Instructor is not navigated to assignment response page");

            //Expected - 2 : # Extend due date link should be displayed
            if (!assignmentResponsesPage.extendDueDateLabel.isDisplayed()) {
                CustomAssert.fail("Validate Extend due date link", "Extend due date link is not displayed");
            }
            //Expected - 3 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.availableForStudents_status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 4 : Newly added student name should be displayed in the response section
            if (!currentAssignments.enterGrade_mouseOver.get(0).getText().trim().contains(givenName)) {
                CustomAssert.fail("Validate Newly added student name", "Newly added student name is not displayed in the response section");
            }


            //Expected - 5 : # The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.availableForStudents_status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 6 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 7 : #"Release Grade for All" link should be greyed out
            if (!assignmentResponsesPage.gradeBook_Box.isDisplayed()) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link is not greyed out");
            }

            //Expected - 8 : # "Graded" should not be displayed for grade release link




             /*AFTER DUE DATE IS OVER*/

            int instDataIndexNumber = 16;
            String studDataIndexNumber = "16_1";
            assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "" + instDataIndexNumber);
            new Assignment().create(instDataIndexNumber);//Create an assignment
            new LoginUsingLTI().ltiLogin("" + studDataIndexNumber);//login as a student
            new LoginUsingLTI().ltiLogin("" + instDataIndexNumber);//login as instructor

            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "", "" + instDataIndexNumber);//till save policy
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(instDataIndexNumber);//assign to student
            Thread.sleep(300000);

            /*Row No - 47 : "18. Login as instructor
            19. Navigate to Class assignments page from main navigator"*/
            //Expected - # Instructor should be able to navigate to Class Assignments page

            new LoginUsingLTI().ltiLogin("" + instDataIndexNumber);//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            Thread.sleep(150000);

            if (!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()) {
                CustomAssert.fail("Validate Class assignment page", "Class assignment page is not opened.");
            }

            //Row No - 48 : 20. Observe the Assignment card entry details
            //Expected - 1# View Student Response link should be displayed
            try {
                WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrades_link());//click on view student responses

            } catch (Exception e) {
                CustomAssert.fail("Validate View Student Response link", "View Student Response link is not displayed");
            }

            //Expected - 2 : # Class Status should not be displayed as “Available for Students”
            new Navigator().NavigateTo("Class Assignments");
            if (driver.findElements(By.className("ls-assignment-status-awaiting-submissions")).size() != 0) {
                CustomAssert.fail("Validate “Available for Students” status", "Status of class is not displayed as 'Available for Students'");
            }

            //Expected - 3 : # Class Status should be displayed as “Graded”
            CustomAssert.assertEquals(currentAssignments.gradedStatus.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 4 : ”No of Students” vs  “Score Range” bar graph should be displayed
            if (!assignments.assignmentPerformanceGraphICon.isDisplayed()) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is not displayed");
            }

            //Expected - 5 : Assignment details and Unassign Assignment links should not be displayed
            something = false;
            try {
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.assignmentDetailsLink.click();
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
            } catch (Exception e) {
                something = true;
            }

            if (something == false) {
                CustomAssert.fail("Validate Assignment details and Un-assign assignment links", "Assignment details and Un-assign assignment links is displayed");
            }


            //Expected - 6 : Try it link should be displayed
            CustomAssert.assertEquals(myQuestionBank.tryITLink.isDisplayed(), true, "Validate 'Try it' button", "'Try it' button is displayed", "'Try it' button is not displayed");


            //Row No - 54 : 21. Click on View Grades link
            //Expected - 1: # Instructor should be navigated to assignment response page
            currentAssignments.getViewGrade_link().click();//click on view student responses
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(), "Assignment Responses", "Validate assignment response page", "Instructor is navigated to assignment response page", "Instructor is not navigated to assignment response page");

            //Expected - 2 : # Extend due date link should not be displayed
            if (assignmentResponsesPage.extendDueDateLabelList.size() != 0) {
                CustomAssert.fail("Validate Extend due date link", "Extend due date link is displayed");
            }

            //Expected - 3 : # Class Status should not be displayed as “Available for Students”
            if (driver.findElements(By.className("ls-assignment-status-awaiting-submissions")).size() != 0) {
                CustomAssert.fail("Validate “Available for Students” status", "Status of class is displayed as 'Available for Students'");
            }

            //Expected - 4 : # # The status of the assignment should be changed to "Graded"
            CustomAssert.assertNotEquals(currentAssignments.gradedStatus.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 5 : # ”No of Students” vs  “Score Range” bar graph should be displayed
            System.out.println("222 : " + assignments.assignmentPerformanceGraphICon.isDisplayed());
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() == 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is not displayed");
            }

            //Expected - 6 : #"Release Grade for All" link should not be displayed
            if (driver.findElements(By.xpath("//div[@title='Release Grade for All']")).size() != 0) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link should is displayed");
            }

            //Expected - 7 : # "Grades Released" link should be displayed
            //Expected - 8 : # "Grades Released" link should be greyed out
            if (!assignmentResponsesPage.label_gradesReleased.isDisplayed()) {
                CustomAssert.fail("Validate 'Grades Released' link", "\"Grades Released\" link is not displayed");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'gradableQuestionAssignmentWithGradeReleaseOption1ExtendAfterDueDateDisabled' in the class 'AssignmentFlow'", e);
        }
    }


    //This below method should work when the Bug logged: 23888 is resolved
    @Test(priority = 2, enabled = true)
    public void gradableQuestionAssignmentHavingNoManuallyGradedWithGradeReleaseOption3ExtendAfterDueDateDisabled() {
        try {
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate gradableQuestionAssignment", "Validate gradable Question Assignment having no manually graded with Grade Release Option 3 Extend After Due Date Disabled", "info");

            /*Pre condition - "1. Class section should have 2 students S1, S2
            2. Instructor should have assigned gradable question assignment to the class section with grade release option 1 and extend after due date disabled
            3. Due date should not have expired"*/

            String instDataIndex = "63";
            String studDataIndex1 = "63_1";
            String studDataIndex2 = "63_2";
            String studDataIndex3 = "63_3";

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", instDataIndex);

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

            new LoginUsingLTI().ltiLogin(instDataIndex);
            new LoginUsingLTI().ltiLogin(studDataIndex1);
            new LoginUsingLTI().ltiLogin(studDataIndex2);

            new Assignment().create(Integer.parseInt(instDataIndex));//Create an assignment

            new LoginUsingLTI().ltiLogin(instDataIndex);//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "", instDataIndex);//till save policy
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(Integer.parseInt(instDataIndex));//assign to student

            /*Row No - 15 : "1. Login as student S1
            2. Navigate to Assignments page
            3. Click on the assignment name
            4. Attempt all the questions and finish the assignment"*/

            //Expected - # Student S1 should be able to finish the assignment
            new LoginUsingLTI().ltiLogin(studDataIndex1);//login as student 1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

            /*Row No - 16 : "5. Login as student S2
            6. Navigate to Assignments page
            7. Click on the assignment name
            8. Attempt all the questions and finish the assignment"*/

            //Expected - # Student S2 should be able to finish the assignment
            new LoginUsingLTI().ltiLogin(studDataIndex2);//login as student 2
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

            /*Row No - 17 : "9. Login as instructor
            10. Navigate to Class assignments page from main navigator"*/
            //Expected - # Instructor should be able to navigate to Class Assignments page

            new LoginUsingLTI().ltiLogin(instDataIndex);//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            if (!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()) {
                CustomAssert.fail("Validate Class assignment page", "Class assignment page is not opened.");
            }

            //Row No - 18 : 11. Observe the Assignment card entry details
            //Expected - 1# View Student Response link should be displayed
            try {
                currentAssignments.getViewGrade_link().click();//click on view student responses
            } catch (Exception e) {
                CustomAssert.fail("Validate View Student Response link", "View Student Response link is not displayed");
            }

            new Navigator().NavigateTo("Class Assignments");
            //Expected - 2 : # Class Status should be displayed as “Available for Students”
            //Expected - 3 : The status of the assignment should not be "Graded"
            CustomAssert.assertEquals(currentAssignments.status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");


            //Expected - 4 : ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 5 : Assignment details and Unassign Assignment links should be displayed
            boolean something = false;
            try {
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.assignmentDetailsLink.click();
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
            } catch (Exception e) {
                something = true;
            }

            if (something == true) {
                CustomAssert.fail("Validate Assignment details and Un-assign assignment links", "Assignment details and Un-assign assignment links is not displayed");
            }


            //Expected - 6 : Try it link should be displayed
            CustomAssert.assertEquals(myQuestionBank.tryITLink.isDisplayed(), true, "Validate 'Try it' button", "'Try it' button is displayed", "'Try it' button is not displayed");


            //Row No - 24 : 12. Click on View Response link
            currentAssignments.getViewGrade_link().click();//click on view student responses

            //Expected - # 1Instructor should be navigated to assignment response page
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(), "Assignment Responses", "Validate assignment response page", "Instructor is navigated to assignment response page", "Instructor is not navigated to assignment response page");

            //Expected - 2 : # Extend due date link should not be displayed
            if (assignmentResponsesPage.extendDueDateLabelList.size() != 0) {
                CustomAssert.fail("Validate Extend due date link", "Extend due date link is displayed");
            }
            //Expected - 3 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.availableForStudents_status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");


            //Expected - 3 : # The status of the assignment should not be "Graded"
            if (currentAssignments.graded_StatusList.size() != 0) {
                if (currentAssignments.graded_Status.isDisplayed()) {
                    CustomAssert.fail("Validate “Graded” status", "Status of class is 'Graded'");
                }
            }

            //Expected - 4 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 5 : #"Release Grade for All" link should be greyed out
            if (!assignmentResponsesPage.gradeBook_Box.isDisplayed()) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link is not greyed out");
            }

            //Expected - 6 : # "Graded" should not be displayed for grade release link


            /*ADDITION OF NEW STUDENT TO THE CLASS SECTION*/


            //Row No - 31 : 13. Add a new student S3 to the class section
            //Expected - # Addition of new student to the class section should be successful
            new LoginUsingLTI().ltiLogin(studDataIndex3);
            String givenName = ReadTestData.readDataByTagName("", "givenname", studDataIndex3);

            if (!dashboard.getUserName().getText().trim().contains(givenName)) {
                CustomAssert.fail("Validate Addition of new student", "Addition of new student to the class section is not successful");
            }


            /*Row No - 32 : "14. Login as instructor
            15. Navigate to Class assignments page from main navigator"*/
            //Expected - # Instructor should be able to navigate to Class Assignments page
            new LoginUsingLTI().ltiLogin(instDataIndex);
            new Navigator().NavigateTo("Class Assignments");
            if (!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()) {
                CustomAssert.fail("Validate Class assignment page", "Class assignment page is not opened.");
            }


            //Row No - 33 : 11. Observe the Assignment card entry details
            //Expected - 1# View Student Response link should be displayed
            try {
                currentAssignments.getViewGrade_link().click();//click on view student responses
            } catch (Exception e) {
                CustomAssert.fail("Validate View Student Response link", "View Student Response link is not displayed");
            }


            //Expected - 2 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.availableForStudents_status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 3 : # The status of the assignment should not be "Graded"
            if (currentAssignments.graded_StatusList.size() != 0) {
                if (currentAssignments.graded_Status.isDisplayed()) {
                    CustomAssert.fail("Validate “Graded” status", "Status of class is 'Graded'");
                }
            }

            //Expected - 4 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 5 : Assignment details and Un-assign assignment links should be displayed
            try {
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.assignmentDetailsLink.click();
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
                new Navigator().NavigateTo("Class Assignments");
            } catch (Exception e) {
                CustomAssert.fail("Validate Assignment details and Un-assign assignment links", "Assignment details and Un-assign assignment links is not displayed");
            }


            //Expected - 6 : Try it link should be displayed
            CustomAssert.assertEquals(myQuestionBank.tryITLink.isDisplayed(), true, "Validate 'Try it' button", "'Try it' button is displayed", "'Try it' button is not displayed");

            //Expected - 7 : # Class Status should be displayed as “Available for Students”
            //Expected - 8 : # The status of the assignment should not be "Graded"
            CustomAssert.assertEquals(currentAssignments.status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");


            //Expected - 9 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }


            //Row No - 87 : 17. Click on View Response link
            currentAssignments.getViewGrade_link().click();//click on view student responses

            //Expected - 1: # Instructor should be navigated to assignment response page
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(), "Assignment Responses", "Validate assignment response page", "Instructor is navigated to assignment response page", "Instructor is not navigated to assignment response page");

            //Expected - 2 : # Extend due date link should be displayed
            if (!assignmentResponsesPage.extendDueDateLabel.isDisplayed()) {
                CustomAssert.fail("Validate Extend due date link", "Extend due date link is not displayed");
            }
            //Expected - 3 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.availableForStudents_status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 4 : Newly added student name should be displayed in the response section
            if (!currentAssignments.enterGrade_mouseOver.get(0).getText().trim().contains(givenName)) {
                CustomAssert.fail("Validate Newly added student name", "Newly added student name is not displayed in the response section");
            }


            //Expected - 5 : # The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.availableForStudents_status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 6 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 7 : #"Release Grade for All" link should be greyed out
            if (!assignmentResponsesPage.gradeBook_Box.isDisplayed()) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link is not greyed out");
            }

            //Expected - 8 : # "Graded" should not be displayed for grade release link



             /*AFTER DUE DATE IS OVER*/

            int instDataIndexNumber = 64;
            String studDataIndexNumber = "64_1";
            assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "" + instDataIndexNumber);
            new Assignment().create(instDataIndexNumber);//Create an assignment

            new LoginUsingLTI().ltiLogin("" + studDataIndexNumber);//login as a student
            new LoginUsingLTI().ltiLogin("" + instDataIndexNumber);//login as instructor

            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "", "" + instDataIndexNumber);//till save policy
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(instDataIndexNumber);//assign to student
            Thread.sleep(300000);

            /*Row No - 95 : "18. Login as instructor
            19. Navigate to Class assignments page from main navigator"*/
            //Expected - # Instructor should be able to navigate to Class Assignments page

            new LoginUsingLTI().ltiLogin("" + instDataIndexNumber);//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            Thread.sleep(150000);

            if (!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()) {
                CustomAssert.fail("Validate Class assignment page", "Class assignment page is not opened.");
            }

            //Row No - 48 : 20. Observe the Assignment card entry details
            //Expected - 1# View Student Response link should be displayed
            try {
                WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrades_link());//click on view student responses

            } catch (Exception e) {
                CustomAssert.fail("Validate View Student Response link", "View Student Response link is not displayed");
            }

            //Expected - 2 : # Class Status should not be displayed as “Available for Students”
            new Navigator().NavigateTo("Class Assignments");
            if (driver.findElements(By.className("ls-assignment-status-awaiting-submissions")).size() != 0) {
                CustomAssert.fail("Validate “Available for Students” status", "Status of class is not displayed as 'Available for Students'");
            }

            //Expected - 3 : # Class Status should be displayed as “Graded”
            CustomAssert.assertEquals(currentAssignments.gradedStatus.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 4 : ”No of Students” vs  “Score Range” bar graph should be displayed
            if (!assignments.assignmentPerformanceGraphICon.isDisplayed()) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is not displayed");
            }

            //Expected - 5 : Assignment details and Unassign Assignment links should not be displayed
            something = false;
            try {
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.assignmentDetailsLink.click();
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
            } catch (Exception e) {
                something = true;
            }

            if (something == false) {
                CustomAssert.fail("Validate Assignment details and Un-assign assignment links", "Assignment details and Un-assign assignment links is displayed");
            }


            //Expected - 6 : Try it link should be displayed
            CustomAssert.assertEquals(myQuestionBank.tryITLink.isDisplayed(), true, "Validate 'Try it' button", "'Try it' button is displayed", "'Try it' button is not displayed");


            //Row No - 54 : 21. Click on View Grades link
            //Expected - 1: # Instructor should be navigated to assignment response page
            currentAssignments.getViewGrade_link().click();//click on view student responses
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(), "Assignment Responses", "Validate assignment response page", "Instructor is navigated to assignment response page", "Instructor is not navigated to assignment response page");

            //Expected - 2 : # Extend due date link should not be displayed
            if (assignmentResponsesPage.extendDueDateLabelList.size() != 0) {
                CustomAssert.fail("Validate Extend due date link", "Extend due date link is displayed");
            }
            //Expected - 3 : # Class Status should not be displayed as “Available for Students”
            if (driver.findElements(By.className("ls-assignment-status-awaiting-submissions")).size() != 0) {
                CustomAssert.fail("Validate “Available for Students” status", "Status of class is displayed as 'Available for Students'");
            }

            //Expected - 4 : # # The status of the assignment should be changed to "Graded"
            CustomAssert.assertNotEquals(currentAssignments.gradedStatus.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 5 : # ”No of Students” vs  “Score Range” bar graph should be displayed
            System.out.println("222 : " + assignments.assignmentPerformanceGraphICon.isDisplayed());
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() == 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is not displayed");
            }

            //Expected - 6 : #"Release Grade for All" link should not be displayed
            if (driver.findElements(By.xpath("//div[@title='Release Grade for All']")).size() != 0) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link should is displayed");
            }

            //Expected - 7 : # "Grades Released" link should be displayed
            if (!assignmentResponsesPage.label_gradesReleased.isDisplayed()) {
                CustomAssert.fail("Validate 'Grades Released' link", "\"Grades Released\" link is not displayed");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'gradableQuestionAssignmentHavingNoManuallyGradedWithGradeReleaseOption3ExtendAfterDueDateDisabled' in the class 'AssignmentFlow'", e);
        }
    }


    @Test(priority = 3, enabled = true)
    public void gradableQuestionAssignmentWithGradeReleaseOption1ExtendAfterDueDateEnabled() {
        try {
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate gradable Question Assignment", "Validate gradable Question Assignment with Grade Release Option 1 Extend After Due Date Enabled", "info");

            /*Pre condition - "1. Class section should have 2 students S1, S2
            2. Instructor should have assigned gradable question assignment to the class section with grade release option 1 and extend after due date disabled
            3. Due date should not have expired"*/

            String instDataIndex = "110";
            String studDataIndex1 = "110_1";
            String studDataIndex2 = "110_2";
            String studDataIndex3 = "110_3";

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", instDataIndex);

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

            new LoginUsingLTI().ltiLogin(instDataIndex);
            new LoginUsingLTI().ltiLogin(studDataIndex1);
            new LoginUsingLTI().ltiLogin(studDataIndex2);

            new Assignment().create(Integer.parseInt(instDataIndex));//Create an assignment

            new LoginUsingLTI().ltiLogin(instDataIndex);//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//till save policy
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(Integer.parseInt(instDataIndex));//assign to student


            /*Row No - 110 : "1. Login as student S1
            2. Navigate to Assignments page
            3. Click on the assignment name
            4. Attempt all the questions and finish the assignment"*/

            //Expected - # Student S1 should be able to finish the assignment
            new LoginUsingLTI().ltiLogin(studDataIndex1);//login as student 1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

            /*Row No - 111 : "5. Login as student S2
            6. Navigate to Assignments page
            7. Click on the assignment name
            8. Attempt all the questions and finish the assignment"*/

            //Expected - # Student S2 should be able to finish the assignment
            new LoginUsingLTI().ltiLogin(studDataIndex2);//login as student 2
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

            /*Row No - 17 : "9. Login as instructor
            10. Navigate to Class assignments page from main navigator"*/
            //Expected - # Instructor should be able to navigate to Class Assignments page

            new LoginUsingLTI().ltiLogin(instDataIndex);//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            if (!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()) {
                CustomAssert.fail("Validate Class assignment page", "Class assignment page is not opened.");
            }

            //Row No - 18 : 11. Observe the Assignment card entry details
            //Expected - 1# View Student Response link should be displayed
            try {
                currentAssignments.getViewGrade_link().click();//click on view student responses
            } catch (Exception e) {
                CustomAssert.fail("Validate View Student Response link", "View Student Response link is not displayed");
            }

            new Navigator().NavigateTo("Class Assignments");
            //Expected - 2 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 3 : The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 4 : ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 5 : Assignment details and Unassign Assignment links should not be displayed
            boolean something = false;
            try {
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.assignmentDetailsLink.click();
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
            } catch (Exception e) {
                something = true;
            }

            if (something == false) {
                CustomAssert.fail("Validate Assignment details and Un-assign assignment links", "Assignment details and Un-assign assignment links is displayed");
            }


            //Expected - 6 : Try it link should be displayed
            CustomAssert.assertEquals(myQuestionBank.tryITLink.isDisplayed(), true, "Validate 'Try it' button", "'Try it' button is displayed", "'Try it' button is not displayed");


            //Row No - 24 : 12. Click on View Response link
            currentAssignments.getViewGrade_link().click();//click on view student responses

            //Expected - # 1Instructor should be navigated to assignment response page
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(), "Assignment Responses", "Validate assignment response page", "Instructor is navigated to assignment response page", "Instructor is not navigated to assignment response page");

            //Expected - 2 : # Extend due date link should not be displayed

            //Expected - 3 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.availableForStudents_status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");


            //Expected - 3 : # The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.availableForStudents_status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 4 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 5 : #"Release Grade for All" link should be greyed out
            if (!assignmentResponsesPage.gradeBook_Box.isDisplayed()) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link is not greyed out");
            }

            //Expected - 6 : # "Graded" should not be displayed for grade release link


            /*ADDITION OF NEW STUDENT TO THE CLASS SECTION*/


            //Row No - 31 : 13. Add a new student S3 to the class section
            //Expected - # Addition of new student to the class section should be successful
            new LoginUsingLTI().ltiLogin(studDataIndex3);
            String givenName = ReadTestData.readDataByTagName("", "givenname", studDataIndex3);

            if (!dashboard.getUserName().getText().trim().contains(givenName)) {
                CustomAssert.fail("Validate Addition of new student", "Addition of new student to the class section is not successful");
            }


            /*Row No - 32 : "14. Login as instructor
            110. Navigate to Class assignments page from main navigator"*/
            //Expected - # Instructor should be able to navigate to Class Assignments page
            new LoginUsingLTI().ltiLogin(instDataIndex);
            new Navigator().NavigateTo("Class Assignments");
            if (!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()) {
                CustomAssert.fail("Validate Class assignment page", "Class assignment page is not opened.");
            }


            //Row No - 33 : 11. Observe the Assignment card entry details
            //Expected - 1# View Student Response link should be displayed
            try {
                currentAssignments.getViewGrade_link().click();//click on view student responses
            } catch (Exception e) {
                CustomAssert.fail("Validate View Student Response link", "View Student Response link is not displayed");
            }


            //Expected - 2 : Assignment details and Un-assign assignment links should be displayed
            try {
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.assignmentDetailsLink.click();
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
                new Navigator().NavigateTo("Class Assignments");
            } catch (Exception e) {
                CustomAssert.fail("Validate Assignment details and Un-assign assignment links", "Assignment details and Un-assign assignment links is not displayed");
            }


            //Expected - 3 : Try it link should be displayed
            CustomAssert.assertEquals(myQuestionBank.tryITLink.isDisplayed(), true, "Validate 'Try it' button", "'Try it' button is displayed", "'Try it' button is not displayed");

            //Expected - 4 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 5 : # The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");

            //Expected - 6 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Row No - 24 : 17. Click on View Response link

            currentAssignments.getViewGrade_link().click();//click on view student responses

            //Expected - 1: # Instructor should be navigated to assignment response page
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(), "Assignment Responses", "Validate assignment response page", "Instructor is navigated to assignment response page", "Instructor is not navigated to assignment response page");

            //Expected - 2 : # Extend due date link should be displayed

            //Expected - 3 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.availableForStudents_status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 4 : Newly added student name should be displayed in the response section
            if (!currentAssignments.enterGrade_mouseOver.get(2).getText().trim().contains(givenName)) {
                CustomAssert.fail("Validate Newly added student name", "Newly added student name is not displayed in the response section");
            }


            //Expected - 5 : # The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.availableForStudents_status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 6 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 7 : #"Release Grade for All" link should be greyed out
            if (!assignmentResponsesPage.gradeBook_Box.isDisplayed()) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link is not greyed out");
            }

            //Expected - 8 : # "Graded" should not be displayed for grade release link



             /*AFTER DUE DATE IS OVER*/

            int instDataIndexNumber = 111;
            String studDataIndexNumber = "111_1";
            assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "" + instDataIndexNumber);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "" + instDataIndexNumber);


            new Assignment().create(instDataIndexNumber);//Create an assignment
            new LoginUsingLTI().ltiLogin("" + studDataIndexNumber);//login as a student
            new LoginUsingLTI().ltiLogin("" + instDataIndexNumber);//login as instructor

            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//till save policy
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(instDataIndexNumber);//assign to student
            Thread.sleep(800000);

            new LoginUsingLTI().ltiLogin(studDataIndexNumber);//login as a student

            //Row No - 142 : 18. Add a new student S4 to the class section
            //Expected - # Addition of new student to the class section should be successful
            givenName = ReadTestData.readDataByTagName("", "givenname", studDataIndexNumber);
            if (!dashboard.getUserName().getText().trim().contains(givenName)) {
                CustomAssert.fail("Validate Addition of new student", "Addition of new student to the class section is not successful");
            }

            /*Row No - 143 : "19. Login as student S4
            20. Navigate to Assignments page
            21. Verify the Assignment entry"*/
            //Expected - # Assignment assigned should not be delivered to the new student
            new Navigator().NavigateTo("Assignments");


            /*Row No - 144 ; "22. Login as instructor
            23. Navigate to Class Assignments page
            24. Navigate to View Student Response page"*/
            //Expected - # Newly added student name should not be displayed in the response section
            new LoginUsingLTI().ltiLogin("" + instDataIndexNumber);//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student responses


            //Row No - 145 : 25. Extend the due date for the Assignment
            //Expected - # Instructor should be able to extend the due date for the assignment
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new Assignment().extentDueTimeFromAssignmentResponsePage("" + instDataIndexNumber);
            if (!lessonPage.getExtendedDueDate().getText().contains("Due date has been extended to")) {
                CustomAssert.fail("Validate extending the due date ", "Instructor is not able to extend the due date for the assignment");

            }


            /*Row No - 146 : "26. Login as student S4
            27. Navigatate to Assignments page
            28. Verfy the Assignment entry"*/
            //Expected - # Assignment assigned should be delivered to the new student
            new LoginUsingLTI().ltiLogin(studDataIndexNumber);//login as a student
            new Navigator().NavigateTo("Assignments");
            if (driver.findElements(By.cssSelector("span[title = '" + assessmentName + "']")).size() == 0) {
                CustomAssert.fail("Validate Assignment assigned", "Assignment assigned is not delivered to the new student");
            }



             /*"29. Login as instructor
            30. Navigate to Class assignments page from main navigator"*/
            new LoginUsingLTI().ltiLogin("" + instDataIndexNumber);//login as instructor
            new Navigator().NavigateTo("Class Assignments");

            //Row No - 148 : 31. Observe the Assignment card entry details
            //Expected -1:  # View Student Response link should be displayed
            try {
                //currentAssignments.getViewGrade_link().click();//click on view student responses
                driver.findElement(By.cssSelector("span[title = 'View Student Responses']")).click();//click on view student responses
            } catch (Exception e) {
                CustomAssert.fail("Validate View Student Response link", "View Student Response link is not displayed");
            }


            //Expected - 2 : # Class Status should not be displayed as “Available for Students”
            new Navigator().NavigateTo("Class Assignments");
            CustomAssert.assertEquals(currentAssignments.status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 3 : # Class Status should not be displayed as “Graded”
            CustomAssert.assertNotEquals(currentAssignments.status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 4 : ”No of Students” vs  “Score Range” bar graph should be displayed
            /*if(!assignments.assignmentPerformanceGraphICon.isDisplayed()){
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph","'No of Students' vs 'Score Range' bar graph is not displayed");
            }*/


            //Expected - 5 : Assignment Details link should be displayed
            //Expected - 6 : Un-assign Assignment link should be displayed
            something = false;
            try {
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.assignmentDetailsLink.click();
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
            } catch (Exception e) {
                something = true;
            }

            if (something == false) {
                CustomAssert.fail("Validate Assignment details and Un-assign assignment links", "Assignment details and Un-assign assignment links is displayed");
            }


            //Row No - 154 : 12. Click on View Response link
            currentAssignments.getViewGrade_link().click();//click on view student responses

            //Expected - # 1: Instructor should be navigated to assignment response page
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(), "Assignment Responses", "Validate assignment response page", "Instructor is navigated to assignment response page", "Instructor is not navigated to assignment response page");

            //Expected - 2 : # Extend due date link should be displayed

            //Expected - 3 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.availableForStudents_status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");


            //Expected - 4 : # The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.availableForStudents_status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 5 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }


            //Expected - 6 : # "Grades Released" link should not be displayed
            if (assignmentResponsesPage.label_gradesReleasedList.size() != 0) {
                CustomAssert.fail("Validate 'Grades Released' link", "\"Grades Released\" link is not displayed");
            }


            /*Row No - 161 : "33. Wait till the due date expires.
            34. Navigate to Class Assignments Page*/
            Thread.sleep(800000);
            new Navigator().NavigateTo("Class Assignments");
            //Expected - 1# View Student Response link should be displayed
            try {
                currentAssignments.getViewGrade_link().click();//click on view student responses
            } catch (Exception e) {
                CustomAssert.fail("Validate View Student Response link", "View Student Response link is not displayed");
            }

            //Expected - 2 : # Class Status should be displayed as “Available for Students”
            new Navigator().NavigateTo("Class Assignments");
            CustomAssert.assertEquals(currentAssignments.status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 3 : The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 4 : ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 5 : Assignment Details link should be displayed
            //Expected - 6 : Un-assign Assignment link should not be displayed
            something = false;
            try {
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.assignmentDetailsLink.click();
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
            } catch (Exception e) {
                something = true;
            }

            if (something == false) {
                CustomAssert.fail("Validate Assignment details and Un-assign assignment links", "Assignment details and Un-assign assignment links is displayed");
            }


            //Row No - 167 : 35. Click on view student responses
            //Expected - # Instructor should be navigated to assignment response page
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);

            new LoginUsingLTI().ltiLogin("" + instDataIndexNumber);//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student responses
            Thread.sleep(5000);
            if (driver.findElements(By.cssSelector("span[title = 'Response - " + assessmentName + "']")).size() == 0) {
                CustomAssert.fail("Validate assignment response page", "Instructor is not navigated to assignment response page");
            }

            /*# Expected - 2 : “The grade release process is on hold so you can extend due dates for students.
             If you have no further extensions, click yes to resume grading.” notification should be displayed*/

            CustomAssert.assertEquals(currentAssignments.resumeGradingContent.getText().trim(), "The grade release process is on hold so you can extend due dates for students.\n" +
                    "If you have no further extensions, click Yes to resume grading.", "Validate Resume Grading Content Notification", "The Notification 'The grade release process is on hold so you can extend due dates for students.\n" +
                    "If you have no further extensions, click Yes to resume grading.' is displayed", "The Notification 'The grade release process is on hold so you can extend due dates for students.\n" +
                    "\" +\n" + " \"If you have no further extensions, click Yes to resume grading.' is not displayed");


            //Expected - 3 : # Extend due date link should be displayed
            if (!assignmentResponsesPage.extendDueDateLabel.isDisplayed()) {
                CustomAssert.fail("Validate Extend due date link", "Extend due date link is not displayed");
            }

            //Expected - 4 : # Class Status should be displayed as “Available for Students”

            if (!currentAssignments.assignmentStatus.get(2).getText().trim().contains("Available for Students")) {
                CustomAssert.fail("Validate “Available for Students” status", "Status of class is displayed as 'Available for Students'");
            }
            //Expected - 5 : The status of the assignment should not be "Graded"
            if (currentAssignments.assignmentStatus.get(2).getText().trim().contains("Graded")) {
                CustomAssert.fail("Validate “Graded” status", "Class Status is displayed as “Graded”");
            }

            //Expected - 6 : ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                if (driver.findElement(By.className("ls-assignment-performance-graph")).isDisplayed()) {
                    CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
                }
            }

            //Expected - 7 : # "Grades Released" link should not be displayed
            if (assignmentResponsesPage.label_gradesReleasedList.size() != 0) {
                CustomAssert.fail("Validate 'Grades Released' link", "\"Grades Released\" link is displayed");
            }

            //Expected - 8 : # "Release Grade for All" link should not be displayed.
            if (assignmentResponsesPage.gradeBook_BoxList.size() != 0) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link is displayed");
            }


            //Row No - 175 : 36. Click on Yes link in the yellow notification in assignment response page
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();
            assessmentResponses.resumeGrading_button.click();
            new Navigator().NavigateTo("Class Assignments");
            //Expected - 2 : # Class Status should not be displayed as “Available for Students”
            if (currentAssignments.assignmentStatus.get(1).getText().trim().contains("Available for Students")) {
                CustomAssert.fail("Validate “Available for Students” status", "Status of class is displayed as 'Available for Students'");
            }

            //Expected - 3 : # # The status of the assignment should be changed to "Graded"
            if (!currentAssignments.assignmentStatus.get(1).getText().trim().contains("Graded")) {
                CustomAssert.fail("Validate “Graded” status", "The status of the assignment is not changed to \"Graded\"");
            }

            //Expected - 4 : ”No of Students” vs  “Score Range” bar graph should be displayed
            if (!assignments.assignmentPerformanceGraphICon.isDisplayed()) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is not displayed");
            }

            //Expected - 5 : # "Release Grade for All" link should not be displayed.
            if (driver.findElements(By.xpath("//div[@title='Release Grade for All']")).size() != 0) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link should is displayed");
            }

            //Expected - 6 : # "Grades Released" link should be displayed
            //Expected - 7 : # "Grades Released" link should be greyed out
            currentAssignments.getViewGrades_link().click();
            Thread.sleep(5000);
            if (!assignmentResponsesPage.label_gradesReleased.isDisplayed()) {
                CustomAssert.fail("Validate 'Grades Released' link", "\"Grades Released\" link is not displayed");
            }

            /*Row No - 182 : "37. Navigate to Class Assignments page
            38. Observe the Assignment card entry details"*/
            //Expected - # View Grades link should be displayed
            new Navigator().NavigateTo("Class Assignments");
            if (!currentAssignments.getViewGrades_link().isDisplayed()) {
                CustomAssert.fail("View Grades link", "View Grades link is not displayed");
            }

            //Expected - 2 : # Class Status should not be displayed as “Available for Students”
            if (currentAssignments.assignmentStatus.get(1).getText().trim().contains("Available for Students")) {
                CustomAssert.fail("Validate “Available for Students” status", "Status of class is displayed as 'Available for Students'");
            }


            //Expected - 3 : # Class Status should be displayed as “Graded”
            if (!currentAssignments.assignmentStatus.get(1).getText().trim().contains("Graded")) {
                CustomAssert.fail("Validate “Graded” status", "Class Status is not displayed as “Graded”");
            }


            //Expected - 4 : # ”No of Students” vs  “Score Range” bar graph should be displayed
            if (!assignments.assignmentPerformanceGraphICon.isDisplayed()) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is not displayed");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'gradableQuestionAssignmentWithGradeReleaseOption1ExtendAfterDueDateEnabled' in the class 'AssignmentFlow'", e);
        }
    }


    //Its getting failed because of bug
    @Test(priority = 4, enabled = true)
    public void gradableQuestionAssignmentHavingNoManuallyGradedQuestionWithGradeReleaseOption3ExtendAfterDueDateEnabled() {
        try {
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate gradable Question Assignment", "Validate gradable Question Assignment with Grade Release Option 1 Extend After Due Date Enabled", "info");

            /*Pre condition - "1. Class section should have 2 students S1, S2
            2. Instructor should have assigned gradable question assignment to the class section with grade release option 1 and extend after due date disabled
            3. Due date should not have expired"*/

            String instDataIndex = "190";
            String studDataIndex1 = "190_1";
            String studDataIndex2 = "190_2";
            String studDataIndex3 = "190_3";

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", instDataIndex);

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

            new LoginUsingLTI().ltiLogin(instDataIndex);
            new LoginUsingLTI().ltiLogin(studDataIndex1);
            new LoginUsingLTI().ltiLogin(studDataIndex2);

            new Assignment().create(Integer.parseInt(instDataIndex));//Create an assignment

            new LoginUsingLTI().ltiLogin(instDataIndex);//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//till save policy
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(Integer.parseInt(instDataIndex));//assign to student


            /*Row No - 190 : "1. Login as student S1
            2. Navigate to Assignments page
            3. Click on the assignment name
            4. Attempt all the questions and finish the assignment"*/

            //Expected - # Student S1 should be able to finish the assignment
            new LoginUsingLTI().ltiLogin(studDataIndex1);//login as student 1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment*/

            /*Row No - 191 : "5. Login as student S2
            6. Navigate to Assignments page
            7. Click on the assignment name
            8. Attempt all the questions and finish the assignment"*/

            //Expected - # Student S2 should be able to finish the assignment
            new LoginUsingLTI().ltiLogin(studDataIndex2);//login as student 2
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

            /*Row No - 192 : "9. Login as instructor
            10. Navigate to Class assignments page from main navigator"*/
            //Expected - # Instructor should be able to navigate to Class Assignments page

            new LoginUsingLTI().ltiLogin(instDataIndex);//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            if (!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()) {
                CustomAssert.fail("Validate Class assignment page", "Class assignment page is not opened.");
            }

            //Row No - 193 : 11. Observe the Assignment card entry details
            //Expected - 1# View Student Response link should be displayed
            try {
                currentAssignments.getViewGrade_link().click();//click on view student responses
            } catch (Exception e) {
                CustomAssert.fail("Validate View Student Response link", "View Student Response link is not displayed");
            }

            new Navigator().NavigateTo("Class Assignments");
            //Expected - 2 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 3 : The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 4 : ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 5 : Assignment details and Unassign Assignment links should not be displayed
            boolean something = false;
            try {
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.assignmentDetailsLink.click();
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
            } catch (Exception e) {
                something = true;
            }

            if (something == false) {
                CustomAssert.fail("Validate Assignment details and Un-assign assignment links", "Assignment details and Un-assign assignment links is displayed");
            }


            //Expected - 6 : Try it link should be displayed
            CustomAssert.assertEquals(myQuestionBank.tryITLink.isDisplayed(), true, "Validate 'Try it' button", "'Try it' button is displayed", "'Try it' button is not displayed");


            //Row No - 199 : 12. Click on View Response link
            currentAssignments.getViewGrade_link().click();//click on view student responses

            //Expected - # 1Instructor should be navigated to assignment response page
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(), "Assignment Responses", "Validate assignment response page", "Instructor is navigated to assignment response page", "Instructor is not navigated to assignment response page");

            //Expected - 2 : # Extend due date link should not be displayed

            //Expected - 3 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.availableForStudents_status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");


            //Expected - 3 : # The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.availableForStudents_status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 4 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 5 : #"Release Grade for All" link should be greyed out
            if (!assignmentResponsesPage.gradeBook_Box.isDisplayed()) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link is not greyed out");
            }

            //Expected - 6 : # "Graded" should not be displayed for grade release link


            /*ADDITION OF NEW STUDENT TO THE CLASS SECTION*/


            //Row No - 206 : 13. Add a new student S3 to the class section
            //Expected - # Addition of new student to the class section should be successful
            new LoginUsingLTI().ltiLogin(studDataIndex3);
            String givenName = ReadTestData.readDataByTagName("", "givenname", studDataIndex3);

            if (!dashboard.getUserName().getText().trim().contains(givenName)) {
                CustomAssert.fail("Validate Addition of new student", "Addition of new student to the class section is not successful");
            }


            /*Row No - 207 : "14. Login as instructor
            110. Navigate to Class assignments page from main navigator"*/
            //Expected - # Instructor should be able to navigate to Class Assignments page
            new LoginUsingLTI().ltiLogin(instDataIndex);
            new Navigator().NavigateTo("Class Assignments");
            if (!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()) {
                CustomAssert.fail("Validate Class assignment page", "Class assignment page is not opened.");
            }


            //Row No - 208 : 11. Observe the Assignment card entry details
            //Expected - 1# View Student Response link should be displayed
            try {
                currentAssignments.getViewGrade_link().click();//click on view student responses
            } catch (Exception e) {
                CustomAssert.fail("Validate View Student Response link", "View Student Response link is not displayed");
            }


            //Expected - 2 : Assignment details and Un-assign assignment links should be displayed
            try {
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.assignmentDetailsLink.click();
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
                new Navigator().NavigateTo("Class Assignments");
            } catch (Exception e) {
                CustomAssert.fail("Validate Assignment details and Un-assign assignment links", "Assignment details and Un-assign assignment links is not displayed");
            }


            //Expected - 3 : Try it link should be displayed
            CustomAssert.assertEquals(myQuestionBank.tryITLink.isDisplayed(), true, "Validate 'Try it' button", "'Try it' button is displayed", "'Try it' button is not displayed");

            //Expected - 4 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 5 : # The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");

            //Expected - 6 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Row No - 214 : 17. Click on View Response link

            currentAssignments.getViewGrade_link().click();//click on view student responses

            //Expected - 1: # Instructor should be navigated to assignment response page
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(), "Assignment Responses", "Validate assignment response page", "Instructor is navigated to assignment response page", "Instructor is not navigated to assignment response page");

            //Expected - 2 : # Extend due date link should be displayed

            //Expected - 3 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.availableForStudents_status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 4 : Newly added student name should be displayed in the response section
            if (!currentAssignments.enterGrade_mouseOver.get(2).getText().trim().contains(givenName)) {
                CustomAssert.fail("Validate Newly added student name", "Newly added student name is not displayed in the response section");
            }


            //Expected - 5 : # The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.availableForStudents_status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 6 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 7 : #"Release Grade for All" link should be greyed out
            if (!assignmentResponsesPage.gradeBook_Box.isDisplayed()) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link is not greyed out");
            }

            //Expected - 8 : # "Graded" should not be displayed for grade release link



             /*AFTER DUE DATE IS OVER*/

            int instDataIndexNumber = 191;
            String studDataIndexNumber = "191_1";
            assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "" + instDataIndexNumber);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "" + instDataIndexNumber);


            new Assignment().create(instDataIndexNumber);//Create an assignment
            new LoginUsingLTI().ltiLogin("" + studDataIndexNumber);//login as a student
            new LoginUsingLTI().ltiLogin("" + instDataIndexNumber);//login as instructor

            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//till save policy
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(instDataIndexNumber);//assign to student
            Thread.sleep(800000);

            new LoginUsingLTI().ltiLogin(studDataIndexNumber);//login as a student

            //Row No - 222 : 18. Add a new student S4 to the class section
            //Expected - # Addition of new student to the class section should be successful
            givenName = ReadTestData.readDataByTagName("", "givenname", studDataIndexNumber);
            if (!dashboard.getUserName().getText().trim().contains(givenName)) {
                CustomAssert.fail("Validate Addition of new student", "Addition of new student to the class section is not successful");
            }


            /*Row No - 223 ; "22. Login as instructor
            23. Navigate to Class Assignments page
            24. Navigate to View Student Response page"*/
            //Expected - # Newly added student name should not be displayed in the response section
            new LoginUsingLTI().ltiLogin("" + instDataIndexNumber);//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student responses


            //Row No - 224 : 25. Extend the due date for the Assignment
            //Expected - # Instructor should be able to extend the due date for the assignment
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new Assignment().extentDueTimeFromAssignmentResponsePage("" + instDataIndexNumber);
            if (!lessonPage.getExtendedDueDate().getText().contains("Due date has been extended to")) {
                CustomAssert.fail("Validate extending the due date ", "Instructor is not able to extend the due date for the assignment");

            }


            /*Row No - 225 : "26. Login as student S4
            27. Navigatate to Assignments page
            28. Verfy the Assignment entry"*/
            //Expected - # Assignment assigned should be delivered to the new student
            new LoginUsingLTI().ltiLogin(studDataIndexNumber);//login as a student
            new Navigator().NavigateTo("Assignments");
            if (driver.findElements(By.cssSelector("span[title = '" + assessmentName + "']")).size() == 0) {
                CustomAssert.fail("Validate Assignment assigned", "Assignment assigned is not delivered to the new student");
            }



             /*"226. Login as instructor
            30. Navigate to Class assignments page from main navigator"*/
            new LoginUsingLTI().ltiLogin("" + instDataIndexNumber);//login as instructor
            new Navigator().NavigateTo("Class Assignments");

            //Row No - 227 : 31. Observe the Assignment card entry details
            //Expected -1:  # View Student Response link should be displayed
            try {
                //currentAssignments.getViewGrade_link().click();//click on view student responses
                driver.findElement(By.cssSelector("span[title = 'View Student Responses']")).click();//click on view student responses
            } catch (Exception e) {
                CustomAssert.fail("Validate View Student Response link", "View Student Response link is not displayed");
            }


            //Expected - 2 : # Class Status should not be displayed as “Available for Students”
            new Navigator().NavigateTo("Class Assignments");
            CustomAssert.assertEquals(currentAssignments.status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 3 : # Class Status should not be displayed as “Graded”
            CustomAssert.assertNotEquals(currentAssignments.status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 5 : Assignment Details link should be displayed
            //Expected - 6 : Un-assign Assignment link should be displayed
            something = false;
            try {
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.assignmentDetailsLink.click();
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
            } catch (Exception e) {
                something = true;
            }

            if (something == false) {
                CustomAssert.fail("Validate Assignment details and Un-assign assignment links", "Assignment details and Un-assign assignment links is displayed");
            }


            //Row No - 233 : 12. Click on View Response link
            currentAssignments.getViewGrade_link().click();//click on view student responses

            //Expected - # 1: Instructor should be navigated to assignment response page
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(), "Assignment Responses", "Validate assignment response page", "Instructor is navigated to assignment response page", "Instructor is not navigated to assignment response page");

            //Expected - 2 : # Extend due date link should be displayed

            //Expected - 3 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.availableForStudents_status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");


            //Expected - 4 : # The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.availableForStudents_status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 5 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }


            //Expected - 6 : # "Grades Released" link should not be displayed
            if (assignmentResponsesPage.label_gradesReleasedList.size() != 0) {
                CustomAssert.fail("Validate 'Grades Released' link", "\"Grades Released\" link is not displayed");
            }


            /*Row No - 240 : "33. Wait till the due date expires.
            34. Navigate to Class Assignments Page*/
            Thread.sleep(800000);
            new Navigator().NavigateTo("Class Assignments");
            //Expected - 1# View Student Response link should be displayed
            try {
                currentAssignments.getViewGrade_link().click();//click on view student responses
            } catch (Exception e) {
                CustomAssert.fail("Validate View Student Response link", "View Student Response link is not displayed");
            }

            //Expected - 2 : # Class Status should be displayed as “Available for Students”
            new Navigator().NavigateTo("Class Assignments");
            CustomAssert.assertEquals(currentAssignments.status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 3 : The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 4 : ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 5 : Assignment Details link should be displayed
            //Expected - 6 : Un-assign Assignment link should not be displayed
            something = false;
            try {
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.assignmentDetailsLink.click();
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
            } catch (Exception e) {
                something = true;
            }

            if (something == false) {
                CustomAssert.fail("Validate Assignment details and Un-assign assignment links", "Assignment details and Un-assign assignment links is displayed");
            }


            //Row No - 246 : 35. Click on view student responses
            //Expected - # Instructor should be navigated to assignment response page
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);

            new LoginUsingLTI().ltiLogin("" + instDataIndexNumber);//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student responses
            Thread.sleep(5000);
            if (driver.findElements(By.cssSelector("span[title = 'Response - " + assessmentName + "']")).size() == 0) {
                CustomAssert.fail("Validate assignment response page", "Instructor is not navigated to assignment response page");
            }

            /*# Expected - 2 : “The grade release process is on hold so you can extend due dates for students.
             If you have no further extensions, click yes to resume grading.” notification should be displayed*/

            CustomAssert.assertEquals(currentAssignments.resumeGradingContent.getText().trim(), "The grade release process is on hold so you can extend due dates for students.\n" +
                    "If you have no further extensions, click Yes to resume grading.", "Validate Resume Grading Content Notification", "The Notification 'The grade release process is on hold so you can extend due dates for students.\n" +
                    "If you have no further extensions, click Yes to resume grading.' is displayed", "The Notification 'The grade release process is on hold so you can extend due dates for students.\n" +
                    "\" +\n" + " \"If you have no further extensions, click Yes to resume grading.' is not displayed");


            //Expected - 3 : # Extend due date link should be displayed
            if (!assignmentResponsesPage.extendDueDateLabel.isDisplayed()) {
                CustomAssert.fail("Validate Extend due date link", "Extend due date link is not displayed");
            }

            //Expected - 4 : # Class Status should be displayed as “Available for Students”
            if (!currentAssignments.assignmentStatus.get(2).getText().trim().contains("Available for Students")) {
                CustomAssert.fail("Validate “Available for Students” status", "Status of class is displayed as 'Available for Students'");
            }
            //Expected - 5 : The status of the assignment should not be "Graded"
            if (currentAssignments.assignmentStatus.get(2).getText().trim().contains("Graded")) {
                CustomAssert.fail("Validate “Graded” status", "Class Status is displayed as “Graded”");
            }

            //Expected - 6 : ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                if (driver.findElement(By.className("ls-assignment-performance-graph")).isDisplayed()) {
                    CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
                }
            }

            //Expected - 7 : # "Grades Released" link should not be displayed
            if (assignmentResponsesPage.label_gradesReleasedList.size() != 0) {
                CustomAssert.fail("Validate 'Grades Released' link", "\"Grades Released\" link is displayed");
            }

            //Expected - 8 : # "Release Grade for All" link should not be displayed.
            if (assignmentResponsesPage.gradeBook_BoxList.size() != 0) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link is displayed");
            }

            //Row No - 254 : 36. Click on Yes link in the yellow notification in assignment response page
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();
            assessmentResponses.resumeGrading_button.click();
            new Navigator().NavigateTo("Class Assignments");
            //Expected - 2 : # Class Status should not be displayed as “Available for Students”
            if (currentAssignments.assignmentStatus.get(1).getText().trim().contains("Available for Students")) {
                CustomAssert.fail("Validate “Available for Students” status", "Status of class is displayed as 'Available for Students'");
            }

            //Expected - 3 : # # The status of the assignment should be changed to "Graded"
            if (!currentAssignments.assignmentStatus.get(1).getText().trim().contains("Graded")) {
                CustomAssert.fail("Validate “Graded” status", "The status of the assignment is not changed to \"Graded\"");
            }

            //Expected - 4 : ”No of Students” vs  “Score Range” bar graph should be displayed
            if (!assignments.assignmentPerformanceGraphICon.isDisplayed()) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is not displayed");
            }

            //Expected - 5 : # "Release Grade for All" link should not be displayed.
            if (driver.findElements(By.xpath("//div[@title='Release Grade for All']")).size() != 0) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link should is displayed");
            }

            //Expected - 6 : # "Grades Released" link should be displayed
            //Expected - 7 : # "Grades Released" link should be greyed out
            currentAssignments.getViewGrades_link().click();
            Thread.sleep(5000);
            if (!assignmentResponsesPage.label_gradesReleased.isDisplayed()) {
                CustomAssert.fail("Validate 'Grades Released' link", "\"Grades Released\" link is not displayed");
            }

            /*Row No - 261 : "37. Navigate to Class Assignments page
            38. Observe the Assignment card entry details"*/
            //Expected - # View Grades link should be displayed
            new Navigator().NavigateTo("Class Assignments");
            if (!currentAssignments.getViewGrades_link().isDisplayed()) {
                CustomAssert.fail("View Grades link", "View Grades link is not displayed");
            }

            //Expected - 2 : # Class Status should not be displayed as “Available for Students”
            if (currentAssignments.assignmentStatus.get(1).getText().trim().contains("Available for Students")) {
                CustomAssert.fail("Validate “Available for Students” status", "Status of class is displayed as 'Available for Students'");
            }


            //Expected - 3 : # Class Status should be displayed as “Graded”
            if (!currentAssignments.assignmentStatus.get(1).getText().trim().contains("Graded")) {
                CustomAssert.fail("Validate “Graded” status", "Class Status is not displayed as “Graded”");
            }


            //Expected - 4 : # ”No of Students” vs  “Score Range” bar graph should be displayed
            if (!assignments.assignmentPerformanceGraphICon.isDisplayed()) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is not displayed");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'gradableQuestionAssignmentHavingNoManuallyGradedQuestionWithGradeReleaseOption3ExtendAfterDueDateEnabled' in the class 'AssignmentFlow'", e);
        }
    }

    //This below method should work when the Bug logged: 23888 is resolved
    @Test(priority = 5, enabled = true)
    public void validateAssigningAdaptivePracticeAssessmentToClassSection() {
        try {
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate gradable Question Assignment", "Validate gradable Question Assignment with Grade Release Option 1 Extend After Due Date Enabled", "info");

            /*Pre condition - "1. Class section should have 2 students S1, S2
            2. Instructor should have assigned gradable question assignment to the class section with grade release option 1 and extend after due date disabled
            3. Due date should not have expired"*/

            String instDataIndex = "269";
            String studDataIndex1 = "269_1";
            String studDataIndex2 = "269_2";
            String studDataIndex3 = "269_3";

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", instDataIndex);

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);

            new LoginUsingLTI().ltiLogin(instDataIndex);
            new LoginUsingLTI().ltiLogin(studDataIndex1);
            new LoginUsingLTI().ltiLogin(studDataIndex2);

            new LoginUsingLTI().ltiLogin(instDataIndex);//login as instructor
            new PracticeTest().assignOrionAdaptivePractice(Integer.parseInt(instDataIndex), 0);

            /*Row No - 269 : "1. Login as student S1
            2. Navigate to Assignments page
            3. Click on the assignment name
            4. Attempt all the questions and finish the assignment"*/
            //Expected - # Student S1 should be able to finish the assignment
            new LoginUsingLTI().ltiLogin(studDataIndex1);//login as student 1
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            new DiagnosticTest().startTest(1); //start Diagnostic test
            new DiagnosticTest().attemptAllCorrect(2, false, false); //attempt all correct question
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("button[class='ls-summary-button ls-summary-start-assignment']")));
            for (int i = 0; i < 20; i++) {
                new PracticeTest().attemptPracticeTestAfterAssign(1);
                Thread.sleep(500);
            }
            assignments.iamFinished_link.click();
            CustomAssert.assertEquals(performanceSummary.titlePerformanceSummary.getText().trim(), "Performance Summary", "Validate Assignment finished by Student", "Student S1 is able to finish the assignment", "Student S1 is not able to finish the assignment");

            /*Row No - 270 : "5. Login as student S2
            6. Navigate to Assignments page
            7. Click on the assignment name
            8. Attempt all the questions and finish the assignment"*/

            //Expected - # Student S2 should be able to finish the assignment
            new LoginUsingLTI().ltiLogin(studDataIndex2);//login as student 1
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            new DiagnosticTest().startTest(1); //start Diagnostic test
            new DiagnosticTest().attemptAllCorrect(2, false, false); //attempt all correct question
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("button[class='ls-summary-button ls-summary-start-assignment']")));
            for (int i = 0; i < 20; i++) {
                new PracticeTest().attemptPracticeTestAfterAssign(1);
                Thread.sleep(500);
            }
            assignments.iamFinished_link.click();
            CustomAssert.assertEquals(performanceSummary.titlePerformanceSummary.getText().trim(), "Performance Summary", "Validate Assignment finished by Student", "Student S1 is able to finish the assignment", "Student S1 is not able to finish the assignment");

            /*Row No - 271 : "9. Login as instructor
            10. Navigate to Class assignments page from main navigator"*/
            //Expected - # Instructor should be able to navigate to Class Assignments page

            new LoginUsingLTI().ltiLogin(instDataIndex);//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            if (!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()) {
                CustomAssert.fail("Validate Class assignment page", "Class assignment page is not opened.");
            }

            //Row No - 272 : 11. Observe the Assignment card entry details
            //Expected - 1# View Student Response link should be displayed
            try {
                currentAssignments.getViewGrade_link().click();//click on view student responses
            } catch (Exception e) {
                CustomAssert.fail("Validate View Student Response link", "View Student Response link is not displayed");
            }

            new Navigator().NavigateTo("Class Assignments");
            //Expected - 2 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 3 : The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 4 : ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 5 : Assignment details and Un assign Assignment links should not be displayed
            boolean something = false;
            try {
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.assignmentDetailsLink.click();
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
            } catch (Exception e) {
                something = true;
            }

            if (something == false) {
                CustomAssert.fail("Validate Assignment details and Un-assign assignment links", "Assignment details and Un-assign assignment links is displayed");
            }


            //Expected - 6 : Try it link should be displayed
            CustomAssert.assertEquals(myQuestionBank.tryITLink.isDisplayed(), true, "Validate 'Try it' button", "'Try it' button is displayed", "'Try it' button is not displayed");


            //Row No - 199 : 12. Click on View Response link
            currentAssignments.getViewGrade_link().click();//click on view student responses

            //Expected - # 1Instructor should be navigated to assignment response page
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(), "Assignment Responses", "Validate assignment response page", "Instructor is navigated to assignment response page", "Instructor is not navigated to assignment response page");

            //Expected - 2 : # Extend due date link should not be displayed

            //Expected - 3 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.availableForStudents_status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 3 : # The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.availableForStudents_status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");

            //Expected - 4 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 5 : #"Release Grade for All" link should be greyed out
            if (!assignmentResponsesPage.gradeBook_Box.isDisplayed()) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link is not greyed out");
            }

            //Expected - 6 : # "Graded" should not be displayed for grade release link


            /*ADDITION OF NEW STUDENT TO THE CLASS SECTION*/


            //Row No - 286 : 13. Add a new student S3 to the class section
            //Expected - # Addition of new student to the class section should be successful
            new LoginUsingLTI().ltiLogin(studDataIndex3);
            String givenName = ReadTestData.readDataByTagName("", "givenname", studDataIndex3);

            if (!dashboard.getUserName().getText().trim().contains(givenName)) {
                CustomAssert.fail("Validate Addition of new student", "Addition of new student to the class section is not successful");
            }


            /*Row No - 287 : "14. Login as instructor
            110. Navigate to Class assignments page from main navigator"*/
            //Expected - # Instructor should be able to navigate to Class Assignments page
            new LoginUsingLTI().ltiLogin(instDataIndex);
            new Navigator().NavigateTo("Class Assignments");
            if (!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()) {
                CustomAssert.fail("Validate Class assignment page", "Class assignment page is not opened.");
            }


            //Row No - 288 : 11. Observe the Assignment card entry details
            //Expected - 1# View Student Response link should be displayed
            try {
                currentAssignments.getViewGrade_link().click();//click on view student responses
            } catch (Exception e) {
                CustomAssert.fail("Validate View Student Response link", "View Student Response link is not displayed");
            }


            //Expected - 2 : Assignment details and Un-assign assignment links should be displayed
            try {
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.assignmentDetailsLink.click();
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
                new Navigator().NavigateTo("Class Assignments");
            } catch (Exception e) {
                CustomAssert.fail("Validate Assignment details and Un-assign assignment links", "Assignment details and Un-assign assignment links is not displayed");
            }


            //Expected - 3 : Try it link should be displayed
            CustomAssert.assertEquals(myQuestionBank.tryITLink.isDisplayed(), true, "Validate 'Try it' button", "'Try it' button is displayed", "'Try it' button is not displayed");

            //Expected - 4 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 5 : # The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");

            //Expected - 6 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Row No - 294 : 17. Click on View Response link

            currentAssignments.getViewGrade_link().click();//click on view student responses

            //Expected - 1: # Instructor should be navigated to assignment response page
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(), "Assignment Responses", "Validate assignment response page", "Instructor is navigated to assignment response page", "Instructor is not navigated to assignment response page");

            //Expected - 2 : # Extend due date link should be displayed

            //Expected - 3 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.availableForStudents_status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 4 : Newly added student name should be displayed in the response section
            if (!currentAssignments.enterGrade_mouseOver.get(2).getText().trim().contains(givenName)) {
                CustomAssert.fail("Validate Newly added student name", "Newly added student name is not displayed in the response section");
            }


            //Expected - 5 : # The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.availableForStudents_status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");


            //Expected - 6 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Expected - 7 : #"Release Grade for All" link should be greyed out
            if (!assignmentResponsesPage.gradeBook_Box.isDisplayed()) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link is not greyed out");
            }

            //Expected - 8 : # "Graded" should not be displayed for grade release link



             /*AFTER DUE DATE IS OVER*/

            int instDataIndexNumber = 270;
            String studDataIndexNumber = "270_1";
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "" + instDataIndexNumber);

            new Assignment().create(instDataIndexNumber);//Create an assignment
            new LoginUsingLTI().ltiLogin("" + studDataIndexNumber);//login as a student
            new LoginUsingLTI().ltiLogin("" + instDataIndexNumber);//login as instructor

            new LoginUsingLTI().ltiLogin(instDataIndex);//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(instDataIndexNumber);//assign to student
            Thread.sleep(800000);


            //Row No - 222 : 18. Add a new student S4 to the class section
            //Expected - # Addition of new student to the class section should be successful
            new LoginUsingLTI().ltiLogin(studDataIndexNumber);//login as a student
            givenName = ReadTestData.readDataByTagName("", "givenname", studDataIndexNumber);
            if (!dashboard.getUserName().getText().trim().contains(givenName)) {
                CustomAssert.fail("Validate Addition of new student", "Addition of new student to the class section is not successful");
            }


            /*Row No - 223 ; "22. Login as instructor
            23. Navigate to Class Assignments page
            24. Navigate to View Student Response page"*/
            //Expected - # Newly added student name should not be displayed in the response section
            new LoginUsingLTI().ltiLogin("" + instDataIndexNumber);//login as instructor
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student responses


            //Row No - 224 : 25. Extend the due date for the Assignment
            //Expected - # Instructor should be able to extend the due date for the assignment
            lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new Assignment().extentDueTimeFromAssignmentResponsePage("" + instDataIndexNumber);
            if (!lessonPage.getExtendedDueDate().getText().contains("Due date has been extended to")) {
                CustomAssert.fail("Validate extending the due date ", "Instructor is not able to extend the due date for the assignment");

            }


            /*Row No - 225 : "26. Login as student S4
            27. Navigatate to Assignments page
            28. Verfy the Assignment entry"*/
            //Expected - # Assignment assigned should be delivered to the new student
            new LoginUsingLTI().ltiLogin(studDataIndexNumber);//login as a student
            new Navigator().NavigateTo("Assignments");
            if (driver.findElements(By.cssSelector("span[title = '" + assessmentName + "']")).size() == 0) {
                CustomAssert.fail("Validate Assignment assigned", "Assignment assigned is not delivered to the new student");
            }



             /*Row No - 302 : "226. Login as instructor
            30. Navigate to Class assignments page from main navigator"*/
            new LoginUsingLTI().ltiLogin("" + instDataIndexNumber);//login as instructor
            new Navigator().NavigateTo("Class Assignments");

            //Row No - 303 : 31. Observe the Assignment card entry details
            //Expected -1:  # View Student Response link should be displayed
            try {
                driver.findElement(By.cssSelector("span[title = 'View Student Responses']")).click();//click on view student responses
            } catch (Exception e) {
                CustomAssert.fail("Validate View Student Response link", "View Student Response link is not displayed");
            }


            //Expected - 2 : Assignment details and Unassign Assignment links should not be displayed
            something = false;
            try {
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.assignmentDetailsLink.click();
                new Navigator().NavigateTo("Class Assignments");
                currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
            } catch (Exception e) {
                something = true;
            }

            if (something == false) {
                CustomAssert.fail("Validate Assignment details and Un-assign assignment links", "Assignment details and Un-assign assignment links is displayed");
            }


            //Expected - 3 : # Class Status should be displayed as “Available for Students”
            new Navigator().NavigateTo("Class Assignments");
            CustomAssert.assertEquals(currentAssignments.status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - 4 : # Class Status should not be displayed as “Graded”
            CustomAssert.assertNotEquals(currentAssignments.status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");

            //Expected - 5 : # ”No of Students” vs  “Score Range” bar graph should not be displayed
            if (driver.findElements(By.className("ls-assignment-performance-graph")).size() != 0) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is displayed");
            }

            //Row No - 308 : 12. Click on View Response link
            //Expected - # 1: Instructor should be navigated to assignment response page
            currentAssignments.getViewGrade_link().click();//click on view student responses
            CustomAssert.assertEquals(currentAssignments.getResponsePageTitle().getText().trim(), "Assignment Responses", "Validate assignment response page", "Instructor is navigated to assignment response page", "Instructor is not navigated to assignment response page");

            //Expected - 2 : # Extend due date link should be displayed
            if (!assignmentResponsesPage.extendDueDateLabel.isDisplayed()) {
                CustomAssert.fail("Validate Extend due date link", "Extend due date link is not displayed");
            }

            //Expected - 3 : # Class Status should be displayed as “Available for Students”
            CustomAssert.assertEquals(currentAssignments.availableForStudents_status.getText().trim(), "Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");


            //Expected - 4 : # The status of the assignment should not be "Graded"
            CustomAssert.assertNotEquals(currentAssignments.availableForStudents_status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");

            /*# Expected - 5 : “The grade release process is on hold so you can extend due dates for students.
                    If you have no further extensions, click yes to resume grading.” notification should be displayed*/
            CustomAssert.assertEquals(currentAssignments.resumeGradingContent.getText().trim(), "The grade release process is on hold so you can extend due dates for students.\n" +
                    "If you have no further extensions, click Yes to resume grading.", "Validate Resume Grading Content Notification", "The Notification 'The grade release process is on hold so you can extend due dates for students.\n" +
                    "If you have no further extensions, click Yes to resume grading.' is displayed", "The Notification 'The grade release process is on hold so you can extend due dates for students.\n" +
                    "\" +\n" + " \"If you have no further extensions, click Yes to resume grading.' is not displayed");


            if (!assignments.assignmentPerformanceGraphICon.isDisplayed()) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is not displayed");
            }

            //Expected - 6 : #"Release Grade for All" link should not be displayed
            if (driver.findElements(By.xpath("//div[@title='Release Grade for All']")).size() != 0) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link should is displayed");
            }

            //Expected - 7 : # "Grades Released" link should be displayed
            //Expected - 8 : # "Grades Released" link should be greyed out
            if (!assignmentResponsesPage.label_gradesReleased.isDisplayed()) {
                CustomAssert.fail("Validate 'Grades Released' link", "\"Grades Released\" link is not displayed");
            }

            AssessmentResponses assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);

            //Row No - 317 : 36. Click on Yes link in the yellow notification in assignment response page
            new Navigator().NavigateTo("Class Assignments");
            currentAssignments.getViewGrade_link().click();
            assessmentResponses.resumeGrading_button.click();
            new Navigator().NavigateTo("Class Assignments");

            //Expected - 2 : # Class Status should not be displayed as “Available for Students”
            if (currentAssignments.assignmentStatus.get(1).getText().trim().contains("Available for Students")) {
                CustomAssert.fail("Validate “Available for Students” status", "Status of class is displayed as 'Available for Students'");
            }

            //Expected - 3 : # # The status of the assignment should be changed to "Graded"
            if (!currentAssignments.assignmentStatus.get(1).getText().trim().contains("Graded")) {
                CustomAssert.fail("Validate “Graded” status", "The status of the assignment is not changed to \"Graded\"");
            }

            //Expected - 4 : ”No of Students” vs  “Score Range” bar graph should be displayed
            if (!assignments.assignmentPerformanceGraphICon.isDisplayed()) {
                CustomAssert.fail("Validate 'No of Students' vs 'Score Range' bar graph", "'No of Students' vs 'Score Range' bar graph is not displayed");
            }

            //Expected - 5 : # "Release Grade for All" link should not be displayed.
            if (driver.findElements(By.xpath("//div[@title='Release Grade for All']")).size() != 0) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link should is displayed");
            }

            //Expected - 6 : # "Grades Released" link should be displayed
            //Expected - 7 : # "Grades Released" link should be greyed out
            currentAssignments.getViewGrades_link().click();
            Thread.sleep(5000);
            if (!assignmentResponsesPage.label_gradesReleased.isDisplayed()) {
                CustomAssert.fail("Validate 'Grades Released' link", "\"Grades Released\" link is not displayed");
            }


        } catch (Exception e) {
            Assert.fail("Exception in the test method 'validateAssigningAdaptivePracticeAssessmentToClassSection' in the class 'AssignmentFlow'", e);
        }
    }


    @Test(priority = 6, enabled = true)
    public void validateGradableQuestionAssignmentWithGradeReleaseOption1ExtendAfterDueDateDisabled() {
        try {
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate student to be able to access any assignment ", "As a new student, I should be able to access any assignment till due date even if all the students have submitted the assignment for policy 1 , policy 3* or ORION assignment", "info");

            String instDataIndex = "327";
            String studDataIndex1 = "327_1";
            String studDataIndex2 = "327_2";
            String studDataIndex3 = "327_3";

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", instDataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);

            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);
            Gradebook gradebook = PageFactory.initElements(driver, Gradebook.class);

            new LoginUsingLTI().ltiLogin(instDataIndex);
            new LoginUsingLTI().ltiLogin(studDataIndex1);
            new LoginUsingLTI().ltiLogin(studDataIndex2);

            new Assignment().create(Integer.parseInt(instDataIndex));//Create an assignment

            new LoginUsingLTI().ltiLogin(instDataIndex);//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "", instDataIndex);//till save policy*//**//**//**//*
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(Integer.parseInt(instDataIndex));//assign to student

            new LoginUsingLTI().ltiLogin(studDataIndex1);//login as student 1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

            new LoginUsingLTI().ltiLogin(studDataIndex2);//login as student 2
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

            //Row No - 327 : 1. Add a new student user to he class section
            //Expected - # New student should be successfully added to the class section
            new LoginUsingLTI().ltiLogin(studDataIndex3);//login as student 3
            String givenName = ReadTestData.readDataByTagName("", "givenname", studDataIndex3);
            System.out.println("givenName : " + givenName);
            if (!dashboard.getUserName().getText().trim().contains(givenName)) {
                CustomAssert.fail("Validate Addition of new student", "Addition of new student to the class section is not successful");
            }

            /*Row No - 328 : "2. Navigate to Assignments page
            3. Verify the Assignment assigned"*/
            //Expected - # Assignment assigned should be delivered to the student

            new Navigator().NavigateTo("Assignments"); //navigate to Assignments page

            CustomAssert.assertEquals(currentAssignments.assignment_Status.getText().trim(), "Your Status: Not Started", "Validate “Not Started” status", "Status of class is  Not Started", "Status of class is not  Not Started");

            boolean isPresent;
            try {
                WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getAssignmentName());
                isPresent = true;
            } catch (Exception e) {
                isPresent = false;
            }
            CustomAssert.assertEquals(isPresent, true, "Verify Assignment Entry", "Assignment should be displayed in Assignment page", "Assignment should not be displayed in Assignment page");

            //Expected - 2 : # 'Gradable' tag should be displayed beside the assignment name
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments page

            CustomAssert.assertEquals(currentAssignments.gradable_icon.isDisplayed(), true, "Validating Gradable icon", "Gradable icon is displayed along with the assignment name in Class Assignments page", "Gradable icon is displayed along with the assignment name in Class Assignments page");

            //Expected - 3 : # Policy name should be displayed in Assignment Policy section
            //Expected - 4 : # Assignment Policy section should be dislayed below the assignment name
            assignments.policyName.click();
            CustomAssert.assertEquals(assignments.policyHeader.getText().trim(), "Assignment Policies", "Validating Policy name", "Policy name is displayed in Assignment Policy section", "Policy name is not displayed in Assignment Policy section");

            //Expected - 5 : # Due date for the assignment should be displayed
            String dueDate = ReadTestData.readDataByTagName("", "duedate", instDataIndex);
            courseOutline.getClosePopUp().click();
            if (!assignments.getOriginalDueDate().getText().trim().contains(dueDate)) {
                CustomAssert.fail("Validate Due date for the assignment", "Due date for the assignment is not displayed");
            }

            //Expected - 6 : # Status of the assignment should be "Not Started"
            CustomAssert.assertEquals(currentAssignments.assignment_Status.getText().trim(), "Your Status: In Progress", "Validate “Not Started” status", "Status of class is  Not Started", "Status of class is not  Not Started");


            //Row No - 335 : 4. Click on the assignment name
            //Expected - 1 : # Student should be able to click on the assignment name
            //Expected 2: # Student should be navigated to Assignment Question page
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", instDataIndex);
            if (driver.findElements(By.cssSelector("span[title = '" + assessmentName + "']")).size() == 0) {
                CustomAssert.fail("Validate assignment Name", "Student is not navigated to Assignment Question page");
            }

            //Row No - 337 : 5. Attempt the Questions in the assignment
            //Expected : # Student should be able to attempt the questions of the assignment
            //Expected - # Performance Summary should get generated for the assignment
            new LoginUsingLTI().ltiLogin(studDataIndex3);//login as student 3
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments page
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment
            CustomAssert.assertEquals(performanceSummary.titlePerformanceSummary.getText().trim(), "Performance Summary", "Validate Assignment finished by Student", "Performance Summary is generated for the assignment", "Performance Summary is not generated for the assignment");

            //Expected - # Performance by Question report should get generated
            CustomAssert.assertEquals(performanceSummary.performanceByQuestionWrapper.isDisplayed(), true, "Validate Performance by Question report", "Performance by Question report is generated", "Performance by Question report is not generated");

            //Expected - # Question cards should be displayed in the Performance Summary page


            //Row No - 342 : 8. Click on any of the Question card
            //Expected - # Question details should be displayed
            assignments.questionListInQuestionCard.get(0).click();
            if (driver.findElements(By.cssSelector("span[title = '" + assessmentName + "']")).size() == 0) {
                CustomAssert.fail("Validate Question cards", "Question cards is not displayed in the Performance Summary page");
            }

            /*Row No - 343 : "9. Navigate to Assignments page
            10. Verify the Assignments details"*/
            //Expected 1: - # Score should be displayed for the assignment
            new Navigator().NavigateTo("Assignments");
            String score = assignments.getScore().getText();
            String expected = "Score (2/2)";
            CustomAssert.assertEquals(score, expected, "Verify score", "score is getting updated", "score  is not " + expected + "");

            //Expected - 2 : # Overall score for the student get updated accordingly
            CustomAssert.assertEquals(gradebook.getOverallScoreStudentSide().getText(), "100%", "Validate Overall Score percentage", "Overall Score percentage is displayed", "Overall Score percentage is ot displayed properly");

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'validateGradableQuestionAssignmentWithGradeReleaseOption1ExtendAfterDueDateDisabled' in the class 'AssignmentFlow'", e);
        }
    }


    @Test(priority = 7, enabled = true)
    public void validateGradableQuestionAssignmentHavingNoManuallyGradedWithGradeReleaseOption3ExtendAfterDueDateDisabled() {
        try {
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate gradable Question Assignment", "Validate gradable Question Assignment having no manually graded with Grade Release Option 3 Extend After Due Date Disabled", "info");

            /*Pre condition - "1. Class section should have 2 students S1, S2
            2.  Instructor should have assigned auto gradable question assignment to the class section with grade release option-3 and extend after due date disabled
            3. Students S1 and S2 should have completed the assignment
            4. Due date should not have expired"*/

            String instDataIndex = "346";
            String studDataIndex1 = "346_1";
            String studDataIndex2 = "346_2";
            String studDataIndex3 = "346_3";

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", instDataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);

            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);
            Gradebook gradebook = PageFactory.initElements(driver, Gradebook.class);

            new LoginUsingLTI().ltiLogin(instDataIndex);
            new LoginUsingLTI().ltiLogin(studDataIndex1);
            new LoginUsingLTI().ltiLogin(studDataIndex2);

            new Assignment().create(Integer.parseInt(instDataIndex));//Create an assignment

            new LoginUsingLTI().ltiLogin(instDataIndex);//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "", instDataIndex);//till save policy
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(Integer.parseInt(instDataIndex));//assign to student

            new LoginUsingLTI().ltiLogin(studDataIndex1);//login as student 1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

            new LoginUsingLTI().ltiLogin(studDataIndex2);//login as student 2
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

            //Row No - 346 : 1. Add a new student user to he class section
            //Expected - # New student should be successfully added to the class section
            new LoginUsingLTI().ltiLogin(studDataIndex3);//login as student 3
            String givenName = ReadTestData.readDataByTagName("", "givenname", studDataIndex3);
            System.out.println("givenName : " + givenName);
            if (!dashboard.getUserName().getText().trim().contains(givenName)) {
                CustomAssert.fail("Validate Addition of new student", "Addition of new student to the class section is not successful");
            }

            /*Row No - 347 : "2. Navigate to Assignments page
            3. Verify the Assignment assigned"*/
            //Expected - # Assignment assigned should be delivered to the student

            new Navigator().NavigateTo("Assignments"); //navigate to Assignments page

            CustomAssert.assertEquals(currentAssignments.assignment_Status.getText().trim(), "Your Status: Not Started", "Validate “Not Started” status", "Status of class is  Not Started", "Status of class is not  Not Started");

            boolean isPresent;
            try {
                WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getAssignmentName());
                isPresent = true;
            } catch (Exception e) {
                isPresent = false;
            }
            CustomAssert.assertEquals(isPresent, true, "Verify Assignment Entry", "Assignment should be displayed in Assignment page", "Assignment should not be displayed in Assignment page");

            //Expected - 2 : # 'Gradable' tag should be displayed beside the assignment name
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments page

            CustomAssert.assertEquals(currentAssignments.gradable_icon.isDisplayed(), true, "Validating Gradable icon", "Gradable icon is displayed along with the assignment name in Class Assignments page", "Gradable icon is displayed along with the assignment name in Class Assignments page");

            //Expected - 3 : # Policy name should be displayed in Assignment Policy section
            //Expected - 4 : # Assignment Policy section should be dislayed below the assignment name
            assignments.policyName.click();
            CustomAssert.assertEquals(assignments.policyHeader.getText().trim(), "Assignment Policies", "Validating Policy name", "Policy name is displayed in Assignment Policy section", "Policy name is not displayed in Assignment Policy section");

            //Expected - 5 : # Due date for the assignment should be displayed
            String dueDate = ReadTestData.readDataByTagName("", "duedate", instDataIndex);
            courseOutline.getClosePopUp().click();
            if (!assignments.getOriginalDueDate().getText().trim().contains(dueDate)) {
                CustomAssert.fail("Validate Due date for the assignment", "Due date for the assignment is not displayed");
            }

            //Expected - 6 : # Status of the assignment should be "Not Started"
            CustomAssert.assertEquals(currentAssignments.assignment_Status.getText().trim(), "Your Status: In Progress", "Validate “Not Started” status", "Status of class is  Not Started", "Status of class is not  Not Started");


            //Row No - 354 : 4. Click on the assignment name
            //Expected - 1 : # Student should be able to click on the assignment name
            //Expected 2: # Student should be navigated to Assignment Question page
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", instDataIndex);
            if (driver.findElements(By.cssSelector("span[title = '" + assessmentName + "']")).size() == 0) {
                CustomAssert.fail("Validate assignment Name", "Student is not navigated to Assignment Question page");
            }

            //Row No - 356 : 5. Attempt the Questions in the assignment
            //Expected : # Student should be able to attempt the questions of the assignment
            //Expected - # Performance Summary should get generated for the assignment
            new LoginUsingLTI().ltiLogin(studDataIndex3);//login as student 3
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments page
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment
            CustomAssert.assertEquals(performanceSummary.titlePerformanceSummary.getText().trim(), "Performance Summary", "Validate Assignment finished by Student", "Performance Summary is generated for the assignment", "Performance Summary is not generated for the assignment");

            //Expected - # Performance by Question report should get generated
            CustomAssert.assertEquals(performanceSummary.performanceByQuestionWrapper.isDisplayed(), true, "Validate Performance by Question report", "Performance by Question report is generated", "Performance by Question report is not generated");

            //Expected - # Question cards should be displayed in the Performance Summary page


            //Row No - 361 : 8. Click on any of the Question card
            //Expected - # Question details should be displayed
            assignments.questionListInQuestionCard.get(0).click();
            if (driver.findElements(By.cssSelector("span[title = '" + assessmentName + "']")).size() == 0) {
                CustomAssert.fail("Validate Question cards", "Question cards is not displayed in the Performance Summary page");
            }

            /*Row No - 343 : "9. Navigate to Assignments page
            10. Verify the Assignments details"*/
            //Expected 1: - # Score should be displayed for the assignment
            new Navigator().NavigateTo("Assignments");
            String score = assignments.getScore().getText();
            String expected = "Score (2/2)";
            CustomAssert.assertEquals(score, expected, "Verify score", "score is getting updated", "score  is not " + expected + "");

            //Expected - 2 : # Overall score for the student get updated accordingly
            CustomAssert.assertEquals(gradebook.getOverallScoreStudentSide().getText(), "100%", "Validate Overall Score percentage", "Overall Score percentage is displayed", "Overall Score percentage is ot displayed properly");


        } catch (Exception e) {
            Assert.fail("Exception in the test method 'gradableQuestionAssignmentHavingNoManuallyGradedWithGradeReleaseOption3ExtendAfterDueDateDisabled' in the class 'AssignmentFlow'", e);
        }
    }


    @Test(priority = 8, enabled = true)
    public void assigningAssignmentWithGradeReleaseOption2_AddingStudentAfterAllStudentSubmitsAssignment() {
        try {
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate assignment with grade release option 2", "Validate Assigning assignment with grade release option 2 and adding student after all student submits the assignment", "info");

            /*Precondition - "1. Class section should have students S1 and S2
            2. Instructor should have assigned question assignment with grade release option 2
            3. All student should have submitted the assignment
            4. Due date should not have expired "*/

            String instDataIndex = "395";
            String studDataIndex1 = "395_1";
            String studDataIndex2 = "395_2";
            String studDataIndex3 = "395_3";
            String studDataIndex4 = "395_4";

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", instDataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);

            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);
            Gradebook gradebook = PageFactory.initElements(driver, Gradebook.class);

            new LoginUsingLTI().ltiLogin(instDataIndex);
            new LoginUsingLTI().ltiLogin(studDataIndex1);
            new LoginUsingLTI().ltiLogin(studDataIndex2);


            new Assignment().create(Integer.parseInt(instDataIndex));//Create an assignment

            new LoginUsingLTI().ltiLogin(instDataIndex);//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on due date", "", "", "");//till save policy
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(Integer.parseInt(instDataIndex));//assign to student

            new LoginUsingLTI().ltiLogin(studDataIndex1);//login as student 1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

            new LoginUsingLTI().ltiLogin(studDataIndex2);//login as student 2
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment


            /*Row No - "1. Login as instructor
            2. Navigate to Class Assignments page
            3. Verify the Assignment details"*/
            //Expected - 1 : # Assignment status should be "Needs Grading"
            new LoginUsingLTI().ltiLogin(instDataIndex);
            new Navigator().NavigateTo("Class Assignments");//navigate to assignments
            CustomAssert.assertEquals(currentAssignments.assignment_Status.getText().trim(), "Class Status:  Needs Grading", "Validate “Class Status:  Needs Grading” status", "Status of class is  Class Status:  Needs Grading", "Status of class is not  Class Status:  Needs Grading");


            /*Row No - 397 : "4. Click on View Student Responses option
            5. Verify the Assignment details"*/
            //Expected - # Assignment status should be "Needs Grading"
            currentAssignments.getViewGrade_link().click();//click on view student responses
            CustomAssert.assertEquals(currentAssignments.assignmentStatus.get(1).getText().trim(), "Class Status:  Needs Grading", "Validate “Class Status:  Needs Grading” status", "Status of class is  Class Status:  Needs Grading", "Status of class is not  Class Status:  Needs Grading");

            //Expected - 2: # Release Grade for All link should get enabled
            if (driver.findElements(By.cssSelector("div[title='Release Grade for All']")).size() == 0) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link should is not displayed");
            }


            /*Row No - 399 : "6. Add a new student to the class section
            7. Login as instructor
            8. Navigate to Class Assignments page
            9. Verify the Assignments details"*/
            //Expected - # Assignment status should be "Available for student"
            new LoginUsingLTI().ltiLogin(studDataIndex3);
            new LoginUsingLTI().ltiLogin(instDataIndex);
            new Navigator().NavigateTo("Class Assignments");//navigate to assignments
            CustomAssert.assertEquals(currentAssignments.assignment_Status.getText().trim(), "Class Status:  Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");


            /*Row No - 401 : "10. Click on View Students Response option
            11. Verify the Assignment details"*/
            //Expected - # Assignment status should be "Available for student"
            currentAssignments.getViewGrade_link().click();//click on view student responses
            CustomAssert.assertEquals(currentAssignments.assignmentStatus.get(1).getText().trim(), "Class Status:  Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected - # New student should be in Not started section
            CustomAssert.assertEquals(assignments.notStartedCount.getText().trim(), "1", "Validate New student", "Student is in Not started section", "Student is not in Not started section");

            //Expected - # Release Grade for All link should get disabled and greyed out
            if (!assignmentResponsesPage.gradeBook_Box.isDisplayed()) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link is not greyed out");
            }


            /*Row No - 404 : "12. Login as new student
            13. Navigate to Assignments page
            14. Click on the assignment name"*/
            //Expected - # Student should be able to navigate to Question Assignment page
            //Expected - 2: # New student be able to finish the assignment
            new LoginUsingLTI().ltiLogin(studDataIndex4);
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment
        } catch (Exception e) {
            Assert.fail("Exception in the test method 'assigningAssignmentWithGradeReleaseOption2_AddingStudentAfterAllStudentSubmitsAssignment' in the class 'AssignmentFlow'", e);
        }
    }

    //This below method should work when the Bug logged: 23888 is resolved
    @Test(priority = 9, enabled = true)
        public void assigningAssignmentWithManualQuestionsWithGradeReleaseOption3 () {
            try {
                WebDriver driver = Driver.getWebDriver();
                ReportUtil.log("Validate assigning Assignment With Manual Questions with Grade Release Option3", "Validate Assigning assignment with grade release option 3", "info");

                /*Precondition - "1. Class section should have students S1 and S2
                2. Instructor should have assigned question assignment with grade release option 2
                3. All student should have submitted the assignment
                4. Due date should not have expired "*/

                String instDataIndex = "406";
                String studDataIndex1 = "406_1";
                String studDataIndex2 = "406_2";
                String studDataIndex3 = "406_3";
                String studDataIndex4 = "406_4";

                String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", instDataIndex);
                CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
                Assignments assignments = PageFactory.initElements(driver, Assignments.class);
                CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);

                Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
                AssessmentResponses assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);
                AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
                MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
                PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);
                Gradebook gradebook = PageFactory.initElements(driver, Gradebook.class);

                new LoginUsingLTI().ltiLogin(instDataIndex);
                new LoginUsingLTI().ltiLogin(studDataIndex1);
                new LoginUsingLTI().ltiLogin(studDataIndex2);

                new Assignment().create(Integer.parseInt(instDataIndex));//Create an assignment

                new LoginUsingLTI().ltiLogin(instDataIndex);//login as instructor
                new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
                new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "",instDataIndex);//till save policy
                new Navigator().NavigateTo("Assignments");//navigate to Assignments
                new Assignment().assignToStudent(Integer.parseInt(instDataIndex));//assign to student

                new LoginUsingLTI().ltiLogin(studDataIndex1);//login as student 1
                new Navigator().NavigateTo("Assignments");//navigate to assignments
                new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

                new LoginUsingLTI().ltiLogin(studDataIndex2);//login as student 2
                new Navigator().NavigateTo("Assignments");//navigate to assignments
                new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

                /*"1. Login as instructor
                2. Navigate to Class Assignments page
                3. Verify the Assignment details"*/
                //#Expected - 1 : Assignment status should be "Needs Grading"
                new LoginUsingLTI().ltiLogin(instDataIndex);
                new Navigator().NavigateTo("Class Assignments");//navigate to assignments
                CustomAssert.assertEquals(currentAssignments.assignment_Status.getText().trim(), "Class Status:  Needs Grading", "Validate “Class Status:  Needs Grading” status", "Status of class is  Class Status:  Needs Grading", "Status of class is not  Class Status:  Needs Grading");

                //Expected - 2 : # Student should get added in submitted section
                CustomAssert.assertEquals(currentAssignments.getSubmittedBoxCount().getText().trim(), "2", "Validate New student", "Student is in Submitted section", "Student is not in Submitted section");

                /*"4. Click on View Students Response option
                5. Verify the Assignment details"*/

                //# Expected - 1: Assignment status should be "Needs Grading"
                currentAssignments.getViewGrade_link().click();//click on view student responses
                CustomAssert.assertEquals(currentAssignments.assignmentStatus.get(1).getText().trim(), "Class Status:  Needs Grading", "Validate “Class Status:  Needs Grading” status", "Status of class is  Class Status:  Needs Grading", "Status of class is not  Class Status:  Needs Grading");

                //Expected - 2 : # Student should get added in submitted section
                for(int a=0;a<currentAssignments.submitted_boxWithCount.size();a++)
                    System.out.println("12345 : " + currentAssignments.submitted_boxWithCount.get(a).getText());
                CustomAssert.assertEquals(currentAssignments.submitted_boxWithCount.get(1).getText().trim(), "2\n" + "Submitted", "Validate New student", "Student is in Submitted section", "Student is not in Submitted section");

                //Expected - 3: # Release Grade for All link should get enabled
                if (driver.findElements(By.cssSelector("div[title='Release Grade for All']")).size() == 0) {
                    CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link should is not displayed");
                }

                //Row No - 411 : 6. Provide grades for all manually graded questions
                new LoginUsingLTI().ltiLogin(instDataIndex);
                new Navigator().NavigateTo("Class Assignments");//navigate to assignments
                currentAssignments.getViewGrade_link().click();//click on view student responses
                Thread.sleep(5000);
                WebDriverUtil.mouseHover(By.className("idb-gradebook-assignment-username"));
                Thread.sleep(2000);
                currentAssignments.getEnterGrade_link().click();
                driver.switchTo().activeElement().clear();
                driver.switchTo().activeElement().sendKeys("2");
                driver.findElement(By.xpath("/html")).click();
                //Expected -1 : # Status of the Assignment should not get Graded
                CustomAssert.assertNotEquals(currentAssignments.status.getText().trim(), "Graded", "Validate “Graded” status", "Status of class is not 'Graded'", "Status of class is 'Graded'");

                //Expected - 2 : # Student status should get added in graded section
                CustomAssert.assertEquals(currentAssignments.reviewed_boxWithCount.get(0).getText().trim(), "1\n" + "Submitted", "Validate New student", "Student is in Submitted section", "Student is not in Submitted section");

                //Expected - 3 ; # Release Grade for All button should get enabled
                if (driver.findElements(By.cssSelector("div[title='Release Grade for All']")).size() == 0) {
                    CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link should is not displayed");
                }


               /*Row No - 414 : "7. Add a new student to the class section
                8. Login as instructor
                9. Navigate to Class Assignments page
                10. Verify the Assignments details"*/
                //Expected - # Assignment status should be "Available for student"
                new LoginUsingLTI().ltiLogin(studDataIndex3);
                new LoginUsingLTI().ltiLogin(instDataIndex);
                new Navigator().NavigateTo("Class Assignments");//navigate to assignments
                CustomAssert.assertEquals(currentAssignments.assignment_Status.getText().trim(), "Class Status:  Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

                //Expected 2 : # New student should be in Not started section
                CustomAssert.assertEquals(assignments.notStartedCount.getText().trim(), "1", "Validate New student", "Student is in Not started section", "Student is not in Not started section");


               /*Row No - 416: "11. Click on View Student Responses option
                12. Verify the Assignment details"*/
                //Expected - # Assignment status should be "Available for student"
                currentAssignments.getViewGrade_link().click();//click on view student responses
                CustomAssert.assertEquals(currentAssignments.assignment_Status.getText().trim(), "Class Status:  Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");


                //Expected - 2 : # New student should be in Not started section
                CustomAssert.assertEquals(assignments.notStartedCount.getText().trim(), "1", "Validate New student", "Student is in Not started section", "Student is not in Not started section");


                //Expected - 3 : # Release Grade for All link should get disabled and greyed out
                if (!assignmentResponsesPage.gradeBook_Box.isDisplayed()) {
                    CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link is not greyed out");
                }

                /*Row No - 419 : "13. Login as new student
                14. Navigate to Assignments page
                15. Click on the assignment name"*/
                //Expected - # Student should be able to navigate to Question Assignment page
                //Expected - # New student be able to finish the assignment
                new LoginUsingLTI().ltiLogin(studDataIndex4);
                new Navigator().NavigateTo("Assignments");//navigate to assignments
                new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

            } catch (Exception e) {
                Assert.fail("Exception in the test method 'assigningAssignmentWithManualQuestionsWithGradeReleaseOption3' in the class 'AssignmentFlow'", e);
            }
        }



    @Test(priority = 10, enabled = true)
    public void assigningAssignmentWithManualQuestionsWithGradeReleaseOption4 () {
        try {
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate assigning Assignment With Manual Questions with Grade Release Option4", "Validate Assigning assignment with grade release option 4", "info");

            /*Precondition - "1. Class section should have students S1 and S2
            2. Instructor should have assigned question assignment with grade release option 2
            3. All student should have submitted the assignment
            4. Due date should not have expired "*/

            String instDataIndex = "422";
            String studDataIndex1 = "422_1";
            String studDataIndex2 = "422_2";
            String studDataIndex3 = "422_3";
            String studDataIndex4 = "422_4";

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", instDataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);

            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);
            Gradebook gradebook = PageFactory.initElements(driver, Gradebook.class);

            new LoginUsingLTI().ltiLogin(instDataIndex);
            new LoginUsingLTI().ltiLogin(studDataIndex1);
            new LoginUsingLTI().ltiLogin(studDataIndex2);

            new Assignment().create(Integer.parseInt(instDataIndex));//Create an assignment

            new LoginUsingLTI().ltiLogin(instDataIndex);//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release explicitly by the instructor", "", "", "",instDataIndex);//till save policy
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(Integer.parseInt(instDataIndex));//assign to student

            new LoginUsingLTI().ltiLogin(studDataIndex1);//login as student 1
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

            new LoginUsingLTI().ltiLogin(studDataIndex2);//login as student 2
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

                /*"1. Login as instructor
                2. Navigate to Class Assignments page
                3. Verify the Assignment details"*/
            //#Expected - 1 : Assignment status should be "Needs Grading"
            new LoginUsingLTI().ltiLogin(instDataIndex);
            new Navigator().NavigateTo("Class Assignments");//navigate to assignments
            CustomAssert.assertEquals(currentAssignments.assignment_Status.getText().trim(), "Class Status:  Needs Grading", "Validate “Class Status:  Needs Grading” status", "Status of class is  Class Status:  Needs Grading", "Status of class is not  Class Status:  Needs Grading");

            //Expected - 2 : # Student should get added in submitted section
            CustomAssert.assertEquals(currentAssignments.getSubmittedBoxCount().getText().trim(), "2", "Validate New student", "Student is in Submitted section", "Student is not in Submitted section");

                /*"4. Click on View Students Response option
                5. Verify the Assignment details"*/

            //# Expected - 1: Assignment status should be "Needs Grading"
            currentAssignments.getViewGrade_link().click();//click on view student responses

            if(driver.findElements(By.cssSelector("span[title = 'Needs Grading']")).size()==0){
                CustomAssert.fail("Validate “Class Status:  Needs Grading” status", "Status of class is not Class Status:  Needs Grading");
            }

            //Expected - 2 : # Student should get added in submitted section
            for(int a=0;a<currentAssignments.submitted_boxWithCount.size();a++)
                System.out.println("12345 : " + currentAssignments.submitted_boxWithCount.get(a).getText());
            CustomAssert.assertEquals(currentAssignments.submitted_boxWithCount.get(1).getText().trim(), "2\n" + "Submitted", "Validate New student", "Student is in Submitted section", "Student is not in Submitted section");

            //Expected - 3: # Release Grade for All link should get enabled
            if (driver.findElements(By.cssSelector("div[title='Release Grade for All']")).size() == 0) {
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link should is not displayed");
            }

            /*Row No - 414 : "7. Add a new student to the class section
            8. Login as instructor
            9. Navigate to Class Assignments page
            10. Verify the Assignments details"*/
            //Expected - # Assignment status should be "Available for student"
            new LoginUsingLTI().ltiLogin(studDataIndex3);
            new LoginUsingLTI().ltiLogin(instDataIndex);
            new Navigator().NavigateTo("Class Assignments");//navigate to assignments
            CustomAssert.assertEquals(currentAssignments.assignment_Status.getText().trim(), "Class Status:  Available for Students", "Validate “Available for Students” status", "Status of class is Available for Students", "Status of class is not Available for Students");

            //Expected 2 : # New student should be in Not started section
            CustomAssert.assertEquals(currentAssignments.submittedCount.getText().trim(), "1", "Validate New student", "Student is in Not started section", "Student is not in Not started section");


               /*Row No - 416: "11. Click on View Student Responses option
                12. Verify the Assignment details"*/
            //Expected - # Assignment status should be "Available for student"
            currentAssignments.getViewGrade_link().click();//click on view student responses
            if(driver.findElements(By.cssSelector("span[title = 'Available for Students']")).size()==0){
                CustomAssert.fail("Validate “Class Status:  Available for Students” status", "Status of class is not Class Status:  Available for Students");
            }

            //Expected - 2 : # New student should be in Not started section
            CustomAssert.assertEquals(assignments.notStartedCount.getText().trim(), "1", "Validate New student", "Student is in Not started section", "Student is not in Not started section");


            //Expected - 3 : # Release Grade for All link should get disabled and greyed out
            if(assignmentResponsesPage.gradeBook_BoxList.size()!=0){
                CustomAssert.fail("Validate \"Release Grade for All\" link", "Release Grade for All\" link is not greyed out");
            }

                /*Row No - 419 : "13. Login as new student
                14. Navigate to Assignments page
                15. Click on the assignment name"*/
            //Expected - # Student should be able to navigate to Question Assignment page
            //Expected - # New student be able to finish the assignment
            new LoginUsingLTI().ltiLogin(studDataIndex4);
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(instDataIndex));//submit assignment

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'assigningAssignmentWithManualQuestionsWithGradeReleaseOption3' in the class 'AssignmentFlow'", e);
        }
    }
}