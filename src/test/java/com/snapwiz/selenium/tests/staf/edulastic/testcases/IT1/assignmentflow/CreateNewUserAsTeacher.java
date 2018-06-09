package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.assignmentflow;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.*;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by mukesh on 11/19/14.
 */
public class CreateNewUserAsTeacher extends Driver {
    @Test(priority = 1,enabled = true)
    public void createNewUserAsTeacher(){
        try {
            //TC row no 3
            //"1. Enter the valid URL 2. Select teacher 3. Enter all valid information and sign up 4. Select ""Visit Class""5. Click “Create your first assignment
            //Expected 1.User should be directed to “create assignment” page
            String appendChar = "a";
            new SignUp().teacher(appendChar, 3); //signup as a teacher
            new School().createWithOnlyName(appendChar, 3); //create school
            new Classes().createClass(appendChar,3);//create class
            new Click().clickByXpath("(//a[@class='btn btn-blue btn-rounded'])[2]");//click on Create New Assignment button
            String assignmentUrl= driver.getCurrentUrl();
            if(!assignmentUrl.contains("#createAssessment/close"))
                Assert.fail("User is not directed to “create assignment” page");

            //Tc Row no 4
            //"1. Enter the valid URL 2. Select teacher 3. Enter all valid information and sign up 4. Select ""Visit Class"" 5. Click Assignments from the left side"
            //Expected 2.User should be directed to home page of assignment
            new Navigator().navigateTo("assignment"); //navigate to assignment
            String assignmentHomePageURl=driver.getCurrentUrl(); //get the current url
            if(!assignmentHomePageURl.contains("/#assignment/close"))
                Assert.fail("User is not directed to home page of assignment");

            //TC row no 6
            //"1. Enter valid URl 2. Enter existing valid UN and PWD for instructor 3. Click on assignment on the left side of the menu bar"
            //Expected  1.User should be directed to home page of assignment 2.All Status, View drafts and New Assignment tabs should be present 3.All assignments should be displayed in no particular order.
            new Navigator().navigateTo("assignment"); //navigate to assignment
            String assignmentHomePageURl6=driver.getCurrentUrl(); //get the current url
            if(!assignmentHomePageURl6.contains("/#assignment/close"))
                Assert.fail("User is not directed to home page of assignment");
             new Click().clickByid("as-assignment-filter-viewall");//click on the All status

            //All assignments should be displayed in no particular order. Pending
            //TC row no 10
            //"1. Enter valid URL 2. Enter existing valid UN and PWD for instructor 3. Click on assignment on the left side of the menu bar 4. Click the dropdown beside ""All Status""
            //Expected "1.Following should be available: Awaiting Submission, Grading in progress, Review in  Progress, Graded, Reviewed, Scheduled"
            new Navigator().navigateTo("assignment"); //navigate to assignment
            //new Click().clickByid("as-assignment-filter-viewall");//click on the All status
            Select select=new Select(driver.findElement(By.id("as-assignment-filter-viewall")));
            select.selectByVisibleText("Awaiting Submission");
            select.selectByVisibleText("Grading in Progress");
            select.selectByVisibleText("Review in Progress");
            select.selectByVisibleText("Graded");
            select.selectByVisibleText("Reviewed");
            select.selectByVisibleText("Scheduled");

        } catch (Exception e) {
            Assert.fail("Exception in test case createNewUserAsTeacher of class CreateNewUserAsTeacher",e);
        }

    }
    @Test(priority = 2,enabled = true)
    public void assignmentStatusAsAwaitingSubmission()
    {
        try
        {
            //Tc row no 11
            //"1. Enter valid URL 2. Enter existing valid UN and PWD for instructor 3. Click on assignment on the left side of the menu bar
            //4. Click the dropdown beside ""All Status""
            //5.Select any of the options:Awaiting Submission,Grading in progress, Review in Progress, Graded, Reviewed, Scheduled"
            //Expected  1.On selecting any of the options, assignment with proper status should filter out and displayed

            String appendChar = "a";
            new SignUp().teacher(appendChar, 11);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 11);//create school
            String classCode = new Classes().createClass(appendChar, 11);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, 11);//create student 1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 11);//log in as teacher
            new Assignment().create(11, "truefalse");//create an Assignment
            new Assignment().assignToStudent(11, appendChar);//assign to student1; //status awaiting submission

            new Navigator().navigateTo("assignment"); //navigate to assignment
            Select select=new Select(driver.findElement(By.id("as-assignment-filter-viewall")));
            select.selectByVisibleText("Awaiting Submission");
            Thread.sleep(2000);
            String status = new Assignment().assignmentStatus(11);//get the status of assignment
            Assert.assertEquals(status, "Awaiting Submission", "Gradable Assignment's status is not \"Awaiting Submission\" after assigning the assignment");

        }
        catch (Exception e) {
            Assert.fail("Exception in test case assignmentStatusAsAwaitingSubmission of class CreateNewUserAsTeacher",e);
        }

    }
    @Test(priority = 3,enabled = true)
    public void assignmentStatusAsGradingInProgress()
    {
        try
        {
            //Tc row no 11
            //"1. Enter valid URL 2. Enter existing valid UN and PWD for instructor 3. Click on assignment on the left side of the menu bar
            //4. Click the dropdown beside ""All Status""
            //5.Select any of the options:Awaiting Submission,Grading in progress, Review in Progress, Graded, Reviewed, Scheduled"
            //Expected  1.On selecting any of the options, assignment with proper status should filter out and displayed

            String appendChar = "a";
            new SignUp().teacher(appendChar, 12);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 12);//create school
            String classCode = new Classes().createClass(appendChar, 12);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, 12);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, 12);//log in as teacher
            new Assignment().create(12, "truefalse");//create an Assignment
            new Assignment().addQuestion(12, "truefalse");//add a question
            new Assignment().assignToStudent(12, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, 12);//log in as student 1
            new Assignment().submitAssignment(12);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 12);//log in as teacher
            new Navigator().navigateTo("assignment"); //navigate to assignment
            Select select=new Select(driver.findElement(By.id("as-assignment-filter-viewall")));
            select.selectByVisibleText("Grading in Progress");
            String status2 = new Assignment().assignmentStatus(12);//get the status of assignment
            Assert.assertEquals(status2, "Grading in Progress", "Gradable Assignment's status is not \"Grading in Progress\" after submission by all students of the class.");

        }
        catch (Exception e) {
            Assert.fail("Exception in test case assignmentStatusAsGradingInProgress of class CreateNewUserAsTeacher",e);
        }

    }
    @Test(priority = 4,enabled = true)
    public void assignmentStatusAsGraded()
    {
        try
        {
            //Tc row no 11
            //"1. Enter valid URL 2. Enter existing valid UN and PWD for instructor 3. Click on assignment on the left side of the menu bar
            //4. Click the dropdown beside ""All Status""
            //5.Select any of the options:Awaiting Submission,Grading in progress, Review in Progress, Graded, Reviewed, Scheduled"
            //Expected  1.On selecting any of the options, assignment with proper status should filter out and displayed

            String appendChar = "a";
            new SignUp().teacher(appendChar, 13);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 13);//create school
            String classCode = new Classes().createClass(appendChar, 13);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, 13);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, 13);//log in as teacher
            new Assignment().create(13, "truefalse");//create an Assignment
            new Assignment().addQuestion(13, "truefalse");//add a question
            new Assignment().assignToStudent(13, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, 13);//log in as student 1
            new Assignment().submitAssignment(13);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 13);//log in as teacher
            new Navigator().navigateTo("assignment"); //navigate to assignment
            Select select=new Select(driver.findElement(By.id("as-assignment-filter-viewall")));
            select.selectByVisibleText("Graded");
            String status = new Assignment().assignmentStatus(13);//get the status of assignment
            Assert.assertEquals(status, "Graded", "Gradable Assignment's status is not \"Graded\" after grade is released by the teacher.");

        }
        catch (Exception e) {
            Assert.fail("Exception in test case assignmentStatusAsGradingInProgress of class CreateNewUserAsTeacher",e);
        }

    }

    @Test(priority = 5,enabled = true)
    public void assignmentStatusAsReviewInProgress()
    {
        try
        {
            //Tc row no 11
            //"1. Enter valid URL 2. Enter existing valid UN and PWD for instructor 3. Click on assignment on the left side of the menu bar
            //4. Click the dropdown beside ""All Status""
            //5.Select any of the options:Awaiting Submission,Grading in progress, Review in Progress, Graded, Reviewed, Scheduled"
            //Expected  1.On selecting any of the options, assignment with proper status should filter out and displayed

            String appendChar = "a";
            new SignUp().teacher(appendChar, 14);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 14);//create school
            String classCode = new Classes().createClass(appendChar, 14);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, 14);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, 14);//log in as teacher
            new Assignment().create(14, "truefalse");//create an Assignment
            new Assignment().addQuestion(14, "truefalse");//add a question
            new Assignment().assignToStudent(14, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, 14);//log in as student 1
            new Assignment().submitAssignment(14);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 14);//log in as teacher
            new Navigator().navigateTo("assignment"); //navigate to assignment
            Select select=new Select(driver.findElement(By.id("as-assignment-filter-viewall")));
            select.selectByVisibleText("Review in Progress");
            String status = new Assignment().assignmentStatus(14);//get the status of assignment
            Assert.assertTrue(status.contains("Grading in Progress") , "Non-Gradable Assignment's status is not \"Review in Progress\" after submission by all students of the class.");

        }
        catch (Exception e) {
            Assert.fail("Exception in test case assignmentStatusAsGradingInProgress of class CreateNewUserAsTeacher",e);
        }

    }

    @Test(priority = 6,enabled = true)
    public void assignmentStatusAsReviewed()
    {
        try
        {
            //Tc row no 11
            //"1. Enter valid URL 2. Enter existing valid UN and PWD for instructor 3. Click on assignment on the left side of the menu bar
            //4. Click the dropdown beside ""All Status""
            //5.Select any of the options:Awaiting Submission,Grading in progress, Review in Progress, Graded, Reviewed, Scheduled"
            //Expected  1.On selecting any of the options, assignment with proper status should filter out and displayed

            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            String appendChar = "a";
            new SignUp().teacher(appendChar, 15);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 15);//create school
            String classCode = new Classes().createClass(appendChar, 15);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, 15);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, 15);//log in as teacher
            new Assignment().create(15, "truefalse");//create an Assignment
            new Assignment().addQuestion(15, "truefalse");//add a question
            new Assignment().assignToStudent(15, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, 15);//log in as student 1
            new Assignment().submitAssignment(15);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 15);//log in as teacher
            new Navigator().navigateTo("assignment"); //navigate to assignment
            Select select=new Select(driver.findElement(By.id("as-assignment-filter-viewall")));
            select.selectByVisibleText("Review in Progress");
            String status = new Assignment().assignmentStatus(15);//get the status of assignment
            Assert.assertEquals(status, "Grading in Progress", "Non-Gradable Assignment's status is not \"Review in Progress\" after submission by all students of the class.");
            new Assignment().releaseGrades(15, " Release Feedback");//click on  Release Feedback for All
            new Navigator().navigateTo("assignment");//navigate To Assignments
            String status3 = new Assignment().assignmentStatus(15);//get the status of assignment
            Assert.assertEquals(status3, "Graded", "Non-Gradable Assignment's status is not \"Reviewed\" after feedback is released by the teacher.");

        }
        catch (Exception e) {
            Assert.fail("Exception in test case assignmentStatusAsReviewed of class CreateNewUserAsTeacher",e);
        }

    }
    @Test(priority = 7,enabled = true)
    public void assignmentStatusAsScheduled()
    {
        try
        {
            //Tc row no 11
            //"1. Enter valid URL 2. Enter existing valid UN and PWD for instructor 3. Click on assignment on the left side of the menu bar
            //4. Click the dropdown beside ""All Status""
            //5.Select any of the options:Awaiting Submission,Grading in progress, Review in Progress, Graded, Reviewed, Scheduled"
            //Expected  1.On selecting any of the options, assignment with proper status should filter out and displayed

            String appendChar = "a";
            new SignUp().teacher(appendChar, 16);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 16);//create school
            String classCode = new Classes().createClass(appendChar, 16);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, 16);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, 16);//log in as teacher
            new Assignment().create(16, "truefalse");//create an Assignment
            new Assignment().addQuestion(16, "truefalse");//add a question
            new Assignment().assignToStudent(16, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, 16);//log in as teacher
            new Navigator().navigateTo("assignment"); //navigate to assignment
            Select select=new Select(driver.findElement(By.id("as-assignment-filter-viewall")));
            select.selectByVisibleText("Scheduled");
            String status = new Assignment().assignmentStatus(16);//get the status of assignment
            Assert.assertEquals(status, "Scheduled", "Non-Gradable Assignment's status is not \"Scheduled\" after submission by all students of the class.");

        }
        catch (Exception e) {
            Assert.fail("Exception in test case assignmentStatusAsScheduled of class CreateNewUserAsTeacher",e);
        }

    }
    @Test(priority = 8,enabled = true)
    public void useExistingAssignment()
    {
        try
        {
            AssessmentLibrary assessmentLibrary = PageFactory.initElements(driver,AssessmentLibrary.class);
            //Tc row no 13
            //"1. Enter valid URL  2. Enter valid UN and PWD for instructor 3. Click on assignment on the left side of the menu bar
            //4.Click on ""new assignment"" 5.Select ""Use Existing"""
            String appendChar = "a";
            new SignUp().teacher(appendChar, 13); //sign up as a teacher
            new School().createWithOnlyName(appendChar, 13); //create school
            new Classes().createClass(appendChar,13);//create class
            new Assignment().create(13,"truefalse");
            new Assignment().assignToStudent(13,appendChar);
            new Navigator().navigateTo("assignment"); //navigate to assignment
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on Create New Assignment button
            new Click().clickByid("search-assessment-with-val");//click on search

            //"3.Teacher should see the following a. Subject Name(Left of the page)b. Grade (Left of the page c. Owner(Me or Community)
            //d. Option to select from “Choose Standard”(Left of the page)e. List of question to select from No of times question is used(Right of the page) g. Option to like and comment(Right of the page)"

            assessmentLibrary.getRadioButtons_owner().get(0).click();//Select 'me' radio button
            boolean ownerLabel= assessmentLibrary.getLabel_owner().isDisplayed();
            Assert.assertEquals(ownerLabel,true,"OWNER label is not visible");

            String me= assessmentLibrary.getList_labels_owner().get(0).getText();
            Assert.assertEquals("Private",me,"Me label is not visible to the teacher");

            String community=assessmentLibrary.getList_labels_owner().get(1).getText();
            Assert.assertEquals("Public",community,"Community label is not  visible to the teacher");

            String grade = assessmentLibrary.getSelectbox_grade().getText();
            Assert.assertEquals(grade,"Grade 1","Grade is not displayed as expected");

            String subject = assessmentLibrary.getSelectbox_subject().getText();
            Assert.assertEquals(subject,"Math - Common Core","Subject is not displayed as expected");

            String standard= assessmentLibrary.getLabel_chooseStandard().getText();
            Assert.assertEquals(standard,"CHOOSE STANDARD","CHOOSE STANDARD is not visible to the teacher");

            new Navigator().navigateTo("assessmentLibrary");//Navigate to Assessment Library
            assessmentLibrary.getRadioButtons_owner().get(0).click();//Select 'me' radio button
            assessmentLibrary.getList_assessment().get(0).click();//Select the assessment

            String noOfTimesUsed = new TextFetch().textFetchByXpath("//strong[@class='edu-green']/parent::span");

            if(!noOfTimesUsed.contains("Used 1 time(s)")){
                Assert.fail("'Used 1 time(s)' is not displayed");
            }

            new Click().clickBycssselector("li.lsm-createdAssignment-like.enabled");//Like the assessment
            Thread.sleep(1000);
            String likeCount=new TextFetch().textfetchbycssselector("span[class='lsm-createdAssignment-like-count']");
            if(!likeCount.contains("1"))
                Assert.fail("teacher is not able to like on questions ");

            //TC row no 18
            //"1. Enter valid URL 2. Enter valid UN and PWD for instructor  3. Click on assignment on the left side of the menu bar   4.Click on ""new assignment" 5.Select ""Use Existing""  6.Select Any of the assignments from this page"
            //"1.Following should be available:   a. Back b. Customize c. Use this"
            new Navigator().navigateTo("assignment"); //navigate to assignment
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on Create New Assignment button
            new Click().clickByid("search-assessment-with-val");//click on search

            //Tc row no 20
            //"1. Enter valid URL  2. Enter valid UN and PWD for instructor  3. Click on assignment on the left side of the menu bar  4.Click on ""new assignment""  5.Select ""Use Existing""  6.Select Any of the assignments from this page7. Click the ""customize"" button"
            //"1. Following should be available: a. Save for later b. Next c. Add more
            assessmentLibrary.getList_assessment().get(0).click();//Select the assignment
            assessmentLibrary.getList_createNewVersion().get(0).click();//Click on customize button
            String saveForLater=new TextFetch().textfetchbyid("assessments-save-later-button");
            Assert.assertEquals(saveForLater,"Save in Drafts","Save for later option is not available");
            String addMore=new TextFetch().textfetchbyid("assessments-back-button");
            if(!addMore.contains("Add more"))
                Assert.fail("Add more button is not available in the page");
            String nextButton=new TextFetch().textfetchbyid("assessments-use-button");
            Assert.assertEquals(nextButton,"Next","Next button is not available in the page");

        }
        catch (Exception e) {
            Assert.fail("Exception in test case useExistingAssignment of class CreateNewUserAsTeacher",e);
        }

    } 

    @Test(priority = 9,enabled = true)
    public  void saveForLater()
    {
        try {
            AssessmentLibrary assessmentLibrary = PageFactory.initElements(driver,AssessmentLibrary.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);

            //Tc row no 21
            //"1. Enter valid URL  2. Enter valid UN and PWD for instructor  3. Click on assignment on the left side of the menu bar4.Click on ""new assignment"" 5.Select ""Use Existing""
            // 6.Select Any of the assignments from this page 7. Click the ""customize"" button 8.Click the ""save for later"" button"
            //Expected "1. User should be directed to “Assignments” page 2.Counter at ""View Draft status"" should be incremented"
            String appendChar = "a";
            new SignUp().teacher(appendChar, 21); //sign up as a teacher
            new School().createWithOnlyName(appendChar, 21); //create school
            new  Classes().createClass(appendChar,21);//create class

            new Assignment().selectExistingAssignment();
            assessmentLibrary.getList_assessment().get(0).click();//Select the assessment
            assessmentLibrary.getList_createNewVersion().get(0).click();//Click on customize button
            new Click().clickByid("assessments-save-later-button"); //click on the save for later button
            Thread.sleep(3000);
            String draftCountBeforeIncrement=new TextFetch().textfetchbylistcssselector("ins.iCheck-helper >span", 1);
          /*  String count=draftCountBeforeIncrement.substring(1,draftCountBeforeIncrement.length()-1);
            int integerValueBeforeIncrement=Integer.parseInt(count);*/
            int integerValueBeforeIncrement=0;
            if(!draftCountBeforeIncrement.contains("1"))
            {
                integerValueBeforeIncrement=0;
            }

            MyAssessments myAssessments=PageFactory.initElements(driver,MyAssessments.class);
            // to check draft status is incrementing or not
            myAssessments.button_creteNewAssessment.click();
            new Click().clickByid("search-assessment-with-val");//click on search

            new Assignment().selectCommunity();
            assessmentLibrary.getList_assessment().get(0).click();//Click on assessment
            assessmentLibrary.getList_createNewVersion().get(0).click();//Click on customize
            new Click().clickByid("assessments-save-later-button"); //click on the save for later button
            String draftCountAfterIncrement=new TextFetch().textfetchbylistcssselector("ins.iCheck-helper >span",1);
            int integerValueAfterIncrement=0;
            if(draftCountAfterIncrement.contains("1"))
            {
                integerValueAfterIncrement=1;
            }

            if(draftCountBeforeIncrement.equals(draftCountAfterIncrement))
                Assert.fail("\"View Draft status\"\" is not incrementing\"");
            if(!(integerValueBeforeIncrement<integerValueAfterIncrement)){
                Assert.fail("\"View Draft status\"\" is not incrementing\"");
            }


            //TC row no 22
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on Create New Assignment button
            new Click().clickByid("search-assessment-with-val");//click on search

            new Assignment().selectCommunity();
            assessmentLibrary.getList_assessment().get(0).click();//Click on assessment
            assessmentLibrary.getList_createNewVersion().get(0).click();//Click on customize
            new Click().clickByid("assessments-use-button");//click on the next button

            String assignThis=new TextFetch().textfetchbyid("assign-button");
            Assert.assertEquals(assignThis,"Save","Assign button is not present");

            //TC row no 23
            assign.getRadio_button_rightNow().click();//Click on right now radio button
            //1. “Assign to” field should be automatically filled
            String assignTO=new TextFetch().textfetchbyclass("item-text");
            if(assignTO.equals("")||assignTO==null)
                Assert.fail("“Assign to” field is not filled automatically");
            //2. “Short Label” field should be empty for teacher to fill in
            String shortLabel=driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).getAttribute("value");
            if(shortLabel.length()<=0)
                Assert.fail("Short label is empty");
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).sendKeys("shor");//short label name for due date error

            //3. “Gradable” check box should be empty, for teacher to check/uncheck
             boolean isSelected=driver.findElement(By.className("icheckbox_square-green")).isSelected();
            if(isSelected==true)
                Assert.fail(" “Gradable” check box is not empty, for teacher to check/uncheck");
            //4. Points allotted for the assignment should be correct
            //6. Due date should be empty, for teacher to enter

            String dueDate= driver.findElement(By.id("lsm_assignment_due_date")).getText();
            if(dueDate.length()>0) {
                Assert.fail("Due date is not empty, for teacher to enter");
            }
            //8. On entering previous/past date Error message should be displayed
            new Click().clickByid("lsm_assignment_due_date");//click on Due Date field
            new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-w']");
            new Click().clickbylinkText("11");//select past due date

            new Click().clickByid("lsm_assignment_due_time");//click on Due Time field
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals("12:00 AM")) {
                    time.click();
                    break;
                }
            }
            new Click().clickByid("assign-button");//click on the assign

            String errorMsg=new TextFetch().textfetchbycssselector("div[class='lsm-assignment-name-tooltip lsm-assign-assessment-error-message-tooltip']");
            Assert.assertEquals("Due date must be a future date.",errorMsg,"Error Message is not appeared");

            //11. If all the fields are filled, on clicking assign, Assigned message should appear
            new Click().clickByid("lsm_assignment_due_date");//click on Due Date field
            Thread.sleep(1000);
            new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
            new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
            new Click().clickbylinkText("28");//select past due date
            new Click().clickByid("assign-button");//click on the assign
            Thread.sleep(3000);
            String message = new TextFetch().textfetchbycssselector("span[class='right-header-msg-section right-crumb']");
            Assert.assertEquals(message,"Assignment Assigned","Message is not displayed as expected");
        }
        catch (Exception e){
            Assert.fail("Exception in test case  saveForLater of class CreateNewUserAsTeacher",e);
        }
    }

    @Test(priority = 10,enabled = true)
    public void backAndAddMoreButtonFunctionality()
    {

        try {
            AssessmentLibrary assessmentLibrary = PageFactory.initElements(driver,AssessmentLibrary.class);

            //Tc row no 24
            String appendChar = "a";
            new SignUp().teacher(appendChar, 24); //sign up as a teacher
            new School().createWithOnlyName(appendChar, 24); //create school
            new  Classes().createClass(appendChar,24);//create class
            new Assignment().create(24,"truefalse");//Create true false question
            new Assignment().assignToStudent(24,appendChar);
            new Assignment().selectExistingAssignment();
            assessmentLibrary.getRadioButtons_owner().get(0).click();//Click on me
            assessmentLibrary.getList_assessment().get(0).click();//Click on assessment
            assessmentLibrary.getList_createNewVersion().get(0).click();//Click on customize
            new Click().clickByid("assessments-use-button");//click on the next button
            new WebDriverWait(driver, 24).until(ExpectedConditions.presenceOfElementLocated(By.id("as-description")));
            new Click().clickByid("go-back");//click on the back button

            //Expected 1.User is directed to main assignment page(Step 1 of 3: Select questions)
            String selectQuestion= driver.findElement(By.xpath("//span[@class='left-header-msg-section left-crumb']/parent::div")).getText();
            if(!selectQuestion.contains("Step 2 of 3Select questions"))
                Assert.fail("User is not directed to main assignment page(Step 2 of 3Select questions)");

            //Tc row no 25
            new Click().clickByid("assessments-back-button");//click on the add more
            //Expected "1. Teacher is directed to “Step 1 of 2:Select questions”,i.e. Place from where questions   Can be selected"
            String selectQuestion1= driver.findElement(By.xpath("//span[@class='right-header-msg-section right-crumb']/parent::span")).getText();
            if(!selectQuestion1.contains("Step 1 of 3Select or Create questions"))
                Assert.fail("User is not directed to main assignment page(Step 1 of 3Select or Create questions)");

        } catch (Exception e) {
            Assert.fail("Exception in tescase backAndAddMoreButtonFunctionality of class CreateNewUserAsTeacher",e);
        }

    }

    @Test(priority = 11,enabled = true)
    public void createNewAssignment()
    {

        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "27");

            //TC row no 27
            String appendChar = "a";
            AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
            new SignUp().teacher(appendChar, 27); //signup as a teacher
            new School().createWithOnlyName(appendChar, 27); //create school
            new  Classes().createClass(appendChar, 27);//create class
            new Navigator().navigateTo("assignment"); //navigate to assignment
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on Create New Assignment button
            driver.findElement(By.id("new-assessment-name")).clear();
            driver.findElement(By.id("new-assessment-name")).sendKeys(assessmentname);//give the assignment title
            new Click().clickByid("new-assessment-name");
            new Click().clickByXpath("//html/body");
            new Click().clickByid("create-assessment-with-val");//click on Create

            String selectQuestion1=new TextFetch().textfetchbyclass("lsm-add-questions-title");
                if(!selectQuestion1.contains("Step 1 of 3Select or Create questions"))
                    Assert.fail(" User is not able to go  to “Step 1 of 2: Select questions” page");

            new Click().clickByXpath("(//span[@class='lsm-select-btn lsm-select-tlo-questions-btn btn btn-blue btn-outline btn-rounded'])[1]");//click on the select

            //TC row no 29
            //Expected 3 3.Create option should only be available beside ELOs, along with  Auto select and Select
            new Click().clickByid("question-selection-done");//click on the done
            String ELOCreate=new TextFetch().textfetchbycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");
            Assert.assertEquals(ELOCreate,"Create","Create option is not available beside ELOs");
            //TC row no 30
            //"4.TLOs should have the following :Auto select  Select"

            String autoSelect=new TextFetch().textfetchbycssselector("span[class='lsm-auto-assign-btn btn btn-blue btn-outline btn-rounded']");
            Assert.assertEquals(autoSelect,"Auto Select","Auto Select option is not available");
            String select =new TextFetch().textfetchbycssselector("span[class='lsm-select-btn lsm-select-tlo-questions-btn btn btn-blue btn-outline btn-rounded']");
            Assert.assertEquals(select,"Select","Select option is not available");

            //Tc row no 31
            //"6. Following are found on the left side of the page, after clicking “Create new assignmen :name of the subject Grade Owner Choose standards
            Select sel= new Select(driver.findElement(By.xpath("//div[@id='lsm-createAssignment-subject-dropDown']/select")));
            sel.selectByIndex(0); //select 2nd subject
            Select sel1=new Select(driver.findElement(By.xpath("//div[@id='lsm-createAssignment-grade-dropDown']/select")));
            sel1.selectByIndex(1);//select a grade

            boolean ownerLabel=assessmentLibrary.getLabel_owner().isDisplayed();
            Assert.assertEquals(ownerLabel,true,"OWNER label is not visible");
            String standard=new TextFetch().textfetchbyclass("lsm-createAssignment-chooseStd-text");
            Assert.assertEquals(standard,"CHOOSE STANDARD","CHOOSE STANDARD is not visible to the teacher");

        } catch (Exception e) {
            Assert.fail("Exception in tescase createNewAssignment of class CreateNewUserAsTeacher",e);
        }

    }
    @Test(priority = 12,enabled = true)
    public void clickOnCreateBesideELO()
    {

        try {
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "27");

            //TC row no 33
            //1. User is directed to “types of questions”
            String appendChar = "a7";
            new SignUp().teacher(appendChar, 33); //sign up as a teacher
            new School().createWithOnlyName(appendChar, 33); //create school
            new  Classes().createClass(appendChar,33);//create class
            new Navigator().navigateTo("assignment"); //navigate to assignment
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on Create New Assignment button
            driver.findElement(By.id("new-assessment-name")).clear();
            driver.findElement(By.id("new-assessment-name")).sendKeys(assessmentname);//give the assignment title
            new Click().clickByid("new-assessment-name");
            new Click().clickByXpath("//html/body");
            new Click().clickByid("create-assessment-with-val");//click on Create
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//click on Create link

            //TC row no 34
            //2. Teacher should be able to select any type of question
            new Click().clickByid("qtn-multiple-choice-type");//click on Multiple Choice question
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickByid("qtn-multiple-selection-type");//Click on Multiple Selection question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickByid("qtn-text-drop-down-type");//Click on text drop down question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickByid("qtn-essay-type");//Click on Essay question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickByid("qtn-text-entry-type");//Click on text entry question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickByid("qtn-true-false-type");//Click on true false question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-maple-numeric-type");//Click on Numeric Entry question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-math-symbolic-notation-type");//Click on Expression Evaluator question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-fraction-editor-type");//Click on Fraction Editor question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-classification-type");//Click on Classification question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-graphing-type");//Click on Graphing question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-graph-placement-type");//Click on Graph placement question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-matching-tables-type");//Click on Matching Tables question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-pictograph-type");//Click on Pictograph question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-dnd-type");//Click on Drag and Drop question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-hybrid-type");//Click on multipart question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-number-line-type");//Click on Number line question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-graph-type");//Click on graph plotter question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-number-line-range");//Click on Range Plotter question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-line-plot-type");//Click on line plot question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-passage-type");//Click on Passage based question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-lbl-on-img-type");//Click on label an image(Text) question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-lbl-dropdown-type");//Click on label an image(Dropdown)question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-resequence-type");//Click on Resequence question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-numeric-entry-units-type");//Click on numeric entry with units question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-cf-type");//Click on Cloze(Formula) question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-cloze-matrix-type");//Click on Cloze Matrix question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("ELA Tech Enhanced");//Click on ELA Tech Enhanced tab
            new Click().clickByXpath("(//div[@id='qtn-passage-type'])[2]");//Click on Passage based question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("ELA Tech Enhanced");//Click on ELA Tech Enhanced tab
            new Click().clickByid("qtn-sentence-type");//Click on Sentence Response question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("ELA Tech Enhanced");//Click on ELA Tech Enhanced tab
            new Click().clickByid("qtn-sentence-correction-type");//Click on Sentence Correction question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("ELA Tech Enhanced");//Click on ELA Tech Enhanced tab
            new Click().clickByXpath("(//div[@id='qtn-dnd-type'])[2]");//Click on drag and drop question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("ELA Tech Enhanced");//Click on ELA Tech Enhanced tab
            new Click().clickByXpath("(//div[@id='qtn-matching-tables-type'])[2]");//Click on Matching Tables question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("ELA Tech Enhanced");//Click on ELA Tech Enhanced tab
            new Click().clickByXpath("(//div[@id='qtn-classification-type'])[2]");//Click on Classification question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("Math Tech Enhanced");//Click on ELA Tech Enhanced tab
            new Click().clickByid("qtn-mtf-type");//Click on Match The following question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow
            new Click().clickbylinkText("ELA Tech Enhanced");//Click on ELA Tech Enhanced tab
            new Click().clickByXpath("(//div[@id='qtn-resequence-type'])[2]");//Click on Resequence question type
            new Click().clickByclassname("as-question-editor-back");//click on the back arrow

            new Navigator().navigateTo("assignment"); //navigate to assignment
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on Create New Assignment button
            driver.findElement(By.id("new-assessment-name")).clear();
            //Tc row no 37
            //"6.Error message should be displayed if teacher tries to create the assignment without giving any name"
            new Click().clickByid("create-assessment-with-val");//click on create
            String errorMsg=new TextFetch().textfetchbycssselector("div[class='as-errorMsg assessment-name-message']");
            if(!errorMsg.contains("Please provide an assessment name."))
                Assert.fail("Error message is not displayed if teacher tries to create the assignment without giving any name");

            //Tc row no 36
            //5. Teacher should give name to assignment
            driver.findElement(By.id("new-assessment-name")).clear();
            driver.findElement(By.id("new-assessment-name")).sendKeys(assessmentname);//give the assignment title
            new Click().clickByid("new-assessment-name");
            new Click().clickByXpath("//html/body");
            new Click().clickByid("create-assessment-with-val");//click on Create
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");
            new Click().clickByid("qtn-true-false-type");//click on true false question
            new TextSend().textsendbyclass("Assignment36","lsm-createAssignment-input-name");
            //TC row no 38
            //7.Teacher should be able to save the assignment, after giving the name
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("True False Question 39");//type the question
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option A
            new Click().clickByid("saveQuestionDetails1");//click on the save
            Thread.sleep(7000);

            //TC row no 39
            //"8. On successfully saving the assignment, saved message should be displayed on top  Of the page"

           String saved=new TextFetch().textfetchbyid("footer-notification-text");
           Assert.assertEquals(saved,"Saved.","saved message is not displayed on top  Of the page");

            //Tc row 40
            //"9.On successful saving, “Review” button should be enabled and total number of questions in
            //The assignment should be highlighted on the review button"
            String review = assignments.getButton_review().getText();
            if(!review.contains("Review"))
                Assert.fail(" “Review” button is  not enabled ");
            String noOfQuestion=new TextFetch().textfetchbycssselector("small[class='lsm-createAssignment-total-questions count-badge']");
            Assert.assertEquals(noOfQuestion,"1","Number of question is not highlighting on the review button");

            //Tc row no 41
            //"10.On clicking review, following options are found:a. Save for later b. Add more c. Next"
            assignments.getButton_review().click();//Click on review button
            String saveForLater=new TextFetch().textfetchbyid("assessments-save-later-button");
            Assert.assertEquals(saveForLater,"Save in Drafts","Save for later option is not available");
            String addMore=new TextFetch().textfetchbyid("assessments-back-button");
            if(!addMore.contains("Add more"))
                Assert.fail("Add more button is not available in the page");
            String nextButton=new TextFetch().textfetchbyid("assessments-use-button");
            Assert.assertEquals(nextButton,"Next","Next button is not available in the page");

            //Tc row no 42
            new Click().clickByid("assessments-save-later-button"); //click on the save for later button
            Thread.sleep(2000);
            String draftCountAfterIncrement=new TextFetch().textfetchbylistcssselector("ins.iCheck-helper >span",1);
            System.out.println(draftCountAfterIncrement);
            int integerValueAfterIncrement=0;
            if(draftCountAfterIncrement.contains("1")) {
                integerValueAfterIncrement=1;
            }

            if(integerValueAfterIncrement==0){
                Assert.fail("\"View Draft status\"\" is not incrementing\"");
            }
        } catch (Exception e) {
            Assert.fail("Exception in tescase clickOnCreateBesideELO of class CreateNewUserAsTeacher",e);
        }

    }

    @Test(priority = 13,enabled = true)
    public void nextButtonFunctionality()
    {

        try {
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "27");

            //TC row no 43
            String appendChar = "a";
            new SignUp().teacher(appendChar, 43); //signup as a teacher
            new School().createWithOnlyName(appendChar, 43); //create school
            new  Classes().createClass(appendChar,43);//create class
            new Navigator().navigateTo("assignment"); //navigate to assignment
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on Create New Assignment button
            driver.findElement(By.id("new-assessment-name")).clear();
            driver.findElement(By.id("new-assessment-name")).sendKeys(assessmentname);//give the assignment title
            new Click().clickByid("new-assessment-name");
            new Click().clickByXpath("//html/body");
            new Click().clickByid("create-assessment-with-val");//click on Create
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//click on Create link
            new Click().clickByid("qtn-true-false-type");//click on true false question
            new TextSend().textsendbyclass("Assignment431","lsm-createAssignment-input-name");
            //TC row no 38
            //7.Teacher should be able to save the assignment, after giving the name
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("True False Question 39");//type the question
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option
            new TextSend().textsendbyid("True False Solution Text","content-solution");
            new TextSend().textsendbyid("True False Hint Text","content-hint");
            new Click().clickByid("saveQuestionDetails1");//click on the save
            assignments.getButton_review().click();//click on the review button
            new Click().clickByid("assessments-use-button");//click on the next button
            String assignThis=new TextFetch().textfetchbyid("assign-button");
            Assert.assertEquals(assignThis, "Assign", "Assign button is not present");

        } catch (Exception e) {
            Assert.fail("Exception in tescase NextButtonFunctionality of class CreateNewUserAsTeacher",e);
        }

    }


}
