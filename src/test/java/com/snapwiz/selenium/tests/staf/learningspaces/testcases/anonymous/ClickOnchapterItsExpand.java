package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandCollapseChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCVerify;


public class ClickOnchapterItsExpand extends Driver{
	public static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.ClickOnchapterItsExpand");
	/*
	 * 562-569
	 */
	@Test
	public void chapterExpandOnClick()
	{
		try
		{
		    new LoginUsingLTI().ltiLogin("562");
		    new Navigator().NavigateTo("eTextBook");		    
		    new ExpandCollapseChapter().collapseChapter(1);
			
		    	String colorv=driver.findElement(By.cssSelector("h3[class='chapter-heading-label']")).getCssValue("background-color");
			   // driver.findElement(By.cssSelector("h3[class='chapter-heading-label']")).click();
			    Thread.sleep(3000);	
			    new ExpandCollapseChapter().expandChapter(1);
			    String color=driver.findElement(By.cssSelector("div[class='chapter-heading chapter-selected']")).getCssValue("background-color");
			    System.out.println(colorv); System.out.println(color);
			    if(colorv.equals(color))
			    {	    	
			    	Assert.fail("The color of the chapter box does not change even after clicking on it.");
			    }   
			    
			    WebElement el=driver.findElement(By.cssSelector("div[class='chapter-heading chapter-selected']"));
	    		String value34=el.getCssValue("background-image");
	    		String url;
	    	    if(Config.browser.equals("chrome"))
	    	    	url = "url("+Config.baseURL+"/webresources/images/ls/toc-chapter-up-arr.png)";
	    	    else
	    	    	url = "url(\""+Config.baseURL+"/webresources/images/ls/toc-chapter-up-arr.png\")";
	    		if(!value34.trim().equals(url))
	    			Assert.fail("Arrow not pointing upwards after clicking on the chapter and its expanded");
	    		
	    		//Expanding chapter 2
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("chapter-heading")));
			    Thread.sleep(2000);			    
			    int index = 0;
			    List<WebElement> chapters =  driver.findElements(By.className("chapter-heading"));
			   for(WebElement chapter:chapters)
			   {
				   if(index==1)
				   {
					   chapter.click();
					   break;
				   }
				   index++;
			   }			   	    
		}
			
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in TestCase chapterExpandOnClick in class ClickOnchapterItsExpand",e);		
			  Assert.fail("Exception in TestCase chapterExpandOnClick in class ClickOnchapterItsExpand",e);
		}
	}


}
