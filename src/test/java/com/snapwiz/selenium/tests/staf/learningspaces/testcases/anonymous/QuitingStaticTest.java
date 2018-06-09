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
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.QuitAssessment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class QuitingStaticTest extends Driver
{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.QuitingStaticTest");
@Test	
	public void quitingStaticTest()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1234");
			String statictest = ReadTestData.readDataByTagName("tocdata", "statictest", "1");
			new Navigator().NavigateTo("eTextbook");			
			new TopicOpen().topicOpen(statictest);			
			if(new QuitAssessment().quitAssesmentForStaticTest())
			{
				logger.log(Level.INFO,"On quiting static test it opens the last accessed lesson in the TOC & the asssessment tab get closed");
			}
			else
			{
				logger.log(Level.INFO,"On quiting static test it doesnt open the last accessed lesson in the TOC & the asssessment tab doesnt get closed");
				Assert.fail("On quiting static test it doesnt open the last accessed lesson in the TOC & the asssessment tab doesnt get closed");
			}
			
		}
		catch(Exception e)
		{
			
			logger.log(Level.SEVERE,"Exception in QuitingStaticTest",e);
			Assert.fail("Exception in QuitingStaticTest",e);
		}
	}

}
