package com.snapwiz.selenium.tests.staf.orion.testcases.IT16.R1612;

/*
 * brajesh 28/8/2014
 */
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;

public class EnhancementMentorDashboard 
{
	@Test
	public void enhancementMentorDashboard()
	{
		try
		{
			Driver.startDriver();
			String classsectionNameTestdata=ReadTestData.readDataByTagName("", "context_title", "24");
			String classsectionNameTestdata1=ReadTestData.readDataByTagName("", "context_title", "25");
			//Row no-24---(step)1. Login as an mentor----(Excepted)mentor should be navigate to Dashboard and "View Class Reports" dropdown field should be present in the Dashboard			
			new LoginUsingLTI().ltiLogin("24");
							
			//row no 25, (step)1. Login as an mentor ---(Excepted)"View Class Report" filter should be aligned with Class section dropdown filter			
			String classSectionName=Driver.driver.findElement(By.cssSelector("div[class='show-all-sections-dropdown ir-show-sections-dropDown-content']")).getText();
			if(!classsectionNameTestdata.contains(classSectionName))
				Assert.fail("class section name not shown along with 'view all report' dropdown ");
			
			//row no 26, (step-2)Click on "View Class Report" dropdown 
			//row no 27, (step)1. Login as an mentor (Excepted) First filter should have a default text as “ View class reports”.			
			Driver.driver.findElement(By.linkText("View class reports")).click();//click on drop down 
			
			//row no 28(step-3). Select Productivity Report in dropdown field from the dashboard 			
			Driver.driver.findElement(By.linkText("Productivity Report")).click();
			
			//row-28-excepcted-1 :-mentor should navigate to Productivity Report page			
			String productivityReportPage=Driver.driver.findElement(By.id("ir-productivity-report-title")).getText();
			
			//row-29-excepcted-2  :-Productivity Report displayed should be same as when mentor aceses the report through My reports from profile dropdown.
			new Navigator().NavigateToInstructorReport();
			String productivityReportPageAfternavigateFromProfileDropdown=Driver.driver.findElement(By.id("ir-productivity-report-title")).getText();
			if(!productivityReportPage.contains(productivityReportPageAfternavigateFromProfileDropdown))
				Assert.fail("Productivity Report displayed not same as when mentor aceses the 'productivity report' through 'My reports' from profile dropdown");
			
			//row 30 --step--4. Click on Report dropdown field in reports page
			Driver.driver.findElement(By.linkText("Productivity Report")).click();
			Thread.sleep(2000);
			
			//row 30--Excepted:-"View Class Report" option should not be present in dropdown field
			String allDropdownText=Driver.driver.findElement(By.className("al-show-all-reports-dropdown")).getText();
			if(allDropdownText.contains("View class reports"))
				Assert.fail("'View Class Report' option  present in dropdown field after select one report from dropdown");
			
			//row no-31--Step:-5. Click on Orion Dashboard icon
			new Navigator().orionDashboard();//click on orion dashboard
			
			//row no-31 (Excepted-1)-mentor should be navigated to Dashboard
			//row no-32 (Excepted-2)-Default text in report dropdown field should be "View Class Reports"
			Driver.driver.findElement(By.linkText("View class reports")).click();//click on drop down ))
					
			//row 33-step-6. Select Metacognitive Report in dropdown field from the dashboard
			Driver.driver.findElement(By.linkText("Metacognitive Report")).click();
			
			//row-33-(excepted):-mentor should navigate to Metacognitive report page
			String metaconginitiveReportPage=Driver.driver.findElement(By.id("ir-metacognitive-report-title")).getText();
			
			//row-34-(excepted):-Metacognitive Report  displayed should be same as when mentor accesses the report through My reports from profile dropdown
			new Navigator().NavigateToInstructorReport();
			String metaconginitiveReportPageAftermentornavigateFromProfileDropdown=Driver.driver.findElement(By.id("ir-metacognitive-report-title")).getText();
			if(!metaconginitiveReportPage.contains(metaconginitiveReportPageAftermentornavigateFromProfileDropdown))
				Assert.fail("'Metacognitive Report'  displayed not same as when mentor accesses the report through My reports from profile dropdown");
			
			//row no-35--Step:-5. Click on Orion Dashboard icon
			new Navigator().orionDashboard();//click on orion dashboard
			
			//row no-35 (Excepted-1)-mentor should be navigated to Dashboard
			//row no-36 (Excepted-2)-Default text in report dropdown field should be "View Class Reports"
			Driver.driver.findElement(By.linkText("View class reports")).click();//click on drop down ))
			
			//row no-36 step-8. Select Most Challenging Activities Report in dropdown field from the dashboard
			Driver.driver.findElement(By.linkText("Most Challenging Activities")).click();
			
			//row 37 -Excepted-1-mentor should navigate to Most Challenging Activities Report page
			String mostChallengingActivityPage=Driver.driver.findElement(By.id("ir-most-challenging-activities-report-title")).getText();
			
			//row 38--Excepted-2-Most Challenging Activities Report displayed should be same as when mentor accesses the report through My reports from profile dropdown
			new Navigator().NavigateToInstructorReport();
			String mostChallengingActivityPageAfterNvigateToMyReportFromProfileDropDown=Driver.driver.findElement(By.id("ir-most-challenging-activities-report-title")).getText(); 
			if(!mostChallengingActivityPage.contains(mostChallengingActivityPageAfterNvigateToMyReportFromProfileDropDown))
				Assert.fail("Most Challenging Activities Report displayed not  same as when mentor accesses the report through My reports from profile dropdown");			
			
			//row no-39--Step:-5. Click on Orion Dashboard icon
			new Navigator().orionDashboard();//click on orion dashboard
			
			//row no-39 (Excepted-1)-mentor should be navigated to Dashboard
			//row no-40 (Excepted-2)-Default text in report dropdown field should be "View Class Reports"
			Driver.driver.findElement(By.linkText("View class reports")).click();//click on drop down ))
			Thread.sleep(2000);
			
			//row no-41 step-8. 10. Select Performance Report in dropdown field from the dashboard
			Driver.driver.findElement(By.linkText("Performance Report")).click();
			
			//row 41 -Excepted-1-mentor should navigate to Performance Report page
			String PerformanceReportPage=Driver.driver.findElement(By.id("ir-performance-report-title")).getText();
			
			//row 42--Excepted-2-Performance Report displayed should be same as when mentor accesses the report through My reports from profile dropdown
			new Navigator().NavigateToInstructorReport();
			String PerformanceReportPageAfterNvigateToMyReportFromProfileDropDown=Driver.driver.findElement(By.id("ir-performance-report-title")).getText(); 
			if(!PerformanceReportPage.contains(PerformanceReportPageAfterNvigateToMyReportFromProfileDropDown))
				Assert.fail("Performance Report displayed not same as when mentor accesses the report through My reports from profile dropdown");

			//row no-43--Step:-5. Click on Orion Dashboard icon
			new Navigator().orionDashboard();//click on orion dashboard
			
			//row no-43 (Excepted-1)-mentor should be navigated to Dashboard
			//row no-44 (Excepted-2)-Default text in report dropdown field should be "View Class Reports"
			Driver.driver.findElement(By.linkText("View class reports")).click();//click on drop down ))
			Thread.sleep(2000);
			
			//row no-45.step-12 Change the class section through dropdown in Dashboard
			//login as same mentor with different class section
			new LoginUsingLTI().ltiLogin("25");
			Driver.driver.findElement(By.linkText(classsectionNameTestdata1)).click();//click on class section name
			Thread.sleep(2000);
			
			Driver.driver.findElement(By.linkText(classsectionNameTestdata)).click();//select another class section name
			Driver.driver.findElement(By.linkText("View class reports")).click();//click on drop down 
			Thread.sleep(2000);
			
			//row no-45.step-13. Select any report through dropdown field
			Driver.driver.findElement(By.linkText("Productivity Report")).click();
			
			//row no-45 Excepted--1-- mentor should navigate to report page of the selected class-section
			String PerformanceReportPageClassSection1=Driver.driver.findElement(By.id("ir-productivity-report-title")).getText();
			if(!PerformanceReportPageClassSection1.contains("Productivity Report"))
				Assert.fail("mentor not  navigate to report page of the selected class-section");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in test class EnhancementMentorDashboard in test method enhancementMentorDashboard",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
	

}
