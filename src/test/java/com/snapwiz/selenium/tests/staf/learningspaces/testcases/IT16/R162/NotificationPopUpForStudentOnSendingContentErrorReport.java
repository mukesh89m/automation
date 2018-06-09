package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R162;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by Sumit on 8/7/2014.
 */
public class NotificationPopUpForStudentOnSendingContentErrorReport extends Driver{

    @Test
    public void notificationPopUpForStudentOnSendingContentErrorReport()
    {
        try
        {

            new LoginUsingLTI().ltiLogin("91");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new DiagnosticTest().startTest(4);
            new ReportContentIssue().clickOnReportContentIssueIcon();//click on report content issue
            driver.findElement(By.id("text-area-content-issue")).click();//click on report content issue text area
            String issueText = new RandomString().randomstring(5);
            driver.findElement(By.id("text-area-content-issue")).sendKeys(issueText);//report text
            driver.findElement(By.id("send-content-issue-btn")).click();//click on report content issue send button
            Thread.sleep(3000);
            //TC row no. 91
            String notice = driver.findElement(By.className("al-notification-message-body")).getText();
            if(!notice.contains("Are you sure there is a problem with this question? You may want to start a discussion on this question with your classmates before reporting a problem."))
                Assert.fail("Notification does not appear for student after clicking on send button for content error report.");

            int rowCountBefore;
            boolean emailReceived ;
            int rowCountAfter ;
            DBConnect.Connect();
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            driver.findElement(By.id("back-to-question")).click(); //click on cancel
            Thread.sleep(2000);
            new Gmail().login(Config.suppportEmail, Config.suppportEmailPassword);//log in to gmail
            driver.findElement(By.className("yW")).click();//open the email
            Thread.sleep(3000);
            //validate the text
            String emailContent = driver.findElement(By.cssSelector("div[class='a3s']")).getText();
            //TC row no. 94
            if(emailContent.contains(issueText))
                Assert.fail("On clicking cancel button in the pop-up for reporting a content issue then email is still sent to the configured email id.");
            driver.findElement(By.cssSelector("a[title='Google Account: build@snapwiz.com']")).click();Thread.sleep(3000);//click on account
            Thread.sleep(3000);
            driver.findElement(By.id("gb_71")).click(); // To click the sign out button
            Thread.sleep(20000);
            driver.manage().deleteAllCookies();
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            //TC row no. 96
            if(rowCountBefore != rowCountAfter)
                Assert.fail("On clicking cancel button in the pop-up for reporting a content issue then the record is still inserted in DB.");

            new DirectLogin().CMSLogin();
            driver.findElement(By.cssSelector("img[alt=\"" + Config.course + "\"]")).click();//open course
            Thread.sleep(15000);
            new ReportContentIssue().goToContentErrorReportPage();
            //TC row no. 95
            boolean issuePresent = new ReportContentIssue().isIssuePresentInContentErrorReportPage(issueText);
            if(issuePresent)
                Assert.fail("On clicking cancel button in the pop-up for reporting a content issue then the record is still present in content error report page.");

            new LoginUsingLTI().ltiLogin("91");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new DiagnosticTest().continueDiagnosticTest();
            String text = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text);
            emailReceived = new ValidateEmail().validateEmail(91,text);    //login to email and verify
            //TC row no. 92
            if(!emailReceived)
                Assert.fail("On clicking send button in the pop-up for reporting a content issue then email is not sent to the configured email id.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case notificationPopUpForStudentOnSendingContentErrorReport in class NotificationPopUpForStudentOnSendingContentErrorReport.", e);
        }
    }

}
