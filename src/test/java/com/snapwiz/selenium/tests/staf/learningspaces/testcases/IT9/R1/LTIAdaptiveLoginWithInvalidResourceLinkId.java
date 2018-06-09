package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

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

public class LTIAdaptiveLoginWithInvalidResourceLinkId extends Driver{
	  private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LTILoginAd");


	@Test
	public void ltiAdaptiveLoginWithInvalidResourceLinkId()
	{
		try
		{
			logger.log(Level.INFO,"Starting TestCase LTIAdaptiveLoginWithInvalidResourceLinkId");
            new LoginUsingLTI().ltiLogin("25");
			Thread.sleep(5000);
			String excpeterrror=ReadTestData.readDataByTagName("", "expectederror", "25");
			WebElement text=driver.findElement(By.xpath("html/body/div[2]/div[3]/div[1]"));
			String textvalue=text.getText();
			if(!textvalue.contains(excpeterrror))
			{
				Assert.fail("Error message not shown");
			}
		}
		catch(Exception e)
		{
			Assert.fail("exception in testcase ltiAdaptiveLoginWithInvalidResourceLinkId",e);		
		}
	}

}
