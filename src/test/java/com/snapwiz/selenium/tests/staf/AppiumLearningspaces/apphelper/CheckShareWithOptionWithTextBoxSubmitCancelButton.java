package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;

public class CheckShareWithOptionWithTextBoxSubmitCancelButton {

	public void ShareWithOptionWithTextBoxSubmitCancelButton(String dataIndex) throws Exception 
	{
		try
		{
			String sharewithlabel = ReadTestData.readDataByTagName("", "sharewithlabel", "97");
			//Share with Label Check
			if(!Driver.driver.findElement(By.className("text--nowrap")).getText().equals(sharewithlabel))
				Assert.fail("Share With label text not present beside share with text box");
			//Submit Button
			int submitButton=Driver.driver.findElements(By.xpath("//*[@id='post-submit-button']")).size();
			int cancelButton=Driver.driver.findElements(By.xpath("//*[@id='post-submit-cancel-button']")).size();
			int textBox=Driver.driver.findElements(By.className("maininput")).size();
			if(submitButton != 1 || cancelButton!=1 || textBox != 1)
	  			Assert.fail("One of submit button, cancel button or share with textbox not present");  			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in CheckShareWithOptionWithTextBoxSubmitCancelButton",e);
		}
		
	}
}

