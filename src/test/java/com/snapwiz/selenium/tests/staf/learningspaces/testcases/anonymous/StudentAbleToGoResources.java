package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ResourseCreate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class StudentAbleToGoResources extends Driver
{
	@Test
	public void studentabletogoresources()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("2912");
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().ResourcesOpenInNewtab(1, 1);
			String activetext=driver.findElement(By.cssSelector("div[class='tab active']")).getText();//fetch active tab text
			String resourcesname = ReadTestData.readDataByTagName("StudentAbleToGoResources", "resoursename", "29122");
			String resourcesname1 = ReadTestData.readDataByTagName("StudentAbleToGoResources", "resoursename", "29121");
			if(!resourcesname.equals(activetext))
				Assert.fail("Resources not open ");
			new TopicOpen().resourcesopeninexistingtab(2);//click on exiting tab
			Thread.sleep(3000);
			String activetext1=driver.findElement(By.cssSelector("div[class='tab active']")).getText();//fetch active tab text
			if(!resourcesname1.equals(activetext1))
				Assert.fail("Resources not open on exiting tab");	
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method studentabletogoresources in class StudentAbleToGoResources",e);
		}
	}


}
