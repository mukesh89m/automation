package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R142;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UploadResources;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;

public class ValidateReserveForGradingPolicyCheckBox extends Driver
{
	@Test
	public void validateReserveForGradingPolicyCheckBox()
	{
		try 
		{
			String filename =  ReadTestData.readDataByTagName("", "filename", "38");
			String resourcename=ReadTestData.readDataByTagName("", "resourcesname", "38");
			new LoginUsingLTI().ltiLogin("38");
			new UploadResources().openUploadResource();
			//upload file
			new Click().clickByid("uploadFile");
			new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
			Thread.sleep(4000);
			new TextSend().textsendbyclass(resourcename	, "ins-uploadResource-input");//name in resouurces name filed
			//38 checked reserve for grading policy
			new Click().clickByclassname("ls-instructor-only-grading-policy-check");
			Thread.sleep(2000);
			//40,41--reserve for assignment checkbox disable
			boolean reserveforassignmentcheckbob=new BooleanValue().booleanbycssselector("label[class='ls-instructor-only-assignment-check disable']");
			if(reserveforassignmentcheckbob==false)
				Assert.fail("reserve for assignemnt checkbox not disable");
			//39--chapter associate area disable
			boolean chapternameblock=new BooleanValue().booleanbycssselector("div[class='blockUI blockMsg blockElement']");
			if(chapternameblock==false)
				Assert.fail("associate resources not blobk ");
			//42
			driver.findElement(By.xpath("//span[@title='Save']")).click();//click on save button
            Thread.sleep(500);
			//48--notification text ferching
			String message=new TextFetch().textfetchbyclass("ls-notification-text-span");
			if(!message.contains("The Assignment Reference has been successfully created."))
				Assert.fail("message not display 'The Grading Policy has been successfully created.'");
			Thread.sleep(3000);
			//47,43 resources image shiwn
			boolean resourcesimage=new BooleanValue().booleanbycssselector("div[class='ls-resource-doctypes ls-resource-image']");
			if(resourcesimage==false)
				Assert.fail("resources image not shown.");
			//49,50 verify by instructor resources upload with grading policy on etextbook
			new UploadResources().VerifyResourceswithGradingPolicyInetextbook("resource38");
			//51
			new LoginUsingLTI().ltiLogin("38_1");
			//51,52--verify by student resources upload with grading policy on etextbook
			new UploadResources().VerifyResourceswithGradingPolicyInetextbook("resource38");
		}
		catch (Exception e)
		{
			Assert.fail("Exception in testcase method ValidateReserveForGradingPolicyCheckBox in class ValidateReserveForGradingPolicyCheckBox",e);
		}
	}

}
