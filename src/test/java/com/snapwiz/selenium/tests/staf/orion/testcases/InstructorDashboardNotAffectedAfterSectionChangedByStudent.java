package com.snapwiz.selenium.tests.staf.orion.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiscussionValidate;
import com.snapwiz.selenium.tests.staf.orion.apphelper.InstructorDashboardMatrix;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.RunScheduledJobs;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;
import com.snapwiz.selenium.tests.staf.orion.uihelper.ComboBox;



public class InstructorDashboardNotAffectedAfterSectionChangedByStudent
{
	@Test
	public void instructorDashboardNotAffectedAfterSectionChangedByStudent()
	{
		try 
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("462");//login as student of first class section
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 6, "correct", false, false, true);//attempt diagnostic test\
			new Navigator().orionDashboard();
			Thread.sleep(2000);
			new PracticeTest().startTest();//Start practice test
			new PracticeTest().practiceTestAttempt(3, 6, "correct", false, false);
			new RunScheduledJobs().runScheduledJobs();
			new LoginUsingLTI().ltiLogin("4622"); //login as instructor of first class section
			int studentvisited=new InstructorDashboardMatrix().instructordashboardmatrix(0);
			int questionattempt=new InstructorDashboardMatrix().instructordashboardmatrix(1);
			int timespent=new InstructorDashboardMatrix().instructordashboardmatrix(2);
			int discussion=new InstructorDashboardMatrix().instructordashboardmatrix(3);
			new Navigator().NavigateToInstructorReport();
			Driver.driver.findElement(By.id("al-performance-report")).click();//Click on my report
			Thread.sleep(2000);
			String performanceproficiency=new DiscussionValidate().insvalidateperformanceproductivitychallenging();
			
			new ComboBox().selectValue(1, "Productivity Report");//select productivity
			Thread.sleep(2000);
			String productivityproficiency=new DiscussionValidate().insvalidateperformanceproductivitychallenging();
				
			Driver.driver.findElement(By.id("al-metacognitive-report")).click();//click on metacongitive result
			String metacongitiveproficiency=new DiscussionValidate().insvalidateperformanceproductivitychallenging();
			
			Driver.driver.findElement(By.id("al-most-challenging-activity")).click();//Click on most challenging activity
			Thread.sleep(2000);
			String challengeproficiency=new DiscussionValidate().insvalidateperformanceproductivitychallenging();
					
			new LoginUsingLTI().ltiLogin("4621");//login as student with the same id as before by changing the class section of student
			new SelectChapterForTest().selectchapterfortest(0, 2);//select chapter for test
			new DiagnosticTest().DiagonesticTestQuitBetween(2, 6, "correct", false, false, true);//attempt diagnostic test\
			new RunScheduledJobs().runScheduledJobs();
			new LoginUsingLTI().ltiLogin("4622");//login as instructor of same class section of 1st student
			int studentvisited1=new InstructorDashboardMatrix().instructordashboardmatrix(0);
			int questionattempt1=new InstructorDashboardMatrix().instructordashboardmatrix(1);
			int timespent1=new InstructorDashboardMatrix().instructordashboardmatrix(2);
			int discussion1=new InstructorDashboardMatrix().instructordashboardmatrix(3);
			if(studentvisited!=studentvisited1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("dash board activity (student visited)not change after  student with other  section do some activity.");
			}
			if(questionattempt!=questionattempt1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("dash board activity (question attempted)not change after  student with other  section do some activity.");
			}
			if(timespent!=timespent1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("dash board activity (time spent)not change after  student with other  section do some activity.");
			}
			if(discussion!=discussion1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("dash board activity (Discussion)not change after  student with other  section do some activity.");
			}
			new Navigator().NavigateToInstructorReport();
			String proficiency=new DiscussionValidate().insvalidateperformanceproductivitychallenging();

			new LoginUsingLTI().ltiLogin("4623"); //login as instructor of second class section
			new Navigator().NavigateToInstructorReport();
			Driver.driver.findElement(By.id("al-performance-report")).click();//Click on my report
			Thread.sleep(2000);
			String performanceproficiency1=new DiscussionValidate().insvalidateperformanceproductivitychallenging();
			new ComboBox().selectValuewithtitle(1, "Productivity Report");//select productivity
			Thread.sleep(2000);
			String productivityproficiency1=new DiscussionValidate().insvalidateperformanceproductivitychallenging();
			Driver.driver.findElement(By.id("al-metacognitive-report")).click();//click on metacongitive result
			String metacongitiveproficiency1=new DiscussionValidate().insvalidateperformanceproductivitychallenging();
            Thread.sleep(2000);
			Driver.driver.findElement(By.id("al-most-challenging-activity")).click();//Click on most challenging activity
			Thread.sleep(2000);
			String challengeproficiency1=new DiscussionValidate().insvalidateperformanceproductivitychallenging();
			if(performanceproficiency1.equals(performanceproficiency))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("performnace proficency equal after instruction login as diffrent section is same as 2nd class section of student ");
			}
			if(productivityproficiency1.equals(productivityproficiency))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("productivity proficiency1 equal after instruction login as diffrent section as same as 2nd class section of student ");
			}
			if(metacongitiveproficiency1.equals(metacongitiveproficiency))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("metacongitive proficiency1 equal after instruction login as diffrent section as same as 2nd class section of student ");
			}
			if(challengeproficiency1.equals(challengeproficiency))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("challenge proficiency1 equal after instruction login as diffrent section as same as 2nd class section of student ");
			}
		} 
		catch (Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase instructorDashboardNotAffectedAfterSectionChangedByStudent",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}

}
