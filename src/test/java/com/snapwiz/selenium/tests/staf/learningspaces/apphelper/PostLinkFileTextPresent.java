package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class PostLinkFileTextPresent extends Driver{

	/*
	 * Post Link UploadFile Present or not
	 */
	public void postLinkFileTextPresent()
	{
		WebDriver driver=Driver.getWebDriver();
		driver.findElement(By.linkText("Post")).click();
		int postvalue=driver.findElements(By.linkText("Post")).size();
		int linkvalue=driver.findElements(By.linkText("Link")).size();
		int uploadvalue=driver.findElements(By.linkText("File")).size();
		if(postvalue!=1 || linkvalue!=1 || uploadvalue!=1)		
			Assert.fail("POST, LINK or UPLOAD tabs not present on header");
			
		
		
		
	}

}
