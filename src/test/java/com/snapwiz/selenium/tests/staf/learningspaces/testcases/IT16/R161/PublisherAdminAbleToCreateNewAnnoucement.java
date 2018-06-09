package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R161;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class PublisherAdminAbleToCreateNewAnnoucement extends Driver
{
	@Test
	public void publisherAdminAbleToCreateNewAnnoucement()
	{
		try
		{

			new DirectLogin().publisherAdmin();
			//7
            new Navigator().publisherAdminNavigateTo("Announcements");
			new Click().clickByclassname("pa-add-announcements-button");//--3-click on add new announcement button
			//8-13
			String popText=new TextFetch().textfetchbyclass("pa-announcement-dialogBox");//fetch text of pop up
			if(!popText.contains("Create new announcement"))
				Assert.fail("pop up header is not 'Create new announcement'");
			if(!popText.contains("Your Current Time"))
				Assert.fail("time not shown ");
			if(!popText.contains("Announcement Description*"))
				Assert.fail("pop up not content text 'Announcement Description*'");
			if(!popText.contains("Start Date"))
				Assert.fail("pop up not content text 'Start Date'");
			if(!popText.contains("End Date"))
				Assert.fail("pop up  not content text 'End Date'");
			if(!popText.contains("Cancel"))
				Assert.fail("pop up  not content text 'Cancel'");
			if(!popText.contains("Save"))
				Assert.fail("pop up  not content text 'Save'");
			if(!popText.contains("Status"))
				Assert.fail("pop up  not content text 'Status'");
			//14
			new Click().clickByclassname("pa-dialogBox-Cancel");//click on cancle link
			int popclosed=driver.findElements(By.className("pa-announcement-dialogBox")).size();
			if(popclosed!=0)
				Assert.fail("after click on cancle pop up not closed");
			new Click().clickByclassname("pa-add-announcements-button");//---click on add new announcement button
			new Click().clickBycssselector("span[class='pa-dialogBox-Save pa-resource-save-btn']");//click on save button
			String errorMsg=new TextFetch().textfetchbyclass("pa-anouncement-errormessage-lable");
			if(!errorMsg.contains("Please provide a description."))
				Assert.fail("error message not shown when click on save with out give any things in description");
			driver.findElement(By.className("pa-anouncement-textbox")).clear();
			driver.findElement(By.className("pa-anouncement-textbox")).sendKeys("descrption");//put description
			new Click().clickBycssselector("span[class='pa-dialogBox-Save pa-resource-save-btn']");//click on save button
			String errorMsgEndDate=new TextFetch().textfetchbyclass("pa-anouncement-errormessage-lable");
			if(!errorMsgEndDate.contains("Please select end date."))
				Assert.fail("error message not shown when click on save with out select end date");
			driver.findElement(By.xpath("//*[@id='pa_assignment_end_date']")).click();
			Thread.sleep(2000);
			new Click().clickBycssselector("a[data-handler='next']");//clcik for next months
			new Click().clickbylinkText("22");//select end date
			new Click().clickBycssselector("span[class='pa-dialogBox-Save pa-resource-save-btn']");//click on save button
			String errorMsgwithouttime=new TextFetch().textfetchbyclass("pa-anouncement-errormessage-lable");
			if(!errorMsgwithouttime.contains("Please select end time."))
				Assert.fail("error message not shown when click on save with out select end time");
			
			driver.findElement(By.xpath("//*[@id='pa_assignment_end_time']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@class='ui-timepicker-list']/li[2]")).click();//select time
			driver.findElement(By.xpath("//*[@id='pa_assignment_end_date']")).click();
			Thread.sleep(2000);
			new Click().clickBycssselector("a[data-handler='prev']");//clcik for previous month 
			new Click().clickBycssselector("a[data-handler='prev']");//clcik for previous months
			new Click().clickbylinkText("22");//select end date
			new Click().clickBycssselector("span[class='pa-dialogBox-Save pa-resource-save-btn']");//click on save button
			String errorMsgInvalidDate=new TextFetch().textfetchbyclass("pa-anouncement-errormessage-lable");
			if(!errorMsgInvalidDate.contains("End date must be a future date."))
				Assert.fail("error message not shown when click on save with select previous(invalid) date");
		} 
		catch(Exception e)
		{
			Assert.fail("Exception in class PublisherAdminAbleToCreateNewAnnoucement in method publisherAdminAbleToCreateNewAnnoucement",e);
		}
	}


}
