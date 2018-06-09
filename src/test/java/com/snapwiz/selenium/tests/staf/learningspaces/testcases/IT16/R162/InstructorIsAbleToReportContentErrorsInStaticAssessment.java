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

/**
 * Created by Sumit on 8/7/2014.
 */
public class InstructorIsAbleToReportContentErrorsInStaticAssessment extends Driver{

    @Test
    public void instructorIsAbleToReportContentErrorsInStaticAssessment()
    {
        try
        {

            new LoginUsingLTI().ltiLogin("130");//login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new SelectCourse().selectInvisibleAssignment("1.1 Concept Check");
            new WebDriverWait(driver,200).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[title='Report Content Errors']")));
            WebElement scroll=driver.findElement(By.cssSelector("div[title='Report Content Errors']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            //TC row no. 130----"1. Open Static assessment -----1. Below each question it should show "Report content error" icon
            int errorIcon = driver.findElements(By.cssSelector("div[title='Report Content Errors']")).size();
            if(errorIcon == 0)
                Assert.fail("Report content error icon is absent for static assessment in instructor side");

            //TC row no. 131 ----4. Click on "Report content error" icon--1. Pop-up for reporting content-errors should appear.
            driver.findElement(By.cssSelector("div[title='Report Content Errors']")).click();//click on report content issue
            Thread.sleep(2000);
            int popUp = driver.findElements(By.id("content-issue-dialog")).size();
            if(popUp == 0)
                Assert.fail("on clicking Report content error icon the pop-up doesn't appear in static assessment in instructor side");

            int rowCountBefore;
            boolean emailReceived ;
            int rowCountAfter ;
            DBConnect.Connect();
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();

            //TC row no. 133---3. On click of "Send" button it should not show any notification which is shown for student
            driver.findElement(By.id("text-area-content-issue")).click();//click on report content issue text area
            String issueText = new RandomString().randomstring(5);
            driver.findElement(By.id("text-area-content-issue")).sendKeys(issueText);//report text
            driver.findElement(By.id("send-content-issue-btn")).click();//click on report content issue send button
            int notice = driver.findElements(By.className("al-notification-message-body")).size();
            if(notice != 0)
                Assert.fail("Notification appears for instructor after clicking on send button for content error report for a static assessment.");

            //TC row no. 132--5. Report a content error. --1. Email should be sent to the configured mail-id.
            emailReceived = new ValidateEmail().validateEmail(130,issueText);    //login to email and verify
            if(emailReceived == false)
                Assert.fail("On reporting a content issue for static assessment question by the instructor then email is still not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();

            //TC row no. 132---5. Report a content error. --2. Records for the content error should get inserted into the database.
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("On reporting a content issue for static assessment question by the instructor then the record is still not inserted in DB.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case instructorIsAbleToReportContentErrorsInAdaptiveTestReviewPage in class instructorIsAbleToReportContentErrorsInAdaptiveTestReviewPage.", e);
        }
    }

}
