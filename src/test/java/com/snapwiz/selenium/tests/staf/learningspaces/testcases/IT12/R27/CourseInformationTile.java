package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R27;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class CourseInformationTile extends Driver {

	@Test(priority = 1, enabled = true)
	public void courseInformationTile() {
		try {
			new LoginUsingLTI().ltiLogin("10");//login as instructor
			String courseName = driver.findElement(By.className("course-detail-container")).getText();
			if (courseName == null) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After login, the course name is absent in Course Information tile in instructor dashboard.");
			}
			String innerHtml = driver.findElement(By.className("course-details-container")).getAttribute("innerHTML");
			System.out.println(innerHtml);
			if (!innerHtml.contains("<img src=\"https://s3.amazonaws.com/wiley-p/253/bioprinciples")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After login, the course thumbnail is absent Course Information tile in instructor dashboard.");
			}
			String studyButton = driver.findElement(By.className("study-course-link")).getText();
			if (!studyButton.contains("STUDY")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After login, the STUDY button is absent Course Information tile in instructor dashboard.");
			}
		} catch (Exception e) {
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase courseInformationTile in class CourseInformationTile.", e);
		}
	}

	@Test(priority = 2, enabled = true)
	public void recentActivityInCourseInformationTile() {
		try {
			new LoginUsingLTI().ltiLogin("24");//login as instructor
			new TOCShow().chaptertree();
			new TOCShow().tocHide();
			String chName = driver.findElement(By.xpath("(//span[@class='tab_title'])[3]")).getAttribute("title");
			System.out.println("chapter :" + chName);
			new Navigator().NavigateTo("Dashboard"); //go to Dashboard
			String recentActivities = driver.findElement(By.className("ls-dashboard-activities-chaptername")).getText();
			System.out.println("......." + recentActivities);
			if (!chName.contains(recentActivities)) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The recent activity doesnt have the correct lesson name as shown in TOC(for lesson at chapter level).");
			}
			String activityLabel = driver.findElement(By.className("recent-activity-label")).getText();
			if (activityLabel == null) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The recent activity label doesnt has the activity name in course Info Tile(for lesson at chapter level).");
			}
			String activityAge = driver.findElement(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']")).getText();
			if (activityAge == null) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The recent activity label doesnt has the activity age in course Info Tile(for lesson at chapter level).");
			}
			int activities = driver.findElements(By.cssSelector("a[class='activity-row activity-lesson']")).size();
			System.out.println("activities: " + activities);
			if (activities != 1) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Dont have only one entry in recent activity tile when a single activity is done.");
			}
		} catch (Exception e) {
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase recentActivityInCourseInformationTile in class CourseInformationTile.", e);
		}
	}

	@Test(priority = 3, enabled = true)
	public void recentActivityForTopicLevelLessonInCourseInformationTile() {
		try {
			new LoginUsingLTI().ltiLogin("27");//login as instructor
			new Navigator().NavigateTo("eTextbook"); //go to etext book
			new TOCShow().tocHide();
			String chName = driver.findElement(By.xpath("(//span[@class='tab_title'])[3]")).getAttribute("title");
			new Navigator().NavigateTo("Dashboard"); //go to Dashboard
			String recentActivities = driver.findElement(By.className("ls-dashboard-activities-chaptername")).getText();
			if (!chName.contains(recentActivities)) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The recent activity doesnt have the correct lesson name as shown in TOC(for lesson at topic level).");
			}
			String activityLabel = driver.findElement(By.className("recent-activity-label")).getText();
			if (activityLabel == null) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The recent activity label doesnt has the activity name in course Info Tile(for lesson at topic level).");
			}
			String activityAge = driver.findElement(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']")).getText();
			if (activityAge == null) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The recent activity label doesnt has the activity age in course Info Tile(for lesson at topic level).");
			}
			int activities = driver.findElements(By.cssSelector("a[class='activity-row activity-lesson']")).size();
			System.out.println("activities: " + activities);
			if (activities != 1) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Dont have one entry in recent activity tile when a single activity is done.");
			}
			//click on lesson activity
			driver.findElement(By.cssSelector("span.ls-dashboard-activities-chaptername")).click();
			//driver.findElement(By.cssSelector("a[title='"+card2topic1+"']")).click();
			Thread.sleep(3000);
			String lessonActivity = driver.findElement(By.xpath("(//span[@class='tab_title'])[3]")).getAttribute("title");
			Thread.sleep(2000);
			if (!lessonActivity.contains(chName)) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking recent activity Instructor doesnt navigate to the eTextbook with the specific lesson opened in the center pane .");
			}
			new TOCShow().chaptertree();
			/*String highlightedText = driver.findElement(By.cssSelector("li[class='toc__title text--truncate selected']")).getText();
			if (!highlightedText.contains(lessonActivity)) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking recent activity the chapter opened is not highlighted in TOC.");
			}*/

		} catch (Exception e) {
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase recentActivityForTopicLevelLessonInCourseInformationTile in class CourseInformationTile.", e);
		}
	}

	}



