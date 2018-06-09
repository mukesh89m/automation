package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper;

import org.openqa.selenium.By;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;

public class Notification {
	
	public String getNotificationMessage() throws Exception
	{
		
		Thread.sleep(2000);		
		return	Driver.driver.findElement(By.className("notification-message-body")).getText();
	}
	
	

}
