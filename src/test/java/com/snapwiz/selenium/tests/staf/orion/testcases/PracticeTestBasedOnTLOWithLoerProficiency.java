package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Proficiency;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class PracticeTestBasedOnTLOWithLoerProficiency {
	@Test
	public void practiceTestBasedOnTLOWithLoerProficiency()
	{
		try
		{
			Driver.startDriver();	
			new LoginUsingLTI().ltiLogin("219"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diag test for 1st chapter
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);
			new Navigator().orionDashboard();
			//declaring an array
			List<Integer> profvalues = new ArrayList<Integer>();
			List<Integer> prof = new Proficiency().getProficiencyOfEachTLO();
			Integer [] allProficiencies = prof.toArray(new Integer[profvalues.size()]);
			int minValue = allProficiencies[0];  
			
			//find the minimum proficiency
		    for(int i=0;i < allProficiencies.length;i++)
		    {  
		      if(allProficiencies[i] < minValue)
		      {  
		      minValue = allProficiencies[i];  
		      
		      } 
		    }
		    //find index of minimum proficiency
		    int index = 0;
		    for(int i : allProficiencies)
		    {
		    	if(i == minValue)
		    	{
		    		break;
		    	}
		    	index ++;
		    }
		    //list all the TLOs
			List<WebElement> TLOs = Driver.driver.findElements(By.className("al-terminal-objective-title"));
			String TLOwithMinimumProf = TLOs.get(index).getText();
			
			new PracticeTest().startTest();
			//compare the TLO name 
			String TLOName = Driver.driver.findElement(By.cssSelector("div[class='al-diagtest-skills-evaluted-ul']")).getText();
			if(TLOName.contains(TLOwithMinimumProf))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Practice test is not starting with a question of the TLO which has lower proficiency.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in practiceTestBasedOnTLOWithLoerProficiency in class PracticeTestBasedOnTLOWithLoerProficiency",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		new Screenshot().captureScreenshotFromTestCase();
		Driver.driver.quit();
	}
}
