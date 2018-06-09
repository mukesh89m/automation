package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class CheckShareWithOptionWithTextBoxSubmitCancelButton extends Driver {

	public void ShareWithOptionWithTextBoxSubmitCancelButton(String dataIndex) throws Exception 
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			String sharewithlabel = ReadTestData.readDataByTagName("", "sharewithlabel", "97");
			//Share with Label Check
			if(!driver.findElement(By.className("text--nowrap")).getText().equals(sharewithlabel))
				Assert.fail("Share With label text not present beside share with text box");
			//Submit Button
			int submitButton=driver.findElements(By.xpath("//*[@id='post-submit-button']")).size();
			int cancelButton=driver.findElements(By.xpath("//*[@id='post-submit-cancel-button']")).size();
			int textBox=driver.findElements(By.className("maininput")).size();
			if(submitButton != 1 || cancelButton!=1 || textBox != 1)
	  			Assert.fail("One of submit button, cancel button or share with textbox not present");  			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in CheckShareWithOptionWithTextBoxSubmitCancelButton",e);
		}
		
	}
}

