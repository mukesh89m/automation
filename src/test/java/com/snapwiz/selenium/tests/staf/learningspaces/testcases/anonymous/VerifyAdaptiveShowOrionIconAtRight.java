package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelecteTextBook;

public class VerifyAdaptiveShowOrionIconAtRight extends Driver {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.VerifyAdaptiveShowOrionIconAtRight");
	/*
	 * 634-653
	 */
	@Test
	public void verifyadaptiveicon()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("634");
			new SelecteTextBook().etextselector();	
					
			WebElement we=driver.findElement(By.xpath("/html/body/div[4]/div/div/ul[2]/div/div[2]/div/li/div/div[2]/ul"));
			String adptivetext=(String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",we);
			System.out.println(adptivetext);

			
		}
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in verifyadaptiveicon in class VerifyAdaptiveShowOrionIconAtRight",e);				
			  Assert.fail("Exception in verifyadaptiveicon in class VerifyAdaptiveShowOrionIconAtRight",e);		  
		}
	}
	

}
