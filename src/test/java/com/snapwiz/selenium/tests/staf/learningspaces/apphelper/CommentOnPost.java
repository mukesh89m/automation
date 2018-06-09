package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;


import org.openqa.selenium.*;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class CommentOnPost extends Driver{
	
	public boolean commentOnPost(String commenttext,int postOneBasedIndex)
	{
		Boolean commentposted = false;
        WebDriver driver=Driver.getWebDriver();
		try
		{
		int index = 1;
		List<WebElement> commentarea = driver.findElements(By.name("comment"));
		for(WebElement comment : commentarea)
		{
			if(index == postOneBasedIndex)
			{
				List<WebElement> allComments = driver.findElements(By.cssSelector("a[title='Comments']"));
                allComments.get(postOneBasedIndex).click();//click on Comments Link
                Thread.sleep(1000);
                String commentText1 = new RandomString().randomstring(10);
                List<WebElement> comment1=driver.findElements(By.xpath("//div[@placeholder='Write your comment']"));
                for(WebElement ele:comment1){
                    if(ele.isDisplayed()){
                        ele.sendKeys(commentText1);
                    }
                }
                List<WebElement> posts = driver.findElements(By.xpath("//div[starts-with(@class,'post-comment')]"));
                for(WebElement post : posts)
                {
                    if(post.isDisplayed())
                    {
                        posts.get(postOneBasedIndex).click();
                    }
                }

				Thread.sleep(3000);
				commentposted = true;
			}
            else
            {
                List<WebElement> allComments = driver.findElements(By.cssSelector("a[title='Comments']"));
                ((JavascriptExecutor)driver).executeScript("arguments[0].click()",allComments.get(postOneBasedIndex));
                //allComments.get(postOneBasedIndex).click();//click on Comments Link
                Thread.sleep(3000);
                String commentText1 = new RandomString().randomstring(10);
                List<WebElement> comment1=driver.findElements(By.xpath("//div[@placeholder='Write your comment']"));
                for(WebElement ele:comment1){
                    if(ele.isDisplayed()){
                        ele.sendKeys(commentText1);
                    }
                }
                List<WebElement> posts = driver.findElements(By.xpath("//div[starts-with(@class,'post-comment')]"));
                for(WebElement post : posts)
                {
                    if(post.isDisplayed()) {
                        ((JavascriptExecutor)driver).executeScript("arguments[0].click()",post);
                    }
                }
                Thread.sleep(3000);
                commentposted = true;
            }
            if(commentposted == true) break;
			
			index++;
		}
		Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper CommentOnPost",e);
		}
		return commentposted;
	}

    public static void commentOnPostWithStaticText(String commentText) throws InterruptedException {
        WebDriver driver=Driver.getWebDriver();
        try {
            List<WebElement> allComments = driver.findElements(By.cssSelector("a[title='Comments']"));
            for(WebElement ele:allComments){
                if(ele.isDisplayed()){
                    ele.click();
                }
            }
            Thread.sleep(1000);
            List<WebElement> comment=driver.findElements(By.xpath("//div[@placeholder='Write your comment']"));
            for(WebElement ele:comment){
                if(ele.isDisplayed()){
                    ele.sendKeys(commentText);
                }
            }
            List<WebElement> posts = driver.findElements(By.xpath("//div[starts-with(@class,'post-comment')]"));
            for(WebElement post : posts)
            {
                if(post.isDisplayed())
                {
                    post.click();
                }
            }
        } catch (InterruptedException e) {
            Assert.fail("Exception in app helper commentOnPostWithStaticText", e);
        }

    }
}
