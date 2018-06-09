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
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;

public class StudentAbleToNevigateAssignmentPage extends Driver
{

	@Test(priority=1,enabled=true)
	public void studentabletonevigateassignmentpage()
	{
		try
		{
			new Assignment().create(2190);//1st assignment create
			new Assignment().create(21902);//2nd assignment create
			new LoginUsingLTI().ltiLogin("21901");//creating student
			new LoginUsingLTI().ltiLogin("2190");//login as intructor
			new Assignment().assignToStudent(2190);//assignment1 assign to student21901
			new LoginUsingLTI().ltiLogin("21902");//login as intructor21902
			new Assignment().assignToStudent(21902);//assignment2 assign to student21901
			new LoginUsingLTI().ltiLogin("21901");//login as student21901
			new Navigator().NavigateTo("Assignments");
			String pagevalue=driver.findElement(By.className("sbSelector")).getAttribute("title");
			if(!pagevalue.trim().equals("All Assignments"))
				Assert.fail("Student not navigated to assignment page");
			String assignmentname1=ReadTestData.readDataByTagName("", "assessmentname", "2190");
			String assignmentname2=ReadTestData.readDataByTagName("", "assessmentname", "21902");
			boolean assignmentpresent1=false;
			boolean assignmentpresent2=false;
			List<WebElement> allElements=driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
			for(WebElement element: allElements)
			{				
				if(element.getText().contains(assignmentname1))
				{
					assignmentpresent1=true;	
					break;
				}
			}
			for(WebElement element: allElements)
			{				
				if(element.getText().contains(assignmentname2))
				{
					assignmentpresent2=true;	
					break;
				}
			}
			if(assignmentpresent1==false || assignmentpresent2==false)
				Assert.fail("All assignment  not present");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase studentabletonevigateassignmentpage in class StudentAbleToNevigateAssignmentPage",e);
		}
	}
	@Test(priority=2,enabled=true,dependsOnMethods="studentabletonevigateassignmentpage")
	public void studentabletonevigateassignmentspagethroughlessonpage()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("21901");//login as student21901
			new Navigator().NavigateTo("eTextbook");
			new TOCShow().tocHide();
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();
			Thread.sleep(3000);
			driver.findElement(By.className("view-all-assignment-right-tab")).click(); //click on view all assignment link
			Thread.sleep(3000);
			String pagevalue=driver.findElement(By.className("sbSelector")).getAttribute("title");
			if(!pagevalue.trim().equals("All Assignments"))
				Assert.fail("Student not nevigated to assignment page");
			String assignmentname1=ReadTestData.readDataByTagName("", "assessmentname", "2190");
			String assignmentname2=ReadTestData.readDataByTagName("", "assessmentname", "21902");
			boolean assignmentpresent1=false;
			boolean assignmentpresent2=false;
			List<WebElement> allElements=driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
			for(WebElement element: allElements)
			{				
				if(element.getText().contains(assignmentname1))
				{
					assignmentpresent1=true;	
					break;
				}
			}
			for(WebElement element: allElements)
			{				
				if(element.getText().contains(assignmentname2))
				{
					assignmentpresent2=true;	
					break;
				}
			}
			
			if(assignmentpresent1==false || assignmentpresent2==false)
				Assert.fail("All assignment  not present");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase studentabletonevigateassignmentspagethroughlessonpage in class StudentAbleToNevigateAssignmentPage",e);
		}
	}


}
