package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R27;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class InstructorAbleToViewLatestCourseStreamEntry extends Driver
{
	@Test
	public void instructorAbleToViewLatestCourseStreamEntry()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("179");//login as instructor
			new Navigator().NavigateTo("Course Stream");//navigate to course stream	
			String randontext=new RandomString().randomstring(2);
			new PostMessage().postmessage(randontext);//post text in course stream
			new Navigator().NavigateTo("Dashboard");//navigate to dashboard
			String streamtext=new TextFetch().textfetchbyclass("stream-title");
			if(!streamtext.contains("Class Activity"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Title of dash board is not Class Activity ");
			}
			boolean seeall=new BooleanValue().booleanbyclass("course-button");
			if(seeall==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("See all link is not present on dashbaord");
			}
			int streamtdashboard=driver.findElements(By.xpath("//*[starts-with(@class, 'news ')]")).size();//check number of post present on dashboard
			if(streamtdashboard>3)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("three latest stream not shown on dash board");
			}
			String coursestreamtext=driver.findElement(By.xpath("//*[starts-with(@class, 'news ')]")).getText();//fetching text from under course stream on dashbaord
			if(!coursestreamtext.contains(Config.familyname))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("instructor name not shown");
			}
			if(!coursestreamtext.contains("Instructor"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("post by instructor not shown");
			}
			if(!coursestreamtext.contains("posted a discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("level not shown");
			}
			if(!coursestreamtext.contains(randontext))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("posted text in course stream its not shown on dashboard");
			}
			if(coursestreamtext.contains("Like"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("like link shown in stream  on dashboard");
			}
			if(coursestreamtext.contains("Comments"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("comment link shown in stream  on dashboard");
			}
			boolean imageofinstructor=new BooleanValue().booleanbylistclass("photo", 0);//thumbnail of instructor
			if(imageofinstructor==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("image if instructor not shown");
			}
			boolean staricon=new BooleanValue().booleanbylistclass("course-stream-item-unbookmark", 0);//bookmark icon
			if(staricon==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("bookmark icon not sjownn");
			}
			new Click().clickbylist("middle", 0);//click on stream on dashboard
			String coursestream=driver.getCurrentUrl();
			if(!coursestream.contains("coursestream"))
			{
				Assert.fail("after click on stream on dashboard its not goes to course starem page");
			}
			new Navigator().NavigateTo("Dashboard");//navigate to dashboard
			new Click().clickbylist("middle", 0);//click on stream on dashboard
			String coursestream1=driver.getCurrentUrl();
			if(!coursestream1.contains("coursestream"))
			{
				Assert.fail("after click on stream on dashboard its not goes to course starem page");
			}
			new Click().clickbylistcssselector("i[class='ls-icon-img ls--star-icon']", 0);
			new Navigator().NavigateTo("Dashboard");//navigate to dashboard
			boolean bookmarkedicon=new BooleanValue().booleanbylistclass("course-stream-item-bookmark", 0);//bookmarked icon
			if(bookmarkedicon==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("bookmarked as course stream page its not shown on dashboard");
			}
			new LoginUsingLTI().ltiLogin("1791");//login as new instructor
			int streamtdashboard1=driver.findElements(By.xpath("//*[starts-with(@class, 'news ')]")).size();//check number of post present on dashboard
			if(streamtdashboard1!=0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("for new instructor ther is posted in course straem on dashbaord");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase InstructorAbleToViewLatestCourseStreamEntry in method instructorAbleToViewLatestCourseStreamEntry",e);
		}
	}


}
