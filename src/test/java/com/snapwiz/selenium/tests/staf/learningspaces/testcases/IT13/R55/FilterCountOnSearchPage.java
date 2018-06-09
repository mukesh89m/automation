package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R55;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.OpenSearchPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class FilterCountOnSearchPage extends Driver
{
	@Test()
	public void filterCountOnSearchPage()
	{
		try
		{
			String searchtext=ReadTestData.readDataByTagName("tocdata", "searchtext", "1");
			new OpenSearchPage().openSearchPage();
			new OpenSearchPage().searchquestion(searchtext);
			Thread.sleep(2000);
			//57-60---select filter and count filter
			new OpenSearchPage().selectQuestiontype("True / False");//select true false question type
			new Click().clickByid("search-filter-link");//click on search filter
			new Click().clickBycssselector("a[title='All Difficulty Level']");	//click on all difficulty	
			new Click().clickBycssselector("a[title='Easy']");//select easy type
			new Click().clickBycssselector("a[title='All Status']");	//click on all status 	
			new Click().clickBycssselector("a[title='Draft']");//select draft
			new Click().clickByclassname("cms-conetent-search-go-btn");//click on go button
			String filtercount=new TextFetch().textfetchbyid("search-filter-count");//filter count
			if(!filtercount.contains("3"))
			{
				Assert.fail("filter count not shown");
			}
			new OpenSearchPage().selectQuestiontype("All Question Type");//select default type question type
			String filtercount1=new TextFetch().textfetchbyid("search-filter-count");
			if(!filtercount1.contains("2"))
			{
				Assert.fail("filter count not decrease");
			}
			new Click().clickByid("deliver-course");//go to summary page
			new Click().clickByid("content-search-icon");//come back to search page
			String filtercount2=new TextFetch().textfetchbyid("search-filter-count");//agian count the filter
			if(!filtercount1.equals(filtercount2))
			{
				Assert.fail("filter count not same after going to other activity and come back to search page");
			}
			//63-65---filter value with previous value nad get final filter count
			new Click().clickByid("search-filter-link");//click on search filter
			new Click().clickBycssselector("a[title='Easy']");//select easy type
			new Click().clickBycssselector("a[title='All Difficulty Level']");	//click on all difficulty	
			new Click().clickBycssselector("a[title='Draft']");//select draft
			new Click().clickBycssselector("a[title='All Status']");	//click on all status 	
			new Click().clickByclassname("cms-conetent-search-go-btn");//click on go button
			boolean finalfiltercount=driver.findElement(By.id("search-filter-count")).isDisplayed();//agian count the filter
			if(finalfiltercount==true)
			{
				Assert.fail("count value shown even after no filter selected");
			}			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase class FilterQuestionTypeSearchPage in method filterQuestionTypeSearchPage ",e);
		}
	}

}
