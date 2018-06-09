package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.InstructorDashboardMatrix;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.RunScheduledJobs;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class InstructorDashBorad
{
	@Test(priority=1,enabled=true)
	public void instructordashboard()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("908"); //login as instructor
			new LoginUsingLTI().ltiLogin("9081"); //login as student
			new SelectChapterForTest().selectchapterfortest(0, 2);
			new DiagnosticTest().attemptAllCorrect(2, false, false);
			new RunScheduledJobs().runScheduledJobs();
			new LoginUsingLTI().ltiLogin("908");//login as instructor
			List<WebElement> allactivity=Driver.driver.findElements(By.cssSelector("span[class='idb-student-activity-time-line idb-student-activity-filter']"));
			String activitytext=allactivity.get(0).getAttribute("title");//check days 

			if(!activitytext.contains("Last 7 Days"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("last 7 days activity text not shown");
			}
			String activitytext1=allactivity.get(1).getAttribute("title");//check week

			if(!activitytext1.contains("Last 5 Weeks"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("last 5 days activity text not shown");
			}
			String activitytext2=allactivity.get(2).getAttribute("title");//check all

			if(!activitytext2.contains("All"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("all  activity text not shown");
			}
			List<WebElement> dropdowns=Driver.driver.findElements(By.className("sbSelector"));//.getAttribute("title");//check class section name
            if(!dropdowns.get(0).getAttribute("title").equals("View class reports"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("View class reports dropdown not shown");
            }

            if(!dropdowns.get(1).getAttribute("title").equals(Config.context_title))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("class section dropdown not shown");
			}
			boolean graph=Driver.driver.findElement(By.className("highcharts-axis-labels")).isDisplayed();//check graph
			if(graph==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("graph not shown at dashboard");
			}
			String studentactive=new InstructorDashboardMatrix().instructordashboardmatrixtext(0);//fetch student visited activity
			if(!studentactive.contains("Students Visited"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("student visited activity matrix not shown");
			}
			String questionattempd=new InstructorDashboardMatrix().instructordashboardmatrixtext(1);//fetch attempt question
			if(!questionattempd.contains("Questions Attempted"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("question attempted matrix not shown");
			}
			String timespent=new InstructorDashboardMatrix().instructordashboardmatrixtext(2);//time spent
			if(!timespent.contains("Spent"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("time spent matrix not shown");
			}
			String discussion=new InstructorDashboardMatrix().instructordashboardmatrixtext(3);	//discussion
			if(!discussion.contains("Discussions"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("discussion text matrix");
			}
			String moreactivity=new InstructorDashboardMatrix().instructordashboardmatrixtext(4);//more activity
			if(!moreactivity.contains("More Activity This Period"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("more activity matrix not shown");
			}
			String url=Driver.driver.getCurrentUrl();//fetch current url
			List<WebElement> allmatrix=Driver.driver.findElements(By.className("idb-student-activity-data-card"));//fetch list of all matrix
			allmatrix.get(0).click();//click on student visited
			Thread.sleep(2000);
			String urlafterclickonstudentvisited=Driver.driver.getCurrentUrl();
			if(!url.equals(urlafterclickonstudentvisited))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("student visited link is clickable");
			}
			allmatrix.get(1).click();//click on question attempted
			Thread.sleep(2000);
			String urlafterclickonquestionattempted=Driver.driver.getCurrentUrl();
			if(!url.equals(urlafterclickonquestionattempted))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("attempte question link is clickable");
			}
			allmatrix.get(2).click();//click on spent hours
			Thread.sleep(2000);
			String urlafterclickonspenthours=Driver.driver.getCurrentUrl();
			if(!url.equals(urlafterclickonspenthours))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("spent hours link is clickable");
			}
			allmatrix.get(4).click();//click on more activity
			Thread.sleep(2000);
			String urlafterclickonmoreactivity=Driver.driver.getCurrentUrl();
			if(!url.equals(urlafterclickonmoreactivity))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("more activity link is clickable");
			}
			allmatrix.get(3).click();//click on discussion
			Thread.sleep(2000);
			int notification=Driver.driver.findElements(By.className("al-notification-message-body")).size();
			int discussionpagetitle = Driver.driver.findElements(By.className("instructor-question-discussion-text")).size();
			if(notification ==0 && discussionpagetitle == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("discussion link is not clickable");
			}
		}
		catch (Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase InstructorDashBorad in class InstructorDashBorad ",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void InsVisitedStudent()
	{
		try 
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("911");//login as instructor with different class section
			int visitedstudentwithdifferentsection=new InstructorDashboardMatrix().instructordashboardmatrix(0);
			if(visitedstudentwithdifferentsection!=0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("student visited more the 0");
			}
			new LoginUsingLTI().ltiLogin("912");//login as instructor with default class section
			int visitedstudentwithsamesection1=new InstructorDashboardMatrix().instructordashboardmatrix(0);
			if(visitedstudentwithsamesection1==0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("student visited equal to 0");
			}
			new LoginUsingLTI().ltiLogin("911_student");//login as student with new class section
			new RunScheduledJobs().runScheduledJobs();
            Thread.sleep(120000); //waiting for 2 minutes after running the scheduled jobs
			new LoginUsingLTI().ltiLogin("911");//login as instructor with new class section
			int visitedstudentafteronestudentlogsin=new InstructorDashboardMatrix().instructordashboardmatrix(0);

			if(visitedstudentafteronestudentlogsin != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("student visited not equal to 1");
			}
		} 
		catch (Exception e) 
		{ 
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase method InsVisitedStudent in class InstructorDashBorad",e);
		}
		
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}

}
