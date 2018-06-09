package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ResourcesDetails;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ResourseCreate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class URLBasedResources extends Driver
{
	@Test
	public void urlbasedresources()
	{
		try
		{
			String resourcesname=ReadTestData.readDataByTagName("URLBasedResources", "resoursename", "1859");//fetch resources name
			new ResourseCreate().URLBasedResources(1859, 0);//resources create at chapter 1
			new LoginUsingLTI().ltiLogin("1859");//login as student
			
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			driver.findElement(By.cssSelector("div[data-id='resources']")).click();//click on resources tab
			Thread.sleep(3000);
			new ResourcesDetails().resourcesdetails("1859");	//verify all text in resources right side frame		
			new TopicOpen().topicOpen(resourcesname);//open resources in tab
			Thread.sleep(3000);
			int numberoflike=new ResourcesDetails().resourcescountoflike(2);//1st time fetch number of like count
			if(numberoflike!=0)
				Assert.fail("at intialy number of like not equal to zero");
			new ResourcesDetails().ResourcesLikeClick(2);//click on like
			Thread.sleep(3000);
			int numberoflike1=new ResourcesDetails().resourcescountoflike(2);//2nd time fetch number of like after click on like link
			if(numberoflike1!=1)
				Assert.fail("after click on like link like count not increase");
			new ResourcesDetails().resourcescomment(2, "comment");//comment on resources 1st time
			Thread.sleep(2000);
			int commnetcount=new ResourcesDetails().commentcount(2);//count number of comment
			if(commnetcount!=1)
				Assert.fail("after write a commnet commnet count not increse");
			Thread.sleep(2000);
			new ResourcesDetails().resourcescommentlikeclick(0);//click on resources comment like
			Thread.sleep(3000);
			int resourceslikecount=new ResourcesDetails().resourcescommentcountoflike(0);//count resources comment like
			if(resourceslikecount!=1)
				Assert.fail("after like a commnet like count not increase");			
			new LoginUsingLTI().ltiLogin("18591");//login as different student same class section
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			driver.findElement(By.cssSelector("div[data-id='resources']")).click();//clcik on resource tab
			Thread.sleep(3000);	
			new TopicOpen().topicOpen(resourcesname);
			Thread.sleep(3000);
			int numberoflikeagainlogin=new ResourcesDetails().resourcescountoflike(2);//again count number of like of resources
			if(numberoflikeagainlogin!=1)	
				Assert.fail("number of like not as prevoius");
			new ResourcesDetails().ResourcesLikeClick(2);//click on like link by 2nd student
			Thread.sleep(3000);
			int numberoflike1againlogin=new ResourcesDetails().resourcescountoflike(2);//count number of like after 2nd student click on like
			if(numberoflike1againlogin!=2)
				Assert.fail("number of like not increase after 2nd time click on like link by 2nd student");
			new ResourcesDetails().resourcescomment(2, "comment");//comment on student by 2nd student
			Thread.sleep(2000);
			int commnetcountagainlogin=new ResourcesDetails().commentcount(2);//commnet count 2nd time
			if(commnetcountagainlogin!=2)
				Assert.fail("number of commnet count not increase");
			Thread.sleep(2000);
			new ResourcesDetails().resourcescommentlikeclick(1);//click on like link by 2nd student on his own comment
			Thread.sleep(3000);
			int resourceslikecountagainlogin=new ResourcesDetails().resourcescommentcountoflike(1);
			if(resourceslikecountagainlogin!=1)
				Assert.fail("number of like not increase");
			new LoginUsingLTI().ltiLogin("1859");//again login as previous student
			new Navigator().NavigateTo("eTextbook");
			driver.findElement(By.cssSelector("div[data-id='resources']")).click();//clcik on resource tab
			Thread.sleep(3000);
			new ResourcesDetails().resourcesdetails("1859");
			new TopicOpen().topicOpen(resourcesname);
			Thread.sleep(3000);
			int numberoflikeagainloginprestudent=new ResourcesDetails().resourcescountoflike(2);//again count number of like of resources
			if(numberoflikeagainloginprestudent!=2)	
				Assert.fail("number of like not as prevoius");
			int commnetcountagainloginprestudent=new ResourcesDetails().commentcount(2);//commnet count 2nd time
			if(commnetcountagainloginprestudent!=2)
				Assert.fail("number of commnet count not increase");
			driver.findElement(By.cssSelector("i[class='ls-fileicon-img ls-right-assignment-url']")).click();
			Thread.sleep(15000);
			//driver.switchTo().activeElement();
			//String winHandleBefore = driver.getWindowHandle();//store current window
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
			}
			String url=driver.getCurrentUrl();
			if(!url.contains("www.google."))
				Assert.fail("after click on url based resources its not open new url");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase urlbasedresources in class URLBasedResources",e);
		}
	}

}
