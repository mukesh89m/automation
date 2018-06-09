package com.snapwiz.selenium.tests.staf.orion.apphelper;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.snapwiz.selenium.tests.staf.orion.Driver;

public class QuestionCount
{
	public boolean questioncount(String chapternametoverify,String presentquestion,String totalnoofquestion,String presentquestionafterclick,String totalnoofquestionafterclick)
	{
		try
		{
			String chaptername=Driver.driver.findElement(By.className("al-diag-test-title")).getAttribute("title");
			
			String questionnumber=Driver.driver.findElement(By.className("al-diag-chapter-details")).getText();
			
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='button']")));
			Thread.sleep(5000);
			String nextbutton=Driver.driver.findElement(By.cssSelector("input[type='button']")).getAttribute("title");
			if(nextbutton.trim().equals("Next"))
		    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='button']")));	
			Thread.sleep(5000);
			String questionnumberafterclick=Driver.driver.findElement(By.className("al-diag-chapter-details")).getText();
			Thread.sleep(5000);
			
			boolean chapternamevalue=chaptername.contains(chapternametoverify);
			boolean presentquestionnumbervalue=questionnumber.contains(presentquestion);
			boolean totalquestionnumbervalue=questionnumber.contains(totalnoofquestion);
			boolean presentquestionnumbervalueafterclick=questionnumberafterclick.contains(presentquestionafterclick);
			boolean totalquestionnumbervalueafterclick=questionnumberafterclick.contains(totalnoofquestionafterclick);
			System.out.println(chapternamevalue);
			System.out.println(presentquestionnumbervalue);			
			System.out.println(totalquestionnumbervalue);
			System.out.println(presentquestionnumbervalueafterclick);
			System.out.println(totalquestionnumbervalueafterclick);
			
			if(chapternamevalue==true && presentquestionnumbervalue==true && totalquestionnumbervalue==true && presentquestionnumbervalueafterclick==true && totalquestionnumbervalueafterclick==true)
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
