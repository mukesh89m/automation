package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R5;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.HighLight;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TextSelectActions;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;


public class SocialAnnotationsOnLessonContent extends Driver{
	
	@Test(priority=1,enabled=true)
	public void actionsOnLessonText()
	{
		try
		{
			// topicname = ReadTestData.readDataByTagName("tocdata", "card1topic1", "1");
			String paragraphid = ReadTestData.readDataByTagName("SocialAnnotationsOnLessonContent", "paragraphid", "935");
			new LoginUsingLTI().ltiLogin("935");
			new Navigator().NavigateTo("eTextBook");			
			new TopicOpen().lessonOpen(0, 0);
			new TextSelectActions().verifyHighlight(paragraphid,"SocialAnnotationsOnLessonContent","935");
			driver.findElement(By.className("ls-new-btn-section")).click(); //closing social annotation box after verifying highlight
			new TextSelectActions().verifyAddNote(paragraphid,"SocialAnnotationsOnLessonContent","935");
			driver.findElement(By.className("ls-new-btn-section")).click(); //closing social annotation box after verifying addnote
			new TextSelectActions().verifyAddDiscussion(paragraphid,"SocialAnnotationsOnLessonContent","935");
			driver.findElement(By.className("ls-new-btn-section")).click(); //closing social annotation box after verifying adddiscussion
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in TC actionsOnLessonText in class SocialAnnotationsOnLessonContent",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void highlightTextYellow()
	{
		try
		{
			String paragraphid = ReadTestData.readDataByTagName("SocialAnnotationsOnLessonContent", "paragraphid", "938");
			new LoginUsingLTI().ltiLogin("938");
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().lessonOpen(0, 0);
			String yellow = ReadTestData.readDataByTagName("SocialAnnotationsOnLessonContent", "yellow", "938");
			new TextSelectActions().highLightText(yellow, paragraphid);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in TC highlightTextYellow in class SocialAnnotationsOnLessonContent",e);
		}
	}

	@Test(priority=3,enabled=true)
	public void highlightTextGreen()
	{
		try
		{
			String paragraphid = ReadTestData.readDataByTagName("SocialAnnotationsOnLessonContent", "paragraphid", "940");
			new LoginUsingLTI().ltiLogin("940");
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().lessonOpen(0, 0);
			String green = ReadTestData.readDataByTagName("SocialAnnotationsOnLessonContent", "green", "940");
			new TextSelectActions().highLightText(green, paragraphid);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in TC highlightTextGreen in class SocialAnnotationsOnLessonContent",e);
		}
	}
	
	@Test(priority=4,enabled=true)
	public void highlightTextBlue()
	{
		try
		{
			String paragraphid = ReadTestData.readDataByTagName("SocialAnnotationsOnLessonContent", "paragraphid", "939");
			new LoginUsingLTI().ltiLogin("939");
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().lessonOpen(0, 0);
			String blue = ReadTestData.readDataByTagName("SocialAnnotationsOnLessonContent", "blue", "939");
			new TextSelectActions().highLightText(blue, paragraphid);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in TC highlightTextBlue in class SocialAnnotationsOnLessonContent",e);
		}
	}
	
	@Test(priority=5,enabled=true)
	public void highlightTextOrange()
	{
		try
		{
			String paragraphid = ReadTestData.readDataByTagName("SocialAnnotationsOnLessonContent", "paragraphid", "941");
			new LoginUsingLTI().ltiLogin("941");
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().lessonOpen(0, 0);
			String orange = ReadTestData.readDataByTagName("SocialAnnotationsOnLessonContent", "orange", "941");
			new TextSelectActions().highLightText(orange,paragraphid);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in TC highlightTextOrange in class SocialAnnotationsOnLessonContent",e);
		}
	}
	@Test(priority=6,enabled=true)
	public void selectedTextRemainHighlighted()
	{
		try
		{
			String paragraphid = ReadTestData.readDataByTagName("SocialAnnotationsOnLessonContent", "paragraphid", "942");
			String orange = ReadTestData.readDataByTagName("SocialAnnotationsOnLessonContent", "orange", "942");
			new LoginUsingLTI().ltiLogin("942");
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().lessonOpen(0, 0);
			new TextSelectActions().highLightText(orange, paragraphid);
			new TOCShow().chaptertree();	
			new TopicOpen().lessonOpen(0, 1);
			new TOCShow().chaptertree();	
			new TopicOpen().lessonOpen(0, 0);
			new HighLight().validateHightLight(orange);
		
				
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in TC selectedTextRemainHighlighted in class SocialAnnotationsOnLessonContent",e);
		}
	}
	
	@Test(priority=7,enabled=true)
	public void RemoveHighlight()
	{
		try
		{
			String paragraphid = ReadTestData.readDataByTagName("SocialAnnotationsOnLessonContent", "paragraphid", "944");
			new LoginUsingLTI().ltiLogin("944");
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().lessonOpen(0, 0);
			Thread.sleep(10000);
			String orange = ReadTestData.readDataByTagName("SocialAnnotationsOnLessonContent", "orange", "944");
			new TextSelectActions().highLightText(orange,paragraphid);
			new HighLight().removeHighlight();
			Thread.sleep(3000);
			new TextSelectActions().highLightText(orange, paragraphid);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in TC RemoveHighlight in class SocialAnnotationsOnLessonContent",e);
		}
	}
	

	
}
