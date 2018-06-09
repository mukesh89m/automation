package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Config;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;

public class RunScheduledJobs 
{
	public void runScheduledJobs()
	{
		try
		{
			new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DirectLogin().CMSLogin();
            Driver.driver.get(Config.baseURL+"/secure/runJob?ajax=true&jobname=ClassSectionActivityJob");
            Thread.sleep(120000);
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
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DirectLogin().CMSLogin();
            Driver.driver.get(Config.baseURL+"/secure/runJob?ajax=true&jobname=StudentParticipationCourseSummaryJob");
            Thread.sleep(180000);
            Driver.driver.get(Config.baseURL+"/secure/runJob?ajax=true&jobname=ClassSectionPerformanceJob");
            Thread.sleep(120000);
            Driver.driver.quit();
            Driver.startDriver();
        }
        catch(Exception e)
        {
            Assert.fail("exception in app helper runScheduledJobsForDashboard",e);
        }
    }

    public void runScheduledJob(String jobName)
    {
        try
        {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DirectLogin().CMSLogin();
            Driver.driver.get(Config.baseURL+"/secure/runJob?ajax=true&jobname="+jobName);
            Thread.sleep(120000);
            Driver.driver.quit();
            Driver.startDriver();
        }
        catch(Exception e)
        {
            Assert.fail("exception in app helper runScheduledJob",e);
        }
    }

}
