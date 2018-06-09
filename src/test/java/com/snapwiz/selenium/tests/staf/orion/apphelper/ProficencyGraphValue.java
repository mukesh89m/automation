package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;

public class ProficencyGraphValue 
{
	/*
	 * @Brajesh
	 *Return chapter level transparent value %
	 */
	public String proficencygraphvaluechapter(int chpterno)
	{
		String style=null;
		try
		{
			int index=0;
			List<WebElement> chpterlevelproficency=Driver.driver.findElements(By.className("al-recommended-focus-area-proficiency-filler"));//fetch all chapter graph
			for(WebElement element:chpterlevelproficency)
			{
				if(index==chpterno)
				{
					 style= element.getAttribute("style");
				}
				index++;
			}
	}
		catch(Exception e)
		{
			Assert.fail("Exception in method proficencygraphvaluechapter",e);
		}
		return style;
	}

	/*
	 * @Brajesh
	 *Return TLO level transparent value %
	 */
	public String proficencygraphvalueTLO(int TLONo)
	{
		String style=null;
		try
		{
			int index=0;
			List<WebElement> chpterlevelproficency=Driver.driver.findElements(By.className("al-proficiency-wrapper"));//fetch all TLO graph
			for(WebElement element:chpterlevelproficency)
			{
				if(index==TLONo)
				{
					 style= element.getAttribute("style");
				}
				index++;
			}
	}
		catch(Exception e)
		{
			Assert.fail("Exception in method proficencygraphvaluechapter",e);
		}
		return style;
	}
	/*
	 * @Brajesh
	 *Return TLO level proficiency %
	 */
	public int proficencyPercentagechapterTLO(int TLO)
	{
		int percentage=0;
		try
		{
			int index=0;
			List<WebElement> chpterlevelproficency=Driver.driver.findElements(By.className("al-proficiency-percentage"));//fetch all TLO graph %
			for(WebElement element:chpterlevelproficency)
			{
				if(index==TLO)
				{
					String perce= element.getText();
					percentage=Integer.parseInt(perce.substring(0, 2));
				}
				index++;
			}
	}
		catch(Exception e)
		{
			Assert.fail("Exception in method proficencyPercentagechapterTLO",e);
		}
		return percentage;
	}
	/*
	 * @Brajesh
	 * return proficiency % of each chapter
	 */
	public int proficencyPercentagechapter(int chapter)
	{
		int percentage=0;
		try
		{
			int index=0;
			List<WebElement> chpterlevelproficency=Driver.driver.findElements(By.className("al-recommended-focus-area-proficiency-value"));//fetch all Chapter graph %
			for(WebElement element:chpterlevelproficency)
			{
				if(index==chapter)
				{
					String perce= element.getText();
					percentage=Integer.parseInt(perce.substring(0, 2));
				}
				index++;
			}
	}
		catch(Exception e)
		{
			Assert.fail("Exception in method proficencygraphvaluechapter",e);
		}
		return percentage;
	}
}
