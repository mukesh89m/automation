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

public class DiscussionWidgetWithMoreThanTwoPerspective extends Driver
{
	@Test
	public void discussionwidgetwithmorethantwoperspectives()
	{		
		try
		{
			String chaptername=ReadTestData.readDataByTagName("tocdata", "chapterName", "1");//fetch chapter name
			new Widget().createChapterWidget(2383);//create widget
			driver.quit();
			startDriver("firefox");
			new LoginUsingLTI().ltiLogin("2383");//login a instructor
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TopicOpen().topicOpen(chaptername);//open chapter
			new Widget().AddPerspectivemorethanone(3);//add two prospective
			Thread.sleep(4000);
			boolean viewperspectivevalue=driver.findElement(By.cssSelector("div[class='ls-content-post__view-comments']")).isDisplayed();
			if(viewperspectivevalue==false)
				Assert.fail("view all prescpective not shown when add more than two prespective.");	
			driver.findElement(By.cssSelector("div[class='ls-content-post__view-comments']")).click();
			Thread.sleep(2000);
			int perspectiveblock=driver.findElements(By.cssSelector("li[class='ls-stream-post-comment']")).size();
			if(perspectiveblock!=3)
				Assert.fail("All 3 perspective block not shown");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase discussionwidgetwithmorethantwoperspective in class DiscussionWidgetWithMoreThanTwoPerspective ",e);
		}
	}

}
