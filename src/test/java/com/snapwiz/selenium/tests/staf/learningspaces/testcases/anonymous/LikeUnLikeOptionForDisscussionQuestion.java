package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;

public class LikeUnLikeOptionForDisscussionQuestion extends Driver
{
	@Test(priority=1,enabled=true)
	public void likeunlikeoptionfordisscussionquestion()
	{
		try
		{
			String chaptername=ReadTestData.readDataByTagName("tocdata", "chapterName", "1");//fetch chapter name
			new Widget().createChapterWidget(2409);//create chapter level widget
			driver.quit();
			startDriver("firefox");
			new LoginUsingLTI().ltiLogin("2409");//login a instructor
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TopicOpen().topicOpen(chaptername);//open chapter
			int likeofdisscussion=new Widget().Countoflikeofdisscussion(0);//Initially count number of like of discussion
			if(likeofdisscussion!=0)
				Assert.fail("initially number of like not equal to zero");
			new Widget().clickonlikeofdisscussion(0);//click on like link
			Thread.sleep(2000);
			int likeofdisscussion1=new Widget().Countoflikeofdisscussion(0);//count number of like after click on like
			if(likeofdisscussion1!=1)
				Assert.fail("after click on like count of like not increase");
			new Widget().clickonUnlikeofdisscussion(0);//click on unlike
			Thread.sleep(2000);
			int likeofdisscussion2=new Widget().Countoflikeofdisscussion(0);
			if(likeofdisscussion2!=0)
				Assert.fail("after click on unlike count of like not decrease");
			new Widget().perspectiveAdd();//add perspective
			Thread.sleep(2000);
			new Widget().clickonlikeofperspective(0);//click on perspective like
			Thread.sleep(2000);
			int likeofdisscussion3=new Widget().Countoflikeofdisscussion(1);//count number of like of perspective
			if(likeofdisscussion3!=1)
				Assert.fail("after click on perspective like count of like not increase");
			Thread.sleep(2000);
			new Widget().clickonUnlikeofperspective(0);//click on perspective Unlike
			Thread.sleep(2000);
			int likeofdisscussion4=new Widget().Countoflikeofdisscussion(1);//count number of like of perspective
			if(likeofdisscussion4!=0)
				Assert.fail("after click on perspective Unlike count of like not decrease");			
			new Widget().prospectivecomment(0,1);//comment on prospective
			Thread.sleep(2000);
			new Widget().clickonlikeofperspectivecomment(0);//click on perspective comment like
			int likecountofcomment=new Widget().Countoflikeofdisscussion(2);//count number of like of perspective comment
			if(likecountofcomment!=1)
				Assert.fail("after click on perspective comment like count of like not increase");
			new Widget().clickonUnlikeofperspectivecomment(0);//click on perspective comment Unlike
			int likecountofcomment1=new Widget().Countoflikeofdisscussion(2);//count number of like of perspective comment
			if(likecountofcomment1!=0)
				Assert.fail("after click on perspective comment Unlike count of like not decrease");
			new LoginUsingLTI().ltiLogin("24091");//login as 2nd student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TopicOpen().topicOpen(chaptername);//open chapter
			Thread.sleep(2000);
			int likeofdisscussion2ndstudent=new Widget().Countoflikeofdisscussion(0);//count like of disscussion
			if(likeofdisscussion2!=likeofdisscussion2ndstudent)
				Assert.fail("count of like of disscussion widget not euqal As previous student ");
			driver.findElement(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")).click();//click on perspective link
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,500)");
			boolean perspective=driver.findElement(By.cssSelector("div[class='ls-comment-entry']")).isDisplayed();//check perspective shown or not
			if(perspective==false)
				Assert.fail("perspective not shown for 2nd student");
			driver.findElement(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']")).click();//click on perspective comment link
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,500)");
			boolean perspectivecomment=driver.findElement(By.cssSelector("div[class='ls-perspctive-comments-posted']")).isDisplayed();//check perspective comment shown or not
			if(perspectivecomment==false)
				Assert.fail("perspective comment not shown for 2nd student");						
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase likeunlikeoptionfordisscussionquestion in class LikeUnLikeOptionForDisscussionQuestion ",e);
		}
	}


}
