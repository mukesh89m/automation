package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;



public class TIconColorChange extends Driver {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.TIconColorChange");
	/*
	 * 80-83
	 */
	@Test
	public void tIconColorChange()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("80");
			String tcolorbeforeclick=driver.findElement(By.className("ls-shareImg")).getCssValue("background-color");
			driver.findElement(By.className("ls-shareImg")).click();
			Thread.sleep(5000);
			boolean editorvalue=driver.findElement(By.id("html-editor-non-draggable")).isDisplayed();
			
			
			
			String tcolorafterclick=driver.findElement(By.className("ls-shareImg")).getCssValue("background-color");
			if(editorvalue==true)
			{
				logger.log(Level.INFO,"TextRichbox is open and T icon able to click");
				boolean value1=driver.findElement(By.id("undo")).isDisplayed();
			
				boolean value2=driver.findElement(By.id("redo")).isDisplayed();
				
				boolean value3=driver.findElement(By.id("createequation")).isDisplayed();
			
				boolean value4=driver.findElement(By.id("underline")).isDisplayed();
		
				boolean value5=driver.findElement(By.id("justifyfull")).isDisplayed();
			
				boolean value6=driver.findElement(By.id("insertunorderedlist")).isDisplayed();
			
				boolean value7=driver.findElement(By.id("superscript")).isDisplayed();
			
				boolean value8=driver.findElement(By.id("hilitecolor")).isDisplayed();
				
				
				if(value1==true && value2==true && value3==true && value4==true && value5==true && value6==true && value7==true && value8== true)
				{
					logger.log(Level.INFO,"Testcase TIconColorChange Pass");
				}
				else
				{
					logger.log(Level.INFO,"Testcase TIconColorChange Fail");
					Assert.fail(" All the options should in the rich Text editor not visible for the user.");
				}
			}
			else
			{
				logger.log(Level.INFO,"RichTextbox not open");
				Assert.fail("student not able to click T icon");
			}
		}
		catch (Exception e)
		 {
			  logger.log(Level.SEVERE,"Exception in DirectLogin Application Helper");
			  Assert.fail("Exception  in testcase loginlti in class LoginUsingLTI",e);
		  }
	}


}
