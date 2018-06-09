package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R27;

import java.util.ArrayList;
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

public class TopTenPercent extends Driver{
	
	@Test(priority = 1, enabled = false)
	public void topTenPercentforClassParticipation()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("158");//login as student
			//create 14students
			for(int i = 1; i<15; i++)
			{
				String s = String.valueOf(i);
				new LoginUsingLTI().ltiLogin("158_"+s);//login as student
			}
			new LoginUsingLTI().ltiLogin("158");//login as student
			new Navigator().NavigateTo("Course Stream");
			String randomtext = new RandomString().randomstring(5);
			for(int i = 0; i< 5; i ++)
				new PostMessage().postmessage(randomtext);//post 5 messages
			new LoginUsingLTI().ltiLogin("158_1");//login as student
			new Navigator().NavigateTo("Course Stream");
			for(int i = 0; i< 3; i ++)
				new PostMessage().postmessage(randomtext);//post 3 messages
			new LoginUsingLTI().ltiLogin("158_2");//login as student
			new Navigator().NavigateTo("Course Stream");
			for(int i = 0; i< 7; i ++)
				new PostMessage().postmessage(randomtext);//post 7 messages
			
			new LoginUsingLTI().ltiLogin("158_3");//login as student
			new Navigator().NavigateTo("Course Stream");
			new PostMessage().postmessage(randomtext);//post 1 messages
			//login to CMS and run job
			new RunScheduledJobs().runScheduledJobsForDashboard();
			new LoginUsingLTI().ltiLogin("159");//login as instructor
			String topTenPercent = driver.findElement(By.className("percent")).getText();
			/*5 messages has been posted by student158 and 7 messages posted by student158_2
			so average will be top two students posts (5+7)/2  =6, it is divided by 2 because 10% of 15 student is 1.5 so rounding off gives 2
			*/
			if(!topTenPercent.substring(21).equals("6"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In average Class participation tile 'Top 10% of the class:' is not giving accurate result.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase topTenPercentforClassParticipation in class TopTenPercent.",e);
		}
		 
	}
	
	@Test(priority = 2, enabled = false)
	public void topTenPercentforTimeSpent()
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "158");
			new Assignment().create(158);//create an assignment
			new Assignment().addQuestions(158, "qtn-type-true-false-img", assessmentname);
			new LoginUsingLTI().ltiLogin("158");//login as student
			//create 14students
			for(int i = 1; i<15; i++)
			{
				String s = String.valueOf(i);
				new LoginUsingLTI().ltiLogin("158_"+s);//login as student
			}
			new LoginUsingLTI().ltiLogin("159");//login as instructor
			new Assignment().assignToStudent(158);
			new LoginUsingLTI().ltiLogin("158");//login as student
			new Assignment().submitAssignmentAsStudent(158);
			new Navigator().NavigateTo("Dashboard");
			String timeTaken = driver.findElement(By.cssSelector("div[class='number number2']")).getText();
			List<Integer> integerarray = new ArrayList<Integer>();
			integerarray.add(Integer.parseInt(timeTaken.substring(0, 2)));
			new LoginUsingLTI().ltiLogin("158_1");//login as student
			new Assignment().submitAssignmentAsStudent(158);
			new Navigator().NavigateTo("Dashboard");
			String timeTaken1 = driver.findElement(By.cssSelector("div[class='number number2']")).getText();
			integerarray.add(Integer.parseInt(timeTaken1.substring(0, 2)));
			new LoginUsingLTI().ltiLogin("158_2");//login as student
			new Assignment().submitAssignmentAsStudent(158);
			new Navigator().NavigateTo("Dashboard");
			String timeTaken2 = driver.findElement(By.cssSelector("div[class='number number2']")).getText();
			integerarray.add(Integer.parseInt(timeTaken2.substring(0, 2)));
			int maxone = 0;
			int maxtwo = 0;
			for(int n: integerarray)
			{
				if(n>maxone){ //if n is greater than maxone 
				maxtwo=maxone; //then move maxone to maxtwo
				maxone=n; //new maxone is n 
				}//else if (n!= maxone && n > maxtwo){ //if n great than maxtwo but not same as maxone
				else if (n > maxtwo){ 
				maxtwo = n; // new maxtwo is n
				}
			}
			int top1 = maxone;
			int top2 = maxtwo;
			//login to CMS and run job
			new RunScheduledJobs().runScheduledJobsForDashboard();
			new LoginUsingLTI().ltiLogin("159");//login as instructor
			List<WebElement> topTenPercent = driver.findElements(By.cssSelector("div[class='percent']"));
			double calculatedTopTenPercent = Math.floor((top1+top2)/(double)2);//it is divided by 2 because 10% of 15 student is 1.5 so rounding off gives 2
			String top10percent = topTenPercent.get(1).getText().substring(22,24);
			int percent = Integer.parseInt(top10percent);
			if(calculatedTopTenPercent != percent)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In average Time Spent tile 'Top 10% of the class:' is not giving accurate result.");
			}
			//calculate the top 10% for avg question attempted
			integerarray.clear();
			new LoginUsingLTI().ltiLogin("158");//login as student
			List<WebElement> allQuestionAttempted = driver.findElements(By.cssSelector("div[class='number']"));
			integerarray.add(Integer.parseInt(allQuestionAttempted.get(2).getText()));
			new LoginUsingLTI().ltiLogin("158_1");//login as student
			List<WebElement> allQuestionAttempted1 = driver.findElements(By.cssSelector("div[class='number']"));
			integerarray.add(Integer.parseInt(allQuestionAttempted1.get(2).getText()));
			new LoginUsingLTI().ltiLogin("158_2");//login as student
			List<WebElement> allQuestionAttempted2 = driver.findElements(By.cssSelector("div[class='number']"));
			integerarray.add(Integer.parseInt(allQuestionAttempted2.get(2).getText()));
			int maxQuestionAttemptedOne = 0;
			int maxQuestionAttemptedTwo = 0;
			for(int n: integerarray)
			{
				if(n>maxQuestionAttemptedOne){ 
					maxQuestionAttemptedTwo=maxQuestionAttemptedOne; 
				maxQuestionAttemptedOne=n; 
				}
				else if (n > maxQuestionAttemptedTwo){ 
					maxQuestionAttemptedTwo = n; // new maxtwo is n
				}
			}
			int t1 = maxQuestionAttemptedOne;
			int t2 = maxQuestionAttemptedTwo;
			new LoginUsingLTI().ltiLogin("159");//login as instructor
			List<WebElement> topTenPercentForQAttempted = driver.findElements(By.cssSelector("div[class='percent']"));
			double calculatedTopTenPercentForQAttempted = Math.floor((t1+t2)/(double)2);//it is divided by 2 because 10% of 15 student is 1.5 so rounding off gives 2
			String top10percentForQAttempted = topTenPercentForQAttempted.get(3).getText().substring(21);
			int percentForQAttempted = Integer.parseInt(top10percentForQAttempted);
			if(calculatedTopTenPercentForQAttempted != percentForQAttempted)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In average Question attempted tile 'Top 10% of the class:' is not giving accurate result.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase topTenPercentforTimeSpent in class TopTenPercent.",e);
		}
	}
	
	@Test(priority = 3, enabled = false)
	public void topTenPercentForPracticePeromance()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("159");//login as instructor
			new LoginUsingLTI().ltiLogin("158");//login as student
			//create 14students
			for(int i = 1; i<15; i++)
			{
				String s = String.valueOf(i);
				new LoginUsingLTI().ltiLogin("158_"+s);//login as student
			}
			new LoginUsingLTI().ltiLogin("158");//login as student
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
				Assert.fail("In Practice performance tile is not giving accurate result in student site.");
			}
			new LoginUsingLTI().ltiLogin("158_1");//login as student
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
				Assert.fail("In Practice performance tile is not giving accurate result in student site.");
			}
			
			new LoginUsingLTI().ltiLogin("158_2");//login as student
			new Navigator().NavigateTo("Learning Content");//navigate to Learning Content
			new DiagnosticTest().startTest(4);
			String str2 = driver.findElement(By.className("al-diag-chapter-details")).getText();//find the total number of questions
			int Qno2 = Integer.parseInt(str2.substring(6, 8));
			new DiagnosticTest().attemptAllCorrect(0, false, false);
			new Navigator().NavigateTo("Dashboard");
			List<WebElement> practicePerf2 = driver.findElements(By.cssSelector("div[class='number']"));
			String practicePerfSt3 = practicePerf2.get(1).getText().replace("%", "");
			int practicePerfStudent3 = Integer.parseInt(practicePerfSt3);	
			float calculatedPracticePerf3 = (Qno2/(float)Qno2)*100;//all question has been attempted correctly
			if(practicePerfStudent3 != calculatedPracticePerf3)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Practice performance tile is not giving accurate result in student site.");
			}
			//login to CMS and run job
			new RunScheduledJobs().runScheduledJobsForDashboard();
			new LoginUsingLTI().ltiLogin("159");//login as instructor
			List<WebElement> topTenPercentPracticePerf = driver.findElements(By.cssSelector("div[class='percent']"));
			double calculatedTopTenPercentPracticePerf = Math.floor((calculatedPracticePerf3+calculatedPracticePerf2)/(double)2);//it is divided by 2 because 10% of 15 student is 1.5 so rounding off gives 2
			String top10percentForPracticePerf = topTenPercentPracticePerf.get(2).getText().substring(21).replace("%", "");
			int percentForPracticePerf = Integer.parseInt(top10percentForPracticePerf);
			if(calculatedTopTenPercentPracticePerf != percentForPracticePerf)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In average Practice performance tile 'Top 10% of the class:' is not giving accurate result.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase topTenPercentForPracticePeromance in class TopTenPercent.",e);
		}
	}
	@Test(priority = 4, enabled = true)
	public void defaultValueInTile()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("168");//login as instructor
			List<WebElement> allTopTenPercent = driver.findElements(By.cssSelector("div[class='percent']"));
			if(!allTopTenPercent.get(0).getText().contains("Total class contributions:0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The 'Top 10% of the class:' label is shown without Zero reading for Avg Class Participation tile.");
			}
			if(!allTopTenPercent.get(1).getText().contains("Top 10% of the class: 0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The 'Top 10% of the class:' label is shown without Zero reading for Avg Time Spent tile.");
			}
			if(!allTopTenPercent.get(2).getText().contains("Top 10% of the class:0%"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The 'Top 10% of the class:' label is shown without Zero reading for Avg Class Participation tile.");
			}
			if(!allTopTenPercent.get(3).getText().contains("Top 10% of the class:0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The 'Top 10% of the class:' label is shown without Zero reading for Avg Question Attempted tile.");
			}
			
			List<WebElement> allNumber = driver.findElements(By.cssSelector("div[class='number']"));
			if(!allNumber.get(0).getText().contains("NA"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The avg class participation is not showing zero.");
			}
			if(!allNumber.get(1).getText().contains("0%"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The avg practice performance is not showing zero percent.");
			}
			if(!allNumber.get(2).getText().contains("0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The avg question attempted is not showing zero.");
			}
			String timeSpent = driver.findElement(By.cssSelector("div[class='number number2']")).getText();
			if(!timeSpent.contains("0"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The avg time spent is not showing zero.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase topTenPercentForPracticePeromance in class TopTenPercent.",e);
		}
	}

}
