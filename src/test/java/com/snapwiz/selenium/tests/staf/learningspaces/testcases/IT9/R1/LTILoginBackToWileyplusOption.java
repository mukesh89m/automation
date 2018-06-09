package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.CloseHelpPage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;

public class LTILoginBackToWileyplusOption extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LTILoginBackToWileyplusOption");

	@Test
	public void ltiLoginBackToWileyplusOption()
	{
		try
		{
		   new LoginUsingLTI().ltiLogin("37");
		   new CloseHelpPage().closehelppage();
	       boolean backtooption=driver.findElement(By.id("al-header-return-url")).isDisplayed();
	        if(backtooption==true)
	        {
	        	logger.log(Level.INFO,"Testcase LTILoginBackToWileyplusOption Pass ");
	        }
	        else
	        {
	        	logger.log(Level.INFO,"Testcase LTILoginBackToWileyplusOption Fail ");
	        	Assert.fail("Back to WileyPlus option NOT available over dashboard(similar to what we have in the current ORION product)");
	        }			
		}
		catch(Exception e)
		 {
			 Assert.fail("Exception  in testcase ltiLoginBackToWileyplusOption in class ltiLoginBackToWileyplusOption.",e);
		 }
	}

}
