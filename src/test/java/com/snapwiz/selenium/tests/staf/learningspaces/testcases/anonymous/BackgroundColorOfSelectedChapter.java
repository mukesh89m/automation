package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandCollapseChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCVerify;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class BackgroundColorOfSelectedChapter extends Driver {
	public static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.BackgroundColorChangeWhenHoverChapter");

	/*
	 * 682-689
	 */
	@Test
	public void BackgroungColorofChapterAfterClick()
	{
		try
		{
		    new LoginUsingLTI().ltiLogin("690");
		    new Navigator().NavigateTo("eTextbook");		   
		    new TOCVerify().tocChapterVerify(1);	
		    new ExpandCollapseChapter().expandChapter(1);
		    Thread.sleep(3000);
		    String defaultcolor;
		    if(Config.browser.equals("chrome"))
				defaultcolor = "rgba(0, 0, 0, 0)";
			else
				defaultcolor = "transparent";
		    String unselectedchpcolorv=driver.findElement(By.cssSelector("h3[class='chapter-heading-label']")).getCssValue("background-color");
		    if(!unselectedchpcolorv.equals(defaultcolor)) Assert.fail("Some color present for the chapter without clicking on it.");
		    Thread.sleep(3000);
		    new TopicOpen().topicOpen("1.2: Overarching ideas: Patterns, order and organisation: Physical and chemical properties"); 
		    //Validating the content of the topic rendered
		    String topiccontent = ReadTestData.readDataByTagName("BackgroundColorOfSelectedChapter", "topiccontent", "690");
		    if(!topiccontent.trim().equals(driver.findElement(By.id("widget-content-widget-13613-3")).getText().trim()))
		    Assert.fail("Content specific to the topic opened is not rendered.");
		    
		    int tocshow = driver.findElements(By.linkText("1.2: Overarching ideas: Patterns, order and organisation: Physical and chemical properties")).size();
		    if(tocshow != 0) Assert.fail("TOC bar is visible even after rendering the topic text");
		    
		    new TOCShow().tocShow();
		    
			WebElement we = driver.findElement(By.cssSelector("li[class='toc__title text--truncate selected']"));
		    String color=we.getCssValue("background-color");
		    if(!color.contains("rgba"))   Assert.fail("The chapter name doesn't get highligted with some color when mouse over is done on it.");
			
			
		}
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in TestCase BackgroundColorOfSelectedChapter",e);			 
			
			  Assert.fail("Exception in TestCase BackgroundColorOfSelectedChapter",e);
		}
	}


}
