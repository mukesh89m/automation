package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class CustomCourseNumber extends Driver {
	private String baseUrlLTI = "http://idc-lti1.snapwiz.lan/";
	//private String baseUrlLTI=Config.baseLTIURL; 
    @Test
    public void customcoursenumber()
    {
    	try
    	{
    		System.out.println(baseUrlLTI);
    		driver.get(baseUrlLTI);
    		Thread.sleep(5000);
    		WebElement text=driver.findElement(By.xpath("/html/body/form/fieldset/table/tbody/tr[21]/td"));
    		String validtext=text.getText();
    		if(!validtext.equals("custom_course_number :"))
    			Assert.fail("text custom_course_number not shown");   		
    	}
    	catch(Exception e)
    	{ 		 		  
  			  Assert.fail("Exception in class CustomCourseNumber in testcase method customcoursenumber ",e);
  	    }
    }

 }
