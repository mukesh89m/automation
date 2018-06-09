package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class RunScheduledJobs extends Driver
{
	public void runScheduledJobs()
	{
        WebDriver driver=Driver.getWebDriver();
		try
		{
			new DirectLogin().CMSLogin();
            driver.get(Config.baseURL+"/secure/runJob?ajax=true&jobname=ClassSectionActivityJob");
            Thread.sleep(120000);
			driver.quit();
            new ReInitDriver().startDriver("firefox");
		}
		catch(Exception e)
		{
			Assert.fail("exception in app helper runScheduledJobs",e);
		}
	}

    public void runScheduledJobsForDashboard()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new DirectLogin().CMSLogin();
            driver.get(Config.baseURL+"/secure/runJob?ajax=true&jobname=StudentParticipationCourseSummaryJob");
            Thread.sleep(180000);
            driver.get(Config.baseURL+"/secure/runJob?ajax=true&jobname=ClassSectionPerformanceJob");
            Thread.sleep(120000);
            driver.quit();
            new ReInitDriver().startDriver("firefox");
        }
        catch(Exception e)
        {
            Assert.fail("exception in app helper runScheduledJobsForDashboard",e);
        }
    }

    public void runScheduledJob(String jobName)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new DirectLogin().CMSLogin();
            driver.get(Config.baseURL+"/secure/runJob?ajax=true&jobname="+jobName);
            Thread.sleep(120000);
            driver.quit();
            driver=new ReInitDriver().startDriver("firefox");
        }
        catch(Exception e)
        {
            Assert.fail("exception in app helper runScheduledJob",e);
        }
    }

}
