package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AttemptTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PerfomancePageVerification;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectAnswerAndSubmit;

public class PerformanceFrameCheckForDiagnostic extends Driver
{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PerformanceFrameCheckForDiagnostic");
	
	@Test
	public void performanceFrameCheckForDiagnostic()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1090");
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			
			if (new PerfomancePageVerification().performanceTabBarGraph())
			{
				logger.log(Level.INFO,"Perfomance tab is present with Bar graph  'Performance in last 10 questions' as first item and 'About this question' in second position");
			}
			else
			{
				Assert.fail("Perfomance tab is not present with Bar graph  'Performance in last 10 questions' as first item and 'About this question' in second position");
			}
			if(new PerfomancePageVerification().checkforTextAlongBarGraph())
			{
				logger.log(Level.INFO,"Along x-axis 'Question' is present and along y-axis 'Easy' and 'Hard' is present");
			}
			else
			{
				Assert.fail("Along x-axis 'Question' is not present and along y-axis 'Easy' and 'Hard' is not present");
			}
			new SelectAnswerAndSubmit().daigonestianswersubmit("D");
			if(new PerfomancePageVerification().checkBarAreComingForAnswers())
			{
				logger.log(Level.INFO,"For answers Bar appers  in perfomance graph");
			}
			else
			{
				Assert.fail("For answers Bar doesn't apper  in perfomance graph");
			}
			
			new AttemptTest().Diagonestictest();
			new LoginUsingLTI().ltiLogin("10901");
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			if(new PerfomancePageVerification().checkTenBarsAreComingForAnswersForDiagnostic())
			{
				logger.log(Level.INFO,"Performance in the last 10 Q's are coming in bar chart");
			}
			else
			{
				Assert.fail("Performance in the last 10 Q's are coming in bar chart");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.log(Level.SEVERE,"Exception in PerformanceFrameCheckForDiagnostic",e);
			Assert.fail("Exception in PerformanceFrameCheckForDiagnostic",e);
			
		}
	}

}
