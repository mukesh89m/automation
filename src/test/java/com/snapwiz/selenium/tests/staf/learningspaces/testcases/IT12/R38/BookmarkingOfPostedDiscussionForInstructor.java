package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R38;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Bookmark;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Discussion;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;

public class BookmarkingOfPostedDiscussionForInstructor extends Driver {

	@Test(priority = 1, enabled = true)
	public void bookmarkingOfPostedDiscussionForInstructor() {
		try {
			new LoginUsingLTI().ltiLogin("119");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String discussionText = new RandomString().randomstring(6);
			new Discussion().postDiscussion(discussionText);
			String text = driver.findElement(By.className("ls-right-user-post-body")).getText();
			//check discussion is posted or not
			if (!text.equals(discussionText)) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Discussion is not posted, at instructor site.");
			}
			//check it is bookmarked by default
			boolean isBookmarked1 = new Bookmark().isBookmarked("discussion");
			if (isBookmarked1 == false) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The discussion that has bees posted is not bookmarked bydefault.");
			}

			new Navigator().NavigateTo("Course Stream"); // go to Course Stream
			boolean isPresent1 = new Bookmark().isPresentInCourseStream("discussion");
			if (isPresent1 == false) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The discussion that has been posted is not appearing in Course Stream, at instructor site.");
			}
			//check the star color
			String color = new Bookmark().colorOfStarInCourseStream();
			if (!color.equals("yellow")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The star icon for the discussion that has been posted is not appearing yellow in Course Stream, at instructor site.");
			}

			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			//navigate to some other lesson come to the same lesson again
			//click on next lesson 
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-next")));
			Thread.sleep(3000);
			//click on previous lesson 
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-prev")));
			Thread.sleep(3000);
			boolean isBookmarked2 = new Bookmark().isBookmarked("discussion");
			if (isBookmarked2 == false) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to any other lesson and coming back to the previous lesson the * icon of discussion doesn't remain yellow, at instructor site.");
			}
			new Bookmark().unbookmark("discussion");//unbookmark discussion
			boolean isBookmarked3 = new Bookmark().isBookmarked("discussion");
			if (isBookmarked3 == true) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark for discussion the * icon remains yellow, at instructor site.");
			}
			new Navigator().NavigateTo("Course Stream"); // go to Course Stream
			String color1 = new Bookmark().colorOfStarInCourseStream();
			if (color1.equals("yellow")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark for discussion the * icon for the discussion doesnt apprear grey in Course Stream, at instructor site.");
			}
		} catch (Exception e) {
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookmarkingOfPostedDiscussionForInstructor in class BookmarkingOfPostedDiscussionForInstructor", e);
		}
	}

	@Test(priority = 2, enabled = true)
	public void postDicussionAndBookmarkInCourseStreamByInstructor() {
		try {
			new LoginUsingLTI().ltiLogin("119_1");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			String discussionText = new RandomString().randomstring(10);
			new Discussion().postDiscussion(discussionText);
			new Navigator().NavigateTo("Course Stream");// go to Course Stream
			new Bookmark().unbookmarkFromCourseStream();//unbookmark discussion
			String color = new Bookmark().colorOfStarInCourseStream();
			if (color.equals("yellow")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("* icon for a recently added discussion doesnt apprear grey after removing the bookmark in Course Stream, at instructor site.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to eTextbook
			new TOCShow().tocHide();
			//check for * color in lesson
			boolean isBookmarked = new Bookmark().isBookmarked("discussion");
			if (isBookmarked == true) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark for discussion from the Course Stream the * icon for the discussion is not grey in Lesson page, at instructor site.");
			}
		} catch (Exception e) {
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase postDicussionAndBookmarkInCourseStreamByInstructor in class BookmarkingOfPostedDiscussionForInstructor", e);
		}
	}

	@Test(priority = 3, enabled = true)
	public void discussionPostedByAnotherInstructor() {
		try {
			new LoginUsingLTI().ltiLogin("119_2");
			new Navigator().NavigateTo("eTextbook");//navigate to eTextbook
			new TOCShow().tocHide();
			String discussionText = new RandomString().randomstring(10);
			new Discussion().postDiscussion(discussionText);//post discussion
			new LoginUsingLTI().ltiLogin("119_3");
			new Navigator().NavigateTo("eTextbook");//navigate to eTextbook
			new TOCShow().tocHide();
			boolean isBookmarked = new Bookmark().isBookmarked("discussion");
			if (isBookmarked == true) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Discussion posted by another student also appears yellow, at instructor site.");
			}
			new Bookmark().bookmark("discussion");//bookmark the discussion
			boolean isBookmarked1=driver.findElement(By.cssSelector("i[class='ls-right-section-sprites ls--right-star-icon']")).isDisplayed();
		//	boolean isBookmarked1 = new Bookmark().isBookmarked("discussion");
			if (isBookmarked1 == false) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a discussion which has been posted by some other student it doesn't turn to yellow, at instructor site.");
			}

			new Navigator().NavigateTo("Course Stream");// go to Course Stream
			String color = new Bookmark().colorOfStarInCourseStream();
			if (!color.equals("yellow")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a discussion which has been posted by some other student, the Star icon for that particular discussion course stream entry is not yellow, at instructor site.");
			}
		} catch (Exception e) {
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase discussionPostedByAnotherInstructor in class BookmarkingOfPostedDiscussionForInstructor", e);
		}
	}
}
