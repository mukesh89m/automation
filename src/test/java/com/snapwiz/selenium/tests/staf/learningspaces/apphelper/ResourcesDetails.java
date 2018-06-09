package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class ResourcesDetails extends Driver
{
	//its check like,comments,post time,description text instructor name
	public void resourcesdetails(String dataindex)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
            String description= ReadTestData.readDataByTagName("", "description", dataindex);//fetch resources name
			String resourcemessage=driver.findElement(By.cssSelector("div[class='ls-right-resouce-msg']")).getText();//fetch all text of right side frame
            System.out.println("resourcemessage: "+resourcemessage);
            if(!(resourcemessage.trim().equals(description)))
				Assert.fail("Description not present in the resorces.");
			String resourcesdetail=driver.findElement(By.cssSelector("div[class='ls-right-user-content']")).getText();
            System.out.println("resourcesdetail: "+resourcesdetail);
			if(!resourcesdetail.contains("ago"))
				Assert.fail("ago option not present in resorces deatils.");
			if(resourcesdetail.contains("givenname family"))
				Assert.fail("in potsed resources title present");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in method resourcesdetails class ResourcesDetails.",e);
		}
	}
	
	//count the number of like
	public int resourcescountoflike(int index)
	{
		int likenumber=0;
		WebDriver driver=Driver.getWebDriver();
		try
		{
			List<WebElement> numberoflike=driver.findElements(By.cssSelector("span[class='ls-right-post-like-count']"));//fetch number of like
			int number=0;
			for(WebElement likecount:numberoflike)
			{
				String count=likecount.getText();
				if(index==number)
				{
					likenumber=Integer.parseInt(count);	
					break;
				}
				number++;
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper resourcescountoflike in class ResourcesDetails.",e);			
		}
		return likenumber;
	}
	
	//click on resources Like 
	
	public void ResourcesLikeClick(int liketoclik)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> likenumber=driver.findElements(By.cssSelector("a[class='right-post-like']"));//click on like
            likenumber.get(liketoclik).click();
			/*int index=0;
			for(WebElement numberoflike:likenumber)
			{
				if(index==liketoclik)
				{
					numberoflike.click();
					break;					
				}
				index++;
				
			}*/
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper ResourcesLikeClick in class ResourcesDetails.",e);
		}
		
	}
	//comment on resources 
	public void resourcescomment(int commentIndex, String comment)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> commentnumber = driver.findElements(By.cssSelector("a[id='ls-right-post-comment-link']"));//click on comment link
            commentnumber.get(commentIndex).click();//click on comment link
            driver.switchTo().activeElement().sendKeys(comment+Keys.ENTER);//commnet on resources
			/*int index=0;
			for(WebElement numberofcomment:commentnumber)
			{
				if(index==commenttoclick)
				{
					numberofcomment.click();
					Thread.sleep(2000);
					driver.switchTo().activeElement().sendKeys("comments1");//commnet on resources
					driver.switchTo().activeElement().sendKeys(Keys.ENTER);//press enter key after write commnet
					Thread.sleep(2000);
					break;					
				}
				index++;
			}*/
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper resourcescomment in class ResourcesDetails.",e);
		}
	}
	
	//count number of resources commnet like
	public int resourcescommentcountoflike(int index)
	{
		int likenumber=0;
		try
		{
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> numberoflike=driver.findElements(By.cssSelector("span[class='ls-comment-like-count']"));//count number of comment like
			int number=0;
			for(WebElement likecount:numberoflike)
			{
				String count=likecount.getText();
				if(index==number)
				{
					likenumber=Integer.parseInt(count);	
					break;
				}
				number++;
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper resourcescommentcountoflike in class ResourcesDetails.",e);
			
		}
		return likenumber;
	}
	//click on resources commnet like
	public void resourcescommentlikeclick(int likeIndex)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> likenumber=driver.findElements(By.cssSelector("a[class='js-comment-like ls-like-comment']"));//click on commnet like link
            likenumber.get(likeIndex).click();//click on like
			/*int index=0;
			for(WebElement numberoflike:likenumber)
			{
				if(index==commentlikeclick)
				{
					numberoflike.click();
					Thread.sleep(2000);
					break;					
				}
				index++;				
			}		*/
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper resourcescommentlikeclick in class ResourcesDetails.",e);
			
		}
	}
	//count number of comment on resources
	public int commentcount(int index)
	{
		int commentnumber=0;
		try
		{
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> numberofcomment=driver.findElements(By.cssSelector("span[class='ls-right-stream-post-comment-count']"));//number of comment
			int number=0;
			for(WebElement commentcount:numberofcomment)
			{
				String count=commentcount.getText();
				if(index==number)
				{
					commentnumber=Integer.parseInt(count);	
					break;
				}
				number++;
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper commentcount in class ResourcesDetails.",e);
			
		}
		return commentnumber;
	}
}
