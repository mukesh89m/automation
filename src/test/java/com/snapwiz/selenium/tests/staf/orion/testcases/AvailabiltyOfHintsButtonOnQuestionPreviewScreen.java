package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import com.snapwiz.selenium.tests.staf.orion.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;

public class AvailabiltyOfHintsButtonOnQuestionPreviewScreen
{
	/*
	 * Brajesh
	 * check functionality about hint button in preview page
	 */
	@Test
	public void availabilityofhintbutton()
	{
		try
		{
			Driver.startDriver();
			String[] tloid=ReadTestData.readData("tloids", "0");
			String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", "396");
			String chaptername=ReadTestData.readDataByTagName("", "chapterName", "396");
			new Assignment().create(396);//create practice test at chapter 1
			new Assignment().addQuestions(397, "qtn-type-true-false-img", assignmentname, false, false,null, false,Integer.parseInt(tloid[0]),Integer.parseInt(tloid[1]));//add question 2			
			new Assignment().addQuestions(398, "qtn-type-true-false-img", assignmentname, false, false,null, false,Integer.parseInt(tloid[0]),Integer.parseInt(tloid[1]));	//add question 3
			new DirectLogin().CMSLogin();
			new SelectCourse().selectcourse();
			Thread.sleep(1000);
			Driver.driver.findElement(By.cssSelector("div[title='"+chaptername+"']")).click();//select chpter name
			Driver.driver.findElement(By.cssSelector("div[title='"+assignmentname+"']")).click();//select assignment
            Thread.sleep(3000);
			Driver.driver.findElement(By.id("preview-the-image-quiz")).click();//click on preview button
			
			//switch to another window
			Thread.sleep(2000);
			String winHandleBefore = Driver.driver.getWindowHandle();
			for(String winHandle : Driver.driver.getWindowHandles()){
				Driver.driver.switchTo().window(winHandle);
			}

			Driver.driver.findElement(By.id("cms-question-preview-footer-hint")).click();//click on hint button
			Thread.sleep(2000);
            if(!Driver.driver.findElement(By.className("cms-question-preview-question-hint-label")).isDisplayed())
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("color of hint button not changed after click again.");
            }
			Driver.driver.findElement(By.id("cms-question-preview-footer-hint")).click();
			Thread.sleep(2000);
			if(Driver.driver.findElement(By.className("cms-question-preview-question-hint-label")).isDisplayed())
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("color of hint button not changed after click again.");
			}
			Driver.driver.close();
			Driver.driver.switchTo().window(winHandleBefore);
            new CMSActions().navigateToQuestionNo(3);
			Driver.driver.findElement(By.id("preview-the-image-quiz")).click();//click on preview button
			//switch to another window
			Thread.sleep(2000);
			String winHandleBefore1 = Driver.driver.getWindowHandle();
			for(String winHandle : Driver.driver.getWindowHandles()){
				Driver.driver.switchTo().window(winHandle);
			}
			boolean hintbutton=Driver.driver.findElement(By.id("cms-question-preview-footer-hint")).isDisplayed();
			if(hintbutton==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("for not hints qustion hints button not shown");
			}
			Driver.driver.close();
			Driver.driver.switchTo().window(winHandleBefore1);
            Driver.driver.findElement(By.cssSelector("#hint > #question-raw-content")).click();
            Thread.sleep(3000);
            Driver.driver.switchTo().frame("iframe-hint").findElement(By.xpath("/html/body")).sendKeys("This is hint text.");//enter hint text
            Driver.driver.switchTo().defaultContent();
            Thread.sleep(3000);
			Driver.driver.findElement(By.id("saveQuestionDetails1")).click();//click on save button
			Thread.sleep(5000);
			Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
			Thread.sleep(2000);
			String winHandleBefore2 = Driver.driver.getWindowHandle();
			for(String winHandle : Driver.driver.getWindowHandles()){
				Driver.driver.switchTo().window(winHandle);
			}
            Driver.driver.findElement(By.id("cms-question-preview-footer-hint")).click();
            Thread.sleep(2000);
            if(!Driver.driver.findElement(By.className("cms-question-preview-question-hint-label")).isDisplayed())
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("color of hint button not changed after click again.");
            }
			Thread.sleep(3000);
			String hinttext=Driver.driver.findElement(By.className("cms-question-preview-question-hint-content")).getText();//Fetch hint text
			if(!hinttext.contains("This is hint text."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("hint text not shown");
			}
				
			Driver.driver.close();
			Driver.driver.switchTo().window(winHandleBefore2);	
			
				
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase availabilityofhintbutton in class AvailabiltyOfHintsButtonOnQuestionPreviewScreen",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}


}
