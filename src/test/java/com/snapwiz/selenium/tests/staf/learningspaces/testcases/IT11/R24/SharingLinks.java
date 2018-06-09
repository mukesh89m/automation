package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R24;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class SharingLinks extends Driver {
	
	@Test(priority = 1, enabled = true)
	public void sharingLinks()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("102"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			driver.findElement(By.linkText("Link")).click(); //click on Link
			Thread.sleep(2000);
			//TC row no. 102
			String deafultText = driver.switchTo().frame("iframe-user-link-input-div").findElement(By.xpath("/html/body/font")).getText();
		    driver.switchTo().defaultContent();
			if(!deafultText.trim().contains("Share your link"))
			{
				new Screenshot().captureScreenshotFromTestCase();    	 
				Assert.fail("Default Text 'Share Your Link' is NOT present");
			}
			
			new PostMessage().postLinkWithoutSubmit("www.wikipedia.com");
			//TC row no. 104
			boolean thumbnail=driver.findElement(By.id("scrapper")).isDisplayed();
		  	if(thumbnail == false)
		  	{
		  		new Screenshot().captureScreenshotFromTestCase();    	 
		  		Assert.fail("Thumbnail is NOT present after typing the URL");
		  	}
		  	new PostMessage().postLinkWithoutSubmit("www.wikipedia.com ");//URL entered along with a space
		    //TC row no. 105
		  	boolean thumbnail2=driver.findElement(By.id("scrapper")).isDisplayed();
		  	if(thumbnail2 == false)
		  	{
		  		new Screenshot().captureScreenshotFromTestCase();    	 
		  		Assert.fail("Thumbnail is NOT present after entering the URL with a space");
		  	}	
		  	driver.findElement(By.id("post-submit-cancel-button")).click();  //click on cancel
		  	Thread.sleep(2000);
		    //TC row no. 106
		  	boolean thumbnail3=driver.findElement(By.id("scrapper")).isDisplayed();
		  	if(thumbnail3 == true)
		  	{
		  		new Screenshot().captureScreenshotFromTestCase();    	 
		  		Assert.fail("Thumbnail is present after we click on cancel.");
		  	}
		  	String link = "www.wikipedia.com";
			new PostMessage().postLinkWithoutSubmit(link+Keys.TAB);
			//TC row no. 107
			boolean thumbnail5=driver.findElement(By.id("scrapper")).isDisplayed();
		  	if(thumbnail5 == false)
		  	{
		  		new Screenshot().captureScreenshotFromTestCase();    	 
		  		Assert.fail("Thumbnail is NOT present after entering the URL and TAB key pressed.");
		  	}
			WebElement t=driver.findElement(By.id("iframe-user-link-input-div"));
			driver.switchTo().frame(t) ;
			for(int i=0; i<link.length(); i++)		
				driver.findElement(By.xpath("/html/body")).sendKeys(Keys.BACK_SPACE);
			driver.switchTo().defaultContent();
		  	Thread.sleep(5000);
		    //TC row no. 108
		  	boolean thumbnail4=driver.findElement(By.id("scrapper")).isDisplayed();
		  	if(thumbnail4 == true)
		  	{
		  		new Screenshot().captureScreenshotFromTestCase();    	 
		  		Assert.fail("Thumbnail doesn't get removed after deleting the URL");	
		  	}
		  	driver.findElement(By.id("post-submit-cancel-button")).click();  //click on cancel
		  	Thread.sleep(2000);
		  	new PostMessage().postLinkWithoutSubmit("www.wikipedia.com"+Keys.SPACE+"www.yahoo.com");
		  	Thread.sleep(7000);
		    //TC row no. 109
			String thumbnail6=driver.findElement(By.id("scrapper")).getText();
		  	if(!thumbnail6.contains("wikipedia"))
		  	{
		  		new Screenshot().captureScreenshotFromTestCase();    
		  		Assert.fail("On Entering Two URLs, Thumbnail of the First entered URL is NOT present.");	
		  	}
		  	driver.findElement(By.id("post-submit-cancel-button")).click();  //click on cancel
		  	Thread.sleep(2000);
		  	String str = new RandomString().randomstring(2);
		  	new PostMessage().postLinkWithoutSubmit(str+Keys.SPACE+"www.wikipedia.com");  //Fill in some text and enter a URL
		    //TC row no. 110
		  	boolean thumbnail7=driver.findElement(By.id("scrapper")).isDisplayed();
		  	if(thumbnail7 == false)
		  	{
		  		new Screenshot().captureScreenshotFromTestCase();    
		  		Assert.fail("Fill in some text and enter a URL then the thumbnail for the URL is not displayed.");	
		  	}
		  	driver.findElement(By.id("post-submit-cancel-button")).click();  //click on cancel
		  	Thread.sleep(2000);
		  	new PostMessage().postLinkWithoutSubmit("www.wikipedia.com"+Keys.SPACE+str);  //Enter a URL and fill in some text.
		    //TC row no. 111
		  	boolean thumbnail8=driver.findElement(By.id("scrapper")).isDisplayed();
		  	if(thumbnail8 == false)
		  	{
		  		new Screenshot().captureScreenshotFromTestCase();    
		  		Assert.fail("Enter a URL and fill in some text then the thumbnail for the URL is not displayed.");	
		  	}
		  	driver.findElement(By.id("post-submit-cancel-button")).click();  //click on cancel
		  	Thread.sleep(2000);
		  	new PostMessage().postLinkWithoutSubmit(str+Keys.SPACE+"www.wikipedia.com"+Keys.SPACE+str+Keys.TAB); //Fill in some text Enter a URL and fill in some text and button out
		    //TC row no. 112
		  	boolean thumbnail9=driver.findElement(By.id("scrapper")).isDisplayed();
		  	if(thumbnail9 == false)
		  	{
		  		new Screenshot().captureScreenshotFromTestCase();    
		  		Assert.fail("Fill in some text Enter a URL and fill in some text and button out then the thumbnail for the URL is not displayed.");	
		  	}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase sharingLinks in class SharingLinks.", e);
		}
	}

	@Test(priority = 2, enabled = true)
	public void linkTextAreNotCaseSensitive()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("113"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			new PostMessage().postLinkWithoutSubmit("www.WIKIPEDIA.com");   //URL in uppercase
			//TC row no. 113
			boolean thumbnail=driver.findElement(By.id("scrapper")).isDisplayed();
		  	if(thumbnail == false)
		  	{
		  		new Screenshot().captureScreenshotFromTestCase();    	 
		  		Assert.fail("Thumbnail is NOT present after typing the URL in uppercase.");
		  	}
		  	new PostMessage().postLinkWithoutSubmit("www.WiKiPedia.com");//URL in mix of both uppercase and lowercase
		    //TC row no. 113
		  	boolean thumbnail2=driver.findElement(By.id("scrapper")).isDisplayed();
		  	if(thumbnail2 == false)
		  	{
		  		new Screenshot().captureScreenshotFromTestCase();    	 
		  		Assert.fail("Thumbnail is NOT present after entering the URL in mix of both uppercase and lowercase.");
		  	}	
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase linkTextAreNotCaseSensitive in class SharingLinks.", e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void shareLinkWithClassSection()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("116"); //login as instructor
			new LoginUsingLTI().ltiLogin("114_1"); //login as instructor
			new LoginUsingLTI().ltiLogin("114"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			new PostMessage().postlink("www.yahoo.com");
			//TC row no. 114
			String title = new TextFetch().textfetchbyclass("ls-stream-post__head");
			if(!title.contains("shared a link"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Link Posting with title \"Shared a Link\" is not added in course stream.");
			}
			new LoginUsingLTI().ltiLogin("114_1"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			//TC row no. 115
			String title1 = new TextFetch().textfetchbyclass("ls-stream-post__head");
			if(!title1.contains("shared a link"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For another instructor in same class section the Title \"shared a link\" for a link is not added in course stream where the link shared with the current class section.");
			}
			new LoginUsingLTI().ltiLogin("116"); //login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			//TC row no. 116
			int size = driver.findElements(By.className("ls-stream-post__head")).size();
			if(size != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For another instructor in different class section the Title \"shared a link\" for a link is added in course stream.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase shareLinkWithClassSection in class SharingLinks.", e);
		}
	}
	@Test(priority = 4, enabled = true)
	public void sharingLinkWithAInstructor()
	{
		try
		{
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "117_1");
			new LoginUsingLTI().ltiLogin("117_1");  	//login as instructor
			new LoginUsingLTI().ltiLogin("117_2");  	//login as instructor
			new LoginUsingLTI().ltiLogin("117");  	//login as instructor
			new Navigator().NavigateTo("Course Stream");
			new PostMessage().postLinkAndShare("www.yahoo.com", shareWithInitialString, "studentnametag", "117_1");
			String title = new TextFetch().textfetchbyclass("ls-stream-post__head");
			//TC row no. 117
			if(!title.contains("shared a link"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Title \"shared a link\" for a link is not added in course stream when link is shared with a specific instructor.");
			}
			new LoginUsingLTI().ltiLogin("117_1");  	//login as instructor 
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			//TC row no. 119
			String title1 = new TextFetch().textfetchbyclass("ls-stream-post__head");
			if(!title1.contains("shared a link"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Title \"shared a link\" for a link is not added in course stream for the instructor for which the link has been shared.");
			}
			new LoginUsingLTI().ltiLogin("117_2");  	//login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			//TC row no. 120
			int size = driver.findElements(By.className("ls-stream-post__head")).size();
			if(size != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The link is also displayed for other instructor to whom the link has not been shared.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase sharingLinkWithAInstructor in class SharingPosts", e);
		}
	}
	@Test(priority = 5, enabled = true)
	public void sharingLinkWithAMentor()
	{
		try
		{
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "124_1");
			new LoginUsingLTI().ltiLogin("124_1");  	//login as mentor
			new LoginUsingLTI().ltiLogin("124_2");  	//login as mentor
			new LoginUsingLTI().ltiLogin("124");  	//login as mentor
			new Navigator().NavigateTo("Course Stream");
			new PostMessage().postLinkAndShare("www.yahoo.com", shareWithInitialString, "studentnametag", "124_1");
			String title = new TextFetch().textfetchbyclass("ls-stream-post__head");
			//TC row no. 124
			if(!title.contains("shared a link"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Title \"shared a link\" for a link is not added in course stream when link is shared with a specific mentor.");
			}
			new LoginUsingLTI().ltiLogin("124_1");  	//login as mentor 
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			//TC row no. 125
			String title1 = new TextFetch().textfetchbyclass("ls-stream-post__head");
			if(!title1.contains("shared a link"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Title \"shared a link\" for a link is not added in course stream for the mentor for which the link has been shared.");
			}
			new LoginUsingLTI().ltiLogin("124_2");  	//login as mentor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			//TC row no. 126
			int size = driver.findElements(By.className("ls-stream-post__head")).size();
			if(size != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The link is also displayed for other mentor to whom the link has not been shared.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase sharingLinkWithAMentor in class SharingPosts", e);
		}
	}

}
