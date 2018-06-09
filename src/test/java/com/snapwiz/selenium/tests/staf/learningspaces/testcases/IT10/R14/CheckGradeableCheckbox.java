package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class CheckGradeableCheckbox extends Driver
{
	
	@Test(priority = 1, enabled = true)
	
	public void uncheckGradableCheckbox()
	{
		try
		{
			new Assignment().create(1714);
			new LoginUsingLTI().ltiLogin("1714");
			new Assignment().assignToStudent(1714);
			
			new LoginUsingLTI().ltiLogin("1714");
			new Navigator().NavigateTo("Assignments");
			/*
			 * checking for 'Graded' Label with Gradable Checkbox is unchecked
			 */
			int sizeGrade = driver.findElements(By.cssSelector("span[class='ls-assignment-graded-quiz-icon assessment-block-icon']")).size();
			if(sizeGrade != 0)
			{
				Assert.fail("Gradable is not checked but 'Graded' Label still appears in assignment");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in uncheckGradeableCheckbox in class CheckGradeableCheckbox",e);
		}
		
	}
	
	@Test(priority = 2, enabled =true)
	public void checkGradableCheckbox()
	{
		try
		{
			new Assignment().create(1715);
			new LoginUsingLTI().ltiLogin("1715");
			new Assignment().assignToStudent(1715);
			
			new LoginUsingLTI().ltiLogin("1715");
			new Navigator().NavigateTo("Assignments");
			/*
			 * checking for 'Graded' Label with Gradable Checkbox is checked
			 */
			int sizeGrade = driver.findElements(By.cssSelector("span[class='ls-assignment-graded-quiz-icon assessment-block-icon']")).size();
			if(sizeGrade == 0)
			{
				Assert.fail("Gradable is checked but 'Graded' Label still not appearing in assignment");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in checkGradableCheckbox in class CheckGradeableCheckbox",e);
		}
		
	}


}
