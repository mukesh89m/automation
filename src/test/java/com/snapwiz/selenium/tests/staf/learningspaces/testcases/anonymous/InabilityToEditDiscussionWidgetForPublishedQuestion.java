package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;

public class InabilityToEditDiscussionWidgetForPublishedQuestion extends Driver {
	@Test
	public void inabilityToEditDiscussionWidgetForPublishedQuestion()
	{
		try
		{
			new Widget().createChapterWidget(2123);		//create widget and publish
			List<WebElement> alltabs = driver.findElements(By.cssSelector("span[class='publisher-count discussion-widget-publisherIcons-bg discussion-widget-publisher-count-bg-holder']"));
			if(alltabs.size() != 0)			//tabs are absent so edit option will not be there
				Assert.fail("We are able to edit a discussion widget in published lesson.");

		}
		catch(Exception e)
		{
			Assert.fail("Exception in inabilityToEditDiscussionWidgetForPublishedQuestion in InabilityToEditDiscussionWidgetForPublishedQuestion class.",e);
		}
	}

}
