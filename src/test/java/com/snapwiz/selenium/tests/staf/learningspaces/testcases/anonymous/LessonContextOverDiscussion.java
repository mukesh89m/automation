package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;



public class LessonContextOverDiscussion extends Driver {
	
	@Test
	public void lessonContextOverDiscussion()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("332");
			Thread.sleep(5000);
			 Actions action = new Actions(driver);
			    WebElement we = driver.findElement(By.xpath("/html/body/header/div/div/div/ul/li"));
			    action.moveToElement(we).moveToElement(driver.findElement(By.xpath("/html/body/header/div/div/div/ul/li[2]/a"))).click().build().perform();
			    driver.findElement(By.cssSelector("a[class=' ls-toc-btn ls-right-new-btn']")).click();
			    
			   
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail();
		}
	}

}
