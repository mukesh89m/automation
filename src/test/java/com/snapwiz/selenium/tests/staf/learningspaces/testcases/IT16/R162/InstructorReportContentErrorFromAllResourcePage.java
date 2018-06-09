package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R162;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by Sumit on 8/8/2014.
 */
public class InstructorReportContentErrorFromAllResourcePage extends Driver {

    @Test
    public void instructorReportContentErrorFromAllResourcePage() {
        try {
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "141");
            new Assignment().create(141);

            new LoginUsingLTI().ltiLogin("141");//login as instructor
            new Navigator().NavigateTo("Assignments");

            driver.findElement(By.cssSelector("span.ins-assignment-button-sprite.instructor-assignment-new")).click();

            Thread.sleep(3000);
            if (driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources")) {//Opening All Resources tab if not opened after clicking on New Assignment button

                driver.findElement(By.cssSelector("span[title='All Resources']")).click();
            }
            //Adding assignment to search
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(3000);
            driver.findElement(By.className("resource-title")).click();//click to open the assignment
            Thread.sleep(2000);

            //TC row no. 141----"1. Open Static assessment -----1. Below each question it should show "Report content error" icon
            int errorIcon = driver.findElements(By.cssSelector("div[title='Report Content Errors']")).size();
            if (errorIcon == 0)
                Assert.fail("Report content error icon is absent for assignment in Resource page in instructor side");

            //TC row no. 142 ----4. Click on "Report content error" icon--1. Pop-up for reporting content-errors should appear.
            driver.findElement(By.cssSelector("div[title='Report Content Errors']")).click();//click on report content issue
            Thread.sleep(2000);
            int popUp = driver.findElements(By.id("content-issue-dialog")).size();
            if (popUp == 0)
                Assert.fail("on clicking Report content error icon the pop-up doesn't appear for assignment in Resource page in instructor side");

            int rowCountBefore;
            boolean emailReceived;
            int rowCountAfter;
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();

            //TC row no. 144---3. On click of "Send" button it should not show any notification which is shown for student
            driver.findElement(By.id("text-area-content-issue")).click();//click on report content issue text area
            String issueText = new RandomString().randomstring(5);
            driver.findElement(By.id("text-area-content-issue")).sendKeys(issueText);//report text
            driver.findElement(By.id("send-content-issue-btn")).click();//click on report content issue send button
            int notice = driver.findElements(By.className("al-notification-message-body")).size();
            if (notice != 0)
                Assert.fail("Notification appears for instructor after clicking on send button for content error report for a for assignment in Resource page.");

            //TC row no. 143--5. Report a content error. --1. Email should be sent to the configured mail-id.
            emailReceived = new ValidateEmail().validateEmail(141, issueText);    //login to email and verify
            if (emailReceived == false)
                Assert.fail("On reporting a content issue for for assignment in Resource page question by the instructor then email is still not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();

            //TC row no. 143---5. Report a content error. --2. Records for the content error should get inserted into the database.
            if (rowCountBefore + 1 != rowCountAfter)
                Assert.fail("On reporting a content issue for  assignment in Resource page  by the instructor then the record is still not inserted in DB.");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorReportContentErrorFromAllResourcePage in class InstructorReportContentErrorFromAllResourcePage.", e);
        }
    }
}
