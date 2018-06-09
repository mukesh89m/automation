package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;

public class PerformancereportPageProficency 
{
	public int performancereportPageProficency()
	{
		int proficency=0;
		try
		{
			new Navigator().NavigateTo("Proficiency Report");//navigate to proficency report by student
			String proficencytext1=Driver.driver.findElement(By.id("al-cource-proficiency-summary")).getText();//fetch proficency report of user from pro
			proficency=Integer.parseInt(proficencytext1.substring(0, 2));//
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method PerformancereportPageProficency",e);
		}
		return proficency;
	}

}
