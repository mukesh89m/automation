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
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

import java.util.ArrayList;
import java.util.List;

public class IndicationOfQuestionWhichalreadyPartOfCustomAssignment extends Driver
{
	@Test
	public void indicationOfQuestionWhichalreadyPartOfCustomAssignment()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("235");// login as instructor
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
			driver.findElement(By.xpath("//label[@id='"+allCheckboxId.get(0)+"']")).click();//checked check box
			driver.findElement(By.xpath("//label[@id='"+allCheckboxId.get(1)+"']")).click();//checked check box
			driver.findElement(By.cssSelector("input[class='ls-ins-search-question-content ls-ins-search-question-content-selected']")).clear();
			driver.findElement(By.cssSelector("input[class='ls-ins-search-question-content ls-ins-search-question-content-selected']")).sendKeys(searchtext);

			boolean checkedbox=new BooleanValue().booleanbylistclass("checked",0);
			if(checkedbox==false)
			{
				Assert.fail("previous question is not checked");
			}
			new Click().clickBycssselector("span[title='Selected Questions (2) ']");
			int questiononyourquestiontab=driver.findElements(By.id("ls-ins-your-question-scoring-data")).size();
			if(questiononyourquestiontab<2)
			{
				Assert.fail("cheked question not added in your question tab");
			}
			
			
			
		}
		catch (Exception e) {
			Assert.fail("Exception in testcase ExpandFunctionalityOfSearchedResult in method expandFunctionalityOfSearchedResult ",e);
		}
	}


	
}
