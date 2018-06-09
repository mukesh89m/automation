package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class FileBasedResources  extends Driver
{
	@Test
	public void filebasedresources()
	{
		try
		{
			new ResourseCreate().resourseCreate(1875,0);
			new LoginUsingLTI().ltiLogin("1875");//login as student			
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Resources");
            new ResourcesDetails().resourcesdetails("1875");	//verify all text in resources right side frame
            new Navigator().openResourceFromResourceTab(1875);
			String numberOfLike = new TextFetch().textfetchbyclass("ls-right-post-like-count");//1st time fetch number of like count
			if(numberOfLike.equals("0"))
				Assert.fail("Initially number of like not equal to zero");
			new ResourcesDetails().ResourcesLikeClick(0);//click on like
			int numberoflike1=new ResourcesDetails().resourcescountoflike(2);//2nd time fetch number of like after click on like link
			if(numberoflike1!=1)
				Assert.fail("after click on like link like count does not increase");
			new ResourcesDetails().resourcescomment(1, "This is a comment text.");//comment on resources 1st time
			int commentcount=new ResourcesDetails().commentcount(2);//count number of comment
			if(commentcount!=1)
				Assert.fail("after write a comment comment count not increse");
			new ResourcesDetails().resourcescommentlikeclick(0);//click on resources comment like
			int resourceslikecount=new ResourcesDetails().resourcescommentcountoflike(0);//count resources comment like
			if(resourceslikecount!=1)
				Assert.fail("after like a comment like count not increase");
			new LoginUsingLTI().ltiLogin("18751");//login as different student same class section
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Resources");//click on resource tab
            new Navigator().openResourceFromResourceTab(1875);
			int numberoflikeagainlogin=new ResourcesDetails().resourcescountoflike(2);//again count number of like of resources
			if(numberoflikeagainlogin!=1)	
				Assert.fail("number of like not as prevoius");
            new ResourcesDetails().ResourcesLikeClick(0);//click on like//click on like link by 2nd student
			int numberoflike1againlogin=new ResourcesDetails().resourcescountoflike(2);//count number of like after 2nd student click on like
			if(numberoflike1againlogin!=2)
				Assert.fail("number of like not increase after 2nd time click on like link by 2nd student");
			new ResourcesDetails().resourcescomment(1, "This is a comment text.");//comment on student by 2nd student
			int commentcountagainlogin=new ResourcesDetails().commentcount(2);//comment count 2nd time
			if(commentcountagainlogin!=2)
				Assert.fail("number of comment count not increase");
			new ResourcesDetails().resourcescommentlikeclick(1);//click on like link by 2nd student on his own comment
			int resourceslikecountagainlogin = new ResourcesDetails().resourcescountoflike(2);
			if(resourceslikecountagainlogin!= 2)
				Assert.fail("number of like not increase");
			new LoginUsingLTI().ltiLogin("1875");//again login as previous student
            new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Resources");//click on resource tab
            new Navigator().openResourceFromResourceTab(1875);
			int numberoflikeagainloginprestudent=new ResourcesDetails().resourcescountoflike(2);//again count number of like of resources
			if(numberoflikeagainloginprestudent!=2)	
				Assert.fail("number of like not as prevoius");
			int commentcountagainloginprestudent=new ResourcesDetails().commentcount(2);//comment count 2nd time
			if(commentcountagainloginprestudent!=2)
				Assert.fail("number of comment count not increase");

		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase filebasedresources in class FileBasedResources",e);
		}
	}


}
