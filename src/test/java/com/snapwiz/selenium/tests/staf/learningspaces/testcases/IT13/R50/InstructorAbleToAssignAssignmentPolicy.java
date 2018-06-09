package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R50;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

public class InstructorAbleToAssignAssignmentPolicy extends Driver {
	
	
	@Test
	public void instructorAbleToAssignAssignmentPolicy()
	{
		try
		{
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "191");
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "191");

            new Assignment().create(191);
			new LoginUsingLTI().ltiLogin("191_2");//create a student
			new LoginUsingLTI().ltiLogin("191");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "191 Policy description text", "1",null, false, "1", "", null, "", "", "");
			new Navigator().NavigateTo("Question Banks"); 	//Navigate to resource

			//Adding assignment to search
			driver.findElement(By.id("all-resource-search-textarea")).clear();
			driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\""+assignmentname+"\"");
			driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
			new Click().clickByclassname("assign-this");
			//driver.findElement(By.cssSelector("span[class='assign-this resource-assign-this-image ls-assign-this-sprite']")).click();
            driver.findElement(By.className("ir-ls-assign-dialog-gradable-label-check")).click();	//click gradable check box
			Thread.sleep(2000);
			//testcase row no. 203
			String addPolicyIcon = driver.findElement(By.id("assignment-policy-icons")).getCssValue("height");
			if(!addPolicyIcon.contains("20px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Add policy icon(+) is not displayed beside the dropdown if we dont select any policy from the dropdown.");
			}
			new MouseHover().mouserhoverbyid("assignment-policy-icons");
			//testcase row no. 204
			String tooltip1 = driver.findElement(By.id("assignment-policy-icons")).getAttribute("title");
			if(!tooltip1.equals("Add assignment policy"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Hovering over the add policy icon The tooltip for this icon does not say \"Add assignment policy\".");
			}
			//testcase row no. 192
			List<WebElement> newField = driver.findElements(By.cssSelector("div[class='ir-ls-assign-dialog-assign-to-label ir-ls-assign-dialog-label']"));
			if(!newField.get(2).getText().contains("Assignment Policy:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to view a new field to assign an assignment policy.");
			}
			driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")).click();	//click on  Select an assignment policy dropdown
			Thread.sleep(2000);
			
			List<WebElement> allValues = driver.findElements(By.className("overview"));
            for(WebElement ele :allValues){
                System.out.println("ele :"+ele.getText());
            }
			//testcase row no. 196
			if(!allValues.get(9).getText().contains(assignmentpolicyname))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The dropdown doesnt show the list of all assignment policies.");
			}
			driver.findElement(By.linkText(assignmentpolicyname)).click();		//select assignment policy //testcase row no. 197
			Thread.sleep(2000);
			new Navigator().NavigateTo("Assignments"); 	//Navigate to Assignments
			new Assignment().assignToStudent(191);	//assign it to class section
			new Navigator().NavigateTo("Summary"); 	//Navigate to Assignments
			driver.findElement(By.className("assign-more")).click(); //Click on update assignment
			Thread.sleep(2000);
            driver.findElement(By.className("assignment-update-reassign")).click();
		    //testcase row no. 192
		    WebElement newField1 = driver.findElement(By.cssSelector("div[class='ir-ls-assignment-policy-wrapper ir-ls-assignment-policy-content-item']"));
			if(!newField1.getText().contains("Assignment Policy:"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor is not able to view a new field to assign an assignment policy.");
			}

			 //testcase row no. 199
			String viewPolicyIcon = driver.findElement(By.id("assignment-policy-icons")).getCssValue("height");
			if(!viewPolicyIcon.contains("20px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("View policy icon is not displayed beside the dropdown.");
			}
			 //testcase row no. 200
			new MouseHover().mouserhoverbyid("assignment-policy-icons");
			String tooltip = driver.findElement(By.id("assignment-policy-icons")).getAttribute("title");
			if(!tooltip.equals("View assignment policy"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Hovering over the view policy icon The tooltip for this icon does not say \"View assignment policy\".");
			}
			new Click().clickByclassname("assignment-policy-view-icon"); //click on assignment policy icons
			//testcase row no. 201
			int popup = driver.findElements(By.id("html-dialog-pop-up")).size();
			if(popup == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking over the assignment policy icon the policy opo-up doesnot open.");
			}
			new Click().clickByid("dialog-close"); //click on assignment policy icons

            //commented the code as per bug id 8198
            //Only the owner instructor of the assignment has update assignment link.

			/*new LoginUsingLTI().ltiLogin("191_1");//login as another instructor
			new Navigator().NavigateTo("Summary"); 	//Navigate to resource
			//TC row no. 205
            driver.findElement(By.className("assign-more")).click();//click on update
			Thread.sleep(2000);
			new Click().clickByid("assignment-policy-icons"); //click on + icon to add policy

			new MouseHover().mouserhoverbyid("ls-ins-assignment-policy-name");//mouse hover the name field
			driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon")).click();	//click on edit icon
		    Thread.sleep(2000);
		    driver.switchTo().activeElement().sendKeys(assignmentpolicyname1);
		    driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
		    Thread.sleep(2000);
		    new Click().clickByclassname("ls-save-policy-btn");		//click on Save Policy
		    Thread.sleep(2000);

		    new Navigator().NavigateTo("Summary"); 	//Navigate to Resources
            driver.findElement(By.className("assign-more")).click();//click on update
			Thread.sleep(2000);
		    driver.findElement(By.linkText("Select an assignment policy")).click();	//click on  Select an assignment policy dropdown
			Thread.sleep(2000);
			driver.findElement(By.linkText(assignmentpolicyname1)).click();		//select assignment policy //testcase row no. 207
			Thread.sleep(2000);
			new LoginUsingLTI().ltiLogin("191_2");//login as student
			new Navigator().NavigateTo("Assignments");//navigate to Assignments
			driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();	//click on assignment
			Thread.sleep(10000);
			new SelectAnswerAndSubmit().staticAssignment("A");
			new LoginUsingLTI().ltiLogin("191");//login as instructor
			new Navigator().NavigateTo("Summary"); 	//Navigate to Resources
            driver.findElement(By.className("ls-grade-book-assessment")).click();//click on view student responses
			Thread.sleep(2000);
            driver.findElement(By.cssSelector("div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-release']")).click(); //click on Release Grade for all
            Thread.sleep(2000);*/
			/*List<WebElement> isDisabled = driver.findElements(By.cssSelector("div[class='sbHolder sbHolderDisabled']"));
			//Tc Row no. 193
			if(!isDisabled.get(4).getText().contains(assignmentpolicyname1))//disabled element wiil be at 4th index
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Choose assignment policy fieldis not disabled and not changeable if students have already attempted the assignment.");
			}*/
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase instructorAbleToAssignAssignmentPolicy in class InstructorAbleToAssignAssignmentPolicy.",e);
		}
	}

}
