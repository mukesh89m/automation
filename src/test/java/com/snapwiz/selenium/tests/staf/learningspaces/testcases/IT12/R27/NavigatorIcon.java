package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R27;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class NavigatorIcon extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void navigatorIcon()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("219");//login as instructor
			String wileyLogo = driver.findElement(By.cssSelector("a[class='ls-site-logo ls-fx--opacity']")).getAttribute("innerHTML");
			if(!wileyLogo.contains("logo.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The WileyPlus logo is absent.");
			}
			String navigator = driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']")).getCssValue("background-image");
			if(!navigator.contains("ls-study-plan-sprite.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The navigator icon is absent.");
			}
			
			new Navigator().NavigateTo("Course Stream");
			Thread.sleep(3000);
			String courseStream = driver.getCurrentUrl();
			if(!courseStream.contains("coursestream"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Course Stream from navigator instructor doesn't navigate to Course Stream.");
			}
			String title = new TextFetch().textfetchbyclass("ls-header__title-text");
			if(!title.equals(Config.course))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the Course Stream the Course Name is absent in the header.");
			}
            boolean header = driver.findElement(By.cssSelector("div[class='ls-student-header-wrapper ls-instructor-header-wrapper']")).isDisplayed();
			if(header != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Course Stream the header is minimized.");
			}
			boolean classSection = driver.findElement(By.cssSelector("div[class='ls-class-section-drop-down-wrapper scrollbar-wrapper']")).isDisplayed();
			if(classSection != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Course Stream the class section dropdown is not displayed.");
			}
			int wileyLogo1 = driver.findElements(By.cssSelector("a[class='ls-site-logo ls-fx--opacity']")).size();
			if(wileyLogo1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The WileyPlus logo is present when we naviaget to Course Stream from the main Navigator.");
			}
			new Navigator().NavigateTo("eTextbook");
			Thread.sleep(3000);
			
			String eTextBook = driver.getCurrentUrl();
			if(!eTextBook.contains("eTextBook"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking eTextBook from navigator instructor doesn't navigate to eTextBook.");
			}
			
			new Navigator().NavigateTo("Assignments");
			Thread.sleep(3000);
			String assignments = driver.getCurrentUrl();
			if(!assignments.contains("assignment"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Assignments from navigator instructor doesn't navigate to Assignments page.");
			}
			String title1 = new TextFetch().textfetchbyclass("ls-header__title-text");
			if(!title1.equals(Config.course))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the Assignments page the Course Name is absent in the header.");
			}
			int wileyLogo2 = driver.findElements(By.cssSelector("a[class='ls-site-logo ls-fx--opacity']")).size();
			if(wileyLogo2 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The WileyPlus logo is present when we naviaget to Assignments page from the main Navigator.");
			}
            boolean header1 = driver.findElement(By.cssSelector("div[class='ls-student-header-wrapper ls-instructor-header-wrapper']")).isDisplayed();
			if(header1 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Assignments page the header is minimized.");
			}
			boolean classSection1 = driver.findElement(By.cssSelector("div[class='ls-class-section-drop-down-wrapper scrollbar-wrapper']")).isDisplayed();
			if(classSection1 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Assignments page the class section dropdown is not displayed.");
			}
			new Navigator().NavigateTo("Assignment Policies");
			Thread.sleep(3000);
			String assignmentPolicy = driver.getCurrentUrl();
			if(!assignmentPolicy.contains("assignmentPolicy"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking assignment Policy from navigator instructor doesn't navigate to assignment Policy page.");
			}
			int wileyLogo3 = driver.findElements(By.cssSelector("a[class='ls-site-logo ls-fx--opacity']")).size();
			if(wileyLogo3 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The WileyPlus logo is present when we naviaget to Assignment Policies page from the main Navigator.");
			}
			String title2 = new TextFetch().textfetchbyclass("ls-header__title-text");
			if(!title2.equals(Config.course))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the Assignment Policies page the Course Name is absent in the header.");
			}
            boolean header2 = driver.findElement(By.cssSelector("div[class='ls-student-header-wrapper ls-instructor-header-wrapper']")).isDisplayed();
			if(header2 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Assignment Policies page the header is minimized.");
			}
			boolean classSection2 = driver.findElement(By.cssSelector("div[class='ls-class-section-drop-down-wrapper scrollbar-wrapper']")).isDisplayed();
			if(classSection2 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Assignment Policies page the class section dropdown is not displayed.");
			}
			//new Navigator().NavigateTo("My Reports");
			
			new Navigator().NavigateTo("Proficiency Report");
			Thread.sleep(3000);
			String proficiencyReport = driver.getCurrentUrl();
			if(!proficiencyReport.contains("instProfRepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Proficiency Report from navigator instructor doesn't navigate to assignment Policy page.");
			}
			int wileyLogo4 = driver.findElements(By.cssSelector("a[class='ls-site-logo ls-fx--opacity']")).size();
			if(wileyLogo4 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The WileyPlus logo is present when we naviaget to Proficiency Report page from the main Navigator.");
			}
			String title3 = new TextFetch().textfetchbyclass("ls-header__title-text");
			if(!title3.equals(Config.course))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the Proficiency Report page the Course Name is absent in the header.");
			}
            boolean header3 = driver.findElement(By.cssSelector("div[class='ls-student-header-wrapper ls-instructor-header-wrapper']")).isDisplayed();
			if(header3 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Proficiency Report page the header is minimized.");
			}
			boolean classSection3 = driver.findElement(By.cssSelector("div[class='ls-class-section-drop-down-wrapper scrollbar-wrapper']")).isDisplayed();
			if(classSection3 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Proficiency Report page the class section dropdown is not displayed.");
			}
			new Navigator().NavigateTo("Metacognitive Report");
			Thread.sleep(3000);
			String metacognitiveReport = driver.getCurrentUrl();
			if(!metacognitiveReport.contains("instMetaCogRepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking metacognitive Report  from navigator instructor doesn't navigate to metacognitive Report page.");
			}
			int wileyLogo5 = driver.findElements(By.cssSelector("a[class='ls-site-logo ls-fx--opacity']")).size();
			if(wileyLogo5 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The WileyPlus logo is present when we naviaget to Metacognitive Report page from the main Navigator.");
			}
			String title4 = new TextFetch().textfetchbyclass("ls-header__title-text");
			if(!title4.equals(Config.course))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the Metacognitive Report page the Course Name is absent in the header.");
			}
            boolean header4 = driver.findElement(By.cssSelector("div[class='ls-student-header-wrapper ls-instructor-header-wrapper']")).isDisplayed();
			if(header4 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Metacognitive Report page the header is minimized.");
			}
			boolean classSection4 = driver.findElement(By.cssSelector("div[class='ls-class-section-drop-down-wrapper scrollbar-wrapper']")).isDisplayed();
			if(classSection4 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Metacognitive Report page the class section dropdown is not displayed.");
			}
			new Navigator().NavigateTo("Productivity Report");
			Thread.sleep(3000);
			String productivityReport = driver.getCurrentUrl();
			if(!productivityReport.contains("instProdRepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking productivity Report  from navigator instructor doesn't navigate to productivity Report page.");
			}
			int wileyLogo6 = driver.findElements(By.cssSelector("a[class='ls-site-logo ls-fx--opacity']")).size();
			if(wileyLogo6 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The WileyPlus logo is present when we naviaget to Productivity Report page from the main Navigator.");
			}
			String title5 = new TextFetch().textfetchbyclass("ls-header__title-text");
			if(!title5.equals(Config.course))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the Productivity Report page the Course Name is absent in the header.");
			}
            boolean header5 = driver.findElement(By.cssSelector("div[class='ls-student-header-wrapper ls-instructor-header-wrapper']")).isDisplayed();
			if(header5 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Productivity Report page the header is minimized.");
			}
			boolean classSection5 = driver.findElement(By.cssSelector("div[class='ls-class-section-drop-down-wrapper scrollbar-wrapper']")).isDisplayed();
			if(classSection5 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Productivity Report page the class section dropdown is not displayed.");
			}
			new Navigator().NavigateTo("Most Challenging Activities Report");
			Thread.sleep(3000);
			String MCAReport = driver.getCurrentUrl();
			if(!MCAReport.contains("instMCARepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Most Challenging Activities Report  from navigator instructor doesn't navigate to Most Challenging Activities Report page.");
			}
			int wileyLogo7 = driver.findElements(By.cssSelector("a[class='ls-site-logo ls-fx--opacity']")).size();
			if(wileyLogo7 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The WileyPlus logo is present when we naviaget to Most Challenging Activities Report page from the main Navigator.");
			}
			String title6 = new TextFetch().textfetchbyclass("ls-header__title-text");
			if(!title6.equals(Config.course))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the Most Challenging Activities Report page the Course Name is absent in the header.");
			}
            boolean header6 = driver.findElement(By.cssSelector("div[class='ls-student-header-wrapper ls-instructor-header-wrapper']")).isDisplayed();
			if(header6 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Most Challenging Activities Report page the header is minimized.");
			}
			boolean classSection6 = driver.findElement(By.cssSelector("div[class='ls-class-section-drop-down-wrapper scrollbar-wrapper']")).isDisplayed();
			if(classSection6 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Most Challenging Activities Report page the class section dropdown is not displayed.");
			}
			new Navigator().NavigateTo("Engagement Report");
			Thread.sleep(3000);
			String engagementReport = driver.getCurrentUrl();
			if(!engagementReport.contains("instEngRepo"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Engagement Report  from navigator instructor doesn't navigate to Engagement Report page.");
			}
			int wileyLogo8 = driver.findElements(By.cssSelector("a[class='ls-site-logo ls-fx--opacity']")).size();
			if(wileyLogo8 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The WileyPlus logo is present when we naviaget to Most Challenging Activities Report page from the main Navigator.");
			}
			String title7 = new TextFetch().textfetchbyclass("ls-header__title-text");
			if(!title7.equals(Config.course))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the Engagement Report page the Course Name is absent in the header.");
			}
            boolean header7 = driver.findElement(By.cssSelector("div[class='ls-student-header-wrapper ls-instructor-header-wrapper']")).isDisplayed();
			if(header7 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Engagement Report page the header is minimized.");
			}
			boolean classSection7 = driver.findElement(By.cssSelector("div[class='ls-class-section-drop-down-wrapper scrollbar-wrapper']")).isDisplayed();
			if(classSection7 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Engagement Report page the class section dropdown is not displayed.");
			}
			new Navigator().NavigateTo("Resources");
			Thread.sleep(3000);
			String resources = driver.getCurrentUrl();
			if(!resources.contains("resources"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Resources from navigator instructor doesn't navigate to Resources page.");
			}
			int wileyLogo9 = driver.findElements(By.cssSelector("a[class='ls-site-logo ls-fx--opacity']")).size();
			if(wileyLogo9 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The WileyPlus logo is present when we naviaget to Resources page from the main Navigator.");
			}
			String title8 = new TextFetch().textfetchbyclass("ls-header__title-text");
			if(!title8.equals(Config.course))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the Resources page the Course Name is absent in the header.");
			}
            boolean header8 = driver.findElement(By.cssSelector("div[class='ls-student-header-wrapper ls-instructor-header-wrapper']")).isDisplayed();
			if(header8 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Resources page the header is minimized.");
			}
			boolean classSection8 = driver.findElement(By.cssSelector("div[class='ls-class-section-drop-down-wrapper scrollbar-wrapper']")).isDisplayed();
			if(classSection8 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Resources page the class section dropdown is not displayed.");
			}
			new Navigator().NavigateTo("Dashboard");
			Thread.sleep(3000);
			String dashboard = driver.getCurrentUrl();
			if(!dashboard.contains("Dashboard"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Dashboard from navigator instructor doesn't navigate to Dashboard page.");
			}
            boolean header9 = driver.findElement(By.cssSelector("div[class='ls-student-header-wrapper ls-instructor-header-wrapper']")).isDisplayed();
			if(header9 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Dashboard the header is minimized.");
			}
			boolean classSection9 = driver.findElement(By.cssSelector("div[class='ls-class-section-drop-down-wrapper scrollbar-wrapper']")).isDisplayed();
			if(classSection9 != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to Dashboard the class section dropdown is not displayed.");
			}
			String wileyLogon = driver.findElement(By.cssSelector("a[class='ls-site-logo ls-fx--opacity']")).getAttribute("innerHTML");
			if(!wileyLogon.contains("logo.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The WileyPlus logo is absent when we naviaget to Dashboard from the main Navigator.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase navigatorIcon in class NavigatorIcon.",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void eTextbook()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("229");//login as instructor
			new Navigator().NavigateTo("eTextbook");//go to eTextbook
			boolean sprite = driver.findElement(By.className("ls-site-nav-options")).isDisplayed();
			if(sprite != false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to etext book the navigator sprite is not closed by default.");
			}
            boolean header = driver.findElement(By.cssSelector("div[class='ls-student-header-wrapper ls-instructor-header-wrapper']")).isDisplayed();
			if(header != true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After opening TOC the header is minimized by default.");
			}

			new TOCShow().tocHide();//hide the TOC
			Thread.sleep(2000);
			int wileyLogo = driver.findElements(By.cssSelector("a[class='ls-site-logo ls-fx--opacity']")).size();
			if(wileyLogo != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The WileyPlus logo is present in the header");
			}
            String navigator = driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']")).getCssValue("background-image");
            if(!navigator.contains("ls-study-plan-sprite.png"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("The navigator icon is absent.");
            }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase eTextbook in class NavigatorIcon.",e);
		}
	}

}
