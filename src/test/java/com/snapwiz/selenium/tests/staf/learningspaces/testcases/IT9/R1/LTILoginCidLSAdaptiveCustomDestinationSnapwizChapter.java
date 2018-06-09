package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TextValidate;


public class LTILoginCidLSAdaptiveCustomDestinationSnapwizChapter extends Driver {
	


	  @Test
	  public void loginltiCidAsLSAdaptiveCustomDestinationAsSnapwizChapter()
	  {
		  try
			 {
			 new LoginUsingLTI().ltiLogin("15");
			 String expectederror = ReadTestData.readDataByTagName("", "expectederror", "15");
			 Boolean textPresent = new TextValidate().IsTextPresent(expectederror);
		    	if(textPresent == false)		    	
			    	Assert.fail("LTI login with custom destination as Snapwiz-chapter successful");
			 }
	         
			 catch(Exception e)
			 {
				 Assert.fail("Exception in TestCase loginltiCidAsLSAdaptiveCustomDestinationAsSnapwizChapter in class LTILoginCidLSAdaptiveCustomDestinationSnapwizChapter",e);	 
			 }
			
	  }
	  

		
	}


