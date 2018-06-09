package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;

public class RichTextEditorIcon extends Driver
{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LTILoginCidLSAdaptiveCustomDestinationSnapwizChapter");
	/*
	 * Testcase 68 to 74  
	 */
	@Test
	public void richTextEditorIcon()
	{
		try
		{

			logger.log(Level.INFO,"Starting TestCase LTILogin");
			new LoginUsingLTI().ltiLogin("68");
			new Navigator().NavigateTo("Course Stream");
			driver.findElement(By.linkText("Post")).click();
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("div[class='ls-shareImg']")).click();

			driver.findElement(By.linkText("Link")).click();
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("div[class='ls-shareImg']")).click();

			driver.findElement(By.linkText("File")).click();
			Thread.sleep(3000);

			driver.findElement(By.linkText("Post")).click();
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("div[class='ls-shareImg']")).click();
			Thread.sleep(3000);
			boolean tclick = driver.findElement(By.id("html-editor-non-draggable")).isDisplayed();
			if(tclick ==true)
			{
				logger.log(Level.INFO,"able to click on T icon");
				boolean value1=driver.findElement(By.id("undo")).isDisplayed();
                driver.findElement(By.id("undo")).click();
				boolean value2=driver.findElement(By.id("redo")).isDisplayed();

				boolean value3=driver.findElement(By.id("createequation")).isDisplayed();

				boolean value4=driver.findElement(By.id("underline")).isDisplayed();

				//boolean value5=driver.findElement(By.id("justifyfull")).isDisplayed();

				boolean value6=driver.findElement(By.id("insertunorderedlist")).isDisplayed();

				boolean value7=driver.findElement(By.id("superscript")).isDisplayed();

				boolean value8=driver.findElement(By.id("hilitecolor")).isDisplayed();
				boolean value9=driver.findElement(By.id("italic")).isDisplayed();

				boolean value10=driver.findElement(By.id("insertorderedlist")).isDisplayed();

				//boolean value11=driver.findElement(By.id("addrawhtml")).isDisplayed();
				if(value1==true && value2==true && value3==true && value4==true && value6==true && value7==true && value8== true && value9==true && value10==true)
				{
					logger.log(Level.INFO,"All the options in rich text editor are visible");
				}
				else
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("All the options in rich text editor are NOT visible");
				}
			}
			else
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Unable to click on T icon");
			}
            driver.findElement(By.linkText("Link")).click();
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("div[class='ls-shareImg']")).click();
            Thread.sleep(3000);
            int i=0;
            List <WebElement> undo = driver.findElements(By.id("undo"));
            for(WebElement un : undo)
            {System.out.println(i);
                System.out.println(un.isDisplayed()); i++;
                if(un.isDisplayed() == true) {
                    un.click();
                    break;
                }
            }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase richTextEditorIcon in class RichTextEditorIcon",e);	 
		}
	}


}
