package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class ConfidenceLevelVerificationWhenClickedOnPracticeTestBeforeDiagnosticTest extends Driver
{
    private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.ConfidenceLevelVerificationWhenClickedOnPracticeTestBeforeDiagnosticTest");


    @Test(priority = 1, enabled=true)
    public void firstCardShouldContainDiagnosticAndPracticeTest()
    {
        try
        {

            new LoginUsingLTI().ltiLogin("696");
            new Navigator().NavigateTo("eTextbook");

            WebElement WE1 = driver.findElement(By.cssSelector("ul[class='assessment-card']"));
            String card = WE1.getText();

            if(card.contains("Diagnostic") && card.contains("Personalized Practice"))
            {
                logger.log(Level.INFO,"First Card contains Diagnostic and Practice Test");
            }
            else
            {
                logger.log(Level.INFO,"First Card does not contain Diagnostic and Practice Test");
                Assert.fail("First Card does not contain Diagnostic and Practice Test");
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Diagnostic - Ch 1:")));
            Thread.sleep(2000);
            String confidenceLevel = driver.findElement(By.cssSelector("div[class='ls-self-rating-message']")).getText();

            if(!confidenceLevel.equals("Enter Confidence Level"))
                Assert.fail("Options for entering confidence level is NOT present after clicking on Diagnostic Test");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Personalized Practice")));
            Thread.sleep(3000);
            String notificationRobo = driver.findElement(By.cssSelector("div[class='al-notification-message-body']")).getText();
            if(!notificationRobo.equals("Your diagnostic test is not yet complete. Please finish it before attempting the practice test."))
                Assert.fail("No Robo notification displayed, When the diagnostic test is inprogress and student clicked on Practice Test");
        }
        catch(Exception e)
        {
            Assert.fail("Exception  in testcase firstCardShoulContainDiagnosticAndPracticeTest in class ConfidenceLevelVerificationWhenClickedOnPracticeTestBeforeDiagnosticTest",e);
        }
    }

    @Test(priority = 2, enabled=true)
    public void attemptFewQuestionsOfDiagnosticThenClickPractice()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("710");
            new Navigator().NavigateTo("eTextbook");
            new DiagnosticTest().startTest(4);

            for(int i = 0; i<4; i++)
            {
                driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
                Thread.sleep(3000);
            }

            new Navigator().NavigateTo("eTextbook");
            new PracticeTest().startTest();
            String notificationRobo = driver.findElement(By.cssSelector("div[class='al-notification-message-body']")).getText();
            System.out.println(notificationRobo);
            if(notificationRobo.equals("Your diagnostic test is not yet complete. Please finish it before attempting the practice test."))
            {
                logger.log(Level.INFO,"Displays Robo notification, When the diagnostic test is inprogress and student clicked on Practice ");
            }
            else
            {
                logger.log(Level.INFO,"No Robo notification displayed, When the diagnostic test is inprogress and student clicked on Practice");
                Assert.fail("Robo notification not displayed or incorrect, When the diagnostic test is inprogress and student clicked on Practice");
            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception  in testcase attemptFewQuestionsOfDiagnosticThenClickPractice in class ConfidenceLevelVerificationWhenClickedOnPracticeTestBeforeDiagnosticTest",e);
        }
    }

}
