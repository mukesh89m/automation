package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;

public class ClickOnquestionCard
{
	public void clickonquestioncard(int questioncardindex)
	{
		try
		{
			List<WebElement> allelement=Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'question_card_id_')]"));
			questioncardindex=1;
			   for(WebElement elements:allelement)
			   {
				   if(questioncardindex==1)
				   {
					   boolean questionno=Driver.driver.findElement(By.className("question-card-question-no")).isDisplayed();//question number check in card					   
					   if(questionno==false)
						   Assert.fail("Question number not shown");
					   boolean questionpoint=Driver.driver.findElement(By.className("question-card-content")).isDisplayed();//question point check in card
					   if(questionpoint==false)
						   Assert.fail("Question point not shown");
					   boolean questiontimetaken=Driver.driver.findElement(By.className("question-card-time-taken")).isDisplayed();//question time taken check in card
					   if(questiontimetaken==false)
						   Assert.fail("Question time taken not shown");					   
					   elements.click();
					   Thread.sleep(3000);
					   break;
				   }
				   questioncardindex++;
			   }
			  
		}
		catch(Exception e)
		{
			Assert.fail("Exception in appHelper clickonquestioncard in class  ClickOnquestionCard",e);
		}
	}


    public void clickonquestioncard(int questioncardPosition,int dataIndex)
    {
        try
        {
            List<WebElement> allelement=Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'question_card_id_')]"));
            new Click().clickByElement(allelement.get(questioncardPosition-1));
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in appHelper clickonquestioncard in class  ClickOnquestionCard",e);
        }
    }

}
