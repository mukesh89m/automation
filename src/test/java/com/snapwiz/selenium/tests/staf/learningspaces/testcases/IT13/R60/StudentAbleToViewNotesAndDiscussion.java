package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R60;

import java.util.List;

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
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class StudentAbleToViewNotesAndDiscussion extends Driver{

	@Test(priority = 1, enabled = true)
	public void studentAbleToViewNotes()
	{
		try
		{
            String note = new RandomString().randomstring(10);
		    new LoginUsingLTI().ltiLogin("1");//login as student
		    new Navigator().NavigateTo("Learning Content");//navigate to Assignments
            new SelectCourse().selectInvisibleAssignment("1.1 Concept Check");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-static-practice-test-submit-button")));//click submit
			Thread.sleep(2000);
		    new Discussion().postNote(note); //post note in 1st question
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-static-practice-test-next-button")));//click next
			Thread.sleep(2000);	  
		    new AttemptTest().StaticTest();
		    new Navigator().NavigateTo("Learning Content");//navigate to Learning Content
            new SelectCourse().selectInvisibleAssignment("1.1 Concept Check");
		    //TC row no. 2 & 16
		    String report = new TextFetch().textfetchbyclass("performance-summary-titleText");
		    if(!report.contains("Performance Summary"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After adding note for a question and submitting the static test the student doesnt go to performance summary page..");
		    }

            new QuestionCard().clickOnCard("5",2);
            Thread.sleep(2000);
		    new Navigator().navigateToTab("Favorite"); //navigate to stream tab
		    //TC row no. 4
		    String noteText = new TextFetch().textfetchbyclass("star-content");
		    if(!noteText.contains(note))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking on the question from question card the added note is not displayed in star tab.");
		    }
		    new Navigator().NavigateTo("Proficiency Report");//navigate to Proficiency Report
            new QuestionCard().clickOnCard("5",2);
            Thread.sleep(2000);
		    new Navigator().navigateToTab("Fav"); //navigate to stream tab
		    //TC row no. 18
		    String noteText1 = new TextFetch().textfetchbyclass("star-content");
		    if(!noteText1.contains(note))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking on the question from question card from Profociency report the added note is not displayed in stream tab.");
		    }
		}
		catch(Exception e )
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase studentAbleToViewNotes in class StudentAbleToViewNotesAndDiscussion.",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void studentAbleToViewDiscussion()
	{
		try {
            String discussionText = new RandomString().randomstring(6);
            new LoginUsingLTI().ltiLogin("5");//login as student
            new Navigator().NavigateTo("Learning Content");//navigate to Assignments
            new SelectCourse().selectInvisibleAssignment("1.1 Concept Check");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-static-practice-test-submit-button")));//click submit
            Thread.sleep(2000);

		    new Navigator().navigateToTab("Discussion"); //navigate to stream tab
		    new Discussion().postDiscussion(discussionText); //post discussion in 1st question	
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-static-practice-test-next-button")));//click next
			Thread.sleep(2000);	
		    new AttemptTest().StaticTest();
		    //TC row no. 5 & 19 & 22
		    String report = new TextFetch().textfetchbyclass("performance-summary-titleText");
		    if(!report.contains("Performance Summary"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After adding discussion for a question and submitting the static test the student doesnt go to performance summary page.");
		    }
            new QuestionCard().clickOnCard("5",2);
		    new Navigator().navigateToTab("Discussion"); //navigate to discussion tab
		    //TC row no. 7
		    String discussion = new TextFetch().textfetchbyclass("ls-right-user-post-body");
            if(!discussion.contains(discussionText))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking on the question from question card the added discussion is not displayed in stream tab.");
		    }
		    new Navigator().NavigateTo("Proficiency Report");//navigate to Proficiency Report
            new QuestionCard().clickOnCard("5",2);
            Thread.sleep(2000);
		    new Navigator().navigateToTab("Discussion"); //navigate to discussion tab
		    //TC row no. 21
		    String discussion1 = new TextFetch().textfetchbyclass("ls-right-user-post-body");
		    if(!discussion1.contains(discussionText))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking on the question from question card from Profociency report the added discussion is not displayed in stream tab.");
		    }
		    new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
		    //TC row no 23
		    boolean isPresent = new CourseStream().isPresentCourseStream("Discussion");
		    if(isPresent == false)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Discussion entry is not present in Course Stream when the discussion is added for Static Test while attempting.");
		    }
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']"))); //click on jump-out icon
             new WebDriverWait(driver,500).until(ExpectedConditions.presenceOfElementLocated(By.className("al-diag-test-title")));
		    Thread.sleep(2000);
		    //TC row no 24
		    List<WebElement> answer = driver.findElements(By.className("true-tick")); //checking for correct annswer tick mark in front of answer option
		    if(answer.size() == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
		    	Assert.fail("The student does not navigate to the preview page when we click on jump out icon for a discussion which has been added for question.");
		    }
		   
		   //TC row no. 25
		    String discussion2 = new TextFetch().textfetchbyclass("ls-right-user-post-body");
		    if(!discussion2.contains(discussionText))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking jump out icon for a discussion which has been added for a question, the added discussion is not displayed in stream tab.");
		    }
		    String discussionText1 = new RandomString().randomstring(10);
		   //TC row no. 26,27
		    new Discussion().postDiscussion(discussionText1); //add 1 more discussion in 1st question
		    new Navigator().NavigateTo("Proficiency Report");//navigate to Proficiency Report
            new QuestionCard().clickOnCard("5",2);
		    Thread.sleep(2000);
		    new Navigator().navigateToTab("Discussion"); //navigate to discussion tab
		    //TC row no. 28
		    String discussion3 = new TextFetch().textfetchbyclass("stream-content");
		    if(!discussion3.contains(discussionText1))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking jump out icon for a discussion we add one more discussion and we go to Proficiency Report page then the recently added discussion is not displayed.");
		    }

        }
		
		catch(Exception e )
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase studentAbleToViewDiscussion in class StudentAbleToViewNotesAndDiscussion.",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void studentAbleToViewTabs()
	{
		try
		{
            String discussionText = new RandomString().randomstring(4);
		    new LoginUsingLTI().ltiLogin("37");//login as student
		    new Navigator().NavigateTo("Learning Content");//navigate to Assignments
            new SelectCourse().selectInvisibleAssignment("1.1 Concept Check");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-static-practice-test-submit-button")));//click submit
			Thread.sleep(2000); //click submit
		    new Navigator().navigateToTab("Discussion"); //navigate to Discussion tab
		    new Discussion().postDiscussion(discussionText); //post discussion in 1st question	
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-static-practice-test-next-button")));//click next
			Thread.sleep(5000);
		    new AttemptTest().StaticTest();
		    new Navigator().NavigateTo("Proficiency Report");//navigate to Proficiency Report
            Thread.sleep(5000);
		    //TC row no. 37
		    String tabName = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
		    if(!tabName.contains("Filter"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Filter tab is absent in Proficiency Report page.");
		    }
		    //TC row no. 38
		    String tabs = new TextFetch().textfetchbyclass("tabs");
		    if(tabs.contains("Discussion") || tabs.contains("About"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Discussion tab and About tab are available in Proficiency Report page.");
		    }
		    new Click().clickByclassname("question-card-question-content"); //click on a question 
		    //TC row no. 39
		    String tabName1 = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
		    if(!tabName1.contains("About"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("About tab is absent when we click on a question from Proficiency Report page.");
		    }
		    //TC row no. 40
		    String tabs1 = new TextFetch().textfetchbyclass("tabs");
		    if(!tabs1.contains("Discussion"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
		    	Assert.fail("Discussion tab is not added when we click on a question from Proficiency Report page.");
		    }
		    //TC row no. 41
		    new Navigator().navigateToTab("Discussion"); //navigate to Discussion tab
		    new Navigator().navigateToTab("Filter"); //navigate to filter tab
		    List<WebElement> allCards = driver.findElements(By.className("question-card-question-content"));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allCards.get(allCards.size()-1));//click on question card for 1st question
		    Thread.sleep(2000);
		    //TC row no. 50
		    String tabName2 = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
		    if(!tabName2.contains("About"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("About tab is absent when we click on a question with a discussion from Proficiency Report page.");
		    }
		    //TC row no. 48 & 49
		    String tabs2 = new TextFetch().textfetchbyclass("tabs");
		    if(!tabs2.contains("Discussion") || !tabs2.contains("Filter"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Discussion tab and Filter tab are not available when we click on a question with a discussion from Proficiency Report page..");
		    }
		    //TC row no. 55
		    String qtext = new TextFetch().textfetchbyid("question-edit");
		    if(qtext == null || qtext.equals(""))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("when we click on a question with a discussion from Proficiency Report page the question is not displayed in left side.");
		    }
		    //TC row no. 56
		    String tabName3 = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
		    if(!tabName3.contains("About"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("About tab is absent when we click on a question with a discussion from Proficiency Report page.");
		    }
		    new Navigator().navigateToTab("Discussion"); //navigate to Discussion tab
		    //TC row no. 57
		    String discussion = new TextFetch().textfetchbyclass("ls-right-user-post-body");
		    if(!discussion.contains(discussionText))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("When we click on a question with a discussion from Proficiency Report page, then the discussion is not displayed.");
		    }
		    String comment = new RandomString().randomstring(4);
		    new Discussion().commentOnDiscussion(0, comment);
		    List<WebElement> allComments = driver.findElements(By.xpath("//*[starts-with(@class, 'ls-comments-text')]"));
		    //TC row no. 58
		    if(!allComments.get(allComments.size()-1).getText().contains(comment))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Student not able to comment on a discussion from Discussion tab.");
		    }
		    String editedText = new RandomString().randomstring(4);


		    new Discussion().editDiscussion(editedText);
		    //TC row no. 60
		    String editedDiscussion = driver.findElement(By.className("ls-right-user-post-body")).getText();
		    if(!editedDiscussion.equals(editedText))
				Assert.fail("The edited discussion has not been posted.");
		    new Navigator().navigateToTab("Filter"); //navigate to Filter tab
		    //TC row no. 65
		    int questionCard = driver.findElements(By.className("report-sidebar-question-card-sectn-wrapper")).size();
		    if(questionCard == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Question card is absent in Filter tab.");
		    }
		    List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
			Thread.sleep(2000);
			if(!allElements.get(0).getText().equals("Show All Chapters"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Show All Chapters filter is absent in filter tab.");
			}
			if(!allElements.get(1).getText().equals("Show All"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Show All filter is absent in filter tab.");
			}
			if(!allElements.get(2).getText().equals("Show All Objectives"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Show All Objectives filter is absent in filter tab.");
			}
			if(!allElements.get(3).getText().equals("Most Recent"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Most Recent filter is absent in filter tab.");
			}
			new Click().clickByclassname("question-card-question-content"); //click on a question from question card
			driver.findElement(By.cssSelector("img[title='Proficiency Report']")).click();
			Thread.sleep(2000);
			//TC row no. 67
			String filteTab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!filteTab.contains("Filter"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking the graph icon in question page, in Proficiency report page the Filter tab is not selected by default.");
			}
			String report = new TextFetch().textfetchbyid("reportTitle");
			if(!report.contains("Proficiency Report"))
			{
			    new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking the graph icon in question page the user doesnt navigate to Proficiency Report page.");
			}
			
			//TC row no. 68
		    int questionCard1 = driver.findElements(By.className("report-sidebar-question-card-sectn-wrapper")).size();
		    if(questionCard1 == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Question card is absent in Filter tab.");
		    }
		    List<WebElement> allElements1 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
			Thread.sleep(2000);
			if(!allElements1.get(0).getText().equals("Show All Chapters"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Show All Chapters filter is absent in filter tab, when we naviagte from clicking graph icon in question page.");
			}
			if(!allElements1.get(1).getText().equals("Show All"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Show All filter is absent in filter tab, when we naviagte from clicking graph icon in question page.");
			}
			if(!allElements1.get(2).getText().equals("Show All Objectives"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Show All Objectives filter is absent in filter tab, when we naviagte from clicking graph icon in question page.");
			}
			if(!allElements1.get(3).getText().equals("Most Recent"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Most Recent filter is absent in filter tab, when we naviagte from clicking graph icon in question page.");
			}
			//TC row no. 69
		    String tabs3 = new TextFetch().textfetchbyclass("tabs");
		    if(tabs3.contains("Discussion"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Discussion tab is not hidden we are in Graphical view.");
		    }
		    new Click().clickByclassname("question-card-question-content"); //click on a question from question card
		    //TC row no. 73
		    String points = new TextFetch().textfetchbyclass("static-assessment-point-content-box");
		    if(!points.contains("Points"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Point is not displayed in About tab for a question.");
		    }
		    //TC row no. 74
		    String correctPerc = new TextFetch().textfetchbyclass("al-diagtest-percentage-score-text");
		    if(!correctPerc.contains("Students got it correct"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Percentage of Students got it correct is not displayed in About tab for a question.");
		    }
		    //TC row no. 75 & 84
		    String questionObjective = new TextFetch().textfetchbyclass("al-diagtest-percentage-score-text");
		    if(questionObjective == null)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Question objectives associated to the question is not displayed in About tab for a question.");
		    }
		    //TC row no. 79
		    driver.findElement(By.cssSelector("div.toggle-content")).click(); //click on expand icon for a question
		    Thread.sleep(2000);
		    driver.findElement(By.cssSelector("div.toggle-content")).click(); //click on collapse icon for a question
		    Thread.sleep(2000);
		    //TC row no 83
            List<WebElement> answer = driver.findElements(By.className("true-tick")); //checking for correct annswer tick mark in front of answer option
            if(answer.size() == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Correct answer is not dispalyed in question view page.");
		    }
		    new Click().clickByclassname("question-association-skill-id"); //click on objective
		   //TC row no 85
            Thread.sleep(10000);
		    String url = driver.getCurrentUrl();
		    if(!url.contains("eTextBook"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("click on objective from about tab for a question then the user doesnt navigate to eTextBook.");
		    }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase studentAbleToViewTabs in class StudentAbleToViewNotesAndDiscussion.",e);
		}
	}
	@Test(priority = 4, enabled = true)
	public void studentAbleToViewTabsForLSCourse()
	{
		try
		{
            new Assignment().create(42);//create static assessment

		    new LoginUsingLTI().ltiLogin("42");//login as student
		    new Navigator().NavigateTo("Learning Content");//navigate to etextbook
            new SelectCourse().selectInvisibleAssignment("0.2 Concept Check");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input[type=\"button\"]")));//click on Submit button
		    new Navigator().navigateToTab("Discussion"); //navigate to Discussion tab
		    String discussionText = new RandomString().randomstring(5);
		    new Discussion().postDiscussion(discussionText); //post discussion in 1st question	
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input[type=\"button\"]")));//click next
			Thread.sleep(5000);
		    new AttemptTest().StaticTest();
            new Navigator().NavigateTo("Performance Report");//navigate to Performance Report
		    List<WebElement> allCards = driver.findElements(By.className("question-card-question-content"));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allCards.get(allCards.size()-1));//click on question card for 1st question
		    Thread.sleep(2000);
		    //TC row no. 44
		    String tabName2 = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
		    if(!tabName2.contains("About"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("About tab is absent when we click on a question with a discussion from Proficiency Report page(for LS course).");
		    }
		    //TC row no. 42 & 43
		    String tabs2 = new TextFetch().textfetchbyclass("tabs");
		    if(!tabs2.contains("Discussion") || !tabs2.contains("Filter"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Discussion tab and Filter tab are not available when we click on a question with a discussion from Proficiency Report page(for LS course).");
		    }
		   
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase studentAbleToViewTabsForLSCourse in class StudentAbleToViewNotesAndDiscussion.",e);
		}
	}
}
