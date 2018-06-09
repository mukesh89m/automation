package com.snapwiz.selenium.tests.staf.orion.testcases;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class QuestionMarkSymbol {
	
	@Test(priority = 1, enabled = true)
	public void clickOnProficiencySymbolInStudentDashboard()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("588");
			new DiagnosticTest().startTest(0, 3);
			for(int i=0;i<5;i++)
			new DiagnosticTest().attemptCorrect(0);
			new DiagnosticTest().quitTestAndGoToDashboard();
			new Navigator().orionDashboard();
			Driver.driver.findElement(By.id("al-proficiency-help-img-dashboard")).click();
			Thread.sleep(2000);
			if(!Driver.driver.findElement(By.className("help-data-container")).getText().equalsIgnoreCase("Proficiency is estimated by taking into account a number of factors, such as answers to questions, confidence, and time. At first, the Proficiency bars will appear faded because ORION needs more data. As you answer more questions, the Proficiency bars will become brighter."))
				Assert.fail("The description shown after clicking on the question mark for Proficiency heading in student dashboard");
			Driver.driver.findElement(By.id("al-performance-help-img-dashboard")).click();
			if(!Driver.driver.findElement(By.className("help-data-container")).getText().equalsIgnoreCase("Performance is the number of correct answers out of the number of attempted questions."))
				Assert.fail("The description shown after clicking on the question mark for Performance heading in student dashboard");
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickOnProficiencySymbol in class QuestionMarkSymbol");
		}
	}
	
	@AfterMethod
	public void TearDown()throws Exception
	{
		Driver.driver.quit();
	}
	

}
