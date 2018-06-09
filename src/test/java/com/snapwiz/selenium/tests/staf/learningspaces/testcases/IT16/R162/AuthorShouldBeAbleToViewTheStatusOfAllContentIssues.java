package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R162;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.OpenAssignmentFromCMS;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ReportContentIssue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by Sumit on 8/4/2014.
 */
public class AuthorShouldBeAbleToViewTheStatusOfAllContentIssues extends Driver{

    @Test
    public void authorShouldBeAbleToViewTheStatusOfAllContentIssues()
    {
        try
        {

            new Assignment().create(31);

            new OpenAssignmentFromCMS().openAssignmentFromCMS("31");
            String text = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text);
            new ReportContentIssue().goToContentErrorReportPage();//go to report page
            //TC row no. 31
            String columnNames = driver.findElement(By.id("reported-content-issue-col-names")).getText();
            if(!columnNames.contains("Id"))
                Assert.fail("Report page doesn't have the Id column.");
            if(!columnNames.contains("Content Type"))
                Assert.fail("Report page doesn't have the Content Type column.");
            if(!columnNames.contains("User Type"))
                Assert.fail("Report page doesn't have the User Type column.");
            if(!columnNames.contains("Reported By"))
                Assert.fail("Report page doesn't have the Reported By column.");
            if(!columnNames.contains("Reported Date"))
                Assert.fail("Report page doesn't have the Reported Date column.");
            if(!columnNames.contains("Comments"))
                Assert.fail("Report page doesn't have the Comments column.");
            if(!columnNames.contains("Status"))
                Assert.fail("Report page doesn't have the Status column.");
            if(!columnNames.contains("Action"))
                Assert.fail("Report page doesn't have the Action column.");

            int count = 0;
            List<WebElement> closeElementList = driver.findElements(By.cssSelector("div[class='col status']"));
            for(WebElement closeElement : closeElementList)
            {
                if(closeElement.getText().equals("Closed"))
                {
                    count++;//count the closed status
                }
            }
            driver.findElement(By.cssSelector("span[class='fix-it fixIt-qtn-content-issue']")).click();//click on Fix it button
            Thread.sleep(2000);
            //TC row no. 33, 37
            String url = driver.getCurrentUrl();
            if(!url.contains("quesEditView"))
                Assert.fail("On clicking \"Fix it\" from report page user is not navigate to the question editor page.");
            new ReportContentIssue().clickOnReportContentIssueIcon();//click on report content issue icon
            Thread.sleep(2000);
            //TC row no. 34, 38
            String contentIssue = driver.findElement(By.className("content-issue-view-sectn-body")).getText();
            if(!contentIssue.contains(text))
                Assert.fail("On clicking \"Fix it\" from report page the content issue is not displayed in the content error pop-up.");

            new ReportContentIssue().clickOnResolveButton(0);//click on Resolve button
            Thread.sleep(2000);
            new ReportContentIssue().goToContentErrorReportPage();//go to report page
            int count1 = 0;
            List<WebElement> closeElementList1 = driver.findElements(By.cssSelector("div[class='col status']"));
            for(WebElement closeElement : closeElementList1)
            {
                if(closeElement.getText().equals("Closed"))
                {
                    count1++;//count the closed status
                }
            }
            //TC row no. 35, 39
            if(count1-1 != count)   //if close count remains same then its a fail
                Assert.fail("On clicking Resolve from content error pop-up the status does not changes to \"Closed\" in report page.");
            //TC row no. 36
            String textShown = driver.findElement(By.className("reported-content-issue-cmnts")).getText();
            if(!textShown.contains(text))
                Assert.fail("On clicking \"Resolve\" from content error pop-up the issue is not displayed in report page.");
            //TC row no. 40
            driver.findElement(By.className("resolved-ce-issue")).click();
            Thread.sleep(2000);
            String url1 = driver.getCurrentUrl();
            if(url1.contains("quesEditView"))//if url changes then its a fail, means the button is enabled
                Assert.fail("After the content issue is resolved, \"Resolved\" button is not disabled.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case authorShouldBeAbleToViewTheStatusOfAllContentIssues in class AuthorShouldBeAbleToViewTheStatusOfAllContentIssues.", e);
        }
    }

}
