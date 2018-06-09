package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.CloseHelpPage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class LTILoginAdaptiveCourseValidResourceLinkId extends Driver {

	
	 @Test
	  public void ltiLoginAdaptiveCourseValidResourceLinkId()  
	 {
		 try
		 {
			 new LoginUsingLTI().ltiLogin("24");
			 new CloseHelpPage().closehelppage();
			 String text=new TextFetch().textfetchbyclass("al-content-title");
			 if(!text.contains("BUILD YOUR PROFICIENCY"))
				 Assert.fail("not shown login page");
		 }
		 catch(Exception e)
		 {
			 Assert.fail("Exception in class LTILoginAdaptiveCourseValidResourceLinkId in method lTILoginAdaptiveCourseValidResourceLinkId",e);
		 }
  	 }
	 

}
