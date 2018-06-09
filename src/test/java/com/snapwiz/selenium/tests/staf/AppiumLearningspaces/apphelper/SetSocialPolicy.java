package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class SetSocialPolicy {

    /*
    @Author: Sumit
    it sets the social policy from Setting page
     */

	public void setSocialPolicy(String dataIndex, String policyNo) 
	{
		try
		{
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-user-nav__username")));//click on username
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Settings")));//click on Setting
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("label[for='"+policyNo+"']")));//Select Option in the settings page
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id("settings-save")));//click on Save button
            Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper method setSocialPolicy.", e);
		}
		
	}
	
}
