package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1512;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;

public class ParticipationRatingTileOnStudentDashboard extends Driver{

	@Test
	public void participationRatingTileOnStudentDashboard()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("2");
			//TC row no. 2
			String participation = driver.findElement(By.cssSelector("div[class='box class-participation-score']")).getText();
			if(!participation.contains("PARTICIPATION RATING"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Participation Rating\" tile doesnt appear in student side dashboard.");
			}
			//TC row no. 3
			String helpIcon = driver.findElement(By.id("participationRating")).getCssValue("background-image");
			if(!helpIcon.contains("ls_dashboard_help_icon.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Participation Rating\" tile doesnt have the help icon in student side dashboard.");
			}
			//TC row no. 4
			String score = driver.findElement(By.className("number")).getText();
			if(!score.contains("0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Rating is not zero in student side dashboard.");
			}
			//TC row no. 5
			String bottom = driver.findElement(By.className("percent")).getText();
			if(!bottom.contains("Compared to your classmates"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"My Score:\" text is not displayed at the bottom of the \"Participation Rating\" tile.");
			}
			//TC row no. 6
			String tooltip = driver.findElement(By.id("participationRating")).getAttribute("tooltiptext");
			if(!tooltip.contains("This shows your level of involvement in class discussions from the Course Stream."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Tooltip text is not displayed properly for the \"Participation Rating\" tile.");
			}
			driver.findElement(By.className("number")).click();//Click on Participation Rating tile
			Thread.sleep(2000);
			//TC row no. 7
			String url = driver.getCurrentUrl();
			if(!url.contains("coursestream"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Clicking on Participation Rating tile student doesnt navigate to Course Stream.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase participationRatingTileOnStudentDashboard in class ParticipationRatingTileOnStudentDashboard",e);
		}
	}

}

