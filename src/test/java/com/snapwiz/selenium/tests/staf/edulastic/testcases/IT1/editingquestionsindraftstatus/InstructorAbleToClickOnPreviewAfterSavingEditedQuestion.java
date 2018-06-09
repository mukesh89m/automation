package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.editingquestionsindraftstatus;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentLibrary;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview.PreviewPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Mukesh on 12/18/14.
 */
public class InstructorAbleToClickOnPreviewAfterSavingEditedQuestion extends Driver {
    @Test(priority = 1,enabled = true)
    public void instructorAbleToClickOnPreviewAfterSavingEditedQuestion()
    {
        Assignments locator=PageFactory.initElements(driver, Assignments.class);
        AssessmentQuestionPage  aqpLocator=PageFactory.initElements(driver,AssessmentQuestionPage.class);
        AssessmentQuestionsListPage aqlpLocator=PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
        QuestionEditPage qepLocator=PageFactory.initElements(driver,QuestionEditPage.class);
        PreviewPage ppLocator=PageFactory.initElements(driver,PreviewPage.class);
        String expected=null;
        String actual=null;

        try {
            String appendChar = "b";
            int index=152;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Assignment().create(index,"truefalse");
            locator.getButton_review().click();//Click on 'Review' Button
            locator.getButton_saveForLater().click();//Click on 'Save for later' button

            new Assignment().selectAssessment(index);
            String owner = locator.getLabelValue_Ownwer().getText();
            String questionID = locator.getLabelValue_QuestionID().getText();
            String questionText =  locator.getCreateAssignmentQuestionName().getText();
            System.out.println("Question text:"+questionText);
            locator.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            //expected="Q1:  True False"+" "+questiontext+"" ;
            if(!questionText.contains(aqpLocator.getLabel_questionName().getText()))
                Assert.fail("The particular question is not opened");

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(aqpLocator.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals("Owner:"+aqpLocator.getLabelValue_Owner().getText().trim(),"Owner:You","Owner of the assignment is not displayed");

            //Expected - 4 : Edit and delete option must be displayed
            Assert.assertEquals(aqpLocator.getButton_Edit().getText().trim(),"Edit","Edit Option is not displayed");
            Assert.assertEquals(aqpLocator.getButton_Delete().getText().trim(),"Delete","Delete Option is not displayed");

            //Expected 5. Preview button must be displayed"
            Assert.assertEquals(aqpLocator.getButton_Preview().getText().trim(),"Preview","Preview button is not displayed");

            //Expected "6. ""Shuffle answer"" check box must be displayed as set by the instructor"
          /*  Shuffle question removed
          boolean shuffleIsDisplayed=aqpLocator.getCheckBox_shuffleAnswerChoices().isDisplayed();
            System.out.println("shuffle is Displayed:"+shuffleIsDisplayed);
            if(shuffleIsDisplayed==false)
                Assert.fail("Shuffle answer check box is not displayed");*/

            //Expected "7. TLO and ELO should be displayed properly
            String tlo=aqpLocator.getLabel_tlo().getText();
            String elo=aqpLocator.getLabel_elo().getText();
            expected="1.OA - Operations & Algebraic Thinking";
            if(!tlo.contains(expected))
                Assert.fail("Tlo is not displayed properly");
            expected="1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to";
            if(!elo.contains(expected))
                Assert.fail("Elo is not displayed Properly");

            //6. Click on "Edit option"
            aqpLocator.getButton_Edit().click();//click on the edit button
            //Expected :8.Instructor must be taken to the question edit mode and he should be able to edit the question now
            qepLocator.getTextBox_QuestionEditField().clear();
            qepLocator.getTextBox_QuestionEditField().sendKeys("Edited true false question152");
            qepLocator.getButton_Save().click();//click on the save button

            aqpLocator.getButton_Preview().click();//click on the review button
            Thread.sleep(10000);

            String parentWindow=driver.getWindowHandle();
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);//switch to the Preview Window
            }
            System.out.println("current url:"+driver.getCurrentUrl());
            //Expected:A pop up should open that shows a preview of the question as a student would view
            if(!ppLocator.getPreviewPageTitle().getText().equals("PREVIEW"))
                Assert.fail("A pop up is not Displayed");

            //Expected10. Question should be displayed
            expected="Edited true false question152";
            if(!ppLocator.getQuestionText().getText().contains(expected))
                Assert.fail("Question is not displayed in the review Page");
            //Expected 11. Answer options should be displayed
            ppLocator.getAnswerOption().get(0).click();//click on the A answer option

            //Expected 12. A submit button should be displayed
            Assert.assertEquals(ppLocator.getSubmitButton().getText(),"Submit","Submit button is not displayed");
            //11. Click on ""submit"" button"
            ppLocator.getSubmitButton().click();//click on the submit button

            String backGroundImage=ppLocator.getCorrectTickMark().getCssValue("background-image");
            System.out.println("Correct Tick Mark:"+backGroundImage);
            if(!backGroundImage.contains("/questions/images/correct-icon.png"))
                Assert.fail("The Feedback (right or wrong) is not displayed");

            Assert.assertEquals(ppLocator.getHintLabel().getText().trim(),"Hint","Hint Field is not visible after submit");
            Thread.sleep(5000);
            Assert.assertEquals(ppLocator.getHintContent().getText().trim(),"True False Hint Text","Hint Content Field is not visible after submit");
            Assert.assertEquals(ppLocator.getSolutionLabel().getText().trim(), "Solution", "Solution Field is not visible after submit");
            Assert.assertEquals(ppLocator.getSolutionLabelContent().getText().trim(),"True False Solution Text","Solution Content Field is not visible after submit");


        } catch (Exception e) {
            Assert.fail("Exception in testcase instructorAbleToClickOnPreviewAfterSavingEditedQuestion of class InstructorAbleToClickOnPreviewAfterSavingEditedQuestion", e);
        }

    }
    @Test(priority = 2,enabled = true)
    public void instructorAbleToClickOnPreviewAfterSavingEditedQuestionForMultipleChoice() {
        Assignments locator = PageFactory.initElements(driver, Assignments.class);
        AssessmentQuestionPage aqpLocator = PageFactory.initElements(driver, AssessmentQuestionPage.class);
        AssessmentQuestionsListPage aqlpLocator = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
        QuestionEditPage qepLocator = PageFactory.initElements(driver, QuestionEditPage.class);
        PreviewPage ppLocator = PageFactory.initElements(driver, PreviewPage.class);
        String expected = null;
        String actual = null;

        try {
            String appendChar = "b";
            int index = 164;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            new Assignment().create(index, "multiplechoice");
            locator.getButton_review().click();//Click on 'Review' Button
            locator.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(index);
            String owner = locator.getLabelValue_Ownwer().getText();
            String questionID = locator.getLabelValue_QuestionID().getText();
            String questionText = locator.getCreateAssignmentQuestionName().getText();
            System.out.println("Question text:" + questionText);
            locator.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if (!questionText.contains(aqpLocator.getLabel_questionName().getText()))
                Assert.fail("The particular question is not opened");

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(aqpLocator.getLabelValue_QuestionID().getText(), questionID, "ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals("Owner:" + aqpLocator.getLabelValue_Owner().getText().trim(), "Owner:You", "Owner of the assignment is not displayed");

            //Expected - 4 : Edit and delete option must be displayed
            Assert.assertEquals(aqpLocator.getButton_Edit().getText().trim(), "Edit", "Edit Option is not displayed");
            Assert.assertEquals(aqpLocator.getButton_Delete().getText().trim(), "Delete", "Delete Option is not displayed");

            //Expected 5. Preview button must be displayed"
            Assert.assertEquals(aqpLocator.getButton_Preview().getText().trim(), "Preview", "Preview button is not displayed");

            //Expected "6. ""Shuffle answer"" check box must be displayed as set by the instructor"
           /* shuffle question removed
           boolean shuffleIsDisplayed = aqpLocator.getCheckBox_shuffleAnswerChoices().isDisplayed();
            System.out.println("shuffle is Displayed:" + shuffleIsDisplayed);
            if (shuffleIsDisplayed == false)
                Assert.fail("Shuffle answer check box is not displayed");*/

            //Expected "7. TLO and ELO should be displayed properly
            String tlo = aqpLocator.getLabel_tlo().getText();
            String elo = aqpLocator.getLabel_elo().getText();
            expected = "1.OA - Operations & Algebraic Thinking";
            if (!tlo.contains(expected))
                Assert.fail("Tlo is not displayed properly");
            expected = "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to";
            if (!elo.contains(expected))
                Assert.fail("Elo is not displayed Properly");

            //6. Click on "Edit option"
            aqpLocator.getButton_Edit().click();//click on the edit button
            //Expected :8.Instructor must be taken to the question edit mode and he should be able to edit the question now
            qepLocator.getTextBox_MultipleChoiceQuestionEditField().clear();
            qepLocator.getTextBox_MultipleChoiceQuestionEditField().sendKeys("Edited multiple choice question164");
            qepLocator.getButton_Save().click();//click on the save button

            aqpLocator.getButton_Preview().click();//click on the review button
            Thread.sleep(10000);

            String parentWindow = driver.getWindowHandle();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);//switch to the Preview Window
            }
            System.out.println("current url:" + driver.getCurrentUrl());
            //Expected:A pop up should open that shows a preview of the question as a student would view
            if (!ppLocator.getPreviewPageTitle().getText().equals("PREVIEW"))
                Assert.fail("A pop up is not Displayed");

            //Expected10. Question should be displayed
            expected = "Edited multiple choice question164";
            if (!ppLocator.getQuestionText().getText().contains(expected))
                Assert.fail("Question is not displayed in the review Page");
            //Expected 11. Answer options should be displayed
            ppLocator.getMultipleChoiceAnswerOption().get(0).click();//click on the A answer option

            //Expected 12. A submit button should be displayed
            Assert.assertEquals(ppLocator.getSubmitButton().getText(), "Submit", "Submit button is not displayed");
            //11. Click on ""submit"" button"
            ppLocator.getSubmitButton().click();//click on the submit button

            String backGroundImage = ppLocator.getMultipleSelectionCorrectTickMark().getCssValue("background-image");
            System.out.println("Correct Tick Mark:" + backGroundImage);
            if (!backGroundImage.contains("/questions/images/correct-icon.png"))
                Assert.fail("The Feedback (right or wrong) is not displayed");

            Assert.assertEquals(ppLocator.getHintLabel().getText().trim(), "Hint", "Hint Field is not visible after submit");
           /* String content=driver.findElement(By.id("cms-question-preview-question-hint-wrapper")).getAttribute("innerHTML");
            System.out.println("Content:"+content);*/
            Thread.sleep(5000);
            Assert.assertEquals(ppLocator.getHintContent().getText().trim(),"True False Hint Text","Hint Content Field is not visible after submit");
            Assert.assertEquals(ppLocator.getSolutionLabel().getText().trim(), "Solution", "Solution Field is not visible after submit");
            Assert.assertEquals(ppLocator.getSolutionLabelContent().getText().trim(),"True False Solution Text","Solution Content Field is not visible after submit");
            Assert.assertEquals(ppLocator.getQuestionPoint_previewPage().getAttribute("value"), "1", "Points is not visible in the preview page after submit");


        } catch (Exception e) {
            Assert.fail("Exception in testcase instructorAbleToClickOnPreviewAfterSavingEditedQuestionForMultipleChoice of class InstructorAbleToClickOnPreviewAfterSavingEditedQuestion", e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void instructorAbleToClickOnPreviewAfterSavingEditedQuestionForMultipleSelect()
    {
        Assignments locator=PageFactory.initElements(driver, Assignments.class);
        AssessmentQuestionPage  aqpLocator=PageFactory.initElements(driver,AssessmentQuestionPage.class);
        AssessmentQuestionsListPage aqlpLocator=PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
        QuestionEditPage qepLocator=PageFactory.initElements(driver,QuestionEditPage.class);
        PreviewPage ppLocator=PageFactory.initElements(driver,PreviewPage.class);
        String expected=null;
        String actual=null;

        try {
            String appendChar = "a";
            int index=176;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Assignment().create(index,"multipleselection");
            locator.getButton_review().click();//Click on 'Review' Button
            locator.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(index);
            String owner = locator.getLabelValue_Ownwer().getText();
            String questionID = locator.getLabelValue_QuestionID().getText();
            String questionText =  locator.getCreateAssignmentQuestionName().getText();
            System.out.println("Question text:"+questionText);
            locator.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            //expected="Q1:  True False"+" "+questiontext+"" ;
            if(!questionText.contains(aqpLocator.getLabel_questionName().getText()))
                Assert.fail("The particular question is not opened");

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(aqpLocator.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals("Owner:"+aqpLocator.getLabelValue_Owner().getText().trim(),"Owner:You","Owner of the assignment is not displayed");

            //Expected - 4 : Edit and delete option must be displayed
            Assert.assertEquals(aqpLocator.getButton_Edit().getText().trim(),"Edit","Edit Option is not displayed");
            Assert.assertEquals(aqpLocator.getButton_Delete().getText().trim(),"Delete","Delete Option is not displayed");

            //Expected 5. Preview button must be displayed"
            Assert.assertEquals(aqpLocator.getButton_Preview().getText().trim(),"Preview","Preview button is not displayed");

            //Expected "6. ""Shuffle answer"" check box must be displayed as set by the instructor"
          /* Shuffle question removed
         boolean shuffleIsDisplayed=aqpLocator.getCheckBox_shuffleAnswerChoices().isDisplayed();
            System.out.println("shuffle is Displayed:"+shuffleIsDisplayed);
            if(shuffleIsDisplayed==false)
                Assert.fail("Shuffle answer check box is not displayed");*/

            //Expected "7. TLO and ELO should be displayed properly
            String tlo=aqpLocator.getLabel_tlo().getText();
            String elo=aqpLocator.getLabel_elo().getText();
            expected="1.OA - Operations & Algebraic Thinking";
            if(!tlo.contains(expected))
                Assert.fail("Tlo is not displayed properly");
            expected="1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to";
            if(!elo.contains(expected))
                Assert.fail("Elo is not displayed Properly");

            //6. Click on "Edit option"
            aqpLocator.getButton_Edit().click();//click on the edit button
            //Expected :8.Instructor must be taken to the question edit mode and he should be able to edit the question now
            qepLocator.getTextBox_MultipleSelectionEditField().clear();
            qepLocator.getTextBox_MultipleSelectionEditField().sendKeys("Edited multiple selection question172");
            qepLocator.getButton_Save().click();//click on the save button

            aqpLocator.getButton_Preview().click();//click on the review button
            Thread.sleep(10000);

            String parentWindow=driver.getWindowHandle();
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);//switch to the Preview Window
            }
            System.out.println("current url:"+driver.getCurrentUrl());
            //Expected:A pop up should open that shows a preview of the question as a student would view
            if(!ppLocator.getPreviewPageTitle().getText().equals("PREVIEW"))
                Assert.fail("A pop up is not Displayed");

            //Expected10. Question should be displayed
            expected="Edited multiple selection question172";
            if(!ppLocator.getQuestionText().getText().contains(expected))
                Assert.fail("Question is not displayed in the review Page");
            //Expected 11. Answer options should be displayed
            ppLocator.getMultipleSelectionAnswerOption().get(0).click();//click on the A answer option

            //Expected 12. A submit button should be displayed
            Assert.assertEquals(ppLocator.getSubmitButton().getText(),"Submit","Submit button is not displayed");
            //11. Click on ""submit"" button"
            ppLocator.getSubmitButton().click();//click on the submit button

            String backGroundImage=ppLocator.getMultipleSelectionCorrectTickMark().getCssValue("background-image");
            System.out.println("Correct Tick Mark:"+backGroundImage);
            if(!backGroundImage.contains("/questions/images/correct-icon.png"))
                Assert.fail("The Feedback (right or wrong) is not displayed");

            Assert.assertEquals(ppLocator.getHintLabel().getText().trim(),"Hint","Hint Field is not visible after submit");
            Thread.sleep(5000);
            Assert.assertEquals(ppLocator.getHintContent().getText().trim(),"True False Hint Text","Hint Content Field is not visible after submit");
            Assert.assertEquals(ppLocator.getSolutionLabel().getText().trim(), "Solution", "Solution Field is not visible after submit");
            Assert.assertEquals(ppLocator.getSolutionLabelContent().getText().trim(),"True False Solution Text","Solution Content Field is not visible after submit");
            Assert.assertEquals(ppLocator.getQuestionPoint_previewPage().getAttribute("value"),"1","Points is not visible in the preview page after submit");


        } catch (Exception e) {
            Assert.fail("Exception in testcase instructorAbleToClickOnPreviewAfterSavingEditedQuestionForMultipleSelect of class InstructorAbleToClickOnPreviewAfterSavingEditedQuestion", e);
        }

    }


    @Test(priority = 4,enabled = true)
    public void instructorAbleToClickOnPreviewAfterSavingEditedQuestionForTextEntry()
    {
        Assignments locator=PageFactory.initElements(driver, Assignments.class);
        AssessmentQuestionPage  aqpLocator=PageFactory.initElements(driver,AssessmentQuestionPage.class);
        AssessmentQuestionsListPage aqlpLocator=PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
        QuestionEditPage qepLocator=PageFactory.initElements(driver,QuestionEditPage.class);
        PreviewPage ppLocator=PageFactory.initElements(driver,PreviewPage.class);
        String expected=null;
        String actual=null;

        try {
            String appendChar = "a";
            int index=188;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Assignment().create(index,"textentry");
            locator.getButton_review().click();//Click on 'Review' Button
            locator.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(index);
            String owner = locator.getLabelValue_Ownwer().getText();
            String questionID = locator.getLabelValue_QuestionID().getText();
            String questionText =  locator.getCreateAssignmentQuestionName().getText();
            System.out.println("Question text:"+questionText);
            locator.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            //expected="Q1:  True False"+" "+questiontext+"" ;
            if(!questionText.contains(aqpLocator.getLabel_questionName().getText()))
                Assert.fail("The particular question is not opened");

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(aqpLocator.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals("Owner:"+aqpLocator.getLabelValue_Owner().getText().trim(),"Owner:You","Owner of the assignment is not displayed");

            //Expected - 4 : Edit and delete option must be displayed
            Assert.assertEquals(aqpLocator.getButton_Edit().getText().trim(),"Edit","Edit Option is not displayed");
            Assert.assertEquals(aqpLocator.getButton_Delete().getText().trim(),"Delete","Delete Option is not displayed");

            //Expected 5. Preview button must be displayed"
            Assert.assertEquals(aqpLocator.getButton_Preview().getText().trim(),"Preview","Preview button is not displayed");

            //Expected "6. ""Shuffle answer"" check box must be displayed as set by the instructor"
            //Expected "7. TLO and ELO should be displayed properly
            String tlo=aqpLocator.getLabel_tlo().getText();
            String elo=aqpLocator.getLabel_elo().getText();
            expected="1.OA - Operations & Algebraic Thinking";
            if(!tlo.contains(expected))
                Assert.fail("Tlo is not displayed properly");
            expected="1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to";
            if(!elo.contains(expected))
                Assert.fail("Elo is not displayed Properly");

            //6. Click on "Edit option"
            aqpLocator.getButton_Edit().click();//click on the edit button
            Thread.sleep(5000);
            //Expected :8.Instructor must be taken to the question edit mode and he should be able to edit the question now
            ((JavascriptExecutor)driver).executeScript("document.getElementById('question-raw-content').value='Edited text entry'");
            //qepLocator.getTextBox_QuestionEditField().sendKeys("Edited text entry");
            qepLocator.getButton_Save().click();//click on the save button

            aqpLocator.getButton_Preview().click();//click on the review button
            Thread.sleep(10000);

            String parentWindow=driver.getWindowHandle();
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);//switch to the Preview Window
            }
            System.out.println("current url:"+driver.getCurrentUrl());
            //Expected:A pop up should open that shows a preview of the question as a student would view
            if(!ppLocator.getPreviewPageTitle().getText().equals("PREVIEW"))
                Assert.fail("A pop up is not Displayed");

            //Expected10. Question should be displayed
            expected="Text Entry question text IT_editingquestionsindraftstatus_188";
            if(!ppLocator.getQuestionText().getText().contains(expected))
                Assert.fail("Question is not displayed in the review Page");
            //Expected 11.Text input field for answering should be displayed
             boolean textField=driver.findElement(By.xpath("//input[starts-with(@id,'text_')]")).isDisplayed();
            if(textField==false) {
                Assert.fail("Text input field for answering is not displayed ");
            }

            ppLocator.getInputTextEntry().sendKeys("answer"); //send value in input text entry
            //Expected 12. A submit button should be displayed
            Assert.assertEquals(ppLocator.getSubmitButton().getText(),"Submit","Submit button is not displayed");
            //11. Click on ""submit"" button"
            ppLocator.getSubmitButton().click();//click on the submit button

             //The Feedback (right or wrong) or 'Partially correct' along with the actual solution must be displayed

            String wrongTickMark=ppLocator.getWrongTickMark().getAttribute("class");
            if(!wrongTickMark.contains("visible_redactor_input div-preview-wrong-answer"))
                Assert.fail("The feedback right or wrong is not displayed");

            Assert.assertEquals(ppLocator.getHintLabel().getText().trim(),"Hint","Hint Field is not visible after submit");
            Thread.sleep(5000);
            Assert.assertEquals(ppLocator.getHintContent().getText().trim(),"True False Hint Text","Hint Content Field is not visible after submit");
            Assert.assertEquals(ppLocator.getSolutionLabel().getText().trim(), "Solution", "Solution Field is not visible after submit");
            Assert.assertEquals(ppLocator.getSolutionLabelContent().getText().trim(),"True False Solution Text","Solution Content Field is not visible after submit");
            Assert.assertEquals(ppLocator.getQuestionPoint_previewPage().getAttribute("value"),"1","Points is not visible in the preview page after submit");


        } catch (Exception e) {
            Assert.fail("Exception in testcase instructorAbleToClickOnPreviewAfterSavingEditedQuestionForTextEntry of class InstructorAbleToClickOnPreviewAfterSavingEditedQuestion", e);
        }

    }
    @Test(priority = 5,enabled = true)
    public void instructorNotAbleToEditQuestionOfDifferentOwnerAssignment()
    {
        Assignments locator=PageFactory.initElements(driver, Assignments.class);
        AssessmentQuestionPage  aqpLocator=PageFactory.initElements(driver,AssessmentQuestionPage.class);
        AssessmentQuestionsListPage aqlpLocator=PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "10");

        String expected=null;
        String actual=null;

        try {
            String appendChar1 = "j";
            String appendChar2="k";
            int index=10;
            new SignUp().teacher(appendChar1, index);//Sign up as teacher1
            new School().createWithOnlyName(appendChar1, index);//create school
            new Classes().createClass(appendChar1, index,"Grade 2", "Mathematics", "Math - Common Core");//create class
            new Navigator().logout();//logout

            new SignUp().teacher(appendChar2, index);//Sign up as teacher
            new School().createWithOnlyName(appendChar2, index);//create school
            new Classes().createClass(appendChar2, index);//create class
            new Navigator().logout();//logout

            new Login().loginAsInstructor(appendChar1, index);//log in as teacher1
            new Assignment().create(index,"truefalse");
            new Assignment().assignToStudent(index,appendChar1);
            new Navigator().logout();//logout

            new Login().loginAsInstructor(appendChar2, index);//log in as teacher2
            new Assignment().useExistingAssignment(index,appendChar2);
            new Navigator().navigateTo("assignment");//navigate To Assignments
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on +new Assignment button
            new Click().clickBycssselector("button#search-assessment-with-val");//click on search
            driver.findElement(By.xpath("//span[@id='select2-gradeSelection-container']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[contains(@id,'Grade 2')]")).click();

            List<WebElement> allAssignment = driver.findElements(By.cssSelector("div[class='font-semi-bold space-15 assign-title']"));

            int index1 = 0;
            for(WebElement assignment:allAssignment) {
                if (assignment.getText().trim().equals(assessmentname)) {
                    driver.findElement(By.xpath("//a[@title='"+assessmentname+"']")).click();
                    break;
                }
                else{
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", assignment);
                    index1++;
                }
            }
            assessmentLibrary.getList_createNewVersion().get(0).click();//Click on customize button
            locator.getButton_saveForLater().click();
            new Navigator().navigateTo("myAssessments");//Navigate to my assessment page
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");//Click on Draft
            driver.findElement(By.xpath("//span[@id='select2-gradeSelection-container']")).click();
            driver.findElement(By.xpath("//*[contains(@id,'Grade 2')]")).click();
            new Click().clickBycssselector("div[class='font-semi-bold space-15 assign-title']");//click on Assignment
            new Click().clickByXpath("//i[@class='ed-icon icon-edit-assesment']/following-sibling::span");//Click on edit button
            String questionID = locator.getLabelValue_QuestionID().getText();
            aqlpLocator.getArrowLink().click();

            //Expected - 1 : The particular question should be opened
            expected="True False question text IT_10" ;
            if(!aqpLocator.getLabel_questionName().getText().contains(expected)) {
                Assert.fail("The particular question is not opened");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(aqpLocator.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            String appendCharacterBuild=System.getProperty("UCHAR");
            if(appendCharacterBuild!=null) {
                appendCharacterBuild = appendChar1 + appendCharacterBuild;
            }
            else {
                appendCharacterBuild = appendChar1;
            }
            expected="instructorNotAbleToEditQuestionOfDifferentOwnerAssignmentins"+appendCharacterBuild;
            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals("Owner:"+aqpLocator.getLabelValue_Owner().getText().trim(),"Owner:"+expected,"Owner of the assignment is not displayed");

           //4. Edit and delete option must not be displayed

            boolean editFound;
            editFound = new BooleanValue().presenceOfElement(index,By.className("as-question-preview-edit-button"));

            //Expected - 4 : Edit option must not be displayed
            Assert.assertEquals(editFound, false, "Edit option is displayed");

            boolean deleteFound;
            deleteFound = new BooleanValue().presenceOfElement(index,By.className("as-question-preview-delete-button"));

            //Expected - 5 : delete option must not be displayed
            Assert.assertEquals(deleteFound, false, "Delete option is displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(aqpLocator.getButton_Preview().getText().trim(),"Preview","Preview button is not displayed");
            //Expected - 7. "Shuffle answer" check box must be displayed as set by the instructor
          /* Shuffle question removed
          boolean isSelected;
            isSelected = aqpLocator.getCheckBox_shuffleAnswerChoices().isSelected();
            Assert.assertEquals(isSelected,false,"Check box is selected even though it was not selected by instructor");
*/
            //Expected "7. TLO and ELO should be displayed properly
            expected="2.OA - Operations & Algebraic Thinking";
            String expected1="2.OA.A.1 - Use addition and subtraction within 100 to solve one- and two-step word problems involving situations of adding to";

            List<WebElement> allTloElo=driver.findElements(By.className("as-question-tlos-wrapper"));
            for(WebElement each:allTloElo)
            {
                if(!(each.getText().contains(expected)||each.getText().contains(expected1)))
                    Assert.fail("Elo and Tlo is not displayed properly");
            }
            /*String tlo=aqpLocator.getLabel_tlo().getText();
            System.out.println("Tlo:"+tlo);
            String elo=aqpLocator.getLabel_elo().getText();
            System.out.println("Elo:"+elo);
            expected="2.OA - Operations & Algebraic Thinking";
            if(!tlo.contains(expected))
                Assert.fail("Tlo is not displayed properly");
            expected="2.OA.A.1 - Use addition and subtraction within 100 to solve one- and two-step word problems involving situations of adding to";
            if(!elo.contains(expected))
                Assert.fail("Elo is not displayed Properly");
*/

        } catch (Exception e) {
            Assert.fail("Exception in testcase instructorNotAbleToEditQuestionOfDifferentOwnerAssignment of class InstructorAbleToClickOnPreviewAfterSavingEditedQuestion", e);
        }

    }

}
