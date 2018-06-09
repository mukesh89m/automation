package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;

public class CreateOwnQuizByStudent 
{
	public void createOwnQuizByStudent()
	{
		try
		{
			new Navigator().NavigateTo("Start Practice");
			Driver.driver.findElement(By.className("custom-practice-quiz")).click();
			Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("span[class='select-lo ']")).click();
			//Driver.driver.findElement(By.xpath("html/body/div[3]/div/div[3]/div/div/div[5]/div/div[2]/span")).click(); //Click on 'Select Learning Objectives' radio button
			Thread.sleep(1000);
            Driver.driver.findElements(By.className("lo-checkbox")).get(0).click();
            Driver.driver.findElements(By.className("lo-checkbox")).get(1).click();
			//Driver.driver.findElement(By.xpath("//*[@id='taxanomy-section-wrapper-1']/div[2]/div/ul/li[1]/span[1]")).click();
			Thread.sleep(1000);
            String qcount=new TextFetch().textfetchbyclass("quiz-data-question-count");//fetch total number of question
            Driver.driver.findElement(By.id("user-question-count")).clear();
            Driver.driver.findElement(By.id("user-question-count")).sendKeys(qcount);
			Driver.driver.findElement(By.className("start-custom-quiz")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper CreateOwnQuizByStudent",e);
		}
	}
	
	public void createOwnQuizByStudentFromAllTLO(String quiztype)
	{
		try
		{
			new Navigator().NavigateTo("Start Practice");
			//pass question type 1-practice type,2-test,3,simulation
			Driver.driver.findElement(By.cssSelector("div[class='practice-icon custom-practice-icon']")).click();//click on custom practice test\
			Driver.driver.findElement(By.xpath("html/body/div[5]/div/div[3]/div/div/div[2]/div[1]/div["+quiztype+"]/span")).click();//select quiz type
           // Driver.driver.findElements(By.className("quiz-mode ")).get(1).click();
			Driver.driver.findElement(By.className("custom-practice-quiz")).click();
			Thread.sleep(2000);
			new ComboBox().selectValue(0, "All");
			Thread.sleep(2000);
			String qcount=new TextFetch().textfetchbyclass("quiz-data-question-count");//fetch total number of question
			int totalquestion=Integer.parseInt(qcount);
			Driver.driver.findElement(By.id("user-question-count")).clear();
			if(totalquestion>40)
			{
				Driver.driver.findElement(By.id("user-question-count")).sendKeys("40");
			}
			else
			{
				Driver.driver.findElement(By.id("user-question-count")).sendKeys(qcount);
			}
			Driver.driver.findElement(By.className("start-custom-quiz")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper createOwnQuizByStudentFromAllTLO",e);
		}
	}
	
	public void OpenCustomQuizPage()
	{
		try
		{
			new Navigator().NavigateTo("Start Practice");
			Driver.driver.findElement(By.className("custom-practice-quiz")).click();//click on custom practice test
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper OpenCustomQuizPage",e);
		}
	}
	
	public void QuestionCountEditable(String question)
	{
	
		try
		{
			Driver.driver.findElement(By.id("user-question-count")).clear();
			Driver.driver.findElement(By.id("user-question-count")).sendKeys(question);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper QuestionCountEditable",e);
		}
	}

    public void createOwnQuizByStudentWithTwoTlo()
    {
        try
        {
            new Navigator().NavigateTo("Start Practice");
            Driver.driver.findElement(By.className("custom-practice-quiz")).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("span[class='select-lo ']")).click();
            //Driver.driver.findElement(By.xpath("html/body/div[3]/div/div[3]/div/div/div[5]/div/div[2]/span")).click(); //Click on 'Select Learning Objectives' radio button
            Thread.sleep(1000);
            Driver.driver.findElements(By.className("lo-checkbox")).get(0).click();
            Driver.driver.findElements(By.className("lo-checkbox")).get(1).click();
            String qcount=new TextFetch().textfetchbyclass("quiz-data-question-count");//fetch total number of question
            int totalquestion=Integer.parseInt(qcount);
            Driver.driver.findElement(By.id("user-question-count")).clear();
            if(totalquestion>40)
            {
                Driver.driver.findElement(By.id("user-question-count")).sendKeys("40");
            }
            else
            {
                Driver.driver.findElement(By.id("user-question-count")).sendKeys(qcount);
            }
            Driver.driver.findElement(By.className("start-custom-quiz")).click();
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper createOwnQuizByStudentWithTwoTlo",e);
        }
    }
}