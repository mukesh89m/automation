package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R50;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentPolicy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class DifferentFieldsOfAssigmentPolicy extends Driver {
	
	@Test(priority = 1, enabled = true)
	public void policyNameField()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("10");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
			String nameField = new TextFetch().textfetchbyid("ls-ins-assignment-policy-name");
			if(!nameField.contains("Click to enter a name for this policy..."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The default text in Name Field is absent in New Policy tab.");
			}
			new AssignmentPolicy().enterPolicyName("Policy Name");
		    String tabName = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!tabName.equals("Policy Name"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Name of the tab doesnt changes when we provide a new policy name.");
			}
			//create a string of length 255 characters
			String str = "";
			for (int i = str.length(); i < 255; i++)
	            str = str+"a";
			new AssignmentPolicy().enterPolicyName(str);
		    String tabName1 = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!tabName1.equals(str))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The name field doesnt support 255 characters.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase policyNameField in class DifferentFieldsOfAssigmentPolicy",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void descriptionField()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("16");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
			String descField = new TextFetch().textfetchbyid("ls-ins-assignment-policy-desc");
			if(!descField.contains("Click to enter a description for this policy..."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The default text in assignment policy description is absent in New Policy tab.");
			}
		    new AssignmentPolicy().enterPolicyDescription("Policy description text.");
		    String descField1 = new TextFetch().textfetchbyid("ls-ins-assignment-policy-desc");
			if(!descField1.contains("Policy description text."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Unable modify the policy description test.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase descriptionField in class DifferentFieldsOfAssigmentPolicy",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void scorePerQuestionField()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("21");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
			//name the policy
			new AssignmentPolicy().enterPolicyName("Policy Name.");
			List<WebElement> allFields = driver.findElements(By.className("ls-assignment-policy-question"));
			if(!allFields.get(0).getText().contains("Points per question"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Score per question field is absent in New Policy tab.");
			}
			String defaultValue = driver.findElement(By.cssSelector("input[id='score']")).getAttribute("value");
			if(defaultValue.length()>0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Score per question field is not blank by default.");
			}
			driver.findElement(By.id("score")).click();		//click on score per question field
			driver.findElement(By.id("score")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("score")).sendKeys("356");
			driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
			Thread.sleep(2000);
			new Click().clickByclassname("ls-save-policy-btn");		//click on Save Policy
			new TabClose().tabClose(1);		//close the tab
			driver.findElement(By.linkText("Update Policy")).click();	//click to update the policy
			Thread.sleep(2000);
			String value = driver.findElement(By.cssSelector("input[id='score']")).getAttribute("value");
			if(!value.equals("356"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Unable to edit the score per question field.");
			}
			
			driver.findElement(By.id("score")).click();	//click on score per question field
			driver.findElement(By.id("score")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("score")).sendKeys("1234");
			driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
			Thread.sleep(2000);
			new Click().clickByclassname("ls-save-policy-btn");		//click on Save Policy
			new TabClose().tabClose(1);		//close the tab
			driver.findElement(By.linkText("Update Policy")).click();	//click to update the policy
			Thread.sleep(2000);
			String value1 = driver.findElement(By.cssSelector("input[id='score']")).getAttribute("value");
			if(!value1.equals("123"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Able to add more than three digit in the score per question field.");
			}
			
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("score-icon")));	//click on help icon
			Thread.sleep(2000);
			String policyHelp = new TextFetch().textfetchbyclass("policy-help-data-container");
			if(!policyHelp.contains("Enter the points available for each question in the assignment. If you set points per question when creating a custom assignment, leave this box blank."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Help icon for Score per question the pop-up is not displayed.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase scorePerQuestionField in class DifferentFieldsOfAssigmentPolicy",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void orderingField()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("28");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
			List<WebElement> allFields = driver.findElements(By.className("ls-assignment-policy-question"));
			String orderingField = allFields.get(1).getText();
			if(!orderingField.contains("Ordering"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Ordering label is absent in New Policy tab.");
			}
			String ordringOptions = new TextFetch().textfetchbyclass("ls-assignment-policy-answer-options");
			if(!ordringOptions.contains("Random"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Randomized label is absent in Ordering field of New Policy tab.");
			}
			if(!ordringOptions.contains("Keep in order"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'Keep the assignment order' label is absent in Ordering field of New Policy tab.");
			}
			//list all the radio buttons
			List<WebElement> allRadioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
			String keepTheOrder = allRadioButtons.get(1).getAttribute("checked");		//Keep the assignment order radio button is at 1st index
			if(!keepTheOrder.equals("true"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'Keep the assignment order' radio button is not selected by default.");
			}
			//list all the radio options for Ordering
			List<WebElement> allOrdering = driver.findElements(By.xpath("//*[starts-with(@name, 'orderingupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOrdering.get(0));	//click on Randomized radio button
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOrdering.get(1));	//click on Keep the assignment order radio button
			Thread.sleep(2000);
			
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("ordering-icon")));	//click on help icon
			Thread.sleep(2000);
			String policyHelp = new TextFetch().textfetchbyclass("policy-help-data-container");
			if(!policyHelp.contains("Select random if you would like the order of questions to be random for each student."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Help icon for Ordering, the pop-up is not displayed.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase orderingField in class DifferentFieldsOfAssigmentPolicy",e);
		}
	}
	
	@Test(priority = 5, enabled = true)
	public void immediateFeedbackField()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("34");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
			List<WebElement> allFields = driver.findElements(By.className("ls-assignment-policy-question"));
			String immediateFeedback = allFields.get(2).getText();
			if(!immediateFeedback.contains("Immediate Feedback"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Immediate Feedback label is absent in New Policy tab.");
			}
			List<WebElement> allImmediateFeedbackOptions=driver.findElements(By.className("ls-assignment-policy-answer-options"));
			if(!allImmediateFeedbackOptions.get(1).getText().contains("Enable"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Enable option is absent in immediate Feedback Options of New Policy tab.");
			}
			if(!allImmediateFeedbackOptions.get(1).getText().contains("Disable"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Disable option is absent in immediate Feedback Options of New Policy tab.");
			}
			//list all the radio buttons
			List<WebElement> allRadioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
			Boolean diasble = allRadioButtons.get(2).isSelected();		//Disable radio button for Immediate feedback is at 3rd index
			if(diasble==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'Enable' radio button for Immediate feedback is not selected by default.");
			}
			//list all the radio options for Immediate Feedback
			List<WebElement> allImmediateFeedback = driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(0));	//click on Enable radio button
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(1));	//click on Disable radio button
			Thread.sleep(2000);
			
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("immediate-feedback-icon")));	//click on help icon
			Thread.sleep(2000);
			String policyHelp = new TextFetch().textfetchbyclass("policy-help-data-container");
			if(!policyHelp.contains("Enable immediate feedback if you want students to see if their answer was correct or incorrect after each attempt. If you disable immediate feedback, students will only have one attempt per question, and they will not see if their response was correct or incorrect until their grade has been released."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Help icon for immediate feedback, the help text pop-up is not displayed.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase immediateFeedbackField in class DifferentFieldsOfAssigmentPolicy",e);
		}
	}
	
	@Test(priority = 6, enabled = true)
	public void gradeReleaseOptionField()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("40");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
			List<WebElement> allFields = driver.findElements(By.className("ls-assignment-policy-question"));
			String gradeReleaseOption = allFields.get(3).getText();
			if(!gradeReleaseOption.contains("Grade Release options"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Grade Release options label is absent in New Policy tab.");
			}
			
			String allGradeReleaseOptions = new TextFetch().textfetchbyclass("ls-ls-assignment-policy-mode");
			if(!allGradeReleaseOptions.contains("Students see their grade when they complete their assignment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Grade Release options the 'Students see their grade when they complete their assignment' radio button is absent.");
			}
			if(!allGradeReleaseOptions.contains("Students see their grade at the time of the assignment due date"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Grade Release options the 'Students see their grade at the time of the assignment due date' radio button is absent.");
			}
			if(!allGradeReleaseOptions.contains("For assignments with manual grading, students see their grades after you have graded them"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Grade Release options the 'For assignments with manual grading, students see their grades after you have graded them' radio button is absent.");
			}
			if(!allGradeReleaseOptions.contains("Students see their grades when you decide to release the grades"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Grade Release options the 'Students see their grades when you decide to release the grades' radio button is absent.");
			}
			
			//list all the radio buttons
            List<WebElement> allImmediateFeedback = driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(1));	//click on Disable radio button
			List<WebElement> allRadioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
			Boolean diasble = allRadioButtons.get(7).isSelected();		//'Release explicitly by the instructor' radio button for Immediate feedback is at 7th index
			if(diasble==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'Students see their grades when you decide to release the grades' radio button for Grade Release Options is not selected by default when the Immediate feedback is disabled.");
			}
			
			//list all the radio options for Grade Release Option
			List<WebElement> allgradeReleaseOptions = driver.findElements(By.xpath("//*[starts-with(@name, 'gradeReleaseOptionsupdate-tabId')]"));
			//to verify we are able to select other radio buttons
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(0));	//click on Auto-release on assignment submission radio button
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(1));	//click on Auto-release on due date radio button
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(2));	//click on Release as they are being graded radio button
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(3));	//click on Release explicitly by the instructor radio button
			Thread.sleep(2000);
			
			//list all the radio options for Immediate Feedback
			List<WebElement> allImmediateFeedback1 = driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback1.get(0));	//click on Enable radio button
			Thread.sleep(2000);
			//list all the radio buttons
			List<WebElement> allRadioButtons1 = driver.findElements(By.cssSelector("input[type='radio']"));
            Boolean enable = allRadioButtons1.get(7).isSelected();		//'Release explicitly by the instructor' radio button for Immediate feedback is at 7th index
			if(enable==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'Students see their grade when they complete their assignment' radio button for Grade Release Options is not selected by default when the Immediate feedback is enabled.");
			}
			//to verify we are able to select other radio buttons
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(1));	//click on Auto-release on due date radio button
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(2));	//click on Release as they are being graded radio button
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(3));	//click on Release explicitly by the instructor radio button
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("grade-options-icon")));	//click on help icon
			Thread.sleep(2000);
			String policyHelp = new TextFetch().textfetchbyclass("policy-help-data-container");
			if(!policyHelp.contains("You can control when students will see their grade on an assignment."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Help icon for Grade Release option the pop-up is not displayed.");
			}
			
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(1));	//click on Disable radio button for immediate feedback
			Thread.sleep(2000);
            Boolean diasble1 = allRadioButtons.get(7).isSelected();		//'Release explicitly by the instructor' radio button for Immediate feedback is at 7th index
            if(diasble1==false)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("On changing the Immediate feedback option the Grade Release Configuration are getting altered.");
            }

			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase gradeReleaseOptionField in class DifferentFieldsOfAssigmentPolicy",e);
		}
	}
	
	@Test(priority = 7, enabled = true)
	public void noOfAttemptsField()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("49");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
			List<WebElement> allFields = driver.findElements(By.className("ls-assignment-policy-question"));
			String noOfAttempts = allFields.get(4).getText();
			if(!noOfAttempts.contains("Number of attempts"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Number of attempts label is absent in New Policy tab.");
			}
			List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
			if(!allElements.get(0).getText().equals("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("By default 1 is not selected in Number of Attempts.");
			}
			
			//list all the radio options for Immediate Feedback
			List<WebElement> allImmediateFeedback = driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(0));	//click on Enable radio button
			Thread.sleep(2000);
			new ComboBox().selectValue(0, "3");	//select a value from the dropdown 'Number of attempts' to check if we are able to select a value or not
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("attempts-icon")));	//click on help icon
			Thread.sleep(2000);
			String policyHelp = new TextFetch().textfetchbyclass("policy-help-data-container");
			if(!policyHelp.contains("Enter the number of attempts available for students on each question."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Help icon for number of question attempt the pop-up is not displayed.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase noOfAttemptsField in class DifferentFieldsOfAssigmentPolicy",e);
		}
	}
	
	@Test(priority = 8, enabled = true)
	public void showHintField()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("67");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
			List<WebElement> allFields = driver.findElements(By.className("ls-assignment-policy-question"));
			String showHintLabel = allFields.get(6).getText();
			if(!showHintLabel.contains("Show hints"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Show hints label is absent in New Policy tab.");
			}
			//list all the radio options for Immediate Feedback
			List<WebElement> allImmediateFeedback = driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(0));	//click on Enable radio button
			Thread.sleep(2000);
			
			/*//list all the radio buttons
			List<WebElement> allRadioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
			String isSelected = allRadioButtons.get(10).getAttribute("checked");		//'No' radio button for Show Hint is at 9th index
			if(!isSelected.equals("true"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'No' radio button for Show hint is not selected by default when the Immediate feedback is enabled.");
			}*/
			boolean atAttempNoIsDispalyed = driver.findElement(By.className("policy-at-attempt-number")).isDisplayed();
			if(atAttempNoIsDispalyed == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'At attempt number' is displayed when 'No' radio button is selected for Show Hint option.");
			}
			
			//list all the radio options for show hints
			List<WebElement> allshowHints = driver.findElements(By.xpath("//*[starts-with(@name, 'showHintsupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allshowHints.get(0));	//click on Yes radio button in Show hint
			Thread.sleep(2000);
			boolean atAttempNoIsDispalyed1 = driver.findElement(By.className("policy-at-attempt-number")).isDisplayed();
			if(atAttempNoIsDispalyed1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'At attempt number' is not displayed when 'Yes' radio button is selected for Show Hint option.");
			}
			String defaultValue = new TextFetch().textfetchbyid("show-content-link-policy-attempts-wrapper");
			if(!defaultValue.equals("1"))		
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("By default the value selected by the instructor in \"number of attempts\" doesnt appear selected in \"At Attempt Number\"");
			}
			
			new ComboBox().selectValue(0, "3");	//select a value 3 from the dropdown 'Number of attempts'
			String changedValue = new TextFetch().textfetchbyid("show-content-link-policy-attempts-wrapper");
			if(!changedValue.equals("3"))		// 3 has been selected from the dropdown 'Number of attempts'
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The value in \"At Attempt Number\" dropdown doesnt reset if we change the value of \"Number of attempts\".");
			}
			
			
			List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
			allElements.get(2).click();  //click on dropdown 'At Attempt Number'
			Thread.sleep(2000);
			List<WebElement> allOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'sbOptions_')]"));
			if(!allOptions.get(2).getText().contains("1") || !allOptions.get(2).getText().contains("2"))	//@ 2nd index 1, 2 option in "At attempt number" dropdown will be there as because Number of attempts has been set to 3
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The instructor unable to change it to a value LESS than or equal to the value in \"number of attempts\" field.");
			}
			driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
			Thread.sleep(3000);
			allElements.get(0).click();  //click on dropdown 'Number of attempts'
			Actions a = new Actions(driver);
			for(int i=0;i<15;i++)	{
                a.sendKeys(Keys.DOWN).perform();
            }
                a.sendKeys(Keys.RETURN).perform();

            //select a value 'Unlimited' from the dropdown 'Number of attempts'

			 String unlimitedValue = new TextFetch().textfetchbyid("show-content-link-policy-attempts-wrapper");
            System.out.println("unlimitedValue :"+unlimitedValue);
            if(!unlimitedValue.equals("15"))		// Unlimited has been selected from the dropdown 'Number of attempts'
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("If we select \"Unlimited\" in \"Number of attempts\" then the value at \"At attempt number\" doesnt reset to 15.");
			}
			
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("show-hints-policy-question-help-icon")));	//click on help icon
			Thread.sleep(2000);
			String policyHelp = new TextFetch().textfetchbyclass("policy-help-data-container");
			if(!policyHelp.contains("Some questions have a hint available. You can decide at which attempt to display hints to students."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Help icon for Show Hint the pop-up is not displayed.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(1));	//click on Disable radio button for Immediate feedback
			Thread.sleep(2000);
			String isDisabled = driver.findElement(By.cssSelector("input[class='showSolution']")).getAttribute("disabled");
			if(!isDisabled.equals("true"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("If we disable the Immediate feedback then the Show Hint field doesnt become disabled.");
			}
			//list all the radio buttons
			List<WebElement> allRadioButtons1 = driver.findElements(By.cssSelector("input[type='radio']"));
			String isSelected1 = allRadioButtons1.get(8).getAttribute("checked");		//'Yes' radio button for Show Solution is at 8th index
			if(!isSelected1.equals("true"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'Yes' radio button for Show solution is not selected by default when the Immediate feedback is disabled.");
			}
			
			String resetValue = new TextFetch().textfetchbyid("show-hints-policy-attempts-wrapper");
			if(!resetValue.equals("1"))		
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On disabling the immediate feedback the value doesnot reset to 1.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase showHintField in class DifferentFieldsOfAssigmentPolicy",e);
		}
	}
	
	@Test(priority = 9, enabled = true)
	public void showSolutionField()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("82");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
			List<WebElement> allFields = driver.findElements(By.className("ls-assignment-policy-question"));
			String showSolution = allFields.get(8).getText();
			if(!showSolution.contains("Show solution"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Show solution label is absent in New Policy tab.");
			}
			
			//list all the radio options for Immediate Feedback
			List<WebElement> allImmediateFeedback = driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(0));	//click on Enable radio button
			Thread.sleep(2000);
			//list all the radio buttons
			/*List<WebElement> allRadioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
			String isSelected = allRadioButtons.get(13).getAttribute("checked");		//'No' radio button for Show Solution is at 13th index
			if(!isSelected.equals("true"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'No' radio button for Show solution is not selected by default when the Immediate feedback is enabled.");
			}*/
			
			boolean atAttempNoIsDispalyed = driver.findElement(By.className("policy-after-attempt-number")).isDisplayed();
			if(atAttempNoIsDispalyed == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'After attempt number' is displayed when 'No' radio button is selected for Show Solution option.");
			}
			
			List<WebElement> allshowSolution = driver.findElements(By.xpath("//*[starts-with(@name, 'showSolutionupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allshowSolution.get(0));	//click on Yes radio button in Show solution
			Thread.sleep(2000);
			boolean atAttempNoIsDispalyed1 = driver.findElement(By.className("policy-after-attempt-number")).isDisplayed();
			if(atAttempNoIsDispalyed1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'After attempt number' is not displayed when 'Yes' radio button is selected for Show Solution option.");
			}
			
			String defaultValue = new TextFetch().textfetchbyid("show-solution-policy-attempts-wrapper");
			if(!defaultValue.equals("1"))		
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("By default the value selected by the instructor in \"number of attempts\" doesnt appear selected in \"After Attempt Number\"");
			}
			
			new ComboBox().selectValue(0, "3");	//select a value 3 from the dropdown 'Number of attempts'
			String changedValue = new TextFetch().textfetchbyid("show-solution-policy-attempts-wrapper");
			if(!changedValue.equals("3"))		// 3 has been selected from the dropdown 'Number of attempts'
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The value in \"After Attempt Number\" dropdown doesnt reset if we change the value of \"Number of attempts\".");
			}
			
			List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
			allElements.get(3).click();  //click on dropdown 'After Attempt Number'
			Thread.sleep(2000);
			List<WebElement> allOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'sbOptions_')]"));
			if(!allOptions.get(3).getText().contains("1") || !allOptions.get(3).getText().contains("2"))		//@ 3rd index 1, 2 option in "After attempt number" dropdown will be there as because Number of attempts has been set to 3
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The instructor unable to change the value of \"After attempt number\" to a value LESS than or equal to the value in \"number of attempts\" field.");
			}
			driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
			Thread.sleep(3000);
			allElements.get(0).click();  //click on dropdown 'Number of attempts'
			Actions a = new Actions(driver);
			for(int i=0;i<15;i++) {    //select a value 'Unlimited' from the dropdown 'Number of attempts'
                a.sendKeys(Keys.DOWN).perform();
            }
			a.sendKeys(Keys.RETURN).perform();
			Thread.sleep(3000);
			String unlimitedValue = new TextFetch().textfetchbyid("show-solution-policy-attempts-wrapper");
			if(!unlimitedValue.equals("15"))		// Unlimited has been selected from the dropdown 'Number of attempts'
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("If we select \"Unlimited\" in \"Number of attempts\" then the value at \"After attempt number\" doesnt reset to 15.");
			}
			
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("show-solution-icon")));	//click on help icon
			Thread.sleep(2000);
			String policyHelp = new TextFetch().textfetchbyclass("policy-help-data-container");
			if(!policyHelp.contains("Some questions have a full solution available. You can decide after which attempt a student can access the full solution."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Help icon for Show solution the pop-up is not displayed.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(1));	//click on Disable radio button for Immediate feedback
			Thread.sleep(2000);
			String isDisabled = driver.findElement(By.cssSelector("input[class='showHints']")).getAttribute("disabled");
			if(!isDisabled.equals("true"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("If we disable the Immediate feedback then the Show solution field doesnt become disabled.");
			}
			
		/*	//list all the radio buttons
			List<WebElement> allRadioButtons1 = driver.findElements(By.cssSelector("input[type='radio']"));
			String isSelected1 = allRadioButtons1.get(13).getAttribute("checked");		//'No' radio button for Show Solution is at 13th index
			if(!isSelected1.equals("true"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'No' radio button for Show solution is not selected by default when the Immediate feedback is disabled.");
			}*/
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase showSolutionField in class DifferentFieldsOfAssigmentPolicy",e);
		}
	}
	
	@Test(priority = 10, enabled = true)
	public void showReadingContentField()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("96");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
			List<WebElement> allFields = driver.findElements(By.className("ls-assignment-policy-question"));
			String showReadingContent = allFields.get(7).getText();
			if(!showReadingContent.contains("Show reading content Link"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Show reading content Link label is absent in New Policy tab.");
			}
		/*	//list all the radio buttons
			List<WebElement> allRadioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
			String isSelected = allRadioButtons.get(10).getAttribute("checked");		//'Yes' radio button for Show Reading Content @10th index
			if(!isSelected.equals("true"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("By default 'Yes' radio button for Show Reading Content link is not selected .");
			}
			*/
			//list all the radio options for Immediate Feedback
			List<WebElement> allImmediateFeedback = driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(0));	//click on Enable radio button
			Thread.sleep(2000);
			String defaultValue = new TextFetch().textfetchbyid("show-content-link-policy-attempts-wrapper");
			if(!defaultValue.equals("1"))		
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("By default the value in \"At Attempt Number\" for Show Reading Content Link is not 1.");
			}
			//list all the radio options for Show Reading Content
			List<WebElement> allShowReadingContent = driver.findElements(By.xpath("//*[starts-with(@name, 'showReadContentLinkupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allShowReadingContent.get(1));	//click on No radio button
			Thread.sleep(2000);
			List<WebElement> atAttempNoIsDispalyed = driver.findElements(By.className("policy-at-attempt-number"));
			if(atAttempNoIsDispalyed.get(1).isDisplayed() == true)	//"At Attempt Number" for Show Reading Content Link is at 1st index
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'At attempt number' is displayed when 'No' radio button is selected for Show Reading Content link option.");
			}
			
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allShowReadingContent.get(0));	//click on Yes radio button
			Thread.sleep(2000);
			List<WebElement> atAttempNoIsDispalyed1 = driver.findElements(By.className("policy-at-attempt-number"));
			if(atAttempNoIsDispalyed1.get(1).isDisplayed() == false)	//"At Attempt Number" for Show Reading Content Link is at 1st index
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'At attempt number' is not displayed when 'Yes' radio button is selected for Show Reading Content link option.");
			}
			String defaultValue1 = new TextFetch().textfetchbyid("show-content-link-policy-attempts-wrapper");
			if(!defaultValue1.equals("1"))		
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("By default the value in \"At Attempt Number\" for Show Reading Content Link is not 1 if Show reading content is set to 'Yes'.");
			}
			
			new ComboBox().selectValue(0, "3");	//select a value 3 from the dropdown 'Number of attempts'
			String changedValue = new TextFetch().textfetchbyid("show-content-link-policy-attempts-wrapper");
			if(!changedValue.equals("3"))		// 3 has been selected from the dropdown 'Number of attempts'
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The value in \"At Attempt Number\" dropdown doesnt reset if we change the value of \"Number of attempts\".");
			}
			List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
			allElements.get(2).click();  //click on dropdown 'At Attempt Number' for Show Reading Content Link
			Thread.sleep(2000);
			List<WebElement> allOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'sbOptions_')]"));
            if(!allOptions.get(2).getText().contains("1"))		//1, 2 option in "After attempt number" dropdown will be there as because Number of attempts has been set to 3
			{
                System.out.println("1");
                new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The instructor unable to change the value of \"At attempt number\" to a value LESS than or equal to the value in \"number of attempts\" field.");
			}
            if(!allOptions.get(2).getText().contains("2"))		//1, 2 option in "After attempt number" dropdown will be there as because Number of attempts has been set to 3
            {
                System.out.println("2");
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("The instructor unable to change the value of \"At attempt number\" to a value LESS than or equal to the value in \"number of attempts\" field.");
            }
			
			allElements.get(0).click();  //click on dropdown 'Number of attempts'
			Actions a = new Actions(driver);
			for(int i=0;i<15;i++) {        //select a value 'Unlimited' from the dropdown 'Number of attempts'
                a.sendKeys(Keys.DOWN).perform();
            }
			a.sendKeys(Keys.RETURN).perform();
			Thread.sleep(3000);
			String unlimitedValue = new TextFetch().textfetchbyid("show-content-link-policy-attempts-wrapper");
			if(!unlimitedValue.equals("15"))		// Unlimited has been selected from the dropdown 'Number of attempts'
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("If we select \"Unlimited\" in \"Number of attempts\" then the value at \"At attempt number\" for Show Reading Content Link doesnt reset to 15.");
			}
			
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("show-content-link-icon")));	//click on help icon
			Thread.sleep(2000);
			String policyHelp = new TextFetch().textfetchbyclass("policy-help-data-container");
			if(!policyHelp.contains("Every question has a link to the relevant section of the eTextbook. You can decide at which attempt to show students a link to the eTextbook so they can study."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Help icon for for Show Reading Content Link the pop-up is not displayed.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase showReadingContentField in class DifferentFieldsOfAssigmentPolicy",e);
		}
	}
	
	@Test(priority = 11, enabled = true)
	public void showAnswerField()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("110");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
			List<WebElement> allFields = driver.findElements(By.className("ls-assignment-policy-question"));
			String showAnswer = allFields.get(9).getText();
			if(!showAnswer.contains("Show answer"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Show answer label is absent in New Policy tab.");
			}
			//list all the radio options for Immediate Feedback
			List<WebElement> allImmediateFeedback = driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(0));	//click on Enable radio button
			Thread.sleep(2000);
			/*//list all the radio buttons
			List<WebElement> allRadioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
			String isSelected = allRadioButtons.get(15).getAttribute("checked");		//'No' radio button for Show Answer @15th index
			if(!isSelected.equals("true"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("By default 'No' radio button for Show Answer is not selected .");
			}*/
			
			List<WebElement> atAttempNoIsDispalyed = driver.findElements(By.className("policy-after-attempt-number"));
			if(atAttempNoIsDispalyed.get(1).isDisplayed() == true)	//"After Attempt Number" for Show Answer is at 1st index
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'After attempt number' is displayed when 'No' radio button is selected for Show Answer option.");
			}
			//list all the radio options for Show Answer
			List<WebElement> allShowAnswer = driver.findElements(By.xpath("//*[starts-with(@name, 'showAnswerupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allShowAnswer.get(0));	//click on Yes radio button
			Thread.sleep(2000);
			List<WebElement> atAttempNoIsDispalyed1 = driver.findElements(By.className("policy-after-attempt-number"));
			if(atAttempNoIsDispalyed1.get(1).isDisplayed() == false)	//"After Attempt Number" for Show Answer is at 1st index
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'At attempt number' is not displayed when 'Yes' radio button is selected for Show Answer option.");
			}
			String defaultValue = new TextFetch().textfetchbyid("show-answer-policy-attempts-wrapper");
			if(!defaultValue.equals("1"))		
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("By default the value in \"At Attempt Number\" for Show Answer is not 1.");
			}
			new ComboBox().selectValue(0, "3");	//select a value 3 from the dropdown 'Number of attempts'
			String changedValue = new TextFetch().textfetchbyid("show-answer-policy-attempts-wrapper");
			if(!changedValue.equals("3"))		// 3 has been selected from the dropdown 'Number of attempts'
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The value in \"At Attempt Number\" dropdown for Show Answer doesnt reset if we change the value of \"Number of attempts\".");
			}
			List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
			allElements.get(4).click();  //click on dropdown 'At Attempt Number' for Show Answer
			Thread.sleep(2000);
			List<WebElement> allOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'sbOptions_')]"));
			if(!allOptions.get(4).getText().contains("1") || !allOptions.get(4).getText().contains("2"))		//1, 2 option in "After attempt number" dropdown will be there as because Number of attempts has been set to 3
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The instructor unable to change the value of \"At attempt number\" for Show Answer to a value LESS than or equal to the value in \"number of attempts\" field.");
			}
			
			allElements.get(0).click();  //click on dropdown 'Number of attempts'
			Actions a = new Actions(driver);
			for(int i=0;i<15;i++) {
                //select a value 'Unlimited' from the dropdown 'Number of attempts'
                a.sendKeys(Keys.DOWN).perform();
            }
		    a.sendKeys(Keys.RETURN).perform();
			Thread.sleep(3000);
			String unlimitedValue = new TextFetch().textfetchbyid("show-answer-policy-attempts-wrapper");
			if(!unlimitedValue.equals("15"))		// Unlimited has been selected from the dropdown 'Number of attempts'
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("If we select \"Unlimited\" in \"Number of attempts\" then the value at \"At attempt number\" for Show Answer doesnt reset to 15.");
			}
			
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("show-answer-icon")));	//click on help icon
			Thread.sleep(2000);
			String policyHelp = new TextFetch().textfetchbyclass("policy-help-data-container");
			if(!policyHelp.contains("You can decide after which attempt a student should see the correct answer to a question."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Help icon for for Show Answer the pop-up is not displayed.");
			}
			
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(1));	//click on Disable radio button for Immediate feedback
			Thread.sleep(2000);
			String isDisabled = driver.findElement(By.cssSelector("input[class='showAnswer']")).getAttribute("disabled");
			if(!isDisabled.equals("true"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("If we disable the Immediate feedback then the Show Answer field doesnt become disabled.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase showAnswerField in class DifferentFieldsOfAssigmentPolicy",e);
		}
	}
	
	@Test(priority = 12, enabled = false)  //extend due date field is now removed from UI so disabling the test-case
	public void extendDueDateField()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("125");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
			List<WebElement> allFields = driver.findElements(By.className("ls-assignment-policy-question"));
			String extendDueDate = allFields.get(10).getText();
			if(!extendDueDate.contains("Extend due date for individual students"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Extend due date for individual students field is absent in New Policy tab.");
			}
			
			//list all the radio options for Extend Due date
			List<WebElement> allImmediateFeedback = driver.findElements(By.xpath("//*[starts-with(@name, 'extendDueDateForStudentupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(0));	//click on Enable radio button
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(1));	//click on Disable radio button
			Thread.sleep(2000);
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase extendDueDateField in class DifferentFieldsOfAssigmentPolicy",e);
		}
	}
	
	@Test(priority = 13, enabled = false)  //extend due time field is now removed from UI so disabling the test-case
	public void extendDueTimeField()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("129");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
			List<WebElement> allFields = driver.findElements(By.className("ls-assignment-policy-question"));
			String extendDueTime = allFields.get(11).getText();
			if(!extendDueTime.contains("Extend time limit for individual students"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Extend time limit for individual students field is absent in New Policy tab.");
			}
			
			//list all the radio options for Extend due time for individual students
			List<WebElement> allExtendDueTime = driver.findElements(By.xpath("//*[starts-with(@name, 'extendTimeLimitForStudentupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allExtendDueTime.get(0));	//click on Enable radio button
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allExtendDueTime.get(1));	//click on Disable radio button
			Thread.sleep(2000);
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase extendDueTimeField in class DifferentFieldsOfAssigmentPolicy.",e);
		}
	}

}
