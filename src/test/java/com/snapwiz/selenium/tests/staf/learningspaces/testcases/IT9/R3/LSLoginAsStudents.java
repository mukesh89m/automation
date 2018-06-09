package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
/*Test CaseId 44 and 45*/
public class LSLoginAsStudents extends Driver{
	
	@Test
	
	  public void ScrollDown()
	  {
		  try
		  {	

			  new LoginUsingLTI().ltiLogin("44");
			  new Navigator().NavigateTo("Course Stream");
			  WebElement text=driver.findElement(By.className("share-to-ls-label"));
			  String validtext=text.getText();
			  if(!validtext.contains("Share a new"))
				  Assert.fail("After login it doesnt take to student dashboard");
		  }
	  	
	catch(Exception e)
		  {
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in ScrollDown in class LSLoginAsStudents",e);
		  }
		  
	}

}
