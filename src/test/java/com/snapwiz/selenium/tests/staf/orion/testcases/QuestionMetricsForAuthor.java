package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Questions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.uihelper.DifficultyLevelSummary;

public class QuestionMetricsForAuthor {
	
	@Test(priority = 1, enabled = false)
	public void summaryPage()
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();
			Driver.driver.findElement(By.cssSelector("img[alt=\""+Config.course+"\"]")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("deliver-course")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("refresh-publish-content")).click();
			Thread.sleep(10000);
			Driver.driver.findElement(By.linkText("Difficulty Level Count")).click();
			String charttitle = Driver.driver.findElement(By.className("highcharts-title")).getText();
			String questioncnt_dlc = charttitle.substring(0, charttitle.indexOf("Questions"));
			if(questioncnt_dlc == "" || questioncnt_dlc == null) Assert.fail("The total question count not shown at the center of Pie chart");
			String dataLabels = Driver.driver.findElement(By.className("highcharts-data-labels")).getText();
			System.out.println(dataLabels);
			Thread.sleep(3000);
			Driver.driver.findElement(By.linkText("Bloom's Taxonomy Count")).click();
			Thread.sleep(3000);
			String questioncnt_btc = charttitle.substring(0, charttitle.indexOf("Questions"));
			if(questioncnt_btc == "" || questioncnt_btc == null) Assert.fail("The total question count not shown at the center of Pie chart");
			Driver.driver.findElement(By.linkText("Bloom's Taxonomy Count")).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.linkText("Question Status Count")).click();
			String questioncnt_qsc = charttitle.substring(0, charttitle.indexOf("Questions"));
			if(questioncnt_qsc == "" || questioncnt_qsc == null) Assert.fail("The total question count not shown at the center of Pie chart");
			//System.out.println(dataLabels.substring(0, dataLabels.indexOf("No Difficulty")));
			//System.out.println(dataLabels.substring(dataLabels.indexOf("No Difficulty")));
			if(Integer.parseInt(questioncnt_dlc) !=Integer.parseInt(questioncnt_btc)) Assert.fail("Total question count in dlc not equal to present in btc");
			if(Integer.parseInt(questioncnt_qsc) !=Integer.parseInt(questioncnt_btc)) Assert.fail("Total question count in btc not equal to present in qsc");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in Test Case summaryPage in class QuestionMetricsForAuthor",e);
		}
	}
	
	@Test(priority = 2, enabled = false)
	public void clickOnLegends()
	{
		try
		{
			Driver.startDriver();
			List<Integer> counts = new DifficultyLevelSummary().getPieChartQuestionCounts();			
			if(counts.get(0) != (counts.get(1)+counts.get(2)+counts.get(3)+counts.get(4)))
				Assert.fail("Question count at center of pie chart not equal to sum of question count under breakup.");
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in Test Case clickOnLegends in class QuestionMetricsForAuthor",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void deactivateQuestion()
	{
		try
		{			
			Driver.startDriver();
			List<Integer> counts = new DifficultyLevelSummary().getPieChartQuestionCounts();
			System.out.println(counts);
			new Questions().deActivateQuestion(617);
			List<Integer> counts_after_deactivate = new DifficultyLevelSummary().getPieChartQuestionCounts();
			System.out.println(counts_after_deactivate);
			if(counts.get(0) != (counts_after_deactivate.get(0) +1) )
				Assert.fail("The count of total question in pie chart not decremented after deactivating a questions");
			if(counts_after_deactivate.get(0) != (counts_after_deactivate.get(1)+counts_after_deactivate.get(2)+counts_after_deactivate.get(3)+counts_after_deactivate.get(4)))
				Assert.fail("Question count at center of pie chart not equal to sum of question count under breakup.");
			//Create Duplicate Question			
			new Questions().duplicateQuestion(617);
			List<Integer> counts_after_adding_duplicate = new DifficultyLevelSummary().getPieChartQuestionCounts();
			System.out.println(counts_after_adding_duplicate);
			System.out.println("one - "+counts_after_adding_duplicate.get(0));
			System.out.println("two - "+counts.get(0));
			if(counts_after_adding_duplicate.get(0) != counts.get(0))
				Assert.fail("The total question count after adding a duplicate question of deactivated question is not equal to the initial count");
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in Test Case deactivateQuestion in class QuestionMetricsForAuthor",e);
		}
	}
	
	@AfterMethod
	public void TearDown()throws Exception
	{
		new Screenshot().captureScreenshotFromTestCase();
		Driver.driver.quit();
	}

}
