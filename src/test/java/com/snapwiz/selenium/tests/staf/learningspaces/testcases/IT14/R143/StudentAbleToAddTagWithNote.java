package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R143;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AddNote;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class StudentAbleToAddTagWithNote extends Driver
{
	@Test
	public void studentAbleToAddTagWithNote()
	{
		try
		{
			String tag=ReadTestData.readDataByTagName("", "tag", "77");
			new LoginUsingLTI().ltiLogin("77");
			new AddNote().addNote("77", true, false,0);
			Thread.sleep(2000);
			//86-88,97-99
			new AddNote().addTag(tag, 0);
			boolean addedtag=new BooleanValue().booleanbyclass("bit-box");//verify after save tag is added or not
			if(addedtag==false)
				Assert.fail("tag not added with note");
			//89-90
			new Click().clickByclassname("edit-tag-icon");//click on edit tag icon
			//91-94
			new Click().clickByclassname("closebutton");//
			List<WebElement> alltag1=driver.findElements(By.className("maininput"));
			alltag1.get(0).sendKeys(tag);
			Thread.sleep(3000);
			//82,83
			driver.findElement(By.xpath("//*[starts-with(@id, 'share-with-')]/li[2]")).click(); //click on auto suggest tag name
			new Click().clickByclassname("save-tags-button");//click on save button
			boolean addedtag1=new BooleanValue().booleanbyclass("bit-box");//verify after save tag is added or not
			if(addedtag1==false)
				Assert.fail("tag not added with note which is added by auto suggestion box");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase class StudentAbleToAddTagWithNote in testmethod studentAbleToAddTagWithNote",e);
		}	
	}


}
