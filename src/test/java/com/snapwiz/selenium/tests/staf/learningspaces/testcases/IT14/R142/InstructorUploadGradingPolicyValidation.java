package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R142;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UploadResources;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class InstructorUploadGradingPolicyValidation extends Driver
{
	@Test
	public void instructorUploadGradingPolicyValidation()
	{
		try
		{
			String classsectionname=ReadTestData.readDataByTagName("", "context_title", "58_1");
			new LoginUsingLTI().ltiLogin("58_1");
			new LoginUsingLTI().ltiLogin("58");
			new UploadResources().uploadResources("58", false, true, false);
			Thread.sleep(4000);
			//61
			boolean resorceundermyresourcestab=new BooleanValue().booleanbycssselector("div[class='ls-assessment-item-sectn-right ls-my-resource-item-section']");
			if(resorceundermyresourcestab==false)
				Assert.fail("grading policy present over my resources tab");
			//62,64
			new Click().clickByclassname("default-cs-wrapper");
			new Click().clickBycssselector("div[title='"+classsectionname+"']");
			Thread.sleep(3000);
			new Navigator().NavigateTo("Resources");//navigate to resource 
			new Click().clickBycssselector("div[data-id='my-resources']");//click on my resources tab
			//66
			int resorceundermyresourcestabon2ndclass=driver.findElements(By.cssSelector("div[class='ls-assessment-item-sectn-right ls-my-resource-item-section']")).size();
			if(resorceundermyresourcestabon2ndclass==0)
				Assert.fail("grading policy resource is not present in 2nd class section of instructor my resources tab page");

		}	
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method InstructorUploadGradingPolicyValidation",e);
		}
	}

}
