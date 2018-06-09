package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1413;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.StaticAssignmentSubmit;

public class FinishStaticTestAndAddDiscussion extends Driver{
	
	@Test
	public void finishStaticTestAndAddDiscussion()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("56_1");		//login a student
			new LoginUsingLTI().ltiLogin("56");		//login a instructor
			new Navigator().navigateFromProfileDropDown("Settings");	//navigate to Settings page from profile dropdown
			driver.findElement(By.cssSelector("label[for='two']")).click(); //Select Option-2 in the settings page
			driver.findElement(By.id("settings-save")).click();	//click on Save button

			new LoginUsingLTI().ltiLogin("56_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.linkText("1.1 Concept Check")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[data-type='static_assessment']")));	//click on static assessment
            new WebDriverWait(driver,120).until(ExpectedConditions.visibilityOfElementLocated(By.className("ls-static-practice-test-submit-button")));
		    driver.findElement(By.className("ls-static-practice-test-submit-button")).click();//click on submit
			Thread.sleep(3000);
			new Navigator().navigateToTab("Discussion"); //navigate to Discussion tab
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("+ New Discussion")));	//click on +New Discussion
			Thread.sleep(2000);
			List<WebElement> socialPolicy = driver.findElements(By.className("social-policy-configuration"));
			//TC row no. 56
			if(socialPolicy.size() != 3)	//there will be 3 including the element on pop-up
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On adding discussion from discussion tab for static assessment the visual indicator icon is absent in the pop-up.");
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
			//TC row no. 59
			if(socialPolicy1.size() != 4)	//there will be 4 including the element on pop-up
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On editing discussion from discussion tab for static assessment the visual indicator icon is absent in the pop-up.");
			}
			driver.findElement(By.cssSelector("div[class='ls-static-practice-test-next-button']")).click();//click on next button
			Thread.sleep(2000);
			new StaticAssignmentSubmit().staticAssesment();//submit the static assessment
			driver.findElement(By.className("question-card-question-details")).click();//click on questionm card
			Thread.sleep(2000);
			new Navigator().navigateToTab("Discussion"); //navigate to Discussion tab
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("+ New Discussion")));	//click on +New Discussion
			Thread.sleep(2000);
			List<WebElement> socialPolicy2 = driver.findElements(By.className("social-policy-configuration"));
			//TC row no. 68 
			if(socialPolicy2.size() != 3)	//there will be 3 including the element on pop-up
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On adding discussion from performance summary page for static assessment the visual indicator icon is absent in the pop-up.");
			}

			driver.findElement(By.cssSelector("inline[class='redactor_placeholder']")).click();
			driver.findElement(By.xpath("//div[starts-with(@id,'editdiscussion-text')]")).clear();
			String discussionText2 = new RandomString().randomstring(15);
			driver.findElement(By.xpath("//div[starts-with(@id,'editdiscussion-text')]")).sendKeys(discussionText2);
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click();	//click submit
			Thread.sleep(2000);
			List<WebElement> allElement1 = driver.findElements(By.className("stream-content-posts-list"));	//list all discussion
			allElement1.get(0).click();	//click on a discussion
			Thread.sleep(2000);
			List<WebElement> socialPolicy3 = driver.findElements(By.className("social-policy-configuration"));
			//TC row no. 71
			if(socialPolicy3.size() != 4)	//there will be 4 including the element on pop-up
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On editing discussion from performance summary page for static assessment the visual indicator icon is absent in the pop-up.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase finishStaticTestAndAddDiscussion in class FinishStaticTestAndAddDiscussion.",e);
		}
	}
}
