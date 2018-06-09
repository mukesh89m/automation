package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R22;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SearchQuestionInCustomCourseAssignemnt;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

import java.util.ArrayList;
import java.util.List;

public class InstructorAbleToAddQuestionsInMyAssignemnt extends Driver
{
	@Test
	public void instructorAbleToAddQuestionsInMyAssignemnt()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("214");// login as instructor
			new Navigator().NavigateTo("My Question Bank");//click on my question bank
			new Click().clickByid("customAssignment");// click on create custom													
			Thread.sleep(2000);
			String searchtext=ReadTestData.readDataByTagName("tocdata", "searchtext", "1");
			new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
			Thread.sleep(4000);

			List<WebElement> allCheckbox =  driver.findElements(By.className("ls-ins-question-wrapper"));       // list all the question check box
			ArrayList<String> allCheckboxId = new ArrayList<>();
			for(WebElement checkbox: allCheckbox)
			{
				String checkBox = checkbox.getAttribute("questionid");
				allCheckboxId.add(checkBox);
			}
			driver.findElement(By.xpath("//label[@id='"+allCheckboxId.get(0)+"']")).click();

			String questioncount=driver.findElement(By.cssSelector("div[data-id='your-question']")).getText();//fetch text of my question tab
			if(!questioncount.contains("1"))
			{
				Assert.fail("number not incerase after checked the check box");
			}
			driver.findElement(By.xpath("//label[@id='"+allCheckboxId.get(1)+"']")).click(); //click on the second checkbox
			String questioncount1=driver.findElement(By.cssSelector("div[data-id='your-question']")).getText();//fetch text of my question tab
			if(!questioncount1.contains("2"))
			{
				Assert.fail("number not incerase after checked the check box 2");
			}
		}
		catch (Exception e)
		{
			Assert.fail("Exception in testcase InstructorAbleToAddQuestionsInMyAssignemnt in method instructorAbleToAddQuestionsInMyAssignemnt ",e);
		}
	}


}

