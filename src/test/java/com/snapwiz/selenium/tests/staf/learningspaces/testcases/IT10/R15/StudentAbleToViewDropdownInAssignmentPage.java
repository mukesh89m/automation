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
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;

public class StudentAbleToViewDropdownInAssignmentPage extends Driver
{
	@Test(priority=1,enabled=false)
	public void studentabletoviewdropdowninassignmentvalue()
	{
		try
		{
			new Assignment().create(2267);//assignment create
			new LoginUsingLTI().ltiLogin("22671");//student create
			new LoginUsingLTI().ltiLogin("2267");//instructor login
			new Assignment().assignToStudent(2267);//assign assignment			
			new LoginUsingLTI().ltiLogin("22671");//student login
			new Navigator().NavigateTo("Assignments");
			boolean dropdownvalue=driver.findElement(By.className("sbHolder")).isDisplayed();
			if(dropdownvalue==false)
				Assert.fail("dropdown not present.");
			String dropdowndefaultvalue=driver.findElement(By.className("sbSelector")).getAttribute("title");
			if(!dropdowndefaultvalue.trim().equals("All Assignments"))
				Assert.fail("Default value of drop down is not All Assignments.");
			driver.findElement(By.className("sbSelector")).click();
			Thread.sleep(3000);
			StringBuilder stringbuilder = new StringBuilder();
			List<WebElement> allelements=driver.findElements(By.xpath("//*[starts-with(@id, 'sbOptions_')]"));
			for(WebElement element:allelements)
			{
				stringbuilder.append(element.getText());
			}
			String finalString = stringbuilder.toString();
			if(!finalString.contains("Non-graded Assessment")|| !finalString.contains("Graded Assessment") || !finalString.contains("Learning Activity"))
				Assert.fail("in dropdown --Graded Assessment,Non-graded Assessment,Learning Activity not present" );
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase studentabletoviewdropdowninassignmentvalue in class  StudentAbleToViewDropdownInAssignmentPage",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void filtervalueindropdown()
	{
		try
		{
			new Assignment().create(2283);//assignment create
			new Assignment().create(22832);//assignment create
			new LoginUsingLTI().ltiLogin("22831");//user create
			new LoginUsingLTI().ltiLogin("2283");//1st instructor login
			new Assignment().assignToStudent(2283);//Assign assignment to student
			new LoginUsingLTI().ltiLogin("22832");//2nd instructor login
			new Assignment().assignToStudent(22832);//Assign assignment to student
			new LoginUsingLTI().ltiLogin("22831");//student login
			new Navigator().NavigateTo("Assignments");
			StringBuilder stringbuilder=new StringBuilder();
			List<WebElement> allelements=driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
			for(WebElement element:allelements)
			{
				stringbuilder.append(element.getText());
			}
			String finalString = stringbuilder.toString();
			String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", "2283");
			String assignmentname1=ReadTestData.readDataByTagName("", "assessmentname", "22832");
			if(!finalString.contains(assignmentname) || !finalString.contains(assignmentname1))
				Assert.fail("By default all assignments not present on assignments page.");
			new ComboBox().selectValue(0,"Question Assignment");
			String assignmentnameafterfilter=driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).getText();
			if(!assignmentnameafterfilter.contains(assignmentname1))
				Assert.fail("Assignment not filtered as chosen option.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase filtervalueindropdown in class  StudentAbleToViewDropdownInAssignmentPage",e);
		}
	}


}
