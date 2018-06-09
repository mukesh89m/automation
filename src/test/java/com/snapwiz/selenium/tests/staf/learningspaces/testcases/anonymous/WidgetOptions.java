package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;


public class WidgetOptions extends Driver{
	@Test(priority=1,enabled=true)
	public void widgetOptionsVerify()
	{
		try
		{
			new Widget().widgetOptionsVerify(3023);
			driver.findElement(By.id("discussion")).click(); //selecting discussion option on right clicking
			Thread.sleep(2000);
			 WebElement menuitem = driver.findElement(By.className("discussion-widget-container"));
			 Locatable hoverItem = (Locatable) menuitem;
			  Mouse mouse = ((HasInputDevices) driver).getMouse();
			  mouse.mouseMove(hoverItem.getCoordinates());
			  driver.findElement(By.xpath("//*[starts-with(@id, 'share-')]")).click();

		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase widgetOptionsVerify in class WidgetOptions",e);
		}
	}

}
