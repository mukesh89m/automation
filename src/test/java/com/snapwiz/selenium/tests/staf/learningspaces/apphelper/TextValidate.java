package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class TextValidate extends Driver {
	 public Boolean IsTextPresent( String textToBeVerified) throws Exception
	  {
		 Thread.sleep(3000);  
		    try{
				WebDriver driver=Driver.getWebDriver();
		    	WebElement labelNode = driver.findElement(By.cssSelector("body"));
		        String labelNodeText = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",labelNode);
		        
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
