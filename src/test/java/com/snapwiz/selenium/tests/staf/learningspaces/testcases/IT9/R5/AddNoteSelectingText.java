package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R5;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;


public class AddNoteSelectingText extends Driver {
	@Test(priority=1,enabled=true)
	public void addNoteSelectingTextBlue()
	{
		try
		{
			String blue = ReadTestData.readDataByTagName("AddNoteSelectingText", "blue", "962");
			String paragraphid = ReadTestData.readDataByTagName("AddNoteSelectingText", "paragraphid", "962");
			new LoginUsingLTI().ltiLogin("962");
			new Navigator().NavigateTo("eTextBook");
            new TOCShow().tocHide();
		    new TextSelectActions().addNote(blue, paragraphid);
			new HighLight().validateHightLight(blue);
			
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TC addNoteSelectingTextBlue inside class AddNoteSelectingText",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void addNoteOrange()
	{
		try
		{
			String orange = ReadTestData.readDataByTagName("", "orange", "949");
			new LoginUsingLTI().ltiLogin("949");
			new Navigator().NavigateTo("eTextBook");
            new TOCShow().tocHide();
		    new TextSelectActions().addNote(orange, "");
			new HighLight().validateHightLight(orange);
			
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TC addNoteOrange in class SocialAnnotationsOnLessonContent",e);
		}
	}
	
	@Test(priority=3,enabled=true)
	public void addNoteSelectingTextGreen()
	{
		try
		{
			String green = ReadTestData.readDataByTagName("", "green", "963");
			new LoginUsingLTI().ltiLogin("963");
			new Navigator().NavigateTo("eTextBook");
            new TOCShow().tocHide();
		    new TextSelectActions().addNote(green, "");
			new HighLight().validateHightLight(green);

		}
		catch(Exception e)
		{
			Assert.fail("Exception in TC addNoteSelectingTextGreen inside class AddNoteSelectingText",e);
		}
	}
	
	@Test(priority=4,enabled=true)
	public void accessingNoteFromHighligtedText()
	{
		try
		{
			String green = ReadTestData.readDataByTagName("", "green", "965");
			new LoginUsingLTI().ltiLogin("965");
			new Navigator().NavigateTo("eTextBook");
            new TOCShow().tocHide();
		    new TextSelectActions().addNote(green, "");
			new HighLight().validateHightLight(green);
			new HighLight().clickAndValidateHighlight("965");	
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TC accessingNoteFromHighligtedText inside class AddNoteSelectingText",e);
		}
	}
	
	@Test(priority=5,enabled=true)
	public void editingNoteText()
	{
		try
		{
			String green = ReadTestData.readDataByTagName("", "green", "969");
			new LoginUsingLTI().ltiLogin("969");
			new Navigator().NavigateTo("eTextBook");
            new TOCShow().tocHide();
		    new TextSelectActions().addNote(green, "");
			new HighLight().validateHightLight(green);
			new HighLight().clickAndValidateHighlight("969");
			new HighLight().clickAndEditNote();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TC editingNoteText inside class AddNoteSelectingText",e);
		}
	}

	@Test(priority=6,enabled=true)
	public void noteBoxValidateByOpeningNoteFromStream()
	{
		try
		{
			String green = ReadTestData.readDataByTagName("AddNoteSelectingText", "green", "1021");
			new LoginUsingLTI().ltiLogin("1021");
			new Navigator().NavigateTo("eTextBook");
            new TOCShow().tocHide();
		    new TextSelectActions().addNote(green, "");
			new HighLight().validateHightLight(green);
			new HighLight().NoteBoxOpenedByClickingNoteUnderStreamValidate();
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TC noteBoxValidateByOpeningNoteFromStream inside class AddNoteSelectingText",e);
		}
	}


}
