package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.uihelper.Click;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.TextSend;

public class SearchQuestionInCustomCourseAssignemnt 
{

	public void searchQuestionInCustomCourseAssignemnt(String serachtext)
	{
		try
		{
			new TextSend().textsendbycssSelector(serachtext, "input[class='ls-ins-search-question-content ls-ins-search-question-content-selected']");// text entry for search
			new Click().clickByclassname("ls-ins-search-icon");//click on search button
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper SearchQuestionInCustomCourseAssignemnt in method SearchQuestionInCustomCourseAssignemnt ",e);
		}
	}
}
