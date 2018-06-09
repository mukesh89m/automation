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

public class OrderOfPerspective extends Driver{
@Test
	public void orderOfPerspective()
	{
		try
		{
			String topicToOpen = ReadTestData.readDataByTagName("tocdata", "topicToOpen", "1");
			new Widget().createChapterWidget(2202);
			
			driver.quit();
			startDriver("firefox");
			
			new LoginUsingLTI().ltiLogin("2202");		//login as instructor
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
			String randomText1 = new RandomString().randomstring(3);
			driver.switchTo().activeElement().sendKeys(randomText1+Keys.RETURN);//comment on perspective
			String randomText2 = new RandomString().randomstring(3);
			driver.switchTo().activeElement().sendKeys(randomText2+Keys.RETURN);//comment on perspective
			//List all the comments
			List<WebElement> allComments = driver.findElements(By.className("ls-comment-entry"));
			String firstComment = allComments.get(0).getText();
			String secondComment = allComments.get(1).getText();
			String thirdComment = allComments.get(2).getText();
			if(!firstComment.equals(randomText) || !secondComment.equals(randomText1) || !thirdComment.equals(randomText2))
				Assert.fail("The perspectives are not ordered based on the age with the earliest perspective on the top and the latest at the bottom.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in orderOfPerspective in OrderOfPerspective class.",e);
		}
	}

}
