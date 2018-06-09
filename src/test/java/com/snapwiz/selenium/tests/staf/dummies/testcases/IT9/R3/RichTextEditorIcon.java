package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;

public class RichTextEditorIcon 
{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.LTILoginCidLSAdaptiveCustomDestinationSnapwizChapter");
	/*
	 * Testcase 68 to 74  
	 */
	@Test
	public void richTextEditorIcon()
	{
		try
		{
			Driver.startDriver();
			logger.log(Level.INFO,"Starting TestCase LTILogin");
			new UserCreate().CreateStudent("aa", "");//create student
			new DirectLogin().studentLogin("aa");;
			new Navigator().NavigateTo("Course Stream");
			Driver.driver.findElement(By.linkText("Post")).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("div[class='ls-shareImg']")).click();

			Driver.driver.findElement(By.linkText("Link")).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("div[class='ls-shareImg']")).click();

			Driver.driver.findElement(By.linkText("File")).click();
			Thread.sleep(3000);

			Driver.driver.findElement(By.linkText("Post")).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("div[class='ls-shareImg']")).click();
			Thread.sleep(3000);
			boolean tclick = Driver.driver.findElement(By.id("html-editor-non-draggable")).isDisplayed();
			if(tclick ==true)
			{
				logger.log(Level.INFO,"able to click on T icon");
				boolean value1=Driver.driver.findElement(By.id("undo")).isDisplayed();

				boolean value2=Driver.driver.findElement(By.id("redo")).isDisplayed();

				boolean value3=Driver.driver.findElement(By.id("createequation")).isDisplayed();

				boolean value4=Driver.driver.findElement(By.id("underline")).isDisplayed();

				//boolean value5=Driver.driver.findElement(By.id("justifyfull")).isDisplayed();

				boolean value6=Driver.driver.findElement(By.id("insertunorderedlist")).isDisplayed();

				boolean value7=Driver.driver.findElement(By.id("superscript")).isDisplayed();

				boolean value8=Driver.driver.findElement(By.id("hilitecolor")).isDisplayed();
				boolean value9=Driver.driver.findElement(By.id("italic")).isDisplayed();

				boolean value10=Driver.driver.findElement(By.id("insertorderedlist")).isDisplayed();

				if(value1==true && value2==true && value3==true && value4==true  && value6==true && value7==true && value8== true && value9==true && value10==true)
				{
					logger.log(Level.INFO,"All the options in rich text editor are visible");
				}
				else
				{
					Assert.fail("All the options in rich text editor are NOT visible");
				}
			}
			else
			{
				Assert.fail("Unable to click on T icon");
			}

		}
		catch(Exception e)
		{

			Assert.fail("Exception in TestCase richTextEditorIcon in class RichTextEditorIcon",e);	 
		}
	}
	@AfterMethod

	public void tearDown() throws Exception {
		Driver.driver.quit();    
	}

}
