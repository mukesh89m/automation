package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class RichTextEditor extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.RichTextEditor");
	/*
	 * 84-94
	 */
	@Test
	public void richTextEditor()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("84");
			new Navigator().NavigateTo("Course Stream");
			String firstpostxpath = ReadTestData.readDataByTagName("objectrepository", "firstpostxpath", "0");
			driver.findElement(By.linkText("Post")).click();
			int tvalue=driver.findElements(By.className("ls-shareImg")).size(); // T icon Image
			if(tvalue ==1)
			{
				String tcolor=driver.findElement(By.className("ls-shareImg")).getCssValue("background-color");
				driver.findElement(By.className("ls-shareImg")).click();
				Thread.sleep(5000);
				String tcolor1=driver.findElement(By.className("ls-shareImg")).getCssValue("background-color");
				
				
				boolean editorvalue=driver.findElement(By.id("html-editor-non-draggable")).isDisplayed();
				if(editorvalue==true)
				{
					logger.log(Level.INFO,"TextRichbox is open and T icon able to click");
					boolean value1=driver.findElement(By.id("undo")).isDisplayed();
					
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
					    System.out.println("All True");						
						Thread.sleep(5000);
						driver.findElement(By.xpath("//*[@id='createequation']")).click();
						
						Thread.sleep(10000);
						driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/button")).click();
						
						driver.findElement(By.xpath("/html/body/div/div/div/div/div[3]/div/table[3]/tbody/tr/td/button")).click();
						
						
						driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys("5");
						
						
						
						driver.findElement(By.id("wiris-container-save-user-text-input-div")).click();
						
					
						driver.findElement(By.id("post-submit-button")).click();
						Thread.sleep(5000);						
						String postedmathml=driver.findElement(By.className("ls-stream-share__title")).getText();
						System.out.println(postedmathml);
					
						if(!postedmathml.contains("5"))
						{
							logger.log(Level.INFO,"Mathml value not entered");
							Assert.fail("Mathml value not posted successfullys");
						}
						
						driver.navigate().refresh();
						new Navigator().NavigateTo("Course Stream");
						
						
						postedmathml=driver.findElement(By.className("ls-stream-share__title")).getText();
						Thread.sleep(2000);
					
						if(!postedmathml.contains("5"))
						{
							logger.log(Level.INFO,"Mathml value not entered");
							Assert.fail("Mathml value not matching with the input given after refreshing the page");
						}
						
					}
					else
					{
						logger.log(Level.INFO,"Testcase RichTextEditor Fail");
						Assert.fail(" All the options in the rich Text editor not visible for the user.");
					}
					
				}
				else
				{
					logger.log(Level.INFO,"RichTextEditor not expand");
					Assert.fail("Students are not able to click on the Rich Text Editor Icon ");
				}
				
			}
			else
			{
				logger.log(Level.INFO,"T icon not display");
				Assert.fail("T option not present for Post/Link/file share");
			}
			
		}
		catch (Exception e)
		 {
			  logger.log(Level.SEVERE,"Exception  in testcase RichTextEditor",e);	
			  Assert.fail("Exception  in testcase RichTextEditor",e);
		  }
	}

}
