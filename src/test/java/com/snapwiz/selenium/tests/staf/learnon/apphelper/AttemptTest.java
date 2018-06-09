package com.snapwiz.selenium.tests.staf.learnon.apphelper;


import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.SelectAnswerAndSubmit;
import org.openqa.selenium.By;
import org.testng.Assert;

public class AttemptTest 
{

	//Attempt Daigonestics Test
	public void Diagonestictest()
	{
		
		int testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();
		
		while(testend==1)
		{
			try
			{
			//Thread.sleep(3000);
			testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();
			if(testend == 0)
			break;
			//Driver.driver.findElements(By.className("al-diag-test-timer"));		
			new SelectAnswerAndSubmit().daigonestianswersubmit("A");
			}
			catch(Exception e)
			{
				Assert.fail("Question or element not found",e);
			}
			
					
		}
		
	}
	//Attempt adaptive test
	public void AdaptiveTest(int numberofquestion)
	{
		for(int i=0;i<=numberofquestion;i++)
		{
			new SelectAnswerAndSubmit().Adaptiveasnswersubmit("B");
		}
		
	}
	public void StaticTest()
	{

		int testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();
		
		while(testend==1)
		{
			try
			{
			Thread.sleep(3000);
			testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();
			if(testend == 0)
			break;
			Driver.driver.findElements(By.className("al-diag-test-timer"));		
			new SelectAnswerAndSubmit().staticanswersubmit("A");	
			}
			catch(Exception e)
			{
				Assert.fail("Question or element not found",e);
			}
			
					
		}
	}
}
