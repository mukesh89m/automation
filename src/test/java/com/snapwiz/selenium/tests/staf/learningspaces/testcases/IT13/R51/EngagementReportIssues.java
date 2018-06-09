package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R51;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Sumit on 8/20/2014, testcase is created as per bug 10944
 */
public class EngagementReportIssues extends Driver{

    @Test
    public void engagementReportIssues()
    {
        try
        {
           new Assignment().create(10944);
            new LoginUsingLTI().ltiLogin("10944_1");//create student

            new LoginUsingLTI().ltiLogin("10944");//log in as instructor
            new Assignment().assignToStudent(10944);

            new LoginUsingLTI().ltiLogin("10944_1");
            new Assignment().submitAssignmentAsStudent(10944);

            new RunScheduledJobs().runScheduledJobsForDashboard();

            new LoginUsingLTI().ltiLogin("10944");//log in as instructor
            new Navigator().NavigateTo("Engagement Report");//go to Engagement Report
            driver.findElement(By.cssSelector("div.students-performance-report-content-row.student-performance-content-odd-row > div.students-report-content-body-third > div.row-non-assigned-status > div.fLeft > span")).click();//Clicking on Reading/Assessments
            Thread.sleep(5000);
            String url = driver.getCurrentUrl();
            if(!url.contains("/eTextBook"))
                Assert.fail("1. Clicking on Reading/Assessments from engagement report, and When instructor clicks on a lesson it navigates to some other page.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case engagementReportIssues in class EngagementReportIssues.", e);
        }
    }
}
