package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R14_3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AddCart;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class EditingAssignmentCart extends Driver
{
	@Test
	public void editingAssignmentCart()
	{
		try
		{
			String topic = ReadTestData.readDataByTagName("tocdata", "topic5","1");
			String topic1 = ReadTestData.readDataByTagName("tocdata", "topic2","1");
			new LoginUsingLTI().ltiLogin("67");//login as instructor
/*			new Navigator().NavigateTo("eTextbook");//navigate to e-textbook
			new TopicOpen().topicOpen(topic);//open topic
*/			new TOCShow().chaptertree();
			new TopicOpen().lessonOpen(0, 0);//open 1st chapter
			Thread.sleep(3000);
			new AddCart().widgetaddtocart();//add image widget to cart
/*			new TopicOpen().TOCOpen();//open toc
			new TopicOpen().topicOpen(topic1);//open other topic
*/			new TOCShow().chaptertree();
			new TopicOpen().lessonOpen(0, 1);//open 1st chapter
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,400)");//scroll window
			Thread.sleep(2000);
			new AddCart().widgetaddtocart();//add image widget to cart
			new Click().clickByclassname("assignment-cart-wrapper");
			Thread.sleep(2000);
			new MouseHover().mouserhover("ir-ls-assign-dialog-header-wrapper");//mouse hover to edit the assignment cart
			new Click().clickByclassname("ir-ls-assign-this-edit-link");
			driver.findElement(By.className("ir-ls-assign-this-header-edit-text")).clear();
			String ranstring=new RandomString().randomstring(3);
			driver.findElement(By.className("ir-ls-assign-this-header-edit-text")).sendKeys(ranstring);//change the name of assignment cart
			Thread.sleep(2000);
			new Click().clickbylist("delete-learning-activity", 1);//delete the cart 2
			driver.findElement(By.className("yes-remove-assignmentcart-cart")).click();//click on yes from remove from cart
			Thread.sleep(2000);
			new Click().clickByclassname("assignment-cart-wrapper");//click on assignment cart icon
			String nameofcartafterediting=new TextFetch().textfetchbyclass("ir-ls-assign-dialog-header-wrapper");//fetch changed name of assignment cart
			System.out.println(nameofcartafterediting);
			if(!nameofcartafterediting.contains(ranstring))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("name of assignment cart not change");
			}
			String notificationcountodcart=new TextFetch().textfetchbyclass("assignment-cart-notifications");//fetch count of cart
			int count=Integer.parseInt(notificationcountodcart);
			if(count!=1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("cart notification not decrease after delete the assignment cart");
			}
			new Click().clickbylist("delete-learning-activity", 0);//delete  cart 
			driver.findElement(By.className("yes-remove-assignmentcart-cart")).click();//click on yes from remove from cart
			Thread.sleep(2000);
			/*new Click().clickByid("showHeader");//Click on show header
			Thread.sleep(2000);*/
			boolean carddisplay=driver.findElement(By.className("assignment-cart-wrapper")).isDisplayed();//verify after deletion cart is present or not
			if(carddisplay==true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("after deleting all cart is display");
			}
		}
		catch (Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase EditingAssignmentCart",e);
		}
	}


}
