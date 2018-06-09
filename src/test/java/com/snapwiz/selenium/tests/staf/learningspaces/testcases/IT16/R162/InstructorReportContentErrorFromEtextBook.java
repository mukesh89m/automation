package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R162;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ValidateContentErrorInDB;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ValidateEmail;

/*
 * Created by Sumit on 8/8/2014.
 */
public class InstructorReportContentErrorFromEtextBook extends Driver{

    @Test
    public void instructorReportContentErrorFromEtextBook()
    {
        try
        {
            new Assignment().create(134);

            new LoginUsingLTI().ltiLogin("134");//login as instructor
            new Assignment().assignAssignmentFromEtextBook("134", 1);
            new Assignment().openAssignmentFromAssignmentTab(0);

            //TC row no. 134----"1. Open assignment from eTextbook -----1. Below each question it should show "Report content error" icon
            int errorIcon = driver.findElements(By.cssSelector("div[title='Report Content Errors']")).size();
            if(errorIcon == 0)
                Assert.fail("Report content error icon is absent for assignment opened from eTextbook in instructor side");

            //TC row no. 135 ----4. Click on "Report content error" icon--1. Pop-up for reporting content-errors should appear.
            driver.findElement(By.cssSelector("div[title='Report Content Errors']")).click();//click on report content issue
            Thread.sleep(2000);
            int popUp = driver.findElements(By.id("content-issue-dialog")).size();
            if(popUp == 0)
                Assert.fail("on clicking Report content error icon the pop-up doesn't appear for assignment opened from eTextbook in instructor side");

            int rowCountBefore;
            boolean emailReceived ;
            int rowCountAfter ;
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();

            //TC row no. ---3. On click of "Send" button it should not show any notification which is shown for student
            driver.findElement(By.id("text-area-content-issue")).click();//click on report content issue text area
            String issueText = new RandomString().randomstring(5);
            driver.findElement(By.id("text-area-content-issue")).sendKeys(issueText);//report text
            driver.findElement(By.id("send-content-issue-btn")).click();//click on report content issue send button
            int notice = driver.findElements(By.className("al-notification-message-body")).size();
            if(notice != 0)
                Assert.fail("Notification appears for instructor after clicking on send button for content error report for a for assignment opened from eTextbook.");

            //TC row no. 136--5. Report a content error. --1. Email should be sent to the configured mail-id.
            emailReceived = new ValidateEmail().validateEmail(134,issueText);    //login to email and verify
            if(emailReceived == false)
                Assert.fail("On reporting a content issue for for assignment opened from eTextbook by the instructor then email is still not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();

            //TC row no. 136---5. Report a content error. --2. Records for the content error should get inserted into the database.
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("On reporting a content issue for  assignment opened from eTextbook by the instructor then the record is still not inserted in DB.");


            new LoginUsingLTI().ltiLogin("134");//login as instructor
            new Navigator().NavigateTo("Assignments");
            driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();//click on Assignment
            Thread.sleep(2000);

            //TC row no. 137----"1. Open assignment from Assignment page -----1. Below each question it should show "Report content error" icon
            int errorIcon1 = driver.findElements(By.cssSelector("div[title='Report Content Errors']")).size();
            if(errorIcon1 == 0)
                Assert.fail("Report content error icon is absent for assignment opened from Assignment Page in instructor side");

            //TC row no. 138 ----4. Click on "Report content error" icon--1. Pop-up for reporting content-errors should appear.
            driver.findElement(By.cssSelector("div[title='Report Content Errors']")).click();//click on report content issue
            Thread.sleep(2000);
            int popUp1 = driver.findElements(By.id("content-issue-dialog")).size();
            if(popUp1 == 0)
                Assert.fail("on clicking Report content error icon the pop-up doesn't appear for assignment opened from Assignment Page in instructor side");

            //TC row no. 140---3. On click of "Send" button it should not show any notification which is shown for student
            driver.findElement(By.id("text-area-content-issue")).click();//click on report content issue text area
            String issueText1 = new RandomString().randomstring(5);
            driver.findElement(By.id("text-area-content-issue")).sendKeys(issueText1);//report text
            driver.findElement(By.id("send-content-issue-btn")).click();//click on report content issue send button
            int notice1 = driver.findElements(By.className("al-notification-message-body")).size();
            if(notice1 != 0)
                Assert.fail("Notification appears for instructor after clicking on send button for content error report for a for assignment opened from Assignment Page.");

            //TC row no. 139--5. Report a content error. --1. Email should be sent to the configured mail-id.
            emailReceived = new ValidateEmail().validateEmail(134,issueText1);    //login to email and verify
            if(emailReceived == false)
                Assert.fail("On reporting a content issue for for assignment opened from Assignment Page by the instructor then email is still not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();

            //TC row no. 139---5. Report a content error. --2. Records for the content error should get inserted into the database.
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("On reporting a content issue for  assignment opened from Assignment Page by the instructor then the record is still not inserted in DB.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case instructorReportContentErrorFromEtextBook in class InstructorReportContentErrorFromEtextBook.", e);
        }
    }

}
