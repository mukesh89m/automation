package com.snapwiz.selenium.tests.staf.orion.testcases.regression.orion;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.orion.apphelper.*;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.Preview.Preview;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.allActivity.AllActivity;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.eTextbook.Discussion;
import com.snapwiz.selenium.tests.staf.orion.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.Dashboard.DashBoard;

import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.PerformanceReportInstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 28-01-2016.
 */
public class DiagnosticAndPractice extends Driver {
    @Test(priority = 1, enabled = true)
    public void diagnosticAndPractice() {
        WebDriver driver=Driver.getWebDriver();
        try {
            DashBoard dashBoard = PageFactory.initElements(driver,DashBoard.class);
            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(driver,PerformanceReportInstructor.class);

            new LoginUsingLTI().ltiLogin("1"); // Login as Student
            ReportUtil.log("Student Login", "Student logged sucessefully", "info");
            CustomAssert.assertEquals(dashBoard.buildYourProficiency.getText(), "BUILD YOUR PROFICIENCY", "Verify dashboard page.", "Student landed to Dashboard.", "Student is not landed to Dashboard.");
            CustomAssert.assertEquals(dashBoard.beginDiagnostic_Button.getAttribute("title"),"Begin Diagnostic","Verify begin button","Begin button is displayed","Begin button is not displayed.");
            dashBoard.beginDiagnostic_Button.click();//click on begin button
            dashBoard.continue_Button.click();//click on continue button
            CustomAssert.assertEquals(dashBoard.error_Message.getText(), "Please say how confident you feel about this chapter. You will get more accurate recommendations!", "Verify error message", "Error message is displayed", "Error message is not displaying");
            dashBoard.confidenceLevel.get(1).click();//select confidence level
            Thread.sleep(2000);
            dashBoard.continue_Button.click();//click on continue button
            for(int j =0;j<1;j++)
                new DiagnosticTest().attemptCorrect(2);
            new DiagnosticTest().quitTestAndGoToDashboard();
            CustomAssert.assertEquals(performanceReportInstructor.coursePerformanceSummaryTitle.getText(),"Chapter Performance Summary","Verify Chapter Performance Summary title","Chapter Performance Summary is displayed","Chapter Performance Summary title is not displaying");
            CustomAssert.assertEquals(performanceReportInstructor.questionCard.size(),1,"Verify question card","Question card is displayed","Question card is not displaying");
            ReportUtil.log("Quit' link functionality"," 'Quit' link functionality of diagnostic assessment is displayed","info");


            Discussion discussion = PageFactory.initElements(driver,Discussion.class);
            //new LoginUsingLTI().ltiLogin("1"); // Login as Student
            driver.findElement(By.className("al-home-icon")).click();
            dashBoard.beginDiagnostic_Button.click();//click on begin button
            new DiagnosticTest().startTest(0,1);
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 1, "correct", false, false, false);
            CustomAssert.assertEquals(dashBoard.continueDiagnostic.getAttribute("title"),"Continue Diagnostic","Verify continue button","Begin button should  converted to Continue button for that chapter.","Begin\" button is not converted to Continue button for that chapter.");
            dashBoard.continueDiagnostic.click();//click on continue
            CustomAssert.assertEquals(discussion.questionLabel.getText(),"Q 2.2:","Verify question","Student navigated to question page(where he/she left the assessment)","Student is not navigated to question page(where he/she left the assessment)");
            ReportUtil.log("ContinueLater' link functionality","'ContinueLater' link functionality of diagnostic assessment is displayed","info");

            //new LoginUsingLTI().ltiLogin("1"); // Login as Student
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("al-home-icon")));
            CustomAssert.assertEquals(dashBoard.practice_Button.get(0).getAttribute("title"), "Practice", "Verify practice button at chapter level", "Practice button is displayed at chapter level.", "Practice button is not displayed at chapter level.");
            new MouseHover().mouserHoverByXpathList(dashBoard.content.get(1));// Mouse Over on tlo
            CustomAssert.assertEquals(dashBoard.practice_Button.get(1).getAttribute("title"), "Practice", "Verify practice button at Tlo level", "Practice button is displayed at Tlo level.", "Practice button is not displayed at Tlo level.");
            CustomAssert.assertEquals(dashBoard.study_Button.get(1).getAttribute("title"), "Study", "Verify Study button at Tlo level", "Study button is displayed at Tlo level.", "Study button is not displayed at Tlo level.");
            new PracticeTest().startTest();
            for(int i=0;i<1;i++) {
                new PracticeTest().attemptQuestion("correct", 2, false, false);//click on chapter level practice button
            }
            new PracticeTest().quitTestAndGoToDashboard();
            CustomAssert.assertEquals(dashBoard.practice_Button.get(0).isDisplayed(), true, "Verify dashboard", ".Student  navigated to dashboard.", ".Student is not navigated to dashboard.");
            new PracticeTest().startTest();
            for(int i=0;i<1;i++) {
                new PracticeTest().attemptQuestion("correct", 2, false, false);//click on chapter level practice button
            }
            new PracticeTest().quitTestAndGoToReport();
            CustomAssert.assertEquals(performanceReportInstructor.coursePerformanceSummaryTitle.getText(),"Chapter Performance Summary","Verify Chapter Performance Summary title","Chapter Performance Summary is displayed","Chapter Performance Summary title is not displaying");
            CustomAssert.assertEquals(performanceReportInstructor.questionCard.size(),3,"Verify question card","Question card is displayed","Question card is not displaying");
            ReportUtil.log("Practice' button functionality","'Practice' button functionality at chapter level is displayed","info");

            //new LoginUsingLTI().ltiLogin("1"); // Login as Student
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("al-home-icon")));
//            new MouseHover().mouserHoverByXpathList(dashBoard.content.get(1));// Mouse Over on tlo
//            dashBoard.practice_Button.get(1).click();
           WebElement TLO=dashBoard.content.get(1);
            WebElement PracBtn=dashBoard.practice_Button.get(1);
            new Actions(driver).moveToElement(TLO).click(PracBtn).build().perform();
            for(int i=0;i<1;i++) {
                new PracticeTest().attemptQuestion("correct", 2, false, false);//click on chapter level practice button
            }
            new PracticeTest().quitTestAndGoToReport();
            CustomAssert.assertEquals(performanceReportInstructor.coursePerformanceSummaryTitle.getText(),"Objective Performance Summary","Verify Objective Performance Summary title","Objective Performance Summary is displayed","Objective Performance Summary title is not displaying");
            CustomAssert.assertEquals(performanceReportInstructor.questionCard.size(),2,"Verify question card","Question card is displayed","Question card is not displaying");
            ReportUtil.log("'Practice' button on tlo level","'Practice' button on tlo level of practice assessment is displayed","info");

            Preview preview = PageFactory.initElements(driver,Preview.class);

            //new LoginUsingLTI().ltiLogin("1"); // Login as Student
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("al-home-icon")));
//            new MouseHover().mouserHoverByXpathList(dashBoard.content.get(1));// Mouse Over on tlo
//            dashBoard.practice_Button.get(1).click();
            new Actions(driver).moveToElement(dashBoard.content.get(1)).click(dashBoard.practice_Button.get(1)).build().perform();
            new AddDiscussionInQuestions().addDiscussionAndNotesOnPractice(1, 1, "correct", "Discussion", 3, "Reply", "Notes", 3);//click on chapter level practice button
            preview.contentIssue.click();//click on report content issue
            preview.contentIssueTextArea.click();
            preview.contentIssueTextArea.sendKeys("Report Issues");
            preview.send_Button.click();//click on send button
            Thread.sleep(2000);
            CustomAssert.assertEquals(preview.yesLinkContentIssueNotification.getText(),"Yes","Verify yes link on notification popup of report content issue","Yes link is displayed","Yes link is not displaying");
            preview.yesLinkContentIssueNotification.click();//click on yes
            preview.contentIssue.click();//click on report content issue
            preview.contentIssueTextArea.click();
            preview.contentIssueTextArea.sendKeys("Report Issues");
            preview.send_Button.click();//click on send button
            WebDriverUtil.waitTillVisibilityOfElement(preview.cancelLinkContentIssueNotification,10);
            CustomAssert.assertEquals(preview.cancelLinkContentIssueNotification.getText(), "Cancel", "Verify cancel link on notification popup of report content issue", "Cancel link is displayed", "Cancel link is not displaying");
            preview.cancelLinkContentIssueNotification.click();//click on cancel
            new PracticeTest().quitTestAndGoToReport();
            CustomAssert.assertEquals(performanceReportInstructor.coursePerformanceSummaryTitle.getText(),"Objective Performance Summary","Verify Objective Performance Summary title","Objective Performance Summary is displayed","Objective Performance Summary title is not displaying");
            ReportUtil.log("discussion and notes"," Added discussion and notes to question in practice assessment","info");

            AllActivity allActivity = PageFactory.initElements(driver,AllActivity.class);

           // new LoginUsingLTI().ltiLogin("1"); // Login as Student
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("al-home-icon")));
            new Navigator().NavigateTo("All Activity");//Navigate to All Activity from profile dropdown
            CustomAssert.assertTrue(allActivity.activityHeader.get(0).getText().contains("Completed a Practice Session"), "Verify practice assessment", "Completed practice session is available", "Completed practice session is not available");
            CustomAssert.assertTrue(allActivity.activityHeader.get(1).getText().contains("Deleted a Note"), "Verify Deleted Note entry", "Deleted Note entry is available", "Deleted Note entry is not available");
            CustomAssert.assertTrue(allActivity.activityHeader.get(2).getText().contains("Posted a Note"), "Verify Posted Note entry", "Posted Note entry is available", "Posted Note entry is not available");
            CustomAssert.assertTrue(allActivity.activityHeader.get(5).getText().contains("Replied to a Discussion"), "Verify Replied to Discussion entry ", "Replied to Discussion entry is available", "Replied to Discussion entry is not available");
            CustomAssert.assertTrue(allActivity.activityHeader.get(7).getText().contains("Started a Discussion"), "Verify Started a Discussion entry ", "Started a Discussion entry is available", "Started a Discussion entry is not available");
            CustomAssert.assertTrue(allActivity.activityHeader.get(12).getText().contains("Completed a Diagnostic"), "Verify Completed a Diagnostic entry ", "Completed a Diagnostic entry is available", "Completed a Diagnostic entry is not available");

            WebDriverUtil.clickOnElementUsingJavascript( allActivity.allChaptersFilter.get(0));////click on all chapters filter
            Thread.sleep(3000);
            chapterName("Ch 3: Economic Challenges Facing Contemporary Business");
            CustomAssert.assertEquals(allActivity.notificationStudent.getText(),"No Activity Found.","Verify notification message","A notification popup with message No Activity Found message is displayed","A notification popup with message No Activity Found message is not displayed");

            allActivity.myJournal.click();//click on my journal
            Thread.sleep(2000);
            CustomAssert.assertEquals(allActivity.myJournalTitle.getText(),"My Journal","Verify my journal page","My journal page is displayed","My journal page is not opened");
            CustomAssert.assertTrue(allActivity.activityHeader.get(0).getText().contains("Completed a Practice Session"), "Verify practice assessment", "Completed practice session is available", "Completed practice session is not available");
            CustomAssert.assertTrue(allActivity.activityHeader.get(1).getText().contains("Deleted a Note"), "Verify Deleted Note entry", "Deleted Note entry is available", "Deleted Note entry is not available");
            CustomAssert.assertTrue(allActivity.activityHeader.get(2).getText().contains("Posted a Note"), "Verify Posted Note entry", "Posted Note entry is available", "Posted Note entry is not available");
            CustomAssert.assertTrue(allActivity.activityHeader.get(5).getText().contains("Replied to a Discussion"), "Verify Replied to Discussion entry ", "Replied to Discussion entry is available", "Replied to Discussion entry is not available");
            CustomAssert.assertTrue(allActivity.activityHeader.get(7).getText().contains("Started a Discussion"), "Verify Started a Discussion entry ", "Started a Discussion entry is available", "Started a Discussion entry is not available");
            CustomAssert.assertTrue(allActivity.activityHeader.get(12).getText().contains("Completed a Diagnostic"), "Verify Completed a Diagnostic entry ", "Completed a Diagnostic entry is available", "Completed a Diagnostic entry is not available");
            allActivity.allChaptersFilter.get(0).click();//click on all chapters filter
            chapterName("Ch 3: Economic Challenges Facing Contemporary Business");
            CustomAssert.assertEquals(allActivity.notificationStudent.getText(),"No Activity Found.","Verify notification message","A notification popup with message No Activity Found message is displayed","A notification popup with message No Activity Found message is not displayed");
            ReportUtil.log("All Activity and My journal","All the entries are available in All Activity and My journal page ","info");

        } catch (Exception e) {
            Assert.fail("Exception in test method allActivity of class DiagnosticAndPractice", e);
        }
    }


    public void chapterName(String chapter){
        WebDriver driver=Driver.getWebDriver();
        WebElement chapterName=driver.findElement(By.xpath("//ul[contains(@id,'sbOptions_')]/div[2]/div/li/a[@title='"+chapter+"']"));
        WebDriverUtil.clickOnElementUsingJavascript(chapterName);//click on particular chapter

    }
}
