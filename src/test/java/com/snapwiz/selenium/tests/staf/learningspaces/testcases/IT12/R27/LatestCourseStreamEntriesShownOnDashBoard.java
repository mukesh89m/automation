package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R27;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AddCart;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignLesson;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.CommentOnPost;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class LatestCourseStreamEntriesShownOnDashBoard extends Driver
{
	@Test(priority=1,enabled=true)
	public void latestCourseStreamEntriesShownOnDashBoard()
	{
		try
		{
			String randontext=new RandomString().randomstring(2);
			String randontext1=new RandomString().randomstring(2);
			new LoginUsingLTI().ltiLogin("193");//login as instructor
			new Navigator().NavigateTo("Course Stream");//navigate to course stream
			new PostMessage().postmessage(randontext);//post text in course stream
            Thread.sleep(3000);
			new PostMessage().postlink("www.google.com");//post link
			new FileUpload().fileUpload("193", true);//208
			new Navigator().NavigateTo("Dashboard");//navigate to dashboard
			List<WebElement> streamdashboard=driver.findElements(By.xpath("//*[starts-with(@class, 'news ')]"));//check number of post present on dashboard
			String post1=streamdashboard.get(0).getText();
			if(!post1.contains("img.png"))
			{
				Assert.fail("image stream not  on top");
			}
			String post2=streamdashboard.get(1).getText();
			if(!post2.contains("www.google.com"))
			{
				Assert.fail("link is not on second");
			}
			String post3=streamdashboard.get(2).getText();
			if(!post3.contains(randontext))
			{
				Assert.fail("text is not on third");
			}
			new Navigator().NavigateTo("Course Stream");//navigate to course stream	
			new CommentOnPost().commentOnPost(randontext1, 1);
			new Navigator().NavigateTo("Dashboard");//navigate to dashboard
			List<WebElement> streamdashboard1=driver.findElements(By.className("ls-link-span"));//check number of post present on dashboard
			String post11=streamdashboard1.get(0).getText();
			if(!post11.contains("www.google.com"))
			{
				Assert.fail("latest commented post not on top");
			}
			
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase InstructorAbleToViewLatestCourseStreamEntry in method instructorAbleToViewLatestCourseStreamEntry",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void DifferentKindOfStreamEntries()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("205");
			new Navigator().NavigateTo("Course Stream");//navigate to course stream	
			new PostMessage().postlink("www.google.com");//post link--206
			new Navigator().NavigateTo("Dashboard");//navigate to dashboard
			//---207 
			new Click().clickbylist("text-to-url", 0);// click on link
			String winHandleBefore = driver.getWindowHandle();
			for(String winHandle :driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.close();
			driver.switchTo().window(winHandleBefore);
			new Navigator().NavigateTo("Course Stream");//navigate to course stream	
			new FileUpload().fileUpload("2051", true);//post mp3 --211
			new Navigator().NavigateTo("Dashboard");//navigate to dashboard
			List<WebElement> streamdashboard1=driver.findElements(By.xpath("//*[starts-with(@class, 'news ')]"));//check number of post present on dashboard
			Thread.sleep(2000);
			streamdashboard1.get(0).click();//210
			new FileUpload().fileUpload("2052", true);//post mp3 --209
			new Navigator().NavigateTo("Dashboard");//navigate to dashboard
			List<WebElement> streamdashboard2=driver.findElements(By.xpath("//*[starts-with(@class, 'news ')]"));//check number of post present on dashboard
			Thread.sleep(2000);
			streamdashboard2.get(0).click();//212
			String curreturl=driver.getCurrentUrl();
			if(!curreturl.contains("coursestream"))
			{
				Assert.fail("after click on swf link its not goes to course stream");
			}
			//213
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().lessonOpen(0, 0);//open chapter 1
			new AddCart().widgetaddtocart();//add card image widget
			new Click().clickByclassname("assignment-cart-wrapper");//click on assignment cart
			new AssignLesson().assigncart(2052);//assign cart to class section
			new Click().clickByclassname("ls-site-nav-drop-down");//click on navidator
			new Click().clickByclassname("ls--dashboard");//click on dashbaord navigator
			String assignemntduedate=new TextFetch().textfetchbylistclass("bottom", 0);
			if(!assignemntduedate.contains("Due Date"))
			{
				Assert.fail("due date of assignment not shown on dashboard of instructor");
			}
			List<WebElement> streamdashboard3=driver.findElements(By.xpath("//*[starts-with(@class, 'news ')]"));//check number of post present on dashboard
			Thread.sleep(2000);
			streamdashboard3.get(0).click();//click on 1st post 
			Thread.sleep(2000);
			String pageurl=driver.getCurrentUrl();
			if(!pageurl.contains("coursestream"))
			{
				Assert.fail("after click on assignment its not goes to course stream page");
			}
			//217
			String randomstring=new RandomString().randomstring(2);
			new PostMessage().postmessage(randomstring);//post message
			driver.quit();
			startDriver("firefox");
			new LoginUsingLTI().ltiLogin("205");
			List<WebElement> streamdashboard4=driver.findElements(By.xpath("//*[starts-with(@class, 'news ')]"));//check number of post present on dashboard
			String posttext=streamdashboard4.get(0).getText();
			if(!posttext.contains(randomstring))
			{
				Assert.fail("course stream tile not updated");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase InstructorAbleToViewLatestCourseStreamEntry in method DifferentKindOfStreamEntries",e);
		}
		
	}


}
