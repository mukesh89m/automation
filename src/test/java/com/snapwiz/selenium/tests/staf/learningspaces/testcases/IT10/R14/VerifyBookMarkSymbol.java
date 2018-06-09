package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class VerifyBookMarkSymbol extends Driver
{
	@Test
	public void verifyBookMarkSymbol()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1945");
			new Navigator().NavigateTo("Resources");
			//CHECKING IF STAR ICON FOR BOOKMARK IS PRESENT OR NOT
			int bookmarkSize = driver.findElements(By.cssSelector("i[class='addThisToMyResources not-bookmarked ls-resource-bookmark-icon']")).size();
			if(bookmarkSize == 0)
			{
				Assert.fail("Bookmark symbol is not present");
			}
			String bookmark = driver.findElement(By.cssSelector("i[class='addThisToMyResources not-bookmarked ls-resource-bookmark-icon']")).getAttribute("title");
			if(!bookmark.contains("Add to My Resources"))
			{
				Assert.fail("Bookmark symbol is not in grey color.");
			}
			//CLICK TO BOOKMARK
			List<WebElement> allBookmark = driver.findElements(By.xpath("//*[starts-with(@class, 'addThisToMyResources')]"));
			allBookmark.get(0).click();
			String bookmark1 = driver.findElement(By.xpath("//i[starts-with(@id,'addThisToMyResources')]")).getCssValue("background-image");
			if(!bookmark1.contains("sprite.png"))
			{
				Assert.fail("Bookmark symbol is not in yellow color when clicked on the star icon.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifyBookMarkSymbol in class VerifyBookMarkSymbol", e);
		}
	}

}
