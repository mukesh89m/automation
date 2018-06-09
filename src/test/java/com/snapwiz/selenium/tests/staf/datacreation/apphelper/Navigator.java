package com.snapwiz.selenium.tests.staf.datacreation.apphelper;

import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class Navigator 
{

	public void NavigateTo(String navigateTo) {
        try {
            Thread.sleep(3000);
			/*if(Driver.driver.getCurrentUrl().contains("learningContent"))
			{
			int headervalue=Driver.driver.findElements(By.cssSelector("a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']")).size();
			if(headervalue>=1)
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.cssSelector("a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']")));
			}*/
            Driver.driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']")).click();
            Thread.sleep(3000);
            if (Driver.driver.findElements(By.cssSelector("a[class='navigator ls-toc-sprite selected']")).size() == 0) {
                Driver.driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']")).click();
                Thread.sleep(3000);
            }
            if (navigateTo.equals("My Journal")) navigateTo = "My Notes";
            if (navigateTo.equals("Learning Content")) navigateTo = "e-Textbook";
            if (navigateTo.equals("eTextbook")) navigateTo = "e-Textbook";
            if (navigateTo.equals("eTextBook")) navigateTo = "e-Textbook";
            if (navigateTo.equals("Assignment Policies")) navigateTo = "Policies";
            if (navigateTo.equals("Assignments")) {
                if (Driver.driver.getCurrentUrl().contains("learningSpaceInstructorDashboard")) {
                    navigateTo = "Summary";
                }
            }
            Thread.sleep(3000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText(navigateTo)));
            //Driver.driver.findElement(By.linkText(navigateTo)).click();
            Thread.sleep(3000);
            if (navigateTo.equals("Learning Content")) {
                int helppage = Driver.driver.findElements(By.className("close-help-page")).size();
                if (helppage == 1)
                    Driver.driver.findElement(By.className("close-help-page")).click();
            }
            if (navigateTo.equals("Instructor Resources")) {
                if (Driver.driver.findElement(By.cssSelector("div[class='tab active']")).getText().equals("My Resources")) {//Opening All Resources tab if not opened after clicking on New Assignment button
                    System.out.println("Opening All Resources tab");
                    Driver.driver.findElement(By.cssSelector("span[title='All Resources']")).click();
                }
            }
          /*  if(Driver.driver.findElements(By.className("swhelp-button-cancel")).size() > 0)
                Driver.driver.findElement(By.className("swhelp-button-cancel")).click();*/
            Thread.sleep(5000);
        } catch (Exception e)
        {
            Assert.fail("Exception in NavigateTo in AppHelper Navigator",e);
        }
    }
	
	public void orionDashboard()
	{
		try
		{
		Thread.sleep(5000);
		Driver.driver.findElement(By.cssSelector("img[title='ORION Dashboard']")).click();
		}
		catch(Exception e)
		{

			Assert.fail("Exception in orionDashboard in AppHelper Navigator",e);
		}
	}
	
	public void NavigateToStudentReport()
	{
		try
		{
			Driver.driver.findElement(By.className("al-user-profile-click-wrapper")).click();
			Driver.driver.findElement(By.id("myReport")).click();
			//Driver.driver.findElement(By.linkText("Most Challenging Activities")).click();
			//Driver.driver.findElement(By.linkText(reportName)).click();
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
			Driver.driver.findElement(By.className("al-user-profile-click-wrapper")).click();
			Driver.driver.findElement(By.id("ls-my-Activity")).click();
			//Driver.driver.findElement(By.linkText("Most Challenging Activities")).click();
			//Driver.driver.findElement(By.linkText(reportName)).click();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{

			Assert.fail("Exception in  Navigate To Student AllActivity in AppHelper Navigator",e);
		}
		
	}
	
	public void NavigateToInstructorReport()
	{
		try
		{
			Driver.driver.findElement(By.className("idb-user-profile-click-wrapper")).click();
			Driver.driver.findElement(By.id("instructorReports")).click();
			//Driver.driver.findElement(By.linkText("Most Challenging Activities")).click();
			//Driver.driver.findElement(By.linkText(reportName)).click();
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
			Driver.driver.findElement(By.className("idb-user-profile-click-wrapper")).click();
			Driver.driver.findElement(By.id("instructorSettings")).click();
			//Driver.driver.findElement(By.linkText("Most Challenging Activities")).click();
			//Driver.driver.findElement(By.linkText(reportName)).click();
			Thread.sleep(5000);
		}
		catch(Exception e)
		{

			Assert.fail("Exception in NavigateToInstructorSettings to reports in AppHelper Navigator",e);
		}
		
	}
	
	public void NavigateToReport(String reportName)
	{
		try
		{
			Driver.driver.findElement(By.cssSelector("div[title='"+reportName+"']")).click();
			/*Driver.driver.findElement(By.linkText("Most Challenging Activities")).click();
			Driver.driver.findElement(By.linkText(reportName)).click();*/
			Thread.sleep(3000);
		}
		catch(Exception e)
		{

			Assert.fail("Exception in Navigating to reports in AppHelper Navigator",e);
		}
		
	}
    //@Author Sumit
    //to navigate to a page from profile drop down present on top right corner for Orion course
    public void navigateFromProfileDropDownForOrion(String pageName)
    {
        try
        {
            if(Driver.driver.findElements(By.className("idb-user-profile-name")).size() > 0)
                Driver.driver.findElement(By.className("idb-user-profile-name")).click(); //click on profile dropdown of instructor
            else
            Driver.driver.findElement(By.className("al-user-profile-name")).click();	//click on profile dropdown of student

            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("li[title='"+pageName+"']")).click();//click on page that you want to navigate
            Thread.sleep(2000);
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
            Driver.driver.findElement(By.cssSelector("a[selectedid='viewClassReports']")).click();	//click on view Class Reports dropdown
            Thread.sleep(2000);
            Driver.driver.findElement(By.linkText(reportName)).click();//click on report that you want to navigate
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in navigateFromProfileDropDownForOrion in AppHelper class Navigator", e);
        }
    }
}
