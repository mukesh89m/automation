package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;

public class LTILSLoginCustomDestinationWithInvalidData extends Driver{

	@Test
	public void ltiLSLoginCustomDestinationWithInvalidData()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("12");
			String text=driver.findElement(By.xpath("html/body/div[2]/div[3]/div[2]")).getText();;
			String texttoverify=ReadTestData.readDataByTagName("", "expectederror", "12");
			if(!text.contains(texttoverify))
			{
				Assert.fail("User is not redirected to error page when custom-distination, field with invalid data");
			}		
		}
		catch(Exception e)
		 {
			 Assert.fail("Exception  in testcase loginlti in class LoginUsingLTI",e);
		 }		
	}


}
