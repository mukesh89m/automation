package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.CommentOnPost;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;

public class LikeUnlikeCommentCourseStream {
	
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.LikeUnlikeCommentCourseStream");
	@Test
	public void likeUnlikeCommentCourseStream()
	{
		try
		{
			String randomstring = new RandomString().randomstring(5);
			logger.log(Level.INFO,"TestCase likeUnlikeCommentCourseStream under class LikeUnlikeCommentCourseStream Execution Started");
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");			
			new PostMessage().postmessage(randomstring);
			boolean posted = new PostMessageValidate().postMessageValidate(randomstring);
			if(posted == false) Assert.fail("Post not Posted");
			/*
			 * 345
			 */
			Driver.driver.findElement(By.className("ls-post-like-link")).click();
			String likecount = Driver.driver.findElement(By.className("ls-post-like-count")).getText();
			if(!likecount.equals("1"))
				Assert.fail("Like count of the post not equal to 1");
			/*
			 * 346
			 */
			String likelinkafterclicking = Driver.driver.findElement(By.className("ls-post-like-link")).getText();
			if(!likelinkafterclicking.equals("Unlike"))
				Assert.fail("Like link not converted to unlike after clicking on it");
			
			/*
			 * 348
			 */
			Driver.driver.findElement(By.className("ls-post-like-link")).click();
			likecount = Driver.driver.findElement(By.className("ls-post-like-count")).getText();
			if(!likecount.equals("0"))
				Assert.fail("Like count not updated to 0 after clicking on unlike");

			/*
			 * 349
			 */
			String unlikelikelinkafterclicking = Driver.driver.findElement(By.className("ls-post-like-link")).getText();
			if(!unlikelikelinkafterclicking.equals("Like"))
				Assert.fail("UnLike link not converted to Like after clicking on it");

			/*
			 * 350 351
			 */
			//Posting Ist Comment
			new CommentOnPost().commentOnPost(randomstring, 0);


			String comment1text = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",Driver.driver.findElement(By.cssSelector("div[class='ls-stream-post__comment-text']")));
			if(!comment1text.contains(randomstring))
				Assert.fail("Ist Comment Not Posted");
			/*
			 * 352
			 */
			//Posting IInd Comment 
			String randomstring2 = new RandomString().randomstring(5);
			new CommentOnPost().commentOnPost(randomstring2, 0);
			List<WebElement> comments = Driver.driver.findElements(By.cssSelector("div[class='ls-stream-post__comment-text']"));
			if(!comments.get(1).getText().contains(randomstring2))
				Assert.fail("IInd Comment Not Posted");

			//Posting IIIrd Comment
			String randomstring3 = new RandomString().randomstring(5);
			new CommentOnPost().commentOnPost(randomstring3, 0);
			/*
			 * 353	
			 */
			Driver.driver.navigate().refresh();

            Driver.driver.findElement(By.cssSelector("a[title='Comments']")).click();
			String viewallcommentslinktext = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",Driver.driver.findElement(By.className("ls-stream-post__view-comments")));
			if(!viewallcommentslinktext.contains("View all comments"))
				Assert.fail("A link 'View all comments' not appearing if there are more than 2 comments.");
			/*
			 * 354		
			 */
			Driver.driver.findElement(By.cssSelector("i[class='ls-arrow ls-arrow--down']")).click();
			Thread.sleep(2000);
			String hideallcommentslinktext = Driver.driver.findElement(By.className("ls-stream-post__view-comments")).getText();

			if(!hideallcommentslinktext.contains("Hide all comments"))
				Assert.fail("View All Comments Link not getting changed to Hide All Comments after clicking on View All Comments");	
			/*
			 * 357
			 */
			Driver.driver.findElement(By.cssSelector("i[class='ls-arrow ls-arrow--up']")).click();
			String viewallcommentslinktextagain = Driver.driver.findElement(By.className("ls-stream-post__view-comments")).getText();
			Thread.sleep(2000);
			if(!viewallcommentslinktextagain.contains("View all comments"))
				Assert.fail("Hide All Comments link not getting changed to View All Comments after clicking on Hide All Comments");	

		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase likeUnlikeCommentCourseStream in class LikeUnlikeCommentCourseStream",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}

}
