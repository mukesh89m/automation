package com.snapwiz.selenium.tests.staf.learningspaces.uihelper;

import org.openqa.selenium.By;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.WebDriver;

public class Notification extends Driver{
	
	public String getNotificationMessage() throws Exception
	{
		WebDriver driver=Driver.getWebDriver();
		Thread.sleep(2000);
		return	driver.findElement(By.className("notification-message-body")).getText();
	}
	
	

}
