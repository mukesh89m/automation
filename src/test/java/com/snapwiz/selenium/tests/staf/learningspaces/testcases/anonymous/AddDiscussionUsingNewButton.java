package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;


public class AddDiscussionUsingNewButton extends Driver {
	
	@Test(priority=1,enabled=true)
	public void validateNewButtonOptions()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("996");
			new Navigator().NavigateTo("eTextbook");
            new TOCShow().tocHide();
			new NewButtonActions().verifyNewButton();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TC validateNewButtonOptions in class AddDiscussionUsingNewButton.",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void validateNewDiscussionBox()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("998");
			new Navigator().NavigateTo("eTextbook");
            new TOCShow().tocHide();
			new NewButtonActions().verifyAddDiscussionBox();
			new NewButtonActions().cancelButtonPressAddDiscussionBox();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TC validateNewDiscussionBox in class AddDiscussionUsingNewButton.",e);
		}
	}
	
	@Test(priority=3,enabled=true)
	public void addNewDiscussion()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1001");
			new Navigator().NavigateTo("eTextbook");
            new TOCShow().tocHide();
			new NewButtonActions().verifyAddDiscussionBox();
			new NewButtonActions().addDiscussion();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TC addNewDiscussion in class AddDiscussionUsingNewButton.",e);
		}
	}

	@Test(priority=4,enabled=true)
	public void validateNewNoteBox()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1005");
			new Navigator().NavigateTo("eTextbook");
            new TOCShow().tocHide();
			new NewButtonActions().verifyAddNoteBox();
			new NewButtonActions().cancelButtonPressAddNoteBox();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TC validateNewNoteBox in class AddDiscussionUsingNewButton.",e);
		}
	}
	
	@Test(priority=5,enabled=true)
	public void addNewNote()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1008");
			new Navigator().NavigateTo("eTextbook");
            new TOCShow().tocHide();
			new NewButtonActions().verifyAddNoteBox();
			new NewButtonActions().addNote();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TC addNewNote in class AddDiscussionUsingNewButton.",e);
		}
	}
	
	@Test(priority=6,enabled=true)
	public void commentLinkInDiscussionBox()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1031");
			new Navigator().NavigateTo("eTextbook");
            new TOCShow().tocHide();
			new NewButtonActions().verifyAddDiscussionBox();
			new NewButtonActions().addDiscussion();
			new NewButtonActions().discussionBoxAfterDiscussionClickVerify();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TC commentLinkInDiscussionBox in class AddDiscussionUsingNewButton.",e);
		}
	}

	@Test(priority=7,enabled=true)
	public void commentLinkInNoteBox()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1034");
			new Navigator().NavigateTo("eTextbook");
            new TOCShow().tocHide();
			new NewButtonActions().verifyAddNoteBox();
			new NewButtonActions().addNote();
			new NewButtonActions().noteBoxAfterNoteClickVerify();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TC commentLinkInNoteBox in class AddDiscussionUsingNewButton.",e);
		}
	}
	

}
