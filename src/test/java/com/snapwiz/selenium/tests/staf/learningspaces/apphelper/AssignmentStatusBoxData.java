package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class AssignmentStatusBoxData extends Driver
{
	public int countofboxes()
	{
		int count=0;
		try
		{
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> allelements=driver.findElements(By.className("ls-assignment-activity-data-card"));
			for(WebElement element:allelements)
			{
				count++;
			}								
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method countofbox in class AssignmentStatusBoxData.");
		}
		return count;
		
	}
	public int countofassignmentsinbox(String text)
	{
		int numberofassignment=0;
		int index = 0;
		
		try
		{
			WebDriver driver=Driver.getWebDriver();
			
			List<WebElement> boxtext=driver.findElements(By.className("ls-assignement-data-label"));
			for(WebElement textofbox:boxtext)
			{
				
				if(textofbox.getText().equals(text))
				{
					System.out.println("inside if");
					break;			
				}
				index++;
				
			}
			
			List<WebElement> boxvalues = driver.findElements(By.className("ls-assignment-data-count"));
			numberofassignment = Integer.parseInt(boxvalues.get(index).getText());
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method countofassignmentsinbox in class AssignmentStatusBoxData.",e);
		}
		return numberofassignment;
	}

}
