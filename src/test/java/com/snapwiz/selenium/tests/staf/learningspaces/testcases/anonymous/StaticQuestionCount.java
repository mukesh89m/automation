package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandFirstChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.QuestionCount;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelecteTextBook;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class StaticQuestionCount extends Driver {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.DiagonesticQuestionCount");
	/*
	 * 1222-1224
	 */
	@Test
	public void diagonsticquestioncount()
	{
		try
		{
			driver.manage().deleteAllCookies();
			new LoginUsingLTI().ltiLogin("1222");
			new SelecteTextBook().etextselector();
			new ExpandFirstChapter().expandFirstChapter();
			new TopicOpen().topicOpen("Static - Chemical change - 1");
			
			String chaptername=ReadTestData.readDataByTagName("StaticQuestionCount", "chaptername", "1222");
			String presentquestionno=ReadTestData.readDataByTagName("StaticQuestionCount", "presentquestionno", "1222");
			String totalquestionno=ReadTestData.readDataByTagName("StaticQuestionCount", "totalquestionno", "1222");
			String presentquestionnoafterclick=ReadTestData.readDataByTagName("StaticQuestionCount", "presentquestionnoafterclick", "1222");
			String totalquestionnumberafterclick=ReadTestData.readDataByTagName("StaticQuestionCount", "totalquestionnumberafterclick", "1222");
			
			boolean questioncount=new QuestionCount().questioncount(chaptername,presentquestionno,totalquestionno,presentquestionnoafterclick,totalquestionnumberafterclick);
			System.out.println(questioncount);
			if(questioncount==true)
			{
				logger.log(Level.INFO,"chapter name total question  number and present question number dispalyed on test page ");
			}
			else
			{
				logger.log(Level.INFO,"chapter name total question  number and present question number NOT dispalyed on test page ");
				Assert.fail("chapter name total question  number and present question number NOT dispalyed on test page ");
			}
			
		}
		catch(Exception e)
	    {
				  logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper",e);				 
				  Assert.fail("Exception in LoginUsingLTI Application Helper",e);
				  
		}
	}


}
