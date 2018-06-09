package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R22;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;

public class AvailabilityOfSearchAndVBrowseOptionInNewAssignmentTab extends Driver
{
	@Test
	public void availabilityOfSearchAndVBrowseOptionInNewAssignmentTab()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("47");//login as instructor
			new Navigator().NavigateTo("Question Banks");//navigate to Assignments
			new Click().clickByid("customAssignment");//click on create custom assignment
            new Click().clickByclassname("ls-ins-search-text");//click on search box
			String defaultTextInSearchBox = driver.findElement(By.cssSelector("input[class='ls-ins-search-question-content ls-ins-search-question-content-selected']")).getAttribute("placeholder");//default text of serachbox
            if(!defaultTextInSearchBox.contains("Search question statements..."))
			{
				Assert.fail("Default text in not present in search box");
			}
			boolean searchButton=new BooleanValue().booleanbyclass("ls-ins-search-icon");
			if(searchButton==false)
			{
				Assert.fail("Search button is not present");
			}
			boolean browseButton=new BooleanValue().booleanbyclass("ls-ins-browse-icon");
			if(browseButton==false)
			{
				Assert.fail("Browse button is not present");
			}
            new Click().clickByclassname("ls-ins-browse-text");//click on Browse button
			boolean searchresult = new BooleanValue().booleanbyclass("ls-ins-search-result");
			if(searchresult==false)
			{
				Assert.fail("Search result is not present");
			}
			String colorsearchbutton=driver.findElement(By.className("ls-ins-search-icon")).getCssValue("background-color");//color of search button
			new Click().clickByclassname("ls-ins-browse-icon");//click on browse button
			boolean searchresult1=new BooleanValue().booleanbyclass("ls-ins-search-result");//verify search result field
			if(searchresult1==false)
			{
				Assert.fail("searchresult1 not present");
			}
			String colorbrowsebutton=driver.findElement(By.className("ls-ins-browse-icon")).getCssValue("background-color");//color of browse button
			if(!colorbrowsebutton.equals(colorsearchbutton))
			{
				Assert.fail("color of both button not equal");
			}
			new Click().clickBycssselector("input[class='ls-ins-search-question-content ls-ins-search-question-content-selected']");//click in search box
			String searchboxwidth=driver.findElement(By.cssSelector("input[class='ls-ins-search-question-content ls-ins-search-question-content-selected']")).getAttribute("style");//fetch width of search box
			new Click().clickBycssselector("span[title='Question Banks']");
			new Click().clickBycssselector("span[title='New Assignment']");
			String searchboxwidth1=driver.findElement(By.cssSelector("input[class='ls-ins-search-question-content ls-ins-search-question-content-selected']")).getAttribute("style");//fetch width of search box
			if(!searchboxwidth.contains(searchboxwidth1))
			{
				Assert.fail("width ofsearch edit box not increase and differ when tab switching(all resources to new asssignment)");
			}
			new Click().clickBycssselector("span[title='Question Banks']");
			new Click().clickBycssselector("span[title='New Assignment']");
			String searchboxwidth2=driver.findElement(By.cssSelector("input[class='ls-ins-search-question-content ls-ins-search-question-content-selected']")).getAttribute("style");//fetch width of search box
			if(!searchboxwidth.contains(searchboxwidth2))
			{
				Assert.fail("width ofsearch edit box not increase and differ when tab switching(my resources to new asssignment)");
			}
			String serachrtext=ReadTestData.readDataByTagName("tocdata", "searchtext", "1");
            new Click().clickByclassname("ls-ins-search-text");//click on search box

            new TextSend().textsendbycssSelector(serachrtext, "input[class='ls-ins-search-question-content ls-ins-search-question-content-selected ls-ins-empty-search']");// text entry for search
			new Click().clickByclassname("ls-ins-search-icon");
			int searchcount=driver.findElements(By.className("ls-ins-question-wrapper")).size();//total number of search result
			new Click().clickBycssselector("span[title='Question Banks']");
			new Click().clickBycssselector("span[title='New Assignment']");
			int searchcount1=driver.findElements(By.className("ls-ins-question-wrapper")).size();//total number of search result
			if(searchcount!=searchcount1)
			{
				Assert.fail("search count not statble while swithg the tab(all resources to new assignment)");
			}
			new Click().clickBycssselector("span[title='Question Banks']");//click on my recourse tab
			new Click().clickBycssselector("span[title='New Assignment']");//click on new assignment tab
			int searchcount2=driver.findElements(By.className("ls-ins-question-wrapper")).size();//total number of search result
			if(searchcount!=searchcount2)
			{
				Assert.fail("search count not statble while swithg the tab(my resources to new assignment)");
			}

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase AvailabilityOfSearchAndVBrowseOptionInNewAssignmentTab in method availabilityOfSearchAndVBrowseOptionInNewAssignmentTab ",e);
		}
	}

}
