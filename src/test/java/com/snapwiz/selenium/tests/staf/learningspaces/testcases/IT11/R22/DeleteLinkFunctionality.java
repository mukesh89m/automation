package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R22;

import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SearchQuestionInCustomCourseAssignemnt;

import java.util.ArrayList;
import java.util.List;

public class DeleteLinkFunctionality extends Driver
{
	@Test
	public void deleteLinkFunctionality()
	{
		try
		{
			MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
			new LoginUsingLTI().ltiLogin("339");// login as instructor
			new Navigator().NavigateTo("My Question Bank");//click on my question bank
			myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
			Thread.sleep(2000);
			String searchquestion=ReadTestData.readDataByTagName("tocdata", "searchtext", "1");
			new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
			Thread.sleep(2000);
			List<WebElement> allCheckbox =  driver.findElements(By.className("ls-ins-question-wrapper"));       // list all the question check box
			ArrayList<String> allCheckboxId = new ArrayList<>();
			for(WebElement checkbox: allCheckbox)
			{
				String checkBox = checkbox.getAttribute("questionid");
				allCheckboxId.add(checkBox);
			}
			driver.findElement(By.xpath("//label[@id='"+allCheckboxId.get(0)+"']")).click();
			driver.findElement(By.xpath("//label[@id='"+allCheckboxId.get(1)+"']")).click();
			String randomname=new RandomString().randomstring(2);
			String randomndescption=new RandomString().randomstring(2);
			new MouseHover().mouserhoverbyid("ls-ins-assignment-name");//mouse hover on edit name
			Thread.sleep(3000);
			driver.findElement(By.id("ls-ins-assignment-name")).click();
			new TextSend().textsendbyclasslist(randomname, "ls-ins-edit-assignment", 0);//edit name
			new MouseHover().mouserhoverbyid("ls-ins-assignment-desc");//mouse hover on description
			Thread.sleep(3000);
			driver.findElement(By.id("ls-ins-assignment-desc")).click();
			new TextSend().textsendbyclasslist(randomndescption, "ls-ins-edit-assignment",1);//edit description
			new Click().clickByclassname("save-for-later-text");
			Thread.sleep(2000);
			new Click().clickBycssselector("span[title='My Question Bank']");
			driver.findElement(By.className("delete-this")).click();
			String notification=new TextFetch().textfetchbyclass("notification-text-span");
			if(!notification.contains("Are you sure you want to delete this assignment?"))
			{
				Assert.fail("notification not shown when we click on delete this icon");
			}
			new Click().clickByclassname("ls-donot-delete-assignment");//click on 'no'
			boolean customassignment=new BooleanValue().booleanbyclass("resource-title");
			if(customassignment==false)
			{
				Assert.fail("custom assignment not shown after click on  no");
			}
            Thread.sleep(2000);
            driver.findElement(By.className("delete-this")).click();//click on delete this
			new Click().clickbyxpath("//img[@title='Close']");//click on cross icon
			boolean customassignment1=new BooleanValue().booleanbyclass("resource-title");
			if(customassignment1==false)
			{
				Assert.fail("custom assignment not shown after click on  cross");
			}
			Thread.sleep(2000);
			driver.findElement(By.className("delete-this")).click();//click on delete this
			driver.findElement(By.xpath("//span[@title='Yes']")).click();
			if(driver.findElements(By.className("no-data-notification-message-block-for-resources")).size() != 1)
			{
				Assert.fail("Custom assignment not getting deleted");
			}

		}
		catch (Exception e)
		{
			Assert.fail("Exception in testcase AvalabilityOfSaveAssignement in method avalabilityOfSaveAssignement ",e);
		}
	}

}
