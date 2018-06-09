package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandCollapseChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class StudentNavigateAllTab extends Driver
{
	@Test(priority=1,enabled=true)
	public void studentnavigatealltab()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("2919");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook	
			String defaulttopicopen=ReadTestData.readDataByTagName("tocdata", "defaulttopictitle", "1");//fetch 1st tipoc name to open
			String topic1=ReadTestData.readDataByTagName("tocdata", "card3topic1", "1");//fetch 1st tipoc name to open
			String topic2=ReadTestData.readDataByTagName("tocdata", "card4topic1", "1");//fetch 1st tipoc name to open
			String subtopic1=ReadTestData.readDataByTagName("tocdata", "subtopic1", "1");//fetch 1st tipoc name to open
		
			String test1=ReadTestData.readDataByTagName("tocdata", "statictest", "1");//fetch 1st test to open	
			new TopicOpen().topicOpen(test1);
			Thread.sleep(3000);
			new TOCShow().tocShow();
			Thread.sleep(3000);
			new TopicOpen().topicopeninnewtab(topic1, 5);//open 2nd topic
			Thread.sleep(3000);
			new TOCShow().tocShow();
			Thread.sleep(3000);
			new TopicOpen().topicopeninnewtab(topic2, 8);//open 3rd topic
			Thread.sleep(3000);
			boolean arrowicon=driver.findElement(By.className("hiddentabs")).isDisplayed();//check arrow icon apper or not
			if(arrowicon==true)
				Assert.fail("arrow icon shown before open only 4 tab");
			new TOCShow().tocShow();
			new ExpandCollapseChapter().collapseChapter(1);
			new ExpandCollapseChapter().expandChapter(1);
			Thread.sleep(3000);
			new TopicOpen().topicopeninnewtab(subtopic1, 3);//open subtopic 1.1
			Thread.sleep(3000);
			boolean arrowicon1=driver.findElement(By.className("hiddentabs")).isDisplayed();//check arrow icon apper or not
			if(arrowicon1==false)
				Assert.fail("arrow icon not shown after 5 tab open");
			
			String numberofhiddebtab=driver.findElement(By.className("hiddentabs")).getText();//ftech number of hidden tab
			if(!numberofhiddebtab.equals("1"))
				Assert.fail("hidden tab count not shown");
			driver.findElement(By.className("hiddentabs")).click();//click on arrow icon
			Thread.sleep(3000);
			String hiddentext=driver.findElement(By.className("hidden-tab-data")).getText();//fetch dropdown text
			if(!hiddentext.equals(defaulttopicopen))
				Assert.fail("hidden list text not eqal to 1st open tab");
			boolean hiddentabselected=driver.findElement(By.cssSelector("div[class='hiddentabs dropdownActive']")).isEnabled();//verify hidden tab value is enabled or not
			if(hiddentabselected==false)
				Assert.fail("after click on arrow link --arrow link not enabled.");
			
			driver.findElement(By.className("hidden-tab-data")).click();//click on hidden  topic text
			Thread.sleep(3000);
			driver.findElement(By.className("hiddentabs")).click();//click on arrow icon
			Thread.sleep(3000);
			String hiddentext1=driver.findElement(By.className("hidden-tab-data")).getText();//again fetch text from drop down
			if(!hiddentext1.equals(test1))
				Assert.fail("hidden list text not eqal to 1st  tab");
			
			driver.findElement(By.cssSelector("div[class='hiddentabs dropdownActive']")).click();//click on enabled hidden tab
			Thread.sleep(3000);
			boolean listsize=driver.findElement(By.className("hidden-tab-data")).isDisplayed();//check dropdown value is close or not
			if(listsize==true)
				Assert.fail("Dropdown not get closed on clicking on arrow icon again.");

		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method studentnavigatealltab in class  StudentNavigateAllTab",e);
		}
	}	


}
