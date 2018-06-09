package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;

public class AbilityOfTextEditorToAddQuestionText extends Driver{
	@Test
	public void abilityOfTextEditorToAddQuestionText()
	{
		try
		{
			startDriver("firefox");
			new Widget().createWidgetAtTopicLevel(1999);
			//verify the enable button in 1st tab
			int enableButton = driver.findElements(By.cssSelector("div[class='select-question discussion-widget-publisherIcons-bg selected']")).size();
			if(enableButton != 1)
				Assert.fail("icon to enable the question in absent in the first question tab.");
			//check the option is enable, and green in color
			String isEnabled = driver.findElement(By.cssSelector("div[class='select-question discussion-widget-publisherIcons-bg selected']")).getAttribute("title");
			if(!isEnabled.equals(""))
				Assert.fail("The question added is not enabled.");
			//click on default text
			List<WebElement> widgetdefaulttext = driver.findElements(By.className("widget-content"));
			widgetdefaulttext.get(3).click();
			Thread.sleep(3000);
			int textEditorSize = driver.findElements(By.cssSelector("div[class='text-editor-buttons ui-draggable']")).size();
			if(textEditorSize == 0)
				Assert.fail("Text editor doesn't open after clicking on the text field.");
			
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
			alltabs.get(1).click();		//click outside the textbox(clicked on tab)
			Thread.sleep(3000);
			//verify the enable button in 2nd tab
			int enableButton1 = driver.findElements(By.cssSelector("div[class='select-question discussion-widget-publisherIcons-bg']")).size();
			if(enableButton1 != 1)
				Assert.fail("icon to enable the question in absent in the newly created tab.");
			//verify the Tooltip for icon should say "Enable"
			String enbleText = driver.findElement(By.cssSelector("div[class='select-question discussion-widget-publisherIcons-bg']")).getAttribute("title");
			if(!enbleText.equals("Enable"))
				Assert.fail("Tooltip for icon should doesn't say \"Enable\".");
			List<WebElement> allWidgetContent = driver.findElements(By.className("tab-content-data"));
			String textOnTab2 = allWidgetContent.get(1).getText();
			if(!textOnTab2.equals("TEXT"))
				Assert.fail("On clicking outside the text box the text is not getting added.");
			if(!textOnTab2.equals("TEXT"))
				Assert.fail("HTML editor options doesn't work over question text.");
		    //click on the enable icon
			driver.findElement(By.cssSelector("div[class='select-question discussion-widget-publisherIcons-bg']")).click();
			Thread.sleep(3000);
			String iconEnableText = driver.findElement(By.cssSelector("div[class='select-question discussion-widget-publisherIcons-bg selected']")).getAttribute("title");
			if(!iconEnableText.equals(""))
				Assert.fail("On clicking the enble icon on the 2nd tab the icon doesn't become disabled.");
			new Widget().navigateToDiscussionTab(0);			//goto 1st tab
			String iconEnableText1 = driver.findElement(By.cssSelector("div[class='select-question discussion-widget-publisherIcons-bg']")).getAttribute("title");
			if(!iconEnableText1.equals("Enable"))
				Assert.fail("After enabling the 2nd tab question the 1st tab Tooltip for icon should doesn't say \"Enable\".");
			//add 1 more tab
			driver.findElement(By.cssSelector("span[class='discussion-widget-publisherIcons-bg discussion-widget-publisher-addCount-bg']")).click();
			Thread.sleep(3000);
			//list all the close icons in the tabs
			List<WebElement> allCloseIcon = driver.findElements(By.className("close_tab"));
			int numberOfCloseIcon = allCloseIcon.size();
			allCloseIcon.get(numberOfCloseIcon-1).click();		//click on close icon for last tab(here the 3rd tab)
			Thread.sleep(3000);
			List<WebElement> alltabs1 = driver.findElements(By.cssSelector("span[class='publisher-count discussion-widget-publisherIcons-bg discussion-widget-publisher-count-bg-holder']"));
			if(alltabs1.size() != 2)
				Assert.fail("On Clicking the close icon the tab doesn't get closed.");
			new Widget().navigateToDiscussionTab(0);	//goto 1st tab
			allCloseIcon.get(0).click();		//close the 1st tab
			int allCloseIcon1 = driver.findElements(By.className("close_tab")).size();
			if(allCloseIcon1 != 0)
				Assert.fail("Close symbol is present in the enabled tab.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in abilityOfTextEditorToAddQuestionText in AbilityOfTextEditorToAddQuestionText class.",e);
		}
	}

}
