package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;

public class TOCHide extends Driver {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.TOCHide");
	/*
	 * 904-906
	 */
	@Test
	public void tochide()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("904");
			new Navigator().NavigateTo("eTextbook");
			String topictitle = (driver.findElement(By.cssSelector("div[class='tab active']")).getText());
			String defaulttopictitle = ReadTestData.readDataByTagName("tocdata", "defaulttopictitle", "1");
			String card1topic1 = ReadTestData.readDataByTagName("tocdata", "card1topic1", "1");
			if(!topictitle.equals(defaulttopictitle)) Assert.fail("The student is not naviagted to the lesson expected with TOC opened");
			
			int tocopened = driver.findElements(By.linkText(card1topic1)).size();
			if(tocopened != 1) Assert.fail("TOC Menu Not Visible");
			
			new TOCShow().tocHide();
			
			 tocopened = driver.findElements(By.linkText(card1topic1)).size();
			if(tocopened != 0) Assert.fail("TOC Menu is Visible even after hiding it");
						
		}
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in TestCase TOCHide",e);			 
			  Assert.fail("Exception in TestCase TOCHide",e);
			  
		}
	}

}
