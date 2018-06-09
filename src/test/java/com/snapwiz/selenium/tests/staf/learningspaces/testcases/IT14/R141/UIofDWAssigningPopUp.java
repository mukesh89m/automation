package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class UIofDWAssigningPopUp extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void UIOfDWAssigningPopUp()
	{
		try
		{
			String classSectionName = ReadTestData.readDataByTagName("", "context_title", "81");
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "81");
			String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", "81");
			String shareName = ReadTestData.readDataByTagName("", "shareName", "81");
			String shareWithInitialString1 = ReadTestData.readDataByTagName("", "shareWithInitialString", "81_1");
			String shareWithClass1 = ReadTestData.readDataByTagName("", "shareWithClass", "81_1");
			String shareName1 = ReadTestData.readDataByTagName("", "shareName", "81_1");
            String y[]=shareName1.split(" ");
            shareName1 = y[1] + ", " + y[0];//reverse the name with comma in between
			new LoginUsingLTI().ltiLogin("81_1");		//create a student
			new LoginUsingLTI().ltiLogin("81");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			//open Lesson With Discussion Widget
            new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.assign-this-text")));	//click on Assign This link
			Thread.sleep(3000);
			//TC row no. 81
			String assignThis = driver.findElement(By.cssSelector("span.assign-this-text")).getText();
			if(!assignThis.equals("Assign this"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On hovering over DW \"Assign This\" link is not available.");
			}
			//TC row no. 82
			String addToCart = driver.findElement(By.cssSelector("span.assign-this-text")).getText();
			if(addToCart.contains("Add to cart"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On hovering over DW \"Add To Cart\" link is available.");
			}
			
			//TC row no. 83
			String DWName = new TextFetch().textfetchbyclass("ir-ls-assign-dialog-header");
			if(DWName == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Name of DW assignemnt is not shown on clicking \"Assign This\" link for DW.");
			}
			String assgnToField =driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-assign-to-wrapper ir-ls-assign-dialog-content-item']")).getText();
			if(!assgnToField.contains("Assign To:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Assgn To Field is not shown on clicking \"Assign This\" link for DW.");
			}
			
			String shortLabelField =driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-short-label-wrapper ir-ls-assign-dialog-content-item']")).getText();

			String gradableCheckbox =driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-wrapper ir-ls-assign-dialog-content-item']")).getText();
			if(!gradableCheckbox.contains("Gradable:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Gradable checkbox is not shown on clicking \"Assign This\" link for DW.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-dw-gradable-label-check ")));	//check gradable chekbox
			Thread.sleep(3000);
			//TC row no. 84
			String gradingPolicy =driver.findElement(By.cssSelector("div[class='ir-ls-grading-policy-wrapper ir-ls-grading-policy-content-item']")).getText();
			if(!gradingPolicy.contains("Assignment Reference:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Grading policy dropdown is not shown on clicking \"Assign This\" link for DW.");
			}
			
			String gradingPolicyDesc =driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-content-item ir-ls-assign-dialog-assign-policy-desc-wrap']")).getText();
			if(!gradingPolicyDesc.contains("Assignment Reference Description:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Grading Policy Description is not shown on clicking \"Assign This\" link for DW.");
			}
			
			String totalPoints =driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-tpoints-label-wrapper ir-ls-assign-dialog-content-item']")).getText();
			if(!totalPoints.contains("Total Points:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Total Points: is not shown on clicking \"Assign This\" link for DW.");
			}
			
			String accessibleAfter =driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-accessible-after-wrapper ir-ls-assign-dialog-content-item']")).getText();
			if(!accessibleAfter.contains("Accessible After:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Accessible After is not shown on clicking \"Assign This\" link for DW.");
			}
			
			String dueDate =driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-due-date-wrapper ir-ls-assign-dialog-content-item']")).getText();
			if(!dueDate.contains("Due Date:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Due Date is not shown on clicking \"Assign This\" link for DW.");
			}
			
			String description =driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-additional-notes-wrapper ir-ls-assign-dialog-content-item']")).getText();
			if(!description.contains("Description:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Description is not shown on clicking \"Assign This\" link for DW.");
			}
			
			String assignButton =driver.findElement(By.cssSelector("div[class='submit-assignment-content']")).getText();
			if(!assignButton.contains("Assign"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Assign button is not shown on clicking \"Assign This\" link for DW.");
			}
			
			String cancelLink =driver.findElement(By.cssSelector("div[class='cancel-content']")).getText();
			if(!cancelLink.contains("Cancel"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Cancel link is not shown on clicking \"Assign This\" link for DW.");
			}
			//TC row no. 85
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-dw-gradable-label-check  selected']")));	//uncheck gradable chekbox
			Thread.sleep(3000);
			boolean gradingPolicyIsDisplayed = driver.findElement(By.cssSelector("div[class='ir-ls-grading-policy-wrapper ir-ls-grading-policy-content-item']")).isDisplayed();
			if(gradingPolicyIsDisplayed == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On deselcting Gradable checkbox \"Grading Policy\" option is still displayed.");
			}
			boolean gradingPolicyDescIsDisplayed = driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-content-item ir-ls-assign-dialog-assign-policy-desc-wrap']")).isDisplayed();
			if(gradingPolicyDescIsDisplayed == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On deselcting Gradable checkbox \"Grading Policy Description\" option is still displayed.");
			}
			boolean totalPointIsDisplayed = driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-content-item ir-ls-assign-dialog-assign-policy-desc-wrap']")).isDisplayed();
			if(totalPointIsDisplayed == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On deselcting Gradable checkbox \"Total Point\" option is still displayed.");
			}
			String DWtext = driver.findElement(By.className("tab-content-data")).getText();
			String assignmentName = driver.findElement(By.className("ir-ls-assign-dialog-header")).getText();
			//TC row no. 86
			if(!DWtext.contains(assignmentName))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Assignment field name doesnt display DW name by default.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-this-edit-link")));   //click on edit icon
			Thread.sleep(2000);
			driver.findElement(By.className("ir-ls-assign-this-header-edit-text")).clear();
			driver.findElement(By.className("ir-ls-assign-this-header-edit-text")).sendKeys("New Assignment Name");
			driver.findElement(By.id("ir-ls-assign-dialog")).click();	//click outside
			Thread.sleep(2000);
			//TC row no. 88
			String newName = new TextFetch().textfetchbyclass("ir-ls-assign-dialog-header");
			if(!newName.equals("New Assignment Name"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is unable to edit the DW Assignment Name");
			}
			String classSectionName1 = driver.findElement(By.className("item-text")).getText();
			System.out.println(classSectionName1);
			//TC row no. 90
			if(!classSectionName.contains(classSectionName1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("By default Class section name is not present in Assign To Field.");
			}
			//TC row no. 91
			driver.findElement(By.className("closebutton")).click();	//remove the class section name from assign To field
			Thread.sleep(2000);
			boolean sharefound = false;
			driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			Thread.sleep(3000);
			List<WebElement> suggestname;
			if(shareWithClass.toUpperCase().equals("TRUE"))
				 suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'cls_')]"));
			else
				 suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
			   for (WebElement answerchoice: suggestname)
			   {
			    if(answerchoice.getText().trim().equals(shareName))
			    {
			    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice);
			        answerchoice.click();	
			        sharefound = true;
			        break;
			    }
			   }
			 //TC row no. 92
			if(sharefound == false)
				Assert.fail("Instructor is unable to re select class section name");
			
			driver.findElement(By.className("closebutton")).click();	//remove the class section name from assign To field
			Thread.sleep(2000);
			boolean sharefound1 = false;
			driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString1);
			Thread.sleep(3000);
            shareName1 = shareName1 + " - " + classSectionName;
			List<WebElement> suggestname1;
			if(shareWithClass1.toUpperCase().equals("TRUE"))
				 suggestname1 = driver.findElements(By.xpath("//*[starts-with(@rel, 'cls_')]"));
			else
				 suggestname1 = driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
			   for (WebElement answerchoice: suggestname1)
			   {
			    if(answerchoice.getText().trim().equals(shareName1))
			    {
			    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice);
			        answerchoice.click();	
			        sharefound1 = true;
			        break;
			    }
			   }
			//TC row no. 93
			if(sharefound1 == false)
				Assert.fail("Instructor is unable to select a student name in Assign To field.");
			
			Thread.sleep(2000);
			driver.findElement(By.id("ir-ls-assign-dialog")).click();	//click outside
			Thread.sleep(2000);
			//TC row no. 95
			String str = driver.findElement(By.className("input-filed")).getAttribute("value");
			if(!str.equals("abcd"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is able to fill in text more than 4 characters in Short Label field.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-dw-gradable-label-check ")));	//check gradable chekbox
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("total-points")));	//click on tptla points field
			Thread.sleep(2000);
			driver.findElement(By.id("total-points")).sendKeys("23");
			driver.findElement(By.id("ir-ls-assign-dialog")).click();	//click outside
			Thread.sleep(2000);
			//TC row no. 107
			String totalPoints1 = driver.findElement(By.id("total-points")).getAttribute("value");
			if(!totalPoints1.equals("23"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor unable to fill in points in the field.");
			}
			//TC row no. 111
			String accessibleAfterDate = driver.findElement(By.id("accessible-date")).getAttribute("value");
			if(accessibleAfterDate.length() == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Accessible after date Field is not pre populated with current date.");
			}
			String accessibleAfterTime = driver.findElement(By.id("accessible-time")).getAttribute("value");
			if(accessibleAfterTime.length() == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Accessible after date Field is not pre populated with current time.");
			}
			//TC row no 112


            driver.findElement(By.id("accessible-date")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("4")).click();
			Thread.sleep(2000);
			
			//TC row no. 114
			String dueDateBlank = driver.findElement(By.id("due-date")).getAttribute("value");
			if(dueDateBlank.length() != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Due date Field is not blank by default for DW assignment.");
			}
			String dueTimeBlank = driver.findElement(By.id("due-time")).getAttribute("value");
			if(dueTimeBlank.length() != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Due date Field is not blank by deafult.");
			}
			//TC row no 115
			driver.findElement(By.id("due-time")).click();
			List<WebElement> elements1 = driver.findElements(By.xpath("//li"));
			for(WebElement time : elements1)
			{
				if(time.getText().equals("12:00 AM"))
				{
					time.click();
					break;
				}
			}
			driver.findElement(By.id("due-date")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("28")).click();
			Thread.sleep(2000);

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase UIOfDWAssigningPopUp in class UIofDWAssigningPopUp.",e);
		}	
	}
	
	@Test(priority = 2, enabled = true)
	public void checkingTheMandatoryFields()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("89");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.assign-this-text")));	//click on Assign This link
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-this-edit-link")));   //click on edit icon
			Thread.sleep(2000);
			driver.findElement(By.className("ir-ls-assign-this-header-edit-text")).clear();
			driver.findElement(By.className("ir-ls-assign-this-header-edit-text")).sendKeys(" ");
			driver.findElement(By.id("ir-ls-assign-dialog")).click();	//click outside
			Thread.sleep(2000);
			//TC row no. 89
			String isAssignmentNameMandatory = driver.findElement(By.className("ir-ls-assign-this-header-edit-text")).getAttribute("style");
			if(!isAssignmentNameMandatory.contains("red"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Assignment name is not madatory for a DW assignment.");
			}
			driver.findElement(By.className("ir-ls-assign-this-header-edit-text")).sendKeys("New Assignment Name");
			Thread.sleep(2000);
			driver.findElement(By.className("input-filed")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("ir-ls-assign-dialog")).click();	//click outside
			Thread.sleep(2000);
			//TC row no. 96

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ir-ls-assign-dialog-dw-gradable-label-check ")));	//check gradable chekbox
			Thread.sleep(3000);
			driver.findElement(By.id("due-time")).click();
			driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();

			/*List<WebElement> elements1 = driver.findElements(By.xpath("//li"));
			for(WebElement time : elements1)
			{
				if(time.getText().equals("12:00 AM"))
				{
					time.click();
					break;
				}
			}*/
			driver.findElement(By.id("due-date")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("28")).click();
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")));	//click on Assign
			Thread.sleep(3000);
			String isTotalPointsMandatory = driver.findElement(By.id("total-points")).getAttribute("style");
			//TC row no. 108
			if(!isTotalPointsMandatory.contains("width: 35px;"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Total point field is not madatory for a DW assignment.");
			}
			driver.findElement(By.id("total-points")).sendKeys("23");//give total points
			
			driver.findElement(By.id("due-date")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-w']")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("1")).click();//select a due date before accessible date
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")));    //check gradable chekbox
			Thread.sleep(5000);
			//TC row no. 117
		/*	String error_message = driver.findElement(By.className("error-message-assign")).getText();
			Assert.assertEquals("Accessible after date must be before the due date.",error_message);*/
			//select a proper due date
			driver.findElement(By.id("due-date")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("28")).click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("assign-cancel"))); 		//click on cancel
			Thread.sleep(2000);
			//TC row no. 120
			int assignThisPopUp = driver.findElements(By.id("ir-ls-assign-dialog")).size();
			System.out.println("assignThisPopUp: "+assignThisPopUp);
			if(assignThisPopUp != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking on Cancel link on Assigning a DW assignment the pop-up doesnt disappear.");
			}
			new Navigator().NavigateTo("Assignments");
			String assignmentNotPresentMsg = driver.findElement(By.className("ls-assignment-not-available")).getText();
			//Tc row no. 120 and 121
			Assert.assertEquals("No Assignment exists.  + New Assignment",assignmentNotPresentMsg);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase checkingTheMandatoryFields in class UIofDWAssigningPopUp.",e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void assignToFieldAndDueDateFields()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("94");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.assign-this-text")));	//click on Assign This link
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")));    //check gradable chekbox
			Thread.sleep(2000);
			//TC row no. 116
			int size = driver.findElements(By.cssSelector("input[class='input-filed hasDatepicker invalid-value']")).size();
			System.out.println("size :"+size);
			if( size != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is allowed to proceed with empty due date/time for assigning a DW assignment.");
			}
			//select due date and time
			driver.findElement(By.id("due-time")).click();
			driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();

			/*List<WebElement> elements1 = driver.findElements(By.xpath("//li"));
			for(WebElement time : elements1)
			{
				if(time.getText().equals("12:00 AM"))
				{
					time.click();
					break;
				}
			}*/
			driver.findElement(By.id("due-date")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("28")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("closebutton")).click();	//remove the class section name from assign To field
            driver.findElement(By.className("closebutton")).click();	//remove the class section name from assign To field
            driver.findElement(By.className("closebutton")).click();	//remove the class section name from assign To field
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click(); 		//click on Assign button
			Thread.sleep(2000);
			//TC row no. 94
			String blankAssignToField = driver.findElement(By.xpath("//ul[contains(@class,'holder')]")).getAttribute("style");
			if(!blankAssignToField.contains(""))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is allowed to proceed with empty Assign To Field for assigning a DW assignment.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase assignToFieldAndDueDateFields in class UIofDWAssigningPopUp.",e);
		}
	}

}
