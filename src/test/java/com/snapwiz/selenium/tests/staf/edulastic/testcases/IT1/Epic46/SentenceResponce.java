package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.Epic46;


import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Generic.QuestionCreationGeneric;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview.PreviewPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dharaneesha on 12/29/14.
 */
public class SentenceResponce extends Driver {
        String actual = null;
        String expected = null;
        boolean isDisplayed = false;


    @Test(priority = 1,enabled = true)
    public void checkDefaultLandingPageBehaviour(){
        try{

            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(59));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(59));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);




            Thread.sleep(2000);
            int index = 59;
            String appendChar = "AA";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            Thread.sleep(1000);
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question



            //Row No - 59 : Default landing page behaviour when instructor selects Sentence response question type
            /*Row No - 60 : Expected : "Default landing page must be displayed with the following details
            1. Question title must be displayed as ""Sentence response""
            2.  Text field to enter question body  with 'Enter question text' as default content
            3. 'Redactor'must be enabled for the question text field"*/
            Thread.sleep(2000);
            actual =  sentenceResponceQuestionCreation.getLabel_questionTitle().getText();
            expected  = "Sentence Response question";
            Assert.assertEquals(actual,expected,"Question Title is not displayed as 'Sentence Response question'");


            actual = sentenceResponceQuestionCreation.getTextField_EnterQuestionText().getText();
            expected = "Enter Question Text";
            Assert.assertEquals(actual, expected, "Text field to enter question body  with 'Enter question text' is not displayed");


            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().click();
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys("Question");
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().click();

            System.out.println("1 : " + sentenceResponceQuestionCreation.getEditor_redactorToolbar().isDisplayed());
            isDisplayed = sentenceResponceQuestionCreation.getEditor_redactorToolbar().isDisplayed();
            Assert.assertEquals(isDisplayed,true,"Redactor' is not enabled for the question text field");



            //expected Row No - 61 : 4. Instructor should be able to enter passage by clicking on the 'Enter Your Paragraph here' on Edit text tab
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys("First Line" + Keys.ENTER +"Second Line"+Keys.ENTER +"Third Line" +Keys.ENTER +"Fourth Line");
            actual = sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().getText();
            System.out.println("Actual Text : " + actual);
            Assert.assertEquals(actual,"First Line\n" +"Second Line\n" +"Third Line\n" +"Fourth Line","Instructor is not able to enter the passage on Edit Text box'");


            //Expected - Row No. 62 : 5. Instructor should be able to create answer choices by highlighting text from the Highlight text tab
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();
            Thread.sleep(3000);
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);//send keys in Edit text
            sentenceResponceQuestionCreation.getTab_highlightText().click();
            highlightText();
            Thread.sleep(2000);
            Actions action = new Actions(driver);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            action.moveToElement(we).moveToElement(driver.findElement(By.className("sentence-response-selectiontext"))).click().build().perform();
            Thread.sleep(5000);
            actual = sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().getText();
            expected = "Mark as correct answer";
            Assert.assertEquals(actual,expected,"Mark as correct answer option is not appearing");
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct answer' option

            action = new Actions(driver);
            we = driver.findElement(By.className("sentence-response-selectiontext"));
            action.moveToElement(we).moveToElement(driver.findElement(By.className("sentence-response-selectiontext"))).click().build().perform();
            Thread.sleep(5000);
            actual = sentenceResponceQuestionCreation.getInputLink_removeCorrectAnswer().getText();
            expected = "Remove correct answer";
            Assert.assertEquals(actual,expected,"Remove correct answer option is not appearing");

            Thread.sleep(10000);
            sentenceResponceQuestionCreation.getLink_clearAllAnswerChoices().click();
            Thread.sleep(2000);
            sentenceResponceQuestionCreation.getButton_Yes().click();//Click on 'Yes, Clear all' button
            Thread.sleep(2000);


            //Row No - Managing passage
            /*Expected : "1.Instructor should be able to enter passage by clicking on the 'Enter passage text' on Edit text tab
            2. 'Redactor' must be enabled fro this field as well"*/
            //I believe it is duplicated hence it is skipped for the time being

        }catch(Exception e){
         Assert.fail("Exception in testcase 'checkDefaultLandingPageBehaviour' in the class 'SentenceResponce'",e);
        }
    }




    @Test(priority = 2,enabled = true)
    public void validateErrorMessages() {
        try {
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(59));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(59));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);

            int index = 59;
            String appendChar = "E";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            Thread.sleep(1000);
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question

            //Expected - 1 : 'You have unsaved changes.' Notification should be displayed before Save button is clicked when only the passage text is typed
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys("First Line" + Keys.ENTER +"Second Line"+Keys.ENTER +"Third Line" +Keys.ENTER +"Fourth Line" + Keys.ENTER );
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys("Fifth Line" + Keys.ENTER);
            Thread.sleep(5000);
            actual = sentenceResponceQuestionCreation.getNotificationText().getText();
            expected = "You have unsaved changes.";
            Assert.assertEquals(actual, expected, "'You have unsaved changes' Notification Text is not displayed after click on save button in the question's Page");



            //Expected - 2 : 'Please rename your assessment' Notification should be displayed after Save button is clicked when only the passage text is typed
            questionCreationGeneric.getButton_Save().click();
            Thread.sleep(1000);
            actual = sentenceResponceQuestionCreation.getErrMsg_PleaseRenameUrAssessment().getText();
            expected  = "Please rename your assessment";
            Assert.assertEquals(actual, expected, "'Please rename your assessment' Notification Text is not displayed after click on save button in the question's Page");


            //Expected - 3 : print "You are about to edit some text which is already highlighted...."
            sentenceResponceQuestionCreation.getTab_highlightText().click();//Click on Tab 'Highlight Text'
            highlightText();
            Thread.sleep(1000);
            sentenceResponceQuestionCreation.getTab_editText().click();//Click on Tab 'Edit Text'
            actual = sentenceResponceQuestionCreation.getEdit_notificationtext().getText().trim();
            expected = "You are about to edit some text which is already highlighted. Additions/deletions to the text will delete all the highlighted text. Are you sure you want to continue?";
            if(!(actual.contains(expected))){
                Assert.fail("You are about to edit some text which .....' Notification Text is not displayed after click on 'Edit Text' button in the question's Page");
            }


            //Expected - 4 : Print "You are about to edit some text which is already highlighted. A..."
            sentenceResponceQuestionCreation.getButton_Yes().click();
            actual = sentenceResponceQuestionCreation.getNotificationText().getText();
            /*expected = "You are about to edit some text which is already highlighted. Additions/deletions to the text will automatically be applied to the highlighted text. Are you sure you want to continue? ";
            Assert.assertNotEquals(actual, expected, "'You are about to edit some text which .....' Notification Text is displayed after click on 'Yes' button in the question's Page");*/
            expected = "You have unsaved changes";
            Assert.assertEquals(actual, expected, "'You have unsaved changes' Notification Text is not displayed after click on 'Yes' button in the question's Page");



            //Expected - 5 : Print "Question title should not be empty"
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);
            questionCreationGeneric.getButton_Save().click();
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("footer-notification-text")));
            Thread.sleep(2000);
            actual = sentenceResponceQuestionCreation.getNotificationText().getText();
            expected = "Question title should not be empty";
            Assert.assertEquals(actual, expected, "'Question title should not be empty' Notification Text is not displayed after click on 'Yes' button in the question's Page");


            //Expected - 6 : Print "Select correct answer choice"
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);
            questionCreationGeneric.getButton_Save().click();
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("footer-notification-text")));
            actual = sentenceResponceQuestionCreation.getNotificationText().getText().trim();
            expected = "Select atleast an answer choice";
            Assert.assertEquals(actual, expected, "'Select correct answer choice' Notification Text is not displayed after click on 'Yes' button in the question's Page");


        } catch (Exception e) {
              Assert.fail("Exception in the testcase 'validateErrorMessages' in the class 'SentenceResponce'");
            }
    }


    @Test(priority = 3,enabled = true)
    public void createAnswerChoicesBySelectingOneOrMoreWords() {
        try {
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(59));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(59));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);

            //Row No - 65 : Instructor should be able to create answer choices by Selecting one or more words from the sentence
            /*Expected - "On clicking Highlight text tab,
            1. Instructor should be able to selct one or more words from the passage as answer choices
            2. This should automatically highligt the text"*/

            int index = 59;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            Thread.sleep(1000);
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            closeHelp();
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();
            Thread.sleep(3000);
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);
            sentenceResponceQuestionCreation.getTab_highlightText().click();
            Thread.sleep(1000);
            highlightText();
            Thread.sleep(5000);
            Actions action = new Actions(driver);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            action.moveToElement(we).moveToElement(driver.findElement(By.className("sentence-response-selectiontext"))).click().build().perform();
            Thread.sleep(5000);
            actual = sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().getText();
            expected = "Mark as correct answer";
            Assert.assertEquals(actual,expected,"Mark as correct answer option is not appearing");
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct answer' option
            Thread.sleep(5000);
            WebElement element = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.id("sentence-selection-raw-content-div-heighlight")));
            Actions actions = new Actions(driver);
            actions.moveToElement(element, 100, 0)
                    .clickAndHold()
                    .moveByOffset(200, 0)
                    .release()
                    .perform();
            Thread.sleep(2000);
            action = new Actions(driver);
            we = driver.findElement(By.className("sentence-response-selectiontext"));
            Thread.sleep(2000);
            action.moveToElement(we).moveToElement(driver.findElements(By.className("sentence-response-selectiontext")).get(1)).click().build().perform();
            Thread.sleep(6000);
            actual = sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().getText();
            expected = "Mark as correct answer";
            Assert.assertEquals(actual,expected,"Mark as correct answer option is not appearing");
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct answer' option
            Thread.sleep(5000);
            if(!(sentenceResponceQuestionCreation.getText_selection().getAttribute("style").contains("background: none repeat scroll 0% 0% rgb(210, 231, 205);"))){
                Assert.fail("After Click on 'Mark as Correct answer', the colour of text is not highlighted with different color");
            }




            //Row No - 66 : Instructor should be able to Make an answer choice Correct
            /*Expected - "On mouse hover on selected text instructor should be prompted with the following options
            1. Remove answer choice
            2. Mark as correct answer "*/
            Thread.sleep(10000);
            sentenceResponceQuestionCreation.getLink_clearAllAnswerChoices().click();
            Thread.sleep(1000);
            sentenceResponceQuestionCreation.getButton_Yes().click();//Click on 'Yes, Clear all' button
            Thread.sleep(2000);
            highlightText(0,0,50,0);
            Thread.sleep(3000);
            action = new Actions(driver);
            we = driver.findElement(By.className("sentence-response-selectiontext"));
            action.moveToElement(we).moveToElement(driver.findElement(By.className("sentence-response-selectiontext"))).click().build().perform();
            Thread.sleep(5000);
            actual = sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().getText();
            expected = "Mark as correct answer";
            Assert.assertEquals(actual,expected,"Mark as correct answer option is not appearing");

            actual = sentenceResponceQuestionCreation.getInputLink_removeAnswerChoice().getText();
            expected = "Remove answer choice";
            Assert.assertEquals(actual,expected,"Remove answer choice option is not appearing");


            //Row No - 67 : On clicking Mark as correct answer
            //Expected - The choice should be marked as correct answer
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on 'Mark as correct answer' link

        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'createAnswerChoicesBySelectingOneOrMoreWords' in the class 'SentenceResponce'",e);
        }
    }



    @Test(priority = 4,enabled = true)
    public void CheckIfMultipleCorrectAnswerIsEnabled() {
        try {

            //Row No - 68 : If “Multiple Correct Answer” is enabled
            //Expected - Instructor should be able to mark more than one correct answer

            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(59));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(59));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);


            int index = 59;
            String appendChar = "B";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            Thread.sleep(1000);
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);
            Thread.sleep(2000);
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);
            Thread.sleep(3000);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();
            Thread.sleep(3000);
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);
            sentenceResponceQuestionCreation.getTab_highlightText().click();
            sentenceResponceQuestionCreation.getCheckBox_AllowMultipleCorrectAnswer().click();//Click on 'Allow multiple correct answer'
            highlightText();
            Thread.sleep(5000);
            Actions action = new Actions(driver);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            action.moveToElement(we).moveToElement(driver.findElement(By.className("sentence-response-selectiontext"))).click().build().perform();
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct answer' option
            Thread.sleep(2000);


            Thread.sleep(10000);
            WebElement element = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.id("sentence-selection-raw-content-div-heighlight")));
            Thread.sleep(1000);

            Actions actions = new Actions(driver);
            Thread.sleep(1000);
            actions.moveToElement(element, 0, 50)
                    .clickAndHold()
                    .moveByOffset(50, 0)
                    .release()
                    .perform();
            Thread.sleep(2000);
            action = new Actions(driver);
            we = driver.findElement(By.className("sentence-response-selectiontext"));
            action.moveToElement(we).moveToElement(driver.findElements(By.className("sentence-response-selectiontext")).get(1)).click().build().perform();
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct answer' option
            Thread.sleep(2000);


            //Row No - 69 : An answer choice marked as correct should appear in green
            //Expected - When instructor marks the highlighted text as correct answer choice, It must appear with Green background
            if(!(driver.findElements(By.cssSelector("span[class='sentence-response-selectiontext selected-text-correct-answer']")).get(0).getAttribute("style").equals("background: none repeat scroll 0% 0% rgb(210, 231, 205);"))){
              Assert.fail("Instructor is not able to mark the first correct answer");
            }

            if(!(driver.findElements(By.cssSelector("span[class='sentence-response-selectiontext selected-text-correct-answer']")).get(0).getAttribute("style").equals("background: none repeat scroll 0% 0% rgb(210, 231, 205);"))){
                Assert.fail("Instructor is not able to mark more than one correct answer (the second one)");
            }



            //Row No - 70 : Instructor clicks on "Clear all Answer Choices"
            //Expected - All the selected answer choices must be removed
            sentenceResponceQuestionCreation.getLink_clearAllAnswerChoices().click();// Click on 'Clear all Answer Choices'
            Thread.sleep(1000);
            sentenceResponceQuestionCreation.getButton_Yes().click();//Click on 'Yes, Clear all' button
            boolean boo = new BooleanValue().presenceOfElement(70,By.className("sentence-response-selectiontext selected-text-correct-answer"));
            if(boo == true){
                Assert.fail("All the selected answer choices is not removed after click on 'Clear all Answer Choices'");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'CheckIfMultipleCorrectAnswerIsEnabled' in the class 'SentenceResponce'",e);
        }
    }





    @Test(priority = 5,enabled = true)
    public void optionsAllowed() {
        try {

            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(59));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(59));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);

            //Row No - 71: Options allowed
            //Expected - Only " Allow students to use scratchpad"  should be displayed, it should be disabled by default
            int index = 59;
            String appendChar = "S";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            closeHelp();


            boolean isDisplayed =    sentenceResponceQuestionCreation.getCheckBox_allowStudentToUseScratchPad().isDisplayed();
            if(isDisplayed ==false){
                Assert.fail("Allow students to use scratchpad' checkbox should be disabled by default");
            }

            actual = sentenceResponceQuestionCreation.getLabel_allowStudentToUseScratchPad().getText();
            expected = "Allow student to use Scratchpad";
            Assert.assertEquals(actual, expected, "'Allow students to use scratchpad' is not displayed");

            boolean isEnabled =    sentenceResponceQuestionCreation.getCheckBox_allowStudentToUseScratchPad().isSelected();
            if(isEnabled ==true){
                Assert.fail("Allow students to use scratchpad' checkbox should be disabled by default");
            }


            //Row No - 72 : Parameters
            /*Expected - "The following parameters must be ensured
            1. One question should be worth one point
            2. Questions should have solution and hint as other question types
            3. Questions should be allowed to be  tagged with difficulty level
            4. Questions can be tagged with LOs as other question types
            5. The error message ( if any ) should be displayed."*/


            //Expected -1 : One question should be worth one point
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);
            sentenceResponceQuestionCreation.getTab_highlightText().click();// Click on tab 'Highlight Text'
            highlightText();
            Thread.sleep(2000);
            Actions action = new Actions(driver);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            action.moveToElement(we).moveToElement(driver.findElement(By.className("sentence-response-selectiontext"))).click().build().perform();
            Thread.sleep(1000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct answer' option
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName); //Type 'Assessment Name'
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText); //Type 'Question Text'
            questionCreationGeneric.getButton_Save().click();//Click on 'Save' button to save the question
            sentenceResponceQuestionCreation.getButton_Preview().click(); //Click on 'Preview' button to view the question
            Thread.sleep(1000);
            List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(browserTabs .get(1));
            questionCreationGeneric.getSubmitButton().click(); //Click on 'Submit' button to submit the question
            actual = previewPage.getQuestionPoint_previewPage().getAttribute("value");
            expected  = "1";
            Assert.assertEquals(actual, expected, "Points Value is not 1 in the Preview Page (One question should be worth one point)");



            //Expected - 2 :  Questions should have solution and hint as other question types
            sentenceResponceQuestionCreation.getButton_leftArrow().click();//Click on Left arrow back button
            Thread.sleep(1000);
            driver.switchTo().window(browserTabs .get(0));
            actual = sentenceResponceQuestionCreation.getLabel_Solution().getText(); // 'Solution' text
            expected  = "Solution";
            Assert.assertEquals(actual, expected, "'Solution' label is not displayed in the question's Page");

            actual = sentenceResponceQuestionCreation.getLabel_Hint().getText();// 'Hint' Text
            expected  = "Hint";
            Assert.assertEquals(actual, expected, "'Hint' label is not displayed in the question's Page");

            actual = sentenceResponceQuestionCreation.getTextField_EnterSolutionText().getText(); // 'Enter Solution Text...' text
            expected  = "Enter Solution Text...";
            Assert.assertEquals(actual, expected, "'Enter Solution Text...' textfield is not displayed in the question's Page");

            actual = sentenceResponceQuestionCreation.getTextField_EnterHintText().getText();// 'Enter Hint Text...' Text
            expected  = "Enter Hint Text...";
            Assert.assertEquals(actual, expected, "'Enter Hint Text...' textfield is not displayed in the question's Page");


            //Expected - 3 : Questions should be allowed to be  tagged with difficulty level
            actual = new Select(sentenceResponceQuestionCreation.getDropDown_DifficultyLevel()).getFirstSelectedOption().getText();
            expected = "Difficulty Level";
            Assert.assertEquals(actual, expected, "'No default selection 'Difficulty Level'");
            new Select(sentenceResponceQuestionCreation.getDropDown_DifficultyLevel()).selectByVisibleText("Easy");
            new Select(sentenceResponceQuestionCreation.getDropDown_DifficultyLevel()).selectByVisibleText("Medium");
            new Select(sentenceResponceQuestionCreation.getDropDown_DifficultyLevel()).selectByVisibleText("Hard");
            new Select(sentenceResponceQuestionCreation.getDropDown_DifficultyLevel()).selectByVisibleText("Difficulty Level");



            //Expected - 4 : Questions can be tagged with LOs as other question types
            sentenceResponceQuestionCreation.getDropDown_LearningObjectives().click();
            String learningObjectivesContent = sentenceResponceQuestionCreation.getLabel_LearningObjectivesContent().getText();
            if(!(learningObjectivesContent.contains("1.OA Operations & Algebraic Thinking\n" +"1.OA.A.1 Use addition and subtraction within 20 to solve word problems involving situations of adding to, ta"))){
                Assert.fail("TLO is not displayed as per the expected format in Learning objectives dropdown");
            }



            //Expected - The error message ( if any ) should be displayed.
            questionCreationGeneric.getButton_Save().click();
            actual = sentenceResponceQuestionCreation.getNotificationText().getText();
            expected = "You have unsaved changes.";
            Assert.assertNotEquals(actual, expected, "'You have unsaved changes' Notification Text is not displayed after click on save button in the question's Page");

        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'optionsAllowed' in the class 'SentenceResponce'",e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void saveQuestionsByClickingOnSaveButton() {
        try {

            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(59));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(59));


            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);


            int index = 59;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            Thread.sleep(1000);
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);
            Thread.sleep(2000);
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);
            Thread.sleep(3000);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();
            Thread.sleep(3000);
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);
            sentenceResponceQuestionCreation.getTab_highlightText().click();
            highlightText();
            Thread.sleep(5000);
            Actions action = new Actions(driver);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            action.moveToElement(we).moveToElement(driver.findElement(By.className("sentence-response-selectiontext"))).click().build().perform();
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct answer' option
            Thread.sleep(2000);
            questionCreationGeneric.getButton_Save().click();
            Thread.sleep(2000);
            actual = sentenceResponceQuestionCreation.getNotificationText().getText();
            expected = "Saved.";
            Assert.assertEquals(actual,expected,"A notification 'Saved' is not displayed after clicking on Save button");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'saveQuestionsByClickingOnSaveButton' in the class 'SentenceResponce'", e);
        }
    }


    @Test(priority = 7,enabled = true)
    public void instructorNotToBeAbleToSaveQuestionWithoutPassageText(){
        try{

            //Row No - 74 : Instructor must not be able to save the question without passage text
            /*"Expected : 1. If passage text is not entered, the question must not be saved
            2. An error message saying ““Please enter passage text before saving the question.” must be displayed"*/
            //Discuss it

            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(59));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(59));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);


            int index = 59;
            String appendChar = "B";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            Thread.sleep(1000);
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);
            questionCreationGeneric.getButton_Save().click();//Click on 'Save' button
            Thread.sleep(1000);
            actual = sentenceResponceQuestionCreation.getNotificationText().getText();
            expected = "Select atleast an answer choice";
            Assert.assertEquals(actual,expected,"A notification 'Select atleast an answer choice' is not displayed after clicking on Save button");

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'instructorNotToBeAbleToSaveQuestionWithoutPassageText' in the class 'SentenceResponce'");
        }
    }


    @Test(priority = 8,enabled = true)
    public void saveQuestionWithoutHighlightingAnswerChoices(){
        try{

            //Row No - 75 : Saving the question without highlighting answer choices
            /*Expected - "1. Instructor must not be able to save the question if Answer choices are not highlighted
            2. An error message saying “Please enter at least one correct answer” must be displayed"*/
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(59));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(59));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);


            int index = 59;
            String appendChar = "B";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();
            Thread.sleep(1000);
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);
            questionCreationGeneric.getButton_Save().click();
            Thread.sleep(2000);
            actual = sentenceResponceQuestionCreation.getNotificationText().getText();
            expected = "Select atleast an answer choice";
            Assert.assertEquals(actual,expected,"A notification 'Select atleast an answer choice' is not displayed after clicking on Save button");

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'saveQuestionWithoutHighlightingAnswerChoices' in the class 'SentenceResponce'");
        }
    }



    @Test(priority = 9,enabled = true)
    public void CheckOptionsAfterHighlightingToBeRenderedCorrectly() {
        try {

            /*Row No - 76 : "1.Launch the application and create sentence response type question
            2. Enter text to create a passage
            3.Check the checkbox, allow multiple correct answer
            4.Go to highlight text
            5.Highlight more than one text
            6.Hover the mouse on one of the optin and select ""Remove answer choice""
            7.Go to the immediate next highlighted text
            8. Repeat steps 5-7 multiple times."*/

            //Expected  -  1.If the text is highlighted(only) options should be "Remove answer choice" and "Mark as correct answer"

            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(59));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(59));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);


            int index = 59;
            String appendChar = "E";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            Thread.sleep(1000);
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();//Clear the assessment text field
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);// Enter the assessment name
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);//Enter the question name
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();//Click on Question text passage area
            Thread.sleep(1000);
            sentenceResponceQuestionCreation.getCheckBox_AllowMultipleCorrectAnswer().click();//Click on 'Allow multiple correct answer'
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);// Type the question text in passage
            sentenceResponceQuestionCreation.getTab_highlightText().click();// Click on highlight text tab


            //Do highlight for 1st line
            highlightText(0,0,50,0);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            /*mouseHoverOnHighlightedText(we,we);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_removeAnswerChoice().click();//Click on link 'Remove Answer Choice' option
            Thread.sleep(2000);
*/

            //Do highlight for 2nd line
            highlightText(0,50,50,0);
            WebElement we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            WebElement we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(0);
           /* mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_removeAnswerChoice().click();//Click on link 'Remove Answer Choice' option
            Thread.sleep(2000);*/


            //Do highlight for 3rd line
            highlightText(0,100,50,0);
            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(0);
            /*mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_removeAnswerChoice().click();//Click on link 'Remove Answer Choice' option
            Thread.sleep(2000);*/




            //Do highlight for 4th line
            highlightText(0,150,50,0);
            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            //we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(0);
            /*mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_removeAnswerChoice().click();//Click on link 'Remove Answer Choice' option
            Thread.sleep(2000);*/



            //Mouse hover on 1st highlighted text
            mouseHoverOnHighlightedText(we,we);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_removeAnswerChoice().click();//Click on link 'Remove Answer Choice' option
            Thread.sleep(2000);


            //Mouse hover on 2nd highlighted text
            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(0);

            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            actual = sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().getText();
            expected = "Mark as correct answer";
            Assert.assertEquals(actual,expected,"'Mark as correct answer' option is not appearing");




            actual = sentenceResponceQuestionCreation.getInputLink_removeAnswerChoice().getText();
            expected = "Remove answer choice";
            Assert.assertEquals(actual,expected,"'Remove answer choice' option is not appearing");

            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct answer' option
            if(!(sentenceResponceQuestionCreation.getText_selection().getAttribute("style").contains("background: none repeat scroll 0% 0% rgb(210, 231, 205);"))){
                Assert.fail("After Click on 'Mark as Correct answer', the colour of text is not highlighted with different color");
            }


            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(0);
            mouseHoverOnHighlightedText(we1,we2);//Mouse over pon 2nd highlighted text
            sentenceResponceQuestionCreation.getInputLink_removeAnswerChoice().click();//Click on link 'Remove Answer Choice' option
            Thread.sleep(2000);
            boolean boo = new BooleanValue().presenceOfElement(70,By.className("sentence-response-selectiontext selected-text-correct-answer"));
            if(boo == true){
                Assert.fail("All the selected answer choices is not removed after click on 'Clear all Answer Choices'");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'CheckIfMultipleCorrectAnswerIsEnabled' in the class 'SentenceResponce'",e);
        }
    }


    @Test(priority = 10,enabled = true)
    public void checkValidationInPreviewPage() {
        try {

            /*Row No - 77 : "1.Launch the application and create sentence response type question
            2. Enter text to create a passage
            3.Check the checkbox, allow multiple correct answer
            4.Go to highlight text
            5. Highlight multiple text, and have multiple correct/single answer
            6.Remove the highlighted correct answer by ""remove answer choice""
            7.Select a different answer, to be correct
            8.Save and preview"*/

            //Expected - Validation should always be rendered correctly

            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(59));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(59));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);


            int index = 59;
            String appendChar = "J";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            Thread.sleep(1000);
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            closeHelp();
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();//Clear the assessment text field
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);// Enter the assessment name
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);//Enter the question name
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();//Click on Question text passage area
            Thread.sleep(1000);
            sentenceResponceQuestionCreation.getCheckBox_AllowMultipleCorrectAnswer().click();//Click on 'Allow multiple correct answer'
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);// Type the question text in passage
            sentenceResponceQuestionCreation.getTab_highlightText().click();// Click on highlight text tab




            highlightText(0,0,50,0);
            /*WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            mouseHoverOnHighlightedText(we,we);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_removeAnswerChoice().click();//Click on link 'Remove Answer Choice' option
            Thread.sleep(2000);*/


            //Do highlight for 2nd line
            highlightText(0,50,50,0);
            /*WebElement we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            WebElement we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(0);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_removeAnswerChoice().click();//Click on link 'Remove Answer Choice' option
            Thread.sleep(2000);*/


            //Do highlight for 3rd line
            highlightText(0,100,50,0);
           /* we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(0);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_removeAnswerChoice().click();//Click on link 'Remove Answer Choice' option
            Thread.sleep(2000);
*/



            //Do highlight for 4th line
            highlightText(0,130,50,0);
            /*we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(0);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_removeAnswerChoice().click();//Click on link 'Remove Answer Choice' option
            Thread.sleep(2000);*/


            //Select a different answer to be correct
            //highlightText(0,150,50,0);
            WebElement we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            WebElement we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(0);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as Correct Answer' option
            Thread.sleep(2000);




            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(2);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as Correct Answer' option
            Thread.sleep(2000);




            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(0);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_removeCorrectAnswer().click();//Click on link 'Remove Correct Answer' option
            Thread.sleep(2000);





            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(3);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as Correct Answer' option
            Thread.sleep(2000);




            questionCreationGeneric.getButton_Save().click();//Click on 'Save' button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));
            sentenceResponceQuestionCreation.getButton_Preview().click(); //Click on 'Preview' button to view the question
            Thread.sleep(1000);
            List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(browserTabs .get(1));
            Thread.sleep(1000);
            previewPage.getText_selection().click();
            Thread.sleep(1000);
            previewPage.getText_selection().click();
            Thread.sleep(1000);

            questionCreationGeneric.getSubmitButton().click(); //Click on 'Submit' button to submit the question

            actual = previewPage.getNotificationText().getText();
            expected = "You got it right.";
            Assert.assertEquals(actual,expected,"Message notification 'You got it right.' is not displayed");




            actual = previewPage.getQuestionPoint_previewPage().getAttribute("value");
            expected  = "1";
            Assert.assertEquals(actual, expected, "Points Value is not 1 in the Preview Page (One question should be worth one point)");



            //Expected - 2 :  Questions should have solution and hint as other question types
            sentenceResponceQuestionCreation.getButton_leftArrow().click();//Click on Left arrow back button
            Thread.sleep(1000);
            driver.switchTo().window(browserTabs .get(0));
            actual = sentenceResponceQuestionCreation.getLabel_Solution().getText(); // 'Solution' text
            expected  = "Solution";
            Assert.assertEquals(actual, expected, "'Solution' label is not displayed in the question's Page");

            actual = sentenceResponceQuestionCreation.getLabel_Hint().getText();// 'Hint' Text
            expected  = "Hint";
            Assert.assertEquals(actual, expected, "'Hint' label is not displayed in the question's Page");

            actual = sentenceResponceQuestionCreation.getTextField_EnterSolutionText().getText(); // 'Enter Solution Text...' text
            expected  = "Enter Solution Text...";
            Assert.assertEquals(actual, expected, "'Enter Solution Text...' textfield is not displayed in the question's Page");

            actual = sentenceResponceQuestionCreation.getTextField_EnterHintText().getText();// 'Enter Hint Text...' Text
            expected  = "Enter Hint Text...";
            Assert.assertEquals(actual, expected, "'Enter Hint Text...' textfield is not displayed in the question's Page");


            //Expected - 3 : Questions should be allowed to be  tagged with difficulty level
            actual = new Select(sentenceResponceQuestionCreation.getDropDown_DifficultyLevel()).getFirstSelectedOption().getText();
            expected = "Difficulty Level";
            Assert.assertEquals(actual, expected, "'No default selection 'Difficulty Level'");
            new Select(sentenceResponceQuestionCreation.getDropDown_DifficultyLevel()).selectByVisibleText("Easy");
            new Select(sentenceResponceQuestionCreation.getDropDown_DifficultyLevel()).selectByVisibleText("Medium");
            new Select(sentenceResponceQuestionCreation.getDropDown_DifficultyLevel()).selectByVisibleText("Hard");
            new Select(sentenceResponceQuestionCreation.getDropDown_DifficultyLevel()).selectByVisibleText("Difficulty Level");



            //Expected - 4 : Questions can be tagged with LOs as other question types
            sentenceResponceQuestionCreation.getDropDown_LearningObjectives().click();
            String learningObjectivesContent = sentenceResponceQuestionCreation.getLabel_LearningObjectivesContent().getText();
            if(!(learningObjectivesContent.contains("1.OA Operations & Algebraic Thinking\n" +"1.OA.A.1 Use addition and subtraction within 20 to solve word problems involving situations of adding to, ta"))){
                Assert.fail("TLO is not displayed as per the expected format in Learning objectives dropdown");
            }



            //Expected - The error message ( if any ) should be displayed.
            questionCreationGeneric.getButton_Save().click();
            actual = sentenceResponceQuestionCreation.getNotificationText().getText();
            expected = "You have unsaved changes.";
            Assert.assertNotEquals(actual, expected, "'You have unsaved changes' Notification Text is not displayed after click on save button in the question's Page");


        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'checkValidationInPreviewPage' in the class 'SentenceResponce'",e);
        }
    }


    @Test(priority = 11,enabled = true)
    public void instructorToBeAbleToEditQuestionByEditLink() {//It has 2 bugs! Once those are fixed , the script should work fine! As of now the script is getting failed
        try {


            //Row No - 78  : Instructor should be able edit the question by clicking on the edit link
            /*Expected : "1. The question with values should be populated. Instructor can edit the question body, answer choices.
            2. Clicking save must save all the changes and the changes must be reflected"*/


            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(59));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(59));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);


            int index = 59;
            String appendChar = "I";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            Thread.sleep(1000);
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            Thread.sleep(2000);
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();//Clear the assessment text field
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);// Enter the assessment name
            Thread.sleep(2000);
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);//Enter the question name
            Thread.sleep(2000);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();//Click on Question text passage area
            Thread.sleep(1000);
            sentenceResponceQuestionCreation.getCheckBox_AllowMultipleCorrectAnswer().click();//Click on 'Allow multiple correct answer'
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);// Type the question text in passage
            sentenceResponceQuestionCreation.getTab_highlightText().click();// Click on highlight text tab


            highlightText();
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            mouseHoverOnHighlightedText(we,we);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct Answer Choice' option
            Thread.sleep(2000);



            //Do highlight for 3rd line
            highlightText(0,100,50,0);
            WebElement we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            WebElement we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct Answer Choice' option
            Thread.sleep(2000);




            questionCreationGeneric.getButton_Save().click();//Click on 'Save' button
            Thread.sleep(5000);

            /*WebDriverWait wait = new WebDriverWait(driver, 120);
            wait.until(ExpectedConditions.elementToBeClickable(sentenceResponceQuestionCreation.getButton_review()));*/
           //div[class='lsm-createAssignment-done selected']
            sentenceResponceQuestionCreation.getButton_review().click(); //Click on 'Review' button to view the question
            Thread.sleep(1000);
            questionsListPage.getArrowLink().click();
            List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(browserTabs .get(0));
            Thread.sleep(1000);
            assessmentReview.getButton_Edit().click();
            Thread.sleep(2000);
            actual =  sentenceResponceQuestionCreation.getLabel_questionTitle().getText();
            expected  = "Sentence Response question";
            Assert.assertEquals(actual,expected,"Question Title is not displayed as 'Sentence Response question'");

            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().clear();
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText + questionText);
            actual =  sentenceResponceQuestionCreation.getTextField_EnterQuestionText().getText();
            expected = questionText+questionText;
            Assert.assertEquals(actual, expected, "Text field to enter question body  is not editable");
            sentenceResponceQuestionCreation.getTab_editText().click();//Click on edit text tab
            sentenceResponceQuestionCreation.getButton_Yes().click();//Click on 'Yes, Clear all' button


            //expected Row No - 61 : 4. Instructor should be able to enter passage by clicking on the 'Enter Your Paragraph here' on Edit text tab
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().clear();
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys("First Line" + Keys.ENTER +"Second Line"+Keys.ENTER +"Third Line");
            actual = sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().getText();
            System.out.println("Actual Text : " + actual);
            Assert.assertEquals(actual,"First Line\n" +"Second Line\n" +"Third Line","Instructor is not able to enter the passage on Edit Text box'");



            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName+assessmentName);





            //Expected - Row No. 62 : 5. Instructor should be able to create answer choices by highlighting text from the Highlight text tab
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().clear();
            Thread.sleep(3000);
            str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);//send keys in Edit text
            sentenceResponceQuestionCreation.getTab_highlightText().click();
            highlightText();
            Thread.sleep(2000);
            Actions action = new Actions(driver);
            we = driver.findElement(By.className("sentence-response-selectiontext"));
            action.moveToElement(we).moveToElement(driver.findElement(By.className("sentence-response-selectiontext"))).click().build().perform();
            Thread.sleep(5000);
            actual = sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().getText();
            expected = "Mark as correct answer";
            Assert.assertEquals(actual,expected,"Mark as correct answer option is not appearing");
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct answer' option

            //Remove the above comments later, important


            action = new Actions(driver);
            we = driver.findElement(By.className("sentence-response-selectiontext"));
            action.moveToElement(we).moveToElement(driver.findElement(By.className("sentence-response-selectiontext"))).click().build().perform();
            Thread.sleep(5000);
            actual = sentenceResponceQuestionCreation.getInputLink_removeCorrectAnswer().getText();
            expected = "Remove correct answer";
            Assert.assertEquals(actual,expected,"Remove correct answer option is not appearing");

            Thread.sleep(10000);
            sentenceResponceQuestionCreation.getLink_clearAllAnswerChoices().click();
            Thread.sleep(1000);
            sentenceResponceQuestionCreation.getButton_Yes().click();//Click on 'Yes, Clear all' button
            Thread.sleep(5000);







            /*highlightText();
            Thread.sleep(2000);
            action = new Actions(driver);
            we = driver.findElement(By.className("sentence-response-selectiontext"));
            action.moveToElement(we).moveToElement(driver.findElement(By.className("sentence-response-selectiontext"))).click().build().perform();
           Thread.sleep(5000);
            actual = sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().getText();
            expected = "Mark as correct answer";
            Assert.assertEquals(actual,expected,"Mark as correct answer option is not appearing");
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct answer' option*/






            questionCreationGeneric.getButton_Save().click();
            Thread.sleep(1000);


            actual = sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().getAttribute("value");
            expected = assessmentName + assessmentName;
            Assert.assertEquals(actual, expected, "Assessment name is not editable");



            Thread.sleep(1000);
            sentenceResponceQuestionCreation.getButton_review().click(); //Click on 'Review' button to view the question
            Thread.sleep(1000);
            questionsListPage.getArrowLink().click();
            browserTabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(browserTabs .get(0));
            Thread.sleep(1000);
            assessmentReview.getButton_Edit().click();
            Thread.sleep(1000);
            actual =  sentenceResponceQuestionCreation.getLabel_questionTitle().getText();
            expected  = "Sentence Response question";
            //Assert.assertEquals(actual,expected,"Question Title is not displayed as 'Sentence Response question'");

            Thread.sleep(2000);
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().clear();
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);
            actual =  sentenceResponceQuestionCreation.getTextField_EnterQuestionText().getText();
            expected = questionText;
            Assert.assertEquals(actual, expected, "Text field to enter question body  is not editable");
            Thread.sleep(2000);
            sentenceResponceQuestionCreation.getTab_editText().click();//Click on edit text tab
            sentenceResponceQuestionCreation.getButton_Yes().click();//Click on yes button


            //expected Row No - 61 : 4. Instructor should be able to enter passage by clicking on the 'Enter Your Paragraph here' on Edit text tab
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().clear();
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys("First Line" + Keys.ENTER +"Second Line"+Keys.ENTER +"Third Line"+Keys.ENTER +"Fourth Line");
            actual = sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().getText();
            System.out.println("Actual Text : " + actual);
            Assert.assertEquals(actual,"First Line\n" +"Second Line\n" +"Third Line\n" +"Fourth Line","Instructor is not able to enter the passage on Edit Text box'");



            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);





            //Expected - Row No. 62 : 5. Instructor should be able to create answer choices by highlighting text from the Highlight text tab
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().clear();
            Thread.sleep(3000);
            str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);//send keys in Edit text
            sentenceResponceQuestionCreation.getTab_highlightText().click();
            highlightText();
            Thread.sleep(2000);
            action = new Actions(driver);
            we = driver.findElement(By.className("sentence-response-selectiontext"));
            action.moveToElement(we).moveToElement(driver.findElement(By.className("sentence-response-selectiontext"))).click().build().perform();
            Thread.sleep(5000);
            actual = sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().getText();
            expected = "Mark as correct answer";
            Assert.assertEquals(actual,expected,"Mark as correct answer option is not appearing");
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct answer' option

            //Remove the above comments later, important


           /* action = new Actions(driver);
            we = driver.findElement(By.className("sentence-response-selectiontext"));
            action.moveToElement(we).moveToElement(driver.findElement(By.className("sentence-response-selectiontext"))).click().build().perform();
            Thread.sleep(5000);*/
            action.moveToElement(driver.findElement(By.cssSelector("span[class='sentence-response-selectiontext selected-text-correct-answer']"))).click().build().perform();
            actual = sentenceResponceQuestionCreation.getInputLink_removeCorrectAnswer().getText();
            expected = "Remove correct answer";
            Assert.assertEquals(actual,expected,"Remove correct answer option is not appearing");

            Thread.sleep(10000);
            sentenceResponceQuestionCreation.getLink_clearAllAnswerChoices().click();
            Thread.sleep(1000);
            sentenceResponceQuestionCreation.getButton_Yes().click();//Click on 'Yes, Clear all' button
            Thread.sleep(2000);


            questionCreationGeneric.getButton_Save().click();
            Thread.sleep(1000);

            actual = sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().getAttribute("value");
            expected = assessmentName;
            Assert.assertEquals(actual, expected, "Assessment name is not editable");


        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'instructorToBeAbleToEditQuestionByEditLink' in the class 'SentenceResponce'",e);
        }
    }




    @Test(priority = 12,enabled = true)
    public void instructorToBeAbleToPreviewQuestionByPreviewLink() {
        try {


            //Row No - 79 : Instructor must be able to Preview the question by clicking on  'Preview' Link
            /*Expected - "1. A new tab must be opened that displays the preview
            2. The preview should be displayed just like a student would view the question"*/


            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(59));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(59));




            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);


            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);


            int index = 59;
            String appendChar = "J";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            Thread.sleep(1000);
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();//Clear the assessment text field
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);// Enter the assessment name
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);//Enter the question name
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();//Click on Question text passage area
            Thread.sleep(1000);
            sentenceResponceQuestionCreation.getCheckBox_AllowMultipleCorrectAnswer().click();//Click on 'Allow multiple correct answer'
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);// Type the question text in passage
            sentenceResponceQuestionCreation.getTab_highlightText().click();// Click on highlight text tab


            highlightText(0,0,50,0);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            mouseHoverOnHighlightedText(we,we);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct Answer Choice' option
            Thread.sleep(2000);
            questionCreationGeneric.getButton_Save().click();//Click on 'Save' button
            Thread.sleep(2000);
            sentenceResponceQuestionCreation.getButton_review().click(); //Click on 'Review' button to view the question
            //Thread.sleep(1000);
            questionsListPage.getArrowLink().click();
            List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(browserTabs .get(0));
            Thread.sleep(3000);
            sentenceResponceQuestionCreation.getButton_Preview().click(); //Click on 'Preview' button to view the question
            //Thread.sleep(1000);
            browserTabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(browserTabs .get(1));
            Thread.sleep(3000);
            actual = previewPage.getPreviewPageTitle().getText();
            expected = "PREVIEW";
            Assert.assertEquals(actual,expected,"Preview page is not opened");
            actual = previewPage.getQuestionTitle().getText();
            expected  = questionText;
            Assert.assertEquals(actual,expected,"Question text is not matching");


            previewPage.getText_selection().click();
            questionCreationGeneric.getSubmitButton().click(); //Click on 'Submit' button to submit the question
            Thread.sleep(3000);
            actual = previewPage.getNotificationText().getText();
            expected = "You got it right.";
            Assert.assertEquals(actual,expected,"Message notification 'You got it right.' is not displayed");
            actual = previewPage.getQuestionPoint_previewPage().getAttribute("value");
            expected  = "1";
            Assert.assertEquals(actual, expected, "Points Value is not 1 in the Preview Page (One question should be worth one point)");


        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'instructorToBeAbleToEditQuestionByEditLink' in the class 'SentenceResponce'",e);
        }
    }




    @Test(priority = 13,enabled = true)
    public void checkInstructorShortViewAfterHeClicksOnReview() {
        try {


            //Row No - 80 :  Instructor short view of sentence response type question when he clicks on 'Review'
            /*Expected  - "1.Only question title should be displayed.  
            2.Question Type should be displayed as “Sentence response”
            3.Owner of the question must be displayed
            4. Question ID must be displayed
            5. Corresponding TLO and ELO under which the question falls must be displayed
            6.Re-ordering in listview should be supported"*/


            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(59));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(59));
            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);


            int index = 59;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            Thread.sleep(1000);
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();//Clear the assessment text field
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);// Enter the assessment name
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);//Enter the question name
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();//Click on Question text passage area
            Thread.sleep(1000);
            sentenceResponceQuestionCreation.getCheckBox_AllowMultipleCorrectAnswer().click();//Click on 'Allow multiple correct answer'
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);// Type the question text in passage
            sentenceResponceQuestionCreation.getTab_highlightText().click();// Click on highlight text tab


            highlightText(0,0,50,0);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            mouseHoverOnHighlightedText(we,we);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct Answer Choice' option
            Thread.sleep(2000);
            questionCreationGeneric.getButton_Save().click();//Click on 'Save' button
            //Thread.sleep(1000);
            sentenceResponceQuestionCreation.getButton_review().click(); //Click on 'Review' button to view the question
            Thread.sleep(1000);



            //String questionID = pf_assignments.getLabelValue_QuestionID().getText();
            //String tloText = questionsListPage.getLabelValue_StandardTlo().getText();


            actual = questionsListPage.getCreateAssignmentQuestionName().getText();
            expected = "Q1:  "+questionText;
            Assert.assertEquals(actual,expected,"Question title is not as expected in the question's list page");


            actual = questionsListPage.getQuestionDetailsDisplay().getText();
            expected = "TYPE:Sentence ResponseOWNER:YouID:10799STANDARD : 1.OA, 1.OA.A.1";
            if(!(actual.contains("TYPE:Sentence ResponseOWNER:YouID:")&&actual.contains("STANDARD : 1.OA, 1.OA.A.1"))){
                Assert.fail("Question Type or Owner, ID, TLO or ELO is not matching as expected");
            }

            if(!(driver.findElements(By.xpath("//span[text() = 'ID:']//following-sibling::span[@class='as-questionType']")).size()!=0)){
                Assert.fail("ID is not matching as expected");

            }


        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'instructorToBeAbleToEditQuestionByEditLink' in the class 'SentenceResponce'",e);
        }
    }





    @Test(priority = 14,enabled = true)
    public void checkInstructorQuestionExpandedView() {
        try {


            //Row No - 81 : Instructor question expanded view
            /*Expected - "1. Question body must be displayed
            2. Owner and id of the question must be displayed
            3. The passage must be displayed with the highlighted text "*/


            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(59));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(59));
            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);


            int index = 59;
            String appendChar = "sgsg";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();//Clear the assessment text field
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);// Enter the assessment name
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);//Enter the question name
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();//Click on Question text passage area
            sentenceResponceQuestionCreation.getCheckBox_AllowMultipleCorrectAnswer().click();//Click on 'Allow multiple correct answer'
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);// Type the question text in passage
            sentenceResponceQuestionCreation.getTab_highlightText().click();// Click on highlight text tab
            highlightText(0,0,50,0);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            mouseHoverOnHighlightedText(we,we);
            Thread.sleep(1000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct Answer Choice' option
            Thread.sleep(1000);
            questionCreationGeneric.getButton_Save().click();//Click on 'Save' button
            sentenceResponceQuestionCreation.getButton_review().click(); //Click on 'Review' button to view the question
            Thread.sleep(1000);
            String questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText =  pf_assignments.getCreateAssignmentQuestionName().getText();
            String tloText = questionsListPage.getLabelValue_StandardTlo().getText();
            Assert.assertEquals(tloText,"STANDARD : 1.OA, 1.OA.A.1","TLO is not displayed properly");
            questionsListPage.getArrowLink().click();//Click on right arrow
            List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(browserTabs .get(0));
            Thread.sleep(1000);




            //Expected - 1 : The particular question should be opened
            actual = previewPage.getQuestionTitle().getText();
            String questionTextTokens[] = questionText.split(":");
            expected = questionTextTokens[1].trim();
            Assert.assertEquals(actual,expected,"Question title is not as expected in the question's list page");



            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(pf_assQuestion.getLabelValue_QuestionID().getText().trim(),questionID.trim(),"ID of the question is not displayed");


            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals("Owner: "+pf_assQuestion.getLabelValue_Owner().getText(),"Owner: You","Owner of the assignment is not displayed");

            //Expected - 4 : Edit and delete option must be displayed
            Assert.assertEquals(pf_assQuestion.getButton_Edit().getText(),"Edit","Edit Option is not displayed");
            Assert.assertEquals(pf_assQuestion.getButton_Delete().getText(), "Delete", "Delete Option is not displayed");

            //Expected - 5 : "  Preview button must be displayed"
            Assert.assertEquals(pf_assQuestion.getButton_Preview().getText(),"Preview","Preview Option is not displayed");



            //Expected - "7. TLO and ELO should be displayed properly
            Assert.assertEquals(pf_assQuestion.getLabel_tlo().getText(),"1.OA - Operations & Algebraic Thinking","TLO is not displayed properly");
            Assert.assertEquals(pf_assQuestion.getLabel_elo().getText(),"1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to","ELO is not displayed properly");


            if(!(driver.findElements(By.id("sentence-selection-heighlight-text-wrapper")).size()!=0)){
            Assert.fail("Question body is not displayed in sentence response question");
            }

            if(!(sentenceResponceQuestionCreation.getText_selection().getAttribute("style").equals("background: none repeat scroll 0% 0% rgb(210, 231, 205);"))){
                Assert.fail("The passage is not displayed with the highlighted text");
            }


        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'instructorToBeAbleToEditQuestionByEditLink' in the class 'SentenceResponce'",e);
        }
    }



    @Test(priority = 15,enabled = true)
    public void studentToBeAbleToAttemptSentenceResponseQuestion() {
        try {


            //Row No - 82 : Student should be able to attempt the 'Sentence response' question
            /*Expected "1. The question must be displayed
            2. Passage should be displayed
            3. Passage should not have any highlighted text initially"*/
            int index = 82;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);


            String appendChar = "F";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out*/
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            Thread.sleep(1000);

            //TO create sentence response question
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();//Clear the assessment text field
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);// Enter the assessment name
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);//Enter the question name
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();//Click on Question text passage area
            sentenceResponceQuestionCreation.getCheckBox_AllowMultipleCorrectAnswer().click();//Click on 'Allow multiple correct answer'
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);// Type the question text in passage
            sentenceResponceQuestionCreation.getTab_highlightText().click();// Click on highlight text tab
            Thread.sleep(5000);
            //Highlight the first line
            highlightText(0,0,50,0);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            mouseHoverOnHighlightedText(we,we);
            Thread.sleep(1000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct Answer Choice' option
            Thread.sleep(1000);

            //Highlight the 2nd line
            highlightText(0,50,50,0);
            questionCreationGeneric.getButton_Save().click();//Click on 'Save' button


            //new Assignment().create(index, "sentenceresponse");
            Thread.sleep(1000);
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            Thread.sleep(1000);
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as a student
            new Assignment().openAssignment(index);
            Thread.sleep(3000);



            //Expected - 1. The question must be displayed
            actual = previewPage.getQuestionTitle().getText();
            //expected = "Sentence Response "+questionText;
            expected = questionText;
            Assert.assertEquals(actual,expected,"Question title is not displayed as expected in the question's list page");


            //Expected - 2. Passage should be displayed
            if(pf_assQuestion.getTextArea_Passage().getText().equals("null")||pf_assQuestion.getTextArea_Passage().getText().equals("")||pf_assQuestion.getTextArea_Passage().getText().isEmpty()){
                Assert.fail("Passage is not displayed");
            }


            //Expected - 3. Passage should not have any highlighted text initially
            if(sentenceResponceQuestionCreation.getText_selection().getAttribute("style").equals("background: none repeat scroll 0% 0% rgb(210, 231, 205);")){
                Assert.fail("Passage should not have any highlighted text initially");
            }




            //Expected - 4. On mouse hover, the entire highlighted text must be displayed
            we = driver.findElement(By.className("sentence-response-selectiontext"));
            Thread.sleep(5000);
            Actions action = new Actions(driver);
            action.moveToElement(we).moveToElement(we).build().perform();
            Thread.sleep(5000);
            String style = sentenceResponceQuestionCreation.getText_selection().getAttribute("style");
            System.out.println("style : " + style);
            if(!(sentenceResponceQuestionCreation.getText_selection().getAttribute("style").equals("background-image: none; background-repeat: repeat; background-attachment: scroll; background-position: 0% 0%; background-clip: border-box; background-origin: padding-box; background-size: auto auto;"))){
                Assert.fail("On mouse hover, the entire highlighted text must be displayed");
            }





            //Expected - 5. Student must be able to select the answer choice by clicking on any part of the answer choice
            mouseHoverOnHighlightedText(we,we);
            Thread.sleep(5000);
            style =  sentenceResponceQuestionCreation.getHighlightedYellowText().getAttribute("style");
            System.out.println("style 1 : " + style);
            if(!(sentenceResponceQuestionCreation.getHighlightedYellowText().getAttribute("style").equals("background-image: none; background-repeat: repeat; background-attachment: scroll; background-clip: border-box; background-origin: padding-box; background-size: auto auto;"))){
                Assert.fail("Student is not able to select the answer choice by clicking on any part of the answer choice resulting in yellow color");
            }




            //Expected - 6. The selected answer choice must be highlighted with bight green color and blue border
            //Implementaion Pending, Hence not automated






            //Expected -7. Student should be able to select multiple answers if "Allow multiple correct answer" check box is selected by the instructor
            //TO be written
            WebElement we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            WebElement we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            style =  driver.findElements(By.className("sentence-response-selectiontext")).get(1).getAttribute("style");
            System.out.println("style 1 : " + style);
            if(!(driver.findElements(By.className("sentence-response-selectiontext")).get(1).getAttribute("style").equals("background-image: none; background-repeat: repeat; background-attachment: scroll; background-clip: border-box; background-origin: padding-box; background-size: auto auto;"))){
                Assert.fail("Student is not able to select multiple answers if \"Allow multiple correct answer\" check box is selected by the instructor");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'studentToBeAbleToAttemptSentenceResponseQuestion' in the class 'SentenceResponce'",e);
        }
    }


    @Test(priority = 16,enabled = true)
    public void studentToBeAbleToDeselectAnswerChoice() {
        try {


            //Row No - 87 : Student shoulde be able to deselect the answer choice that is  already selected by clicking on it
            //Expected - The selected answer choice must be deselected

            int index = 87;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);


            String appendChar = "B";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            Thread.sleep(1000);

            //TO create sentence response question
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();//Clear the assessment text field
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);// Enter the assessment name
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);//Enter the question name
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();//Click on Question text passage area
            sentenceResponceQuestionCreation.getCheckBox_AllowMultipleCorrectAnswer().click();//Click on 'Allow multiple correct answer'
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);// Type the question text in passage
            sentenceResponceQuestionCreation.getTab_highlightText().click();// Click on highlight text tab
            Thread.sleep(5000);
            //Highlight the first line
            highlightText(0,0,50,0);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            mouseHoverOnHighlightedText(we,we);
            Thread.sleep(1000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();//Click on link 'Mark as correct Answer Choice' option
            Thread.sleep(1000);

            //Highlight the 2nd line
            highlightText(0,50,50,0);
            questionCreationGeneric.getButton_Save().click();//Click on 'Save' button


            //new Assignment().create(index, "sentenceresponse");
            Thread.sleep(1000);
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            Thread.sleep(1000);
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as a student
            new Assignment().openAssignment(index);
            Thread.sleep(3000);

            WebElement we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            WebElement we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            Thread.sleep(2000);
            mouseHoverOnHighlightedText(we1,we1);
            Thread.sleep(5000);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            mouseHoverOnHighlightedText(we1,we1);
            Thread.sleep(5000);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);

            Thread.sleep(5000);
            Actions action = new Actions(driver);
            //WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            action.moveToElement(we1).moveToElement(we1).build().perform();
            Thread.sleep(5000);
            String specialStyle = sentenceResponceQuestionCreation.getText_selection().getAttribute("style");
            System.out.println("specialStyle : " + specialStyle);
            if(!(sentenceResponceQuestionCreation.getText_selection().getAttribute("style").equals("background: none repeat scroll 0% 0% rgb(153, 153, 153);"))){
                Assert.fail("The selected answer choice is not able to be deselected");
            }



            Thread.sleep(5000);
            action = new Actions(driver);
            action.moveToElement(we1).moveToElement(we2).build().perform();
            Thread.sleep(5000);
            if(!(driver.findElements(By.className("sentence-response-selectiontext")).get(1).getAttribute("style").equals("background: none repeat scroll 0% 0% rgb(153, 153, 153);"))){
                Assert.fail("The selected answer choice is not able to be deselected");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'studentToBeAbleToDeselectAnswerChoice' in the class 'SentenceResponce'",e);
        }
    }




    @Test(priority = 17,enabled = true)
    public void studentToBeAbleToUseWriteBoardIfItIsAllowedForQuestion() {
        try {


            //Row No - 88 : Student should be able to use the writeboard if it is allowed for the question
            //Expected - 1. ' Show your work' link should be displayed if the question has writeboard enabled

            int index = 88;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);


            String appendChar = "M";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            Thread.sleep(1000);
            new Assignment().create(index, "sentenceresponse");
            Thread.sleep(1000);
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            Thread.sleep(1000);
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as a student
            new Assignment().openAssignment(index);//Open the assignment
            Thread.sleep(3000);

            actual = sentenceResponceQuestionCreation.getlinkText_YourWork().getText();// Fetch the link text 'Show your work'
            expected = "Show your work";
            Assert.assertEquals(actual,expected,"' Show your work' link is not displayed if the question has writeboard enabled");





            //Expected - 2. On clicking ' Show your work' link, writeboard should be opened
            sentenceResponceQuestionCreation.getlinkText_YourWork().click();//Click on link 'Show your work'
            Thread.sleep(2000);
            actual = sentenceResponceQuestionCreation.getlinkText_YourWork().getText();//Fetch the link text 'Hide your work'
            expected = "Hide your work";
            Assert.assertEquals(actual,expected,"' Show your work' link is not displayed if the question has writeboard enabled");


            driver.switchTo().frame("whiteBoard_iframe_kedukWBSTUDENT__whiteBoard_JSONData__false");
            List<WebElement> mainControlsList = sentenceResponceQuestionCreation.getToolbar_WriteboardConfigurator().findElements(By.tagName("div"));
            if(!(mainControlsList.get(0).getAttribute("class").equals("left-align") && mainControlsList.get(1).getAttribute("class").equals("copy-paste") && mainControlsList.get(2).getAttribute("class").equals("front-back") && mainControlsList.get(3).getAttribute("class").equals("right-align"))){
                Assert.fail("On clicking ' Show your work' link, writeboard is not opened");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'studentToBeAbleToUseWriteBoardIfItIsAllowedForQuestion' in the class 'SentenceResponce'",e);
        }
    }


    @Test(priority = 18,enabled = true)
    public void evaluateSRQWhenAllowMultipleCorrectAnswerIsDisabledSelectCorrectAnswer() {
        try {


            //Row No - 90: Evaluation of sentence response question with "Allow multiple correct answer" is disabled
            //Expected - 1. On selecting the correct answer student gets Full points

            int index = 90;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);
            InstructorDashboard dashBoard = PageFactory.initElements(driver, InstructorDashboard.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);
            Performance performance = PageFactory.initElements(driver, Performance.class);

            String appendChar = "ABC";


            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            Thread.sleep(1000);

            //TO create sentence response question
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            closeHelp();
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();//Clear the assessment text field
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);// Enter the assessment name
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);//Enter the question name
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();//Click on Question text passage area
            //sentenceResponceQuestionCreation.getCheckBox_AllowMultipleCorrectAnswer().click();//Click on 'Allow multiple correct answer'
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);// Type the question text in passage
            sentenceResponceQuestionCreation.getTab_highlightText().click();// Click on highlight text tab

            Thread.sleep(5000);
            highlightText(0,0,50,0);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            mouseHoverOnHighlightedText(we,we);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();

            Thread.sleep(2000);
            highlightText(0,100,50,0);
            Thread.sleep(3000);
            WebElement we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            WebElement we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();
            Thread.sleep(3000);
            questionCreationGeneric.getButton_Save().click();//Click on 'Save' button

            Thread.sleep(1000);
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            Thread.sleep(1000);
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as a student
            new Assignment().openAssignment(index);
            Thread.sleep(3000);
            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            Thread.sleep(2000);
            mouseHoverOnHighlightedText(we1, we2);
            Thread.sleep(1000);
            assessments.getButton_submit().click();
            assessments.getFinal_submit().click();//Click on submit
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teachers
            dashBoard.getButton_ViewResponses().click();// Click on button 'View Responses'
            dashBoard.getButton_releaseGradeForAll().click();// Click on button 'Release grade for all'
            assessmentResponses.getGradeBookContentColumn().click();//Click on grade book content link to open the grade book
            actual = pf_assQuestion.getValue_QuestionPerformanceScore().getAttribute("value");
            expected = "1";
            Assert.assertEquals(actual,expected," On selecting the correct answer student is not getting Full points(1)");

            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar,index);//log in as Student
            dashBoard.getLink_activeAsssessment().click();//Click on Active assessment link
            performance.getButton_Continue().click();//Click on continue button
            Thread.sleep(3000);
            actual = pf_assQuestion.getValue_QuestionPerformanceScore().getAttribute("value");
            expected = "1";
            Assert.assertEquals(actual,expected," On selecting the correct answer student is not getting Full points(1.0)");

        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'evaluateSRQWhenAllowMultipleCorrectAnswerIsDisabled_SelectCorrectAnswer' in the class 'SentenceResponce'",e);
        }
    }




    @Test(priority = 19,enabled = true)
    public void evaluateSRQWhenAllowMultipleCorrectAnswerIsDisabledSelectInCorrectAnswer() {
        try {


            //Row No - 91: Evaluation of sentence response question with "Allow multiple correct answer" is disabled
            //Expected - 2. On selecting the incorrect answer student gets 0 points

            int index = 91;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);
            InstructorDashboard dashBoard = PageFactory.initElements(driver, InstructorDashboard.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);
            Performance performance = PageFactory.initElements(driver, Performance.class);

            String appendChar = "Cam";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            Thread.sleep(1000);

            //TO create sentence response question
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            closeHelp();
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();//Clear the assessment text field
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);// Enter the assessment name
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);//Enter the question name
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();//Click on Question text passage area
            //sentenceResponceQuestionCreation.getCheckBox_AllowMultipleCorrectAnswer().click();//Click on 'Allow multiple correct answer'
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);// Type the question text in passage
            sentenceResponceQuestionCreation.getTab_highlightText().click();// Click on highlight text tab

            Thread.sleep(5000);
            highlightText(0,0,50,0);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            mouseHoverOnHighlightedText(we,we);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();


            highlightText(0,100,50,0);
            WebElement we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            WebElement we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();
            Thread.sleep(3000);
            questionCreationGeneric.getButton_Save().click();//Click on 'Save' button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));
            sentenceResponceQuestionCreation.getButton_review().click();//Click on review button
            questionsListPage.getButton_saveForLater().click();//Click on Save for later button

            Thread.sleep(1000);
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            Thread.sleep(1000);
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as a student
            new Assignment().openAssignment(index);
            Thread.sleep(3000);
            we = driver.findElement(By.className("sentence-response-selectiontext"));
            Thread.sleep(2000);
            mouseHoverOnHighlightedText(we, we);
            Thread.sleep(1000);
            assessments.getButton_submit().click();
            assessments.getFinal_submit().click();//Click on submit button
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teachers
            dashBoard.getButton_ViewResponses().click();// Click on button 'View Responses'
            dashBoard.getButton_releaseGradeForAll().click();// Click on button 'Release grade for all'
            assessmentResponses.getGradeBookContentColumn().click();//Click on grade book content link to open the grade book
            actual = pf_assQuestion.getValue_QuestionPerformanceScore().getAttribute("value");
            expected = "0";
            Assert.assertEquals(actual,expected," On selecting the correct answer student is not getting Full points(1.0)");




            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar,index);//log in as Student
            dashBoard.getLink_activeAsssessment().click();//Click on Active assessment link
            performance.getButton_Continue().click();//Click on continue button
            Thread.sleep(3000);
            actual = pf_assQuestion.getValue_QuestionPerformanceScore().getAttribute("value");
            expected = "0";
            Assert.assertEquals(actual,expected," On selecting the correct answer student is not getting Full points(1.0)");

        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'evaluateSentenceResponseQuestionWhenAllowMultipleCorrectAnswerIsDisabled' in the class 'SentenceResponce'",e);
        }
    }



    @Test(priority = 20,enabled = true)
    public void evaluateSentenceResponseQuestionWhenAllowMultipleCorrectAnswerIsEnabled() {
        try {


            //Row No - 92 : Evaluation of sentence response question with "Allow multiple correct answer" is enabled
            //Expected - 1. Full points if student selects all the correct answer and doesn't select any incorrect answer
            //Expected - 2 : 3. Points should not be in negative
            int index = 92;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);
            InstructorDashboard dashBoard = PageFactory.initElements(driver, InstructorDashboard.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);
            Performance performance = PageFactory.initElements(driver, Performance.class);

            String appendChar = "DEF";


            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            Thread.sleep(1000);

            //TO create sentence response question
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();//Clear the assessment text field
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);// Enter the assessment name
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);//Enter the question name
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();//Click on Question text passage area
            sentenceResponceQuestionCreation.getCheckBox_AllowMultipleCorrectAnswer().click();//Click on 'Allow multiple correct answer'
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);// Type the question text in passage
            sentenceResponceQuestionCreation.getTab_highlightText().click();// Click on highlight text tab

            //1st line
            Thread.sleep(5000);
            highlightText(0,0,50,0);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            mouseHoverOnHighlightedText(we,we);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();


            //2nd line
            Thread.sleep(2000);
            highlightText(0,50,50,0);
            Thread.sleep(3000);
            WebElement we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            WebElement we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            Thread.sleep(5000);
            Actions action = new Actions(driver);
            action.moveToElement(we1).moveToElement(we2).build().perform();
            Thread.sleep(5000);



            //3rd line
            Thread.sleep(2000);
            highlightText(0,100,50,0);
            Thread.sleep(3000);
            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();
            Thread.sleep(3000);


            /*//3rd line
            Thread.sleep(2000);
            highlightText(0,150,50,0);
            Thread.sleep(3000);
            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();
            Thread.sleep(3000);*/



            questionCreationGeneric.getButton_Save().click();//Click on 'Save' button
            Thread.sleep(1000);
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            Thread.sleep(1000);
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as a student
            new Assignment().openAssignment(index);
            Thread.sleep(3000);
            we = driver.findElement(By.className("sentence-response-selectiontext"));
            //we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            Thread.sleep(2000);
            mouseHoverOnHighlightedText(we, we);
            Thread.sleep(1000);


            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);//try this
            //we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(0);

            Thread.sleep(2000);
            mouseHoverOnHighlightedText(we1, we2);
            Thread.sleep(1000);

            assessments.getButton_submit().click();
            assessments.getFinal_submit().click();//Click on submit
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teachers
            dashBoard.getButton_ViewResponses().click();// Click on button 'View Responses'
            dashBoard.getButton_releaseGradeForAll().click();// Click on button 'Release grade for all'
            assessmentResponses.getGradeBookContentColumn().click();//Click on grade book content link to open the grade book
            actual = pf_assQuestion.getValue_QuestionPerformanceScore().getAttribute("value");
            expected = "1";
            Assert.assertEquals(actual,expected," On selecting the correct answer student is not getting Full points(1.0)");

            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar,index);//log in as Student
            dashBoard.getLink_activeAsssessment().click();//Click on Active assessment link
            performance.getButton_Continue().click();//Click on continue button
            Thread.sleep(3000);
            actual = pf_assQuestion.getValue_QuestionPerformanceScore().getAttribute("value");
            expected = "1";
            Assert.assertEquals(actual,expected," On selecting the correct answer student is not getting Full points(1.0)");


        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'evaluateSentenceResponseQuestionWhenAllowMultipleCorrectAnswerIsEnabled' in the class 'SentenceResponce'",e);
        }
    }




    @Test(priority = 21,enabled = true)
    public void evaluateSRQWhenAllowMultipleCorrectAnswerIsEnabledCorrectAndIncorrect() {
        try {


            //Row No - 93 : Evaluation of sentence response question with "Allow multiple correct answer" is enabled
            /*Expected - "2. In case of selecting both correct and incorrect answer
            Points scored = max(Number of correct choices answered by the student /n - (total number of wrong choices answered by the student  / (n-1)), 0)"*/
            //Expected - 2 : 3. Points should not be in negative
            int index = 93;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);
            InstructorDashboard dashBoard = PageFactory.initElements(driver, InstructorDashboard.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);
            Performance performance = PageFactory.initElements(driver, Performance.class);

            String appendChar = "A";


            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            Thread.sleep(1000);

            //TO create sentence response question
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();//Clear the assessment text field
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);// Enter the assessment name
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);//Enter the question name
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();//Click on Question text passage area
            sentenceResponceQuestionCreation.getCheckBox_AllowMultipleCorrectAnswer().click();//Click on 'Allow multiple correct answer'
            String str = new RandomString().randomstring(50);
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(str);// Type the question text in passage
            sentenceResponceQuestionCreation.getTab_highlightText().click();// Click on highlight text tab

            //1st line
            Thread.sleep(5000);
            highlightText(0,0,50,0);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            mouseHoverOnHighlightedText(we,we);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();


            //2nd line
            Thread.sleep(2000);
            highlightText(0,50,50,0);
            Thread.sleep(3000);
            WebElement we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            WebElement we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            Thread.sleep(5000);
            Actions action = new Actions(driver);
            action.moveToElement(we1).moveToElement(we2).build().perform();
            Thread.sleep(5000);



            //3rd line
            Thread.sleep(2000);
            highlightText(0,100,50,0);
            Thread.sleep(3000);
            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();
            Thread.sleep(3000);


            /*//3rd line
            Thread.sleep(2000);
            highlightText(0,150,50,0);
            Thread.sleep(3000);
            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();
            Thread.sleep(3000);*/



            questionCreationGeneric.getButton_Save().click();//Click on 'Save' button
            Thread.sleep(1000);
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            Thread.sleep(1000);
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as a student
            new Assignment().openAssignment(index);
            Thread.sleep(3000);
            we = driver.findElement(By.className("sentence-response-selectiontext"));
            //we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            Thread.sleep(2000);
            mouseHoverOnHighlightedText(we, we);
            Thread.sleep(1000);


            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(2);//try this
            //we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(0);

            Thread.sleep(2000);
            mouseHoverOnHighlightedText(we1, we2);
            Thread.sleep(1000);

            assessments.getButton_submit().click();
            assessments.getFinal_submit().click();//Click on submit
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teachers
            dashBoard.getButton_ViewResponses().click();// Click on button 'View Responses'
            dashBoard.getButton_releaseGradeForAll().click();// Click on button 'Release grade for all'
            assessmentResponses.getGradeBookContentColumn().click();//Click on grade book content link to open the grade book
            actual = pf_assQuestion.getValue_QuestionPerformanceScore().getAttribute("value");
            expected = "0.3";
            if(!(actual.contains(expected))){
            Assert.fail("On selecting the correct answer student is not getting Full points(1.0)");
            }
            //Assert.assertEquals(actual,expected," On selecting the correct answer student is not getting Full points(1.0)");

            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar,index);//log in as Student
            dashBoard.getLink_activeAsssessment().click();//Click on Active assessment link
            performance.getButton_Continue().click();//Click on continue button
            Thread.sleep(3000);
            actual = pf_assQuestion.getValue_QuestionPerformanceScore().getAttribute("value");
            expected = "0.3";
            if(!(actual.contains(expected))){
                Assert.fail("On selecting the correct answer student is not getting Full points(1.0)");
            }
            //Assert.assertEquals(actual,expected," On selecting the correct answer student is not getting Full points(1.0)");


        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'evaluateSRQWhenAllowMultipleCorrectAnswerIsEnabledCorrectAndIncorrect' in the class 'SentenceResponce'",e);
        }
    }



    @Test(priority = 22,enabled = true)
    public void instructorToBeAbleToViewStudentsAnswer() {
        try {


            //Row No - 95 : Instructor must be able to view the student's answer
            /*Expected - "Instructor must view the answer in the following way
            1. The question body must be displayed
            2. The passage with answer choices selected by the student must be displayed correctly"*/
            int index = 95;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            String feedback = ReadTestData.readDataByTagName("", "feedback", Integer.toString(index));
            String passageText = ReadTestData.readDataByTagName("", "passageText", Integer.toString(index));



            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);
            InstructorDashboard dashBoard = PageFactory.initElements(driver, InstructorDashboard.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);
            Performance performance = PageFactory.initElements(driver, Performance.class);

            String appendChar = "C";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            Thread.sleep(1000);

            //TO create sentence response question
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            closeHelp();
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();//Clear the assessment text field
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);// Enter the assessment name
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);//Enter the question name
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();//Click on Question text passage area
            sentenceResponceQuestionCreation.getCheckBox_AllowMultipleCorrectAnswer().click();//Click on 'Allow multiple correct answer'
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(passageText);// Type the question text in passage
            sentenceResponceQuestionCreation.getTab_highlightText().click();// Click on highlight text tab

            //1st line
            Thread.sleep(5000);
            highlightText(0, 0, 50, 0);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            mouseHoverOnHighlightedText(we, we);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();


            //2nd line
            Thread.sleep(2000);
            highlightText(0, 50, 50, 0);
            Thread.sleep(3000);
            WebElement we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            WebElement we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            Thread.sleep(5000);
            Actions action = new Actions(driver);
            action.moveToElement(we1).moveToElement(we2).build().perform();
            Thread.sleep(5000);



            //3rd line
            Thread.sleep(2000);
            highlightText(0, 100, 50, 0);
            Thread.sleep(3000);
            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();
            Thread.sleep(3000);


            questionCreationGeneric.getButton_Save().click();//Click on 'Save' button
            Thread.sleep(1000);
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            Thread.sleep(1000);
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as a student
            new Assignment().openAssignment(index);
            Thread.sleep(3000);
            we = driver.findElement(By.className("sentence-response-selectiontext"));
            Thread.sleep(2000);
            mouseHoverOnHighlightedText(we, we);
            Thread.sleep(1000);


            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(2);//try this

            Thread.sleep(2000);
            mouseHoverOnHighlightedText(we1, we2);
            Thread.sleep(1000);
            assessments.getButton_submit().click();
            assessments.getFinal_submit().click();//Click on submit
            new Navigator().logout();//log out*/
            new Login().loginAsInstructor(appendChar, index);//log in as teachers
            dashBoard.getButton_ViewResponses().click();// Click on button 'View Responses'
            dashBoard.getButton_releaseGradeForAll().click();// Click on button 'Release grade for all'
            assessmentResponses.getGradeBookContentColumn().click();//Click on grade book content link to open the grade book
            Thread.sleep(3000);



            //Row No - 95 : Expected - 1. The question body must be displayed
            actual = pf_assQuestion.getLabel_questionName().getText().trim();
            expected = questionText;
            Assert.assertEquals(actual,expected," The question body is not displayed with the expected text");




            //Row No - 95 : Expected - 2. The passage with answer choices selected by the student must be displayed correctly
            actual = sentenceResponceQuestionCreation.getText_selection().getText();
            expected = "tmsoga";
            Assert.assertEquals(actual,expected,"The First correct answer is not shown as per the student's attempt");

            actual = driver.findElements(By.cssSelector("span[class='sentence-response-selectiontext selected-text-correct-answer']")).get(1).getText();
            expected = "splnnjm";
            Assert.assertEquals(actual,expected,"The Second correct answer is not shown as per the student's attempt");

            actual = driver.findElements(By.className("sentence-response-selectiontext")).get(2).getText();
            expected= "lbylgkr";
            Assert.assertEquals(actual,expected,"The wrong answer is not marked with Red color");



            //Row No - 96 : Expected - 3. The correct answer should be marked with green color and a tick mark
            actual = sentenceResponceQuestionCreation.getText_selection().getAttribute("style");
            System.out.println("Style 1 : " + actual);
            expected = "background: none repeat scroll 0% 0% rgb(210, 231, 205);";
            Assert.assertEquals(actual,expected,"The First correct answer is not marked with green color");

            actual = driver.findElements(By.cssSelector("span[class='sentence-response-selectiontext selected-text-correct-answer']")).get(1).getAttribute("style");
            System.out.println("Style 2 : " + actual);
            expected = "background: none repeat scroll 0% 0% rgb(210, 231, 205);";
            Assert.assertEquals(actual,expected,"The Second correct answer is not marked with green color");



            //Row No - 96 : Expected - 4. The wrong answer must be marked with Red color and a cross
            actual = driver.findElements(By.className("sentence-response-selectiontext")).get(2).getAttribute("style");
            System.out.println("Style 3 : " + actual);
            expected= "background: none repeat scroll 0% 0% rgb(255, 102, 102);";
            Assert.assertEquals(actual,expected,"The wrong answer is not marked with Red color");



            //Row No - 97 : Expected - 6. The marks area should be shown if the assessment is gradable
            actual = performance.getQuestionPerformanceScore().findElement(By.tagName("input")).getAttribute("value");
            expected = "0.3";
            if(!(actual.contains(expected))){
                Assert.fail("On selecting the correct answer student is not getting Full points(0.33)");
            }
            //Assert.assertEquals(actual,expected,"The marks area is not shown with the value '0.3' if the assessment is gradable");

            actual = assessmentResponses.getQuestionpoint().getText();
            expected = "1";
            Assert.assertEquals(actual,expected,"The marks area is not shown with the value '1' if the assessment is gradable");



            //Row No - 97 : Expected - 5.Instructor must be able to enter feedback for the question
            performance.getTextArea_EnterFeedback().click();// Click on 'Enter feedback'
            performance.getTextArea_EnterFeedback().sendKeys(feedback);
            performance.getButton_Save().click();
            Thread.sleep(3000);

            /*new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar,index);//log in as Student
            dashBoard.getLink_activeAsssessment().click();//Click on Active assessment link
            performance.getButton_Continue().click();//Click on continue button
            Thread.sleep(3000);
            actual = pf_assQuestion.getValue_QuestionPerformanceScore().getAttribute("value");
            expected = "0.3";
            Assert.assertEquals(actual,expected," On selecting the correct answer student is not getting Full points(1.0)");*/


        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'instructorToBeAbleToViewStudentsAnswer' in the class 'SentenceResponce'",e);
        }
    }


    @Test(priority = 23,enabled = true)
    public void StudentToBeAbleToViewAnswerGivenByHim() {
        try {


            //Row No - 99 : Student must be able to view the student's answer
            /*Expected - "Instructor must view the answer in the following way
            1. The question body must be displayed
            2. The passage with answer choices selected by the student must be displayed correctly"*/
            int index = 99;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            String feedback = ReadTestData.readDataByTagName("", "feedback", Integer.toString(index));
            String passageText = ReadTestData.readDataByTagName("", "passageText", Integer.toString(index));



            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);
            InstructorDashboard dashBoard = PageFactory.initElements(driver, InstructorDashboard.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);
            Performance performance = PageFactory.initElements(driver, Performance.class);

            String appendChar = "G";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            Thread.sleep(1000);

            //TO create sentence response question
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            Thread.sleep(3000);
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_SentenceResponse().click(); //Click on Icon 'Sentence Response' to create the sentence response type question
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().clear();//Clear the assessment text field
            sentenceResponceQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);// Enter the assessment name
            sentenceResponceQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);//Enter the question name
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().click();//Click on Question text passage area
            sentenceResponceQuestionCreation.getCheckBox_AllowMultipleCorrectAnswer().click();//Click on 'Allow multiple correct answer'
            sentenceResponceQuestionCreation.getTextArea_EnterYourParagraphHere().sendKeys(passageText);// Type the question text in passage
            sentenceResponceQuestionCreation.getTab_highlightText().click();// Click on highlight text tab

            //1st line
            Thread.sleep(5000);
            highlightText(0, 0, 50, 0);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            mouseHoverOnHighlightedText(we, we);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();


            //2nd line
            Thread.sleep(2000);
            highlightText(0, 50, 50, 0);
            Thread.sleep(3000);
            WebElement we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            WebElement we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            Thread.sleep(5000);
            Actions action = new Actions(driver);
            action.moveToElement(we1).moveToElement(we2).build().perform();
            Thread.sleep(5000);



            //3rd line
            Thread.sleep(2000);
            highlightText(0, 100, 50, 0);
            Thread.sleep(3000);
            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(1);
            mouseHoverOnHighlightedText(we1,we2);
            Thread.sleep(5000);
            sentenceResponceQuestionCreation.getInputLink_MarkAsCorrectAnswer().click();
            Thread.sleep(3000);


            questionCreationGeneric.getButton_Save().click();//Click on 'Save' button
            Thread.sleep(1000);
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            Thread.sleep(1000);
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);//log in as a student
            new Assignment().openAssignment(index);
            Thread.sleep(3000);
            we = driver.findElement(By.className("sentence-response-selectiontext"));
            Thread.sleep(2000);
            mouseHoverOnHighlightedText(we, we);
            Thread.sleep(1000);


            we1 = driver.findElement(By.className("sentence-response-selectiontext"));
            we2 = driver.findElements(By.className("sentence-response-selectiontext")).get(2);//try this

            Thread.sleep(2000);
            mouseHoverOnHighlightedText(we1, we2);
            Thread.sleep(1000);
            assessments.getButton_submit().click();
            assessments.getFinal_submit().click();//Click on submit
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teachers
            dashBoard.getButton_ViewResponses().click();// Click on button 'View Responses'
            assessmentResponses.getGradeBookContentColumn().click();//Click on grade book content link to open the grade book
            performance.getTextArea_EnterFeedback().click();// Click on 'Enter feedback'
            performance.getTextArea_EnterFeedback().sendKeys(feedback);
            performance.getButton_Save().click();
            Thread.sleep(3000);
            driver.findElement(By.className("user-performance-back-btn")).click();
            Thread.sleep(3000);
            dashBoard.getButton_releaseGradeForAll().click();// Click on button 'Release grade for all'
            Thread.sleep(3000);
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar,index);//log in as Student
            Thread.sleep(2000);
            dashBoard.getLink_activeAsssessment().click();//Click on Active assessment link
            Thread.sleep(5000);
            performance.getButton_Continue().click();//Click on continue button
            Thread.sleep(5000);

           //Row No - 99 : Expected - 1. The question body must be displayed
            actual = pf_assQuestion.getLabel_questionName().getText().trim();
            expected = questionText;
            Assert.assertEquals(actual,expected," The question body is not displayed with the expected text");




            //Row No - 99 : Expected - 2. The passage with answer choices selected by the student must be displayed correctly
            actual = sentenceResponceQuestionCreation.getText_selection().getText();
            expected = "tmsoga";
            Assert.assertEquals(actual,expected,"The First correct answer is not shown as per the student's attempt");

            actual = driver.findElements(By.cssSelector("span[class='sentence-response-selectiontext selected-text-correct-answer']")).get(1).getText();
            expected = "splnnjm";
            Assert.assertEquals(actual,expected,"The Second correct answer is not shown as per the student's attempt");

            actual = driver.findElements(By.className("sentence-response-selectiontext")).get(2).getText();
            expected= "lbylgkr";
            Assert.assertEquals(actual,expected,"The wrong answer is not marked with Red color");



            //Row No - 100 : Expected - 3. The correct answer should be marked with green color and a tick mark
            actual = sentenceResponceQuestionCreation.getText_selection().getAttribute("style");
            System.out.println("Style 1 : " + actual);
            expected = "background: none repeat scroll 0% 0% rgb(210, 231, 205);";
            Assert.assertEquals(actual,expected,"The First correct answer is not marked with green color");

            actual = driver.findElements(By.cssSelector("span[class='sentence-response-selectiontext selected-text-correct-answer']")).get(1).getAttribute("style");
            System.out.println("Style 2 : " + actual);
            expected = "background: none repeat scroll 0% 0% rgb(210, 231, 205);";
            Assert.assertEquals(actual,expected,"The Second correct answer is not marked with green color");



            //Row No - 100 : Expected - 4. The wrong answer must be marked with Red color and a cross
            actual = driver.findElements(By.className("sentence-response-selectiontext")).get(2).getAttribute("style");
            System.out.println("Style 3 : " + actual);
            expected= "background: none repeat scroll 0% 0% rgb(255, 102, 102);";
            Assert.assertEquals(actual,expected,"The wrong answer is not marked with Red color");



            //Row No - 101 : Expected - 6. The marks area should be shown if the assessment is gradable
            //Row No - 103 : Expected - 9 : 9. The marks area should be shown if the assessment is graded
            actual = performance.getQuestionPerformanceScore().findElement(By.tagName("input")).getAttribute("value");
            expected = "0.3";
            if(!(actual.contains(expected))){
                Assert.fail("The marks area is not shown with the value '0.3' if the assessment is gradable");
            }

            actual = assessmentResponses.getQuestionpoint().getText();
            expected = "1";
            Assert.assertEquals(actual,expected,"The marks area is not shown with the value '1' if the assessment is gradable");



            //Row No - 97 : Expected - 5. If the instructor has entered any feedback for the question, then that should be displayed
            /*performance.getTextArea_EnterFeedback().click();// Click on 'Enter feedback'
            performance.getTextArea_EnterFeedback().sendKeys(feedback);
            performance.getButton_Save().click();
            Thread.sleep(3000);*/

            actual = sentenceResponceQuestionCreation.getFeedback_content().getText().trim();
            Assert.assertEquals(actual,feedback,"Feedback is not shpowing even If the instructor has entered feedback for the question");


        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'StudentToBeAbleToViewAnswerGivenByHim' in the class 'SentenceResponce'",e);
        }
    }





    public void highlightText() {
        try {
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(59));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(59));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);


            Thread.sleep(10000);
            WebElement element = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.id("sentence-selection-raw-content-div-heighlight")));
            Thread.sleep(1000);

            Actions actions = new Actions(driver);
            Thread.sleep(1000);
            actions.moveToElement(element, 0, 0)
                    .clickAndHold()
                    .moveByOffset(50, 0)
                    .release()
                    .perform();
            Thread.sleep(2000);
            System.out.println("Completed");
        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'highlightText' in the class 'SentenceResponce'",e);

        }
    }
    public void highlightText(int x1, int y1, int x2, int y2) {
        try {
            Thread.sleep(10000);
            WebElement element = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.id("sentence-selection-raw-content-div-heighlight")));
            Thread.sleep(1000);
            Actions actions = new Actions(driver);
            Thread.sleep(1000);
            actions.moveToElement(element, x1, y1)
                    .clickAndHold()
                    .moveByOffset(x2, y2)
                    .release()
                    .perform();
            Thread.sleep(2000);
            System.out.println("Completed");
        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'highlightText' in the class 'SentenceResponce'",e);

        }
    }

  public void mouseHoverOnHighlightedText(WebElement dest1, WebElement dest2){
      try{
          Thread.sleep(5000);
          Actions action = new Actions(driver);
          //WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
          action.moveToElement(dest1).moveToElement(dest2).click().build().perform();
          Thread.sleep(5000);
      }catch(Exception e){
          Assert.fail("Exception in the test case 'mouseHoverOnHighlightedText' in the class 'SentenceResponce'",e);
      }
  }
}
