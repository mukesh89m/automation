package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R27;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RunScheduledJobs;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;

public class AverageTimeSpent extends Driver {

	@Test(priority = 1, enabled = true)
	public void averageTimeSpent()
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "133");
			new Assignment().create(133);//create an assignment
			new Assignment().addQuestions(133, "qtn-type-true-false-img", assessmentname);
			new LoginUsingLTI().ltiLogin("131");//login as student
			new LoginUsingLTI().ltiLogin("132");//login as student
			new LoginUsingLTI().ltiLogin("133");//login as instructor
			new Assignment().assignToStudent(133);
			new LoginUsingLTI().ltiLogin("131");//login as student
			new Assignment().submitAssignmentAsStudent(133);
			new Navigator().NavigateTo("Dashboard");
			String timeTaken = driver.findElement(By.cssSelector("div[class='number number2']")).getText();
			int time1 = Integer.parseInt(timeTaken.substring(0, 2));
			//find the question attempted for 1st student
			List<WebElement> attemptedQuestion = driver.findElements(By.cssSelector("div[class='number']"));
			int attemptedQuestion1 = Integer.parseInt(attemptedQuestion.get(2).getText());
			new LoginUsingLTI().ltiLogin("132");//login as student
			new Assignment().submitAssignmentAsStudent(133);
			new Navigator().NavigateTo("Dashboard");
			String timeTaken1 = driver.findElement(By.cssSelector("div[class='number number2']")).getText();
			int time2 = Integer.parseInt(timeTaken1.substring(0, 2));
			//find the question attempted for 2nd student
			List<WebElement> allattemptedQuestion = driver.findElements(By.cssSelector("div[class='number']"));
			int attemptedQuestion3 = Integer.parseInt(allattemptedQuestion.get(2).getText());
			int totalTime = time1+time2;
            System.out.println("Time 1 "+time1);
            System.out.println("Time 2 "+time2);
            System.out.println("total time "+totalTime);
			int totalQuestionsAttempted = attemptedQuestion1+attemptedQuestion3;
			//login to CMS and run job
			new RunScheduledJobs().runScheduledJobsForDashboard();
			new LoginUsingLTI().ltiLogin("133");//login as instructor
			String time3 = driver.findElement(By.cssSelector("div[class='number number2']")).getText();
            System.out.println("Time 3 "+time3);
			int time4 = Integer.parseInt(time3.substring(0, 2));
            System.out.println("Time 4 "+time4);
			int avgTime = totalTime/2; //since 2 students are there in this class section
            System.out.println("Average Time "+avgTime);
			if(time4 != avgTime)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The average time spent tile in instructor dashboard is not producing the accurate result.");
			}
			
			//calculate average question attempted in instructor side
			String title = driver.findElement(By.cssSelector("div[class='box avg-practice-questions']")).getText();System.out.println("title "+title);
			if(!title.contains("AVG QUESTIONS ATTEMPTED"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The AVG QUESTIONS ATTEMPTED 4 th tile in instructor dashboard doesnt have the label 'AVG QUESTIONS ATTEMPTED'.");
			}
			List<WebElement> avgQuestionAttempted = driver.findElements(By.cssSelector("div[class='number']"));
			int avgQuestionAttempted1 = Integer.parseInt(avgQuestionAttempted.get(2).getText());
			int avgQuestionAttempted2 = totalQuestionsAttempted/2; //since 2 students are there in this class section
			if(avgQuestionAttempted1 != avgQuestionAttempted2)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The average question attempted tile in instructor dashboard is not producing the accurate result.");
			}
			driver.findElement(By.cssSelector("div.box.avg-practice-questions > div.number > p")).click();//click on average question attempted tile
            Thread.sleep(3000);
			String url = driver.getCurrentUrl();
			if(!url.contains("instProfRepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("for LS+ adaptive course On clickng average question attempted tile in instructor dashboard it doesnt navigate to proficiency report page.");
			}
			new LoginUsingLTI().ltiLogin("155");//login as instructor of LS course
			driver.findElement(By.cssSelector("div.box.avg-practice-questions > div.number > p")).click();//click on average question attempted tile
            Thread.sleep(3000);
			String url2 = driver.getCurrentUrl();
			if(!url2.contains("instPerfRepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("for LS course On clicking average question attempted tile in instructor dashboard it doesnt navigate to performance report page.");
			}
			//calculate Average Class Participation
			new LoginUsingLTI().ltiLogin("131");//login as student
			new Navigator().NavigateTo("Course Stream");
			String randomtext = new RandomString().randomstring(10);
			new PostMessage().postmessage(randomtext);//post message
			String randomtext1 = new RandomString().randomstring(10);
			new PostMessage().postmessage(randomtext1);//post message
            new PostMessage().postmessage(randomtext1);//post message
            new PostMessage().postmessage(randomtext1);//post message
            new PostMessage().postmessage(randomtext1);//post message
            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("131");//login as student
			new Navigator().NavigateTo("Dashboard");
			String classParticipation = driver.findElement(By.cssSelector("div[class='number']")).getText();
            classParticipation = classParticipation.substring(0,classParticipation.length()-1);
            float classParticipation1 = Float.parseFloat(classParticipation);
            new LoginUsingLTI().ltiLogin("132");//login as student
			new Navigator().NavigateTo("Course Stream");
			String randomtext2 = new RandomString().randomstring(10);
			new PostMessage().postmessage(randomtext2);//post message
			String randomtext3 = new RandomString().randomstring(10);
			new PostMessage().postmessage(randomtext3);//post message
            new PostMessage().postmessage(randomtext3);//post message
            new PostMessage().postmessage(randomtext3);//post message
            new PostMessage().postmessage(randomtext3);//post message
            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("132");//login as student
			new Navigator().NavigateTo("Dashboard");
			String classParticipation2 = driver.findElement(By.cssSelector("div[class='number']")).getText();
            classParticipation2 = classParticipation2.substring(0,classParticipation2.length()-1);
			float classParticipation3 = Float.parseFloat(classParticipation2);
			float totalParticipation =  classParticipation1+classParticipation3;
            System.out.println("Participation for student 1 "+classParticipation1); System.out.println("Participation for student 2 "+classParticipation3);
            System.out.println("Total Participation "+totalParticipation);
			//login to CMS and run job
			new RunScheduledJobs().runScheduledJobsForDashboard();
			new LoginUsingLTI().ltiLogin("133");//login as instructor
			String classParticipation4 = driver.findElement(By.xpath("//div[@class='number']//p[1]")).getText().trim();
            float classParticipation5 = Float.parseFloat(classParticipation4);
			float avgClassParticipation = totalParticipation/(float)2.0; //since two students are there in this class section
            System.out.println("Avg Participation "+avgClassParticipation);
            System.out.println("Avg Participation shown "+classParticipation5);
			if((classParticipation5 != avgClassParticipation || classParticipation5 == 0))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The average class participation tile in instructor dashboard is not producing the accurate result.");
			}
			driver.findElement(By.cssSelector("div.number > p")).click();//click on average class participation tile
			Thread.sleep(3000);
			String url1 = driver.getCurrentUrl();
			if(!url1.contains("coursestream"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking average class participation tile in instructor dashboard its not navigating to Course Stream.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase averageTimeSpent in class AverageTimeSpent.",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void averagePracticePerformance()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("137");//login as student
			new LoginUsingLTI().ltiLogin("138");//login as student
			new LoginUsingLTI().ltiLogin("139");//login as instructor
			new LoginUsingLTI().ltiLogin("137");//login as student
			new Navigator().NavigateTo("Learning Content");//navigate to Learning Content
			new DiagnosticTest().startTest(4);
			String str = driver.findElement(By.className("al-diag-chapter-details")).getText();//find the total number of questions
			int Qno = Integer.parseInt(str.substring(6, 8));
            for(int i =0 ; i<10; i++)
				new DiagnosticTest().attemptCorrect(0);
			for(int i =0 ; i<Qno-10; i++)
				new DiagnosticTest().attemptIncorrect(0);
			new Navigator().NavigateTo("Dashboard");
			List<WebElement> practicePerf = driver.findElements(By.cssSelector("div[class='number']"));
			String practicePerfSt1 = practicePerf.get(1).getText().replace("%", "");
			int practicePerfStudent1 = Integer.parseInt(practicePerfSt1);
			float calculatedPracticePerf1 = (10/(float)Qno)*100;
			if(practicePerfStudent1 != calculatedPracticePerf1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In student site the Practice Performance calculated is not correct.");
			}
			new LoginUsingLTI().ltiLogin("138");//login as student
			new Navigator().NavigateTo("Learning Content");//navigate to Learning Content
			new DiagnosticTest().startTest(4);
			String str1 = driver.findElement(By.className("al-diag-chapter-details")).getText();//find the total number of questions
			int Qno1 = Integer.parseInt(str1.substring(6, 8));
			for(int i =0 ; i<15; i++)
				new DiagnosticTest().attemptCorrect(0);
			for(int i =0 ; i<Qno1-15; i++)
				new DiagnosticTest().attemptIncorrect(0);
			new Navigator().NavigateTo("Dashboard");
			List<WebElement> practicePerf1 = driver.findElements(By.cssSelector("div[class='number']"));
			String practicePerfSt2 = practicePerf1.get(1).getText().replace("%", "");
			int practicePerfStudent2 = Integer.parseInt(practicePerfSt2);	
			float calculatedPracticePerf2 = (15/(float)Qno1)*100;//15 question has been attempted correctly
			if(practicePerfStudent2 != calculatedPracticePerf2)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In student site the Practice Performance calculated is not correct.");
			}
			//login to CMS and run job
			new RunScheduledJobs().runScheduledJobsForDashboard();
			new LoginUsingLTI().ltiLogin("139");//login as instructor
			List<WebElement> practicePerf2 = driver.findElements(By.cssSelector("div[class='number']"));
			String avgpracticePerf = practicePerf2.get(1).getText().replace("%", "");
			int avgpracticePerfInstructor = Integer.parseInt(avgpracticePerf);	
			double calculatgedavgPracticePerf = Math.ceil((calculatedPracticePerf1+calculatedPracticePerf2)/(double)2);
			if(calculatgedavgPracticePerf != avgpracticePerfInstructor || calculatgedavgPracticePerf !=63) //student 1 should be 50% , student 2 should be 75% and avg should be 63%
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In instructor site the average Practice Performance calculated is not correct.");
			}
			driver.findElement(By.cssSelector("div.box.avg-practice-performance > div.number > p")).click();//click on avg practice performance tile
            Thread.sleep(3000);
			String url = driver.getCurrentUrl();
			if(!url.contains("instProfRepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For a course of LS+Adaptive type clicking on avg practice performance tile the instructor doesnt navigate to instructor proficiency report page.");
			}
			
			new LoginUsingLTI().ltiLogin("146");//login as instructor of course type LS
			driver.findElement(By.cssSelector("div.box.avg-practice-performance > div.number > p")).click();//click on avg practice performance tile
            Thread.sleep(3000);
			String url1 = driver.getCurrentUrl();
			if(!url1.contains("instPerfRepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For a course of LS type clicking on avg practice performance tile the instructor doesnt navigate to performance report page.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase averagePracticePerformance in class AverageTimeSpent.",e);
		}
	}

}
