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

public class AbilityOfStudentToAddPerspectiveAndcomment extends Driver
{
	@Test
	public void abilityofstudenttoaddperspectiveandcomment()
	{
		try
		{
			String chaptername=ReadTestData.readDataByTagName("tocdata", "chapterName", "1");//fetch chapter name
			new Widget().createChapterWidget(2368);//create widget
			driver.quit();
			startDriver("firefox");
			new LoginUsingLTI().ltiLogin("2368");//login a instructor
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TopicOpen().topicOpen(chaptername);//open chapter
			new Widget().perspectiveAdd();
			Thread.sleep(2000);
			boolean thumbnial=driver.findElement(By.cssSelector("div[class='ls-content-comment-user-img']")).isDisplayed();
			if(thumbnial==false)
				Assert.fail("thumbnial not dispalyed");
			String perspectivetext=driver.findElement(By.cssSelector("li[class='ls-stream-post-comment']")).getText();//fetch all text of perspective block
			if(!perspectivetext.contains("givenname family"))
				Assert.fail("name of student not shown");
			if(!perspectivetext.contains("Comments"))
				Assert.fail("comment not shown");
			if(!perspectivetext.contains("Like"))
				Assert.fail("Like not shown");
			if(!perspectivetext.contains("ago"))
				Assert.fail("age of perspective not shown");
			new Widget().prospectivecomment(0, 1);
			String cmomentblock=driver.findElement(By.cssSelector("ul[class='ls-list ls-content-perspective-comments']")).getText();//fetch all text of comment block
			System.out.println(cmomentblock);
			if(!cmomentblock.contains("givenname family"))
				Assert.fail("name of student not shown");
			if(!cmomentblock.contains("ago"))
				Assert.fail("age of comment not shown");
			if(!cmomentblock.contains("Like"))
				Assert.fail("Like not shown");			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase abilityofstudenttoaddperspectiveandcomment in class AbilityOfStudentToAddPerspectiveAndcomment ",e);
		}
	}
	@AfterMethod
	public void TearDown()throws Exception
	{
		driver.quit();
	}
}
