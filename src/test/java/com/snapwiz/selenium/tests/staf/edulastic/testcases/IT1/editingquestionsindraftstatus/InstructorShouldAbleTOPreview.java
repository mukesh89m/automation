package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.editingquestionsindraftstatus;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview.PreviewPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by priyanka on 12/18/2014.
 */
public class InstructorShouldAbleTOPreview extends Driver{

    @Test(priority = 1,enabled = true)
    public void matchTheFollowing(){
        try {
            AssessmentQuestionPage assessmentquestionPage=PageFactory.initElements(driver, AssessmentQuestionPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionsListPage questionsListPage=PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage=PageFactory.initElements(driver, QuestionEditPage.class);
            MatchTheFollowingPreviewPage matchthefollowing=PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);

                //tc row no 248
            String appendChar = "a";
            int index=248;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            new SignUp().teacher(appendChar,index);
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            new Navigator().navigateTo("assignment");//navigate to assignment
            new Assignment().create(index,"matchthefollowing");
            assignments.getButton_review().click();//Click on 'Review' Button
            assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(index);
            String questionID = assignments.getLabelValue_QuestionID().getText();
            String questionText =  assignments.getCreateAssignmentQuestionName().getText();
            String tloText = questionsListPage.getLabelValue_StandardTlo().getText();
            Assert.assertEquals(tloText,"STANDARD : 1.OA, 1.OA.A.1","TLO is not displayed properly");
            assignments.getRightArrow().click();//Click on Question's Right arrow
            if(!questionText.contains("Match the Following matchthefollowing_248")){
                Assert.fail("The particular question is not opened");
            }
            Assert.assertEquals(assessmentquestionPage.getLabelValue_QuestionID().getText(), questionID, "ID of the question is not displayed");
            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals("Owner:" + assessmentquestionPage.getLabelValue_Owner().getText().trim(), "Owner:You", "Owner of the assignment is not displayed");

            //Expected - 4 : Edit and delete option must be displayed
            Assert.assertEquals(assessmentquestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");
            Assert.assertEquals(assessmentquestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");
            Assert.assertEquals(assessmentquestionPage.getButton_Preview().getText(),"Preview","Preview button is not displayed");
            //Expected - 5. TLO and ELO should be displayed properly
            Assert.assertEquals(assessmentquestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");
            Assert.assertEquals(assessmentquestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");
            assessmentquestionPage.getButton_Edit().click();//click on edit
            Thread.sleep(2000);
            questionEditPage.getTextBox_QuestionEditField().clear();//Clear the question
            questionEditPage.getTextBox_QuestionEditField().sendKeys("new " + questiontext);//Type the new question
            Assert.assertEquals(questionEditPage.getTextBox_QuestionEditField().getText(), "new " + questiontext, "Instructor is not able to edit the question");
            questionEditPage.getButton_Save().click();//click on save
            assessmentquestionPage.getButton_Preview().click();//click on preview
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            String question=new TextFetch().textFetchByXpath("//label[@class='control-label']");
            if(!question.contains("new matchthefollowing_248")){
                Assert.fail("Question is not displaying");
            }
            String blankSpace=matchthefollowing.getBlankSpace().getText();
            if(!(blankSpace.equals("")|| blankSpace==null))
                Assert.fail("blank spaces are not displaying");
            String answeroption=matchthefollowing.getAnswerOption().getText();
            if(answeroption.equals("")|| answeroption==null){
                Assert.fail("text are not displaying");
            }
            String submit=matchthefollowing.getGet_submit().getText();
            if(!submit.contains("Submit")){
                Assert.fail("Submit button is not displaying");
            }
            WebElement source1=matchthefollowing.getGet_answerchoice1();
            WebElement destination1=matchthefollowing.getGet_blankspace1();
            Actions ac = new Actions(driver);
            ac.dragAndDrop(source1,destination1).build().perform();
            WebElement source2=matchthefollowing.getGet_answerchoice2();
            WebElement destination2=matchthefollowing.getGet_blankspace2();
            ac.dragAndDrop(source2,destination2).build().perform();
            WebElement source3=matchthefollowing.getGet_answerchoice3();
            WebElement destination3=matchthefollowing.getGet_blankspace3();
            ac.dragAndDrop(source3,destination3).build().perform();
            matchthefollowing.getGet_submit().click();
            String wronganswer=matchthefollowing.getWronganswer().getAttribute("class");
            System.out.println("srcvalue:"+wronganswer);
            if(!wronganswer.contains("stud-dnd-match-rhs ui-droppable wrong-answer"))
                Assert.fail("answer is wrong");

        }

           catch (Exception e){
                Assert.fail("Exception in test case matchTheFollowing of class InstructorAbleToClickOnPreview",e);
            }
        }
    @Test(priority = 2,enabled = true)
    public void dragAndDrop(){
        try{
            //tc row no 259
            AssessmentQuestionPage assessmentquestionPage=PageFactory.initElements(driver, AssessmentQuestionPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            QuestionEditPage questionEditPage=PageFactory.initElements(driver, QuestionEditPage.class);
            PreviewPage previewPage=PageFactory.initElements(driver,PreviewPage.class);
            String appendChar = "a";
            int index=259;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            new SignUp().teacher(appendChar,index);
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            new Navigator().navigateTo("assignment");//navigate to assignment
            new Assignment().create(index,"draganddrop");
            assignments.getButton_review().click();//Click on 'Review' Button
            assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(index);
            String questionID = assignments.getLabelValue_QuestionID().getText();
            String questionText =  assignments.getCreateAssignmentQuestionName().getText();
            assignments.getRightArrow().click();//Click on Question's Right arrow
            if(!questionText.contains("Drag and Drop draganddrop_259")){
                Assert.fail("The particular question is not opened");
            }
            Assert.assertEquals(assessmentquestionPage.getLabelValue_QuestionID().getText(), questionID, "ID of the question is not displayed");
            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals("Owner:" + assessmentquestionPage.getLabelValue_Owner().getText().trim(), "Owner:You", "Owner of the assignment is not displayed");

            Thread.sleep(2000);
           //Expected - 4 : Edit and delete option must be displayed.
            Assert.assertEquals(assessmentquestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");
            Assert.assertEquals(assessmentquestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");
            Assert.assertEquals(assessmentquestionPage.getButton_Preview().getText(),"Preview","Preview button is not displayed");
            assessmentquestionPage.getButton_Edit().click();
            questionEditPage.getTextBox_QuestionEditField().clear();//Clear the question
            questionEditPage.getTextBox_QuestionEditField().sendKeys("new " + questiontext);//Type the new question
            Assert.assertEquals(questionEditPage.getTextBox_QuestionEditField().getText(), "new " + questiontext, "Instructor is not able to edit the question");
            questionEditPage.getButton_Save().click();//click on save
            assessmentquestionPage.getButton_Preview().click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            String question = new TextFetch().textFetchByXpath("//label[@class='control-label']");
            if (!question.contains("new draganddrop_259")) {
                Assert.fail("Question is not displaying");
            }
            String blankSpace = previewPage.getBlankSpace().getText();
            if (!(blankSpace.equals("") || blankSpace == null))
                Assert.fail("blank spaces are not displaying");
            String answeroption = previewPage.getAnswerchoice().getText();
            if (answeroption.equals("") || answeroption == null) {
                Assert.fail("text are not displaying");
            }
            String submit = previewPage.getGet_submit().getText();
            if (!submit.contains("Submit")) {
                Assert.fail("Submit button is not displaying");
            }
            WebElement source1 = previewPage.getGet_answerchoice1();
            WebElement destination1 = previewPage.getBlankSpace();
            Actions ac = new Actions(driver);
            ac.dragAndDrop(source1, destination1).build().perform();
            WebElement source2 = previewPage.getGet_answerchoice2();

            ac.dragAndDrop(source2, destination1).build().perform();
            WebElement source3 = previewPage.getGet_answerchoice3();

            ac.dragAndDrop(source3, destination1).build().perform();
            previewPage.getGet_submit().click();//click on submit
            Thread.sleep(2000);
            String wronganswer = previewPage.getWrongAnswer().getAttribute("class");
            System.out.println("srcvalue:" + wronganswer);
            if (!wronganswer.contains("dnd-previewDropArea drop-container background-white ui-droppable wrong-answer"))
                Assert.fail("answer is wrong");
            Assert.assertEquals(previewPage.getHintLabel().getText().trim(), "Hint", "Hint Field is not visible after submit");
            //Assert.assertEquals(previewPage.getHintContent().getText().trim(), "True False Hint Text", "Hint Content Field is not visible after submit");
            Assert.assertEquals(previewPage.getSolutionLabel().getText().trim(), "Solution", "Solution Field is not visible after submit");
            //Assert.assertEquals(previewPage.getSolutionLabelContent().getText().trim(), "True False Solution Text", "Solution Content Field is not visible after submit");


        }
            catch (Exception E){
                Assert.fail("Exception in test case dragAndDrop of class InstructorAbleToClickOnPreview",E);
            }

    }
    @Test(priority = 3,enabled = true)
    public void essayTypeQuestion(){
        try{
            //tc row no 271
            AssessmentQuestionPage assessmentquestionPage=PageFactory.initElements(driver, AssessmentQuestionPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            QuestionEditPage questionEditPage=PageFactory.initElements(driver, QuestionEditPage.class);
            PreviewPage previewPage=PageFactory.initElements(driver,PreviewPage.class);
            String appendChar = "a";
            int index=271;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            new SignUp().teacher(appendChar,index);
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            new Navigator().navigateTo("assignment");//navigate to assignment
            new Assignment().create(index,"essay");
            assignments.getButton_review().click();//Click on 'Review' Button
            assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(index);
            String questionID = assignments.getLabelValue_QuestionID().getText();
            String questionText =  assignments.getCreateAssignmentQuestionName().getText();
            assignments.getRightArrow().click();//Click on Question's Right arrow
            if(!questionText.contains("Essay essaytypequestion_271")){
                Assert.fail("The particular question is not opened");
            }
            Assert.assertEquals(assessmentquestionPage.getLabelValue_QuestionID().getText(), questionID, "ID of the question is not displayed");
            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals("Owner:" + assessmentquestionPage.getLabelValue_Owner().getText().trim(), "Owner:You", "Owner of the assignment is not displayed");


            Thread.sleep(2000);
            //Expected - 4 : Edit and delete option must be displayed.
            Assert.assertEquals(assessmentquestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");
            Assert.assertEquals(assessmentquestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");
            Assert.assertEquals(assessmentquestionPage.getButton_Preview().getText(),"Preview","Preview button is not displayed");
            assessmentquestionPage.getButton_Edit().click();
            questionEditPage.getTextBox_QuestionEditField().clear();//Clear the question
            questionEditPage.getTextBox_QuestionEditField().sendKeys("new " + questiontext);//Type the new question
            Assert.assertEquals(questionEditPage.getTextBox_QuestionEditField().getText(),"new "+questiontext,"Instructor is not able to edit the question");
            questionEditPage.getButton_Save().click();//click on save
            assessmentquestionPage.getButton_Preview().click();//click on preview
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            String question=new TextFetch().textFetchByXpath("//label[@class='control-label']");
            if(!question.contains("new essaytypequestion_271")){
                Assert.fail("Question is not displaying");
            }
            String textInput=previewPage.getTextinputfield().getText();
            if(!(textInput.equals("")|| textInput==null)) {
                Assert.fail("Text input field is not displaying");
            }
            previewPage.getTextinputfield().sendKeys("textinput");//enter text
            previewPage.getGet_submit().click();//click on submit
            Assert.assertEquals(previewPage.getHintLabel().getText().trim(), "Hint", "Hint Field is not visible after submit");
            //Assert.assertEquals(previewPage.getHintContent().getText().trim(), "True False Hint Text", "Hint Content Field is not visible after submit");
            Assert.assertEquals(previewPage.getSolutionLabel().getText().trim(), "Solution", "Solution Field is not visible after submit");
            //Assert.assertEquals(previewPage.getSolutionLabelContent().getText().trim(), "True False Solution Text", "Solution Content Field is not visible after submit");
        }
        catch (Exception e){
            Assert.fail("Exception in test case essayTypeQuestion of class InstructorAbleToClickOnPreview",e);
        }

    }
    @Test(priority = 4,enabled = true)
    public void labelAnImageText(){
        try{
            //tc row no 283
            AssessmentQuestionPage assessmentquestionPage=PageFactory.initElements(driver, AssessmentQuestionPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            QuestionEditPage questionEditPage=PageFactory.initElements(driver, QuestionEditPage.class);
            PreviewPage previewPage=PageFactory.initElements(driver,PreviewPage.class);
            String appendChar = "a";
            int index=283;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            new SignUp().teacher(appendChar,index);
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            new Navigator().navigateTo("assignment");//navigate to assignment
            new Assignment().create(index,"labelanimagetext");
            assignments.getButton_review().click();//Click on 'Review' Button
            assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(index);
            String questionID = assignments.getLabelValue_QuestionID().getText();
            String questionText =  assignments.getCreateAssignmentQuestionName().getText();
            assignments.getRightArrow().click();//Click on Question's Right arrow
            if(!questionText.contains("Label An Image(Text) question textlabelanimagetext_283")){
                Assert.fail("The particular question is not opened");
            }
            Assert.assertEquals(assessmentquestionPage.getLabelValue_QuestionID().getText(), questionID, "ID of the question is not displayed");
            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals("Owner:" + assessmentquestionPage.getLabelValue_Owner().getText().trim(), "Owner:You", "Owner of the assignment is not displayed");

            Thread.sleep(2000);
            //Expected - 4 : Edit and delete option must be displayed.
            Assert.assertEquals(assessmentquestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");
            Assert.assertEquals(assessmentquestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");
            Assert.assertEquals(assessmentquestionPage.getButton_Preview().getText(),"Preview","Preview button is not displayed");
            assessmentquestionPage.getButton_Edit().click();//click on edit
            questionEditPage.getTextBox_QuestionEditField().clear();//Clear the question
            questionEditPage.getTextBox_QuestionEditField().sendKeys("new " + questiontext);//Type the new question
            Assert.assertEquals(questionEditPage.getTextBox_QuestionEditField().getText(),"new "+questiontext,"Instructor is not able to edit the question");
            questionEditPage.getButton_Save().click();//click on save
            assessmentquestionPage.getButton_Preview().click();//click on preview
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            String question=new TextFetch().textFetchByXpath("//label[@class='control-label']");
            if(!question.contains("new labelanimagetext_283")){
                Assert.fail("Question is not displaying");
            }
            String textInput=previewPage.getAnswerBox().getText();
            if(!(textInput.equals("")|| textInput==null)) {
                Assert.fail("Text input field is not displaying");
            }
            String submit=previewPage.getSubmitButton().getText();
            if(!submit.contains("Submit")){
                Assert.fail("Submit button is not displaying");
            }
            previewPage.getAnswerBox().sendKeys("text");
            previewPage.getSubmitButton().click();//click on submit
            String wronganswer=previewPage.getAnswer().getAttribute("class");
            System.out.println("srcvalue:"+wronganswer);
            if(!wronganswer.contains("wrong-answer"))
                Assert.fail("answer is wrong");
            Assert.assertEquals(previewPage.getHintLabel().getText().trim(), "Hint", "Hint Field is not visible after submit");
            //Assert.assertEquals(previewPage.getHintContent().getText().trim(), "True False Hint Text", "Hint Content Field is not visible after submit");
            Assert.assertEquals(previewPage.getSolutionLabel().getText().trim(), "Solution", "Solution Field is not visible after submit");
            //Assert.assertEquals(previewPage.getSolutionLabelContent().getText().trim(), "True False Solution Text", "Solution Content Field is not visible after submit");
        }
        catch (Exception e){
            Assert.fail("Exception in test case labelAnImageText of class InstructorAbleToClickOnPreview",e);
        }

    }
    @Test(priority = 5,enabled = true)
    public void labelAnImageDropDown(){
        try{
            //tc row no 295
            AssessmentQuestionPage assessmentquestionPage=PageFactory.initElements(driver, AssessmentQuestionPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            QuestionEditPage questionEditPage=PageFactory.initElements(driver, QuestionEditPage.class);
            PreviewPage previewPage=PageFactory.initElements(driver,PreviewPage.class);
            String appendChar = "a";
            int index=295;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            new SignUp().teacher(appendChar,index);
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            new Navigator().navigateTo("assignment");//navigate to assignment
            new Assignment().create(index,"labelanimagedropdown");
            assignments.getButton_review().click();//Click on 'Review' Button
            assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(index);
            String questionID = assignments.getLabelValue_QuestionID().getText();
            String questionText =  assignments.getCreateAssignmentQuestionName().getText();
            assignments.getRightArrow().click();//Click on Question's Right arrow
            if(!questionText.contains("Label An Image(Dropdown) question textlabelanimagedropdown_295")){
                Assert.fail("The particular question is not opened");
            }
            Assert.assertEquals(assessmentquestionPage.getLabelValue_QuestionID().getText(), questionID, "ID of the question is not displayed");
            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals("Owner:" + assessmentquestionPage.getLabelValue_Owner().getText().trim(), "Owner:You", "Owner of the assignment is not displayed");

            Thread.sleep(2000);
            //Expected - 4 : Edit and delete option must be displayed.
            Assert.assertEquals(assessmentquestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");
            Assert.assertEquals(assessmentquestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");
            Assert.assertEquals(assessmentquestionPage.getButton_Preview().getText(),"Preview","Preview button is not displayed");
            assessmentquestionPage.getButton_Edit().click();//click on edit
            questionEditPage.getTextBox_QuestionEditField().clear();//Clear the question
            questionEditPage.getTextBox_QuestionEditField().sendKeys("new " + questiontext);//Type the new question
            Assert.assertEquals(questionEditPage.getTextBox_QuestionEditField().getText(), "new " + questiontext, "Instructor is not able to edit the question");
            questionEditPage.getButton_Save().click();//click on save
            assessmentquestionPage.getButton_Preview().click();//click on preview
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            String question=new TextFetch().textFetchByXpath("//label[@class='control-label']");
            if(!question.contains("new labelanimagedropdown_295")){
                Assert.fail("Question is not displaying");
            }
            Select sel = new Select(driver.findElement(By.id("answer-value")));
            sel.selectByVisibleText("Answer 2");

            previewPage.getSubmitButton().click();
            String wronganswer=previewPage.getImageDropDownAnswer().getAttribute("id");
            System.out.println("srcvalue:"+wronganswer);
            if(!wronganswer.contains("stud-view-label-drpdwn-answerText")) {
                Assert.fail("answer is wrong");
            }
            Assert.assertEquals(previewPage.getHintLabel().getText().trim(), "Hint", "Hint Field is not visible after submit");
            //Assert.assertEquals(previewPage.getHintContent().getText().trim(), "True False Hint Text", "Hint Content Field is not visible after submit");
            Assert.assertEquals(previewPage.getSolutionLabel().getText().trim(), "Solution", "Solution Field is not visible after submit");
            //Assert.assertEquals(previewPage.getSolutionLabelContent().getText().trim(), "True False Solution Text", "Solution Content Field is not visible after submit");

        }
        catch (Exception e){
            Assert.fail("Exception in test case labelAnImageDropDown of class InstructorAbleToClickOnPreview", e);
        }
    }
    @Test(priority = 6,enabled = true)
    public void resequenceTypeQuestion(){
        try{
            //tc row no 307
            AssessmentQuestionPage assessmentquestionPage=PageFactory.initElements(driver, AssessmentQuestionPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            QuestionEditPage questionEditPage=PageFactory.initElements(driver, QuestionEditPage.class);
            PreviewPage previewPage=PageFactory.initElements(driver,PreviewPage.class);
            String appendChar = "a";
            int index=307;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            new SignUp().teacher(appendChar,index);
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            new Assignment().create(index,"resequence");
            assignments.getButton_review().click();//Click on 'Review' Button
            assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(index);
            String questionID = assignments.getLabelValue_QuestionID().getText();
            String questionText =  assignments.getCreateAssignmentQuestionName().getText();
            assignments.getRightArrow().click();//Click on Question's Right arrow
            if(!questionText.contains("Resequence resequencetypequestion_307")){
                Assert.fail("The particular question is not opened");
            }
            Assert.assertEquals(assessmentquestionPage.getLabelValue_QuestionID().getText(), questionID, "ID of the question is not displayed");
            Assert.assertEquals("Owner:" + assessmentquestionPage.getLabelValue_Owner().getText().trim(), "Owner:You", "Owner of the assignment is not displayed");

            Thread.sleep(2000);
            Assert.assertEquals(assessmentquestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");
            Assert.assertEquals(assessmentquestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");
            Assert.assertEquals(assessmentquestionPage.getButton_Preview().getText(),"Preview","Preview button is not displayed");
            assessmentquestionPage.getButton_Edit().click();//click on edit
            questionEditPage.getTextBox_QuestionEditField().clear();//Clear the question
            questionEditPage.getTextBox_QuestionEditField().sendKeys("new " + questiontext);//Type the new question
            Assert.assertEquals(questionEditPage.getTextBox_QuestionEditField().getText(),"new "+questiontext,"Instructor is not able to edit the question");
            questionEditPage.getButton_Save().click();//click on save
            assessmentquestionPage.getButton_Preview().click();//click on preview
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            String question=new TextFetch().textFetchByXpath("//label[@class='control-label']");
            if(!question.contains("new resequencetypequestion_307")){
                Assert.fail("Question is not displaying");
            }
            String answeroption=previewPage.getAnswerOptionResequence().getText();
            if(answeroption.equals("")|| answeroption==null){
                Assert.fail("text are not displaying");
            }
            String submit=previewPage.getSubmitButton().getText();
            if(!submit.contains("Submit")){
                Assert.fail("Submit button is not displaying");
            }
            WebElement source1=previewPage.getAnswerOptionResequence1();
            WebElement destination1=previewPage.getAnswerOptionResequence2();
            Actions ac = new Actions(driver);
            ac.dragAndDrop(source1,destination1).build().perform();

            previewPage.getSubmitButton().click();//click on submit
            String wronganswer=previewPage.getWrongAnswerResequence().getAttribute("class");
            if(!wronganswer.contains("student-resequence-answer-choice hover-border wrongAnswerChoice"))
                Assert.fail("answer is wrong");
            Assert.assertEquals(previewPage.getHintLabel().getText().trim(), "Hint", "Hint Field is not visible after submit");
            Assert.assertEquals(previewPage.getHintContent().getText().trim(), "True False Hint Text", "Hint Content Field is not visible after submit");
            Assert.assertEquals(previewPage.getSolutionLabel().getText().trim(), "Solution", "Solution Field is not visible after submit");
            Assert.assertEquals(previewPage.getSolutionLabelContent().getText().trim(), "True False Solution Text", "Solution Content Field is not visible after submit");
        }
        catch (Exception e){
            Assert.fail("Exception in test case labelAnImageDropDown of class InstructorAbleToClickOnPreview",e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void clozeMatrixTypeQuestion() {
        try {
            //tc row no 319
            AssessmentQuestionPage assessmentquestionPage = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            QuestionEditPage questionEditPage=PageFactory.initElements(driver, QuestionEditPage.class);
            PreviewPage previewPage=PageFactory.initElements(driver,PreviewPage.class);
            String appendChar = "a";
            int index = 319;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            new SignUp().teacher(appendChar, index);
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            new Assignment().create(index, "clozematrix");
            assignments.getButton_review().click();//Click on 'Review' Button
            assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(index);
            String questionID = assignments.getLabelValue_QuestionID().getText();
            String questionText = assignments.getCreateAssignmentQuestionName().getText();
            assignments.getRightArrow().click();//Click on Question's Right arrow
            if(!questionText.contains("Cloze Matrix clozematrixtypequestion_319")){
                Assert.fail("The particular question is not opened");
            }
            Assert.assertEquals(assessmentquestionPage.getLabelValue_QuestionID().getText(), questionID, "ID of the question is not displayed");
            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals("Owner:" + assessmentquestionPage.getLabelValue_Owner().getText().trim(), "Owner:You", "Owner of the assignment is not displayed");
            Thread.sleep(2000);
            //Expected - 4 : Edit and delete option must be displayed.
            Assert.assertEquals(assessmentquestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");
            Assert.assertEquals(assessmentquestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Assert.assertEquals(assessmentquestionPage.getButton_Preview(),"Preview","Preview button is not displayed");
            assessmentquestionPage.getButton_Edit().click();//click on edit
            questionEditPage.getTextBox_QuestionEditField().clear();//Clear the question
            questionEditPage.getTextBox_QuestionEditField().sendKeys("new " + questiontext);//Type the new question
            Assert.assertEquals(questionEditPage.getTextBox_QuestionEditField().getText(),"new "+questiontext,"Instructor is not able to edit the question");
            questionEditPage.getButton_Save().click();//click on save
            assessmentquestionPage.getButton_Preview().click();//click on preview
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            String question=new TextFetch().textFetchByXpath("//label[@class='control-label']");

            if(!question.contains("new clozematrixtypequestion_319")){
                Assert.fail("Question is not displaying");
            }
            String submit=previewPage.getSubmitButton().getText();
            if(!submit.contains("Submit")){
                Assert.fail("Submit button is not displaying");
            }
            int matrixSize=driver.findElements(By.id("matrix-question-table-preview")).size();
            if(matrixSize != 1){
                Assert.fail("Matrix is not displayed with blank text field for answering");
            }


            //String blankSpace=previewPage.getMatrixBlankSpace().getText();
            String blankSpace=new TextFetch().textFetchByXpath("//input[@type='text']");
            if(!(blankSpace.equals("")|| blankSpace==null)) {
                Assert.fail("blank spaces is not displaying");
            }
            new TextSend().textsendbycssSelector("5","input[type='text']");
            //previewPage.getMatrixBlankSpace().sendKeys("5");
            previewPage.getSubmitButton().click();//click on submit
            Thread.sleep(2000);
            String wronganswer=driver.findElement(By.cssSelector("div[class='matrix-box-preview matrix-ans-redit wrong-answer']")).getAttribute("class");
            //String wronganswer=previewPage.getMatrixWrongAnswer().getAttribute("class");
            System.out.println("srcvalue:"+wronganswer);
            if(!wronganswer.contains("matrix-box-preview matrix-ans-redit wrong-answer"))
                Assert.fail("answer is wrong");
        }
        catch (Exception e){
            Assert.fail("Exception in test case labelAnImageDropDown of class InstructorAbleToClickOnPreview",e);
        }
    }
     @Test(priority = 8,enabled = true)
     public void graphPlotterTypeQuestion(){
        try{
            //tc row no 331
            AssessmentQuestionPage assessmentquestionPage=PageFactory.initElements(driver, AssessmentQuestionPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            QuestionEditPage questionEditPage=PageFactory.initElements(driver, QuestionEditPage.class);
            PreviewPage previewPage=PageFactory.initElements(driver,PreviewPage.class);
            String appendChar = "a";
            int index=331;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            new SignUp().teacher(appendChar,index);
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            new Navigator().navigateTo("assignment");//navigate to assignment
            new Assignment().create(index,"graphplotter");
            assignments.getButton_review().click();//Click on 'Review' Button
            assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(index);
            String questionID = assignments.getLabelValue_QuestionID().getText();
            String questionText =  assignments.getCreateAssignmentQuestionName().getText();
            assignments.getRightArrow().click();//Click on Question's Right arrow
            if(!questionText.contains("Graph Plotter clozematrixtypequestion_331")){
                Assert.fail("The particular question is not opened");
            }
            Assert.assertEquals(assessmentquestionPage.getLabelValue_QuestionID().getText(), questionID, "ID of the question is not displayed");//verify question id
            Assert.assertEquals("Owner:" + assessmentquestionPage.getLabelValue_Owner().getText().trim(), "Owner:You", "Owner of the assignment is not displayed");//verify owner
            Thread.sleep(2000);
            Assert.assertEquals(assessmentquestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");//verify edit
            Assert.assertEquals(assessmentquestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");//verify delete
            Assert.assertEquals(assessmentquestionPage.getButton_Preview().getText(),"Preview","Preview button is not displayed");//verify preview

            assessmentquestionPage.getButton_Edit().click();//click on edit
            questionEditPage.getTextBox_QuestionEditField().clear();//Clear the question
            questionEditPage.getTextBox_QuestionEditField().sendKeys("new " + questiontext);//Type the new question
            Assert.assertEquals(questionEditPage.getTextBox_QuestionEditField().getText(),"new "+questiontext,"Instructor is not able to edit the question");
            questionEditPage.getButton_Save().click();//click on save
            assessmentquestionPage.getButton_Preview().click();//click on preview
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            String question=new TextFetch().textFetchByXpath("//label[@class='control-label']");
            if(!question.contains("new clozematrixtypequestion_331")){
                Assert.fail("Question is not displaying");
            }
            String submit=previewPage.getSubmitButton().getText();//verify submit
            if(!submit.contains("Submit")){
                Assert.fail("Submit button is not displaying");
            }
        }
        catch (Exception e){
            Assert.fail("Exception in test case graphPlotterTypeQuestion of class InstructorAbleToClickOnPreview",e);
        }
    }
    @Test(priority = 9,enabled = true)
    public void clozeFormulaTypeQuestion(){
        try{
            //tc row no 343
            AssessmentQuestionPage assessmentquestionPage=PageFactory.initElements(driver, AssessmentQuestionPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            QuestionEditPage questionEditPage=PageFactory.initElements(driver, QuestionEditPage.class);
            PreviewPage previewPage=PageFactory.initElements(driver,PreviewPage.class);
            String appendChar = "a";
            int index=343;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            new SignUp().teacher(appendChar,index);
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            new Navigator().navigateTo("assignment");//navigate to assignment
            new Assignment().create(index,"clozeformula");
            assignments.getButton_review().click();//Click on 'Review' Button
            assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(index);
            String questionID = assignments.getLabelValue_QuestionID().getText();
            String questionText =  assignments.getCreateAssignmentQuestionName().getText();
            assignments.getRightArrow().click();//Click on Question's Right arrow
            if(!questionText.contains("Cloze Formula")){
                Assert.fail("The particular question is not opened");
            }
            Assert.assertEquals(assessmentquestionPage.getLabelValue_QuestionID().getText(), questionID, "ID of the question is not displayed");//verify question id
            Assert.assertEquals("Owner:" + assessmentquestionPage.getLabelValue_Owner().getText().trim(), "Owner:You", "Owner of the assignment is not displayed");//verify owner
            Thread.sleep(2000);
            Assert.assertEquals(assessmentquestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");//verify edit button
            Assert.assertEquals(assessmentquestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");//verify delete
            Assert.assertEquals(assessmentquestionPage.getButton_Preview().getText(),"Preview","Preview button is not displayed");//verify preview
            Assert.assertEquals(assessmentquestionPage.getLabel_tlo().getText(),"1.OA - Operations & Algebraic Thinking","TLO is not displayed properly");//verify TLO
            Assert.assertEquals(assessmentquestionPage.getLabel_elo().getText(),"1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to","ELO is not displayed properly");//verify ELO
            assessmentquestionPage.getButton_Edit().click();// click on edit
            questionEditPage.getTextBox_QuestionEditField().clear();//Clear the question
            questionEditPage.getTextBox_QuestionEditField().sendKeys("new " + questiontext);//Type the new question
            Assert.assertEquals(questionEditPage.getTextBox_QuestionEditField().getText(),"new "+questiontext,"Instructor is not able to edit the question");
            questionEditPage.getButton_Save().click();//click on save
            assessmentquestionPage.getButton_Preview().click();//click on preview
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            String question=new TextFetch().textFetchByXpath("//label[@class='control-label']");
            if(!question.contains("new clozematrixtypequestion_343")){
                Assert.fail("Question is not displaying");
            }
           /* String blankSpace=previewPage.getBlankSpaceCloze().getText();
            System.out.println(">>>>>>>>>>"+blankSpace);
            if(!(blankSpace.equals("")|| blankSpace==null)) {
                Assert.fail("blank spaces are not displaying");
            }*/
            String answeroption=previewPage.getAnswerChoice().getText();//verify the answeroption
            if(answeroption.equals("")|| answeroption==null){
                Assert.fail("text are not displaying");
            }
            String submit=previewPage.getGet_submit().getText();//verify the submit button
            if(!submit.contains("Submit")){
                Assert.fail("Submit button is not displaying");
            }
            WebElement source1=previewPage.getAnswerChoice1_clozeFormula();
            WebElement destination1=previewPage.getBlankSpace1();
            Actions ac = new Actions(driver);
            ac.dragAndDrop(source1,destination1).build().perform();
            WebElement source2=previewPage.getAnswerChoice2_clozeFormula();
            WebElement destination2=previewPage.getBlankSpace2();
            ac.dragAndDrop(source2,destination2).build().perform();
            WebElement source3=previewPage.getAnswerChoice3_clozeFormula();
            WebElement destination3=previewPage.getBlankSpace3();
            ac.dragAndDrop(source3,destination3).build().perform();
            previewPage.getGet_submit().click();      //click on submit
            String wronganswer=previewPage.getWrongAnswerClozeFormula().getAttribute("id");
            System.out.println("srcvalue:"+wronganswer);
            if(!wronganswer.contains("eval-img"))
                Assert.fail("answer is wrong");
            Assert.assertEquals(previewPage.getHintLabel().getText().trim(), "Hint", "Hint Field is not visible after submit");
            //Assert.assertEquals(previewPage.getHintContent().getText().trim(), "True False Hint Text", "Hint Content Field is not visible after submit");
            Assert.assertEquals(previewPage.getSolutionLabel().getText().trim(), "Solution", "Solution Field is not visible after submit");
            //Assert.assertEquals(previewPage.getSolutionLabelContent().getText().trim(), "True False Solution Text", "Solution Content Field is not visible after submit");

        }
        catch (Exception e){
            Assert.fail("Exception in test case clozeFormulaTypeQuestion of class InstructorAbleToClickOnPreview",e);
        }
    }



}

