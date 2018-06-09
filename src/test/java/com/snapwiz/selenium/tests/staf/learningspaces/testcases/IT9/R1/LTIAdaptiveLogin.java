package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;


import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class LTIAdaptiveLogin extends Driver{
	
	  //3 Verify AdaptiveLTiLogin
	  
	 @Test
	  public void ltiAdaptiveLogin()  {
		 try
		 {			 
			 new LoginUsingLTI().ltiLogin("4");
			 if(driver.findElements(By.className("al-dialog-welcome-close-icon")).size()>0)
				 driver.findElement(By.className("al-dialog-welcome-close-icon")).click();
			
			 String adtextverify=ReadTestData.readDataByTagName("", "text2", "4");
			 String value=new TextFetch().textfetchbyclass("al-content-title-wrapper");
             Assert.assertEquals(value, adtextverify, "The student unable to navigate to the current ORION product");

		 }
		 catch(Exception e)
		 {
			 Assert.fail("Exception in  TestCase ltiAdaptiveLogin in class LTIAdaptiveLogin.",e);
		 }			
	 }

}
