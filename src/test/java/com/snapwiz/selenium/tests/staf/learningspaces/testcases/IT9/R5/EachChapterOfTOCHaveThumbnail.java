package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R5;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class EachChapterOfTOCHaveThumbnail extends Driver
{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.EachChapterOfTOCHaveThumbnail");
	/*
	 * 555-559
	 */
	@Test(priority=1,enabled=true)
	public void ThumbnailOfTOC()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("555");
			new Navigator().NavigateTo("eTextBook");	
			int tocicon=driver.findElements(By.className("defult-chapter-thumbnail")).size();
			if(tocicon==0)
				Assert.fail("chapter icon not shown along with chapter");
          }
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in LoginUSingLTI Application Helper",e);			
			  Assert.fail("Exception in TestCase ChapterNumberOnPlaceOFThumnail in class EachChapterOfTOCHaveThumbnail",e);
		 }
	}
	

}
