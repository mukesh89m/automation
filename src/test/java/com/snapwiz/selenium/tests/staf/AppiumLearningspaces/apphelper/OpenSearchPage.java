package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.SelectCourse;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;

/*
 * brajesh 
 * open search page of course
 */
public class OpenSearchPage
{
	public void openSearchPage()
	{
		try
		{
			new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin().CMSLogin();
			new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectCourse().selectcourse();
			new Click().clickByid("content-search-icon");
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper OpenSearchPage in class OpenSearchPage",e);
		}
	}
	//search question
	public void searchquestion(String text)
	{
		try
		{
			Driver.driver.findElement(By.id("content-search-field")).click();
			Driver.driver.findElement(By.id("content-search-field")).sendKeys(text);
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
			new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin().CMSLogin();
			new SelectCourse().selectcourse();
			new Click().clickByid("content-search-icon");
			Thread.sleep(2000);
			new Click().clickByid("tab-browse");
			Driver.driver.findElement(By.xpath("html/body/div[6]/div[2]/div[1]/div[1]/div[3]/div[1]/div/div[1]/div[1]/div/a[2]")).click();
			Thread.sleep(2000);
			new Click().clickBycssselector("a[title='Search Questions']");
			Driver.driver.findElement(By.xpath("html/body/div[6]/div[2]/div[1]/div[1]/div[3]/div[1]/div/div[1]/div[2]/div/a[2]")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.xpath("html/body/div[6]/div[2]/div[1]/div[1]/div[3]/div[1]/div/div[1]/div[2]/div/ul/div[2]/div/li[2]/a")).click();
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

			new Click().clickbyxpath("html/body/div[6]/div[2]/div[1]/div[1]/div[3]/div[1]/div/div[2]/div[4]/div/a[2]");
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(questiontype)).click();
			Thread.sleep(2000);

		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper selectQuestiontype in class OpenSearchPage",e);
		}
	}

}
