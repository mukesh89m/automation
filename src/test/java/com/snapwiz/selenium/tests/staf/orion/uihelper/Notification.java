package com.snapwiz.selenium.tests.staf.orion.uihelper;

import org.openqa.selenium.By;

import com.snapwiz.selenium.tests.staf.orion.Driver;

public class Notification {
	
	public String getNotificationMessage() throws Exception
	{
		
		Thread.sleep(2000);		
		return	Driver.driver.findElement(By.className("al-notification-message-body")).getText();
	}
	
	public String getNotificationMessageFromCMS() throws Exception
	{
		
		Thread.sleep(2000);		
		return	Driver.driver.findElement(By.className("cms-notification-message-body")).getText();
	}
	
	public String getNotificationMessageFromCMSCopy() throws Exception
	{
		
		Thread.sleep(2000);		
		return	Driver.driver.findElement(By.id("select-node")).getText();
	}

	public String getNotificationMessageFromCMSMoved() throws Exception
	{
		
		Thread.sleep(2000);		
		return	Driver.driver.findElement(By.id("select-node")).getText();
	}
}
