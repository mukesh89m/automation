package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandCollapseChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandFirstChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ResourseCreate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class StudentAbleToViewMultipleLessonsAndAssessemnt extends Driver
{
	@Test(priority=1,enabled=false)
	public void studentabletoviewmultiplelessonandassessemnt()
	{
		try
		{
			new ResourseCreate().resourseCreate(28641,0);//create 1st resources on chapter level 1
			new ResourseCreate().resourseCreate(28642,0);//create 2st resources on chapter level 1
			new LoginUsingLTI().ltiLogin("2864");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new ExpandFirstChapter().expandFirstChapter();//expand 1st chapter
			String topic1=ReadTestData.readDataByTagName("tocdata", "card2topic1", "1");//fetch 1st tipoc name to open
			String topic2=ReadTestData.readDataByTagName("tocdata", "card3topic1", "1");//fetch 1st tipoc name to open
			String test1=ReadTestData.readDataByTagName("tocdata", "statictest", "1");//fetch 1st test to open	
			boolean closeoption=driver.findElement(By.cssSelector("span[class='close_tab']")).isDisplayed();
			if(closeoption==true)
				Assert.fail("for only one open chapter close link is available");
			new TopicOpen().topicopeninnewtab(topic1, 2);//open lesson in new tab
			new TOCShow().tocShow();
			new ExpandCollapseChapter().collapseChapter(1);
			new ExpandCollapseChapter().expandChapter(1);
			Thread.sleep(3000);
			
			new TopicOpen().topicOpen(test1);//open Assessment in new tab	
			Thread.sleep(3000);	
			new TOCShow().tocShow();
			Thread.sleep(3000);	
			new TopicOpen().topicopeninnewtab(topic2, 5);//open lesson in new tab
			Thread.sleep(3000);
			new TopicOpen().ResourcesOpenInNewtab(1,2);//open Resource 
			Thread.sleep(3000);
			List<WebElement> alltabname=driver.findElements(By.cssSelector("div[class='tab']"));
			int tabopen=alltabname.size();
			System.out.println(tabopen);
			if(tabopen<3)
				Assert.fail("Multiple tab not open of assessment ,resources,and lesson");
			driver.findElement(By.cssSelector("span[title='"+test1+"']")).click();
			Thread.sleep(3000);
			int topiccontentshown=driver.findElements(By.cssSelector("div[id='assessmentTimer']")).size();//fetch topic content after open   topic
			if(topiccontentshown<1)
				Assert.fail("topic content not shown.");
			new TOCShow().tocShow();
			new TOCShow().tocHide();
			driver.findElement(By.xpath("//*[starts-with(@id,'close-assessment-')]")).click();//click on close link
			Thread.sleep(3000);
			int closetoipc=driver.findElements(By.cssSelector("span[title='"+test1+"']")).size();//fetch topic content after close that topic
			if(closetoipc>=1)
				Assert.fail("Close topic still present in tab");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method studentabletoviewmultiplelessonandassessemnt in class StudentAbleToViewMultipleLessonsAndAssessemnt ",e);
		}
	}
	
	@Test(priority=2,enabled=false)
	public void openlessonresourcesassignment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("2864");
			new Navigator().NavigateTo("eTextbook");
			new ExpandFirstChapter().expandFirstChapter();
			int tabvalue1=driver.findElements(By.cssSelector("div[class='tab']")).size();//fetch number of tab
			String test1=ReadTestData.readDataByTagName("tocdata", "statictest", "1");//fetch 1st test to open	
			String topictoopen=ReadTestData.readDataByTagName("tocdata", "card3topic1", "1");//fetch 1st topic name to open
			String topic2=ReadTestData.readDataByTagName("tocdata", "card2topic1", "1");//fetch 1st tipoc name to open
			new TopicOpen().topicOpen(topictoopen);
			int tabvalue2=driver.findElements(By.cssSelector("div[class='tab']")).size();//number of tab  after click on chapter
			if(tabvalue1!=tabvalue2)
				Assert.fail("Topic open in new tab without click on new tab button of that topic.");
			new TOCShow().tocShow();
			new TopicOpen().topicOpen(test1);
			Thread.sleep(3000);
			int tabvalue3=driver.findElements(By.cssSelector("div[class='tab']")).size();//number of tab  after click on assessment
			if(tabvalue3<tabvalue2)
				Assert.fail("Assessment not open in new tab.");
			new TOCShow().tocShow();
			new TopicOpen().topicOpen(topic2);
			
			driver.findElement(By.cssSelector("span[title='Resources']")).click();//click on resources tab
			Thread.sleep(3000);
			driver.findElement(By.linkText("28641Resourse")).click();
			Thread.sleep(3000);
			int tabvalue4=driver.findElements(By.cssSelector("div[class='tab']")).size();//number of tab  after click on resources
			if(tabvalue4<tabvalue3)
				Assert.fail("resources not open  in new tab");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method openlessonresourcesassignment in class  InstructorAbleToOpenMultipleTab",e);
		}
	}
	
	@Test(priority=3,enabled=true)
	public void studentabletogoanotherlesson()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("2875");
			new Navigator().NavigateTo("eTextbook");
			new ExpandFirstChapter().expandFirstChapter();
			String topic1=ReadTestData.readDataByTagName("tocdata", "card2topic1", "1");//fetch 1st topic name to open
			String topic2=ReadTestData.readDataByTagName("tocdata", "card3topic1", "1");//fetch 1st tipoc name to open
			String topic3=ReadTestData.readDataByTagName("tocdata", "card4topic1", "1");//fetch 1st tipoc name to open
			String topic4=ReadTestData.readDataByTagName("tocdata", "topic4", "1");//fetch 1st tipoc name to open
			String test1=ReadTestData.readDataByTagName("tocdata", "statictest", "1");//fetch 1st test to open	
			new TopicOpen().topicopeninnewtab(topic2, 5);
			Thread.sleep(3000);
			int tabvalue1=driver.findElements(By.cssSelector("div[class='tab']")).size();//fetch number of tab
			System.out.println(tabvalue1);
			String topictitle=driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			new TOCShow().tocShow();
			Thread.sleep(3000);
			new TopicOpen().topicOpen(topic3);
			Thread.sleep(3000);
			int tabvalue2=driver.findElements(By.cssSelector("div[class='tab']")).size();//fetch number of tab
			System.out.println(tabvalue2);
			String topictitle1=driver.findElement(By.cssSelector("div[class='tab active']")).getText();//fetch active tab title
			if(tabvalue1!=tabvalue2)
				Assert.fail("after click on topic hover link its open in new tab not referesh old tab");
			if(topictitle.equals(topictitle1))
				Assert.fail("After cilck on topic hover link its not referesh the last open tab");
			new TOCShow().tocShow();
			Thread.sleep(3000);
			new TopicOpen().topicOpen(test1);
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("span[title='"+topic1+"']")).click();//click on tab
			Thread.sleep(3000);
			new TOCShow().tocShow();
			Thread.sleep(3000);
			new TopicOpen().topicOpen(topic4);
			Thread.sleep(3000);
			String topictitle4=driver.findElement(By.cssSelector("div[class='tab active']")).getText();	//fetch active tab title
			if(!topictitle4.equals(topic4))
				Assert.fail("Student not land land on the last opened lesson tab refreshed with specific lesson content ");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method studentabletogoanotherlesson in class  InstructorAbleToOpenMultipleTab",e);
		}		
	}

}
