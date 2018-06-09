package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R161;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AddAnnouncement;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class PublishAdminAbleToDeleteAnnouncement extends Driver
{
	@Test
	public void publishAdminAbleToDeleteAnnouncement()
	{
		try 
		{

			String descrption=new RandomString().randomstring(4);
			new AddAnnouncement().addAnnouncement("25", descrption);//crate announcement

			/*
			 * 20-22
			 * "Enter the Status as Active,Click on Save button" 
			 * "Newly created Announcement should be displayed on the Manage Announcement tab with correct details:1)Announcemen2)Start Date3)Expiry Dat4)Status"
			 */
		
			String announcementHeader=new TextFetch().textfetchbyclass("pa-manage-announcements-header-div");
			if(!announcementHeader.contains("Announcement"))
				Assert.fail("announce header not contains 'Announcement'");
			if(!announcementHeader.contains("Start Date"))
				Assert.fail("announce header not contains 'Start Date'");
			if(!announcementHeader.contains("Expiry Date"))
				Assert.fail("announce header not contains 'Expiry Date'");
			if(!announcementHeader.contains("Status"))
				Assert.fail("announce header not contains 'Status'");
			
			/*
			 * 25
			 * "1. Login as a ""Publisher Admin"" 
				2. Mouse hover on one of the Active announcement in  Manage announcement page			
			 * 	On Mouse hover,Active announcement should show “Delete “ button. 				
			 */
			new MouseHover().mouserHoverByIdList("pa-manage-announcements-data-div",0);
			/*
			 * 26--29
			 * 3. Click on "Delete" button
			 *  Delete Announcement pop-up should display
			   "Following message should be displayed in the pop-up:
			   “Are you sure you want to delete this announcement?”"			  	
			 */
		
			new Click().clickByclassname("pa-delete-announcement-btn");//click on delete button
			String popUpMsg=new TextFetch().textfetchbyclass("ls-pa-deleteAnnouncement-dialogBox");//ftech text of pop up
			if(!popUpMsg.contains("Are you sure you want to delete this announcement?"))
			Assert.fail("pop up not contains the message 'Are you sure you want to delete this announcement?'");
			//29,30
			new Click().clickByclassname("pa-dialogBox-Cancel");//click on cancle link
			int popUpClose=driver.findElements(By.className("ls-pa-deleteAnnouncement-dialogBox")).size();
			if(popUpClose!=0)
				Assert.fail("after clic on cancle link pop up not close");
			//--31
			new MouseHover().mouserHoverByIdList("pa-manage-announcements-data-div",0);//mouse hover on an announcement
			new Click().clickByclassname("pa-delete-announcement-btn");//click on delete button
			new Click().clickByclassname("pa-dialogBox-Delete");//click on yes button

			new DBConnect().Connect();
			DBConnect.st.execute("delete from t_announcement;");
			new Navigator().publisherAdminNavigateTo("Announcements");//Navigate to Announcements
            String str =new TextFetch().textfetchbyclass("pa-no-activity-msg");
            System.out.println("str: "+str);
            if(!str.contains("It seems you have not created any announcements yet"))
                Assert.fail("after delete announcement not deleted from dashboard");
		} 
		catch(Exception e)
		{
			Assert.fail("Exception in class PublishAdminAbleToDeleteAnnouncement in method publishAdminAbleToDeleteAnnouncement",e);
		}
	}

}
