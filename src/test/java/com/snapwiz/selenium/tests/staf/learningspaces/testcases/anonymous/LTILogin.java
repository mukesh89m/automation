package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;

public class LTILogin extends Driver {

	  private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.LTILogin");
	 /*
	  * 1-2
	  */
	  
	 @Test
	  public void loginlti()  {
		 try
		 {
             String lstextverify = ReadTestData.readDataByTagName("LTILogin", "lstextverify", "2");
             String adtextverify=ReadTestData.readDataByTagName("LTILogin", "adtextverify", "2");
			 new LoginUsingLTI().ltiLogin("2");
			 Thread.sleep(10000);
			 WebElement text=driver.findElement(By.className("al-content-title"));
			 String validtext=text.getText();
             System.out.println("validtext: "+validtext);
             System.out.println("lstextverify: "+lstextverify);

			 if(validtext.trim().equals(adtextverify))
			 {
				 logger.log(Level.INFO,"Student landed on to learning spaces dashboard. And No Adaptive feature present. ");
			 }
			 else
			 {
				 logger.log(Level.INFO,"Student should NOT land on to learning spaces dashboard. or Adaptive feature present. ");
				 Assert.fail("Student din NOT land on to learning spaces dashboard OR Adaptive feature is present on the dashboard.");
			 }
         
		 }
		 catch(Exception e)
		 {
             Assert.fail("Exception  in testcase loginlti in class LTILogin.",e);
		 }
	 }

	
}
