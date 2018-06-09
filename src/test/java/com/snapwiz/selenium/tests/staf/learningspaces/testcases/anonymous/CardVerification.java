package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandCollapseChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCVerify;



public class CardVerification extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.CardVerification");
	/*
	 * 584-591
	 */
	@Test(priority=1,enabled=true)
	public void cardverifiction()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1");
			new Navigator().NavigateTo("eTextbook");
			new TOCVerify().tocChapterVerify(1);		 
    		
    		 //Verifying if arrow is pointing up when chapter is expanded
    		new ExpandCollapseChapter().expandChapter(1); //Expanding chapter 1
    		Thread.sleep(3000);
    		//Verifying the topics in chapter 1
    		new TOCVerify().tocTopicValidate(1,1);
		    new TOCVerify().tocTopicValidate(1,2);
		    new TOCVerify().tocTopicValidate(1,3);
		    new TOCVerify().tocTopicValidate(1,4);   	   
			
			
		}
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in Test Case cardverifiction in class CardVerification",e);			 
			  Assert.fail("Exception in Test Case cardverifiction in class CardVerification",e);
			  
		}
	}
	
	@Test(priority=2,enabled=false)
	public void cardVerificationCourseTypeLS()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("2");
			new Navigator().NavigateTo("eTextbook");
			new TOCVerify().tocChapterVerify(2);
			
			String unselectedchpcolorv=driver.findElement(By.cssSelector("h3[class='chapter-heading-label']")).getCssValue("background-color");
		    if(!unselectedchpcolorv.equals("transparent")) Assert.fail("Some color present for the chapter without mouse overing it.");
		    Actions action = new Actions(driver); //Mouse overing the chapter
			WebElement we = driver.findElement(By.className("chapter-heading"));
			action.moveToElement(we).build().perform();
		    String color=we.getCssValue("background-color");
		    if(!color.contains("rgba"))   Assert.fail("The chapter name doesn't get highligted with some color when mouse over is done on it.");
		    
		    new TOCVerify().tocTopicValidate(2,1);
		    new TOCVerify().tocTopicValidate(2,2);
		    //Verifying if arrow is pointing down when chapter is not expanded
		    new ExpandCollapseChapter().collapseChapter(1); //Collapsing chapter 1 after topic validation
		    Thread.sleep(3000);
		    WebElement el=driver.findElement(By.cssSelector("div[class='chapter-heading']"));
    		String imagevalue=el.getCssValue("background-image");    	 
    		if(!imagevalue.trim().equals("url(\"http://localhost:8080/webresources/images/ls/toc-chapter-down-arr.png\")"))
    			Assert.fail("Arrow not pointing downwards when the chapter 1 is not expanded expanded");
    		
    		 //Verifying if arrow is pointing up when chapter is expanded
    		new ExpandCollapseChapter().expandChapter(1); //Expanding chapter 1
    		Thread.sleep(3000);
    		//Verifying the topics in chapter 1
    		new TOCVerify().tocTopicValidate(2,1);
		    new TOCVerify().tocTopicValidate(2,2);
		    new TOCVerify().tocTopicValidate(2,3);
		    new TOCVerify().tocTopicValidate(2,4);
    	    WebElement element=driver.findElement(By.cssSelector("div[class='chapter-heading chapter-selected']"));
	    	String value34=element.getCssValue("background-image");	    	
	    	if(!value34.trim().equals("url(\"http://localhost:8080/webresources/images/ls/toc-chapter-up-arr.png\")"))
	    		Assert.fail("Arrow not pointing upwards after clicking on the chapter and its expanded");
			
			
		}
		catch (Exception e)
		{
			  logger.log(Level.SEVERE,"Exception in Test Case cardverifiction in class CardVerification",e);			 
			  Assert.fail("Exception in Test Case cardverifiction in class CardVerification",e);
			  
		}
	}

	
}
