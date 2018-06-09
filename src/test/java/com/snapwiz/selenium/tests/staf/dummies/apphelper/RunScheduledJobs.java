package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Config;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;

public class RunScheduledJobs 
{
	public void runScheduledJobs()
	{
		try
		{
			new DirectLogin().CMSLogin();
			Driver.driver.get(Config.baseURL+"/secure/runScheduledJobs");
			Driver.driver.quit();
			Driver.startDriver();
		}
		catch(Exception e)
		{
			Assert.fail("exception in app helper runScheduledJobs",e);
		}
	}
	public void runScheduledJobsForDashboard()
    {
        try
        {
            new DirectLogin().CMSLogin();
            Driver.driver.get(Config.baseURL+"/secure/runStudentCourseSummaryJob");
            Thread.sleep(300000);
            Driver.driver.quit();
            Driver.startDriver();
        }
        catch(Exception e)
        {
            Assert.fail("exception in app helper runScheduledJobsForDashboard",e);
        }
    }
}
