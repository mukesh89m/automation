package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R37;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
public class ProfileDropdown extends Driver
{
	@Test(priority = 1, enabled = true)
	public void Orioncoursenormallogin()
	{
		try
		{
			new DirectLogin().directLogin("2");
			driver.findElement(By.className("al-user-profile-name")).click();
			boolean myActivity = driver.findElement(By.id("ls-my-Activity")).isDisplayed();
			if(myActivity==false)
			{
				Assert.fail("My Activity option is not present in profile dropdown");
			}
			boolean myReport = driver.findElement(By.id("myReport")).isDisplayed();
			if(myReport==false)
			{
				Assert.fail("My Report option is not present in profile dropdown");
			}
			boolean myProfile = driver.findElement(By.id("myProfile")).isDisplayed();
			if(myProfile==false)
			{
				Assert.fail("My Profile option is not present in profile dropdown");
			}
			boolean help = driver.findElement(By.id("help")).isDisplayed();
			if(help==false)
			{
				Assert.fail("Help option is not present in profile dropdown");
			}
			boolean signOut = driver.findElement(By.id("signOut")).isDisplayed();
			if(signOut==false)
			{
				Assert.fail("SignOut option is not present in profile dropdown");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase Orioncoursenormallogin in class ProfileDropdown",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void Orioncoursesignout()
	{
		try
		{
			new DirectLogin().directLogin("4");
			driver.findElement(By.className("al-user-profile-name")).click();
			boolean signOut = driver.findElement(By.id("signOut")).isDisplayed();
			if(signOut==true)
			{
				driver.findElement(By.id("signOut")).click();
				Thread.sleep(2000);
				boolean loginbuttonpresent = driver.findElement(By.id("login-heading")).isDisplayed();
				if(loginbuttonpresent==false)
				{
					Assert.fail("Orion course - Click on Logout is not naviagted to wileyplus page");
				}
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase Orioncoursesignout in class ProfileDropdown",e);
		}
	}
	

	@Test(priority = 3, enabled = true)
	public void LScoursenormallogin()
	{
		try
		{
			new DirectLogin().directLogin("5");
			Thread.sleep(3000);
			driver.findElement(By.className("ls-user-nav__username")).click();
			driver.findElement(By.cssSelector("a[href='/logout']")).click();
			boolean loginbuttonpresent = driver.findElement(By.id("login-heading")).isDisplayed();
			if(loginbuttonpresent==false)
			{
				Assert.fail("LS course - Click on Logout is not naviagted to wileyplus page");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase LScoursenormallogin in class ProfileDropdown",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void AdLScoursenormallogin()
	{
		try
		{
			new DirectLogin().directLogin("6");
			Thread.sleep(3000);
			driver.findElement(By.className("ls-user-nav__username")).click();
			driver.findElement(By.cssSelector("a[href='/logout']")).click();
			boolean loginbuttonpresent = driver.findElement(By.id("login-heading")).isDisplayed();
			if(loginbuttonpresent==false)
			{
				Assert.fail("Adlspace course - Click on Logout is not naviagted to wileyplus page");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase AdLScoursenormallogin in class ProfileDropdown",e);
		}
	}
	
	@Test(priority = 5, enabled = true)
	public void Orioncoursesignoutmyactivitypage()
	{
		try
		{
			new DirectLogin().directLogin("7");
			driver.findElement(By.className("al-user-profile-name")).click();
			driver.findElement(By.id("myReport")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("al-user-profile-name")).click();
			boolean signOut = driver.findElement(By.id("signOut")).isDisplayed();
			if(signOut==true)
			{
				driver.findElement(By.id("signOut")).click();
				Thread.sleep(2000);
				boolean loginbuttonpresent = driver.findElement(By.id("login-heading")).isDisplayed();
				if(loginbuttonpresent==false)
				{
					Assert.fail("Orion course - Click on Logout is not naviagted to wileyplus page");
				}
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase Orioncoursesignoutmyactivitypage in class ProfileDropdown",e);
		}
	}	

}
