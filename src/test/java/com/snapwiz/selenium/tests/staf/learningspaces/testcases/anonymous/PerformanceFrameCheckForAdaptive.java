package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;


public class PerformanceFrameCheckForAdaptive extends Driver
{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PerformanceFrameCheckForAdaptive");
@Test
	public void performanceFrameCheckForAdaptive()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1148");
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			new AttemptTest().Diagonestictest();
			new TabClose().tabClose(1);
			Thread.sleep(5000);
			new TOCShow().tocShow();
			String adaptivetest = ReadTestData.readDataByTagName("tocdata", "adaptivetest", "1");
			new TopicOpen().topicOpen(adaptivetest);
			if(new PerfomancePageVerification().performanceTabBarGraph())
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
			new SelectAnswerAndSubmit().Adaptiveasnswersubmit("A");
			if(new PerfomancePageVerification().checkBarAreComingForAnswers())
			{
				logger.log(Level.INFO,"For answers Bar appears  in perfomance graph");
			}
			else
			{
				Assert.fail("For answers Bar doesn't appear  in perfomance graph");
			}
			
			if(new PerfomancePageVerification().checkTenBarsAreComingForAnswers())
			{
				logger.log(Level.INFO,"Performance in the last 10 Q's are coming in bar chart");
			}
			else
			{
				Assert.fail("Performance in the last 10 Q's are not coming in bar chart");
			} 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.log(Level.SEVERE,"Exception in PerformanceFrameCheckForAdaptive",e);
			Assert.fail("Exception in PerformanceFrameCheckForAdaptive",e);
		}
	}

}
