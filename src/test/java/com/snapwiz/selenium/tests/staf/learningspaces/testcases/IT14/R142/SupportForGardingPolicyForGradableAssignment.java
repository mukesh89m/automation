package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R142;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

import java.util.List;

public class SupportForGardingPolicyForGradableAssignment extends Driver
{
	@Test
	public void supportForGardingPolicyForGradableAssignment() {
		try {
			String filename = ReadTestData.readDataByTagName("", "filename", "1");
			String filename1 = ReadTestData.readDataByTagName("", "filename1", "1");
			String resourcename = ReadTestData.readDataByTagName("", "resoursename", "1");
			new LoginUsingLTI().ltiLogin("1");
			//2-6
			new UploadResources().openUploadResource();//open upload resources pop up
			new Click().clickByid("uploadFile");//--9---click on upload file
			new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");//10
			Thread.sleep(4000);//11-13
			new Click().clickByclassname("ins-deleteFileIcon");//delete icon appear after file uploaded successfully
			//new Click().clickbyxpath("//span[contains(@title,'Delete Uploaded Resource')]");
			Thread.sleep(2000);
			int fileafterdelete = driver.findElements(By.className("ins-deleteUploadedFile")).size();//15,16
			if (fileafterdelete != 0)
				Assert.fail("file not delete after click on delete icon");
			new Click().clickByid("uploadFile");//--9---click on upload file
			Thread.sleep(2000);
			new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename1 + "^");//14
			if(!(new Notification().getNotificationMessage().equals("There was a problem while uploading the file. Please try again."))) {
				Assert.fail("Notification message after uploading a file greater than 25 MB is not as expected");
			}

			//16
			new Click().clickByid("uploadFile");//--9---click on upload file
			new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");//10
			Thread.sleep(4000);
			//31,32
			new Click().clickByclassname("ls-instructor-only-assignment-check");//checked reserve for assignemt
			//20			
			new TextSend().textsendbyclass("", "ins-uploadResource-input");//name in resouurces name filed
			//21
			boolean staricon = new BooleanValue().booleanbyclass("ls-ins-star-icon");
			if (staricon == false)
				Assert.fail("* icon not shown over resource name");
			driver.findElement(By.className("ins-uploadResource-input")).clear();//clear the string
			new Click().clickbylistcssselector("div[class='course-chapter-label node']", 0);//choose an chapter
			//25,23
			new Click().clickBycssselector("span[class='ins-dialogBox-Save ins-resource-save-btn']");//click on save button
			String errormsg = new TextFetch().textfetchbyclass("ls-notification-text-span");
			if (!errormsg.contains("Please provide a resource name."))
				Assert.fail("error message not shown when try to upload the resources wiothout resources name");
			new TextSend().textsendbyclass(resourcename, "ins-uploadResource-input");//name in resouurces name filed
			Thread.sleep(2000);
			//29
			new Click().clickBycssselector("span[class='ins-dialogBox-Save ins-resource-save-btn']");//click on save button
			Thread.sleep(3000);
			new Navigator().NavigateTo("e-Textbook");
			new TOCShow().tocHide();
            new Navigator().navigateToResourceTab();
			WebElement element=driver.findElement(By.xpath("//a[text()='"+resourcename+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
			Thread.sleep(3000);
			boolean found=false;
			List<WebElement> resources = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
			for(WebElement res : resources)
			{
				if(res.getText().contains(resourcename))
				{
					found=true;
				}
			}
			if (found==false)
				Assert.fail("Resource is not available under resource tab");

		} catch (Exception e) {
			Assert.fail("Exception in testcase method supportForGardingPolicyForGradableAssignment in test class SupportForGardingPolicyForGradableAssignment.", e);
		}
	}

}
