package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;

public class Widget extends Driver {

    public void openWidgetFromAssignmentPage()
    {
		WebDriver driver=Driver.getWebDriver();
        try
        {
            new Navigator().NavigateTo("Assignments");	//navigate to Assignments
            driver.findElement(By.cssSelector("span[class='learning-activity-title']")).click();	//click on DW assignment
        }
        catch (Exception e)
        {
            Assert.fail("Exception while opening discussion widget from Assignments page",e);
        }
    }

	public void createChapterWidget(int dataIndex)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
		String course = Config.course;
		String chapterName = ReadTestData.readDataByTagName("tocdata", "chapterName", "1");
		//String lessonName = ReadTestData.readDataByTagName("tocdata", "lessonName", "1");
		//String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
		String lessonName = ReadTestData.readDataByTagName("", "lessonName", Integer.toString(dataIndex));
		String numberoftabs = ReadTestData.readDataByTagName("", "numberoftabs", Integer.toString(dataIndex));
		new DirectLogin().CMSLogin();
		Thread.sleep(5000);
		String title=driver.getTitle();
		if(title.equals("Course Content Details"))
			{
			 driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click(); //clicking on course
			 if(chapterName == null)
			 {
				 driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
			 }
			 else
			 {
				 List <WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
				 for(WebElement chapters: allChapters)
				 {
					 if(chapters.getText().equals(chapterName))
					 {
						 chapters.click();
						 break;
					 }
					 
				 }	
			 }
			//
				 List <WebElement> allLesson = driver.findElements(By.xpath("//*[starts-with(@class, 'collection-lesson-name')]"));
				 for(WebElement lesson: allLesson)
				 {
					 if(lesson.getText().contains("lesson"))
					 {
						 Locatable hoverItem1 = (Locatable) lesson;
						 Mouse mouse1 = ((HasInputDevices) driver).getMouse();
						 mouse1.mouseMove(hoverItem1.getCoordinates());
						 Thread.sleep(3000);
						 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='delete-category']"))); //click on cross mark
						 //driver.findElement(By.cssSelector("span[class='delete-category']")).click();
						 Thread.sleep(3000);
						 //click on the pop up to delete
						 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.cms-notification-message-ignore-changes.cms-notification-message-delete-associated-content > span")));
						 //driver.findElement(By.cssSelector("span.cms-notification-message-ignore-changes.cms-notification-message-delete-associated-content > span")).click();
						 Thread.sleep(3000);
						 List <WebElement> allLesson1 = driver.findElements(By.xpath("//*[starts-with(@class, 'collection-lesson-name')]"));
						 for(WebElement lesson1: allLesson1)
						 {
							 if(lesson1.getText().contains("lesson"))
								 Assert.fail("Unable to delete the recently added lesson from CMS.");
							 
						 }
					 }
				 }	
				 
					 
			//	 
			 
			 driver.findElement(By.cssSelector("div.create-lesson")).click(); //Clicking on create lesson link in the footer
			 Thread.sleep(3000);
			 driver.findElement(By.id("addLesson")).sendKeys(lessonName); // Entering lesson name
			 driver.findElement(By.id("add-lesson-to-subtopic")).click(); //Clicking on Create Button
			 Thread.sleep(3000);
			 WebElement box =  driver.findElement(By.cssSelector("div[class='page selectedpage']")); //right click on create lesson page
			 Actions clicker = new Actions(driver);
			 clicker.contextClick(box).sendKeys(Keys.ESCAPE).perform();			 
			 Thread.sleep(2000);
			 driver.findElement(By.id("discussion")).click(); //selecting discussion option on right clicking
			 Thread.sleep(3000);		 
			int textindex  = 2;
			 for(int i=0;i<Integer.parseInt(numberoftabs);i++)
			 {
			 Thread.sleep(3000);
			 List<WebElement> widgetdefaulttext = driver.findElements(By.className("widget-content"));
			 widgetdefaulttext.get(textindex).click(); //Clicking on default text of the discussion widget
			 Thread.sleep(3000);
			 if(i==0)
			 widgetdefaulttext.get(textindex).click(); //Clicking on default text of the discussion widget
			 Thread.sleep(3000);
			 WebElement t=driver.findElement(By.className("text-iframe"));
			 driver.switchTo().frame(t) ;
			 driver.findElement(By.xpath("/html/body")).sendKeys("Text on tab"+(i+1));

			 driver.switchTo().defaultContent();
			
			 if(i != 2) //Preventing one extra tab to get opened
			 driver.findElement(By.className("discussion-widget-publisher-addTab")).click();
			 textindex++;
			 }
			 driver.findElement(By.className("discussion-widget-publisher-tabs")).click();
			 Thread.sleep(3000);
			 new ComboBox().selectValue(3, "Publish"); //Publishing
			 driver.findElement(By.id("saveQuestionDetails1")).click(); //Clicking on save button
			 
			} //if condition for title of the page ends
		else
		{
			Assert.fail("Login to  failed");
		}
		} //Method ends
		catch(Exception e)
		{
			Assert.fail("Exception in app helper createChapterWidget in class Widget",e);
		}
	}
	
	public void perspectiveAdd()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
		String random = new RandomString().randomstring(5);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
		Thread.sleep(3000);
		List<WebElement> perspectives = driver.findElements(By.name("perspective"));
		for(WebElement perspective : perspectives)
		{
			if(perspective.isDisplayed())
			{
				perspective.sendKeys(random); //entering text in the perspective textarea which is currently displayed
			}
		}
			List<WebElement> perspectivesPost = driver.findElements(By.className("post-perspective"));
			for(WebElement perspective : perspectivesPost)
			{
				if(perspective.isDisplayed()) {
					Thread.sleep(2000);
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", perspective);
					break;
				}
			}

		//Validating the added perspective
		boolean perspectivefound = false;
		List<WebElement> perspectivetexts = driver.findElements(By.className("ls-comment-entry"));
		for(WebElement perspectivetext : perspectivetexts)
		{
			System.out.println("perspectivetext::"+perspectivetext.getText());
			if(perspectivetext.getText().equals(random))
			{
				perspectivefound = true;
				break;
			}
		}
			if(perspectivefound == false)
				Assert.fail("Perspective not added successfully");
		}
		catch(Exception e)
		{
		Assert.fail("Exception in app helper perspectiveAdd in class Widget",e);
		}
	}
	
	public void navigateToTab(int zerobasedTabIndex)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
		List<WebElement> widgettabs = driver.findElements(By.className("ls-publisher-tab"));  //Finding all tabs
		widgettabs.get(zerobasedTabIndex).click(); //clicking on a particular tab as per the index passed
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper navigateToTab in class Widget",e);
		}
	}
	
	public void commentonPerspective(int zerobasedIndexOfCommentsLink)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
		String random = new RandomString().randomstring(5);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 250)"); //y value '250' can be altered
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives")));
		List<WebElement> commentlink = driver.findElements(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']"));
		commentlink.get(zerobasedIndexOfCommentsLink).click();
		driver.findElement(By.name("perspective-comment")).sendKeys(random+Keys.ENTER);
		Thread.sleep(3000);
		boolean commentfound = false;
		List<WebElement> comments = driver.findElements(By.className("ls-perspctive-comments-posted"));
		for(WebElement comment:comments)
		{
			if(comment.getText().equals(random))
			{
				commentfound = true;
				break;
			}
		}
		if(commentfound == false)
			Assert.fail("Comment not posted on perspective successfully");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper commentonPerspective in class Widget",e);
		}

	}

	public void widgetOptionsVerify(int dataIndex)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			String course = ReadTestData.readDataByTagName("", "course", Integer.toString(dataIndex));
			String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
			String lessonName = ReadTestData.readDataByTagName("", "lessonName", Integer.toString(dataIndex));
			
			driver.get(Config.baseURL);
			driver.findElement(By.id("username")).sendKeys("lspaces1");
			driver.findElement(By.id("password")).sendKeys("snapwiz");
			driver.findElement(By.id("loginSubmitBtn")).click();
			Thread.sleep(5000);
			String title=driver.getTitle();
			if(title.equals("Course Content Details"))
			{
				 driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click(); //clicking on course
				 List <WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
				 for(WebElement chapters: allChapters)
				 {
					 if(chapters.getText().equals(chapterName))
					 {
						 chapters.click();
						 break;
					 }
					 
				 }			 
			 
			 driver.findElement(By.cssSelector("div.create-lesson")).click(); //Clicking on create lesson link in the footer
			 Thread.sleep(3000);
			 driver.findElement(By.id("addLesson")).sendKeys(lessonName); // Entering lesson name
			 driver.findElement(By.id("add-lesson-to-subtopic")).click(); //Clicking on Create Button
			 Thread.sleep(3000);
			 WebElement box =  driver.findElement(By.cssSelector("div[class='page selectedpage']")); //right click on create lesson page
			 Actions clicker = new Actions(driver);
			 clicker.contextClick(box).sendKeys(Keys.ESCAPE).perform();			 
			 Thread.sleep(2000);
				
			 String widgetOptions = driver.findElement(By.className("context-menu-inner")).getText();
			 String [] widgetOption = widgetOptions.split("\\n");
			 if(!widgetOption[0].equals("+ Discussion")) Assert.fail("Option 1 not found as + Discussion on right clicking the lesson content area for adding a new lesson widget");
			 if(!widgetOption[1].equals("+ Video")) Assert.fail("Option 2 not found as + Video on right clicking the lesson content area for adding a new lesson widget");
			 if(!widgetOption[2].equals("+ Quiz")) Assert.fail("Option 3 not found as + Quiz on right clicking the lesson content area for adding a new lesson widget");
			 if(!widgetOption[3].equals("+ Text")) Assert.fail("Option 4 not found as + Text on right clicking the lesson content area for adding a new lesson widget");
			 if(!widgetOption[4].equals("+ Image")) Assert.fail("Option 5 not found as + Image on right clicking the lesson content area for adding a new lesson widget");
			 if(!widgetOption[5].equals("+ Flash")) Assert.fail("Option 6 not found as + Flash on right clicking the lesson content area for adding a new lesson widget");
			 if(!widgetOption[6].equals("+ Table of Contents")) Assert.fail("Option 7 not found as + Table of Contents on right clicking the lesson content area for adding a new lesson widget");
			 if(!widgetOption[7].equals("+ Audio")) Assert.fail("Option 8 not found as + Table of Contents on right clicking the lesson content area for adding a new lesson widget");
			 if(!widgetOption[8].equals("+ Page")) Assert.fail("Option 9 not found as + Page on right clicking the lesson content area for adding a new lesson widget");
			}
			else
				Assert.fail("Login to CMS failed");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper widgetOptionsVerify in class Widget",e);
		}
	}
public void createWidgetAtTopicLevel(int dataIndex)
{
	WebDriver driver=Driver.getWebDriver();
	try
	{
		String course = Config.course;
		String chapterName = ReadTestData.readDataByTagName("tocdata", "chapterName", "1");
		//String lessonName = ReadTestData.readDataByTagName("tocdata", "lessonName", "1");
		String topicname = ReadTestData.readDataByTagName("", "topicname", Integer.toString(dataIndex));
		String lessonName = ReadTestData.readDataByTagName("", "lessonName", Integer.toString(dataIndex));
		String numberoftabs = ReadTestData.readDataByTagName("", "numberoftabs", Integer.toString(dataIndex));
		new DirectLogin().CMSLogin();
		/*driver.get(Config.baseURL);
		driver.findElement(By.id("username")).sendKeys("lspaces2");
		driver.findElement(By.id("password")).sendKeys("snapwiz");
		driver.findElement(By.id("loginSubmitBtn")).click();
		Thread.sleep(5000);*/
		String title=driver.getTitle();
		if(title.equals("Course Content Details"))
		{
		 driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
		 int index = 0;
		 //Find the chapter index
		 List <WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
		 for(WebElement element : allChapters)
		 {
			 if(element.getText().equals(chapterName))
					 {
				 		break;
					 }
			 index++;
		 }
		//Find the topic under a chapter and click on it
		 List <WebElement> expansionSymbol = driver.findElements(By.xpath("//*[starts-with(@class, 'expand-chapter-tree expand')]"));
		 expansionSymbol.get(index).click();
		 Thread.sleep(3000);
		 //List the topic and click on a topic
		 List <WebElement> allTopic = driver.findElements(By.xpath("//*[starts-with(@class, 'course-topic-label node')]"));
		 for(WebElement topic: allTopic)
		 {
			 if(topic.getText().equals(topicname))
			 {
				// topic.click();
				 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", topic); //click on topic
				 Thread.sleep(3000);
				 break;
			 }
			 
		 }
		 //
		 List <WebElement> allLesson = driver.findElements(By.xpath("//*[starts-with(@class, 'collection-lesson-name')]"));
		 for(WebElement lesson: allLesson)
		 {
			 if(lesson.getText().contains("lesson") || lesson.getText().length()>0)
			 {
				 Locatable hoverItem1 = (Locatable) lesson;
				 Mouse mouse1 = ((HasInputDevices) driver).getMouse();
				 mouse1.mouseMove(hoverItem1.getCoordinates());
				 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='delete-category']"))); //click on cross mark
				 //driver.findElement(By.cssSelector("span[class='delete-category']")).click();
				 Thread.sleep(3000);
				 //click on the pop up to delete
				 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.cms-notification-message-ignore-changes.cms-notification-message-delete-associated-content > span")));
				 //driver.findElement(By.cssSelector("span.cms-notification-message-ignore-changes.cms-notification-message-delete-associated-content > span")).click();
				 Thread.sleep(3000);
				 
			 }
		 }		 
		 List <WebElement> allLesson1 = driver.findElements(By.xpath("//*[starts-with(@class, 'collection-lesson-name')]"));
		 for(WebElement lesson1: allLesson1)
		 {
			 if(lesson1.getText().contains("lesson"))
				 Assert.fail("Unable to delete the recently added lesson from CMS.");
			 
		 }	 
	//	 	 
		 driver.findElement(By.cssSelector("div.create-lesson")).click(); //Clicking on create lesson link in the footer
		 Thread.sleep(3000);
		 driver.findElement(By.id("addLesson")).sendKeys(lessonName); // Entering lesson name
		 driver.findElement(By.cssSelector("span[id='add-lesson-to-subtopic']")).click();
		 Thread.sleep(3000);
		 WebElement box =  driver.findElement(By.cssSelector("div[class='page selectedpage']")); //right click on create lesson page
		 Actions clicker = new Actions(driver);
		 clicker.contextClick(box).sendKeys(Keys.ESCAPE).perform();			 
		 Thread.sleep(2000);
		 driver.findElement(By.id("discussion")).click(); //selecting discussion option on right clicking
		 Thread.sleep(3000);		 
		int textindex  = 2;
		 for(int i=0;i<Integer.parseInt(numberoftabs);i++)
		 {
		 Thread.sleep(3000);
		 List<WebElement> widgetdefaulttext = driver.findElements(By.className("widget-content"));
		 widgetdefaulttext.get(textindex).click(); //Clicking on default text of the discussion widget
		 Thread.sleep(3000);
		 if(i==0)
		 widgetdefaulttext.get(textindex).click(); //Clicking on default text of the discussion widget
		 Thread.sleep(3000);
		 WebElement t=driver.findElement(By.className("text-iframe"));
		 driver.switchTo().frame(t) ;
		 driver.findElement(By.xpath("/html/body")).sendKeys("Text on tab"+(i+1));
		 driver.switchTo().defaultContent();
		
		 if(i != 2) //Preventing one extra tab to get opened
		 driver.findElement(By.className("discussion-widget-publisher-addTab")).click();
		 textindex++;
		 }
		 driver.findElement(By.className("discussion-widget-publisher-tabs")).click();
		 Thread.sleep(3000);
		 new ComboBox().selectValue(3, "Draft"); //Publishing
		 driver.findElement(By.id("saveQuestionDetails1")).click(); //Clicking on save button
	  }
		else
		{
			Assert.fail("Login to CMS failed");
		}
	}
	catch(Exception e)
	{
		Assert.fail("Exception in createLessonAtTopicLevel in Widget class.",e);
	}
}
public void navigateToDiscussionTab(int zerobasedTabIndex)
{
	WebDriver driver=Driver.getWebDriver();
	try
	{
	List<WebElement> widgettabs = driver.findElements(By.cssSelector("span[class='publisher-count ls-publisherIcons-bg ls-publisher-count-bg-holder']"));  //Finding all tabs
	widgettabs.get(zerobasedTabIndex).click(); //clicking on a particular tab as per the index passed
	Thread.sleep(3000);
	}
	catch(Exception e)
	{
		Assert.fail("Exception in app helper navigateToTab in class Widget",e);
	}
}
//click on like of disscussion
public void clickonlikeofdisscussion(int liketoclick)
{
	WebDriver driver=Driver.getWebDriver();
	try
	{
		List<WebElement> alllike=driver.findElements(By.cssSelector("span[class='ls-discussion-like-link']"));
		int index=0;
		for(WebElement like:alllike)
		{
			if(index==liketoclick)
			{
				if(!like.getText().equals("Like"))
					Assert.fail("initialy value is not like");
				like.click();
				Thread.sleep(2000);
				if(!like.getText().equals("Unlike"))
					Assert.fail("after click on like its noyt converted to unlike");
				
				break;
			}
			index++;
		}
		
	}
	catch(Exception e)
	{
		Assert.fail("Exception in app helper clickonlikeofdisscussion in class Widget",e);
	}
}

public void clickonUnlikeofdisscussion(int liketoclick)
{
	WebDriver driver=Driver.getWebDriver();
	try
	{
		List<WebElement> alllike=driver.findElements(By.cssSelector("span[class='ls-discussion-like-link']"));
		int index=0;
		for(WebElement like:alllike)
		{
			if(index==liketoclick)
			{
				if(!like.getText().equals("Unlike"))
					Assert.fail("initialy value is not like");
				like.click();
				Thread.sleep(2000);
				if(!like.getText().equals("Like"))
					Assert.fail("after click on like its noyt converted to unlike");
				
				break;
			}
			index++;
		}
		
	}
	catch(Exception e)
	{
		Assert.fail("Exception in app helper clickonlikeofdisscussion in class Widget",e);
	}
}
//return like count od dissussion
public int Countoflikeofdisscussion(int liketocount)
{
	int likecount=0;
	WebDriver driver=Driver.getWebDriver();
	try
	{
		List<WebElement> alllike=driver.findElements(By.cssSelector("span[class='ls-comment-like-count']"));
		int index=0;
		for(WebElement like:alllike)
		{
			String likenumber=like.getText();
			if(index==liketocount)
			{
				likecount=Integer.parseInt(likenumber);
				break;
			}
			index++;
		}
		
	}
	catch(Exception e)
	{
		Assert.fail("Exception in app helper Countoflikeofdisscussion in class Widget",e);
	}
	return likecount;
}

//click on presepective like
public void clickonlikeofperspective(int liketoclick)
{
	WebDriver driver=Driver.getWebDriver();
	try
	{
		List<WebElement> alllike=driver.findElements(By.cssSelector("span[class='ls-post-like-link']"));
		int index=0;
		for(WebElement like:alllike)
		{
			if(index==liketoclick)
			{
				if(!like.getText().equals("Like"))
					Assert.fail("initialy value is not like");
				like.click();
				Thread.sleep(2000);
				if(!like.getText().equals("Unlike"))
					Assert.fail("after click on like its noyt converted to unlike");
				break;
			}
			index++;
		}
		
	}
	catch(Exception e)
	{
		Assert.fail("Exception in app helper clickonlikeofperspective in class Widget",e);
	}
}

public void clickonUnlikeofperspective(int liketoclick)
{
	WebDriver driver=Driver.getWebDriver();
	try
	{
		List<WebElement> alllike=driver.findElements(By.cssSelector("span[class='ls-post-like-link']"));
		int index=0;
		for(WebElement like:alllike)
		{
			if(index==liketoclick)
			{
				if(!like.getText().equals("Unlike"))
					Assert.fail("initialy value is not like");
				like.click();
				Thread.sleep(2000);
				if(!like.getText().equals("Like"))
					Assert.fail("after click on like its noyt converted to unlike");
				break;
			}
			index++;
		}
		
	}
	catch(Exception e)
	{
		Assert.fail("Exception in app helper clickonUnlikeofperspective in class Widget",e);
	}
}

//click on persective comment like
public void clickonlikeofperspectivecomment(int liketoclick)
{
	WebDriver driver=Driver.getWebDriver();
	try
	{
		List<WebElement> alllike=driver.findElements(By.cssSelector("span[class='ls-comment-like-link']"));
		int index=0;
		for(WebElement like:alllike)
		{
			if(index==liketoclick)
			{
				if(!like.getText().equals("Like"))
					Assert.fail("initialy value is not like");
				like.click();
				Thread.sleep(2000);
				if(!like.getText().equals("Unlike"))
					Assert.fail("after click on like its noyt converted to unlike");								
				break;
			}
			index++;
		}
		
	}
	catch(Exception e)
	{
		Assert.fail("Exception in app helper clickonlikeofperspectivecomment in class Widget",e);
	}
}
	public void clickonUnlikeofperspectivecomment(int liketoclick)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> alllike=driver.findElements(By.cssSelector("span[class='ls-comment-like-link']"));
			int index=0;
			for(WebElement like:alllike)
			{
				if(index==liketoclick)
				{
					if(!like.getText().equals("Unlike"))
						Assert.fail("initialy value is not like");
					like.click();
					Thread.sleep(2000);
					if(!like.getText().equals("Like"))
						Assert.fail("after click on like its noyt converted to unlike");								
					break;
				}
				index++;
			}
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper clickonUnlikeofperspectivecomment in class Widget",e);
		}
}
	
	public void prospectivecomment(int prespectivetocomment,int numberofcomment)
	{
		String random = new RandomString().randomstring(5);
		try
		{
			WebDriver driver=Driver.getWebDriver();
			
			//((JavascriptExecutor)driver).executeScript("window.scrollTo(0,500)");
			//Thread.sleep(2000);
			//driver.findElement(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")).click();
			//Thread.sleep(2000);
			
				List<WebElement> allpresctive=driver.findElements(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']"));
			
			
			int index=0;
			for(WebElement prespectivecomment:allpresctive)
			{
				if(index==prespectivetocomment)
				{
					((JavascriptExecutor)driver).executeScript("window.scrollTo(0,500)");
					Thread.sleep(2000);
					prespectivecomment.click();					
					Thread.sleep(2000);
					for(int i=1;i<=numberofcomment;i++)
					{
						
						driver.switchTo().activeElement().sendKeys(random+Keys.ENTER);
					}
					break;
				}
				index++;
			}
			Thread.sleep(3000);
			boolean commentfound = false;
			List<WebElement> comments = driver.findElements(By.className("ls-perspctive-comments-posted"));
			for(WebElement comment:comments)
			{
				if(comment.getText().equals(random))
				{
					commentfound = true;
					break;
				}
			}
			if(commentfound == false)
				Assert.fail("Comment not posted on perspective successfully");
					
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper prospectivecomment in class Widget",e);
		}
		
	}
	
	public void AddPerspectivemorethanone(int numberofprespective)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
		String random = new RandomString().randomstring(5);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
		List<WebElement> perspectives = driver.findElements(By.name("perspective"));
		for(WebElement perspective : perspectives)
		{
			if(perspective.isDisplayed())
			{
				for(int i=1;i<=numberofprespective;i++)
				{
					perspective.sendKeys(random+Keys.ENTER); //entering text in the perspective textarea which is currently displayed
				}
			}
		}
		Thread.sleep(2000);
		//Validating the added perspective
		boolean perspectivefound = false;
		List<WebElement> perspectivetexts = driver.findElements(By.className("ls-comment-entry"));
		for(WebElement perspectivetext : perspectivetexts)
		{
			System.out.println(perspectivetext.getText());
			if(perspectivetext.getText().equals(random))
			{
				perspectivefound = true;
				break;
			}
		}
			if(perspectivefound == false)
				Assert.fail("Perspective not added successfully");
		}
		catch(Exception e)
		{
		Assert.fail("Exception in app helper AddPerspectivemorethanone in class Widget",e);
		}
	}

	public void createDiscussionWidget(int dataIndex)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
		String course = Config.course;
		String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
		String lessonName = ReadTestData.readDataByTagName("", "lessonName", Integer.toString(dataIndex));
		String textondiscussiontab = ReadTestData.readDataByTagName("", "textondiscussiontab ", Integer.toString(dataIndex));
		String numberoftabs = ReadTestData.readDataByTagName("", "numberoftabs", Integer.toString(dataIndex));
		new DirectLogin().CMSLogin();
		Thread.sleep(5000);
		String title=driver.getTitle();
		if(title.equals("Course Content Details"))
		{
			
			driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click(); //clicking on course
			if(chapterName == null)
			{
				driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
			}
			else
			{
				List <WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
				for(WebElement chapters: allChapters)
				{
					
					if(chapters.getText().contains(chapterName))
					{
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters);
						break;
					}

				}	
			}
			driver.findElement(By.cssSelector("div.create-lesson")).click(); //Clicking on create lesson link in the footer
			Thread.sleep(3000);
			driver.findElement(By.id("addLesson")).sendKeys(lessonName); // Entering lesson name
			driver.findElement(By.id("add-lesson-to-subtopic")).click(); //Clicking on Create Button
			Thread.sleep(3000);
			WebElement box =  driver.findElement(By.cssSelector("div[class='page selectedpage']")); //right click on create lesson page
			Actions clicker = new Actions(driver);
			clicker.contextClick(box).sendKeys(Keys.ESCAPE).perform();			 
			Thread.sleep(2000);
			driver.findElement(By.id("discussion")).click(); //selecting discussion option on right clicking
			Thread.sleep(3000);		 
			int textindex  = 2;
			for(int i=0;i<Integer.parseInt(numberoftabs);i++)
			{
				Thread.sleep(3000);
				List<WebElement> widgetdefaulttext = driver.findElements(By.className("widget-content"));
				widgetdefaulttext.get(textindex).click(); //Clicking on default text of the discussion widget
				Thread.sleep(3000);
				if(i==0)
					widgetdefaulttext.get(textindex).click(); //Clicking on default text of the discussion widget
				Thread.sleep(3000);
				WebElement t=driver.findElement(By.className("text-iframe"));
				driver.switchTo().frame(t) ;
				driver.findElement(By.xpath("/html/body")).sendKeys(textondiscussiontab+(i+1));

				driver.switchTo().defaultContent();

				if(i != 2) //Preventing one extra tab to get opened
					driver.findElement(By.className("discussion-widget-publisher-addTab")).click();
				textindex++;
			}
			driver.findElement(By.className("discussion-widget-publisher-tabs")).click();
			Thread.sleep(3000);
			new ComboBox().selectValue(3, "Publish"); //Publishing
			driver.findElement(By.id("saveQuestionDetails1")).click(); //Clicking on save button

		} //if condition for title of the page ends
		else
		{
			Assert.fail("Login to  failed");
		}
		} //Method ends
		catch(Exception e)
		{
			Assert.fail("Exception in app helper createDiscussionWidget in class Widget",e);
		}
	}

    public void addRemoveToCart(String widgetClassName,String action)
    {
		WebDriver driver=Driver.getWebDriver();
        try
        {
            /*WebElement we=driver.findElement(By.cssSelector("img[class='"+widgetClassName+"']"));//fetch image to hover
            Actions ac=new Actions(driver);
            ac.moveToElement(we).build().perform();*/
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.className("assign-options")));
            Thread.sleep(2000);
            if(action.equalsIgnoreCase("addtocart"))
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.className("add-assignment-cart-text")));
            if(action.equalsIgnoreCase("removefromcart"))
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.className("remove-assignment-cart-text")));

        }
        catch(Exception e)
        {
            Assert.fail("Exception while adding widget to cart",e);
        }
    }

}
