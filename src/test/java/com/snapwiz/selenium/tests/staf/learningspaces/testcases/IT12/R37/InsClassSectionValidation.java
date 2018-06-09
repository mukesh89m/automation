package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R37;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;

public class InsClassSectionValidation extends Driver
{
	@Test(priority = 1, enabled = true)
	public void  newContenxtId()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("28");
			Thread.sleep(3000);
			String contextidfield = driver.findElement(By.cssSelector("a[class='sbSelector']")).getText();
			if(!contextidfield.equals("studtitle"))
				Assert.fail("Instructor not registered with the mentioned context_id");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase newcontenxtid in class instructorclasssectionvalidation",e);
		}
	}

	
	@Test(priority = 2, enabled = true)
	public void  contenxtIdFromJsonString()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("29");
			Thread.sleep(3000);
			new ComboBox().selectValue(0,"Kimmel_ClassA");
			Thread.sleep(2000);			
			new ComboBox().selectValue(0,"Kimmel_ClassB");
			Thread.sleep(2000);
			new ComboBox().selectValue(0,"studtitle");
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase contenxtidfromjsonstring in class instructorclasssectionvalidation",e);
		}
	}

	@Test(priority = 3, enabled = true)
	public void  contextIdFieldWithJsonString()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("30");
			Thread.sleep(3000);
			String contextidfieldwithjsonvalue = driver.findElement(By.cssSelector("a[class='sbSelector']")).getText();
			if(!contextidfieldwithjsonvalue.equals("Kimmel_ClassB"))
				Assert.fail("Instructor not registered with the mentioned context_id");	
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase contextidfieldwithjsonstring in class instructorclasssectionvalidation",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void  contenxtIdWithOnlyJsonString()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("31");
			Thread.sleep(3000);
			new ComboBox().selectValue(0,"Kimmel_ClassA");
			Thread.sleep(2000);			
			new ComboBox().selectValue(0,"Kimmel_ClassB");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase contenxtidwitonlyjsonstring in class instructorclasssectionvalidation",e);
		}
	}

}
