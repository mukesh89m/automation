package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R54;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UploadResources;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class InstructorAbleToassociateResourcesWithToc extends Driver
{
	@Test(priority=1,enabled=true)
	public void instructorAbleToassociateResourcesWithToc()
	{
		try
		{
            new LoginUsingLTI().ltiLogin("33");
            new UploadResources().openUploadResource();
            //33---help text
            new Click().clickbylist("ins-resourceUpload-help-img", 1);
            String poptext=new TextFetch().textfetchbylistclass("help-data-container",1);
            if(!poptext.contains("You can choose a specific course/chapter/section or subsection where"))
            {
                Assert.fail("help pop up not shown");
            }
            //41-47,55-57
            new Click().clickbylist("expand-chapter-tree", 0);//click on + icon
            new Click().clickbylistcssselector("div[class='course-topic-label node']", 0);//click on topic lable node
            new Click().clickbylistcssselector("div[class='expand-chapter-tree expand']", 0);//collapse the chapter
            //48
            String associtedchapter=new TextFetch().textfetchbyclass("ins-associated-lesson");
			if(!associtedchapter.contains("Ch 1:"))
			{
				Assert.fail("Associated chapter name not displayed after selecting the chapter");
			}
            if(!associtedchapter.contains("You have selected"))
            {
                Assert.fail("Associated tag name not  displayed after selecting the chapter");
            }
            new Click().clickByclassname("ins-dialogClose");//close the upload resources dialog
            new UploadResources().uploadResources("33", true, true, false);//--53--upload resources without selecting any chapter
            if(driver.findElements(By.className("ls-ins-uploadResource-dialogBox")).size()==0) {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Resource upload successfully even no chapter selected");
            }
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase class InstructorAbleToassociateResourcesWithToc in method instructorAbleToassociateResourcesWithToc",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void validateTheUploadedResources()
	{
		try
		{
			String resourcesname=ReadTestData.readDataByTagName("", "resourcesname", "58");
			new LoginUsingLTI().ltiLogin("58_1");
			new LoginUsingLTI().ltiLogin("58");
			new UploadResources().uploadResources("58", true, true, true);
			Thread.sleep(2000);
			new LoginUsingLTI().ltiLogin("58");
			new Navigator().NavigateTo("Resources");//navigate to resource 
			new Click().clickBycssselector("div[data-id='my-resources']");//click on my resources tab
			String activetab=new TextFetch().textfetchbycssselector("div[class='tab active']");
			if(!activetab.contains("My Resources"))
			{
				Assert.fail("My resources tab is not open");
			}
			String uploadedresourcestext=new TextFetch().textfetchbylistcssselector("div[class='ls-assessment-item-sectn-right ls-my-resource-item-section']", 0);
			if(!uploadedresourcestext.contains(resourcesname))
			{
				Assert.fail("successfully uploaded resources not available on my resources tab");
			}
			new LoginUsingLTI().ltiLogin("58_1");
			new Navigator().NavigateTo("Resources");//navigate to resource 
			new Click().clickBycssselector("div[data-id='my-resources']");//click on my resources tab
			String uploadedresourcestext1=new TextFetch().textfetchbylistcssselector("div[class='ls-assessment-item-sectn-right ls-my-resource-item-section']", 0);
			if(!uploadedresourcestext1.contains(resourcesname))
			{
				Assert.fail("successfully uploaded resources not available on my resources tab");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase class InstructorAbleToassociateResourcesWithToc in method validateTheUploadedResources",e);
		}
	}
	
}
