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
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;;

public class WidgetCreate extends Driver {

	@Test(priority =1 , enabled=false)
	public void createWidget()
	{
		try
		{
			String chapterName = ReadTestData.readDataByTagName("WidgetCreate", "chapterName", "3007");
			new Widget().createChapterWidget(3007);
			new LoginUsingLTI().ltiLogin("3007");
			new Navigator().InstructorNavigateTo("eTextbook");
			new TopicOpen().topicOpen(chapterName);
			new Widget().navigateToTab(0);		
			new Widget().perspectiveAdd();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase Widget in class WidgetCreate",e);
		}
	}
	
	@Test(priority=2,enabled=false)
	public void commentOnPerspective()
	{
		try
		{
		String chapterName = ReadTestData.readDataByTagName("WidgetCreate", "chapterName", "3007");
		new LoginUsingLTI().ltiLogin("3007");
		new Navigator().InstructorNavigateTo("eTextbook");
		new TopicOpen().topicOpen(chapterName);
		new Widget().commentonPerspective(0);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase addPerspective in class WidgetCreate",e);
		}
	}
	
	@Test(priority=3,enabled = true)
	public void studentViewOfWidget()
	{
		try
		{
		String chapterName = ReadTestData.readDataByTagName("WidgetCreate", "chapterName", "3012");
		new LoginUsingLTI().ltiLogin("3012");
		new Navigator().NavigateTo("eTextbook");
		new TopicOpen().topicOpen(chapterName);
		if(!(driver.findElement(By.className("ls-dialog-txt")).getText().length() > 0))
		Assert.fail("Discussion widget not present on student eTextBook");
		new Widget().perspectiveAdd();
		new Widget().commentonPerspective(0);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase studentViewOfWidget in class WidgetCreate",e);
		}
	}
	

}
