package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;



public class AssignmentSocialElement 
{
	public int countoflikerightframe(int assignmentnumber)
	{
		int likecount=0;
		try
		{
			List<WebElement> allassignment=Driver.driver.findElements(By.cssSelector("span[class='ls-right-post-like-count ls-post-like-count']"));
			int index=0;
			for(WebElement assignment:allassignment)
			{
				String numberoflike=assignment.getText();
				if(index==assignmentnumber)
				{
					likecount=Integer.parseInt(numberoflike);
					break;
				}
				index++;				
			}			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper method assignmentsocialelement in class AssignmentSocialElement",e);
		}
		return likecount;
	}
	
	public void clickonlike(int liketoclik)
	{
		try
		{
			List<WebElement> likenumber=Driver.driver.findElements(By.cssSelector("span[class='ls-right-post-like-link']"));//click on like
			int index=0;
			for(WebElement numberoflike:likenumber)
			{
				if(index==liketoclik)
				{
					numberoflike.click();
					break;					
				}
				index++;
				
			}
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper method clickonlike in class AssignmentSocialElement",e);
		}
	}
	
	public void clickonlikecoursestream(int liketoclik)
	{
		try
		{
			List<WebElement> likenumber=Driver.driver.findElements(By.cssSelector("span[class='ls-post-like-link']"));//click on like
			int index=0;
			for(WebElement numberoflike:likenumber)
			{
				if(index==liketoclik)
				{
					numberoflike.click();
					break;					
				}
				index++;
				
			}
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper method clickonlikecoursestream in class AssignmentSocialElement",e);
		}
	}
	
	public int countoflikecoursestream(int assignmentnumber)
	{
		int likecount=0;
		try
		{
			List<WebElement> allassignment=Driver.driver.findElements(By.cssSelector("span[class='ls-post-like-count']"));
			int index=0;
			for(WebElement assignment:allassignment)
			{
				String numberoflike=assignment.getText();
				if(index==assignmentnumber)
				{
					likecount=Integer.parseInt(numberoflike);
					break;
				}
				index++;				
			}			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper method countoflikecoursestream in class AssignmentSocialElement",e);
		}
		return likecount;
	}
	
	public int countofcomment(int assignmentnumber)
	{
		int likecount=0;
		try
		{
			List<WebElement> allassignment=Driver.driver.findElements(By.cssSelector("span[class='ls-stream-post-comment-count']"));
			int index=0;
			for(WebElement assignment:allassignment)
			{
				String numberoflike=assignment.getText();
				if(index==assignmentnumber)
				{
					likecount=Integer.parseInt(numberoflike);
					break;
				}
				index++;				
			}	
		}
	
		catch(Exception e)
		{
			Assert.fail("Exception in app helper method countoflikecoursestream in class AssignmentSocialElement",e);
		}
		return likecount;
	}
	
	public void clickoncommnetcoursestream(int assignmentnumber)
	{
		try
		{
			List<WebElement> allComment=Driver.driver.findElements(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']"));//click on like
			int index=0;
			for(WebElement comment:allComment)
			{
				if(index==assignmentnumber)
				{
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", comment);	//click on comment
					Thread.sleep(2000);
					String ranstring=new RandomString().randomstring(4);
					Thread.sleep(2000);
					Driver.driver.switchTo().activeElement().sendKeys(ranstring);//comment on resources
					Driver.driver.switchTo().activeElement().sendKeys(Keys.ENTER);//press enter key after write comment
					Thread.sleep(2000);
					break;					
				}
				index++;				
			}
	
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper method clickoncommnetcoursestream in class AssignmentSocialElement",e);
		}
	}
	public void addComment(String comment)
	{
		try
		{
			Driver.driver.findElement(By.id("ls-right-post-comment-link")).click();
			Thread.sleep(2000);
			Driver.driver.switchTo().activeElement().sendKeys(comment);//commnet on resources
			Driver.driver.switchTo().activeElement().sendKeys(Keys.RETURN);//press enter key after write commnet
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper method addComment in class AssignmentSocialElement",e);
		}
	}

}
