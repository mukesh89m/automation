package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;

public class InsDiscussionWidgetWithMoreThanTwoPerspective extends Driver
{
	@Test
	public void insdiscussionwidgetwithmorethantwoperspective()
	{
		
		try
		{
			String chaptername=ReadTestData.readDataByTagName("tocdata", "chapterName", "1");//fetch chapter name			new Widget().createChapterWidget(2231);//create widget
			driver.quit();
			startDriver("firefox");
			new LoginUsingLTI().ltiLogin("2231");//login a instructor
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TopicOpen().topicOpen(chaptername);//open chapter
			new Widget().AddPerspectivemorethanone(3);//add two prospective
			Thread.sleep(4000);
			boolean viewperspectivevalue=driver.findElement(By.cssSelector("div[class='ls-content-post__view-comments']")).isDisplayed();
			if(viewperspectivevalue==false)
				Assert.fail("view all prescpective not shown when add more than two prespective.");		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase insdiscussionwidgetwithmorethantwoperspective in class InsDiscussionWidgetWithMoreThanTwoPerspective ",e);
		}
	}


}
