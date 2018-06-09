package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R15;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;

import java.util.List;

public class StudentAbleToNavigateNextPreviousButtonmanytimes extends Driver
{
	@Test(priority=1,enabled=true)
	public void studentabletonavigatenextpreviousbottonmanytimes()
	{
		try
		{
			new Assignment().create(2542);//Assignment create
			String assessmentname=ReadTestData.readDataByTagName("", "assessmentname", "2542");
			new Assignment().addQuestions(2542, "qtn-multiple-choice-img",assessmentname);//add question
			new Assignment().addQuestions(2542, "qtn-type-true-false-img",assessmentname);//add question

			new LoginUsingLTI().ltiLogin("25421");//student create
			new LoginUsingLTI().ltiLogin("2542");//login as instructor
			new Assignment().assignToStudent(2542);//assign assignment to student

			new LoginUsingLTI().ltiLogin("25421");//login as student
			new Navigator().NavigateTo("Assignments");//Navigate to assignment page
			// driver.findElement(By.xpath("//*[contains(text(),'"+assessmentname+"')]")).click();//click on the assessment name
			driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();//click on the assessment name

			int helppage = driver.findElements(By.className("close-help-page")).size();

		    if(helppage == 1)
		    	 driver.findElement(By.className("close-help-page")).click();//close help page if present
		   for(int i=0;i<5;i++)
		   {
			   new Assignment().nextButtonInQuestionClick();
			   Thread.sleep(3000);
               new Assignment().previousButtonInQuestionClick();
			   Thread.sleep(3000);

		   }
		   Thread.sleep(3000);
           new Assignment().nextButtonInQuestionClick();
		   Thread.sleep(3000);
           new Assignment().nextButtonInQuestionClick();
		   Thread.sleep(3000);
		   driver.findElement(By.cssSelector("label[id='al-diagtest-markForReview']")).click();//click on mark as review
		    Thread.sleep(3000);
		   new Assignment().submitButtonInQuestionClick();
		   Thread.sleep(3000);
		   boolean summaryvalue=driver.findElement(By.className("report-chart-title")).isDisplayed();//verify summary page
		   if(summaryvalue==false)
			   Assert.fail("After submitting submit page its not gone to summary page");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase summarypagepopup in class SummaryPagePopUp",e);
		}
	}


}
