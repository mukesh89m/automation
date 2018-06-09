package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiscussionWidget;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;

public class StudentsAddPerspectiveToDW extends Driver{
	
	@Test
	public void studentsAddPerspectiveToDW()
	{
		try
		{
		    new LoginUsingLTI().ltiLogin("45_1");		//login a instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			String str = new RandomString().randomstring(10);
			new DiscussionWidget().addTabInDW(str);
			new DiscussionWidget().enableOrDisableDWQuestion(1); //enable the tab
			
			new LoginUsingLTI().ltiLogin("45");		//login a student
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidgetForStudent();
			new Widget().perspectiveAdd(); 	//add a perspective

			new LoginUsingLTI().ltiLogin("45_1");		//login a instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().navigateToTab(0); //navigate to 1st tab
			new DiscussionWidget().enableOrDisableDWQuestion(0); //enable the tab
			//TC row no. 45
			List<WebElement> color = driver.findElements(By.cssSelector("span[class='publisher-count ls-publisherIcons-bg ls-publisher-count-bg-holder']"));
			for(WebElement ele : color) {
				System.out.println(">>>>>"+ele.getCssValue("background-position"));
			}
            if(!color.get(0).getCssValue("background-position").trim().equals("-4px -32px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor Able to enable a different DW question after student posts a perspective for a DW question.");
			}
			//TC row no. 46
			int count= driver.findElements(By.xpath("//span[@class='publisher-count ls-publisherIcons-bg ls-publisher-count-bg-holder']")).size();
			if (count > 2)
			{
				Assert.fail("Instructor Able to add more tabs after student posts a perspective for a DW question.");
			}
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(45);
			//TC row no. 49
			int size1 = driver.findElements(By.cssSelector("span[class='ls-discussion-widget-publisherIcons-bg ls-discussion-widget-publisher-addCount-bg']")).size();
			if(size1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor Able to add more tabs after DW in assigned.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase studentsAddPerspectiveToDW in class StudentsAddPerspectiveToDW.",e);
		}
	}

}
