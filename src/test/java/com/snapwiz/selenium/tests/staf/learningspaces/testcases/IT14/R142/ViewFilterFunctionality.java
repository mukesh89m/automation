package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R142;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UploadResources;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class ViewFilterFunctionality extends Driver
{
	@Test
	public void viewFilterFunctionality()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("81");//login as instructor
			new UploadResources().uploadResources("81", false, true, false);
			Thread.sleep(3000);
			//81
			new Navigator().NavigateTo("Resources");//navigate to resource 
			new Click().clickBycssselector("div[data-id='my-resources']");//click on my resources tab
			String filtersectiontext=new TextFetch().textfetchbyclass("ls-assessment-filter-section");
			if(!filtersectiontext.contains("Type"))
				Assert.fail("type of resources dropdown not shown");
			if(!filtersectiontext.contains("All Chapters"))
				Assert.fail("All Chapters dropdown not shown");
			if(!filtersectiontext.contains("All Sections"))
				Assert.fail("All Sections dropdown not shown");
			//82
			driver.findElement(By.linkText("Type")).click();//click on type
			String type=driver.findElement(By.id("ls-assessment-myActivity-filter-allactivity-drop-down-wrapper")).getText(); //fetch text from dropdown
			if(!type.contains("Learning Activities"))
				Assert.fail("in Type of resources Learning Activities type not present");
			//83
			new Click().clickBycssselector("a[title='Assignment Reference']");
			Thread.sleep(2000);
			//87
			new Click().clickBycssselector("span[title='All Resources']");//click on all resources tab
			Thread.sleep(2000);
			//new Click().clickbypartiallinkText("View Filters");//click on view filter link under all resources tab
			Thread.sleep(2000);
			String filtersectiontext1=new TextFetch().textfetchbylistclass("ls-assessment-filter-section",1);
			if(!filtersectiontext1.contains("All Chapters"))
				Assert.fail("All Chapters dropdown not shown under all resources tab");
			if(!filtersectiontext1.contains("All Sections"))
				Assert.fail("All Sections dropdown not shown under all resources tab");
            new Click().clickbylinkText("All Chapters");
			new Click().clickbypartiallinkText("Ch 1");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method viewFilterFunctionality ",e);
		}
	}

}
