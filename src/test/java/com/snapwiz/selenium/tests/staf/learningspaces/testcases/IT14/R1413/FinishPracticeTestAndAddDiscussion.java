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
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;

public class FinishPracticeTestAndAddDiscussion extends Driver {
	
	@Test(priority = 1, enabled = true)
	public  void finishPracticeTestAndAddDiscussion() {
		try
		{
			String assignmentname = ReadTestData.readDataByTagName("", "assignmentname", "45");
			new LoginUsingLTI().ltiLogin("45_1");		//login a student
			new LoginUsingLTI().ltiLogin("45");		//login a instructor
			new Navigator().navigateFromProfileDropDown("Settings");	//navigate to Settings page from profile dropdown
			driver.findElement(By.cssSelector("label[for='two']")).click(); //Select Option-2 in the settings page
			driver.findElement(By.id("settings-save")).click();	//click on Save button
			
			new LoginUsingLTI().ltiLogin("45_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new DiagnosticTest().startTest(4);
			new DiagnosticTest().attemptAllCorrect(0, false, false);
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new Navigator().NavigateToOrion();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("practice-test-button")));	//click on Adaptive Practice
			Thread.sleep(3000);
			driver.findElement(By.id("submit-practice-question-button")).click();//click on submit
			Thread.sleep(3000);
			driver.findElement(By.id("submit-practice-question-button")).click();//click on submit
			Thread.sleep(3000);
			new Navigator().navigateToTab("Discussion"); //navigate to Discussion tab
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("+ New Discussion")));	//click on +New Discussion
			Thread.sleep(2000);
			List<WebElement> socialPolicy = driver.findElements(By.className("social-policy-configuration"));
			//TC row no. 45
			if(socialPolicy.size() != 3)	//there will be 3 including the element on pop-up
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On adding discussion from discussion tab for practice test the visual indicator icon is absent in the pop-up.");
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
			//TC row no. 48
			if(socialPolicy1.size() != 4)	//there will be 4 including the element on pop-up
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On editing discussion from discussion tab for practice test the visual indicator icon is absent in the pop-up.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase finishPracticeTestAndAddDiscussion in class FinishPracticeTestAndAddDiscussion.",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public  void finishTLOLevelPracticeTestAndAddDiscussion() {
		try
		{
			String assignmentname = ReadTestData.readDataByTagName("", "assignmentname", "51");


			new LoginUsingLTI().ltiLogin("51_1");		//login a student
			new LoginUsingLTI().ltiLogin("51");		//login a instructor
			new Navigator().navigateFromProfileDropDown("Settings");	//navigate to Settings page from profile dropdown
			driver.findElement(By.cssSelector("label[for='two']")).click(); //Select Option-2 in the settings page
			driver.findElement(By.id("settings-save")).click();	//click on Save button


		    new LoginUsingLTI().ltiLogin("51_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new DiagnosticTest().startTest(4);
			new DiagnosticTest().attemptAllCorrect(0, false, false);
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
            new Navigator().NavigateToOrion();
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
			List<WebElement> allPracticeTest = driver.findElements(By.cssSelector("a[data-type='tlo_adaptive_assessment']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allPracticeTest.get(1));	//click on TLO level Adaptive Practice
			Thread.sleep(3000);
			driver.findElement(By.id("submit-practice-question-button")).click();//click on submit
			Thread.sleep(3000);
			driver.findElement(By.id("submit-practice-question-button")).click();//click on submit
			Thread.sleep(3000);
			new Navigator().navigateToTab("Discussion"); //navigate to Discussion tab
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("+ New Discussion")));	//click on +New Discussion
			Thread.sleep(2000);
			List<WebElement> socialPolicy = driver.findElements(By.className("social-policy-configuration"));
			//TC row no. 51
			if(socialPolicy.size() != 3)	//there will be 3 including the element on pop-up
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On adding discussion from discussion tab for TLO level practice test the visual indicator icon is absent in the pop-up.");
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
			//TC row no. 53
			if(socialPolicy1.size() != 4)	//there will be 4 including the element on pop-up
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On editing discussion from discussion tab for TLO level practice test the visual indicator icon is absent in the pop-up.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase finishTLOLevelPracticeTestAndAddDiscussion in class FinishPracticeTestAndAddDiscussion.",e);
		}
	}
}
