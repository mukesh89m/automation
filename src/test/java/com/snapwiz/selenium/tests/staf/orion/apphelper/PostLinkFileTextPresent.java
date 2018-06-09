package com.snapwiz.selenium.tests.staf.orion.apphelper;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;

public class PostLinkFileTextPresent {

	/*
	 * Post Link UploadFile Present or not
	 */
	public void postLinkFileTextPresent()
	{
		Driver.driver.findElement(By.linkText("Post")).click();
		int postvalue=Driver.driver.findElements(By.linkText("Post")).size();
		int linkvalue=Driver.driver.findElements(By.linkText("Link")).size();
		int uploadvalue=Driver.driver.findElements(By.linkText("File")).size();
		if(postvalue!=1 || linkvalue!=1 || uploadvalue!=1)		
			Assert.fail("POST, LINK or UPLOAD tabs not present on header");
			
		
		
		
	}

}
