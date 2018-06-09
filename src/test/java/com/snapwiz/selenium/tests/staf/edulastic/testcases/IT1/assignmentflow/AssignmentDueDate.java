package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.assignmentflow;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentDetailsPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentLibrary;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assign;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Mukesh on 11/24/14.
 */
public class AssignmentDueDate extends  Driver{

    @Test(priority = 1,enabled = true)
    public  void enterDueDateAsPreviousDate()
    {
        try {

            //Tc row no 44
            AssessmentLibrary assessmentLibrary = PageFactory.initElements(driver,AssessmentLibrary.class);
            Assign assign = PageFactory.initElements(driver, Assign.class);

            String appendChar = "a";
            new SignUp().teacher(appendChar, 44); //signup as a teacher
            new School().createWithOnlyName(appendChar, 44); //create school
            new  Classes().createClass(appendChar, 44);//create class
            new Assignment().selectExistingAssignment();
            assessmentLibrary.getList_assessment().get(0).click();//Select the assessment
            assessmentLibrary.getList_createNewVersion().get(0).click();//Click on customize
            new Click().clickByid("assessments-save-later-button");//click on the save for later
            new Navigator().navigateTo("myAssessments");//Navigate to my assessment page
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");//Click on Draft
            new Click().clickBycssselector("div[class='font-semi-bold space-15 assign-title']");//click on Assignment
            AssessmentDetailsPage assessmentDetailsPage=PageFactory.initElements(driver,AssessmentDetailsPage.class);
            assessmentDetailsPage.editAssessment.click();
            new Click().clickByid("assessments-use-button");//click on next button
            assign.getRadio_button_rightNow().click();//Click on right now radio button
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).clear();
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).sendKeys("shor");//short label name for due date error
            Thread.sleep(5000);
            driver.findElement(By.id("lsm_assignment_due_date")).click();
            Thread.sleep(5000);
            new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-w']");
            Thread.sleep(5000);
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

        }
        catch (Exception e){
            Assert.fail("Exception in test case  enterDueDateAsPreviousDate of class AssignmentDueDate",e);
        }
    }
    @Test(priority = 2,enabled = true)
    public  void enterDueDateAsFutureDate()
    {
        try {
            AssessmentLibrary assessmentLibrary = PageFactory.initElements(driver,AssessmentLibrary.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);

            //Tc row no 45
            String appendChar = "a";
            new SignUp().teacher(appendChar, 45); //signup as a teacher
            new School().createWithOnlyName(appendChar, 45); //create school
            new  Classes().createClass(appendChar,45);//create class
            new Assignment().selectExistingAssignment();
            assessmentLibrary.getList_assessment().get(0).click();//Click on assessment
            assessmentLibrary.getList_createNewVersion().get(0).click();//Click on customize button
            new Click().clickByid("assessments-save-later-button");//click on the save for later
            new Navigator().navigateTo("myAssessments");//Navigate to my assessment page
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");//Click on Draft
            new Click().clickBycssselector("div[class='font-semi-bold space-15 assign-title']");//click on Assignment
            AssessmentDetailsPage assessmentDetailsPage=PageFactory.initElements(driver,AssessmentDetailsPage.class);
            assessmentDetailsPage.editAssessment.click();
            new Click().clickByid("assessments-use-button");//click on next button
            assign.getRadio_button_rightNow().click();//Click on right now button
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).clear();
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).sendKeys("shor");//short label name for due date error
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
            Calendar calendar=Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            new Click().clickByid("assign-button");//click on the assign
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            //Expected"1. 2. Same assignment with assigned due date should be displayed in ""assignment page""
            String dueDate=new TextFetch().textfetchbycssselector("div[class='col-xs-12 col-sm-5']").substring(10).trim();

            Assert.assertEquals(dueDate,Calender.getCurrentMonthName(calendar.get(Calendar.MONTH)+1)+" 28, "+year+" 12:25 AM"," same assigned due date is not displayed in assignment page");

        }

        catch (Exception e){
            Assert.fail("Exception in test case  enterDueDateAsFutureDate of class AssignmentDueDate",e);
        }
    }
    @Test(priority = 3,enabled = true)
    public  void enterAccessibleDateAsFuture()
    {
        try {
            AssessmentLibrary assessmentLibrary = PageFactory.initElements(driver,AssessmentLibrary.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);

            //Tc row no 46
            String appendChar = "a0";
            new SignUp().teacher(appendChar, 46); //signup as a teacher
            new School().createWithOnlyName(appendChar, 46); //create school
            String classCode = new Classes().createClass(appendChar, 46);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 46);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 46); //login as instructor
            new Assignment().selectExistingAssignment();
            assessmentLibrary.getList_assessment().get(0).click();//Select the assessment
            assessmentLibrary.getList_createNewVersion().get(0).click();//Click on customize button
            new Click().clickByid("assessments-save-later-button");//click on the save for later
            new Navigator().navigateTo("myAssessments");//Navigate to my assessment page
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");//Click on Draft
            new Click().clickBycssselector("div[class='font-semi-bold space-15 assign-title']");//click on Assignment
            AssessmentDetailsPage assessmentDetailsPage=PageFactory.initElements(driver,AssessmentDetailsPage.class);
            assessmentDetailsPage.editAssessment.click();
            new Click().clickByid("assessments-use-button");//click on next button
            assign.getRadio_button_rightNow().click();//Click on right now radio button
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).sendKeys("shor");//short label name for due date error
            Thread.sleep(5000);
            new Click().clickByXpath("//input[@id='start-later']/parent::div");//Click on Later
            driver.findElement(By.id("lsm_assignment_accessible_date")).click();//click on the accessible
            new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
            new Click().clickbylinkText("25");
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
            new Click().clickByid("assign-button");//click on the assign
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            //Expected"1.Corresponding assignment will have "status: scheduled", at instructor's assignment page
            String status=new TextFetch().textfetchbycssselector("span.status-label");
            Assert.assertEquals(status, "Scheduled", "\"status: scheduled\",is not appeared at instructor's assignment page");

            new Navigator().logout();//logout

            new Login().loginAsStudent(appendChar, 46);//log in as student 1
            new Navigator().navigateTo("assignment"); //navigate to assignment

            //Expected 3.It should not be visible to students, before the accessible date
            if(driver.findElements(By.cssSelector("span[class='user-name-label ellipsis']")).size()>0)
                Assert.fail("Assignment is not visible to the student");
        }
        catch (Exception e){
            Assert.fail("Exception in test case  enterAccessibleDateAsFuture of class AssignmentDueDate",e);
        }
    }
    @Test(priority = 4,enabled = true)
    public  void backButtonFunctionalitySecondTime()
    {
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "48");
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            //Tc row no 48
            String appendChar = "a";
            new SignUp().teacher(appendChar, 48); //signup as a teacher
            new School().createWithOnlyName(appendChar, 48); //create school
            new Classes().createClass(appendChar, 48);//create class
            new Navigator().navigateTo("assignment"); //navigate to assignment
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on Create New Assignment button
            new TextSend().textsendbyid(assessmentname,"new-assessment-name");//Enter assessment name
            new Click().clickByid("create-assessment-with-val");//Click on Create button
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//Click on create link
            new Click().clickByid("qtn-true-false-type");//click on true false question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("True False Question 39");//type the question
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option
            new TextSend().textsendbyid("True False Solution Text", "content-solution");
            new TextSend().textsendbyid("True False Hint Text", "content-hint");
            new Click().clickByid("saveQuestionDetails1");//click on the save
            assignments.getButton_review().click();
            new Click().clickByid("assessments-use-button");//click on the next button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("assign-button")));
            new Click().clickByid("go-back");//click on the back arrow
            Thread.sleep(2000);

            String selectQuestion1=new TextFetch().textFetchByXpath("//span[@class='left-header-msg-section left-crumb']/parent::div");
            if(!selectQuestion1.contains("Step 2 of 3Select questions"))
                Assert.fail("User is not directed to main assignment page(Step 2 of 3Select questions)");
        }
        catch (Exception e){
            Assert.fail("Exception in test case  backButtonFunctionalitySecondTime of class AssignmentDueDate",e);
        }
    }
    @Test(priority = 5,enabled = true)
    public  void assignThisForCreateNewAssignment()
    {
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "49");
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);

            //TC row no 49
            String appendChar = "a";
            new SignUp().teacher(appendChar, 49); //signup as a teacher
            new School().createWithOnlyName(appendChar, 49); //create school
            new  Classes().createClass(appendChar,49);//create class
            new Navigator().navigateTo("assignment"); //navigate to assignment
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on Create New Assignment button
            new TextSend().textsendbyid(assessmentname,"new-assessment-name");//Enter assessment name
            new Click().clickByid("create-assessment-with-val");//Click on Create button
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//Click on create link
            new Click().clickByid("qtn-true-false-type");//click on true false question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("True False Question 49");//type the question
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option
            new TextSend().textsendbyid("True False Solution Text", "content-solution");
            new TextSend().textsendbyid("True False Hint Text", "content-hint");
            new Click().clickByid("saveQuestionDetails1");//click on the save
            WebDriverWait wait=new WebDriverWait(driver,120);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Saved.']")));            assignments.getButton_review().click();
            new Click().clickByid("assessments-use-button");//click on the next button
            //TC row no 23
            //1. “Assign to” field should be automatically filled
            String assignTO=new TextFetch().textfetchbyclass("item-text");
            if(assignTO.equals("")||assignTO==null)
                Assert.fail("“Assign to” field is not filled automatically");
            //2. “Short Label” field should be empty for teacher to fill in
            String shortLabel=driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).getAttribute("value");
            if(shortLabel.length()==0)
                Assert.fail("Short label is not empty");
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).sendKeys("shor");//short label name for due date error

            //3. “Gradable” check box should be empty, for teacher to check/uncheck
            boolean isSelected=driver.findElement(By.xpath("//div[starts-with(@class,'icheckbox_square-green')]/descendant::ins")).isSelected();
            if(isSelected==true)
                Assert.fail(" “Gradable” check box is not empty, for teacher to check/uncheck");
            //4. Points allotted for the assignment should be correct
            //6. Due date should be empty, for teacher to enter

            String dueDate= driver.findElement(By.id("lsm_assignment_due_date")).getAttribute("class");
            if(!dueDate.contains("form-control input-lg lsm-assignment-input hasDatepicker"))
                Assert.fail("Due date is not empty, for teacher to enter");
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

            //11. If all the fields are filled, on clicking assign, teacher should go back to assignment Page
            new Click().clickByid("lsm_assignment_due_date");//click on Due Date field
            Thread.sleep(1000);
            new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
            new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
            new Click().clickbylinkText("28");//select past due date
            new Click().clickByid("assign-button");//click on the assign

            //. 12. The new assignment should be visible on the “assignments” page now
            new Navigator().navigateTo("assignment"); //navigate to assignment
            String assignment=new TextFetch().textfetchbycssselector("h4[class='as-title as-label']");
            System.out.println("Assignment Name:"+assignment);
            if(!assignment.contains("Assessment49"))
                Assert.fail("New Assignment is not visible on the Assignment page");
        }
        catch (Exception e){
            Assert.fail("Exception in test case  assignThisForCreateNewAssignment of class AssignmentDueDate",e);
        }
    }
    @Test(priority = 6,enabled = true)
    public  void clickOnAddMoreButton()
    {
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "49");
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            //Tc row no 61
            String appendChar = "a";
            new SignUp().teacher(appendChar, 61); //signup as a teacher
            new School().createWithOnlyName(appendChar, 61); //create school
            new Classes().createClass(appendChar, 61);//create class
            new Navigator().navigateTo("assignment"); //navigate to assignment
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on Create New Assignment button
            new TextSend().textsendbyid(assessmentname,"new-assessment-name");//Enter assessment name
            new Click().clickByid("create-assessment-with-val");//Click on Create button
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//Click on create link
            new Click().clickByid("qtn-true-false-type");//click on true false question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("True False Question 39");//type the question
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option
            new TextSend().textsendbyid("True False Solution Text","content-solution");
            new TextSend().textsendbyid("True False Hint Text","content-hint");
            new Click().clickByid("saveQuestionDetails1");//click on the save
            assignments.getButton_review().click();//click on the review button
            new Click().clickByid("assessments-back-button");//click on the add more
            String selectQuestion1=new TextFetch().textfetchbyclass("lsm-add-questions-title");
            if(!selectQuestion1.contains("Step 1 of 3Select or Create questions"))
                Assert.fail("User is not directed to main assignment page(Step 1 of 3Select questions)");
        }
        catch (Exception e){
            Assert.fail("Exception in test case  clickOnAddMoreButton of class AssignmentDueDate",e);
        }
    }

}
