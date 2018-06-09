package com.snapwiz.selenium.tests.staf.orion.testcases;



import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;

public class InstructorProfile
{
	/*
	 * Brajesh(10-jan-2014)
	 * Test about instructor profile in all aspect
	 */
	@Test(priority=1,enabled=true)
	public void instructorprofileupdate()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("360");//login as instructor
			Driver.driver.findElement(By.className("idb-user-profile-name")).click();//click on name of instructor
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("myProfile")).click();//click on my profile
			Thread.sleep(2000);
			List<WebElement> profile=Driver.driver.findElements(By.className("left-col"));
			String fname=profile.get(0).getText();
			String lname=profile.get(1).getText();
			String email=profile.get(2).getText();
			if(!fname.equals("First Name"))
				Assert.fail("First name not present");
			if(!lname.equals("Last Name"))
				Assert.fail("Last name not present");
			if(!email.equals("Email"))
				Assert.fail("Email not present");
			boolean insiamge=Driver.driver.findElement(By.id("my-profile-thumbnail-blk")).isDisplayed();//verify profile image
			if(insiamge==false)
				Assert.fail("image of instrctor not shown");			
			Actions ac= new Actions(Driver.driver);
			WebElement we1 = Driver.driver.findElement(By.id("upload-thumnail-section"));
			ac.moveToElement(we1).build().perform();
			String image=Driver.driver.findElement(By.id("upload-profile-thumbnail")).getCssValue("background-image");//check plus image 
			if(!image.contains("thumbnail-plus-icon.png"))
				Assert.fail("plus sign image not shown");
			Actions action = new Actions(Driver.driver);
			WebElement we = Driver.driver.findElement(By.id("upload-thumnail-section"));
			action.moveToElement(we).build().perform();
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("upload-profile-thumbnail")));//uplaod picture
			String filename=ReadTestData.readDataByTagName("", "filename", "360");
			new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
			Thread.sleep(3000);
			Driver.driver.findElement(By.id("start_queue")).click();//click on upload button
			Thread.sleep(10000);			
			String image1=Driver.driver.findElement(By.id("user-thumbnail-img")).getAttribute("src");//fetch image name after upload
			if(!image1.contains("img"))					 
				Assert.fail("instructor profile image not replaced");
			//image of different size
			Actions ac1= new Actions(Driver.driver);
			WebElement we2 = Driver.driver.findElement(By.id("upload-thumnail-section"));
			ac1.moveToElement(we2).build().perform();
			Actions action1 = new Actions(Driver.driver);
			WebElement we3 = Driver.driver.findElement(By.id("upload-thumnail-section"));
			action1.moveToElement(we3).build().perform();
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("upload-profile-thumbnail")));//uplaod picture
			String filename1=ReadTestData.readDataByTagName("", "filename1", "360");
			new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");
			Thread.sleep(3000);
			Driver.driver.findElement(By.id("start_queue")).click();//click on upload button
			Thread.sleep(10000);			
			String image2=Driver.driver.findElement(By.id("user-thumbnail-img")).getAttribute("src");//fetch image name after upload
			if(!image2.contains("index"))					 
				Assert.fail("instructor profile image not replaced");
			
			 
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method instructorprofileupdate in class InstructorProfile",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
	
	
}
