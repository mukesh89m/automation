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

public class AbilityOfInstructorToAddPerspectiveAndcomment extends Driver
{
	@Test
	public void abilityofinstructortoaddperspectiveandcomment()
	{
		
		try
		{
			String chaptername=ReadTestData.readDataByTagName("tocdata", "chapterName", "1");//fetch chapter name
			new Widget().createChapterWidget(2217);//create widget
			driver.quit();
			startDriver(driver);
			new LoginUsingLTI().ltiLogin("2217");//login a instructor
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TopicOpen().topicOpen(chaptername);//open chapter
			new Widget().perspectiveAdd();
			Thread.sleep(2000);
			boolean thumbnial=driver.findElement(By.cssSelector("div[class='ls-content-comment-user-img']")).isDisplayed();
			if(thumbnial==false)
				Assert.fail("thumbnial not dispalyed");
			String perspectivetext=driver.findElement(By.cssSelector("li[class='ls-stream-post-comment']")).getText();//fetch all text of perspective block
			System.out.println(perspectivetext);
			if(!perspectivetext.contains("Instructor"))
				Assert.fail("instructor level of student not shown");
			if(!perspectivetext.contains("givenname family"))
				Assert.fail("name of instructor not shown");
			if(!perspectivetext.contains("Comments"))
				Assert.fail("comment not shown");
			if(!perspectivetext.contains("Like"))
				Assert.fail("Like not shown");
			if(!perspectivetext.contains("ago"))
				Assert.fail("age of perspective not shown");
			new Widget().prospectivecomment(0, 1);
			String cmomentblock=driver.findElement(By.cssSelector("li[class='perspective-comment-block']")).getText();//fetch all text of comment block
			System.out.println(cmomentblock);
			if(!cmomentblock.contains("Instructor"))
				Assert.fail("level of instructor not shown");
			if(!cmomentblock.contains("givenname family"))
				Assert.fail("name of instructor not shown");
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


}
