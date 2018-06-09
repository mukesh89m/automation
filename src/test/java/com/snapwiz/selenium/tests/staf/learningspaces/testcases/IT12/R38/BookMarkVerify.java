package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R38;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

public class BookMarkVerify extends Driver {

	@Test(priority = 1, enabled = true)
	public void bookMark()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("3");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().isPresent("lesson");//find the book mark symbol
			new Bookmark().bookmark("lesson");//bookmark the lesson
			boolean bookmarked = new Bookmark().isBookmarked("lesson");//check the color
			if(bookmarked == false)
				Assert.fail("Page is not bookmarked");
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isPresent = new Bookmark().isPresentInMyJournal("lesson");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a lesson it is absent in My Journal.");
			}
			new LoginUsingLTI().ltiLogin("3_1");//login as another student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			boolean bookmarked1 = new Bookmark().isBookmarked("lesson");//check the color
			if(bookmarked1 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("A lesson which has been bookmarked by some other student gets automatically bookmarked for other students also");
			}
			new LoginUsingLTI().ltiLogin("3");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().unbookmark("lesson");//remove bookmark
			boolean isbookmarked = new Bookmark().isBookmarked("lesson");;
			if(isbookmarked == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking it doesn't turn grey.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isPresent1 = new Bookmark().isMyJournalEmpty();
			if(isPresent1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking it is still present in My Journal.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("lesson");//bo0kmark the lesson once again
			new Navigator().NavigateTo("My Journal");//go to My Journal
			new Bookmark().unbookmarkFromMyJournal();//deselect the * icon from My Journal page
			boolean isRemoved = new Bookmark().isRemovedFromMyJournal();
			if(isRemoved == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking from My Journal it is still visible in My Journal.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			boolean isBookmarked = new Bookmark().isBookmarked("lesson");
			if(isBookmarked == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking from My Journal, in lesson page the * icon is still yellow.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookMark in class BookMarkVerify",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void bookMarkImageWidget()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("23");
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
			//go to My Journal
			new Navigator().NavigateTo("My Journal");
			//check for the image
			boolean isPresent = new Bookmark().isPresentInMyJournal("widget");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarikng a image widget the Image is not displayed in My Journal Page.");
			}
			new LoginUsingLTI().ltiLogin("23_1");//login as another student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			boolean bookmarked1 = new Bookmark().isBookmarked("widget");
			if(bookmarked1 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("A widget in a lesson which has been bookmarked by some other student gets automatically bookmarked for other students also");
			}
			new LoginUsingLTI().ltiLogin("23");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
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
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isPresent1 = new Bookmark().isMyJournalEmpty();
			if(isPresent1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking it is still present in My Journal.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			//goto another lesson and come back
			driver.findElement(By.cssSelector("a.ls-next")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.ls-prev")).click();
			Thread.sleep(2000);
			
			//check whether bookmarked
			boolean isBookmarked = new Bookmark().isBookmarked("widget");
			if(isBookmarked == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking if we navigate to some other lesson and come back the * icon for the widget is yellow.");
			}
			new Bookmark().bookmark("widget");//bookmark the widget
			new Navigator().NavigateTo("My Journal");//go to My Journal
			new Bookmark().unbookmarkFromMyJournal();
			boolean isRemoved = new Bookmark().isRemovedFromMyJournal();
			if(isRemoved == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking the widget from My Journal it is still visible in My Journal.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			boolean isBookmarked1 = new Bookmark().isBookmarked("widget");
			if(isBookmarked1 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking the widget from My Journal, in lesson page the * icon is still yellow for the widget.");
			}
			//like on the widget;
			driver.findElement(By.cssSelector("a[title='Like']")).click();
			Thread.sleep(2000);
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
			
			new Navigator().NavigateTo("My Journal");//go to My Journal
			//check for the image
			boolean isPresent4 = new Bookmark().isPresentInMyJournal("widget");
			if(isPresent4 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a image widget from Course stream the entry is not created in My Journal.");
			}
			
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookMarkImageWidget in class BookMarkVerify",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void bookmarkResource()
	{
		try
		{
			String resourcesname = ReadTestData.readDataByTagName("", "resourcesname", Integer.toString(56));

			new LoginUsingLTI().ltiLogin("56_1");//login as instructor
            new UploadResources().uploadResources("56", false, false, true);
			new LoginUsingLTI().ltiLogin("56");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().navigateToResourceTab();//go to resource tab
			System.out.println("resoursename: "+resourcesname);
			String appendChar=null;
			if(System.getProperty("UCHAR")==null)
			{
				appendChar=LoginUsingLTI.appendChar;
			}
			else {
				appendChar=System.getProperty("UCHAR");
			}
			String actualResources= resourcesname+appendChar;
			WebElement element=driver.findElement(By.xpath("//a[text()='"+actualResources+"']"));
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
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On Opening a resource it is not in grey color.");
			}
			new Bookmark().bookmark("resource");//bookmark the resource
			boolean isYellow = new Bookmark().isBookmarked("resource");
			if(isYellow == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On bookmaring a resource it is not change to yellow color.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isPresent = new Bookmark().isPresentInMyJournal("resource");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a resource the resouce is not displayed in My Journal.");
			}

			new LoginUsingLTI().ltiLogin("60");//login as another student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().navigateToResourceTab();//go to resource tab

			System.out.println("resoursename: "+resourcesname);
			String appendChar1=null;
			if(System.getProperty("UCHAR")==null)
			{
				appendChar1=LoginUsingLTI.appendChar;
			}
			else {
				appendChar1=System.getProperty("UCHAR");
			}
			String actualResources1= resourcesname+appendChar1;
			WebElement element1=driver.findElement(By.xpath("//a[text()='"+actualResources1+"']"));
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
			if(isBookmarked1 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The resource which hass bookmarked by a student is also get bookmarked for another student.");
			}
			new Bookmark().bookmark("resource");//bookmark the resource for 2nd student
			new LoginUsingLTI().ltiLogin("56");//login as 1st student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().navigateToResourceTab();//go to resource tab

			System.out.println("resoursename: "+resourcesname);
			String appendChar2=null;
			if(System.getProperty("UCHAR")==null)
			{
				appendChar2=LoginUsingLTI.appendChar;
			}
			else {
				appendChar2=System.getProperty("UCHAR");
			}
			String actualResources2= resourcesname+appendChar2;
			/*WebElement element2=driver.findElement(By.xpath("//a[text()='"+actualResources2+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element2);
			int resindex2 = 0;
			List<WebElement> resources2 = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(WebElement res : resources2)
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
			List<WebElement> resources2 = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(int a=0;a<resources2.size();a++){
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resources2.get(a));
				if((resources2.get(a).getText().equals(actualResources2))){
					System.out.println("actualResources2:"+actualResources2);
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
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The resource which has been bookmarked by a student gets unbookmarked when another student bookmark the same resource.");
			}
			//navigate to some other lesson come to the same lesson again
			//click on next lesson 
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-next")));
			Thread.sleep(3000);
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().navigateToResourceTab();//navigate to resource tab

			System.out.println("resoursename: "+resourcesname);
			String appendChar3=null;
			if(System.getProperty("UCHAR")==null)
			{
				appendChar3=LoginUsingLTI.appendChar;
			}
			else {
				appendChar3=System.getProperty("UCHAR");
			}
			String actualResources3= resourcesname+appendChar3;
			/*WebElement element3=driver.findElement(By.xpath("//a[text()='"+actualResources3+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element3);
			int resindex3 = 0;
			List<WebElement> resources3 = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(WebElement res : resources3)
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
			List<WebElement> resources3 = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(int a=0;a<resources3.size();a++){
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resources3.get(a));
				if((resources3.get(a).getText().equals(actualResources3))){
					System.out.println("actualResources3:"+actualResources3);
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
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a resource if we navigate to some other lesson and comes to same lesson again it doesn't remain bookmarked.");
			}
			new Bookmark().unbookmark("resource");//remove bookmark
			boolean isBookmarked4 = new Bookmark().isBookmarked("resource");
			if(isBookmarked4 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark from a resource it doesn't turn grey.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isEmpty = new Bookmark().isMyJournalEmpty();
			if(isEmpty == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark from a resource it doesn't get removed from my journal.");
			}
			//again bookmark the resource.
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().navigateToResourceTab();//go to resource tab

			System.out.println("resoursename: "+resourcesname);
			String appendChar4=null;
			if(System.getProperty("UCHAR")==null)
			{
				appendChar4=LoginUsingLTI.appendChar;
			}
			else {
				appendChar4=System.getProperty("UCHAR");
			}
			String actualResources4= resourcesname+appendChar4;
			/*WebElement element4=driver.findElement(By.xpath("//a[text()='"+actualResources4+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element4);
			int resindex4 = 0;
			List<WebElement> resources4 = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(WebElement res : resources4)
			{
				System.out.println("Resource Name: "+res.getText());
				if(res.getText().contains(resourcesname))
				{
					break;
				}
				resindex4++;
			}
			System.out.println("resindex"+resindex4);*/
			int resindex4 = 0;
			List<WebElement> resources4 = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(int a=0;a<resources4.size();a++){
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resources4.get(a));
				if((resources4.get(a).getText().equals(actualResources4))){
					System.out.println("actualResources4:"+actualResources4);
					break;
				}
				resindex4++;
			}
			System.out.println("resindex4: "+resindex4);
			List<WebElement> allOpenLink4 = driver.findElements(By.xpath("//div[@class='ls-right-hover-elements']/a[2]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOpenLink4.get(resindex4));
			Thread.sleep(2000);

			new Bookmark().bookmark("resource");//bookmark resource
			new Navigator().NavigateTo("My Journal");//go to My Journal
			//unbookmark from my journal
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.my-journal-card-bookmark-icon.general-sprites")));
			//driver.findElement(By.cssSelector("span.my-journal-card-bookmark-icon.general-sprites")).click();
			Thread.sleep(2000);
			int anyPost = driver.findElements(By.className("journal-card-title")).size();
			if(anyPost > 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark from a resource from my journal page it doesn't get removed from my journal.");
			}
			//goto lesson 
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().navigateToResourceTab();//go to resource tab

			System.out.println("resoursename: "+resourcesname);
			String appendChar5=null;
			if(System.getProperty("UCHAR")==null)
			{
				appendChar5=LoginUsingLTI.appendChar;
			}
			else {
				appendChar5=System.getProperty("UCHAR");
			}
			String actualResources5= resourcesname+appendChar5;
			/*WebElement element5=driver.findElement(By.xpath("//a[text()='"+actualResources5+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element5);
			int resindex5 = 0;
			List<WebElement> resources5 = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(WebElement res : resources5)
			{
				System.out.println("Resource Name: "+res.getText());
				if(res.getText().contains(resourcesname))
				{
					break;
				}
				resindex5++;
			}
			System.out.println("resindex"+resindex5);*/
			int resindex5 = 0;
			List<WebElement> resources5 = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(int a=0;a<resources5.size();a++){
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resources5.get(a));
				if((resources5.get(a).getText().equals(actualResources5))){
					System.out.println("actualResources5:"+actualResources5);
					break;
				}
				resindex5++;
			}
			System.out.println("resindex5: "+resindex5);
			List<WebElement> allOpenLink5 = driver.findElements(By.xpath("//div[@class='ls-right-hover-elements']/a[2]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOpenLink5.get(resindex5));
			Thread.sleep(2000);

			boolean isBookmarked5 = new Bookmark().isBookmarked("resource");
			if(isBookmarked5 == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark from a resource from my journal  it doesn't turn grey in lesson page.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookmarkResource in class BookMarkVerify",e);
		}
	}
	@Test(priority = 4, enabled = true)
	public void likeCommentOnResource()
	{
		try
		{
			new ResourseCreate().resourseCreate(69, 0);
			new LoginUsingLTI().ltiLogin("69");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().openFirstResourceFromResourceTab(0);//open recently added resource
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
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().navigateToResourceTab();//go to resource tab
			new Navigator().openResourceFromResourceTabFromCMS(69);
			new Bookmark().bookmark("resource");
			new Navigator().NavigateTo("Course Stream"); // go to Course Stream
			String str = driver.findElement(By.cssSelector("span[class='ls-stream-post__action']")).getText();
			if(!str.contains("added a comment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'added a comment' word is absent in course stream after bookmarking a resource.");
			}
			//check whether the star is yellow or not.
			String isYellow = driver.findElement(By.cssSelector("i[class='ls-icon-img ls--star-icon']")).getCssValue("background-position");
			if(!isYellow.equals("-94px -19px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking a resource the star icon in course stream is not yellow.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().openResourceFromResourceTabFromCMS(69);
			new Bookmark().unbookmark("resource");//unbookmark
			new Navigator().NavigateTo("Course Stream"); // go to Course Stream
			String isYellow1 = driver.findElement(By.cssSelector("i[class='ls-icon-img ls--star-icon']")).getCssValue("background-position");
			if(isYellow1.equals("-94px -38px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing the bookmark for a resource the star in Course Stream still appreas yellow.");
			}
			new LoginUsingLTI().ltiLogin("69_1");//login as another student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().navigateToResourceTab();//go to resource tab
			new Navigator().NavigateTo("Course Stream"); // go to Course Stream
			//check whether the star is yellow or not.
			String isGrey = driver.findElement(By.cssSelector("i[class='ls-icon-img ls--star-icon']")).getCssValue("background-position");
			if(isGrey.equals("-94px -38px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The activities of other student in Course Stream remain in grey.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase likeCommentOnResource in class BookMarkVerify",e);
		}
	}
	@Test(priority = 5, enabled = true)
	public void likeCommentAndClickStarIconFromCourseStream()
	{
		try
		{
			String resourcesname = ReadTestData.readDataByTagName("", "resourcesname", Integer.toString(74));

            new ResourseCreate().resourseCreate(74, 0);
			new LoginUsingLTI().ltiLogin("74");
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
			new CommentOnPost().commentOnPost(new RandomString().randomstring(5), 1);//comment on resource
			new Navigator().NavigateTo("Course Stream"); // go to Course Stream
			new Bookmark().bookmarkFromCourseStream();
			String color = new Bookmark().colorOfStarInCourseStream();
			if(!color.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Color of the star is not yellow after bookmaring is done on Course Stream.");
			}
			//check whether Course Stream name get updated or not
			String str = driver.findElement(By.cssSelector("span[class='ls-stream-post__action']")).getText();
			if(str.contains("bookmarked this"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking resource from Course Stream the course stream entry gets updated.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().navigateToResourceTab();//go to resource tab
            Thread.sleep(5000);
			/*WebElement element1=driver.findElement(By.xpath("//a[text()='"+resourcesname+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element1);
			int resindex1 = 0;
			List<WebElement> resources1 = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(WebElement res : resources1)
			{
				System.out.println("Resource Name: "+res.getText());
                System.out.println("resourcesname:"+resourcesname);
                if(res.getText().trim().contains(resourcesname))
				{
					break;
				}
				resindex1++;
			}
			System.out.println("resindex"+resindex1);*/
			int resindex1 = 0;
			List<WebElement> resources1 = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(int a=0;a<resources1.size();a++){
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resources1.get(a));
				if((resources1.get(a).getText().equals(resourcesname))){
					System.out.println("resourcesname:"+resourcesname);
					break;
				}
				resindex1++;
			}
			System.out.println("resindex1: "+resindex1);
			List<WebElement> allOpenLink1 = driver.findElements(By.xpath("//div[@class='ls-right-hover-elements']/a[2]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOpenLink1.get(resindex1));
			Thread.sleep(5000);

			boolean isYellow = new Bookmark().isBookmarked("resource");
			if(isYellow == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking resource from Course Stream the star symbol for the same resource in eTextbook doesnt appear yellow.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isPresent = new Bookmark().isPresentInMyJournal("resource");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking resource from Course Stream its not added to My Journal.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase likeCommentAndClickStarIconFromCourseStream in class BookMarkVerify",e);
		}
	}
	@Test(priority = 6, enabled = true)
	public void likeCommentAndClickStarIconFromEtextBook()
	{
		try
		{
			new ResourseCreate().resourseCreate(79, 0);
			new LoginUsingLTI().ltiLogin("79");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().navigateToResourceTab();//go to resource tab
			new Navigator().openResourceFromResourceTabFromCMS(69);
		//	new Navigator().openResourceFromResourceTab(79);//open recently added resource
			new Bookmark().bookmark("resource");
			new AssignmentSocialElement().clickonlike(1);//click to like
			new CommentOnPost().commentOnPost("Comment on resource", 1);//comment on resource
			new Navigator().NavigateTo("Course Stream"); // go to Course Stream
			String color = new Bookmark().colorOfStarInCourseStream();
			if(!color.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Color of the star is not yellow in Course Stream after bookmaring is done on eTextbook.");
			}
			new Bookmark().unbookmarkFromCourseStream();
			String color1 = new Bookmark().colorOfStarInCourseStream();
			if(color1.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Color of the star doesnt turn grey in Course Stream after unbookmaring is done from Course Stream.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isEmpty = new Bookmark().isMyJournalEmpty();
			if(isEmpty == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark from Course Stream the entry is not removed from My Journal.");
			}
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Navigator().navigateToResourceTab();//go to resource tab
			new Navigator().openResourceFromResourceTabFromCMS(69);
		//	new Navigator().openResourceFromResourceTab(79);//open recently added resource
			boolean isBookmarked = new Bookmark().isBookmarked("resource");
			if(isBookmarked == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After removing bookmark from Course Stream the star symbol in eTextbook is yellow.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase likeCommentAndClickStarIconFromEtextBook in class BookMarkVerify",e);
		}
	}
	@Test(priority = 7, enabled = true)
	public void bookMarkImageWidgetAndLike()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("46");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("widget");
			driver.findElement(By.cssSelector("a[title='Like']")).click();	//like on the widget;
			Thread.sleep(2000);
			new Navigator().NavigateTo("Course Stream");
			boolean isPresent = new Bookmark().isPresentInCourseStream("widget");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking widget and then hiting like from lesson, the entry is not added to Course Stream .");
			}
			String color = new Bookmark().colorOfStarInCourseStream();
			if(!color.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking widget and then hiting like from lesson, the * icon color in Course Stream is not yellow.");
			}
			new Bookmark().unbookmarkFromCourseStream();
			String color1 = new Bookmark().colorOfStarInCourseStream();
			if(color1.equals("yellow"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking widget from course stream, the * icon color in Course Stream is not grey.");
			}
			new Navigator().NavigateTo("My Journal");//go to My Journal
			boolean isRemoved = new Bookmark().isMyJournalEmpty();
			if(isRemoved == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After unbookmarking widget from course stream, the * icon color in Course Stream is not grey.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookMarkImageWidgetAndLike in class BookMarkVerify",e);
		}
	}

}
