package com.snapwiz.selenium.tests.staf.orion.uihelper;

import org.openqa.selenium.By;

import com.snapwiz.selenium.tests.staf.orion.Driver;

public class PerformanceReport 
{
	public void performancereport()throws Exception
	{
		int performancereport=Driver.driver.findElements(By.id("al-performance-report")).size();
		if(performancereport>=1)
		{
			Driver.driver.findElement(By.id("al-performance-report")).click();//click on performance report if available
		}
	}
}
