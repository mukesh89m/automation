package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R1714;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
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
 * Created by sumit on 4/12/14.
 */
public class MPQuestionForStaticAssessmentsWithQuestionPartsAsIndependent extends Driver {

    @Test(priority = 1, enabled = true)
    public void mPQuestionForStaticAssessmentsWithQuestionPartsAsIndependent()
    {
        try
        {
            //Driver.startDriver();
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse(); //select a course
            new SelectCourse().selectChapterByIndex(0);//select chapter
            driver.findElement(By.cssSelector("div.create-practice")).click();//click on Create Practice
            Thread.sleep(3000);
            new Click().clickByclassname("create-regular-assessment-popup-item");
            Thread.sleep(3000);
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
            new Click().clickbylistid("qtn-essay-type", 0);//click on Essay type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Essay type question part");//type the question
            new Click().clickByid("essay-question-height");//click on Min line height textbox
            new TextSend().textsendbyid("3", "essay-question-height");
            new TextSend().textsendbyid("1", "mpq-question-part-input-label");//enter label in essay MPQ
            new Click().clickByid("saveQuestionDetails1");//click on Save
            new Click().clickBycssselector("img[alt='Back']");//click on Back
            new Click().clickbyxpath("//label[@for='one']");//click on dependent
            String message = new TextFetch().textfetchbycssselector("div[class='cms-notification-message-body-title two-line-title']");
            Assert.assertEquals(message, "Your multipart question has \"manually graded\" question part, so it cannot be converted to a dependent multi-part question.", "-----");
            new Click().clickByid("saveQuestionDetails1");//click on Save
            new Assignment().addQuestionLink();
            new Click().clickByid("qtn-multi-part");//click on Multi-part question
            new Click().clickbyxpath("//label[@for='one']");//click on dependent
            new TextSend().textsendbyid("multipart question 2", "question-mp-raw-content-0");
            new Click().clickByid("saveQuestionDetails1");//click on Save
            new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
            boolean manualGradableFound= false;
            List<WebElement> allQuestionTypes = driver.findElements(By.className("multipart-qtn-content-icon"));
            for(WebElement types: allQuestionTypes )
            {
                if(types.getAttribute("title").equals("Essay Type Question") || types.getAttribute("title").equals("Write Board"))
                {
                    manualGradableFound = true;
                }
            }
            Assert.assertEquals(manualGradableFound, false, "Manually graded question types(Essay and Writeboard question types) is shown as an option while adding a new question part as dependent MPQ.");
            new Click().clickbyxpath("//label[@for='two']");//click on independent
            new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
            boolean essayFound = false;
            List<WebElement> allQuestionTypes1 = driver.findElements(By.className("multipart-qtn-content-icon"));
            for(WebElement types: allQuestionTypes1 )
            {
                if(types.getAttribute("title").equals("Essay Type Question") || types.getAttribute("title").equals("Write Board"))
                {
                    essayFound = true;
                }
            }
            Assert.assertEquals(essayFound, true, "Manually graded question types(Essay and Writeboard question types) is not shown when we switch from Dependent to Independent.");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase mPQuestionForStaticAssessmentsWithQuestionPartsAsIndependent in  class MPQuestionForStaticAssessmentsWithQuestionPartsAsIndependent.", e);

        }
    }
    @AfterMethod
    public void TearDown()throws Exception
    {
        driver.quit();
    }
}
