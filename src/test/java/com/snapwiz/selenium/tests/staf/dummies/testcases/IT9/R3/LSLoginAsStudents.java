package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;
/*Test CaseId 44 and 45*/
public class LSLoginAsStudents {

	@Test
	public void ScrollDown()
	{
		try
		{	
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			WebElement text=Driver.driver.findElement(By.className("share-to-ls-label"));
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
	@AfterMethod
	public void tearDown() throws Exception {
		Driver.driver.quit();    
	}
}
