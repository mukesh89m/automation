package com.snapwiz.selenium.tests.staf.datacreation.apphelper;

import com.snapwiz.selenium.tests.staf.datacreation.Config;
import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import org.testng.Assert;

public class RunScheduledJobs extends Driver
{
	public void runScheduledJobs()
	{
		try
		{
            new DirectLogin().CMSLogin();
            driver.get(Config.baseURL+"/secure/runJob?ajax=true&jobname=ClassSectionActivityJob");
		}
		catch(Exception e)
		{
			Assert.fail("exception in app helper runScheduledJobs",e);
		}
	}

    public void runScheduledJobsForLSDashboard()
    {
        try
        {
            new DirectLogin().CMSLogin();
            Driver.driver.get(Config.baseURL+"/secure/runJob?ajax=true&jobname=StudentParticipationCourseSummaryJob");
            Driver.driver.get(Config.baseURL+"/secure/runJob?ajax=true&jobname=ClassSectionPerformanceJob");
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
            new DirectLogin().CMSLogin();
            driver.get(Config.baseURL+"/secure/runJob?ajax=true&jobname="+jobName);
        }
        catch(Exception e)
        {
            Assert.fail("exception in app helper runScheduledJob",e);
        }
    }

}
