package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R38;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Bookmark;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ResourseCreate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SearchTextAndFetchTheResult;

public class BookmarkVerifyFromTOCSearch extends Driver {
@Test(priority = 1, enabled = true)
public void bookmarkVerifyFromTOCSearchForResource()
	{
		try
		{
			String resoursename = ReadTestData.readDataByTagName("", "resoursename", "142");
			new ResourseCreate().resourseCreate(142, 0);
			new LoginUsingLTI().ltiLogin("142");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new SearchTextAndFetchTheResult().searchTextAndFetchTheResult(resoursename);
			driver.findElement(By.cssSelector("div[class='ls-search-icon']")).click();
			Thread.sleep(5000);
			driver.findElement(By.className("ls-result-view-title")).click();
			Thread.sleep(2000);
			int starIcon = driver.findElements(By.cssSelector("i[class='ls-right-section-sprites ls--right-star-icon-resource']")).size();
			if(starIcon == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("* icon is absent for resource, on searching from TOC.");
			}
			new Bookmark().bookmark("resource");
			boolean isBookmarked = new Bookmark().isBookmarked("resource");
			if(isBookmarked == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the resource when opened from searching from TOC * icon doesn't turn to yellow.");
			}
			driver.findElement(By.xpath("(//span[@class='tab_title'])[3]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//span[@class='tab_title'])[4]")).click();
			Thread.sleep(2000);
			boolean isBookmarked1 = new Bookmark().isBookmarked("resource");
			if(isBookmarked1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("When we navigate to different tab and again come back to resource tab the * icon doesnt remain yellow.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookmarkVerifyFromTOCSearchForResource in class BookmarkVerifyFromTOCSearch",e);
		}
	}
@Test(priority = 2, enabled = true)
public void bookmarkVerifyFromTOCSearchForLesson()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("142_1");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            Thread.sleep(5000);
			/*WebElement scroll=driver.findElement(By.cssSelector("a[data-type='lesson']"));
            Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            Thread.sleep(2000);
			String firstLesson = driver.findElement(By.cssSelector("a[data-type='lesson']")).getText();//1st lesson name
            System.out.println("firstLesson : " + firstLesson);*/
            //String firstLesson = "Introduction";
            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            String firstLesson = driver.findElement(By.cssSelector("a[data-type='lesson']")).getAttribute("title");//1st lesson name
            new SearchTextAndFetchTheResult().searchTextAndFetchTheResult(firstLesson);
			driver.findElement(By.cssSelector("div[class='ls-search-icon']")).click();
			Thread.sleep(5000);
			List<WebElement> allResult = driver.findElements(By.className("ls-result-view-title"));
			allResult.get(1).click();
			Thread.sleep(2000);
			int starIcon = driver.findElements(By.cssSelector("a[class='lesson-bookmark-sprite-img bookmark-lesson']")).size();
			if(starIcon == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("* icon is absent for lesson, on searching from TOC.");
			}
			new Bookmark().bookmark("lesson");
			boolean isBookmarked = new Bookmark().isBookmarked("lesson");
			if(isBookmarked == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After bookmarking the lesson (when opened from searching from TOC) the * icon doesn't turn to yellow.");
			}
			//click on next chapter
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a.ls-next")));
			Thread.sleep(2000);
			//click on previous chapter
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a.ls-prev")));
			Thread.sleep(2000);
			boolean isBookmarked2 = new Bookmark().isBookmarked("lesson");
			if(isBookmarked2 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to any other lesson and coming back to the previous lesson the * icon of lesson doesn't remain yellow.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase bookmarkVerifyFromTOCSearchForLesson in class BookmarkVerifyFromTOCSearch",e);
		}
	}

}
