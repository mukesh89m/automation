package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

/*
 * @Brajesh 
 * add announcement by publisher admin
 */
public class AddAnnouncement extends Driver
{
	public void addAnnouncement(String dataIndex,String descrption)
	{
        WebDriver driver=Driver.getWebDriver();
        try
		{
			String enddate=ReadTestData.readDataByTagName("", "enddate", dataIndex);
			new DirectLogin().publisherAdmin();
            new Navigator().publisherAdminNavigateTo("Announcements");//Navigate to Announcements page
			new Click().clickByclassname("pa-add-announcements-button");//click on add new announcement button
			driver.findElement(By.className("pa-anouncement-textbox")).clear();
			driver.findElement(By.className("pa-anouncement-textbox")).sendKeys(descrption);//put description
			driver.findElement(By.xpath("//*[@id='pa_assignment_end_date']")).click();//click on end date
			new Click().clickBycssselector("a[data-handler='next']");//clcik for next months
			new Click().clickbylinkText(enddate);//select end date
			driver.findElement(By.xpath("//*[@id='pa_assignment_end_time']")).click();
			driver.findElement(By.xpath("//*[@class='ui-timepicker-list']/li[2]")).click();//select time
			new Click().clickBycssselector("span[class='pa-dialogBox-Save pa-resource-save-btn']");//click on save button
		}
		catch(Exception e)
		{
			Assert.fail("exception in app helper AddAnnouncement in method addAnnouncement",e);
		}
	}

	/*
	 * Brajesh
	 * Announcement functionality for student,instructor and mentor on his dashboard
	 */

	public void annoncementFunctionality(String dataindex,int countofannouncement)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			int countOfAnnouncement=driver.findElements(By.className("announcements-data")).size();
			if(countOfAnnouncement<countofannouncement)
				Assert.fail("added announcement not shown on dashboard of user");
			new Click().clickByid("announcements-close");//close announcement
			int announcementDisplay=driver.findElements(By.id("announcements-outer-wrapper")).size();//verify announcement area
			if(announcementDisplay!=0)
				Assert.fail("announcement display not closed after click on 'x' button");
			new Navigator().NavigateTo("Dashboard");
			int announcementDisplayAfteRefreshDashboard=driver.findElements(By.id("announcements-outer-wrapper")).size();//verify announcement area
			if(announcementDisplayAfteRefreshDashboard==0)
				Assert.fail("announcement not display after referesh dashboard");
			new Navigator().NavigateTo("Course Stream");//navigate to course stream
			int announcementDisplayAfteNavigateToOtherpage=driver.findElements(By.id("announcements-outer-wrapper")).size();//verify announcement area
			if(announcementDisplayAfteNavigateToOtherpage!=0)
				Assert.fail("announcement  display after navigate to other page");
			new Navigator().NavigateTo("Dashboard");
			int announcementDisplayAfteRefreshDashboard1=driver.findElements(By.id("announcements-outer-wrapper")).size();//verify announcement area
			if(announcementDisplayAfteRefreshDashboard1==0)
				Assert.fail("announcement not display after back back to dashbaord from other page");
			new Click().clickByid("announcements-close");//close announcement
			driver.navigate().refresh();//refresh browser
			int announcementDisplayAfteRefreshBrowser=driver.findElements(By.id("announcements-outer-wrapper")).size();//verify announcement area
			if(announcementDisplayAfteRefreshBrowser==0)
				Assert.fail("announcement not display after referesh browser");
		}
		catch(Exception e)
		{
			Assert.fail("exception in app helper AddAnnouncement in method annoncementFunctionality",e);
		}
	}

	public void annoncementFunctionalityOrion(String dataindex,int countofannouncement)
	{
		WebDriver driver=Driver.getWebDriver();try
		{
			int countOfAnnouncement=driver.findElements(By.className("announcements-data")).size();
			if(countOfAnnouncement<countofannouncement)
				Assert.fail("added announcement not shown on dashboard of user");
			new Click().clickByid("announcements-close");//close announcement
			int announcementDisplay=driver.findElements(By.id("announcements-outer-wrapper")).size();//verify announcement area
			if(announcementDisplay!=0)
				Assert.fail("announcement display not closed after click on 'x' button");
			new Navigator().orionDashboard();
			int announcementDisplayAfteRefreshDashboard=driver.findElements(By.id("announcements-outer-wrapper")).size();//verify announcement area
			if(announcementDisplayAfteRefreshDashboard==0)
				Assert.fail("announcement not display after referesh dashboard");
			Thread.sleep(2000);
			new Navigator().navigateFromProfileDropDownForOrion("My Profile");//navigate to my profile
			int announcementDisplayAfteNavigateToOtherpage=driver.findElements(By.id("announcements-outer-wrapper")).size();//verify announcement area
			if(announcementDisplayAfteNavigateToOtherpage!=0)
				Assert.fail("announcement  display after navigate to other page");
			new Navigator().orionDashboard();
			int announcementDisplayAfteRefreshDashboard1=driver.findElements(By.id("announcements-outer-wrapper")).size();//verify announcement area
			if(announcementDisplayAfteRefreshDashboard1==0)
				Assert.fail("announcement not display after back back to dashbaord from other page");
			new Click().clickByid("announcements-close");//close announcement
			driver.navigate().refresh();//refresh browser
			int announcementDisplayAfteRefreshBrowser=driver.findElements(By.id("announcements-outer-wrapper")).size();//verify announcement area
			if(announcementDisplayAfteRefreshBrowser==0)
				Assert.fail("announcement not display after referesh browser");
		}
		catch(Exception e)
		{
			Assert.fail("exception in app helper AddAnnouncement in method annoncementFunctionalityOrion",e);
		}
	}

}
