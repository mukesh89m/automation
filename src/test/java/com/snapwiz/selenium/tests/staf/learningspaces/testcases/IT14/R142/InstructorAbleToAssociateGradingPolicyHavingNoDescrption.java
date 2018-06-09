package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R142;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UploadResources;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;


public class InstructorAbleToAssociateGradingPolicyHavingNoDescrption extends Driver
{
	@Test
	public void instructorAbleToAssociateGradingPolicyHavingNoDescrption()
	{
		try
		{
			String gradingpolicy=ReadTestData.readDataByTagName("", "gradingpolicy", "103");
			new Assignment().create(103);
			new LoginUsingLTI().ltiLogin("103");
			//103
			new UploadResources().uploadResources("103", false, true, false);
			new Assignment().AssignAssessmentwithgradingPolicy(103, false);
			new Navigator().NavigateTo("Assignments");
			//104
			String gradingpolicytdescrption=new TextFetch().textfetchbyclass("ls-assignment-grading-desc");
			if(!gradingpolicytdescrption.contains(gradingpolicy))
				Assert.fail("grading policy text shown even there is no description in grading policy");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in test cases InstructorAbleToAssociateGradingPolicyHavingNoDescrption ",e);
		}
	}

}
