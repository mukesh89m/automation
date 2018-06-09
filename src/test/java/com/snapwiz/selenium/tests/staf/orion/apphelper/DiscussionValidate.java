package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.uihelper.ComboBox;

public class DiscussionValidate 
{
	//discussion validate when student login with other class section
	public void discussionvalidate()
	{
		try
		{
			List<WebElement> replyddiscussionsectionchange=Driver.driver.findElements(By.className("my-journal-activity-event-action"));//click on reply discussion
			replyddiscussionsectionchange.get(0).click();
			Thread.sleep(2000);
			String notificationtext="You have participated in these discussions in your old class section. Since you have moved to a new class section, your access to these discussions have been revoked.";
			String notification=Driver.driver.findElement(By.className("al-notification-message-body")).getText();
			System.out.println(notification);
			if(!notification.equals(notificationtext))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("notification not shown");
			}
			
			replyddiscussionsectionchange.get(1).click();
			Thread.sleep(2000);
			String notification1=Driver.driver.findElement(By.className("al-notification-message-body")).getText();
			if(!notification1.equals(notificationtext))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("notification1 not shown");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("excpetion in app helper dkiscusssion validate",e);
		}
	}
	
	public void validateperformanceProductivitychallenge()
	{
		try
		{
			new Navigator().NavigateToStudentReport();
			Driver.driver.findElement(By.id("al-performance-report")).click();//click on performance report link
			Thread.sleep(2000);
			int questioncard=Driver.driver.findElements(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']")).size();
			if(questioncard<1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("question card not shown");
			}
			boolean performance=Driver.driver.findElement(By.className("al-performance-chart-label")).isDisplayed();
			if(performance==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("performance report not shown");
			}
			new ComboBox().selectValue(4, "Productivity Report");
			boolean productivitychart=Driver.driver.findElement(By.className("al-productivity-chart-block")).isDisplayed();
			if(productivitychart==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("productivitychart report not shown");
			}
			Driver.driver.findElement(By.id("al-metacognitive-report")).click();//click on metacognitive report link
			Thread.sleep(2000);
			boolean metacognity=Driver.driver.findElement(By.id("al-metacognitive-report-display-block")).isDisplayed();
			if(metacognity==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("metacognity report not shown");
			}
			Driver.driver.findElement(By.id("al-most-challenging-activity")).click();//click on most-challenging-activity report link
			Thread.sleep(2000);
			boolean challengingactivty=Driver.driver.findElement(By.id("al-most-challenging-activities-tlo")).isDisplayed();
			if(challengingactivty==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("challengingactivty report not shown");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("excpetion in app helper validateperformanceProductivitychallenge validate",e);
		}
	}
	//return perfornamce producibility proficiency
	public String insvalidateperformanceproductivitychallenging()
	{
		String value=null;
		try
		{
            Thread.sleep(3000);
			value=Driver.driver.findElement(By.className("al-proficiency-percentage")).getText();
			
		} 
		catch (Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in app helper validateperformanceproductivitychallenging",e);
		}
		return value;
	}

}
