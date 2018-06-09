package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;
/*
 * @Brajesh
 * added Note to "My Notes" page
 */
public class AddNote extends Driver
{
	public void addNote(String dataIndex,boolean associatetochapter,boolean fileupload,int chapter)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			String filename =  ReadTestData.readDataByTagName("", "filename", dataIndex);
			String note =  ReadTestData.readDataByTagName("", "note", dataIndex);
			new Navigator().NavigateTo("My Notes");
			new Click().clickByclassname("add-note-button");//click on add new button
			new Click().clickByclassname("ins-uploadResource-textbox");
			new TextSend().textsendbyclass(note, "ins-uploadResource-textbox");
			if(fileupload==true)
			{
				new Click().clickByid("uploadFile");
				new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
				Thread.sleep(10000);
			}
			
			if(associatetochapter==true)
			{
				new Click().clickbylistcssselector("div[class='course-chapter-label node']", chapter);
			}
			new Click().clickBycssselector("span[class='ins-dialogBox-Save ins-resource-save-btn']");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper addNote",e);
		}
	}
//open new note pop-up	
	public void openNewNotepopup()
	{
		try
		{			
			new Navigator().NavigateTo("My Notes");
			new Click().clickByclassname("add-note-button");//click on add new button
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper openNewNotepopup",e);
		}
	}
	//add tag with the note
	public void addTag(String tag,int index)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			new Click().clickbylist("add-tags-button",index);//click on add tag button
			List<WebElement> we=driver.findElements(By.xpath("//*[starts-with(@id, 'share-with-')]/input"));
			we.get(index).sendKeys(tag);//write tab name
			Thread.sleep(2000);
			we.get(index).sendKeys(Keys.ENTER);
			new Click().clickbylist("save-tags-button",index);//click on save button
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper addTag",e);
		}
	}
	//search added tag with node base on text
	public void searchtag(String tag)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			driver.findElement(By.xpath("//*[@id='tags-search_annoninput']/input")).click();//click onn search filed
			driver.findElement(By.xpath("//*[@id='tags-search_annoninput']/input")).sendKeys(tag);//send keys to search filed
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper searchtag",e);
		}
	}
	
	

}
