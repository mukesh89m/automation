package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentSocialElement;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiscussionWidget;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class AbilityToViewAndCommentOnStudentPerspective extends Driver {
	
	@Test
	public void abilityToViewAndCommentOnStudentPerspective() {
		try {
			new LoginUsingLTI().ltiLogin("56_1");        //login a instructor
			new Navigator().NavigateTo("eTextbook");    //navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			String str = new RandomString().randomstring(10);
			new DiscussionWidget().addTabInDW(str);
			new DiscussionWidget().enableOrDisableDWQuestion(1); //enable the tab
			new LoginUsingLTI().ltiLogin("56");        //login as student
			new Navigator().NavigateTo("eTextbook");    //navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidgetForStudent();
			new Widget().perspectiveAdd();    //add a perspective
			new LoginUsingLTI().ltiLogin("56_1");        //login as instructor
			new Navigator().NavigateTo("eTextbook");    //navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			String perspective = driver.findElement(By.className("ls-comment-entry")).getText();
			//TC row no. 56
			if (perspective == null) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Perspective added by student is not viewable to instructor.");
			}
			//TC row no. 57
			List<WebElement> allElement = driver.findElements(By.className("js-discussion-post-like"));
			allElement.get(1).click(); //click on like for DW in eTextbook which is at 1st index(1st element is disabled )
			Thread.sleep(3000);
			String text = allElement.get(1).getText();
			if (!text.contains("Unlike")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to like the DW.");
			}

			//TC row no. 58
			new Widget().perspectiveAdd();    //add a perspective as instructor
			//TC row no. 59
			new AssignmentSocialElement().clickonlikecoursestream(0);    //click on like for perspective for DW in eTextbook
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			String commentText = new RandomString().randomstring(10);
			new DiscussionWidget().commentOnPerspective(commentText, 0);
			String comment = new TextFetch().textfetchbyclass("ls-perspctive-comments-posted");
			//TC row no. 60
			if (!comment.contains(commentText)) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to comment on perspective.");
			}
			new Click().clickByclassname("ls-comment-like-link");    //click on Like for Comment
			String text1 = new TextFetch().textfetchbyclass("js-comment-like");
			//TC row no. 61
			if (!text1.contains("(1)")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to like the Comment on perspective.");
			}
			new LoginUsingLTI().ltiLogin("56");        //login as student
			new Navigator().NavigateTo("eTextbook");    //navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidgetForStudent();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			Thread.sleep(3000);
			int numberOfPerspectives = driver.findElements(By.className("ls-comment-entry")).size();    //count how many perspective are present
			//TC row no. 63
			if (numberOfPerspectives != 2)    //2 perspective will be there one by the student another by the instructor
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Perspective added by the instructor is not visible to the student.");
			}
			List<WebElement> likeForDW = driver.findElements(By.className("js-discussion-post-like"));
			//TC row no. 62
			for (WebElement l : likeForDW) {
				if (l.isDisplayed() == true) {
					if (!l.getText().contains("1")) {
						new Screenshot().captureScreenshotFromTestCase();
						Assert.fail("Like to DW by instructor is not visible to student.");
					}
				}

			}

			List<WebElement> likeToPerspective = driver.findElements(By.className("js-discussion-comment-like"));
			//TC row no. 64
			if (!likeToPerspective.get(1).getText().contains("1")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Like to perspective by instructor is not visible.");
			}
			List<WebElement> allCommentLink = driver.findElements(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allCommentLink.get(1)); //clicking on comment link for perspective
			Thread.sleep(3000);
			List<WebElement> perspectiveComment = driver.findElements(By.className("ls-perspctive-comments-posted"));
			//TC row no. 65
			if (perspectiveComment.get(perspectiveComment.size() - 1).getText() == null) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Comment by instructor in perspective is not available to student.");
			}
			//TC row no. 66
			String likeToComment = driver.findElement(By.className("js-comment-like")).getText();
			if (!likeToComment.contains("1")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor Like for comment to the perspective is not visible to student.");
			}
		} catch (Exception e) {
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase abilityToViewAndCommentOnStudentPerspective in class AbilityToViewAndCommentOnStudentPerspective.", e);
		}
	}

}
