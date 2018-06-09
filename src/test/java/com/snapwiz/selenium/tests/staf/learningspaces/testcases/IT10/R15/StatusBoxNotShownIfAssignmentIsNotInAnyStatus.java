package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R15;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class StatusBoxNotShownIfAssignmentIsNotInAnyStatus extends Driver
{
	@Test(priority=1,enabled=true)
	public void statusboxnotshownifassignmentisnotinanystatus()
	{
		try
		{
			new Assignment().create(2301);//create assignment1
			new Assignment().create(23011);//create assignment2
			new LoginUsingLTI().ltiLogin("23012");//create student
			new LoginUsingLTI().ltiLogin("2301");//login as instructor
			new Assignment().assignToStudent(2301);//Assign 1st assignments to student
			new LoginUsingLTI().ltiLogin("23011");//login as instructor
			new Assignment().assignToStudent(23011);//Assign 2nd assignments to student
			new LoginUsingLTI().ltiLogin("23012");//login as instructor
			new Navigator().NavigateTo("Assignments");
			int number=0;
			List<WebElement> allelements=driver.findElements(By.className("ls-assignment-data-count"));//fetch number of assignments
			for(WebElement elements:allelements)
			{
				number=Integer.parseInt(elements.getText());//fetch number of assignments
				break;
			}
			
			List<WebElement> allelements1=driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));//fetch assignments name
			for(WebElement element1:allelements1)
			{
				element1.click();
				int helppage = driver.findElements(By.className("close-help-page")).size();
			    if(helppage == 1)
			    	driver.findElement(By.className("close-help-page")).click();
				break;
			}
			new Navigator().NavigateTo("Assignments");
			int number1=0;
			List<WebElement> allelements2=driver.findElements(By.className("ls-assignment-data-count"));//fetch number of assignment after click on assignment
			for(WebElement elements2:allelements2)
			{
				number1=Integer.parseInt(elements2.getText());//again fetch number of assignments
				break;
			}
			StringBuilder stringbuilder=new StringBuilder();
			List<WebElement> allelements3=driver.findElements(By.className("ls-assignement-data-label"));
			for(WebElement element3:allelements3)
			{
				stringbuilder.append(element3.getText());
			}
			String finalstring=stringbuilder.toString();
			if(finalstring.contains("Submitted") || number<number1)
				Assert.fail("Status box shown for those assignments which is not under any status and box assignments number not decremented.");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase statusboxnotshownifassignmentisnotinanystatus in class StatusBoxNotShownIfAssignmentIsNotInAnyStatus",e);
		}
	}



}
