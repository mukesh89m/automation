package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelperAppium;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import org.openqa.selenium.By;

public class Notification {
	
	public String getNotificationMessage() throws Exception
	{
		
		Thread.sleep(2000);		
		return	Driver.driver.findElement(By.className("notification-message-body")).getText();
	}
	
	

}
