package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R50;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentPolicy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class SaveAssignmentPolicy extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void saveAssignmentPolicy()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("133");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
			new AssignmentPolicy().enterPolicyName("Policy Name");
		    new AssignmentPolicy().enterPolicyDescription("Policy description text.");
		    new Click().clickByclassname("ls-save-policy-btn");		//click on Save Policy
		    String notification = new TextFetch().textfetchbyclass("policy-notification-text-span");
		    if(!notification.contains("Saved New Assignment Policy Successfully."))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After saving the policy the required notification doesnt appear.");
		    }
		    new TabClose().tabClose(1);		//close the tab
		    String savedPolicy = new TextFetch().textfetchbyclass("assignment-policy-heading");
		    if(!savedPolicy.contains("Policy Name"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After saving the policy it does not get added assignment policy page.");
		    }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase saveAssignmentPolicy in class SaveAssignmentPolicy.",e);
		}
	}
}
