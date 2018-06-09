package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R162;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

import java.util.List;

/*
 * Created by Sumit on 8/4/2014.
 */
public class StudentContentErrorReportForDiagnosticQuestions extends Driver {

    @Test
    public void studentContentErrorReportForDiagnosticQuestions()
    {
        try
        {

            new LoginUsingLTI().ltiLogin("67");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new DiagnosticTest().startTest(4);
            int rowCountBefore = 0;
            DBConnect.Connect();
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            String text = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text);//TC row no. 67
            boolean emailReceived = false;
            emailReceived = new ValidateEmail().validateEmail(67,text);    //login to email and verify
            //TC row no. 68
            if(emailReceived == false)
                Assert.fail("After reporting content error for a diagnostic question the email is not sent to the configured email id.");
            int rowCountAfter = 0;
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            //TC row no. 68
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("After reporting content error for a diagnostic question the record is not inserted in DB.");

            new LoginUsingLTI().ltiLogin("67");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new DiagnosticTest().continueDiagnosticTest();
            new DiagnosticTest().attemptAllCorrect(0, false, false);//complete the diagnostic test
            driver.findElement(By.className("question-card-question-content")).click();//click on question card
            Thread.sleep(2000);
            DBConnect.Connect();
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            String text1 = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text1);
            emailReceived = new ValidateEmail().validateEmail(67,text1);    //login to email and verify
            //TC row no. 69
            if(emailReceived == false)
                Assert.fail("After reporting content error for a diagnostic question from question card the email is not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            //TC row no. 69
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("After reporting content error for a diagnostic question from question card the record is not inserted in DB.");


            new LoginUsingLTI().ltiLogin("67");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new PracticeTest().startTest();
            Thread.sleep(1000);
            int  submitButton = driver.findElements(By.id("submit-practice-question-button")).size();
            while(submitButton!=0) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("submit-practice-question-button"))); //submit
                Thread.sleep(1000);
                submitButton = driver.findElements(By.id("submit-practice-question-button")).size();
            }
            Thread.sleep(3000);
            int noticeSize = driver.findElements(By.className("al-notification-message-body")).size();
            if(noticeSize==1)

            {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("submit-practice-question-button")));
                Thread.sleep(3000);
            }
            DBConnect.Connect();
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            String text2 = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text2);//TC row no. 70
            emailReceived = new ValidateEmail().validateEmail(67,text2);    //login to email and verify
            //TC row no. 71
            if(emailReceived == false)
                Assert.fail("After reporting content error for a adaptive practice question then email is not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            //TC row no. 71
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("After reporting content error for a adaptive practice question then the record is not inserted in DB.");

            new LoginUsingLTI().ltiLogin("67");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new PracticeTest().startTest();
            for (int i = 0; i< 4; i++)
                new PracticeTest().AttemptCorrectAnswer(0,"67");
            new PracticeTest().quitThePracticeTest();
            driver.findElement(By.className("question-card-question-content")).click();//click on question card
            Thread.sleep(2000);
            DBConnect.Connect();
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            String text3 = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text3);
            emailReceived = new ValidateEmail().validateEmail(67,text3);    //login to email and verify
            //TC row no. 74
            if(emailReceived == false)
                Assert.fail("After reporting content error for a adaptive practice question from question card the email is not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            //TC row no. 74
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("After reporting content error for a adaptive practice question from question card the record is not inserted in DB.");

            new LoginUsingLTI().ltiLogin("67");//login as student
            new Navigator().NavigateTo("My Activity");
            driver.findElement(By.cssSelector("a[asstype='diagnostic']")).click();//click on Diagnostic from My activity
            Thread.sleep(5000);
            driver.findElement(By.className("question-card-question-content")).click();//click on question card
            Thread.sleep(2000);
            DBConnect.Connect();
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            String text4 = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text4);//TC row no 75, 76
            emailReceived = new ValidateEmail().validateEmail(67,text4);    //login to email and verify
            //TC row no. 77
            if(emailReceived == false)
                Assert.fail("After reporting content error for a diagnostic question from question card the email is not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            //TC row no. 77
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("After reporting content error for a diagnostic question from question card the record is not inserted in DB.");

            new LoginUsingLTI().ltiLogin("67");//login as student
            new Navigator().NavigateTo("Proficiency Report");
            driver.findElement(By.className("question-card-question-content")).click();//click on question card
            Thread.sleep(2000);
            String issueText = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(issueText);
            emailReceived = new ValidateEmail().validateEmail(67,issueText);    //login to email and verify
            //TC row no. 90
            if(emailReceived == false)
                Assert.fail("After reporting content error for a diagnostic question from question card from  Proficiency Report page the email is not sent to the configured email id.");

            new LoginUsingLTI().ltiLogin("67");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new PracticeTest().startTloLevelPracticeTest(1);
            Thread.sleep(1000);
            int  submitButton1 = driver.findElements(By.id("submit-practice-question-button")).size();
            while(submitButton1!=0) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("submit-practice-question-button"))); //submit
                Thread.sleep(1000);
                submitButton1 = driver.findElements(By.id("submit-practice-question-button")).size();
            }
            Thread.sleep(3000);
            int noticeSize1 = driver.findElements(By.className("al-notification-message-body")).size();
            if(noticeSize1==1)

            {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input[id='submit-practice-question-button']")));
                Thread.sleep(3000);
            }
            DBConnect.Connect();
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            String text5 = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text5);//TC row no. 72
            emailReceived = new ValidateEmail().validateEmail(67,text5);    //login to email and verify
            //TC row no. 73
            if(emailReceived == false)
                Assert.fail("After reporting content error for a TLO level adaptive practice question then email is not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            //TC row no. 73
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("After reporting content error for a TLO level adaptive practice question then the record is not inserted in DB.");

            new LoginUsingLTI().ltiLogin("67");//login as student
            new Navigator().NavigateTo("Proficiency Report");
            driver.findElement(By.className("question-card-question-content")).click();//click on question card
            Thread.sleep(2000);
            String issueText1 = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(issueText1);
            emailReceived = new ValidateEmail().validateEmail(67,issueText1);    //login to email and verify
            //TC row no. 89
            if(emailReceived == false)
                Assert.fail("After reporting content error for a diagnostic question from question card from  Proficiency Report page the email is not sent to the configured email id.");

            new LoginUsingLTI().ltiLogin("67");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new PracticeTest().startTloLevelPracticeTest(1);
            for(int i = 0; i < 7; i++)
                new PracticeTest().AttemptCorrectAnswer(0,"67");
            new PracticeTest().quitThePracticeTest();

            new LoginUsingLTI().ltiLogin("67_1");//login as instructor
            new Navigator().NavigateTo("Proficiency Report");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("coursePerformanceByChapters-xAxisLabel")));
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("chapterPerformanceByObjectives-xAxisLabel")));
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("chapterPerformanceByQuestions-xAxisLabel")));
            Thread.sleep(2000);
            DBConnect.Connect();
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            //TC row no. 145----"1. Go to a question by clicking on the display_label below the bar in "Objective Performance By Questions" page-----1. Below each question it should show "Report content error" icon
            int errorIcon = driver.findElements(By.cssSelector("div[title='Report Content Errors']")).size();
            if(errorIcon == 0)
                Assert.fail("Report content error icon is absent for question in \"Objective Performance By Questions\" page in instructor side");

            //TC row no. 146 ----4. Click on "Report content error" icon--1. Pop-up for reporting content-errors should appear.
            List<WebElement> allIcon = driver.findElements(By.cssSelector("div[title='Report Content Errors']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allIcon.get(1));//click on icon
            Thread.sleep(2000);
            int popUp = driver.findElements(By.id("content-issue-dialog")).size();
            if(popUp == 0)
                Assert.fail("On clicking Report content error icon the pop-up doesn't appear in \"Objective Performance By Questions\" in instructor side");

            //TC row no. 148---3. On click of "Send" button it should not show any notification which is shown for student
            driver.findElement(By.id("text-area-content-issue")).click();//click on report content issue text area
            String issueText2 = new RandomString().randomstring(5);
            driver.findElement(By.id("text-area-content-issue")).sendKeys(issueText2);//report text
            driver.findElement(By.id("send-content-issue-btn")).click();//click on report content issue send button
            int notice = driver.findElements(By.className("al-notification-message-body")).size();
            if(notice != 0)
                Assert.fail("Notification appears for instructor after clicking on send button for content error report for a question in \"Objective Performance By Questions\" page.");

            //TC row no. 147--5. Report a content error. --1. Email should be sent to the configured mail-id.
            emailReceived = new ValidateEmail().validateEmail(67_1,issueText2);    //login to email and verify
            if(emailReceived == false)
                Assert.fail("On reporting a content issue for \"Objective Performance By Questions\" question by the instructor then email is still not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();

            //TC row no. 147---5. Report a content error. --2. Records for the content error should get inserted into the database.
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("On reporting a content issue for \"Objective Performance By Questions\" by the instructor then the record is still not inserted in DB.");

        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in test case studentContentErrorReportForDiagnosticQuestions in class StudentContentErrorReportForDiagnosticQuestions.", e);
        }
    }

}
