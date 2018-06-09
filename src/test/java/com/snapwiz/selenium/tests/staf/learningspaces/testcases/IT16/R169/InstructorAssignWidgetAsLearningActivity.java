package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R169;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

/*
 * @Brajesh Kumar
 * @Created-date:-1/09/2014
 * Testcase-id-IT16-R16.9
 * Row no-2-11
 * Testcase-desc:-Instructor Assign Widget as Learning Activity
 */

public class InstructorAssignWidgetAsLearningActivity extends Driver
{
	@Test
	public void instructorAssignWidgetAsLearningActivity()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1");
			
			//Row no-1(user story)Verify "Add to cart" text for a widget
			//2-steps-1. Click on Assign icon present on a widget
			new Navigator().NavigateTo("e-Textbook");
			new TopicOpen().lessonOpen(0, 0);//open 1st lesson and 1st chapter
			new Click().clickByclassname("assign-options");
			
			//Excepted 1:-"Add to Activity" should appear instead of Add to cart
			String textOnWidget=new TextFetch().textfetchbyclass("assign-options-wrapper");
            if(!textOnWidget.contains("Add to Activity"))
				Assert.fail("'Add to Activity' not appear after click on assign icon of image widget");
			
			//Row no-3 (step)1. Click on Add to activity option present on a widget
			new Click().clickByclassname("add-assignment-cart-text");//click on add to activity
			
			//Excepted-1--Text should display as "This item has been added to your assignment activities”
			String notificationText=new TextFetch().textfetchbyclass("ls-notification-text-span");
			if(!notificationText.contains("This item has been added to your assignment activities"))
                Assert.fail("Text not  display as 'This item has been added to your assignment activities'");

            // Row no-5 (step).1. Verify cart icon---Excepted --“stacked rectangle” icon should be displayed
            boolean cartIcon=new BooleanValue().booleanbyclass("assignment-cart-wrapper");
            if(cartIcon==false)
                Assert.fail("after add to activity cart ion not shown");
            //Row no-6 (step)-2. Mouse hover on the stacked rectangle icon
            new MouseHover().mouserhover("assignment-cart-wrapper");
            Thread.sleep(2000);
            //Excepted :-Tooltip should display saying "Assign Activity"
            String toolTipText=driver.findElement(By.className("assignment-cart-wrapper")).getAttribute("title");
            if(!toolTipText.contains("Assign Activity"))
                Assert.fail("Tool tip text not display saying 'Assign Activity'");

            //Row no-7 (step) "3. Click on stacked rectangled icon  4. Mouse hover on trashcan icon"
            new Click().clickByclassname("assignment-cart-wrapper");
            new MouseHover().mouserhover("delete-learning-activity");

            //Excepted:-“Clear Activity” tooltip should display
            String tooltipOfDeleteIcon=driver.findElement(By.className("delete-learning-activity")).getAttribute("title");
            if(!tooltipOfDeleteIcon.contains("Clear Activity"))
                Assert.fail("“Clear Activity” tooltip not display after mouse hover on delete icon");
            //Row no-8 (step)1. Click on trashcan icon for individual item.
            new Click().clickbylist("delete-learning-activity",0);//click on delete icon for individual

            //row-no-9--Excepted:-2-Delete notification text should display as “You are about to remove this item from the assign activities. Are you sure? Yes | No”
            String deleteNotification=new TextFetch().textfetchbyclass("ls-notification-text-span");
            if(!deleteNotification.contains("You are about to remove this item from the assign activities. Are you sure? Yes | No"))
                Assert.fail("Delete notification text not display as “You are about to remove this item from the assign activities. Are you sure? Yes | No”");
            new Click().clickBycssselector("a[title='Close']");//close notification

            //Row no-10 (step)-2. Click on trashcan icon which is for all items.
            //new Click().clickByclassname("assignment-cart-wrapper");//click on cart icon
            new Click().clickBycssselector("div[class='delete-icon delete-assignment-cart']");//click on delete icon for all

            //Row no-11-Excepted:-Delete notification text should display as “You are about to remove this item from the assign activities. Are you sure? Yes | No”
            String deleteNotification1=new TextFetch().textfetchbyclass("ls-notification-text-span");
            if(!deleteNotification1.contains("You are about to delete the assignment activity. Are you sure? Yes | No"))
                Assert.fail("Delete notification(for all item) text not display as “You are about to remove this item from the assign activities. Are you sure? Yes | No”");
        }
		catch(Exception e)
		{
			Assert.fail("Exception in testcase in class InstructorAssignWidgetAsLearningActivity in method instructorAssignWidgetAsLearningActivity",e);
		}
	}


}
