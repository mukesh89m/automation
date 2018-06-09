package com.snapwiz.selenium.tests.staf.edulastic.apphelpers;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AddQuestion;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.GraphingQuestionCreation;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.SentenceResponceQuestionCreation;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.*;
import com.snapwiz.selenium.tests.staf.framework.utility.Properties;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.*;
import gherkin.lexer.Th;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

/*
 * Created by Sumit on 9/9/2014.
 */
public class QuestionCreate extends Driver {

    public void trueFalseQuestions(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String noOfELOs = ReadTestData.readDataByTagName("", "noofelos", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickBycssselector("div[title='True or False']");//click on True/False type question

            if(shuffleAnswer != null) {
                if (shuffleAnswer.equals("true"))
                    new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-unchecked']");//check shuffle answer choice
            }
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("question-raw-content")));
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("True False "+questiontext);//type the question
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option A
            Thread.sleep(2000);
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-raw-content");//click on Question
                    driver.findElement(By.id("question-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            if(noOfELOs!=null) {
                saveQuestion(learningobjective, solutionText, hintText, dataIndex, noOfELOs);
                ReportUtil.log("True False type question creation", "True False question is created successfully", "pass");
            }
            else {
                saveQuestion(learningobjective, solutionText, hintText, dataIndex);
                ReportUtil.log("True False type question creation", "True False question is created successfully", "pass");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper 'QuestionCreate' in method 'trueFalseQuestions'", e);
        }
    }

    public void multipleChoice(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickBycssselector("div[title='Multiple Choice']");//click on multiple choice question
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("question-raw-content")));

            //new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-checked']");//Uncheck shuffle answer choice
            if(shuffleAnswer != null) {
                if (shuffleAnswer.equals("true"))
                    new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-unchecked']");//check shuffle answer choice
            }
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Multiple Choice "+questiontext);//type the question
            Thread.sleep(1000);
            new Click().clickBycssselector("div[class='single-select-choice-icon single-select-choice-icon-deselect']"); //select correct answer as A
            Thread.sleep(1000);
            List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("Option 1");
            Thread.sleep(1000);
            answerOptions.get(1).sendKeys("Option 2");
            Thread.sleep(1000);
            answerOptions.get(2).sendKeys("Option 3");
            Thread.sleep(1000);
            answerOptions.get(3).sendKeys("Option 4");
            Thread.sleep(1000);
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }

            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-raw-content");//click on Question
                    driver.findElement(By.id("question-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Multiple Choice type question creation", "Multiple Choice question is created successfully", "pass");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper 'QuestionCreate' in method 'multipleChoice'", e);
        }
    }

    public void textEntry(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickByid("qtn-text-entry-type");//click on Text Entry type question
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("question-raw-content")));
            new Click().clickByid("question-raw-content");//click on Question
            Thread.sleep(2000);
            driver.findElement(By.id("question-raw-content")).sendKeys("Text Entry "+questiontext);//type the question
            new TextSend().textsendbycssSelector("Correct Answer", "input[class='numeric_text_entry_input get-user-entry']");
            new Click().clickBycssselector("span[class='btn sty-green save-spanish-text accept_answer text_entry_accept_answer']"); //click on Accept answer button
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-raw-content");//click on Question
                    driver.findElement(By.id("question-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Text Entry type question creation", "Text Entry question is created successfully", "pass");

        }
        catch (Exception e) {
            Assert.fail("Exception while creating 'Text Entry' Question type",e);
        }

    }

    public void textDropDown(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickByid("qtn-text-drop-down-type");//click on Text Entry Drop Down type question
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("question-raw-content")));
            new Click().clickByid("question-raw-content");//click on Question
            Thread.sleep(2000);
            driver.findElement(By.id("question-raw-content")).sendKeys("Text Drop Down "+questiontext);//type the question

            new Click().clickByclassname("text-drop-val");
            driver.switchTo().activeElement().sendKeys("Answer1");
            //  driver.findElements(By.id("ans1")).get(0).sendKeys("Answer1");

            new Click().clickByclassname("select-icon-text-drop-down"); //select answer 1 as correct answer

            driver.findElements(By.className("text-drop-val")).get(1).click();
            driver.findElements(By.id("ans1")).get(1).sendKeys("Answer2");

            driver.findElements(By.className("text-drop-val")).get(2).click();
            driver.findElements(By.id("ans1")).get(2).sendKeys("Answer3");

            new Click().clickBycssselector("span[class='done-button btn sty-green text-drop-accept accept_answer']"); //click on accept answer button
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-raw-content");//click on Question
                    driver.findElement(By.id("question-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Text Drop Down type question creation", "Text Drop Down question is created successfully", "pass");
        }
        catch (Exception e) {
            Assert.fail("Exception while creating text entry drop down question",e);
        }
    }

    public void numericEntryWithUnits(int dataIndex) {
        WebDriver driver=Driver.getWebDriver();
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-numeric-entry-units-type");//click on Numeric Entry with Units type question
            new Click().clickByid("question-raw-content");//click on Question
            Thread.sleep(2000);
            driver.findElement(By.id("question-raw-content")).sendKeys("Numeric Entry With Units "+questiontext);//type the question
            Thread.sleep(2000);
            new TextSend().textsendbycssSelector("10", "input[class='numeric_unit_correct_answer numeric_text_entry numeric_text_entry_input']");

            new Click().clickBycssselector("div[class='unit-arrow-down add-more-num-entry-unit']"); //click on add more units
            new Click().clickBycssselector("li[value='feet']"); //select feet
            new Click().clickByclassname("unit_tick_image"); //click on the tick mark for unit
            new Click().clickBycssselector("div[class='add-more-num-entry-unit unit-arrow-down']"); //click on add more units
            new Click().clickBycssselector("li[value='yards']"); //select yards
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget num_ent_unit_accept_answer']"); //click on accept answer
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            Thread.sleep(5000);
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-raw-content");//click on Question
                    driver.findElement(By.id("question-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Numeric Entry With Units type question creation", "Numeric Entry With Units question is created successfully", "pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating 'Numeric Entry With Units' question",e);
        }
    }

    public void numeric(int dataIndex) {
        WebDriver driver=Driver.getWebDriver();
        try {

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-numeric-type");//click on Numeric Entry with Units type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Numeric Type "+questiontext);//type the question


            new Click().clickByid("answer_math_edit");
            Thread.sleep(9000);

            driver.findElement(By.className("wrs_focusElement")).sendKeys("3");
            driver.findElement(By.id("wiris-answer-container-save-choice1")).click();//click on Save

            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer maple_symc_not_accept_answer']"); //click on accept answer
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-raw-content");//click on Question
                    driver.findElement(By.id("question-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("numeric type question creation", "numeric question is created successfully", "pass");
        } catch (Exception e) {
            Assert.fail("Exception while creating 'numeric' Question",e);
        }
    }

    public void expressionEvaluator(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-math-symbolic-notation-type");//click on Expression Evaluator type question
            new Click().clickByid("question-raw-content");//click on Question
            Thread.sleep(2000);
            driver.findElement(By.id("question-raw-content")).sendKeys("Expression Evaluator "+questiontext);//type the question
            Thread.sleep(2000);
            Select select=new Select(driver.findElement(By.cssSelector("div.content-MathToolbar >select")));
            select.selectByValue("Full");
            Thread.sleep(2000);
            new Click().clickByid("answer_math_edit");
            Thread.sleep(2000);
            enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","5");

            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer maple_symc_not_accept_answer']"); //click on accept answer
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }

            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-raw-content");//click on Question
                    driver.findElement(By.id("question-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){

                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            new Select(driver.findElement(By.xpath("//div[@class='content-MathToolbar']//select"))).selectByVisibleText("Full");
            saveQuestion(learningobjective,solutionText,hintText,dataIndex);
            ReportUtil.log("Expression Evaluator type question creation", "Expression Evaluator question is created successfully", "pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating 'Expression Evaluator' Question",e);
        }
    }

    public void matchTheFollowing(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));


            if(filename == null) {
                filename = "img.png";
            }

            String elatechenhanced= ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            if(elatechenhanced!=null)
            {
                new Click().clickbylinkText("ELA Tech Enhanced");//click on ELA Tech Enhanced tab
                new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@id='qtn-mtf-type'])[2]")));
                new Click().clickByXpath("(//div[@id='qtn-mtf-type'])[2]");//click on Match the Following type question
            }
            else
            {
                new Click().clickbylinkText("Math Tech Enhanced");//Click on Math Tech Enhanced tab
                new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@id='qtn-mtf-type'])[2]")));
                new Click().clickByXpath("(//div[@id='qtn-mtf-type'])[1]");//click on Match the Following type question
            }
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Match the Following " + questiontext);//type the question

            List<WebElement> lhsboxes = driver.findElements(By.cssSelector("div[class='dnd-match-lhs box']")); //Fetching all the lhs boxes

            lhsboxes.get(0).click();
            new WebDriverWait(driver,60).until(ExpectedConditions.jsReturnsValue("return $('#answer_math_edit').is(':visible')"));
            driver.findElement(By.id("answer_math_edit")).click();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.id("edit-answer-math-dialog")),60);
            enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","5");

            lhsboxes.get(1).click();
            new WebDriverWait(driver,60).until(ExpectedConditions.jsReturnsValue("return $('#answer_choice_edit').is(':visible')"));
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            lhsboxes.get(2).click();
            new WebDriverWait(driver,60).until(ExpectedConditions.jsReturnsValue("return $('#answer_image_edit').is(':visible')"));
            new Click().clickByid("answer_image_edit");
            Thread.sleep(6000);
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.id("pickfiles")), 60);
            //((JavascriptExecutor)driver).executeScript("document.getElementById('pickfiles').click();");
            driver.findElement(By.xpath("//a[.='Select files']")).click();
            System.out.println(Config.fileUploadPath);
            if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                new KeysSend().sendKeyBoardKeys("!");
            }
            
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("widget-createimage_start_queue")));
            // new Click().clickByid("widget-createimage_start_queue");
            (new WebDriverWait(driver, 30))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img[class='answer-choice-img answer-choice-image-size']")));
            lhsboxes.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            Thread.sleep(2000);
            enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","6");

            lhsboxes.get(4).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","7");

            //Filling RHS

            List<WebElement> rhsboxes = driver.findElements(By.cssSelector("div[class='dnd-match-rhs box']")); //Fetching all the lhs boxes

            rhsboxes.get(0).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","5");

            rhsboxes.get(1).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            rhsboxes.get(2).click();
            new Click().clickByid("answer_image_edit");
            Thread.sleep(9000);
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.id("pickfiles")), 100);
            driver.findElement(By.xpath("//a[.='Select files']")).click();
           // ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("pickfiles")));
            // new Click().clickByid("pickfiles");
            System.out.println(Config.fileUploadPath);
            Thread.sleep(3000);
            if( Properties.getPropertyValue("Browser").equals("FIREFOX")){
            }
            if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                new KeysSend().sendKeyBoardKeys("!");
            }
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("widget-createimage_start_queue")));
            (new WebDriverWait(driver, 120))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));

            rhsboxes.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","6");

            rhsboxes.get(4).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","7");
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }

            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-mc-raw-content");//click on Question
                    driver.findElement(By.id("question-mc-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective,solutionText,hintText,dataIndex);
            ReportUtil.log("Match the Following type question creation", "Match the Following question is created successfully", "pass");

        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating 'Match the Following' Question",e);
        }
    }

    public void dragAndDrop(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            if (filename == null){
                filename = "img.png";
            }
            String elatechenhanced= ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            if(elatechenhanced!=null)
            {
                new Click().clickbylinkText("ELA Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByXpath("(//*[@id='qtn-dnd-type'])[2]");//click on Drag and Drop type question
            }
            else
            {
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-dnd-type");//click on Drag and Drop type question
            }

            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Drag and Drop " + questiontext);//type the question

            List<WebElement> answers = driver.findElements(By.className("answer"));
            answers.get(0).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","5");


            answers.get(1).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            answers.get(2).click();
            new Click().clickByid("answer_image_edit");
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("pickfiles")));

            if( Properties.getPropertyValue("Browser").equals("FIREFOX")){
            }
            if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                new KeysSend().sendKeyBoardKeys("!");
            }

            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("widget-createimage_start_queue")));
            (new WebDriverWait(driver, 120))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img[class='answer-choice-image-size']")));

            answers.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","10");

            List<WebElement> answerstodrag = driver.findElements(By.className("answer"));
            Actions ac = new Actions(driver);
            answerstodrag.get(0).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-raw-content");//click on Question
                    driver.findElement(By.id("question-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective,solutionText,hintText,dataIndex);
            ReportUtil.log("Drag and Drop type question creation", "Drag and Drop question is created successfully", "pass");

        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating 'Drag and Drop' Question",e);
        }
    }


    public void multipleSelection(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickByid("qtn-multiple-selection-type");//click on Multiple Selection type question
            //new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-checked']");//Uncheck shuffle answer choice
            if(shuffleAnswer != null) {
                if (shuffleAnswer.equals("true"))
                    new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-unchecked']");//check shuffle answer choice
            }
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Multi Selection "+questiontext);//type the question
            List<WebElement> multipleSelections = driver.findElements(By.cssSelector("div[class='multiple-select-choice-icon multiple-select-choice-icon-deselect']"));
            multipleSelections.get(0).click();
            multipleSelections.get(1).click(); //selecting option A and B as correct answers
            List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            Thread.sleep(1000);
            answerOptions.get(0).sendKeys("Option 1");
            Thread.sleep(1000);
            answerOptions.get(1).sendKeys("Option 2");
            answerOptions.get(2).sendKeys("Option 3");
            answerOptions.get(3).sendKeys("Option 4");
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));

            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }

            if(learningobjective != null)
                associateTlo(dataIndex, learningobjective);//add TLO

            if(solutionText == null || solutionText.equals("true"))
                new TextSend().textsendbyid("Solution Text","content-solution");

            if(hintText == null || hintText.equals("true"))
                new TextSend().textsendbyid("Hint Text","content-hint");

            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-raw-content");//click on Question
                    driver.findElement(By.id("question-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }

            saveQuestion(learningobjective,solutionText,hintText,dataIndex);

            ReportUtil.log("Multiple Selection type question creation", "Multiple Selection question is created successfully", "pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating 'Multiple Selection' question type", e);
        }
    }

    public void graphPlotter(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-graph-type");//click on Graph Plotter type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Graph Plotter "+questiontext);//type the question
            if(learningobjective != null)
                associateTlo(dataIndex, learningobjective);//add TLO

            if(solutionText == null || solutionText.equals("true"))
                new TextSend().textsendbyid("Solution Text","content-solution");

            if(hintText == null || hintText.equals("true"))
                new TextSend().textsendbyid("Hint Text","content-hint");
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-mc-raw-content");//click on Question
                    driver.findElement(By.id("question-mc-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective,solutionText,hintText,dataIndex);
            ReportUtil.log("Graph Plotter type question creation","Graph Plotter question is created successfully", "pass");
        }
        catch (Exception e) {
            Assert.fail("Exception while creating 'Graph Plotter' question type", e);
        }
    }

    public void clozeMatrix(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-cloze-matrix-type");//click on Cloze Matrix type question
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("question-raw-content")));
            Thread.sleep(1000);
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Cloze Matrix " + questiontext);//type the question
            enterValueInClozeMatrix(0, 1);
            enterValueInClozeMatrix(1,2);
            enterValueInClozeMatrix(2,3);
            enterValueInClozeMatrix(3,4);
            enterValueInClozeMatrix(4,5);
            enterValueInClozeMatrix(5,6);
            enterValueInClozeMatrix(6,7);
            enterValueInClozeMatrix(7,8);
            enterValueInClozeMatrix(8,9);
            driver.findElement(By.xpath("//tr[@id='row1']/td/div/div/div")).click();
            new Click().clickByclassname("make-text-entry-box");//select correct answer
            if(learningobjective != null)
                associateTlo(dataIndex, learningobjective);//add TLO
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-mc-raw-content");//click on Question
                    driver.findElement(By.id("question-mc-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }

            saveQuestion(learningobjective,solutionText,hintText,dataIndex);

            ReportUtil.log("Cloze Matrix type question creation","Cloze Matrix question is created successfully", "pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating 'Cloze Matrix' question type", e);
        }
    }


    public void resequence(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String elatechenhanced= ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            if(elatechenhanced!=null)
            {
                new Click().clickbylinkText("ELA Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickbylistid("qtn-resequence-type", 1);//click on resequence type question
            }
            else
            {
                new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByid("qtn-resequence-type");//click on resequence type question
            }

            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Resequence "+questiontext);//type the question

            //delete two out of five

            driver.findElement(By.cssSelector("span.back-pointer-icon")).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("div.dnd-resequence-delete.cursor-pointer")).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("span.back-pointer-icon")).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("div.dnd-resequence-delete.cursor-pointer")).click();

            List<WebElement> allAnswers = driver.findElements(By.cssSelector("div[class='resequence-answer-choice hover-border']"));
            allAnswers.get(0).click();//click on 1st row

            new Click().clickByid("answer_choice_edit");
            driver.findElement(By.id("answer_choice_txt")).sendKeys("1");

            allAnswers.get(1).click();//click on 2nd row
            new Click().clickByid("answer_choice_edit");
            driver.findElement(By.id("answer_choice_txt")).sendKeys("2");

            allAnswers.get(2).click();//click on 3rd row
            new Click().clickByid("answer_choice_edit");
            driver.findElement(By.id("answer_choice_txt")).sendKeys("3");
            if(shuffleAnswer != null) {
                if (shuffleAnswer.equals("true"))
                    new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-unchecked']");//check shuffle answer choice
            }
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-mc-raw-content");//click on Question
                    driver.findElement(By.id("question-mc-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Resequence type question creation","Resequence question is created successfully", "pass");
        }
        catch (Exception e) {
            Assert.fail("Exception while creating 'Resequence' question type",e);
        }
    }

    public void labelAnImageText(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try{
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-lbl-on-img-type");//click on Label An Image(Text) type question
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("question-raw-content")));
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Label An Image(Text) question text"+ questiontext);//type the question

            List<WebElement> answers = driver.findElements(By.className("labelAnswer"));
            answers.get(0).click();
            driver.switchTo().activeElement().sendKeys("Answer 1");

            answers.get(1).click();
            driver.switchTo().activeElement().sendKeys("Answer 2");

            answers.get(2).click();
            driver.switchTo().activeElement().sendKeys("Answer 3");

            List<WebElement> answerstodrag = driver.findElements(By.className("labelAnswer"));
            Actions ac = new Actions(driver);
            answerstodrag.get(0).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();
            Thread.sleep(1000);
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-mc-raw-content");//click on Question
                    driver.findElement(By.id("question-mc-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            driver.findElement(By.cssSelector("a[id='uploadbackgroundImage']")).click();
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("pickfiles")));
            if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                new KeysSend().sendKeyBoardKeys("!");
            }

            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + "img.png" + "^");
            Thread.sleep(5000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("widget-createimage_start_queue")));
            (new WebDriverWait(driver, 120))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));


            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Label An Image Text type question creation","Label An Image Text question is created successfully", "pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating 'label An Image Text' question type",e);
        }
    }
    public void labelAnImageDropdown(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-lbl-dropdown-type");//click on Label An Image Dropdown type question
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("question-raw-content")));
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Label An Image(Dropdown) question text"+questiontext);//type the question

            List<WebElement> answerChoices = driver.findElements(By.className("label-drpdwn-answerChoice-style"));
            answerChoices.get(0).click();
            List<WebElement> subAnswerChoices = driver.findElements(By.className("label-drpdwn-subAnswerChoice-text"));

            WebDriverUtil.clickOnElementUsingJavascript(subAnswerChoices.get(0));
            driver.switchTo().activeElement().sendKeys("Answer 1"+Keys.ENTER);
//            Thread.sleep(4000);
//            List<WebElement> element=driver.findElements(By.id("tick-image"));
            WebDriverUtil.executeJavascript("document.getElementById('tick-image').click()");
//            WebDriverUtil.executeJavascript("$('#tick-image').click()");
            subAnswerChoices.get(1).click();
            driver.switchTo().activeElement().sendKeys("Answer 2"+Keys.ENTER);

            WebDriverUtil.clickOnElementUsingJavascript(answerChoices.get(1));
            WebDriverUtil.clickOnElementUsingJavascript(subAnswerChoices.get(3));

            driver.switchTo().activeElement().sendKeys("Answer 3"+Keys.ENTER);
            Thread.sleep(2000);
            WebDriverUtil.executeJavascript("$('#ans_ch_2>ol>li>span:eq(2)>img').click();");

            subAnswerChoices.get(4).click();
            driver.switchTo().activeElement().sendKeys("Answer 4");

            WebDriverUtil.clickOnElementUsingJavascript(answerChoices.get(2));
            subAnswerChoices.get(6).click();
            driver.switchTo().activeElement().sendKeys("Answer 5"+Keys.ENTER);
            Thread.sleep(3000);

            WebDriverUtil.executeJavascript("$('#ans_ch_3>ol>li>span:eq(2)>img').click();");
            subAnswerChoices.get(7).click();
            driver.switchTo().activeElement().sendKeys("Answer 6");

            Thread.sleep(2000);
            driver.findElement(By.cssSelector("a[id='uploadbackgroundImage']")).click();

            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.id("pickfiles")), 60);
            driver.findElement(By.xpath("//a[.='Select files']")).click();
            System.out.println(Config.fileUploadPath);
            Thread.sleep(3000);
            if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                new KeysSend().sendKeyBoardKeys("!");
            }
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + "img.png" + "^");
            Thread.sleep(5000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("widget-createimage_start_queue")));
            (new WebDriverWait(driver, 120))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));

            List<WebElement> answerstodrag = driver.findElements(By.className("label-drpdwn-answerChoice-style"));
            Actions ac = new Actions(driver);
             WebDriverUtil.clickOnElementUsingJavascript(answerstodrag.get(0));
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();
            WebDriverUtil.clickOnElementUsingJavascript(answerstodrag.get(1));
            ac.dragAndDropBy(driver.findElement(By.id("ans-drag-btn")), 520,130).build().perform();
            WebDriverUtil.clickOnElementUsingJavascript(answerstodrag.get(2));
            ac.dragAndDropBy(driver.findElement(By.id("ans-drag-btn")),540,150).build().perform();

            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-mc-raw-content");//click on Question
                    driver.findElement(By.id("question-mc-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Label An Image Dropdown type question creation","Label An Image Dropdown question is created successfully", "pass");

        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating 'label An Image Dropdown' question type",e);
        }
    }

    public void clozeFormula(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try{
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-cf-type");//click on Cloze Formula type question
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("question-raw-content")));
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


            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",answerstodrop.get(0));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.id("question-raw-content")));

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

            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-mc-raw-content");//click on Question
                    driver.findElement(By.id("question-mc-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Cloze Formula type question creation","Cloze Formula question is created successfully", "pass");
        }
        catch (Exception e){
            Assert.fail("Exception while creating 'Cloze Formula' question type",e);
        }
    }
    public String essay(int dataIndex)
    {
        String rubricName=null;
        WebDriver driver=Driver.getWebDriver();
        AddQuestion addQuestion=PageFactory.initElements(Driver.getWebDriver(),AddQuestion.class);
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));
            String rubric = ReadTestData.readDataByTagName("", "rubric", Integer.toString(dataIndex));
            String rubricname = ReadTestData.readDataByTagName("", "rubricname", Integer.toString(dataIndex));
            String rubricdescription = ReadTestData.readDataByTagName("", "rubricdescription", Integer.toString(dataIndex));
            String criteriadescription = ReadTestData.readDataByTagName("", "criteriadescription", Integer.toString(dataIndex));
            String ratingonedescription = ReadTestData.readDataByTagName("", "ratingonedescription", Integer.toString(dataIndex));
            String ratingtwodescription = ReadTestData.readDataByTagName("", "ratingtwodescription", Integer.toString(dataIndex));

            new Click().clickByid("qtn-essay-type");//click on Essay type question
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("question-raw-content")));
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Essay "+questiontext);//type the question

            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(rubric != null) {
                if (rubric.equals("true")) {
                    rubricName=rubricname+ StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);
                    addQuestion.chkbox_GradingRubric.click(); //click on the grading rubic check box
                    addQuestion.editBox_RubricName.sendKeys(rubricName);
                    addQuestion.editBox_RubricDescription.sendKeys(rubricdescription);
                    addQuestion.btn_RubricSaveAndContinue.click();
                    Thread.sleep(2000);
                    addQuestion.criteria_Description.click();
                    WebDriverUtil.enterValueUsingJavascript(addQuestion.criteriaDescription_textarea,criteriadescription);
                    addQuestion.rating_Description.get(0).click();
                    WebDriverUtil.enterValueUsingJavascript(addQuestion.criteriaDescription_textarea,ratingonedescription);
                    addQuestion.rating_Description.get(1).click();
                    WebDriverUtil.enterValueUsingJavascript(addQuestion.criteriaDescription_textarea,ratingtwodescription);
                    WebDriverUtil.scrollIntoView(addQuestion.save_rubric_button,true);
                    addQuestion.save_rubric_button.click();

                }
            }

            if(learningobjective != null)
                associateTlo(dataIndex, learningobjective);//add TLO
            if(solutionText == null || solutionText.equals("true"))
                new TextSend().textsendbyid("Solution Text","content-solution");

            if(hintText == null || hintText.equals("true"))
                new TextSend().textsendbyid("Hint Text","content-hint");

            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-mc-raw-content");//click on Question
                    driver.findElement(By.id("question-mc-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }

            saveQuestion(learningobjective,solutionText,hintText,dataIndex);

            ReportUtil.log("Essay type question creation","Essay question is created successfully", "pass");
        }
        catch (Exception e) {
            Assert.fail("Exception while creating 'essay' question type",e);
        }
        return rubricName;
    }

    public void numberline(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String axisstartvalue = ReadTestData.readDataByTagName("","axisstartvalue", Integer.toString(dataIndex));
            String endvalue = ReadTestData.readDataByTagName("","endvalue", Integer.toString(dataIndex));
            String majorticks = ReadTestData.readDataByTagName("","majorticks", Integer.toString(dataIndex));
            String minorticks = ReadTestData.readDataByTagName("", "majorticks", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));


            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-number-line-type");//Click on Numberline type question
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("question-raw-content")));

            if(shuffleAnswer != null) {
                if (shuffleAnswer.equals("true"))
                    new Click().clickByid("isShuffleAnswerChoice]");//check shuffle answer choice
            }

            driver.findElement(By.id("question-raw-content")).sendKeys("Number Line "+questiontext);//type the question

            List<WebElement> answers = driver.findElements(By.className("answer"));
            answers.get(0).click();//click on 1st answer option
            driver.findElement(By.id("answer_choice_edit")).click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 1");
            answers.get(1).click();//click on 2nd answer option
            driver.findElement(By.id("answer_choice_edit")).click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 2");
            answers.get(2).click();//click on 3rd answer option
            driver.findElement(By.id("answer_choice_edit")).click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 3");
            answers.get(3).click();//click on 4th answer option
            driver.findElement(By.id("answer_choice_edit")).click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 4");

            //dragging and dropping values
            Actions ac = new Actions(driver);
            List<WebElement> answersToDrop = driver.findElements(By.xpath("//li[starts-with(@class,'answer-choice-hover numberLine-label-answer-choice-hover')]"));
            List<WebElement> rhsFields = driver.findElements(By.cssSelector("div[class='num-line-box ui-droppable']"));

            answersToDrop.get(0).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")), rhsFields.get(0)).build().perform();

            answersToDrop.get(1).click();
            ac.dragAndDrop(driver.findElements(By.id("ans-drag-btn")).get(1), rhsFields.get(10)).build().perform();

            answersToDrop.get(2).click();
            ac.dragAndDrop(driver.findElements(By.id("ans-drag-btn")).get(2), rhsFields.get(2)).build().perform();

            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-mc-raw-content");//click on Question
                    driver.findElement(By.id("question-mc-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Numberline type question creation","Numberline question is created successfully", "pass");
        }
        catch (Exception e) {
            Assert.fail("Exception while creating 'numberline question type",e);
        }
    }

    public void classification(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));
            String elatechenhanced= ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            if(elatechenhanced!=null)
            {
                new Click().clickbylinkText("ELA Tech Enhanced");//click on ELA Tech Enhanced tab
                new Click().clickbylistid("qtn-classification-type", 1);//click on Classification type question
            }
            else
            {
                new Click().clickbylinkText("Math Tech Enhanced");//Click on Math Tech Enhanced tab
                new Click().clickbylistid("qtn-classification-type",0);//Click on Classification type question
            }

            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("question-raw-content")));
            new TextSend().textsendbyid("Classification "+questiontext,"question-raw-content");//type the question

            if(shuffleAnswer != null) {
                if (shuffleAnswer.equals("true"))
                    new Click().clickByid("isShuffleAnswerChoice]");//check shuffle answer choice
            }


            new Click().clickByXpath("(//span[@class='classification-name'])[1]");//click on class name
            driver.switchTo().activeElement().sendKeys("class1");//enter class name

            List<WebElement> answers = driver.findElements(By.className("answer"));
            answers.get(0).click();//click on 1st answer option
            driver.findElement(By.id("answer_choice_edit")).click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 1");
            answers.get(1).click();//click on 2nd answer option
            driver.findElement(By.id("answer_choice_edit")).click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 2");
            answers.get(2).click();//click on 3rd answer option
            driver.findElement(By.id("answer_choice_edit")).click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 3");
            answers.get(3).click();//click on 4th answer option
            driver.findElement(By.id("answer_choice_edit")).click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 4");

            //dragging and dropping values
            Actions ac = new Actions(driver);

            WebElement classToDrop =driver.findElement(By.cssSelector("li[class='classification ui-draggable']"));
            WebElement dstClassToDrop = driver.findElement(By.cssSelector("div[class='classification-container ui-droppable']"));

            ac.moveToElement(classToDrop, 20, 5 ).click().build().perform();
            //classToDrop.click();
            ac.dragAndDrop(driver.findElement(By.xpath("//span[@class='drag-classification pull-right']/img")),dstClassToDrop).build().perform();

            List<WebElement> answersToDrop = driver.findElements(By.className("answer"));
            WebElement dstanswersToDrop= driver.findElement(By.cssSelector("div[class='classification-div ui-droppable ui-resizable ui-draggable']"));

            answersToDrop.get(0).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")), dstanswersToDrop).build().perform();

            answersToDrop.get(1).click();
            ac.dragAndDrop(driver.findElements(By.id("ans-drag-btn")).get(1),dstanswersToDrop).build().perform();

            answersToDrop.get(2).click();
            ac.dragAndDrop(driver.findElements(By.id("ans-drag-btn")).get(2), dstanswersToDrop).build().perform();
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-mc-raw-content");//click on Question
                    driver.findElement(By.id("question-mc-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Classification type question creation","Classification question is created successfully", "pass");
        }catch(Exception e){
            Assert.fail("Exception while creating 'Classification' question type",e);
        }
    }

    public String passageBasedQuestions(int dataIndex)
    {
        String rubricName=null;

        try
        {
            WebDriver driver=Driver.getWebDriver();
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));
            String elatechenhanced= ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String gradereleaseoption = ReadTestData.readDataByTagName("", "gradereleaseoption", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String daadmin = ReadTestData.readDataByTagName("", "daadmin", Integer.toString(dataIndex));
            String saadmin = ReadTestData.readDataByTagName("", "saadmin", Integer.toString(dataIndex));

            if(elatechenhanced!=null)
            {
                new Click().clickbylinkText("ELA Tech Enhanced");//click on Math Tech Enhanced tab
                new Click().clickByXpath("(//*[@id='qtn-passage-type'])[2]");//click on passage based question
            }
            else
            {
                new Click().clickbylinkText("Math Tech Enhanced");//Click on Math Tech Enhanced tab
                new Click().clickByXpath("(//*[@id='qtn-passage-type'])[1]");//click on passage based question
            }
            if(gradereleaseoption.equals("On assignment submission")) //If grade release option is 'on assignment submission' then exclude essay type question else include all the question in passage based
            {
                new TextSend().textsendbyid("Header pf the Paragraph", "passage_title");
                new TextSend().textsendbyclass("Passage Title","tab-title-text");
                String passage = new RandomString().randomstring(25);
                new TextSend().textsendbyid(passage, "question-edit-passage-text");
                new Click().clickByid("saveQuestionDetails1");//click on save button
                WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//div[text()='Successfully saved.']")),30);
                new Click().clickByclassname("add-question-text");//click on Add a New Question for this passage

                trueFalseQuestions(dataIndex);//Crete true false question

                new Click().clickByclassname("passage-add-more-ques");//click on More Question for this passage
                new Click().clickByclassname("add-question-text");// click on Add a New Question for this passage
                new Click().clickByXpath("//a[text()='Classic Question Types']");
                multipleChoice(dataIndex);//Create multiple choice question

                new Click().clickByclassname("passage-add-more-ques");//click on More Question for this passage
                new Click().clickByclassname("add-question-text");// click on Add a New Question for this passage
                multipleSelection(dataIndex);//Create multiple selection question type

                new Click().clickByclassname("passage-add-more-ques");//click on More Question for this passage
                new Click().clickByclassname("add-question-text");// click on Add a New Question for this passage
                textDropDown(dataIndex);//Create text drop down question type

                new Click().clickByclassname("passage-add-more-ques");//click on More Question for this passage
                new Click().clickByclassname("add-question-text");// click on Add a New Question for this passage
                textEntry(dataIndex);//Create text entry question type

                new Click().clickByclassname("passage-add-more-ques");//click on More Question for this passage
                new Click().clickByclassname("add-question-text");// click on Add a New Question for this passage
                new Click().clickbylinkText("Math Tech Enhanced");//Click on Math Tech Enhanced tab
                Thread.sleep(3000);
                numericEntryWithUnits(dataIndex);//Create numeric entry with units question type

                new Click().clickByclassname("passage-add-more-ques");//click on More Question for this passage
                new Click().clickByclassname("add-question-text");// click on Add a New Question for this passage
                numeric(dataIndex);//Create numeric question type

                ReportUtil.log("Passage based type question creation","Passage based question is created successfully", "pass");
            }else
            {

                if( (daadmin!=null && daadmin.equals("true")) || (saadmin!=null && saadmin.equals("true")))
                {
                        new TextSend().textsendbyid("Header pf the Paragraph", "passage_title");
                        new TextSend().textsendbyclass("Passage Title","tab-title-text");
                        String passage = new RandomString().randomstring(25);
                        new TextSend().textsendbyid(passage, "question-edit-passage-text");
                        new Click().clickByid("saveQuestionDetails1");//click on save button
                        WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//div[text()='Successfully saved.']")),60);
                        new Click().clickByclassname("add-question-text");//click on Add a New Question for this passage
                        new Click().clickByXpath("//a[text()='Classic Question Types']");
                        trueFalseQuestions(dataIndex);//Crete true false question

                        new Click().clickByclassname("passage-add-more-ques");//click on More Question for this passage
                        new Click().clickByclassname("add-question-text");// click on Add a New Question for this passage
                        rubricName=essay(dataIndex);//Create essay question type
                }else
                {
                    new TextSend().textsendbyid("Header pf the Paragraph", "passage_title");
                    new TextSend().textsendbyclass("Passage Title","tab-title-text");
                    String passage = new RandomString().randomstring(25);
                    new TextSend().textsendbyid(passage, "question-edit-passage-text");
                    new Click().clickByid("saveQuestionDetails1");//click on save button
                    WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//div[text()='Successfully saved.']")),30);
                    new Click().clickByclassname("add-question-text");//click on Add a New Question for this passage
                    new Click().clickByXpath("//a[text()='Classic Question Types']");
                    trueFalseQuestions(dataIndex);//Crete true false question


                    new Click().clickByclassname("passage-add-more-ques");//click on More Question for this passage
                    new Click().clickByclassname("add-question-text");// click on Add a New Question for this passage
                    new Click().clickByXpath("//a[text()='Classic Question Types']");
                    multipleChoice(dataIndex);//Create multiple choice question

                    new Click().clickByclassname("passage-add-more-ques");//click on More Question for this passage
                    new Click().clickByclassname("add-question-text");// click on Add a New Question for this passage
                    multipleSelection(dataIndex);//Create multiple selection question type

                    new Click().clickByclassname("passage-add-more-ques");//click on More Question for this passage
                    new Click().clickByclassname("add-question-text");// click on Add a New Question for this passage
                    textDropDown(dataIndex);//Create text drop down question type

                    new Click().clickByclassname("passage-add-more-ques");//click on More Question for this passage
                    new Click().clickByclassname("add-question-text");// click on Add a New Question for this passage
                    rubricName=essay(dataIndex);//Create essay question type

                    new Click().clickByclassname("passage-add-more-ques");//click on More Question for this passage
                    new Click().clickByclassname("add-question-text");// click on Add a New Question for this passage
                    textEntry(dataIndex);//Create text entry question type

                    new Click().clickByclassname("passage-add-more-ques");//click on More Question for this passage
                    new Click().clickByclassname("add-question-text");// click on Add a New Question for this passage
                    new Click().clickbylinkText("Math Tech Enhanced");//Click on Math Tech Enhanced tab
                    numericEntryWithUnits(dataIndex);//Create numeric entry with units question type

                    new Click().clickByclassname("passage-add-more-ques");//click on More Question for this passage
                    new Click().clickByclassname("add-question-text");// click on Add a New Question for this passage
                    numeric(dataIndex);//Create numeric question type
                }
            }
            ReportUtil.log("Passage based type question creation","Passage based question is created successfully", "pass");

        }
        catch (Exception e) {
            Assert.fail("Exception while 'passage based' question type",e);
        }
        return rubricName;
    }

    public void matchingTables(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));
            String elatechenhanced= ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            if(elatechenhanced!=null)
            {
                new Click().clickbylinkText("ELA Tech Enhanced");//click on ELA Tech Enhanced tab
                new Click().clickbylistid("qtn-matching-tables-type", 1);//click on Matching Tables type question
            }
            else
            {
                new Click().clickbylinkText("Math Tech Enhanced");//Click on Math Tech Enhanced tab
                new Click().clickByid("qtn-matching-tables-type");//click on Matching Tables type question
            }

            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Matching Tables "+questiontext);//type the question
            List<WebElement> matrixCells = driver.findElements(By.cssSelector("div[class='matrix-text-area matching-text']"));
            WebDriverUtil.clickOnElementUsingJavascript(matrixCells.get(0));
            new Click().clickbylistid("answer_choice_edit", 1);
            driver.switchTo().activeElement().sendKeys("1st column");
            driver.findElement(By.xpath("//html/body")).click();

            matrixCells.get(1).click();
            new Click().clickbylistid("answer_choice_edit", 1);
            driver.switchTo().activeElement().sendKeys("2nd column");

            matrixCells.get(2).click();
            new Click().clickbylistid("answer_choice_edit", 1);
            driver.switchTo().activeElement().sendKeys("3rd column");

            matrixCells.get(3).click();
            new Click().clickbylistid("answer_choice_edit", 1);
            driver.switchTo().activeElement().sendKeys("1st row");

            matrixCells.get(4).click();
            new Click().clickbylistid("answer_choice_edit", 1);
            driver.switchTo().activeElement().sendKeys("2nd row");

            new Click().clickBycssselector("label[for='matrix-box-checkbox-btn-1']");//click on 1st checkbox
            new Click().clickBycssselector("label[for='matrix-box-checkbox-btn-3']");//click on 2nd checkbox


            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-mc-raw-content");//click on Question
                    driver.findElement(By.id("question-mc-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Matching Tables type question creation","Matching Tables question is created successfully", "pass");
        }
        catch (Exception e) {
            Assert.fail("Exception while creating 'matching Tables' question type",e);
        }
    }

    public void sentenceResponse(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String allowMultipleCorrectAnswer = ReadTestData.readDataByTagName("", "allowMultipleCorrectAnswer", Integer.toString(dataIndex));
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickbylinkText("ELA Tech Enhanced");//Click on ELA Tech Enhanced tab
            new Click().clickByid("qtn-sentence-type");//click on Sentence Response type question
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("question-raw-content")));
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Sentence Response "+questiontext);//type the question
            String str = new RandomString().randomstring(30);
            Thread.sleep(4000);
            new TextSend().textsendbyid(str, "sentence-selection-raw-content-text-area");//send keys in Edit text
            if(allowMultipleCorrectAnswer!=null) {
                if (allowMultipleCorrectAnswer.equalsIgnoreCase("Yes")) {
                    sentenceResponceQuestionCreation.getCheckBox_AllowMultipleCorrectAnswer().click();
                }
            }
            Thread.sleep(15000);
            new Click().clickByid("tab-highlight-text");//click on Highlight text tab
            Thread.sleep(5000);
            WebDriverUtil.waitForAjax(driver,60);
            WebElement highlightedText=driver.findElement(By.id("sentence-selection-raw-content-div-heighlight"));
            Actions action1 = new Actions(driver);
            action1.doubleClick(highlightedText).build().perform();

           /* int x=highlightedText.getLocation().getX();
            int y=highlightedText.getLocation().getX();
            System.out.println("x:"+x);
            System.out.println("y:"+y);

            Actions actions = new Actions(driver);
            actions.moveToElement(element, x, y)
                    .clickAndHold()
                    .moveByOffset(0, y)
                    .release()
                    .build()
                    .perform();


          //  new Click().clickByid("saveQuestionDetails1");//click on save button to click outside*/
            //new Click().clickByid("sentence-selection-raw-content-div-heighlight");//click on highlighted section


            Actions action = new Actions(driver);
            WebElement we = driver.findElement(By.className("sentence-response-selectiontext"));
            action.moveToElement(we).moveToElement(driver.findElement(By.className("sentence-response-selectiontext"))).click().build().perform();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("mark-correct-answer-option")));

            new Click().clickByid("mark-correct-answer-option");//click on mark correct answer option
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-mc-raw-content");//click on Question
                    driver.findElement(By.id("question-mc-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);

            ReportUtil.log("Sentence Response type question creation","Sentence Response question is created successfully", "pass");
        }
        catch (Exception e) {
            Assert.fail("Exception while creating 'Sentence Response' question type",e);
        }
    }


    public void linePlot(int dataIndex){
        try{
            WebDriver driver=Driver.getWebDriver();
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-line-plot-type");//Click on Line Plot type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Line Plot "+questiontext);//type the question

            new Click().clickByXpath("//div[@class='num-line-box dot-effect']");
            //new Click().clickByid("line-plot-section");//Click on line plot section
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-mc-raw-content");//click on Question
                    driver.findElement(By.id("question-mc-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Line Plot type question creation","Line Plot question is created successfully", "pass");
        }catch (Exception e){
            Assert.fail("Exception while creating 'Line Plot' question type",e);

        }
    }

    public void rangePlotter(int dataIndex){
        WebDriver driver=Driver.getWebDriver();
        String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
        try{String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-number-line-range");//Click on Range Plotter type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Range Plotter "+questiontext);//type the question

            WebElement src = driver.findElement(By.xpath("(//div[@type='segment'])[1]"));
            WebElement dst = driver.findElement(By.id("numberLineAnsBox_2"));

            new DragAndDrop().dragAndDrop(src,dst);

            new Click().clickByid("numberLineAnsBox_2");

            WebElement srcObject = driver.findElement(By.cssSelector("div[class='range-nl-create-point finite-closed']"));
            WebElement dstLine = driver.findElement(By.id("numberLineAnsBox_4"));

            new DragAndDrop().dragAndDrop(srcObject,dstLine);//Draw the object

            new Click().clickByid("numberLineAnsBox_4");
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-mc-raw-content");//click on Question
                    driver.findElement(By.id("question-mc-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Range Plotter type question creation","Range Plotter question is created successfully", "pass");
        }catch (Exception e){
            Assert.fail("Exception while creating 'Range Plotter' question type",e);

        }
    }

    public void fractionEditor(int dataIndex){
        WebDriver driver=Driver.getWebDriver();
        String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
        try{String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));


            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickBycssselector("div[title='Fraction Editor']");//click on Fraction Editor type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Fraction editor "+questiontext);//type the question
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-mc-raw-content");//click on Question
                    driver.findElement(By.id("question-mc-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("FractionEditor type question creation","FractionEditor question is created successfully", "pass");
        }catch (Exception e){
            Assert.fail("Exception while creating 'FractionEditor' question type",e);

        }
    }


    public void graphing(int dataIndex){
        WebDriver driver=Driver.getWebDriver();
        try{
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            GraphingQuestionCreation graphing = PageFactory.initElements(driver,GraphingQuestionCreation.class);
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-graphing-type");//Click on graphing question type
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Graphing "+questiontext);//type the question

            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }

            Select sel = new Select(driver.findElement(By.className("drawing-prompt-combo")));
            sel.selectByValue("Using drawing tools");

            graphing.getList_toolsInGrid().get(0).click();//Click on Point tool
            graphing.getGridArea().click();//Click on grid area

            WebDriverUtil.clickOnElementUsingJavascript(graphing.getAnswerLayer());//Click on answer layer
            graphing.getLabelsInGrid().get(16).click();//Place the point on graph

            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-raw-content");//click on Question
                    driver.findElement(By.id("question-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Graphing type question creation","Graphing question is created successfully", "pass");
        }catch (Exception e){
            Assert.fail("Exception while creating 'Graphing' question type",e);

        }
    }

    public void pictograph(int dataIndex){
        WebDriver driver=Driver.getWebDriver();
        try{
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            GraphingQuestionCreation graphing = PageFactory.initElements(driver,GraphingQuestionCreation.class);
            List<String> filename = ReadTestData.readDataByTagNameList("", "filename", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-pictograph-type");//Click on graphing question type
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Pictograph "+questiontext);//type the question
            //type class name
            new Click().clickByXpath("(//span[@class='classification-name'])[1]");//click on class name
            driver.switchTo().activeElement().sendKeys("class1");//enter class name
            driver.findElement(By.xpath("//html/body")).click();
            Thread.sleep(1000);
            Actions ac = new Actions(driver);

            WebElement classToDrop =driver.findElement(By.cssSelector("li[class='classification ui-draggable'] > span"));
            WebElement dstClassToDrop = driver.findElement(By.cssSelector("div[class='classification-container ui-droppable']"));

            System.out.println("size "+driver.findElements(By.cssSelector("li[class='classification ui-draggable'] > span")).size());
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//span[@class='drag-classification pull-right']/img")));
            ac.moveToElement(driver.findElement(By.xpath("//span[@class='drag-classification pull-right']/img")) ).click().build().perform();
            Thread.sleep(2000);

            ac.dragAndDrop(driver.findElement(By.xpath("//span[@class='drag-classification pull-right']/img")),dstClassToDrop).build().perform();

            List<WebElement> element=driver.findElements(By.cssSelector("span.element-option"));

            /*
            upload first image
            */
            element.get(0).click();
            Thread.sleep(2000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("pickfiles")));
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + "img.png" + "``^");
            Thread.sleep(5000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("widget-createimage_start_queue")));
            (new WebDriverWait(driver, 120))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
            driver.findElements(By.cssSelector("input.element-count-text.unit-content")).get(0).sendKeys("1");
            driver.findElements(By.cssSelector("input.element-unit-text.unit-content")).get(0).sendKeys("mm");




            /*
            upload Second image
            */
            element.get(1).click();
            Thread.sleep(2000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("pickfiles")));
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + "index.jpg" + "``^");
            Thread.sleep(5000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("widget-createimage_start_queue")));
            (new WebDriverWait(driver, 120))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
            driver.findElements(By.cssSelector("input.element-count-text.unit-content")).get(1).sendKeys("1");
            driver.findElements(By.cssSelector("input.element-unit-text.unit-content")).get(1).sendKeys("mm");


              /*
            upload Third image
            */
            element.get(2).click();
            Thread.sleep(2000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("pickfiles")));
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + "index.jpg" + "``^");
            Thread.sleep(5000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("widget-createimage_start_queue")));
            (new WebDriverWait(driver, 120))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
            driver.findElements(By.cssSelector("input.element-count-text.unit-content")).get(2).sendKeys("1");
            driver.findElements(By.cssSelector("input.element-unit-text.unit-content")).get(2).sendKeys("mm");



            List<WebElement> answerstodrag = driver.findElements(By.xpath("//img[@id='ans-drag-btn']"));
            driver.findElements(By.cssSelector("input.element-count-text.unit-content")).get(0).click();
            Thread.sleep(1000);

            Actions action = new Actions(driver);
            answerstodrag.get(0).click();
            action.dragAndDrop(answerstodrag.get(0),driver.findElement(By.cssSelector("div[class='classification-div ui-droppable ui-resizable ui-draggable']"))).build().perform();

            //click on answer layer
            driver.findElement(By.className("pic-answer-layer")).click();
            Thread.sleep(2000);
            driver.findElements(By.cssSelector("input.element-count-text.unit-content")).get(1).click();
            Thread.sleep(1000);

            action = new Actions(driver);
            answerstodrag.get(1).click();
            action.dragAndDrop(answerstodrag.get(1),driver.findElement(By.cssSelector("div[class='classification-div ui-droppable ui-resizable ui-draggable']"))).build().perform();

            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }

            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Pictograph type question creation","Pictograph question is created successfully", "pass");
        }catch (Exception e){
            Assert.fail("Exception while creating 'Pictograph' question type",e);

        }
    }


    public void graphPlacement(int dataIndex){
        WebDriver driver=Driver.getWebDriver();
        try{
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            GraphingQuestionCreation graphing = PageFactory.initElements(driver,GraphingQuestionCreation.class);
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-graph-placement-type");//Click on Graph placement question type
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Graph Placement "+questiontext);//type the question

            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }

            List<WebElement> answers = driver.findElements(By.xpath("//ul[@id='answer_choices']/li"));
            answers.get(0).click();//click on 1st answer option
            driver.findElement(By.id("answer_choice_edit")).click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 1");
            answers.get(1).click();//click on 2nd answer option
            driver.findElement(By.id("answer_choice_edit")).click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 2");
            answers.get(2).click();//click on 3rd answer option
            driver.findElement(By.id("answer_choice_edit")).click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 3");
            answers.get(3).click();//click on 4th answer option
            driver.findElement(By.id("answer_choice_edit")).click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 4");

            graphing.getList_toolsInGrid().get(0).click();//Click on Point tool
            graphing.getGridArea().click();//Click on grid area

            graphing.getAnswerLayer().click();//Click on answer layer
            graphing.getLabelsInGrid().get(16).click();//Place the point on graph

            if(mathmlInQuestion!=null){
                if(mathmlInQuestion.equals("true")){
                    new Click().clickByid("question-raw-content");//click on Question
                    driver.findElement(By.id("question-raw-content")).sendKeys(Keys.END);
                    List<WebElement> mathml = driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']"));

                    for(int i=0; i<mathml.size()-1;i++)
                        if(mathml.get(i).isDisplayed()){
                            driver.findElements(By.xpath("//a[@class='re-icon re-matheditor redactor-btn-image']")).get(i).click();//Click on mathml icon
                            enterValueInMathMLEditorInQuestionTextBox("Square root", "5");
                            break;
                        }
                }
            }

            answers = driver.findElements(By.xpath("//ul[@id='answer_choices']/li"));
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollBy(0,-250)", "");
            answers.get(0).click();//click on 1st answer option
            Thread.sleep(1000);

            new DragAndDrop().dragAndDrop(driver.findElements(By.id("ans-drag-btn")).get(0),driver.findElement(By.id("resizable-graph")));
            Thread.sleep(1000);

            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Graph Placement type question creation","Graph Placement question is created successfully", "pass");
        }catch (Exception e){
            Assert.fail("Exception while creating 'Graph Placement' question type",e);

        }
    }




    public void sentencecorrection(int dataIndex){
        WebDriver driver=Driver.getWebDriver();
        try{
            String usewriteboard = ReadTestData.readDataByTagName("", "usewriteboard", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            GraphingQuestionCreation graphing = PageFactory.initElements(driver,GraphingQuestionCreation.class);
            List<String> filename = ReadTestData.readDataByTagNameList("", "filename", Integer.toString(dataIndex));
            String mathmlInQuestion = ReadTestData.readDataByTagName("", "mathmlinquestiontext", Integer.toString(dataIndex));

            new Click().clickbylinkText("ELA Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-sentence-correction-type");//Click on sentence correction question type
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Sentence correction "+questiontext);//type the question
            Thread.sleep(1000);
            new Click().clickByclassname("text-drop-val");
            driver.switchTo().activeElement().sendKeys("Answer1");
            //  driver.findElements(By.id("ans1")).get(0).sendKeys("Answer1");

            new Click().clickByclassname("select-icon-text-drop-down"); //select answer 1 as correct answer

            driver.findElements(By.className("text-drop-val")).get(1).click();
            driver.findElements(By.id("ans1")).get(1).sendKeys("Answer2");
            driver.findElement(By.xpath("//div[@id='add-new-text-drop-down-value']")).click();
            Thread.sleep(1000);

            driver.findElements(By.className("text-drop-val")).get(2).click();
            driver.findElements(By.id("ans1")).get(2).sendKeys("Answer3");

            new Click().clickBycssselector("span[class='done-button btn sty-green text-drop-accept accept_answer']"); //click on accept answer button
            if(usewriteboard != null) {
                if (usewriteboard.equals("true")) {
                    new Click().clickByXpath("//label[@id='writeboard']");//click to select writeboard
                }
            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Sentence Correction type question creation","Sentence Correction question is created successfully", "pass");
        }catch (Exception e){
            Assert.fail("Exception while creating 'Sentence Correction' question type",e);

        }
    }

    public void multipart(int dataIndex,String questionType){
        WebDriver driver=Driver.getWebDriver();
        try{

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String gradereleaseoption = ReadTestData.readDataByTagName("", "gradereleaseoption", Integer.toString(dataIndex));

            //new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Actions(driver).click(driver.findElement(By.linkText("Math Tech Enhanced"))).perform();
            WebDriverUtil.waitForAjax(driver, 60);
            new Click().clickByid("qtn-hybrid-type");//Click on multipart question type
            WebDriverUtil.waitForAjax(driver, 60);
            driver.findElement(By.id("questionEditor")).clear();//Clear question editor

            if(questionType.equals("multipleChoiceAndSelectionAndEssay"))
            {
                new TextSend().textsendbyid("Multipart : MultipleChoice Question", "questionEditor");//Enter question
                new Click().clickByid("questionEditor");
                Thread.sleep(3000);
                new Click().clickBycssselector("span[class='hybrid-widget-icon hybrid-tabs-icons']");//Click on widget tab
                new Click().clickBycssselector("span[class='hybrid-widget-tools-icon hybrid-multi-choice-icon']");//Click on Multiple Choice question type

                List<WebElement> answerChoices = driver.findElements(By.xpath("//div[@class='redactor_box']/div[starts-with(@id,'popupEditor')]"));
                answerChoices.get(0).clear();
                driver.switchTo().activeElement().sendKeys("Answer Choice1");

                answerChoices.get(1).clear();
                driver.switchTo().activeElement().sendKeys("Answer Choice2");

                answerChoices.get(2).clear();
                driver.switchTo().activeElement().sendKeys("Answer Choice3");

                answerChoices.get(3).clear();
                driver.switchTo().activeElement().sendKeys("Answer Choice4");

                List<WebElement> selectAnswerChoice = driver.findElements(By.cssSelector("span.choice-value"));
                selectAnswerChoice.get(4).click();//Select A

                new Click().clickByclassname("accept-answer");//Click on Accept

                new Click().clickByid("questionEditor");//Click on question editor
                Actions actions=new Actions(driver);
                actions.sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
                actions.sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER));

                    driver.findElement(By.id("questionEditor")).sendKeys("Multipart : MultipleSelection Question");//Enter question
                new Click().clickBycssselector("span[class='hybrid-widget-icon hybrid-tabs-icons']");//Click on widget tab
                new Click().clickBycssselector("span[class='hybrid-widget-tools-icon hybrid-multiple-select-icon']");//Click on multiple selection question type

                List<WebElement> ansChoices = driver.findElements(By.xpath("//div[@class='redactor_box']/div[starts-with(@id,'popupEditor')]"));
                ansChoices.get(0).clear();
                ansChoices.get(0).sendKeys("Answer Choice1");
                ansChoices.get(1).clear();
                ansChoices.get(1).sendKeys("Answer Choice2");
                ansChoices.get(2).clear();
                ansChoices.get(2).sendKeys("Answer Choice3");
                ansChoices.get(3).clear();
                ansChoices.get(3).sendKeys("Answer Choice4");

                List<WebElement> selectAnswerChoiceMultipleSelect = driver.findElements(By.xpath("//div[starts-with(@class,'multi-select-choice-icon multi-select-choice-icon')]"));
                selectAnswerChoiceMultipleSelect.get(0).click();//Select A
                selectAnswerChoiceMultipleSelect.get(1).click();//Select B

                new Click().clickByclassname("accept-answer");//Click on Accept
                new Click().clickByid("questionEditor");//Click on question editor
                actions.sendKeys(Keys.chord(Keys.CONTROL,Keys.END));
                actions.sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER));


                if(gradereleaseoption!=null)
                {
                    if(!gradereleaseoption.equals("On assignment submission"))
                    {

                        new Click().clickByid("questionEditor");//Click on question editor
                        driver.findElement(By.id("questionEditor")).sendKeys("Multipart : Essay Question");//Enter question
                        new Click().clickBycssselector("span[class='hybrid-widget-icon hybrid-tabs-icons']");//Click on widget tab

                        new Click().clickBycssselector("span[class='hybrid-widget-tools-icon hybrid-essay-icon']");//Click on essay question type
                        Thread.sleep(1000);
                        new Click().clickByclassname("accept-answer");//Click on Accept
                    }
                }



            }

            if(questionType.equals("textEntryAndDropdown")) {

                new TextSend().textsendbyid("Multipart : TextEntry Question", "questionEditor");//Enter question
                new Click().clickByid("questionEditor");
                driver.findElement(By.id("questionEditor")).sendKeys(Keys.END);
                new Click().clickBycssselector("span[class='hybrid-widget-icon hybrid-tabs-icons']");//Click on widget tab

                new Click().clickBycssselector("span[class='hybrid-widget-tools-icon hybrid-widget-text-entry-icon']");//Click on text entry question type
                Thread.sleep(1000);
                new TextSend().textsendbycssSelector("20", "input[class='numeric_text_entry_input get-user-entry']");//Enter correct answer
                new Click().clickByclassname("accept-answer");//Click on Accept
                Thread.sleep(1000);

                new Click().clickByid("questionEditor");//Click on question editor
                driver.findElement(By.id("questionEditor")).sendKeys(Keys.END);
                driver.findElement(By.id("qinstance")).sendKeys(Keys.ENTER);
                driver.findElement(By.id("questionEditor")).sendKeys("Multipart : TextDropdown Question");

                new Click().clickBycssselector("span[class='hybrid-widget-tools-icon hybrid-text-entryDrop-icon']");//Click on text entry question type
                Thread.sleep(2000);
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.className("text-drop-val")));
                driver.switchTo().activeElement().sendKeys("Answer1");

                new Click().clickByclassname("select-icon-text-drop-down"); //select answer 1 as correct answer

                driver.findElements(By.className("text-drop-val")).get(1).click();
                driver.findElements(By.id("ans1")).get(1).sendKeys("Answer2");

                driver.findElements(By.className("text-drop-val")).get(2).click();
                driver.findElements(By.id("ans1")).get(2).sendKeys("Answer3");
                new Click().clickByclassname("accept-answer");//Click on Accept


            }

            if(questionType.equals("numeric")) {

                new TextSend().textsendbyid("Multipart : Numeric", "questionEditor");//Enter question
                new Click().clickByid("questionEditor");
                driver.findElement(By.id("questionEditor")).sendKeys(Keys.END);
                new Click().clickBycssselector("span[class='hybrid-widget-icon hybrid-tabs-icons']");//Click on widget tab

                new Click().clickBycssselector("span[class='hybrid-widget-tools-icon hybrid-numeric-entry-icon']");//Click on advance numeric question type
                Thread.sleep(1000);
                new TextSend().textsendbycssSelector("20", "input[class='numeric_correct_text_entry_input num-entry-ans border-color-gray']");//Enter correct answer
                new Click().clickByclassname("accept-answer");//Click on Accept

            }
            saveQuestion(learningobjective, solutionText, hintText, dataIndex);
            ReportUtil.log("Multipart type question creation","Multipart question is created successfully", "pass");
        }catch (Exception e){
            Assert.fail("Exception while creating 'Multipart' question type",e);

        }
    }
    public void associateTlo(int dataIndex, String learningobjective)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            driver.findElement(By.id("as-learing-objectives-span")).click(); //Clicking on Learning objective drop-down from footer
            driver.findElement(By.id("link-add-learning-objectives")).click(); //clicking on 'Add Learning Objective' link
            if(learningobjective.equals("true")) {
                driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[3]/label")).click();


            }
            else
                driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[@title='"+learningobjective+"']/label")).click();
            driver.findElement(By.cssSelector("span.add-collection-to-subtopic")).click();//click on Associate
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper 'QuestionCreate' in method 'associateTlo'", e);
        }
    }

    public void associateTlo(int dataIndex, String learningobjective,String noOfELOs)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            if(learningobjective.equals("true")) {
                List<WebElement> tlos = driver.findElements(By.xpath("//ins[@class='iCheck-helper']"));
                if(noOfELOs!=null){
                    for(int i=1;i<=Integer.parseInt(noOfELOs);i++) {
                        tlos.get(i).click();
                    }
                }
            }
            else {
                driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[@title='" + learningobjective + "']/label")).click();
            }
            Thread.sleep(4000);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper 'QuestionCreate' in method 'associateTlo'", e);
        }
    }

    public void enterValueInMathMLEditor(String operation,String value)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.cssSelector("button[title='"+operation+"']")).click();
            driver.findElement(By.className("wrs_focusElement")).sendKeys(value);
            //driver.findElement(By.cssSelector("#wiris-answer-container-save-choice1 > span.ui-button-text")).click();
            driver.findElement(By.id("wiris-container-save-choice1")).click();//click on Save
        }
        catch (Exception e){
            Assert.fail("Exception while entering value in Math ML editor", e);
        }
    }

    public void enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion(String operation,String value)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            Thread.sleep(5000);
            driver.findElement(By.cssSelector("button[title='"+operation+"']")).click();
            driver.findElement(By.className("wrs_focusElement")).sendKeys(value);
            driver.findElement(By.id("wiris-answer-container-save-choice1")).click();//click on Save
        }
        catch (Exception e){
            Assert.fail("Exception while entering value in Math ML editor", e);
        }
    }
    public void enterValueInClozeMatrix(int matrixIndex, int value)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//html//body")).click();
            List<WebElement> matrixCells = driver.findElements(By.className("matrix-text-area"));
            WebDriverUtil.clickOnElementUsingJavascript(matrixCells.get(matrixIndex));
            Thread.sleep(1000);
            new Action().doubleClick(driver.findElement(By.id("answer_choice_edit")));
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("answer_choice_txt")));
            new TextSend().textsendbyid(Integer.toString(value), "answer_choice_txt");
        }
        catch (Exception e) {
            Assert.fail("Exception while entering value in Cloze Matrix editor", e);
        }
    }

    public void saveQuestion(String learningobjective,String solutionText, String hintText, int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();

            if (solutionText == null || solutionText.equals("true"))
                new TextSend().textsendbyid("Solution Text", "content-solution");

            if (hintText == null || hintText.equals("true"))
                new TextSend().textsendbyid("Hint Text", "content-hint");
            Thread.sleep(1000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("saveQuestionDetails1")));
            WebDriverUtil.waitForAjax(driver,60);
            if (learningobjective != null)
                associateTlo(dataIndex, learningobjective);//add TLO
            new Click().clickByXpath("(//input[@type='checkbox']/following-sibling::ins)[2]");//Click on 1.OA.A.1
            Thread.sleep(4000);
            new Click().clickBycssselector("span[class^='close-standard-popup']");//Click on Done button
            WebDriverWait wait=new WebDriverWait(driver,60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Successfully saved.']")));
            WebDriverUtil.waitForAjax(driver,60);

        }
        catch (Exception e)
        {
            Assert.fail("Exception while saving a question",e);
        }
    }


    public void saveQuestion(String learningobjective,String solutionText, String hintText, int dataIndex,String nooOfElo)
    {
        try {
            WebDriver driver=Driver.getWebDriver();

            if (solutionText == null || solutionText.equals("true"))
                new TextSend().textsendbyid("Solution Text", "content-solution");

            if (hintText == null || hintText.equals("true"))
                new TextSend().textsendbyid("Hint Text", "content-hint");

            new Click().clickByid("saveQuestionDetails1");//click on save button
            WebDriverWait wait=new WebDriverWait(driver,60);
            if (learningobjective != null)
                associateTlo(dataIndex, learningobjective,nooOfElo);//add TLO
            new Click().clickBycssselector("span[class^='close-standard-popup']");//Click on Done button
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Successfully saved.']")));
            WebDriverUtil.waitForAjax(driver,60);

        }
        catch (Exception e)
        {
            Assert.fail("Exception while saving a question",e);
        }
    }

    public void fileUploadInQuestionCreation(String dataIndex)
    {
        try{
            WebDriver driver=Driver.getWebDriver();
            String filename = ReadTestData.readDataByTagName("", "filename",dataIndex);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("pickfiles")));
            //new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("widget-createimage_start_queue")));
            //new Click().clickByid("widget-createimage_start_queue");
            Thread.sleep(3000);
        }catch (Exception e)
        {
            Assert.fail("Exception while uploading a file",e);
        }
    }

    public void enterValueInMathMLEditorInQuestionTextBox(String operation,String value)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[title='" + operation + "']")));
            driver.findElement(By.cssSelector("button[title='"+operation+"']")).click();
            driver.findElement(By.className("wrs_focusElement")).sendKeys(value);
            driver.findElement(By.id("wiris-container-save-choice1")).click();//click on Save
        }
        catch (Exception e){
            Assert.fail("Exception while entering value in Math ML editor", e);
        }
    }
}
