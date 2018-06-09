package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;

import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.MouseHover;

public class Navigator 
{

	public void NavigateTo(String navigateTo)
	{
		try
		{
			Thread.sleep(12000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='navigator dummies-navigator-icon']")));
			//Driver.driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']")).click();
			Thread.sleep(3000);
			if(navigateTo.equals("eTextbook")) navigateTo = "Learning Content";
			//Driver.driver.findElement(By.linkText(navigateTo)).click();
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText(navigateTo)));
			if(navigateTo.equals("Learning Content"))
			{
			int helppage = Driver.driver.findElements(By.className("close-help-page")).size(); 
		     if(helppage == 1)
		    	 Driver.driver.findElement(By.className("close-help-page")).click();
			}
			if(navigateTo.equals("Resources"))
			{
				if(Driver.driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources"))
				{//Opening All Resources tab if not opened after clicking on New Assignment button
					System.out.println("Opening All Resources tab");
					Driver.driver.findElement(By.cssSelector("span[title='All Resources']")).click();
				}
			}
			//Thread.sleep(5000);
		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in NavigateTo in AppHelper Navigator",e);
		}
	}
	
	public void institutionAdminNavigateTo(String navigateTo)
	{
		try
		{
			Thread.sleep(6000);
			Driver.driver.findElement(By.className("ls-site-nav-drop-down")).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.partialLinkText(navigateTo)).click();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in institutionAdminNavigateTo in AppHelper class Navigator", e);
		}
	}
	
	public void InstructorNavigateTo(String navigateTo)
	{
		try
		{

			 String streamvalue=Driver.driver.findElement(By.xpath("/html/body/header/div/div/div/ul/li/a")).getText();
		     
			 if(!streamvalue.trim().equals(navigateTo))
		     {
		    	
			     Actions action = new Actions(Driver.driver);
			     WebElement we = Driver.driver.findElement(By.xpath("/html/body/header/div/div/div/ul/li/a"));
			     action.moveToElement(we).build().perform();
			     Driver.driver.findElement(By.linkText(navigateTo)).click();
			     if(navigateTo.equals("eTextbook"))
					{
					int helppage = Driver.driver.findElements(By.className("close-help-page")).size(); 
				     if(helppage == 1)
				    	 Driver.driver.findElement(By.className("close-help-page")).click();
					}
			     Thread.sleep(5000);
			    
		     }
		     else
		     {		    	
		    	 
		     }
		}
		catch(Exception e)
		{
			Assert.fail("Exception in InstructorNavigateTo in AppHelper class Navigator", e);
		}
	}
	public void navigateToResourceTab()
	{
		try
		{
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span[title='Resources']")));
			//Driver.driver.findElement(By.cssSelector("span[title='Resources']")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in navigateToResourceTab in AppHelper class Navigator", e);
		}
	}
	public void openResourceFromResourceTab(int zerobasedindex)
	{
		try
		{
			//hover on resources
			List<WebElement> resourceWE = Driver.driver.findElements(By.cssSelector("li[class='resource-content-posts-list']")); 
			Locatable hoverItem1 = (Locatable) resourceWE.get(zerobasedindex);
			Mouse mouse1 = ((HasInputDevices) Driver.driver).getMouse();
			mouse1.mouseMove(hoverItem1.getCoordinates());
			//click on resource
			List<WebElement> allOpenLink = Driver.driver.findElements(By.cssSelector("span.ls-right-tab-hover-sprite.folder-cycle-bg"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allOpenLink.get(zerobasedindex));
		//	allOpenLink.get(zerobasedindex).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("div.ls-main_aside > div.tabs > div.tab.active")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in navigateToResourceTab in AppHelper class Navigator", e);
		}
	}
	
	public void clickOnJumpOutIcon()
	{
		try
		{
			MouseHover.mouserhover("my-journal-activity-details");
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span[class='my-journal-media-popout-icon card-icons']")));
			//Driver.driver.findElement(By.cssSelector("span[class='my-journal-media-popout-icon card-icons']")).click();//click on jump out icon
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in clickOnJumpOutIcon in AppHelper class Navigator", e);
		}
	}
	//@Author Sumit
	//to navigate to tabs present in RHS, pass the tab name as a string to navigate
	public void navigateToTab(String tabName)
	{
		try
		{
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("span[title='"+tabName+"']")));
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in navigateToResourceTab in AppHelper class Navigator", e);
		}
	}
	//@Author Sumit
	//to validate the navigator elements
	public void verifyNavigatorElements(String pageName)
	{
		try
			{
				int element = Driver.driver.findElements(By.linkText(pageName)).size();
				Thread.sleep(3000);
				if(element == 0)
				{
					Assert.fail("The \""+pageName+"\" is absent in navigator.");
				}
		
			}
		catch(Exception e)
			{
				Assert.fail("Exception in navigatorElements in AppHelper class Navigator", e);
			}
	}
}
