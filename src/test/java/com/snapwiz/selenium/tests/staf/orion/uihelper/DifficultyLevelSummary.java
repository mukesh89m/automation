package com.snapwiz.selenium.tests.staf.orion.uihelper;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;

public class DifficultyLevelSummary {
	
		
	public List<Integer> getPieChartQuestionCounts()
	{
		List<Integer> counts = new ArrayList<Integer>();
		try
		{			
			new DirectLogin().CMSLogin();
			Driver.driver.findElement(By.cssSelector("img[alt=\""+Config.course+"\"]")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("deliver-course")).click();
			Thread.sleep(2000);			
			Driver.driver.findElement(By.id("refresh-publish-content")).click();
			Thread.sleep(10000);
			Driver.driver.findElement(By.id("deliver-course")).click();
			Thread.sleep(2000);	
			String charttitle = Driver.driver.findElement(By.className("highcharts-title")).getText();
			int totalquestions = Integer.parseInt(charttitle.substring(0, charttitle.indexOf("Questions")));
			counts.add(totalquestions); //Total Questions
			List<WebElement> legends = Driver.driver.findElements(By.className("highcharts-legend-item"));
			legends.get(0).click();
			legends.get(1).click();
			legends.get(2).click();
			legends.get(3).click();
			//Getting No difficulty count
			legends.get(0).click();
			String nodifficulty = Driver.driver.findElement(By.className("highcharts-data-labels")).getText();
			nodifficulty = nodifficulty.substring(0, nodifficulty.indexOf("No Difficulty"));
			legends.get(0).click();
			
			//Getting Easy count
			legends.get(1).click();
			String easy = Driver.driver.findElement(By.className("highcharts-data-labels")).getText();
			easy = easy.substring(0, easy.indexOf("Easy"));
			legends.get(1).click();
			
			//Getting Medium count
			legends.get(2).click();
			String medium = Driver.driver.findElement(By.className("highcharts-data-labels")).getText();
			medium = medium.substring(0, medium.indexOf("Medium"));
			legends.get(2).click();
			
			//Getting Hard count
			legends.get(3).click();
			String hard = Driver.driver.findElement(By.className("highcharts-data-labels")).getText();
			hard = hard.substring(0, hard.indexOf("Hard"));
			legends.get(3).click();
			//Resetting the pie chart
			legends.get(0).click();
			legends.get(1).click();
			legends.get(2).click();
			legends.get(3).click();
			
			counts.add(Integer.parseInt(nodifficulty));
			counts.add(Integer.parseInt(easy));
			counts.add(Integer.parseInt(medium));
			counts.add(Integer.parseInt(hard));
		}
		catch(Exception e)
		{
			Assert.fail("Exception in UI helper getPieChartQuestionCounts in class DifficultyLevelSummary",e);
		}
		return counts;
	}

}
