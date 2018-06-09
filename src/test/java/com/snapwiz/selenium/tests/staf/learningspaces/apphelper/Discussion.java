package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class Discussion extends Driver {
	public void postDiscussion(String discussionText)
	{
		try
		{
			Thread.sleep(3000);
			WebDriver driver=Driver.getWebDriver();
			//click on +New Discussion
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='ls-toc-btn ls-right-new-btn']")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[starts-with(@id,'editdiscussion-text')]")));
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[starts-with(@id,'editdiscussion-text')]")).clear();
			driver.findElement(By.xpath("//div[starts-with(@id,'editdiscussion-text')]")).sendKeys(discussionText);
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")));//click submit
			Thread.sleep(2000);
			String discussion = driver.findElement(By.cssSelector(".ls-right-user-post-body>b")).getText().trim();
			if(!discussion.contains(discussionText))
				CustomAssert.fail("Verify Discussion","The discussion has not been posted.");

		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method postDiscussion", e);
		}
	}

	public void postNote(String noteText)
	{
		try
		{
			//click on NEW
			WebDriver driver=Driver.getWebDriver();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[data-id='fav']")));
			new UIElement().waitAndFindElement(By.linkText("+ New Note"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("+ New Note")));
			new UIElement().waitAndFindElement(By.xpath("//div[starts-with(@id,'editnote-text')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[starts-with(@id,'editnote-text')]")));
			new UIElement().waitAndFindElement(By.xpath("//div[starts-with(@id,'editnote-text')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[starts-with(@id,'editnote-text')]")));
			new UIElement().waitAndFindElement(By.xpath("//div[starts-with(@id,'editnote-text')]"));
			driver.findElement(By.xpath("//div[starts-with(@id,'editnote-text')]")).clear();
			driver.findElement(By.xpath("//div[starts-with(@id,'editnote-text')]")).sendKeys(noteText);
			new UIElement().waitAndFindElement(By.xpath("//button[text()='Submit']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button[aria-label='Submit Note']")));
			Thread.sleep(2000);
			boolean notepresent = false;
			List<WebElement> notes = driver.findElements(By.className("star-content"));
			for(WebElement note : notes)
			{
				if(note.getText().contains(noteText)) {
					notepresent = true;
					break;
				}
			}
			if(notepresent == false)
				Assert.fail("The note has not been posted.");

		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method postNote", e);
		}
	}

	public void deleteNote()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[data-id='fav']")));
			Thread.sleep(2000);
			int index=0;
			List<WebElement> boxes = driver.findElements(By.className("ls-right-user-content"));
			for(WebElement box:boxes)
			{
				if(!box.getText().contains("You posted a note"))
				{
					index++;
				}
				else
					break;
			}
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElements(By.className("ls-right-user-post-body")).get(index));
			new UIElement().waitAndFindElement(By.cssSelector("div.ls-user-posted-note-edit"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div.ls-user-posted-note-edit")));
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editnote-button remove-annotation']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='editnote-button remove-annotation']")));
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method deleteNote", e);
		}
	}
	public void deleteDiscussion()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("a[class='ls-right-user-name ellipsis']")));


			new UIElement().waitAndFindElement(By.linkText("Edit"));
			WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.linkText("Edit")));
			new UIElement().waitAndFindElement(By.cssSelector("div[class='remove-annotation']"));
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("div[class='remove-annotation']")));
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method deleteDiscussion", e);
		}
	}

	public void deletePost(int index)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> toggle = driver.findElements(By.className("ls-dropdown__toggle"));
			toggle.get(index).click(); //click on the toggle

			WebElement remove = driver.findElement(By.linkText("Report Abuse"));
			if(remove.isDisplayed())
			{
				remove.click();
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method deletePost", e);
		}
	}

	public void addDiscussionAfterHighlightingText(String discussionText)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			new CloseHelpPage().closehelppage();
			WebElement element = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p[class='nonindent default']")));
			System.out.println(element.getLocation().getX());
			System.out.println(element.getLocation().getY());

			Actions actions = new Actions(driver);
			if(Config.browser.equals("chrome"))
			{
				actions.moveToElement(element, 0, 0)
						.doubleClick()
						.release()
						.perform();
			}
			else
			{
				actions.moveToElement(element)
						.clickAndHold()
						.moveByOffset(160, 165)
						.release()
						.perform();
			}
			List<WebElement> allColors = driver.findElements(By.xpath("//*[starts-with(@class, 'pcolor discussioncolor')]"));
			Thread.sleep(2000);
			allColors.get(0).click();
			new UIElement().waitAndFindElement(By.xpath("//div[starts-with(@id,'editdiscussion-text')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[starts-with(@id,'editdiscussion-text')]")));
			driver.findElement(By.xpath("//div[starts-with(@id,'editdiscussion-text')]")).clear();
			driver.findElement(By.xpath("//div[starts-with(@id,'editdiscussion-text')]")).sendKeys(discussionText);
			new UIElement().waitAndFindElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']"));
			driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click();//click submit
            Thread.sleep(5000);
			new UIElement().waitAndFindElement(By.className("ls-right-user-post-body"));
			String discussion = driver.findElement(By.className("ls-right-user-post-body")).getText();
			if(!discussion.equals(discussionText))
				Assert.fail("The discussion has not been posted.");

		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method addDiscussionAfterHighlightingText", e);
		}
	}

	public void highlightingText()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			new CloseHelpPage().closehelppage();
			WebElement element = (new WebDriverWait(driver,50)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p[class='nonindent default']")));
			Actions actions = new Actions(driver);
			actions.moveToElement(element)
					.clickAndHold()
					.moveByOffset(160, 165)
					.release()
					.perform();

		}
		catch(Exception e)
		{
			Assert.fail("Exception in appHelper Discussion in method HighlightingText", e);
		}
	}

	public void editDiscussion(String editedText)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-dialog-entry-edit")));
			new UIElement().waitAndFindElement(By.xpath("//div[starts-with(@id,'editdiscussion-text')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[starts-with(@id,'editdiscussion-text')]")));
			driver.findElement(By.xpath("//div[starts-with(@id,'editdiscussion-text')]")).clear();
			driver.findElement(By.xpath("//div[starts-with(@id,'editdiscussion-text')]")).sendKeys(editedText);
			new UIElement().waitAndFindElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']"));
			driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click();//click submit
            Thread.sleep(5000);
			new WebDriverWait(driver,200).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-right-user-post-body")));

			String discussion = driver.findElement(By.className("ls-right-user-post-body")).getText();
			System.out.println("editedText:"+editedText);
			System.out.println("discussion:"+discussion);
			if(!discussion.equals(editedText)){

				Assert.fail("The edited discussion has not been posted.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method editDiscussion", e);
		}
	}


	public void addNoteAfterHighlightingText(String noteText, String color)
	{
		try
		{
			Thread.sleep(8000);//sleep added so that the help pop-up disappears
			WebDriver driver=Driver.getWebDriver();
			WebElement element = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p[class='nonindent default']")));
			Actions actions = new Actions(driver);
			if(Config.browser.equals("chrome"))
			{
				actions.moveToElement(element, 0, 0)
						.doubleClick()
						.release()
						.perform();
			}
			else
			{
				actions.moveToElement(element)
						.clickAndHold()
						.moveByOffset(150, 160)
						.release()
						.perform();
			}
			Thread.sleep(5000);
			List<WebElement> allColors = driver.findElements(By.xpath("//*[starts-with(@class, 'pcolor notecolor')]"));
			Thread.sleep(2000);
			if(color.equalsIgnoreCase("yellow"))
				WebDriverUtil.clickOnElementUsingJavascript(allColors.get(0));
			if(color.equalsIgnoreCase("blue"))
				WebDriverUtil.clickOnElementUsingJavascript(allColors.get(1));
			if(color.equalsIgnoreCase("green"))
				WebDriverUtil.clickOnElementUsingJavascript(allColors.get(2));
			if(color.equalsIgnoreCase("red"))
				WebDriverUtil.clickOnElementUsingJavascript(allColors.get(3));
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editnote-text ']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='editnote-text ']")));
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editnote-text ']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='editnote-text ']")));
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editnote-text ']"));
			driver.findElement(By.cssSelector("div[class='editnote-text ']")).clear();
			driver.findElement(By.cssSelector("div[class='editnote-text ']")).sendKeys(noteText);
			new UIElement().waitAndFindElement(By.cssSelector("button[class='editnote-button editnote-submit']"));
			driver.findElement(By.cssSelector("button[class='editnote-button editnote-submit']")).click();//click submit
			new UIElement().waitAndFindElement(By.cssSelector("div[data-id='fav']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[data-id='fav']")));
			Thread.sleep(2000);
			boolean notepresent = false;
			List<WebElement> notes = driver.findElements(By.className("ls-right-user-post-body"));
			for(WebElement note : notes)
			{
				if(note.getText().equals(noteText)) {
					notepresent = true;
					break;
				}
			}
			if(notepresent == false)
				Assert.fail("The note has not been posted.");


		}
		catch(Exception e)
		{
				Assert.fail("Exception in aphhelper Discussion in method addNoteAfterHighlightingText", e);
		}
	}

	public void editNote(String editedNote)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("ls-user-posted-note-edit")));
			driver.findElement(By.className("ls-user-posted-note-edit")).click();//click on edit
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editnote-text edit-mode-note']"));
			driver.findElement(By.cssSelector("div[class='editnote-text edit-mode-note']")).clear();
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editnote-text edit-mode-note']"));
			driver.findElement(By.cssSelector("div[class='editnote-text edit-mode-note']")).sendKeys(editedNote);
			new UIElement().waitAndFindElement(By.cssSelector("button[class='editnote-button editnote-submit']"));
			driver.findElement(By.cssSelector("button[class='editnote-button editnote-submit']")).click();//click submit
			Thread.sleep(3000);
			new UIElement().waitAndFindElement(By.className("ls-right-user-post-body"));
			String note = driver.findElement(By.className("ls-right-user-post-body")).getText();

			if(!note.equals(editedNote)){
				Assert.fail("The note has not been edited.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method editNote", e);
		}
	}


	public void commentOnDiscussion(int index, String comment)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> allElement = driver.findElements(By.className("stream-content-posts-list"));
			allElement.get(index).click();
			new UIElement().waitAndFindElement(By.cssSelector("a.right-post-comment.active"));
			driver.findElement(By.cssSelector("a.right-post-comment.active")).click();
			Thread.sleep(500);
			driver.switchTo().activeElement().sendKeys(comment);
			driver.switchTo().activeElement().sendKeys(Keys.RETURN);
			Thread.sleep(2000);
			List<WebElement> allComments = driver.findElements(By.xpath("//*[starts-with(@class, 'ls-comments-text')]"));
			if(!allComments.get(allComments.size()-1).getText().contains(comment))
			{
				Assert.fail("Comment for the discusion not posted.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method commentOnDiscussion", e);
		}
	}
	public void likeDiscussion(int index, String comment)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> allElement = driver.findElements(By.className("stream-content-posts-list"));
			allElement.get(index).click();
			new UIElement().waitAndFindElement(By.className("right-post-like"));
			new Click().clickByclassname("right-post-like"); //click on like
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method likeDiscussion", e);
		}
	}

	public void addDiscussioninLessonText(String discussionText)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			WebElement element = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p[class='nonindent default']")));
			Actions actions = new Actions(driver);
			if(Config.browser.equals("chrome"))
			{
				actions.moveToElement(element, 0, 0)
						.doubleClick()
						.release()
						.perform();
			}
			else
			{
				actions.moveToElement(element, 60, 60)
						.clickAndHold()
						.moveByOffset(150, 150)
						.release()
						.perform();
			}
			List<WebElement> allColors = driver.findElements(By.xpath("//*[starts-with(@class, 'pcolor discussioncolor')]"));
			allColors.get(0).click();
			new UIElement().waitAndFindElement(By.cssSelector("div[id='editdiscussion-textINTERIM']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[id='editdiscussion-textINTERIM']")));
			new UIElement().waitAndFindElement(By.cssSelector("div[id='editdiscussion-textINTERIM']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[id='editdiscussion-textINTERIM']")));
			driver.findElement(By.cssSelector("div[id='editdiscussion-textINTERIM']")).clear();
			driver.findElement(By.cssSelector("div[id='editdiscussion-textINTERIM']")).sendKeys(discussionText);
			new UIElement().waitAndFindElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']"));
			driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click();//click submit
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method addDiscussioninLessonText", e);
		}
	}

	public void DiscussionAndNotesBookmarkedValidation(String randomtext,boolean questioncard)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			if(questioncard==true)
			{
				new Click().clickbylist("question-card-label", 0);//clikc on question card
			}
			new Navigator().navigateToTab("Discussion");//navigate to discussion tab
			new Discussion().postDiscussion(randomtext);
			new Navigator().navigateToTab("Fav");//navigater to favrouite tab
			boolean thumnailUnderFavTab=new BooleanValue().booleanbyclass("ls-right-user-img");//verify thumnail under fav tab
			if(thumnailUnderFavTab==true)
				Assert.fail("thumnail shown for discussion under fav tab");
			new Navigator().navigateToTab("Discussion");//navigate to discussion tab
			Thread.sleep(2000);
			new Click().clickbylist("ls-right-user-content",0);//clikc on discussion posted
			new Click().clickbylistcssselector("i[class='ls-right-section-sprites ls--right-star-icon']", 0);//unbookmark the discussion
			new Navigator().navigateToTab("Fav");//navigater to favrouite tab
			boolean discussionAfterunbookmark=new BooleanValue().booleanbyclass("ls-right-user-content");//verify thumnail under fav tab
			if(discussionAfterunbookmark==true)
				Assert.fail("discussion shown under fav tab after unbookmark");
			new Navigator().navigateToTab("Discussion");//navigate to discussion tab
			new UIElement().waitAndFindElement(By.className("ls-right-user-post-body"));
			new Click().clickByclassname("ls-right-user-post-body");//click on added discussion
			new Click().clickByclassname("ls-dialog-entry-edit");//click on edit icon
			new Click().clickByclassname("remove-annotation");//delete the post
			new Navigator().navigateToTab("Fav");//navigater to favrouite tab

			new Discussion().postNote(randomtext);//post notes
			new Click().clickbylist("stream-content-posts-list", 0);
			new Click().clickByclassname("ls-user-posted-note-edit");//click on edit link for notes
			//38
			new Click().clickBycssselector("div[class='editnote-button remove-annotation']");//click,on delete icon
			int notesAfterDelete=driver.findElements(By.className("ls-right-user-post-body")).size();
			if(notesAfterDelete != 0)
				Assert.fail("after unbookmark the highlighted text its not remove form star tab");
		}
		catch(Exception e)
		{
			Assert.fail("Excpetion in app helper DiscussionAndNotesBookmarkedValidation",e);
		}
	}
	//Author Sumit
	//checks whether a note od discussion is present under a particular tab
	public boolean isDiscussionOrNotePresentUnderTabs(String tabName, String discussionOrNote)
	{
		boolean isPresent = false;
		WebDriver driver=Driver.getWebDriver();
		try
		{
			Thread.sleep(2000);
			new Navigator().navigateToTab(tabName);

			List<WebElement> discussion;
			discussion = driver.findElements(By.className("ls-right-user-post-body"));
			for(WebElement dis:discussion)
			{
				new UIElement().waitAndFindElement(dis);
				if(dis.getText().equals(discussionOrNote))
				{
					isPresent = true;
					break;
				}
			}
		}
		catch (Exception e)
		{
			Assert.fail("Exception in app helper isDiscussionOrNotePresentUnderTabs in class Discussion",e);
		}
		return isPresent;
	}

	public void addDiscussionInQuestionReviewPage()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			driver.findElement(By.cssSelector("span[title='Discussion']")).click(); // Click on Discussion Tab
			Thread.sleep(4000);
			driver.findElement(By.cssSelector("a[class='ls-toc-btn ls-right-new-btn']")).click(); // Click on New Discussion
			String discussionText = new RandomString().randomstring(5);
			driver.findElement(By.cssSelector("div[class='editdiscussion-text redactor_editor redactor-editor-focused']")).click();
			driver.findElement(By.cssSelector("div[class='editdiscussion-text redactor_editor redactor-editor-focused']")).sendKeys(discussionText);
			driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click(); // Click on Submit
			Thread.sleep(3000);
			String discussionStatus = driver.findElement(By.xpath("(.//*[@class='stream-content-posts-list']//ul[@class='ls-right-post-status']//li)[3]")).getText();
			System.out.println("discussionStatus::"+discussionStatus);
			if(!discussionStatus.trim().equals("A few seconds ago"))
			{
				Assert.fail("Discussion not posted successfully");
			}
		}
		catch (Exception e)
		{
			Assert.fail("Exception in app helper addDiscussionInQuestionReviewPage in class Discussion",e);
		}
	}
}
