package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;

public class MetacognitiveReport {

	public void mouseHoverOnDot(int chapterNumber)
	{
		try
		{
		List<WebElement> allDots = Driver.driver.findElements(By.cssSelector("g.highcharts-markers > path"));
		Locatable hoverItem = (Locatable) allDots.get(chapterNumber);
		Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
		mouse.mouseMove(hoverItem.getCoordinates());
		Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper mouseHoverOnDot in class MetacognitiveReport.",e);
		}
	}
	
	public void openPracticeTestFromMetacognitiveReportPage(String chapterName)
	{
		try
		{
			
			List<WebElement> practicebuttons = Driver.driver.findElements(By.cssSelector("div[chname='"+chapterName+"']"));
	  		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",practicebuttons.get(practicebuttons.size()-1)); 
	  		Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper openPracticeTestFromMetacognitiveReportPage in class MetacognitiveReport.", e);
		}
	}
}
