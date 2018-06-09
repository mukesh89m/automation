package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R15;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class AssignmentsTimer extends Driver
{
	@Test(priority=1,enabled=true)
	public void assignmenttimer()
	{
		try
		{
			new Assignment().create(2499);//Assignment create
			String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", "2499");
			new Assignment().addQuestions(2499, "qtn-multiple-choice-img",assignmentname);
			new LoginUsingLTI().ltiLogin("24991");//student create
			new LoginUsingLTI().ltiLogin("2499");//login as instructor
			new Assignment().assignToStudent(2499);//assign assignment to student
			new LoginUsingLTI().ltiLogin("24991");//login as student
			new Navigator().NavigateTo("Assignments");//Navigate to assignment page
			driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();//click on assignments
			int helppage = driver.findElements(By.className("close-help-page")).size();
		    if(helppage == 1)
		    	 driver.findElement(By.className("close-help-page")).click();//close help page if present
			new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("assessmentTimer")));
		    new AssignmentsDetails().AssignmentTimervalidate();
            new AttemptQuestion().trueFalse(false, "correct", 2499);//select answer
            new Assignment().nextButtonInQuestionClick();
		    Thread.sleep(2000);
			new AssignmentsDetails().AssignmentTimervalidate(); 
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase studentableToviewtightsidetab in class StudentAbleToViewRightSideTab.",e);
			
		}
	}
	@Test(priority=2,enabled=true)
	public void EachQuestionstartFromZero()
	{
		try
		{
			new Assignment().create(2517);//create question
			String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", "2517");//fetch assessment name from testdata
			new Assignment().addQuestions(2517, "qtn-multiple-choice-img",assignmentname);//add question
			new LoginUsingLTI().ltiLogin("25171");//create student
			new LoginUsingLTI().ltiLogin("2517");//login as instructor
			new Assignment().assignToStudent(2517);//Assign assignment to student
			new LoginUsingLTI().ltiLogin("25171");//loginas student
			new Navigator().NavigateTo("Assignments");//navigate to assignments  page
			driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();//click on assignments
			int helppage = driver.findElements(By.className("close-help-page")).size();
		    if(helppage == 1)
		    	 driver.findElement(By.className("close-help-page")).click();//close help page if present
		    String timetaken=driver.findElement(By.className("timevalue")).getAttribute("timetaken");//time taken at 1st time
			int timetaken1=Integer.parseInt(timetaken);//convert into integer of above time
			new Assignment().nextButtonInQuestionClick();
			Thread.sleep(3000);
			String timetaken2=driver.findElement(By.className("timevalue")).getAttribute("timetaken");//time taken at 2nd time
			int timetaken3=Integer.parseInt(timetaken2);//convert into integer of above time
			if(timetaken1<1)
				Assert.fail("1st Question time not start from Zero");
			if(timetaken3<1)
				Assert.fail("2nd Question time not start from Zero");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase EachQuestionstartFromZero in class StudentAbleToViewRightSideTab.",e);
		}
	}

}
