package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Notification;



public class AllEntriesfilteforCourseStream extends Driver{
	/*
	 * 371-374
	 */
	@Test(priority=1,enabled=true)
	public void allEntriesfilteforCourseStream()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("371");
			new Navigator().NavigateTo("Course Stream");
			List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@class, 'sbSelector')]"));
			allElements.get(0).click();		   
		    Thread.sleep(2000);
		    driver.findElement(By.linkText("Assignments")).click();
		     
		    allElements.get(0).click();		   
		    Thread.sleep(2000);
		    driver.findElement(By.linkText("Entries by Instructors")).click();
		     	
		    allElements.get(0).click();		   
			Thread.sleep(2000);
			driver.findElement(By.linkText("My Bookmarks")).click();
		    
			
			allElements.get(0).click();		   
			Thread.sleep(2000);
			driver.findElement(By.linkText("Posts")).click();
			
			allElements.get(0).click();		   
			Thread.sleep(2000);
			driver.findElement(By.linkText("Files")).click();
			
			allElements.get(0).click();		   
			Thread.sleep(2000);
			driver.findElement(By.linkText("Links")).click();
			
			allElements.get(0).click();		   
			Thread.sleep(2000);
			driver.findElement(By.linkText("Private Messages")).click();
			
			
			
			
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

			new LoginUsingLTI().ltiLogin("371");
			new Navigator().NavigateTo("Course Stream");
			List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@class, 'sbSelector')]"));
			allElements.get(0).click();		   
			Thread.sleep(2000);
			driver.findElement(By.linkText("Assignments")).click();
			String notificationmsg = new Notification().getNotificationMessage();
		   		if(!notificationmsg.equals("No posts found for the filter criteria"))
		   			Assert.fail("Robo Notification message is not as expected");

            allElements.get(0).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("Entries by Instructors")).click();
            notificationmsg = new Notification().getNotificationMessage();
            if(!notificationmsg.equals("No posts found for the filter criteria"))
                Assert.fail("Robo Notification message is not as expected");

            allElements.get(0).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("My Bookmarks")).click();
            notificationmsg = new Notification().getNotificationMessage();
            if(!notificationmsg.equals("No posts found for the filter criteria"))
                Assert.fail("Robo Notification message is not as expected");


            allElements.get(0).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("Posts")).click();
            notificationmsg = new Notification().getNotificationMessage();
            if(!notificationmsg.equals("No posts found for the filter criteria"))
                Assert.fail("Robo Notification message is not as expected");

            allElements.get(0).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("Files")).click();
            notificationmsg = new Notification().getNotificationMessage();
            if(!notificationmsg.equals("No posts found for the filter criteria"))
                Assert.fail("Robo Notification message is not as expected");

            allElements.get(0).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("Links")).click();
            notificationmsg = new Notification().getNotificationMessage();
            if(!notificationmsg.equals("No posts found for the filter criteria"))
                Assert.fail("Robo Notification message is not as expected");

            allElements.get(0).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("Private Messages")).click();
            notificationmsg = new Notification().getNotificationMessage();
            if(!notificationmsg.equals("No posts found for the filter criteria"))
                Assert.fail("Robo Notification message is not as expected");
		 
		}
		catch(Exception e)
	    {
			 
			  Assert.fail("Exception  in testcase RoboNotification in class AllEntriesfilteforCourseStream",e);
		     
		}
	}

    @Test(priority=3,enabled=true)
    public void instructorTagOnInstructorPostsinCS()
    {
        try
        {

            new LoginUsingLTI().ltiLogin("372");
            new Navigator().NavigateTo("Course Stream");
            new PostMessage().postmessage(new RandomString().randomstring(5));
            if(!(new TextFetch().textfetchbyclass("ls-instructor-icon")).equals("Instructor"))
                Assert.fail("Instructor tag not present for post done by instructor in CS");

            new FileUpload().fileUpload("372",true);
            if(!(new TextFetch().textfetchbyclass("ls-instructor-icon")).equals("Instructor"))
                Assert.fail("Instructor tag not present for file upload done by instructor in CS");

            new PostMessage().postlink("http://www.google.com");
            if(!(new TextFetch().textfetchbyclass("ls-instructor-icon")).equals("Instructor"))
                Assert.fail("Instructor tag not present for file upload done by instructor in CS");


        }
        catch (Exception e)
        {
            Assert.fail("Exception  in testcase instructorTagOnInstructorPostsinCS in class AllEntriesfilteforCourseStream",e);
        }
    }


}
