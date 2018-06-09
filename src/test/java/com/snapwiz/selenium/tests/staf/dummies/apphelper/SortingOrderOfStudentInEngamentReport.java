package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.uihelper.Click;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.TextFetch;



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
				Assert.fail("after click on shoting link name not shorted again");
			}
			
		} 
		catch (Exception e)
		{
			Assert.fail("Exception in app helper SortingOrderOfStudentInEngamentReport",e);
		}
	}

}
