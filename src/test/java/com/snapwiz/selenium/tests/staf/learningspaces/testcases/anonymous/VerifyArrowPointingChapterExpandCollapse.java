package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandCollapseChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCVerify;

public class VerifyArrowPointingChapterExpandCollapse extends Driver{

	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.VerifyArrowPointingChapterExpandCollapse");
	@Test
	public void verifyArrowPointingChapterExpandCollapse()
	{
		try
		{
			logger.log(Level.INFO,"TestCase verifyArrowPointingChapterExpandCollapse under class AgeOfPostingComment Execution Started");
			new LoginUsingLTI().ltiLogin("570");
			new Navigator().NavigateTo("eTextbook");
			new TOCVerify().tocChapterVerify(1);			
			String unselectedchpcolorv=driver.findElement(By.cssSelector("h3[class='chapter-heading-label']")).getCssValue("background-color");
			String defaultcolor;
			if(Config.browser.equals("chrome"))
				defaultcolor = "rgba(0, 0, 0, 0)";
			else
				defaultcolor = "transparent";
		    if(!unselectedchpcolorv.equals(defaultcolor)) Assert.fail("Some color present for the chapter without mouse overing it.");
		    Actions action = new Actions(driver); //Mouse overing the chapter
			WebElement we = driver.findElement(By.className("chapter-heading"));
			action.moveToElement(we).build().perform();
		    String color=we.getCssValue("background-color");
		    if(!color.contains("rgba"))   Assert.fail("The chapter name doesn't get highligted with some color when mouse over is done on it.");
		    new ExpandCollapseChapter().expandChapter(1);
		    //Verifying if arrow is pointing down when chapter is not expanded
		    new ExpandCollapseChapter().collapseChapter(1); //Collapsing chapter 1 after topic validation
		    Thread.sleep(3000);
		    WebElement el=driver.findElement(By.cssSelector("div[class='chapter-heading']"));
    		String imagevalue=el.getCssValue("background-image");    
    		
    		String urlarrowdown;
    	    if(Config.browser.equals("chrome"))
    	    	urlarrowdown = "url("+Config.baseURL+"/webresources/images/ls/toc-chapter-down-arr.png)";
    	    else
    	    	urlarrowdown = "url(\""+Config.baseURL+"/webresources/images/ls/toc-chapter-down-arr.png\")";
    		
    		if(!imagevalue.trim().equals(urlarrowdown))
    			Assert.fail("Arrow not pointing downwards when the chapter 1 is not expanded");
    		
    		 //Verifying if arrow is pointing up when chapter is expanded
    		new ExpandCollapseChapter().expandChapter(1); //Expanding chapter 1
    		Thread.sleep(3000);
    	    WebElement element=driver.findElement(By.cssSelector("div[class='chapter-heading chapter-selected']"));
	    	String value34=element.getCssValue("background-image");	    	
	    	String urlarrowup;
    	    if(Config.browser.equals("chrome"))
    	    	urlarrowup = "url("+Config.baseURL+"/webresources/images/ls/toc-chapter-up-arr.png)";
    	    else
    	    	urlarrowup = "url(\""+Config.baseURL+"/webresources/images/ls/toc-chapter-up-arr.png\")";
	    	if(!value34.trim().equals(urlarrowup))
	    		Assert.fail("Arrow not pointing upwards after clicking on the chapter and its expanded");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase verifyArrowPointingChapterExpandCollapse in class VerifyArrowPointingChapterExpandCollapse",e);
		}
	}
	

}
