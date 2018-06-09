package com.snapwiz.selenium.tests.staf.dummies.uihelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;


public class ShareWith {
	
	public void share(String shareName,boolean removeExistingShare)
	{
		try
		{
			System.out.println("share name "+shareName);
            Thread.sleep(3000);
			boolean sharefound = false;
			Driver.driver.findElement(By.className("maininput")).click();
			Thread.sleep(2000);
			if(removeExistingShare == true)
			Driver.driver.findElement(By.className("closebutton")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("maininput")).sendKeys(shareName);
			Thread.sleep(3000);
			List<WebElement> suggestname;
				 suggestname = Driver.driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]")); 
			   for (WebElement answerchoice: suggestname)
			   {
				   System.out.println("Names "+answerchoice.getText());
			    if(answerchoice.getText().trim().equals(shareName))
			    {
			    	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
			    	answerchoice.click();
			    	Thread.sleep(3000);
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
