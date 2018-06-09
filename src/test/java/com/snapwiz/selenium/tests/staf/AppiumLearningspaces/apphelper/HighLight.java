package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Config;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;

public class HighLight {

	public void validateHightLight(String color)
	{
		try
		{
			color=color.toLowerCase();
			String colorhex,colorx,colory,colorz,rgbhiglighted=null;
			Thread.sleep(5000);

			WebElement highligted = Driver.driver.findElement(By.xpath("//*[starts-with(@class, 'highlighted annotation')]"));

			colorhex = highligted.getAttribute("style");

			if(colorhex.length()>10)
			{
				colorx= colorhex.substring(22, 25);
				colory= colorhex.substring(27,30);
				colorz = colorhex.substring(32, 35);

				rgbhiglighted = String.format("#%02x%02x%02x", Integer.parseInt(colorx), Integer.parseInt(colory), Integer.parseInt(colorz));

			}

			if(!rgbhiglighted.equals(color))
				Assert.fail("Selected Text Not highlighted with the desired color");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper HighLightValidate in method  validateHightLight",e);
		}
	}

	public void removeHighlight()
	{
		try
		{
			Thread.sleep(5000);

			WebElement highligted = Driver.driver.findElement(By.xpath("//*[starts-with(@class, 'highlighted annotation')]"));
			highligted.click();
			Thread.sleep(3000);
			WebElement removehighlight = Driver.driver.findElement(By.cssSelector("div[class='ptext remove-annotation']"));
			if(!removehighlight.getText().equals("Remove Highlight"))
				Assert.fail("Remove Highlight Option not present after clicking on a highlighted text.");
			removehighlight.click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper HighLightValidate in method removeHighlight",e);
		}
	}

	public void clickAndValidateHighlight(String tcIndex)
	{
		try
		{
			Thread.sleep(5000);
			String postedstring = ReadTestData.readDataByTagName("", "postedtext", tcIndex);
			WebElement highligted = Driver.driver.findElement(By.xpath("//*[starts-with(@class, 'highlighted annotation')]"));
			highligted.click();
			Thread.sleep(3000);
			WebElement notebox = Driver.driver.findElement(By.cssSelector("div[class='editnote-wrapper prow']"));
			if(!notebox.isDisplayed())
				Assert.fail("NoteBox with arrow pointing downwards not opened after clicking the text highlighted after adding a note/discussion");
			WebElement postedatext = Driver.driver.findElement(By.className("ls-user-posted-status"));
			System.out.println("Test Data "+postedstring);
			System.out.println(postedatext.getText().trim());
			if(!postedatext.getText().trim().equals("givenname family"))
				Assert.fail("User Name not present in NoteBox opened with arrow pointing downwards after clicking the text highlighted after adding a note/discussion");

			WebElement staricon = Driver.driver.findElement(By.cssSelector("i[class='ls-right-section-sprites ls--right-star-icon']"));
			if(!staricon.isDisplayed())
				Assert.fail("NoteBox with arrow pointing downwards opened after clicking the text highlighted after adding a note/discussion doesnot contain star icon after text posted a note/discussion.");

			String notetext = Driver.driver.findElement(By.className("editnote-text")).getText().trim();
			if(!notetext.equals(TextSelectActions.noterandomstring))
				Assert.fail("The note added does not match with the note present in the NoteBox opened after clicking the text highlighted by adding a note/discussion");



		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper HighLightValidate in method clickAndValidateHighlight",e);
		}
	}

	public void clickAndValidateDiscussion(String tcIndex)
	{
		try
		{
			Thread.sleep(5000);
			String postedstring = ReadTestData.readDataByTagName("", "postedtext", tcIndex);
			WebElement highligted = Driver.driver.findElement(By.xpath("//*[starts-with(@class, 'highlighted annotation')]"));
			highligted.click();
			Thread.sleep(3000);
			WebElement notebox = Driver.driver.findElement(By.cssSelector("div[class='prow editdiscussion-wrapper']"));
			if(!notebox.isDisplayed())
				Assert.fail("NoteBox with arrow pointing downwards not opened after clicking the text highlighted after adding a discussion");
			WebElement postedatext = Driver.driver.findElement(By.className("ls-user-posted-status"));
			System.out.println("Test Data "+postedstring);
			System.out.println(postedatext.getText().trim());
			if(!postedatext.getText().trim().equals("givenname family"))
				Assert.fail("User Name NOT present in NoteBox opened with arrow pointing downwards after clicking the text highlighted after adding a discussion");

			WebElement staricon = Driver.driver.findElement(By.cssSelector("i[class='ls-right-section-sprites ls--right-star-icon']"));
			if(!staricon.isDisplayed())
				Assert.fail("NoteBox with arrow pointing downwards opened after clicking the text highlighted after adding a discussion doesnot contain star icon after user name.");

			String notetext = Driver.driver.findElement(By.className("edit-content-body")).getText();
			if(!notetext.trim().equals(TextSelectActions.discussionstring))
				Assert.fail("The discussion added does not match with the discussion present in the DiscussionBox opened after clicking the text highlighted by adding a discussion");

			WebElement commentlink = Driver.driver.findElement(By.cssSelector("a[class='right-post-comment active']"));
			if(!commentlink.isDisplayed())
				Assert.fail("NoteBox with arrow pointing downwards opened after clicking the text highlighted after adding a discussion doesnot contain comment link.");

			WebElement age = Driver.driver.findElement(By.cssSelector("span[class='ls-stream-post-time']"));
			if(!age.isDisplayed())
				Assert.fail("NoteBox with arrow pointing downwards opened after clicking the text highlighted after adding a discussion doesnot contain age of the discussion");

		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper HighLightValidate in method clickAndValidateDiscussion",e);
		}
	}


	public void clickAndEditNote()
	{
		try
		{
			Thread.sleep(5000);
			WebElement highlighted = Driver.driver.findElement(By.xpath("//*[starts-with(@class, 'highlighted annotation')]"));
			highlighted.click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.className("ls-user-posted-note-edit")).click();
			Thread.sleep(2000);
			String random = new RandomString().randomstring(5);
			Driver.driver.findElement(By.className("editnote-text")).sendKeys(random+" ");
			Thread.sleep(2000);
			//Driver.driver.findElement(By.className("ls-new-btn-section")).click(); //Clicking outside in the right side New button section to save the note
			Driver.driver.findElement(By.cssSelector("button[class='editnote-button editnote-submit']")).click(); //Clicking on save button instead of clicking outside the notebox to save the edited note
			//new KeysSend().sendKeyBoardKeys("`^");
			Thread.sleep(3000);
			WebElement highlightedafteredit = Driver.driver.findElement(By.xpath("//*[starts-with(@class, 'highlighted annotation')]"));
			highlightedafteredit.click();
			String notetext = Driver.driver.findElement(By.className("editnote-text")).getText().trim();
			System.out.println("Note:" +notetext); System.out.println(notetext.equals(random+"  "+TextSelectActions.noterandomstring));
			if(Config.browser.equals("ie"))
			{
				if(!notetext.equals(random+"  "+TextSelectActions.noterandomstring))
					Assert.fail("The Note Text after editing is not as expected.");
			}
			else
			{
				if(!notetext.equals(random+" "+TextSelectActions.noterandomstring))
					Assert.fail("The Note Text after editing is not as expected.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper HighLightValidate in method clickAndEditNote",e);
		}
	}

	public void clickAndEditDiscussion()
	{
		try
		{
			Thread.sleep(5000);
			WebElement highlighted = Driver.driver.findElement(By.xpath("//*[starts-with(@class, 'highlighted annotation')]"));
			highlighted.click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.className("ls-dialog-entry-edit")).click();
			String random = new RandomString().randomstring(5);
			new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.KeysSend().sendKeyBoardKeys(random+" ");
			//Driver.driver.findElement(By.className("ls-new-btn-section")).click(); //Clicking outside in the right side New button section to save the discussion
			Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click(); //Clicking on save button instead of clicking outside the discussion to save the edited discussion
			//new KeysSend().sendKeyBoardKeys("`^");
			Thread.sleep(3000);
			WebElement highlightedafteredit = Driver.driver.findElement(By.xpath("//*[starts-with(@class, 'highlighted annotation')]"));
			highlightedafteredit.click();
			String notetext = Driver.driver.findElement(By.className("edit-content-body")).getText();
			if(!notetext.trim().equals(random+" "+TextSelectActions.discussionstring))
				Assert.fail("The Discussion Text after editing is not as expected.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper HighLightValidate in method clickAndEditDiscussion",e);
		}
	}

	public void validateDiscussionByAnotherUser()
	{
		try
		{
			Thread.sleep(5000);
			WebElement highlighted = Driver.driver.findElement(By.xpath("//*[starts-with(@class, 'highlighted annotation')]"));
			highlighted.click();
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(Driver.driver, 10);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.className("ls-dialog-entry-edit")));
			if(element !=null)
				Assert.fail("Edit option present for a discussion accessed by second student added by first student");

		}
		catch(Exception e)
		{

		}
	}

	public void DiscussionBoxOpenedByClickingDiscussionUnderStreamValidate()
	{
		try
		{
			Thread.sleep(3000);
			Driver.driver.findElement(By.className("stream-content-posts-list")).click();
			Thread.sleep(3000);
			if(!Driver.driver.findElement(By.cssSelector("div[class='prow editdiscussion-wrapper']")).isDisplayed())
				Assert.fail("Discussion box not getting expanded after clicking on the discussion in the stream view");
			Driver.driver.findElement(By.className("ls-dialog-entry-edit")).click();
			int commentlink = Driver.driver.findElements(By.cssSelector("a[class='right-post-comment active']")).size();
			if(commentlink == 0)
				Assert.fail("Discussion Box with arrow pointing downwards opened after clicking the discussion under streams doesnot contain comment link.");

		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper DiscussionBoxOpenedByClickingDiscussionUnderStreamValidate in class NewButtonActions",e);
		}
	}

	public void NoteBoxOpenedByClickingNoteUnderStreamValidate()
	{
		try
		{
			Thread.sleep(3000);
			Driver.driver.findElement(By.className("stream-content-posts-list")).click();
			Thread.sleep(3000);
			if(!Driver.driver.findElement(By.cssSelector("div[class='editnote-wrapper prow']")).isDisplayed())
				Assert.fail("Note box not getting expanded after clicking on the note in the stream view");
		/*Driver.driver.findElement(By.className("ls-user-posted-note-edit")).click();
		int commentlinkpresent = Driver.driver.findElements(By.cssSelector("a[class='right-post-comment active']")).size();
		if(commentlinkpresent == 0)
			Assert.fail("Note Box with arrow pointing downwards opened after clicking the note under streams doesnot contain comment link.");
		*/
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper NoteBoxOpenedByClickingNoteUnderStreamValidate in class NewButtonActions",e);
		}

	}
}
