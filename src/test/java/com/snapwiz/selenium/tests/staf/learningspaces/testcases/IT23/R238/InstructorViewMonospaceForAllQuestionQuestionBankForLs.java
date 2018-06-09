package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R238;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 9/16/2015.
 */
public class InstructorViewMonospaceForAllQuestionQuestionBankForLs extends Driver {

    @DataProvider(name = "questionType")
    public Object[][] getQuestionTypes() {
        return new Object[][] {
                {"TrueFalseText_IT23_R238"},
                {"MultipleChoiceText_IT23_R238"},
                {"MultipleSelectionText_IT23_R238"},
                {"TextEntryText_IT23_R238"},
                {"TextDropDownText_IT23_R238"},
                {"NumericEntryText_IT23_R238"},
                {"AdvancedNumericText_IT23_R238"},
                {"ExpressionEvaluatorText_IT23_R238"},
                {"EssayTypeText_IT23_R238"},
                {"WorkBoardText_IT23_R238"},
                {"AudioText_IT23_R238"},
                {"MatchTheFollowingText_IT23_R238"},
                {"DragAndDrpText_IT23_R238"},
                {"LabelAnImageTextText_IT23_R238"},
                {"LabelAnImageDropDownText_IT23_R238"},
        };
    }
        @Test(priority = 1, enabled = true)
        public void instructorViewMonospaceForTrueFalseQuestionBank() {

            try {
                //TC row no 623
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(623));
                QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
                MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
                NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);

                new DirectLogin().CMSLogin();//login in cms
                new SelectCourse().selectLSCourse();//select course geography
                new SelectCourse().selectChapterByIndex(0);//select 1st chapter
                new Assignment().selectParticularQuestionInCMS("True / False", 623);
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("true-false-answer-select")));
                new Assignment().selectTextAndVerifyFont(623);
                manageContent.turFalseAnswer.click();//click on Answer Option A
                new Click().clickbylinkText("Draft"); //click on Draft option
                new Click().clickbylinkText("Publish");//click on Publish option if isPublished is null or true
                new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
                manageContent.save_Button.click();//click on save

                new LoginUsingLTI().ltiLogin("623"); //login as instructor
                new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
                questionBank.getQuestionBankTitle().click();//click on question bank tab
                Thread.sleep(2000);
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
                verifyFont(623);

            } catch (Exception e) {
                Assert.fail("Exception in TC instructorViewMonospaceForTrueFalseQuestionBank of class InstructorViewMonospaceForAllQuestionQuestionBank", e);
            }
        }


        @Test(priority = 2, enabled = true)
        public void instructorViewMonospaceForMultipleChoiceQuestionBank() {

            try {
                //TC row no 6231
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(6231));
                QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
                new DirectLogin().CMSLogin();//login in cms
                new SelectCourse().selectLSCourse();//select course geography
                new SelectCourse().selectChapterByIndex(0);//select 1st chapter
                new Assignment().selectParticularQuestionInCMS("Multiple Choice", 6231);
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-mc-raw-content")));
                new Assignment().selectTextAndVerifyFont(6231);
                new Click().clickBycssselector("div[class='single-select-choice-icon single-select-choice-icon-deselect']"); //select correct answer as A
                List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
                answerOptions.get(0).sendKeys("Option 1");
                answerOptions.get(1).sendKeys("Option 2");
                answerOptions.get(2).sendKeys("Option 3");
                answerOptions.get(3).sendKeys("Option 4");
                new Click().clickbylinkText("Draft"); //click on Draft option
                new Click().clickbylinkText("Publish");//click on Publish option if isPublished is null or true
                new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
                manageContent.save_Button.click();//click on save
                new LoginUsingLTI().ltiLogin("6231"); //login as instructor
                new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
                questionBank.getQuestionBankTitle().click();//click on question bank tab
                Thread.sleep(2000);
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
                verifyFont(6231);


            } catch (Exception e) {
                Assert.fail("Exception in TC instructorViewMonospaceForMultipleChoiceQuestionBank of class InstructorViewMonospaceForAllQuestionQuestionBank", e);
            }
        }


        @Test(priority = 3, enabled = true)
        public void instructorViewMonospaceForMultipleSelectionQuestionBank() {

            try {
                //TC row no 6232
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(6232));
                QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
                new DirectLogin().CMSLogin();//login in cms
                new SelectCourse().selectLSCourse();//select course geography
                new SelectCourse().selectChapterByIndex(0);//select 1st chapter
                new Assignment().selectParticularQuestionInCMS("Multiple Selection", 6232);
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-ms-raw-content")));
                new Assignment().selectTextAndVerifyFont(6232);
                List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
                answerOptions.get(0).sendKeys("Option 1");
                answerOptions.get(1).sendKeys("Option 2");
                answerOptions.get(2).sendKeys("Option 3");
                answerOptions.get(3).sendKeys("Option 4");
                new Click().clickByid("swuploadclose");//close pop-up
                List<WebElement> multipleSelections = driver.findElements(By.cssSelector("div[class='multiple-select-choice-icon multiple-select-choice-icon-deselect']"));
                multipleSelections.get(0).click();
                multipleSelections.get(1).click(); //selecting option A and B as correct answers
                new Click().clickbylinkText("Draft"); //click on Draft option
                new Click().clickbylinkText("Publish");//click on Publish option if isPublished is null or true
                new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
                manageContent.save_Button.click();//click on save
                new LoginUsingLTI().ltiLogin("6232"); //login as instructor
                new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
                questionBank.getQuestionBankTitle().click();//click on question bank tab
                Thread.sleep(2000);
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
                verifyFont(6232);

            } catch (Exception e) {
                Assert.fail("Exception in TC instructorViewMonospaceForMultipleSelectionQuestionBank of class InstructorViewMonospaceForAllQuestionQuestionBank", e);
            }
        }


        @Test(priority = 4, enabled = true)
        public void instructorViewMonospaceForTextEntryQuestionBank() {

            try {
                //TC row no 6233
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(6233));
                QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
                new DirectLogin().CMSLogin();//login in cms
                new SelectCourse().selectLSCourse();//select course geography
                new SelectCourse().selectChapterByIndex(0);//select 1st chapter
                new Assignment().selectParticularQuestionInCMS("Text Entry", 6233);
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
                new Assignment().selectTextAndVerifyFont(6233);
                new TextSend().textsendbycssSelector("Correct Answer", "input[class='numeric_text_entry_input get-user-entry is-value-changed-txt-entry']");
                new Click().clickBycssselector("span[class='btn sty-green save-language-text accept_answer text_entry_accept_answer']"); //click on Accept answer button
                new Click().clickbylinkText("Draft"); //click on Draft option
                new Click().clickbylinkText("Publish");//click on Publish option if isPublished is null or true
                new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
                manageContent.save_Button.click();//click on save
                new LoginUsingLTI().ltiLogin("6233"); //login as instructor
                new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
                questionBank.getQuestionBankTitle().click();//click on question bank tab
                Thread.sleep(2000);
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
                verifyFont(6233);

            } catch (Exception e) {
                Assert.fail("Exception in TC instructorViewMonospaceForTextEntryQuestionBank of class InstructorViewMonospaceForAllQuestionQuestionBank", e);
            }
        }


        @Test(priority = 5, enabled = true)
        public void instructorViewMonospaceForTextDropDownQuestionBank() {

            try {
                //TC row no 6234
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(6234));
                QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
                new DirectLogin().CMSLogin();//login in cms
                new SelectCourse().selectLSCourse();//select course geography
                new SelectCourse().selectChapterByIndex(0);//select 1st chapter
                new Assignment().selectParticularQuestionInCMS("Text Drop Down", 6234);
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
                new Assignment().selectTextAndVerifyFont(6234);
                new Click().clickByclassname("text-drop-val");
                new TextSend().textsendbyid("Answer1", "ans1");
                new Click().clickByclassname("select-icon-text-drop-down"); //select answer 1 as correct answer
                driver.findElements(By.className("text-drop-val")).get(1).click();
                driver.findElements(By.id("ans1")).get(1).sendKeys("Answer2");
                driver.findElements(By.className("text-drop-val")).get(2).click();
                driver.findElements(By.id("ans1")).get(2).sendKeys("Answer3");
                new Click().clickBycssselector("span[class='done-button btn sty-green text-drop-accept accept_answer']"); //click on accept answer
                new Click().clickbylinkText("Draft"); //click on Draft option
                new Click().clickbylinkText("Publish");//click on Publish option if isPublished is null or true
                new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
                manageContent.save_Button.click();//click on save
                new LoginUsingLTI().ltiLogin("6234"); //login as instructor
                new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
                questionBank.getQuestionBankTitle().click();//click on question bank tab
                Thread.sleep(2000);
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
                Thread.sleep(2000);
                verifyFont(6234);

            } catch (Exception e) {
                Assert.fail("Exception in TC instructorViewMonospaceForTextDropDownQuestionBank of class InstructorViewMonospaceForAllQuestionQuestionBank", e);
            }
        }


        @Test(priority = 6, enabled = true)
        public void instructorViewMonospaceForNumericEntryQuestionBank() {

            try {
                //TC row no 6235
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(6235));
                QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
                new DirectLogin().CMSLogin();//login in cms
                new SelectCourse().selectLSCourse();//select course geography
                new SelectCourse().selectChapterByIndex(0);//select 1st chapter
                new Assignment().selectParticularQuestionInCMS("Numeric Entry w/Units", 6235);
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
                new Assignment().selectTextAndVerifyFont(6235);
                new TextSend().textsendbycssSelector("10", "input[class='numeric_unit_correct_answer numeric_text_entry numeric_text_entry_input is-value-changed']");
                new Click().clickBycssselector("div[class='unit-arrow-down add-more-num-entry-unit']"); //click on add more units
                new Click().clickBycssselector("li[value='feet']"); //select feet
                new Click().clickByclassname("unit_tick_image"); //click on the tick mark for unit
                new Click().clickBycssselector("div[class='add-more-num-entry-unit unit-arrow-down']"); //click on add more units
                new Click().clickBycssselector("li[value='yards']"); //select yards
                new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget num_ent_unit_accept_answer']"); //click on accept answer
                new Click().clickbylinkText("Draft"); //click on Draft option
                new Click().clickbylinkText("Publish");//click on Publish option if isPublished is null or true
                new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
                manageContent.save_Button.click();//click on save
                new LoginUsingLTI().ltiLogin("6235"); //login as instructor
                new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
                questionBank.getQuestionBankTitle().click();//click on question bank tab
                Thread.sleep(2000);
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
                Thread.sleep(2000);
                verifyFont(6235);

            } catch (Exception e) {
                Assert.fail("Exception in TC instructorViewMonospaceForNumericEntryQuestionBank of class InstructorViewMonospaceForAllQuestionQuestionBank", e);
            }
        }


        @Test(priority = 7, enabled = true)
        public void instructorViewMonospaceForAdvancedNumericQuestionBank() {

            try {
                //TC row no 6236
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(6236));
                QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
                new DirectLogin().CMSLogin();//login in cms
                new SelectCourse().selectLSCourse();//select course geography
                new SelectCourse().selectChapterByIndex(0);//select 1st chapter
                new Assignment().selectParticularQuestionInCMS("Advanced Numeric", 6236);
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
                new Assignment().selectTextAndVerifyFont(6236);
                new TextSend().textsendbycssSelector("10", "input[class='numeric_correct_text_entry_input num-entry-ans border-color-gray']");
                new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer accept_answer_maple_numeric']"); //click on accept answer
                new Click().clickByid("question-raw-content");//click on Question
                new Click().clickbylinkText("Draft"); //click on Draft option
                new Click().clickbylinkText("Publish");//click on Publish option if isPublished is null or true
                new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
                manageContent.save_Button.click();//click on save
                new LoginUsingLTI().ltiLogin("6236"); //login as instructor
                new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
                questionBank.getQuestionBankTitle().click();//click on question bank tab
                Thread.sleep(2000);
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
                Thread.sleep(2000);
                verifyFont(6236);

            } catch (Exception e) {
                Assert.fail("Exception in TC instructorViewMonospaceForAdvancedNumericQuestionBank of class InstructorViewMonospaceForAllQuestionQuestionBank", e);
            }
        }

        @Test(priority = 8, enabled = true)
        public void instructorViewMonospaceForExpressionEvaluatorQuestionBank() {

            try {
                //TC row no 6237
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(6237));
                QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
                new DirectLogin().CMSLogin();//login in cms
                new SelectCourse().selectLSCourse();//select course geography
                new SelectCourse().selectChapterByIndex(0);//select 1st chapter
                new Assignment().selectParticularQuestionInCMS("Expression Evaluator", 6237);
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
                new Assignment().selectTextAndVerifyFont(6237);
                new Click().clickByid("answer_math_edit");
                new PublisherAbleToSupportNewFontsForQuestions().enterValueInMathMLEditor("Square root", "5");
                new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer maple_symc_not_accept_answer']"); //click on accept answer
                new Click().clickbylinkText("Draft"); //click on Draft option
                new Click().clickbylinkText("Publish");//click on Publish option if isPublished is null or true
                new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
                manageContent.save_Button.click();//click on save
                new LoginUsingLTI().ltiLogin("6237"); //login as instructor
                new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
                questionBank.getQuestionBankTitle().click();//click on question bank tab
                Thread.sleep(2000);
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
                Thread.sleep(2000);
                verifyFont(6237);

            } catch (Exception e) {
                Assert.fail("Exception in TC instructorViewMonospaceForExpressionEvaluatorQuestionBank of class InstructorViewMonospaceForAllQuestionQuestionBank", e);
            }
        }


        @Test(priority = 9, enabled = true)
        public void instructorViewMonospaceForEssayTypeQuestionBank() {

            try {
                //TC row no 6238
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(6238));
                QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
                new DirectLogin().CMSLogin();//login in cms
                new SelectCourse().selectLSCourse();//select course geography
                new SelectCourse().selectChapterByIndex(0);//select 1st chapter
                new Assignment().selectParticularQuestionInCMS("Essay Type Question", 6238);
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
                new Assignment().selectTextAndVerifyFont(6238);
                new Click().clickByid("essay-question-height");//click on Min line height textbox
                new TextSend().textsendbyid("3", "essay-question-height");
                new Click().clickbylinkText("Draft"); //click on Draft option
                new Click().clickbylinkText("Publish");//click on Publish option if isPublished is null or true
                new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
                manageContent.save_Button.click();//click on save
                new LoginUsingLTI().ltiLogin("6238"); //login as instructor
                new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
                questionBank.getQuestionBankTitle().click();//click on question bank tab
                Thread.sleep(2000);
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
                Thread.sleep(2000);
                verifyFont(6238);

            } catch (Exception e) {
                Assert.fail("Exception in TC instructorViewMonospaceForEssayTypeQuestionBank of class InstructorViewMonospaceForAllQuestionQuestionBank", e);
            }
        }


        @Test(priority = 10, enabled = true)
        public void instructorViewMonospaceForWorkBoardQuestionBank() {

            try {
                //TC row no 6239
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(6239));
                QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
                new DirectLogin().CMSLogin();//login in cms
                new SelectCourse().selectLSCourse();//select course geography
                new SelectCourse().selectChapterByIndex(0);//select 1st chapter
                new Assignment().selectParticularQuestionInCMS("Workboard", 6239);
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
                new Assignment().selectTextAndVerifyFont(6239);
                new Click().clickbylinkText("Draft"); //click on Draft option
                new Click().clickbylinkText("Publish");//click on Publish option if isPublished is null or true
                new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
                manageContent.save_Button.click();//click on save
                new LoginUsingLTI().ltiLogin("6239"); //login as instructor
                new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
                questionBank.getQuestionBankTitle().click();//click on question bank tab
                Thread.sleep(2000);
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
                Thread.sleep(2000);
                verifyFont(6239);

            } catch (Exception e) {
                Assert.fail("Exception in TC instructorViewMonospaceForWorkBoardQuestionBank of class InstructorViewMonospaceForAllQuestionQuestionBank", e);
            }
        }


        @Test(priority = 10, enabled = true)
        public void instructorViewMonospaceForAudioQuestionBank() {

            try {
                //TC row no 62310
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(62310));
                QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
                new DirectLogin().CMSLogin();//login in cms
                new SelectCourse().selectLSCourse();//select course geography
                new SelectCourse().selectChapterByIndex(0);//select 1st chapter
                new Assignment().selectParticularQuestionInCMS("Audio", 62310);
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
                new Assignment().selectTextAndVerifyFont(62310);
                new Click().clickbylinkText("Draft"); //click on Draft option
                new Click().clickbylinkText("Publish");//click on Publish option if isPublished is null or true
                new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
                manageContent.save_Button.click();//click on save
                new LoginUsingLTI().ltiLogin("62310"); //login as instructor
                new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
                questionBank.getQuestionBankTitle().click();//click on question bank tab
                Thread.sleep(2000);
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
                Thread.sleep(2000);
                verifyFont(62310);

            } catch (Exception e) {
                Assert.fail("Exception in TC instructorViewMonospaceForAudioQuestionBank of class InstructorViewMonospaceForAllQuestionQuestionBank", e);
            }
        }


        @Test(priority = 11, enabled = true)
        public void instructorViewMonospaceForMatchTheFollowingQuestionBank() {

            try {
                //TC row no 62311
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(62311));
                QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
                String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(62311));

                new DirectLogin().CMSLogin();//login in cms
                new SelectCourse().selectLSCourse();//select course geography
                new SelectCourse().selectChapterByIndex(0);//select 1st chapter
                new Assignment().selectParticularQuestionInCMS("Match the following", 62311);
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
                new Assignment().selectTextAndVerifyFont(62311);

                List<WebElement> lhsboxes = driver.findElements(By.cssSelector("div[class='dnd-match-lhs box']")); //Fetching all the lhs boxes
                lhsboxes.get(0).click();
                driver.findElement(By.id("answer_math_edit")).click();
                new PublisherAbleToSupportNewFontsForQuestions().enterValueInMathMLEditor("Square root", "5");

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
                new PublisherAbleToSupportNewFontsForQuestions().enterValueInMathMLEditor("Square root", "6");

                lhsboxes.get(4).click();
                driver.findElement(By.id("answer_math_edit")).click();
                new PublisherAbleToSupportNewFontsForQuestions().enterValueInMathMLEditor("Square root", "7");

                //Filling RHS

                List<WebElement> rhsboxes = driver.findElements(By.cssSelector("div[class='dnd-match-rhs box']")); //Fetching all the lhs boxes

                rhsboxes.get(0).click();
                driver.findElement(By.id("answer_math_edit")).click();
                new PublisherAbleToSupportNewFontsForQuestions().enterValueInMathMLEditor("Square root", "5");

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
                new PublisherAbleToSupportNewFontsForQuestions().enterValueInMathMLEditor("Square root", "6");

                rhsboxes.get(4).click();
                driver.findElement(By.id("answer_math_edit")).click();
                new PublisherAbleToSupportNewFontsForQuestions().enterValueInMathMLEditor("Square root", "7");
                new Click().clickbylinkText("Draft"); //click on Draft option
                new Click().clickbylinkText("Publish");//click on Publish option if isPublished is null or true
                new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
                manageContent.save_Button.click();//click on save

                new LoginUsingLTI().ltiLogin("62311"); //login as instructor
                new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
                questionBank.getQuestionBankTitle().click();//click on question bank tab
                Thread.sleep(2000);
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
                Thread.sleep(2000);
                verifyFont(62311);

            } catch (Exception e) {
                Assert.fail("Exception in TC instructorViewMonospaceForMatchTheFollowingQuestionBank of class InstructorViewMonospaceForAllQuestionQuestionBank", e);
            }
        }


        @Test(priority = 12, enabled = true)
        public void instructorViewMonospaceForDragDropQuestionBank() {

            try {
                //TC row no 62312
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(62312));
                QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
                String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(62312));

                new DirectLogin().CMSLogin();//login in cms
                new SelectCourse().selectLSCourse();//select course geography
                new SelectCourse().selectChapterByIndex(0);//select 1st chapter
                new Assignment().selectParticularQuestionInCMS("Drag and Drop", 62312);
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
                new Assignment().selectTextAndVerifyFont(62312);
                List<WebElement> answers = driver.findElements(By.className("answer"));
                answers.get(0).click();
                driver.findElement(By.id("answer_math_edit")).click();
                new PublisherAbleToSupportNewFontsForQuestions().enterValueInMathMLEditor("Square root", "5");
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
                new PublisherAbleToSupportNewFontsForQuestions().enterValueInMathMLEditor("Square root", "10");

                List<WebElement> answerstodrag = driver.findElements(By.className("answer"));
                Actions ac = new Actions(driver);
                answerstodrag.get(0).click();
                ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")), driver.findElement(By.id("canvas"))).build().perform();
                new Click().clickbylinkText("Draft"); //click on Draft option
                new Click().clickbylinkText("Publish");//click on Publish option if isPublished is null or true
                new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
                manageContent.save_Button.click();//click on save

                new LoginUsingLTI().ltiLogin("62312"); //login as instructor
                new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
                questionBank.getQuestionBankTitle().click();//click on question bank tab
                Thread.sleep(2000);
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
                Thread.sleep(2000);
                verifyFont(62312);

            } catch (Exception e) {
                Assert.fail("Exception in TC instructorViewMonospaceForDragDropQuestionBank of class InstructorViewMonospaceForAllQuestionQuestionBank", e);
            }
        }


        @Test(priority = 13, enabled = true)
        public void instructorViewMonospaceForLabelAnImageTextQuestionBank() {

            try {
                //TC row no 62313
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(62313));
                QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
                String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(62313));

                new DirectLogin().CMSLogin();//login in cms
                new SelectCourse().selectLSCourse();//select course geography
                new SelectCourse().selectChapterByIndex(0);//select 1st chapter
                new Assignment().selectParticularQuestionInCMS("Label an image (Text)", 62313);
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
                new Assignment().selectTextAndVerifyFont(62313);
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
                ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")), driver.findElement(By.id("canvas"))).build().perform();
                Thread.sleep(1000);
                new Click().clickbylinkText("Draft"); //click on Draft option
                new Click().clickbylinkText("Publish");//click on Publish option if isPublished is null or true
                new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
                manageContent.save_Button.click();//click on save

                new LoginUsingLTI().ltiLogin("62313"); //login as instructor
                new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
                questionBank.getQuestionBankTitle().click();//click on question bank tab
                Thread.sleep(2000);
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
                Thread.sleep(2000);
                verifyFont(62313);

            } catch (Exception e) {
                Assert.fail("Exception in TC instructorViewMonospaceForLabelAnImageTextQuestionBank of class InstructorViewMonospaceForAllQuestionQuestionBank", e);
            }
        }


        @Test(priority = 14, enabled = true)
        public void instructorViewMonospaceForLabelAnImageDropDownQuestionBank() {

            try {
                //TC row no 62314
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(62314));
                QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
                String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(62314));

                new DirectLogin().CMSLogin();//login in cms
                new SelectCourse().selectLSCourse();//select course geography
                new SelectCourse().selectChapterByIndex(0);//select 1st chapter
                new Assignment().selectParticularQuestionInCMS("Label an image (Dropdown)", 62314);
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
                new Assignment().selectTextAndVerifyFont(62314);

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
                ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")), driver.findElement(By.id("canvas"))).build().perform();
                answerstodrag.get(1).click();
                ac.dragAndDropBy(driver.findElement(By.id("ans-drag-btn")), 520, 130).build().perform();
                answerstodrag.get(2).click();
                ac.dragAndDropBy(driver.findElement(By.id("ans-drag-btn")), 540, 150).build().perform();

                new Click().clickbylinkText("Draft"); //click on Draft option
                new Click().clickbylinkText("Publish");//click on Publish option if isPublished is null or true
                new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
                manageContent.save_Button.click();//click on save

                new LoginUsingLTI().ltiLogin("62314"); //login as instructor
                new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
                questionBank.getQuestionBankTitle().click();//click on question bank tab
                Thread.sleep(2000);
                driver.findElement(By.id("all-resource-search-textarea")).clear();
                driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
                driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
                Thread.sleep(2000);
                verifyFont(62314);

            } catch (Exception e) {
                Assert.fail("Exception in TC instructorViewMonospaceForLabelAnImageDropDownQuestionBank of class InstructorViewMonospaceForAllQuestionQuestionBank", e);
            }
        }




    @Test(priority = 15, enabled = true,dataProvider = "questionType")
    public void instructorViewMonospaceFromMyQuestionBankEditThis(String searchquestion) {

        try {
            //tc row no 617
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "617");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver,MyQuestionBank.class);
            new LoginUsingLTI().ltiLogin("617");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("617");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            myQuestionBank.closeTab.click();//click on 'x' on of new assignment
            Thread.sleep(2000);
            myQuestionBank.editThis.click();//click on edit this
            Assert.assertEquals(newAssignment.answerBar.get(0).getCssValue("font-family"), "monospace", "question text is not in monospace font");


        } catch (Exception e) {
            Assert.fail("Exception in TC instructorViewMonospaceFromMyQuestionBankEditThis of class InstructorViewMonospaceForAllQuestionQuestionBank", e);
        }
    }


    public void verifyFont(int dataindex) {

        try {

            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataindex));
            Thread.sleep(2000);

            questionBank.tryItLink.get(0).click();//click on tryit
            String parentWindow = driver.getWindowHandle();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            String font = manageContent.question_Text.getCssValue("font-family");
            Assert.assertEquals(font, "monospace", "The text is not displaying in the monospace font.");
            driver.close();
            driver.switchTo().window(parentWindow);
            Thread.sleep(2000);
            questionBank.getPreviewButton().click();//click on preview button
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            String font1 = manageContent.question_Text.getCssValue("font-family");
            Assert.assertEquals(font1, "monospace", "The text is not displaying in the monospace font.");
            questionBank.getQuestionBankTitle().click();//click on question bank tab
            Thread.sleep(2000);
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(2000);
            myQuestionBank.customizeThis.click();//click on customize this
            Assert.assertEquals(newAssignment.search_Text.get(0).getCssValue("font-family"), "monospace", "question text is not in monospace font");
            questionBank.getQuestionBankTitle().click();//click on question bank tab
            Thread.sleep(2000);
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(2000);
            questionBank.getAddToMyQuestionBank().click();//click on add to my question bank
            Thread.sleep(1000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank tab
            Thread.sleep(8000);
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            List<WebElement> preview=driver.findElements(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",preview.get(0));
            Thread.sleep(8000);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            String font2 = manageContent.question_Text.getCssValue("font-family");
            Assert.assertEquals(font2, "monospace", "The text is not displaying in the monospace font.");
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank tab
            Thread.sleep(5000);
            myQuestionBank.tryItIcon.get(0).click();//click on tryit
            String parentWindow1 = driver.getWindowHandle();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            String font3 = manageContent.question_Text.getCssValue("font-family");
            Assert.assertEquals(font3, "monospace", "The text is not displaying in the monospace font.");
            driver.close();
            driver.switchTo().window(parentWindow1);
            myQuestionBank.customizeThis.click();//click on customize this
            Assert.assertEquals(newAssignment.search_Text.get(0).getCssValue("font-family"), "monospace", "question text is not in monospace font");

        } catch (Exception e) {
                Assert.fail("Exception in TC verifyFont of class InstructorViewMonospaceForAllQuestionQuestionBank", e);
            }
        }
    }


