package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class MediaBasedResources extends Driver
{
	@Test(priority = 1,enabled = true)
	public void mediaBasedResources()
	{
		try
		{
			String resourcesname=ReadTestData.readDataByTagName("", "resoursename", "1894");//fetch resources name
			new ResourseCreate().resourseCreate(1894, 0);

			new LoginUsingLTI().ltiLogin("1894");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToResourceTab();
			new ResourcesDetails().resourcesdetails("1894");	//verify all text in resources right side frame
			new TopicOpen().topicOpen(resourcesname);//open resources in tab
			int numberoflike=new ResourcesDetails().resourcescountoflike(2);//1st time fetch number of like count
			if(numberoflike!=0)
				Assert.fail("at intialy number of like not equal to zero");
            new Navigator().openResourceFromResourceTab(1894);
			new ResourcesDetails().ResourcesLikeClick(2);//click on like
			int numberoflike1=new ResourcesDetails().resourcescountoflike(2);//2nd time fetch number of like after click on like link
			if(numberoflike1!=1)
				Assert.fail("after click on like link like count not increase");
			new ResourcesDetails().resourcescomment(2, "comment");//comment on resources 1st time
			int commnetcount=new ResourcesDetails().commentcount(2);//count number of comment
			if(commnetcount!=1)
				Assert.fail("after write a commnet commnet count not increse");
			new ResourcesDetails().resourcescommentlikeclick(0);//click on resources comment like
			int resourceslikecount=new ResourcesDetails().resourcescommentcountoflike(0);//count resources comment like
			if(resourceslikecount!=1)
				Assert.fail("after like a commnet like count not increase");			
			new LoginUsingLTI().ltiLogin("18941");//login as different student same class section
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			driver.findElement(By.cssSelector("div[data-id='resources']")).click();//clcik on resource tab
			new TopicOpen().topicOpen(resourcesname);
			int numberoflikeagainlogin=new ResourcesDetails().resourcescountoflike(2);//again count number of like of resources
			if(numberoflikeagainlogin!=1)	
				Assert.fail("number of like not as prevoius");
			new ResourcesDetails().ResourcesLikeClick(2);//click on like link by 2nd student
			int numberoflike1againlogin=new ResourcesDetails().resourcescountoflike(2);//count number of like after 2nd student click on like
			if(numberoflike1againlogin!=2)
				Assert.fail("number of like not increase after 2nd time click on like link by 2nd student");
			new ResourcesDetails().resourcescomment(2, "comment");//comment on student by 2nd student
			int commnetcountagainlogin=new ResourcesDetails().commentcount(2);//commnet count 2nd time
			if(commnetcountagainlogin!=2)
				Assert.fail("number of commnet count not increase");
			new ResourcesDetails().resourcescommentlikeclick(1);//click on like link by 2nd student on his own comment
			int resourceslikecountagainlogin=new ResourcesDetails().resourcescommentcountoflike(1);
			if(resourceslikecountagainlogin!=1)
				Assert.fail("number of like not increase");
			new LoginUsingLTI().ltiLogin("1894");//again login as previous student
			new Navigator().NavigateTo("eTextbook");
			driver.findElement(By.cssSelector("div[data-id='resources']")).click();//clcik on resource tab
			new ResourcesDetails().resourcesdetails("1894");
			new TopicOpen().topicOpen(resourcesname);
			int numberoflikeagainloginprestudent=new ResourcesDetails().resourcescountoflike(2);//again count number of like of resources
			if(numberoflikeagainloginprestudent!=2)	
				Assert.fail("number of like not as prevoius");
			int commnetcountagainloginprestudent=new ResourcesDetails().commentcount(2);//commnet count 2nd time
			if(commnetcountagainloginprestudent!=2)
				Assert.fail("number of commnet count not increase");
			//driver.findElement(By.cssSelector("i[class='ls-fileicon-img ls-right-assignment-url']")).click();
			//Thread.sleep(15000);
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase mediaBasedResources in class MediaBasedResources",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void orderofresources()
	{
		try
		{
			String resourcesname1=ReadTestData.readDataByTagName("MediaBasedResources", "resoursename", "1894");//fetch resources name
			String resourcesname2=ReadTestData.readDataByTagName("MediaBasedResources", "resoursename", "1912");//fetch resources name
			//new ResourseCreate().creatresourcesattopiclevel(1912, 0, 0);
			new LoginUsingLTI().ltiLogin("1912");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			
			driver.findElement(By.cssSelector("div[data-id='resources']")).click();//click on resources tab
			Thread.sleep(3000);
			String[] resourcesarray={resourcesname1,resourcesname2};
			String [] resourcesfetch=new String[2];
			int i=0;
			List<WebElement> allresources=driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));//fetch all resources name
			for(WebElement resources:allresources)
			{
				resourcesfetch[i]=resources.getText();
				i++;
			}
			if(!resourcesarray[1].equals(resourcesfetch[0]))
				Assert.fail("at index 0 last post resources not present");
			if(!resourcesarray[0].equals(resourcesfetch[1]))
				Assert.fail("at index 1 first post resources not present");			
			new TopicOpen().topicOpen(resourcesname1);//open resources in tab
			Thread.sleep(3000);
			new ResourcesDetails().resourcescomment(2, "comment");//comment on resources 1st time
			Thread.sleep(2000);
			new Navigator().NavigateTo("eTextbook");
			driver.findElement(By.cssSelector("div[data-id='resources']")).click();//click on resources tab
			Thread.sleep(3000);
			String [] resourcesfetch1=new String[2];
			int j=0;
			List<WebElement> allresources1=driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));//fetch all resources name 2nd time
			for(WebElement resources1:allresources1)
			{
				resourcesfetch1[j]=resources1.getText();				
				j++;
			}
			if(!resourcesarray[1].equals(resourcesfetch1[1]))
				Assert.fail("at index 1 last post resources not present after commnet on post");
			if(!resourcesarray[0].equals(resourcesfetch1[0]))
				Assert.fail("at index 0 first post resources not present after cpommnet on post");				
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase orderofresources in class MediaBasedResources",e);
		}
	}


}
