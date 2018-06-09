package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;

public class MaximumTabLimit extends Driver {
@Test
	public void maximumTabLimit()
	{
		try
		{
			//create discussion widget with 3 tabs
			new Widget().createWidgetAtTopicLevel(2099);
			//add two more tabs
			driver.findElement(By.cssSelector("span[class='discussion-widget-publisherIcons-bg discussion-widget-publisher-addCount-bg']")).click();
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("span[class='discussion-widget-publisherIcons-bg discussion-widget-publisher-addCount-bg']")).click();
			Thread.sleep(3000);
			//verify + icon to add tab
			int plusIcon = driver.findElements(By.cssSelector("span[class='discussion-widget-publisherIcons-bg discussion-widget-publisher-addCount-bg']")).size();
			if(plusIcon != 0)
				 Assert.fail(" + icon is  available at the end of the tab after adding Five tabs.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in maximumTabLimit in MaximumTabLimit class.",e);
		}
	}

}
