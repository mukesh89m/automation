package com.snapwiz.selenium.tests.staf.dummies.testcases.IT14.R32;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.CreateOwnQuizByStudent;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.Click;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.TextFetch;

public class TotalNumberOfAvailableQuestions 
{
	@Test
	public void totalNumberOfAvailableQuestions()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("aa", "");//create student
			new DirectLogin().studentLogin("aa");
			new CreateOwnQuizByStudent().OpenCustomQuizPage();
			//182-195
			Driver.driver.findElement(By.cssSelector("span[value='custom']")).click();//select 'select learing objective'option
			new Click().clickbylist("lo-checkbox", 0);//checked 1st LO
			new Click().clickbylist("lo-checkbox", 1);//checked 2nd LO
			String questionpopulated=Driver.driver.findElement(By.id("user-question-count")).getAttribute("value");//fetch value of 
			if(!questionpopulated.contains("10"))
				Assert.fail("attempt question edit box not populated with 10 questions");
			String qcount=new TextFetch().textfetchbyclass("quiz-data-question-count");//fetch total number of question
			int totalquestion=Integer.parseInt(qcount);
			Driver.driver.findElement(By.id("user-question-count")).clear();
			Driver.driver.findElement(By.id("user-question-count")).sendKeys("20");
			Driver.driver.findElement(By.cssSelector("span[value='custom']")).click();
			Thread.sleep(2000);
			//196-205 question number variation
			new CreateOwnQuizByStudent().QuestionCountEditable("0");//question change to 0
			String error=Driver.driver.findElement(By.id("user-question-count")).getAttribute("style");
			if(!error.contains("red"))
				Assert.fail("error message not shown while question number is 0");
			new CreateOwnQuizByStudent().QuestionCountEditable("1");//question change to 1
			String error1=Driver.driver.findElement(By.id("user-question-count")).getAttribute("style");
			if(error1.contains("red"))
				Assert.fail("error message shown while question number is 1");
			new CreateOwnQuizByStudent().QuestionCountEditable("399");
            Thread.sleep(2000);
			String error2=Driver.driver.findElement(By.id("user-question-count")).getAttribute("style");
            if(!error2.contains("red"))
				Assert.fail("error message shown while question number is 399");
			new CreateOwnQuizByStudent().QuestionCountEditable("401");
			String error3=Driver.driver.findElement(By.id("user-question-count")).getAttribute("style");
			if(!error3.contains("red"))
				Assert.fail("error message not shown while question number is 401");//question change to 401
			new CreateOwnQuizByStudent().QuestionCountEditable("400");
			String error4=Driver.driver.findElement(By.id("user-question-count")).getAttribute("style");
			if(!error4.contains("red"))
				Assert.fail("error message not shown while question number is 400");//question change to 400
			//206-209
			new Click().clickbylistcssselector("span[class='difficulty-level checked']", 0);//deselect all deificulty level
			String questionpopulated1=Driver.driver.findElement(By.id("user-question-count")).getAttribute("value");
			if(!questionpopulated1.contains("0"))
				Assert.fail("attempt question edit box not populated with 0 questions while deselect all difficulty");
			new CreateOwnQuizByStudent().QuestionCountEditable("0");
			String error5=Driver.driver.findElement(By.id("user-question-count")).getAttribute("style");
			if(!error5.contains("red"))
				Assert.fail("error message not shown while question number is edited 0 after deselct all difficulty");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase TotalNumberOfAvailableQuestions in test method totalNumberOfAvailableQuestions",e);
		}
	}
	@AfterMethod
	public void TearDown() throws Exception
	{
		Driver.driver.quit();
	}	

}
