package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R143;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AddNote;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.HelpText;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class StudentAbleToAddNewNoteAndAttachedaFile extends Driver
{
	@Test(priority=1,enabled=true)
	public void studentAbleToAddNewNoteAndAttachedaFile()
	{
		try
		{
			String filename =  ReadTestData.readDataByTagName("", "filename", "1");
			String filename1 =  ReadTestData.readDataByTagName("", "filename1", "1");
			new LoginUsingLTI().ltiLogin("1");//login as student
			//2-9
			new AddNote().openNewNotepopup();//open "add note" pop-up
			//10--UI element of note box
			String noteboxtext=new TextFetch().textfetchbycssselector("div[class='ls-ins-uploadResource-dialogBox add-study-note-dialog-box']");
			if(!noteboxtext.contains("Create new note"))
				Assert.fail("header of pop-up is not 'Create new note'");
			if(!noteboxtext.contains("Notes *"))
				Assert.fail("note text box lable  not present with * '");
			if(!noteboxtext.contains("Upload File"))
				Assert.fail("upload file link not present");
			if(!noteboxtext.contains("Associate Notes to"))
				Assert.fail("assosciate note with chaper not present");
			if(!noteboxtext.contains("Cancel"))
				Assert.fail("cancle link not present at bottom");
			if(!noteboxtext.contains("Save"))
				Assert.fail("save button not present at bottom");
			boolean closeicon=new BooleanValue().booleanbyclass("ins-dialogClose");
			if(closeicon==false)
				Assert.fail("cross icon not present on pop-up");
			//11----default text of add note text filed
			String defaulttextofnotebox=driver.findElement(By.className("ins-uploadResource-textbox")).getAttribute("placeholder");
			if(!defaulttextofnotebox.contains("Add a note.."))
				Assert.fail("default text not contains 'Add a note...'in note box field");
			new Click().clickBycssselector("span[class='ins-dialogBox-Save ins-resource-save-btn']");
			//13,14
			String errortext=new TextFetch().textfetchbyclass("mandatory-note-error");//verify note box filed is mandatry
			if(!errortext.contains("Please provide a note"))
				Assert.fail("error text not shown when click on save without give note");
			//17,15
			new Click().clickByid("uploadFile");
			new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
			//16
			Thread.sleep(1000);
			boolean progressbar=new BooleanValue().booleanbyid("fileupload-progress-bar");//verify progress bar
			if(progressbar==false)
				Assert.fail("upload file progress abr not display");
			Thread.sleep(5000);
			//23
			new Click().clickByclassname("ins-deleteFileIcon");//click on delete item
			Thread.sleep(3000);
			//24
			new Click().clickByid("uploadFile");
			new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename1+"^");//try to upload more than 10 MB file
			Thread.sleep(1000);
			//18
			boolean errorpopup=new BooleanValue().booleanbyclass("notification-message-header");//verify error pop-up when more than  10MB file uploaded
			if(errorpopup==false)
				Assert.fail("when try to upload more than 10 mb file error message not shown");
			//25,26
			String helpdata=new HelpText().helpText(0);//fetch help text data
			if(!helpdata.contains("where this note should be associated."))
				Assert.fail("help text not shown after click on '?' symbol");
			//26-54
			new Click().clickbylist("expand-chapter-tree", 0);//click on '+' symbol for expand the chapter
			boolean topicnode=new BooleanValue().booleanbylistcssselector("div[class='expand-topic-tree expand']", 1);
			if(topicnode==false)
				Assert.fail("topic node not shown after expand chapter");
			new Click().clickbylistcssselector("div[class='expand-chapter-tree expand']", 0);//click on '-' symbol for collapse the chapter
			//35
			String assosciatedchapter=new TextFetch().textfetchbyclass("ins-associated-lesson");//associate chapter text
			if(!assosciatedchapter.contains("You have selected Ch"))
				Assert.fail("after select the chapter associated message not shown on botton to which chapter associted with note");
			new Click().clickByclassname("ins-dialogBox-Cancel");
			//55
			int notebox=driver.findElements(By.className("ins-dialogBox-UploadSection")).size();//verify after click on cancle note add box close or not
			if(notebox!=0)
				Assert.fail("after click on cancel note pop-box not closed");
			//57
			new AddNote().addNote("1", false,true,0);//uploaded resources without select any chapter
			String notifiaction=new TextFetch().textfetchbyclass("ls-notification-text-span");
			if(!notifiaction.contains("You have to choose a node to which this note will be associated before you save."))
				Assert.fail("no notification shown when we try to save the note without select any chapter or topic or course");
			//56,58
			new Click().clickByclassname("ins-dialogClose");//close note box pop-up
			int postednote=driver.findElements(By.cssSelector("div[class='my-journal-card posted-note']")).size();//verify any note added or not
			if(postednote!=0)
				Assert.fail("note added in \"My Notes\" page with out save or without associated with some chapter");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase StudentAbleToAddNewNoteAndAttachedaFile in  method studentAbleToAddNewNoteAndAttachedaFile",e);
		}
	}
	@Test(priority=2,enabled=false)
	public void AddNoteForInstructor()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1_1");//login as instructor
			new Click().clickBycssselector("a[class='navigator ls-toc-sprite']");
			//4
			int Mynote=driver.findElements(By.className("ls--my-notes")).size();
			if(Mynote!=0)
				Assert.fail("for instructor \"my note\" option present in navigation dropdown");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase AddNoteForInstructor",e);
		}
	}


}
