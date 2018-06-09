package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1413;

import java.awt.*;
import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class PostDiscussionsInLessonPage extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void postDiscussionsInLessonPageByHighlightingText()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("25_1");		//login a student
			new LoginUsingLTI().ltiLogin("25");		//login a instructor
            new SetSocialPolicy().setSocialPolicy("25", "two");

			new LoginUsingLTI().ltiLogin("25_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();


			WebElement element = (new WebDriverWait(driver,120)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p[class='nonindent default']")));
			Actions actions = new Actions(driver);
			actions.moveToElement(element, 60, 60)
					.clickAndHold()
					.moveByOffset(100, 100)
					.release()
					.perform();
			List<WebElement> allColors = driver.findElements(By.xpath("//*[starts-with(@class, 'pcolor discussioncolor')]"));
			allColors.get(0).click();
			Thread.sleep(2000);
			//TC row no. 25
			List<WebElement> socialPolicy = driver.findElements(By.className("social-policy-configuration"));
			if(socialPolicy.size() != 4)	//there will be 4 including the element on pop-up
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On adding discussion by selecting text from eTextBook the visual indicator icon is absent in the pop-up.");
			}
			WebElement we = driver.findElement(By.xpath(".//*[@id='pagecontext-INTERIM']/div[1]/div"));
			
			new MouseHover().mouserhoverbywebelement(we);
			Thread.sleep(2000);
			//TC row no. 27
			if(!we.getCssValue("background-position").contains("23px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On hovering over the visual indicator icon over discussion pop-up the color doesnt become blue.");
			}
			String discussionText = new RandomString().randomstring(15);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
			driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).clear();
			driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).sendKeys(discussionText);
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click();//click submit
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[starts-with(@class, 'highlighted annotation-')]")).click();
			Thread.sleep(2000);
			List<WebElement> socialPolicy1 = driver.findElements(By.className("social-policy-configuration"));
			//TC row no. 28
			if(socialPolicy1.size() != 5)	//there will be 5 including the element on pop-up
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On editing discussion by selecting text from eTextBook the visual indicator icon is absent in the pop-up.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase postDiscussionsInLessonPageByHighlightingText in class PostDiscussionsInLessonPage.",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void postDiscussionsInLessonPage()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("31_1");		//login a student
			new LoginUsingLTI().ltiLogin("31");		//login a instructor
			new Navigator().navigateFromProfileDropDown("Settings");	//navigate to Settings page from profile dropdown
			driver.findElement(By.cssSelector("label[for='two']")).click(); //Select Option-2 in the settings page
			driver.findElement(By.id("settings-save")).click();	//click on Save button
			
			new LoginUsingLTI().ltiLogin("31_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("+ New Discussion")));	//click on +New Discussion
			Thread.sleep(2000);
			List<WebElement> socialPolicy = driver.findElements(By.className("social-policy-configuration"));
			//TC row no. 31
			if(socialPolicy.size() != 4)	//there will be 4 including the element on pop-up
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On adding discussion the visual indicator icon is absent in the pop-up.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
			driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).clear();
			String discussionText = new RandomString().randomstring(15);
			driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).sendKeys(discussionText);
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")));//click submit
			Thread.sleep(2000);
			List<WebElement> allElement = driver.findElements(By.className("stream-content-posts-list"));	//list all discussion
			allElement.get(0).click();	//click on a discussion
			Thread.sleep(2000);
			List<WebElement> socialPolicy1 = driver.findElements(By.className("social-policy-configuration"));
			//TC row no. 34
			if(socialPolicy1.size() != 5)	//there will be 5 including the element on pop-up
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On editing discussion the visual indicator icon is absent in the pop-up.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase postDiscussionsInLessonPage in class PostDiscussionsInLessonPage.",e);
		}
	}
}
