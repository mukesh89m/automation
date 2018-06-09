package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCVerify;

public class BackgroundColorChangeWhenHoverChapter extends Driver{
	public static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.BackgroundColorChangeWhenHoverChapter");

	/*
	 * 560-561
	 */
	@Test
	public void BackgroungColorofChapterAfterHover()
	{
		try
		{
		    new LoginUsingLTI().ltiLogin("560");
		    new Navigator().NavigateTo("eTextbook");		   
		    new TOCVerify().tocChapterVerify(1);	
		    String unselectedchpcolorv=driver.findElement(By.cssSelector("h3[class='chapter-heading-label']")).getCssValue("background-color");
		    if(!unselectedchpcolorv.equals("transparent")) Assert.fail("Some color present for the chapter without mouse overing it.");
		    Actions action = new Actions(driver); //Mouse overing the chapter
			WebElement we = driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li[2]/div/div"));
			action.moveToElement(we).build().perform();
		    String color=we.getCssValue("background-color");
		    if(!color.contains("rgba"))   Assert.fail("The chapter name doesn't get highligted with some color when mouse over is done on it.");
		}
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in TestCase BackgroungColorofChapterAfterHover in class BackgroundColorChangeWhenHoverChapter",e);			 
			  Assert.fail("Exception in TestCase BackgroungColorofChapterAfterHover in class BackgroundColorChangeWhenHoverChapter",e);
		}
	}

}
