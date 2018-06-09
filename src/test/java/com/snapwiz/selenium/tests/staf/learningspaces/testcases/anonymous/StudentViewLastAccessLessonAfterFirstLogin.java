package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class StudentViewLastAccessLessonAfterFirstLogin extends Driver
{
	public static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.StudentViewLastAccessLessonAfterFirstLogin");
	/*
	 * 536-543
	 */
	@Test(priority=1,enabled=true)
    public void FirstLoginToVerifyHelpPage()
	{
		try
		{
			 String topic = ReadTestData.readDataByTagName("StudentViewLastAccessLessonAfterFirstLogin", "topic", "536");
		     new LoginUsingLTI().ltiLogin("536");  
		     new Navigator().NavigateTo("eTextbook");
			 int helppage = driver.findElements(By.className("close-help-page")).size();
			     if(helppage != 1)
			    	 Assert.fail("Help Page not shown in eTextBook when user logged in first time.");
			     else
			     {
			    	 WebElement element = (new WebDriverWait(driver,2)).until(ExpectedConditions.presenceOfElementLocated(By.className("close-help-page")));
				     element.click();
				     Thread.sleep(3000);
				     new TopicOpen().topicOpen(topic);
				     Thread.sleep(3000);
				     String textinlesson = ReadTestData.readDataByTagName("StudentViewLastAccessLessonAfterFirstLogin", "lessontext", "536");
				     String lessontext=driver.findElement(By.id("widget-content-widget-13613-3")).getText();
				    	if(!lessontext.trim().equals(textinlesson.trim()))
					    Assert.fail("Lesson not opened");
			     }  		     
		   
		     
		}
		    
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in LoginUSingLTI Application Helper",e);			
			  Assert.fail("Exception in TestCase FirstLoginToVerifyHelpPage in class StudentViewLastAccessLessonAfterFirstLogin",e);	 
		}
		
	}
	@Test(priority=2,enabled=true,dependsOnMethods={"FirstLoginToVerifyHelpPage"})
	public void SecondLoginToVerifyHelpPage()
	{
		try
		{
			 String textinlesson = ReadTestData.readDataByTagName("StudentViewLastAccessLessonAfterFirstLogin", "lessontext", "536");
		     new LoginUsingLTI().ltiLogin("536");
		     new Navigator().NavigateTo("eTextbook");
		     int helpvalue=driver.findElements(By.cssSelector("div[class='ls-help-page-view']")).size();
		     if(helpvalue == 1)
		    	 Assert.fail("Help Page opening after logging in the second time by the same user.");
		     else
		     {		    	 
		    	 new TOCShow().tocHide(); 
		    	 String lessontext=driver.findElement(By.id("widget-content-widget-13613-2")).getText();
		    	 if(!lessontext.trim().equals(textinlesson.trim()))
		    	 Assert.fail("After second login the last accessed lesson is not opened");		    	 
		     }
		   
		}
		    
		catch (Exception e)
		{
			  Assert.fail("Exception in TestCase SecongLoginToVerifyHelpPage in class StudentViewLastAccessLessonAfterFirstLogin"); 
		 }
		
	}



}
