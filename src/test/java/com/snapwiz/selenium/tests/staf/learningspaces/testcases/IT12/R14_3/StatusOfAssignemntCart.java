package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R14_3;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AddCart;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignLesson;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class StatusOfAssignemntCart extends Driver {
	@Test(priority=1,enabled=true)
	public void statusOfAssignemntCart()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1611");//login as student
			new LoginUsingLTI().ltiLogin("161");//login as instructor
     		new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().lessonOpen(0, 0);//open 1st chapter
			Thread.sleep(3000);
			new AddCart().widgetaddtocart();//add image widget to cart
			new Click().clickByclassname("assignment-cart-wrapper");//click on assignment cart
			Thread.sleep(2000);
			new AssignLesson().assigncartwithclasssection(1611);
			new LoginUsingLTI().ltiLogin("1611");//login as student
			new Navigator().NavigateTo("Assignments");
			//verufy not access"-" icon
			boolean notaccessd=driver.findElement(By.cssSelector("i[class='ls-la-assignment-status-icon ls-la-not-accessed']")).isDisplayed();
			if(notaccessd==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("not accessed icon not shown");
			}
			String status=new TextFetch().textfetchbyclass("ls-assignment-status");
			if(!status.contains("Not Started"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("assignemnt status is not 'Not Started' 1st time");
			}
			driver.findElement(By.cssSelector("div[class='ls-stream-learing-activity-level ls-assignment-learning-activity ellipsis assignment-cart-item']")).click();//click on assignment name
			Thread.sleep(2000);
			new Click().clickByclassname("widget-close");//close expand image widget
			boolean lesson1=new BooleanValue().booleanbyid("sec-intro");//verify lesson page
			Thread.sleep(2000);
			if(lesson1==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("after click on assignment its not goes to lesson page");
			}
			new Navigator().NavigateTo("Assignments");
			boolean accesssed=driver.findElement(By.cssSelector("i[class='ls-la-assignment-status-icon ls-la-accessed']")).isDisplayed();
			if(accesssed==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Accessed icon not shown");
			}
			String status1=new TextFetch().textfetchbyclass("ls-assignment-status");//fetch status of assignment after after accessed the assignemnt
			if(!status1.contains("Submitted"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("assignemnt status is not Submitted last time");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase StatusOfAssignemntCart in method statusOfAssignemntCart",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void CartIsAssignButStudentNotAccessed()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1661");//login as student
			new LoginUsingLTI().ltiLogin("166");//login as instructor
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().lessonOpen(0, 0);//open 1st chapter
			Thread.sleep(3000);
			new AddCart().widgetaddtocart();//add image widget to cart
			new Click().clickByclassname("assignment-cart-wrapper");//click on assignment cart
			Thread.sleep(2000);
			new AssignLesson().assigncartwithclasssection(1661);
			new LoginUsingLTI().ltiLogin("1661");//login as student
			new Navigator().NavigateTo("eTextbook");
            new TopicOpen().lessonOpen(0, 1);//open 2nd chapter
			new Navigator().NavigateTo("Assignments");
			String status1=new TextFetch().textfetchbyclass("ls-assignment-status");
			if(!status1.contains("Status: Not Started"))
			Assert.fail("Status of assignment not coming as 'Not Started'");			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase StatusOfAssignemntCart in method CartIsAssignButStudentNotAccessed",e);
		}
	}

}
