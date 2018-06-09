package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;

import org.openqa.selenium.*;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;



public class AssignmentSocialElement extends Driver
{
	public int countoflikerightframe(int assignmentnumber)
	{
		int likecount=0;
		WebDriver driver=Driver.getWebDriver();
		try
		{
			List<WebElement> allassignment=driver.findElements(By.cssSelector("span[class='ls-right-post-like-count ls-post-like-count']"));
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
		WebDriver driver=Driver.getWebDriver();
		try
		{
			List<WebElement> likenumber=driver.findElements(By.cssSelector("i[class='ls-icon-img ls--like-icon']"));//click on like
			int index=0;
			for(WebElement numberoflike:likenumber)
			{
				if(index==liketoclik)
				{
					((JavascriptExecutor)driver).executeScript("arguments[0].click();",numberoflike);
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
		WebDriver driver=Driver.getWebDriver();
		try
		{
			List<WebElement> likenumber=driver.findElements(By.cssSelector("span[class='ls-post-like-link']"));//click on like
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
		WebDriver driver=Driver.getWebDriver();
		try
		{
			List<WebElement> allassignment=driver.findElements(By.cssSelector("span[class='ls-post-like-count']"));
            String numberoflike=allassignment.get(assignmentnumber).getText();
            likecount=Integer.parseInt(numberoflike);
			/*int index=0;
			for(WebElement assignment:allassignment)
			{
				String numberoflike=assignment.getText();
				if(index==assignmentnumber)
				{
					likecount=Integer.parseInt(numberoflike);
					break;
				}
				index++;				
			}*/
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
		WebDriver driver=Driver.getWebDriver();
		try
		{
			List<WebElement> allassignment=driver.findElements(By.cssSelector("span[class='ls-stream-post-comment-count']"));
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
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> likenumber=driver.findElements(By.cssSelector("a[class='ls-stream-post__footer-comment-link js-toggle-comments']"));//click on like
			int index=0;
			for(WebElement numberoflike:likenumber)
			{
				if(index==assignmentnumber) {
					numberoflike.click();
					//((JavascriptExecutor) driver).executeScript("arguments[0].click();", numberoflike);
					String ranstring = new RandomString().randomstring(4);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//div[@name='comment']")).click();
					driver.switchTo().activeElement().sendKeys(ranstring);//commnet on resources
                    List<WebElement> posts = driver.findElements(By.xpath("//div[@class='post-comment']"));
                    for(WebElement post : posts)
                    {
                        if(post.isDisplayed())
                        {
                            post.click(); //click on the post
                        }
                    }
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
			WebDriver driver=Driver.getWebDriver();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("ls-right-post-comment-link")));
			//driver.findElement(By.id("ls-right-post-comment-link")).click();
			Thread.sleep(2000);
			String commentText ="ghsdfsghjsdg";
			driver.findElement(By.xpath("//div[@placeholder='Write your comment']")).sendKeys(commentText);
			driver.findElement(By.className("post-comment")).click();
			/*driver.switchTo().activeElement().sendKeys(comment);//commnet on resources
			driver.switchTo().activeElement().sendKeys(Keys.RETURN);//press enter key after write commnet*/
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper method addComment in class AssignmentSocialElement",e);
		}
	}

}
