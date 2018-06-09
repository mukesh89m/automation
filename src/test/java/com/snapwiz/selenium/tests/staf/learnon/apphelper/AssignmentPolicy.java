package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class AssignmentPolicy {

	
	public void createAssignmentPolicy(String policyName, String policyDescription, String scorePerQuestion, String Ordering, boolean immediateFeedBack, String numberOfAttempt, String showAnswerAtAttemptNumber, String gradeReleaseOption, String showHintsAtAttemptNumber, String showReadingContentLinkAtAttemptNumber, String showSolutionAtAttemptNumber)
	{
		try
		{
			new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
			new MouseHover();
			MouseHover.mouserhoverbyid("ls-ins-assignment-policy-name");//mouse hover the name field
			Driver.driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon")).click();	//click on edit icon
		    Thread.sleep(2000);
		    Driver.driver.switchTo().activeElement().sendKeys(policyName);
		    MouseHover.mouserhoverbyid("ls-ins-assignment-policy-desc");//mouse hover the description field
			List<WebElement> allEditIcon = Driver.driver.findElements(By.cssSelector("i[class='ls-policy-ls-assignment-image ls-ins-edit-assignment-policy-icon']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allEditIcon.get(1));	//click on edit icon to edit description
			Thread.sleep(2000);
			Driver.driver.switchTo().activeElement().sendKeys(policyDescription);
		    Driver.driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
		    Thread.sleep(2000);
		    Driver.driver.findElement(By.id("score")).click();		//click on score per question field
			Driver.driver.findElement(By.id("score")).clear();
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("score")).sendKeys(scorePerQuestion);
			Driver.driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
			Thread.sleep(2000);
		    
		    if(immediateFeedBack == true)
		    {
		    	//list all the radio options for Immediate Feedback
				List<WebElement> allImmediateFeedback = Driver.driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allImmediateFeedback.get(0));	//click on Enable radio button
				Thread.sleep(2000);
		    }
		    if(Ordering != null)
		    {
		    	//list all the radio options for Ordering
				List<WebElement> allOrdering = Driver.driver.findElements(By.xpath("//*[starts-with(@name, 'orderingupdate-tabId')]"));
		    	if(Ordering.equals("Randomized"))
		    	{
		    		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allOrdering.get(0));	//click on Randomized radio button
					Thread.sleep(2000);
		    	}
		    	if(Ordering.equals("Keep the assignment order"))
		    	{
		    		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allOrdering.get(1));	//click on Keep the assignment order radio button
					Thread.sleep(2000);
		    	}
		    }
		    if(gradeReleaseOption != null)
		    {
		    	//list all the radio options for Grade Release Option
				List<WebElement> allgradeReleaseOptions = Driver.driver.findElements(By.xpath("//*[starts-with(@name, 'gradeReleaseOptionsupdate-tabId')]"));
				if(gradeReleaseOption.equals("Auto-release on assignment submission"))
				{
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(0));	//click on Auto-release on assignment submission radio button
					Thread.sleep(2000);
				}
				if(gradeReleaseOption.equals("Auto-release on due date"))
				{
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(1));	//click on Auto-release on due date radio button
					Thread.sleep(2000);
				}
				if(gradeReleaseOption.equals("Release as they are being graded"))
				{
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(2));	//click on Release as they are being graded radio button
					Thread.sleep(2000);
				}
				if(gradeReleaseOption.equals("Release explicitly by the instructor"))
				{
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(3));	//click on Auto-release on assignment submission radio button
					Thread.sleep(2000);
				}
		    }
		    if(!numberOfAttempt.equals("1"))
		    {
		    	new ComboBox().selectValue(1, numberOfAttempt);	//select a value from the dropdown 'Number of attempts'
		    }
		    
		    if(!showHintsAtAttemptNumber.equals(""))
		    {
		    			//list all the radio options for show hints
		    			List<WebElement> allshowHints = Driver.driver.findElements(By.xpath("//*[starts-with(@name, 'showHintsupdate-tabId')]"));
		    			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allshowHints.get(0));	//click on Yes radio button in Show hint
		    			Thread.sleep(2000);
		    			List<WebElement> allElements = Driver.driver.findElements(By.className("sbSelector"));
		    			if(!allElements.get(1).getText().equals(showHintsAtAttemptNumber))
		    			{
		    				new ComboBox().selectValue(2, showHintsAtAttemptNumber);	//click on dropdown 'At Attempt Number' for Show Hint and select a value
		    			}
		    		
		    }
		    if(!showReadingContentLinkAtAttemptNumber.equals(""))
		    {
		    			//list all the radio options for show hints
		    			List<WebElement> allShowReadingContent = Driver.driver.findElements(By.xpath("//*[starts-with(@name, 'showReadContentLinkupdate-tabId')]"));
		    			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allShowReadingContent.get(0));	//click on Yes radio button in Show Reading Content
		    			Thread.sleep(2000);
		    			List<WebElement> allElements = Driver.driver.findElements(By.className("sbSelector"));
		    			if(allElements.get(2).getText().equals(showReadingContentLinkAtAttemptNumber))
		    			{
		    				List<WebElement> allElements1 = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
		    				Thread.sleep(2000);
		    				allElements1.get(3).click();  
		    				Thread.sleep(2000);
		    				List<WebElement> allOptions = Driver.driver.findElements(By.linkText(showReadingContentLinkAtAttemptNumber));
		    				allOptions.get(1).click();
		    				Thread.sleep(2000);
		    			}
		    			else
		    			{
		    				new ComboBox().selectValue(3, showReadingContentLinkAtAttemptNumber);
		    			}
		    			
		    }
		    if(!showSolutionAtAttemptNumber.equals(""))
		    {
		    	
		    			//list all the radio options for show hints
		    			List<WebElement> allshowSolution = Driver.driver.findElements(By.xpath("//*[starts-with(@name, 'showSolutionupdate-tabId')]"));
		    			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allshowSolution.get(0));	//click on Yes radio button in Show solution
		    			Thread.sleep(2000);
		    			List<WebElement> allElements = Driver.driver.findElements(By.className("sbSelector"));
		    			if(allElements.get(3).getText().equals(showSolutionAtAttemptNumber))
		    			{
		    				List<WebElement> allElements1 = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
		    				Thread.sleep(2000);
		    				allElements1.get(4).click();  
		    				Thread.sleep(2000);
		    				List<WebElement> allOptions = Driver.driver.findElements(By.linkText(showSolutionAtAttemptNumber));
		    				allOptions.get(2).click();
		    				Thread.sleep(2000);
		    			}
		    			else{
		    				new ComboBox().selectValue(4, showSolutionAtAttemptNumber);
		    			}
		    		
		    }
		    if(!showAnswerAtAttemptNumber.equals(""))
		    {
		    	
		    		//list all the radio options for Show Answer
		    		List<WebElement> allShowAnswer = Driver.driver.findElements(By.xpath("//*[starts-with(@name, 'showAnswerupdate-tabId')]"));
		    		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allShowAnswer.get(0));	//click on Yes radio button
		    		Thread.sleep(2000);
		    		List<WebElement> allElements = Driver.driver.findElements(By.className("sbSelector"));
	    			if(allElements.get(4).getText().equals(showSolutionAtAttemptNumber))
	    			{
	    				List<WebElement> allElements1 = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
	    				Thread.sleep(2000);
	    				allElements1.get(5).click();  
	    				Thread.sleep(2000);
	    				List<WebElement> allOptions = Driver.driver.findElements(By.linkText(showAnswerAtAttemptNumber));
	    				allOptions.get(1).click();
	    				Thread.sleep(2000);
	    			}
	    			else
	    			{
	    				new ComboBox().selectValue(5, showAnswerAtAttemptNumber);
	    			}
		    	
		    }
            Driver.driver.findElement(By.xpath("//*[starts-with(@name,'allowCollaborationupdate-tabId')]")).click();
		    new Click().clickByclassname("ls-save-policy-btn");		//click on Save Policy
		    Thread.sleep(2000);
		    String notification = new TextFetch().textfetchbyclass("policy-notification-text-span");
		    if(!notification.contains("Saved New Assignment Policy Successfully."))
		    {
		    	new com.snapwiz.selenium.tests.staf.learnon.apphelper.Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Error in creating Assignment Policy.");
		    }

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in apphelper createAssignmentPolicy in class AssignmentPolicy",e);
		}
	}
	//open assignment plocy form instructor resources page
	public void openAssignmentPolicy(String assignemntname)
	{
		try
		{
			new Navigator().NavigateTo("Question Banks");
			Thread.sleep(2000);
			new TextSend().textsendbyid("\""+assignemntname+"\"", "all-resource-search-textarea");
			new Click().clickByid("all-resource-search-button");
			new Click().clickByclassname("assign-this");
			new Click().clickByclassname("ir-ls-assign-dialog-gradable-label-check");
			new Click().clickByid("assignment-policy-icons");
		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper openAssignmentPolicy in class AssignmentPolicy",e);
		}
	}

    //Author Sumit on 14/8/2014
    //It will update the Allow collaboration
    public void updateAllowCollaboration(String dataIndex, boolean allowCollaboration)
    {
       try
       {
            String policyname = ReadTestData.readDataByTagName("", "policyname", dataIndex);
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            int index = 0;
            List<WebElement> allPolicy = Driver.driver.findElements(By.className("assignment-policy-heading"));
            for(WebElement l: allPolicy)
            {
                if(l.getText().contains(policyname))
                {
                    break;
                }
                index++;
            }
           List<WebElement> allUpdateLink = Driver.driver.findElements(By.className("update-policy"));
           allUpdateLink.get(index).click();//click on update link
           List<WebElement> allowCollaborationRadioButton = Driver.driver.findElements(By.xpath("//*[starts-with(@name, 'allowCollaborationupdate-tabId')]"));
           if(allowCollaboration == true)
        	   ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allowCollaborationRadioButton.get(0));
                //allowCollaborationRadioButton.get(0).click();
           if(allowCollaboration == false)
        	   ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allowCollaborationRadioButton.get(1));
               //allowCollaborationRadioButton.get(1).click();
           new Click().clickByclassname("ls-save-policy-btn");		//click on Save Policy
           Thread.sleep(2000);
       }
       catch (Exception e)
       {
           Assert.fail("Exception in apphelper updateAllowCollaboration in class AssignmentPolicy",e);
       }
    }
}
