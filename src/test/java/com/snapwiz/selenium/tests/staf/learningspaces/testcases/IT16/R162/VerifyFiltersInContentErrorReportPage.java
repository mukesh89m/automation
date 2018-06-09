package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R162;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.OpenAssignmentFromCMS;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ReportContentIssue;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by Sumit on 8/4/2014.
 */
public class VerifyFiltersInContentErrorReportPage extends Driver{

    @Test
    public void verifyFiltersInContentErrorReportPage() {
        try
        {

            new Assignment().create(50);

            new OpenAssignmentFromCMS().openAssignmentFromCMS("50");
            String text = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text);
            new ReportContentIssue().goToContentErrorReportPage();//go to report page
            //TC row no. 50, 51
            String allChapter = driver.findElement(By.linkText("All Chapters")).getText();
            if(!allChapter.equals("All Chapters"))
                Assert.fail("In content error report page \"All Chapters\" dropdown is absent.");

            //TC row no. 50, 51
            String userType = driver.findElement(By.linkText("All User Type")).getText();
            if(!userType.equals("All User Type"))
                Assert.fail("In content error report page \"All User Type\" dropdown is absent.");

            //TC row no. 50, 51
            String allStatus = driver.findElement(By.linkText("All Status")).getText();
            if(!allStatus.equals("All Status"))
                Assert.fail("In content error report page \"All Status\" dropdown is absent.");

            //TC row no. 50, 51
            String question = driver.findElement(By.linkText("Question")).getText();
            if(!question.equals("Question"))
                Assert.fail("In content error report page \"Question\" dropdown is absent.");

            driver.findElement(By.linkText("All Chapters")).click();//click on all Chapters
            Thread.sleep(2000);
            driver.findElement(By.partialLinkText("Ch 2:")).click();//select another Chapter
            Thread.sleep(5000);
            //TC row no. 55, 56, 57

            //TC row no. 58, 59, 60
            new ReportContentIssue().goToContentErrorReportPage();//go to report page to refresh and reset
            String errorText = driver.findElement(By.className("reported-content-issue-cmnts")).getText();
            driver.findElement(By.linkText("All User Type")).click();//select all user type
            Thread.sleep(3000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("Student")));//select student
            Thread.sleep(15000);
            String errorText1 = driver.findElement(By.className("reported-content-issue-cmnts")).getText();
            if(errorText.equals(errorText1))
                Assert.fail("After selecting another type of user the content error list does not load according to new user.");

            //TC row no. 61, 62, 63
            new OpenAssignmentFromCMS().openAssignmentFromCMS("50");
            new ReportContentIssue().goToContentErrorReportPage();//go to report page to refresh and reset
            String errorText2 = driver.findElement(By.className("reported-content-issue-cmnts")).getText();
            driver.findElement(By.linkText("All Status")).click();
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("Closed")));//select Closed
            Thread.sleep(15000);
            String errorText3 = driver.findElement(By.className("reported-content-issue-cmnts")).getText();
            if(errorText2.equals(errorText3))
                Assert.fail("After selecting another status the content error list does not load according to new status.");

            //TC row no. 64, 65, 66
            new ReportContentIssue().goToContentErrorReportPage();//go to report page to refresh and reset
            String errorText4 = driver.findElement(By.className("reported-content-issue-cmnts")).getText();
            driver.findElement(By.linkText("Question")).click();
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("Lesson")));//select Lesson
            Thread.sleep(15000);
            int errorTextPresent = driver.findElements(By.className("reported-content-issue-cmnts")).size();
            if(errorTextPresent != 0) {
                String errorText5 = driver.findElement(By.className("reported-content-issue-cmnts")).getText();
                if (errorText4.equals(errorText5))
                    Assert.fail("After selecting another content type the content error list does not load according to new content type.");
            }
            else {
                String noReport1 = driver.findElement(By.className("notify-text")).getText();
                if(noReport1 == null)
                    Assert.fail("After selecting another content type the content error list does not load according to new content type.");
            }


            //TC row no. 52, 53, 54
            driver.findElement(By.linkText(Config.course)).click();
            Thread.sleep(2000);
            driver.findElement(By.partialLinkText("Geography")).click();//select another course
            Thread.sleep(2000);
            String noReport1 = driver.findElement(By.className("notify-text")).getText();
            if(noReport1 == null)
                Assert.fail("After selecting another course the content error list does not load according to new course.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case verifyFiltersInContentErrorReportPage in class VerifyFiltersInContentErrorReportPage.", e);
        }
    }

}
