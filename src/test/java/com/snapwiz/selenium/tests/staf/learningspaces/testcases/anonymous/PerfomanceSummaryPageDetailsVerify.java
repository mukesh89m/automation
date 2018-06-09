package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PerfomancePageVerification;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectAnswerAndSubmit;

public class PerfomanceSummaryPageDetailsVerify extends Driver
{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.PerfomanceSummaryPageDetailsVerify");
	@Test
	public void perfomanceSummaryPageDetailsVerify()
	{
		try
		{
			String daigonestictest = ReadTestData.readDataByTagName("tocdata", "daigonestictest", "1");
			new LoginUsingLTI().ltiLogin("1263");
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);		
			new SelectAnswerAndSubmit().daigonestianswersubmit("D");
			Thread.sleep(3000);
			new SelectAnswerAndSubmit().daigonestianswersubmit("A");		
			Thread.sleep(3000);
			if (!new PerfomancePageVerification().performanceTabBarGraph())			
				Assert.fail("Perfomance tab is not present with Bar graph for 'Performance in last 10 question' as first item and 'About this question' in second position");
						
			if(!new PerfomancePageVerification().checkforTextAlongBarGraph())			
				Assert.fail("Along x-axis 'Question' is not present and along y-axis 'Easy' and 'Hard' is not present");			
			
			if(!new PerfomancePageVerification().checkBarAreComingForAnswers())			
				Assert.fail("For answers Bar doesn't apper  in perfomance graph");		
			
			driver.findElement(By.cssSelector("div[class='al-quit-diag-test-icon']")).click();
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("span[class='al-quit-diag-test al-view-diag-practice-test-report']")).click();
			Thread.sleep(3000);
			
			String chapterNumberName  = driver.findElement(By.cssSelector("div[id='al-peformance-title-id']")).getText();
			if(!chapterNumberName.equals(daigonestictest))			
				Assert.fail("In Performance Summary Page chapter name and chapter number is not displaying");
			
			String PerfomancepagePresent = driver.findElement(By.cssSelector("div[class='al-performance-chart-title']")).getText();
			if(!PerfomancepagePresent.equals("Performance Summary"))			
				Assert.fail("Performance Summary Page is not displaying");
			
			

			String  performanceObjectives = driver.findElement(By.cssSelector("div[class='al-chapter-performance-title']")).getText();
			if(!performanceObjectives.equals("Performance by Questions"))			
				Assert.fail("'Performance by Questions' is absent in performance summary page");
			
		
			String textInYaxis = driver.findElement(By.cssSelector("div[id='al-preformance-bar-chart-outer-wrapper-id']")).getText();
		
			if(!textInYaxis.contains("Hard"))			
				Assert.fail("'Hard' is absent along Y-axis");
			
			if(!textInYaxis.contains("Medium"))			
				Assert.fail("'Medium' is absent along Y-axis");
			
			if(!textInYaxis.contains("Easy"))			
				Assert.fail("'Easy' is absent along Y-axis");
			
			if(!textInYaxis.contains("Questions"))			
				Assert.fail("Text along X- axis is not 'Questions' and hence InCorrect");			
			
			WebElement WE3 = driver.findElement(By.cssSelector("div[class='al-filter-questions-block']"));
			String fiterQuestion = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",WE3);
			if(!fiterQuestion.contains("Filter Questions"))			
				Assert.fail("'Fiter option is not present");
			
			boolean questionCard = driver.findElement(By.cssSelector("div[id='al-peformance-title-id']")).isDisplayed();
			if(questionCard==false)			
				Assert.fail("Question card is not displayed in performance summary page");		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.log(Level.SEVERE,"Exception in PerfomanceSummaryPageDetailsVerify",e);
			Assert.fail("Exception in PerfomanceSummaryPageDetailsVerify",e);
		}
	}


}
