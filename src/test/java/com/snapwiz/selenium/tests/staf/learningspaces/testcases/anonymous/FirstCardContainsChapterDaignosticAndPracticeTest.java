package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelecteTextBook;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCVerify;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

//import junit.framework.TestCase;

public class FirstCardContainsChapterDaignosticAndPracticeTest extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.FirstCardContainsChapterDaignosticAndPracticeTest");
	//first card text verification
	@Test
	public void firstcardverification()
	{
		try
		{
			driver.manage().deleteAllCookies();
			new LoginUsingLTI().ltiLogin("791");
			new SelecteTextBook().etextselector();
			String [] toc=new TOCVerify().tocverify(1);				
			String testdata0=new ReadTestData().readDataByTagName("FirstCardContainsChapterDaignosticAndPracticeTest", "topic0", "791");
			String testdata1=new ReadTestData().readDataByTagName("FirstCardContainsChapterDaignosticAndPracticeTest", "topic1", "791");
			String testdata2=new ReadTestData().readDataByTagName("FirstCardContainsChapterDaignosticAndPracticeTest", "topic2", "791");
			
			if(toc[0].equals(testdata0) && toc[1].equals(testdata1) && toc[2].equals(testdata2))
			{
				logger.log(Level.INFO,"First card contains daigonestic ,static and practice test");
				
			}
			else
			{
				logger.log(Level.INFO,"First card Not contains daigonestic ,static and practice test");
				Assert.fail("Exception in Card values diffrent card contain same value");
			}
			
			
			
		}
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in LoginUSingLTI Application Helper",e);			 
			  driver.quit();
			  Assert.fail("Error in LTI Login Or Element not found");
			  
		}
	}

}
