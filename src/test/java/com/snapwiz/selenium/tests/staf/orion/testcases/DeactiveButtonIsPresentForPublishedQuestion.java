package com.snapwiz.selenium.tests.staf.orion.testcases;

import com.snapwiz.selenium.tests.staf.orion.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.orion.uihelper.ComboBox;

public class DeactiveButtonIsPresentForPublishedQuestion
{
	@Test
	public void deactiveButtonIsPresentForPublishedQuestion()
	{
		try
		{
			Driver.startDriver();
			String[] tloid=ReadTestData.readData("tloids", "0");
			String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", "601");
			String chaptername=ReadTestData.readDataByTagName("", "chapterName", "601");
			new Assignment().create(601);//create practice test at chapter 1
			new Assignment().addQuestions(6011, "qtn-type-true-false-img", assignmentname, false, false,null, false,Integer.parseInt(tloid[0]),Integer.parseInt(tloid[1]));//add question 2
			new DirectLogin().CMSLogin();	//login as publisher		
			new SelectCourse().selectcourse();//select course
			new SelectCourse().selectchapter(chaptername);//select chapter 
			new SelectCourse().selectassignment(assignmentname);//select assignment
			Driver.driver.findElement(By.xpath("(//*[starts-with(@id,'question-label')])[3]")).click();//click on unpublish question
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("questionOptions")).click();//click on question option
			Thread.sleep(6000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("questionRevisions"))); //click on revision link
			//Driver.driver.findElement(By.id("questionRevisions")).click();//click on revision link
			Thread.sleep(3000);
			boolean version=Driver.driver.findElement(By.id("cms-question-revision-version-and-action")).isDisplayed();
			if(version==false)
			{
				Assert.fail("diffrent version not shown for unpublish question");
			}
            Thread.sleep(5000);
			int deactivatebutton=Driver.driver.findElements(By.id("cms-question-revision-deactivate-button")).size();
            System.out.println("Deactive button:"+deactivatebutton);
            if(deactivatebutton!=0)
			{
				Assert.fail("deactive button shown for unpublish question.");
			}
			new ComboBox().selectValue(3, "QA");//change status
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("saveQuestionDetails1")).click();//save the question status
			Thread.sleep(5000);
			Driver.driver.findElement(By.id("questionOptions")).click();//click on question option
			Thread.sleep(2000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("questionRevisions"))); //click on revision link
			Thread.sleep(6000);
			int deactivatebutton1=Driver.driver.findElements(By.id("cms-question-revision-deactivate-button")).size();
			if(deactivatebutton1!=0)
			{
				Assert.fail("deactive 1 button shown for unpublish question.");
			}
			new ComboBox().selectValue(3, "Approve");
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("saveQuestionDetails1")).click();//save the question status
			Thread.sleep(5000);
			Driver.driver.findElement(By.id("questionOptions")).click();//click on question option
			Thread.sleep(2000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("questionRevisions"))); //click on revision link
			Thread.sleep(6000);
			int deactivatebutton2=Driver.driver.findElements(By.id("cms-question-revision-deactivate-button")).size();
			if(deactivatebutton2!=0)
			{
				Assert.fail("deactive 2 button shown for unpublish question.");
			}
			
			
			Driver.driver.findElement(By.cssSelector("span[class='question-link-labels  ']")).click();//click on publish question
			Thread.sleep(5000);
			Driver.driver.findElement(By.id("questionOptions")).click();//click on question option
			Thread.sleep(2000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("questionRevisions"))); //click on revision link
			Thread.sleep(6000);
			boolean version1=Driver.driver.findElement(By.id("cms-question-revision-version-and-action")).isDisplayed();
			if(version1==false)
			{
				Assert.fail("diffrent version not shown for unpublish question");
			}
			int deactivatebutton3=Driver.driver.findElements(By.id("cms-question-revision-deactivate-button")).size();
			System.out.println(deactivatebutton3);
			if(deactivatebutton3==0)
			{
				Assert.fail("deactive button not shown for publish question.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase  deactiveButtonIsPresentForPublishedQuestion in class DeactiveButtonIsPresentForPublishedQuestion",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}

}
