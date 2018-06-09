package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R161;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AddAnnouncement;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.CloseHelpPage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;

public class UserAbleToViewAddedAnnouncment extends Driver
{
	@Test
	public void userAbleToViewAddedAnnouncment()
	{
		try
		{

			for (int i = 0; i < 3; i++)
			{
				new AddAnnouncement().addAnnouncement("41", new RandomString().randomstring(4));//create announcement
			}
			
			  //instructor ,student ,mentor dashboard announcement activity for LS+AD course
			 
			for (int i = 41; i <= 43; i++)
			{
				new LoginUsingLTI().ltiLogin(String.valueOf(i));//login as user-instructor,mentor,student
				new AddAnnouncement().annoncementFunctionality("", 3);	//check availability of announcement on different page
			}
			 //instructor ,student ,mentor dashboard announcement activity for AD course

			for (int i = 80; i <= 82; i++) 
			{
				new LoginUsingLTI().ltiLogin(String.valueOf(i));//login as user-instrctor,mentor,student
				new CloseHelpPage().closehelppageOrion();//close helppage
				new AddAnnouncement().annoncementFunctionalityOrion("", 3);	//check avilabilty of announcement on different page
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in class UserAbleToViewAddedAnnouncment in method userAbleToViewAddedAnnouncment",e);
		}
	}

}
