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

public class VisibilityOfNewlyAddedQuestion extends Driver{
	
	@Test
	public void visibilityOfNewlyAddedQuestion()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("76_1");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			String str = new RandomString().randomstring(10);
			new DiscussionWidget().addTabInDW(str);
			new DiscussionWidget().enableOrDisableDWQuestion(1); //enable the tab

			new LoginUsingLTI().ltiLogin("76");		//login as student
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidgetForStudent();
            boolean found = false;
			List<WebElement> DWText = driver.findElements(By.className("ls-dialog-txt"));
            for(WebElement text: DWText)
            {
                if(text.getText().contains(str))
                {
                    found = true;
                    break;
                }

            }
			//TC row no. 77
			if(found == false)	//recently added DW question is at last index
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Newly added question in a DW is not available to the student.");
			}

			new LoginUsingLTI().ltiLogin("76_2");		//login as another instructor of same class section
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			//TC row no. 79
            boolean found1 = false;
			List<WebElement> DWText1 = driver.findElements(By.className("tab-content-data"));
            for(WebElement text: DWText1)
            {
                if(text.getText().contains(str))
                {
                    found1 = true;
                    break;
                }

            }
			if(found1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Newly added question in a DW is not available to another instructor of same class section.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase visibilityOfNewlyAddedQuestion in class VisibilityOfNewlyAddedQuestion.",e);
		}
	}

}
