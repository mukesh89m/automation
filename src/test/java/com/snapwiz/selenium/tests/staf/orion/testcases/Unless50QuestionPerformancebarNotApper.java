package com.snapwiz.selenium.tests.staf.orion.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.ProficencyGraphValue;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class Unless50QuestionPerformancebarNotApper
{
	@Test(priority=1,enabled=true)
	public void unless50QuestionPerformancebarNotApper()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("1");//login as student
			new SelectChapterForTest().selectchapterfortest(0,3);//select chapter for test 
			new DiagnosticTest().DiagonesticTestQuitBetween(3, 19, "correct",false,false,true);//select answer and submit all the question of chapter
			new Navigator().orionDashboard();//navigate to orion dsah board
			String opticity=new ProficencyGraphValue().proficencygraphvalueTLO(3);//fetch style for TLO graph
			System.out.println(opticity);
			if(!opticity.contains("0."))
				Assert.fail(" TLO proficency graph not transparent");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method unless50QuestionPerformancebarNotApper in class Unless50QuestionPerformancebarNotApper",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void Attempt10Question()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("2");//login as student
			new SelectChapterForTest().selectchapterfortest(0,3);//select chapter for test 
			new DiagnosticTest().DiagonesticTestQuitBetween(3, 10, "correct",false,false,true);//select answer and submit all the question of chapter
			Driver.driver.findElement(By.cssSelector("img[title='ORION Dashboard']")).click();//redirect to orion dashboard
			Thread.sleep(2000);
			String opticity=new ProficencyGraphValue().proficencygraphvaluechapter(0);
			if(!opticity.contains("0.2"))
				Assert.fail(" chapter level proficency graph not transparent");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method Attempt10Question in class Unless50QuestionPerformancebarNotApper",e);
		}
	}
	
	@Test(priority=3,enabled=true)
	public void Attempt5Question()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("3");//login as student
			new SelectChapterForTest().selectchapterfortest(0,3);//select chapter for test 
			new DiagnosticTest().DiagonesticTestQuitBetween(3, 5, "correct",false,false,true);//select answer and submit all the question of chapter
			Driver.driver.findElement(By.cssSelector("img[title='ORION Dashboard']")).click();
			Thread.sleep(2000);
			int chpterproficency=Driver.driver.findElements(By.className("al-recommended-focus-area-proficiency-wrapper")).size();
			if(chpterproficency!=0)
				Assert.fail("chapter level proficency level graph shown when attemp only 5 question");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method Attempt10Question in class Unless50QuestionPerformancebarNotApper",e);
		}
	}
	@AfterMethod
	  public void tearDown() throws Exception 
	  {
	    	Driver.driver.quit();
	  }
}

