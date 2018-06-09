package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandCollapseChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCVerify;

public class EachChapterOfTOCHaveThumbnail extends Driver
{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.EachChapterOfTOCHaveThumbnail");
	/*
	 * 555-559
	 */
	@Test(priority=1,enabled=true)
	public void ThumbnailOfTOC()
	{
		try
		{
			String thumb1 = ReadTestData.readDataByTagName("EachChapterOfTOCHaveThumbnail", "thumb1", "555");
			String thumb2 = ReadTestData.readDataByTagName("EachChapterOfTOCHaveThumbnail", "thumb2", "555");
			new LoginUsingLTI().ltiLogin("555");
			new Navigator().NavigateTo("eTextbook");	
			new TOCVerify().tocChapterVerify(1);
		    new ExpandCollapseChapter().expandChapter(1);
			new TOCVerify().tocTopicValidate(1,1);
			new TOCVerify().tocTopicValidate(1,2);			
			new ExpandCollapseChapter().collapseChapter(1);
			 Thread.sleep(3000);
			ArrayList<String> list = new ArrayList<String>();
			List<WebElement> thumbnails = driver.findElements(By.className("square"));
            	for(WebElement thumb : thumbnails)
            	{
            		list.add(thumb.getText());
            	}
				  				   
            	String[] array = list.toArray(new String[list.size()]);
            	if(!array[0].equals(thumb1) || !array[1].equals(thumb2))
            		Assert.fail("Thumbnail value of one of the two chapters is not equal to the expected value");
          }
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in LoginUSingLTI Application Helper",e);			
			  Assert.fail("Exception in TestCase ChapterNumberOnPlaceOFThumnail in class EachChapterOfTOCHaveThumbnail",e);
		 }
	}
	
	@Test(priority=2,enabled=false) //Need Review
	public void ChapterNumberOnPlaceOFThumnail()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("555");
			new Navigator().NavigateTo("eTextbook");	
		  
			       logger.log(Level.INFO,"help page shown same user First time access");				  		   
				   		   
				   boolean iconvalue= driver.findElement(By.className("toc-icon")).isDisplayed();
				  
				   if(iconvalue==false)
				   {
					   logger.log(Level.INFO,"Thumnail not present");
					   String chaptericon=driver.findElement(By.className("square")).getText();
					   
					   if(chaptericon.equals("1"))
					   {
						   logger.log(Level.INFO,"Chapter number present");					
					   }
					   else
					   {
						   logger.log(Level.INFO,"Chapter number present");						
						   Assert.fail();
					   }
					
					  
				   }
				   else
				   {
					   logger.log(Level.INFO,"Thumnail  present");					 
					   Assert.fail();
				   }
			
         
				
            		
		}
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in TestCase ChapterNumberOnPlaceOFThumnail in class EachChapterOfTOCHaveThumbnail",e);			 
			  Assert.fail();
		 }
	  	
	}

	
}
