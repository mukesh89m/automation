package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;


public class VerifySectionTitleScroll extends Driver {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.VerifySectionTitleScroll");
	/*
	 * 901-903
	 */
	@Test
	public void verifysectionscroll()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("901");
			new Navigator().NavigateTo("eTextbook");
			
			String topictitle = (driver.findElement(By.cssSelector("div[class='tab active']")).getText());
			
			String defaulttopictitle = ReadTestData.readDataByTagName("tocdata", "defaulttopictitle", "1");
			String card1topic1 = ReadTestData.readDataByTagName("tocdata", "card1topic1", "1");
			if(!topictitle.equals(defaulttopictitle)) Assert.fail("The student is not naviagted to the lesson expected with TOC opened");
			Thread.sleep(5000);
			System.out.println(card1topic1);
			int tocopened = driver.findElements(By.linkText(card1topic1)).size();
			if(tocopened != 1) Assert.fail("TOC Menu Not Visible");
		
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,700)", "");
			
			
            Thread.sleep(3000);
            String titleelement = (driver.findElement(By.cssSelector("div[class='tab active']")).getText());
            System.out.println(titleelement);
            if(!titleelement.equals(defaulttopictitle)) Assert.fail("Title of the topic not present after the topic content is scrolled down.");
            if(!driver.findElement(By.cssSelector("div[class='tab active']")).isDisplayed())
            	Assert.fail("Title of the topic not visible after the topic content is scrolled down.");
            new TOCShow().tocHide();
            driver.findElement(By.cssSelector("div[class='tab active']")).click();
		}
		
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in TestCase VerifySectionTitleScroll Application Helper",e);			 
			  Assert.fail("Exception in TestCase VerifySectionTitleScroll Application Helper",e);
		}
		
	}

}

