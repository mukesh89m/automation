package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;

public class LTILoginWithCourseNotConfigure extends Driver {
	
    private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LTILoginWithCourseNotConfigure");
	// 7
   @Test
   public void ltiLoginWithCourseNotConfigure()
   {
	   try
	   {
		   logger.log(Level.INFO,"TestCase LTILoginWithCourseNotConfigure Started");
		   new LoginUsingLTI().ltiLogin( "7");
		   Thread.sleep(5000);
		   String text=driver.findElement(By.xpath("html/body/div[2]/div[3]/div[1]")).getText();
		   String errormsg=ReadTestData.readDataByTagName("", "expectederror", "7");
		   System.out.println(text+"  "+errormsg);
		   if(text.contains(errormsg))
		   {
			   logger.log(Level.INFO,"TestCase LTILoginWithCourseNotConfigure Pass ");
		   }
		   else
		   {
			   logger.log(Level.INFO,"TestCase LTILoginWithCourseNotConfigure fail ");
			   Assert.fail("After login with course not configure its not going to error page");
		   }
	   }
	   catch(Exception e)
	   {
		   logger.log(Level.SEVERE,"Exception in  TestCase ltiLoginWithCourseNotConfigure in class LTILoginWithCourseNotConfigure",e); 
		   Assert.fail("Exception in  TestCase ltiLoginWithCourseNotConfigure in class LTILoginWithCourseNotConfigure",e);
	   }
   }

}
