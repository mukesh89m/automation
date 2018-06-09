package com.snapwiz.selenium.tests.staf.orion.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.common.Header;
import com.snapwiz.selenium.tests.staf.orion.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class Navigator 
{
	public WebDriver driver= Driver.getWebDriver();
	public Navigator(){
		Config.readconfiguration();
	}
	public void NavigateTo(String navigateTo)
	{
		try
		{
			new UIElement().waitAndFindElement(By.linkText("User profile drop down"));
			driver.findElement(By.linkText("User profile drop down")).click();
			new UIElement().waitAndFindElement(By.partialLinkText(navigateTo));
			driver.findElement(By.partialLinkText(navigateTo)).click();
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in NavigateTo in AppHelper Navigator",e);
		}
	}
	
	public void orionDashboard()
	{
		try
		{
			Thread.sleep(3000);
			Header header=PageFactory.initElements(driver, Header.class);
			header.DashboardIcon.click();
			WebDriverUtil.waitForAjax(driver,10);
	        ReportUtil.log("Navigating to Orion Dashboard", "Navigated to orion dashboard", "info");
        }
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in orionDashboard in AppHelper Navigator",e);
		}
	}
	
	public void NavigateToStudentReport()
	{
		try
		{
			WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("al-user-profile-click-wrapper")));
			WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("myReport")));
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in Navigating to reports in AppHelper Navigator",e);
		}
		
	}
	
	public void NavigateToStudentAllActivity()
	{
		try
		{
			driver.findElement(By.className("al-user-profile-click-wrapper")).click();
			driver.findElement(By.id("ls-my-Activity")).click();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in  Navigate To Student AllActivity in AppHelper Navigator",e);
		}
		
	}
	
	public void NavigateToInstructorReport()
	{
		try
		{
			driver.findElement(By.className("idb-user-profile-click-wrapper")).click();
			driver.findElement(By.id("instructorReports")).click();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			
			Assert.fail("Exception in Navigating to reports in AppHelper Navigator",e);
		}
		
	}
	
	public void NavigateToInstructorSettings()
	{
		try
		{
			driver.findElement(By.className("idb-user-profile-click-wrapper")).click();
			driver.findElement(By.id("instructorSettings")).click();
			Thread.sleep(5000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in NavigateToInstructorSettings to reports in AppHelper Navigator",e);
		}
		
	}

	public void NavigateToReport(String reportName)
	{
		try
		{
			driver.findElement(By.cssSelector("div[title='" + reportName + "']")).click();

			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in Navigating to reports in AppHelper Navigator",e);
		}
		
	}

    /*
	* @Author Mukesh
	* @value 0,1,2,3
	* 0-Productivity Report
	* 1-Metacognitive Report
	* 2-Most Challenging Activities
	* 3-Performance Report
	*
	* */

	public void NavigateToAllReport(int reportIndex)
	{
		try
		{
			driver.findElement(By.xpath("//div[@class='al-show-all-reports-dropdown']//a[@class='sbToggle']")).click(); //click on the report toggle
			List<WebElement> allReport = driver.findElements(By.xpath("//div[@class='al-show-all-reports-dropdown']//ul/li/a"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",allReport.get(reportIndex));
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in NavigateToAllReport to reports in AppHelper Navigator",e);
		}

	}
	 /*
	* @Author Mukesh
	* Desc- it will click on the practice link  where ever its display
	* */

	public void clickOnPracticeLink()
	{
		try
		{
			List<WebElement> link = driver.findElements(By.xpath("//*[@title='Practice']"));
			for (WebElement eachLink:link ){
				if(eachLink.isDisplayed()){
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", eachLink);
					break;
				}
			}

		}
		catch(Exception e)
		{
			Assert.fail("Exception in clickOnPracticeLink to reports in AppHelper Navigator",e);
		}

	}

    //@Author Sumit
    //to navigate to a page from profile drop down present on top right corner for Orion course
    public void navigateFromProfileDropDownForOrion(String pageName)
    {
        try
        {
            if(driver.findElements(By.className("idb-user-profile-name")).size() > 0)
                driver.findElement(By.className("idb-user-profile-name")).click(); //click on profile dropdown of instructor
            else
            driver.findElement(By.className("al-user-profile-name")).click();	//click on profile dropdown of student

            Thread.sleep(2000);
            driver.findElement(By.cssSelector("li[title='"+pageName+"']")).click();//click on page that you want to navigate
            Thread.sleep(2000);
            ReportUtil.log("Navigation to Various Pages","Navigated to '"+pageName+"' from profile dropdown","info");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in navigateFromProfileDropDownForOrion in AppHelper class Navigator", e);
        }
    }
    
  //@Author Sumit
    //to navigate to a report from instructor DashBoard
    public void navigateToReport(String reportName)
    {
        try
        {
            driver.findElement(By.cssSelector("a[selectedid='viewClassReports']")).click();	//click on view Class Reports dropdown
            Thread.sleep(2000);
            driver.findElement(By.linkText(reportName)).click();//click on report that you want to navigate
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in navigateFromProfileDropDownForOrion in AppHelper class Navigator", e);
        }
    }
}
