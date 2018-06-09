package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentSocialElement;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandFirstChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UpdateContentIndex;

public class SocialActivityAvilableOnBothAssignmenttabAndCourcesStream extends Driver
{
	@Test
	public void socialactivityavilableonbothassignmenttabandcourcesstream()
	{
		try
		{
			String topic1=ReadTestData.readDataByTagName("tocdata", "card2topic1", "1");//fetch topic 1
			new Assignment().create(3326);//create assignment
			new LoginUsingLTI().ltiLogin("33261");//create student
			new UpdateContentIndex().updatecontentindex("3326");//update index
			driver.quit();
			 startDriver("firefox");
			new LoginUsingLTI().ltiLogin("3326");//login as instructor
			new Assignment().assignToStudent(3326);//Assignment assign to student
			new LoginUsingLTI().ltiLogin("33261");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new ExpandFirstChapter().expandFirstChapter();//expand 1st chapter
			new TopicOpen().topicOpen(topic1);//open topic 1
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab
			Thread.sleep(3000);
			int numberoflike=new AssignmentSocialElement().countoflikerightframe(0);//count number of like initial at right side frame
			if(numberoflike!=0)
				Assert.fail("at initial number of like not equal to 0(zero)");
			new AssignmentSocialElement().clickonlike(2);//click on like on reght side frame
			Thread.sleep(2000);
			int numberoflike1=new AssignmentSocialElement().countoflikerightframe(0);//count like after click on like in right side frame
			if(numberoflike1!=1)
				Assert.fail("after click  on like link its not increase ");
			new Navigator().NavigateTo("Course Stream");//navigate to course stream
			Thread.sleep(2000);
			int likecountatcourcsestream=new AssignmentSocialElement().countoflikecoursestream(0);//count number of like in course stream initaly
			if(likecountatcourcsestream!=1)
				Assert.fail("In course stream number of like not reflected from right side frame");
			new AssignmentSocialElement().clickonlikecoursestream(0);//click on unlike in course stream
			Thread.sleep(2000);
			int likecountatcourcsestream1=new AssignmentSocialElement().countoflikecoursestream(0);//count number of like in corse stream after clike on unlike
			if(likecountatcourcsestream1!=0)
				Assert.fail("In course stream number of like not decrease after click on unlike link"); 
			new AssignmentSocialElement().clickonlikecoursestream(0);//click on like in course stream
			Thread.sleep(2000);
			int likecountatcourcsestream2=new AssignmentSocialElement().countoflikecoursestream(0);//count of like in course stream
			if(likecountatcourcsestream2!=1)
				Assert.fail("In course stream number of like not increase after click on like link in course stream");
			int numberofcommentcount=new AssignmentSocialElement().countofcomment(0);//count number of comment
			if(numberofcommentcount!=0)
				Assert.fail("By default number of count not equal to zero");
			new AssignmentSocialElement().clickoncommnetcoursestream(0);//write comment for assignment
			Thread.sleep(2000);
			int numberofcommentcount1=new AssignmentSocialElement().countofcomment(0);//count number of comment after commented of assignment
			if(numberofcommentcount1!=1)
				Assert.fail("number of count not increase after comment on assignment");
			boolean instructorlevel=driver.findElement(By.cssSelector("span[class='ls-instructor-icon']")).isDisplayed();
			if(instructorlevel==false)
				Assert.fail("instructor level not shown in course stream");
			new Navigator().NavigateTo("eTextbook");
			new ExpandFirstChapter().expandFirstChapter();//expand 1st chapter
			new TopicOpen().topicOpen(topic1);//open topic 1
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("span[class='ls-right-view-all-assignments']")).click();//click on  view all assignment link
			Thread.sleep(3000);
			String url=driver.getCurrentUrl();//get current url
			if(!url.contains("studentAssignment"))
				Assert.fail("after click on view all assignment link its not redirect to assignment page");
		}//end of try
		
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper socialactivityavilableonbothassignmenttabandcourcesstream in class SocialActivityAvilableOnBothAssignmenttabAndCourcesStream.",e);
		}//end of catch
	}

}
