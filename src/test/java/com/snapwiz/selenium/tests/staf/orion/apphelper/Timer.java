package com.snapwiz.selenium.tests.staf.orion.apphelper;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import org.openqa.selenium.By;

public class Timer 
{
	public boolean timer()
	{
		try
		{
			boolean timershow=Driver.driver.findElement(By.id("assessmentTimer")).isDisplayed();
			Thread.sleep(5000);
			String timevalue=Driver.driver.findElement(By.className("timevalue")).getAttribute("timetaken");
			int inttimevalue = Integer.parseInt(timevalue);
			Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[3]")).click();	
			Thread.sleep(5000);
			String timevalue1=Driver.driver.findElement(By.className("timevalue")).getAttribute("timetaken");
			int inttimevalue1 = Integer.parseInt(timevalue1);
			Thread.sleep(5000);
			Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[4]")).click();
			Driver.driver.findElement(By.cssSelector("input[type='button']")).click();
			String timevalue2=Driver.driver.findElement(By.className("timevalue")).getAttribute("timetaken");
			int inttimevalue2 = Integer.parseInt(timevalue2);
			if(timershow == true && inttimevalue < inttimevalue1 && inttimevalue2 == 0 )
			{
				return true;
			}
			else
			{
				
				return false;
			}
			
		}
		catch(Exception e)
		{
			
			
			return false;
		}
	}

}
