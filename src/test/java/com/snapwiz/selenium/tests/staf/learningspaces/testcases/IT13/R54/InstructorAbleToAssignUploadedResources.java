package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R54;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignLesson;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UploadResources;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class InstructorAbleToAssignUploadedResources extends Driver
{
	@Test(priority=1,enabled=true)
	public void instructorAbleToAssignUploadedResources()
	{
		try
		{
			String resourcesname=ReadTestData.readDataByTagName("", "resourcesname", "92");
			new LoginUsingLTI().ltiLogin("92");
			//92
			new UploadResources().uploadResources("92", true, true, true);
            new Navigator().NavigateTo("e-Textbook");
            new TOCShow().tocHide();
			new Navigator().navigateToResourceTab();
            Thread.sleep(4000);
			driver.findElement(By.className("ls-resource-show-assign-this-block")).click();
            Thread.sleep(2000);
			String tabsunderresourcestab=new TextFetch().textfetchbyclass("resource-content-posts");
			if(!tabsunderresourcestab.contains("Open"))
			{
				Assert.fail(" 'open' tab not present");
			}
			if(!tabsunderresourcestab.contains("New tab"))
			{
				Assert.fail(" 'New tab' tab not present");
			}
			if(!tabsunderresourcestab.contains("Add to Activity"))
			{
				Assert.fail(" 'Add to cart' tab not present");
			}
			if(!tabsunderresourcestab.contains("Assign this"))
			{
				Assert.fail(" 'Assign this' tab not present");
			}
			//95,96,97

			new AssignLesson().assignResourceFromMyResources(92);//assign resource to default class section
			//98,99--check on assignment page
			new Navigator().NavigateTo("Assignments");
			String assignmenttext=new TextFetch().textfetchbyclass("ls-assignment-item-left");
			if(!assignmenttext.contains(resourcesname))
			{
				Assert.fail("assigned resources is not on assignment page.");
			}
			if(!assignmenttext.contains("View Student Responses"))
			{
				Assert.fail("View Student Responses link is not on assignment page.");
			}
			if(!assignmenttext.contains("Update Assignment"))
			{
				Assert.fail("Update Assignment is not on assignment page.");
			}
			if(!assignmenttext.contains("Un-assign Assignment"))
			{
				Assert.fail("Un-assign Assignment is not on assignment page.");
			}
			//100---check on course stream page
			new Navigator().NavigateTo("Course Stream");
			String coursestreamtext=new TextFetch().textfetchbylistclass("ls-media__body", 0);
			if(!coursestreamtext.contains(resourcesname))
			{
				Assert.fail("assigned resources is not on course stream page page.");
			}
			//101--assignment check in textbook assignment tab
			new TOCShow().chaptertree();
			new TopicOpen().lessonOpen(0, 0);
			new Navigator().navigateToTab("Assignments");
			String assigresourcesinassigntab=new TextFetch().textfetchbyclass("assignment-content-posts");
			if(!assigresourcesinassigntab.contains(resourcesname))
			{
				Assert.fail("assigned resource not in assignment tab in textbook p;age");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase class InstructorAbleToAssignUploadedResources in method instructorAbleToAssignUploadedResources",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void instructorAbleToAssignUploadedResourcesFromResourcesPage()
	{
		try
		{
			String resourcesname=ReadTestData.readDataByTagName("", "resourcesname", "107");
			new LoginUsingLTI().ltiLogin("107");
			new UploadResources().uploadResources("107", true, true, true);
            new UIElement().waitAndFindElement(By.className("delete-uploaded-resource"));//119,107,108,75
			new Click().clickByclassname("delete-uploaded-resource");//delete the upload resources
			int countofresources=driver.findElements(By.cssSelector("div[class='ls-assessment-item-sectn-right ls-my-resource-item-section']")).size();
			if(countofresources!=0)
			{
				Assert.fail("resources not deleted from the my resources page");
			}
            new LoginUsingLTI().ltiLogin("107");
			new UploadResources().uploadResources("107", true, true, true);
			Thread.sleep(3000);
			//109-111,120-122
			new Click().clickbylist("assign-this", 0);
			new AssignLesson().assigncart(107);
			//123,112--on assignment page
			new Navigator().NavigateTo("Assignments");
			String assignmenttext=new TextFetch().textfetchbyclass("ls-assignment-item-left");
			if(!assignmenttext.contains(resourcesname))
			{
				Assert.fail("assigned resources is not on assignment page.");
			}
			if(!assignmenttext.contains("View Student Responses"))
			{
				Assert.fail("View Student Responses link is not on assignment page.");
			}
			if(!assignmenttext.contains("Update Assignment"))
			{
				Assert.fail("Update Assignment is not on assignment page.");
			}
			if(!assignmenttext.contains("Un-assign Assignment"))
			{
				Assert.fail("Un-assign Assignment is not on assignment page.");
			}
			//124,113---check on course stream page
			new Navigator().NavigateTo("Course Stream");
			String coursestreamtext=new TextFetch().textfetchbylistclass("ls-media__body", 0);
			if(!coursestreamtext.contains(resourcesname))
			{
				Assert.fail("assigned resources is not on course stream page page.");
			}
			//125,114--assignment check in textbook assignment tab
			new TOCShow().chaptertree();
            new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            new TopicOpen().lessonOpen(0, 0);
			new Navigator().navigateToTab("Assignments");
			String assigresourcesinassigntab=new TextFetch().textfetchbyclass("assignment-content-posts");
			if(!assigresourcesinassigntab.contains(resourcesname))
			{
				Assert.fail("assigned resource not in assignment tab in textbook p;age");
			}

		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase class InstructorAbleToAssignUploadedResources in method instructorAbleToAssignUploadedResourcesFromResourcesPage",e);
		}
	}
	@Test(priority=3,enabled=true) //not clicking on add to cart on line no.185
	public void StudentableToViewAssignResources()
	{
		try
		{
			String resourcesname=ReadTestData.readDataByTagName("", "resourcesname", "128");

			new LoginUsingLTI().ltiLogin("1281");//login as student
			new LoginUsingLTI().ltiLogin("128");//login as instructor
			new UploadResources().uploadResources("128", true, true, true);//upload resources
            if(new UploadResources().uploadresourcessuccessfully()== false)
                Assert.fail("Failed to upload instructor resource");
			Thread.sleep(3000);
			new Navigator().NavigateTo("e-Textbook");
            new TOCShow().tocHide();
			// 103-105---assign by add cart--when mouse hover on etextbook resources tab
			new Navigator().navigateToResourceTab();//navigate to resource tab
            new Navigator().openParticularResourceFromResourceTab(128);
            driver.findElement(By.cssSelector("span[class='ls-right-tab-hover-sprite add-to-cart-resource-bg']")).click();//click on add to cart
			Thread.sleep(2000);
			new Click().clickByclassname("assignment-cart-wrapper");//click on cart icon
			new AssignLesson().assigncart(128);
			new LoginUsingLTI().ltiLogin("128");//login as instructor
			new Navigator().NavigateTo("Resources");
			new Navigator().navigateToTab("My Resources");
            //reqirement change text not shown now image shown
			String resourcestextafterassigned=new TextFetch().textfetchbylistcssselector("div[class='ls-assessment-item-sectn-right ls-my-resource-item-section']", 0);
			if(!resourcestextafterassigned.contains("Assign This"))
			{
				Assert.fail("assign this link present even resources is assign");
			}
			//78
			if(resourcestextafterassigned.contains("Delete This"))
			{
				Assert.fail("Delete This link present even resources is assign");
			}
			boolean assignThislink=new BooleanValue().booleanbycssselector("i[class='assign-this resource-assign-this-image ls-assign-this-sprite']");
            if(assignThislink==false)
                Assert.fail("assign this link not present for instructor on my library page");

            new LoginUsingLTI().ltiLogin("1281");//login as student
			//128--on assignment page
            String appendChar=null;
            if(System.getProperty("UCHAR")==null)
            {
                appendChar=LoginUsingLTI.appendChar;
            }
            else {
                appendChar=System.getProperty("UCHAR");
            }
            String actualResources= resourcesname+appendChar;
			new Navigator().NavigateTo("Assignments");
			String assignmenttext=new TextFetch().textfetchbyclass("ls-assignment-item-left");

            System.out.println("assignmenttext" +assignmenttext);
            System.out.println("resourcesname" +actualResources);
            if(!assignmenttext.contains(actualResources))
			{
				Assert.fail("assigned resources is not on assignment page.");
			}
			//129--on course stream page
			new Navigator().NavigateTo("Course Stream");
			String coursestreamtext=new TextFetch().textfetchbylistclass("ls-media__body", 0);
			if(!coursestreamtext.contains(actualResources))
			{
				Assert.fail("assigned resources is not on course stream page page.");
			}
			//130--on etextbook assignment tab
			new TOCShow().chaptertree();
            new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            new TopicOpen().lessonOpen(0, 0);
			new Navigator().navigateToTab("Assignments");
			String assigresourcesinassigntab=new TextFetch().textfetchbyclass("assignment-content-posts");
			if(!assigresourcesinassigntab.contains("Due Date"))
			{
				Assert.fail("assigned resource not in assignment tab in textbook p;age");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase class InstructorAbleToAssignUploadedResources in method StudentableToViewAssignResources",e);
		}
	}
}
