package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R38;

import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

import java.util.List;

public class BookMarkVerifyForInstructor extends Driver {
	@Test(priority = 1, enabled = true)
	public void lessonBookMarkVerifyForInstructor()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("19");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().isPresent("lesson");//find the book mark symbol
			new Bookmark().bookmark("lesson");//bookmark the lesson
			boolean bookmarked = new Bookmark().isBookmarked("lesson");//check the color
			if(bookmarked == false)
				Assert.fail("Page is not bookmarked");
			
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().unbookmark("lesson");//remove bookmark
			boolean isbookmarked = new Bookmark().isBookmarked("lesson");
			if(isbookmarked == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking it doesn't turn grey.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase lessonBookMarkVerifyForInstructor in class BookMarkVerifyForInstructor",e);
		}
	}
	@Test(priority = 2, enabled = false) //instructor can not bookmark a widget so disabling the TC
	public void bookMarkImageWidgetForInstructor()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("51");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("widget");
			//check for book mark symbol
			boolean bookmarked = new Bookmark().isBookmarked("widget");
			
			if(bookmarked == false )
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The bookmark symbol doesn't turn yellow after clicking it.");
			}
			//navigate to some other lesson come to the same lesson again
			//click on next lesson 
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-next")));
			Thread.sleep(3000);
			//click on previous lesson 
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-prev")));
			Thread.sleep(3000);
			new Bookmark().unbookmark("widget");//again unbookmark
			//find the book mark symbol
			boolean isBookmarked0 = new Bookmark().isBookmarked("widget");
			if(isBookmarked0 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After navigating to some other lesson and again coming back to the lesson and unbookmark is does not trun grey.");
			}
			//like on the widget;
			driver.findElement(By.cssSelector("span.ls-right-post-like-link")).click();
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			boolean isPresent3 = new Bookmark().isPresentInCourseStream("widget");
			if(isPresent3 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After liking the widget from lesson page, the entry is not added to Course Stream.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("widget");//bookmark the widget
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			boolean isPresent2 = new Bookmark().isPresentInCourseStream("widget");
			if(isPresent2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the widget from lesson page, the entry is not added to Course Stream.");
			}
			String color = new Bookmark().colorOfStarInCourseStream();
			if(!color.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the widget from lesson page, the * icon in Course Stream is not yellow.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().unbookmark("widget");
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			String color1 = new Bookmark().colorOfStarInCourseStream();
			if(color1.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking the widget from lesson page, the * icon in Course Stream is not grey.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new AssignmentSocialElement().addComment("Commented text");//add a comment
			new Navigator().NavigateTo("Course Stream");//go to Course Stream
			new Bookmark().bookmarkFromCourseStream();
			String color2 = new Bookmark().colorOfStarInCourseStream();
			if(!color2.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the widget from Course Stream, the * icon in Course Stream doesn't turn yellow.");
			}
			//Name of Course Stream should not update
			String courseStreamText = driver.findElement(By.cssSelector("span[class='ls-stream-post__action']")).getText();
			if(courseStreamText.contains("bookmarked this"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the widget from Course Stream, the Course Stream name gets updated.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			boolean isBookmarked2 = new Bookmark().isBookmarked("widget");
			if(isBookmarked2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the widget from Course Stream, the * icon in lesson page is not yellow.");
			}
			
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookMarkImageWidgetForInstructor in class BookMarkVerifyForInstructor",e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void bookMarkImageWidgetAndLikeForInstructor()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("51_1");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='Like']")));//click on like
            Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");
			boolean isPresent = new Bookmark().isPresentInCourseStream("widget");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After hitting like from lesson, the entry is not added to Course Stream .");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookMarkImageWidgetAndLike in class BookMarkVerifyForInstructor",e);
		}
	}
	@Test(priority = 4, enabled = true)
	public void bookmarkResourceForInstructor()
	{
		try
		{
			String resourcesname = ReadTestData.readDataByTagName("", "resourcesname", Integer.toString(84));

			new ResourseCreate().resourseCreate(84, 0);
			new LoginUsingLTI().ltiLogin("84");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
            (new Navigator()).navigateToResourceTab();
			WebElement element=driver.findElement(By.xpath("//a[text()='"+resourcesname+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
			int resindex = 0;
			List<WebElement> resources = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(WebElement res : resources)
			{
				System.out.println("Resource Name: "+res.getText());
				if(res.getText().contains(resourcesname))
				{
					break;
				}
				resindex++;
			}
			System.out.println("resindex"+resindex);
			List<WebElement> allOpenLink = driver.findElements(By.xpath("//div[@class='ls-right-hover-elements']/a[2]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOpenLink.get(resindex));
			Thread.sleep(2000);

			boolean isBookmarked = new Bookmark().isBookmarked("resource");
			if(isBookmarked == true)
			{
				Assert.fail("On Opening a resource it is not in grey color.");
			}

			new Bookmark().bookmark("resource");//bookmark the resource
			boolean isYellow = new Bookmark().isBookmarked("resource");
			if(isYellow == false)
			{
				Assert.fail("On bookmarking a resource it is not change to yellow color.");
			}

			new LoginUsingLTI().ltiLogin("84_1");//login as another instructor
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().navigateToResourceTab();//go to resource tab
			WebElement element1=driver.findElement(By.xpath("//a[text()='"+resourcesname+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element1);
			int resindex1 = 0;
			List<WebElement> resources1 = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(WebElement res : resources1)
			{
				System.out.println("Resource Name: "+res.getText());
				if(res.getText().contains(resourcesname))
				{
					break;
				}
				resindex1++;
			}
			System.out.println("resindex"+resindex1);
			List<WebElement> allOpenLink1 = driver.findElements(By.xpath("//div[@class='ls-right-hover-elements']/a[2]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOpenLink1.get(resindex1));
			Thread.sleep(2000);
			
			boolean isBookmarked1 = new Bookmark().isBookmarked("resource");
            System.out.println("bookmarked:"+isBookmarked1);
            if(isBookmarked1 == true)
			{
				Assert.fail("The resource which has bookmarked by a instructor is also get bookmarked for another instructor.");
			}
			new Bookmark().bookmark("resource");//bookmark the resource for 2nd instructor*/
			new LoginUsingLTI().ltiLogin("84");//login as 1st instructor
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();

            (new Navigator()).navigateToResourceTab();
            Thread.sleep(5000);
/*			WebElement element3=driver.findElement(By.xpath("//a[text()='"+resourcesname+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element3);
			int resindex2 = 0;
			List<WebElement> resources3 = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(WebElement res : resources3)
			{
				System.out.println("Resource Name: "+res.getText());
				if(res.getText().contains(resourcesname))
				{
					break;
				}
				resindex2++;
			}
			System.out.println("resindex"+resindex2);*/
			int resindex2 = 0;
			List<WebElement> resources3 = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(int a =0;a<resources3.size();a++){
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resources3.get(a));
				if(resources3.get(a).getText().equals(resourcesname)){
					System.out.println("resourcesname:"+resourcesname);
					break;
				}
				resindex2++;
			}
			System.out.println("resindex2: "+resindex2);
			List<WebElement> allOpenLink2 = driver.findElements(By.xpath("//div[@class='ls-right-hover-elements']/a[2]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOpenLink2.get(resindex2));
			Thread.sleep(2000);
			boolean isBookmarked2 = new Bookmark().isBookmarked("resource");
			if(isBookmarked2 == false)
			{
				Assert.fail("The resource which has been bookmarked by a instructor gets unbookmarked when another instructor bookmark the same resource.");
			}
			//navigate to some other lesson come to the same lesson again
			//click on next lesson 
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-next")));
			Thread.sleep(3000);

			new Navigator().navigateToResourceTab();//navigate to resource tab
            Thread.sleep(5000);
			/*WebElement element5=driver.findElement(By.xpath("//a[text()='"+resourcesname+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element5);
			int resindex3 = 0;
			List<WebElement> resources4 = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(WebElement res : resources4)
			{
				System.out.println("Resource Name: "+res.getText());
				if(res.getText().contains(resourcesname))
				{
					break;
				}
				resindex3++;
			}
			System.out.println("resindex"+resindex3);*/
			int resindex3 = 0;
			List<WebElement> resources4 = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(int a =0;a<resources4.size();a++){
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resources4.get(a));
				if(resources4.get(a).getText().equals(resourcesname)){
					System.out.println("resourcesname:"+resourcesname);
					break;
				}
				resindex3++;
			}
			System.out.println("resindex3: "+resindex3);
			List<WebElement> allOpenLink3 = driver.findElements(By.xpath("//div[@class='ls-right-hover-elements']/a[2]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOpenLink3.get(resindex3));
			Thread.sleep(2000);

			//the resource should remain bookmarked
			boolean isBookmarked3 = new Bookmark().isBookmarked("resource");
			if(isBookmarked3 == false)
			{
				Assert.fail("After bookmarking a resource if we navigate to some other lesson and comes to same lesson again it doesn't remain bookmarked.");
			}
			Thread.sleep(3000);
			new Bookmark().unbookmark("resource");//remove bookmark
			boolean isBookmarked4 = new Bookmark().isBookmarked("resource");
			if(isBookmarked4 == true)
			{
				Assert.fail("After removing bookmark from a resource it doesn't turn grey.");
			}

		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase bookmarkResource in class BookMarkVerifyForInstructor",e);
		}
	}
	@Test(priority = 5, enabled = true)
	public void likeCommentOnResource()
	{
		try
		{
			String resourcesname = ReadTestData.readDataByTagName("", "resourcesname", Integer.toString(84));

			new LoginUsingLTI().ltiLogin("84_2");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().navigateToResourceTab();//go to resource tab

			WebElement element=driver.findElement(By.xpath("//a[text()='"+resourcesname+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
			int resindex = 0;
			List<WebElement> resources = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(WebElement res : resources)
			{
				System.out.println("Resource Name: "+res.getText());
				if(res.getText().contains(resourcesname))
				{
					break;
				}
				resindex++;
			}
			System.out.println("resindex"+resindex);
			List<WebElement> allOpenLink = driver.findElements(By.xpath("//div[@class='ls-right-hover-elements']/a[2]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOpenLink.get(resindex));
			Thread.sleep(2000);

			new AssignmentSocialElement().clickonlike(1);//click to like
			new CommentOnPost().commentOnPost("Comment on resource", 1);//comment on resource
			new Navigator().NavigateTo("Course Stream"); // go to Course Stream
			int likeCount = new AssignmentSocialElement().countoflikecoursestream(0);
			if(likeCount != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Like count is not added in the course stream.");
			}
			int commentCount = new AssignmentSocialElement().countofcomment(0);
			if(commentCount != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("comment Count is not added in the course stream.");
			}

			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase likeCommentOnResource in class BookMarkVerifyForInstructor",e);
		}
	}

}
