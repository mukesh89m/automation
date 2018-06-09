package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R161;


import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class PublisherAdminAbleToLoginAndViewDashboard extends Driver
{
	@Test
	public void publisherAdminAbleToLoginAndViewDashboard()
	{
		try
		{

			new DirectLogin().publisherAdmin();//login as publisher admin//
            new Navigator().publisherAdminNavigateTo("Announcements");//Navigate to Announcements
			new DBConnect().Connect();
			DBConnect.st.execute("delete from t_announcement;");
			new Click().clickBycssselector("a[class='navigator pa-toc-sprite selected']");
			new Click().clickbylinkText("Announcements");
			String pageHeading=new TextFetch().textfetchbyclass("pa-manage-announcements-title");//fetch text of heading
			if(!pageHeading.contains("Manage Announcements"))
				Assert.fail("page heading is not 'Manage Announcements'");
			//3
			String defaulText=new TextFetch().textfetchbyclass("pa-no-activity-msg");
			if(!defaulText.contains("It seems you have not created any announcements yet."))
				Assert.fail("default text not contains 'It seems you have not created any announcements yet.'");
			//4
			boolean publisherLogo=new BooleanValue().booleanbycssselector("span[class='tab_icon manage-announcements-icon']");
			if(publisherLogo==false)
				Assert.fail("publisher logo not shown on left side");
			//5
			new MouseHover().mouserhover("pa-user-nav__username");
			new Click().clickbylinkText("Logout");	
			boolean loginPage=new BooleanValue().booleanbyid("username");
			if(loginPage==false)
				Assert.fail("afetr click on logout ,not redirect to login page");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in class PublisherAdminAbleToLoginAndViewDashboard in method publisherAdminAbleToLoginAndViewDashboard",e);
		}
	}


}
