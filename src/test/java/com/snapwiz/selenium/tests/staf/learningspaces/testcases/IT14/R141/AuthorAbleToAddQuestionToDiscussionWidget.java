package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;

public class AuthorAbleToAddQuestionToDiscussionWidget extends Driver {

	@Test
	public void authorAbleToAddQuestionToDiscussionWidget()
	{
		try
		{
			new Assignment().createChapter(2);
			new Widget().createDiscussionWidget(2);
			new LoginUsingLTI().ltiLogin("2");//login a student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TopicOpen().openLastChapter();
			List<WebElement> allElements = driver.findElements(By.cssSelector("a[data-type='lesson']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allElements.get(allElements.size()-1));
			// TC row no. 4
			String discussion = driver.findElement(By.cssSelector("span[class='ls-dialog-tag ls-dialog-quote ls-dialog-tag-open']")).getText();
			if(!discussion.equals("Discuss:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Discussion widget is not available to the student.");
			}
			new LoginUsingLTI().ltiLogin("5");//login a instructor
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TopicOpen().openLastChapter();
			List<WebElement> allElements1 = driver.findElements(By.cssSelector("a[data-type='lesson']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allElements1.get(allElements1.size()-1));
			// TC row no. 5
			int discussion1 = driver.findElements(By.cssSelector("div[class='widget discussion-widget ls-publisher-tabs-ins-border']")).size();
			if(discussion1 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Discussion widget is not available to the instructor.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase authorAbleToAddQuestionToDiscussionWidget in class AuthorAbleToAddQuestionToDiscussionWidget.",e);		}
		}

}
