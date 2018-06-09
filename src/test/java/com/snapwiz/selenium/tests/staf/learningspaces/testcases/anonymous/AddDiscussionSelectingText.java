package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;


public class AddDiscussionSelectingText extends Driver{
	
	@Test(priority=1,enabled=true)
	public void addDiscussionSelectingText()
	{
		try
		{
			String green = ReadTestData.readDataByTagName("", "green", "972");
			new LoginUsingLTI().ltiLogin("972");
			new Navigator().NavigateTo("eTextBook");			
			new TOCShow().tocHide();
		    new TextSelectActions().addDiscussion(green, "",10,40);
		    new HighLight().validateHightLight(green);

		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase addDiscussionSelectingText in class AddDiscussionSelectingText",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void validateDiscussionAddedByAnotherStudent()
	{
		try
		{
			String green = ReadTestData.readDataByTagName("", "green", "985");
			new LoginUsingLTI().ltiLogin("985");
			new Navigator().NavigateTo("eTextBook");
            new TOCShow().tocHide();
		    new TextSelectActions().addDiscussion("", "",10,40);
		    new HighLight().validateHightLight(green);
		    new HighLight().clickAndValidateDiscussion("985"); 
		    //Validating TC 985,993 "9. Login as a different student of same class section
		    //10.Acess the same lesson" #1. Discussion added by first student should be available
		    new LoginUsingLTI().ltiLogin("9851");
			new Navigator().NavigateTo("eTextBook");			
			new TopicOpen().lessonOpen(0, 0);
		    new HighLight().validateHightLight(green);
		    new HighLight().validateDiscussionByAnotherUser();

		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase validateDiscussionAddedByAnotherStudent in class AddDiscussionSelectingText",e);
		}
	}
	
	@Test(priority=3,enabled=true)
	public void editDiscussion()
	{
		try
		{
			String green = ReadTestData.readDataByTagName("", "green", "990");
			new LoginUsingLTI().ltiLogin("990");
			new Navigator().NavigateTo("eTextBook");
            new TOCShow().tocHide();
		    new TextSelectActions().addDiscussion("", "",10,40);
		    new HighLight().validateHightLight(green);
		    new HighLight().clickAndEditDiscussion();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase editDiscussion in class AddDiscussionSelectingText",e);
		}
	}

	@Test(priority=4,enabled=true)
	public void discussionBoxValidateByOpeningDiscussionFromStream()
	{
		try
		{
			String green = ReadTestData.readDataByTagName("", "green", "1013");
			new LoginUsingLTI().ltiLogin("1013");
			new Navigator().NavigateTo("eTextBook");			
			new TopicOpen().lessonOpen(0, 0);
		    new TextSelectActions().addDiscussion(green, "",10,80);
		    new HighLight().validateHightLight(green);
		    new HighLight().DiscussionBoxOpenedByClickingDiscussionUnderStreamValidate();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase discussionBoxValidateByOpeningDiscussionFromStream in class AddDiscussionSelectingText",e);
		}
	}
		

}
