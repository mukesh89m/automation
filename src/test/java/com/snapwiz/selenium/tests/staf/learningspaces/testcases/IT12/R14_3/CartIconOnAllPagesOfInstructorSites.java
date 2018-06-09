package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R14_3;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AddCart;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class CartIconOnAllPagesOfInstructorSites extends Driver
{
	@Test
	public void cartIconOnAllPagesOfInstructorSites()
	{
		try
		{
			String topic = ReadTestData.readDataByTagName("tocdata", "topic5","1");
			new LoginUsingLTI().ltiLogin("109");
/*			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().topicOpen(topic);//open topic
*/			new TOCShow().chaptertree();
			new TopicOpen().lessonOpen(0, 0);//open 1st chapter
			Thread.sleep(3000);
			new AddCart().widgetaddtocart();//add image widget to cart
			new AddCart().editcart();//edit cart name
			new Click().clickByclassname("ls-site-nav-drop-down");//Click on navigator dropdown
			new Click().clickByclassname("ls--assignments");//click on assignment for going on assignments page	
			Thread.sleep(3000);
			new AddCart().editcart();//edit cart name
			new Navigator().NavigateTo("Course Stream");//navigate to course stream
			Thread.sleep(3000);
			new AddCart().editcart();//edit cart name
			new Navigator().NavigateTo("Proficiency Report");
			Thread.sleep(3000);
			new AddCart().editcart();//edit cart name
			new Navigator().NavigateTo("Metacognitive Report");
			Thread.sleep(3000);
			new AddCart().editcart();//edit cart name
			new Navigator().NavigateTo("Productivity Report");
			Thread.sleep(3000);
			new AddCart().editcart();//edit cart name
			new Navigator().NavigateTo("Most Challenging Activities Report");
			Thread.sleep(3000);
			new AddCart().editcart();//edit cart name
			new Navigator().NavigateTo("Resources");
			Thread.sleep(3000);
			new AddCart().editcart();//edit cart name
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in class CartIconOnAllPagesOfInstructorSites",e);
		}		
	}


}
