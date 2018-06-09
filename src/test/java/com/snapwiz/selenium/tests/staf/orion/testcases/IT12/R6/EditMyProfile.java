package com.snapwiz.selenium.tests.staf.orion.testcases.IT12.R6;

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
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.uihelper.ComboBox;

public class EditMyProfile {
	
	@Test(priority = 1, enabled = true)
	public void editMyProfile()
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();//login to CMS
			new ComboBox().selectValuewithtitle(1, "My Profile");//got My Profile
			Thread.sleep(2000);
			String url = Driver.driver.getCurrentUrl();//get the current url
			if(!url.contains("swatUserProfile"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking My Profile the profile page is not displayed.");
			}
			// check the header for My Profile page
			String header = Driver.driver.findElement(By.className("profile-icon-text")).getText();
			if(!header.equals("Personal Details"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Profile page dont show with header as Personal Details.");
			}
			//check for user profile
			String img = Driver.driver.findElement(By.id("user-thumbnail-img")).getAttribute("src");
			if(!img.contains("user-default-thumbnail.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Profile page dont have the first name of the user.");
			}
			//check for First Name label
			String userName = Driver.driver.findElement(By.className("user-name-section")).getText();
			if(!userName.contains("First Name"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Profile page dont have the first name of the user.");
			}
			//check for Last Name label
			if(!userName.contains("Last Name"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Profile page dont have the last name of the user.");
			}
			//check for email address
			String email = Driver.driver.findElement(By.className("user-email-section")).getText();
			if(!email.contains("Email address"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Profile page dont have the email address of the user.");
			}
			//check for arrow icon
			String arrow = Driver.driver.findElement(By.className("small-down-arrow")).getCssValue("background-image");
			if(!arrow.contains("smal-down.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Next to 'Change password' should show down arrow icon is absent.");
			}
			List<WebElement> allEditLink = Driver.driver.findElements(By.cssSelector("div[class='cms-shown-text shown-text']"));
			//mouse hover first name
			Actions action = new Actions(Driver.driver);
			WebElement we = allEditLink.get(0);
			action.moveToElement(we).build().perform();
			Driver.driver.findElement(By.cssSelector("div.edit-link > img")).click();//click on edit
			Thread.sleep(2000);
			Driver.driver.switchTo().activeElement().clear();
			Driver.driver.switchTo().activeElement().sendKeys("Edited First Name");//edit the first name
			Driver.driver.findElement(By.id("body-content-wrapper")).click();//click outside the textbox
			Thread.sleep(2000);
			String editedName = Driver.driver.findElement(By.cssSelector("div[class='cms-static-text static-text static-text-field']")).getText();
			if(!editedName.equals("Edited First Name"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("We are unable to edit the first name.");
			}
			//mouse hover last name
			Actions action1 = new Actions(Driver.driver);
			WebElement we1 = allEditLink.get(1);
			action1.moveToElement(we1).build().perform();
			Driver.driver.findElement(By.xpath("//div[@id='body-content-wrapper']/div/div[2]/div[2]/div/div[4]/div/div[2]/img")).click();//click on edit
			Thread.sleep(2000);
			Driver.driver.switchTo().activeElement().clear();
			Driver.driver.switchTo().activeElement().sendKeys("Edited Last Name");//edit the Last name
			Driver.driver.findElement(By.id("body-content-wrapper")).click();//click outside the textbox
			Thread.sleep(2000);
			String editedName1 = Driver.driver.findElement(By.cssSelector("div[class='static-text cms-static-text static-text-field']")).getText();
			if(!editedName1.equals("Edited Last Name"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("We are unable to edit the last name.");
			}
			//mouse hover email id
			Actions action2 = new Actions(Driver.driver);
			WebElement we2 = allEditLink.get(2);
			action2.moveToElement(we2).build().perform();
			Driver.driver.findElement(By.xpath("//div[@id='body-content-wrapper']/div/div[2]/div[2]/div[2]/div[2]/div/div[2]/img")).click();//click on edit symbol for email address
			Thread.sleep(2000);
			Driver.driver.switchTo().activeElement().clear();
			Driver.driver.switchTo().activeElement().sendKeys("Edited email address");//edit the email address
			Driver.driver.findElement(By.id("body-content-wrapper")).click();//click outside the textbox
			Thread.sleep(2000);
			List<WebElement> allList = Driver.driver.findElements(By.cssSelector("div[class='cms-static-text static-text static-text-field']"));
			String editedEmail = allList.get(1).getText();
			if(!editedEmail.equals("Edited email address"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("We are unable to edit the email address.");
			}
			Driver.driver.findElement(By.id("save")).click();
			Thread.sleep(2000);
			String message = Driver.driver.findElement(By.id("cms-notification-message-body-title")).getText();
			if(!message.contains("Your changes have been saved successfully."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking save button all the edited fields are not saved successfully.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase editMyProfile in class EditMyProfile",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void changePasswordAndClickSave()
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();//login to CMS
			new ComboBox().selectValuewithtitle(1, "My Profile");//got My Profile
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("cms-user-change-password")).click();//click on change password link
			Thread.sleep(2000);
			int popup = Driver.driver.findElements(By.className("change-password-popup-outer-wrapper")).size();
			if(popup == 0 )
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking 'Change password' the popup to change the password doesnt come.");
			}
			Driver.driver.findElement(By.id("new-password")).clear();
			Driver.driver.findElement(By.id("new-password")).sendKeys("newpassword");// enter new password
			Driver.driver.findElement(By.id("confirm-password")).clear();
			Driver.driver.findElement(By.id("confirm-password")).sendKeys("newpassword");//confirm new password
			Driver.driver.findElement(By.id("change-password-save-btn")).click();//click on save button
			Thread.sleep(2000);
			int popup1 = Driver.driver.findElements(By.className("change-password-popup-outer-wrapper")).size();
			if(popup1 != 0 )
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking save button to save changes for password the popup doesnt disappear. ");
			}
			String message = Driver.driver.findElement(By.id("cms-notification-message-body-title")).getText();
			if(!message.contains("Your password has been updated successfully."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking save button to save changes for password the required notification doesnt come.");
			}
			//again login to cms with the new password
			new Driver().firefoxDriverStart();
		    Driver.driver.get(Config.baseURL);
			Thread.sleep(3000);
			Driver.driver.findElement(By.id("username")).sendKeys(Config.cmsUserName);
			Driver.driver.findElement(By.id("password")).sendKeys("newpassword");//using new password
			Driver.driver.findElement(By.id("loginSubmitBtn")).click();//click Login
			Thread.sleep(3000);
			String url = Driver.driver.getCurrentUrl();//get the current url
			if(!url.contains("cmsHome"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After changing the password if log in using new password it doesnt take Course Managemnt page.");
			}
			//reset the password
			new ComboBox().selectValuewithtitle(1, "My Profile");//got My Profile
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("cms-user-change-password")).click();//click on change password link
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("new-password")).clear();
			Driver.driver.findElement(By.id("new-password")).sendKeys(Config.cmsPassword);//enter the old password
			Driver.driver.findElement(By.id("confirm-password")).clear();
			Driver.driver.findElement(By.id("confirm-password")).sendKeys(Config.cmsPassword);//confirm the old password
			Driver.driver.findElement(By.id("change-password-save-btn")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase changePasswordAndClickSave in class EditMyProfile",e);
		}
	}
	
	
	@Test(priority = 3, enabled = true)
	public void changePasswordAndClickCancel()
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();//login to CMS
			new ComboBox().selectValuewithtitle(1, "My Profile");//got My Profile
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("cms-user-change-password")).click();//click on change password link
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("new-password")).clear();
			Driver.driver.findElement(By.id("new-password")).sendKeys("newpassword");// enter new password
			Driver.driver.findElement(By.id("confirm-password")).clear();
			Driver.driver.findElement(By.id("confirm-password")).sendKeys("newpassword");//confirm new password
			Driver.driver.findElement(By.id("change-password-cancel")).click();//click on cancel
			Thread.sleep(2000);
			new DirectLogin().CMSLogin();//login to CMS with old password
			String url = Driver.driver.getCurrentUrl();//get the current url
			if(!url.contains("cmsHome"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Change password and click cancel then log in to CMS with old password it doesnt take Course Managemnt page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase changePasswordAndClickCancel in class EditMyProfile",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void newPasswordAndConfirmPasswordMismatch()
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();//login to CMS
			new ComboBox().selectValuewithtitle(1, "My Profile");//got My Profile
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("cms-user-change-password")).click();//click on change password link
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("new-password")).clear();
			Driver.driver.findElement(By.id("new-password")).sendKeys("newpassword");// enter new password
			Driver.driver.findElement(By.id("confirm-password")).clear();
			Driver.driver.findElement(By.id("confirm-password")).sendKeys("newpassword123");//enter a different password
			Driver.driver.findElement(By.id("change-password-save-btn")).click();//click on save button
			Thread.sleep(2000);
			String message = Driver.driver.findElement(By.id("cms-notification-message-body-title")).getText();
			if(!message.contains("Your password entries do not match. Please correct and retry."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("If the new password and confirm password mismathches then the required modification is not shown.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase newPasswordAndConfirmPasswordMismatch in class EditMyProfile",e);
		}
	}
	@Test(priority = 5, enabled = true)
	public void uploadProfilePics()
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();//login to CMS
			new ComboBox().selectValuewithtitle(1, "My Profile");//got My Profile
			Thread.sleep(2000);
			Actions action = new Actions(Driver.driver);
			WebElement we = Driver.driver.findElement(By.id("upload-profile-thumbnail"));
		    action.moveToElement(we).build().perform();
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("upload-profile-thumbnail")));//uplaod picture
			new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+"img.png"+"^");
			Thread.sleep(3000);
			Driver.driver.findElement(By.id("start_queue")).click();//click on upload button
			Thread.sleep(10000);
			String img = Driver.driver.findElement(By.id("user-thumbnail-img")).getAttribute("src");
			if(img.contains("user-default-thumbnail.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("We are unable to change the profile pics.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase uploadProfilePics in class EditMyProfile",e);
		}
	}
	@Test(priority = 6, enabled = true)
	public void fieldsAre255CharacterLong()
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();//login to CMS
			new ComboBox().selectValuewithtitle(1, "My Profile");//got My Profile
			Thread.sleep(2000);
			List<WebElement> allEditLink = Driver.driver.findElements(By.cssSelector("div[class='cms-shown-text shown-text']"));
			String str = "";
			for (int i = str.length(); i < 255; i++)
	            str = str+"a";
			//mouse hover first name
			Actions action = new Actions(Driver.driver);
			WebElement we = allEditLink.get(0);
			action.moveToElement(we).build().perform();
			Driver.driver.findElement(By.cssSelector("div.edit-link > img")).click();//click on edit
			Thread.sleep(2000);
			Driver.driver.switchTo().activeElement().clear();
			Driver.driver.switchTo().activeElement().sendKeys(str);//edit the first name
			Driver.driver.findElement(By.id("body-content-wrapper")).click();//click outside the textbox
			Thread.sleep(2000);
			String editedName = Driver.driver.findElement(By.cssSelector("div[class='cms-static-text static-text static-text-field']")).getText();
			if(!editedName.equals(str))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("First name place is not supporting 255 characters.");
			}
			//mouse hover last name
			Actions action1 = new Actions(Driver.driver);
			WebElement we1 = allEditLink.get(1);
			action1.moveToElement(we1).build().perform();
			Driver.driver.findElement(By.xpath("//div[@id='body-content-wrapper']/div/div[2]/div[2]/div/div[4]/div/div[2]/img")).click();//click on edit
			Thread.sleep(2000);
			Driver.driver.switchTo().activeElement().clear();
			Driver.driver.switchTo().activeElement().sendKeys(str);//edit the Last name
			Driver.driver.findElement(By.id("body-content-wrapper")).click();//click outside the textbox
			Thread.sleep(2000);
			String editedName1 = Driver.driver.findElement(By.cssSelector("div[class='static-text cms-static-text static-text-field']")).getText();
			if(!editedName1.equals(str))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Last name place is not supporting 255 characters.");
			}
			//mouse hover email id
			Actions action2 = new Actions(Driver.driver);
			WebElement we2 = allEditLink.get(2);
			action2.moveToElement(we2).build().perform();
			Driver.driver.findElement(By.xpath("//div[@id='body-content-wrapper']/div/div[2]/div[2]/div[2]/div[2]/div/div[2]/img")).click();//click on edit symbol for email address
			Thread.sleep(2000);
			Driver.driver.switchTo().activeElement().clear();
			Driver.driver.switchTo().activeElement().sendKeys(str);//edit the email address
			Driver.driver.findElement(By.id("body-content-wrapper")).click();//click outside the textbox
			Thread.sleep(2000);
			List<WebElement> allList = Driver.driver.findElements(By.cssSelector("div[class='cms-static-text static-text static-text-field']"));
			String editedEmail = allList.get(1).getText();
			if(!editedEmail.equals(str))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Email address place is not supporting 255 characters.");
			}
			
			
			Driver.driver.findElement(By.id("cms-user-change-password")).click();//click on change password link
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("new-password")).clear();
			Driver.driver.findElement(By.id("new-password")).sendKeys(str);// enter new password
			Driver.driver.findElement(By.id("confirm-password")).clear();
			Driver.driver.findElement(By.id("confirm-password")).sendKeys(str);//confirm new password
			Driver.driver.findElement(By.id("change-password-save-btn")).click();//click on save button
			Thread.sleep(2000);
			String message = Driver.driver.findElement(By.id("cms-notification-message-body-title")).getText();
			if(!message.contains("Your password has been updated successfully."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In password field we can not use 255 characters.");
			}
			//reset the password
			Driver.driver.findElement(By.id("cms-user-change-password")).click();//click on change password link
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("new-password")).clear();
			Driver.driver.findElement(By.id("new-password")).sendKeys(Config.cmsPassword);//enter the old password
			Driver.driver.findElement(By.id("confirm-password")).clear();
			Driver.driver.findElement(By.id("confirm-password")).sendKeys(Config.cmsPassword);//confirm the old password
			Driver.driver.findElement(By.id("change-password-save-btn")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase fieldsAre255CharacterLong in class EditMyProfile",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
