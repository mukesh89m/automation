package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT15.R1512;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class ParticipationScoreCalculation extends Driver{
	
	@Test
	public void participationScoreCalculation()
	{
		try
		{

			new DBConnect();
			//TC row no. 8-14
			new LoginUsingLTI().ltiLogin("8_3");//create instructor
			new LoginUsingLTI().ltiLogin("8");//login as student1
			new Navigator().NavigateTo("Course Stream");
			String randomtext = new RandomString().randomstring(15);
			new PostMessage().postmessage(randomtext);
			new PostMessage().postmessage(randomtext);
			new PostMessage().postmessage(randomtext);
			new Navigator().NavigateTo("Dashboard"); //Total Weighted Score of student1 is 0.6 = 1
			String score = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score: "+score);
			if(!score.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in dashboard.");
			}
			Double d = 0.0;
			d = new ParctipationScore().parctipationScore("8");
			System.out.println("-->"+d);
			if(d != 0.6)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in DB.");
			}
			
			new LoginUsingLTI().ltiLogin("8_1");//login as student2
			new Navigator().NavigateTo("Course Stream");
			new PostMessage().postlink("www.google.com");
			new Navigator().NavigateTo("Dashboard");//Total Weighted Score of student2 is 0.2 = 0
			String score1 = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score1: "+score1);
			if(!score1.contains("0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in dashboard.");
			}
			d = new ParctipationScore().parctipationScore("8_1");
			System.out.println("-->"+d);
			if(d != 0.2)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in DB.");
			}

			new LoginUsingLTI().ltiLogin("8_2");//login as student3
			new Navigator().NavigateTo("Course Stream");
			new FileUpload().fileUpload("8_2", true);
			new FileUpload().fileUpload("8_2", true);
			new FileUpload().fileUpload("8_2", true);
			new Navigator().NavigateTo("Dashboard");//Total Weighted Score of student3 is 0.6 = 1
			String score2 = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score2: "+score2);
			if(!score2.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in dashboard.");
			}
			d = new ParctipationScore().parctipationScore("8_2");
			System.out.println("-->"+d);
			if(d != 0.6)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in DB.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();	//run scheduled jobs for updation of scores
			
			new LoginUsingLTI().ltiLogin("8_3");//login as instructor
			new Navigator().NavigateTo("Engagement Report");
			//TC row no. 16
			String participationScoreColum = driver.findElement(By.className("students-report-content-body-fourth")).getText();
			System.out.println("participationScoreColum: "+participationScoreColum);
			if(!participationScoreColum.contains("Class Participation Score"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Engagement report \"Class Participation Score\" column is not displayed.");
			}
			//TC row no. 17
			List<WebElement> helpIcon = driver.findElements(By.className("performance-question-help-img"));
			System.out.println("-->"+helpIcon.get(2).getCssValue("background-image"));
			if(!helpIcon.get(2).getCssValue("background-image").contains("help-question-icon.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Engagement report \"?\" icon is not displayed besides \"Participation Score\" header text.");
			}
			
			//TC row no. 18
			List<WebElement> percentile = driver.findElements(By.className("students-report-content-body-fourth"));
			for(WebElement l: percentile)
			{
				System.out.println("==>"+l.getText());
			}
			if(!percentile.get(4).getText().contains("67"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile.get(8).getText().contains("17"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile.get(12).getText().contains("67"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			//TC row no. 19
			if(percentile.get(4).getText().contains("%") || percentile.get(8).getText().contains("%") ||percentile.get(12).getText().contains("%"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Percentile in engagement report is displayed with \"%\" sign.");
			}
			//TC row no. 20
			helpIcon.get(2).click(); //click on help icon for Class Participation score column
			Thread.sleep(2000);
			String helpText = driver.findElement(By.className("help-data-container")).getText();
			System.out.println("helpText: "+helpText);
			if(!helpText.contains("The percentile score calculation includes social activities done by the student across entire application."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Clicking on help icon for Class Participation score column the proper help text is not displayed.");
			}
			
			new LoginUsingLTI().ltiLogin("8");//login as student1
			new Navigator().NavigateTo("Course Stream");
			String commenttext = new RandomString().randomstring(15);
			new CourseStream().addCommentInCourseStream(commenttext, 0);
			new CourseStream().addCommentInCourseStream(commenttext, 0);
			new Navigator().NavigateTo("Dashboard");//Total Weighted Score of student1 is 0.6+0.2 = 0.8 = 1
			//TC row no. 24
			String score3 = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score3: "+score3);
			if(!score3.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in dashboard after student post some comment on a post.");
			}
			d = new ParctipationScore().parctipationScore("8");
			System.out.println("-->"+d);
			if(d != 0.8)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in DB.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();	//run scheduled jobs for updation of scores

			new LoginUsingLTI().ltiLogin("8_3");//login as instructor
			new Navigator().NavigateTo("Engagement Report");
			//TC row no. 25
			List<WebElement> percentile1 = driver.findElements(By.className("students-report-content-body-fourth"));
			for(WebElement l: percentile1)
			{
				System.out.println("==>"+l.getText());
			}
			if(!percentile1.get(4).getText().contains("83"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile1.get(8).getText().contains("17"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile1.get(12).getText().contains("50"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			//TC row no. 26
			if(percentile1.get(4).getText().contains("%") || percentile1.get(8).getText().contains("%") ||percentile1.get(12).getText().contains("%"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Percentile in engagement report is displayed with \"%\" sign.");
			}

			new LoginUsingLTI().ltiLogin("8");//login as student1
			new Navigator().NavigateTo("Course Stream");
			driver.findElement(By.cssSelector("span.ls-post-like-link > span")).click(); //click on like
			Thread.sleep(3000);
			new Navigator().NavigateTo("Dashboard");//Total Weighted Score of student1 is 0.8+0.02= 0.82 = 1
			//TC row no. 30
			String score4 = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score4: "+score4);
			if(!score4.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in dashboard after student like a post.");
			}
			d = new ParctipationScore().parctipationScore("8");
			System.out.println("-->"+d);
			if(d != 0.82)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in DB.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();	//run scheduled jobs for updation of scores
			
			new LoginUsingLTI().ltiLogin("8_3");//login as instructor
			new Navigator().NavigateTo("Engagement Report");
			//TC row no. 31
			List<WebElement> percentile2 = driver.findElements(By.className("students-report-content-body-fourth"));
			for(WebElement l: percentile2)
			{
				System.out.println("==>"+l.getText());
			}
			if(!percentile2.get(4).getText().contains("83"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile2.get(8).getText().contains("17"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile2.get(12).getText().contains("50"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			//TC row no. 32
			if(percentile2.get(4).getText().contains("%") || percentile2.get(8).getText().contains("%") ||percentile2.get(12).getText().contains("%"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Percentile in engagement report is displayed with \"%\" sign.");
			}
			
			new LoginUsingLTI().ltiLogin("8_1");//login as student2
			new Navigator().NavigateTo("e-Textbook");
			new TOCShow().tocHide();
			String discussionText = new RandomString().randomstring(15);
			new Discussion().postDiscussion(discussionText);
			new Discussion().postDiscussion(discussionText);
			new Discussion().postDiscussion(discussionText);
			new Navigator().NavigateTo("Dashboard");//Total Weighted Score of student2 is 0.2+0.6= 0.8 = 1
			//TC row no. 36
			String score5 = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score5: "+score5);
			if(!score5.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in dashboard after student post some discussion.");
			}
			d = new ParctipationScore().parctipationScore("8_1");
			System.out.println("-->"+d);
			if(d != 0.8)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in DB.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();	//run scheduled jobs for updation of scores
			
			new LoginUsingLTI().ltiLogin("8_3");//login as instructor
			new Navigator().NavigateTo("Engagement Report");
			//TC row no. 35
			List<WebElement> percentile3 = driver.findElements(By.className("students-report-content-body-fourth"));
			for(WebElement l: percentile3)
			{
				System.out.println("==>"+l.getText());
			}
			if(!percentile3.get(4).getText().contains("83"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile3.get(8).getText().contains("50"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile3.get(12).getText().contains("17"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			
			new LoginUsingLTI().ltiLogin("8_1");//login as student2
			new Navigator().NavigateTo("Course Stream");
			String commenttext1 = new RandomString().randomstring(15);
			new CourseStream().addCommentInCourseStream(commenttext1, 3);//at index 3 post of other student is available	
			new Navigator().NavigateTo("Dashboard");//Total Weighted Score of student2 is 0.2+0.6+0.1= 0.9 = 1
			//TC row no. 40
			String score6 = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score6: "+score6);
			if(!score6.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in dashboard after student post a comment in other student's post.");
			}
			d = new ParctipationScore().parctipationScore("8_1");
			System.out.println("-->"+d);
			if(d != 0.9)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in DB.");
			}

			new RunScheduledJobs().runScheduledJobsForDashboard();	//run scheduled jobs for updation of scores

			new LoginUsingLTI().ltiLogin("8_3");//login as instructor
			new Navigator().NavigateTo("Engagement Report");
			//TC row no. 39
			List<WebElement> percentile4 = driver.findElements(By.className("students-report-content-body-fourth"));
			for(WebElement l: percentile4)
			{
				System.out.println("==>"+l.getText());
			}
			if(!percentile4.get(4).getText().contains("50"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile4.get(8).getText().contains("83"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile4.get(12).getText().contains("17"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			
			
			new LoginUsingLTI().ltiLogin("8_1");//login as student2
			new Navigator().NavigateTo("e-Textbook");
			new TOCShow().tocHide();
			String comment = new RandomString().randomstring(15);
			new AssignmentSocialElement().addComment(comment);
			driver.findElement(By.cssSelector("div[class='toggle-widget-size toggle-widget-size-collapse']")).click();//click on collapse icon for image widget
			Thread.sleep(3000);
			new Navigator().NavigateTo("Dashboard");//Total Weighted Score of student2 is 0.2+0.6+0.1+0.2= 1.1 = 1
			//TC row no. 44
			String score7 = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score7: "+score7);
			if(!score7.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in dashboard after student post a comment in widget.");
			}
			d = new ParctipationScore().parctipationScore("8_1");
			System.out.println("-->"+d);
			if(d != 1.1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in DB.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();	//run scheduled jobs for updation of scores

			new LoginUsingLTI().ltiLogin("8_3");//login as instructor
			new Navigator().NavigateTo("Engagement Report");
			//TC row no. 43, 45
			List<WebElement> percentile5 = driver.findElements(By.className("students-report-content-body-fourth"));
			for(WebElement l: percentile5)
			{
				System.out.println("==>"+l.getText());
			}
			if(!percentile5.get(4).getText().contains("50"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile5.get(8).getText().contains("83"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile5.get(12).getText().contains("17"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			//TC row no. 46
			if(percentile5.get(4).getText().contains("%") || percentile5.get(8).getText().contains("%") ||percentile5.get(12).getText().contains("%"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Percentile in engagement report is displayed with \"%\" sign.");
			}
			
			new ResourseCreate().resourseCreate(8, 0); //upload a reource

			new LoginUsingLTI().ltiLogin("8_1");//login as student2
			new Navigator().NavigateTo("e-Textbook");
			new TOCShow().tocHide();
			new Navigator().openResourceFromResourceTab(8);
			String comment1 = new RandomString().randomstring(15);
			new CourseStream().addCommentInCourseStream(comment1, 0); //comment at index 0 for new tab
			new Navigator().NavigateTo("Dashboard");//Total Weighted Score of student2 is 0.2+0.6+0.1+0.2+0.05= 1.15 = 1
			//TC row no. 50
			String score8 = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score8: "+score8);
			if(!score8.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in dashboard after student post a comment on resource.");
			}
			d = new ParctipationScore().parctipationScore("8_1");
			System.out.println("-->"+d);
			if(d != 1.15)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in DB.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();	//run scheduled jobs for updation of scores

			new LoginUsingLTI().ltiLogin("8_3");//login as instructor
			new Navigator().NavigateTo("Engagement Report");
			//TC row no. 49, 52
			List<WebElement> percentile6 = driver.findElements(By.className("students-report-content-body-fourth"));
			for(WebElement l: percentile6)
			{
				System.out.println("==>"+l.getText());
			}
			if(!percentile6.get(4).getText().contains("50"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile6.get(8).getText().contains("83"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile6.get(12).getText().contains("17"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			//TC row no. 53
			if(percentile6.get(4).getText().contains("%") || percentile6.get(8).getText().contains("%") ||percentile6.get(12).getText().contains("%"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Percentile in engagement report is displayed with \"%\" sign.");
			}
			
			
			new Assignment().create(8);
			
			new LoginUsingLTI().ltiLogin("8_3");//login as instructor
			new Assignment().assignToStudent(8);  //Assigning assignment
			
			new LoginUsingLTI().ltiLogin("8_1");//login as student2
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			new AssignmentSocialElement().clickoncommnetcoursestream(0);//comment in Assignment
			new Navigator().NavigateTo("Dashboard");//Total Weighted Score of student2 is 0.2+0.6+0.1+0.2+0.05+0.05= 1.2 = 1
			//TC row no. 57
			String score14 = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score14: "+score14);
			if(!score14.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in dashboard after student post a comment on resource.");
			}
			d = new ParctipationScore().parctipationScore("8_1");
			System.out.println("-->"+d);
			if(d != 1.2)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in DB.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();	//run scheduled jobs for updation of scores

			new LoginUsingLTI().ltiLogin("8_3");//login as instructor
			new Navigator().NavigateTo("Engagement Report");
			//TC row no. 56
			List<WebElement> percentile7 = driver.findElements(By.className("students-report-content-body-fourth"));
			for(WebElement l: percentile7)
			{
				System.out.println("==>"+l.getText());
			}
			if(!percentile7.get(4).getText().contains("50"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile7.get(8).getText().contains("83"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile7.get(12).getText().contains("17"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			
			new LoginUsingLTI().ltiLogin("8_1");//login as student2
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("js-post-like")).click();	//Like the Assignment
			Thread.sleep(3000);
			new Navigator().NavigateTo("Dashboard");//Total Weighted Score of student2 is 0.2+0.6+0.1+0.2+0.05+0.05+0.01= 1.21 = 1
			//TC row no. 61
			String score9 = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score9: "+score9);
			if(!score9.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in dashboard after student post a comment on resource.");
			}
			d = new ParctipationScore().parctipationScore("8_1");
			System.out.println("-->"+d);
			if(d != 1.21)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in DB.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();	//run scheduled jobs for updation of scores

			new LoginUsingLTI().ltiLogin("8_3");//login as instructor
			new Navigator().NavigateTo("Engagement Report");
			//TC row no. 63
			List<WebElement> percentile8 = driver.findElements(By.className("students-report-content-body-fourth"));
			for(WebElement l: percentile8)
			{
				System.out.println("==>"+l.getText());
			}
			if(!percentile8.get(4).getText().contains("50"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile8.get(8).getText().contains("83"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile8.get(12).getText().contains("17"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			//TC row no. 64
			if(percentile8.get(4).getText().contains("%") || percentile8.get(8).getText().contains("%") ||percentile8.get(12).getText().contains("%"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Percentile in engagement report is displayed with \"%\" sign.");
			}
			
			
			new LoginUsingLTI().ltiLogin("8_2");//login as student3
			new Navigator().NavigateTo("e-Textbook");
			new DiagnosticTest().startTest(4);
			new AttemptTest().Diagonestictest();
			driver.findElement(By.className("report-sidebar-question-card-right")).click();	//click on question card
			Thread.sleep(3000);
			new Navigator().navigateToTab("Discussion"); //navigate to discussion tab
			String str = new RandomString().randomstring(15);
			new Discussion().postDiscussion(str);
			new Navigator().NavigateTo("Dashboard");//Total Weighted Score of student3 is 0.6+0.2=0.8=1
			//TC row no. 68
			String score10 = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score10: "+score10);
			if(!score10.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in dashboard after student post a discusiion after compltetion of diag test.");
			}
			d = new ParctipationScore().parctipationScore("8_2");
			System.out.println("-->"+d);
			if(d != 0.8)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in DB.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();	//run scheduled jobs for updation of scores

			new LoginUsingLTI().ltiLogin("8_3");//login as instructor
			new Navigator().NavigateTo("Engagement Report");
			//TC row no. 67
			List<WebElement> percentile9 = driver.findElements(By.className("students-report-content-body-fourth"));
			for(WebElement l: percentile9)
			{
				System.out.println("==>"+l.getText());
			}
			if(!percentile9.get(4).getText().contains("50"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile9.get(8).getText().contains("83"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile9.get(12).getText().contains("17"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			
			new LoginUsingLTI().ltiLogin("8_1");//login as student2
			new Navigator().NavigateTo("e-Textbook");
			new DiagnosticTest().startTest(4);
			new AttemptTest().Diagonestictest();
			driver.findElement(By.className("report-sidebar-question-card-right")).click();	//click on question card
			Thread.sleep(3000);
			new Navigator().navigateToTab("Discussion"); //navigate to discussion tab
			String str1 = new RandomString().randomstring(15);
			new Discussion().commentOnDiscussion(0, str1);
			new Navigator().NavigateTo("Dashboard");//Total Weighted Score of student2 is 0.2+0.6+0.1+0.2+0.05+0.05+0.01+0.1= 1.31 = 1
			//TC row no. 72
			String score15 = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score15: "+score15);
			if(!score15.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in dashboard after student post a comment on on discussion after finishing Diag test.");
			}
			d = new ParctipationScore().parctipationScore("8_1");
			System.out.println("-->"+d);
			if(d != 1.31)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in DB.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();	//run scheduled jobs for updation of scores

			new LoginUsingLTI().ltiLogin("8_3");//login as instructor
			new Navigator().NavigateTo("Engagement Report");
			//TC row no. 71, 73
			List<WebElement> percentile10 = driver.findElements(By.className("students-report-content-body-fourth"));
			for(WebElement l: percentile10)
			{
				System.out.println("==>"+l.getText());
			}
			if(!percentile10.get(4).getText().contains("50"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile10.get(8).getText().contains("83"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile10.get(12).getText().contains("17"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			//TC row no. 74
			if(percentile10.get(4).getText().contains("%") || percentile10.get(8).getText().contains("%") ||percentile10.get(12).getText().contains("%"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Percentile in engagement report is displayed with \"%\" sign.");
			}
			
			new Assignment().create(9);//create Adaptive practice test
		
			new LoginUsingLTI().ltiLogin("8_2");//login as student3
			new Navigator().NavigateTo("e-Textbook");
			new PracticeTest().startTest();//start adaptive practice
			new SelectAnswerAndSubmit().Adaptiveasnswersubmit("A");
			new Navigator().navigateToTab("Discussion"); //navigate to discussion tab
			String str2 = new RandomString().randomstring(15);
			new Discussion().postDiscussion(str2);
			new Navigator().NavigateTo("Dashboard");//Total Weighted Score of student3 is 0.6+0.2+0.2=1
			//TC row no. 78
			String score11 = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score11: "+score11);
			if(!score11.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in dashboard after student post a discussion on adaptive practice question.");
			}
			d = new ParctipationScore().parctipationScore("8_2");
			System.out.println("-->"+d);
			if(d != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in DB.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();	//run scheduled jobs for updation of scores

			new LoginUsingLTI().ltiLogin("8_3");//login as instructor
			new Navigator().NavigateTo("Engagement Report");
			//TC row no. 77
			List<WebElement> percentile11 = driver.findElements(By.className("students-report-content-body-fourth"));
			for(WebElement l: percentile11)
			{
				System.out.println("==>"+l.getText());
			}
			if(!percentile11.get(4).getText().contains("17"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile11.get(8).getText().contains("83"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile11.get(12).getText().contains("50"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			
			new LoginUsingLTI().ltiLogin("8_1");//login as student2
			new Navigator().NavigateTo("e-Textbook");
			new PracticeTest().startTest();//start adaptive practice
			new SelectAnswerAndSubmit().Adaptiveasnswersubmit("A");
			new Navigator().navigateToTab("Discussion"); //navigate to discussion tab
			String str3 = new RandomString().randomstring(15);
			new Discussion().commentOnDiscussion(0, str3);
			new Navigator().NavigateTo("Dashboard");//Total Weighted Score of student2 is 0.2+0.6+0.1+0.2+0.05+0.05+0.01+0.1+0.1= 1.41 = 1
			//TC row no. 82
			String score12 = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score12: "+score12);
			if(!score12.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in dashboard after student comments on a discussion on adaptive practice question.");
			}
			d = new ParctipationScore().parctipationScore("8_1");
			System.out.println("-->"+d);
			if(d != 1.41)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in DB.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();	//run scheduled jobs for updation of scores

			new LoginUsingLTI().ltiLogin("8_3");//login as instructor
			new Navigator().NavigateTo("Engagement Report");
			//TC row no. 81, 83
			List<WebElement> percentile14 = driver.findElements(By.className("students-report-content-body-fourth"));
			for(WebElement l: percentile14)
			{
				System.out.println("==>"+l.getText());
			}
			if(!percentile14.get(4).getText().contains("17"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile14.get(8).getText().contains("83"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile14.get(12).getText().contains("50"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			//TC row no. 84
			if(percentile14.get(4).getText().contains("%") || percentile14.get(8).getText().contains("%") ||percentile14.get(12).getText().contains("%"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Percentile in engagement report is displayed with \"%\" sign.");
			}
			
			new LoginUsingLTI().ltiLogin("8_2");//login as student3
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().staticAssessmentOpen(0, 0);
			driver.findElements(By.className("qtn-label")); //select answer
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input[type=\"button\"]")));//click on submit button
			Thread.sleep(2000);
			new Navigator().navigateToTab("Discussion"); //navigate to discussion tab
			String str5 = new RandomString().randomstring(15);
			new Discussion().postDiscussion(str5);
			new Navigator().NavigateTo("Dashboard");//Total Weighted Score of student3 is 0.6+0.2+0.2+0.2=1.2 = 1
			//TC row no. 88
			String score16 = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score16: "+score16);
			if(!score16.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in dashboard after student post a discussion on static practice question.");
			}
			d = new ParctipationScore().parctipationScore("8_2");
			System.out.println("-->"+d);
			if(d != 1.2)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in DB.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();	//run scheduled jobs for updation of scores

			new LoginUsingLTI().ltiLogin("8_3");//login as instructor
			new Navigator().NavigateTo("Engagement Report");
			//TC row no. 81, 83
			List<WebElement> percentile12 = driver.findElements(By.className("students-report-content-body-fourth"));
			for(WebElement l: percentile12)
			{
				System.out.println("==>"+l.getText());
			}
			if(!percentile12.get(4).getText().contains("17"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile12.get(8).getText().contains("83"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile12.get(12).getText().contains("50"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			
			new LoginUsingLTI().ltiLogin("8_1");//login as student2
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().staticAssessmentOpen(0, 0);
			driver.findElements(By.className("qtn-label")); //select answer
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input[type=\"button\"]")));//click on submit button
			Thread.sleep(2000);
			new Navigator().navigateToTab("Discussion"); //navigate to discussion tab
			String str4 = new RandomString().randomstring(15);
			new Discussion().commentOnDiscussion(0, str4); //comment on Discussion
			new Navigator().NavigateTo("Dashboard");//Total Weighted Score of student2 is 0.2+0.6+0.1+0.2+0.05+0.05+0.01+0.1+0.1+0.1= 1.51 = 2
			//TC row no. 92
			String score13 = new TileTextInDashboard().studentParticipationScoreLowerText();
			System.out.println("score13: "+score13);
			if(!score13.contains("2"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in dashboard after student comments on a discussion on static test question.");
			}
			d = new ParctipationScore().parctipationScore("8_1");
			System.out.println("-->"+d);
			if(d != 1.51)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Weighted score for the student is not displayed correctly in DB.");
			}
			
			new RunScheduledJobs().runScheduledJobsForDashboard();	//run scheduled jobs for updation of scores

			new LoginUsingLTI().ltiLogin("8_3");//login as instructor
			new Navigator().NavigateTo("Engagement Report");
			//TC row no. 91, 93
			List<WebElement> percentile13 = driver.findElements(By.className("students-report-content-body-fourth"));
			for(WebElement l: percentile13)
			{
				System.out.println("==>"+l.getText());
			}
			if(!percentile13.get(4).getText().contains("17"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile13.get(8).getText().contains("83"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			if(!percentile13.get(12).getText().contains("50"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Participation Score of each student doesnt display the percentile in Engagement Report.");
			}
			//TC row no. 94
			if(percentile13.get(4).getText().contains("%") || percentile13.get(8).getText().contains("%") ||percentile13.get(12).getText().contains("%"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Percentile in engagement report is displayed with \"%\" sign.");
			}


			 //prepare the script once the bug 8846 is resolved 
	/*		 new Assignment().create(10);//create Assignment
			
			new LoginUsingLTI().ltiLogin("8_3");//login as instructor
			String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", "10");
			new Navigator().NavigateTo("Assignment Policies");
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicy, "Policy Desc", "2", "", true, "", "", "", "", "", "");
			new Assignment().assignToStudent(10);
			
			
			new LoginUsingLTI().ltiLogin("8_2");//login as student3
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			List<WebElement> allAssignment = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
			Thread.sleep(2000);
			allAssignment.get(1).click();//click on assignment which will be at first index
			Thread.sleep(3000);
			new SelectAnswerAndSubmit().directAssignmentselectanswerandsubmit();*/
			
		}
		
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase participationScoreCalculation in class ParticipationScoreCalculation",e);
		}
	}

}
