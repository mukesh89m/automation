package com.snapwiz.selenium.tests.staf.orion.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Config;

public class RunScheduledJobs extends Driver
{
	public void runScheduledJobs()
	{
		try
		{
            WebDriver driver=Driver.getWebDriver();
			new DirectLogin().CMSLogin();
            driver.get(Config.baseURL+"/secure/runJob?ajax=true&jobname=ClassSectionActivityJob");
            Thread.sleep(120000);
            ReportUtil.log("Running scheduled jobs", "Job is run so that the instructor tiles get updated", "info");
        }
		catch(Exception e)
		{
			Assert.fail("exception in app helper runScheduledJobs",e);
		}
	}

    public void runScheduledJob(String jobName)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            new DirectLogin().CMSLogin();
            driver.get(Config.baseURL + "/secure/runJob?ajax=true&jobname=" + jobName);
            Thread.sleep(120000);
        }
        catch(Exception e)
        {
            Assert.fail("exception in app helper runScheduledJob",e);
        }
    }

}
