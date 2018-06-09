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

public class InsPrespectiveWithTwoComment extends Driver
{
	@Test
	public void insprespectivewithtwocomment()
	{
		try
		{
			String chaptername=ReadTestData.readDataByTagName("tocdata", "chapterName", "1");//fetch chapter name			new Widget().createChapterWidget(2241);//create widget
			driver.quit();
			startDriver("firefox");
			new LoginUsingLTI().ltiLogin("2241");//login a instructor
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TopicOpen().topicOpen(chaptername);//open chapter
			new Widget().perspectiveAdd();//add prospective
			Thread.sleep(2000);
			new Widget().prospectivecomment(0, 2);//two comment on prospective
			Thread.sleep(5000);
			boolean viewallcomment=driver.findElement(By.cssSelector("div[class='ls-content-view-all-posted-comments']")).isDisplayed();//fetch view all comment links
			if(viewallcomment==true)
				Assert.fail("view all comment link shown when only two comments post.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase prespectivewithtwocomment in class PrespectiveWithTwoComment ",e);
		}
	}

	

}
