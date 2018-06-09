package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class InstructorAbleToOpenMultipleTab extends Driver
{
	@Test(priority = 1, enabled = true)
	public void instructorabletoopenmultipletab()
	{
		try
		{
			new ResourseCreate().resourseCreate(1507,0);//create 1st resources on chapter level 1
			new LoginUsingLTI().ltiLogin("1507");//login as instructor
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new DiagnosticTest().startTestForInstructor();//open dia test
            new Navigator().navigateToResourceTab();
            driver.findElement(By.className("ls-resource-show-assign-this-block")).click();//click on arrow icon
            List<WebElement> allNewTabs = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-forward-bg']"));
            for(WebElement tab : allNewTabs)
            {
                if(tab.isDisplayed()==true)
                {
                    tab.click();
                    break;
                }
            }

			List<WebElement> alltabname=driver.findElements(By.cssSelector("div[class='tab']"));
			int tabopen=alltabname.size();
            if(tabopen<3)
				Assert.fail("Multiple tab not open of assessment ,resources,and lesson");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method instructorabletoopenmultipletab in class  InstructorAbleToOpenMultipleTab",e);
		}
	}
	
	
	@Test(priority = 2,enabled = true)
	public void openlessonresourcesassignment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1507");
			new Navigator().NavigateTo("eTextbook");
			int tabvalue1=driver.findElements(By.cssSelector("div[class='tab']")).size();
            new TopicOpen().lessonOpen(0, 1);
			int tabvalue2=driver.findElements(By.cssSelector("div[class='tab']")).size();//number of tab  after click on chapter
			if(tabvalue1 != tabvalue2)
				Assert.fail("Topic opens on new tab even though we didn't clicked on new tab button..");
            new Navigator().NavigateTo("eTextbook");
            new DiagnosticTest().startTestForInstructor();
			int tabvalue3 = driver.findElements(By.cssSelector("div[class='tab']")).size();//number of tab  after click on assessment
			if(tabvalue3<tabvalue2)
				Assert.fail("Assessment does not open in new tab.");
            new Navigator().navigateToResourceTab();
            driver.findElement(By.className("ls-resource-show-assign-this-block")).click();//click on arrow icon
            List<WebElement> allNewTabs = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-forward-bg']"));
            for(WebElement tab : allNewTabs)
            {
                if(tab.isDisplayed()==true)
                {
                    tab.click();
                    break;
                }
            }
			int tabvalue4=driver.findElements(By.cssSelector("div[class='tab']")).size();//number of tab  after click on resources
			if(tabvalue4<tabvalue3)
				Assert.fail("Resources does not not open in new tab");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method openlessonresourcesassignment in class  InstructorAbleToOpenMultipleTab",e);
		}
	}
	
	@Test(priority=3,enabled=true)
	public void instructorabletogoanotherassessment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1507");
            new Navigator().NavigateTo("eTextbook");
            new DiagnosticTest().startTestForInstructor();
            new Navigator().NavigateTo("eTextbook");
            new PracticeTest().startTest();
            String tab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
            if(!tab.contains("Personalized Practice - Ch 1:"))
                Assert.fail("Instructor is unable to open practice test.");

		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method instructorabletogoanotherassessment in class  InstructorAbleToOpenMultipleTab",e);
		}
	}

	
}
