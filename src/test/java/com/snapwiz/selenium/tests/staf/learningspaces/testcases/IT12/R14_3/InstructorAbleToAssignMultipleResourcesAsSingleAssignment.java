package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R14_3;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class InstructorAbleToAssignMultipleResourcesAsSingleAssignment extends Driver
{
	@Test(priority=1,enabled=true)
	public void instructorAbleToAssignMultipleResourcesAsaSingleAssignment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1");//login a instructor
			new Navigator().NavigateTo("e-Textbook");		
			new TopicOpen().lessonOpen(0, 0);//open 1st chapter
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.className("assign-options")));
            Thread.sleep(1000);
            boolean assignthistab=driver.findElement(By.className("assign-this-text")).isDisplayed();//verify assign this tab
			if(assignthistab==false)
			{
				Assert.fail("Assign this tab not shown");
			}
			new Widget().addRemoveToCart("image-main widget-content","addtocart");
            Thread.sleep(1000);
			String notificationtext=driver.findElement(By.className("ls-notification-text-span")).getText();//fetch notification after clcik on add to cart
            if(!notificationtext.contains("This item has been added to your assignment activities."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("notification text not present");
			}
			new Navigator().NavigateTo("e-Textbook");		
			new TopicOpen().lessonOpen(0, 0);//open 1st chapter
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,400)");//scroll window
			Thread.sleep(3000);
			String cartcount=driver.findElement(By.className("assignment-cart-notifications")).getText();//fetch count in cart
            new Widget().addRemoveToCart("image-main widget-content","removefromcart");
			String notifucationtextafterclickonremovefromcart=driver.findElement(By.className("ls-notification-text-span")).getText();
			if(!notifucationtextafterclickonremovefromcart.contains( "Yes | No"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("yes or no link not present after click on remove form cart");
			}
			driver.findElement(By.className("not-remove-assignmentcart-cart")).click();//click on no on notification
			String cartcount2=driver.findElement(By.className("assignment-cart-notifications")).getText();//fetch count in cart after click on remove from cart no
			if(!cartcount.equals(cartcount2))
			{
				Assert.fail("card count not varies with add in cart");
			}
            new Widget().addRemoveToCart("image-main widget-content","removefromcart");
			driver.findElement(By.className("yes-remove-assignmentcart-cart")).click();//click on yes from remove from cart
            Thread.sleep(2000);
            new Widget().addRemoveToCart("image-main widget-content","addtocart");
			new Click().clickByclassname("assignment-cart-wrapper");//click on cart symbol
			Thread.sleep(2000);
			String carttext=new TextFetch().textfetchbyclass("ir-ls-assign-dialog-header");
			if(!carttext.contains("New Assignment "))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("cart not shown in formate new assignment and time stamp");
			}
			boolean deletecart=driver.findElement(By.cssSelector("div[class='delete-icon delete-assignment-cart']")).isDisplayed();
			if(deletecart==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("delete symbol not shown in cart ");
			}
			new AssignLesson().assigncart(1);//assign cart to default class section
			boolean assignmentcartvalue=driver.findElement(By.className("assignment-cart-wrapper")).isDisplayed();
			if(assignmentcartvalue==true){
				Assert.fail("cart is dispalyaed after assign even no widget in cart.");
			}
            new Widget().addRemoveToCart("image-main widget-content","addtocart");
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().lessonOpen(1, 4);//open 1st chapter
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,1300)");//scroll window
			Thread.sleep(3000);
			new AddCart().widgetaddtocart();
			String notificationinotherchapter=driver.findElement(By.className("ls-notification-text-span")).getText();
			if(!notificationinotherchapter.contains("You cannot add items from another chapter. You need to first assign or delete the current assignment activity before moving to another chapter."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Notification not shown when instructor tries to add other chapter widget in cart");
			}

		} 
		catch (Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase studentAbleToAssignMultipleResourcesAsaSingleAssignment",e);
		}

	}
	@Test(priority=2,enabled=true)
	public void instructorAssignResources()
	{
		try
		{
			new ResourseCreate().resourseCreate(1, 0);//add resources
			new LoginUsingLTI().ltiLogin("2");//login as instructor
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().lessonOpen(0, 0);//open 1st chapter
			driver.findElement(By.cssSelector("span[title='Resources']")).click();//click on resources tab
			Thread.sleep(2000);
			List<WebElement> we=driver.findElements(By.className("resource-content-posts-list"));//fetch all resources list
			driver.findElements(By.className("ls-resource-show-assign-this-block")).get(0).click();
            Thread.sleep(2000);
			String resourcestabtext=we.get(0).getText();
			if(!resourcestabtext.contains("Open"))//verify tab1
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("open tab not present");
			}
			if(!resourcestabtext.contains("New tab"))//verify tab2
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("new tab not present");
			}
			if(!resourcestabtext.contains("Add to Activity"))//verify tab3
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("add to cart not prest");
			}
			if(!resourcestabtext.contains("Assign this"))//verify tab4
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("assign this not present");
			}

			driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite add-to-cart-resource-bg']")).get(0).click();//click on add to cart
			String notificationtext=driver.findElement(By.className("ls-notification-text-span")).getText();//verify notification
			if(!notificationtext.contains("This item has been added to your assignment activities."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("notification text not present");
			}
		}
		catch (Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase studentAbleToAssignMultipleResourcesAsaSingleAssignment",e);
		}
	}

}
