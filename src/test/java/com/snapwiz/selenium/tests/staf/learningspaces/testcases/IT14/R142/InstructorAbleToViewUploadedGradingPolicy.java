package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R142;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DataFetchFromDataBase;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UploadResources;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;

public class InstructorAbleToViewUploadedGradingPolicy extends Driver
{

	@Test
	public void instructorAbleToViewUploadedGradingPolicy()
	{
		try
		{
			String resourcesname=ReadTestData.readDataByTagName("", "resourcesname", "68");
			String description=ReadTestData.readDataByTagName("", "description", "68");
			new LoginUsingLTI().ltiLogin("68");//--login as instructor
			new UploadResources().uploadResources("68", false, true, false);//--68,69//upload resources with grading policy 
			Thread.sleep(3000);
			String resourcesdetails=new TextFetch().textfetchbycssselector("div[class='ls-assessment-item-sectn-right ls-my-resource-item-section']");//fetch uploaded grading policy text from my resources tab
			if(!resourcesdetails.contains(resourcesname))//73
				Assert.fail("resources name not desplay in my resources tab");
			if(!resourcesdetails.contains(description))//73
				Assert.fail("resources description not desplay in my resources tab");
			if(resourcesdetails.contains("Assign This"))//71
				Assert.fail("Assign this link present with uploaded grading policy");
			Thread.sleep(2000);
			new Click().clickbylist("resource-title", 0);//open uploaded resources with new tab
			boolean resourcesinnewtab = new BooleanValue().booleanbyclass("thumbnail-and-description-wrapper");//verify new tab open or not
            System.out.println("resourcesinnewtab: "+resourcesinnewtab);
            if(resourcesinnewtab==false)
				Assert.fail("media type grading policy not open in new tab");
			//79--check upload grading policy under all resources tab
			new Click().clickBycssselector("div[data-id='all-resources']");//click on all resources tab
			new TextSend().textsendbyid(resourcesname, "all-resource-search-textarea");
			new Click().clickByid("all-resource-search-button");
			boolean presenceofgradingpolicyunderallresourcestab=new BooleanValue().booleanbyclass("no-data-notification-message-block-for-resources");//verify upload grading policy under all resources tab
			if(presenceofgradingpolicyunderallresourcestab==false)
				Assert.fail("uploaded grading policy presence in all resources tab");
			//74
			new Click().clickBycssselector("div[data-id='my-resources']");//click on my resources tab
			new Click().clickByclassname("delete-uploaded-resource");//click on delete this link
			Thread.sleep(2000);
			//76
			String sql="select id from t_resources where content_name = '"+resourcesname+"'";
			int entryforquestion=new DataFetchFromDataBase().userverification(1, sql);//fetch resource id form data base
			if(entryforquestion!=0)
				Assert.fail("after delete the polci not deleted form t_resources tab");
			//75
			int presenceofgradingpolicyafterdelete=driver.findElements(By.cssSelector("div[class='ls-assessment-item-sectn-right ls-my-resource-item-section']")).size();//verify upload grading policy after delete
			if(presenceofgradingpolicyafterdelete!=0)
				Assert.fail("after click on delete prading policy not deleted");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in class InstructorAbleToViewUploadedGradingPolicy",e);
		}
	}
}
