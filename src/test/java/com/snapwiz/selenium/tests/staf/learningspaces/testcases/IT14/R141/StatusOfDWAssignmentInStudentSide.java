package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class StatusOfDWAssignmentInStudentSide extends Driver{
	@Test
	public void statusOfDWAssignmentInStudentSide()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("404_1");		//create a student
			new LoginUsingLTI().ltiLogin("404_2");		//create a student
			new LoginUsingLTI().ltiLogin("404");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(404);

			new LoginUsingLTI().ltiLogin("404_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
			String perspective = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);
			new LoginUsingLTI().ltiLogin("404_2");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
			String perspective2 = new RandomString().randomstring(5);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective2);

			new LoginUsingLTI().ltiLogin("404");		//login as instructor
			new Assignment().viewResponseOfDW();
			Thread.sleep(2000);
			driver.findElement(By.id("view-user-question-performance-feedback-box")).click();//click inside the feedback textbox
			Thread.sleep(2000);
			driver.findElement(By.id("view-user-question-performance-feedback-box")).clear();
			String feedbackText = new RandomString().randomstring(5);
			driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys(feedbackText);
			driver.findElement(By.className("view-user-discussion-performance-save-btn")).click();	//click on save button
			Thread.sleep(2000);
			

			new LoginUsingLTI().ltiLogin("404_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			//TC row no. 406
			String status = driver.findElement(By.className("ls-assignment-activity-data-card")).getText();
			if(!status.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After submission by the student the DW assignment status count is not present in Submitted box in student side.");
			}
			//TC row no. 407
			String status1 = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status1.contains("Submitted"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After submission by the student the DW assignment status doesnt change to Submitted in student side.");
			}
			
		
			new LoginUsingLTI().ltiLogin("404");		//login as instructor
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();	//click on View Student Responses
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("div[title='Release Grade for All']")).click();	//click on Release Grade for All
			Thread.sleep(2000);

			new LoginUsingLTI().ltiLogin("404_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
				
			//TC row no. 408
			String status2 = driver.findElement(By.className("ls-assignment-activity-data-card")).getText();
			if(!status2.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After submission by the student the DW assignment status count is not present in Graded box in student side.");
			}
			//TC row no. 409
			String status3 = driver.findElement(By.className("ls-assignment-status")).getText();
			if(!status3.contains("Score"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After submission by the student the DW assignment status doesnt change to Score in student side.");
			}
			driver.findElement(By.cssSelector("span[class='learning-activity-title']")).click();//click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));

		    //TC row no. 410
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
			String tab = driver.findElement(By.xpath("(//span[@class='tab_title'])[4]")).getText();
			if(!tab.contains("Discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On opening DW assignment from Assignment the student doesnt get redirected to discussion tab view in the e-textbook.");
			}
			Thread.sleep(3000);
			new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
			new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
			//TC row no. 411
			List<WebElement>  perspective1 = driver.findElements(By.className("ls-comment-entry"));


			if(!perspective1.get(0).getText().contains(perspective))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The added perspective is not displayed in discussion tab view in the e-textbook in student side.");
			}
			//TC row no. 412
			List<WebElement> feedback = driver.findElements(By.cssSelector("div[class='ls-comment-entry  ls-feedback-comment']"));


			if(!feedback.get(0).getText().contains(feedbackText))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The feedback provide by instructor is not displayed in discussion tab view in the e-textbook in student side.");
			}
			List<WebElement> discussion = driver.findElements(By.cssSelector("span[class='ls-dialog-tag ls-dialog-quote ls-dialog-tag-open']"));

			if(!discussion.get(discussion.size()-1).getText().contains("Discuss:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The DW is not displayed in discussion tab view in the e-textbook in student side.");
			}
			//TC row no. 413
			driver.findElement(By.cssSelector("a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']")).click();//click on TOC icon
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 414
			String text = driver.findElement(By.className("ls-stream-post__action")).getText();
			if(!text.contains("posted a perspective"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In student side DW assignment entry is not available in the Course Stream.");
			}
			driver.findElement(By.cssSelector("span[class='ls-lesson-title ellipsis']")).click();
			Thread.sleep(2000);
			//TC row no. 415

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
			String tab1 = driver.findElement(By.xpath("(//span[@class='tab_title'])[4]")).getText();
			if(!tab1.contains("Discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On opening DW assignment from Course Stream the student doesnt get redirected to discussion tab view in the e-textbook.");
			}
			//TC row no. 416
			new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
			new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
			List<WebElement> feedback1 = driver.findElements(By.cssSelector("div[class='ls-comment-entry  ls-feedback-comment']"));
            System.out.println("feedbackText: "+feedbackText);

			if(!feedback1.get(0).getText().contains(feedbackText))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On opening DW assignment from Course Stream the feedback provide by instructor is not displayed in discussion tab view in the e-textbook in student side.");
			}
			new Navigator().navigateToTab("Assignments"); //navigate to Assignments Tab
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-cycle-bg']")));	//click on Open link in Assignments tab
			Thread.sleep(2000);
			//TC row no. 417
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
			new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
			String tab2 = driver.findElement(By.xpath("(//span[@class='tab_title'])[4]")).getText();
			if(!tab2.contains("Discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On opening DW assignment from Assignmnets tab present at right side the student doesnt get redirected to discussion tab view in the e-textbook.");
			}
			//TC row no. 418
			new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
			new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
			List<WebElement> feedback2 = driver.findElements(By.cssSelector("div[class='ls-comment-entry  ls-feedback-comment']"));

			if(!feedback2.get(0).getText().contains(feedbackText))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On opening DW assignment from Assignmnets tab present at right side the feedback provide by instructor is not displayed in discussion tab view in the e-textbook in student side.");
			}
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='jumpout-icon ls-jumpout-img']")));	//click on Jump out icon
			Thread.sleep(2000);
			//TC row no. 420
			String tab3 = driver.findElement(By.xpath("(//span[@class='tab_title'])[4]")).getText();
			if(!tab3.contains("Discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking jump out icon from eTextbook the DW doesnt open in new tab.");
			}
            boolean found = false;
			//TC row no. 421
			List<WebElement> feedback3 = driver.findElements(By.cssSelector("div[class='ls-comment-entry  ls-feedback-comment']"));

            for(WebElement l:feedback3)
            {

                if(l.getText().contains(feedbackText))
                {
                    found = true;
                    break;
                }
            }

			if(found == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On opening DW assignment by clicking jump out icon from eTextbook the feedback provide by instructor is not displayed in discussion tab view in the e-textbook in student side.");
			}

			List<WebElement> allThumbnail = driver.findElements(By.className("ls-content-comment-user-img"));

			//TC row no. 423
			if(allThumbnail.get(7).getAttribute("innerHTML") == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In discussion tab the Instructor thumbnail near the feedback is not displayed.");
			}
			List<WebElement> allNames = driver.findElements(By.className("ls-content-comment-user-img"));

			//TC row no. 423
			if(allNames.get(7).getText() == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In discussion tab the Instructor name near the feedback is not displayed.");
			}
            boolean labelFound = false;
			//TC row no. 424
			List<WebElement> feedbackLabel = driver.findElements(By.cssSelector("span[class='ls-instructor-feedback-icon']"));
            for(WebElement l:feedbackLabel)
            {

                if(l.getText().contains("Instructor Feedback"))
                {
                    labelFound = true;
                    break;
                }
            }
			if(labelFound == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In discussion tab \"Instructor Feedback\" label is not displayed.");
			}
			//TC row no. 425
			List<WebElement> allPerspective = driver.findElements(By.cssSelector("div[class='ls-comment-entry']"));

            for(WebElement l:allPerspective)
            {
                System.out.println("allPerspective: "+l.getText());
            }
			if(!allPerspective.get(1).getText().contains(perspective2))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In discussion tab the perspective posted by other student is not visible.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase statusOfDWAssignmentInStudentSide in class StatusOfDWAssignmentInStudentSide.",e);
		}
	}
}
