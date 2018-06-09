package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UploadResources;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class VerifyGradingPolicyDropdown extends Driver{
	
	@Test
	public void verifyGradingPolicyDropdown()
	{
		try
		{
			String resourcesname = ReadTestData.readDataByTagName("", "resourcesname", "99");
			String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", "99");
			String totalpoints = ReadTestData.readDataByTagName("", "totalpoints", "99");
			String description=ReadTestData.readDataByTagName("", "description", "99");
			String duetime=ReadTestData.readDataByTagName("", "duetime", "99");
			String duedate=ReadTestData.readDataByTagName("", "duedate", "99");
			String additionalnote=ReadTestData.readDataByTagName("", "additionalnote", "99");
			new LoginUsingLTI().ltiLogin("99_1");		//login as student
			new LoginUsingLTI().ltiLogin("99");		//login as instructor
			new UploadResources().uploadResources("99", false, true, false);
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			//open Lesson With Discussion Widget
            new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.assign-this-text")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-dw-gradable-label-check ")));	//check gradable chekbox
			Thread.sleep(2000);
			//TC row no. 99
			String str = driver.findElement(By.cssSelector("div[class='ir-ls-grading-policy-filter-drop-down-wrapper ir-ls-assign-dialog-field scrollbar-wrapper']")).getText();
			if(!str.contains("Select your Assignment Reference"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Select your Assignment Reference\" is not selected by default.");
			}
			if(System.getProperty("UCHAR") == null) {
				resourcesname = resourcesname + LoginUsingLTI.appendChar;
			}
			else {
				resourcesname = resourcesname + System.getProperty("UCHAR");
			}
			new ComboBox().selectValue(1, resourcesname);//select Grading policy
			//TC row no. 104
			driver.findElement(By.id("total-points")).sendKeys(totalpoints);  //enter Total Points
			//TC row no. 105

			driver.findElement(By.id("due-time")).click();//click on due time
			driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();

		/*	List<WebElement> elements = driver.findElements(By.xpath("//li"));//fetch all time section
			for(WebElement time : elements)
			{
				if(time.getText().equals(duetime))
				{
					time.click();
					break;
				}
			}*/

			driver.findElement(By.id("due-date")).click();//click on due date
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a[title='Next']")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText(duedate)).click();//click on specified date
			Thread.sleep(2000);
			driver.findElement(By.id("additional-notes")).click();
		    driver.switchTo().activeElement().sendKeys(additionalnote);//add additional note
		    driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
		    Thread.sleep(3000);
		    new Navigator().NavigateTo("Assignments");	//navigate to Assignments
		    //TC row no. 332
		    int size = driver.findElements(By.cssSelector("div[class='ls-resource-doctypes ls-resource-text']")).size();
		    if(size == 0 )
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Grading policy file icon for a DW assignment is not displayed in instructor side.");
		    }
		    String name = driver.findElement(By.cssSelector("div[class=' ls-assignment-cart-item']")).getText();
		    if(!name.contains(resourcesname))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Grading policy file link for a DW assignment is not displayed in instructor side.");
		    }
		    
		    new LoginUsingLTI().ltiLogin("99_1");		//login as student
		    new Navigator().NavigateTo("Assignments");	//navigate to Assignments
		    //TC row no. 390
		    int size1 = driver.findElements(By.cssSelector("div[class='ls-resource-doctypes ls-resource-text']")).size();
		    if(size1 == 0 )
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Grading policy file icon for a DW assignment is not displayed in student side.");
		    }
		    String name1 = driver.findElement(By.cssSelector("span[class='resource-title']")).getText();
		    if(!name1.contains(resourcesname))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Grading policy file link for a DW assignment is not displayed in student side.");
		    }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyGradingPolicyDropdown in class VerifyGradingPolicyDropdown.",e);
		}
	}

}
