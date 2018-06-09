package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R14_3;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AddCart;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignLesson;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Filter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class PopUpToAssignCart extends Driver {

	@Test
	public void popUpToAssignCart()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("801");//login as student
			new LoginUsingLTI().ltiLogin("80");//login as instructor
			new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
			new TopicOpen().lessonOpen(0, 0);//open 1st chapter
			Thread.sleep(3000);
			new AddCart().widgetaddtocart();//add image widget to cart
			new Click().clickByclassname("assignment-cart-wrapper");//click on assign cart
			new Click().clickBycssselector("span[class='btn sty-green submit-assign']");//click on assign on the cart
			new Click().clickByid("assign-cancel");
			int assignmentcartaftercancle=driver.findElements(By.id("ir-ls-assign-dialog")).size();
			if(assignmentcartaftercancle!=0)
			{
				Assert.fail("assignment pop up not close after cancle it");
			}
			Thread.sleep(2000);
			new Click().clickByclassname("assignment-cart-wrapper");//click on assign cart	
			Thread.sleep(2000);
			new AssignLesson().assigncartwithclasssection(801);
			new Navigator().NavigateTo("Summary");
			new Filter().filterApply("All Activity", "Learning Activity");
			String assignmentname=new TextFetch().textfetchbyclass("ls-assignment-name-block");//fetch text from assignment page
			if(!assignmentname.contains("New Assignment"))
			{
				Assert.fail("assignment name not shown in assignment page");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase PopUpToAssignCart ",e);
		}
	}

}
