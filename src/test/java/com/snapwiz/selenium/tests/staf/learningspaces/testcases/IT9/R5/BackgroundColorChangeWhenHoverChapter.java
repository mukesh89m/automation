package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R5;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class BackgroundColorChangeWhenHoverChapter extends Driver{
	public static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.BackgroundColorChangeWhenHoverChapter");

	/*
	 * 560-561
	 */
	@Test
	public void BackgroungColorofChapterAfterHover()
	{
		try
		{
		    new LoginUsingLTI().ltiLogin("560");
		    new Navigator().NavigateTo("eTextBook");		   
		    String unselectedchpcolorv=driver.findElement(By.cssSelector("h3[class='chapter-heading-label']")).getCssValue("background-color");
		    if(!unselectedchpcolorv.equals("transparent")) Assert.fail("Some color present for the chapter without mouse overing it.");
			List<WebElement> we = driver.findElements(By.className("chapter-heading"));
		    new MouseHover().mouserhoverbywebelement(we.get(1));
		    String color=we.get(1).getCssValue("background-color");
		    System.out.println(color);
		    if(!color.contains("rgba"))   Assert.fail("The chapter name doesn't get highligted with some color when mouse over is done on it.");
		}
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in TestCase BackgroungColorofChapterAfterHover in class BackgroundColorChangeWhenHoverChapter",e);			 
			  Assert.fail("Exception in TestCase BackgroungColorofChapterAfterHover in class BackgroundColorChangeWhenHoverChapter",e);
		}
	}

}
