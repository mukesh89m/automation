package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R27;

import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;

import java.util.List;

public class OrderingOfActivities extends Driver {
	
	@Test(priority = 1, enabled = true)
	public void orderingOfActivities()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("57");//login as instructor
			new Navigator().NavigateTo("eTextbook"); //go to etext book
			new TopicOpen().lessonOpen(0, 1);  //access a topic level lesson
			String lessonName1 = driver.findElement(By.xpath("(//span[@class='tab_title'])[3]")).getAttribute("title");
			new Navigator().NavigateTo("eTextbook"); //go to etext book
			new TopicOpen().lessonOpen(0, 0);  //access a topic level lesson
            String lessonName = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			new Navigator().NavigateTo("Dashboard"); //go to Dashboard
			Thread.sleep(2000);
			String recentActivities = driver.findElement(By.cssSelector("span[class='ls-dashboard-activities-chaptername']")).getText();
			if(!recentActivities.contains(lessonName))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The recent activity doesnt have the latest accessed lesson on top.");
			}
			//click on lesson activity
			driver.findElement(By.cssSelector("a[title='"+lessonName1+"']")).click();//click on recent activyty which is at bottom
		    Thread.sleep(3000);
		    String lessonActivity = driver.findElement(By.xpath("(//span[@class='tab_title'])[3]")).getAttribute("title");
		    if(!lessonActivity.contains(lessonName1))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking recent activity which is at bottom, Instructor doesnt navigate to the eTextbook with the specific lesson opened in the center pane .");
		    }
		    new Navigator().NavigateTo("Dashboard"); //go to Dashboard
		    Thread.sleep(2000);
		    String recentActivities1 = driver.findElement(By.className("ls-dashboard-activities-chaptername")).getText();
			if(!recentActivities1.contains(lessonName1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The recent activity doesnt have the latest accessed lesson on top.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase orderingOfActivities in class OrderingOfActivities.",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void orderingOfActivitiesOnClosingLesson()
	{
		try
		{

			LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);

			new LoginUsingLTI().ltiLogin("60");//login as instructor
			new Navigator().NavigateTo("Learning Content"); //go to etext book
            new TopicOpen().lessonOpen(0, 0);  //access a topic level lesson
			Thread.sleep(2000);
            new Navigator().NavigateTo("Learning Content"); //go to etext book
			Thread.sleep(4000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", lessonPage.getLessonIcon());
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", lessonPage.getOpenTabNewLessonIcon());
			String lessonName1 = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			driver.findElement(By.xpath("(//span[@class='close_tab'])[4]")).click();
			new Navigator().NavigateTo("Dashboard"); //go to Dashboard
			Thread.sleep(2000);
			String recentActivities = driver.findElement(By.cssSelector("span[class='ls-dashboard-activities-chaptername']")).getText();
			if(!recentActivities.contains(lessonName1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("If we open a lesson in new tab and close it then it doesnt appear in Recent Activity in Dashboard.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase orderingOfActivitiesOnClosingLesson in class OrderingOfActivities.",e);
		}
	}

}
