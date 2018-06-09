package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.ReadTestData;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Logout;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;

public class CancelFunctionality 
{	
	@Test(priority = 1, enabled = true)
	public void cancelFunctionalityForPostTab()
	{
		try
		{
			String methodName = new Exception().getStackTrace()[0].getMethodName();
			String shareWithInitialString = methodName.substring(0, 3);
			System.out.println("student: "+shareWithInitialString);
			String studentnametag = methodName+"b";
			Driver.startDriver();
			new UserCreate().CreateStudent("b", "");//create student
			new DirectLogin().studentLogin("b");
			new Logout().logout();
			
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			String defaulttextpresent = ReadTestData.readDataByTagName("CancelFunctionality", "defaulttext", "266");
			Driver.driver.findElement(By.linkText("Post")).click();
			Driver.driver.findElement(By.className("maininput")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			Thread.sleep(3000);
			List<WebElement> suggestname = Driver.driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]")); 
			for (WebElement answerchoice: suggestname)
			{ 					   
				if(answerchoice.getText().trim().equals(studentnametag))
				{				    	
					answerchoice.click();
					break;
				}
			}				   
			Driver.driver.findElement(By.id("post-submit-cancel-button")).click();   
			Thread.sleep(3000);
			String defaulttext = Driver.driver.switchTo().frame("iframe-user-text-input-div").findElement(By.xpath("/html/body/font")).getText();
			Driver.driver.switchTo().defaultContent();
			if(!defaulttext.equals(defaulttextpresent))
				Assert.fail("Default Text 'Share your knowledge or seek answers' is NOT present for Post tab");
			Driver.driver.findElement(By.linkText("Post")).click();
			if(Driver.driver.findElement(By.className("item-text")).getText() == null)
				Assert.fail("Default class section name not present in the share-with text box");
		}
		catch(Exception e)
		{
			Assert.fail("Exception TestCase cancelFunctionalityForPostTab in class CancelFunctionality",e);	
		}

	}

	@Test(priority = 2, enabled = true)
	public void cancelFunctionalityForLinkTab()
	{
		try
		{
			String methodName = new Exception().getStackTrace()[0].getMethodName();
			String shareWithInitialString = methodName.substring(0, 3);
			System.out.println("student: "+shareWithInitialString);
			String studentnametag = methodName+"b";
			Driver.startDriver();
			new UserCreate().CreateStudent("b", "");//create student
			new DirectLogin().studentLogin("b");
			new Logout().logout();
			
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			String defaulttextpresent = ReadTestData.readDataByTagName("CancelFunctionality", "defaulttext", "268");
			Driver.driver.findElement(By.linkText("Link")).click();
			Thread.sleep(5000);
			Driver.driver.findElement(By.className("maininput")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			Thread.sleep(5000);
			List<WebElement> suggestname = Driver.driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]")); 
			for (WebElement answerchoice: suggestname)
			{ 					  
				if(answerchoice.getText().trim().equals(studentnametag))
				{				    	
					answerchoice.click();
					break;
				}
			}
			Driver.driver.findElement(By.id("post-submit-cancel-button")).click();   
			Thread.sleep(3000);
			String defaulttext = Driver.driver.switchTo().frame("iframe-user-link-input-div").findElement(By.xpath("/html/body/font")).getText();
			Driver.driver.switchTo().defaultContent();
			if(!defaulttext.equals(defaulttextpresent))
				Assert.fail("Default Text 'Share your link' is NOT present for Link tab");
			Driver.driver.findElement(By.linkText("Link")).click();
			if(Driver.driver.findElement(By.className("item-text")).getText() == null)
				Assert.fail("Default class section name not present in the share-with text box");


		}
		catch(Exception e)
		{
			Assert.fail("Exception TestCase cancelFunctionalityForLinkTab in class CancelFunctionality",e);	
		}

	}

	@Test(priority = 3, enabled = true)
	public void cancelFunctionalityForFileTab()
	{
		try
		{
			String methodName = new Exception().getStackTrace()[0].getMethodName();
			String shareWithInitialString = methodName.substring(0, 3);
			System.out.println("student: "+shareWithInitialString);
			String studentnametag = methodName+"b";
			Driver.startDriver();
			new UserCreate().CreateStudent("b", "");//create student
			new DirectLogin().studentLogin("b");
			new Logout().logout();
			
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			Driver.driver.findElement(By.linkText("File")).click();
			Thread.sleep(5000);
			Driver.driver.findElement(By.className("maininput")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			Thread.sleep(5000);
			List<WebElement> suggestname = Driver.driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]")); 
			for (WebElement answerchoice: suggestname)
			{ 					   
				if(answerchoice.getText().trim().equals(studentnametag))
				{				    	
					answerchoice.click();
					break;
				}
			}

			new FileUpload().fileUpload("269", false);

			new FileUpload().enterFileUploadText(); //entering file upload text

			Driver.driver.findElement(By.id("post-submit-cancel-button")).click();
			Thread.sleep(3000);
			int file = Driver.driver.findElements(By.cssSelector("img[id='file-thumbnail-img']")).size();

			if(file != 0)					
				Assert.fail("Uploaded file does not get removed on clicking cancel button");
			Driver.driver.findElement(By.linkText("File")).click();
			if(Driver.driver.findElement(By.className("item-text")).getText() == null)
				Assert.fail("Default class section name not present in the share-with text box");
		}
		catch(Exception e)
		{
			Assert.fail("Exception TestCase cancelFunctionalityForFileTab in class CancelFunctionality",e);	
		}
	}
	@AfterMethod
	public void tearDown() throws Exception {
		  Driver.driver.quit();  
	}
}
