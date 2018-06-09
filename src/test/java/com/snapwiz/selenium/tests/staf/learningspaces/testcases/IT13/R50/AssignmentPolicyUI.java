package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R50;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class AssignmentPolicyUI extends  Driver {
	
	@Test(priority = 1, enabled = true)
	public void assignmentPolicyUI()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("4");
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			String url = driver.getCurrentUrl();
			if(!url.contains("assignmentPolicy"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking 'Assignment Policies' from navigator it doesnt navigate to assignment policy page.");
			}
			String newAssignmentPolicy= new TextFetch().textfetchbyid("newAssignmentPolicy-link");
			if(!newAssignmentPolicy.contains("+ New Assignment Policy"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("  '+ New Assignment Policy' link is not available at top right corner");
			}
			new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
			String newTab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!newTab.equals("New Policy"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Clicking on + New Assignment Policy link it doesnt open a new tab with name 'New Policy'.");
			}
			
			String nameField = new TextFetch().textfetchbyid("ls-ins-assignment-policy-name");
			if(!nameField.contains("Click to enter a name for this policy..."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Name Field is absent in New Policy tab.");
			}
			String descField = new TextFetch().textfetchbyid("ls-ins-assignment-policy-desc");
			if(!descField.contains("Click to enter a description for this policy..."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Description Field is absent in New Policy tab.");
			}
			String savePolicyButton = new TextFetch().textfetchbyclass("ls-save-policy-btn");
			if(!savePolicyButton.contains("Save Policy"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Save Policy button is absent in New Policy tab.");
			}
			
			String scorePerQuestion = driver.findElement(By.cssSelector("div[class='ls-assignment-policy-question ls-assignment-policy-question-first']")).getText();
			if(!scorePerQuestion.contains("Points per question"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Points per question field is absent in New Policy tab.");
			}
			
			List<WebElement> allFields = driver.findElements(By.className("ls-assignment-policy-question"));
			String orderingField = allFields.get(1).getText();
			if(!orderingField.contains("Ordering"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Ordering field is absent in New Policy tab.");
			}
			//list all the radio options for Ordering
			List<WebElement> allOrdering = driver.findElements(By.xpath("//*[starts-with(@name, 'orderingupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOrdering.get(0));	//click on Randomized radio button
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOrdering.get(1));	//click on Keep the assignment order radio button
			Thread.sleep(2000);
			
			String immediateFeedback = allFields.get(2).getText();
			if(!immediateFeedback.contains("Immediate Feedback"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Immediate Feedback field is absent in New Policy tab.");
			}
			
			//list all the radio options for Immediate Feedback
			List<WebElement> allImmediateFeedback = driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(0));	//click on Enable radio button
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(1));	//click on Disable radio button
			Thread.sleep(2000);
			
			String gradeReleaseOption = allFields.get(3).getText();
			if(!gradeReleaseOption.contains("Grade Release options"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Grade Release options field is absent in New Policy tab.");
			}
			
			//list all the radio options for Grade Release Option
			List<WebElement> allgradeReleaseOptions = driver.findElements(By.xpath("//*[starts-with(@name, 'gradeReleaseOptionsupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(0));	//click on Auto-release on assignment submission radio button
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(1));	//click on Auto-release on due date radio button
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(2));	//click on Release as they are being graded radio button
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(3));	//click on Release explicitly by the instructor radio button
			Thread.sleep(2000);
			
			String policyConfigurationLabel = new TextFetch().textfetchbyclass("policy-config-question-text");
			if(!policyConfigurationLabel.contains("Policy configuration for each question attempt"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'Policy configuration for each question attempt' label is absent in New Policy tab.");
			}
			
			String noOfAttempts = allFields.get(4).getText();
			if(!noOfAttempts.contains("Number of attempts"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Number of attempts field is absent in New Policy tab.");
			}
			
			String showHintLabel = allFields.get(6).getText();
			if(!showHintLabel.contains("Show hints"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Show hints field is absent in New Policy tab.");
			}
			
			String showReadingContent = allFields.get(7).getText();
			if(!showReadingContent.contains("Show reading content Link"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Show reading content Link field is absent in New Policy tab.");
			}
			
			String showSolution = allFields.get(8).getText();
			if(!showSolution.contains("Show solution"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Show solution field is absent in New Policy tab.");
			}
			
			String showAnswer = allFields.get(9).getText();
			if(!showAnswer.contains("Show answer"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Show answer field is absent in New Policy tab.");
			}
			
			String otherPolicyConfigurationLabel = driver.findElement(By.cssSelector("div[class='ls-policy-config-questions-wrapper other-policy-wrapper']")).getText();
			if(!otherPolicyConfigurationLabel.contains("Other Policy configuration"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("'Other Policy configuration' label is absent in New Policy tab.");
			}
			//Options to extendDuedate and extendTimelimit is being removed from assignment policy UI
			/*String extendDueDate = allFields.get(10).getText();
			if(!extendDueDate.contains("Extend due date for individual students"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Extend due date for individual students field is absent in New Policy tab.");
			}
			
			//list all the radio options for Extend due date for individual students
			List<WebElement> allExtendDueDate = driver.findElements(By.xpath("/*//*[starts-with(@name, 'extendDueDateForStudentupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allExtendDueDate.get(0));	//click on Enable radio button
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allExtendDueDate.get(1));	//click on Disable radio button
			Thread.sleep(2000);
			
			String extendDueTime = allFields.get(11).getText();
			if(!extendDueTime.contains("Extend time limit for individual students"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Extend time limit for individual students field is absent in New Policy tab.");
			}
			
			//list all the radio options for Extend due time for individual students
			List<WebElement> allExtendDueTime = driver.findElements(By.xpath("/*//*[starts-with(@name, 'extendTimeLimitForStudentupdate-tabId')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allExtendDueTime.get(0));	//click on Enable radio button
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", allExtendDueTime.get(1));	//click on Disable radio button*/
			Thread.sleep(2000);
			new TabClose().tabClose(1);	//close new policy tab
			String closeTab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!closeTab.equals("Assignment Policies"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Unable to close the New Policy tab.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase assignmentPolicyUI in class AssignmentPolicyUI",e);
		}
	}


}
