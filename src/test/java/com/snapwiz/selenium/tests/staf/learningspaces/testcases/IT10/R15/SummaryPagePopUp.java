package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R15;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class SummaryPagePopUp extends Driver
{
	@Test(priority=1,enabled=true)
	public void summarypagepopup()
	{
		try
		{
			new Assignment().create(2526);//Assignment create
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "2526");
			new Assignment().addQuestions(2526, "qtn-multiple-choice-img",assessmentname);//add question
			new Assignment().addQuestions(2526, "qtn-type-true-false-img",assessmentname);//add question
			new Assignment().addQuestions(2526, "qtn-multiple-choice-img",assessmentname);//add question
			new LoginUsingLTI().ltiLogin("25261");//student create
			new LoginUsingLTI().ltiLogin("2526");//login as instructor
			new Assignment().assignToStudent(2526);//assign assignment to student
			new LoginUsingLTI().ltiLogin("25261");//login as student
			new Navigator().NavigateTo("Assignments");//Navigate to assignment page
            new Assignment().clickonAssignment(assessmentname);
            new AttemptQuestion().trueFalse(false, "Correct", 2526);//attempt option A
            new Assignment().nextButtonInQuestionClick();//click on Next
            new Assignment().nextButtonInQuestionClick();//click on Next
			Thread.sleep(2000);
		    driver.findElement(By.id("al-diagtest-markForReview")).click();//click on mark as review
            new Assignment().nextButtonInQuestionClick();//click on Next
		    new AssignmentsDetails().assignmentsummarypopup();
		    driver.findElement(By.cssSelector("tr[qindex='2']")).click();//click on question number two from pop up
		    Thread.sleep(3000);
		   String questionnumber= driver.findElement(By.className("current-question-index")).getText();//fetch open question number
		   if(!questionnumber.contains("(2 of"))
			   Assert.fail("After click on question from summary popup window its does not go to the clicked question");
		   new AssignmentsDetails().assignmentsummarypopup();
		   boolean imagevalue=driver.findElement(By.cssSelector("i[class='s s--check-green']")).isDisplayed();//verify answer attempt image
		   if(imagevalue==false)
			   Assert.fail("Attemped checked image not  shown");
		   boolean imagevalue1=driver.findElement(By.cssSelector("i[class='s s--marked']")).isDisplayed();//verify mark as review image
		   if(imagevalue1==false)
			   Assert.fail("Mark as review image not shown");
		   Thread.sleep(3000);
		   driver.findElement(By.xpath("/html/body")).click();
		   int popupvalue= driver.findElements(By.cssSelector("tr[qindex='2']")).size();
		   if(popupvalue>=1)
			   Assert.fail("Pop up did not close after clicking out side of pop up.");
		   
		   
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase summarypagepopup in class SummaryPagePopUp",e);
		}
	}


}
