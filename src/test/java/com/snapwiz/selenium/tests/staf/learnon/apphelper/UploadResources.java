package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.snapwiz.selenium.tests.staf.learnon.Config;
import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
/*
 * Brajesh
 * uploaded resources by instructor
 */

public class UploadResources 
{
	public void uploadResources(String dataIndex,boolean reserveforassignment,boolean reserveforgradedpolic,boolean associteresourcesto)
	{
		try
		{
			String filename =  ReadTestData.readDataByTagName("", "filename", dataIndex);
			String resourcesname=ReadTestData.readDataByTagName("", "resourcesname", dataIndex);
			String description=ReadTestData.readDataByTagName("", "description", dataIndex);
			String gradingpolicy=ReadTestData.readDataByTagName("", "gradingpolicystatus", dataIndex);
			new Navigator().NavigateTo("Resources");//navigate to resource
			new Click().clickBycssselector("div[data-id='my-resources']");//click on my resources tab
			new Click().clickByid("upload-resourse-button");//click on upload resources button
			new Click().clickByid("uploadFile");
            new com.snapwiz.selenium.tests.staf.learnon.apphelper.KeysSend().sendKeyBoardKeys("$");
			new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
			Thread.sleep(10000);
			Driver.driver.findElement(By.className("ins-uploadResource-input")).clear();
			Driver.driver.findElement(By.className("ins-uploadResource-input")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("ins-uploadResource-input")).sendKeys(resourcesname);//give resources name
			Driver.driver.findElement(By.className("ins-uploadResource-textbox")).sendKeys(description);//give description
			if(reserveforassignment==true)
				new Click().clickByclassname("ls-instructor-only-assignment-check");//checked reserve for assignment
            if(gradingpolicy != null) {
                if (gradingpolicy.equals("true"))
                    new Click().clickByclassname("ls-instructor-only-grading-policy-check");//checked for reserve for graded policy
            }
			if(associteresourcesto==true)
				new Click().clickbylistcssselector("div[class='course-chapter-label node']", 0);//select chapter to associate
			Thread.sleep(20000);
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
            WebElement notification = (new WebDriverWait(Driver.driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.className("ls-notification-text")));
			if(notification.getText().contains("The resource is successfully created."))
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
	
	public void VerifyResourceswithGradingPolicyInetextbook(String resourcename)
	{
		try
		{
			new Navigator().NavigateTo("e-Textbook");
			//50--search resources name in search field of etextbook
			new Click().clickBycssselector("input[class='toc-sprite search-course-stream ui-autocomplete-input']");
			new TextSend().textsendbycssSelector("\""+resourcename+"\"", "input[class='toc-sprite search-course-stream ui-autocomplete-input']");
			new Click().clickByclassname("ls-search-icon");
			Thread.sleep(2000);
			boolean searchresult=new BooleanValue().booleanbyclass("ls-search-results-not-found");//verify search result not found
			if(searchresult==false)
				Assert.fail("after search resources name is displayed");
		} 
		catch (Exception e) 
		{
			Assert.fail("Exception in apphelper  VerifyResourceswithGradingPolicyInetextbook",e);	
		}
	}
	
}

