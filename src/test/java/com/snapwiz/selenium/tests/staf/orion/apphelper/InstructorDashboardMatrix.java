package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;
/*
 * return dash board matrix data
 * 0-student visited
 * 1-question attempt
 * 2-second spend
 * 3-discussion
 * 4-more activity
 */
public class InstructorDashboardMatrix 
{
	public int instructordashboardmatrix(int datawant)
	{
		int activitydata=0;
		try 
		{
			List<WebElement> data=Driver.driver.findElements(By.className("idb-student-activity-time-spent-data-count"));
			String fetchdata=data.get(datawant).getText();
			activitydata=Integer.parseInt(fetchdata);
		} 
		catch (Exception e) 
		{
			Assert.fail("Exception in app helper InstructorDashboardMatrix",e);

		}
		return activitydata;
	}
	public int discussioncount()
	{
		int activitydata=0;
		try 
		{
		
			String fetchdata=Driver.driver.findElement(By.className("instructor-discussion-notification")).getText();
			activitydata=Integer.parseInt(fetchdata);
		} 
		catch (Exception e) 
		{
			Assert.fail("Exception in app helper discussioncount",e);

		}
		return activitydata;
	}
	public String instructordashboardmatrixtext(int datawant)
	{
		String activitydata=null;
		try 
		{
			List<WebElement> data=Driver.driver.findElements(By.className("idb-student-activity-time-spent-data-label"));
			activitydata=data.get(datawant).getText();
			
		} 
		catch (Exception e) 
		{
			Assert.fail("Exception in app helper instructordashboardmatrixtext",e);

		}
		return activitydata;
	}

}
