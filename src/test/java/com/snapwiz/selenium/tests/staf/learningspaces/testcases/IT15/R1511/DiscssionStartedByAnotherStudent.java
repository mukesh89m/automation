package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1511;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Discussion;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class DiscssionStartedByAnotherStudent extends Driver
{
	@Test
	public void discssionStartedByAnotherStudent()
	{
		try
		{

			String randomtext=new RandomString().randomstring(3);
			new LoginUsingLTI().ltiLogin("751");//login as student 1
			new LoginUsingLTI().ltiLogin("75");	//login as student2		
			//1-19
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().lessonOpen(0, 0);//open lesoon 1 of chapter 1
			new Navigator().navigateToTab("Fav");//navigater to favrouite tab
			new Navigator().navigateToTab("Discussion");//navigate to discussion tab
			new Discussion().postDiscussion(randomtext);//posted a discussion
			new LoginUsingLTI().ltiLogin("751");//login as 1st student	
			new Navigator().NavigateTo("Course Stream");
			new Click().clickBycssselector("span[class='ls-jumpout-icon-link ls-jumpout-icon']");//click on jump out icon from course stream page
			String discussionText=new TextFetch().textfetchbyclass("ls-right-user-head");//fetch string text
			System.out.println("DiscussionText:"+discussionText);
			if(!discussionText.contains("75family, 75givenname posted a discussion"))
				Assert.fail("posted a discusion not shown by another student");
			new Navigator().navigateToTab("Fav");//navigater to favrouite tab
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase DiscssionStartedByAnotherStudent in test method discssionStartedByAnotherStudent",e);
		}
	}


}
