package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.MouseHover;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SearchQuestion;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Click;
import com.snapwiz.selenium.tests.staf.orion.uihelper.ComboBox;

public class SearchQuestionBasedOnText 
{
	@Test(priority=1,enabled=true)
	public void searchquestionbasedontext()
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();//login as author
			new SelectCourse().selectcourse();//select course
            Thread.sleep(3000);
			new SearchQuestion().searchquestion("which");//Search question with passed string

			int countofquestion=Driver.driver.findElements(By.id("content-search-results-block")).size();//Fetch count of search result
			if(countofquestion<1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("no question found based on search string. ");
			}
			MouseHover.mouserhoverbyid("content-search-results-block");//mouse hover on question found block
			boolean expandquestion=Driver.driver.findElement(By.className("expand-question-content")).isDisplayed();//verify expand link
			if(expandquestion==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("expand link not present");
			}
			boolean fullpriviewquestion=Driver.driver.findElement(By.className("preview-question-content")).isDisplayed();//verify full preview link
			if(fullpriviewquestion==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("fullpriviewquestion link not present");
			}
			boolean editquestion=Driver.driver.findElement(By.className("edit-question-content")).isDisplayed();//verify edit link
			if(editquestion==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("editquestion link not present");
			}
			boolean chaptername=Driver.driver.findElement(By.cssSelector("div[class='search-question-content search-question-content-chapter-name']")).isDisplayed();//verify chapter name
			if(chaptername==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("chaptername  not present");
			}
			boolean assessmentname=Driver.driver.findElement(By.cssSelector("div[class='search-question-content search-question-content-assessment-name']")).isDisplayed();//verify assessment name
			if(assessmentname==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("assessmentname  not present");
			}
			boolean objectivename=Driver.driver.findElement(By.cssSelector("span[class='search-question-content']")).isDisplayed();//verify objective associate
			if(objectivename==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("objectivename  not present");
			}
			new Click().clickByid("content-search-icon");
			Thread.sleep(2000);
			boolean copybutton=Driver.driver.findElement(By.id("content-search-copy-btn")).isDisplayed();//verify copy button
			if(copybutton==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("copybutton  not present");
			}
			boolean movebutton=Driver.driver.findElement(By.id("content-search-move-btn")).isDisplayed();//verify move button
			if(movebutton==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("movebutton  not present");
			}
			boolean deletebutton=Driver.driver.findElement(By.id("content-search-delete-btn")).isDisplayed();//verify delete button
			if(deletebutton==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("deletebutton  not present");
			}
			boolean reviewbutton=Driver.driver.findElement(By.id("content-search-review-btn")).isDisplayed();//verify review button
			if(reviewbutton==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("reviewbutton  not present");
			}
            new Click().clickByid("search-filter-link");//click on filter search link
            Thread.sleep(2000);
			boolean allchapter=Driver.driver.findElement(By.cssSelector("a[title='All Chapters (Includes Course Level)']")).isDisplayed();//verify all chapter dropdown
			if(allchapter==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("allchapter dropdown  not present");
			}
			boolean allobjective=Driver.driver.findElement(By.cssSelector("a[title='All Objectives']")).isDisplayed();//verify all objective
			if(allobjective==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("allobjective dropdown  not present");
			}
			
			boolean allstatus=Driver.driver.findElement(By.cssSelector("a[title='All Status']")).isDisplayed();//verify all status
			if(allstatus==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("allstatus dropdown  not present");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("exception in testcase searchquestionbasedontext in class SearchQuestionBasedOnText",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void searchbasedonchapterdropdown()
	{
		try
		{
			Driver.startDriver();
			String chapter1=ReadTestData.readDataByTagName("chapternames", "chapter1", "0");
			String chapter2=ReadTestData.readDataByTagName("chapternames", "chapter2", "0");
			String objective1=ReadTestData.readDataByTagName("tlos", "tlo1", "0");
			String objective2=ReadTestData.readDataByTagName("tlos", "tlo2", "0");
			objective1 = "1.1: "+objective1;
            objective2 = "1.2: "+objective2;
			new DirectLogin().CMSLogin();//login as author
			new SelectCourse().selectcourse();//select course
            Thread.sleep(3000);
			new SearchQuestion().searchquestion("which");//Search question with passed string
			new Click().clickByid("search-filter-link");//click on filter search link
			new ComboBox().selectValuewithtitle(4, chapter1);//click on dropdown chapter1 value
			Thread.sleep(2000);
            new Click().clickByclassname("cms-conetent-search-go-btn"); //clicking on Go button
            Thread.sleep(2000);
			List<WebElement> allchapter=Driver.driver.findElements(By.cssSelector("div[class='search-question-content search-question-content-chapter-name']"));//fetch all chapter name
			for(WebElement chapter:allchapter)
			{
				String chaptertext=chapter.getAttribute("title");
				if(!chaptertext.contains("Ch 1"))
				{
					Assert.fail("filter not  shown result based on selected dropdown chapter 1");
				}
			}
			Thread.sleep(2000);
            new Click().clickByid("search-filter-link");//click on filter search link
            Thread.sleep(2000);
			new ComboBox().selectValuewithtitle(4, chapter2);//click on dropdown chapter2 value
			Thread.sleep(2000);
            new Click().clickByclassname("cms-conetent-search-go-btn"); //clicking on Go button
            Thread.sleep(2000);
			List<WebElement> allchapter2=Driver.driver.findElements(By.cssSelector("div[class='search-question-content search-question-content-chapter-name']"));
			for(WebElement chapter:allchapter2)
			{
				String chaptertext=chapter.getAttribute("title");			
				if(!chaptertext.contains("Ch 2"))
				{
					Assert.fail("filter not  shown result based on selected dropdown chapter2");
				}
			}
            Thread.sleep(2000);
            new Click().clickByid("search-filter-link");//click on filter search link
            Thread.sleep(2000);
			new ComboBox().selectValuewithtitle(4, chapter1);//click on dropdown chapter1 value
			new ComboBox().selectValuewithtitle(8, objective1);
			Thread.sleep(2000);
            new Click().clickByclassname("cms-conetent-search-go-btn"); //clicking on Go button
            Thread.sleep(2000);
			List<WebElement> allobjective=Driver.driver.findElements(By.cssSelector("span[class='search-question-content']"));
			for(WebElement objective:allobjective)
			{
				String objectivetext=objective.getAttribute("title");
				if(!objectivetext.contains("1.1"))
				{
					Assert.fail("filter not  shown result based on selected dropdown chapter2");
				}
			}
            Thread.sleep(2000);
            new Click().clickByid("search-filter-link");//click on filter search link
            Thread.sleep(2000);
			new ComboBox().selectValuewithtitle(8, objective2);
			Thread.sleep(2000);
            new Click().clickByclassname("cms-conetent-search-go-btn"); //clicking on Go button
            Thread.sleep(2000);
			List<WebElement> allobjective1=Driver.driver.findElements(By.cssSelector("span[class='search-question-content']"));
			for(WebElement objective:allobjective1)
			{
				String objectivetext=objective.getAttribute("title");			
				if(!objectivetext.contains("1.2"))
				{
					Assert.fail("filter not  shown result based on selected dropdown chapter2");
				}
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("exception in testcase searchbasedonchapterdropdown in class SearchQuestionBasedOnText",e);
		}
	}
	@Test(priority=3,enabled=true)
	public void mousehoveronquestionblockonsearchpage()
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();//login as author
			new SelectCourse().selectcourse();//select course
			new SearchQuestion().searchquestion("which");//Search question with passed string
			new Click().clickByid("search-filter-link");//click on filter search link
            Thread.sleep(2000);
            new Click().clickByid("search-question-contents-icon"); //clicking on Go button
            Thread.sleep(2000);
			MouseHover.mouserhoverbyid("content-search-results-block");//mouse hover on question found block
			//Driver.driver.findElement(By.id("expand-question-content")).click();//click on expand question link
			new Click().clickByclassname("expand-question-content");
			boolean answeroption=Driver.driver.findElement(By.cssSelector("label[class='qtn-label ']")).isDisplayed();
			if(answeroption==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("after click on expand link question not expand.");
			}
			MouseHover.mouserhover("cms-content-question-review-question-wrapper");//mouse hover on question found block
			new Click().clickByclassname("collapse-question-content");
			MouseHover.mouserhoverbyid("content-search-results-block");//mouse hover on question found block
			new Click().clickByclassname("preview-question-content");
			Thread.sleep(3000);
			String winHandleBefore = Driver.driver.getWindowHandle();//store window before open
			for(String winHandle : Driver.driver.getWindowHandles()){
			    Driver.driver.switchTo().window(winHandle);
			}
			new Click().clickByid("question-reveview-submit");
			Driver.driver.close();//close new open window
			Driver.driver.switchTo().window(winHandleBefore);
			MouseHover.mouserhoverbyid("content-search-results-block");//mouse hover on question found block
			new Click().clickByclassname("edit-question-content");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("exception in testcase mousehoveronquestionblockonsearchpage in class SearchQuestionBasedOnText",e);
		}
	}
	@Test(priority=4,enabled=true)
	public void browsertab()
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();//login as author
			new SelectCourse().selectcourse();//select course
            Thread.sleep(2000);
			new Click().clickByid("content-search-icon");//
            Thread.sleep(2000);
			new Click().clickByid("tab-browse");
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("exception in testcase browsertab in class SearchQuestionBasedOnText",e);
		}
		
	}
    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
