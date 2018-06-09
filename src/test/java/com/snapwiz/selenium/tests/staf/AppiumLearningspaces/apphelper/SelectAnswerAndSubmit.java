package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;

public class SelectAnswerAndSubmit {

	public void daigonestianswersubmit(String answeroption)
	{
		try
		{

            if(Driver.driver.findElements(By.className("preview-single-selection-answer-choices")).size() > 0)
            {
                List<WebElement> answer = Driver.driver.findElements(By.className("choice-value"));
                for (WebElement answerchoice: answer)
                {
                    if(answerchoice.getText().trim().equals(answeroption))
                    {
                        answerchoice.click();
                        break;
                    }
                }
            }

            else if(Driver.driver.findElements(By.className("true-false-answer-choices")).size() > 0)
            {
                List<WebElement> answer = Driver.driver.findElements(By.className("true-false-student-answer-label"));
                for (WebElement answerchoice: answer)
                {
                    if(answerchoice.getText().trim().equals(answeroption))
                    {
                        answerchoice.click();
                        break;
                    }
                }
            }

            else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                Driver.driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys(answeroption);

            else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-dropdown-wrapper visible_redactor_input_wrapper']")).size() > 0)
                new Select(Driver.driver.findElement(By.className("question-raw-content-dropdown"))).selectByVisibleText(answeroption);

	    	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button")));
		    Thread.sleep(2000);
			int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
			if(noticesize==1)
			
			{
				String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
				if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
				{
					Driver.driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
					
				}
				
				
			}
			
		}
		catch(Exception e)
		{
			
			Assert.fail("Exception in App helper SelectAnswerAndSubmit",e);
		}
	}
	public void Adaptiveasnswersubmit(String answeroption)
	{
		try
		{
			List<WebElement> answer = Driver.driver.findElements(By.className("true-false-student-answer-label"));
			for (WebElement answerchoice: answer)
			{
				if(answerchoice.getText().trim().equals(answeroption))
				{
					answerchoice.click();
					break;
				}
			}
			
		
			//((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElements(By.className("qtn-label")));
			Thread.sleep(3000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='button']"))); //submit
			Thread.sleep(3000);
			int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();			
			if(noticesize==1)
			
			{
				System.out.println("notice found");
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[id='submit-practice-question-button']")));
				Thread.sleep(3000);
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[id='next-practice-question-button']")));
				Thread.sleep(3000);
			}
			else
			{
				System.out.println("notice not found");
				Thread.sleep(3000);
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[id='next-practice-question-button']")));
				Thread.sleep(3000);
			}
			
		}
		catch(Exception e)
		{
			
			Assert.fail("Exception in App helper SelectAnswerAndSubmit",e);
		}
		
	}
	
	public void staticanswersubmit(String answeroption)
	{
		try
		{
			List<WebElement> answer = Driver.driver.findElements(By.className("choice-value"));
			for (WebElement answerchoice: answer)
			{
				
				if(answerchoice.getText().trim().equals(answeroption))
				{
					
					answerchoice.click();
					break;
				}
			}
			Thread.sleep(2000);
            Driver.driver.findElement(By.xpath("//div[@class='ls-static-practice-test-submit-button']/input")).click();
			Thread.sleep(2000);
            Driver.driver.findElement(By.xpath("//div[@class='ls-static-practice-test-next-button']/input")).click();

            Thread.sleep(2000);
			
		}
		catch(Exception e)
		{
			
			Assert.fail("Exception in App helper SelectAnswerAndSubmit",e);
		}
		
	}

	public void staticAssignment(String answeroption)
	{
		try
		{
            (new WebDriverWait(Driver.driver, 200))
                    .until(ExpectedConditions.elementToBeClickable(By.className("true-false-student-answer-label")));
            //((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("true-false-student-answer-select")));//select answer option A
            List<WebElement> answer = Driver.driver.findElements(By.className("true-false-student-answer-label"));
            for (WebElement answerchoice: answer)
			{
                if(answerchoice.getText().trim().equals(answeroption))
				{
                    answerchoice.click();
                    try {
                        Driver.driver.findElement(By.cssSelector("div[class='true-false-student-answer-select true-false-student-answer-clicked']"));
                    }
                    catch (Exception e) {
                        Thread.sleep(20000);
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                    }
                    break;
				}
			}
            if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).size() > 0) //next
			    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")));

            else if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).size() > 0) //next
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")));

            else if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).size() > 0) //Finish Assignment
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")));

            else
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']"))); //submit assignment
			Thread.sleep(2000);
		}

        catch(Exception e)
		{
            try {
                (new WebDriverWait(Driver.driver, 20))
                        .until(ExpectedConditions.elementToBeClickable(By.className("true-false-student-answer-label")));
                //((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("true-false-student-answer-select")));//select answer option A
                List<WebElement> answer = Driver.driver.findElements(By.className("true-false-student-answer-label"));
                for (WebElement answerchoice: answer)
                {
                    if(answerchoice.getText().trim().equals(answeroption))
                    {
                        System.out.println("inside If:");
                        answerchoice.click();
                        break;
                    }
                }
                if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).size() > 0) //next
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")));

                else if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).size() > 0) //next
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")));

                else if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).size() > 0) //Finish Assignment
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")));

                else
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']"))); //submit assignment
                Thread.sleep(2000);
            } catch (Exception ee) {
                Assert.fail("Exception in App helper staticAssignment",ee);
            }

		}
	}
	
	public void staticAssignmentWithShowAnswer(String answeroption)
	{
		try
		{
            (new WebDriverWait(Driver.driver, 200))
                .until(ExpectedConditions.elementToBeClickable(By.className("true-false-student-answer-label")));
			List<WebElement> answer = Driver.driver.findElements(By.className("true-false-student-answer-label"));
			for (WebElement answerchoice: answer)
			{
				
				if(answerchoice.getText().trim().equals(answeroption))
				{
					
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                    try {
                        Driver.driver.findElement(By.cssSelector("div[class='true-false-student-answer-select true-false-student-answer-clicked']"));
                    }
                    catch (Exception e) {
                        Thread.sleep(20000);
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                    }
					//answerchoice.click();
					break;
				}
			}
            if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).size() > 0) //next
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")));

            else if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).size() > 0) //next
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")));

            else if(Driver.driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).size() > 0) //Finish Assignment
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")));

            else
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']"))); //submit assignment
            Thread.sleep(2000);
			//((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")));
			//Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).click();//click on submit button			
			Thread.sleep(2000);
			
		}
		catch(Exception e)
		{

			Assert.fail("Exception in App helper staticAssignment",e);
		}
	}
    public void staticAnswerSubmitWithConfidenceLevel(String answeroption,int confidenceLevelIndex)
    {
        String classAttribute=null;
        try
        {
            WebElement confidencelevel = (new WebDriverWait(Driver.driver, 5))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(Integer.toString(confidenceLevelIndex))));
            if(confidencelevel != null)
            {
                if(confidenceLevelIndex==1)
                classAttribute="confidence-level-guess";
                if(confidenceLevelIndex==2)
                    classAttribute="confidence-level-somewhat";
                if(confidenceLevelIndex==3)
                    classAttribute="confidence-level-almost";
                if(confidenceLevelIndex==4)
                    classAttribute="confidence-level-i-know-it";


                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevelIndex+"' and @class='"+classAttribute+"']")));
            }
            List<WebElement> answer = Driver.driver.findElements(By.className("true-false-student-answer-label"));
            for (WebElement answerchoice: answer)
            {

                if(answerchoice.getText().trim().equals(answeroption))
                {

                    answerchoice.click();
                    break;
                }
            }
            //	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div/div/label")));
            Thread.sleep(2000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type=\"button\"]")));
            Thread.sleep(2000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type=\"button\"]")));
            Thread.sleep(2000);

        }
        catch(Exception e)
        {

            Assert.fail("Exception in App helper staticAnswerSubmitWithConfidenceLevel",e);
        }

    }
	
}
