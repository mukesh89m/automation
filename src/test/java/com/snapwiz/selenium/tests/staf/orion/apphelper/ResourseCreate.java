package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.List;

import javax.swing.text.AbstractDocument.BranchElement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.uihelper.ComboBox;

public class ResourseCreate {
	//create resource at chapter level
	public void resourseCreate(int dataIndex,int chapternumber)
	{
		try
		{
		String course = ReadTestData.readDataByTagName("", "course", Integer.toString(dataIndex));
		String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));
		String type = ReadTestData.readDataByTagName("", "type", Integer.toString(dataIndex));
		String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
		String instructoronlyflag = ReadTestData.readDataByTagName("", "instructoronlyflag", Integer.toString(dataIndex));
		String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
		Driver.driver.get(Config.baseURL);
		new DirectLogin().CMSLogin();
		String title=Driver.driver.getTitle();
		if(title.equals("Course Content Details"))
			{
			 Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			 List<WebElement> allchapter=Driver.driver.findElements(By.cssSelector("div[class='course-chapter-label node']"));
			 int index=0;
			 for(WebElement chapter:allchapter)
			 {
				if(index==chapternumber) 
				{
					chapter.click();
					break;
				}
				index++;
			 }
			// Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
			 Driver.driver.findElement(By.cssSelector("div.associate-resource")).click();
			 WebElement resname = Driver.driver.findElement(By.className("associate-resource-field-text"));
			 Actions action = new Actions(Driver.driver);
			 action.doubleClick(resname);
			 action.perform();
			 Driver.driver.findElement(By.id("resource-name-field")).clear();
			 Driver.driver.findElement(By.id("resource-name-field")).sendKeys(resoursename);
			
			 new ComboBox().selectValue(3, type);
			
			
		        WebElement desc = Driver.driver.findElement(By.id("associate-resource-details-field-text-content"));
				 action.doubleClick(desc);
				 action.perform();
				 
				 Driver.driver.findElement(By.id("resource-description")).clear();
				 Driver.driver.findElement(By.id("resource-description")).sendKeys(description);
				 new ComboBox().selectValue(4, instructoronlyflag);
				
			    	 
			    //    Driver.driver.findElement(By.linkText("image")).click();
				 
				 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("browseResource")));
				 new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename+"^");
				 Thread.sleep(2000);
				 Driver.driver.findElement(By.id("start_queue")).click();
				 Thread.sleep(15000);
				 Driver.driver.findElement(By.id("associateResourceToNode")).click();
				 Thread.sleep(30000);
				 Driver.driver.quit();
				 Driver.startDriver();
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper ResourseCreate",e);
		}
	}
	
	//create resources as topic level
	public void creatresourcesattopiclevel(int dataIndex,int topic,int chapter)
	{
		try
		{
			String course = ReadTestData.readDataByTagName("", "course", Integer.toString(dataIndex));
			String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));
			String type = ReadTestData.readDataByTagName("", "type", Integer.toString(dataIndex));
			String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
			String instructoronlyflag = ReadTestData.readDataByTagName("", "instructoronlyflag", Integer.toString(dataIndex));
			String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
			new DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
				if(title.equals("Course Content Details"))
				{
				 Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
				 List<WebElement> allchapter=(List<WebElement>) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0]",Driver.driver.findElements(By.cssSelector("div[class='expand-chapter-tree expand']")));
				 int index1=0;
				 for(WebElement elements:allchapter)
				 {
					 
					 if(index1==chapter)
					 {
						 elements.click();
						 break;
					 }
					 index1++;
				 }
				
				 List<WebElement> alltopic=(List<WebElement>) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0]",Driver.driver.findElements(By.cssSelector("div[class='course-topic-label node']")));
				 int index=0;
				 for(WebElement element:alltopic)
				 {
					
					 if(index==topic)
					 {
						 element.click();
						 break;
					 }
					 index++;
				 }
				 Driver.driver.findElement(By.cssSelector("div.associate-resource")).click();
				 WebElement resname = Driver.driver.findElement(By.className("associate-resource-field-text"));
				 Actions action = new Actions(Driver.driver);
				 action.doubleClick(resname);
				 action.perform();
				 Driver.driver.findElement(By.id("resource-name-field")).clear();
				 Driver.driver.findElement(By.id("resource-name-field")).sendKeys(resoursename);
				
				 new ComboBox().selectValue(3, type);
				
				
			        WebElement desc = Driver.driver.findElement(By.id("associate-resource-details-field-text-content"));
					 action.doubleClick(desc);
					 action.perform();
					 
					 Driver.driver.findElement(By.id("resource-description")).clear();
					 Driver.driver.findElement(By.id("resource-description")).sendKeys(description);
					 new ComboBox().selectValue(4, instructoronlyflag);
					
				    	 
				    //    Driver.driver.findElement(By.linkText("image")).click();
					 
					 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("browseResource")));
					 new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename+"^");
					 Thread.sleep(2000);
					 Driver.driver.findElement(By.id("start_queue")).click();
					 Thread.sleep(15000);
					 Driver.driver.findElement(By.id("associateResourceToNode")).click();
					 Thread.sleep(30000);
					 Driver.driver.quit();
					 Driver.startDriver();
				}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper method creatresourcesattopiclevel in class  ResourseCreate",e);
		}
	}
	
	//add resources at subtopic level
	public void createresourcesatsubtopiclevel(int dataIndex,int topictoexpand,int chapter,int subtopic)
	{
		try
		{
			
				String course = ReadTestData.readDataByTagName("", "course", Integer.toString(dataIndex));
				String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));
				String type = ReadTestData.readDataByTagName("", "type", Integer.toString(dataIndex));
				String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
				String instructoronlyflag = ReadTestData.readDataByTagName("", "instructoronlyflag", Integer.toString(dataIndex));
				String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(dataIndex));
				new DirectLogin().CMSLogin();
				String title=Driver.driver.getTitle();
				if(title.equals("Course Content Details"))
					{
					 Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
					 List<WebElement> allchapter=(List<WebElement>) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0]",Driver.driver.findElements(By.cssSelector("div[class='expand-chapter-tree expand']")));
					 int index1=0;
					 for(WebElement elements:allchapter)
					 {
						 if(index1==chapter)
						 {
							 elements.click();
							 break;
						 }
						 index1++;
					 }
					 List<WebElement> alltopic=(List<WebElement>) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0]",Driver.driver.findElements(By.cssSelector("div[class='expand-topic-tree expand']")));
					 int index=0;
					 for(WebElement element:alltopic)
					 {
						
						 if(index==topictoexpand)
						 {
							 element.click();
							 break;
						 }
						 index++;
					 }
					 List<WebElement> allsubtopictopic=(List<WebElement>) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0]",Driver.driver.findElements(By.cssSelector("div[class='course-subtopic-label node']")));
					 int index2=0;
					 for(WebElement element1:allsubtopictopic)
					 {
					
						 if(index2==subtopic)
						 {
							 element1.click();
							 break;
						 }
						 index2++;
					 }
					 Thread.sleep(3000);
					 Driver.driver.findElement(By.cssSelector("div.associate-resource")).click();
					 WebElement resname = Driver.driver.findElement(By.className("associate-resource-field-text"));
					 Actions action = new Actions(Driver.driver);
					 action.doubleClick(resname);
					 action.perform();
					 Driver.driver.findElement(By.id("resource-name-field")).clear();
					 Driver.driver.findElement(By.id("resource-name-field")).sendKeys(resoursename);
					 new ComboBox().selectValue(3, type);
					 WebElement desc = Driver.driver.findElement(By.id("associate-resource-details-field-text-content"));
						 action.doubleClick(desc);
						 action.perform();
						 
						 Driver.driver.findElement(By.id("resource-description")).clear();
						 Driver.driver.findElement(By.id("resource-description")).sendKeys(description);
						 new ComboBox().selectValue(4, instructoronlyflag);
					    //    Driver.driver.findElement(By.linkText("image")).click();						 
						 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.id("browseResource")));
						 new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename+"^");
						 Thread.sleep(2000);
						 Driver.driver.findElement(By.id("start_queue")).click();
						 Thread.sleep(15000);
						 Driver.driver.findElement(By.id("associateResourceToNode")).click();
						 Thread.sleep(30000);
						 Driver.driver.quit();
						 Driver.startDriver();
					}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper method addresourcesatsubtopiclevel in class  ResourseCreate",e);
		}
	}
	
	public void URLBasedResources(int dataIndex,int chapter)
	{
		try
		{
			String course = ReadTestData.readDataByTagName("", "course", Integer.toString(dataIndex));
			String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(dataIndex));
			String type = ReadTestData.readDataByTagName("", "type", Integer.toString(dataIndex));
			String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
			String instructoronlyflag = ReadTestData.readDataByTagName("", "instructoronlyflag", Integer.toString(dataIndex));
			String urlname = ReadTestData.readDataByTagName("", "urlname", Integer.toString(dataIndex));
			Driver.driver.get(Config.baseURL);
			new DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))
				{
				 Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
				 List<WebElement> allchapter=(List<WebElement>) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0]",Driver.driver.findElements(By.cssSelector("div[class='course-chapter-label node']")));
				 int index1=0;
				 for(WebElement elements:allchapter)
				 {					 
					 if(index1==chapter)
					 {
						 elements.click();
						 break;
					 }
					 index1++;
				 }
				 Driver.driver.findElement(By.cssSelector("div.associate-resource")).click();
				 WebElement resname = Driver.driver.findElement(By.className("associate-resource-field-text"));
				 Actions action = new Actions(Driver.driver);
				 action.doubleClick(resname);
				 action.perform();
				 Driver.driver.findElement(By.id("resource-name-field")).clear();
				 Driver.driver.findElement(By.id("resource-name-field")).sendKeys(resoursename);
				 new ComboBox().selectValue(3, type);
			     WebElement desc = Driver.driver.findElement(By.id("associate-resource-details-field-text-content"));
			     action.doubleClick(desc);
			     action.perform();					 
			     Driver.driver.findElement(By.id("resource-description")).clear();
				 Driver.driver.findElement(By.id("resource-description")).sendKeys(description);
				 new ComboBox().selectValue(4, instructoronlyflag);
				 //Driver.driver.findElement(By.linkText("image")).click();
				 WebElement url = Driver.driver.findElement(By.cssSelector("div[fieldname='resource-url']"));
				 action.doubleClick(url);
				 action.perform();					 
				 Driver.driver.findElement(By.cssSelector("input[id='resource-url-field']")).clear();
				 Driver.driver.findElement(By.cssSelector("input[id='resource-url-field']")).sendKeys(urlname);
				 Driver.driver.findElement(By.id("associateResourceToNode")).click();
				 Thread.sleep(30000);					 
				}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper method URLBasedResources in class  ResourseCreate",e);
		}
	}
	
	
}
