package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessageValidate;

public class ShareALinkWithStudent extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.ShareALinkWithClassSection");
    /*
     * 236-238
     */	
	@Test(priority=1,enabled=true)
	public void PostALinkWithsharingAStudent()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("236");
			new LoginUsingLTI().ltiLogin("237");
			new LoginUsingLTI().ltiLogin("236");
			String linktext=ReadTestData.readDataByTagName("", "linktext", "236");
			String sharewithintialstring=ReadTestData.readDataByTagName("", "sharewithintialstring", "236");
			new Navigator().NavigateTo("Course Stream");
			if(!new PostMessage().postLinkAndShare(linktext, sharewithintialstring, "studentname", "236"))	 Assert.fail("Link not Shared with student");
			if(!new PostMessageValidate().validateLink(linktext))	Assert.fail("Link not posted");
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception  in testcase PostALinkWithsharingAStudent in class ShareALinkWithStudent",e);
			Assert.fail("Exception  in testcase PostALinkWithsharingAStudent in class ShareALinkWithStudent",e);		      
		}
	}
	
	@Test(priority=2,enabled=true,dependsOnMethods={"PostALinkWithsharingAStudent"})
	public void VerifylinkWithWhomLinkShare()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("237");
			new Navigator().NavigateTo("Course Stream");
			String linktext=ReadTestData.readDataByTagName("", "linktext", "236");			
			boolean postedtoverify=new PostMessageValidate().validateLink(linktext);
			if(postedtoverify==false)
			Assert.fail("Link shared by user 236 to user 237 not Present when logged in as user 237");						
		}
		catch(Exception e)
		  {
			Assert.fail("Exception  in testcase VerifylinkWithWhomLinkShare in class ShareALinkWithStudent",e);
		  }
	}
	
	@Test(priority=3,enabled=true,dependsOnMethods={"PostALinkWithsharingAStudent"})
	public void VerifyTheLinkWithWhomLinkNotShare()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("238");
			new Navigator().NavigateTo("Course Stream");
			String linktext=ReadTestData.readDataByTagName("", "linktext", "236");
			boolean postedtoverify=new PostMessageValidate().validateLink(linktext);
			if(postedtoverify==false)
			logger.log(Level.INFO,"linktext not posted on the student with whom link not share");	
		}
		catch(Exception e)
		{			 
			Assert.fail("Exception  in testcase VerifyTheLinkWithWhomLinkNotShare in class ShareALinkWithStudent",e);		      
		}		
	}

	
}


