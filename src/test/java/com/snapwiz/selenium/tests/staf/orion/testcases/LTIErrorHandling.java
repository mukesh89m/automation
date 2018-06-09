package com.snapwiz.selenium.tests.staf.orion.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.Config;


public class LTIErrorHandling
{
	@Test
	public void ltierrorhandling()
	{
		try
		{
				Driver.startDriver();
			  	Driver.driver.get(Config.baseLTIURL + "/");		  
			  	Driver.driver.findElement(By.name("endpoint")).clear(); //Clear fields
			  	Driver.driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);			    
			  	Driver.driver.findElement(By.name("key")).clear();
			  	Driver.driver.findElement(By.name("key")).sendKeys(Config.customerkey);			    
			  	Driver.driver.findElement(By.name("secret")).clear();
			  	Driver.driver.findElement(By.name("secret")).sendKeys(Config.secretkey);			    
			  	Driver.driver.findElement(By.name("resource_link_id")).clear();			  				  
			  	Driver.driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);	
			  	Driver.driver.findElement(By.name("lti_version")).clear();
			  	Driver.driver.findElement(By.name("lti_version")).sendKeys("sdfhbdsfh");
			  	Driver.driver.findElement(By.name("user_id")).clear();
			  	Driver.driver.findElement(By.name("user_id")).sendKeys("user_1630");			    
			  	Driver.driver.findElement(By.name("roles")).clear();
			  	Driver.driver.findElement(By.name("roles")).sendKeys("student");		    
			  	Driver.driver.findElement(By.name("lis_person_name_family")).clear();			  
			  	Driver.driver.findElement(By.name("lis_person_name_family")).sendKeys(Config.familyname);			  			    
			  	Driver.driver.findElement(By.name("lis_person_name_given")).clear();			 
			  	Driver.driver.findElement(By.name("lis_person_name_given")).sendKeys(Config.givenname);
			  	Driver.driver.findElement(By.name("lis_person_contact_email_primary")).clear();
			  	Driver.driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);
			  	Driver.driver.findElement(By.name("context_id")).clear();
			  	Driver.driver.findElement(By.name("context_id")).sendKeys(Config.context_id);
			  	Driver.driver.findElement(By.name("context_title")).clear();
			  	Driver.driver.findElement(By.name("context_title")).sendKeys(Config.context_title);
			  	Driver.driver.findElement(By.name("tool_consumer_instance_guid")).clear();
			  	Driver.driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);			    
			  	Driver.driver.findElement(By.name("tool_consumer_instance_name")).clear();
			  	Driver.driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);			    
			  	Driver.driver.findElement(By.name("custom_courseid")).clear();
			  	Driver.driver.findElement(By.name("custom_courseid")).sendKeys(Config.courseid);			  
			  	Driver.driver.findElement(By.name("custom_destination")).clear();
			  	Driver.driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination); 	
			  	Driver.driver.findElement(By.name("custom_domainid")).clear();
			  	Driver.driver.findElement(By.name("custom_domainid")).sendKeys(Config.custom_domainid);
			  	Driver.driver.findElement(By.name("custom_course_number")).clear();
			  	Driver.driver.findElement(By.name("custom_course_number")).sendKeys(Config.custom_course_number);			  			    
			  	Driver.driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
			  	Driver.driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();
			  	Thread.sleep(2000);
			  	int closewelcomepage=Driver.driver.findElements(By.cssSelector("div[class='al-dialog-welcome-close-icon']")).size();
			    if(closewelcomepage>=1)
			    {
			    	Driver.driver.findElement(By.cssSelector("div[class='al-dialog-welcome-close-icon']")).click();
			    	Thread.sleep(2000);
			    }
			    boolean backtowielyplusoption=Driver.driver.findElement(By.id("al-error-header-return-url")).isDisplayed();//verify back to wiley plus link
			    if(backtowielyplusoption==false)
			    {
			    	Assert.fail("back to wileyplus option not present on error page");
			    }
			    String errortext=Driver.driver.findElement(By.className("al-error-body-content")).getText();//fetch error text
			    if(!errortext.contains("Something went wrong in processing your request."))
			    {
			    	Assert.fail("error text not shown");
			    }
			    Driver.driver.findElement(By.id("al-report-this-issue-link")).click();//report to issue link
			    
			    
		} 
		catch (Exception e)
		{
			Assert.fail("Exception in testcase method ltierrorhandling in class LTIErrorhandling",e);
		}
	}
	
}
