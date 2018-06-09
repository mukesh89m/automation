package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R14_3;

import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

import java.util.List;

public class AssignmentCartAssigned extends Driver
{
	@Test(priority=1,enabled=true)
	public void assignmentcartassigned()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("127");
			new TOCShow().chaptertree();
			new TopicOpen().lessonOpen(0, 0);//open 1st chapter
			Thread.sleep(3000);
			new AddCart().widgetaddtocart();//add image widget to cart
			new Click().clickByclassname("assignment-cart-wrapper");
			new AssignLesson().assigncart(127);
			new Navigator().NavigateTo("Assignments");
			new Filter().filterApply("All Activity", "Learning Activity");
			String assignmentname=new TextFetch().textfetchbyclass("ls-assignment-name-block");
			if(!assignmentname.contains("New Assignment"))
			{
				Assert.fail("assignment name not shown");
			}
			new AssignmentSocialElement().clickonlikecoursestream(0);//click on  like
			new AssignmentSocialElement().clickoncommnetcoursestream(0);//comment on assignment
			new Click().clickbylist("ls-grade-book-assessment", 0);//Click on view response
			Thread.sleep(2000);
			String responsepage=new TextFetch().textfetchbyclass("idb-gradebook-header-div");//fetch response page text
			if(!responsepage.contains("Assignment Responses"))
			{
				Assert.fail("After click on view response its does not goes to response page");
			}
			new Click().clickByclassname("tab");//Click on assignment tab
			Thread.sleep(2000);
			int assignmentleft=driver.findElements(By.className("ls-assignment-item-left")).size();//fetch total number of assignment in assignment page
			new Click().clickbylist("delete-assigned-task", 0);//Click on delete
            new Click().clickBycssselector("div[class='as-modal-yes-btn delete-button']"); //click on Yes button of delete assignment alert shown after clicking on delete button
			int assignmentleftafterdelete=driver.findElements(By.className("ls-assignment-item-left")).size();//fetch assignment after deletion
			if(assignmentleftafterdelete==assignmentleft)
			{
				Assert.fail("Assignment not deleted after click on delete ");
			}
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase AssignmentCartAssigned in method assignmentcartassigned ",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void updateassignment()
	{
		try
		{
            String shareName = ReadTestData.readDataByTagName("", "shareName", "1381");
            String y[]=shareName.split(" ");
            shareName = y[1] + ", " + y[0];//reverse the name with comma in between
            new LoginUsingLTI().ltiLogin("1381");//login as student
			new LoginUsingLTI().ltiLogin("138");//login as instructor
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().lessonOpen(0, 0);//open 1st chapter
			Thread.sleep(3000);
			new AddCart().widgetaddtocart();//add image widget to cart
			new Click().clickByclassname("assignment-cart-wrapper");//click on assignment cart
			new AssignLesson().assigncart(138);
			new Navigator().NavigateTo("Summary");
			new Filter().filterApply("All Activity", "Learning Activity");
			new Click().clickbylist("assign-more", 0);//click on update assignment
            boolean sharefound = false;
            driver.findElement(By.className("maininput")).click();
            Thread.sleep(2000);
            driver.findElement(By.className("maininput")).sendKeys("1381");
            Thread.sleep(3000);
            List<WebElement> suggestname;
                     suggestname = driver.findElements(By.xpath("/*//*[starts-with(@rel, 'uid_')]"));
            for (WebElement answerchoice: suggestname)
            {
                System.out.println("Names "+answerchoice.getText());
                if(answerchoice.getText().trim().equals(shareName))
                {
                    System.out.println("Inside");
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice);
                    answerchoice.click();
                    sharefound = true;
                    break;
                }
            }
            if(sharefound == false)
                Assert.fail("No value selected from the Assign To field");

            driver.findElement(By.id("due-time")).click();//click on dur time
            Thread.sleep(2000);
            List<WebElement> elements = driver.findElements(By.xpath("//li"));//fetch all time section
            for(WebElement time : elements)
            {
                if(time.getText().equals("12:05 AM"))
                {
                    time.click();
                    break;
                }
            }
            Thread.sleep(2000);
            driver.findElement(By.id("due-date")).click();//click on due date
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("26")).click();//click on specified date
            Thread.sleep(2000);
            driver.findElement(By.id("additional-notes")).clear();
            driver.findElement(By.id("additional-notes")).sendKeys("this is new  additional note");//add additional note
            driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
            Thread.sleep(5000);

			//new AssignLesson().assigncartwithclasssectionwithoutshorandwithdefaultclass(1381);//assign assignment to student
			new Filter().filterApply("All Activity", "Learning Activity");
			new Click().clickbylist("assign-more", 0);//click on update assignment link
			Thread.sleep(2000);
			int cancleclassection=driver.findElements(By.className("closebutton")).size();//verify close button of default class section name
			if(cancleclassection!=0)
			{
				Assert.fail("class section name is edittable");
			}
			Thread.sleep(2000);
			new Click().clickByid("assign-cancel");//click on cancel link
			Thread.sleep(2000);
			int assignpopup=driver.findElements(By.id("ir-ls-assign-dialog")).size();//verify after cancle the popup, popup,close or not
			if(assignpopup!=0)
			{
				Assert.fail("after click on cancle link assign pop up not closed");
			}			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase AssignmentCartAssigned in method updateassignment",e);
		}
	}
	
	@Test(priority=3,enabled=true)
	public void AssignAssignmentAtCourseStreaam()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("150");//login as instructor
			new TOCShow().chaptertree();
			new TopicOpen().chapterOpen(0);
			new SelectCourse().selectInvisibleAssignment("Introduction");
			//new TopicOpen().lessonOpen(0, 0);//open 1st chapter
			Thread.sleep(3000);
			new AddCart().widgetaddtocart();//add image widget to cart
			new Click().clickByclassname("assignment-cart-wrapper");//click on assignment cart
			new AssignLesson().assigncart(150);
			Thread.sleep(6000);
			new SelectCourse().selectInvisibleAssignment("Introduction");
			//new TopicOpen().lessonOpen(0, 0);//open 1st chapter
			Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab on right tab
			Thread.sleep(3000);
			new MouseHover().mouserhover("assignment-content-posts-list");
			new Click().clickByclassname("folder-cycle");//Click on open 
			Thread.sleep(2000);
			new Click().clickbylist("close_tab",1);
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();
			Thread.sleep(3000);
			new MouseHover().mouserhover("assignment-content-posts-list");
            Thread.sleep(5000);
            new Click().clickByclassname("folder-cycle");//Click on open
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("div[class='ls-stream-learing-activity-level ls-assignment-learning-activity ellipsis']")).click();//click on assignment name
			Thread.sleep(2000);
			boolean lesson=new BooleanValue().booleanbyid("sec-intro");//verify lesson page
			if(lesson==false)
			{
				Assert.fail("after click on learning activity its not goes to lesson page");
			}
			Thread.sleep(4000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='toggle-widget-size toggle-widget-size-collapse']"))); //collapsing the expanded widget
            //after opening it from Course Stream
			new Navigator().NavigateTo("Course Stream");
			Thread.sleep(2000);
			String coursestreamtext=new TextFetch().textfetchbylistclass("ls-stream-post__body", 0);//fetch string from course stream
			if(!coursestreamtext.contains("New Assignment"))
			{
				Assert.fail("assignment name not present");
			}
			if(!coursestreamtext.contains("L1"))
			{
				Assert.fail("lerning activity number not present");
			}
			driver.findElement(By.cssSelector("span[class='ls-lesson-title ellipsis assignment-cart-item']")).click();//click on first assignment in course stream page
			boolean lesson1=new BooleanValue().booleanbyid("sec-intro");//verify lesson page
			if(lesson1==false)
			{
				Assert.fail("after click on learning activity its not goes to lesson page");
			}
            Thread.sleep(3000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='toggle-widget-size toggle-widget-size-collapse']"))); //collapsing the expanded widget
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase AssignmentCartAssigned in method AssignAssignmentAtCourseStreaam",e);
		}
	}

	
}
