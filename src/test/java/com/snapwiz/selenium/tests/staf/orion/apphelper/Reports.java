package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;


public class Reports {

	public int quadrantCheck(int markerIndex)
	{
		int quadrant = 0;
		try
		{
			List<WebElement> axis = Driver.driver.findElements(By.cssSelector("div.highcharts-container > svg > path"));
			
			Point x =  axis.get(0).getLocation();
			Point y =  axis.get(1).getLocation();
			int xLimit = x.x;
			int yLimit = y.y;
			System.out.println("xlimit" +xLimit); 			System.out.println("ylimit" +yLimit);
			List<WebElement> markers = Driver.driver.findElements(By.cssSelector("g.highcharts-markers > path"));
			Point p = markers.get(markerIndex).getLocation();
			System.out.println(p);
			int x_location = p.x; int y_location = p.y;
			
			if(x_location > xLimit && y_location < yLimit)
				quadrant = 1;
			else if(x_location < xLimit && y_location < yLimit)
				quadrant = 2;
			else if(x_location < xLimit && y_location > yLimit)
				quadrant = 3;
			else if(x_location > xLimit && y_location > yLimit)
				quadrant = 4;
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in app helper quadrantCheck in class Reports",e);			
		}
		return quadrant;
	}
}
