package com.snapwiz.selenium.tests.staf.edulastic.testcases.e9.editassessesment;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentDetailsPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentLibrary;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssignmentReview;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assign;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 24-09-2015.
 */
public class EditAssessmentDeselectResequence extends Driver{




    @Test(priority = 1,enabled = true)
    public void editAssessmentDeselectResequenceSharingPrivate()

    {
        int dataIndex = 9;
        String appendChar = "a235";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver, AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        try {

              new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher

            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().addQuestion(dataIndex,"multiplechoice");
            new Assignment().addQuestion(dataIndex,"multipleselection");
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Assignment().editPublishedAssessment(dataIndex,appendChar);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(2).getText().contains("Q3:  Multi Selection " + questiontext), "Question dosen't match");

            //deselect first question
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",assessmentReview.checkBoxQuestion.get(0));
            Thread.sleep(2000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multi Selection " + questiontext), "Question dosen't match");

            Actions action=new Actions(driver);
            action.clickAndHold(assessmentReview.dragIcon.get(0)).moveToElement(assessmentReview.dragIcon.get(1),0,100).release().build().perform();
            Thread.sleep(3000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multiple Choice " + questiontext), "Question dosen't match");


            assessmentReview.nextButton.click();//click on next button to assign
            assign.getRadio_button_rightNow().click();
            Thread.sleep(1000);
            Assert.assertEquals(assign.totalPoints.getText(),"2","Total point is not matching as expected");
            //verify the sharing level should be private
            Assert.assertTrue(assign.getRadioButton_private().getAttribute("class").contains("checked"), "Sharing level is not private");
            //click on assign button
            assign.saveAssessmentAndUseLater.click();

            assign.getButton_assign().click();
            Thread.sleep(2000);
            new Navigator().navigateTo("assessmentLibrary");
            //select sharing level as 'Private'
            assessmentLibrary.sharingLevelPrivate.click();
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");





        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment",e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void editAssessmentDeselectResequenceSharingSchool()

    {
        int dataIndex = 10;
        String appendChar = "a14";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver,AssessmentDetailsPage.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().addQuestion(dataIndex,"multiplechoice");
            new Assignment().addQuestion(dataIndex,"multipleselection");
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Assignment().editPublishedAssessment(dataIndex,appendChar);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(2).getText().contains("Q3:  Multi Selection " + questiontext), "Question dosen't match");

            //deselect first question
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",assessmentReview.checkBoxQuestion.get(0));
            Thread.sleep(2000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multi Selection " + questiontext), "Question dosen't match");

            Actions action=new Actions(driver);
            action.clickAndHold(assessmentReview.dragIcon.get(0)).moveToElement(assessmentReview.dragIcon.get(1),0,100).release().build().perform();
            Thread.sleep(3000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multiple Choice " + questiontext), "Question dosen't match");


            assessmentReview.nextButton.click();//click on next button to assign

            assign.getRadio_button_rightNow().click();
            Thread.sleep(1000);
            Assert.assertEquals(assign.totalPoints.getText(),"2","Total point is not matching as expected");
            //verify the sharing level should be private
            Assert.assertTrue(assign.getRadioButton_school().getAttribute("class").contains("checked"), "Sharing level is not private");
            //click on assign button
            assign.saveAssessmentAndUseLater.click();

            assign.getButton_assign().click();
            Thread.sleep(2000);
            new Navigator().navigateTo("myAssessments");
            //select sharing level as 'Private'
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment");
        }
    }


    @Test(priority = 3,enabled = true)
    public void editAssessmentDeselectResequenceSharingDistrict()

    {
        int dataIndex =11;
        String appendChar = "a8";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver,AssessmentDetailsPage.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().addQuestion(dataIndex,"multiplechoice");
            new Assignment().addQuestion(dataIndex,"multipleselection");
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Assignment().editPublishedAssessment(dataIndex,appendChar);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(2).getText().contains("Q3:  Multi Selection " + questiontext), "Question dosen't match");

            //deselect first question
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",assessmentReview.checkBoxQuestion.get(0));
            Thread.sleep(2000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multi Selection " + questiontext), "Question dosen't match");

            Actions action=new Actions(driver);
            action.clickAndHold(assessmentReview.dragIcon.get(0)).moveToElement(assessmentReview.dragIcon.get(1),0,100).release().build().perform();
            Thread.sleep(3000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multiple Choice " + questiontext), "Question dosen't match");


            assessmentReview.nextButton.click();//click on next button to assign
            assign.getRadio_button_rightNow().click();
            Thread.sleep(1000);
            Assert.assertEquals(assign.totalPoints.getText(),"2","Total point is not matching as expected");
            //verify the sharing level should be private
            Assert.assertTrue(assign.getRadioButton_district().getAttribute("class").contains("checked"), "Sharing level is not private");
            //click on assign button
            assign.saveAssessmentAndUseLater.click();

            assign.getButton_assign().click();
            Thread.sleep(2000);
            new Navigator().navigateTo("myAssessments");
            //select sharing level as 'Private'
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment");
        }
    }


    @Test(priority = 4,enabled = true)
    public void editAssessmentDeselectResequenceSharingPublic()

    {
        int dataIndex = 12;
        String appendChar = "a8";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver,AssessmentDetailsPage.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().addQuestion(dataIndex,"multiplechoice");
            new Assignment().addQuestion(dataIndex,"multipleselection");
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Assignment().editPublishedAssessment(dataIndex,appendChar);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(2).getText().contains("Q3:  Multi Selection " + questiontext), "Question dosen't match");

            //deselect first question
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",assessmentReview.checkBoxQuestion.get(0));
            Thread.sleep(2000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multi Selection " + questiontext), "Question dosen't match");

            Actions action=new Actions(driver);
            action.clickAndHold(assessmentReview.dragIcon.get(0)).moveToElement(assessmentReview.dragIcon.get(1),0,100).release().build().perform();
            Thread.sleep(3000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multiple Choice " + questiontext), "Question dosen't match");


            assessmentReview.nextButton.click();//click on next button to assign
            assign.getRadio_button_rightNow().click();
            Thread.sleep(1000);
            Assert.assertEquals(assign.totalPoints.getText(),"2","Total point is not matching as expected");
            //verify the sharing level should be private
            Assert.assertTrue(assign.getRadioButton_public().getAttribute("class").contains("checked"), "Sharing level is not private");
            //click on assign button
            assign.saveAssessmentAndUseLater.click();

            assign.getButton_assign().click();
            Thread.sleep(2000);
            new Navigator().navigateTo("myAssessments");
            //select sharing level as 'Private'
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment");
        }
    }
    @Test(priority = 5,enabled = true)
     public void editAssignmentDeselectResequenceSharingPrivate()

    {
        int dataIndex = 13;
        String appendChar = "a20";
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
            new Navigator().logout();
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().addQuestion(dataIndex,"multiplechoice");
            new Assignment().addQuestion(dataIndex,"multipleselection");
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Assignment().editPublishedAssignment(dataIndex,appendChar);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(2).getText().contains("Q3:  Multi Selection " + questiontext), "Question dosen't match");

            //deselect first question
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",assessmentReview.checkBoxQuestion.get(0));
            Thread.sleep(2000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multi Selection " + questiontext), "Question dosen't match");

            Actions action=new Actions(driver);
            action.clickAndHold(assessmentReview.dragIcon.get(0)).moveToElement(assessmentReview.dragIcon.get(1),0,100).release().build().perform();
            Thread.sleep(3000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multiple Choice " + questiontext), "Question dosen't match");


            assessmentReview.nextButton.click();//click on next button to assign
            assign.getRadio_button_rightNow().click();
            Thread.sleep(1000);
            Assert.assertEquals(assign.totalPoints.getText(),"2","Total point is not matching as expected");
            //verify the sharing level should be private
            Assert.assertTrue(assign.getRadioButton_private().getAttribute("class").contains("checked"), "Sharing level is not private");
            //click on assign button
            assign.saveAssessmentAndUseLater.click();

            assign.getButton_assign().click();
            Thread.sleep(2000);
            new Navigator().navigateTo("myAssessments");
            //select sharing level as 'Private'
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssignmentSharingPrivate method in class EditAssignment",e);
        }
    }

    @Test(priority = 6,enabled = true)
     public void editAssignmentDeselectResequenceSharingSchool()

    {
        int dataIndex = 14;
        String appendChar = "a8";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver,AssessmentDetailsPage.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().addQuestion(dataIndex,"multiplechoice");
            new Assignment().addQuestion(dataIndex,"multipleselection");
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Assignment().editPublishedAssignment(dataIndex,appendChar);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(2).getText().contains("Q3:  Multi Selection " + questiontext), "Question dosen't match");

            //deselect first question
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",assessmentReview.checkBoxQuestion.get(0));
            Thread.sleep(2000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multi Selection " + questiontext), "Question dosen't match");

            Actions action=new Actions(driver);
            action.clickAndHold(assessmentReview.dragIcon.get(0)).moveToElement(assessmentReview.dragIcon.get(1),0,100).release().build().perform();
            Thread.sleep(3000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multiple Choice " + questiontext), "Question dosen't match");


            assessmentReview.nextButton.click();//click on next button to assign
            assign.getRadio_button_rightNow().click();
            Thread.sleep(1000);
            Assert.assertEquals(assign.totalPoints.getText(),"2","Total point is not matching as expected");
            //verify the sharing level should be private
            Assert.assertTrue(assign.getRadioButton_school().getAttribute("class").contains("checked"), "Sharing level is not private");
            //click on assign button
            assign.saveAssessmentAndUseLater.click();

            assign.getButton_assign().click();
            Thread.sleep(2000);
            new Navigator().navigateTo("myAssessments");
            //select sharing level as 'Private'
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssignmentSharingPrivate method in class EditAssignment");
        }
    }


    @Test(priority = 7,enabled = true)
     public void editAssignmentDeselectResequenceSharingDistrict()

    {
        int dataIndex =15;
        String appendChar = "a8";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver,AssessmentDetailsPage.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().addQuestion(dataIndex,"multiplechoice");
            new Assignment().addQuestion(dataIndex,"multipleselection");
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Assignment().editPublishedAssignment(dataIndex,appendChar);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(2).getText().contains("Q3:  Multi Selection " + questiontext), "Question dosen't match");

            //deselect first question
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",assessmentReview.checkBoxQuestion.get(0));
            Thread.sleep(2000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multi Selection " + questiontext), "Question dosen't match");

            Actions action=new Actions(driver);
            action.clickAndHold(assessmentReview.dragIcon.get(0)).moveToElement(assessmentReview.dragIcon.get(1),0,100).release().build().perform();
            Thread.sleep(3000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multiple Choice " + questiontext), "Question dosen't match");


            assessmentReview.nextButton.click();//click on next button to assign
            assign.getRadio_button_rightNow().click();
            Thread.sleep(1000);
            Assert.assertEquals(assign.totalPoints.getText(),"2","Total point is not matching as expected");
            //verify the sharing level should be private
            Assert.assertTrue(assign.getRadioButton_district().getAttribute("class").contains("checked"), "Sharing level is not private");
            //click on assign button
            assign.saveAssessmentAndUseLater.click();

            assign.getButton_assign().click();
            Thread.sleep(2000);
            new Navigator().navigateTo("myAssessments");
            //select sharing level as 'Private'
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssignmentSharingPrivate method in class EditAssignment");
        }
    }


    @Test(priority = 8,enabled = true)
     public void editAssignmentDeselectResequenceSharingPublic()

    {
        int dataIndex = 16;
        String appendChar = "a11";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver,AssessmentDetailsPage.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().addQuestion(dataIndex,"multiplechoice");
            new Assignment().addQuestion(dataIndex,"multipleselection");
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Assignment().editPublishedAssignment(dataIndex,appendChar);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(2).getText().contains("Q3:  Multi Selection " + questiontext), "Question dosen't match");

            //deselect first question
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",assessmentReview.checkBoxQuestion.get(0));
            Thread.sleep(2000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multiple Choice " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multi Selection " + questiontext), "Question dosen't match");

            Actions action=new Actions(driver);
            action.clickAndHold(assessmentReview.dragIcon.get(0)).moveToElement(assessmentReview.dragIcon.get(1),0,100).release().build().perform();
            Thread.sleep(3000);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1: Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2: Multiple Choice " + questiontext), "Question dosen't match");


            assessmentReview.nextButton.click();//click on next button to assign
            assign.getRadio_button_rightNow().click();
            Thread.sleep(1000);
            Assert.assertEquals(assign.totalPoints.getText(),"2","Total point is not matching as expected");
            //verify the sharing level should be private
            Assert.assertTrue(assign.getRadioButton_public().getAttribute("class").contains("checked"), "Sharing level is not private");
            //click on assign button
            assign.saveAssessmentAndUseLater.click();

            assign.getButton_assign().click();
            Thread.sleep(2000);
            new Navigator().navigateTo("myAssessments");
            //select sharing level as 'Private'
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  Multi Selection " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment");
        }
    }
}
