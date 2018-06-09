package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandFirstChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class AccessLessonFromAssessmentOrAssignment extends Driver
{
	@Test(priority=1,enabled=true)
	public void accesslessonfromassessmentorassignment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("2881");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new ExpandFirstChapter().expandFirstChapter();//expand first chapter
			String test1=ReadTestData.readDataByTagName("tocdata", "statictest", "1");//fetch 1st test to open	
			String topic1=ReadTestData.readDataByTagName("tocdata", "defaulttopictitle", "1");//fetch 1st test to open
			new TopicOpen().topicOpen(test1);//open static test of chapter 1
			driver.findElement(By.cssSelector("input[type='button']")).click();//click on submit button
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("input[type='button']")).click();//click on next button
			Thread.sleep(3000);
			Actions action = new Actions(driver);
			WebElement we= driver.findElement(By.cssSelector("span[class='question-association-skill-wrapper']"));
			action.moveToElement(we).build().perform();
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.cssSelector("span[class='toc-sprite-image']")));//click on learning objective text new tab
			driver.findElement(By.cssSelector("span[title='"+test1+"']")).click();//click on tab
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("span[class='question-association-skill-wrapper']")).click();//click on learning objective text
			String topictitle4=driver.findElement(By.cssSelector("div[class='tab active']")).getText();//fetch tab title after click on learning objective text
			if(!topictitle4.equals(topic1))
				Assert.fail("The specific lesson linked to the learning objective not open on a new tab. ");						
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method accesslessonfromassessmentorassignment in class  AccessLessonFromAssessmentOrAssignment",e);
		}
	}	

}
