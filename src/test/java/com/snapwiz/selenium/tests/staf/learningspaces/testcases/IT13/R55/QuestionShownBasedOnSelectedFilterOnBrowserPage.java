package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R55;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.OpenSearchPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class QuestionShownBasedOnSelectedFilterOnBrowserPage extends Driver
{
	@Test
	public void questionShownBasedOnSelectedFilterOnBrowserPage()
	{
		try
		{
			new OpenSearchPage().OpenBrowsePage();
			Thread.sleep(2000);
			new OpenSearchPage().selectQuestiontypeInbrowsetab("True / False");	
			WebElement we=driver.findElement(By.xpath("html/body/div[6]/div[2]/div[1]/div[3]/div[2]/div[8]/div[1]"));
			new MouseHover().mouserhoverbywebelement(we);
			new Click().clickbyxpath("html/body/div[6]/div[2]/div[1]/div[3]/div[2]/div[8]/div[3]/div[1]");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase class QuestionShownBasedOnSelectedFilterOnBrowserPage in testcase method",e);
		}
	}

}
