package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R143;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AddNote;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class StudentAbleToSearchTag extends Driver
{
	@Test(priority=1,enabled=true)
	public void studentAbleToSearchTag()
	{
		try
		{
			String tag=ReadTestData.readDataByTagName("", "tag", "101");
			new LoginUsingLTI().ltiLogin("101");//login as student
			new AddNote().addNote("101", true, false,0);//added note without file
			Thread.sleep(2000);
			new AddNote().addTag(tag, 0);//add tag to 1st note
			new AddNote().addNote("101_1", true, false,0);//add note without file
			Thread.sleep(2000);
			int totalpostednote=driver.findElements(By.cssSelector("div[class='my-journal-card posted-note']")).size();//count number of added note
			//103,104
			new AddNote().searchtag(tag);
			//105,106,107,111-113
			driver.findElement(By.xpath("//*[@id='tags-search_feed']/li")).click();//click on tag  which is autosuggest by search filed
			Thread.sleep(2000);
			int notednoteafterselecttagfilter=driver.findElements(By.cssSelector("div[class='my-journal-card posted-note']")).size();//count number of added noted after tag filter applied
			if(notednoteafterselecttagfilter>totalpostednote)
				Assert.fail("after select tag filter posted not not filtered.");
			//114-117
			boolean filterapplied=new BooleanValue().booleanbyclass("filters-applied-title");
			if(filterapplied==false)
				Assert.fail("filter applied label not added below search filed");
			//118
			new Click().clickbylist("closebutton", 1);//close filtered tag
			Thread.sleep(2000);
			//119
			int noteafterdeletefilter=driver.findElements(By.cssSelector("div[class='my-journal-card posted-note']")).size();//count the added note after delete the search tag filter
			if(noteafterdeletefilter!=totalpostednote)
				Assert.fail("added note not refilter after delete the tag filter");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase StudentAbleToSearchTag in test method studentAbleToSearchTag ",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void studentAbletoAddMultpleSearchtagfilter()
	{
		try
		{
			String tag=ReadTestData.readDataByTagName("", "tag", "120");
			String tag1=ReadTestData.readDataByTagName("", "tag", "120_1");
			new LoginUsingLTI().ltiLogin("120");
			new AddNote().addNote("120", true, false,0);//add note without file
			new AddNote().addTag(tag, 0);//add tag with 1st note
			new AddNote().addNote("120_1", true, false,1);//add note without file
			new AddNote().addTag(tag1, 0);//add tag with 2nd note
			Thread.sleep(2000);
			new AddNote().addNote("120_1", true, false,1);//add note without file
			Thread.sleep(2000);
			int countofnotebeforsearchfilter=driver.findElements(By.cssSelector("div[class='my-journal-card posted-note']")).size();//count total number of add note before applied nay search tag filter
			if(countofnotebeforsearchfilter!=3)
				Assert.fail("total added node not shown even, no search filter applied");
			new AddNote().searchtag(tag);
			driver.findElement(By.xpath("//*[@id='tags-search_feed']/li")).click();//click on tag  which is autosuggest by search filed
			Thread.sleep(2000);
			//120-123
			new AddNote().searchtag(tag1);
			driver.findElement(By.xpath("//*[@id='tags-search_feed']/li")).click();//click on tag  which is autosuggest by search filed
			Thread.sleep(2000);
			int countofnotebeforsearchfilteraftertwofilterapplied=driver.findElements(By.cssSelector("div[class='my-journal-card posted-note']")).size();
			if(countofnotebeforsearchfilteraftertwofilterapplied!=2)
				Assert.fail("afetr applying two filter count of added note not increase");
			Thread.sleep(2000);
			//132
			new Click().clickBycssselector("a[title='All Chapters']");//click on all chapters dropdown
			driver.findElement(By.xpath("//*[starts-with(@id, 'sbOptions_')]/div[2]/div/li[2]/a")).click();// click on chapter name
			Thread.sleep(2000);
			int countofnotebeforsearchfilterafterchapterselect=driver.findElements(By.cssSelector("div[class='my-journal-card posted-note']")).size();
			if(countofnotebeforsearchfilterafterchapterselect!=1)
				Assert.fail("after selection of chapter1,only chapter1 conneted note not shown");
			//128-130
			new Navigator().NavigateTo("Assignments");//go to assignment page
			new Navigator().NavigateTo("My Notes");
			int countofnotebeforsearchfilteraftercomefromotherpage=driver.findElements(By.cssSelector("div[class='my-journal-card posted-note']")).size();//count total number of add note before applied nay search tag filter
			if(countofnotebeforsearchfilteraftercomefromotherpage!=3)
				Assert.fail("Tag filter not get reset after come form other page");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase StudentAbleToSearchTag in test method studentAbletoAddMultpleSearchtagfilter ",e);
		}
	}

}
