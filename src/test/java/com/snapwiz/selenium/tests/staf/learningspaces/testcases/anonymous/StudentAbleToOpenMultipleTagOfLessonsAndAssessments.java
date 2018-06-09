package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandFirstChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelecteTextBook;

public class StudentAbleToOpenMultipleTagOfLessonsAndAssessments extends Driver {
	public static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.StudentAbleToOpenMultipleTagOfLessonsAndAssessments");
	/*
	 * 1039-1049
	 */
	@Test
	public void AllowToOpenMultipleTag()
	{
		try
		{
			driver.manage().deleteAllCookies();
			new LoginUsingLTI().ltiLogin("1039");
			new SelecteTextBook().etextselector();
			new ExpandFirstChapter().expandFirstChapter();
			driver.findElement(By.cssSelector("body")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("span[class='show-toc']")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("div[class='tab'][data-id='resources']")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("a[title='res do']")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("a[class='ls_assessment_link']")).click();
			Thread.sleep(5000);
			
			driver.findElement(By.cssSelector("body")).click();
			Thread.sleep(5000);
			
			driver.findElement(By.cssSelector("span[class='show-toc']")).click();
			Thread.sleep(5000);
			
			Actions action = new Actions(driver);
	        WebElement we = driver.findElement(By.xpath("//*[@id='lesson-NDY4Nzc0MzMz']"));
	        action.moveToElement(we).build().perform();
	        
			driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li/div/div[2]/ul[3]/li/div/div")).click();
			Thread.sleep(5000);
			WebElement text=driver.findElement(By.cssSelector("div[class='tabs']"));
			String tabtext=(String)((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML", text);
			System.out.println(tabtext);
			String toctext=ReadTestData.readDataByTagName("StudentAbleToOpenMultipleTagOfLessonsAndAssessments", "toctext", "1039");
			String assignmenttext=ReadTestData.readDataByTagName("StudentAbleToOpenMultipleTagOfLessonsAndAssessments", "assginment", "1039");
			String tabicon=ReadTestData.readDataByTagName("StudentAbleToOpenMultipleTagOfLessonsAndAssessments", "tabicon", "1039");
			if(tabtext.contains(toctext) && tabtext.contains(assignmenttext) && tabtext.contains(tabicon))
			{
				
				int tab1=tabtext.indexOf(toctext);
				int tab2=tabtext.indexOf(assignmenttext);
				
				if(tab1<tab2)
				{
					
					logger.log(Level.INFO,"TOc tab on 1st palce");
					driver.findElement(By.cssSelector("div[class='tab'][data-id='assignment']")).click();
					boolean timewatch=driver.findElement(By.id("assessmentTimer")).isDisplayed();
					if(timewatch==true)
					{
						
						logger.log(Level.INFO,"student able nevigate to the tab");
						driver.findElement(By.cssSelector("span[class='show-toc']")).click();
						driver.findElement(By.cssSelector("body")).click();
						boolean closetab=driver.findElement(By.cssSelector("span[id='close-assignment']")).isDisplayed();
						if(closetab==true)
						{
							driver.findElement(By.cssSelector("span[id='close-assignment']")).click();
							try
							{
								boolean timewatch1=driver.findElement(By.id("assessmentTimer")).isDisplayed();
								Assert.fail("Tab is display after close it");
								
							}
							catch(Exception e)
							{
								driver.quit();
								startDriver("firefox");
								new LoginUsingLTI().ltiLogin("1039");
								new SelecteTextBook().etextselector();
								new ExpandFirstChapter().expandFirstChapter();
								driver.findElement(By.xpath("/html/body/div[3]/div/div/ul[2]/div/div[2]/div/li/div/div[2]/ul/li[3]")).click();
								driver.findElement(By.xpath("//*[@id='lesson-NDY4ODM5ODY5']")).click();
								driver.findElement(By.cssSelector("div[class='tab'][data-id='resources']")).click();
								Thread.sleep(5000);
								driver.findElement(By.cssSelector("a[title='res do']")).click();
								Thread.sleep(5000);
								WebElement text1=driver.findElement(By.cssSelector("div[class='tabs']"));
								String tabtext1=(String)((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML", text1);
								String toctext1=new ReadTestData().readDataByTagName("StudentAbleToOpenMultipleTagOfLessonsAndAssessments", "toctext", "1039");
								String assignmenttext1=new ReadTestData().readDataByTagName("StudentAbleToOpenMultipleTagOfLessonsAndAssessments", "assginment", "1039");
								if(tabtext.contains(toctext1) && tabtext.contains(assignmenttext1))
								{
									logger.log(Level.INFO,"Students are able to open mautiple tag from toc and right side area");
								}
								else
								{
									logger.log(Level.INFO,"Students are Not able to open mautiple tag from toc and right side area");
									Assert.fail("Students are Not able to open mautiple tag from toc and right side area");
								}
								
								
							}
							
						}
						else
						{
							logger.log(Level.INFO,"Close tab option not avilable");
							Assert.fail("Close tab option not avilable");
						}
						
						
						
						
						
						
					}
					else
					{
						logger.log(Level.INFO,"student not able nevigate to the tab");
						Assert.fail("student not able nevigate to the tab");
						
					}
				}				
				else
				{
					logger.log(Level.INFO,"TOc tab Not on 1st palce");
					Assert.fail("TOc tab Not on 1st palce");
				}
			}
			else				
			{
				logger.log(Level.INFO,"multiple tab does not open ");
				Assert.fail("Multiple tab does not open");
			}
			
			
			
			
			
		}
		catch(Exception e)
		   {
				  logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper",e);
				  driver.quit();
				  Assert.fail();
				  
		   }
	}


}
