package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R162;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ReportAnyContentError extends Driver{

    @Test
    public void reportAnyContentError()
    {
        try
        {

            new Assignment().create(2);
            new OpenAssignmentFromCMS().openAssignmentFromCMS("2");
            Thread.sleep(3000);
            //TC row no. 2
            String reportIcon = driver.findElement(By.cssSelector("div[class='add-content-error show-content-issues-dialog']")).getCssValue("background-image");
            if(!reportIcon.contains("plus_icon.png"))
                Assert.fail("In footer the Report Content Error symbol is absent.");
            //TC row no. 2
            String tooltip = driver.findElement(By.cssSelector("div[class='add-content-error show-content-issues-dialog']")).getAttribute("title");
            if(!tooltip.contains("Report Content Errors"))
                Assert.fail("In footer the \"Report Content Error\" tooltip is absent.");
            new ReportContentIssue().clickOnReportContentIssueIcon();//click on Report icon
            Thread.sleep(3000);
            //TC row no. 4
            String header = driver.findElement(By.className("content-issue-header-text")).getText();
            if(!header.contains("Report Content Issue"))
                Assert.fail("On clicking report icon the pop-up don't have the header \"Report a Content Issue\".");
            //TC row no. 4
            String closeIcon = driver.findElement(By.id("close-content-issue-dialog")).getCssValue("background-image");
            if(!closeIcon.contains("delete.png"))
                Assert.fail("In Report Content Error pop-up close icon is absent.");
            //TC row no. 4
            int sendButton = driver.findElements(By.id("send-content-issue-btn")).size();
            if(sendButton == 0)
                Assert.fail("In Report Content Error pop-up send button is absent.");
            //TC row no. 4
            String defaultText = driver.findElement(By.id("text-area-content-issue")).getText();
            if(defaultText.contains("Enter content issue..."))
                Assert.fail("In Report Content Error pop-up the default text \"Enter content issue...\" is absent.");
            driver.findElement(By.id("close-content-issue-dialog")).click();//click on Close icon
            Thread.sleep(2000);
            //TC row no. 7
            int popUp = driver.findElements(By.id("content-issue-dialog")).size();
            if(popUp != 0)
                Assert.fail("After clicking on close icon the Report Content Error pop-up doesn't disappear.");
            String text = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text);
            //TC row no. 5, 6
            int popUp1 = driver.findElements(By.id("content-issue-dialog")).size();
            if(popUp1 != 0)
                Assert.fail("After clicking on send button the Report Content Error pop-up doesn't disappear.");
            boolean emailReceived;
            //TC row no. 10
            emailReceived = new ValidateEmail().validateEmail(2 ,text);    //login to email and verify
            if(emailReceived == false)
                Assert.fail("On reporting a content issue by author email is not sent to the configured email id.");


            new OpenAssignmentFromCMS().openAssignmentFromCMS("2");
            new ReportContentIssue().clickOnReportContentIssueIcon();//click on report content issue
            Thread.sleep(2000);
            driver.findElement(By.id("text-area-content-issue")).click();//click on report content issue text area
            //TC row no. 9
            String defaultText1 = driver.findElement(By.id("text-area-content-issue")).getText();
            if(defaultText1.contains("Enter content issue..."))
                Assert.fail("On clicking inside the report content issue textarea the default text doesn't get removed.");
            driver.findElement(By.xpath("/html/body")).click();//click Outside
            Thread.sleep(2000);
            //TC row no. 8
            int popUp2 = driver.findElements(By.id("content-issue-dialog")).size();
            if(popUp2 != 0)
                Assert.fail("After clicking outside the Report Content Error pop-up doesn't disappear.");

            new OpenAssignmentFromCMS().openAssignmentFromCMS("2");
            new ReportContentIssue().clickOnReportContentIssueIcon();//click on report content issue
            driver.findElement(By.id("send-content-issue-btn")).click();//click on report content issue send button
            Thread.sleep(3000);
            //TC row no. 11
            emailReceived = new ValidateEmail().validateEmail(2,text);    //login to email and verify
            if(emailReceived == true)
                Assert.fail("On clicking send button without entering content by author, then email is still sent to the configured email id.");

            new OpenAssignmentFromCMS().openAssignmentFromCMS("2");
            String text1 = new RandomString().randomstring(10);
            new ReportContentIssue().reportContentIssue(text1);
            new Gmail().login(Config.suppportEmail, Config.suppportEmailPassword);//log in to gmail
            driver.findElement(By.className("yW")).click();//open the email
            Thread.sleep(3000);
            //TC row no. 12
            String subject = driver.findElement(By.className("ha")).getText(); //get the subject of email
            if(!subject.contains("Content issue reported by Internal User"))
                Assert.fail("Subject of the email is not of this format: \"Content issue reported by <Role> \".");

            String details = driver.findElement(By.className("a3s")).getText(); //get the content of email
            if(!details.contains(text1))
                Assert.fail("User comments is absent in report content issue email .");

            //if(!details.contains("http://localhost:8080/secure/viewReportedContentIssue?ceId="))
            if(!details.contains("http://10.0.0.240:8080/secure/viewReportedContentIssue?ceId="))

                Assert.fail("Content Issue Reported for <URL> is absent in report content issue email .");

            if(!details.contains("Below is the content and user information:"))
                Assert.fail("\"Below is the content and user information:\" text is absent in report content issue email .");

            if(!details.contains("Course Name"))
                Assert.fail("\"Course Name\" label is absent in report content issue email .");

            if(!details.contains(Config.course))
                Assert.fail("Course Name is absent in report content issue email .");

            if(!details.contains("Product ID"))
                Assert.fail("\"Product ID\" label is absent in report content issue email .");

            if(!details.contains(Config.courseid))
                Assert.fail("Product ID is absent in report content issue email .");

            if(!details.contains("Author Name"))
                Assert.fail("\"Author Name\" label is absent in report content issue email .");

            if(!details.contains("Wiley"))
                Assert.fail("Author Name is absent in report content issue email .");

            if(!details.contains("Chapter/Section/Subsection Name"))
                Assert.fail("\"Chapter/Section/Subsection Name\" label is absent in report content issue email .");

            if(!details.contains("Question Number/Lesson Number"))
                Assert.fail("\"Question Number/Lesson Number\" label is absent in report content issue email .");

            if(!details.contains("Question Id"))
                Assert.fail("Question Id is absent in report content issue email .");

            if(!details.contains("User Name"))
                Assert.fail("\"User Name\" label is absent in report content issue email .");

            if(!details.contains("Spaces, learning"))
                Assert.fail("User Name is absent in report content issue email .");

            if(!details.contains("User Role"))
                Assert.fail("\"User Role\" label is absent in report content issue email .");

            if(!details.contains("Internal User"))
                Assert.fail("Internal User is absent in report content issue email .");

            if(!details.contains("User Id"))
                Assert.fail("\"User Id\" label is absent in report content issue email .");

            if(!details.contains("Email Id"))
                Assert.fail("\"Email Id\" label is absent in report content issue email .");

            if(!details.contains(Config.cmsUserName))
                Assert.fail("Email Id is absent in report content issue email .");

            if(!details.contains("Class"))
                Assert.fail("\"Class\" label is absent in report content issue email .");

            if(!details.contains("ClassSection"))
                Assert.fail("\"ClassSection\" label is absent in report content issue email .");

            List<WebElement> allDelete = driver.findElements(By.cssSelector("div[class='ar9 T-I-J3 J-J5-Ji']"));
            allDelete.get(1).click();//click on Delete
            Thread.sleep(2000);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case reportAnyContentError in class ReportAnyContentError.", e);
        }
    }

}
