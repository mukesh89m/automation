package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;


public class PublisherAbleToSupportADiscussionWidget extends Driver {
	@Test
	public void publisherAbleToSupportADiscussionWidget()
	{
		try
		{
			new DirectLogin().CMSLogin();
			String course = Config.course;;
			String chapterName = ReadTestData.readDataByTagName("tocdata", "chapterName", "1");
			String topicname = ReadTestData.readDataByTagName("PublisherAbleToSupportADiscussionWidget", "topicname", "1934");
			String lessonname = ReadTestData.readDataByTagName("tocdata", "lessonName", "1");
			String numberoftabs = ReadTestData.readDataByTagName("PublisherAbleToSupportADiscussionWidget", "numberoftabs", "1934");
			Thread.sleep(5000);
			/*
			 * Create a lesson in DRAFT status
			 */
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
					 topic.click();
					 Thread.sleep(3000);
					 break;
				 }
				 
			 }
			 
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
			 
			 driver.findElement(By.cssSelector("div.create-lesson")).click(); //Clicking on create lesson link in the footer
			 Thread.sleep(3000);
			 driver.findElement(By.id("addLesson")).sendKeys(lessonname); // Entering lesson name
			 driver.findElement(By.id("add-lesson-to-subtopic")).click(); //Clicking on Create Button
			 Thread.sleep(3000);
			 new ComboBox().selectValue(3, "Draft"); //in draft status
			 driver.findElement(By.id("saveQuestionDetails1")).click(); //Clicking on save button
			//again login to CMS
			 new DirectLogin().CMSLogin();
			 String title1=driver.getTitle();
				if(title1.equals("Course Content Details"))
				{
				 driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
				 int index1 = 0;
				 //Find the chapter index
				 List <WebElement> allChapters1 = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
				 for(WebElement element : allChapters1)
				 {
					 if(element.getText().equals(chapterName))
							 {
						 		break;
							 }
					 index1++;
				 }
				//Find the topic under a chapter and click on it
				 List <WebElement> expansionSymbol1 = driver.findElements(By.xpath("//*[starts-with(@class, 'expand-chapter-tree expand')]"));
				 expansionSymbol1.get(index1).click();
				 Thread.sleep(3000);
				 //List the topic and click on a topic
				 List <WebElement> allTopic1 = driver.findElements(By.xpath("//*[starts-with(@class, 'course-topic-label node')]"));
				 for(WebElement topic: allTopic1)
				 {
					 if(topic.getText().equals(topicname))
					 {
						 topic.click();
						 Thread.sleep(3000);
						 break;
					 }
					 
				 }
			//click on the particular lesson
		    driver.findElement(By.cssSelector("div[title='"+lessonname+"']")).click();
		    Thread.sleep(3000);
			WebElement box =  driver.findElement(By.cssSelector("div[class='page selectedpage']")); //right click on create lesson page
			 Actions clicker = new Actions(driver);
			 clicker.contextClick(box).sendKeys(Keys.ESCAPE).perform();			 
			 Thread.sleep(2000);
			 driver.findElement(By.id("discussion")).click(); //selecting discussion option on right clicking
			 Thread.sleep(3000);
			 //verifying plus icon near tab
			 String plusIcon = driver.findElement(By.cssSelector("span[class='discussion-widget-publisherIcons-bg discussion-widget-publisher-addCount-bg']")).getCssValue("background-image");
			 if(!plusIcon.contains("discussion-widget-sprite.png"))
				 Assert.fail(" + icon is not available at the end of the tab.");
			//verifying the Default question text in discussion text(searching at index 2, because @ index 0 its the lesson name and @ index 1 its the lesson content name
			 List<WebElement> defaulttext = driver.findElements(By.className("widget-content"));
			 if(!defaulttext.get(2).getText().equals("This is sample text. Please enter content here..."))
				 Assert.fail("Default question text is absent in the siscussion widget.");
			
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
			 //finding the number of tabs
			 List<WebElement> alltabs = driver.findElements(By.cssSelector("span[class='publisher-count discussion-widget-publisherIcons-bg discussion-widget-publisher-count-bg-holder']"));
			 if(alltabs.size() == 1)
				 Assert.fail("Discussion Widget doesn't have a tab approach. New tabs are not created on clicking + icon.");
			 
			 //checking user should land on newly created tab 
			 //list all the close icon in the tabs
			 List<WebElement> allCloseIcon = driver.findElements(By.className("close_tab"));
			 if(allCloseIcon.get(0).isDisplayed() == true || allCloseIcon.get(1).isDisplayed() == false)
				 Assert.fail("On clicking + icon the User doesn't land on to newly created tab.");
			 //check whether each tab can have multiple questions
			 List<WebElement> allWidgetContent = driver.findElements(By.className("tab-content-data"));
			 String textOnTab3 = allWidgetContent.get(2).getText();
			 alltabs.get(0).click();		//click on 1st tab
			 Thread.sleep(3000);
			 String textOnTab1 = allWidgetContent.get(0).getText();
			 if(!textOnTab1.equals("Text on tab1"))
				 Assert.fail("On clicking on the text field it doesn't become editable.");
			 if(!textOnTab1.equals("Text on tab1"))
				 Assert.fail("On Switching question tabs the question detail doesn't appear on the tab.");
			 alltabs.get(1).click();		//click on 2nd tab
			 Thread.sleep(3000);
			 String textOnTab2 = allWidgetContent.get(1).getText();
			 if(!textOnTab1.equals("Text on tab1") || !textOnTab2.equals("Text on tab2") || !textOnTab3.equals("Text on tab3"))
				 Assert.fail("Widget doesn't have ability to have multiple questions.");
			 
			 int widgetSize = driver.findElements(By.className("discussion-widget-container")).size();
			 if(widgetSize != 1)
				 Assert.fail("Discussion widget is not added over lesson content area in a lesson which is in draft status.");
			/*driver.switchTo().defaultContent();
			Thread.sleep(3000);
			 //adding 2nd discussion
			 WebElement box1 =  driver.findElement(By.cssSelector("div[class='page selectedpage']")); //right click on create lesson page
			 Actions clicker1 = new Actions(driver);
			 clicker1.contextClick(box1).sendKeys(Keys.ESCAPE).perform();			 
			 Thread.sleep(2000);
			 driver.findElement(By.id("discussion")).click(); //selecting discussion option on right clicking
			 Thread.sleep(3000);
			 int widgetSize1 = driver.findElements(By.className("discussion-widget-container")).size();
			 if(widgetSize1 != 2)
				 Assert.fail("2nd Discussion widget is not added over lesson content area in a lesson which is in drafte status.");
			*/
			 
			 //Mouse hover the Discussion Widget
			 WebElement WE = driver.findElement(By.className("discussion-widget-container"));
			 Locatable hoverItem = (Locatable) WE;
			  Mouse mouse = ((HasInputDevices) driver).getMouse();
			   mouse.mouseMove(hoverItem.getCoordinates());
			 //fine the image name for the option
			 String foreground = driver.findElement(By.xpath("//*[starts-with(@id, 'bringToFront')]")).getCssValue("background-image");
			 String shareLink = driver.findElement(By.xpath("//*[starts-with(@id, 'share')]")).getCssValue("background-image");
			 String edtiAttr = driver.findElement(By.xpath("//*[starts-with(@id, 'editattr-widget')]")).getCssValue("background-image");
			 String moveTo = driver.findElement(By.xpath("//*[starts-with(@id, 'moveTo-widget')]")).getCssValue("background-image");
			 String delete = driver.findElement(By.xpath("//*[starts-with(@id, 'delete-widget')]")).getCssValue("background-image");
			 if(!foreground.contains("Widget-Icons.png") || !shareLink.contains("lessonshare.png") || !edtiAttr.contains("Widget-Icons.png") || !moveTo.contains("Widget-Icons.png") || !delete.contains("Widget-Icons.png"))
				 Assert.fail("Widget options panel doesnt display Foregraound/background, Share link, Setting, Icon to move the widget, and Delete");
			 //verify the width of the discussion widget
			 String widgetWidth = driver.findElement(By.className("discussion-widget-container")).getAttribute("style");
			 if(!widgetWidth.contains("width: 100%"))
				 Assert.fail("The widget doesnt' span the entire lesson width.");
			 //click on share link
			 driver.findElement(By.xpath("//*[starts-with(@id, 'share')]")).click();
			 Thread.sleep(3000);
			 String popUp = driver.findElement(By.className("ui-dialog-title")).getText();
			 if(!popUp.equals("Widget Share Link"))
				 Assert.fail("On clicking the share link the pop up is not coming.");
			 //close the pop up
			 driver.findElement(By.cssSelector("a[class='ui-dialog-titlebar-close ui-corner-all']")).click();
			 Thread.sleep(3000);
			 //click on tab to make setting icon visible
			 driver.findElement(By.className("discussion-widget-publisher-addTab")).click();
			 Thread.sleep(3000);
			 driver.findElement(By.xpath("//*[starts-with(@id, 'editattr-widget')]")).click();		 //click on setting icon
			 Thread.sleep(3000);
			 String discusstionWidget = driver.findElement(By.className("widget_attributes")).getText();
			 if(!discusstionWidget.contains("DISCUSSION WIDGET SETTINGS"))
				 Assert.fail("On clicking the Setting Icon, Discussion widget settings pop up is not displayed.");
			 //close the pop up
			 driver.findElement(By.className("widget_attributes-close")).click();
			 Thread.sleep(3000);
			 
			//Mouse hover the Discussion Widget  to make delete icon visible
			 WebElement WE1 = driver.findElement(By.className("discussion-widget-container"));
			 Locatable hoverItem1 = (Locatable) WE1;
			 Mouse mouse1 = ((HasInputDevices) driver).getMouse();
			 mouse1.mouseMove(hoverItem1.getCoordinates());
			 //click on tab to make setting icon visible
			 driver.findElement(By.className("discussion-widget-publisher-addTab")).click();
			 Thread.sleep(3000);
			 //click on delete icon
			 driver.findElement(By.xpath("//*[starts-with(@id, 'delete-widget-')]")).click();
			 Thread.sleep(3000);
			 int widgetSize2 = driver.findElements(By.className("discussion-widget-container")).size();
			 if(widgetSize2 != 0)		//after deleting no widget will be there
				 Assert.fail("Discussion widget is not added over lesson content area in a lesson which is in draft status.");
			}
		}
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in publisherAbleToSupportADiscussionWidget in PublisherAbleToSupportADiscussionWidget class.",e);
		}
	}

}
