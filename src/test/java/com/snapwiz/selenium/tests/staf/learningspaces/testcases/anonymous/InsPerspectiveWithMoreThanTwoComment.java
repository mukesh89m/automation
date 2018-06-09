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

public class InsPerspectiveWithMoreThanTwoComment extends Driver
{
	@Test
	public void insperspectivewithmorethantwocomment()
	{
		try
		{
			String chaptername=ReadTestData.readDataByTagName("tocdata", "chapterName", "1");//fetch chapter name
			new Widget().createChapterWidget(2247);//create widget
			driver.quit();
			startDriver("firefox");
			new LoginUsingLTI().ltiLogin("2247");//login a instructor
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TopicOpen().topicOpen(chaptername);//open chapter
			new Widget().perspectiveAdd();//add prospective
			Thread.sleep(2000);
			new Widget().prospectivecomment(0, 3);//two comment on prospective
			Thread.sleep(5000);
			boolean viewallcomment=driver.findElement(By.cssSelector("div[class='ls-content-view-all-posted-comments']")).isDisplayed();//fetch view all comment links
			if(viewallcomment==false)
				Assert.fail("view all comment link not shown when add more than two comments ");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("div[class='ls-content-view-all-posted-comments']")).click();
			Thread.sleep(2000);
			int commentblock=driver.findElements(By.cssSelector("li[class='perspective-comment-block']")).size();
			if(commentblock!=3)
				Assert.fail("not shown all comment after click on view all comment");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase perspectivewithmorethantwocomment in class PerspectiveWithMoreThanTwoComment ",e);
		}
	}


}
