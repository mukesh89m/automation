package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;

import org.openqa.selenium.By;
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

public class EnablingADifferentQuestionAfterStudentAction extends Driver {
	@Test
	public void enablingADifferentQuestionAfterStudentAction()
	{
		try
		{
			String topicToOpen = ReadTestData.readDataByTagName("tocdata", "topicToOpen", "1");
			String textonstudentsite = ReadTestData.readDataByTagName("EnablingADifferentQuestionAfterStudentAction", "textonstudentsite", "2153");
			/*new Widget().createChapterWidget(2153);
			driver.quit();
			Driver.startDriver(); */
			new LoginUsingLTI().ltiLogin("2153");		//login as student
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().topicOpen(topicToOpen);
			//click to add perspective
			driver.findElement(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")).click();
			Thread.sleep(3000);
			//click on text area
			driver.findElement(By.cssSelector("textarea[name='perspective']")).click();
			Thread.sleep(3000);
			String randomText = new RandomString().randomstring(3);
			driver.switchTo().activeElement().sendKeys(randomText+Keys.RETURN);//comment on perspective
			Thread.sleep(3000);
			String comment = driver.findElement(By.className("ls-comment-entry")).getText();
			if(!comment.equals(randomText))
				Assert.fail("After entering the comment on perspective it is not getting displayed.");
				
			new LoginUsingLTI().ltiLogin("21531");		//login as instructor
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().topicOpen(topicToOpen);
			//list all the tabs
			List<WebElement> widgettabs = driver.findElements(By.cssSelector("span[class='publisher-count ls-publisherIcons-bg ls-publisher-count-bg-holder']"));  //Finding all tabs
			widgettabs.get(1).click(); //goto 2nd tab
			Thread.sleep(3000);
			//enable the 2nd question
			driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg']")).click();
			Thread.sleep(3000);
			
			new LoginUsingLTI().ltiLogin("2153");		//login as student
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().topicOpen(topicToOpen);
			//list all the questions
			List<WebElement> allstudentQuestion1 = driver.findElements(By.className("ls-student-question"));
			String studentQuestion1 = allstudentQuestion1.get(1).getText();		//text are of 2nd tab so index 1
			//verify the text for 2nd question tab
			if(!studentQuestion1.equals(textonstudentsite))
				Assert.fail("At student site the question which has been newly enabled is not getting displayed.");
			
			new LoginUsingLTI().ltiLogin("21531");		//login as instructor
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().topicOpen(topicToOpen);
			//verification of all social elements
			List<WebElement> allSocialElements = driver.findElements(By.cssSelector("ul[class='ls-perspective-footer ls-content-post__footer']"));
			String socialElements = allSocialElements.get(1).getText();
			if(!socialElements.contains("(0)"))
				Assert.fail("Like count is not present for discussion widget in instructor site.");
			if(!socialElements.contains("Like"))
				Assert.fail("'Like' label is absent for discussion widget in instructor site.");
			if(!socialElements.contains("(0) Perspectives"))
				Assert.fail("'Perspectives' label is absent along with comment count for discussion widget in instructor site.");
			//verifying like icon 
			String likeIcon = driver.findElement(By.cssSelector("i[class='ls-icon-img ls--like-icon']")).getCssValue("background-image");
			if(!likeIcon.contains("sprite.png"))
				Assert.fail("Like icon is absent for discusion widget in instructor site.");
			//verifying comment Icon
			String commentIcon = driver.findElement(By.cssSelector("i[class='ls-icon-img ls--comments-icon']")).getCssValue("background-image");
			if(!commentIcon.contains("sprite.png"))
				Assert.fail("Comment icon is absent for discusion widget in instructor site.");
			
			List<WebElement> widgettabs1 = driver.findElements(By.cssSelector("span[class='publisher-count ls-publisherIcons-bg ls-publisher-count-bg-holder']"));  //Finding all tabs
			widgettabs1.get(0).click(); //goto 1st tab
			Thread.sleep(3000);
			//enable the 1st question
			driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg']")).click();
			Thread.sleep(3000);
			String iconEnableText = driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg selected']")).getAttribute("title");
			if(!iconEnableText.equals(""))
				Assert.fail("On clicking the enable icon the icon is not becoming enabled.");
			//verify if 2nd question is disabled or not
			widgettabs1.get(1).click(); //goto 2nd tab
			Thread.sleep(3000);
			String iconEnableText1 = driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg']")).getAttribute("title");
			if(!iconEnableText1.equals("Enable"))
				Assert.fail("On clicking the enable icon the icon is not becoming enabled.");
			
			new LoginUsingLTI().ltiLogin("2153");		//login as student
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().topicOpen(topicToOpen);
			//list all the questions
			List<WebElement> allstudentQuestion2 = driver.findElements(By.className("ls-student-question"));
			String studentQuestion2 = allstudentQuestion2.get(0).getText();		//text are of 1st tab so index 0
			//verify the text for 1st question tab
			if(!studentQuestion2.equals("D: Text on tab1"))
				Assert.fail("At student site the question which has been newly enabled is not getting displayed.");
			//verification of all social elements in student site
			List<WebElement> allSocialElements1 = driver.findElements(By.cssSelector("ul[class='ls-content-post__footer']"));
			String socialElements1 = allSocialElements1.get(0).getText();
			if(!socialElements1.contains("(0"))
				Assert.fail("Like count is not present for discussion widget in student site.");
			if(!socialElements1.contains("Like"))
				Assert.fail("'Like' label is absent for discussion widget in student site.");
			if(!socialElements1.contains("(1) Perspectives"))
				Assert.fail("'Comment' label is absent aloong with comment count for discussion widget in student site.");
			//verifying like icon 
			String likeIcon1 = driver.findElement(By.cssSelector("i[class='ls-icon-img ls--like-icon']")).getCssValue("background-image");
			if(!likeIcon1.contains("sprite.png"))
				Assert.fail("Like icon is absent for discusion widget in student site.");
			//verifying comment Icon
			String commentIcon1 = driver.findElement(By.cssSelector("i[class='ls-icon-img ls--comments-icon']")).getCssValue("background-image");
			if(!commentIcon1.contains("sprite.png"))
				Assert.fail("Comment icon is absent for discusion widget in student site.");
			
			new LoginUsingLTI().ltiLogin("21531");		//login as instructor
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().topicOpen(topicToOpen);
			List<WebElement> widgettabs2 = driver.findElements(By.cssSelector("span[class='publisher-count ls-publisherIcons-bg ls-publisher-count-bg-holder']"));  //Finding all tabs
			widgettabs2.get(1).click(); //goto 2nd tab
			//verification of all social elements in instructor site in 2nd tab
			List<WebElement> allSocialElements2 = driver.findElements(By.cssSelector("ul[class='ls-perspective-footer ls-content-post__footer']"));
			String socialElements2 = allSocialElements2.get(1).getText();
			if(!socialElements2.contains("(0"))
				Assert.fail("Like count is not present for discussion widget in instructor site in 2nd tab.");
			if(!socialElements2.contains("Like"))
				Assert.fail("'Like' label is absent for discussion widget in instructor site in 2nd tab.");
			if(!socialElements2.contains("(0) Perspectives"))
				Assert.fail("'Comment' label is absent aloong with comment count for discussion widget in instructor site in 2nd tab.");
			//verifying like icon 
			String likeIcon2 = driver.findElement(By.cssSelector("i[class='ls-icon-img ls--like-icon']")).getCssValue("background-image");
			if(!likeIcon2.contains("sprite.png"))
				Assert.fail("Like icon is absent for discusion widget in instructor site in 2nd tab.");
			//verifying comment Icon
			String commentIcon2 = driver.findElement(By.cssSelector("i[class='ls-icon-img ls--comments-icon']")).getCssValue("background-image");
			if(!commentIcon2.contains("sprite.png"))
				Assert.fail("Comment icon is absent for discusion widget in instructor site in 2nd tab.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in enablingADifferentQuestionAfterStudentAction in EnablingADifferentQuestionAfterStudentAction class.",e);
		}
	}

}
