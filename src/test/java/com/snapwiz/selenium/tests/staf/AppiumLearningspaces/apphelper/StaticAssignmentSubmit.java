package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;

public class StaticAssignmentSubmit 
{
	public void staticAssignmentSubmit(int index)
	{
		try
		{
			new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator().NavigateTo("Assignments");
			Thread.sleep(2000);
			new Click().clickbylistcssselector("span[class='ls-assignment-name instructor-assessment-review']", index);
            new UIElement().waitAndFindElement(By.className("true-false-student-answer-select"));
            if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).size() > 0)
			    new Click().clickBycssselector("a[class='btn btn--primary btn--large btn--submit']");
            else if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).size() > 0)
                new Click().clickBycssselector("a[class='btn btn--primary btn--large btn--submit long-text-button']");
            Thread.sleep(5000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in appp helper staticassignment",e);
		}
	}
	public void clickonassignment(int index)
	{
		try 
		{
			new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator().NavigateTo("Assignments");
			new Click().clickbylistcssselector("span[class='ls-assignment-name instructor-assessment-review']", index);
			new WebDriverWait(Driver.driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));

			
		}
		catch (Exception e) 
		{
			Assert.fail("Exception in appp helper clickonassignment",e);
		}
	}
	//attempt static question as assignment
	public void staticAssignement()
	{

        new UIElement().waitAndFindElement(By.className("question-label"));
		int testend=Driver.driver.findElements(By.id("timer-label")).size();
		
		
		while(testend>=1)
		{
			try
			{
			Thread.sleep(3000);
			testend=Driver.driver.findElements(By.id("timer-label")).size();
			
			if(testend == 0){break;}
			Thread.sleep(2000);
			int submitbutton=Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).size();
			if(submitbutton==1)
			{
				Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).click();
				break;
			}
			new SelectAnswerAndSubmit().staticAssignment("A");
			
			}
			catch(Exception e)
			{
				Assert.fail("Question or element not found",e);
			}
			
					
		}
	}
	//submit static assessment
		public void staticAssesment()
		{
			int testend=Driver.driver.findElements(By.id("timer-label")).size();
			while(testend>=1)
			{
			 try
			  {
				Thread.sleep(3000);
				testend=Driver.driver.findElements(By.id("timer-label")).size();
				
				if(testend == 0){
					break;
					}
				Thread.sleep(2000);
				int submitbutton=Driver.driver.findElements(By.cssSelector("div[class='ls-static-practice-test-submit-button']")).size();
				if(submitbutton==1)
				{
					Driver.driver.findElement(By.cssSelector("div[class='ls-static-practice-test-submit-button']")).click();//click on submit button	
					Thread.sleep(3000);
				}
				List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
				for (WebElement answerchoice: answer)
				{
					
					if(answerchoice.getText().trim().equals("A"))
					{
						answerchoice.click();
						break;
					}
				}
				Driver.driver.findElement(By.cssSelector("div[class='ls-static-practice-test-next-button']")).click();	//click on next button			
				Thread.sleep(3000);
			 }
			catch(Exception e)
			{
				Assert.fail("Question or element not found",e);
			}
				
						
			}
		}

    //Author Sumit
    //navigate to question from question dropdown of static assignment
    public void navigateFromQuestionDropdown(String questionNo)
    {
        try
        {
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("current-question-index")));//click on question dropdown
            Thread.sleep(1000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("tr[qindex='"+questionNo+"']")));//click on a particular question from dropdown
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper method navigateFromQuestionDropdown of class StaticAssignmentSubmit.",e);
        }
    }

}
