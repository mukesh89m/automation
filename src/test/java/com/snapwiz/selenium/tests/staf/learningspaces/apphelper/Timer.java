package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Timer extends Driver
{
	public boolean timer()
	{
        boolean bool = false;
		WebDriver driver=Driver.getWebDriver();
		try
		{
			boolean timershow=driver.findElement(By.id("assessmentTimer")).isDisplayed();
            String timevalue2=driver.findElement(By.className("timevalue")).getAttribute("timetaken");
            int inttimevalue2 = Integer.parseInt(timevalue2);
            Thread.sleep(5000);
			String timevalue=driver.findElement(By.className("timevalue")).getAttribute("timetaken");
			int inttimevalue = Integer.parseInt(timevalue);
			driver.findElement(By.className("tab")).click();//go to another tab
			Thread.sleep(5000);
			String timevalue1=driver.findElement(By.className("timevalue")).getAttribute("timetaken");
			int inttimevalue1 = Integer.parseInt(timevalue1);
			Thread.sleep(5000);
            driver.findElement(By.className("tab")).click();//go to another tab
            new Click().clickByclassname("tab");//go to practice test tab
			if(timershow == true && inttimevalue < inttimevalue1 && inttimevalue2 == 0 )
                bool = true;
			else
                bool = false;

			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in timer method of apphelper Timer.", e);
		}
        return bool;
    }

}
