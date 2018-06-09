package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.Notification;

public class AllEntriesfilteforCourseStream {
	/*
	 * 371-374
	 */
	@Test(priority=1,enabled=true)
	public void allEntriesfilteforCourseStream()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			List<WebElement> allElements = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'sbSelector')]"));
		    allElements.get(0).click();		   
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText("My Bookmarks")).click();
		    
			
			allElements.get(0).click();		   
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText("Posts")).click();
			
			allElements.get(0).click();		   
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText("Files")).click();
			
			allElements.get(0).click();		   
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText("Links")).click();
			
	
		}
		catch(Exception e)
	    {
				  
			  Assert.fail("Exception  in testcase allEntriesfilteforCourseStream in class AllEntriesfilteforCourseStream",e);	     
		}
	}
	
	
	@Test(priority=2,enabled=true)
	public void RoboNotification()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("aa", "");//create student
			new DirectLogin().studentLogin("aa");
			new Navigator().NavigateTo("Course Stream");
			List<WebElement> allElements = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'sbSelector')]"));
			allElements.get(0).click();		   
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText("Links")).click();
			  String notificationmsg = new Notification().getNotificationMessage();
		   		if(!notificationmsg.equals("No posts found for the filter criteria"))
		   			Assert.fail("Robo Notification message is not as expected");
		    int errormsgvalue=Driver.driver.findElements(By.className("notification-message-body")).size();
		    	 if(errormsgvalue == 0)		    	 
		    		 Assert.fail("Robo Notification NOT shown");
		 
		}
		catch(Exception e)
	    { 
			  Assert.fail("Exception  in testcase RoboNotification in class AllEntriesfilteforCourseStream",e);  
		}
	}
	@AfterMethod
	  public void tearDown() throws Exception 
	  {
	     Driver.driver.quit();    
	  }
}
