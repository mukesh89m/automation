package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R162;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by Sumit on 8/7/2014.
 */
public class InstructorIsAbleToReportContentErrorsInAdaptiveTestReviewPage extends Driver{

    @Test
    public void instructorIsAbleToReportContentErrorsInAdaptiveTestReviewPage()
    {
        try
        {

            new LoginUsingLTI().ltiLogin("122");//login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new PracticeTest().startPracticeTestByInstructor();
            new WebDriverWait(driver,200).until(ExpectedConditions.visibilityOfElementLocated(By.className("cms-content-question-review-list-label")));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector("div[title='Report Content Errors']")));
            WebElement scroll=driver.findElement(By.cssSelector("div[title='Report Content Errors']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            //TC row no. 122----"1. Open Practice test -----1. Below each question it should show "Report content error" icon
            int errorIcon = driver.findElements(By.cssSelector("div[title='Report Content Errors']")).size();
            if(errorIcon == 0)
                Assert.fail("Report content error icon is absent for Adaptive practice test in instructor side");

            //TC row no. 123 ----4. Click on "Report content error" icon--1. Pop-up for reporting content-errors should appear.
            driver.findElement(By.cssSelector("div[title='Report Content Errors']")).click();//click on report content issue
            Thread.sleep(2000);
            int popUp = driver.findElements(By.id("content-issue-dialog")).size();
            if(popUp == 0)
                Assert.fail("on clicking Report content error icon the pop-up doesn't appear in Adaptive Practice test in instructor side");

            int rowCountBefore;
            boolean emailReceived ;
            int rowCountAfter ;
            DBConnect.Connect();
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();

            //TC row no. 125---3. On click of "Send" button it should not show any notification which is shown for student
            driver.findElement(By.id("text-area-content-issue")).click();//click on report content issue text area
            String issueText = new RandomString().randomstring(5);
            driver.findElement(By.id("text-area-content-issue")).sendKeys(issueText);//report text
            driver.findElement(By.id("send-content-issue-btn")).click();//click on report content issue send button
            int notice = driver.findElements(By.className("al-notification-message-body")).size();
            if(notice != 0)
                Assert.fail("Notification appears for instructor after clicking on send button for content error report for a Adaptive Practice test.");

            //TC row no. 124--5. Report a content error. --1. Email should be sent to the configured mail-id.
            emailReceived = new ValidateEmail().validateEmail(122,issueText);    //login to email and verify
            if(emailReceived == false)
                Assert.fail("On reporting a content issue for Adaptive Practice question by the instructor then email is still not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();

            //TC row no. 124---5. Report a content error. --2. Records for the content error should get inserted into the database.
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("On reporting a content issue for Adaptive Practice question by the instructor then the record is still not inserted in DB.");

            new LoginUsingLTI().ltiLogin("122");//login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new PracticeTest().startTloLevelPracticeTest(1);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector("div[title='Report Content Errors']")));

            //TC row no. 126----"1. Open TLO level Practice test -----1. Below each question it should show "Report content error" icon
            int errorIcon1 = driver.findElements(By.cssSelector("div[title='Report Content Errors']")).size();
            if(errorIcon1 == 0)
                Assert.fail("Report content error icon is absent for TLO level practice test in instructor side");

            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector("div[title='Report Content Errors']") ));
                    //TC row no. 127 ----4. Click on "Report content error" icon--1. Pop-up for reporting content-errors should appear.
                    driver.findElement(By.cssSelector("div[title='Report Content Errors']")).click();//click on report content issue
            Thread.sleep(2000);
            int popUp1 = driver.findElements(By.id("content-issue-dialog")).size();
            if(popUp1 == 0)
                Assert.fail("On clicking Report content error icon the pop-up doesn't appear in TLO level Adaptive Practice test in instructor side");
             DBConnect.Connect();
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();

            //TC row no. 129---3. On click of "Send" button it should not show any notification which is shown for student
            driver.findElement(By.id("text-area-content-issue")).click();//click on report content issue text area
            String issueText1 = new RandomString().randomstring(5);
            driver.findElement(By.id("text-area-content-issue")).sendKeys(issueText1);//report text
            driver.findElement(By.id("send-content-issue-btn")).click();//click on report content issue send button
            int notice1 = driver.findElements(By.className("al-notification-message-body")).size();
            if(notice1 != 0)
                Assert.fail("Notification appears for instructor after clicking on send button for content error report for a TLO level Adaptive Practice test.");

            //TC row no. 128--5. Report a content error. --1. Email should be sent to the configured mail-id.
            emailReceived = new ValidateEmail().validateEmail(122,issueText1);    //login to email and verify
            if(emailReceived == false)
                Assert.fail("On reporting a content issue for TLO level Adaptive Practice question by the instructor then email is still not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();

            //TC row no. 128---5. Report a content error. --2. Records for the content error should get inserted into the database.
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("On reporting a content issue for TLO level Adaptive Practice question by the instructor then the record is still not inserted in DB.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case instructorIsAbleToReportContentErrorsInAdaptiveTestReviewPage in class instructorIsAbleToReportContentErrorsInAdaptiveTestReviewPage.", e);
        }
    }

}
