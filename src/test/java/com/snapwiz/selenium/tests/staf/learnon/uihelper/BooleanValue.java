package com.snapwiz.selenium.tests.staf.learnon.uihelper;

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class BooleanValue {
		public boolean  booleanbyid(String id)
		{
			boolean text=false;
			try
			{
				text=Driver.driver.findElement(By.id(id)).isDisplayed();
			}
			catch(Exception e)
			{
				Assert.fail("Exception in  apphelper booleanbyid ",e);
			}
			return text;
		}
		
		public boolean  booleanbyclass(String classname)
		{
			boolean text=false;
			try
			{
				text=Driver.driver.findElement(By.className(classname)).isDisplayed();
			}
			catch(Exception e)
			{
				Assert.fail("Exception in  apphelper booleanbyclass ",e);
			}
			return text;
		}
		
		public boolean  booleanbycssselector(String cssselector)
		{
			boolean text=false;
			try
			{
				text=Driver.driver.findElement(By.cssSelector(cssselector)).isDisplayed();
			}
			catch(Exception e)
			{
				Assert.fail("Exception in  apphelper booleanbycssselector ",e);
			}
			return text;
		}
		
		public boolean  booleanbylistid(String id,int index)
		{
			boolean text=false;
			try
			{
				List<WebElement> alllist=Driver.driver.findElements(By.id(id));
				text=alllist.get(index).isDisplayed();
			}
			catch(Exception e)
			{
				Assert.fail("Exception in  apphelper booleanbylistid ",e);
			}
			return text;
		}
		
		public boolean booleanbylistclass(String classname,int index)
		{
			boolean text=false;
			try
			{
				List<WebElement> alllist=Driver.driver.findElements(By.className(classname));
				text=alllist.get(index).isDisplayed();
			}
			catch(Exception e)
			{
				Assert.fail("Exception in  apphelper booleanbylistclass ",e);
			}
			return text;
		}
		
		public boolean booleanbylistcssselector(String cssselector,int index)
		{
			boolean text=false;
			try
			{
				List<WebElement> alllist=Driver.driver.findElements(By.cssSelector(cssselector));
				text=alllist.get(index).isDisplayed();
			}
			catch(Exception e)
			{
				Assert.fail("Exception in  apphelper booleanbylistcssselector ",e);
			}
			return text;
		}
}
