package com.snapwiz.selenium.tests.staf.orion.apphelper;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.snapwiz.selenium.tests.staf.orion.Driver;

public class NotificationText
{
	String notification=null;
	public String notificationtext()
	{
		try
		{
			Thread.sleep(1000);
			int notification1 =Driver.driver.findElements(By.className("al-notification-message-body")).size(); 
			if(notification1 == 0)
				Assert.fail("Notification not present for fetching its text");
			
			notification =Driver.driver.findElement(By.className("al-notification-message-body")).getAttribute("innerHTML");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in class NotificationText in app helper notificationtext ",e);
		}
		return notification;
	}

}
