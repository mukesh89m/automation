package com.snapwiz.selenium.tests.staf.edulastic.testcases.e9.editassessesment;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.reports.MyReports;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by shashank on 13-10-2015.
 */
public class EditAssessmentAfterSharing extends Driver{

    @Test(priority = 1,enabled = true)
    public void editAssessmentAfterSharing() throws Exception
    {
        int dataIndex = 70;
        String appendChar = "a28";
        String appendChar1= "b28";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String updatedAssessmentName=ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver, AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assignments assignments=PageFactory.initElements(driver,Assignments.class);
        StudentResponse studentResponse=PageFactory.initElements(driver,StudentResponse.class);
        MyReports instructorReport = PageFactory.initElements(driver,MyReports.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);



        new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
        new School().createWithOnlyName(appendChar,dataIndex);//Create a school
        new Classes().createClass(appendChar,dataIndex);//Create class
        new Assignment().create(dataIndex,"truefalse");//create assessment
        new Assignment().addQuestion(dataIndex,"multiplechoice");//add question
        new Assignment().addQuestion(dataIndex,"multipleselection");//add question
        new Assignment().assignToStudent(dataIndex,appendChar);//assign as assessment
        String assessmentPublicURl=assign.getAssessmentShareLink().getText();//fetch url from UI

        new Assignment().editPublishedAssessment(dataIndex, appendChar);//edit published assessment
        assessmentReview.saveInDrafts.click();

        new Navigator().logout();//logout
        new SignUp().teacher(appendChar1,dataIndex);//Sign up as a 2nd teacher
        new School().createWithOnlyName(appendChar1,dataIndex);//Create a school
        String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
        driver.get(assessmentPublicURl);
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("toast-message")));
        Assert.assertEquals(assessmentLibrary.errorMessage.getText(), "Assessments not found.", "error message not matches");

        new Navigator().logout();//logout
        new Login().loginAsInstructor(appendChar,dataIndex);
        new Assignment().assignToStudent(71,appendChar);
        assessmentPublicURl=assign.getAssessmentShareLink().getText();//fetch url from UI
        new Navigator().logout();
        new Login().loginAsInstructor(appendChar1,dataIndex);
        driver.get(assessmentPublicURl);
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("as-assessment-name")));
    }


    @Test(priority = 2,enabled = true)
    public void shareAssessmentWithoutApproval() throws Exception
    {
        int dataIndex = 74;
        String appendChar = "a33";
        String appendChar1= "b33";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String updatedAssessmentName=ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver, AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assignments assignments=PageFactory.initElements(driver,Assignments.class);
        StudentResponse studentResponse=PageFactory.initElements(driver,StudentResponse.class);
        MyReports instructorReport = PageFactory.initElements(driver,MyReports.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);



        new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
        new School().createWithOnlyName(appendChar,dataIndex);//Create a school
        new Classes().createClass(appendChar,dataIndex);//Create class
        new Assignment().create(dataIndex,"truefalse");//create assessment
        new Assignment().addQuestion(dataIndex,"multiplechoice");//add question
        new Assignment().addQuestion(dataIndex,"multipleselection");//add question
        new Assignment().assignToStudent(dataIndex,appendChar);//assign as assessment
        String assessmentPublicURl=assign.getAssessmentShareLink().getText();//fetch url from UI
        new Navigator().logout();//logout
        new Login().directLoginAsInstructor(1,"curator@snapwiz.com");
        new Navigator().navigateTo("assessmentLibrary");
        driver.findElement(By.xpath("//span[@id='select2-gradeSelection-container']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//ul/*[contains(@id,'Grade 1')]")).click();

        driver.findElement(By.xpath(".//*[@id='select2-sortSelection-container']")).click();
        List<WebElement> sortElements=driver.findElements(By.xpath("//ul[@id='select2-sortSelection-results']/li"));
        sortElements.get(3).click();
        driver.findElement(By.id("search-fld")).sendKeys(assessmentname);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        driver.findElements(By.xpath("//input[@name='selected-owner']/following-sibling::ins")).get(4).click();
        driver.findElements(By.cssSelector("div[class='font-semi-bold space-15 assign-title']")).get(0).click();//click on Assignment

        driver.findElement(By.className("assessment-edit-sharing")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='sharingEnabled']/following-sibling::ins")).click();
        //   firefoxdriver.findElement(By.id("sharingEnabled")).click();
        driver.findElement(By.id("update-shared-assessment-access-control")).click();
        new Navigator().logout();//logout
        new SignUp().teacher(appendChar1,dataIndex);//Sign up as a 2nd teacher
        new School().createWithOnlyName(appendChar1,dataIndex);//Create a school
        String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
        driver.get(assessmentPublicURl);
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("as-assessment-name")));
        new Navigator().logout();//logout
        new Login().loginAsInstructor(appendChar,dataIndex);
        new Assignment().editPublishedAssessment(dataIndex, appendChar);//edit published assessment
        assessmentReview.saveInDrafts.click();
        new Assignment().assignToStudent(dataIndex,appendChar);
        assessmentPublicURl=assign.getAssessmentShareLink().getText();//fetch url from UI
        new Navigator().logout();//logout
        new Login().loginAsInstructor(appendChar1,dataIndex);
        driver.get(assessmentPublicURl);
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("toast-message")));
        Assert.assertEquals(assessmentLibrary.errorMessage.getText(), "You are not authorized to view this assessment.", "error message not matches");
        new Navigator().logout();//logout
        new Login().directLoginAsInstructor(1,"curator@snapwiz.com");
        new Navigator().navigateTo("assessmentLibrary");
        driver.findElement(By.xpath("//span[@id='select2-gradeSelection-container']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//ul/*[contains(@id,'Grade 1')]")).click();

        driver.findElement(By.xpath(".//*[@id='select2-sortSelection-container']")).click();
         sortElements=driver.findElements(By.xpath("//ul[@id='select2-sortSelection-results']/li"));
        sortElements.get(3).click();
        driver.findElement(By.id("search-fld")).sendKeys(assessmentname);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        driver.findElements(By.xpath("//input[@name='selected-owner']/following-sibling::ins")).get(4).click();
        driver.findElements(By.cssSelector("div[class='font-semi-bold space-15 assign-title']")).get(0).click();//click on Assignment

        driver.findElement(By.className("assessment-edit-sharing")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='sharingEnabled']/following-sibling::ins")).click();
        //   firefoxdriver.findElement(By.id("sharingEnabled")).click();
        driver.findElement(By.id("update-shared-assessment-access-control")).click();
        new Navigator().logout();//logout
        new Login().loginAsInstructor(appendChar1,dataIndex);
        driver.get(assessmentPublicURl);
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("as-assessment-name")));
    }

    @Test(priority = 3,enabled = true)
    public void shareAssessmentWithPrivate() throws Exception
    {
        int dataIndex = 77;
        String appendChar = "a33";
        String appendChar1= "b33";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String updatedAssessmentName=ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver, AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assignments assignments=PageFactory.initElements(driver,Assignments.class);
        StudentResponse studentResponse=PageFactory.initElements(driver,StudentResponse.class);
        MyReports instructorReport = PageFactory.initElements(driver,MyReports.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);



        new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
        new School().createWithOnlyName(appendChar,dataIndex);//Create a school
        new Classes().createClass(appendChar,dataIndex);//Create class
        new Assignment().create(dataIndex,"truefalse");//create assessment
        new Assignment().addQuestion(dataIndex,"multiplechoice");//add question
        new Assignment().addQuestion(dataIndex,"multipleselection");//add question
        new Assignment().assignToStudent(dataIndex,appendChar);//assign as assessment
        String assessmentPublicURl=assign.getAssessmentShareLink().getText();//fetch url from UI
        new Navigator().logout();//logout

        new SignUp().teacher(appendChar1,dataIndex);//Sign up as a 2nd teacher
        new School().createWithOnlyName(appendChar1,dataIndex);//Create a school
        String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
        driver.get(assessmentPublicURl);
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("as-assessment-name")));
        new Navigator().logout();//logout
        new Login().loginAsInstructor(appendChar,dataIndex);
        new Assignment().editPublishedAssessment(dataIndex, appendChar);//edit published assessment
        assessmentReview.saveInDrafts.click();
        new Assignment().assignToStudent(78,appendChar);
        assessmentPublicURl=assign.getAssessmentShareLink().getText();//fetch url from UI
        new Navigator().logout();//logout
        new Login().loginAsInstructor(appendChar1,dataIndex);
        driver.get(assessmentPublicURl);
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("toast-message")));
        Assert.assertEquals(assessmentLibrary.errorMessage.getText(), "You are not authorized to view this assessment.", "error message not matches");
        new Navigator().logout();//logout
    }





    @Test(priority =4,enabled = true)
    public void editAssignmentAfterAssigning() throws Exception
    {
        int dataIndex =79;
        String appendChar = "a38";
        String appendChar1= "b38";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String updatedAssessmentName=ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver, AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assignments assignments=PageFactory.initElements(driver,Assignments.class);
        StudentResponse studentResponse=PageFactory.initElements(driver,StudentResponse.class);
        MyReports instructorReport = PageFactory.initElements(driver,MyReports.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);

        new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher

        new School().createWithOnlyName(appendChar,dataIndex);//Create a school
        String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
        new Navigator().logout();
        new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//signup as student
        new Navigator().logout();
        new Login().loginAsInstructor(appendChar,dataIndex);
        new Assignment().create(dataIndex,"truefalse");//create assessment
        new Assignment().addQuestion(dataIndex,"multiplechoice");//add question
        new Assignment().addQuestion(dataIndex,"multipleselection");//add question
        new Assignment().assignToStudent(dataIndex,appendChar);//assign as assessment
        String assessmentPublicURl=assign.getAssessmentShareLink().getText();//fetch url from UI
        new Assignment().editPublishedAssignment(dataIndex,appendChar);//edit published assessment
        //deselect first question
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click();",assessmentReview.checkBoxQuestion.get(0));
        Thread.sleep(2000);
        Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multiple Choice " + questiontext), "Question dosen't match");
        Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multi Selection " + questiontext), "Question dosen't match");
        //change assessment name
        driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name.form-control")).clear();
        driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name.form-control")).sendKeys(updatedAssessmentName);
        //resequence the questions
        Actions action=new Actions(driver);
        action.clickAndHold(assessmentReview.dragIcon.get(0)).moveToElement(assessmentReview.dragIcon.get(1),0,100).release().build().perform();
        Thread.sleep(3000);
        Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multi Selection " + questiontext), "Question dosen't match");
        Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multiple Choice " + questiontext), "Question dosen't match");
        assessmentReview.saveInDrafts.click();
        //assign as assessment and sharing as private
        new Assignment().assignToStudent(dataIndex,appendChar);

        new Navigator().logout();//logout

        new SignUp().teacher(appendChar1,dataIndex);//Sign up as a 2nd teacher
        new School().createWithOnlyName(appendChar1,dataIndex);//Create a school
        new Classes().createClass(appendChar1,dataIndex);//Create class
        driver.get(assessmentPublicURl);
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("as-assessment-name")));
        Assert.assertEquals(assessmentDetailsPage.numberOfQuestion.size(),2,"number of question are not same as expected");
        new Navigator().logout();//logout
        new Login().loginAsStudent(appendChar,dataIndex);
        new Assignment().openAssignment(dataIndex);

        new Assignment().attemptQuestions("truefalse",dataIndex);
        new Assignment().attemptQuestions("multiplechoice",dataIndex);
        new Assignment().attemptQuestions("multipleselection",dataIndex);

        TakeAssignment takeAssignment =PageFactory.initElements(driver,TakeAssignment.class);
        takeAssignment.submitButton.click();


    }

    @Test(priority =5,enabled = true)
    public void editAssignmentUsageCount1() throws Exception
    {
        int dataIndex = 81;
        String appendChar ="a26";
        String appendChar1="b26";
        String appendChar2="c26";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver,AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().addQuestion(dataIndex,"multiplechoice");//add question
            new Assignment().addQuestion(dataIndex,"multipleselection");//add question
            new Assignment().assignToStudent(dataIndex,appendChar);//assign to student
            String assessmentPublicURl=assign.getAssessmentShareLink().getText();//fetch url from UI
            System.out.println(assessmentPublicURl);
            new Navigator().logout();
            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar1,dataIndex);//Create a school
            new Classes().createClass(appendChar1,dataIndex);//Create class
            new Assignment().useExistingAssignment(82,appendChar1);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().editPublishedAssignment(dataIndex,appendChar);
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",assessmentReview.checkBoxQuestion.get(0));
            Thread.sleep(2000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multi Selection " + questiontext), "Question dosen't match");
            //change assessment name
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name.form-control")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name.form-control")).sendKeys(assessmentname);
            //resequence the quesntions
            Actions action=new Actions(driver);
            action.clickAndHold(assessmentReview.dragIcon.get(0)).moveToElement(assessmentReview.dragIcon.get(1),0,100).release().build().perform();
            Thread.sleep(3000);
            assessmentReview.saveInDrafts.click();
            new Navigator().navigateTo("myAssessments");//Navigate to my assignment page
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");
            int index = 0;
            int count=0;
            List<WebElement> allAssessment = driver.findElements(By.cssSelector("div[class='font-semi-bold space-15 assign-title']"));//as per E8
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assessmentname))
                {
                    count=count+1;
                    break;
                }

                else
                    index++;

            }
            if(count==0)
            {
                Assert.fail("Assessment is not present under draft tab");
            }

            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", index);//click on Assignment
            Thread.sleep(2000);
            //verify assessment details
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");

            driver.findElement(By.id("go-back")).click();
            Thread.sleep(2000);


            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            index = 0;
            count=0;
             allAssessment = driver.findElements(By.cssSelector("div[class='font-semi-bold space-15 assign-title']"));//as per E8
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assessmentname))
                {
                    count++;
                    break;
                }

                else
                    index++;

            }
            if(count==0)
            {
                Assert.fail("Assessment is not present under published tab");
            }
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", index);//click on Assignment
            Thread.sleep(2000);
            //verify assessment details
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(2).getText().contains("Q3:  Multi Selection " + questiontext), "Question dosen't match");

            new Navigator().logout();
            new SignUp().teacher(appendChar2,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar2,dataIndex);//Create a school
            new Classes().createClass(appendChar2,dataIndex);//Create class
            driver.get(assessmentPublicURl);
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("as-assessment-name")));
            new Navigator().logout();


 }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment",e);
        }
    }




    @Test(priority =6,enabled = true)
    public void editAssessementUsageCount0() throws Exception
    {
        int dataIndex = 88;
        String appendChar ="a26";
        String appendChar1="b26";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver,AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().addQuestion(dataIndex,"multiplechoice");//add question
            new Assignment().addQuestion(dataIndex,"multipleselection");//add question
            new Assignment().assignToStudent(dataIndex,appendChar);//assign to student
            String assessmentPublicURl=assign.getAssessmentShareLink().getText();//fetch url from UI
            new Assignment().editPublishedAssessment(dataIndex,appendChar);
            assessmentReview.saveInDrafts.click();
            new Navigator().logout();
            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar1,dataIndex);//Create a school
            new Classes().createClass(appendChar1,dataIndex);//Create class
            driver.get(assessmentPublicURl);
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("toast-message")));
            Assert.assertEquals(assessmentLibrary.errorMessage.getText(),"Assessments not found.","error message not matches");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment",e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void customizedEditAssessment()
    {
        int dataIndex = 88;
        String appendChar ="a30";
        String appendChar1="b30";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver,AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        try {
            new SignUp().teacher(appendChar, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar, dataIndex);//Create class
            new Assignment().create(dataIndex, "truefalse");
            new Assignment().assignToStudent(dataIndex, appendChar);//assign to student
            new Assignment().editPublishedAssessment(dataIndex,appendChar);
            assessmentReview.addMore.click();
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//click on the create besides elos
            new Click().clickByid("qtn-true-false-type");//click on the true false question
            new TextSend().textsendbyid("sample true false question", "question-raw-content");
            new Click().clickByclassname("true-false-answer-select");
            new TextSend().textsendbyclass("assignmnet", "lsm-createAssignment-input-name");
            new Click().clickByid("saveQuestionDetails1");//click on the save button
            Thread.sleep(3000);
            new Click().clickBycssselector("button[class='btn btn-rounded btn-default lsm-createAssignment-done m-r-md selected btn-blue']");//click on the review button
            new Click().clickByid("assessments-use-button");//click on the next button
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id("assign-button")));
            assign.getButton_assign().click();
            assign.getButton_assign().click();
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.share-link-url.col-xs-9")));
            String assessmentPublicURl=assign.getAssessmentShareLink().getText();//fetch url from UI
            new Assignment().editPublishedAssessment(dataIndex,appendChar);
            //deselect first question
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",assessmentReview.checkBoxQuestion.get(0));
            Thread.sleep(2000);
            new Click().clickByid("assessments-use-button");//click on the next button
            assign.getButton_assign().click();
            assign.getButton_assign().click();
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.share-link-url.col-xs-9")));
            assessmentPublicURl=assign.getAssessmentShareLink().getText();//fetch url from UI
            new Navigator().logout();
            new SignUp().teacher(appendChar1, dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar1, dataIndex);//Create a school
            classCode = new Classes().createClass(appendChar1, dataIndex);//Create class
            driver.get(assessmentPublicURl);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessmentAfterSharing",e);
        }
    }
}
