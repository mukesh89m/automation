package com.snapwiz.selenium.tests.staf.orion.testcases;


import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.TLOExpected;
import com.snapwiz.selenium.tests.staf.orion.apphelper.TLOInPracticeTestValidate;

public class DiagTestAllWrong {
	@Test(priority=1,enabled=true) //Akansh
	public void diagTestAllWrong()
	{
		try
		{				
		Driver.startDriver();
		new LoginUsingLTI().ltiLogin("11");
		List<String> tlonames = new PracticeTest().tloNames();		//Get TLO names				
		new SelectChapterForTest().selectchapterfortest(0, 3); //Start the Diagnostic test
		new DiagnosticTest().attemptAllCorrect(0, false, false);		//Attempt all answers as incorrect in diagnostic test
		new Navigator().orionDashboard(); //Go to Orion Dashboard
		Thread.sleep(5000);
		List<Integer> tloquestionattemptedcount = new PracticeTest().attemptedCount(); //Get the no. of questions attempted in each TLO	
		new PracticeTest().startTest(); //start the practice test
		List<String> expectedtlos;
		boolean tlofound;
		for(int i=1;i<=100;i++)
		{
		expectedtlos = new TLOExpected().tloExpected(tloquestionattemptedcount,tlonames,"11");
		tlofound = new TLOInPracticeTestValidate().tloInPracticeTest(expectedtlos);		
			if(tlofound == false)
			{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("TLO found is not as expected");	
			}
		tloquestionattemptedcount = new TLOInPracticeTestValidate().updateAttemptedCount(tlonames, tloquestionattemptedcount);
		
		new PracticeTest().questionPresentCheck("11");		
		new PracticeTest().AttemptCorrectAnswer(3);
		
		}	
	}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase DiagTestAllWrong",e);
		}
	} //Method close
	
	
} //Class close
