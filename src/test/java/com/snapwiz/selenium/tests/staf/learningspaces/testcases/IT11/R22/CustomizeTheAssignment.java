package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R22;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class CustomizeTheAssignment extends Driver
{
	@Test(priority=1,enabled=true)
	public void customizeTheAssignment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("355");// login as instructor
			new Navigator().NavigateTo("Question Banks");
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,1500)");
			Thread.sleep(3000);
			((JavascriptExecutor)driver).executeScript("window.scrollTo(1500,10000)");
			Thread.sleep(3000);
			List<WebElement> we=driver.findElements(By.className("customize-this"));	//fetch customise resources
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",we.get(2));
			Thread.sleep(2000);
			String newtabname=driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!newtabname.contains("New Assignment"))
			{
				Assert.fail("new assignemnt tab not open after click on customize this link of assignment");
			}
			new Click().clickByid("showExpendQuestionIcon");//click on link to expand the search question
			new Click().clickByclassname("expendQuestion");//click on + icon
			String expandedarea=new TextFetch().textfetchbylistclass("ls-ins-question-wrapper", 0);
			System.out.println("......."+expandedarea);
			Thread.sleep(2000);
			String str=driver.findElement(By.xpath("//label[@class='control-label']")).getText();
			System.out.println(",,,,,,,,"+str);

			if(!expandedarea.contains(str))
			{
				Assert.fail("assessment name not shown");
			}
			if(!expandedarea.contains("Ch"))
			{
				Assert.fail("chapter name not shown");
			}
			if(!expandedarea.contains("Q:"))
			{
				Assert.fail("queastion not shown");
			}
			/*new Click().clickByid("showExpendQuestionIcon");//click on link to expand the search question
			new Click().clickByclassname("expendQuestion");//click on + icon
			String expandedarea1=new TextFetch().textfetchbylistclass("ls-ins-question-wrapper", 0);//fetch text from expand question
			//System.out.println(expandedarea1);
			if(!expandedarea1.contains("Assessment Name"))
			{
				Assert.fail("assessment name not shown");
			}
			if(!expandedarea1.contains("Ch"))
			{
				Assert.fail("chapter name not shown");
			}
			if(!expandedarea1.contains("Q:"))
			{
				Assert.fail("queastion not shown");
			}*/
			new Click().clickbylist("ls-ins-customize-checkbox-small", 0);//checked check box
			//new Click().clickbylist("ls-ins-customize-checkbox-small", 1);//checked check box
			new Click().clickBycssselector("div[data-id='your-question']");//click on your question tab
			Actions ac=new Actions(driver);
			int questionwrapper=driver.findElements(By.className("ls-ins-search-bar")).size();
			System.out.println(questionwrapper);
			/*List<WebElement> allelement=driver.findElements(By.className("ls-ins-search-bar"));
			ac.dragAndDropBy(allelement.get(questionwrapper-1), 0, 200).doubleClick().build().perform();*/
		}
		catch (Exception e)
		{
			Assert.fail("Exception in testcase CustomizeTheAssignment in method customizeTheAssignment ",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void BookmarkedAssignemntshownInMyResourcespage()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("355");// login as instructor
			new Navigator().NavigateTo("Resources");// Navigate to resources
			Thread.sleep(2000);
			new Click().clickbylistcssselector("i[class='addThisToMyResources not-bookmarked ls-resource-bookmark-icon']", 0);
			new Click().clickBycssselector("span[title='My Resources']");
			int resourcescountatmyresources=driver.findElements(By.cssSelector("div[class='ls-assessment-item-sectn-right ls-my-resource-item-section']")).size();
			new Click().clickbylistcssselector("i[class='removeThisFromMyResources bookmarked ls-resource-bookmark-icon']", 0);
			new Click().clickBycssselector("span[title='All Resources']");
			new Click().clickBycssselector("span[title='My Resources']");
			Thread.sleep(2000);
			int resourcescountatmyresources1=driver.findElements(By.cssSelector("div[class='ls-assessment-item-sectn-right ls-my-resource-item-section']")).size();
			if(resourcescountatmyresources1>resourcescountatmyresources)
			{
				Assert.fail("after unbookmarked resources not move from my resources to all resources");
			}
		}
		catch (Exception e)
		{
			Assert.fail("Exception in testcase CustomizeTheAssignment in method BookmarkedAssignemntshownInMyResourcespage ",e);
		}
	}


}
