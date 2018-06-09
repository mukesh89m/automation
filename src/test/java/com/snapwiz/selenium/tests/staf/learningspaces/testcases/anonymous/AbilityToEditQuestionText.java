package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;

public class AbilityToEditQuestionText extends Driver {
	@Test
	public void abilityToEditQuestionText()
	{
		try
		{
			startDriver("firefox");
			new Widget().createWidgetAtTopicLevel(2120);
			//click on default text
			List<WebElement> widgetdefaulttext = driver.findElements(By.className("widget-content"));
			widgetdefaulttext.get(3).click();    //click on default text
			Thread.sleep(3000);
			String randomString = new RandomString().randomstring(3);
			//driver.findElement(By.cssSelector("div[class='tab-content-data editable']")).click();
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("div[class='tab-content-data editable']")).clear();
			driver.switchTo().activeElement().sendKeys(randomString);
			List<WebElement> alltabs = driver.findElements(By.cssSelector("span[class='publisher-count discussion-widget-publisherIcons-bg discussion-widget-publisher-count-bg-holder']"));
			alltabs.get(1).click();		//click outside the textbox(clicked on tab)
			Thread.sleep(3000);
			List<WebElement> widgetdefaulttext1 = driver.findElements(By.className("widget-content"));
			widgetdefaulttext1.get(3).click();		//click on text
			Thread.sleep(3000);
			driver.switchTo().activeElement().sendKeys(randomString);		//modify the question text
			List<WebElement> alltabs1 = driver.findElements(By.cssSelector("span[class='publisher-count discussion-widget-publisherIcons-bg discussion-widget-publisher-count-bg-holder']"));
			alltabs1.get(1).click();		//click outside the textbox(clicked on tab)
			Thread.sleep(3000);
			List<WebElement> allWidgetContent = driver.findElements(By.className("tab-content-data"));
			String str = allWidgetContent.get(1).getText().replaceAll("[\n\r]", "");
			System.out.println("-->"+str);
			System.out.println("-->"+randomString+randomString);
			if(!str.trim().equals(randomString+randomString))
				Assert.fail("Unable to modify a given a question text.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in abilityToEditQuestionText in AbilityToEditQuestionText class.",e);
		}
		
	}

}
