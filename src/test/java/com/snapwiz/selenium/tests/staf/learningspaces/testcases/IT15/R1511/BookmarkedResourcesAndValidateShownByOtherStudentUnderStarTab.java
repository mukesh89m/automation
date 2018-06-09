package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1511;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class BookmarkedResourcesAndValidateShownByOtherStudentUnderStarTab extends Driver
{
	@Test
	public void bookmarkedResourcesAndValidateShownByOtherStudentUnderStarTab()
	{
		try
		{

		    new ResourseCreate().resourseCreate(206,0);//upload resources
			new LoginUsingLTI().ltiLogin("206");//login as 1st student
			new LoginUsingLTI().ltiLogin("2061");//login as 2nd student
			new Navigator().NavigateTo("e-Textbook");
			new TOCShow().tocHide();
			//206
			new Navigator().navigateToResourceTab();//navigate to resources tab
            new Navigator().openResourceFromResourceTabFromCMS(206);
			new Click().clickBycssselector("i[class='ls-right-section-sprites ls--right-star-icon-resource']");//click on bookmarked icon

			new LoginUsingLTI().ltiLogin("206");//login as 2nd student
			new Navigator().NavigateTo("e-Textbook");			
			new TopicOpen().lessonOpen(0, 0);//open 1st lesson of 1st chapter
			new Navigator().navigateToTab("Fav");//navigater to favrouite tab
			int bookmarkedResourceUbderStarTab=driver.findElements(By.className("ls-right-user-content")).size();
			if(bookmarkedResourceUbderStarTab==0)
				Assert.fail("bookmarked resources shown to student 1st which is bookmarked by 2nd student under star tab");//verify discussion shown on lesson under discussion tab
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase BookmarkedLessonDiscussionNotesNotShownToSecondStudent in method bookmarkedResourcesAndValidateShownByOtherStudentUnderStarTab",e);

		}
	}

}
