package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.assignmentflow;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.apache.xpath.SourceTree;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Mukesh on 11/25/14.
 */
public class AssignmentFlowActivity extends Driver {
    @Test(priority = 1, enabled = true)
    public void assignmentFlowActivity() {
        try {

            //Tc row no 81
            String appendChar = "a3";
            int index = 81;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);
            new Navigator().navigateTo("assignment");//Navigate to assignment
            String assignmentName = new TextFetch().textfetchbycssselector("h4.as-title");
            if (!assignmentName.contains("IT1_assignmentFlow_Assessment81"))
                Assert.fail("Student is not able to access the Assignment from Assignment page");
            new Navigator().navigateTo("dashboard");
            String assignmentName1 = new TextFetch().textfetchbycssselector("h4.as-title");
            if (!assignmentName1.contains("IT1_assignmentFlow_Assessment81"))
                Assert.fail("Student is not able to access the Assignment from Dashboard page");

            //TC row no 82
            String dueDate = new TextFetch().textfetchbycssselector("div[class='col-xs-12 col-sm-5']").substring(11);
            Calendar calendar = Calendar.getInstance();
            int month = (calendar.get((Calendar.MONTH)));
            int year = (calendar.get(Calendar.YEAR));
            String actualDate = new Calender().getCurrentMonthName(month + 1) + " 28, " + year + " 12:00 AM";
            Assert.assertEquals(dueDate, actualDate, "Due date is not proper");

            //Tc row no 83
            String status = new TextFetch().textfetchbycssselector("span[class='status-label cinnabar']");//get the status of assignment
            Assert.assertEquals(status, "Not Started", "Assignment's status is not \"Not Started\"");
            //Tc row no 84
            new Navigator().navigateTo("assignment");//Navigate to assignment
            new Click().clickBycssselector("h4.as-title");//click on the assignment
            new Click().clickByListClassName("true-false-student-answer-select", 0);//click on the true Question
            new Click().clickBycssselector("div[class='lsMobile-sprite quit-assignment-clock-icon']");//click on the x icon
            String inProgressStatus = new TextFetch().textfetchbycssselector("span[class='status-label tuliptree']");
            Assert.assertEquals(inProgressStatus, "In Progress", "Assignment's status is not \"In Progress\" ");

            new Click().clickBycssselector("h4.as-title");//click on the assignment
            Thread.sleep(2000);
            new Click().clickByListClassName("true-false-student-answer-select", 0);//click on the true Question
            Thread.sleep(2000);
            new Click().clickByid("as-take-next-question");//click on next
            new Click().clickByid("as-take-next-question");//click on submit
            Thread.sleep(2000);
            new Click().clickBycssselector("span[class='btn sty-blue submit-button']");//click on submit
            new Navigator().navigateTo("assignment");//Navigate to assignment
            String turnedIn = new TextFetch().textfetchbycssselector("span[class='status-label mantis']");
            Assert.assertEquals(turnedIn, "Turned In", "Assignment status is not \"Turned In\"");
            new Navigator().logout();//logout

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().releaseGrades(index, "Release Grade");//click on  Release Grade
            new Navigator().logout();//logout

            new Login().loginAsStudent(appendChar, index);
            new Navigator().navigateTo("assignment");//Navigate to assignment
            String graded = new TextFetch().textfetchbycssselector("span[class='status-label mantis']");
            if (!graded.contains("Graded"))
                Assert.fail("Status is not displayed as 'Graded'");

            String marksObtained = new TextFetch().textfetchbycssselector("span[class='view-user-question-performance-score views-number block text-center f24']");
            if (!marksObtained.equals("1.0"))
                Assert.fail("Marks obtained is not displayed correctly");

            String totalMarks = new TextFetch().textfetchbycssselector("span[class='views-number block text-center']");
            if (!totalMarks.equals("2"))
                Assert.fail("Total marks is not displayed correctly");
        } catch (Exception e) {
            Assert.fail("Exception in test case  assignmentFlowActivity of class AssignmentFlowActivity", e);
        }
    }


    @Test(priority = 2, enabled = true)
    public void teacherAssignmentSummary() {
        try {

            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            //Tc row no 88
            String appendChar = "a";
            int index = 88;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().navigateTo("assignment");//Navigate to assignment
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().navigateTo("assignment");//Navigate to assignment

            new Click().clickByid("as-assignment-filter-viewall");//click on the All status

            //Tc row no 90 "Following should be available for each assignment:
            //1.Assignment Name- On clicking it, the assignment page should open up
            Thread.sleep(2000);
            new Navigator().navigateTo("assignment");//Navigate to assignment
            new Click().clickBycssselector("h4[class='as-title as-label']");//click on the assignment name
            String assignmentPage = new TextFetch().textfetchbycssselector("h3[class='lsm-assignment-header-title ellipsis']");
            if (!assignmentPage.contains("IT1_assignmentFlow_Assessment88"))
                Assert.fail("Assignment page is not open up");
            new Click().clickByclassname("assessments-back-link");//click on back button

            //Tc row 91
            //2.Name of teacher at "created by field"
            assignments.more.click();//Click on more

            String delete = new TextFetch().textfetchbyclass("as-delete");
            Assert.assertEquals(delete, "Delete", "  Delete Assignment  is not visible");

            //Tc row no 94
            String dueDate = new TextFetch().textfetchbycssselector("div[class='col-xs-12 col-sm-5']");
            Calendar calendar = Calendar.getInstance();
            int month = (calendar.get((Calendar.MONTH)));
            int year = (calendar.get((Calendar.YEAR)));
            String actualDate = "Due Date : " + new Calender().getCurrentMonthName(month + 1) + " 28, " + year + " 12:00 AM";
            Assert.assertEquals(dueDate, actualDate, "Due date is not proper");

            //Tc row no 95
            String anyOfStatus = new TextFetch().textfetchbyclass("status-label");
            Assert.assertEquals(anyOfStatus, "Awaiting Submission", "No Assignment status is observed");

            //Tc row no 96
            String assigned = new TextFetch().textfetchbylistXpath("//div[@class='count-label']", 0);
            Assert.assertEquals(assigned, "Assigned", "Not Started option is not available");

            String turnedIn = new TextFetch().textfetchbylistXpath("//div[@class='count-label']", 1);
            Assert.assertEquals(turnedIn, "Turned In", "Turned In option is not available");

            String graded = new TextFetch().textfetchbylistXpath("//div[@class='count-label']", 2);
            Assert.assertEquals(graded, "Graded", "Graded option is not available");

        } catch (Exception e) {
            Assert.fail("Exception in test case  teacherAssignmentSummary of class AssignmentFlowActivity", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void actionsActivityOfTeacher() {
        try {

            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            //Tc row no 88
            String appendChar = "a";
            int index = 97;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().navigateTo("assignment");//Navigate to assignment
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().navigateTo("assignment");//Navigate to assignment

            String updatedueDate = new TextFetch().textfetchbyclass("as-update");
            Assert.assertEquals(updatedueDate, "Update", " Update Assignment is not visible");
            String grade = new TextFetch().textfetchbyclass("as-response");
            Assert.assertEquals(grade, "View Responses", "  Student Responses  is not visible");
            assignments.more.click();//Click on more
            String delete = new TextFetch().textfetchbyclass("as-delete");
            Assert.assertEquals(delete, "Delete", "  Delete Assignment  is not visible");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();//log out

            //Tc row no 98 &  100
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Navigator().navigateTo("assignment");//Navigate to assignment
            if (driver.findElements(By.className("as-update")).size() != 0)
                Assert.fail("update action is displayed");
            new Click().clickByclassname("as-delete");//Click on Delete option present for assessment
            driver.findElement(By.id("delete-confm-txt")).sendKeys("DELETE");//Enter delete in delete confirm box
            new Click().clickBycssselector("div[class='as-modal-yes-btn delete-assignment']");//Click on Yes,Delete button
            Thread.sleep(2000);

            boolean assignmentFound = new BooleanValue().presenceOfElement(index, By.xpath("//h4[@class='as-title as-label']"));
            Assert.assertEquals(assignmentFound, false, "Assignment Present even after instructor deletes it");


        } catch (Exception e) {
            Assert.fail("Exception in test case  actionsActivityOfTeacher of class AssignmentFlowActivity", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void updateActionWithInDueDate() {
        try {

            //Tc row no 88
            String appendChar = "a";
            String appendChar2 = "b";
            int index = 102;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().navigateTo("assignment");//Navigate to assignment
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar2, classCode, index);//create student2
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//logout

            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();//log out

            //Tc row no 98 &  100
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Navigator().navigateTo("assignment");//Navigate to assignment

            String updateDueDate = new TextFetch().textfetchbyclass("as-update");
            Assert.assertEquals(updateDueDate, "Update", " Update Assignment is not visible");

        } catch (Exception e) {
            Assert.fail("Exception in test case  updateActionWithInDueDate of class AssignmentFlowActivity", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void studentResponseBeforeSubmittingAssignment() {
        try {

            //Tc row no 88
            String appendChar = "b";
            int index = 103;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().navigateTo("assignment");//Navigate to assignment
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().navigateTo("assignment");//Navigate to assignment
            new Navigator().logout();//logout
            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Navigator().navigateTo("assignment");//Navigate to assignment

            String grade = new TextFetch().textfetchbyclass("as-response");
            Assert.assertEquals(grade, "View Responses", "Student Responses  is not visible");

        } catch (Exception e) {
            Assert.fail("Exception in test case  studentResponseBeforeSubmittingAssignment of class AssignmentFlowActivity", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void updateActionWithInDueDateForMoreThanOneStudent() {
        try {

            //Tc row no 88
            String appendChar = "a";
            String appendChar2 = "b";
            int index = 104;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().navigateTo("assignment");//Navigate to assignment
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar2, classCode, index);//create student2
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar2, index);//log in as student 2
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Navigator().navigateTo("assignment");//Navigate to assignment

            String grade = new TextFetch().textfetchbyclass("as-response");
            Assert.assertEquals(grade, "View Responses", "Student Responses  is not visible");

            //Tc row no 105
            new Click().clickByclassname("as-response");//click on grade
            String studentResponse = new TextFetch().textfetchbyid("studentResponsesHeader");
            Assert.assertEquals(studentResponse, "STUDENT RESPONSES", "Student Responses  page is not displayed");


        } catch (Exception e) {
            Assert.fail("Exception in test case  updateActionWithInDueDateForMoreThanOneStudent of class AssignmentFlowActivity", e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void publicKeyChecked() {
        try {

            //Tc row no 107
            String appendChar = "c";
            String appendChar2 = "d";
            int index = 107;
            new SignUp().teacher(appendChar, index); //signup as a teacher1
            new School().createWithOnlyName(appendChar, index); //create school
            String classCode = new Classes().createClass(appendChar, index, "Grade 4", "Mathematics", "Math - Common Core");//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            Thread.sleep(2000);
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new SignUp().teacher(appendChar2, index); //signup as a teacher2
            new School().createWithOnlyName(appendChar2, index); //create school
            new Classes().createClass(appendChar, index, "Grade 4", "Mathematics", "Math - Common Core");//create class

            new Navigator().navigateTo("assignment"); //navigate to assignment
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on the Create New Assignment
            new Click().clickByid("search-assessment-with-val");//click on search

            Select select = new Select(driver.findElement(By.id("subjectSelection")));
            select.selectByIndex(0); //select 2nd subject
            Select select1 = new Select(driver.findElement(By.id("gradeSelection")));
            select1.selectByIndex(4);//select a grade

            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

            scrollTo(driver.findElement(By.xpath("//a[contains(text(),'IT1_assignmentFlow_Assessment107')]")));
            Thread.sleep(2000);

            boolean found = false;
            List<WebElement> assignmentName = driver.findElements(By.xpath("//div[@class='font-semi-bold space-15 assign-title']/a"));
            for (WebElement ele : assignmentName) {
                if (ele.getText().contains("IT1_assignmentFlow_Assessment107")) {
                    found = true;
                }
            }
            if (found == false)
                Assert.fail("Teacher 2 is not able to see assignment of teacher 1 assignment");


        } catch (Exception e) {
            Assert.fail("Exception in test case  publicKeyChecked of class AssignmentFlowActivity", e);
        }
    }

    public void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView();", element);
    }

    @Test(priority = 8, enabled = true)
    public void PrivateKeyChecked() {
        try {

            //Tc row no 108
            String appendChar = "a";
            String appendChar2 = "b";
            int index = 108;
            new SignUp().teacher(appendChar, index); //signup as a teacher1
            new School().createWithOnlyName(appendChar, index); //create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new SignUp().teacher(appendChar2, index); //signup as a teacher2
            new School().createWithOnlyName(appendChar2, index); //create school
            new Classes().createClass(appendChar2, index);//create class
            new Navigator().navigateTo("assignment"); //navigate to assignment
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on the Create New Assignment
            new Click().clickByid("search-assessment-with-val");//click on search
            new Assignment().selectCommunity();
            boolean found = false;
            List<WebElement> assignmentName = driver.findElements(By.xpath("//h4[@class='assign-title']"));
            for (WebElement ele : assignmentName) {
                if (ele.getText().contains("IT1_assignmentFlow_Assessment108")) {
                    found = true;
                }
            }
            if (found == true)
                Assert.fail("Teacher 2 is able to see assignment of teacher 1 assignment");

        } catch (Exception e) {
            Assert.fail("Exception in test case  PrivateKeyChecked of class AssignmentFlowActivity", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void gradableOption() {
        try {
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "110");

            //Tc row no 110
            String appendChar = "a";
            int index = 110;
            new SignUp().teacher(appendChar, index); //sign up as a teacher
            new School().createWithOnlyName(appendChar, index); //create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//login as student
            new Navigator().logout();//logout

            new Login().loginAsInstructor(appendChar, index); //login as instructor1
            new Navigator().navigateTo("assignment"); //navigate to assignment
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on the Create New Assignment
            new TextSend().textsendbyid(assessmentname, "new-assessment-name");//Enter assessment name
            new Click().clickByid("create-assessment-with-val");//Click on create button
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//Click on create link

            new Click().clickByid("qtn-true-false-type");//click on true false question

            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("True False Question 107");//type the question
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option
            new TextSend().textsendbyid("True False Solution Text", "content-solution");
            new TextSend().textsendbyid("True False Hint Text", "content-hint");
            new Click().clickByid("saveQuestionDetails1");//click on the save
            assignments.getButton_review().click();//click on the review button
            new Click().clickByid("assessments-use-button");//click on the next button
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).sendKeys("shor");//short label name for due date error
            new Click().clickByXpath("//div[starts-with(@class,'icheckbox_square-green')]/descendant::ins");//click on Gradable Checkbox
            Thread.sleep(5000);
            driver.findElement(By.id("lsm_assignment_due_date")).click();
            Thread.sleep(5000);
            new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
            new Click().clickbylinkText("28");//select future  due date

            new Click().clickByid("lsm_assignment_due_time");//click on Due Time field
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals("12:25 AM")) {
                    time.click();
                    break;
                }
            }
            boolean isSelected = driver.findElement(By.id("as-grade-release-on-submission")).isSelected();
            if (isSelected == false)
                Assert.fail("Gradable “on assigment submission” is not checked by default");
            new Click().clickByXpath("//div[starts-with(@class,'icheckbox_square-green')]/descendant::ins");//click on Gradable Checkbox
            new Click().clickByid("assign-button");//click on the assign
            new Navigator().navigateTo("assignment"); //navigate to assignment
            String gradable = new TextFetch().textfetchbyclass("as-grade");
            Assert.assertEquals(gradable, "GRADABLE", "Gradable assignment is not tagged as gradable on the assignment page for instructor");
            new Navigator().logout();//logout

            new Login().loginAsStudent(appendChar, index);//login as student
            new Navigator().navigateTo("assignment"); //navigate to assignment
            String gradable1 = new TextFetch().textfetchbyclass("as-grade");
            Assert.assertEquals(gradable1, "GRADABLE", "Gradable assignment is not tagged as gradable on the assignment page for Student");

        } catch (Exception e) {
            Assert.fail("Exception in test case  gradableOption of class AssignmentFlowActivity", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void assignmentSubmission() {
        try {

            //Tc row no 112
            String appendChar = "a";
            int index = 112;
            new SignUp().teacher(appendChar, index); //signup as a teacher
            new School().createWithOnlyName(appendChar, index); //create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().navigateTo("assignment"); //navigate to assignment
            String status = new TextFetch().textfetchbycssselector("span[class='status-label mantis']");
            Assert.assertEquals(status, "Graded", "Student is not getting grade immediately after submission");


        } catch (Exception e) {
            Assert.fail("Exception in test case  assignmentSubmission of class AssignmentFlowActivity", e);
        }
    }

    @Test(priority = 11, enabled = true)
    public void explicitlyByTeacher() {
        try {

            //Tc row no 113
            String appendChar = "a";
            int index = 113;
            new SignUp().teacher(appendChar, index); //sign up as a teacher
            new School().createWithOnlyName(appendChar, index); //create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().navigateTo("assignment"); //navigate to assignment
            String status = new TextFetch().textfetchbycssselector("span[class='status-label mantis']");
            if (status.contains("Graded: 2.0/2"))
                Assert.fail("Student is able to view the grades");

        } catch (Exception e) {
            Assert.fail("Exception in test case  explicitlyByTeacher of class AssignmentFlowActivity", e);
        }
    }

    @Test(priority = 12, enabled = true)
    public void explicitlyByTeacherAfterReleaseGrade() {
        try {

            //Tc row no 114
            String appendChar = "a";
            int index = 114;
            new SignUp().teacher(appendChar, index); //signup as a teacher
            new School().createWithOnlyName(appendChar, index); //create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Assignment().submitAssignment(index);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().releaseGrades(index, "Release Grade");//click on  Release Feedback for All
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as student 1
            new Navigator().navigateTo("assignment"); //navigate to assignment
            String status = new TextFetch().textfetchbycssselector("span[class='status-label mantis']");
            Assert.assertEquals(status, "Graded", "Status is not displayed as 'Graded'");

            String marksObtained = driver.findElement(By.cssSelector("span[class='view-user-question-performance-score views-number block text-center f24']")).getText();
            Assert.assertEquals(marksObtained, "2.0", "Marks is not displayed correctly");

            String totalMarks = driver.findElement(By.cssSelector("span[class='views-number block text-center']")).getText();
            Assert.assertEquals(totalMarks, "2", "Total Marks is not displayed correctly");

        } catch (Exception e) {
            Assert.fail("Exception in test case  explicitlyByTeacherAfterReleaseGrade of class AssignmentFlowActivity", e);
        }
    }

}
