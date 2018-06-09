package com.snapwiz.selenium.tests.staf.datacreation.apphelper;

import com.snapwiz.selenium.tests.staf.datacreation.Config;
import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import com.snapwiz.selenium.tests.staf.datacreation.ReadTestData;
import com.snapwiz.selenium.tests.staf.datacreation.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.datacreation.uihelper.Click;
import com.snapwiz.selenium.tests.staf.datacreation.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.datacreation.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

/*
 * Created by Sumit on 15/9/2014.
 */

public class QuestionCreate {

    public void trueFalseQuestions(int dataIndex)
    {
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String withImage = ReadTestData.readDataByTagName("", "withImage", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            new Click().clickbylistid("qtn-type-true-false-img", 0);//click on True/False type question
            new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-checked']");//Uncheck shuffle answer choice
            if(shuffleAnswer != null) {
                if (shuffleAnswer.equals("true"))
                    new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-unchecked']");//check shuffle answer choice
            }
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("True False "+questiontext);//type the question
            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                }
            }

            if(withImageInHint!=null)
            {
                if(withImageInHint.equals("true"))
                {
                    new Click().clickByid("content-hint");//click on Hint textbox
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                }
            }

            if(withImageInSolution!=null)
            {
                if(withImageInSolution.equals("true"))
                {
                    new Click().clickByid("content-solution");//click on Solution textbox
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                }
            }
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option A
            Thread.sleep(2000);

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper QuestionCreate in method trueFalseQuestions.", e);
        }
    }
    public void multipleChoice(int dataIndex)
    {
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            new Click().clickbylistid("qtn-multiple-choice-img", 0);//click on Multiple Choice type question
            new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-checked']");//Uncheck shuffle answer choice
            if(shuffleAnswer != null) {
                if (shuffleAnswer.equals("true"))
                    new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-unchecked']");//check shuffle answer choice
            }
            new Click().clickByid("question-mc-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-mc-raw-content")).sendKeys("Multiple Choice "+questiontext);//type the question
            new Click().clickBycssselector("div[class='single-select-choice-icon single-select-choice-icon-deselect']"); //select correct answer as A
            List<WebElement> answerOptions = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("Option 1");
            answerOptions.get(1).sendKeys("Option 2");
            answerOptions.get(2).sendKeys("Option 3");
            answerOptions.get(3).sendKeys("Option 4");

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper QuestionCreate in method multipleChoice.", e);
        }
    }

    public void textEntry(int dataIndex)
    {
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            new Click().clickbylistid("qtn-text-entry-img", 0);//click on Text Entry type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Text Entry "+questiontext);//type the question
            new TextSend().textsendbycssSelector("Correct Answer", "input[class='numeric_text_entry_input get-user-entry is-value-changed-txt-entry']");
            new Click().clickBycssselector("span[class='btn sty-green save-spanish-text accept_answer text_entry_accept_answer']"); //click on Accept answer button

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex);
        }
        catch (Exception e) {
            Assert.fail("Exception while creating Text Entry Question type",e);
        }

    }

    public void textDropDown(int dataIndex)
    {
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            new Click().clickbylistid("qtn-text-entry-drop-down-img", 0);//click on Text Entry Drop Down type question

            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Text Drop Down "+questiontext);//type the question

            new Click().clickByclassname("text-drop-val");
            new TextSend().textsendbyid("Answer1","ans1");

            new Click().clickByclassname("select-icon-text-drop-down"); //select answer 1 as correct answer

            Driver.driver.findElements(By.className("text-drop-val")).get(1).click();
            Driver.driver.findElements(By.id("ans1")).get(1).sendKeys("Answer2");

            Driver.driver.findElements(By.className("text-drop-val")).get(2).click();
            Driver.driver.findElements(By.id("ans1")).get(2).sendKeys("Answer3");

            new Click().clickBycssselector("span[class='done-button btn sty-green text-drop-accept accept_answer']"); //click on accept answer button

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex);
        }
        catch (Exception e) {
            Assert.fail("Exception while creating text entry drop down question",e);
        }
    }

    public void numericEntryWithUnits(int dataIndex) {
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            new Click().clickbylistid("qtn-text-entry-numeric-units-img", 0);//click on Numeric Entry with Units type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Numeric Entry With Units "+questiontext);//type the question

            new TextSend().textsendbycssSelector("10", "input[class='numeric_unit_correct_answer numeric_text_entry numeric_text_entry_input is-value-changed']");

            new Click().clickBycssselector("div[class='unit-arrow-down add-more-num-entry-unit']"); //click on add more units
            new Click().clickBycssselector("li[value='feet']"); //select feet
            new Click().clickByclassname("unit_tick_image"); //click on the tick mark for unit
            new Click().clickBycssselector("div[class='add-more-num-entry-unit unit-arrow-down']"); //click on add more units
            new Click().clickBycssselector("li[value='yards']"); //select yards
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget num_ent_unit_accept_answer']"); //click on accept answer

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating Numeric Entry With Units question",e);
        }
    }

    public void advancedNumeric(int dataIndex) {
        try {

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            new Click().clickByid("qtn-numeric-advanced-img");//click on Advanced Numeric question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Advanced Numeric "+questiontext);//type the question

            new TextSend().textsendbycssSelector("10","input[class='numeric_correct_text_entry_input num-entry-ans border-color-gray']");

            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer accept_answer_maple_numeric']"); //click on accept answer

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex);


        } catch (Exception e) {
            Assert.fail("Exception while creating Advanced Numeric Question",e);
        }
    }

    public void expressionEvaluator(int dataIndex)
    {
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            new Click().clickByid("qtn-expression-evaluator-img");//click on Expression Evaluator type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Expression Evaluator "+questiontext);//type the question

            new Click().clickByid("answer_math_edit");
            enterValueInMathMLEditor("Square root","5");

            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer maple_symc_not_accept_answer']"); //click on accept answer

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating Expression Evaluator Question",e);
        }
    }

    /*public void matchTheFollowing(int dataIndex)
    {
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            new Click().clickByid("qtn-mtf-type");//click on Match the Following type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Match the Following " + questiontext);//type the question

            List<WebElement> lhsboxes = Driver.driver.findElements(By.cssSelector("div[class='dnd-match-lhs box']")); //Fetching all the lhs boxes

            lhsboxes.get(0).click();
            Driver.driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","5");

            lhsboxes.get(1).click();
            Driver.driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            lhsboxes.get(2).click();
            new Click().clickByid("answer_image_edit");
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-createimage_start_queue");
           (new WebDriver.driverWait(Driver.driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img[class='answer-choice-img answer-choice-image-size']")));

            lhsboxes.get(3).click();
            Driver.driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","6");

            lhsboxes.get(4).click();
            Driver.driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","7");

            //Filling RHS

            List<WebElement> rhsboxes = Driver.driver.findElements(By.cssSelector("div[class='dnd-match-rhs box']")); //Fetching all the lhs boxes

            rhsboxes.get(0).click();
            Driver.driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","5");

            rhsboxes.get(1).click();
            Driver.driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            rhsboxes.get(2).click();
            new Click().clickByid("answer_image_edit");
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-createimage_start_queue");
            (new WebDriver.driverWait(Driver.driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img[class='answer-choice-img answer-choice-image-size']")));

            rhsboxes.get(3).click();
            Driver.driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","6");

            rhsboxes.get(4).click();
            Driver.driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","7");

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel, dataIndex);

        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating Match the Following Question",e);
        }
    }

    public void dragAndDrop(int dataIndex)
    {
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            new Click().clickByid("qtn-dnd-type");//click on Drag and Drop type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Drag and Drop " + questiontext);//type the question

            List<WebElement> answers = Driver.driver.findElements(By.className("answer"));
            answers.get(0).click();
            Driver.driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","5");


            answers.get(1).click();
            Driver.driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            answers.get(2).click();
            new Click().clickByid("answer_image_edit");
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-createimage_start_queue");
            (new WebDriver.driverWait(Driver.driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img[class='answer-choice-image-size']")));

            answers.get(3).click();
            Driver.driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","10");

            List<WebElement> answerstodrag = Driver.driver.findElements(By.className("answer"));
            Actions ac = new Actions(Driver.driver);
            answerstodrag.get(0).click();
            ac.dragAndDrop(Driver.driver.findElement(By.id("ans-drag-btn")),Driver.driver.findElement(By.id("canvas"))).build().perform();

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel, dataIndex);

        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating Drag and Drop Question",e);
        }
    }*/
    public void clozeFormula(int dataIndex)
    {
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));

            new Click().clickByid("qtn-cf-type");//click on Cloze Formula type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Cloze Formula "+questiontext);//type the question

            List<WebElement> answers =  Driver.driver.findElements(By.className("answer"));
            answers.get(0).click();
            Driver.driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","1");

            answers.get(1).click();
            Driver.driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","2");

            answers.get(2).click();
            Driver.driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root", "3");

            answers.get(3).click();
            Driver.driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root", "4");

            answers.get(4).click();
            Driver.driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","5");

            List<WebElement> formulaLHS = Driver.driver.findElements(By.cssSelector("div[class='dnd-match-lhs box cloze-formula-lhs']"));
            formulaLHS.get(0).click();
            Driver.driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            enterValueInMathMLEditor("Square root", "6");

            formulaLHS.get(1).click();
            Driver.driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            enterValueInMathMLEditor("Square root","7");

            formulaLHS.get(2).click();
            Driver.driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            enterValueInMathMLEditor("Square root","8");

            formulaLHS.get(3).click();
            Driver.driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            enterValueInMathMLEditor("Square root","9");

            formulaLHS.get(4).click();
            Driver.driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            enterValueInMathMLEditor("Square root","10");


            //dragging and dropping values
            Actions ac = new Actions(Driver.driver);
            List<WebElement> answerstodrop =  Driver.driver.findElements(By.className("answer"));
            List<WebElement> rhsFields = Driver.driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']"));

            answerstodrop.get(0).click();
            ac.dragAndDrop(Driver.driver.findElement(By.id("ans-drag-btn")), rhsFields.get(0)).build().perform();

            answerstodrop.get(1).click();
            ac.dragAndDrop(Driver.driver.findElements(By.id("ans-drag-btn")).get(1), rhsFields.get(1)).build().perform();

            answerstodrop.get(2).click();
            ac.dragAndDrop(Driver.driver.findElements(By.id("ans-drag-btn")).get(2), rhsFields.get(2)).build().perform();

            answerstodrop.get(3).click();
            ac.dragAndDrop(Driver.driver.findElements(By.id("ans-drag-btn")).get(3), rhsFields.get(3)).build().perform();

            answerstodrop.get(4).click();
            ac.dragAndDrop(Driver.driver.findElements(By.id("ans-drag-btn")).get(4), rhsFields.get(4)).build().perform();

            if(learningobjective != null)
                associateTlo(dataIndex, learningobjective);//add TLO

        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper QuestionCreate in method clozeFormula.", e);
        }
    }

    public void multipleSelection(int dataIndex)
    {
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));

            new Click().clickbylistid("qtn-multiple-selection-img", 0);//click on Multiple Selection type question
            new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-checked']");//Uncheck shuffle answer choice
            if(shuffleAnswer != null) {
                if (shuffleAnswer.equals("true"))
                    new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-unchecked']");//check shuffle answer choice
            }
            new Click().clickByid("question-ms-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-ms-raw-content")).sendKeys("Multi Selection "+questiontext);//type the question

            List<WebElement> answerOptions = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("Option 1");
            answerOptions.get(1).sendKeys("Option 2");
            answerOptions.get(2).sendKeys("Option 3");
            answerOptions.get(3).sendKeys("Option 4");;
            new Click().clickByid("swuploadclose");//close pop-up
            List<WebElement> multipleSelections = Driver.driver.findElements(By.cssSelector("div[class='multiple-select-choice-icon multiple-select-choice-icon-deselect']"));
            multipleSelections.get(0).click(); multipleSelections.get(1).click(); //selecting option A and B as correct answers
            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating multiple selection question type", e);
        }
    }

    public void graphPlotter(int dataIndex)
    {
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            new Click().clickByid("qtn-graph-type");//click on Graph Plotter type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Graph Plotter "+questiontext);//type the question
            if(learningobjective != null)
                associateTlo(dataIndex, learningobjective);//add TLO

            if(solutionText == null || solutionText.equals("true"))
                new TextSend().textsendbyid("True False Solution Text","content-solution");

            if(hintText == null || hintText.equals("true"))
                new TextSend().textsendbyid("True False Hint Text","content-hint");

            new Click().clickByid("saveQuestionDetails1");//click on save button
            Thread.sleep(2000);
            new Click().clickByclassname("as-question-editor-back");//click on back
            Thread.sleep(2000);

        }
        catch (Exception e) {
            Assert.fail("Exception while creating graph plotter question type", e);
        }
    }

    public void clozeMatrix(int dataIndex)
    {
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            new Click().clickByid("qtn-cloze-matrix-type");//click on Cloze Matrix type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Cloze Matrix " + questiontext);//type the question
            enterValueInClozeMatrix(0, 1);
            enterValueInClozeMatrix(1,2);
            enterValueInClozeMatrix(2,3);
            enterValueInClozeMatrix(3,4);
            enterValueInClozeMatrix(4,5);
            enterValueInClozeMatrix(5,6);
            enterValueInClozeMatrix(6,7);
            enterValueInClozeMatrix(7,8);
            enterValueInClozeMatrix(8,9);
            Driver.driver.findElement(By.xpath("//tr[@id='row1']/td/div/div/div")).click();
            if(learningobjective != null)
                associateTlo(dataIndex, learningobjective);//add TLO
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating Cloze Matrix question type", e);
        }
    }


    public void resequence(int dataIndex)
    {
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            new Click().clickByid("qtn-resequence-type");//click on Cloze Matrix type question
            if(shuffleAnswer != null) {
                if (shuffleAnswer.equals("true"))
                    new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-unchecked']");//check shuffle answer choice
            }
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Resequence "+questiontext);//type the question
        }
        catch (Exception e) {
            Assert.fail("Exception while creating Resequence question type",e);
        }
    }

    public void essay(int dataIndex)
    {
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            new Click().clickbylistid("qtn-essay-type", 0);//click on Essay type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Essay "+questiontext);//type the question
            new Click().clickByid("essay-question-height");//click on Min line height textbox
            new TextSend().textsendbyid("3", "essay-question-height");
            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex);
        }
        catch (Exception e) {
            Assert.fail("Exception while creating essay question type",e);
        }
    }

    public void writeBoard(int dataIndex)
    {
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            new Click().clickByid("qtn-writeboard-type-new");//click on writeboard type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Write Board "+questiontext);//type the question
            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex);
        }
        catch (Exception e) {
            Assert.fail("Exception while creating essay question type",e);
        }
    }

    public void enterValueInMathMLEditor(String operation,String value)
    {
        try {

            Driver.driver.findElement(By.cssSelector("button[title='"+operation+"']")).click();
            Driver.driver.findElement(By.className("wrs_focusElement")).sendKeys(value);
            Driver.driver.findElement(By.id("wiris-answer-container-save-choice1")).click();
        }
        catch (Exception e){
            Assert.fail("Exception while entering value in Math ML editor");
        }
    }

    public void enterValueInClozeMatrix(int matrixIndex, int value)
    {
        try {
            List<WebElement> matrixCells = Driver.driver.findElements(By.className("matrix-text-area"));
            matrixCells.get(matrixIndex).click();
            new Click().clickByid("answer_choice_edit");
            new TextSend().textsendbyid(Integer.toString(value), "answer_choice_txt");
        }
        catch (Exception e) {
            Assert.fail("Exception while entering value in Cloze Matrix editor");
        }
    }
    public void associateTlo(int dataIndex, String learningobjective)
    {
        try
        {
            Driver.driver.findElement(By.id("learing-objectives-span")).click(); //Clicking on Learning objective drop-down from footer
            Driver.driver.findElement(By.id("link-add-learning-objectives")).click(); //clicking on 'Add Learning Objective' link
            Thread.sleep(2000);
            if(learningobjective.equals("true"))
                Driver.driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[3]/label")).click();
            else
                Driver.driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div["+learningobjective+"]/label")).click();
            new Click().clickBycssselector("span.add-collection-to-subtopic");//click on Associate
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper QuestionCreate in method associateTlo.", e);
        }
    }
    public void saveQuestion(String learningobjective,String solutionText, String hintText, String useWriteBoard, String difficultylevel, String variationLevel,String withImageInHint,String withImageInSolution, int dataIndex)
    {
        try {

            if(useWriteBoard != null)
            {
                new Click().clickBycssselector("#content-writeboard > #writeboard");//check the use writeboard check box
            }
            if(difficultylevel != null)
            {
                new ComboBox().selectValue(6, difficultylevel);
            }
            if(learningobjective != null)
                associateTlo(dataIndex, learningobjective);//add TLO

            if(solutionText == null || solutionText.equals("true")) {
                if(withImageInSolution != null) {
                    if (withImageInSolution.equals("true"))
                        Driver.driver.findElement(By.id("content-solution")).sendKeys("Solution Text");
                }
                else
                    new TextSend().textsendbyid("Solution Text", "content-solution");
            }

            if(hintText == null || hintText.equals("true")) {
                if(withImageInHint != null) {
                    if (withImageInHint.equals("true"))
                        Driver.driver.findElement(By.id("content-hint")).sendKeys("Hint Text");
                    else
                        new TextSend().textsendbyid("Hint Text", "content-hint");
                }
                else
                    new TextSend().textsendbyid("Hint Text", "content-hint");
            }
            if(variationLevel != null)
            {
                new Click().clickBycssselector("#content-variationlevel > select");
                new Click().clickBycssselector("option[value='"+variationLevel+"']");
            }
            new Click().clickbylinkText("Draft"); //click on Draft option

            String needNotToBePubished=ReadTestData.readDataByTagName("","needNotToBePubished",Integer.toString(dataIndex));
            if(needNotToBePubished ==null)
                new Click().clickbylinkText("Publish");

            if(needNotToBePubished !=null){
                if(!(needNotToBePubished.equals("true"))) {
                    new Click().clickbylinkText("Publish");    //click on Publish option if isPublished is null or tru
                }
            }

            new Click().clickByid("saveQuestionDetails1");//click on save button

            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while saving a question",e);
        }
    }
}
