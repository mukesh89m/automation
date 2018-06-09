package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Config;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.TextSend;

/*
 * Created by Sumit on 15/9/2014.
 */

public class QuestionCreate extends  Driver {

    public String trueFalseQuestions(String dataIndex)
    {
        String questiontext = null;
        try
        {
            questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", dataIndex);
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", dataIndex);
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", dataIndex);
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", dataIndex);
            String hintText = ReadTestData.readDataByTagName("", "hintText", dataIndex);
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", dataIndex);
            String withImage = ReadTestData.readDataByTagName("", "withImage", dataIndex);
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", dataIndex);
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", dataIndex);
            String multipleVideo = ReadTestData.readDataByTagName("", "multipleVideo", dataIndex);
            String wistiaCode_multipleVideo = ReadTestData.readDataByTagName("", "wistiaCode_multipleVideo", dataIndex);
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", dataIndex);
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", dataIndex);
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
            String score = ReadTestData.readDataByTagName("", "score", dataIndex);
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", dataIndex);
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", dataIndex);

            String packageName = new Exception().getStackTrace()[2].getClassName();
            String methodName = new Exception().getStackTrace()[2].getMethodName();
            packageName = packageName.substring(64);
            packageName = packageName.replaceAll("\\.", "_");
            if(questiontext==null){
                questiontext  = dataIndex+ "_questionText_"+packageName+"_"+methodName;
                System.out.println("questiontext : " + questiontext);
            }






            new Click().clickbylistid("qtn-type-true-false-img", 0);//click on True/False type question
            //new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-checked']");//Uncheck shuffle answer choice
            if(shuffleAnswer != null) {
                if (shuffleAnswer.equals("true"))
                    new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-unchecked']");//check shuffle answer choice
            }
            new WebDriverWait(Driver.driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.className("true-false-answer-select")));
            new Click().clickByid("question-raw-content");//click on Question
            //Driver.driver.findElement(By.id("question-raw-content")).sendKeys("True False " + questiontext+LoginUsingLTI.appendChar);//type the question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("True False " + questiontext+"_"+appendChar);//type the question
            questiontext = "True False " + questiontext+"_"+appendChar;
            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(Driver.driver,180);
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
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.FileUpload().videoUpload(dataIndex);
                }
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", dataIndex);
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    Driver.driver.findElement(By.id("cms-embed-media-upload-tab")).click();
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    Driver.driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
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
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
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
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    Thread.sleep(30000);

                }
            }
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option A
            Thread.sleep(2000);
            if(learningobjective ==null){
                learningobjective = "true";
            }

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper QuestionCreate in method trueFalseQuestions.", e);
        }
        return  questiontext;
    }
    public void multipleChoice(String dataIndex)
    {
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", dataIndex);
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", dataIndex);
            String hintText = ReadTestData.readDataByTagName("", "hintText", dataIndex);
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", dataIndex);
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", dataIndex);
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", dataIndex);
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", dataIndex);
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", dataIndex);
            String score = ReadTestData.readDataByTagName("", "score", dataIndex);
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", dataIndex);
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", dataIndex);
            String multipleVideo = ReadTestData.readDataByTagName("", "multipleVideo", dataIndex);
            String wistiaCode_multipleVideo = ReadTestData.readDataByTagName("", "wistiaCode_multipleVideo", dataIndex);
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", dataIndex);
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", dataIndex);
            String withImage = ReadTestData.readDataByTagName("", "withImage", dataIndex);
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);

            new Click().clickbylistid("qtn-multiple-choice-img", 0);//click on Multiple Choice type question
            new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-checked']");//Uncheck shuffle answer choice
            if(shuffleAnswer != null) {
                if (shuffleAnswer.equals("true"))
                    new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-unchecked']");//check shuffle answer choice
            }
            new Click().clickByid("question-mc-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-mc-raw-content")).sendKeys("Multiple Choice "+questiontext+"_"+appendChar);//type the question
            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(Driver.driver,180);
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
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.FileUpload().videoUpload(dataIndex);
                }
            }


            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", dataIndex);
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("cms-embed-media-upload-tab")));
                    Thread.sleep(2000);
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    Driver.driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
                }
            }

            new Click().clickBycssselector("div[class='single-select-choice-icon single-select-choice-icon-deselect']"); //select correct answer as A
            List<WebElement> answerOptions = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
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

    public void textEntry(String dataIndex)
    {
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", dataIndex);
            String hintText = ReadTestData.readDataByTagName("", "hintText", dataIndex);
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", dataIndex);
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", dataIndex);
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", dataIndex);
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", dataIndex);
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", dataIndex);
            String score = ReadTestData.readDataByTagName("", "score", dataIndex);
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", dataIndex);
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", dataIndex);
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", dataIndex);
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", dataIndex);
            String enableSpanish = ReadTestData.readDataByTagName("", "enableSpanish", dataIndex);
            String withImage = ReadTestData.readDataByTagName("", "withImage", dataIndex);
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);

            new Click().clickbylistid("qtn-text-entry-img", 0);//click on Text Entry type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Text Entry "+questiontext+"_"+appendChar);//type the question

            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(Driver.driver,180);
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
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.FileUpload().videoUpload(dataIndex);
                }
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", dataIndex);
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    Driver.driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
                }
            }
            if(enableSpanish!=null)
            {
                new Click().clickBycssselector("label[class='enableSpanishPaletteCheck enable-spanish-palette-checkbox-unchecked textEntCheckBox']"); //enable spanish
                new Click().clickByclassname("spanish-popup"); //click on the spanish icon
                for(int i=0;i<5;i++) {
                    new Click().clickbyxpath("//input[@value='Á']"); //click on A
                }
                new Click().clickByid("save-spanish-text"); //click on the save button
                new Click().clickBycssselector("span[class='btn sty-green save-spanish-text accept_answer text_entry_accept_answer']"); //click on Accept answer button


            }
            else
            {
                new TextSend().textsendbycssSelector("Correct Answer", "input[class='numeric_text_entry_input get-user-entry is-value-changed-txt-entry']");
                new Click().clickBycssselector("span[class='btn sty-green save-spanish-text accept_answer text_entry_accept_answer']"); //click on Accept answer button
            }


            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e) {
            Assert.fail("Exception while creating Text Entry Question type",e);
        }

    }

    public void textDropDown(String dataIndex)
    {
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", dataIndex);
            String hintText = ReadTestData.readDataByTagName("", "hintText", dataIndex);
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", dataIndex);
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", dataIndex);
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", dataIndex);
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", dataIndex);
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", dataIndex);
            String score = ReadTestData.readDataByTagName("", "score", dataIndex);
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", dataIndex);
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", dataIndex);
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", dataIndex);
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", dataIndex);
            String withImage = ReadTestData.readDataByTagName("", "withImage", dataIndex);
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
            new Click().clickbylistid("qtn-text-entry-drop-down-img", 0);//click on Text Entry Drop Down type question

            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Text Drop Down "+questiontext+"_"+appendChar);//type the question

            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(Driver.driver,180);
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
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.FileUpload().videoUpload(dataIndex);
                }
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", dataIndex);
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    Driver.driver.findElement(By.id("cms-embed-media-upload-tab")).click();
                  //  ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    Driver.driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
                }
            }
            new Click().clickByclassname("text-drop-val");
            new TextSend().textsendbyid("Answer1","ans1");

            new Click().clickByclassname("select-icon-text-drop-down"); //select answer 1 as correct answer

            Driver.driver.findElements(By.className("text-drop-val")).get(1).click();
            Driver.driver.findElements(By.id("ans1")).get(1).sendKeys("Answer2");

            Driver.driver.findElements(By.className("text-drop-val")).get(2).click();
            Driver.driver.findElements(By.id("ans1")).get(2).sendKeys("Answer3");

            new Click().clickBycssselector("span[class='done-button btn sty-green text-drop-accept accept_answer']"); //click on accept answer button

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e) {
            Assert.fail("Exception while creating text entry drop down question",e);
        }
    }

    public void numericEntryWithUnits(String dataIndex) {
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", dataIndex);
            String hintText = ReadTestData.readDataByTagName("", "hintText", dataIndex);
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", dataIndex);
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", dataIndex);
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", dataIndex);
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", dataIndex);
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", dataIndex);
            String score = ReadTestData.readDataByTagName("", "score", dataIndex);
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", dataIndex);
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", dataIndex);
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", dataIndex);
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", dataIndex);
            String withImage = ReadTestData.readDataByTagName("", "withImage", dataIndex);
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);

            new Click().clickbylistid("qtn-text-entry-numeric-units-img", 0);//click on Numeric Entry with Units type question
            Thread.sleep(5000);
            new Click().clickByid("question-raw-content");//click on Question
            Thread.sleep(2000);
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Numeric Entry With Units "+questiontext+"_"+appendChar);//type the question

            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(Driver.driver,180);
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
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.FileUpload().videoUpload(dataIndex);
                }
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", dataIndex);
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    Driver.driver.findElement(By.id("widget-createimage_start_queue")).click();
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

    public void advancedNumeric(String dataIndex) {
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", dataIndex);
            String hintText = ReadTestData.readDataByTagName("", "hintText", dataIndex);
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", dataIndex);
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", dataIndex);
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", dataIndex);
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", dataIndex);
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", dataIndex);
            String score = ReadTestData.readDataByTagName("", "score", dataIndex);
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", dataIndex);
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", dataIndex);
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", dataIndex);
            String withImage = ReadTestData.readDataByTagName("", "withImage", dataIndex);
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", dataIndex);

            new Click().clickByid("qtn-numeric-advanced-img");//click on Advanced Numeric question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Advanced Numeric "+questiontext+"_"+appendChar);//type the question
            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(Driver.driver,180);
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
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.FileUpload().videoUpload(dataIndex);
                }
            }


            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", dataIndex);
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    Driver.driver.findElement(By.id("widget-createimage_start_queue")).click();
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

    public void expressionEvaluator(String dataIndex)
    {
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", dataIndex);
            String hintText = ReadTestData.readDataByTagName("", "hintText", dataIndex);
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", dataIndex);
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", dataIndex);
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", dataIndex);
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", dataIndex);
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", dataIndex);
            String score = ReadTestData.readDataByTagName("", "score", dataIndex);
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", dataIndex);
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", dataIndex);
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", dataIndex);
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", dataIndex);
            String withImage = ReadTestData.readDataByTagName("", "withImage", dataIndex);
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);

            new Click().clickByid("qtn-expression-evaluator-img");//click on Expression Evaluator type question
            new Click().clickByid("question-raw-content");//click on Question
            Thread.sleep(6000);
           /* Driver.driver.findElement(By.id("question-raw-content")).clear();
            Thread.sleep(3000);*/
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Expression Evaluator "+questiontext+"_"+appendChar);//type the question
            new Click().clickByid("question-raw-content");//click on Question

            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(Driver.driver,180);
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
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.FileUpload().videoUpload(dataIndex);
                }
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", dataIndex);
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    Driver.driver.findElement(By.id("widget-createimage_start_queue")).click();
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

    public void matchTheFollowing(String dataIndex)
    {
        try {

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", dataIndex);
            String hintText = ReadTestData.readDataByTagName("", "hintText", dataIndex);
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", dataIndex);
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", dataIndex);
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", dataIndex);
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", dataIndex);
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", dataIndex);
            String score = ReadTestData.readDataByTagName("", "score", dataIndex);
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", dataIndex);
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", dataIndex);
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", dataIndex);
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", dataIndex);
            String withImage = ReadTestData.readDataByTagName("", "withImage", dataIndex);
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
            new Click().clickByid("qtn-mtf-type");//click on Match the Following type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Match the Following " + questiontext+"_"+appendChar);//type the question

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
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-answer-choice-image");
            new UIElement().waitAndFindElement(By.cssSelector("img[class='answer-choice-img answer-choice-image-size']"));
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
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-answer-choice-image");
            new UIElement().waitAndFindElement(By.cssSelector("img[class='answer-choice-img answer-choice-image-size']"));
            rhsboxes.get(3).click();
            Driver.driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","6");

            rhsboxes.get(4).click();
            Driver.driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","7");

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel, withImageInHint, withImageInSolution, dataIndex, score);

        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating Match the Following Question",e);
        }
    }

    public void dragAndDrop(String dataIndex)
    {
        try {

            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", dataIndex);
            String hintText = ReadTestData.readDataByTagName("", "hintText", dataIndex);
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", dataIndex);
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", dataIndex);
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", dataIndex);
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", dataIndex);
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", dataIndex);
            String score = ReadTestData.readDataByTagName("", "score", dataIndex);
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", dataIndex);
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", dataIndex);
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", dataIndex);
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", dataIndex);
            String withImage = ReadTestData.readDataByTagName("", "withImage", dataIndex);
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
            new Click().clickByid("qtn-dnd-type");//click on Drag and Drop type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Drag and Drop " + questiontext+"_"+appendChar);//type the question

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
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            new Click().clickByid("widget-upload-answer-choice-image");
            new UIElement().waitAndFindElement(By.cssSelector("img[class='answer-choice-image-size']"));
            answers.get(3).click();
            Driver.driver.findElement(By.id("answer_math_edit")).click();
            enterValueInMathMLEditor("Square root","10");

            List<WebElement> answerstodrag = Driver.driver.findElements(By.className("answer"));
            Actions ac = new Actions(Driver.driver);
            answerstodrag.get(0).click();
            ac.dragAndDrop(Driver.driver.findElement(By.id("ans-drag-btn")),Driver.driver.findElement(By.id("canvas"))).build().perform();

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel, withImageInHint, withImageInSolution, dataIndex, score);

        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating Drag and Drop Question",e);
        }
    }

    public void clozeFormula(String dataIndex)
    {
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);

            new Click().clickByid("qtn-cf-type");//click on Cloze Formula type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Cloze Formula "+questiontext+"_"+appendChar);//type the question

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

    public void multipleSelection(String dataIndex)
    {
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", dataIndex);
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", dataIndex);
            String hintText = ReadTestData.readDataByTagName("", "hintText", dataIndex);
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", dataIndex);
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", dataIndex);
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", dataIndex);
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", dataIndex);
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", dataIndex);
            String score = ReadTestData.readDataByTagName("", "score", dataIndex);
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", dataIndex);
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", dataIndex);
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", dataIndex);
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", dataIndex);
            String withImage = ReadTestData.readDataByTagName("", "withImage", dataIndex);
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);

            new Click().clickbylistid("qtn-multiple-selection-img", 0);//click on Multiple Selection type question
            new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-checked']");//Uncheck shuffle answer choice
            if(shuffleAnswer != null) {
                if (shuffleAnswer.equals("true"))
                    new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-unchecked']");//check shuffle answer choice
            }
            new Click().clickByid("question-ms-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-ms-raw-content")).sendKeys("Multi Selection "+questiontext+"_"+appendChar);//type the question

            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(Driver.driver,180);
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
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.FileUpload().videoUpload(dataIndex);
                }
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", dataIndex);
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    Driver.driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
                }
            }

            List<WebElement> answerOptions = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("Option 1");
            answerOptions.get(1).sendKeys("Option 2");
            answerOptions.get(2).sendKeys("Option 3");
            answerOptions.get(3).sendKeys("Option 4");;
            new Click().clickByid("swuploadclose");//close pop-up
            List<WebElement> multipleSelections = Driver.driver.findElements(By.cssSelector("div[class='multiple-select-choice-icon multiple-select-choice-icon-deselect']"));
            multipleSelections.get(0).click(); multipleSelections.get(1).click(); //selecting option A and B as correct answers
            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating multiple selection question type", e);
        }
    }

    public void graphPlotter(String dataIndex)
    {
        try {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", dataIndex);
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", dataIndex);
            String hintText = ReadTestData.readDataByTagName("", "hintText", dataIndex);
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", dataIndex);
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", dataIndex);
            String score = ReadTestData.readDataByTagName("", "score", dataIndex);

            new Click().clickByid("qtn-graph-type");//click on Graph Plotter type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Graph Plotter "+questiontext+"_"+appendChar);//type the question
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

    public void clozeMatrix(String dataIndex)
    {
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", dataIndex);
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", dataIndex);
            String hintText = ReadTestData.readDataByTagName("", "hintText", dataIndex);
            new Click().clickByid("qtn-cloze-matrix-type");//click on Cloze Matrix type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Cloze Matrix " + questiontext+"_"+appendChar);//type the question
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


    public void resequence(String dataIndex)
    {
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", dataIndex);
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", dataIndex);
            String hintText = ReadTestData.readDataByTagName("", "hintText", dataIndex);
            new Click().clickByid("qtn-resequence-type");//click on Cloze Matrix type question
            if(shuffleAnswer != null) {
                if (shuffleAnswer.equals("true"))
                    new Click().clickBycssselector("label[class='shufflechkbox as-shuffle-ans-choices-checkbox-unchecked']");//check shuffle answer choice
            }
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Resequence "+questiontext+"_"+appendChar);//type the question
        }
        catch (Exception e) {
            Assert.fail("Exception while creating Resequence question type",e);
        }
    }

    public void essay(String dataIndex)
    {
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            String shuffleAnswer = ReadTestData.readDataByTagName("", "shuffleAnswer", dataIndex);
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", dataIndex);
            String hintText = ReadTestData.readDataByTagName("", "hintText", dataIndex);
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", dataIndex);
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", dataIndex);
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", dataIndex);
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", dataIndex);
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", dataIndex);
            String score = ReadTestData.readDataByTagName("", "score", dataIndex);
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", dataIndex);
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", dataIndex);
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", dataIndex);
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", dataIndex);
            String withImage = ReadTestData.readDataByTagName("", "withImage", dataIndex);
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);

            new Click().clickbylistid("qtn-essay-type", 0);//click on Essay type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Essay "+questiontext+"_"+appendChar);//type the question
            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(Driver.driver,180);
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
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.FileUpload().videoUpload(dataIndex);
                }
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", dataIndex);
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    Driver.driver.findElement(By.id("widget-createimage_start_queue")).click();
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

    public void writeBoard(String dataIndex)
    {
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", dataIndex);
            String hintText = ReadTestData.readDataByTagName("", "hintText", dataIndex);
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", dataIndex);
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", dataIndex);
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", dataIndex);
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", dataIndex);
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", dataIndex);
            String score = ReadTestData.readDataByTagName("", "score", dataIndex);
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", dataIndex);
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", dataIndex);
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", dataIndex);
            String withImage = ReadTestData.readDataByTagName("", "withImage", dataIndex);
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", dataIndex);

            new Click().clickByid("qtn-writeboard-type-new");//click on writeboard type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("Write Board "+questiontext+"_"+appendChar);//type the question
            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='pickfiles']")));

                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(Driver.driver,180);
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
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.FileUpload().videoUpload(dataIndex);
                }
            }


            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", dataIndex);
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    Driver.driver.findElement(By.id("widget-createimage_start_queue")).click();
                    Thread.sleep(20000);
                }
            }

            saveQuestion(learningobjective, solutionText, hintText, useWriteBoard, difficultylevel, variationLevel,withImageInHint,withImageInSolution, dataIndex,score);
        }
        catch (Exception e) {
            Assert.fail("Exception while creating essay question type",e);
        }
    }
    public void multiPartQuestion(String dataIndex)
    {
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String questiontextstem2 = ReadTestData.readDataByTagName("", "questiontextstem2", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            String solutionTextForMPQ = ReadTestData.readDataByTagName("", "solutionTextForMPQ", dataIndex);
            String hintTextForMPQ = ReadTestData.readDataByTagName("", "hintTextForMPQ", dataIndex);
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", dataIndex);
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", dataIndex);
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", dataIndex);
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", dataIndex);
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", dataIndex);
            String score = ReadTestData.readDataByTagName("", "score", dataIndex);
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", dataIndex);
            String hintText = ReadTestData.readDataByTagName("", "hintText", dataIndex);
            String needNotToBePubished=ReadTestData.readDataByTagName("","needNotToBePubished",dataIndex);
            String status=ReadTestData.readDataByTagName("","status",dataIndex);
            String overrideQuestionParts=ReadTestData.readDataByTagName("","overrideQuestionParts",dataIndex);

            new Click().clickByid("qtn-multi-part");//click on Multi-part question
            if(!(overrideQuestionParts == null)){
                if(overrideQuestionParts.equalsIgnoreCase("true")){
                    new Click().clickByid("one");
                }
            }
            new TextSend().textsendbyid(questiontext+"_"+appendChar, "question-mp-raw-content-0");
            new Click().clickByid("saveQuestionDetails1");//click on Save
            new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
            new Click().clickByid("qtn-type-true-false-img");//click on true false type question
            new TextSend().textsendbyid("1", "mpq-question-part-input-label");//enter label in true false MPQ
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("True False For MPQ");//type the question
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option A
            new ComboBox().selectValue(7, "Hard");
            /*new Click().clickBycssselector("a[selectedid = 'difficulty level']");
            new Click().clickbylinkText("Hard");*/
            Driver.driver.findElement(By.id("content-solution")).sendKeys(solutionTextForMPQ);
            Driver.driver.findElement(By.id("content-hint")).sendKeys(hintTextForMPQ);
            if(learningobjective != null)
                associateTlo(dataIndex, learningobjective);//add TLO
            new Click().clickByid("saveQuestionDetails1");//click on Save
            new Click().clickBycssselector("img[alt='Back']");//click on Back
            new Click().clickByclassname("cms-multipart-add-stem");//Click on "Add question stem" button
            new Click().clickByid("question-mp-raw-content-2");
            new TextSend().textsendbyid(questiontextstem2+"_"+appendChar, "question-mp-raw-content-2");//question for stem 2
            new Click().clickByid("saveQuestionDetails1");//click on Save
            new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
            new Click().clickbylistid("qtn-multiple-choice-img", 0);//click on Multiple Choice type question
            new Click().clickByid("question-mc-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-mc-raw-content")).sendKeys("Multiple Choice Question");//type the question
            new Click().clickBycssselector("div[class='single-select-choice-icon single-select-choice-icon-deselect']"); //select correct answer as A
            List<WebElement> answerOptions = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
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
                Driver.driver.findElement(By.id("questionScore")).clear();
                new TextSend().textsendbyid(score,"questionScore");
            }
            if(variationLevel != null)
            {
                new Click().clickBycssselector("#content-variationlevel > select");
                new Click().clickBycssselector("option[value='"+variationLevel+"']");
            }

            new Click().clickbylinkText("Draft"); //click on Draft option

            if(needNotToBePubished ==null)
                new Click().clickbylinkText("Publish");

            if(needNotToBePubished !=null){
                if(!(needNotToBePubished.equals("true"))) {
                    System.out.println("publishing");
                    new Click().clickbylinkText("Publish");    //click on Publish option if isPublished is null or tru
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

    public void audio(String dataIndex)
    {
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", dataIndex);
            String learningobjective = ReadTestData.readDataByTagName("", "learningobjective", dataIndex);
            String solutionText = ReadTestData.readDataByTagName("", "solutionText", dataIndex);
            String hintText = ReadTestData.readDataByTagName("", "hintText", dataIndex);
            String useWriteBoard = ReadTestData.readDataByTagName("", "useWriteBoard", dataIndex);
            String variationLevel = ReadTestData.readDataByTagName("", "variationLevel", dataIndex);
            String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", dataIndex);
            String withImageInHint = ReadTestData.readDataByTagName("", "withImageInHint", dataIndex);
            String withImageInSolution = ReadTestData.readDataByTagName("", "withImageInSolution", dataIndex);
            String score = ReadTestData.readDataByTagName("", "score", dataIndex);
            String withVedio = ReadTestData.readDataByTagName("", "withVedio", dataIndex);
            String wistiaCode = ReadTestData.readDataByTagName("", "wistiaCode", dataIndex);
            String withUploadVideo = ReadTestData.readDataByTagName("", "withUploadVideo", dataIndex);
            String withUploadAudio = ReadTestData.readDataByTagName("", "withUploadAudio", dataIndex);
            String withImage = ReadTestData.readDataByTagName("", "withImage", dataIndex);
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);

            new Click().clickByid("qtn-audio-type");//click on writeboard type question
            new Click().clickByid("question-raw-content");//click on Question
            Driver.driver.findElement(By.id("question-raw-content")).sendKeys("audio "+questiontext+"_"+appendChar);//type the question

            if(withImage != null)
            {
                if(withImage.equals("true"))
                {
                    new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.xpath("//*[@id='pickfiles']")));
                    Thread.sleep(2000);
                    System.out.println("Config.fileUploadPath+filename : " + Config.fileUploadPath+filename);
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
                    Thread.sleep(15000);
                    new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
                    WebDriverWait wait = new WebDriverWait(Driver.driver,180);
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
                    new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.FileUpload().videoUpload(dataIndex);
                }
            }

            if(withUploadAudio!=null)
            {
                if(withUploadAudio.equals("true")){
                    String filename1 =  ReadTestData.readDataByTagName("", "filename1", dataIndex);
                    new Click().clickBycssselector("a[class='re-icon re-embedaudio redactor-btn-image']");//click on audio icon
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("cms-embed-media-upload-tab")));
                    new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
                    Driver.driver.findElement(By.id("widget-createimage_start_queue")).click();
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
    public void associateTlo(String dataIndex, String learningobjective)
    {
        try
        {
            Driver.driver.findElement(By.id("learing-objectives-span")).click(); //Clicking on Learning objective drop-down from footer
            new WebDriverWait(Driver.driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id("link-add-learning-objectives")));
            Driver.driver.findElement(By.id("link-add-learning-objectives")).click(); //clicking on 'Add Learning Objective' link
            Thread.sleep(2000);
            if(learningobjective.equals("true"))
                Driver.driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[3]/label")).click();
            else {
                WebElement element = Driver.driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[" + learningobjective + "]/label"));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
                Driver.driver.findElement(By.xpath("//div[@class='taxonomyStructureContent']/div[" + learningobjective + "]/label")).click();
                Thread.sleep(3000);
            }
            new Click().clickBycssselector("span.add-collection-to-subtopic");//click on Associate
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper QuestionCreate in method associateTlo.", e);
        }
    }
    public void saveQuestion(String learningobjective,String solutionText, String hintText, String useWriteBoard, String difficultylevel, String variationLevel,String withImageInHint,String withImageInSolution, String dataIndex, String score)
    {
        try {

            if(useWriteBoard != null)
            {
                new Click().clickBycssselector("#content-writeboard > #writeboard");//check the use writeboard check box
            }
            if(difficultylevel != null)
            {
                new Click().clickbylinkText("Difficulty Level");
                new UIElement().waitAndFindElement(By.linkText(difficultylevel));
                new Click().clickbylinkText(difficultylevel);
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


            if(score!=null){
                Driver.driver.findElement(By.id("questionScore")).clear();
                new TextSend().textsendbyid(score,"questionScore");
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

            String needNotToBePubished=ReadTestData.readDataByTagName("","needNotToBePubished",dataIndex);
            if(needNotToBePubished !=null){
                if(!(needNotToBePubished.equals("true"))) {
                    new Click().clickbylinkText("Publish");    //click on Publish option if isPublished is null or tru
                }
            }
            String status=ReadTestData.readDataByTagName("","status",dataIndex);

            if(status !=null){
                if(status.equals("accuracyCheck"))
                    new Click().clickbylinkText("Accuracy Check");    //select accuracy check option
                if(status.equals("draftPendingImages"))
                    new Click().clickbylinkText("Draft - Pending Images");    //select Draft - Pending Images
                if(status.equals("qa"))
                    new Click().clickbylinkText("QA");    //select QA
                if(status.equals("needRevision"))
                    new Click().clickbylinkText("Need Revision");    //select Need Revision
                if(status.equals("approve"))
                    new Click().clickbylinkText("Approve");    //select Approve
                if(status.equals("readyToPublish"))
                    new Click().clickbylinkText("Ready to Publish");    //select Ready to Publish
                if(status.equals("publish"))
                    new Click().clickbylinkText("Publish");    //select Ready to Publish
                if(status.equalsIgnoreCase("needNotToBePublished"));
            }else {
                new Click().clickbylinkText("Publish");
            }

            new WebDriverWait(Driver.driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.id("saveQuestionDetails1")));
            new Click().clickByid("saveQuestionDetails1");//click on save button
        }


        catch (Exception e)
        {
            Assert.fail("Exception while saving a question",e);
        }
    }
}
