package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.orion.apphelper.TLOExpected;
import com.snapwiz.selenium.tests.staf.orion.apphelper.TLOInPracticeTestValidate;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect;

public class RepeatFlow {
	
	@Test(priority =1 , enabled = true)
	public void repeatFlow()
	{
		try
		{
			Driver.startDriver();
			List<String> tlonames = new PracticeTest().tloNames();		//Get TLO names
			new LoginUsingLTI().ltiLogin("179");
			new DiagnosticTest().startTest(0, 3);
			new DiagnosticTest().attemptAllCorrect(3, false, false);
			new Navigator().orionDashboard(); //Go to Orion Dashboard
			Thread.sleep(5000);
			
     		List<Integer> tloquestionattemptedcount = new PracticeTest().attemptedCount(); //Get the no. of questions attempted in each TLO
     		new PracticeTest().startTest(); //start the practice test
			List<String> expectedtlos;
			boolean tlofound;
			int lower = 1;
			int upper = 70;
			for(int i=1;i<=500;i++)
			{
				System.out.println("Question No. "+i);
			int R = (int)(Math.random() * (upper - lower)) + lower;
			expectedtlos = new TLOExpected().tloExpected(tloquestionattemptedcount,tlonames,"179");
			for(String expectedtlo : expectedtlos) System.out.println("Expected TLO "+expectedtlo);
			tlofound = new TLOInPracticeTestValidate().tloInPracticeTest(expectedtlos);		
				if(tlofound == false)
				{
				Assert.fail("TLO found is not as expected");	
				}			
			
			tloquestionattemptedcount = new TLOInPracticeTestValidate().updateAttemptedCount(tlonames, tloquestionattemptedcount);
			new PracticeTest().questionPresentCheckRepeatFlow("179");	
			if(R >=1 && R<=15)
			new SelectAnswerAndSubmit().practiceTestAttempt(3, 1, "skip", false, false);
			if(R >15 && R<=25)
			new SelectAnswerAndSubmit().practiceTestAttempt(3, 1, "incorrect", false, false);
			else if(R>25)
			new SelectAnswerAndSubmit().practiceTestAttempt(3, 1, "correct", false, false);
			
			}
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase repeatFlow in class RepeatFlow",e);
		}
	}

}
