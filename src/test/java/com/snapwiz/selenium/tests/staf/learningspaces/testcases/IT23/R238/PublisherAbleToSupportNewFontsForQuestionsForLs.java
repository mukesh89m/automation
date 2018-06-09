package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R238;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
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
 * Created by priyanka on 9/15/2015.
 */
public class PublisherAbleToSupportNewFontsForQuestionsForLs extends Driver {

    @Test(priority = 1, enabled = true)
    public void authorAbleToSelectFontForAllTheQuestionFields() {

        try {
            //TC row no 818
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("True / False", 10);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("true-false-answer-select")));
            new Assignment().verifyFont(10);
            manageContent.turFalseAnswer.click();//click on Answer Option A
            new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
            manageContent.save_Button.click();//click on save
            Thread.sleep(1000);
            new Assignment().verifyFontOfDuplicateQuestion();

        } catch (Exception e) {
            Assert.fail("Exception in TC authorAbleToSelectFontForAllTheQuestionFields of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 2, enabled = true)
    public void trueFalseWriteTheTextThenSelectItAndThenMakeItMonospace() {

        try {
            //TC row no 28
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("True / False", 28);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("true-false-answer-select")));
            new Assignment().selectTextAndVerifyFont(28);
            manageContent.turFalseAnswer.click();//click on Answer Option A
            solutionText();
            new Assignment().verifyFontOfPreviewPage();

        } catch (Exception e) {
            Assert.fail("Exception in TC trueFalseWriteTheTextThenSelectItAndThenMakeItMonospace of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void multipleChoiceSelectTheFontAndWrite() {

        try {
            //TC row no 37
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Multiple Choice", 37);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-mc-raw-content")));
            new Assignment().verifyFont(37);
            new Click().clickBycssselector("div[class='single-select-choice-icon single-select-choice-icon-deselect']"); //select correct answer as A
            List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("Option 1");
            answerOptions.get(1).sendKeys("Option 2");
            answerOptions.get(2).sendKeys("Option 3");
            answerOptions.get(3).sendKeys("Option 4");
            new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
            manageContent.save_Button.click();//click on save
            Thread.sleep(1000);
            new Assignment().verifyFontOfDuplicateQuestion();

        } catch (Exception e) {
            Assert.fail("Exception in TC multipleChoiceSelectTheFontAndWrite of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void multipleChoiceWriteTheTextThenSelectItAndThenMakeItMonospace() {

        try {
            //TC row no 61
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Multiple Choice", 61);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-mc-raw-content")));
            new Assignment().selectTextAndVerifyFont(61);
            new Click().clickBycssselector("div[class='single-select-choice-icon single-select-choice-icon-deselect']"); //select correct answer as A
            List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("Option 1");
            answerOptions.get(1).sendKeys("Option 2");
            answerOptions.get(2).sendKeys("Option 3");
            answerOptions.get(3).sendKeys("Option 4");
            solutionText();
            new Assignment().verifyFontOfPreviewPage();

        } catch (Exception e) {
            Assert.fail("Exception in TC multipleChoiceWriteTheTextThenSelectItAndThenMakeItMonospace of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 5, enabled = true)
    public void multipleSelectionSelectTheFontAndWrite() {

        try {
            //TC row no 73
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Multiple Selection", 73);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-ms-raw-content")));
            new Assignment().verifyFont(73);
            List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("Option 1");
            answerOptions.get(1).sendKeys("Option 2");
            answerOptions.get(2).sendKeys("Option 3");
            answerOptions.get(3).sendKeys("Option 4");
            new Click().clickByid("swuploadclose");//close pop-up
            List<WebElement> multipleSelections = driver.findElements(By.cssSelector("div[class='multiple-select-choice-icon multiple-select-choice-icon-deselect']"));
            multipleSelections.get(0).click(); multipleSelections.get(1).click(); //selecting option A and B as correct answers
            new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
            manageContent.save_Button.click();//click on save
            Thread.sleep(1000);
            new Assignment().verifyFontOfDuplicateQuestion();

        } catch (Exception e) {
            Assert.fail("Exception in TC multipleSelectionSelectTheFontAndWrite of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 6, enabled = true)
    public void multipleSelectionWriteTheTextThenSelectItAndThenMakeItMonospace() {

        try {
            //TC row no 96
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Multiple Selection", 96);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-ms-raw-content")));
            new Assignment().selectTextAndVerifyFont(96);
            List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("Option 1");
            answerOptions.get(1).sendKeys("Option 2");
            answerOptions.get(2).sendKeys("Option 3");
            answerOptions.get(3).sendKeys("Option 4");
            new Click().clickByid("swuploadclose");//close pop-up
            List<WebElement> multipleSelections = driver.findElements(By.cssSelector("div[class='multiple-select-choice-icon multiple-select-choice-icon-deselect']"));
            multipleSelections.get(0).click(); multipleSelections.get(1).click(); //selecting option A and B as correct answers
            solutionText();
            new Assignment().verifyFontOfPreviewPage();

        } catch (Exception e) {
            Assert.fail("Exception in TC multipleSelectionWriteTheTextThenSelectItAndThenMakeItMonospace of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 7, enabled = true)
    public void textEntrySelectTheFontAndWrite() {

        try {
            //TC row no 108
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Text Entry", 108);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().verifyFont(108);
            new TextSend().textsendbycssSelector("Correct Answer", "input[class='numeric_text_entry_input get-user-entry is-value-changed-txt-entry']");
            new Click().clickBycssselector("span[class='btn sty-green save-language-text accept_answer text_entry_accept_answer']"); //click on Accept answer button
            manageContent.save_Button.click();//click on save
            Thread.sleep(1000);
            new Assignment().verifyFontOfDuplicateQuestion();

        } catch (Exception e) {
            Assert.fail("Exception in TC textEntrySelectTheFontAndWrite of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 8, enabled = true)
    public void textEntryWriteTheTextThenSelectItAndThenMakeItMonospace() {

        try {
            //TC row no 122
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Text Entry",122);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().selectTextAndVerifyFont(122);
            new TextSend().textsendbycssSelector("Correct Answer", "input[class='numeric_text_entry_input get-user-entry is-value-changed-txt-entry']");
            new Click().clickBycssselector("span[class='btn sty-green save-language-text accept_answer text_entry_accept_answer']"); //click on Accept answer button
            solutionText();
            new Assignment().verifyFontOfPreviewPage();

        } catch (Exception e) {
            Assert.fail("Exception in TC textEntryWriteTheTextThenSelectItAndThenMakeItMonospace of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void textDropDownSelectTheFontAndWrite() {

        try {
            //TC row no 129
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Text Drop Down", 129);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().verifyFont(129);
            new Click().clickByclassname("text-drop-val");
            new TextSend().textsendbyid("Answer1", "ans1");
            new Click().clickByclassname("select-icon-text-drop-down"); //select answer 1 as correct answer
            driver.findElements(By.className("text-drop-val")).get(1).click();
            driver.findElements(By.id("ans1")).get(1).sendKeys("Answer2");
            driver.findElements(By.className("text-drop-val")).get(2).click();
            driver.findElements(By.id("ans1")).get(2).sendKeys("Answer3");
            new Click().clickBycssselector("span[class='done-button btn sty-green text-drop-accept accept_answer']"); //click on accept answer button
            manageContent.save_Button.click();//click on save
            Thread.sleep(1000);
            new Assignment().verifyFontOfDuplicateQuestion();

        } catch (Exception e) {
            Assert.fail("Exception in TC textDropDownSelectTheFontAndWrite of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 10, enabled = true)
    public void textDropDownWriteTheTextThenSelectItAndThenMakeItMonospace() {

        try {
            //TC row no 142
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Text Drop Down",142);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().selectTextAndVerifyFont(142);
            new Click().clickByclassname("text-drop-val");
            new TextSend().textsendbyid("Answer1", "ans1");
            new Click().clickByclassname("select-icon-text-drop-down"); //select answer 1 as correct answer
            driver.findElements(By.className("text-drop-val")).get(1).click();
            driver.findElements(By.id("ans1")).get(1).sendKeys("Answer2");
            driver.findElements(By.className("text-drop-val")).get(2).click();
            driver.findElements(By.id("ans1")).get(2).sendKeys("Answer3");
            new Click().clickBycssselector("span[class='done-button btn sty-green text-drop-accept accept_answer']"); //click on accept answer button
            solutionText();
            new Assignment().verifyFontOfPreviewPage();

        } catch (Exception e) {
            Assert.fail("Exception in TC textDropDownWriteTheTextThenSelectItAndThenMakeItMonospace of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }



    @Test(priority = 11, enabled = true)
    public void numericEntryWithUnitsSelectTheFontAndWrite() {

        try {
            //TC row no 150
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Numeric Entry w/Units", 150);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().verifyFont(150);
            new TextSend().textsendbycssSelector("10", "input[class='numeric_unit_correct_answer numeric_text_entry numeric_text_entry_input is-value-changed']");
            new Click().clickBycssselector("div[class='unit-arrow-down add-more-num-entry-unit']"); //click on add more units
            new Click().clickBycssselector("li[value='feet']"); //select feet
            new Click().clickByclassname("unit_tick_image"); //click on the tick mark for unit
            new Click().clickBycssselector("div[class='add-more-num-entry-unit unit-arrow-down']"); //click on add more units
            new Click().clickBycssselector("li[value='yards']"); //select yards
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget num_ent_unit_accept_answer']"); //click on accept answer
            manageContent.save_Button.click();//click on save
            Thread.sleep(1000);
            new Assignment().verifyFontOfDuplicateQuestion();

        } catch (Exception e) {
            Assert.fail("Exception in TC numericEntryWithUnitsSelectTheFontAndWrite of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 12, enabled = true)
    public void numericEntryWriteTheTextThenSelectItAndThenMakeItMonospace() {

        try {
            //TC row no 164
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Numeric Entry w/Units",164);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().selectTextAndVerifyFont(164);
            new TextSend().textsendbycssSelector("10", "input[class='numeric_unit_correct_answer numeric_text_entry numeric_text_entry_input is-value-changed']");
            new Click().clickBycssselector("div[class='unit-arrow-down add-more-num-entry-unit']"); //click on add more units
            new Click().clickBycssselector("li[value='feet']"); //select feet
            new Click().clickByclassname("unit_tick_image"); //click on the tick mark for unit
            new Click().clickBycssselector("div[class='add-more-num-entry-unit unit-arrow-down']"); //click on add more units
            new Click().clickBycssselector("li[value='yards']"); //select yards
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget num_ent_unit_accept_answer']"); //click on accept answer
            solutionText();
            new Assignment().verifyFontOfPreviewPage();

        } catch (Exception e) {
            Assert.fail("Exception in TC numericEntryWriteTheTextThenSelectItAndThenMakeItMonospace of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }

    @Test(priority = 13, enabled = true)
    public void advancedNumericSelectTheFontAndWrite() {

        try {
            //TC row no 172
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Advanced Numeric", 172);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().verifyFont(172);
            new TextSend().textsendbycssSelector("10","input[class='numeric_correct_text_entry_input num-entry-ans border-color-gray']");
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer accept_answer_maple_numeric']"); //click on accept answer
            new Click().clickByid("question-raw-content");//click on Question
            manageContent.save_Button.click();//click on save
            Thread.sleep(1000);
            new Assignment().verifyFontOfDuplicateQuestion();

        } catch (Exception e) {
            Assert.fail("Exception in TC advancedNumericSelectTheFontAndWrite of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 14, enabled = true)
    public void advancedNumericWriteTheTextThenSelectItAndThenMakeItMonospace() {

        try {
            //TC row no 186
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Advanced Numeric",186);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().selectTextAndVerifyFont(186);
            new TextSend().textsendbycssSelector("10","input[class='numeric_correct_text_entry_input num-entry-ans border-color-gray']");
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer accept_answer_maple_numeric']"); //click on accept answer
            new Click().clickByid("question-raw-content");//click on Question
            solutionText();
            new Assignment().verifyFontOfPreviewPage();

        } catch (Exception e) {
            Assert.fail("Exception in TC advancedNumericWriteTheTextThenSelectItAndThenMakeItMonospace of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }



    @Test(priority = 15, enabled = true)
    public void expressionEvaluatorSelectTheFontAndWrite() {

        try {
            //TC row no 194
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Expression Evaluator", 194);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().verifyFont(194);
            new Click().clickByid("answer_math_edit");
            enterValueInMathMLEditor("Square root","5");
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer maple_symc_not_accept_answer']"); //click on accept answer
            manageContent.save_Button.click();//click on save
            Thread.sleep(1000);
            new Assignment().verifyFontOfDuplicateQuestion();

        } catch (Exception e) {
            Assert.fail("Exception in TC expressionEvaluatorSelectTheFontAndWrite of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 16, enabled = true)
    public void expressionEvaluatorWriteTheTextThenSelectItAndThenMakeItMonospace() {

        try {
            //TC row no 208
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Expression Evaluator",208);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().selectTextAndVerifyFont(208);
            new Click().clickByid("answer_math_edit");
            enterValueInMathMLEditor("Square root","5");
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer maple_symc_not_accept_answer']"); //click on accept answer
            solutionText();
            new Assignment().verifyFontOfPreviewPage();

        } catch (Exception e) {
            Assert.fail("Exception in TC expressionEvaluatorWriteTheTextThenSelectItAndThenMakeItMonospace of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }




    @Test(priority = 17, enabled = true)
    public void essayTypeSelectTheFontAndWrite() {

        try {
            //TC row no 216
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Essay Type Question", 216);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().verifyFont(216);
            new Click().clickByid("essay-question-height");//click on Min line height textbox
            new TextSend().textsendbyid("3", "essay-question-height");
            manageContent.save_Button.click();//click on save
            Thread.sleep(1000);
            new Assignment().verifyFontOfDuplicateQuestion();

        } catch (Exception e) {
            Assert.fail("Exception in TC essayTypeSelectTheFontAndWrite of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 18, enabled = true)
    public void essayTypeWriteTheTextThenSelectItAndThenMakeItMonospace() {

        try {
            //TC row no 230
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Essay Type Question",230);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().selectTextAndVerifyFont(230);
            new Click().clickByid("essay-question-height");//click on Min line height textbox
            new TextSend().textsendbyid("3", "essay-question-height");
            solutionText();
            new Assignment().verifyFontOfPreviewPage();

        } catch (Exception e) {
            Assert.fail("Exception in TC essayTypeWriteTheTextThenSelectItAndThenMakeItMonospace of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 19, enabled = true)
    public void workBoardSelectTheFontAndWrite() {

        try {
            //TC row no 238
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Workboard", 238);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().verifyFont(238);
            manageContent.save_Button.click();//click on save
            Thread.sleep(1000);
            new Assignment().verifyFontOfDuplicateQuestion();

        } catch (Exception e) {
            Assert.fail("Exception in TC workBoardSelectTheFontAndWrite of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 20, enabled = true)
    public void workBoardWriteTheTextThenSelectItAndThenMakeItMonospace() {

        try {
            //TC row no 252
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Workboard",252);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().selectTextAndVerifyFont(252);
            solutionText();
            new Assignment().verifyFontOfPreviewPage();

        } catch (Exception e) {
            Assert.fail("Exception in TC workBoardWriteTheTextThenSelectItAndThenMakeItMonospace of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }

    @Test(priority = 21, enabled = true)
    public void audioQuestionSelectTheFontAndWrite() {

        try {
            //TC row no 260
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Audio", 260);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().verifyFont(260);
            manageContent.save_Button.click();//click on save
            Thread.sleep(1000);
            new Assignment().verifyFontOfDuplicateQuestion();

        } catch (Exception e) {
            Assert.fail("Exception in TC audioQuestionSelectTheFontAndWrite of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 22, enabled = true)
    public void  audioQuestionWriteTheTextThenSelectItAndThenMakeItMonospace() {

        try {
            //TC row no 274
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Audio",274);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().selectTextAndVerifyFont(274);
            solutionText();
            new Assignment().verifyFontOfPreviewPage();

        } catch (Exception e) {
            Assert.fail("Exception in TC audioQuestionWriteTheTextThenSelectItAndThenMakeItMonospace of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }

    @Test(priority = 23, enabled = true)
    public void matchTheFollowingSelectTheFontAndWrite() {

        try {
            //TC row no 282
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(282));
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Match the following", 282);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().verifyFont(282);
            List<WebElement> lhsboxes = driver.findElements(By.cssSelector("div[class='dnd-match-lhs box']")); //Fetching all the lhs boxes
            lhsboxes.get(0).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","5");

            lhsboxes.get(1).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            lhsboxes.get(2).click();
            new Click().clickByid("answer_image_edit");
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-answer-choice-image");
            new UIElement().waitAndFindElement(By.cssSelector("img[class='answer-choice-img answer-choice-image-size']"));
            lhsboxes.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","6");

            lhsboxes.get(4).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","7");

            //Filling RHS

            List<WebElement> rhsboxes = driver.findElements(By.cssSelector("div[class='dnd-match-rhs box']")); //Fetching all the lhs boxes

            rhsboxes.get(0).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","5");

            rhsboxes.get(1).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            rhsboxes.get(2).click();
            new Click().clickByid("answer_image_edit");
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-answer-choice-image");
            new UIElement().waitAndFindElement(By.cssSelector("img[class='answer-choice-img answer-choice-image-size']"));
            rhsboxes.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","6");

            rhsboxes.get(4).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","7");

            manageContent.save_Button.click();//click on save
            Thread.sleep(1000);
            new Assignment().verifyFontOfDuplicateQuestion();

        } catch (Exception e) {
            Assert.fail("Exception in TC matchTheFollowingSelectTheFontAndWrite of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 24, enabled = true)
    public void  matchTheFollowingWriteTheTextThenSelectItAndThenMakeItMonospace() {

        try {
            //TC row no 296
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(296));
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Match the following",296);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().selectTextAndVerifyFont(296);

            List<WebElement> lhsboxes = driver.findElements(By.cssSelector("div[class='dnd-match-lhs box']")); //Fetching all the lhs boxes
            lhsboxes.get(0).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","5");

            lhsboxes.get(1).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            lhsboxes.get(2).click();
            new Click().clickByid("answer_image_edit");
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-answer-choice-image");
            new UIElement().waitAndFindElement(By.cssSelector("img[class='answer-choice-img answer-choice-image-size']"));
            lhsboxes.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","6");

            lhsboxes.get(4).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","7");

            //Filling RHS

            List<WebElement> rhsboxes =driver.findElements(By.cssSelector("div[class='dnd-match-rhs box']")); //Fetching all the lhs boxes

            rhsboxes.get(0).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","5");

            rhsboxes.get(1).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            rhsboxes.get(2).click();
            new Click().clickByid("answer_image_edit");
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-answer-choice-image");
            new UIElement().waitAndFindElement(By.cssSelector("img[class='answer-choice-img answer-choice-image-size']"));
            rhsboxes.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","6");

            rhsboxes.get(4).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","7");
            solutionText();
            new Assignment().verifyFontOfPreviewPage();

        } catch (Exception e) {
            Assert.fail("Exception in TC matchTheFollowingWriteTheTextThenSelectItAndThenMakeItMonospace of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }



    @Test(priority = 25, enabled = true)
    public void dragDropQuestionSelectTheFontAndWrite() {

        try {
            //TC row no 304
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(304));
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Drag and Drop", 304);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().verifyFont(304);
            List<WebElement> answers = driver.findElements(By.className("answer"));
            answers.get(0).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","5");

            answers.get(1).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            answers.get(2).click();
            new Click().clickByid("answer_image_edit");
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-answer-choice-image");
            new UIElement().waitAndFindElement(By.cssSelector("img[class='answer-choice-image-size']"));

            answers.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","10");

            List<WebElement> answerstodrag = driver.findElements(By.className("answer"));
            Actions ac = new Actions(driver);
            answerstodrag.get(0).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();

            manageContent.save_Button.click();//click on save
            Thread.sleep(1000);
            new Assignment().verifyFontOfDuplicateQuestion();

        } catch (Exception e) {
            Assert.fail("Exception in TC dragDropQuestionSelectTheFontAndWrite of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 26, enabled = true)
    public void  dragDropQuestionWriteTheTextThenSelectItAndThenMakeItMonospace() {

        try {
            //TC row no 318
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(318));
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Drag and Drop",318);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().selectTextAndVerifyFont(318);
            List<WebElement> answers = driver.findElements(By.className("answer"));
            answers.get(0).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","5");
            answers.get(1).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            answers.get(2).click();
            new Click().clickByid("answer_image_edit");
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-answer-choice-image");
            new UIElement().waitAndFindElement(By.cssSelector("img[class='answer-choice-image-size']"));

            answers.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","10");

            List<WebElement> answerstodrag = driver.findElements(By.className("answer"));
            Actions ac = new Actions(driver);
            answerstodrag.get(0).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();

            solutionText();
            new Assignment().verifyFontOfPreviewPage();

        } catch (Exception e) {
            Assert.fail("Exception in TC dragDropQuestionWriteTheTextThenSelectItAndThenMakeItMonospace of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 27, enabled = true)
    public void labelAnImageTextSelectTheFontAndWrite() {

        try {
            //TC row no 326
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(326));

            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Label an image (Text)", 326);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().verifyFont(326);
            List<WebElement> answers = driver.findElements(By.className("labelAnswer"));
            answers.get(0).click();
            driver.switchTo().activeElement().sendKeys("Answer 1");

            answers.get(1).click();
            driver.switchTo().activeElement().sendKeys("Answer 2");

            answers.get(2).click();
            driver.switchTo().activeElement().sendKeys("Answer 3");

            new Click().clickByid("uploadbackgroundImage");//click on upload a back ground image
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-background");
            new UIElement().waitAndFindElement(By.xpath("//div[contains(@class,'draggable ui-widget-content')]"));


            List<WebElement> answerstodrag = driver.findElements(By.className("labelAnswer"));
            Actions ac = new Actions(driver);
            answerstodrag.get(0).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();
            Thread.sleep(1000);
            manageContent.save_Button.click();//click on save
            Thread.sleep(1000);
            new Assignment().verifyFontOfDuplicateQuestion();

        } catch (Exception e) {
            Assert.fail("Exception in TC labelAnImageTextSelectTheFontAndWrite of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 28, enabled = true)
    public void labelAnImageTextWriteTheTextThenSelectItAndThenMakeItMonospace() {

        try {
            //TC row no 340
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(340));
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Label an image (Text)",340);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().selectTextAndVerifyFont(340);
            List<WebElement> answers = driver.findElements(By.className("labelAnswer"));
            answers.get(0).click();
            driver.switchTo().activeElement().sendKeys("Answer 1");

            answers.get(1).click();
            driver.switchTo().activeElement().sendKeys("Answer 2");

            answers.get(2).click();
            driver.switchTo().activeElement().sendKeys("Answer 3");

            new Click().clickByid("uploadbackgroundImage");//click on upload a back ground image
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-background");
            new UIElement().waitAndFindElement(By.xpath("//div[contains(@class,'draggable ui-widget-content')]"));

            List<WebElement> answerstodrag = driver.findElements(By.className("labelAnswer"));
            Actions ac = new Actions(driver);
            answerstodrag.get(0).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();
            Thread.sleep(1000);
            solutionText();
            new Assignment().verifyFontOfPreviewPage();

        } catch (Exception e) {
            Assert.fail("Exception in TC labelAnImageTextWriteTheTextThenSelectItAndThenMakeItMonospace of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }

    @Test(priority = 29, enabled = true)
    public void labelAnImageDropDownSelectTheFontAndWrite() {

        try {
            //TC row no 348
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(348));

            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Label an image (Dropdown)", 348);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().verifyFont(348);

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

            new Click().clickByid("uploadbackgroundImage");//click on upload a back ground image
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-background");
            new UIElement().waitAndFindElement(By.xpath("//div[contains(@class,'draggable ui-widget-content')]"));

            List<WebElement> answerstodrag = driver.findElements(By.className("label-drpdwn-answerChoice-style"));
            Actions ac = new Actions(driver);
            answerstodrag.get(0).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();
            answerstodrag.get(1).click();
            ac.dragAndDropBy(driver.findElement(By.id("ans-drag-btn")), 520,130).build().perform();
            answerstodrag.get(2).click();
            ac.dragAndDropBy(driver.findElement(By.id("ans-drag-btn")),540,150).build().perform();

            manageContent.save_Button.click();//click on save
            Thread.sleep(1000);
            new Assignment().verifyFontOfDuplicateQuestion();

        } catch (Exception e) {
            Assert.fail("Exception in TC labelAnImageDropDownSelectTheFontAndWrite of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 30, enabled = true)
    public void labelAnImageDropDownWriteTheTextThenSelectItAndThenMakeItMonospace() {

        try {
            //TC row no 362
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(362));
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Label an image (Dropdown)",362);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Assignment().selectTextAndVerifyFont(362);

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


            new Click().clickByid("uploadbackgroundImage");//click on upload a back ground image
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-background");
            new UIElement().waitAndFindElement(By.xpath("//div[contains(@class,'draggable ui-widget-content')]"));

            List<WebElement> answerstodrag = driver.findElements(By.className("label-drpdwn-answerChoice-style"));
            Actions ac = new Actions(driver);
            answerstodrag.get(0).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();
            answerstodrag.get(1).click();
            ac.dragAndDropBy(driver.findElement(By.id("ans-drag-btn")), 520,130).build().perform();
            answerstodrag.get(2).click();
            ac.dragAndDropBy(driver.findElement(By.id("ans-drag-btn")),540,150).build().perform();

            solutionText();
            new Assignment().verifyFontOfPreviewPage();

        } catch (Exception e) {
            Assert.fail("Exception in TC labelAnImageDropDownWriteTheTextThenSelectItAndThenMakeItMonospace of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    @Test(priority = 31, enabled = true)
    public void multiPartSelectTheFontAndWrite() {

        try {
            //TC row no 370
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLSCourse();//select course geography
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Multi Part", 370);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-mp-raw-content-0")));
            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            new Assignment().verifyFont(370);
            new Click().clickByid("one");
            manageContent.save_Button.click();//click on save
            new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
            new Click().clickByid("qtn-type-true-false-img");//click on true false type question
            new TextSend().textsendbyid("1", "mpq-question-part-input-label");//enter label in true false MPQ
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("True False For MPQ");//type the question
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option A
            manageContent.save_Button.click();//click on save
            Thread.sleep(1000);
            new Click().clickByid("cms-mpq-back-button");//click on back button
            manageContent.preview_Button.click();//click on preview
            String parentWindow = driver.getWindowHandle();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
            /* String font = manageContent.question_Text.getCssValue("font-family");
            Assert.assertEquals(font,"monospace");*/
            driver.close();
            driver.switchTo().window(parentWindow);
            new Click().clickByclassname("cms-multipart-add-stem");//Click on "Add question stem" button
            new Click().clickByid("question-mp-raw-content-2");
            new TextSend().textsendbyid("stemquestion", "question-mp-raw-content-2");//question for stem 2
            Actions actions = new Actions(driver);
            WebElement element = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            actions.moveToElement(element, 10, 30)
                    .clickAndHold()
                    .moveByOffset(500,0)
                    .release()
                    .perform();
            manageContent.fontFamilyIcon.get(0).click();//click on font family icon
            manageContent.monoSpaceFont.get(1).click();//click on mono space font
            String font = manageContent.questionContent.getCssValue("font-family");
            Assert.assertEquals(font,"monospace","The font of selected text is not changed in monospace Font.");
            new Click().clickByid("saveQuestionDetails1");//click on Save
            manageContent.preview_Button.click();//click on preview
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-preview-header-logo")));
           /* String font = manageContent.question_Text.getCssValue("font-family");
            Assert.assertEquals(font,"monospace");*/
        } catch (Exception e) {
            Assert.fail("Exception in TC multiPartSelectTheFontAndWrite of class PublisherAbleToSupportNewFontsForQuestions", e);
        }
    }


    public  void enterValueInMathMLEditor(String operation,String value)
    {
        try {

            driver.findElement(By.cssSelector("button[title='"+operation+"']")).click();
            driver.findElement(By.className("wrs_focusElement")).sendKeys(value);
            driver.findElement(By.id("wiris-answer-container-save-choice1")).click();
        }
        catch (Exception e){
            Assert.fail("Exception while entering value in Math ML editor");
        }
    }



    public void solutionText(){
        try{

            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            driver.findElement(By.id("content-solution")).sendKeys("Solution Text");
            Actions actions = new Actions(driver);
            WebElement element = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.id("content-solution")));
            actions.moveToElement(element, 10, 30)
                    .clickAndHold()
                    .moveByOffset(500,0)
                    .release()
                    .perform();

            List<WebElement> fontFamily = manageContent.fontFamilyIcon;
            for(WebElement fonts: fontFamily) {
                if (fonts.isDisplayed()) {
                    fonts.click();
                }
            }
            Thread.sleep(2000);
            List<WebElement> monoSpace = driver.findElements(By.xpath("//a[text()='Monospace']"));
            for(WebElement monoSpaces: monoSpace) {
                if (monoSpaces.isDisplayed()) {
                    monoSpaces.click();
                }
            }

            driver.findElement(By.id("content-hint")).sendKeys("Hint Text");
            WebElement element1 = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.id("content-hint")));
            actions.moveToElement(element1, 10, 30)
                    .clickAndHold()
                    .moveByOffset(500,0)
                    .release()
                    .perform();

            for(WebElement fonts: fontFamily) {
                if (fonts.isDisplayed()) {
                    fonts.click();
                }
            }
            Thread.sleep(2000);
            for(WebElement monoSpaces: monoSpace) {
                if (monoSpaces.isDisplayed()) {
                    monoSpaces.click();
                }
            }
            new Click().clickbylinkText("Draft"); //click on Draft option
            new Click().clickbylinkText("Publish");//click on Publish option if isPublished is null or true
            new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
            manageContent.save_Button.click();//click on save
            Thread.sleep(1000);
        }

        catch (Exception e){

            Assert.fail("Exception while entering solution text");

        }
    }
}

