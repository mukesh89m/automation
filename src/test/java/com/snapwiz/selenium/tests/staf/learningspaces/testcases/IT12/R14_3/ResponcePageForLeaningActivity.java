package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R14_3;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AddCart;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignLesson;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Filter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class ResponcePageForLeaningActivity extends Driver
{
	@Test
	public void responcePageForLeaningActivity()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1701");//login as student
			new LoginUsingLTI().ltiLogin("170");
			new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
			new TopicOpen().lessonOpen(0, 0);//open 1st chapter
			Thread.sleep(3000);
			new AddCart().widgetaddtocart();//add image widget to cart
			new Click().clickByclassname("assignment-cart-wrapper");
			new AssignLesson().assigncartwithclasssection(1701);
			new Navigator().NavigateTo("Assignments");
			Thread.sleep(3000);
			new Filter().filterApply("All Activity", "Learning Activity");
			new Click().clickbylist("ls-grade-book-assessment", 0);//Click on view response
			Thread.sleep(3000);
			int descrption=driver.findElements(By.className("ls-assignment-grading-title")).size();
			boolean descriptionvalue=new BooleanValue().booleanbylistclass("ls-assignment-grading-title", descrption-1);
			if(descriptionvalue==false)
			{
				Assert.fail("descrption not shown");
			}
			String tabname=driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!tabname.contains("Response"))
			{
				Assert.fail("tab name is not changed");
			}		
			boolean status=new BooleanValue().booleanbylistclass("ls-assignment-status", descrption-1);
			if(status==false)
			{
				Assert.fail("status not shown on response page");
			}
			boolean duedate=new BooleanValue().booleanbylistclass("ls-assignment-date-block", descrption-1);
			if(duedate==false)
			{
				Assert.fail("duedate not shown on response page");
			}

			boolean accessafter=new BooleanValue().booleanbylistclass("ls-assignment-accessible-after-title", descrption-1);
			if(accessafter==false)
			{
				Assert.fail("accessafter not shown on response page");
			}
			boolean statusbox=new BooleanValue().booleanbylistclass("ls-instructor-activity-cards-holder", descrption-1);
			if(statusbox==false)
			{
				Assert.fail("statusbox not shown on response page");
			}
			boolean studentname=new BooleanValue().booleanbyclass("idb-gradebook-assignment-username");
			if(studentname==false)
			{
				Assert.fail("student name not shown");
			}
			boolean percent=driver.findElement(By.cssSelector("div[class='idb-gradebook-content-coloumn-complete idb-gradebook-content-perc-complete']")).isDisplayed();
			if(percent==false)
			{
				Assert.fail("percent of student not shown");				
			}
			new MouseHover().mouserhover("idb-question-count-wrapper");//mouse hover on learning activity1
			driver.findElement(By.cssSelector("img[src='/webresources/images/al/check-box.png']")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("div[class='ls-post-a-message ls-enable-post-a-message']")).click();//click on  post message
			String message = new RandomString().randomstring(10);
			driver.findElement(By.className("email-message-content")).sendKeys(message);//send string to textarea
			new Click().clickByid("send-mail");//click on post buttom

			new LoginUsingLTI().ltiLogin("1701");
			new Navigator().NavigateTo("Course Stream");
            if(!driver.findElement(By.className("ls-link-span")).getText().contains(message))
                Assert.fail("Message posted by instructor to student from view responses page is not visible to student on the Course Stream");

            if(!driver.findElement(By.className("ls-stream-post__action")).getText().equals("posted a message"))
                Assert.fail("Text 'posted a message' not present in studetn Course Stream for message posted by instructor to student from view responses page");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase ResponcePageForLeaningActivity in method responcePageForLeaningActivity ",e);
		}
	}


}
