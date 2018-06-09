package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PerfomancePageVerification;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class PerformanceFrameCheckForStatic extends Driver{
private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PerformanceFrameCheckForStatic");
	
	@Test
	public void performanceFrameCheckForStatic()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1206");
			String statictest = ReadTestData.readDataByTagName("tocdata", "statictest", "1");
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().topicOpen(statictest);
			
			if(new PerfomancePageVerification().performanceTabBarGraphForStatic())
			{
				logger.log(Level.INFO,"Perfomance tab is present with 'Points : 1' as first item then bar graph for last 10Q's as second item and 'Students got it correct' as third item");
			}
			else
			{
				Assert.fail("Perfomance tab is not present with 'Points : 1' as first item then bar graph for last 10Q's as second item and 'Students got it correct' as third item");
			}
			if(new PerfomancePageVerification().checkTenBarsAreComingForAnswersForStaic())
			{
				logger.log(Level.INFO,"Performance in the last 10 Q's are coming in bar chart");
			}
			else
			{
				Assert.fail("Performance in the last 10 Q's are coming in bar chart");
			}
			if(new PerfomancePageVerification().checkforTextAlongBarGraphForStatic())
			{
				logger.log(Level.INFO,"Text along x-axis and y-axis are correct");
			}
			else
			{
				Assert.fail("Text along x-axis and y-axis are not correct");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.log(Level.SEVERE,"Exception in PerformanceFrameCheckForStatic",e);
			Assert.fail("Exception in PerformanceFrameCheckForStatic",e);
		}
	}

}
