package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import com.snapwiz.selenium.tests.staf.dummies.Driver;

public class TextValidate {
	 public Boolean IsTextPresent( String textToBeVerified) throws Exception
	  {
		 Thread.sleep(3000);  
		    try{
		    	
		    	WebElement labelNode = Driver.driver.findElement(By.cssSelector("body"));
		        String labelNodeText = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",labelNode);	        	        
		        
		        if(labelNodeText.contains(textToBeVerified))		        	
		        	return true;
		        	
		        else		        	
		        	return false;
		        	
		    	 
		    	}
		    catch (Exception e){
		    	Assert.fail("Exception in App Helper TextValidate",e);    	   
		    	return false;
		    	 }
		    
	  }  

}
