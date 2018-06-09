package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import com.snapwiz.selenium.tests.staf.dummies.apphelper.*;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.uihelper.Click;

public class StaticAssignmentSubmit 
{
	public void staticAssignmentSubmit(int index)
	{
		try
		{
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");
			new Click().clickbylistcssselector("span[class='ls-assignment-name instructor-assessment-review']", index);
			Thread.sleep(2000);
			new Click().clickBycssselector("a[class='btn btn--primary btn--large btn--submit']");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in appp helper staticassignment",e);
		}
	}
	public void clickonassignment(int index)
	{
		try 
		{
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Assignments");
			new Click().clickbylistcssselector("span[class='ls-assignment-name instructor-assessment-review']", index);
			Thread.sleep(2000);
			
		}
		catch (Exception e) 
		{
			Assert.fail("Exception in appp helper clickonassignment",e);
		}
	}

}
