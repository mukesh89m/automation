package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;
/*
 * Brajesh
 * go to summary page of course
 */
public class CourseSummaryPage 
{
	public void courseSummaryPage()
	{
		try
		{
			new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DirectLogin().CMSLogin();
			new SelectCourse().selectcourse();
			new UIElement().waitAndFindElement(By.id("deliver-course"));
			new Click().clickByid("deliver-course");//click on summary page
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper SummaryPageOfCourse",e);
		}
	}
}
