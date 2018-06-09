package com.snapwiz.selenium.tests.staf.orion.uihelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;


public class ShareWith {
	
	public void share(String shareWithInitialString,String shareName,String shareWithClass,boolean removeExistingShare)
	{
		try
		{
			boolean sharefound = false;
			Driver.driver.findElement(By.className("maininput")).click();
			Thread.sleep(2000);
			if(removeExistingShare == true)
			Driver.driver.findElement(By.className("closebutton")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			Thread.sleep(3000);
			List<WebElement> suggestname;
			if(shareWithClass.toUpperCase().equals("TRUE"))
				 suggestname = Driver.driver.findElements(By.xpath("//*[starts-with(@rel, 'cls_')]")); 
			else
				 suggestname = Driver.driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]")); 
			   for (WebElement answerchoice: suggestname)
			   {    		   
			    if(answerchoice.getText().trim().equals(shareName))
			    {
			     answerchoice.click();	
			     sharefound = true;
			     break;
			    }
			   }
			if(sharefound == false)
				Assert.fail("No value selected from the Assign To field");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in UI helper ShareWith",e);
		}
	}

}
