package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R1714;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by Sumit on 4/12/14.
 */
public class AuthorShouldBeAbleToPreviewMPQ extends Driver {

    @Test(priority = 1, enabled = true)
    public void authorShouldBeAbleToPreviewMPQ()
    {
        try
        {
            //Driver.startDriver();
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse(); //select a course
            new SelectCourse().selectChapterByIndex(0);//select chapter
            driver.findElement(By.cssSelector("div.create-practice")).click();//click on Create Practice
            new Click().clickByclassname("create-regular-assessment-popup-item");
            new Click().clickByid("qtn-multi-part");//click on Multi-part question
            new ComboBox().selectValue(3, "Static Practice");
            driver.findElement(By.id("assessmentName")).click();
            driver.findElement(By.id("assessmentName")).clear();
            driver.findElement(By.id("assessmentName")).sendKeys("Multipart Question");
            driver.findElement(By.id("questionSetName")).clear();
            driver.findElement(By.id("questionSetName")).sendKeys("Multipart Question Set");
            new TextSend().textsendbyid("multipart question", "question-mp-raw-content-0");
            new Click().clickByid("saveQuestionDetails1");//click on Save
            new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
            new Click().clickByid("qtn-type-true-false-img");//click on true false type question
            new TextSend().textsendbyid("1", "mpq-question-part-input-label");//enter label in true false MPQ
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("True False For MPQ");//type the question
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option A
            new Click().clickBycssselector("#content-writeboard > #writeboard");//check the use writeboard check box
            new ComboBox().selectValue(7, "Hard");
            driver.findElement(By.id("content-solution")).sendKeys("Solution Text");
            driver.findElement(By.id("content-hint")).sendKeys("Hint Text");
            new Click().clickByid("saveQuestionDetails1");//click on Save
            new Click().clickBycssselector("img[alt='Back']");//click on Back
            new Click().clickByclassname("cms-multipart-add-stem");//Click on "Add question stem" button
            String str2 = new RandomString().randomstring(5);
            new TextSend().textsendbyid(str2, "question-mp-raw-content-2");
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
            new Click().clickByid("saveQuestionDetails1");//click on save button
            new Click().clickBycssselector("img[alt='Back']");//click on Back
            String winHandleBefore = driver.getWindowHandle();//Store the current window handle
            new Click().clickByid("preview-the-image-quiz");//click on Preview page
            Thread.sleep(15000);
            //Switch to new window opened
            for(String winHandle : driver.getWindowHandles()){
                driver.switchTo().window(winHandle);
            }
            String points = new TextFetch().textfetchbyclass("multi-part-question-points");
            Assert.assertEquals(points, "Points Available: 1", "Points Available:1 is absent in MPQ preview.");

            String totalPoints = new TextFetch().textfetchbyclass("question-preview-sidebar-total-points");
            Assert.assertEquals(totalPoints, "Total Points: 2", "Total Points:1 is absent in performance tab of MPQ preview.");

            String perfTenQs = new TextFetch().textfetchbyclass("cms-question-preview-sidebar-title-sectn");
            Assert.assertEquals(perfTenQs, "Performance in Last 10 Qs", "\"Performance in Last 10 Qs\" label is absent in performance tab of MPQ preview.");

            String abtThisQs = new TextFetch().textfetchbyclass("cms-about-this-question-title");
            Assert.assertEquals(abtThisQs, "About this Question", "\"About this Question\" label is absent in performance tab of MPQ preview.");

            String hint = new TextFetch().textfetchbyclass("part-question-hint");
            Assert.assertEquals(hint, "Hint", "\"Hint\" option is absent in MPQ preview.");

            String solution = new TextFetch().textfetchbyclass("part-question-solution");
            Assert.assertEquals(solution, "Solution", "\"Solution\" option is absent in MPQ preview.");

            String checkAnswer = new TextFetch().textfetchbyclass("part-question-check-answer");
            Assert.assertEquals(checkAnswer, "Check Answer", "\"Check Answer\" option is absent in MPQ preview.");

            String writeBoard = new TextFetch().textfetchbyid("show-your-work-label");
            Assert.assertEquals(writeBoard, "+ Workboard", "\"Show your work\" option is absent in MPQ preview.");

            new Click().clickByclassname("true-false-student-answer-label");//select answer A
            new Click().clickByclassname("part-question-check-answer");//click on Check answer
            String result = driver.findElement(By.xpath("//*[starts-with(@class, 'part-evaluated-result part-question-check-answer-evaluated-result')]")).getText();
            Assert.assertEquals(result, "You got it right", "\"You got it right\" message is not coming when we click on Check Answer in Preview page of MPQ.");

            new Click().clickbylist("question-toggle-arrow-icon", 1);//click on expand second question

            String points1 = new TextFetch().textfetchbylistclass("multi-part-question-points", 1);
            Assert.assertEquals(points1, "Points Available: 1", "Points Available:1 is absent in MPQ preview for 2nd question part.");

            driver.close();//Close the new window, if that window no more required

            driver.switchTo().window(winHandleBefore);//Switch back to original browser (first window)
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase authorShouldBeAbleToPreviewMPQ in  class AuthorShouldBeAbleToPreviewMPQ.", e);
        }
    }
    /*@AfterMethod
    public void TearDown()throws Exception
    {
        driver.quit();
    }*/
}
