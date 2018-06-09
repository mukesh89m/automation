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

public class DeleteAndEditFunctionalaityOfYourQuestion extends Driver
{
	@Test
	public void deleteAndEditFunctionalaityOfYourQuestion()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("282");// login as instructor
			new Navigator().NavigateTo("My Question Bank");//click on my question bank
			new Click().clickByid("customAssignment");// click on create custom														// assignment
			Thread.sleep(2000);
			String searchtext=ReadTestData.readDataByTagName("tocdata", "searchtext", "1");
			new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
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
			int checkedquestion=driver.findElements(By.className("checked")).size();
			System.out.println(checkedquestion);
			new Click().clickBycssselector("span[title='Selected Questions (2) ']");
			driver.findElement(By.xpath("//*[@id='ls-ins-your-question-scoring-data']/input")).clear();
			driver.findElement(By.xpath("//*[@id='ls-ins-your-question-scoring-data']/input")).sendKeys("2");
			driver.findElement(By.xpath("//*[@id='ls-ins-your-question-delete-icon']/img")).click();
			boolean yourquestiontab=driver.findElement(By.cssSelector("span[title='Selected Questions (1) ']")).isDisplayed();
			System.out.println(yourquestiontab);
			new Click().clickBycssselector("span[title='Find Questions']");
			int checkedquestion1=driver.findElements(By.className("checked")).size();
			System.out.println(checkedquestion1);
			if(checkedquestion<=checkedquestion1)
			{
				Assert.fail("after deleteting from your Question question not unchecked in search result");
			}
			
		}
		catch (Exception e)
		{
			Assert.fail("Exception in testcase DeleteAndEditFunctionalaityOfYourQuestion in method deleteAndEditFunctionalaityOfYourQuestion ",e);
		}
	}

}
