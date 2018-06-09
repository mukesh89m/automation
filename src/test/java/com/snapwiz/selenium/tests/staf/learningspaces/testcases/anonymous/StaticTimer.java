package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandFirstChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelecteTextBook;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Timer;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class StaticTimer extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.DiagonesticTimer");
	/*
	 * 1225-1227
	 */
	@Test
	public void statictimer()
	{
		try
		{
			driver.manage().deleteAllCookies();
			new LoginUsingLTI().ltiLogin("1225");
			new SelecteTextBook().etextselector();
			new ExpandFirstChapter().expandFirstChapter();
			new TopicOpen().topicOpen("Static - Chemical change - 1");
			boolean timevalue=new Timer().timer();
			
			if(timevalue==true)
			{
				logger.log(Level.INFO,"TestCase diagonestictimer Pass ");
			}
			else
			{
				logger.log(Level.INFO,"TestCase diagonestictimer fail ");
				Assert.fail("Timer not shown or timer stop after click on another tab");
			}
			
		}
		catch(Exception e)
	    {
				  logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper",e);				 
				  Assert.fail("Exception in LoginUsingLTI Application Helper",e);
				  
		}
	}


}
