package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R15;

import java.util.List;

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

public class SortingOrderOfAssignmentsInAssignmentsPage extends Driver
{
	@Test(priority=1,enabled=true)
	public void sortingorderofassignmentsinassignmentspage()
	{
		try
		{
		    new Assignment().create(2239);//Assignment create
			new Assignment().create(22391);//Assignment create
			new Assignment().create(22392);//Assignment create
			new Assignment().create(22393);//Assignment create
			new Assignment().create(22394);//Assignment create
			new LoginUsingLTI().ltiLogin("22395");//student create
			new LoginUsingLTI().ltiLogin("2239");//login as instructor
			new Assignment().assignToStudent(2239);//assignments assign to student
			new LoginUsingLTI().ltiLogin("22391");//login as instructor
			new Assignment().assignToStudent(22391);//assignments assign to student
			new LoginUsingLTI().ltiLogin("22392");//login as instructor
			new Assignment().assignToStudent(22392);//assignments assign to student
			new LoginUsingLTI().ltiLogin("22393");//login as instructor
			new Assignment().assignToStudent(22393);//assignments assign to student
			new LoginUsingLTI().ltiLogin("22394");//login as instructor
			new Assignment().assignToStudent(22394);//assignments assign to student
			new LoginUsingLTI().ltiLogin("22395");//login as student
			new Navigator().NavigateTo("Assignments");
			String assignmentsname=ReadTestData.readDataByTagName("", "assessmentname", "2239");
			new Assignment().clickonAssignment(assignmentsname);
			Thread.sleep(3000);
			new Assignment().submitButtonInQuestionClick();
			new Navigator().NavigateTo("Assignments");
			String assignmentsname1=ReadTestData.readDataByTagName("", "assessmentname", "22391");
			new Assignment().clickonAssignment(assignmentsname1);
			Thread.sleep(3000);
			new Assignment().submitButtonInQuestionClick();
			new LoginUsingLTI().ltiLogin("22391");//instructor login
			new Assignment().provideGRadeToStudent(22391);//provide grade to student
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-release']")).click();//click on release all grade

            new LoginUsingLTI().ltiLogin("22395");
			new Navigator().NavigateTo("Assignments");
			String assignmentsname2=ReadTestData.readDataByTagName("", "assessmentname", "22392");
			new Assignment().clickonAssignment(assignmentsname2); //click on assignments name  
			new Navigator().NavigateTo("Assignments");
			String[] str=new String[5];
			int i=0;
			List<WebElement> allelements=driver.findElements(By.xpath("//*[starts-with(@class, 'ls-assignment-status-')]"));//list of all assignments name
			for(WebElement elements:allelements)
			{				
				str[i]=elements.getText();
				i++;
			}

			String[] orderassignment={"Not Started","Not Started","In Progress","Submitted","Score (0.6/1)"};
			if(!str[0].equals(orderassignment[0]))
				Assert.fail("1st assignments order not \"Not Stared\"");
			if(!str[1].equals(orderassignment[1]))
				Assert.fail("2nd assignments order not \"Not Stared\"");
			if(!str[2].equals(orderassignment[2]))
				Assert.fail("3rd assignments order not \"In Progress\"");
			if(!str[3].equals(orderassignment[3]))
				Assert.fail("1st assignments order not \"Submitted\"");
			if(!str[4].equals(orderassignment[4]))
				Assert.fail("1st assignments order not \"Score (0.6/1)\"");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase sortingorderofassignmentsinassignmentspage in class SortingOrderOfAssignmentsInAssignmentsPage",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void sortingorderofassignmentsasduedate()
	{
		try
		{
			new Assignment().create(2253);//Assignment create
			new Assignment().create(22531);//Assignment create
			new Assignment().create(22532);//Assignment create
			new Assignment().create(22533);//Assignment create
			new Assignment().create(22534);//Assignment create
			new Assignment().create(22535);//Assignment create
			new Assignment().create(22536);//Assignment create
			new Assignment().create(22538);//Assignment create
			new LoginUsingLTI().ltiLogin("22537");//student create
			new LoginUsingLTI().ltiLogin("2253");//login as instructor
			new Assignment().assignToStudent(2253);//assignments assign to student
			new LoginUsingLTI().ltiLogin("22531");//login as instructor
			new Assignment().assignToStudent(22531);//assignments assign to student
			new LoginUsingLTI().ltiLogin("22532");//login as instructor
			new Assignment().assignToStudent(22532);//assignments assign to student
			new LoginUsingLTI().ltiLogin("22533");//login as instructor
			new Assignment().assignToStudent(22533);//assignments assign to student
			new LoginUsingLTI().ltiLogin("22534");//login as instructor
			new Assignment().assignToStudent(22534);//assignments assign to student
			new LoginUsingLTI().ltiLogin("22535");//login as instructor
			new Assignment().assignToStudent(22535);//assignments assign to student
			new LoginUsingLTI().ltiLogin("22536");//login as instructor
			new Assignment().assignToStudent(22536);//assignments assign to student
			new LoginUsingLTI().ltiLogin("22538");//login as instructor
			new Assignment().assignToStudent(22538);//assignments assign to student
	 		
			String assignmentsname1=ReadTestData.readDataByTagName("", "assessmentname", "2253");
			String assignmentsname2=ReadTestData.readDataByTagName("", "assessmentname", "22531");
			String assignmentsname3=ReadTestData.readDataByTagName("", "assessmentname", "22532");
			String assignmentsname4=ReadTestData.readDataByTagName("", "assessmentname", "22533");
			String assignmentsname5=ReadTestData.readDataByTagName("", "assessmentname", "22534");
			String assignmentsname6=ReadTestData.readDataByTagName("", "assessmentname", "22535");
			String assignmentsname7=ReadTestData.readDataByTagName("", "assessmentname", "22536");
			String assignmentsname8=ReadTestData.readDataByTagName("", "assessmentname", "22538");

			new LoginUsingLTI().ltiLogin("22537");//login as student
			new Navigator().NavigateTo("Assignments");
			new Assignment().clickonAssignment(assignmentsname3);
			
			new Navigator().NavigateTo("Assignments");
			new Assignment().clickonAssignment(assignmentsname4);
			
			new Navigator().NavigateTo("Assignments");
			new Assignment().clickonAssignment(assignmentsname5);
			Thread.sleep(3000);
			new Assignment().submitButtonInQuestionClick();
			Thread.sleep(3000);
			new Navigator().NavigateTo("Assignments");
			
			
			new Assignment().clickonAssignment(assignmentsname6);
			Thread.sleep(3000);
			new Assignment().submitButtonInQuestionClick();
			Thread.sleep(3000);
			new Navigator().NavigateTo("Assignments");
			
			new Assignment().clickonAssignment(assignmentsname7);
			Thread.sleep(3000);
			new Assignment().submitButtonInQuestionClick();
			new LoginUsingLTI().ltiLogin("22536");//instructor login
			new Assignment().provideGRadeToStudent(22536);//provide grade to student
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-release']")).click();//click on release all grade
			
			new LoginUsingLTI().ltiLogin("22537");
			new Navigator().NavigateTo("Assignments");
			System.out.println(assignmentsname8);
			new Assignment().clickonAssignment(assignmentsname8);
			Thread.sleep(3000);
			new Assignment().submitButtonInQuestionClick();
			new LoginUsingLTI().ltiLogin("22538");//instructor login
			new Assignment().provideGRadeToStudent(22538);//provide grade to student
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-release']")).click();//click on release all grade
			
			new LoginUsingLTI().ltiLogin("22537");
			new Navigator().NavigateTo("Assignments");
			
			String[] assignments=new String[8];
			int j=0;
			List<WebElement> allassignmentsname=driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));//fetch all assignments name
			for(WebElement element:allassignmentsname)
			{
				assignments[j]=element.getText();
				
				j++;
			}
			String [] orderofassignments={assignmentsname1,assignmentsname2,assignmentsname3,assignmentsname4,assignmentsname5,assignmentsname6,assignmentsname7,assignmentsname8};
	
			if(!(orderofassignments[0].trim().contains("22531 Assessment")))
				Assert.fail("Assignment 1 not started not arrange in order");
			if(!(orderofassignments[1].contains("22532 Assessment")))
				Assert.fail("Assignment 2 not started not arrange in order");
			if(!(orderofassignments[2].contains("22533 Assessment")))
				Assert.fail("Assignment 3 -in progress- not arrange in order");
			if(!(orderofassignments[3].contains("22534 Assessment")))
				Assert.fail("Assignment 4 -in progress- not arrange in order");
			if(!(orderofassignments[4].contains("22535 Assessment")))
				Assert.fail("Assignment 5 -submitted- not arrange in order");
			if(!(orderofassignments[5].contains("22536 Assessment")))
				Assert.fail("Assignment 6 -submitted- not arrange in order");
			if(!(orderofassignments[6].contains("22537 Assessment")))
				Assert.fail("Assignment 7 -graded- not arrange in order");
			if(!(orderofassignments[7].contains("22538 Assessment")))
				Assert.fail("Assignment 8 -graded not arrange in order");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase sortingorderofassignmentsasduedate in class SortingOrderOfAssignmentsInAssignmentsPage",e);
		}
	
	}
	


}
