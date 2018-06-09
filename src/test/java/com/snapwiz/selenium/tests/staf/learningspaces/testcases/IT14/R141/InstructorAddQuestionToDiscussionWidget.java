package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class InstructorAddQuestionToDiscussionWidget extends Driver{
	
	@Test
	public void instructorAddQuestionToDiscussionWidget()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("6");//login a student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			int tabs = driver.findElements(By.cssSelector("span[class='publisher-count ls-publisherIcons-bg ls-publisher-count-bg-holder']")).size();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.ls-discussion-widget-publisherIcons-bg.ls-discussion-widget-publisher-addCount-bg")));
			Thread.sleep(2000);
			//TC row no. 13
			int tabs1 = driver.findElements(By.cssSelector("span[class='publisher-count ls-publisherIcons-bg ls-publisher-count-bg-holder']")).size();
			if(tabs+1 != tabs1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor not able to add tab for discussion widget.");
			}
			for (int i = 5 ; i>tabs1; i--)
			{
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.ls-discussion-widget-publisherIcons-bg.ls-discussion-widget-publisher-addCount-bg")));
				Thread.sleep(2000);
			}
			//TC row no. 16
			List<WebElement> widgetdefaulttext = driver.findElements(By.cssSelector("div[class='widget-content']"));
			if(!widgetdefaulttext.get(widgetdefaulttext.size()-1).getText().contains("This is sample text. Please enter content here..."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Default text is not displayed over newly added text.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", widgetdefaulttext.get(widgetdefaulttext.size()-1));
			Thread.sleep(3000);
			String str = new RandomString().randomstring(25);
			WebElement t=driver.findElement(By.className("text-iframe"));
			driver.switchTo().frame(t);
            Thread.sleep(3000);
			driver.switchTo().activeElement().sendKeys(str);
			driver.switchTo().defaultContent();
			new Click().clickByclassname("cancel-discussion-widget-question"); //click on cancel
			//TC row no. 17, 18, 20, 21
			if(!widgetdefaulttext.get(widgetdefaulttext.size()-1).getText().contains("This is sample text. Please enter content here..."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("If we add text in a tab and click cancel then also it gets saved.");
			}
			//TC row no. 14
			int maxTabs = driver.findElements(By.cssSelector("span.ls-discussion-widget-publisherIcons-bg.ls-discussion-widget-publisher-addCount-bg")).size();
			if(maxTabs != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor able to add more than 5 tabs for discussion widget.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", widgetdefaulttext.get(widgetdefaulttext.size()-1));//Clicking on default text of the discussion widget
			WebElement t1 = driver.findElement(By.className("text-iframe"));
			driver.switchTo().frame(t1) ;
            Thread.sleep(3000);
			driver.switchTo().activeElement().sendKeys(str);
			driver.switchTo().defaultContent();
			Thread.sleep(3000);
			List<WebElement> saveButtons = driver.findElements(By.id("save-discussion-widget-question-text"));
			for(WebElement button : saveButtons)
			{
				if(button.isDisplayed() == true)
				{
					button.click();//click on Save button
					Thread.sleep(2000);
				}
			}
            List<WebElement> tab=driver.findElements(By.cssSelector("span[class='publisher-count ls-publisherIcons-bg ls-publisher-count-bg-holder']"));
			tab.get(4).click();
			List<WebElement> widgetdefaulttext1 = driver.findElements(By.cssSelector("div[class='widget-content']"));
			if(!widgetdefaulttext1.get(widgetdefaulttext1.size()-1).getText().contains(str))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor edits a tab and saves then tab doesnt saves.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase instructorAddQuestionToDiscussionWidget in class InstructorAddQuestionToDiscussionWidget.",e);
		}
	}

}
