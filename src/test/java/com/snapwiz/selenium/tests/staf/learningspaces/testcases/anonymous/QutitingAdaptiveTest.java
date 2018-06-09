package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AttemptTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.QuitAssessment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;

public class QutitingAdaptiveTest extends Driver{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.QutitingAdaptiveTest");

@Test
	public void qutitingAdaptiveTest()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1172");
			String adaptivetest = ReadTestData.readDataByTagName("tocdata", "adaptivetest", "1");
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			new AttemptTest().Diagonestictest();
			new TabClose().tabClose(1);
			new TOCShow().tocShow();
			new TopicOpen().topicOpen(adaptivetest);
			
			if (new QuitAssessment().quitAdaptiveAssessmentAndCheckRoboNotification())
			{
				logger.log(Level.INFO,"Robo Notification is coming with ' View Report' and 'Back to last lesson' after quiting adaptive  test");
			}
			else
			{
				logger.log(Level.INFO,"Robo Notification is not coming after quiting  adaptive the test");
				Assert.fail("Robo Notification is not coming after quiting adaptive the test");
			}
			
			
			if (new QuitAssessment().clickingOnBackToLastLessonForAdaptive())
			{
				logger.log(Level.INFO,"TOC Opens after clicking 'Back to last lesson' in the robo notification");
			}
			else
			{
				logger.log(Level.INFO,"TOC doesn't open after clicking 'Back to last lesson' in the robo notification & the assement tab get closed");
				Assert.fail("TOC doesn't open after clicking 'Back to last lesson' in the robo notification &  the assement tab doesnt get closed");
			}
			new TopicOpen().topicOpen(adaptivetest);
						
			if(new QuitAssessment().clickingOnViewReportForAdaptive())
			{
				logger.log(Level.INFO,"Opens the chapter perfomance page");
			}
			else
			{
				logger.log(Level.INFO,"Chapter perfomance page is not opened");
				Assert.fail("Chapter perfomance page is not opened");
			}
			if(new QuitAssessment().quitTheAssesmentCompletlyAndStartTheSameAssesmentAgainForAdaptive())
			{
				logger.log(Level.INFO,"After quiting  test and starting it again shows the question");
			}
			else
			{
				logger.log(Level.INFO,"After quiting  test and starting it again doesnt show the question");
				Assert.fail("After quiting  test and starting it again doesnt show the question");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.log(Level.SEVERE,"Exception in QutitingAdaptiveTest",e);
			Assert.fail("Exception in QutitingAdaptiveTest",e);
		}
	
	}

}
