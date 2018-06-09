package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R5;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;


public class NevigatePreviousAndNextElementFomLessonPage extends Driver {
	/*
	 * 911-923
	 */
	@Test(priority=1,enabled=true)
	public void validateShortTopicNavigationLinks()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("911");
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().lessonOpen(0, 0);
			new Click().clickByclassname("ls-next");//click on next lesson icon at bottom
			new Click().clickByclassname("ls-prev");//click on previous icon button
			int previouspageicon=driver.findElements(By.className("ls-prev")).size();
			if(previouspageicon!=0)
				Assert.fail("on 1st page previous chapter link shows");
			
		}
		
		catch (Exception e)
		{
			  Assert.fail("Exception in TestCase NevigatePreviousAndNextElementFomLessonPage",e);
			  
		}
	}


}
