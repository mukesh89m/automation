package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R143;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AddNote;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class NoteAddedByStudent extends Driver
{
	@Test
	public void noteAddedByStudent()
	{
		try
		{
			String filename=ReadTestData.readDataByTagName("", "filename", "59");
			new LoginUsingLTI().ltiLogin("59");
			new AddNote().addNote("59", true, true,0);
			Thread.sleep(3000);
			//64
			String uploadfilename=new TextFetch().textfetchbycssselector("div[class='journal-card-bottom-description study-note-attachment']");
			if(!uploadfilename.contains(filename))
				Assert.fail("attached file name not diaplay in added note");
			//73-76--associted chapter name
			String noteheader=new TextFetch().textfetchbyclass("journal-card-title");
			if(!noteheader.contains("Posted a note on Ch"))
				Assert.fail("note header not contains associate chapter name");
			//67-69
			new MouseHover().mouserhover("my-journal-activity-details");//mouse hover on added note
			new Click().clickByclassname("my-journal-trash-icon");//click onn delete icon
			new Click().clickByclassname("cancel-study-note-delete");//click on 'no' link
			//70
			new MouseHover().mouserhover("my-journal-activity-details");
			new Click().clickByclassname("my-journal-trash-icon");//click on delte icon
			new Click().clickByclassname("confirm-study-note-delete");//click on 'yes' link

			new Navigator().NavigateTo("My Notes");
			int postednote=driver.findElements(By.cssSelector("div[class='my-journal-card posted-note']")).size();//verify added note deleted or not
			if(postednote!=0)
				Assert.fail("note is stil available on the my note page even its deleted.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method noteAddedByStudent in test class NoteAddedByStudent.",e);
		}
	}


}
