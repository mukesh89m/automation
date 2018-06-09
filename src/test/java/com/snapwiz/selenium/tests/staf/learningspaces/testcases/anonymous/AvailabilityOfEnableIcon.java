package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandFirstChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;

public class AvailabilityOfEnableIcon extends Driver{
	@Test
	public void availabilityOfEnableIcon()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("2141");		//login as instructor
			new Navigator().NavigateTo("eTextbook");
            new TopicOpen().openLessonWithDiscussionWidget();
			int enableicon = driver.findElements(By.cssSelector("div[class='select-question ls-publisherIcons-bg selected']")).size();
			if(enableicon == 0)
				Assert.fail("Enable icon is absent in 1st question tab of discussion widget in instructor site.");

            new Click().clickByclassname("ls-discussion-widget-publisher-addTab");//click to add tab
			int enableicon1 = driver.findElements(By.cssSelector("div[class='select-question ls-publisherIcons-bg']")).size();
			if(enableicon1 == 0)
				Assert.fail("Enable icon is absent in 2nd question tab of discussion widget in instructor site.");
			String enableText = driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg']")).getAttribute("title");
			if(!enableText.equals("Enable"))
				Assert.fail("Enable icon doesn't show tooltip 'Enable' in 2nd question tab of discussion widget in instructor site");

            //click to enable 2nd question
            new Click().clickBycssselector("div[class='select-question ls-publisherIcons-bg']");
			//the 'title' attribute will be blank for enabled question.
			String enableIcon1 = driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg selected']")).getAttribute("title");
			if(!enableIcon1.equals(""))
				Assert.fail("After clicking the enable icon the icon doesn't become disabled.");

		}
		catch(Exception e)
		{
			Assert.fail("Exception in availabilityOfEnableIcon in AvailabilityOfEnableIcon class.",e);
		}
	}

}
