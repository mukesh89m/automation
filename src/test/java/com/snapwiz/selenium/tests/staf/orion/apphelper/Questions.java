package com.snapwiz.selenium.tests.staf.orion.apphelper;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;

public class Questions {
	
	public void deActivateQuestion(int dataIndex)
	{
		try
		{
		String chapterName = ReadTestData.readDataByTagName("","chapterName" ,Integer.toString(dataIndex));
		String assignmentName = ReadTestData.readDataByTagName("","assignmentName" , Integer.toString(dataIndex));
		new DirectLogin().CMSLogin();	//login as publisher			
		new SelectCourse().selectcourse();//select course
		new SelectCourse().selectchapter(chapterName);//select chapter 
		new SelectCourse().selectassignment(assignmentName);//select assignment
		Driver.driver.findElement(By.cssSelector("span[class='question-link-labels  ']")).click();//click on question
		Thread.sleep(2000);
		Driver.driver.findElement(By.id("questionOptions")).click();//click on question option
		Thread.sleep(2000);
		Driver.driver.findElement(By.id("questionRevisions")).click();//click on revision link
		Thread.sleep(3000);
		Driver.driver.findElement(By.id("cms-question-revision-deactivate-button")).click();
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in App helper deActivateQuestion in class Questions",e);
		}
	}
	
	public void duplicateQuestion(int dataIndex)
	{
		try
		{
			String chapterName = ReadTestData.readDataByTagName("","chapterName" ,Integer.toString(dataIndex));
			String assignmentName = ReadTestData.readDataByTagName("","assignmentName" , Integer.toString(dataIndex));
			new DirectLogin().CMSLogin();	//login as publisher			
			new SelectCourse().selectcourse();//select course
			new SelectCourse().selectchapter(chapterName);//select chapter 
			new SelectCourse().selectassignment(assignmentName);//select assignment
			Driver.driver.findElement(By.cssSelector("span[class='question-link-labels  deactivated']")).click();//click on question
			Driver.driver.findElement(By.id("questionOptions")).click();//click on question option
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("copyQuestion")).click();//click on revision link
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in App helper createDuplicate in class Questions",e);
		}
	}

}
