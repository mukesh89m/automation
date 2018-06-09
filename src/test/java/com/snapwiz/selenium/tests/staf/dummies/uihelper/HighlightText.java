package com.snapwiz.selenium.tests.staf.dummies.uihelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Config;
import com.snapwiz.selenium.tests.staf.dummies.Driver;

public class HighlightText {

	public void highlightText(String color)
	{
		try{
		WebElement element = (new WebDriverWait(Driver.driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p[class='nonindent default']")));
		Actions actions = new Actions(Driver.driver);		
		if(Config.browser.equals("chrome"))
		{
		actions.moveToElement(element, 0, 0)
			.doubleClick()		
		    .release()
		    .perform();
		}
		else
		{
			actions.moveToElement(element, 0, 0)
			    .clickAndHold()
			    .moveByOffset(200, 0)
			    .release()
			    .perform();
		}
		
		List<WebElement> allColors = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'pcolor highlightcolor')]"));
		if(color.equalsIgnoreCase("yellow"))
			allColors.get(0).click();
		if(color.equalsIgnoreCase("blue"))
			allColors.get(1).click();
		if(color.equalsIgnoreCase("green"))
			allColors.get(2).click();
		if(color.equalsIgnoreCase("red"))
			allColors.get(3).click();
		Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in ui helper HighlightText in highlightText method.", e);
		}
	}
	
	public void removeHighlight()
	{
		try
		{		
			List<WebElement> highligted = Driver.driver.findElements(By.xpath("//*[starts-with(@type, '8')]"));
			//List<WebElement> highligted = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'highlighted annotation')]")); 
			highligted.get(0).click();
			Thread.sleep(3000);
			WebElement removehighlight = Driver.driver.findElement(By.cssSelector("div[class='ptext remove-annotation']"));
			if(!removehighlight.getText().equals("Remove Highlight"))
				Assert.fail("Remove Highlight Option not present after clicking on a highlighted text.");
		    removehighlight.click();
		    Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper HighLightValidate in method removeHighlight",e);
		}
	}
	public void validateHighlight(String color)
	{
		try
		{
			color=color.toLowerCase();
			String colorhex,colorx,colory,colorz,rgbhiglighted=null;
			colorhex = Driver.driver.findElement(By.xpath(".//*[@id='timeline_latest']/div/div/div/div/div[2]/div/div[3]/span")).getCssValue("background-color");
			if(colorhex.length()>10)
	          {
	             colorx= colorhex.substring(5, 8);
	             colory= colorhex.substring(10,13);
	             colorz = colorhex.substring(15, 18);
	          
	             rgbhiglighted = String.format("#%02x%02x%02x", Integer.parseInt(colorx), Integer.parseInt(colory), Integer.parseInt(colorz));
	            
	          }
	          if(!rgbhiglighted.equals(color))
	        	  Assert.fail("Selected Text Not highlighted with the desired color in My Journal");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper HighLightValidate in method validateHighlight",e);
		}
	}
		
}
