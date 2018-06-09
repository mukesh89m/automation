package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1413;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class PostCommentForWidget extends Driver{

    @Test(priority = 1, enabled = true)
    public void postCommentForWidget()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("36_1");		//login a student
            new LoginUsingLTI().ltiLogin("36");		//login a instructor
            new Navigator().navigateFromProfileDropDown("Settings");	//navigate to Settings page from profile dropdown
            driver.findElement(By.cssSelector("label[for='two']")).click(); //Select Option-2 in the settings page
            driver.findElement(By.id("settings-save")).click();	//click on Save button

            new LoginUsingLTI().ltiLogin("36_1");		//login a student
            new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
            new TOCShow().tocHide();
            driver.findElement(By.id("ls-right-post-comment-link")).click();
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(driver.findElement(By.cssSelector("div[class='social-policy-configuration']")));
            List<WebElement> iconColor1 = driver.findElements(By.cssSelector("div[class='social-policy-configuration']"));
            //TC row no. 38
            if(!iconColor1.get(0).getCssValue("background-position").contains("23px"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("On mouse hovering over the visual indicator on the textbox area of widget the color of visual indicator doesnt changes.");
            }
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase postCommentForWidget in class PostCommentForWidget.",e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void postDiscussionInPerformanceSummaryPage()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("39_1");		//login a student
            new LoginUsingLTI().ltiLogin("39");		//login a instructor
            new Navigator().navigateFromProfileDropDown("Settings");	//navigate to Settings page from profile dropdown
            driver.findElement(By.cssSelector("label[for='two']")).click(); //Select Option-2 in the settings page
            driver.findElement(By.id("settings-save")).click();	//click on Save button

            new LoginUsingLTI().ltiLogin("39_1");		//login a student
            new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
            new DiagnosticTest().startTest(4);
            new DiagnosticTest().attemptAllCorrect(0, false, false);
            driver.findElement(By.className("question-card-question-details")).click();	//click on question from right side question card
            Thread.sleep(2000);
            new Navigator().navigateToTab("Discussion");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("+ New Discussion")));	//click on +New Discussion
            Thread.sleep(2000);
            List<WebElement> socialPolicy = driver.findElements(By.className("social-policy-configuration"));
            //TC row no. 39
            if(socialPolicy.size() != 3)	//there will be 3 including the element on pop-up
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("On adding discussion from performance summary page the visual indicator icon is absent in the pop-up.");
            }

            driver.findElement(By.cssSelector("inline[class='redactor_placeholder']")).click();
            driver.findElement(By.xpath("//div[starts-with(@id,'editdiscussion-text')]")).clear();
            String discussionText = new RandomString().randomstring(15);
            driver.findElement(By.xpath("//div[starts-with(@id,'editdiscussion-text')]")).sendKeys(discussionText);
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click();	//click submit
            Thread.sleep(2000);
            List<WebElement> allElement = driver.findElements(By.className("stream-content-posts-list"));	//list all discussion
            allElement.get(0).click();	//click on a discussion
            Thread.sleep(2000);
            List<WebElement> socialPolicy1 = driver.findElements(By.className("social-policy-configuration"));
            //TC row no. 42
            if(socialPolicy1.size() != 4)	//there will be 4 including the element on pop-up
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("On editing discussion from performance summary page the visual indicator icon is absent in the pop-up.");
            }

            new Navigator().NavigateTo("Proficiency Report");	//navigate to Proficiency Report
            driver.findElement(By.className("question-card-question-details")).click();	//click on question from right side question card
            Thread.sleep(2000);
            new Navigator().navigateToTab("Discussion");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("+ New Discussion")));	//click on +New Discussion
            Thread.sleep(2000);
            List<WebElement> socialPolicy2 = driver.findElements(By.className("social-policy-configuration"));
            //TC row no. 62
            if(socialPolicy2.size() != 1)	//there will be 1 including the element on pop-up
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("On adding discussion from proficiency report page the visual indicator icon is absent in the pop-up.");
            }
            driver.findElement(By.cssSelector("inline[class='redactor_placeholder']")).click();
            driver.findElement(By.xpath("//div[starts-with(@id,'editdiscussion-text')]")).clear();
            String discussionText1 = new RandomString().randomstring(15);
            driver.findElement(By.xpath("//div[starts-with(@id,'editdiscussion-text')]")).sendKeys(discussionText1);
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click();	//click submit
            Thread.sleep(2000);
            List<WebElement> allElement1 = driver.findElements(By.className("stream-content-posts-list"));	//list all discussion
            allElement1.get(0).click();	//click on a discussion
            Thread.sleep(2000);
            List<WebElement> socialPolicy3 = driver.findElements(By.className("social-policy-configuration"));
            //TC row no. 65
            if(socialPolicy3.size() != 2)	//there will be 2 including the element on pop-up
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("On editing discussion the visual indicator icon is absent in the pop-up.");
            }
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase postCommentForWidget in class PostCommentForWidget.",e);
        }
    }
}
