package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.editpoints;

import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.StudentDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.DragAndDrop;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.KeysSend;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by pragyas on 26-07-2016.
 */
public class EditAssessment extends Driver{

    @Test(priority = 1,enabled = true)
    public void editAssessmentSharedAtPublicLevel(){
        try{
            ReportUtil.log("Description", "This test case validates the details of edited assessment shared at public level like no. of questions, usage count etc", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 1;
            String appendChar= "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            System.out.println("append character is "+appendChar);

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);
            TrueFalseQuestionCreation questionCreate = PageFactory.initElements(driver,TrueFalseQuestionCreation.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("98765496", dataIndex, false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            new Classes().createClass(appendChar, dataIndex);//Create class
            new Assignment().create(dataIndex, "truefalse");//Create an assignment with true false question type
            new Assignment().addQuestion(dataIndex, "multiplechoice");//Add a question

            WebDriverUtil.waitTillInvisibilityOfElement(By.id("toast-container"),60);
            questionCreate.getButton_review().click();//Click on review assessment
            listPage.getButton_next().click();//Click on publish or assign button

            //Verify the total score
            CustomAssert.assertEquals(assign.totalPoints.getText(),"Total points: 2","Verify the total points","Total point is displayed as expected","Total point is not displayed as expected");

            assign.getRadioButton_public().click();//Click on public radio button
            assign.getButton_assign().click();//Click on Publish button
            assign.button_viewAssignment.click();//Click on view assignment
            WebDriverUtil.waitForAjax(driver,60);
            new Common().waitForUIBlockerToClose(60);
            myAssessments.assignmentList.get(0).click();//Click on assessment

            String usageCountBeforeEdit = assessmentDetailsPage.usageCountOfAssignment.getText();

            //Usage count should be zero
            CustomAssert.assertTrue(usageCountBeforeEdit.contains("0 time(s)"),"Verify the usage count","Usage count is displayed as expected","Usage count is not displayed as expected");

            assessmentDetailsPage.tabNotRated.click();//Click on rating tab
            String ratingBeforeEdit = assessmentDetailsPage.message_notRated.getText();

            //Verify rating
            CustomAssert.assertEquals(ratingBeforeEdit,"This assessment has not been rated.","Verify the rating","Rating is displayed as expected","Rating is not displayed as expected");

            assessmentDetailsPage.editAssessment.click();//Click on edit

            //A blink notification should be displayed to the user
            CustomAssert.assertTrue(assessmentDetailsPage.confirmation_popUp.get(0).getText().contains("You are about to edit an assessment that has already been published. If you wish to edit this assessment, we will move this assessment to draft status. Do you want to proceed?"),"Verify the confirmation pop up on after clicking on edit","Confirmation pop up is displayed as expected","Confirmation pop up is not displayed");

            assessmentDetailsPage.NoButton_confirmationPopUp.click();//Click on No button
            new Assignment().addQuestion(dataIndex,"multipleselection");//Add one more question in the assessment
            new Common().waitForToastBlockerToClose(120);
            questionCreate.getButton_review().click();//Click on review assessment
            listPage.getButton_next().click();//click on assign page

            //Verify the total point should be increased after adding a neq question to assessment
            CustomAssert.assertEquals(assign.totalPoints.getText(),"Total points: 3","Verify the total points after adding a new question to assessment","Total point is increased as expected","Total point is not increased as expected");

            //Sharing level should be same as that assigned to parent assessment
            CustomAssert.assertEquals(assign.getRadioButton_public().getAttribute("class"),"iradio_square-green checked","Verify the sharing level","Sharing level is same as parent assessment","Sharing level is not same as parent assessment");

            assign.getButton_assign().click();//click on publish button

            WebDriverUtil.waitTillVisibilityOfElement(assign.button_viewAssignment,30);
            assign.button_viewAssignment.click();//Click on view assessment
            WebDriverUtil.waitForAjax(driver,60);
            new Common().waitForUIBlockerToClose(120);
            myAssessments.assignmentList.get(0).click();//Click on assessment

            //At assessment details page, added questions should be displayed
            CustomAssert.assertEquals(String.valueOf(assessmentDetailsPage.numberOfQuestion.size()),"3","Verify the total number of questions","Total number of questions are displayed as expected","Total number of questions are not correct");
            CustomAssert.assertTrue(assessmentDetailsPage.numberOfQuestion.get(2).getText().contains("Multi Selection " + questiontext), "Verify the newly added question", "Newly added question is displayed as expected", "Newly added question is not displayed");

            //The new assessment should retain usage count of the previous assessment
            CustomAssert.assertEquals(assessmentDetailsPage.usageCountOfAssignment.getText(),usageCountBeforeEdit,"Verify the usage count after editing the assessment","New assessment is retaining the previous assessment usage count","New Assessment is not retaining the previous assessment usage count");

            assessmentDetailsPage.tabNotRated.click();//Click on rating tab

            //The new assessment should retain the ratings of the previous assessment
            CustomAssert.assertEquals(assessmentDetailsPage.message_notRated.getText(),ratingBeforeEdit,"Verify the rating after editing the assessment","New assessment is retaining the previous assessment rating","New Assessment is not retaining the previous assessment rating");

        }catch (Exception e)
        {
            Assert.fail("Exception in testcase 'editAssessmentSharedAtPublicLevel' in class 'EditAssessment'", e);
        }
    }


    @Test(priority = 2,enabled = true)
    public void editAssessmentSharedAtDistrictLevel(){
        try{
            ReportUtil.log("Description", "This test case validates the details of edited assessment shared at district level like no. of questions, usage count etc", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 2;
            String appendChar= "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);
            TrueFalseQuestionCreation questionCreate = PageFactory.initElements(driver,TrueFalseQuestionCreation.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            new Classes().createClass(appendChar, dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");//Create an assignment with true false question type
            new Assignment().addQuestion(dataIndex,"multiplechoice");//Add a question
            new Common().waitForToastBlockerToClose(120);
            questionCreate.getButton_review().click();//Click on review assessment
            listPage.getButton_next().click();//Click on publish or assign button

            //Verify the total score
            CustomAssert.assertEquals(assign.totalPoints.getText(),"Total points: 2","Verify the total points","Total point is displayed as expected","Total point is not displayed as expected");

            assign.getRadioButton_district().click();//Click on district radio button
            assign.getButton_assign().click();//Click on Publish button
            assign.button_viewAssignment.click();//Click on view assignment
            WebDriverUtil.waitForAjax(driver, 60);
            new Common().waitForUIBlockerToClose(120);
            myAssessments.assignmentList.get(0).click();//Click on assessment

            String usageCountBeforeEdit = assessmentDetailsPage.usageCountOfAssignment.getText();

            //Usage count should be zero
            CustomAssert.assertTrue(usageCountBeforeEdit.contains("0 time(s)"),"Verify the usage count","Usage count is displayed as expected","Usage count is not displayed as expected");

            assessmentDetailsPage.tabNotRated.click();//Click on rating tab
            String ratingBeforeEdit = assessmentDetailsPage.message_notRated.getText();

            //Verify rating
            CustomAssert.assertEquals(ratingBeforeEdit,"This assessment has not been rated.","Verify the rating","Rating is displayed as expected","Rating is not displayed as expected");

            assessmentDetailsPage.editAssessment.click();//Click on edit

            //A blink notification should be displayed to the user
            CustomAssert.assertTrue(assessmentDetailsPage.confirmation_popUp.get(0).getText().contains("You are about to edit an assessment that has already been published. If you wish to edit this assessment, we will move this assessment to draft status. Do you want to proceed?"),"Verify the confirmation pop up on after clicking on edit","Confirmation pop up is displayed as expected","Confirmation pop up is not displayed");

            assessmentDetailsPage.NoButton_confirmationPopUp.click();//Click on No button
            new Assignment().addQuestion(dataIndex,"multipleselection");//Add one more question in the assessment
            new Common().waitForToastBlockerToClose(120);
            questionCreate.getButton_review().click();//Click on review assessment
            listPage.getButton_next().click();//click on assign page

            //Verify the total point should be increased after adding a neq question to assessment
            CustomAssert.assertEquals(assign.totalPoints.getText(),"Total points: 3","Verify the total points after adding a new question to assessment","Total point is increased as expected","Total point is not increased as expected");

            //Sharing level should be same as that assigned to parent assessment
            CustomAssert.assertEquals(assign.getRadioButton_district().getAttribute("class"),"iradio_square-green checked","Verify the sharing level","Sharing level is same as parent assessment","Sharing level is not same as parent assessment");

            assign.getButton_assign().click();//click on publish button

            WebDriverUtil.waitTillVisibilityOfElement(assign.button_viewAssignment,30);
            assign.button_viewAssignment.click();//Click on view assessment
            WebDriverUtil.waitForAjax(driver,60);
            new Common().waitForUIBlockerToClose(120);
            myAssessments.assignmentList.get(0).click();//Click on assessment

            //At assessment details page, added questions should be displayed
            CustomAssert.assertEquals(String.valueOf(assessmentDetailsPage.numberOfQuestion.size()),"3","Verify the total number of questions","Total number of questions are displayed as expected","Total number of questions are not correct");
            CustomAssert.assertTrue(assessmentDetailsPage.numberOfQuestion.get(2).getText().contains("Multi Selection " + questiontext), "Verify the newly added question", "Newly added question is displayed as expected", "Newly added question is not displayed");

            //The new assessment should retain usage count of the previous assessment
            CustomAssert.assertEquals(assessmentDetailsPage.usageCountOfAssignment.getText(),usageCountBeforeEdit,"Verify the usage count after editing the assessment","New assessment is retaining the previous assessment usage count","New Assessment is not retaining the previous assessment usage count");

            assessmentDetailsPage.tabNotRated.click();//Click on rating tab

            //The new assessment should retain the ratings of the previous assessment
            CustomAssert.assertEquals(assessmentDetailsPage.message_notRated.getText(),ratingBeforeEdit,"Verify the rating after editing the assessment","New assessment is retaining the previous assessment rating","New Assessment is not retaining the previous assessment rating");

        }catch (Exception e)
        {
            Assert.fail("Exception in testcase 'editAssessmentSharedAtDistrictLevel' in class 'EditAssessment'", e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void editAssessmentSharedAtSchoolLevel(){
        try{
            ReportUtil.log("Description", "This test case validates the details of edited assessment shared at school level like no. of questions, usage count etc", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 3;
            String appendChar= "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);
            TrueFalseQuestionCreation questionCreate = PageFactory.initElements(driver,TrueFalseQuestionCreation.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            new Classes().createClass(appendChar, dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");//Create an assignment with true false question type
            new Common().waitForToastBlockerToClose(120);
            new Assignment().addQuestion(dataIndex,"multiplechoice");//Add a question
            WebDriverUtil.waitForAjax(driver,60);
            new Common().waitForToastBlockerToClose(120);
            questionCreate.getButton_review().click();//Click on review assessment
            listPage.getButton_next().click();//Click on publish or assign button

            //Verify the total score
            CustomAssert.assertEquals(assign.totalPoints.getText(),"Total points: 2","Verify the total points","Total point is displayed as expected","Total point is not displayed as expected");

            assign.getRadioButton_school().click();//Click on district radio button
            assign.getButton_assign().click();//Click on Publish button
            assign.button_viewAssignment.click();//Click on view assignment
            WebDriverUtil.waitForAjax(driver,60);
            new Common().waitForUIBlockerToClose(120);
            myAssessments.assignmentList.get(0).click();//Click on assessment

            String usageCountBeforeEdit = assessmentDetailsPage.usageCountOfAssignment.getText();

            //Usage count should be zero
            CustomAssert.assertTrue(usageCountBeforeEdit.contains("0 time(s)"),"Verify the usage count","Usage count is displayed as expected","Usage count is not displayed as expected");

            assessmentDetailsPage.tabNotRated.click();//Click on rating tab
            String ratingBeforeEdit = assessmentDetailsPage.message_notRated.getText();

            //Verify rating
            CustomAssert.assertEquals(ratingBeforeEdit,"This assessment has not been rated.","Verify the rating","Rating is displayed as expected","Rating is not displayed as expected");

            assessmentDetailsPage.editAssessment.click();//Click on edit

            //A blink notification should be displayed to the user
            CustomAssert.assertTrue(assessmentDetailsPage.confirmation_popUp.get(0).getText().contains("You are about to edit an assessment that has already been published. If you wish to edit this assessment, we will move this assessment to draft status. Do you want to proceed?"),"Verify the confirmation pop up on after clicking on edit","Confirmation pop up is displayed as expected","Confirmation pop up is not displayed");

            assessmentDetailsPage.NoButton_confirmationPopUp.click();//Click on No button
            new Assignment().addQuestion(dataIndex,"multipleselection");//Add one more question in the assessment
            new Common().waitForToastBlockerToClose(120);
            questionCreate.getButton_review().click();//Click on review assessment
            listPage.getButton_next().click();//click on assign page

            //Verify the total point should be increased after adding a neq question to assessment
            CustomAssert.assertEquals(assign.totalPoints.getText(),"Total points: 3","Verify the total points after adding a new question to assessment","Total point is increased as expected","Total point is not increased as expected");

            //Sharing level should be same as that assigned to parent assessment
            CustomAssert.assertEquals(assign.getRadioButton_school().getAttribute("class"),"iradio_square-green checked","Verify the sharing level","Sharing level is same as parent assessment","Sharing level is not same as parent assessment");

            assign.getButton_assign().click();//click on publish button

            WebDriverUtil.waitTillVisibilityOfElement(assign.button_viewAssignment,30);
            assign.button_viewAssignment.click();//Click on view assessment
            WebDriverUtil.waitForAjax(driver, 60);
            new Common().waitForUIBlockerToClose(120);
            myAssessments.assignmentList.get(0).click();//Click on assessment

            //At assessment details page, added questions should be displayed
            CustomAssert.assertEquals(String.valueOf(assessmentDetailsPage.numberOfQuestion.size()),"3","Verify the total number of questions","Total number of questions are displayed as expected","Total number of questions are not correct");
            CustomAssert.assertTrue(assessmentDetailsPage.numberOfQuestion.get(2).getText().contains("Multi Selection " + questiontext), "Verify the newly added question", "Newly added question is displayed as expected", "Newly added question is not displayed");

            //The new assessment should retain usage count of the previous assessment
            CustomAssert.assertEquals(assessmentDetailsPage.usageCountOfAssignment.getText(),usageCountBeforeEdit,"Verify the usage count after editing the assessment","New assessment is retaining the previous assessment usage count","New Assessment is not retaining the previous assessment usage count");

            assessmentDetailsPage.tabNotRated.click();//Click on rating tab

            //The new assessment should retain the ratings of the previous assessment
            CustomAssert.assertEquals(assessmentDetailsPage.message_notRated.getText(),ratingBeforeEdit,"Verify the rating after editing the assessment","New assessment is retaining the previous assessment rating","New Assessment is not retaining the previous assessment rating");

        }catch (Exception e)
        {
            Assert.fail("Exception in testcase 'editAssessmentSharedAtSchoolLevel' in class 'EditAssessment'", e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void editAssessmentSharedAtPublicLevelWithUsageCountMoreThan0(){
        try{
            ReportUtil.log("Description", "This test case validates the details of edited assessment shared at public level like no. of questions, usage count etc when usage count is more than 0", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 4;
            String appendChar= "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);
            TrueFalseQuestionCreation questionCreate = PageFactory.initElements(driver,TrueFalseQuestionCreation.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            new Classes().createClass(appendChar, dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");//Create an assignment with true false question type
            new Assignment().addQuestion(dataIndex,"multiplechoice");//Add a question
            WebDriverUtil.waitTillInvisibilityOfElement(By.id("toast-container"),60);
            questionCreate.getButton_review().click();//Click on review assessment
            listPage.getButton_next().click();//Click on publish or assign button

            //Verify the total score
            CustomAssert.assertEquals(assign.totalPoints.getText(),"Total points: 2","Verify the total points","Total point is displayed as expected","Total point is not displayed as expected");

            new Assignment().assignToStudent(dataIndex,appendChar);

            new Navigator().navigateTo("myAssessments");//Navigate to my assessments
            WebDriverUtil.waitForAjax(driver, 60);
            new Common().waitForUIBlockerToClose(120);
            myAssessments.published.click();//Click on published tab
            new Common().waitForUIBlockerToClose(120);
            myAssessments.assignmentList.get(0).click();//Click on assessment

            String usageCountBeforeEdit = assessmentDetailsPage.usageCountOfAssignment.getText();

            //Usage count should be zero
            CustomAssert.assertTrue(usageCountBeforeEdit.contains("1 time(s)"),"Verify the usage count","Usage count is displayed as expected","Usage count is not displayed as expected");

            assessmentDetailsPage.tabNotRated.click();//Click on rating tab
            String ratingBeforeEdit = assessmentDetailsPage.message_notRated.getText();

            //Verify rating
            CustomAssert.assertEquals(ratingBeforeEdit,"This assessment has not been rated.","Verify the rating","Rating is displayed as expected","Rating is not displayed as expected");

            assessmentDetailsPage.editAssessment.click();//Click on edit

            //A blink notification should be displayed to the user
            CustomAssert.assertTrue(assessmentDetailsPage.confirmation_popUp.get(0).getText().contains("Other users won’t be able to view this assessment until you publish it. Do you want to proceed?"),"Verify the confirmation pop up on after clicking on edit","Confirmation pop up is displayed as expected","Confirmation pop up is not displayed");

            assessmentDetailsPage.NoButton_confirmationPopUp.click();//Click on No button
            new Assignment().addQuestion(dataIndex,"multipleselection");//Add one more question in the assessment
            WebDriverUtil.waitTillInvisibilityOfElement(By.id("toast-container"),60);
            questionCreate.getButton_review().click();//Click on review assessment
            listPage.getButton_next().click();//click on assign page

            //Verify the total point should be increased after adding a neq question to assessment
            CustomAssert.assertEquals(assign.totalPoints.getText(),"Total points: 3","Verify the total points after adding a new question to assessment","Total point is increased as expected","Total point is not increased as expected");

            //Sharing level should be same as that assigned to parent assessment
            CustomAssert.assertEquals(assign.getRadioButton_public().getAttribute("class"),"iradio_square-green checked","Verify the sharing level","Sharing level is same as parent assessment","Sharing level is not same as parent assessment");

            assign.getButton_assign().click();//click on publish button

            WebDriverUtil.waitTillVisibilityOfElement(assign.button_viewAssignment,30);
            assign.button_viewAssignment.click();//Click on view assessment
            new Common().waitForUIBlockerToClose(120);
            myAssessments.assignmentList.get(0).click();//Click on assessment

            //At assessment details page, added questions should be displayed
            CustomAssert.assertEquals(String.valueOf(assessmentDetailsPage.numberOfQuestion.size()),"3","Verify the total number of questions","Total number of questions are displayed as expected","Total number of questions are not correct");
            CustomAssert.assertTrue(assessmentDetailsPage.numberOfQuestion.get(2).getText().contains("Multi Selection " + questiontext), "Verify the newly added question", "Newly added question is displayed as expected", "Newly added question is not displayed");

            //The new assessment should retain usage count of the previous assessment
            CustomAssert.assertEquals(assessmentDetailsPage.usageCountOfAssignment.getText(),usageCountBeforeEdit,"Verify the usage count after editing the assessment","New assessment is retaining the previous assessment usage count","New Assessment is not retaining the previous assessment usage count");

            assessmentDetailsPage.tabNotRated.click();//Click on rating tab

            //The new assessment should retain the ratings of the previous assessment
            CustomAssert.assertEquals(assessmentDetailsPage.message_notRated.getText(),ratingBeforeEdit,"Verify the rating after editing the assessment","New assessment is retaining the previous assessment rating","New Assessment is not retaining the previous assessment rating");

        }catch (Exception e)
        {
            Assert.fail("Exception in testcase 'editAssessmentSharedAtPublicLevelWithUsageCountMoreThan0' in class 'EditAssessment'", e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void editAssessmentSharedAtDistrictLevelWithUsageCountMoreThan0(){
        try{
            ReportUtil.log("Description", "This test case validates the details of edited assessment shared at district level like no. of questions, usage count etc when usage count is more than 0", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 5;
            String appendChar= "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);
            TrueFalseQuestionCreation questionCreate = PageFactory.initElements(driver,TrueFalseQuestionCreation.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            new Classes().createClass(appendChar, dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");//Create an assignment with true false question type
            new Assignment().addQuestion(dataIndex,"multiplechoice");//Add a question
            WebDriverUtil.waitTillInvisibilityOfElement(By.id("toast-container"),60);
            questionCreate.getButton_review().click();//Click on review assessment
            listPage.getButton_next().click();//Click on publish or assign button

            //Verify the total score
            CustomAssert.assertEquals(assign.totalPoints.getText(),"Total points: 2","Verify the total points","Total point is displayed as expected","Total point is not displayed as expected");

            new Assignment().assignToStudent(dataIndex,appendChar);

            new Navigator().navigateTo("myAssessments");//Navigate to my assessments
            WebDriverUtil.waitForAjax(driver,60);
            new Common().waitForUIBlockerToClose(120);
            myAssessments.published.click();//Click on published tab
            new Common().waitForUIBlockerToClose(120);
            myAssessments.assignmentList.get(0).click();//Click on assessment

            String usageCountBeforeEdit = assessmentDetailsPage.usageCountOfAssignment.getText();

            //Usage count should be zero
            CustomAssert.assertTrue(usageCountBeforeEdit.contains("1 time(s)"),"Verify the usage count","Usage count is displayed as expected","Usage count is not displayed as expected");

            assessmentDetailsPage.tabNotRated.click();//Click on rating tab
            String ratingBeforeEdit = assessmentDetailsPage.message_notRated.getText();

            //Verify rating
            CustomAssert.assertEquals(ratingBeforeEdit,"This assessment has not been rated.","Verify the rating","Rating is displayed as expected","Rating is not displayed as expected");

            assessmentDetailsPage.editAssessment.click();//Click on edit

            //A blink notification should be displayed to the user
            CustomAssert.assertTrue(assessmentDetailsPage.confirmation_popUp.get(0).getText().contains("Other users won’t be able to view this assessment until you publish it. Do you want to proceed?"),"Verify the confirmation pop up on after clicking on edit","Confirmation pop up is displayed as expected","Confirmation pop up is not displayed");

            assessmentDetailsPage.NoButton_confirmationPopUp.click();//Click on No button
            new Assignment().addQuestion(dataIndex,"multipleselection");//Add one more question in the assessment
            WebDriverUtil.waitTillInvisibilityOfElement(By.id("toast-container"),60);
            questionCreate.getButton_review().click();//Click on review assessment
            listPage.getButton_next().click();//click on assign page

            //Verify the total point should be increased after adding a neq question to assessment
            CustomAssert.assertEquals(assign.totalPoints.getText(),"Total points: 3","Verify the total points after adding a new question to assessment","Total point is increased as expected","Total point is not increased as expected");

            //Sharing level should be same as that assigned to parent assessment
            CustomAssert.assertEquals(assign.getRadioButton_district().getAttribute("class"),"iradio_square-green checked","Verify the sharing level","Sharing level is same as parent assessment","Sharing level is not same as parent assessment");

            assign.getButton_assign().click();//click on publish button

            WebDriverUtil.waitTillVisibilityOfElement(assign.button_viewAssignment,30);
            assign.button_viewAssignment.click();//Click on view assessment
            new Common().waitForUIBlockerToClose(120);
            myAssessments.assignmentList.get(0).click();//Click on assessment

            //At assessment details page, added questions should be displayed
            CustomAssert.assertEquals(String.valueOf(assessmentDetailsPage.numberOfQuestion.size()),"3","Verify the total number of questions","Total number of questions are displayed as expected","Total number of questions are not correct");
            CustomAssert.assertTrue(assessmentDetailsPage.numberOfQuestion.get(2).getText().contains("Multi Selection " + questiontext), "Verify the newly added question", "Newly added question is displayed as expected", "Newly added question is not displayed");

            //The new assessment should retain usage count of the previous assessment
            CustomAssert.assertEquals(assessmentDetailsPage.usageCountOfAssignment.getText(),usageCountBeforeEdit,"Verify the usage count after editing the assessment","New assessment is retaining the previous assessment usage count","New Assessment is not retaining the previous assessment usage count");

            assessmentDetailsPage.tabNotRated.click();//Click on rating tab

            //The new assessment should retain the ratings of the previous assessment
            CustomAssert.assertEquals(assessmentDetailsPage.message_notRated.getText(),ratingBeforeEdit,"Verify the rating after editing the assessment","New assessment is retaining the previous assessment rating","New Assessment is not retaining the previous assessment rating");

        }catch (Exception e)
        {
            Assert.fail("Exception in testcase 'editAssessmentSharedAtDistrictLevelWithUsageCountMoreThan0' in class 'EditAssessment'", e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void editAssessmentSharedAtSchoolLevelWithUsageCountMoreThan0(){
        try{
            ReportUtil.log("Description", "This test case validates the details of edited assessment shared at school level like no. of questions, usage count etc when usage count is more than 0", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 6;
            String appendChar= "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);
            TrueFalseQuestionCreation questionCreate = PageFactory.initElements(driver,TrueFalseQuestionCreation.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            new Classes().createClass(appendChar, dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");//Create an assignment with true false question type
            new Assignment().addQuestion(dataIndex,"multiplechoice");//Add a question
            WebDriverUtil.waitTillInvisibilityOfElement(By.id("toast-container"),60);
            questionCreate.getButton_review().click();//Click on review assessment
            listPage.getButton_next().click();//Click on publish or assign button

            //Verify the total score
            CustomAssert.assertEquals(assign.totalPoints.getText(),"Total points: 2","Verify the total points","Total point is displayed as expected","Total point is not displayed as expected");

            new Assignment().assignToStudent(dataIndex,appendChar);

            new Navigator().navigateTo("myAssessments");//Navigate to my assessments
            WebDriverUtil.waitForAjax(driver,60);
            new Common().waitForUIBlockerToClose(120);
            myAssessments.published.click();//Click on published tab
            new Common().waitForUIBlockerToClose(120);
            myAssessments.assignmentList.get(0).click();//Click on assessment

            String usageCountBeforeEdit = assessmentDetailsPage.usageCountOfAssignment.getText();

            //Usage count should be zero
            CustomAssert.assertTrue(usageCountBeforeEdit.contains("1 time(s)"),"Verify the usage count","Usage count is displayed as expected","Usage count is not displayed as expected");

            assessmentDetailsPage.tabNotRated.click();//Click on rating tab
            String ratingBeforeEdit = assessmentDetailsPage.message_notRated.getText();

            //Verify rating
            CustomAssert.assertEquals(ratingBeforeEdit,"This assessment has not been rated.","Verify the rating","Rating is displayed as expected","Rating is not displayed as expected");

            assessmentDetailsPage.editAssessment.click();//Click on edit

            //A blink notification should be displayed to the user
            CustomAssert.assertTrue(assessmentDetailsPage.confirmation_popUp.get(0).getText().contains("Other users won’t be able to view this assessment until you publish it. Do you want to proceed?"),"Verify the confirmation pop up on after clicking on edit","Confirmation pop up is displayed as expected","Confirmation pop up is not displayed");

            assessmentDetailsPage.NoButton_confirmationPopUp.click();//Click on No button
            new Assignment().addQuestion(dataIndex,"multipleselection");//Add one more question in the assessment
            WebDriverUtil.waitTillInvisibilityOfElement(By.id("toast-container"),60);
            questionCreate.getButton_review().click();//Click on review assessment
            listPage.getButton_next().click();//click on assign page

            //Verify the total point should be increased after adding a neq question to assessment
            CustomAssert.assertEquals(assign.totalPoints.getText(),"Total points: 3","Verify the total points after adding a new question to assessment","Total point is increased as expected","Total point is not increased as expected");

            //Sharing level should be same as that assigned to parent assessment
            CustomAssert.assertEquals(assign.getRadioButton_school().getAttribute("class"),"iradio_square-green checked","Verify the sharing level","Sharing level is same as parent assessment","Sharing level is not same as parent assessment");

            assign.getButton_assign().click();//click on publish button

            WebDriverUtil.waitTillVisibilityOfElement(assign.button_viewAssignment,30);
            assign.button_viewAssignment.click();//Click on view assessment
            WebDriverUtil.waitForAjax(driver,60);
            new Common().waitForUIBlockerToClose(120);

            myAssessments.assignmentList.get(0).click();//Click on assessment

            //At assessment details page, added questions should be displayed
            CustomAssert.assertEquals(String.valueOf(assessmentDetailsPage.numberOfQuestion.size()),"3","Verify the total number of questions","Total number of questions are displayed as expected","Total number of questions are not correct");
            CustomAssert.assertTrue(assessmentDetailsPage.numberOfQuestion.get(2).getText().contains("Multi Selection " + questiontext), "Verify the newly added question", "Newly added question is displayed as expected", "Newly added question is not displayed");

            //The new assessment should retain usage count of the previous assessment
            CustomAssert.assertEquals(assessmentDetailsPage.usageCountOfAssignment.getText(),usageCountBeforeEdit,"Verify the usage count after editing the assessment","New assessment is retaining the previous assessment usage count","New Assessment is not retaining the previous assessment usage count");

            assessmentDetailsPage.tabNotRated.click();//Click on rating tab

            //The new assessment should retain the ratings of the previous assessment
            CustomAssert.assertEquals(assessmentDetailsPage.message_notRated.getText(),ratingBeforeEdit,"Verify the rating after editing the assessment","New assessment is retaining the previous assessment rating","New Assessment is not retaining the previous assessment rating");

        }catch (Exception e)
        {
            Assert.fail("Exception in testcase 'editAssessmentSharedAtSchoolLevelWithUsageCountMoreThan0' in class 'EditAssessment'", e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void editedAssessmentAtStudentSide(){
        try{
            ReportUtil.log("Description", "This test case validates the edited (add/delete/resequence) assessment at student side", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 7;
            String appendChar= "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            TrueFalseQuestionCreation questionCreate = PageFactory.initElements(driver,TrueFalseQuestionCreation.class);
            StudentDashboard dashboard = PageFactory.initElements(driver,StudentDashboard.class);
            TakeAssignment takeAssignment = PageFactory.initElements(driver,TakeAssignment.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode = new Classes().createClass(appendChar, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Assignment().create(dataIndex,"truefalse");//Create an assignment with true false question type
            new Assignment().addQuestion(dataIndex,"multiplechoice");//Add a question
            WebDriverUtil.waitTillInvisibilityOfElement(By.id("toast-container"),60);
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class

            new Assignment().addQuestion(dataIndex,"multipleselection");//Add a question
            new Common().waitForToastBlockerToClose(120);
            questionCreate.getButton_review().click();//Click on review button

            listPage.removeQuestion.get(1).click();//Remove multiple choice question
            new DragAndDrop().dragAndDrop(listPage.getList_dragQuestion().get(0),listPage.getList_dragQuestion().get(1));//Resequence the questions

            listPage.getButton_next().click();//click on publish or assign
            WebDriverUtil.waitForAjax(driver,60);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            dashboard.list_assignmentsName.get(0).click();//click on assignment

            //Changes made should NOT reflect to the students
            CustomAssert.assertEquals(takeAssignment.questiontext.getText(),"True False "+questiontext,"Verify the 1st question","Changes are not reflected for Q1","Changes are reflected for Q1");

            takeAssignment.button_next.click();//Click on next button
            WebDriverUtil.waitForAjax(driver,60);

            CustomAssert.assertEquals(takeAssignment.questiontext.getText(),"Multiple Choice "+questiontext,"Verify the 2nd question","Changes are not reflected for Q2","Changes are reflected for Q2");

            //Verify the total number of questions
            CustomAssert.assertEquals(String.valueOf(takeAssignment.question_number.size()),"2","Verify the total number of questions","total number of questions  are displayed as expected","Total number of questions are not correct");

            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("myAssessments");//Navigate to my assessments
            WebDriverUtil.waitForAjax(driver,60);
            new Common().waitForUIBlockerToClose(120);
            myAssessments.draft.click();//click on draft tab
            WebDriverUtil.waitForAjax(driver,60);
            new Common().waitForUIBlockerToClose(120);
            myAssessments.assignmentList.get(0).click();//click on assessment

            // New assessment should be found, at assessment details page, deleted questions should not be present and new sequence should be found, new questions should be found
            CustomAssert.assertEquals(String.valueOf(assessmentDetailsPage.numberOfQuestion.size()),"2","Verify the total number of questions","Total number of questions are displayed as expected","Total number of questions are not correct");
            CustomAssert.assertEquals(assessmentDetailsPage.questionName.get(0).getText(),"Q1:  Multi Selection "+questiontext,"Verify the Q1 after edit and assign","Edited question is displayed as Q1","Edited question is not displayed as Q1");
            CustomAssert.assertEquals(assessmentDetailsPage.questionName.get(1).getText(),"Q2:  True False "+questiontext,"Verify the Q2 after edit and assign","Edited question is displayed as Q2","Edited question is not displayed as Q2");

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assessment after editing
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar,dataIndex);//login as a student
            WebDriverUtil.waitForAjax(driver,60);
            dashboard.list_assignmentsName.get(0).click();//click on assignment

            // Now notice that the changes made, should reflect at the student side
            CustomAssert.assertEquals(takeAssignment.questiontext.getText(),"Multi Selection "+questiontext,"Verify the 1st question after edit the assessment","Changes are reflected for Q1","Changes are not reflected for Q1");

            takeAssignment.button_next.click();//Click on next button
            Thread.sleep(1000);

            CustomAssert.assertEquals(takeAssignment.questiontext.getText(),"True False "+questiontext,"Verify the 2nd question after edit the assessment","Changes are reflected for Q2","Changes are not reflected for Q2");

            //Verify the total number of questions
            CustomAssert.assertEquals(String.valueOf(takeAssignment.question_number.size()),"2","Verify the total number of questions","total number of questions  are displayed as expected","Total number of questions are not correct");

        }catch (Exception e)
        {
            Assert.fail("Exception in testcase 'editedAssessmentAtStudentSide' in class 'EditAssessment'", e);
        }
    }

    @Test(priority = 8,enabled = true)
        public void editedAssessmentAtInstructorSide(){
        try{
            ReportUtil.log("Description", "This test case validates the edited assessment accessed by other instructor using deeplink url", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 8;
            String appendChar1= "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2= "b"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            TakeAssignment takeAssignment = PageFactory.initElements(driver,TakeAssignment.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            InstructorDashboard instructorDashboard =PageFactory.initElements(driver,InstructorDashboard.class);
            AssessmentLibrary assessmentLibrary = PageFactory.initElements(driver,AssessmentLibrary.class);

            new SignUp().teacher(appendChar1,dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            new Classes().createClass(appendChar1, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().teacher(appendChar2,dataIndex);//Sign up as an instructor2
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode2 = new Classes().createClass(appendChar2, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1,classCode2,dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as an instructor1
            new Assignment().create(dataIndex,"truefalse");//Create an assignment with true false question type
            new Assignment().addQuestion(dataIndex,"multiplechoice");//Add a question
            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class at public level

            new Navigator().navigateTo("myAssessments");//Navigate to my assessments
            WebDriverUtil.waitForAjax(driver,60);
            new Common().waitForUIBlockerToClose(120);
            myAssessments.published.click();//Click on published tab
            new Common().waitForUIBlockerToClose(120);
            myAssessments.assignmentList.get(0).click();//Click on assessment

            String deepLinkUrl = assessmentDetailsPage.deepLinkUrl.get(0).getText();
            new Navigator().logout();//log out

            new Assignment().whiteListUSer(assessmentname,dataIndex,null);

            new Login().loginAsInstructor(appendChar2,dataIndex);//Login as an instructor2
            new Assignment().useExistingAssignment(dataIndex,appendChar2);//Share the same assignment created by instructor1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as instructor1
            new Assignment().addQuestion(dataIndex,"multipleselection");//Edit the assessment
            new Assignment().assignToStudent(9,appendChar1);//Assign assessment as private, we are passing data index as 9 so that, it will be assigned as private

            new Navigator().navigateTo("myAssessments");//Navigate to my assessments
            WebDriverUtil.waitForAjax(driver,60);
            new Common().waitForUIBlockerToClose(120);
            myAssessments.published.click();//Click on published tab
            new Common().waitForUIBlockerToClose(120);
            myAssessments.assignmentList.get(0).click();//Click on assessment

            // There should not be any uRL for private sharing level
            CustomAssert.assertTrue(assessmentDetailsPage.assessmentDetails.getText().contains("This assessment is not shared with anyone."),"Verify the message for private sharing level","Message is displayed as expected","Message is not displayed");

            if(assessmentDetailsPage.deepLinkUrl.size()>0)
            {
                CustomAssert.fail("Verify the deep link url for the assessment shared at private level","Deep link is displayed for the assessment shared at private level");
            }

            //Teacher1, should be taken to assessment details page with the most recent changes
            CustomAssert.assertEquals(String.valueOf(assessmentDetailsPage.numberOfQuestion.size()),"3","Verify the total number of questions","Total number of questions are displayed as expected","Total number of questions are not correct");
            CustomAssert.assertEquals(assessmentDetailsPage.questionName.get(0).getText(),"Q1:  True False "+questiontext,"Verify the Q2 after edit and assign","Edited question is displayed as Q2","Edited question is not displayed as Q2");
            CustomAssert.assertEquals(assessmentDetailsPage.questionName.get(1).getText(),"Q2:  Multiple Choice "+questiontext,"Verify the Q1 after edit and assign","Edited question is displayed as Q1","Edited question is not displayed as Q1");

            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.getAssessmentName().click();// Click on assessment

            WebDriverUtil.waitTillVisibilityOfElement(takeAssignment.questiontext,60);
            //The assignment at student account should continue to work fine
            CustomAssert.assertEquals(takeAssignment.questiontext.getText(),"True False "+questiontext,"Verify the 1st question","Q1 is displayed as expected","Q1 is not displayed as expected");

            takeAssignment.button_next.click();//Click on next button
            Thread.sleep(1000);

            CustomAssert.assertEquals(takeAssignment.questiontext.getText(),"Multiple Choice "+questiontext,"Verify the 2nd question","Q2 is displayed as expected","Q2 is not displayed as expected");

            //Verify the total number of questions
            CustomAssert.assertEquals(String.valueOf(takeAssignment.question_number.size()),"2","Verify the total number of questions","total number of questions  are displayed as expected","Total number of questions are not correct");
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            new Navigator().logout();//log out

            //The assignment at teacher2's account should continue to work fine
            new Login().loginAsInstructor(appendChar2,dataIndex);//Login as instructor2

            CustomAssert.assertTrue(instructorDashboard.getLink_activeAsssessment().getText().contains(assessmentname),"Verify the assignment at instructor2 side on dashboard","Assignment is displayed as expected","Assign,ent is not displayed");

            new Navigator().navigateTo("assignment");//Navigate to assignment page

            CustomAssert.assertEquals(assignments.getAssessmentName().getText(),assessmentname,"Verify the assignment at instructor2 side on assignment page","Assignment is displayed as expected","Assignment is not displayed on assignment page");

            new Navigator().navigateTo("assessmentLibrary");//Navigate to assessment library
            assessmentLibrary.textBox_search.clear();
            driver.switchTo().activeElement().sendKeys(assessmentname);//Enter assessment name
            new KeysSend().sendKeyBoardKeys("^");//Enter

            if(assessmentLibrary.getList_assessment().size()>0)
            {
                for(WebElement assessment:assessmentLibrary.getList_assessment())
                {
                    if(assessment.getText().equals(assessmentname))
                    {
                        CustomAssert.fail("Verify assessment in assessment library","Assessment is displayed");
                        break;
                    }
                }

            }

            driver.get(deepLinkUrl);//Open deep link url from the assessment assigned by ins1 at public level

            //Error message should be displayed
            CustomAssert.assertEquals(assessmentLibrary.errorMessage.getText(),"You are not authorized to view this assessment.","Verify error message","Error message is displayed as expected","Error message is not displayed");

        }catch (Exception e)
        {
            Assert.fail("Exception in testcase 'editedAssessmentAtInstructorSide' in class 'EditAssessment'", e);
        }
    }

    @Test(priority = 9,enabled = true)
    public void editedAssessmentAfterReassign(){
        try{
            ReportUtil.log("Description", "This test case validates the edited assessment accessed by other instructor using deeplink url", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 10;
            String appendChar1= "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2= "b"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
//            appendChar2="bEUP";

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            TakeAssignment takeAssignment = PageFactory.initElements(driver,TakeAssignment.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            InstructorDashboard instructorDashboard =PageFactory.initElements(driver,InstructorDashboard.class);
            StudentDashboard studentDashboard = PageFactory.initElements(driver,StudentDashboard.class);

            new SignUp().teacher(appendChar1,dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            new Classes().createClass(appendChar1, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().teacher(appendChar2,dataIndex);//Sign up as an instructor2
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode2 = new Classes().createClass(appendChar2, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1,classCode2,dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as an instructor1
            new Assignment().create(dataIndex,"truefalse");//Create an assignment with true false question type
            new Assignment().addQuestion(dataIndex,"multiplechoice");//Add a question
            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class at public level
            new Navigator().logout();//log out

            new Assignment().whiteListUSer(assessmentname,dataIndex,null);

            new Login().loginAsInstructor(appendChar2,dataIndex);//Login as an instructor2
            new Assignment().useExistingAssignment(dataIndex,appendChar2);//Share the same assignment created by instructor1

            new Navigator().navigateTo("dashboard");

            //Teacher2 should be able to see the Assignment
            CustomAssert.assertTrue(instructorDashboard.getLink_activeAsssessment().getText().contains(assessmentname),"Verify the assignment at instructor2 side on dashboard","Assignment is displayed as expected","Assign,ent is not displayed");

            new Navigator().navigateTo("assignment");//Navigate to assignment page

            CustomAssert.assertEquals(assignments.getAssessmentName().getText(),assessmentname,"Verify the assignment at instructor2 side on assignment page","Assignment is displayed as expected","Assignment is not displayed on assignment page");
            new Navigator().logout();//log out

            //Student should be able to see the Assignment
            new Login().loginAsStudent(appendChar1,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.getAssessmentName().click();// Click on assessment

            WebDriverUtil.waitTillVisibilityOfElement(takeAssignment.questiontext,60);
            //The assignment at student account should continue to work fine
            CustomAssert.assertEquals(takeAssignment.questiontext.getText(),"True False "+questiontext,"Verify the 1st question","Q1 is displayed as expected","Q1 is not displayed as expected");

            takeAssignment.button_next.click();//Click on next button
            Thread.sleep(1000);

            CustomAssert.assertEquals(takeAssignment.questiontext.getText(),"Multiple Choice "+questiontext,"Verify the 2nd question","Q2 is displayed as expected","Q2 is not displayed as expected");

            //Verify the total number of questions
            CustomAssert.assertEquals(String.valueOf(takeAssignment.question_number.size()),"2","Verify the total number of questions","total number of questions  are displayed as expected","Total number of questions are not correct");
            new Navigator().navigateTo("dashboard");//Navigate to dashboard

            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as instructor1
            new Assignment().addQuestion(dataIndex,"multipleselection");//Edit the assessment
            new Assignment().assignToStudent(dataIndex,appendChar1);
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar2,dataIndex);//Login as an instructor2
            new Assignment().useExistingAssignment(dataIndex,appendChar2);//ReAssign to class
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1,dataIndex);//Login as a student
            studentDashboard.list_assignmentsName.get(0).click();//Click on assignment

            //Student should now see the assessment with the changes made
            CustomAssert.assertEquals(takeAssignment.questiontext.getText(),"True False "+questiontext,"Verify the 2nd question after edit the assessment","Changes are reflected for Q1","Changes are not reflected for Q1");

            takeAssignment.button_next.click();//Click on next button
            Thread.sleep(1000);
            CustomAssert.assertEquals(takeAssignment.questiontext.getText(),"Multiple Choice "+questiontext,"Verify the 1st question after edit the assessment","Changes are reflected for Q2","Changes are not reflected for Q2");

            takeAssignment.button_next.click();//Click on next button
            Thread.sleep(1000);
            CustomAssert.assertEquals(takeAssignment.questiontext.getText(),"Multi Selection "+questiontext,"Verify the 1st question after edit the assessment","Changes are reflected for Q3","Changes are not reflected for Q3");

            //Verify the total number of questions
            CustomAssert.assertEquals(String.valueOf(takeAssignment.question_number.size()),"3","Verify the total number of questions","total number of questions  are displayed as expected","Total number of questions are not correct");

        }catch (Exception e)
        {
            Assert.fail("Exception in testcase 'editedAssessmentAfterReassign' in class 'EditAssessment'", e);
        }
    }

}
