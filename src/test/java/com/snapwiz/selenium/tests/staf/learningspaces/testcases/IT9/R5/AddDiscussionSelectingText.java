package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R5;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.HighLight;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TextSelectActions;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;


public class AddDiscussionSelectingText {
	
	@Test(priority=1,enabled=true)
	public void addDiscussionSelectingText()
	{
		try
		{
			String blue = ReadTestData.readDataByTagName("AddDiscussionSelectingText", "blue", "972");
			String paragraphid = ReadTestData.readDataByTagName("AddDiscussionSelectingText", "paragraphid", "972");
			new LoginUsingLTI().ltiLogin("972");
			new Navigator().NavigateTo("eTextBook");			
			new TopicOpen().lessonOpen(0, 0);
			Thread.sleep(10000);	
		    new TextSelectActions().addDiscussion(blue, paragraphid,10,40);
		    new HighLight().validateHightLight(blue);
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
			String blue = ReadTestData.readDataByTagName("AddDiscussionSelectingText", "blue", "985");
			String paragraphid = ReadTestData.readDataByTagName("AddDiscussionSelectingText", "paragraphid", "985");
			new LoginUsingLTI().ltiLogin("985");
			new Navigator().NavigateTo("eTextBook");			
			new TopicOpen().lessonOpen(0, 0);
		    new TextSelectActions().addDiscussion(blue, paragraphid,10,40);
		    new HighLight().validateHightLight(blue);
		    new HighLight().clickAndValidateDiscussion("985"); 
		    //Validating TC 985,993 "9. Login as a different student of same class section
		    //10.Acess the same lesson" #1. Discussion added by first student should be available
		    new LoginUsingLTI().ltiLogin("9851");
			new Navigator().NavigateTo("eTextBook");			
			new TopicOpen().lessonOpen(0, 0);
		    new HighLight().validateHightLight(blue);
		    new HighLight().validateDiscussionByAnotherUser();
		    //Validating other user looged in using IE
		    //new Driver().ieDriverStart();
		    new LoginUsingLTI().ltiLogin("9851");
			new Navigator().NavigateTo("eTextBook");			
			new TopicOpen().lessonOpen(0, 0);
		    new HighLight().validateHightLight(blue);
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
			String blue = ReadTestData.readDataByTagName("AddDiscussionSelectingText", "blue", "990");
			String paragraphid = ReadTestData.readDataByTagName("AddDiscussionSelectingText", "paragraphid", "990");
			new LoginUsingLTI().ltiLogin("990");
			new Navigator().NavigateTo("eTextBook");			
			new TopicOpen().lessonOpen(0, 0);
		    new TextSelectActions().addDiscussion(blue, paragraphid,10,40);
		    new HighLight().validateHightLight(blue);
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
			String blue = ReadTestData.readDataByTagName("AddDiscussionSelectingText", "blue", "1013");
			String paragraphid = ReadTestData.readDataByTagName("AddDiscussionSelectingText", "paragraphid", "1013");
			new LoginUsingLTI().ltiLogin("1013");
			new Navigator().NavigateTo("eTextBook");			
			new TopicOpen().lessonOpen(0, 0);
		    new TextSelectActions().addDiscussion(blue, paragraphid,10,80);
		    new HighLight().validateHightLight(blue);
		    new HighLight().DiscussionBoxOpenedByClickingDiscussionUnderStreamValidate();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase discussionBoxValidateByOpeningDiscussionFromStream in class AddDiscussionSelectingText",e);
		}
	}
		

}
