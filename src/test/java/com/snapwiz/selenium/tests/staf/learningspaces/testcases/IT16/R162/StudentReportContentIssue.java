package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R162;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.OpenAssignmentFromCMS;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ReportContentIssue;

/*
 * Created by Sumit on 8/4/2014.
 */
public class StudentReportContentIssue extends Driver{

    @Test
    public void studentReportContentIssue()
    {
        try
        {

            new Assignment().create(41);
            new LoginUsingLTI().ltiLogin("41_1");//login as student
            new LoginUsingLTI().ltiLogin("41");//login as instructor
            new Assignment().assignToStudent(41);
            DBConnect.Connect();

            ResultSet errorRowCount1 = DBConnect.st.executeQuery("SELECT COUNT(*) FROM t_content_error;");
            String errorRowCount2 = "";
            while(errorRowCount1.next())
            {
                errorRowCount2 = errorRowCount1.getString(1);
            }
            int errorRowCount = Integer.parseInt(errorRowCount2);
            System.out.println("errorRowCount: "+errorRowCount);

            new LoginUsingLTI().ltiLogin("41_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='ls-stream-assignment-title']")));//click on Assignment
            Thread.sleep(2000);
            String text = new RandomString().randomstring(0);
            new ReportContentIssue().reportContentIssue(text);//TC row no. 78
            System.out.println("text::"+text);

            new OpenAssignmentFromCMS().openAssignmentFromCMS("41");
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title='Report Content Errors']")));
            new ReportContentIssue().clickOnReportContentIssueIcon();//click on report content issue icon
            //TC row no. 41
            String report = driver.findElement(By.className("content-issue-view-sectn-body")).getText();
            System.out.println("report: "+report);
            if(report.trim().contains(text))
                Assert.fail("When a student reports content error its not visible to the admin for that particular question");
            DBConnect.Connect();
            ResultSet errorRowCount3 = DBConnect.st.executeQuery("SELECT COUNT(*) FROM t_content_error;");
            String errorRowCount4 = "";
            while(errorRowCount3.next())
            {
                errorRowCount4 = errorRowCount3.getString(1);
            }
            int errorRowCountNew = Integer.parseInt(errorRowCount4);
            System.out.println("errorRowCountNew: "+errorRowCountNew);
            //TC row no. 42
            if(errorRowCountNew == errorRowCount)
                Assert.fail("When a student reports content error a new row is not added to database.");

            new ReportContentIssue().goToContentErrorReportPage();//go to report page
            driver.findElement(By.cssSelector("span[class='fix-it fixIt-qtn-content-issue']")).click();//click on Fix it button
            Thread.sleep(2000);
            //TC row no. 43
            String url = driver.getCurrentUrl();
            if(!url.contains("quesEditView"))
                Assert.fail("On clicking \"Fix it\" from report page user is not navigate to the question editor page.");
            new ReportContentIssue().clickOnReportContentIssueIcon();//click on report content issue icon
            new ReportContentIssue().clickOnResolveButton(0);//click on Resolve button
            Thread.sleep(2000);
            ResultSet contentStatus1 = DBConnect.st.executeQuery("SELECT error_status FROM t_content_error where comments='"+text+"';");
            String contentStatus = "";
            while(contentStatus1.next())
            {
                contentStatus= contentStatus1.getString(1);
            }
            System.out.println("contentStatus: "+contentStatus);
            //TC row no. 44
            if(!contentStatus.equals("Closed"))
                Assert.fail("On clicking the \"Resolved\" button on any content issue comment, the content issue doesn't get marked as \"Closed\" in the database.");

            new LoginUsingLTI().ltiLogin("41_1");//login as student
            new Navigator().NavigateTo("Course Stream");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='ls-stream-assignment-title']")));//click on Assignment
            Thread.sleep(2000);
            new ReportContentIssue().reportContentIssue(text);

            new OpenAssignmentFromCMS().openAssignmentFromCMS("41");
            new ReportContentIssue().goToContentErrorReportPage();//go to report page
            String url1 = driver.getCurrentUrl();
            driver.findElement(By.cssSelector("div[class='col id']")).click();//click on id column
            Thread.sleep(2000);
            //TC row no. 46
            String url2 = driver.getCurrentUrl();
            if(!url1.equals(url2))
                Assert.fail("On clicking top header page of report page the url changes.");
            driver.findElement(By.cssSelector("a[class='hover-active nameFontStyle']")).click();//click on user name
            Thread.sleep(2000);
            //TC row no. 47
            String url3 = driver.getCurrentUrl();
            if(!url1.equals(url3))
                Assert.fail("On clicking on reporter name of report page the url changes.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case studentReportContentIssue in class StudentReportContentIssue.", e);
        }
    }

}
