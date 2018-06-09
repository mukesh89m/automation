package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class AbilityToViewAllSocialElementsFromStudent extends Driver {
@Test
	public void abilityToViewAllSocialElementsFromStudent(){
		try
		{
			new LoginUsingLTI().ltiLogin("2176");		//login as student
			new Navigator().NavigateTo("eTextbook");
            new TopicOpen().openLessonWithDiscussionWidget();
            new Widget().perspectiveAdd();
            new Navigator().NavigateTo("eTextbook");
            new TopicOpen().openLessonWithDiscussionWidget();
            new Widget().commentonPerspective(0);

			
			new LoginUsingLTI().ltiLogin("21761");		//login as instructor
			new Navigator().NavigateTo("eTextbook");
            new TopicOpen().openLessonWithDiscussionWidget();
			//list all the perspective
			List<WebElement> allPerspective = driver.findElements(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"));
			allPerspective.get(0).click();		//click on perspective
			Thread.sleep(3000);
			//verify the comment
			String comment = driver.findElement(By.className("ls-comment-entry")).getText();
			if(comment == null)
				Assert.fail("The comment given on the perspective by a student is not visible to the instructor.");
			
			String socialElements = driver.findElement(By.cssSelector("ul[class='ls-content-post__footer']")).getText();
            if(!socialElements.contains("(0)"))
				Assert.fail("Like count is not present for discussion widget in instructor site.");
			if(!socialElements.contains("Like"))
				Assert.fail("'Like' label is absent for discussion widget in instructor site.");
			if(!socialElements.contains("(1) Comments"))
				Assert.fail("'Comments' label is absent along with comment count for discussion widget in instructor site.");
			
			//list all like icon
			List<WebElement> allLikeIcon = driver.findElements(By.cssSelector("i[class='ls-icon-img ls--like-icon']"));
			String likeIcon = allLikeIcon.get(1).getCssValue("background-image");		//like icon for perspective in 1st tab will be at 1st index
			if(!likeIcon.contains("sprite.png"))
				Assert.fail("Like icon is absent in the perspective");
			
			//list all comment icon
			List<WebElement> allCommentIcon = driver.findElements(By.cssSelector("i[class='ls-icon-img ls--comments-icon']"));
			String CommentIcon = allCommentIcon.get(1).getCssValue("background-image");		//comment icon for perspective in 1st tab will be at 1st index
			if(!CommentIcon.contains("sprite.png"))
				Assert.fail("Comment icon is absent in the perspective");

			new LoginUsingLTI().ltiLogin("2176");		//login as student
            new Navigator().NavigateTo("eTextbook");
            new TopicOpen().openLessonWithDiscussionWidget();
            new Widget().perspectiveAdd();
			String comment1 = driver.findElement(By.className("ls-comment-entry")).getText();
			if(comment1 == null)
				Assert.fail("After entering the comment on perspective it is not getting displayed.");

			new LoginUsingLTI().ltiLogin("21761");		//login as instructor
			new Navigator().NavigateTo("eTextbook");
            new TopicOpen().openLessonWithDiscussionWidget();
			//search for perspective
			int comment2 = driver.findElements(By.className("ls-comment-entry")).size();
			if(comment2 != 0)
				Assert.fail("By default all perspectives and comments underneath are not collapsed.");
			
			//verify jump icon
			String jumpIcon = driver.findElement(By.cssSelector("span[class='jumpout-icon ls-jumpout-img']")).getCssValue("background-image");
			if(!jumpIcon.contains("sprite.png"))
				Assert.fail("The jump icon is missing in discussion widget.");
			//click on jump icon
			driver.findElement(By.cssSelector("span[class='popout-widget']")).click();
			Thread.sleep(3000);
			String discussionTab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!discussionTab.equals("Discussion"))
				Assert.fail("After clicking on jump icon discussion widget doesn't open in a new tab.");
			
			List<WebElement> allSocialElements = driver.findElements(By.cssSelector("ul[class='ls-content-post__footer']"));
			String socialElements1 = allSocialElements.get(0).getText();
            if(!socialElements1.contains("(0)"))
				Assert.fail("Like count is not present for discussion widget in instructor site, when opened in a new tab.");
			if(!socialElements1.contains("Like"))
				Assert.fail("'Like' label is absent for discussion widget in instructor site, when opened in a new tab.");
			if(!socialElements1.contains("(2) Perspectives"))
				Assert.fail("'Perspectives' label is absent along with comment count for discussion widget in instructor site, when opened in a new tab.");
			
			String socialElements2 = allSocialElements.get(1).getText();
			if(!socialElements2.contains("(0)"))
				Assert.fail("Like count for perspective comment is not present for discussion widget in instructor site, , when opened in a new tab.");
			if(!socialElements2.contains("Like"))
				Assert.fail("'Like' label for perspective comment is absent for discussion widget in instructor site, when opened in a new tab.");
			if(!socialElements2.contains("(1) Comments"))
				Assert.fail("'Comments' label for perspective comment is absent along with comment count for discussion widget in instructor site, when opened in a new tab.");
			
			//list all like icon
			List<WebElement> allLikeIcon1 = driver.findElements(By.cssSelector("i[class='ls-icon-img ls--like-icon']"));
			
			//verifying like icon for perspective
			String likeIcon1 = allLikeIcon1.get(0).getCssValue("background-image");
			if(!likeIcon1.contains("sprite.png"))
				Assert.fail("Like icon is absent for discusion widget in instructor site, when opened in a new tab.");
			//verifying like icon for perspective comment
			String likeIcon2 = allLikeIcon1.get(1).getCssValue("background-image");
			if(!likeIcon2.contains("sprite.png"))
				Assert.fail("Like icon for comment on perspective is absent for discusion widget in instructor site, when opened in a new tab.");
			
			//List all comment icon
			List<WebElement> allCommentIcon1 = driver.findElements(By.cssSelector("i[class='ls-icon-img ls--comments-icon']"));
			
			//verifying comment icon for perspective
			String commentIcon = allCommentIcon1.get(0).getCssValue("background-image");
			//String commentIcon = driver.findElement(By.cssSelector("i[class='ls-icon-img ls--comments-icon']")).getCssValue("background-image");
			if(!commentIcon.contains("sprite.png"))
				Assert.fail("Comment icon is absent for discusion widget in instructor site, when opened in a new tab.");
			
			//verifying comment icon for perspective comment
			String commentIcon1 = allCommentIcon1.get(1).getCssValue("background-image");
			if(!commentIcon1.contains("sprite.png"))
				Assert.fail("Comment icon for comment on perspective is absent for discusion widget in instructor site, when opened in a new tab.");
			
			//verify perspective comments are collapsed or not 
			boolean commentCollapse = driver.findElement(By.cssSelector("a[title='Comments']")).isSelected();
			if(commentCollapse == true)
				Assert.fail("The Comments for the perspective are not collapsed when discussion wideget is opened in new tab.");
			
			driver.findElement(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']")).click();		//click on comments
			Thread.sleep(3000);
			String commentOnPerspective = driver.findElement(By.className("ls-perspctive-comments-posted")).getText();
			if(commentOnPerspective == null)
				Assert.fail("On clicking on comment option Comments for the perspective are not displayed.");
			
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in abilityToViewAllSocialElementsFromStudent in AbilityToViewAllSocialElementsFromStudent class.",e);
		}
	}

}
