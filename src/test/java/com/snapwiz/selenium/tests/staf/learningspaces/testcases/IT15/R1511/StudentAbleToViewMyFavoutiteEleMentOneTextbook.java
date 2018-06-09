package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1511;

/*
 * @Brajesh Kumar
 */
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Discussion;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class StudentAbleToViewMyFavoutiteEleMentOneTextbook extends Driver
{
	@Test
	public void studentAbleToViewMyFavoutiteEleMentOneTextbook()
	{
		try
		{
			String randomtext=new RandomString().randomstring(3);
			new LoginUsingLTI().ltiLogin("1");			
			//1-19
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().lessonOpen(0, 0);//open lesoon 1 of chapter 1
			new Navigator().navigateToTab("Fav");//navigater to favrouite tab
			new Navigator().navigateToTab("Discussion");//navigate to discussion tab
			new Discussion().postDiscussion(randomtext);
			new Navigator().navigateToTab("Fav");//navigater to favrouite tab
            Thread.sleep(3000);
			boolean thumnailUnderFavTab=new BooleanValue().booleanbyclass("ls-right-user-img");//verify thumnail under fav tab
			if(thumnailUnderFavTab==true)
				Assert.fail("thumnail shown for discussion under fav tab");
			String addedDiscussion=new TextFetch().textfetchbylistclass("ls-right-user-content", 0);//verify discussion in present under fav tab or not
			if(addedDiscussion.contains(randomtext))
				Assert.fail("discussion not shown inunder fav tabs");
			new Click().clickbylist("ls-right-user-content",0);//clikc on discussion posted
			new Click().clickbylistcssselector("i[class='ls-right-section-sprites ls--right-star-icon']", 0);//unbook mark the discussion
			new Navigator().navigateToTab("Discussion");//navigate to discussion tab
			//20-24
			new Navigator().navigateToTab("Fav");//navigater to favrouite tab
			new Discussion().postNote(randomtext);//post notes
			//34-37,25-28  my notes page activity for added notes
			new Navigator().NavigateTo("My Notes");//navigate to my notes
			boolean addNotesOnMyNotespage=new BooleanValue().booleanbyclass("my-journal-activity-detail-section");
			if(addNotesOnMyNotespage==false)
				Assert.fail("added notes not displayed on my notes page");
			new Click().clickByclassname("my-journal-media-popout");//click on jump out icon
			Thread.sleep(3000);
			boolean notesOpenInExpandedForm=new BooleanValue().booleanbycssselector("div[class='editnote-wrapper prow']");
			if(notesOpenInExpandedForm==false)
				Assert.fail("after click on jump out icon in my notes page its not goes to etextbook and added notes not in expanded form");
			new Click().clickByclassname("ls-user-posted-note-edit");//click on edit link for notes
			//38
			new Click().clickBycssselector("div[class='editnote-button remove-annotation']");//click,on delete icon
			boolean notesAfterDelete=new BooleanValue().booleanbyclass("ls-right-user-head");//verify notes deleted or not
			if(notesAfterDelete==true)
				Assert.fail("note not deleted after click on delete icon from fav tab ");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testclass StudentAbleToViewMyFavoutiteEleMentOneTextbook in testmethod studentAbleToViewMyFavoutiteEleMentOneTextbook",e);
		}
	}

}
