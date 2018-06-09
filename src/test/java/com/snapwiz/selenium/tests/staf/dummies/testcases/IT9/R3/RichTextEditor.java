package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;

public class RichTextEditor {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.RichTextEditor");
	/*
	 * 84-94
	 */
	@Test
	public void richTextEditor()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("aa", "");//create student
			new DirectLogin().studentLogin("aa");;
			new Navigator().NavigateTo("Course Stream");
			Driver.driver.findElement(By.linkText("Post")).click();
			int tvalue=Driver.driver.findElements(By.className("ls-shareImg")).size(); // T icon Image
			if(tvalue ==1)
			{
				Driver.driver.findElement(By.className("ls-shareImg")).click();
				Thread.sleep(5000);


				boolean editorvalue=Driver.driver.findElement(By.id("html-editor-non-draggable")).isDisplayed();
				if(editorvalue==true)
				{
					logger.log(Level.INFO,"TextRichbox is open and T icon able to click");
					boolean value1=Driver.driver.findElement(By.id("undo")).isDisplayed();

					boolean value2=Driver.driver.findElement(By.id("redo")).isDisplayed();

					boolean value3=Driver.driver.findElement(By.id("createequation")).isDisplayed();

					boolean value4=Driver.driver.findElement(By.id("underline")).isDisplayed();


					boolean value6=Driver.driver.findElement(By.id("insertunorderedlist")).isDisplayed();

					boolean value7=Driver.driver.findElement(By.id("superscript")).isDisplayed();

					boolean value8=Driver.driver.findElement(By.id("hilitecolor")).isDisplayed();
					boolean value9=Driver.driver.findElement(By.id("italic")).isDisplayed();

					boolean value10=Driver.driver.findElement(By.id("insertorderedlist")).isDisplayed();

					if(value1==true && value2==true && value3==true && value4==true && value6==true && value7==true && value8== true && value9==true && value10==true )
					{
						System.out.println("All True");						
						Thread.sleep(5000);
						Driver.driver.findElement(By.xpath("//*[@id='createequation']")).click();

						Thread.sleep(10000);
						Driver.driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/button")).click();

						Driver.driver.findElement(By.xpath("/html/body/div/div/div/div/div[3]/div/table[3]/tbody/tr/td/button")).click();


						Driver.driver.findElement(By.cssSelector("input[class='wrs_focusElement']")).sendKeys("5");



						Driver.driver.findElement(By.id("wiris-container-save-user-text-input-div")).click();


						Driver.driver.findElement(By.id("post-submit-button")).click();
						Thread.sleep(5000);						
						String postedmathml=Driver.driver.findElement(By.className("ls-stream-post__body")).getText();
						System.out.println(postedmathml);

						if(!postedmathml.contains("5"))
						{
							logger.log(Level.INFO,"Mathml value not entered");
							Assert.fail("Mathml value not posted successfullys");
						}

						Driver.driver.navigate().refresh();
						new Navigator().NavigateTo("Course Stream");


						postedmathml=Driver.driver.findElement(By.className("ls-stream-post__body")).getText();
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
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
