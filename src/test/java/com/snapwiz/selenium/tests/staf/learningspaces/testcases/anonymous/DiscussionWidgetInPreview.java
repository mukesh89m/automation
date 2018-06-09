package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;

public class DiscussionWidgetInPreview extends Driver {
@Test
	public void discussionWidgetInPreview()
	{
		try
		{
			String course = Config.course;
			String lessonName = ReadTestData.readDataByTagName("DiscussionWidgetInPreview", "lessonName", "2111");
			new Widget().createChapterWidget(2111);
			//Store the current window handle
			String winHandleBefore = driver.getWindowHandle();
			driver.findElement(By.id("preview-lesson")).click();		//click on preview button
			Thread.sleep(5000);
			//Switch to new window opened
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			String courseName = driver.findElement(By.cssSelector("div[id='cms-question-preview-header-course-name']")).getText();
			if(!courseName.equals(course))
				Assert.fail("The course name is wrong in the preview.");
			String lessonText = driver.findElement(By.className("widget-content")).getText();
			if(!lessonText.equals(lessonName))
				Assert.fail("The lesson text is not present in preview.");
			String studentQuestion = driver.findElement(By.className("discussion-widget-student-question")).getText();
			if(!studentQuestion.contains("Text on tab1"))
				Assert.fail("The student question is not displayed in preview.");
			String likeDetails = driver.findElement(By.className("js-post-like")).getText();
			if(!likeDetails.contains("(0)"))
				Assert.fail("Like count is not prsent in the preview.");
			if(!likeDetails.contains("Like"))
				Assert.fail("'Like' label is not prsent in the preview.");
			String commentDetails = driver.findElement(By.cssSelector("a[class='discussion-widget-content-post__footer-comment-link js-toggle-comments']")).getText();
			if(!commentDetails.contains("(0)"))
				Assert.fail("Perspectives count is not prsent in the preview.");
			if(!commentDetails.contains("Perspectives"))
				Assert.fail("'Perspectives' label is not prsent in the preview.");
			String likeIcon = driver.findElement(By.cssSelector("i[class='discussion-widget-icon-img discussion-widget--like-icon']")).getCssValue("background-image");
			if(!likeIcon.contains("sprite.png"))
				Assert.fail("Like icon is absent in the preview.");
			String perspectiveIcon = driver.findElement(By.cssSelector("i[class='discussion-widget-icon-img discussion-widget--comments-icon']")).getCssValue("background-image");
			if(!perspectiveIcon.contains("sprite.png"))
				Assert.fail("Perspective icon is absent in the preview.");
			//Close the new window, if that window no more required
			driver.close();
			//Switch back to original browser (first window)
			driver.switchTo().window(winHandleBefore);
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in discussionWidgetInPreview in DiscussionWidgetInPreview class.",e);
		}
	}

}
