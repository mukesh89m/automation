package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiscussionWidget;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class AccessingDWAssignment extends Driver{
	
	@Test
	public void accessingDWAssignment() {
		try {
			new LoginUsingLTI().ltiLogin("131_1");        //create a student
			new LoginUsingLTI().ltiLogin("131");       //login as instructor
			new Navigator().NavigateTo("eTextbook");    //navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(131);
			new Navigator().NavigateTo("Course Stream");    //navigate to Course Stream
			driver.findElement(By.cssSelector("span[class='ls-lesson-title ellipsis']")).click();    //click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='tab active']/span[2][text()='Discussion']")));

			//TC row no. 131
			//String discussionTab = driver.findElement(By.xpath("//div[@class='tab active']/span[2][text()='Discussion']")).getText();
			Thread.sleep(2000);
			String discussionTab = driver.findElement(By.xpath("//span[@title='Discussion']")).getText();
			System.out.println("discussionTab:"+discussionTab);
			if (!discussionTab.contains("Discussion")) {
				System.out.println("Discussion "+discussionTab);
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Clicking on activity link from course stream the Instructor doesnt get redirected to discussion tab view in the e-textbook.");
			}
            new TOCShow().chaptertree();   //navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new Navigator().navigateToTab("Assignments"); //navigate to Assignments tab in RHS
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-cycle-bg']")));
			Thread.sleep(3000);
			//TC row no. 132
			String discussionTab1 = driver.findElement(By.xpath("(//span[@class='tab_title'])[4]")).getText();
			if (!discussionTab1.contains("Discussion")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Opening DW assignment from right side tab the Instructor doesnt get redirected to discussion tab view in the e-textbook.");
			}

			new LoginUsingLTI().ltiLogin("131_1");        //login as student
			new Navigator().NavigateTo("Course Stream");    //navigate to Course Stream
			//TC row no. 133
			String assignment = new TextFetch().textfetchbyclass("ls-stream-post__action");
			if (!assignment.contains("posted an assignment")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After assigning a DW assignment the post is not visble in Course Stream at student side.");
			}
			driver.findElement(By.cssSelector("span[class='ls-lesson-title ellipsis']")).click();    //click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[text()='Discussion'])[1]")));

			//TC row no. 134
			String discussionTab2 = driver.findElement(By.xpath("(//span[@class='tab_title'])[4]")).getText();
             Thread.sleep(5000);
			if (!discussionTab2.contains("Discussion")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Clicking on activity link from course stream the student doesnt get redirected to discussion tab view in the e-textbook.");
			}

			new Navigator().navigateToTab("Assignments"); //navigate to Assignments tab in RHS
			//TC row no. 135
			String assignmentTab = driver.findElement(By.className("ls-right-user-subhead")).getText();
			if (!assignmentTab.contains("posted an assignment")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On assigning a DW assignment the DW entry doesnt get added over right tab in textbook in etextbook in student side.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-cycle-bg']")));
			Thread.sleep(9000);
			//TC row no. 136
            new Navigator().navigateToTab("Discussion");
			String discussionTab3 = driver.findElement(By.xpath("//div[@class='tab active']/span[2][text()='Discussion']")).getText();
			if (!discussionTab3.contains("Discussion")) {
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Opening DW assignment from right side tab the student doesnt get redirected to discussion tab view in the e-textbook.");
			}
		} catch (Exception e) {
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase accessingDWAssignment in class AccessingDWAssignment.", e);
		}
	}
}
