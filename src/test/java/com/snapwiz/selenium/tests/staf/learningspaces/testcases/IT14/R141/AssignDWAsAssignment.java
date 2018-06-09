package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

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
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

import java.util.List;

public class AssignDWAsAssignment extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void assignDWAsAssignment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("47");		//login a instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(47);
			int assignThisPopUp = driver.findElements(By.id("ir-ls-assign-dialog")).size();
			if(assignThisPopUp != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking on Assign button on Assigning a gradable DW assignment the pop-up doesnt disappear.");
			}
			new Navigator().NavigateTo("Assignments");	//navigate to Assignment
			String assignment = new TextFetch().textfetchbyclass("ls-assignment-post-label");
			if(!assignment.contains("posted an assignment."))	
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On assigning a gradable DW it doesnt appear in Assignment page.");
			}
			new ComboBox().selectValue(3, "Discussion Assignment");	//select Discussion Assignment
			String assignment1 = new TextFetch().textfetchbyclass("ls-assignment-post-label");
			if(!assignment1.contains("posted an assignment."))	
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("DW Assignment doesnt get filtered by option \"Discussion Assignment\" although it is a Gradable DW Assignment.");
			}
			new Assignment().deleteAssignment();
			int assignmentPresent = driver.findElements(By.className("ls-assignment-not-available")).size();
			//Tc row no. 50
			if(assignmentPresent == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Gradable DW assignment doesnt get deleted after we click on \"Delete Assignment\".");
			}
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			String questionText = new RandomString().randomstring(10);
			new DiscussionWidget().addTabInDW(questionText);
			//Tc row no. 52
			new DiscussionWidget().enableOrDisableDWQuestion(1);	//enable the added tab
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase assignDWAsAssignment in class AssignDWAsAssignment.",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void assignDWAsAssignmentAndAttemptAsStudent()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("53_1");		//login a student
			new LoginUsingLTI().ltiLogin("53");		//login a instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(53);
			new LoginUsingLTI().ltiLogin("53_1");		//login a student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignment
			new Click().clickByclassname("learning-activity-title"); //click on DW assignment
			String perspective = new RandomString().randomstring(10);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignemnt
			new LoginUsingLTI().ltiLogin("53");		//login a instructor
			new Navigator().NavigateTo("Assignments");	//navigate to Assignment
			int deleteAssignement = driver.findElements(By.className("delete-assigned-task")).size();
			if(deleteAssignement != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("\"Delete Assignment\" link for DW is present in Assignemnts page after the student attempts the DW.");
			}
		
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase assignDWAsAssignment in class AssignDWAsAssignment.",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void assignNonGradableDWAsAssignment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("125");		//login a instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(125);
			//TC row no. 125
			int assignThisPopUp = driver.findElements(By.id("ir-ls-assign-dialog")).size();
			if(assignThisPopUp != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After clicking on Assign button on Assigning a non-gradable DW assignment the pop-up doesnt disappear.");
			}
			//TC row no. 128
			String assignmentTab = driver.findElement(By.className("ls-right-user-subhead")).getText();
			if(!assignmentTab.contains("posted an assignment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On assigning a DW assignment the DW entry doesnt get added over right tab in textbook.");
			}
			new Navigator().NavigateTo("Assignments");	//navigate to Assignment
			//TC row no. 126
			String assignment = new TextFetch().textfetchbyclass("ls-assignment-post-label");
			if(!assignment.contains("posted an assignment."))	
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On assigning a non-gradable DW it doesnt appear in Assignment page.");
			}
			new Navigator().NavigateTo("Course Stream");	//navigate to Course Stream
			//TC row no. 127
			String assignment1 = new TextFetch().textfetchbyclass("ls-stream-post__action");
			if(!assignment1.contains("posted an assignment")) 
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On assigning a non-gradable DW it doesnt appear in Assignment page.");
			}
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			new ComboBox().selectValue(3, "Learning Activity");	//select Learning Activity
			//TC row no. 129
			String assignment2 = new TextFetch().textfetchbyclass("ls-assignment-post-label");
			if(!assignment2.contains("posted an assignment."))	
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Non Gradable DW Assignment doesnt get filtered by option \"Learning Activity\" although it is a non-Gradable DW Assignment.");
			}
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
            List<WebElement> assignThis1 = driver.findElements(By.cssSelector("span.assign-this-text"));
			//TC row no. 130
            if(!assignThis1.get(0).getText().equals("Assign this"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On DW \"Assign This\" link is not available.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase assignDWAsAssignment in class AssignDWAsAssignment.",e);
		}
	}

}
