package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.writeboardforquestions;

import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by Sumit on 16/12/14.
 */
public class EnableWriteBoardForQuestions extends Driver{


    @Test(priority = 1, enabled = true)
    public void enableWriteBoardForTrueFalseQuestion(){
        try
        {
            String appendChar = "b";
            //TC row no. 10
            /*"1. Login as a teacher
            2. Go to Question creation page, select ""True/False"" question type
            "
            Expected: #1. By default "Allow student to use writeboard" checkbox should not be selected*/
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");    //go to Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span.lsm-create-btn.lsm-elo-create-btn");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys("CheckTrueFalse");//give the assignment name
            new Click().clickByid("qtn-true-false-type");//click on True/False type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("True False Type Question");//type the question
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option A

            String isSelected = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("By default \"Allow student to use writeboard\" checkbox is selected for true false type question.");

            //TC row no. 11
            /*3. Enter all the question details, select "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved, "Saved.", "Question is not created.");

            String isSelected1 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected1.contains("writeboardchkbox as-writeboard-checkbox-checked"))
                Assert.fail("If we check the writeboard selection the true false type Question is not saved with writeboard selected.");

            //TC row no. 12
            /*4. Uncheck the "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard option not selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to uncheck writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved1 = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved1, "Saved.", "Question is not created.");

            String isSelected2 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected2.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("Unable to uncheck the \"Allow student to use writeboard\" option after we save the true/ false type question..");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase enableWriteBoardForTrueFalseQuestion in class EnableWriteBoardForQuestions.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void enableWriteBoardForMultipleChoiceQuestion()
    {
        try
        {
            String appendChar = "b";
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");    //go to Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span.lsm-create-btn.lsm-elo-create-btn");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys("Check Multiple Choice");//give the assignment name
            new Click().clickByid("qtn-multiple-choice-type");//click on Multiple choice type question
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-mc-raw-content")));
            closeHelp();
            new Click().clickByid("question-mc-raw-content");//click on Question
            driver.findElement(By.id("question-mc-raw-content")).sendKeys("Multiple choice type Question");//type the question
            List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("Option 1");
            answerOptions.get(1).sendKeys("Option 2");
            answerOptions.get(2).sendKeys("Option 3");
            answerOptions.get(3).sendKeys("Option 4");
            new Click().clickBycssselector("div[class='single-select-choice-icon single-select-choice-icon-deselect']"); //select correct answer as A
            String isSelected = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("By default \"Allow student to use writeboard\" checkbox is selected for true false type question.");

            //TC row no. 11
            /*3. Enter all the question details, select "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));
            String saved = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved, "Saved.", "Question is not created.");

            String isSelected1 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected1.contains("writeboardchkbox as-writeboard-checkbox-checked"))
                Assert.fail("If we check the writeboard selection the true false type Question is not saved with writeboard selected.");

            //TC row no. 12
            /*4. Uncheck the "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard option not selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to uncheck writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved1 = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved1, "Saved.", "Question is not created.");

            String isSelected2 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected2.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("Unable to uncheck the \"Allow student to use writeboard\" option after we save the true/ false type question..");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase enableWriteBoardForMultipleChoiceQuestion in class EnableWriteBoardForQuestions.", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void enableWriteBoardForMultipleSelectionQuestion()
    {
        try
        {
            String appendChar = "b";
            //TC row no. 16
            /*"1. Login as a teacher
            2. Go to Question creation page, select ""Multiple select"" question type
            "
            Expected: #1. By default "Allow student to use writeboard" checkbox should not be selected*/
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class

            new Navigator().navigateTo("assignment");    //go to Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span.lsm-create-btn.lsm-elo-create-btn");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys("Check Multiple Selection");//give the assignment name
            new Click().clickByid("qtn-multiple-selection-type");//click on Multiple Selection type question
            new Click().clickByid("question-ms-raw-content");//click on Question
            driver.findElement(By.id("question-ms-raw-content")).sendKeys("Multiple Select type Question");//type the question
            List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("Option 1");
            answerOptions.get(1).sendKeys("Option 2");
            answerOptions.get(2).sendKeys("Option 3");
            answerOptions.get(3).sendKeys("Option 4");
            List<WebElement> multipleSelections = driver.findElements(By.cssSelector("div[class='multiple-select-choice-icon multiple-select-choice-icon-deselect']"));
            multipleSelections.get(0).click(); multipleSelections.get(1).click(); //selecting option A and B as correct answers

            String isSelected = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("By default \"Allow student to use writeboard\" checkbox is selected for true false type question.");

            //TC row no. 17
            /*3. Enter all the question details, select "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved, "Saved.", "Question is not created.");

            String isSelected1 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected1.contains("writeboardchkbox as-writeboard-checkbox-checked"))
                Assert.fail("If we check the writeboard selection the Multiple selection type Question is not saved with writeboard selected.");

            //TC row no. 18
            /*4. Uncheck the "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard option not selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to uncheck writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved1 = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved1, "Saved.", "Question is not created.");

            String isSelected2 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected2.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("Unable to uncheck the \"Allow student to use writeboard\" option after we save the Multiple selection type question..");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase enableWriteBoardForMultipleSelectionQuestion in class EnableWriteBoardForQuestions.", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void enableWriteBoardForTextEntryQuestion()
    {
        try
        {
            String appendChar = "b";
            /*//TC row no. 19
            "1. Login as a teacher
            2. Go to Question creation page, select ""Text entry"" question type
            "
            Expected: #1. By default "Allow student to use writeboard" checkbox should not be selected*/
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");    //go to Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span.lsm-create-btn.lsm-elo-create-btn");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys("Check Text Entry");//give the assignment name
            new Click().clickByid("qtn-text-entry-type");//click on Text Entry type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Multiple Select type Question");//type the question
            new TextSend().textsendbycssSelector("Correct Answer", "input[class='numeric_text_entry_input get-user-entry']");
            new Click().clickBycssselector("span[class='btn sty-green save-spanish-text accept_answer text_entry_accept_answer']"); //click on Accept answer button

            String isSelected = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("By default \"Allow student to use writeboard\" checkbox is selected for Text Entry type question.");

            //TC row no. 20
            /*3. Enter all the question details, select "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved, "Saved.", "Question is not created.");

            String isSelected1 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected1.contains("writeboardchkbox as-writeboard-checkbox-checked"))
                Assert.fail("If we check the writeboard selection the Text Entry type Question is not saved with writeboard selected.");

            //TC row no. 21
            /*4. Uncheck the "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard option not selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to uncheck writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved1 = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved1, "Saved.", "Question is not created.");

            String isSelected2 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected2.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("Unable to uncheck the \"Allow student to use writeboard\" option after we save the Text Entry type question..");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase enableWriteBoardForTextEntryQuestion in class EnableWriteBoardForQuestions.", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void enableWriteBoardForTextDropdownQuestion()
    {
        try
        {
            String appendChar = "b";
            //TC row no. 22
            /*"1. Login as a teacher
            2. Go to Question creation page, select ""Text Dropdown"" question type
            "
            Expected: #1. By default "Allow student to use writeboard" checkbox should not be selected*/
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");    //go to Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span.lsm-create-btn.lsm-elo-create-btn");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys("Check Text Drop Down Type");//give the assignment name
            new Click().clickByid("qtn-text-drop-down-type");//click on Text Entry Drop Down type question
            closeHelp();
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Text Entry Drop Down type Question");//type the question
            new Click().clickByclassname("text-drop-val");
            new TextSend().textsendbyid("Answer1", "ans1");

            new Click().clickByclassname("select-icon-text-drop-down"); //select answer 1 as correct answer

            driver.findElements(By.className("text-drop-val")).get(1).click();
            driver.findElements(By.id("ans1")).get(1).sendKeys("Answer2");

            driver.findElements(By.className("text-drop-val")).get(2).click();
            driver.findElements(By.id("ans1")).get(2).sendKeys("Answer3");

            new Click().clickBycssselector("span[class='done-button btn sty-green text-drop-accept accept_answer']"); //click on accept answer button
            String isSelected = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("By default \"Allow student to use writeboard\" checkbox is selected for Text Dropdown type question.");

            //TC row no. 23
            /*3. Enter all the question details, select "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));
            String saved = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved, "Saved.", "Question is not created.");

            String isSelected1 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected1.contains("writeboardchkbox as-writeboard-checkbox-checked"))
                Assert.fail("If we check the writeboard selection the Text Dropdown type Question is not saved with writeboard selected.");

            //TC row no. 24
            /*4. Uncheck the "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard option not selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to uncheck writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved1 = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved1, "Saved.", "Question is not created.");

            String isSelected2 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected2.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("Unable to uncheck the \"Allow student to use writeboard\" option after we save the Text Dropdown type question..");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase enableWriteBoardForTextDropdownQuestion in class EnableWriteBoardForQuestions.", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void enableWriteBoardForNumericEntryWithUnitsQuestion()
    {
        try
        {
            String appendChar = "a";
            //TC row no. 25
            /*"1. Login as a teacher
            2. Go to Question creation page, select ""Numeric entry with units"" question type
            "
            Expected: #1. By default "Allow student to use writeboard" checkbox should not be selected*/
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");    //go to Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span.lsm-create-btn.lsm-elo-create-btn");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys("Check Numeric Entry With Units Type");//give the assignment name
            new Click().clickByid("qtn-numeric-entry-units-type");//click on Numeric Entry with Units type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Numeric Entry With Units ");//type the question

            new TextSend().textsendbycssSelector("10", "input[class='numeric_unit_correct_answer numeric_text_entry numeric_text_entry_input']");//enter correct answer
            //Adding First Unit
            new Click().clickBycssselector("div[class='unit-arrow-down add-more-num-entry-unit']"); //click on add more units
            new Click().clickBycssselector("li[value='feet']"); //select feet
            new Click().clickByclassname("unit_tick_image"); //click on the tick mark for unit
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget num_ent_unit_accept_answer']"); //click on accept answer

            //Adding Second Unit
            new Click().clickBycssselector("div[class='add-more-num-entry-unit unit-arrow-down']"); //click on add more units
            new Click().clickBycssselector("li[value='yards']"); //select yards

            new Click().clickByXpath("//div[@class='numeric_entry_accept_answer']/span"); //Click on Accept Answer
            String isSelected = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("By default \"Allow student to use writeboard\" checkbox is selected for Numeric Entry With Units type question.");

            //TC row no. 26
            /*3. Enter all the question details, select "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved, "Saved.", "Question is not created.");

            String isSelected1 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected1.contains("writeboardchkbox as-writeboard-checkbox-checked"))
                Assert.fail("If we check the writeboard selection the Numeric Entry With Units Type Question is not saved with writeboard selected.");

            //TC row no. 27
            /*4. Uncheck the "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard option not selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to uncheck writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved1 = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved1, "Saved.", "Question is not created.");

            String isSelected2 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected2.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("Unable to uncheck the \"Allow student to use writeboard\" option after we save the Numeric Entry With Units Type question..");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase enableWriteBoardForNumericEntryWithUnitsQuestion in class EnableWriteBoardForQuestions.", e);
        }
    }
    @Test(priority = 7, enabled = true)
    public void enableWriteBoardForAdvancedNumericQuestion()
    {
        try
        {
            String appendChar = "b";
            //TC row no. 28
            /* "1. Login as a teacher
            2. Go to Question creation page, select ""Advanced Numeric""  question type
            "
            Expected: #1. By default "Allow student to use writeboard" checkbox should not be selected*/
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");    //go to Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span.lsm-create-btn.lsm-elo-create-btn");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys("Check Advanced Numeric Type");//give the assignment name

            new Click().clickByid("qtn-maple-numeric-type");//click on advanced numeric type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Advanced Numeric question");//type the question

            new TextSend().textsendbycssSelector("10","input[class='numeric_correct_text_entry_input num-entry-ans border-color-gray']");

            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer accept_answer_maple_numeric']"); //click on accept answer

            String isSelected = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("By default \"Allow student to use writeboard\" checkbox is selected for Advanced Numeric type question.");

            //TC row no. 29
            /*3. Enter all the question details, select "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved, "Saved.", "Question is not created.");

            String isSelected1 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected1.contains("writeboardchkbox as-writeboard-checkbox-checked"))
                Assert.fail("If we check the writeboard selection the Advanced Numeric type Question is not saved with writeboard selected.");

            //TC row no. 30
            /*4. Uncheck the "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard option not selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to uncheck writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved1 = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved1, "Saved.", "Question is not created.");

            String isSelected2 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected2.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("Unable to uncheck the \"Allow student to use writeboard\" option after we save the Advanced Numeric Type question..");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase enableWriteBoardForAdvancedNumericQuestion in class EnableWriteBoardForQuestions.", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void enableWriteBoardForExpressionEvaluatorQuestion()
    {
        try
        {
            String appendChar = "b";
            //TC row no. 31
            /*
            "1. Login as a teacher
            2. Go to Question creation page, select ""Expression evaluator"" question type
            "
            Expected: #1. By default "Allow student to use writeboard" checkbox should not be selected
             */
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");    //go to Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span.lsm-create-btn.lsm-elo-create-btn");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys("Check Expression Evaluator Type");//give the assignment name

            new Click().clickByid("qtn-math-symbolic-notation-type");//click on Expression Evaluator type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Expression Evaluator question");//type the question

            new Click().clickByid("answer_math_edit");
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "5");

            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer maple_symc_not_accept_answer']"); //click on accept answer
            String isSelected = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("By default \"Allow student to use writeboard\" checkbox is selected for Expression Evaluator type question.");

            //TC row no. 32
            /*3. Enter all the question details, select "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved, "Saved.", "Question is not created.");

            String isSelected1 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected1.contains("writeboardchkbox as-writeboard-checkbox-checked"))
                Assert.fail("If we check the writeboard selection the Expression Evaluator type Question is not saved with writeboard selected.");

            //TC row no. 33
            /*4. Uncheck the "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard option not selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to uncheck writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved1 = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved1, "Saved.", "Question is not created.");

            String isSelected2 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected2.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("Unable to uncheck the \"Allow student to use writeboard\" option after we save the Expression Evaluator Type question..");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase enableWriteBoardForExpressionEvaluatorQuestion in class EnableWriteBoardForQuestions.", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void enableWriteBoardForMatchTheFollowingQuestion()
    {
        try
        {
            String appendChar = "b";
            //TC row no. 34
            /*
            "1. Login as a teacher
            2. Go to Question creation page, select ""Match the following"" question type
            "
            Expected: #1. By default "Allow student to use writeboard" checkbox should not be selected
             */
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");    //go to Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span.lsm-create-btn.lsm-elo-create-btn");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys("Check Match the Following Type");//give the assignment name

            new Click().clickByid("qtn-mtf-type");//click on Match the Following type question
            closeHelp();
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Match the Following question text");//type the question

            List<WebElement> lhsboxes = driver.findElements(By.cssSelector("div[class='dnd-match-lhs box']")); //Fetching all the lhs boxes

            lhsboxes.get(0).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            lhsboxes.get(1).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");


            lhsboxes.get(2).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            lhsboxes.get(3).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            lhsboxes.get(4).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            //Filling RHS

            List<WebElement> rhsboxes = driver.findElements(By.cssSelector("div[class='dnd-match-rhs box']")); //Fetching all the lhs boxes

            rhsboxes.get(0).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            rhsboxes.get(1).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            rhsboxes.get(2).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            rhsboxes.get(3).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            rhsboxes.get(4).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            String isSelected = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("By default \"Allow student to use writeboard\" checkbox is selected for Match the Following type question.");

            //TC row no. 35
            /*3. Enter all the question details, select "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved, "Saved.", "Question is not created.");

            String isSelected1 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected1.contains("writeboardchkbox as-writeboard-checkbox-checked"))
                Assert.fail("If we check the writeboard selection the Match the Following type Question is not saved with writeboard selected.");

            //TC row no. 36
            /*4. Uncheck the "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard option not selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to uncheck writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved1 = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved1, "Saved.", "Question is not created.");

            String isSelected2 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected2.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("Unable to uncheck the \"Allow student to use writeboard\" option after we save the Match the Following Type question..");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase enableWriteBoardForMatchTheFollowingQuestion in class EnableWriteBoardForQuestions.", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void enableWriteBoardForDragAndDropQuestion()
    {
        try
        {
            String appendChar = "b";
            //TC row no. 37
            /*"1. Login as a teacher
            2. Go to Question creation page, select ""Drag and Drop"" question type
            "
            Expected: #1. By default "Allow student to use writeboard" checkbox should not be selected*/
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");    //go to Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span.lsm-create-btn.lsm-elo-create-btn");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys("Check Drag and Drop Type");//give the assignment name

            new Click().clickByid("qtn-dnd-type");//click on Drag and Drop type question
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            closeHelp();
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Drag and Drop question text" );//type the question

            List<WebElement> answers = driver.findElements(By.className("answer"));
            answers.get(0).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            answers.get(1).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("2000", "answer_choice_txt");

            answers.get(2).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("3000", "answer_choice_txt");

            answers.get(3).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("4000", "answer_choice_txt");

            driver.findElement(By.xpath("/html/body")).click();
            Thread.sleep(2000);

            new Click().clickByclassname("answer");
            Thread.sleep(2000);

            //List<WebElement> answerstodrag = driver.findElements(By.className("answer"));
            Actions ac = new Actions(driver);
            //answerstodrag.get(0).click();
            ac.dragAndDrop(driver.findElement(By.cssSelector("img[id='ans-drag-btn']")),driver.findElement(By.id("canvas"))).build().perform();
            Thread.sleep(2000);

            String isSelected = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("By default \"Allow student to use writeboard\" checkbox is selected for Drag and Drop type question.");

            //TC row no. 38
            /*3. Enter all the question details, select "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[text()='Saved.']")));
            String saved = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved, "Saved.", "Question is not created.");

            String isSelected1 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected1.contains("writeboardchkbox as-writeboard-checkbox-checked"))
                Assert.fail("If we check the writeboard selection the Drag and Drop type Question is not saved with writeboard selected.");

            //TC row no. 39
            /*4. Uncheck the "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard option not selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to uncheck writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved1 = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved1, "Saved.", "Question is not created.");

            String isSelected2 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected2.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("Unable to uncheck the \"Allow student to use writeboard\" option after we save the Drag and Drop Type question..");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase enableWriteBoardForDragAndDropQuestion in class EnableWriteBoardForQuestions.", e);
        }
    }

    @Test(priority = 11, enabled = true)
    public void enableWriteBoardForEssayTypeQuestion()
    {
        try
        {
            String appendChar = "b";
            //TC row no. 40
            /* "1. Login as a teacher
            2. Go to Question creation page, select ""Essay"" question type
            "
            Expected:#1. By default "Allow student to use writeboard" checkbox should not be selected*/
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");    //go to Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span.lsm-create-btn.lsm-elo-create-btn");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys("Check Essay Type");//give the assignment name

            new Click().clickByid("qtn-essay-type");//click on Essay type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Essay type question");//type the question
            new TextSend().textsendbyid("10", "essay-question-height");//enter line height

            String isSelected = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("By default \"Allow student to use writeboard\" checkbox is selected for Essay type question.");

            //TC row no. 41
            /*3. Enter all the question details, select "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button


            String isSelected1 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected1.contains("writeboardchkbox as-writeboard-checkbox-checked"))
                Assert.fail("If we check the writeboard selection the Essay type Question is not saved with writeboard selected.");

            //TC row no. 42
            /*4. Uncheck the "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard option not selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to uncheck writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button;

            String isSelected2 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected2.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("Unable to uncheck the \"Allow student to use writeboard\" option after we save the Essay Type question..");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase enableWriteBoardForEssayTypeQuestion in class EnableWriteBoardForQuestions.", e);
        }
    }

    @Test(priority = 12, enabled = true)
    public void enableWriteBoardForLabelAnImageTypeQuestion()
    {
        try
        {
            String appendChar = "b";
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");    //go to Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span.lsm-create-btn.lsm-elo-create-btn");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys("Check Label An Image(Text) Type");//give the assignment name

            new Click().clickByid("qtn-lbl-on-img-type");//click on Label An Image(Text) type question
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            closeHelp();
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Label An Image(Text) question text" );//type the question

            List<WebElement> answers = driver.findElements(By.className("labelAnswer"));
            answers.get(0).click();
            driver.switchTo().activeElement().sendKeys("Answer 1");
            //answers.get(0).sendKeys();

            answers.get(1).click();
            driver.switchTo().activeElement().sendKeys("Answer 2");

            answers.get(2).click();
            driver.switchTo().activeElement().sendKeys("Answer 3");
            driver.findElement(By.xpath("/html/body")).click();
            Thread.sleep(2000);

            new Click().clickByclassname("labelAnswer");
            Actions ac = new Actions(driver);
            ac.dragAndDrop(driver.findElement(By.cssSelector("img[id='ans-drag-btn']")),driver.findElement(By.id("canvas"))).build().perform();
            Thread.sleep(2000);

            String isSelected = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("By default \"Allow student to use writeboard\" checkbox is selected for Label An Image(Text) type question.");

            //TC row no. 44
            /*3. Enter all the question details, select "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[text()='Saved.']")));
            String saved = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved, "Saved.", "Question is not created.");

            String isSelected1 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected1.contains("writeboardchkbox as-writeboard-checkbox-checked"))
                Assert.fail("If we check the writeboard selection the Label An Image(Text) type Question is not saved with writeboard selected.");

            //TC row no. 45
            /*4. Uncheck the "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard option not selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to uncheck writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[text()='Saved.']")));
            String saved1 = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved1, "Saved.", "Question is not created.");

            String isSelected2 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected2.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("Unable to uncheck the \"Allow student to use writeboard\" option after we save the Label An Image(Text) Type question..");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase enableWriteBoardForLabelAnImageTypeQuestion in class EnableWriteBoardForQuestions.", e);
        }
    }

    @Test(priority = 13, enabled = true)
    public void enableWriteBoardForLabelAnImageDropdownTypeQuestion()
    {
        try
        {
            String appendChar = "b";
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");    //go to Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span.lsm-create-btn.lsm-elo-create-btn");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys("Check Label An Image Dropdown Type");//give the assignment name
            new Click().clickByid("qtn-lbl-dropdown-type");//click on Label An Image Dropdown type question

            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Label An Image(Text) question text" );//type the question

            List<WebElement> answerChoices = driver.findElements(By.className("label-drpdwn-answerChoice-style"));
            answerChoices.get(0).click();
            List<WebElement> subAnswerChoices = driver.findElements(By.className("label-drpdwn-subAnswerChoice-text"));
            subAnswerChoices.get(0).click();
            driver.switchTo().activeElement().sendKeys("Answer 1");
            new Click().clickbylistid("tick-image", 0);//select correct answer
            new Click().clickbylistid("tick-image", 0);//select correct answer


            subAnswerChoices.get(1).click();
            driver.switchTo().activeElement().sendKeys("Answer 2");

            subAnswerChoices.get(2).click();
            driver.switchTo().activeElement().sendKeys("Answer 3");

            answerChoices.get(1).click();//click on second dropdown
            subAnswerChoices.get(3).click();
            driver.switchTo().activeElement().sendKeys("Answer 4");
            new Click().clickbylistid("tick-image", 3);//select correct answer
            new Click().clickbylistid("tick-image", 3);//select correct answer


            subAnswerChoices.get(4).click();
            driver.switchTo().activeElement().sendKeys("Answer 5");

            subAnswerChoices.get(5).click();
            driver.switchTo().activeElement().sendKeys("Answer 6");

            answerChoices.get(2).click();//click on third dropdown
            subAnswerChoices.get(6).click();
            driver.switchTo().activeElement().sendKeys("Answer 7");
            new Click().clickbylistid("tick-image", 6);//select correct answer
            new Click().clickbylistid("tick-image", 6);//select correct answer

            subAnswerChoices.get(7).click();
            driver.switchTo().activeElement().sendKeys("Answer 8");

            subAnswerChoices.get(8).click();
            driver.switchTo().activeElement().sendKeys("Answer 9");

            answerChoices.get(0).click();//click on 1st answer

            Thread.sleep(2000);
            List<WebElement> answerChoices2 = driver.findElements(By.className("label-drpdwn-answerChoice-style"));
            answerChoices2.get(0).click();//click on 1st answer
            List<WebElement> answerstodrag = driver.findElements(By.className("label-drpdwn-answerChoice-style"));
            Actions ac = new Actions(driver);
            answerstodrag.get(0).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();
            Thread.sleep(2000);

            String isSelected = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("By default \"Allow student to use writeboard\" checkbox is selected for Label An Image Dropdown type question.");

            //TC row no. 47
            /*3. Enter all the question details, select "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved, "Saved.", "Question is not created.");

            String isSelected1 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected1.contains("writeboardchkbox as-writeboard-checkbox-checked"))
                Assert.fail("If we check the writeboard selection the Label An Image Dropdown type Question is not saved with writeboard selected.");

            //TC row no. 48
            /*4. Uncheck the "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard option not selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to uncheck writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved1 = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved1, "Saved.", "Question is not created.");

            String isSelected2 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected2.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("Unable to uncheck the \"Allow student to use writeboard\" option after we save the Label An Image Dropdown Type question..");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase enableWriteBoardForLabelAnImageDropdownTypeQuestion in class EnableWriteBoardForQuestions.", e);
        }
    }
    @Test(priority = 14, enabled = true)
    public void enableWriteBoardForResquenceTypeQuestion()
    {
        try
        {
            String appendChar = "b";
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");    //go to Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span.lsm-create-btn.lsm-elo-create-btn");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys("Check Resequence Type");//give the assignment name

            new Click().clickByid("qtn-resequence-type");//click on resequence type question
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            closeHelp();
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Resequence question type");//type the question

            List<WebElement> allAnswers = driver.findElements(By.cssSelector("div[class='resequence-answer-choice hover-border']"));
            allAnswers.get(0).click();//click on 1st row
            new Click().clickByid("answer_choice_edit");
            driver.switchTo().activeElement().sendKeys("1000");

            allAnswers.get(1).click();//click on 2nd row
            new Click().clickByid("answer_choice_edit");
            driver.switchTo().activeElement().sendKeys("1000");

            allAnswers.get(2).click();//click on 3rd row
            new Click().clickByid("answer_choice_edit");
            driver.switchTo().activeElement().sendKeys("1000");

            allAnswers.get(3).click();//click on 4th row
            new Click().clickByid("answer_choice_edit");
            driver.switchTo().activeElement().sendKeys("1000");

            allAnswers.get(4).click();//click on 5th row
            new Click().clickByid("answer_choice_edit");
            driver.switchTo().activeElement().sendKeys("2000");

            String isSelected = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("By default \"Allow student to use writeboard\" checkbox is selected for Resequence type question.");

            //TC row no. 50
            /*3. Enter all the question details, select "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[text()='Saved.']")));
            String saved = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved, "Saved.", "Question is not created.");

            String isSelected1 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected1.contains("writeboardchkbox as-writeboard-checkbox-checked"))
                Assert.fail("If we check the writeboard selection the Resequence type Question is not saved with writeboard selected.");

            //TC row no. 51
            /*4. Uncheck the "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard option not selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to uncheck writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved1 = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved1, "Saved.", "Question is not created.");

            String isSelected2 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected2.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("Unable to uncheck the \"Allow student to use writeboard\" option after we save the Resequence Type question..");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase enableWriteBoardForResquenceTypeQuestion in class EnableWriteBoardForQuestions.", e);
        }
    }

    @Test(priority = 15, enabled = true)
    public void enableWriteBoardForClozeMatrixTypeQuestion()
    {
        try
        {
            String appendChar = "b";
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");    //go to Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span.lsm-create-btn.lsm-elo-create-btn");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys("Check cloze matrix Type");//give the assignment name

            new Click().clickByid("qtn-cloze-matrix-type");//click on Cloze Matrix type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Cloze Matrix " );//type the question
            new QuestionCreate().enterValueInClozeMatrix(0, 1);
            new QuestionCreate().enterValueInClozeMatrix(1, 2);
            new QuestionCreate().enterValueInClozeMatrix(2, 3);
            new QuestionCreate().enterValueInClozeMatrix(3, 4);
            new QuestionCreate().enterValueInClozeMatrix(4, 5);
            new QuestionCreate().enterValueInClozeMatrix(5, 6);
            new QuestionCreate().enterValueInClozeMatrix(6, 7);
            new QuestionCreate().enterValueInClozeMatrix(7, 8);
            new QuestionCreate().enterValueInClozeMatrix(8, 9);
            driver.findElement(By.xpath("//tr[@id='row1']/td/div/div/div")).click();//click on 1st cell
            new Click().clickByclassname("make-text-entry-box");//select correct answer

            String isSelected = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("By default \"Allow student to use writeboard\" checkbox is selected for cloze matrix type question.");

            //TC row no. 53
            /*3. Enter all the question details, select "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved, "Saved.", "Question is not created.");

            String isSelected1 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected1.contains("writeboardchkbox as-writeboard-checkbox-checked"))
                Assert.fail("If we check the writeboard selection the cloze matrix type Question is not saved with writeboard selected.");

            //TC row no. 54
            /*4. Uncheck the "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard option not selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to uncheck writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            Thread.sleep(2000);
            String saved1 = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved1, "Saved.", "Question is not created.");

            String isSelected2 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected2.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("Unable to uncheck the \"Allow student to use writeboard\" option after we save the cloze matrix Type question..");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase enableWriteBoardForClozeMatrixTypeQuestion in class EnableWriteBoardForQuestions.", e);
        }
    }

    @Test(priority = 16, enabled = true)
    public void enableWriteBoardForGraphPlotterTypeQuestion()
    {
        try
        {
            String appendChar = "b";
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");    //go to Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span.lsm-create-btn.lsm-elo-create-btn");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys("Check Graph Plotter Type");//give the assignment name

            new Click().clickByid("qtn-graph-type");//click on Graph Plotter type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Graph Plotter ");//type the question

            new TextSend().textsendbyid("Year", "labelX");//label X
            new TextSend().textsendbyid("population", "labelY");//label Y

            new TextSend().textsendbyid("10", "scaleY");//Scale Y
            new TextSend().textsendbyid("80", "maxY");//Max Y

            new Click().clickByid("add-new-category");//add new category
            new Click().clickByid("add-new-category");//add new category
            List<WebElement> allCategories = driver.findElements(By.className("graph-plotter-category"));
            allCategories.get(0).click();
            allCategories.get(0).clear();
            driver.switchTo().activeElement().sendKeys("2001");
            allCategories.get(1).click();
            allCategories.get(1).clear();
            driver.switchTo().activeElement().sendKeys("2002");
            allCategories.get(2).click();
            allCategories.get(2).clear();
            driver.switchTo().activeElement().sendKeys("2003");
            allCategories.get(3).click();
            allCategories.get(3).clear();
            driver.switchTo().activeElement().sendKeys("2004");
            allCategories.get(4).click();
            allCategories.get(4).clear();
            driver.switchTo().activeElement().sendKeys("2005");

            String isSelected = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("By default \"Allow student to use writeboard\" checkbox is selected for Graph Plotter type question.");

            //TC row no. 56
            /*3. Enter all the question details, select "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[text()='Saved.']")));
            String saved = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved, "Saved.", "Question is not created.");

            String isSelected1 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected1.contains("writeboardchkbox as-writeboard-checkbox-checked"))
                Assert.fail("If we check the writeboard selection the Graph Plotter type Question is not saved with writeboard selected.");

            //TC row no. 57
            /*4. Uncheck the "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard option not selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to uncheck writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[text()='Saved.']")));
            String saved1 = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved1, "Saved.", "Question is not created.");

            String isSelected2 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected2.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("Unable to uncheck the \"Allow student to use writeboard\" option after we save the Graph Plotter Type question..");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase enableWriteBoardForGraphPlotterTypeQuestion in class EnableWriteBoardForQuestions.", e);
        }
    }

    @Test(priority = 17, enabled = true)
    public void enableWriteBoardForClozeFormulaTypeQuestion()
    {
        try
        {
            String appendChar = "b";
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");    //go to Assignments
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span.lsm-create-btn.lsm-elo-create-btn");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys("Check Cloze Formula Type");//give the assignment name
            new Click().clickByid("qtn-cf-type");//click on Cloze Formula type question
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            closeHelp();
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Cloze Formula ");//type the question

            List<WebElement> answers =  driver.findElements(By.className("answer"));
            answers.get(0).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","1");

            answers.get(1).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","2");

            answers.get(2).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "3");

            answers.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "4");

            answers.get(4).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","5");

            List<WebElement> formulaLHS = driver.findElements(By.cssSelector("div[class='dnd-match-lhs box cloze-formula-lhs']"));
            formulaLHS.get(0).click();
            driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "6");

            formulaLHS.get(1).click();
            driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","7");

            formulaLHS.get(2).click();
            driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","8");

            formulaLHS.get(3).click();
            driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","9");

            formulaLHS.get(4).click();
            driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","10");


            //dragging and dropping values
            Actions ac = new Actions(driver);
            List<WebElement> answerstodrop =  driver.findElements(By.className("answer"));
            List<WebElement> rhsFields = driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']"));

            answerstodrop.get(0).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")), rhsFields.get(0)).build().perform();

            answerstodrop.get(1).click();
            ac.dragAndDrop(driver.findElements(By.id("ans-drag-btn")).get(1), rhsFields.get(1)).build().perform();

            answerstodrop.get(2).click();
            ac.dragAndDrop(driver.findElements(By.id("ans-drag-btn")).get(2), rhsFields.get(2)).build().perform();

            answerstodrop.get(3).click();
            ac.dragAndDrop(driver.findElements(By.id("ans-drag-btn")).get(3), rhsFields.get(3)).build().perform();

            answerstodrop.get(4).click();
            ac.dragAndDrop(driver.findElements(By.id("ans-drag-btn")).get(4), rhsFields.get(4)).build().perform();

            String isSelected = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("By default \"Allow student to use writeboard\" checkbox is selected for Cloze Formula type question.");

            //TC row no. 59
            /*3. Enter all the question details, select "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[text()='Saved.']")));
            String saved = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved, "Saved.", "Question is not created.");

            String isSelected1 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected1.contains("writeboardchkbox as-writeboard-checkbox-checked"))
                Assert.fail("If we check the writeboard selection the Cloze Formula type Question is not saved with writeboard selected.");

            //TC row no. 60
            /*4. Uncheck the "Allow student to use writeboard" checkbox and click on "Save" button
            Expected: #1. Question should be saved with writeboard option not selected*/
            new Click().clickByXpath("//label[@id='writeboard']");//click to uncheck writeboard
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[text()='Saved.']")));
            String saved1 = new TextFetch().textfetchbyid("footer-notification-text");
            Assert.assertEquals(saved1, "Saved.", "Question is not created.");

            String isSelected2 = driver.findElement(By.cssSelector("label[id='writeboard']")).getAttribute("class");
            if(!isSelected2.contains("writeboardchkbox as-writeboard-checkbox-unchecked"))
                Assert.fail("Unable to uncheck the \"Allow student to use writeboard\" option after we save the Cloze Formula Type question..");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase enableWriteBoardForClozeFormulaTypeQuestion in class EnableWriteBoardForQuestions.", e);
        }
    }
}
