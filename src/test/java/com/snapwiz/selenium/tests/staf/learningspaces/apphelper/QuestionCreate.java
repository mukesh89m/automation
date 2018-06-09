package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R237.LangaugePaletteForQuestionCreation;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

/*
 * Created by Sumit on 15/9/2014.
 */

public class QuestionCreate extends Driver{

    public void trueFalseQuestions(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String withImage = ReadTestData.readDataByTagName("", "withImage", Integer.toString(dataIndex));
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", Integer.toString(dataIndex));
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", Integer.toString(dataIndex));
            String multipleVideo = ReadTestData.readDataByTagName("", "multipleVideo", Integer.toString(dataIndex));
            String wistiaCode_multipleVideo = ReadTestData.readDataByTagName("", "wistiaCode_multipleVideo", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", Integer.toString(dataIndex));
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            String appendCharToQuestionText = ReadTestData.readDataByTagName("", "appendCharToQuestionText", Integer.toString(dataIndex));
            String bloomsTaxonomyValue = ReadTestData.readDataByTagName("", "bloomsTaxonomyValue", Integer.toString(dataIndex));

            new Click().clickbylistid("qtn-type-true-false-img", 0);//click on True/False type question
            if(shuffleAnswer != null) {
                if (shuffleAnswer.equals("true"))
                    new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-unchecked']");//check shuffle answer choice
            }
            new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.className("true-false-answer-select")));
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).clear();//type the question
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    manageContent.langaugePalette_spanish.click(); //click on the spanish language
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("italian")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    manageContent.langaugePalette_Italian.click(); //click on the italian language
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("french")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    manageContent.langaugePalette_French.click(); //click on the french language
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }
                if(appendCharToQuestionText==null)
                driver.findElement(By.id("question-raw-content")).sendKeys("True False " + questiontext);//type the question
                else
                    driver.findElement(By.id("question-raw-content")).sendKeys("True False " + questiontext+Assignment.appendChar);//type the question

            }

            else{
                if(appendCharToQuestionText==null)
                    driver.findElement(By.id("question-raw-content")).sendKeys("True False " + questiontext);//type the question
                else
                    driver.findElement(By.id("question-raw-content")).sendKeys("True False " + questiontext+Assignment.appendChar);//type the question
            }

            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(driver,180);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
                }
            }
            if(withVedio != null)
            {
                if(withVedio.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-embedvideo redactor-btn-image']");//click on video icon
                    new TextSend().textsendbyclass(wistiaCode,"media-code");
                    new Click().clickByid("cms-embed-media-insert-btn");//click on the insert button
                }
            }

            if(multipleVideo!=null)
            {
                String [] wistiaCodes = wistiaCode_multipleVideo.split(",");
                if(multipleVideo.equals("true"))
                {
                    for(int i=0;i<wistiaCodes.length;i++) {
                        new Click().clickBycssselector("a[class='re-icon re-embedvideo redactor-btn-image']");//click on video icon
                        new TextSend().textsendbyclass(wistiaCodes[i], "media-code");
                        new Click().clickByid("cms-embed-media-insert-btn");//click on the insert button
                        Thread.sleep(5000);
                        new Click().clickByid("question-raw-content");//Click on question area
                        //new Click().clickbyxpath(".//*[@id='question-raw-content']//div[1]/div[1]/div[1]/div[1");
                    }

                }
            }

            if(withUploadVideo!=null)
            {
                if(withUploadVideo.equals("true")){
                    new FileUpload().videoUpload(Integer.toString(dataIndex));
                }
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", Integer.toString(dataIndex));
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    driver.findElement(By.id("cms-embed-media-upload-tab")).click();
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
                }
            }

            if(withImageInHint!=null)
            {
                if(withImageInHint.equals("true"))
                {
                    new Click().clickByid("content-hint");//click on Hint textbox
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    Thread.sleep(30000);

                }
            }

            if(withImageInSolution!=null)
            {
                if(withImageInSolution.equals("true"))
                {
                    new Click().clickByid("content-solution");//click on Solution textbox
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    Thread.sleep(30000);

                }
            }
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option A
            Thread.sleep(2000);
             if(bloomsTaxonomyValue!=null){
                 new Click().clickbylinkText("Bloom's Taxonomy");
                 if(bloomsTaxonomyValue.equals("Knowledge")){
                     new Click().clickbylinkText("Knowledge");
                 }else if(bloomsTaxonomyValue.equals("Comprehension")){
                     new Click().clickbylinkText("Comprehension");
                 }else if(bloomsTaxonomyValue.equals("Application")){
                     new Click().clickbylinkText("Application");
                 }else if(bloomsTaxonomyValue.equals("Analysis")){
                     new Click().clickbylinkText("Analysis");
                 }else if(bloomsTaxonomyValue.equals("Synthesis")){
                     new Click().clickbylinkText("Synthesis");
                 }else if(bloomsTaxonomyValue.equals("Evaluation")){
                     new Click().clickbylinkText("Evaluation");
                 }
             }
            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper QuestionCreate in method trueFalseQuestions.", e);
        }
    }
    public void multipleChoice(int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
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
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", Integer.toString(dataIndex));
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", Integer.toString(dataIndex));
            String multipleVideo = ReadTestData.readDataByTagName("", "multipleVideo", Integer.toString(dataIndex));
            String wistiaCode_multipleVideo = ReadTestData.readDataByTagName("", "wistiaCode_multipleVideo", Integer.toString(dataIndex));
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", Integer.toString(dataIndex));
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", Integer.toString(dataIndex));
            String withImage = ReadTestData.readDataByTagName("", "withImage", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));

            new Click().clickbylistid("qtn-multiple-choice-img", 0);//click on Multiple Choice type question
            new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-checked']");//Uncheck shuffle answer choice
            if(shuffleAnswer != null) {
                if (shuffleAnswer.equals("true"))
                    new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-unchecked']");//check shuffle answer choice
            }
            new Click().clickByid("question-mc-raw-content");//click on Question

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    manageContent.langaugePalette_spanish.click(); //click on the spanish language
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("italian")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    manageContent.langaugePalette_Italian.click(); //click on the italian language
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("french")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    manageContent.langaugePalette_French.click(); //click on the french language
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }
                driver.findElement(By.id("question-mc-raw-content")).sendKeys("Multiple Choice "+questiontext);//type the question
            }
            else {
                driver.findElement(By.id("question-mc-raw-content")).sendKeys("Multiple Choice "+questiontext);//type the question

            }

            if(withImage != null) {
                if(withImage.equals("true")) {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(driver,180);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
                }
            }
            if(withVedio != null) {
                if(withVedio.equals("true")) {
                    new Click().clickBycssselector("a[class='re-icon re-embedvideo redactor-btn-image']");//click on video icon
                    new TextSend().textsendbyclass(wistiaCode,"media-code");
                    new Click().clickByid("cms-embed-media-insert-btn");//click on the insert button
                }
            }
            if(withImageInSolution!=null)
            {
                if(withImageInSolution.equals("true"))
                {
                    new Click().clickByid("content-solution");//click on Solution textbox
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    Thread.sleep(30000);

                }
            }

            if(withImageInHint!=null)
            {
                if(withImageInHint.equals("true"))
                {
                    new Click().clickByid("content-hint");//click on Hint textbox
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    Thread.sleep(30000);

                }
            }
            if(multipleVideo!=null)
            {
                String [] wistiaCodes = wistiaCode_multipleVideo.split(",");
                if(multipleVideo.equals("true"))
                {
                    for(int i=0;i<wistiaCodes.length;i++) {
                        new Click().clickBycssselector("a[class='re-icon re-embedvideo redactor-btn-image']");//click on video icon
                        new TextSend().textsendbyclass(wistiaCodes[i], "media-code");
                        new Click().clickByid("cms-embed-media-insert-btn");//click on the insert button
                        new Click().clickByid("question-raw-content");//Click on question area
                    }

                }
            }

            if(withUploadVideo!=null)
            {
                if(withUploadVideo.equals("true")){
                    new FileUpload().videoUpload(Integer.toString(dataIndex));
                }
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", Integer.toString(dataIndex));
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id("cms-embed-media-upload-tab")));
                    Thread.sleep(2000);
                    new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename1 + "^");
                    driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
                }
            }

            new Click().clickBycssselector("div[class='single-select-choice-icon single-select-choice-icon-deselect']"); //select correct answer as A
            List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("Option 1");
            answerOptions.get(1).sendKeys("Option 2");
            answerOptions.get(2).sendKeys("Option 3");
            answerOptions.get(3).sendKeys("Option 4");

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper QuestionCreate in method multipleChoice.", e);
        }
    }

    public void textEntry(int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", Integer.toString(dataIndex));
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", Integer.toString(dataIndex));
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", Integer.toString(dataIndex));
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", Integer.toString(dataIndex));
            String enableSpanish = ReadTestData.readDataByTagName("", "enableSpanish", Integer.toString(dataIndex));
            String withImage = ReadTestData.readDataByTagName("", "withImage", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            String enablePalette = ReadTestData.readDataByTagName("", "enablePalette", Integer.toString(dataIndex));


            new Click().clickbylistid("qtn-text-entry-img", 0);//click on Text Entry type question
            new Click().clickByid("question-raw-content");//click on Question
            Thread.sleep(5000);

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheSpanish();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("italian")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                   clickOnTheItalian();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("french")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheFrench();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }
                driver.findElement(By.id("question-raw-content")).sendKeys("Text Entry "+questiontext);//type the question

            }
            else {
                driver.findElement(By.id("question-raw-content")).sendKeys("Text Entry "+questiontext);//type the question
            }

            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(driver,180);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
                }
            }

            if(withVedio != null)
            {
                if(withVedio.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-embedvideo redactor-btn-image']");//click on video icon
                    new TextSend().textsendbyclass(wistiaCode, "media-code");
                    new Click().clickByid("cms-embed-media-insert-btn");//click on the insert button
                }
            }

            if(withUploadVideo!=null)
            {
                if(withUploadVideo.equals("true")){
                    new FileUpload().videoUpload(Integer.toString(dataIndex));
                }
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", Integer.toString(dataIndex));
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
                }
            }
            if(enableSpanish!=null)
            {
                new Click().clickBycssselector("a[title='Select Language Palette']"); //select language
                new Click().clickBycssselector("a[rel='spanish']");
                new Click().clickByclassname("spanish-popup"); //click on the spanish icon
                for(int i=0;i<5;i++) {
                    new Click().clickbyxpath("//input[@value='Á']"); //click on A
                }
                new Click().clickByid("save-language-text"); //click on the save button
                new Click().clickBycssselector("span[class='btn sty-green save-language-text accept_answer text_entry_accept_answer']"); //click on Accept answer button
            }
            if(enablePalette!=null)
            {
                new Click().clickBycssselector("a[title='Select Language Palette']"); //select language
                if(enablePalette.equals("spanish")){
                    new Click().clickBycssselector("a[rel='spanish']");
                    new Click().clickByclassname("spanish-popup"); //click on the spanish icon
                    List<WebElement> spanishChars = driver.findElements(By.className("language-icon"));
                    for(int i=0;i<spanishChars.size();i++)
                    {
                        spanishChars.get(i).click();
                    }
                }
                if(enablePalette.equals("french")){
                    new Click().clickBycssselector("a[rel='french']");
                    new Click().clickByclassname("spanish-popup"); //click on the spanish icon
                    List<WebElement> spanishChars = driver.findElements(By.className("language-icon"));
                    for(int i=0;i<spanishChars.size();i++)
                    {
                        spanishChars.get(i).click();
                    }
                }
                if(enablePalette.equals("italian")){
                    new Click().clickBycssselector("a[rel='italian']");
                    new Click().clickByclassname("spanish-popup"); //click on the spanish icon
                    List<WebElement> spanishChars = driver.findElements(By.className("language-icon"));
                    for(int i=0;i<spanishChars.size();i++)
                    {
                        spanishChars.get(i).click();
                    }
                }
                driver.findElement(By.id("save-language-text")).click();
                new Click().clickBycssselector("span[class='btn sty-green save-language-text accept_answer text_entry_accept_answer']"); //click on Accept answer button
            }
            else
            {
                if(enableSpanish==null) {
                    new TextSend().textsendbycssSelector("Correct Answer", "input[class='numeric_text_entry_input get-user-entry is-value-changed-txt-entry']");
                    new Click().clickBycssselector("span[class='btn sty-green save-language-text accept_answer text_entry_accept_answer']"); //click on Accept answer button

                }
            }

            new Click().clickByid("question-raw-content");//click on Question

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e) {
            Assert.fail("Exception while creating Text Entry Question type",e);
        }

    }

    public void textDropDown(int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", Integer.toString(dataIndex));
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", Integer.toString(dataIndex));
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", Integer.toString(dataIndex));
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", Integer.toString(dataIndex));
            String withImage = ReadTestData.readDataByTagName("", "withImage", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));

            new Click().clickbylistid("qtn-text-entry-drop-down-img", 0);//click on Text Entry Drop Down type question
            new Click().clickByid("question-raw-content");//click on Question

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    manageContent.langaugePalette_spanish.click(); //click on the spanish language
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("italian")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    manageContent.langaugePalette_Italian.click(); //click on the italian language
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("french")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    manageContent.langaugePalette_French.click(); //click on the french language
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }
                driver.findElement(By.id("question-raw-content")).sendKeys("Text Drop Down "+questiontext);//type the question
            }
            else {
                driver.findElement(By.id("question-raw-content")).sendKeys("Text Drop Down "+questiontext);//type the question

            }

            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(driver,180);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
                }
            }

            if(withVedio != null)
            {
                if(withVedio.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-embedvideo redactor-btn-image']");//click on video icon
                    new TextSend().textsendbyclass(wistiaCode,"media-code");
                    new Click().clickByid("cms-embed-media-insert-btn");//click on the insert button
                }
            }

            if(withUploadVideo!=null)
            {
                if(withUploadVideo.equals("true")){
                    new FileUpload().videoUpload(Integer.toString(dataIndex));
                }
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", Integer.toString(dataIndex));
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    driver.findElement(By.id("cms-embed-media-upload-tab")).click();
                    //  ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
                }
            }
            new Click().clickByclassname("text-drop-val");
            new TextSend().textsendbyid("Answer1","ans1");

            new Click().clickByclassname("select-icon-text-drop-down"); //select answer 1 as correct answer

            driver.findElements(By.className("text-drop-val")).get(1).click();
            driver.findElements(By.id("ans1")).get(1).sendKeys("Answer2");

            driver.findElements(By.className("text-drop-val")).get(2).click();
            driver.findElements(By.id("ans1")).get(2).sendKeys("Answer3");

            new Click().clickBycssselector("span[class='done-button btn sty-green text-drop-accept accept_answer']"); //click on accept answer button

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e) {
            Assert.fail("Exception while creating text entry drop down question",e);
        }
    }

    public void numericEntryWithUnits(int dataIndex) {
        try {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", Integer.toString(dataIndex));
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", Integer.toString(dataIndex));
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", Integer.toString(dataIndex));
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", Integer.toString(dataIndex));
            String withImage = ReadTestData.readDataByTagName("", "withImage", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            String withAudio = ReadTestData.readDataByTagName("", "withAudio", Integer.toString(dataIndex));
            String url = ReadTestData.readDataByTagName("", "url", Integer.toString(dataIndex));

            new Click().clickbylistid("qtn-text-entry-numeric-units-img", 0);//click on Numeric Entry with Units type question
            Thread.sleep(5000);
            new Click().clickByid("question-raw-content");//click on Question
            Thread.sleep(2000);
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheSpanish();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("italian")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheItalian();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("french")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheFrench();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }
                driver.findElement(By.id("question-raw-content")).sendKeys("Numeric Entry With Units " + questiontext);//type the question
            }
            else {
                driver.findElement(By.id("question-raw-content")).sendKeys("Numeric Entry With Units " + questiontext);//type the question
            }
            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(driver,180);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
                }
            }

            if(withVedio != null)
            {
                if(withVedio.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-embedvideo redactor-btn-image']");//click on video icon
                    new TextSend().textsendbyclass(wistiaCode,"media-code");
                    new Click().clickByid("cms-embed-media-insert-btn");//click on the insert button
                }
            }

            if(withUploadVideo!=null)
            {
                if(withUploadVideo.equals("true")){
                    new FileUpload().videoUpload(Integer.toString(dataIndex));
                }
            }

            if(withAudio != null)
            {
                if(withAudio.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on video icon
                    new TextSend().textsendbyclass(url,"media-code");
                    new Click().clickByid("cms-embed-media-insert-btn");//click on the insert button
                }
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", Integer.toString(dataIndex));
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
                }
            }

            new TextSend().textsendbycssSelector("10", "input[class='numeric_unit_correct_answer numeric_text_entry numeric_text_entry_input is-value-changed']");

            new Click().clickBycssselector("div[class='unit-arrow-down add-more-num-entry-unit']"); //click on add more units
            new Click().clickBycssselector("li[value='feet']"); //select feet
            new Click().clickByclassname("unit_tick_image"); //click on the tick mark for unit
            new Click().clickBycssselector("div[class='add-more-num-entry-unit unit-arrow-down']"); //click on add more units
            new Click().clickBycssselector("li[value='yards']"); //select yards
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget num_ent_unit_accept_answer']"); //click on accept answer

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating Numeric Entry With Units question",e);
        }
    }

    public void advancedNumeric(int dataIndex) {
        try {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", Integer.toString(dataIndex));
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", Integer.toString(dataIndex));
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", Integer.toString(dataIndex));
            String withImage = ReadTestData.readDataByTagName("", "withImage", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));

            new Click().clickByid("qtn-numeric-advanced-img");//click on Advanced Numeric question
            new Click().clickByid("question-raw-content");//click on Question
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheSpanish();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("italian")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheItalian();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("french")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheFrench();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }
                driver.findElement(By.id("question-raw-content")).sendKeys("Advanced Numeric "+questiontext);//type the question
            }
            else {
                driver.findElement(By.id("question-raw-content")).sendKeys("Advanced Numeric "+questiontext);//type the question

            }
            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(driver,180);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
                }
            }

            if(withVedio != null)
            {
                if(withVedio.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-embedvideo redactor-btn-image']");//click on video icon
                    new TextSend().textsendbyclass(wistiaCode,"media-code");
                    new Click().clickByid("cms-embed-media-insert-btn");//click on the insert button
                }
            }

            if(withUploadVideo!=null)
            {
                if(withUploadVideo.equals("true")){
                    new FileUpload().videoUpload(Integer.toString(dataIndex));
                }
            }


            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", Integer.toString(dataIndex));
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
                }
            }

            new TextSend().textsendbycssSelector("10","input[class='numeric_correct_text_entry_input num-entry-ans border-color-gray']");

            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer accept_answer_maple_numeric']"); //click on accept answer
            new Click().clickByid("question-raw-content");//click on Question

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);


        } catch (Exception e) {
            Assert.fail("Exception while creating Advanced Numeric Question",e);
        }
    }

    public void expressionEvaluator(int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", Integer.toString(dataIndex));
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", Integer.toString(dataIndex));
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", Integer.toString(dataIndex));
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", Integer.toString(dataIndex));
            String withImage = ReadTestData.readDataByTagName("", "withImage", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));

            new Click().clickByid("qtn-expression-evaluator-img");//click on Expression Evaluator type question
            new Click().clickByid("question-raw-content");//click on Question
            Thread.sleep(6000);
           /* driver.findElement(By.id("question-raw-content")).clear();
            Thread.sleep(3000);*/
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheSpanish();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("italian")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheItalian();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("french")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheFrench();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }
                driver.findElement(By.id("question-raw-content")).sendKeys("Expression Evaluator " + questiontext);//type the question
            }
            else {
                driver.findElement(By.id("question-raw-content")).sendKeys("Expression Evaluator " + questiontext);//type the question
                new Click().clickByid("question-raw-content");//click on Question
            }


            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(driver,180);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
                }
            }




            if(withVedio != null)
            {
                if(withVedio.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-embedvideo redactor-btn-image']");//click on video icon
                    new TextSend().textsendbyclass(wistiaCode,"media-code");
                    new Click().clickByid("cms-embed-media-insert-btn");//click on the insert button
                }
            }

            if(withUploadVideo!=null)
            {
                if(withUploadVideo.equals("true")){
                    new FileUpload().videoUpload(Integer.toString(dataIndex));
                }
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", Integer.toString(dataIndex));
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
                }
            }

            new Click().clickByid("answer_math_edit");
            enterValueInMathMLEditor("Square root","5");

            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer maple_symc_not_accept_answer']"); //click on accept answer

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel, withImageInHint, withImageInSolution, dataIndex, score);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating Expression Evaluator Question",e);
        }
    }

    public void matchTheFollowing(int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String distractor = ReadTestData.readDataByTagName("", "distractor", Integer.toString(dataIndex));
            String addnewrow = ReadTestData.readDataByTagName("", "addnewrow", Integer.toString(dataIndex));
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", Integer.toString(dataIndex));
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", Integer.toString(dataIndex));
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", Integer.toString(dataIndex));
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", Integer.toString(dataIndex));
            String withImage = ReadTestData.readDataByTagName("", "withImage", Integer.toString(dataIndex));
            String withAudio = ReadTestData.readDataByTagName("", "withAudio", Integer.toString(dataIndex));
            String url = ReadTestData.readDataByTagName("", "url", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));

            new Click().clickByid("qtn-mtf-type");//click on Match the Following type question
            new Click().clickByid("question-raw-content");//click on Question
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheSpanish();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("italian")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheItalian();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("french")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheFrench();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }
                driver.findElement(By.id("question-raw-content")).sendKeys("Match the Following " + questiontext);//type the question
            }
            else {
                driver.findElement(By.id("question-raw-content")).sendKeys("Match the Following " + questiontext);//type the question
            }
            if(withVedio != null)
            {
                if(withVedio.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-embedvideo redactor-btn-image']");//click on video icon
                    new TextSend().textsendbyclass(wistiaCode,"media-code");
                    new Click().clickByid("cms-embed-media-insert-btn");//click on the insert button
                }
            }
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

            if(distractor!=null){
                if(distractor.equals("true")){
                    //To add distractor and new row
                    driver.findElement(By.xpath(".//*[@id='add-distractor']/span/span")).click();
                    List<WebElement> rhsboxes1 = driver.findElements(By.cssSelector("div[class='dnd-match-rhs box']")); //Fetching all the lhs boxes
                    rhsboxes1.get(5).click();
                    Thread.sleep(2000);
                    driver.findElement(By.id("answer_choice_edit")).click();
                    new TextSend().textsendbyid("1000", "answer_choice_txt");

                    driver.findElement(By.xpath(".//*[@id='add-new-row']/span/span")).click();
                    List<WebElement> rhsboxes2 = driver.findElements(By.cssSelector("div[class='dnd-match-rhs box']")); //Fetching all the lhs boxes
                    rhsboxes2.get(6).click();
                    driver.findElement(By.id("answer_choice_edit")).click();
                    new TextSend().textsendbyid("100", "answer_choice_txt");

                    List<WebElement> lhsboxes1 = driver.findElements(By.xpath("//tr[@class='distractor']/following-sibling::tr/td/div/div")); //Fetching all the lhs boxes
                    lhsboxes1.get(0).click();
                    driver.findElement(By.id("answer_choice_edit")).click();
                    new TextSend().textsendbyid("100", "answer_choice_txt");
                }
            }

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel, withImageInHint, withImageInSolution, dataIndex, score);

        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating Match the Following Question",e);
        }
    }

    public void dragAndDrop(int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", Integer.toString(dataIndex));
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", Integer.toString(dataIndex));
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", Integer.toString(dataIndex));
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", Integer.toString(dataIndex));
            String withImage = ReadTestData.readDataByTagName("", "withImage", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));

            new Click().clickByid("qtn-dnd-type");//click on Drag and Drop type question
            new Click().clickByid("question-raw-content");//click on Question
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheSpanish();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("italian")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheItalian();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("french")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheFrench();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }
                driver.findElement(By.id("question-raw-content")).sendKeys("Drag and Drop " + questiontext);//type the question
            }
            else {
                driver.findElement(By.id("question-raw-content")).sendKeys("Drag and Drop " + questiontext);//type the question
            }

            if(withVedio != null)
            {
                if(withVedio.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-embedvideo redactor-btn-image']");//click on video icon
                    new TextSend().textsendbyclass(wistiaCode,"media-code");
                    new Click().clickByid("cms-embed-media-insert-btn");//click on the insert button
                }
            }
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
            driver.findElement(By.id("isShuffleAnswerChoice")).click();

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel, withImageInHint, withImageInSolution, dataIndex, score);

        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating Drag and Drop Question",e);
        }
    }

    public void clozeFormula(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));

            new Click().clickByid("qtn-cf-type");//click on Cloze Formula type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Cloze Formula "+questiontext);//type the question

            List<WebElement> answers =  driver.findElements(By.className("answer"));
            answers.get(0).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","1");

            answers.get(1).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","2");

            answers.get(2).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root", "3");

            answers.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root", "4");

            answers.get(4).click();
            driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","5");

            List<WebElement> formulaLHS = driver.findElements(By.cssSelector("div[class='dnd-match-lhs box cloze-formula-lhs']"));
            formulaLHS.get(0).click();
            driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            enterValueInMathMLEditor("Square root", "6");

            formulaLHS.get(1).click();
            driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            enterValueInMathMLEditor("Square root","7");

            formulaLHS.get(2).click();
            driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            enterValueInMathMLEditor("Square root","8");

            formulaLHS.get(3).click();
            driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            enterValueInMathMLEditor("Square root","9");

            formulaLHS.get(4).click();
            driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            enterValueInMathMLEditor("Square root","10");


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
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
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
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", Integer.toString(dataIndex));
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", Integer.toString(dataIndex));
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", Integer.toString(dataIndex));
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", Integer.toString(dataIndex));
            String withImage = ReadTestData.readDataByTagName("", "withImage", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));

            new Click().clickbylistid("qtn-multiple-selection-img", 0);//click on Multiple Selection type question
            new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-checked']");//Uncheck shuffle answer choice
            if(shuffleAnswer != null) {
                if (shuffleAnswer.equals("true"))
                    new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-unchecked']");//check shuffle answer choice
            }
            new Click().clickByid("question-ms-raw-content");//click on Question
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                   clickOnTheSpanish();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("italian")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheItalian();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("french")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheFrench();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }
                driver.findElement(By.id("question-ms-raw-content")).sendKeys("Multi Selection " + questiontext);//type the question
            }
            else {
                driver.findElement(By.id("question-ms-raw-content")).sendKeys("Multi Selection " + questiontext);//type the question
            }
            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(driver,180);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
                }
            }


            if(withVedio != null)
            {
                if(withVedio.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-embedvideo redactor-btn-image']");//click on video icon
                    new TextSend().textsendbyclass(wistiaCode,"media-code");
                    new Click().clickByid("cms-embed-media-insert-btn");//click on the insert button
                }
            }

            if(withUploadVideo!=null)
            {
                if(withUploadVideo.equals("true")){
                    new FileUpload().videoUpload(Integer.toString(dataIndex));
                }
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", Integer.toString(dataIndex));
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
                }
            }
            if(withImageInHint!=null)
            {
                if(withImageInHint.equals("true"))
                {
                    new Click().clickByid("content-hint");//click on Hint textbox
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    Thread.sleep(30000);

                }
            }

            List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("Option 1");
            answerOptions.get(1).sendKeys("Option 2");
            answerOptions.get(2).sendKeys("Option 3");
            answerOptions.get(3).sendKeys("Option 4");;
            new Click().clickByid("swuploadclose");//close pop-up
            List<WebElement> multipleSelections = driver.findElements(By.cssSelector("div[class='multiple-select-choice-icon multiple-select-choice-icon-deselect']"));
            multipleSelections.get(0).click(); multipleSelections.get(1).click(); //selecting option A and B as correct answers
            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating multiple selection question type", e);
        }
    }

    public void graphPlotter(int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));

            new Click().clickByid("qtn-graph-type");//click on Graph Plotter type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Graph Plotter "+questiontext);//type the question
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
            WebDriver driver=Driver.getWebDriver();
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            new Click().clickByid("qtn-cloze-matrix-type");//click on Cloze Matrix type question
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
            WebDriver driver=Driver.getWebDriver();
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
            driver.findElement(By.id("question-raw-content")).sendKeys("Resequence "+questiontext);//type the question
        }
        catch (Exception e) {
            Assert.fail("Exception while creating Resequence question type",e);
        }
    }

    public void essay(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
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
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", Integer.toString(dataIndex));
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", Integer.toString(dataIndex));
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", Integer.toString(dataIndex));
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", Integer.toString(dataIndex));
            String withImage = ReadTestData.readDataByTagName("", "withImage", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));

            new Click().clickbylistid("qtn-essay-type", 0);//click on Essay type question
            new Click().clickByid("question-raw-content");//click on Question
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheSpanish();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("italian")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheItalian();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("french")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheFrench();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }
                driver.findElement(By.id("question-raw-content")).sendKeys("Essay "+questiontext);//type the question
            }
            else {
                driver.findElement(By.id("question-raw-content")).sendKeys("Essay "+questiontext);//type the question
            }
            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(driver,180);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
                }
            }
            if(withVedio != null)
            {
                if(withVedio.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-embedvideo redactor-btn-image']");//click on video icon
                    new TextSend().textsendbyclass(wistiaCode,"media-code");
                    new Click().clickByid("cms-embed-media-insert-btn");//click on the insert button
                }
            }

            if(withUploadVideo!=null)
            {
                if(withUploadVideo.equals("true")){
                    new FileUpload().videoUpload(Integer.toString(dataIndex));
                }
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", Integer.toString(dataIndex));
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
                }
            }

            new Click().clickByid("essay-question-height");//click on Min line height textbox
            new TextSend().textsendbyid("3", "essay-question-height");
            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e) {
            Assert.fail("Exception while creating essay question type",e);
        }
    }

    public void writeBoard(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", Integer.toString(dataIndex));
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", Integer.toString(dataIndex));
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", Integer.toString(dataIndex));
            String withImage = ReadTestData.readDataByTagName("", "withImage", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));

            new Click().clickByid("qtn-writeboard-type-new");//click on writeboard type question
            new Click().clickByid("question-raw-content");//click on Question
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                   clickOnTheSpanish();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("italian")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheItalian();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("french")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                   clickOnTheFrench();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }
                driver.findElement(By.id("question-raw-content")).sendKeys("Write Board "+questiontext);//type the question
            }
            else{
                driver.findElement(By.id("question-raw-content")).sendKeys("Write Board "+questiontext);//type the question
            }
            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));

                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(driver,180);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
                }
            }


            if(withVedio != null)
            {
                if(withVedio.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-embedvideo redactor-btn-image']");//click on video icon
                    new TextSend().textsendbyclass(wistiaCode,"media-code");
                    new Click().clickByid("cms-embed-media-insert-btn");//click on the insert button
                }
            }

            if(withUploadVideo!=null)
            {
                if(withUploadVideo.equals("true")){
                    new FileUpload().videoUpload(Integer.toString(dataIndex));
                }
            }


            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", Integer.toString(dataIndex));
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
                }
            }

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e) {
            Assert.fail("Exception while creating essay question type",e);
        }
    }
    public void multiPartQuestion(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String questiontextstem2 = ReadTestData.readDataByTagName("", "questiontextstem2", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String dependentPart = ReadTestData.readDataByTagName("", "dependentPart", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String needNotToBePubished=ReadTestData.readDataByTagName("","needNotToBePubished",Integer.toString(dataIndex));
            String status=ReadTestData.readDataByTagName("","status",Integer.toString(dataIndex));
            String overrideQuestionParts=ReadTestData.readDataByTagName("","overrideQuestionParts",Integer.toString(dataIndex));
            String selectLanguagePalette=ReadTestData.readDataByTagName("","selectLanguagePalette",Integer.toString(dataIndex));
            String withImage = ReadTestData.readDataByTagName("", "withImage", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));

            new Click().clickByid("qtn-multi-part");//click on Multi-part question
            if(!(overrideQuestionParts == null)){
                if(overrideQuestionParts.equalsIgnoreCase("true")){
                    new Click().clickByid("one");
                }
            }
            if(dependentPart!=null){
                if(dependentPart.equals("true")){
                    driver.findElement(By.xpath("//label[@for='one']")).click(); //click on the dependent
                }
            }

            Thread.sleep(4000);
            WebDriverUtil.executeJavascript("document.getElementById('question-mp-raw-content-0').innerHTML='" + questiontext + "';");
            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(driver,180);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
                }
            }
            new Click().clickByid("saveQuestionDetails1");//click on Save
            new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
            new Click().clickByid("qtn-type-true-false-img");//click on true false type question
            new TextSend().textsendbyid("1", "mpq-question-part-input-label");//enter label in true false MPQ
            new Click().clickByid("question-raw-content");//click on Question

            if (solutionText == null || solutionText.equals("true")) {
                if (withImageInSolution != null) {
                    if (withImageInSolution.equals("true"))
                        driver.findElement(By.id("content-solution")).sendKeys("Solution Text");
                } else
                    new TextSend().textsendbyid("Solution Text", "content-solution");
            }


            if (score != null) {
                driver.findElement(By.id("questionScore")).clear();
                new TextSend().textsendbyid(score, "questionScore");
            }

            if (hintText == null || hintText.equals("true")) {
                if (withImageInHint != null) {
                    if (withImageInHint.equals("true"))
                        driver.findElement(By.id("content-hint")).sendKeys("Hint Text");
                    else
                        new TextSend().textsendbyid("Hint Text", "content-hint");
                } else
                    new TextSend().textsendbyid("Hint Text", "content-hint");
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                   clickOnTheSpanish();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("italian")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheItalian();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("french")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                   clickOnTheFrench();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }
                driver.findElement(By.id("question-raw-content")).sendKeys("True False For MPQ"+questiontext);//type the question
            }
            else {
                driver.findElement(By.id("question-raw-content")).sendKeys("True False For MPQ");//type the question
            }
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option A


            new Click().clickByid("saveQuestionDetails1");//click on Save
            new Click().clickBycssselector("img[alt='Back']");//click on Back
            new Click().clickByclassname("cms-multipart-add-stem");//Click on "Add question stem" button
            new Click().clickByid("question-mp-raw-content-2");
            new TextSend().textsendbyid(questiontextstem2, "question-mp-raw-content-2");//question for stem 2
            new Click().clickByid("saveQuestionDetails1");//click on Save
            new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
            new Click().clickbylistid("qtn-multiple-choice-img", 0);//click on Multiple Choice type question
            new Click().clickByid("question-mc-raw-content");//click on Question
            driver.findElement(By.id("question-mc-raw-content")).sendKeys("Multiple Choice Question");//type the question
            new Click().clickBycssselector("div[class='single-select-choice-icon single-select-choice-icon-deselect']"); //select correct answer as A
            List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("Option 1");
            answerOptions.get(1).sendKeys("Option 2");
            answerOptions.get(2).sendKeys("Option 3");
            answerOptions.get(3).sendKeys("Option 4");
            new TextSend().textsendbyid("1", "mpq-question-part-input-label");//enter label in true false MPQ
            if(learningobjective != null)
                associateTlo(dataIndex, learningobjective);//add TLO
            new Click().clickByid("saveQuestionDetails1");//click on save button
            new Click().clickBycssselector("img[alt='Back']");//click on Back
            if(useWriteBoard != null)
            {
                new Click().clickBycssselector("#content-writeboard > #writeboard");//check the use writeboard check box
            }
            if(difficultylevel != null)
            {
                new ComboBox().selectValue(6, difficultylevel);
            }

            if(score!=null){
                driver.findElement(By.id("questionScore")).clear();
                new TextSend().textsendbyid(score,"questionScore");
            }
            if(variationLevel != null)
            {
                new Click().clickBycssselector("#content-variationlevel > select");
                new Click().clickBycssselector("option[value='"+variationLevel+"']");
            }

            new Click().clickbylinkText("Draft"); //click on Draft option

            System.out.println("needNotToBePubished : " + needNotToBePubished);
            if(needNotToBePubished ==null)
                System.out.println("1");
                new Click().clickbylinkText("Publish");

            if(needNotToBePubished !=null){
                if(!(needNotToBePubished.equals("true"))) {
                    System.out.println("publishing");
                    new Click().clickbylinkText("Publish");    //click on Publish option if isPublished is null or tru
                    System.out.println("2");
                }
            }
            if(status !=null) {
                if (status.equals("accuracyCheck"))
                    new Click().clickbylinkText("Accuracy Check");    //select accuracy check option
                if (status.equals("draftPendingImages"))
                    new Click().clickbylinkText("Draft - Pending Images");    //select Draft - Pending Images
                if (status.equals("qa"))
                    new Click().clickbylinkText("QA");    //select QA
                if (status.equals("needRevision"))
                    new Click().clickbylinkText("Need Revision");    //select Need Revision
                if (status.equals("approve"))
                    new Click().clickbylinkText("Approve");    //select Approve
                if (status.equals("readyToPublish"))
                    new Click().clickbylinkText("Ready to Publish");    //select Ready to Publish
                if (status.equals("publish"))
                    new Click().clickbylinkText("Publish");    //select Ready to Publish
                if (status.equalsIgnoreCase("needNotToBePublished")) ;
            }
            new Click().clickByid("saveQuestionDetails1");//click on save button

            Thread.sleep(2000);

        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating MPQ type",e);
        }
    }

    public void audio(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", Integer.toString(dataIndex));
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", Integer.toString(dataIndex));
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", Integer.toString(dataIndex));
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", Integer.toString(dataIndex));
            String withImage = ReadTestData.readDataByTagName("", "withImage", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));

            new Click().clickByid("qtn-audio-type");//click on writeboard type question
            new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.id("question-audio-content")));
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("question-raw-content")));//click on Question
            Thread.sleep(5000);
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheSpanish();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("italian")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheItalian();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("french")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheFrench();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }
                driver.findElement(By.id("question-raw-content")).sendKeys("audio " + questiontext);//type the question
            }
            else {
                driver.findElement(By.id("question-raw-content")).sendKeys("audio " + questiontext);//type the question
            }
            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(driver,180);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
                }
            }


            if(withVedio != null)
            {
                if(withVedio.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-embedvideo redactor-btn-image']");//click on video icon
                    new TextSend().textsendbyclass(wistiaCode,"media-code");
                    new Click().clickByid("cms-embed-media-insert-btn");//click on the insert button
                }
            }

            if(withUploadVideo!=null)
            {
                if(withUploadVideo.equals("true")){
                    new FileUpload().videoUpload(Integer.toString(dataIndex));
                }
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", Integer.toString(dataIndex));
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
                }
            }

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e) {
            Assert.fail("Exception while creating audio question type",e);
        }
    }

    public void enterValueInMathMLEditor(String operation,String value)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.cssSelector("button[title='"+operation+"']")).click();
            driver.findElement(By.className("wrs_focusElement")).sendKeys(value);
            driver.findElement(By.id("wiris-answer-container-save-choice1")).click();
        }
        catch (Exception e){
            Assert.fail("Exception while entering value in Math ML editor");
        }
    }

    public void enterValueInClozeMatrix(int matrixIndex, int value)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            List<WebElement> matrixCells = driver.findElements(By.className("matrix-text-area"));
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
            WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.id("learing-objectives-span")).click(); //Clicking on Learning objective drop-down from footer
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id("link-add-learning-objectives")));
            driver.findElement(By.id("link-add-learning-objectives")).click(); //clicking on 'Add Learning Objective' link
            Thread.sleep(2000);
            if(learningobjective.equals("true"))
                driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[3]/label")).click();
            else {
                if(learningobjective.equals("associateTLO")){
                    List<WebElement> element = driver.findElements(By.xpath("//div[@class='taxonomyStructureContent']/div/label"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element.get(element.size()-1));
                    element.get(element.size()-1).click(); //select last tlo
                    Thread.sleep(3000);
                }else {
                    WebElement element = driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[" + learningobjective + "]/label"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                    driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[" + learningobjective + "]/label")).click();
                    Thread.sleep(3000);
                }
            }
            new Click().clickBycssselector("span.add-collection-to-subtopic");//click on Associate
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper QuestionCreate in method associateTlo.", e);
        }
    }
    public void saveQuestion(String learningobjective,String solutionText, String hintText, String useWriteBoard, String difficultylevel, String variationLevel,String withImageInHint,String withImageInSolution, int dataIndex, String score) {
        try {
            WebDriver driver=Driver.getWebDriver();
            if (useWriteBoard != null) {
                new Click().clickBycssselector("#content-writeboard > #writeboard");//check the use writeboard check box
            }
            if (difficultylevel != null) {
                new Click().clickbylinkText("Difficulty Level");
                new UIElement().waitAndFindElement(By.linkText(difficultylevel));
                new Click().clickbylinkText(difficultylevel);
            }
            if (learningobjective != null)
                associateTlo(dataIndex, learningobjective);//add TLO

            if (solutionText == null || solutionText.equals("true")) {
                if (withImageInSolution != null) {
                    if (withImageInSolution.equals("true"))
                        driver.findElement(By.id("content-solution")).sendKeys("Solution Text");
                } else
                    new TextSend().textsendbyid("Solution Text", "content-solution");
            }


            if (score != null) {
                driver.findElement(By.id("questionScore")).clear();
                new TextSend().textsendbyid(score, "questionScore");
            }

            if (hintText == null || hintText.equals("true")) {
                if (withImageInHint != null) {
                    if (withImageInHint.equals("true"))
                        driver.findElement(By.id("content-hint")).sendKeys("Hint Text");
                    else
                        new TextSend().textsendbyid("Hint Text", "content-hint");
                } else
                    new TextSend().textsendbyid("Hint Text", "content-hint");
            }

            String algorithmic = ReadTestData.readDataByTagName("", "algorithmic", Integer.toString(dataIndex));
            if (algorithmic != null) {
                if (algorithmic.equals("true")) {
                    Thread.sleep(3000);
                    WebElement element = driver.findElement(By.id("algorithmic"));
                    driver.findElement(By.xpath("//html/body")).click();
                    new ScrollElement().scrollToViewOfElement(element);
                    new Click().clickBycssselector("span[class='algorithmicExpendOptions parameters-expand']"); //click on the + icon to expan
                    /*driver.findElement(By.className("ace_text-input")).sendKeys("var " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "a = 5;\n" +
                            "var " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "b = rint" + Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "11,20);\n" +
                            "var " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "c = " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "a+" + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "b;");

                }*/
                    driver.findElement(By.className("ace_text-input")).sendKeys("var " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "a = 1;\n" +
                            "var " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "b = 2;\n" +
                            "var " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "c = " + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "a+" + Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "b;");
                }
            }

            if (variationLevel != null) {
                new Click().clickbyxpath("//div[@class='footer-bloomcode']/div/a");
                new Click().clickbyxpath("//div[@class='footer-bloomcode']//ul/li/a[@rel='" + variationLevel + "']");
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("Draft")));
            String needNotToBePubished = ReadTestData.readDataByTagName("", "needNotToBePubished", Integer.toString(dataIndex));
            System.out.println("needNotToBePubished : " + needNotToBePubished);
            if (needNotToBePubished != null) {
                System.out.println("1");
                if (!(needNotToBePubished.equals("true"))) {
                    System.out.println("2");
                    new Click().clickbylinkText("Publish");    //click on Publish option if isPublished is null or tru
                    System.out.println("3");
                }
            }
            String status = ReadTestData.readDataByTagName("", "status", Integer.toString(dataIndex));

            if (status != null) {
                if (status.equals("accuracyCheck"))
                    new Click().clickbylinkText("Accuracy Check");    //select accuracy check option
                if (status.equals("draftPendingImages"))
                    new Click().clickbylinkText("Draft - Pending Images");    //select Draft - Pending Images
                if (status.equals("qa"))
                    new Click().clickbylinkText("QA");    //select QA
                if (status.equals("needRevision"))
                    new Click().clickbylinkText("Need Revision");    //select Need Revision
                if (status.equals("approve"))
                    new Click().clickbylinkText("Approve");    //select Approve
                if (status.equals("readyToPublish"))
                    new Click().clickbylinkText("Ready to Publish");    //select Ready to Publish
                if (status.equals("publish"))
                    new Click().clickbylinkText("Publish");    //select Ready to Publish
                if (status.equalsIgnoreCase("needNotToBePublished")) ;
            } else {
                new Click().clickbylinkText("Publish");
                if (needNotToBePubished != null) {
                    System.out.println("11");
                    if (needNotToBePubished.equals("true")) {
                        System.out.println("22");
                        new Click().clickbylinkText("Publish");    //click on Publish option if isPublished is null or tru
                        new Click().clickbylinkText("Draft");    //select Draft - Pending Images
                        System.out.println("33");
                    }
                }
            }

            new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
            new Click().clickByid("saveQuestionDetails1");//click on save button

        }

        catch (Exception e)
        {
            Assert.fail("Exception while saving a question",e);
        }
    }

    public void labelAnImageText(int dataIndex)
    {
        try{
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective =ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", Integer.toString(dataIndex));

            new Click().clickByid("qtn-lbl-on-img-type");//click on label an image text type question
            new Click().clickByid("question-raw-content");//click on Question

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheSpanish();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("italian")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheItalian();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("french")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheFrench();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }
                driver.findElement(By.id("question-raw-content")).sendKeys("Label An Image(Text) question text"+ questiontext);//type the question
            }
            else {
                driver.findElement(By.id("question-raw-content")).sendKeys("Label An Image(Text) question text"+ questiontext);//type the question
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", Integer.toString(dataIndex));
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    driver.findElement(By.id("cms-embed-media-upload-tab")).click();
                    //  ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
                }
            }



            List<WebElement> answers = driver.findElements(By.className("labelAnswer"));
            answers.get(0).click();
            driver.switchTo().activeElement().sendKeys("Answer 1");

            answers.get(1).click();
            driver.switchTo().activeElement().sendKeys("Answer 2");

            answers.get(2).click();
            driver.switchTo().activeElement().sendKeys("Answer 3");
            driver.findElement(By.xpath("/html/body")).click();
            Thread.sleep(3000);
            new Click().clickByid("uploadbackgroundImage");//click on upload a back ground image
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-background");
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(By.xpath("//div[contains(@class,'draggable ui-widget-content imageUploaded')]"));
            Thread.sleep(3000);
            List<WebElement> answerstodrag = driver.findElements(By.className("labelAnswer"));
            Actions ac = new Actions(driver);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",answerstodrag.get(0));
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();
            Thread.sleep(1000);
            if(useWriteBoard != null) {
                if (useWriteBoard.equals("true")) {
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@id='writeboard']")));
                    // new Click().clickbyxpath("//label[@id='writeboard']");//click to select writeboard
                }

            }
            Thread.sleep(3000);
            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel, withImageInHint, withImageInSolution, dataIndex, score);

        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating label An Image Text question type",e);
        }

    }

    public void labelAnImageDropdown(int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            String withImage = ReadTestData.readDataByTagName("", "withImage", Integer.toString(dataIndex));

            new Click().clickByid("qtn-lbl-dropdown-type");//click on Label An Image Dropdown type question
            new Click().clickByid("question-raw-content");//click on Question
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheSpanish();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("italian")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheItalian();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }

                if(selectLanguagePalette.equals("french")){
                    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
                    clickOnTheFrench();
                    for(WebElement element:manageContent.langaugePalette_allinput){
                        element.click();
                    }
                    manageContent.langaugePalette_saveButton.click(); //click on the save button
                }
                driver.findElement(By.id("question-raw-content")).sendKeys("Label An Image(dropdown)"+ questiontext);//type the question
            }
            else {
                driver.findElement(By.id("question-raw-content")).sendKeys("Label An Image(dropdown)"+ questiontext);//type the question
            }


            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(driver,180);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
                }
            }

            List<WebElement> answerChoices = driver.findElements(By.className("label-drpdwn-answerChoice-style"));
            answerChoices.get(0).click();
            List<WebElement> subAnswerChoices = driver.findElements(By.className("label-drpdwn-subAnswerChoice-text"));
            subAnswerChoices.get(0).click();
            driver.switchTo().activeElement().sendKeys("Answer 1");
            new Click().clickbylistid("tick-image", 0);//select correct answer
            new Click().clickbylistid("tick-image", 0);//select correct answer
            subAnswerChoices.get(1).click();
            driver.switchTo().activeElement().sendKeys("Answer 2");

            answerChoices.get(1).click();
            subAnswerChoices.get(3).click();
            driver.switchTo().activeElement().sendKeys("Answer 3");
            new Click().clickbylistid("tick-image", 2);//select correct answer
            new Click().clickbylistid("tick-image", 2);//select correct answer
            subAnswerChoices.get(4).click();
            driver.switchTo().activeElement().sendKeys("Answer 4");

            answerChoices.get(2).click();
            subAnswerChoices.get(6).click();
            driver.switchTo().activeElement().sendKeys("Answer 5");
            new Click().clickbylistid("tick-image", 4);//select correct answer
            new Click().clickbylistid("tick-image", 4);//select correct answer
            subAnswerChoices.get(7).click();
            driver.switchTo().activeElement().sendKeys("Answer 6");

            Thread.sleep(2000);
            new Click().clickByid("uploadbackgroundImage");//click on upload a back ground image
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-background");
            new UIElement().waitAndFindElement(By.xpath("//div[contains(@class,'draggable ui-widget-content imageUploaded')]"));
            Thread.sleep(3000);
            List<WebElement> answerstodrag = driver.findElements(By.className("label-drpdwn-answerChoice-style"));
            Actions ac = new Actions(driver);
            answerstodrag.get(0).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();
            answerstodrag.get(1).click();
            ac.dragAndDropBy(driver.findElement(By.id("ans-drag-btn")), 520,130).build().perform();
            answerstodrag.get(2).click();
            ac.dragAndDropBy(driver.findElement(By.id("ans-drag-btn")),540,150).build().perform();

            if(useWriteBoard != null) {
                if (useWriteBoard.equals("true")) {
                    new Click().clickbyxpath("//input[@id='writeboard']");//click to select writeboard
                }

            }
            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel, withImageInHint, withImageInSolution, dataIndex, score);

        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating label An Image Dropdown question type",e);
        }
    }


    public void textEntryWithAlgorithm(int dataIndex) {
        try {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));

            new Assignment().clickOnCreateRegularPractice(dataIndex);
            new Click().clickbylistid("qtn-text-entry-img", 0);//click on Text Entry type question
            new Click().clickByid("question-raw-content");//click on Question
            Thread.sleep(5000);
            driver.findElement(By.id("question-raw-content")).sendKeys(questiontext);//type the question
            new Click().clickByid("question-raw-content");//click on Question
            new TextSend().textsendbycssSelector("$c", "input[class='numeric_text_entry_input get-user-entry is-value-changed-txt-entry']");
            new Click().clickBycssselector("span[class='btn sty-green save-language-text accept_answer text_entry_accept_answer']"); //click on Accept answer button

            new QuestionCreate().saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel, withImageInHint, withImageInSolution, dataIndex, score);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating multiple selection question type", e);
        }
    }


    public void advancedNumericWithAlgorithm(int dataIndex) {
        try {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));

            new Assignment().clickOnCreateRegularPractice(dataIndex);
            new Click().clickByid("qtn-numeric-advanced-img");//click on Advanced Numeric question
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.id("question-edit")));
            Thread.sleep(3000);
            driver.findElement(By.id("question-raw-content")).sendKeys(questiontext);//type the question
            List<WebElement> elements=driver.findElements(By.cssSelector("input[class='numeric_correct_text_entry_input num-entry-ans border-color-gray']"));
            for(WebElement ele:elements){
                if(ele.isDisplayed()){
                    ele.sendKeys("$c");
                }
            }
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer accept_answer_maple_numeric']"); //click on accept answer

            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.id("question-edit")));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='re-icon re-maplenumeric re-entry-box redactor-btn-image']")));
            Thread.sleep(3000);
            List<WebElement> elements1=driver.findElements(By.cssSelector("input[class='numeric_correct_text_entry_input num-entry-ans border-color-gray']"));
            for(WebElement ele:elements1){
                if(ele.isDisplayed()){
                    ele.sendKeys("$c");
                }
            }

            List<WebElement> acceptAnswer=driver.findElements(By.cssSelector("span[class='done-button btn sty-green tab_event_widget accept_answer accept_answer_maple_numeric']"));
            for(WebElement ele:acceptAnswer){
                if(ele.isDisplayed()){
                    ele.click();
                }
            }
            new QuestionCreate().saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel, withImageInHint, withImageInSolution, dataIndex, score);


        } catch (Exception e) {
            Assert.fail("Exception while creating advancedNumericWithAlgorithm Question",e);
        }
    }


    public void expressionEvaluatorWithAlgorithmic(int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", Integer.toString(dataIndex));
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", Integer.toString(dataIndex));
            String hintText = ReadTestData.readDataByTagName("", "hintText", Integer.toString(dataIndex));
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", Integer.toString(dataIndex));
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", Integer.toString(dataIndex));
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", Integer.toString(dataIndex));
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", Integer.toString(dataIndex));
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", Integer.toString(dataIndex));
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(dataIndex));

            new Assignment().clickOnCreateRegularPractice(dataIndex);
            new Click().clickByid("qtn-expression-evaluator-img");//click on Expression Evaluator type question
            new Click().clickByid("question-raw-content");//click on Question
            Thread.sleep(6000);
            driver.findElement(By.id("question-raw-content")).sendKeys(questiontext);//type the question
            new Click().clickByid("question-raw-content");//click on Question
            new Click().clickByid("answer_math_edit");
            driver.findElement(By.className("wrs_focusElement")).sendKeys(Keys.chord(Keys.SHIFT, Keys.NUMPAD4) + "c");
            driver.findElement(By.id("wiris-answer-container-save-choice1")).click();
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer maple_symc_not_accept_answer']"); //click on accept answer
            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel, withImageInHint, withImageInSolution, dataIndex, score);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating expressionEvaluatorWithAlgorithmic Question",e);
        }
    }

    public  void clickOnTheSpanish() throws InterruptedException {
        WebDriver driver=Driver.getWebDriver();
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        for(WebElement ele:manageContent.langaugePalette_spanishs){
            if(ele.isDisplayed()){
                WebDriverUtil.clickOnElementUsingJavascript(ele);
                break;
            }
        }
    }
    public void clickOnTheItalian()
    {
        WebDriver driver=Driver.getWebDriver();
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        for(WebElement ele:manageContent.langaugePalette_Italians){
            if(ele.isDisplayed()){
                ele.click();////click on the italian language
                break;
            }
        }
    }

    public  void clickOnTheFrench()
    {
        WebDriver driver=Driver.getWebDriver();
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        for(WebElement ele:manageContent.langaugePalette_Frenchs){
            if(ele.isDisplayed()){
                ele.click();//click on the spanish language
                break;
            }
        }
    }
}
