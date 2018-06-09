package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.TextSend;

public class SearchQuestionInCustomCourseAssignemnt 
{

	public void searchQuestionInCustomCourseAssignemnt(String serachtext)
	{
		try
		{
            new UIElement().waitAndFindElement(By.className("ls-ins-search-icon"));
            Driver.driver.findElement(By.className("ls-ins-search-icon")).click();
            new UIElement().waitAndFindElement(By.cssSelector("input[class='ls-ins-search-question-content ls-ins-search-question-content-selected']"));
            new TextSend().textsendbycssSelector("\"" + serachtext + "\"", "input[class='ls-ins-search-question-content ls-ins-search-question-content-selected']");// text entry for search
			new Click().clickByclassname("ls-ins-search-icon");//click on search button
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper SearchQuestionInCustomCourseAssignemnt in method SearchQuestionInCustomCourseAssignemnt ",e);
		}
	}
}
