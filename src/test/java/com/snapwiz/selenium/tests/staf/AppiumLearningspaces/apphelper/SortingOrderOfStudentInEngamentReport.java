package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.TextFetch;



public class SortingOrderOfStudentInEngamentReport 
{
	public void sortingOrderOfStudentInEngamentReport(int indextosort)
	{
		try 
		{
			String studentname=new TextFetch().textfetchbylistclass("students-report-name-title", 1);
			new Click().clickbylist("students-report-sort-icon", indextosort);//click on sort icon
			new Click().clickbylist("students-report-sort-icon", indextosort);
			String studentname1=new TextFetch().textfetchbylistclass("students-report-name-title", 1);
			//84,85,78
			if(studentname.equals(studentname1))
			{
				Assert.fail("after click on sorting link name not sorted again");
			}
			
		} 
		catch (Exception e)
		{
			Assert.fail("Exception in app helper SortingOrderOfStudentInEngamentReport",e);
		}
	}

}
