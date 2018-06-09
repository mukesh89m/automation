package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;


import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import junit.framework.TestCase;

import org.openqa.selenium.By;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class LSShareBoxClassSectionNameByDefault  extends Driver{
	
	/*
	 * R3.1020
	 * Verify the student is able to view the class section name in the auto-suggest 
	 */
		
	  @Test
	  public void shareBoxClassSectionNameByDefault()
	  {
          try {
              new LoginUsingLTI().ltiLogin("1509");
              new Navigator().NavigateTo("Course Stream");
              new Click().clickbylinkText("Post");
              String test = driver.findElement(By.className("item-text")).getText();
              if (!test.trim().equals("studtitle"))
                  TestCase.fail("Class Section Name not found by default in Share TextBox");
          }
          catch (Exception e)
          {
              Assert.fail("Exception in TC shareBoxClassSectionNameByDefault in class LSShareBoxClassSectionNameByDefault.", e);
          }
	  }
	  


}
