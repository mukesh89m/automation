package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;

public class ReflectionOfTextEditorInInstructorSite extends Driver{
	@Test
	public void reflectionOfTextEditorInInstructorSite()
	{
		try
		{
			String topicToOpen = ReadTestData.readDataByTagName("ReflectionOfTextEditorInInstructorSite", "topicToOpen", "2140");
			new Widget().createWidgetAtTopicLevel(2140);
			List<WebElement> widgetdefaulttext = driver.findElements(By.className("widget-content"));
			widgetdefaulttext.get(3).click();
			Thread.sleep(3000);
			//click on HTML text editor
			driver.findElement(By.cssSelector("#addrawhtml > #addrawhtml")).click();
			Thread.sleep(3000);
			//send HTML text
			driver.switchTo().activeElement().sendKeys("<html><head>TEXT</head></html>");
			Thread.sleep(3000);
			//click on save button
			driver.findElement(By.cssSelector("button[class='html-container-save ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")).click();
			Thread.sleep(3000);
			List<WebElement> alltabs = driver.findElements(By.cssSelector("span[class='publisher-count discussion-widget-publisherIcons-bg discussion-widget-publisher-count-bg-holder']"));
			alltabs.get(1).click();			//click outside the textbox(clicked on tab)
			Thread.sleep(3000);
			//click on the enable icon
			driver.findElement(By.cssSelector("div[class='select-question discussion-widget-publisherIcons-bg']")).click();
			Thread.sleep(3000);
			//publish the lesson
			new ComboBox().selectValue(3, "Publish"); //Publishing
			Thread.sleep(3000);
			driver.quit();
			startDriver("firefox");
			new LoginUsingLTI().ltiLogin("2140");		//login as instructor
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().topicOpen(topicToOpen);
			List<WebElement> allQuestion = driver.findElements(By.className("tab-content-data"));
			//check question text
			String question = allQuestion.get(1).getText();		//text are of 2nd tab so index 1
			if(!question.equals("TEXT"))
				Assert.fail("At instructor site the text editor options are not reflected.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in reflectionOfTextEditorInInstructorSite in ReflectionOfTextEditorInInstructorSite class.",e);
		}
	}

}
