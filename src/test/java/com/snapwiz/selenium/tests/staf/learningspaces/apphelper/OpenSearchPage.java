package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;
/*
 * brajesh 
 * open search page of course
 */
public class OpenSearchPage extends Driver
{
	public void openSearchPage()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			new DirectLogin().CMSLogin();
			new SelectCourse().selectcourse();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.id("content-search-icon")),240);
            //new WebDriverUtil().clickOnElementUsingJavascript(driver.findElement(By.id("content-search-icon")));
            driver.findElement(By.id("content-search-icon")).click();
			Thread.sleep(5000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper OpenSearchPage in class OpenSearchPage",e);
		}
	}
	public void openSearchPageFORLS()
	{
		try
		{
			new DirectLogin().CMSLogin();
			new SelectCourse().selectLsCourse();
			new Click().clickByid("content-search-icon");
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper openSearchPageFORLS in class OpenSearchPage",e);
		}
	}
	//search question
	public void searchquestion(String text)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.id("content-search-field")),240);
			driver.findElement(By.id("content-search-field")).click();
			driver.findElement(By.id("content-search-field")).clear();
			driver.findElement(By.id("content-search-field")).sendKeys(text);
			Thread.sleep(2000);
			new Click().clickByid("search-question-contents-icon");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper searchquestion in class OpenSearchPage",e);
		}
	}

	public void selectQuestiontype(String questiontype)
	{
		try
		{
			new Click().clickByid("search-filter-link");
			new Click().clickbyxpath("html/body/div[6]/div[2]/div[1]/div[1]/div[2]/div/div/div[2]/div/div/div[1]/div[2]/div[3]/div/a[2]");
			new Click().clickBycssselector("a[title='"+questiontype+"']");
			new Click().clickByclassname("cms-conetent-search-go-btn");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper selectQuestiontype in class OpenSearchPage",e);
		}
	}

	public void OpenBrowsePage()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			new DirectLogin().CMSLogin();
			new SelectCourse().selectcourse();
			new Click().clickByid("content-search-icon");
			Thread.sleep(2000);
			new Click().clickByid("tab-browse");
			driver.findElement(By.xpath("html/body/div[6]/div[2]/div[1]/div[1]/div[3]/div[1]/div/div[1]/div[1]/div/a[2]")).click();
			Thread.sleep(2000);
			new Click().clickBycssselector("a[title='Search Questions']");
			driver.findElement(By.xpath("html/body/div[6]/div[2]/div[1]/div[1]/div[3]/div[1]/div/div[1]/div[2]/div/a[2]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("html/body/div[6]/div[2]/div[1]/div[1]/div[3]/div[1]/div/div[1]/div[2]/div/ul/div[2]/div/li[2]/a")).click();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper OpenBrowsePage in class OpenSearchPage",e);
		}
	}

	public void selectQuestiontypeInbrowsetab(String questiontype)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			new Click().clickbyxpath("html/body/div[6]/div[2]/div[1]/div[1]/div[3]/div[1]/div/div[2]/div[4]/div/a[2]");
			Thread.sleep(2000);
			new ScrollElement().scrollToViewOfElement(driver.findElement(By.xpath("//*[@id='cms-content-question-type-dropDown-section-browse-tab']//div[2]/div/li/a[@title='"+questiontype+"']")));
			new Click().clickbyxpath("//*[@id='cms-content-question-type-dropDown-section-browse-tab']//div[2]/div/li/a[@title='"+questiontype+"']");
			Thread.sleep(2000);

		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper selectQuestiontype in class OpenSearchPage",e);
		}
	}

	public void OpenBrowsePageAndFilterChapterName(String questiontype,int dataIndex)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
			String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
			System.out.println(":::"+chapterName);
			new DirectLogin().CMSLogin();
			new SelectCourse().selectcourse();
			new Click().clickByid("content-search-icon");
			Thread.sleep(2000);
			new Click().clickByid("tab-browse");
			driver.findElement(By.xpath("html/body/div[6]/div[2]/div[1]/div[1]/div[3]/div[1]/div/div[1]/div[1]/div/a[2]")).click();
			Thread.sleep(2000);
			new Click().clickBycssselector("a[title='Search Questions']");
			new Click().clickbylistxpath("//a[text()='Select an option']", 0);
			WebElement element=driver.findElement(By.xpath("(//a[@title='"+chapterName+"'])[2]"));
			new ScrollElement().scrollToViewOfElement(element);
			new Click().clickbylistxpath("//a[@title='" + chapterName + "']", 1);
			Thread.sleep(3000);
			selectQuestiontypeInbrowsetab(questiontype);
			manageContent.difficultyLevel.click();
			Thread.sleep(2000);
			manageContent.difficultyLevel_hard.click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[@title='Select Status']")).click();//select status
            new ScrollElement().scrollToViewOfElement(driver.findElement(By.xpath("//a[@title='Publish']")));
            driver.findElement(By.xpath("//a[@title='Publish']")).click();//select select publish


		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper OpenBrowsePage in class OpenSearchPage",e);
		}
	}
	public void OpenBrowsePageAndFilterChapterNameForLS(String questiontype,int dataIndex)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
			String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
			System.out.println(":::"+chapterName);
			new DirectLogin().CMSLogin();
			new SelectCourse().selectLsCourse();
			new Click().clickByid("content-search-icon");
			Thread.sleep(2000);
			new Click().clickByid("tab-browse");
			driver.findElement(By.xpath("html/body/div[6]/div[2]/div[1]/div[1]/div[3]/div[1]/div/div[1]/div[1]/div/a[2]")).click();
			Thread.sleep(2000);
			new Click().clickBycssselector("a[title='Search Questions']");
			new Click().clickbylistxpath("//a[text()='Select an option']", 0);
			WebElement element=driver.findElement(By.xpath("(//a[@title='"+chapterName+"'])[2]"));
			new ScrollElement().scrollToViewOfElement(element);
			new Click().clickbylistxpath("//a[@title='" + chapterName + "']", 1);
			Thread.sleep(3000);
			selectQuestiontypeInbrowsetab(questiontype);
			manageContent.difficultyLevel.click();
			Thread.sleep(2000);
			manageContent.difficultyLevel_hard.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@title='Select Status']")).click();//select status
			new ScrollElement().scrollToViewOfElement(driver.findElement(By.xpath("//a[@title='Publish']")));
			driver.findElement(By.xpath("//a[@title='Publish']")).click();//select select publish


		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper OpenBrowsePage in class OpenSearchPage",e);
		}
	}
	public void OpenBrowsePageAndFilterChapterNameForLSDefaultStatus(String questiontype,int dataIndex)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
			String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
			System.out.println(":::"+chapterName);
			new DirectLogin().CMSLogin();
			new SelectCourse().selectLsCourse();
			new Click().clickByid("content-search-icon");
			Thread.sleep(2000);
			new Click().clickByid("tab-browse");
			driver.findElement(By.xpath("html/body/div[6]/div[2]/div[1]/div[1]/div[3]/div[1]/div/div[1]/div[1]/div/a[2]")).click();
			Thread.sleep(2000);
			new Click().clickBycssselector("a[title='Search Questions']");
			new Click().clickbylistxpath("//a[text()='Select an option']", 0);
			WebElement element=driver.findElement(By.xpath("(//a[@title='"+chapterName+"'])[2]"));
			new ScrollElement().scrollToViewOfElement(element);
			new Click().clickbylistxpath("//a[@title='" + chapterName + "']", 1);
			Thread.sleep(3000);
			selectQuestiontypeInbrowsetab(questiontype);
			manageContent.difficultyLevel.click();
			Thread.sleep(2000);
			manageContent.difficultyLevel_hard.click();
			Thread.sleep(2000);


		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper OpenBrowsePage in class OpenSearchPage",e);
		}
	}
}
