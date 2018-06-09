package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import com.snapwiz.selenium.tests.staf.dummies.apphelper.*;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import org.openqa.selenium.By;
import org.testng.Assert;
import com.snapwiz.selenium.tests.staf.dummies.Config;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.ReadTestData;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.Click;
/*
 * Brajesh
 * uploaded resources by instructor
 */
import com.snapwiz.selenium.tests.staf.dummies.uihelper.TextFetch;

public class UploadResources 
{
	public void uploadResources(String dataIndex,boolean reserveforassignment,boolean reserveforgradedpolic,boolean associteresourcesto)
	{
		try 
		{
			String filename =  ReadTestData.readDataByTagName("", "filename", dataIndex);
			String resourcesname=ReadTestData.readDataByTagName("", "resourcesname", dataIndex);
			String description=ReadTestData.readDataByTagName("", "description", dataIndex);
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator().NavigateTo("Resources");//navigate to resource
			new Click().clickBycssselector("div[data-id='my-resources']");//click on my resources tab
			new Click().clickByid("upload-resourse-button");//click on upload resources button
			new Click().clickByid("uploadFile");
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.KeysSend().sendKeyBoardKeys(Config.fileUploadPath+filename+"^");
			Thread.sleep(10000);
			Driver.driver.findElement(By.className("ins-uploadResource-input")).clear();
			Driver.driver.findElement(By.className("ins-uploadResource-input")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("ins-uploadResource-input")).sendKeys(resourcesname);//give resources name
			Driver.driver.findElement(By.className("ins-uploadResource-textbox")).sendKeys(description);//give description
			if(reserveforassignment==true)			
				new Click().clickByclassname("ls-instructor-only-assignment-check");//checked reserve for assignment			
			/*if(reserveforgradedpolic==true)
				new Click().clickByclassname("ls-instructor-only-grading-policy-check");//checked for reserve for graded policy
		*/	if(associteresourcesto==true)
				new Click().clickbylistcssselector("div[class='course-chapter-label node']", 0);//select chapter to associate
		Thread.sleep(7000);
			Driver.driver.findElement(By.className("ins-dialogBox-Save")).click();
		}	
		catch (Exception e) 
		{
			Assert.fail("Exception in apphelper  UploadResources",e);			
		}
	}
	
	public void openUploadResource()
	{
		try
		{
			new Navigator().NavigateTo("Resources");//navigate to resource
			new Click().clickBycssselector("div[data-id='my-resources']");//click on my resources tab
			new Click().clickByid("upload-resourse-button");//click on upload resources button
		}
		catch (Exception e) 
		{
			Assert.fail("Exception in apphelper  UploadResources",e);			
		}
	}
	
	public boolean uploadresourcessuccessfully()
	{
		boolean success=false;
		try
		{
			String popuptext=new TextFetch().textfetchbyclass("ls-notification-text");
			if(popuptext.contains("The resource is successfully created."))
			{
				success=true;
			}
		}
		catch (Exception e) 
		{
			Assert.fail("Exception in apphelper  uploadresourcessuccessfully",e);			
		}
		return success;
	}
	
}

