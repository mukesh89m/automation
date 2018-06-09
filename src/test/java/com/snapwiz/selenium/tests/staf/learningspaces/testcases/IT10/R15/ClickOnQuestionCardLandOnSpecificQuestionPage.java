package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R15;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ClickOnquestionCard;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class ClickOnQuestionCardLandOnSpecificQuestionPage extends Driver
{
	@Test(priority=1,enabled=true)
	public void clickonQuestionCardLandOnSpecificQuestionPage()
	{
		try
		{
		    new Assignment().create(2601);//Assignment create
			String assessmentname=ReadTestData.readDataByTagName("", "assessmentname", "2601");
			new Assignment().addQuestions(2601, "qtn-multiple-choice-img",assessmentname);//add question
			new Assignment().addQuestions(2601, "qtn-type-true-false-img",assessmentname);//add question
			new LoginUsingLTI().ltiLogin("26011");//student create
			new LoginUsingLTI().ltiLogin("2601");//login as instructor
			new Assignment().assignToStudent(2601);//assign assignment to student
			new LoginUsingLTI().ltiLogin("26011");//login as student
			new Navigator().NavigateTo("Assignments");//Navigate to assignment page
			driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();//click on assignments
			int helppage = driver.findElements(By.className("close-help-page")).size();
		    if(helppage == 1)
		    	 driver.findElement(By.className("close-help-page")).click();//close help page if present
		   for(int i=0;i<2;i++)
		   {
			   new Assignment().nextButtonInQuestionClick();
			   Thread.sleep(3000);
		   }
		   Thread.sleep(3000);
		   driver.findElement(By.cssSelector("label[id='al-diagtest-markForReview']")).click();//click on mark as review
		   Thread.sleep(3000);
		   new Assignment().submitButtonInQuestionClick();//click on submit button
		   Thread.sleep(3000);
		   new Navigator().NavigateTo("Assignments");
           new Assignment().clickonAssignment(assessmentname);
		   boolean summaryvalue=driver.findElement(By.className("report-chart-title")).isDisplayed();//verify summary page
		   if(summaryvalue==false)
			   Assert.fail("After submitting submit page its not gone to summary page");
		   new ClickOnquestionCard().clickonquestioncard(1);//click on question card 1
		   String questionnumber=driver.findElement(By.cssSelector("div[class='question-label']")).getText();//ftech open question number
           if(!questionnumber.contains("1"))
			   Assert.fail("clicked question number not opened");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase summarypagepopup in class SummaryPagePopUp",e);
		}
	}


}
