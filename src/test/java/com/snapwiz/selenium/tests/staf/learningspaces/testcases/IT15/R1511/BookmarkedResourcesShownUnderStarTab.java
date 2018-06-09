package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1511;

import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class BookmarkedResourcesShownUnderStarTab extends Driver
{
	@Test(priority=1,enabled=true)
	public void bookmarkedResourcesShownUnderStarTab()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("84");
			new UploadResources().uploadResources("84", true, false, true);
			Thread.sleep(2000);
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().lessonOpen(0, 0);
			//84-84
			new Navigator().navigateToResourceTab();//navigate to resources tab
            new Navigator().openResourceFromResourceTab(84);

            new ClickOnResourcesToOpen().clickOnResourcesToOpen("84");
			Thread.sleep(2000);
			driver.findElement(By.linkText("New tab")).click();//click on new tab button
			Thread.sleep(3000);
			//88-89
			new Click().clickBycssselector("i[class='ls-right-section-sprites ls--right-star-icon-resource']");//click on bookmarked icon
			new Click().clickbylist("close_tab", 1);//close resources tab
			new Navigator().navigateToTab("Fav");//navigate to fav tab
			//86-87
			String resourcesbookmarkWithText=new TextFetch().textfetchbylistclass("resource-content-posts-list", 0);//fethc text of resources

			if(!(resourcesbookmarkWithText.contains("You bookmarked a Resource")))
				Assert.fail("resource not bookmarked with' bookmarked a Resource' text and not shown under fav tab ");

            if(!(resourcesbookmarkWithText.contains("AAresources_IT15_R1511_84")))
                Assert.fail("resource name not shown under fav tab ");

            if(!(resourcesbookmarkWithText.contains("Instructor")))
                Assert.fail("Instructor not shown under fav tab ");

            if(!(resourcesbookmarkWithText.contains("description_IT15_R1511_84")))
                Assert.fail("Description of Resource not shown under fav tab ");

            if(!(resourcesbookmarkWithText.contains("Accessed")))
                Assert.fail("Accessed tag for Resource not shown under fav tab ");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase BookmarkedResourcesShownUnderStarTab in method bookmarkedResourcesShownUnderStarTab",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void HighlightTheTextBookmarked()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("90");
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().lessonOpen(0, 0);//open lesson 1 of chapter 1
			Thread.sleep(3000);
			//90-92
			new HighlightText().highlightText("blue");//highLightText text yellow
			new Navigator().navigateToTab("Fav");
			String highLightedtext=new TextFetch().textfetchbyclass("stream-content-posts-list");//ftech text under fav tab
			if(!highLightedtext.contains("highlighted a text"))
				Assert.fail("highlighted text not shown under star tab");
			//93-94
			new Navigator().NavigateTo("My Notes");//navigate to my notes
			new Click().clickBycssselector("span[class='my-journal-card-bookmark bookmarked-card']");//remove bookmark from highlighted text on my notes page
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().lessonOpen(0, 0);//open lesson 1 of chapter 1
			new Navigator().navigateToTab("Fav");
			int highlightedTextAfterUnbookmark=driver.findElements(By.className("stream-content-posts-list")).size();
			if(highlightedTextAfterUnbookmark!=0)
				Assert.fail("after unbookmark the highlighted text its not remove form star tab");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase BookmarkedResourcesShownUnderStarTab in method HighlightText",e);
		}
	}



}
