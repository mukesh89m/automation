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

public class AssignmentStatusBox extends Driver
{
	@Test(priority=1,enabled=true)
	public void assignmentstatusbox()
	{
		try
		{
			new Assignment().create(2284);//1stassignment create
			new Assignment().create(22841);//2ndassignment create
			new Assignment().create(22842);//3rdassignment create
			new LoginUsingLTI().ltiLogin("22843");//student create
			new LoginUsingLTI().ltiLogin("2284");//login as instructor
			new Assignment().assignToStudent(2284);//assign 1stassignment to student
			new LoginUsingLTI().ltiLogin("22841");//login as instructor
			new Assignment().assignToStudent(22841);//assign 2ndassignment to student
			new LoginUsingLTI().ltiLogin("22842");//login as instructor
			new Assignment().assignToStudent(22842);//assign 3rdassignment to student
			new LoginUsingLTI().ltiLogin("22843");//login as student
			new Navigator().NavigateTo("Assignments");
			String assignmentname1=ReadTestData.readDataByTagName("", "assessmentname", "2284");
			new Assignment().clickonAssignment(assignmentname1);//click on assignments 1
            new Assignment().submitAssignmentAsStudent(22841);//submit assignment 2
			new Navigator().NavigateTo("Assignments");
			driver.findElement(By.xpath("//a[@title='All Assignments']")).click();
			driver.findElement(By.xpath("//a[@title='Question Practice']")).click();
			Thread.sleep(3000);
			StringBuilder stringbuilder=new StringBuilder();
			List<WebElement> boxelements=driver.findElements(By.className("ls-assignement-data-label"));//fetch list of data from status box
			for(WebElement belement:boxelements)
			{
				stringbuilder.append(belement.getText());
			}
			String finalString = stringbuilder.toString();
			boolean boxclickable=driver.findElement(By.id("ls-not-started-assignment")).isSelected();
			if(!finalString.contains("Not Started"))
				Assert.fail("not started status boxes not presents");
			if(!finalString.contains("In Progress"))
				Assert.fail("in progress status boxes not presents");
			if(!finalString.contains("Submitted"))
				Assert.fail("submitted status boxes not presents");
			if(boxclickable==true)
				Assert.fail(" status boxes clickable");
			int totalnumber=0;
			List<WebElement> datanumberinbox=driver.findElements(By.className("ls-assignment-data-count"));//fetch total number of assignment in each status boxes
			for(WebElement datanumber:datanumberinbox)
			{
				int number=Integer.parseInt(datanumber.getText());
				System.out.println("number"+number);
				totalnumber=totalnumber+number;
				System.out.println("total"+totalnumber);
			}
			if(totalnumber!=3)
				Assert.fail("Sum of all the boxes is not equal to total number of assignments.");
			
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase assignmentstatusbox in class  AssignmentStatusBox",e);
		}
	}

}
