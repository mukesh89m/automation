package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R54;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;

public class SupportForAssignmentsUploadedFile extends Driver
{
	@Test(priority=1,enabled=true)
	public void supportForAssignmentsUploadedFile()
	{
		try
		{
			String filename=ReadTestData.readDataByTagName("", "filename", "1");
            String resourcesname=ReadTestData.readDataByTagName("", "resourcesname", "1_1");
			new LoginUsingLTI().ltiLogin("1");//3--login as instructor
			//4,5,6
			new UploadResources().openUploadResource();//go to upload resources
			String popheader=new TextFetch().textfetchbyclass("ins-dialogHeader-title");
			//7
			if(!popheader.contains("Upload File" ))
			{
				Assert.fail("header is not upload file");
			}
			boolean closepopup=new BooleanValue().booleanbyclass("ins-dialogClose");
            System.out.println("closepopup:"+closepopup);
            if(closepopup==false)
			{
				Assert.fail("closepopup not shown");
			}

			String uploadfile=new TextFetch().textfetchbyid("uploadFile");
			if(!uploadfile.contains("+ UPLOAD FILE"))
			{
				Assert.fail("upload file text is not + UPLOAD FILE");
			}
			String uploadfiledetails=new TextFetch().textfetchbyclass("ins-uploadResourceDetails");
			if(!uploadfiledetails.contains("Resource Name"))
			{
				Assert.fail("resources name not shown");
			}
			if(!uploadfiledetails.contains("Description"))
			{
				Assert.fail("Description not shown");
			}
			if(!uploadfiledetails.contains("Reserved for assignment"))
			{
				Assert.fail("Reserved for assignment not shown");
			}
			if(!uploadfiledetails.contains("Reserved for assignment reference"))
			{
				Assert.fail("Reserved for assignment reference not shown");
			}
			String associatetext=new TextFetch().textfetchbyclass("associate-resource");
			if(!associatetext.contains("Associate resource to"))
			{
				Assert.fail("Associate resource to not shown.");
			}
			//8,9
			new Click().clickByid("uploadFile");//click on upload file
			new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
			Thread.sleep(2000);
			boolean progressbar=new BooleanValue().booleanbyid("fileupload-progress-bar");
			if(progressbar==false)
			{
				Assert.fail("progress bar not shown");
			}
			Thread.sleep(30000);
			//10
			new Click().clickByclassname("ins-deleteFileIcon");//delete the uploaded file
			Thread.sleep(2000);
			//11,12--after deleting again able to upload file
			new Click().clickByid("uploadFile");
            new KeysSend().sendKeyBoardKeys("$");
			new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
			Thread.sleep(2000);
			boolean progressbar1=new BooleanValue().booleanbyid("fileupload-progress-bar");
			if(progressbar1==false)
			{
				Assert.fail("progress bar not shown");
			}
			//14--charter accepted
			String numberofcharter=new RandomString().randomstring(100);
			new TextSend().textsendbyclass(numberofcharter, "ins-uploadResource-input");
			Thread.sleep(2000);
			String chartercountinfiled=driver.findElement(By.className("ins-uploadResource-input")).getAttribute("value");
			if(chartercountinfiled.length()!=255)
			{
				Assert.fail("accepted charter is not 255 in resources name");
			}
			//16 star icon
			boolean staricon=new BooleanValue().booleanbyclass("ls-ins-star-icon");
			if(staricon==false)
			{
				Assert.fail("star icon not shown before resources name");
			}
			//19,18,17--upload resources without resources name
            driver.findElement(By.className("ins-dialogClose")).click();
			new UploadResources().uploadResources("1", true, false, true);
            if(driver.findElements(By.className("ls-ins-uploadResource-dialogBox")).size()==0) {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Resource uploaded without giving Resource Name");
            }
			new Click().clickByclassname("ins-dialogClose");
			//20,21--upload resources without description
			new UploadResources().uploadResources("1_1", true, false, true);
            Thread.sleep(3000);
            if (System.getProperty("UCHAR") == null) {
                resourcesname = resourcesname + LoginUsingLTI.appendChar;
            } else {
                resourcesname = resourcesname + System.getProperty("UCHAR");
            }
            if(!driver.findElement(By.className("resource-title")).getText().equals(resourcesname))
			Assert.fail("Resource Not uploaded");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in class SupportForAssignmentsUploadedFile in method supportForAssignmentsUploadedFile ",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void reserveForAssigment()
	{
		try
		{
			String resourcesname1=ReadTestData.readDataByTagName("", "resourcesname", "21");
			//---when checkbox reserve for assignment is checked------
			new LoginUsingLTI().ltiLogin("21");//login as instructor
			//32
			new UploadResources().openUploadResource();//go to upload resources
			new Click().clickByclassname("ins-resourceUpload-help-img");
			String poptext=new TextFetch().textfetchbyclass("help-data-container");
			Thread.sleep(2000);
			if(!poptext.contains("If you select this option, the resource will not be available to the students"))
			{
				Assert.fail("pop up message not shown");
			}
			new Click().clickByclassname("ins-dialogClose");//close the upload resources dialog
			//21
			new UploadResources().uploadResources("21", true, false, true);//upload resources with flag 'Reserved for assignment' checked
			Thread.sleep(4000);
			new TOCShow().chaptertree();
            new TopicOpen().lessonOpen(0, 0);
            Thread.sleep(2000);
			new Navigator().navigateToResourceTab();

			//24,61
            String appendChar=null;
            if(System.getProperty("UCHAR")==null)
            {
                appendChar=LoginUsingLTI.appendChar;
            }
            else {
                appendChar=System.getProperty("UCHAR");
            }
            String actualResources= resourcesname1+appendChar;
            WebElement element=driver.findElement(By.xpath("//a[text()='"+actualResources+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(2000);
            boolean found =false;
            List<WebElement> resources = driver.findElements(By.xpath("//div[contains(@class,'ls-right-section-status ls-right-section-resource')]/a[1]"));
            for(WebElement res : resources)
            {
                if(res.getAttribute("title").contains(actualResources))
                {
                    found=true;
                }
            }
            if(found == false)
                Assert.fail("resources name not present under resources tab for instructor");
			new LoginUsingLTI().ltiLogin("21_1");//login as student--
			Thread.sleep(2000);
			new TOCShow().chaptertree();
            new TopicOpen().lessonOpen(0, 0);
            new Navigator().navigateToResourceTab();
			//25

            List<WebElement> allresources=driver.findElements(By.className("ls-right-user-content"));
            for(WebElement rersources:allresources)
            {
                String resourcesName=rersources.getText();
                if(resourcesName.contains(resourcesname1))
                {
                    Assert.fail("After checked on reserve for assignment  of upload resources resources shown in resources tab");
                }
            }
			//---when checkbox reserve for assignment is not checked------
			new LoginUsingLTI().ltiLogin("26");//login as instructor
			new UploadResources().uploadResources("26", false, false, true);//upload resources--26 with 'reserve for assignment' and 'reserve for grading policy' un-checked
			String resourcesname2=ReadTestData.readDataByTagName("", "resourcesname", "26");
			Thread.sleep(4000);
			new TOCShow().chaptertree();
            new TopicOpen().lessonOpen(0, 0);
			new Navigator().navigateToResourceTab();
			//30,52
            String appendChar1=null;
            if(System.getProperty("UCHAR")==null)
            {
                appendChar1=LoginUsingLTI.appendChar;
            }
            else {
                appendChar1=System.getProperty("UCHAR");
            }
            String actualResources1= resourcesname2+appendChar1;
            WebElement element1=driver.findElement(By.xpath("//a[text()='"+actualResources1+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element1);
            Thread.sleep(2000);
            boolean found1 =false;
            List<WebElement> resources1 = driver.findElements(By.xpath("//div[contains(@class,'ls-right-section-status ls-right-section-resource')]/a[1]"));
            for(WebElement res : resources1)
            {
                if(res.getAttribute("title").contains(actualResources1))
                {
                    found1=true;
                }
            }
            if(found1 == false)
                Assert.fail("resources name not present under resources tab for instructor");
			new LoginUsingLTI().ltiLogin("26_1");//login as student
			Thread.sleep(3000);
			new TOCShow().chaptertree();
            new TopicOpen().lessonOpen(0, 0);
			new Navigator().navigateToResourceTab();
			//31--check resources present under resources tab for student
            WebElement element11=driver.findElement(By.xpath("//a[text()='"+actualResources1+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element11);
            Thread.sleep(2000);
            boolean found11 =false;
            List<WebElement> resources11 = driver.findElements(By.xpath("//div[contains(@class,'ls-right-section-status ls-right-section-resource')]/a[1]"));
            for(WebElement res : resources11)
            {
                if(res.getAttribute("title").contains(actualResources1))
                {
                    found11=true;
                }
            }
            if(found11 == false)
                Assert.fail("resources name not present under resources tab for instructor");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in class SupportForAssignmentsUploadedFile in method reserveForAssigment ",e);
		}
	}

}
