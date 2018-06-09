package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class CustomCourseNumber extends Driver{
	private String baseUrlLTI = "http://lti.snapwiz.net/";
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.TestLTILoginLsAdp");
    @Test
    public void customcoursenumber()
    {
    	try
    	{
    		driver.get(baseUrlLTI);
    		Thread.sleep(5000);
    		WebElement text=driver.findElement(By.xpath("/html/body/form/fieldset/table/tbody/tr[21]/td"));
    		String validtext=text.getText();
    		if(validtext.equals("custom_course_number :"))
    		{
    			logger.log(Level.INFO,"Testcase pass");
    		}
    		else
    		{
    			logger.log(Level.INFO,"TestCase Fail");
    			TestCase.fail();
    		}
    		
    	}
    	catch(Exception e)
    	{
    		
  		  
  			  logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper",e);
  	    }
    }


}
