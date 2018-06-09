package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R162;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.OpenAssignmentFromCMS;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ReportContentIssue;

/*
 * Created by Sumit on 8/2/2014.
 */
public class AuthorShouldBeAbleToReportAnyContentErrors extends Driver{

    @Test(priority = 1, enabled = true)
    public void authorShouldBeAbleToReportAnyContentErrors()
    {
        try
        {


            new Assignment().create(19);

            new OpenAssignmentFromCMS().openAssignmentFromCMS("19");
            String text = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text);
            String text1 = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text1);

            new OpenAssignmentFromCMS().openAssignmentFromCMS("19");
            //TC row no. 19
            String reportCount = driver.findElement(By.id("content-error-count")).getText();
            int countValue=Integer.parseInt(reportCount);
            if(countValue==0)
                Assert.fail("The report count is not present in the icon.");
            if(reportCount.equals(" ") || reportCount==null)
                Assert.fail("The report count is not present in the icon.");
            new ReportContentIssue().clickOnReportContentIssueIcon();//click on report content issue
            Thread.sleep(2000);
            //TC row no. 20
            List<WebElement> allText = driver.findElements(By.className("content-issue-view-sectn-body"));
            if(!allText.get(0).getText().equals(text1) || !allText.get(1).getText().equals(text))
            {
                Assert.fail("More recently reported issue doesn't appear above an issue reported earlier.");
            }
            //TC row no. 21
            int resolveButton = driver.findElements(By.className("content-error-resolved-it-btn")).size();
            if(resolveButton == 0)
                Assert.fail("In Report Content Error pop-up Resolve button is absent.");
            int removedCount1 = driver.findElements(By.className("content-issue-view-sectn-body")).size();

            new ReportContentIssue().clickOnResolveButton(0);//click on Resolve button
            Thread.sleep(2000);
            //TC row no. 22
            int removedCount2 = driver.findElements(By.className("content-issue-view-sectn-body")).size();

            if(removedCount2 >=removedCount1)
                Assert.fail("On clicking resolve button In Report Content Error pop-up the content issue doesn't get deleted.");

            //TC row no. 23
            DBConnect.Connect();

            ResultSet contentStatus1 = DBConnect.st.executeQuery("SELECT error_status FROM t_content_error where comments='"+text1+"';");

            String contentStatus = "";
            while(contentStatus1.next())
            {
                contentStatus= contentStatus1.getString(1);
            }
            if(!contentStatus.equals("Closed"))
                Assert.fail("On clicking the \"Resolved\" button on any content issue comment, the content issue doesn't get marked as \"Closed\" in the database.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case authorShouldBeAbleToReportAnyContentErrors in class AuthorShouldBeAbleToReportAnyContentErrors.", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void reportCount()
    {
        try
        {

            new Assignment().create(24);

            new DBConnect().Connect();
            DBConnect.st.execute("delete from t_content_error where question_id= 915440 and error_status = 'Open';");
            new OpenAssignmentFromCMS().openAssignmentFromCMS("24");
            String text = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text);//post first content error report
            String reportCount = "";
            //TC row no. 24
            reportCount = driver.findElement(By.id("content-error-count")).getText();
            if(!reportCount.contains("1"))
                Assert.fail("The report count is not present in the icon after posting 1st report.");

            new ReportContentIssue().reportContentIssue(text); //post second content error report
            //TC row no. 25
            reportCount = driver.findElement(By.id("content-error-count")).getText();
            if(!reportCount.contains("2"))
                Assert.fail("The report count is not present in the icon after posting 2nd report.");

            new ReportContentIssue().clickOnReportContentIssueIcon();//click on report content issue
            Thread.sleep(2000);
            new ReportContentIssue().clickOnResolveButton(0);//click on Resolve button
            Thread.sleep(2000);
            //TC row no. 26
            reportCount = driver.findElement(By.id("content-error-count")).getText();
            if(!reportCount.contains("1"))
                Assert.fail("The report count doesn't changes when we click on resolve button.");
            //TC row no. 27, 28
            String icon = driver.findElement(By.id("content-error-count")).getCssValue("background-image");
            if(!icon.contains("notification_icon.png"))
                Assert.fail("The notification icon doesn't disappear after we resolve all the content error report..");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case reportCount in class AuthorShouldBeAbleToReportAnyContentErrors.", e);
        }
    }

}
