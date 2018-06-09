package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;

public class VerifyUIOfDisccussionInStudentSite extends Driver {
@Test
	public void verifyUIOfDisccussionInStudentSite()
	{
		try
		{
			String topicToOpen = ReadTestData.readDataByTagName("tocdata", "topicToOpen", "1");
			new Widget().createChapterWidget(2337);
			
			driver.quit();
			startDriver("firefox");
			new LoginUsingLTI().ltiLogin("2337");		//login as instructor
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().topicOpen(topicToOpen);
			new Widget().perspectiveAdd();			//add a perspective
			String randomText = new RandomString().randomstring(3);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Comments']")));
			Thread.sleep(3000);
			driver.switchTo().activeElement().sendKeys(randomText+Keys.ENTER);//comment on perspective
			Thread.sleep(3000);
			
			new LoginUsingLTI().ltiLogin("23371");		//login as student
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().topicOpen(topicToOpen);
			//verify the UI
			String questionText = driver.findElement(By.className("ls-dialog-txt")).getText();
			if(!questionText.equals("Text on tab1"))
				Assert.fail("The enabled question text is missing in the student view.");
			//Like and perspective options
			String socialElements = driver.findElement(By.cssSelector("ul[class='ls-content-post__footer']")).getText();
			if(!socialElements.contains("(0)"))
				Assert.fail("Like count is not present for discussion widget in student site.");
			if(!socialElements.contains("Like"))
				Assert.fail("'Like' label is absent for discussion widget in student site.");
			if(!socialElements.contains("(1) Perspectives"))
				Assert.fail("'Perspectives' label is absent along with Perspectives  count for discussion widget in student site.");
			//jump out icon
			String jumpoutIcon = driver.findElement(By.cssSelector("span[class='jumpout-icon ls-jumpout-img']")).getCssValue("background-image");
			if(!jumpoutIcon.contains("sprite.png"))
				Assert.fail("The jump out icon is absent for discussion widget in student view");
			//find the added perspective
			boolean perspective = driver.findElement(By.cssSelector("ul[class='ls-list ls-content-post__comments']")).isDisplayed();
			//click perspective
			driver.findElement(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")).click();
			Thread.sleep(3000);
			boolean perspective1 = driver.findElement(By.cssSelector("div[class='ls-content-post__comments openPerspective']")).isDisplayed();
			if(perspective == true || perspective1 == false)
				Assert.fail("By default all perspectives are not collapsed.");
			
			boolean comments = driver.findElement(By.cssSelector("ul[class='ls-list ls-content-perspective-comments']")).isDisplayed();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Comments']")));
			Thread.sleep(3000);
			boolean comments1 = driver.findElement(By.cssSelector("ul[class='ls-list ls-content-perspective-comments']")).isDisplayed();
			
			if(comments == true || comments1 == false)
				Assert.fail("By default all comments on perspective are not collapsed.");
			
			//click on jump icon
			driver.findElement(By.cssSelector("span[class='jumpout-icon ls-jumpout-img']")).click();
			Thread.sleep(3000);
			String discussionTab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!discussionTab.equals("Discussion"))
				Assert.fail("in Student site, After clicking on jump icon discussion widget doesn't open in a new tab.");
			
			List<WebElement> allSocialElements = driver.findElements(By.cssSelector("ul[class='ls-content-post__footer']"));
			String socialElements1 = allSocialElements.get(5).getText();		//elements are of 5th index
			if(!socialElements1.contains("(0)"))
				Assert.fail("Like count is not present for discussion widget in student site, when opened in a new tab.");
			if(!socialElements1.contains("Like"))
				Assert.fail("'Like' label is absent for discussion widget in student site, when opened in a new tab.");
			if(!socialElements1.contains("(1) Perspectives"))
				Assert.fail("'Perspectives' label is absent along with comment count for discussion widget in student site, when opened in a new tab.");
			
			String socialElements2 = allSocialElements.get(6).getText();		//elements are of 6th index
			if(!socialElements2.contains("(0)"))
				Assert.fail("Like count for perspective comment is not present for discussion widget in student site, , when opened in a new tab.");
			if(!socialElements2.contains("Like"))
				Assert.fail("'Like' label for perspective comment is absent for discussion widget in student site, when opened in a new tab.");
			if(!socialElements2.contains("(1) Comments"))
				Assert.fail("'Comments' label for perspective comment is absent along with comment count for discussion widget in student site, when opened in a new tab.");
			
			//list all like icon
			List<WebElement> allLikeIcon1 = driver.findElements(By.cssSelector("i[class='ls-icon-img ls--like-icon']"));
			
			//verifying like icon for perspective
			String likeIcon1 = allLikeIcon1.get(5).getCssValue("background-image");
			if(!likeIcon1.contains("sprite.png"))
				Assert.fail("Like icon is absent for discusion widget in student site, when opened in a new tab.");
			//verifying like icon for perspective comment
			String likeIcon2 = allLikeIcon1.get(6).getCssValue("background-image");
			if(!likeIcon2.contains("sprite.png"))
				Assert.fail("Like icon for comment on perspective is absent for discusion widget in student site, when opened in a new tab.");
			
			//List all comment icon
			List<WebElement> allCommentIcon1 = driver.findElements(By.cssSelector("i[class='ls-icon-img ls--comments-icon']"));
			//verifying comment icon for perspective
			String commentIcon = allCommentIcon1.get(4).getCssValue("background-image");
			//String commentIcon = driver.findElement(By.cssSelector("i[class='ls-icon-img ls--comments-icon']")).getCssValue("background-image");
			if(!commentIcon.contains("sprite.png"))
				Assert.fail("Comment icon is absent for discusion widget in student site, when opened in a new tab.");
			
			//verifying comment icon for perspective comment
			String commentIcon1 = allCommentIcon1.get(5).getCssValue("background-image");
			if(!commentIcon1.contains("sprite.png"))
				Assert.fail("Comment icon for comment on perspective is absent for discusion widget in student site, when opened in a new tab.");
			
			//verify perspective comments are collapsed or not 
			boolean commentCollapse = driver.findElement(By.cssSelector("a[title='Comments']")).isSelected();
			if(commentCollapse == true)
				Assert.fail("in student site, The Comments for the perspective are not collapsed when discussion wideget is opened in new tab.");
			
			driver.findElement(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']")).click();		//click on comments
			Thread.sleep(3000);
			List<WebElement> allcomments = driver.findElements(By.className("ls-perspctive-comments-posted"));
			String commentOnPerspective = allcomments.get(1).getText(); 		//comment is on index 1
			if(!commentOnPerspective.equals(randomText))
				Assert.fail("On clicking on comment option Comments for the perspective are not displayed.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in VerifyUIOfDisccussionInStudentSite in verifyUIOfDisccussionInStudentSite class.",e);
		}
	}

}
