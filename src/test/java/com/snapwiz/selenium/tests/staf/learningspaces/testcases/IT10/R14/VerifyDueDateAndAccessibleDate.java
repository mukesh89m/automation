package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class VerifyDueDateAndAccessibleDate extends Driver
{

@Test(priority = 1, enabled = true)
public void verifyColorForDueDateAccessibleAfterDateForNonGradableAssignment()
	{
	try
		{
			new Assignment().create(1750);
			new LoginUsingLTI().ltiLogin("1750");
			new Assignment().assignToStudent(1750);
			new LoginUsingLTI().ltiLogin("1750");
			new Navigator().NavigateTo("Assignments");
			String dueDateColor = driver.findElement(By.cssSelector("span[class='ls-assignment-not-due-date-title']")).getCssValue("color");
			
			String accessibleAftercolor = driver.findElement(By.cssSelector("div[class='ls-assignment-accessible-after-title']")).getCssValue("color");
			
			if(dueDateColor.length()>10)
            {
               String colorx = dueDateColor.substring(5, 8);
               
               String colory = dueDateColor.substring(10,13);
               
               String colorz = dueDateColor.substring(15, 18);
               
               String rgbhiglighted = String.format("#%02x%02x%02x", Integer.parseInt(colorx), Integer.parseInt(colory), Integer.parseInt(colorz));
               
               if(!rgbhiglighted.equalsIgnoreCase("#8F8F8F"))
               {
            	  Assert.fail("Due date label doesnt appear in grey color");
               }
              
            }
			
			if(accessibleAftercolor.length()>10)
            {
               String colorx = accessibleAftercolor.substring(5, 8);
               
               String colory = accessibleAftercolor.substring(10,13);
               
               String colorz = accessibleAftercolor.substring(15, 18);
               
               String rgbhiglighted = String.format("#%02x%02x%02x", Integer.parseInt(colorx), Integer.parseInt(colory), Integer.parseInt(colorz));
               
               if(!rgbhiglighted.equalsIgnoreCase("#8F8F8F"))
               {
            	  Assert.fail("Accessible After label doesnt appear in grey color");
               }
              
            }
		}
	catch(Exception e)
		{
		Assert.fail("Exception in verifyColorForDueDateAccessibleAfterDateForNonGradableAssignment in class VerifyDueDateAndAccessibleDate",e);
		}
	
	}
@Test(priority = 2, enabled = true)
public void verifyColorForDueDateAccessibleAfterDateForGradableAssignment()
	{
	try
		{
			new Assignment().create(1751);
			new LoginUsingLTI().ltiLogin("17510");
			new LoginUsingLTI().ltiLogin("1751");
			new Assignment().assignToStudent(1751);
			new LoginUsingLTI().ltiLogin("1751");
			new Navigator().NavigateTo("Assignments");
			String dueDateColor = driver.findElement(By.cssSelector("span[class='ls-assignment-not-due-date-title']")).getCssValue("color");
			
			String accessibleAftercolor = driver.findElement(By.cssSelector("div[class='ls-assignment-accessible-after-title']")).getCssValue("color");
			
			if(dueDateColor.length()>10)
            {
               String colorx = dueDateColor.substring(5, 8).trim();
               
               String colory = dueDateColor.substring(10,13).trim();
               
               String colorz = dueDateColor.substring(15, 18).trim();
               
               String rgbhiglighted = String.format("#%02x%02x%02x", Integer.parseInt(colorx), Integer.parseInt(colory), Integer.parseInt(colorz));
               
               if(!rgbhiglighted.equalsIgnoreCase("#8F8F8F"))
               {
            	  Assert.fail("Due date label doesnt appear in grey color");
               }
              
            }
			
			if(accessibleAftercolor.length()>10)
            {
               String colorx = accessibleAftercolor.substring(5, 8).trim();
               
               String colory = accessibleAftercolor.substring(10,13).trim();
               
               String colorz = accessibleAftercolor.substring(15, 18).trim();
               
               String rgbhiglighted = String.format("#%02x%02x%02x", Integer.parseInt(colorx), Integer.parseInt(colory), Integer.parseInt(colorz));
               
               if(!rgbhiglighted.equalsIgnoreCase("#8F8F8F"))
               {
            	  Assert.fail("Accessible After label doesnt appear in grey color");
               }
            }
			new LoginUsingLTI().ltiLogin("17510");	 //logging in as student to submit the assessment and then check the color of due date
			new Assignment().submitAssignmentAsStudent(1751);
			new Navigator().NavigateTo("Assignments");
				String dueDateColor1 = driver.findElement(By.cssSelector("span[class='ls-assignment-not-due-date-title']")).getCssValue("color");
				
				if(dueDateColor1.length()>10)
				{
					String colorx = dueDateColor1.substring(5, 8).trim();
					
					String colory = dueDateColor1.substring(10,12).trim();
					
					String colorz = dueDateColor1.substring(14, 16).trim();
					
					String rgbhiglighted = String.format("#%02x%02x%02x", Integer.parseInt(colorx), Integer.parseInt(colory), Integer.parseInt(colorz));
                    System.out.println("rgbhiglighted: "+rgbhiglighted);
                    if(!rgbhiglighted.equalsIgnoreCase("#7e0c01"))
					{
						Assert.fail("Due date doesnt appear in red color");
					}
				} 
				
				/*String accessibleAfterDatecolor = driver.findElement(By.cssSelector("span[class='ls-assignment-accessible-after-title']")).getCssValue("color");
				
				if(accessibleAfterDatecolor.length()>10)
				{
					String colorx = accessibleAfterDatecolor.substring(5, 7).trim();
					
					String colory = accessibleAfterDatecolor.substring(9,11).trim();
					
					String colorz = accessibleAfterDatecolor.substring(13, 15).trim();
					
					String rgbhiglighted = String.format("#%02x%02x%02x", Integer.parseInt(colorx), Integer.parseInt(colory), Integer.parseInt(colorz));
					
					if(!rgbhiglighted.equals("#484848"))
					{
						Assert.fail("Accessible After Date doesnt appear in red color");
					}
				}            */
		}
	catch(Exception e)
		{
		Assert.fail("Exception in verifyDueDateNotPresentForNonGradableAssignment in class VerifyDueDateAndAccessibleDate",e);
		}
	
	}

}
