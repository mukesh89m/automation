package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;

public class ClosingHelpPage extends Driver {
	
	
	@Test
	public void closingHelpPage()
	{
		try
		{
			  new LoginUsingLTI().ltiLogin("552");
			  new Navigator().NavigateTo("eTextbook");
			  String lessonContent = ReadTestData.readDataByTagName("ClosingHelpPage", "lessonContent", "552");
			  String defaulttopic = ReadTestData.readDataByTagName("tocdata", "defaulttopictitle", "1");
			  if(!driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li/div/div[2]/ul[2]/li")).getText().equals(defaulttopic))
				  Assert.fail("Default topic displayed on opening eTextBook is not "+defaulttopic);
			  
			  String s = driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li/div/div[2]/ul[2]/li")).getAttribute("class");
			  if(!s.trim().equals("toc__title text--truncate selected"))
						  {
				  Assert.fail("Lesson Name is Not HighLighted");
						  }
			  
			  String bgcolor = driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li/div/div[2]/ul[2]/li")).getCssValue("background-color");
			  String lessonText = driver.findElement(By.cssSelector("div[class='wiley-content']")).getText();
			
			  if(!bgcolor.trim().equals("rgba(3, 128, 183, 1)"))
			  {
				  Assert.fail("Lesson Name is Not HighLighted with the correct color");
			  }		  
			  
			  if(!lessonText.contains(lessonContent))
			  {
				  Assert.fail("Doesn't display the lesson content at the center of the pane");
			  }
			 
		}
		catch(Exception e)
		   {
			Assert.fail("Exception TestCase closingHelpPage in class ClosingHelpPage",e);
		   }
		
	}

}
