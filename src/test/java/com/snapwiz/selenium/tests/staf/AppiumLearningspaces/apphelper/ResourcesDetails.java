package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;

public class ResourcesDetails
{
	//its check like,comments,post time,description text instructor name
	public void resourcesdetails(String dataindex)
	{
		try
		{
            String description= ReadTestData.readDataByTagName("", "description", dataindex);//fetch resources name
			String resourcemessage=Driver.driver.findElement(By.cssSelector("div[class='ls-right-resouce-msg']")).getText();//fetch all text of right side frame
            System.out.println("resourcemessage: "+resourcemessage);
            if(!(resourcemessage.trim().equals(description)))
				Assert.fail("Description not present in the resorces.");
			String resourcesdetail=Driver.driver.findElement(By.cssSelector("div[class='ls-right-user-content']")).getText();
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
		try
		{
			List<WebElement> numberoflike=Driver.driver.findElements(By.cssSelector("span[class='ls-right-post-like-count']"));//fetch number of like
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
			List<WebElement> likenumber=Driver.driver.findElements(By.cssSelector("a[class='right-post-like']"));//click on like
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
			List<WebElement> commentnumber = Driver.driver.findElements(By.cssSelector("a[id='ls-right-post-comment-link']"));//click on comment link
            commentnumber.get(commentIndex).click();//click on comment link
            Driver.driver.switchTo().activeElement().sendKeys(comment+Keys.ENTER);//commnet on resources
			/*int index=0;
			for(WebElement numberofcomment:commentnumber)
			{
				if(index==commenttoclick)
				{
					numberofcomment.click();
					Thread.sleep(2000);
					Driver.driver.switchTo().activeElement().sendKeys("comments1");//commnet on resources
					Driver.driver.switchTo().activeElement().sendKeys(Keys.ENTER);//press enter key after write commnet
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
			List<WebElement> numberoflike=Driver.driver.findElements(By.cssSelector("span[class='ls-comment-like-count']"));//count number of comment like 
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
			List<WebElement> likenumber=Driver.driver.findElements(By.cssSelector("a[class='js-comment-like ls-like-comment']"));//click on commnet like link
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
			List<WebElement> numberofcomment=Driver.driver.findElements(By.cssSelector("span[class='ls-right-stream-post-comment-count']"));//number of comment 
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
