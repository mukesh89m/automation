package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SetSocialPolicy extends Driver{

    /*
    @Author: Sumit
    it sets the social policy from Setting page
     */

	public void setSocialPolicy(String dataIndex, String policyNo) 
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-user-nav__username")));//click on username
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("Settings")));//click on Setting
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("label[for='"+policyNo+"']")));//Select Option in the settings page
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("settings-save")));//click on Save button
            Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper method setSocialPolicy.", e);
		}
		
	}
	
}
