package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import junit.framework.Assert;

import org.openqa.selenium.By;
import com.snapwiz.selenium.tests.staf.dummies.Driver;

public class QuestionDifficulty {
	//private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.DaigonesticTestNevigateToQuestion");
	//Its shows difficulty Level and % bar
	public boolean questiondiffculty()
	{
		try
		{
			boolean diffcultybar=Driver.driver.findElement(By.className("al-difficulty-level-status")).isDisplayed();
			boolean diffcultypercent=Driver.driver.findElement(By.className("al-diagtest-percentage-score")).isDisplayed();
			boolean difficltylevel=Driver.driver.findElement(By.className("al-diagtest-percentage-score-text")).isDisplayed();
			if(diffcultybar==true && diffcultypercent==true && difficltylevel==true)
			{
				return true;
			}
			else
			{
				
				return false;
			}
			
		}
		catch(Exception e)
		{
			
			
			return false;
		}
		
			
	}

}
