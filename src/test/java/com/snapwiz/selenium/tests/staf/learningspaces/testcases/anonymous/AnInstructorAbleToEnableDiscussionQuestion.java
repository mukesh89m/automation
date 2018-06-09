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
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Widget;

public class AnInstructorAbleToEnableDiscussionQuestion extends Driver {
	@Test
	public void anInstructorAbleToEnableDiscussionQuestion()
	{
		try
		{
			String textonstudentsite = ReadTestData.readDataByTagName("AnInstructorAbleToEnableDiscussionQuestion", "textonstudentsite", "21263");

			new LoginUsingLTI().ltiLogin("21261");		//login as instructor of 1st class section
			new Navigator().NavigateTo("eTextbook");
            new TopicOpen().openLessonWithDiscussionWidget();
			List<WebElement> allQuestion = driver.findElements(By.className("tab-content-data"));
			for(WebElement L : allQuestion)
			{
				System.out.println("-->"+L.getText());
			}
			//check question text
			String question = allQuestion.get(0).getText();		//text are of 1st tab so index 0
			if(question == null)
				Assert.fail("At instructor site the question text is not getting displayed.");
			//check enble icon
			int enableIcon = driver.findElements(By.cssSelector("div[class='select-question ls-publisherIcons-bg selected']")).size();
			if(enableIcon == 0)
				Assert.fail("The enable icon is not visible in the instructor site.");
			String jumpIcon = driver.findElement(By.cssSelector("span[class='jumpout-icon ls-jumpout-img']")).getCssValue("background-image");
			if(!jumpIcon.contains("sprite.png"))
				Assert.fail("Jump icon in not visible in the discussion widget, in instructor site.");
			
			String enabledField = driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg selected']")).getAttribute("title");
			if(enabledField.length() != 0)		//the 'title' attribute will be blank for enabled question.
				Assert.fail("Question enabled by author is not shown enabled in instructor.");
            new Click().clickByclassname("ls-discussion-widget-publisher-addTab");//click to add tab

			//click to enable 2nd question
            new Click().clickBycssselector("div[class='select-question ls-publisherIcons-bg']");
			String enabledField1 = driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg selected']")).getAttribute("title");
			if(enabledField1.length() != 0)		//the 'title' attribute will be blank for enabled question.
				Assert.fail("On clicking enable icon by the instructor, the 2nd question doesn't become enable.");

            new Widget().navigateToDiscussionTab(0);	//goto 1st tab
			String enabledField2 = driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg']")).getAttribute("title");
			if(!enabledField2.equals("Enable"))
				Assert.fail("After enbling the 2nd question by the instructor the 1st question doesn't become disabled.");
			
			new LoginUsingLTI().ltiLogin("21263");		//login as student of 1st class section
			new Navigator().NavigateTo("eTextbook");
            new TopicOpen().openLessonWithDiscussionWidget();
			List<WebElement> allstudentQuestion = driver.findElements(By.className("ls-student-question"));
			String studentQuestion = allstudentQuestion.get(1).getText();		//text are of 2nd tab so index 1
			if(!studentQuestion.contains(textonstudentsite))
				Assert.fail("At student site the question which has been enabled is not getting displayed.");

			new LoginUsingLTI().ltiLogin("21262");		//login as instructor of 2nd class section
			new Navigator().NavigateTo("eTextbook");
            new TopicOpen().openLessonWithDiscussionWidget();
			String enabledField3 = driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg selected']")).getAttribute("title");
			if(enabledField3.length() != 0)		//the 'title' attribute will be blank for enabled question.
				Assert.fail("Login as instructor requesting class secion 2 then the 1st question is not enabled by default.");
			
			new LoginUsingLTI().ltiLogin("21264");		//login as student of 2nd class section
			new Navigator().NavigateTo("eTextbook");
            new TopicOpen().openLessonWithDiscussionWidget();
			List<WebElement> allstudentQuestion1 = driver.findElements(By.className("ls-student-question"));
			String studentQuestion1 = allstudentQuestion1.get(0).getText();		//text are of 1st tab so index 0
			if(studentQuestion1.contains(textonstudentsite))
				Assert.fail("At student site the question which has been enabled is not getting displayed.");
			
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in anInstructorAbleToEnableDiscussionQuestion in AnInstructorAbleToEnableDiscussionQuestion class.",e);
		}
	}

}
