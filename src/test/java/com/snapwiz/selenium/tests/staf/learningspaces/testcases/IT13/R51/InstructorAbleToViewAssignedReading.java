package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R51;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AddCart;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignLesson;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class InstructorAbleToViewAssignedReading extends Driver
{
	@Test
	public void instructorAbleToViewAssignedReading()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("441");//login as student
			new LoginUsingLTI().ltiLogin("44");//login as instructor
			new Navigator().NavigateTo("Learning Content");//navigate to learning content
			new TopicOpen().lessonOpen(0, 0);//open topic
			Thread.sleep(3000);
			new AddCart().widgetaddtocart();//add image widget to cart
			new Click().clickByclassname("assignment-cart-wrapper");//click on assignment cart
			new AssignLesson().assigncart(441);
			new Navigator().NavigateTo("Engagement Report");//4-navigate to engaged report
			//44
			int quizzedgraph=driver.findElements(By.cssSelector("div[class='assigned-reading-completed-chart no-report-grap-image']")).size();
			if(quizzedgraph<1)
			{
				Assert.fail("graph will apper even student not start the resourcest");
			}
			new LoginUsingLTI().ltiLogin("441");//login as student
			new Navigator().NavigateTo("Assignments");
			new LoginUsingLTI().ltiLogin("44");//login as instructor
			new Navigator().NavigateTo("Engagement Report");//navigate to engagement report
			//50
			int quizzedgraph1=driver.findElements(By.cssSelector("div[class='assigned-reading-completed-chart no-report-grap-image']")).size();
			if(quizzedgraph1<1)
			{
				Assert.fail("graph will apper even student not start the resourcest");
			}
			new LoginUsingLTI().ltiLogin("441");//login as student
			new Navigator().NavigateTo("Assignments");//navigate to assignment

			new Click().clickbylist("learning-activity-title", 0);

			new LoginUsingLTI().ltiLogin("44");//login as instructor
			new Navigator().NavigateTo("Engagement Report");
			//48,51,53,54
			String assignedreading=new TextFetch().textfetchbyclass("assigned-reading-progress");
			if(!assignedreading.contains("100"))
			{
				Assert.fail("after submit ths assignment graph not shown");
			}
			//45
			if(!assignedreading.contains("Assigned Learning Activities Completed"))
			{
				Assert.fail("The title of assigned reading is not Assigned Reading Completed");
			}
			//46
			int numberofstudent=driver.findElements(By.className("students-performance-report-content-rows")).size();
			if(numberofstudent!=1)
			{
				Assert.fail("more than one student shown in report.");
			}
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in test case InstructorAbleToViewAssignedReading in method instructorAbleToViewAssignedReading",e);
		}
	}
}
