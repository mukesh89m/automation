package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R22;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignLesson;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SearchQuestionInCustomCourseAssignemnt;

public class AvalabilityOfSaveAssignement extends Driver
{
	@Test
	public void availabilityOfSaveAssignment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("3151");// login as student
			new LoginUsingLTI().ltiLogin("315");// login as instructor
			new Navigator().NavigateTo("Question Banks");// Navigate to resources
			new Click().clickByid("customAssignment");// click on create custom														// assignment
			String searchquestion=ReadTestData.readDataByTagName("tocdata", "searchtext", "1");
			new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("315");
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
            Thread.sleep(5000);
			new Click().clickBycssselector("span[title='My Question Bank']");
            new UIElement().waitAndFindElement(By.className("resource-title"));
			String assignmentnameinmyresources=new TextFetch().textfetchbyclass("resource-title");
			if(!assignmentnameinmyresources.contains(randomname))
			{
				Assert.fail("assignemt name not shown on my resources");
			}
			boolean createcustumassignemnt=new BooleanValue().booleanbyid("customAssignment");
			if(createcustumassignemnt==true)
			{
				Assert.fail("create custom assignment shown in my resources tab");
			}
			new AssignLesson().Assigncustomeassignemnt(3151);
			new Navigator().NavigateTo("My Question Bank");// Navigate to resources
			String updatedlink=driver.findElement(By.cssSelector("span[class='assign-this']")).getText();
			System.out.println(updatedlink);
			if(!updatedlink.contains("Assign This"))
			{
				Assert.fail("assign this link not change to update assigment");
			}
			new LoginUsingLTI().ltiLogin("3151");// login as student
			new Navigator().NavigateTo("Assignments");//navigate to assignment
			new Click().clickBycssselector("span[class='ls-assignment-name instructor-assessment-review']");
			new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title ")));
			new Click().clickBycssselector("a[class='btn btn--primary btn--large btn--next long-text-button']");//click on next button
			
		}
		catch (Exception e)
		{
			Assert.fail("Exception in testcase AvalabilityOfSaveAssignement in method availabilityOfSaveAssignment ",e);
		}
	}

}
