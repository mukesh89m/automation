package com.snapwiz.selenium.tests.staf.orion.testcases.IT12.R10;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.CMSActions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectCourse;

public class CopyQuestionFromSearchPage 
{
	@Test(priority = 1, enabled = true)
	public void copySelectedQuestionsTopopup() throws Exception
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();
			Thread.sleep(2000);
			new SelectCourse().selectcourse();
			Thread.sleep(2000);
			new SelectCourse().selectChapterByIndex(0);
			new SelectCourse().selectAssessmentByIndex(1);
			Thread.sleep(2000);
			new CMSActions().addQuestion("true-false", "Search true false Question in browse page - Test content");
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			Thread.sleep(4000);
			new CMSActions().searchQuestion("Search true false Question in browse page");
			String checkbox1id =  Driver.driver.findElement(By.className("content-search-qtn-chk")).getAttribute("id");       // Click on CheckBox
		    Driver.driver.findElement(By.xpath("//label[@id='"+checkbox1id+"']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Copy']")).click();
			Thread.sleep(2000);
			String copySelectedQuestionsTo = Driver.driver.findElement(By.cssSelector("Body")).getText();
			if(!copySelectedQuestionsTo.contains("Copy Selected Questions To"))
			{
				Assert.fail("Copy Selected Questions To popup is not opened");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase copySelectedQuestionsTopopup in class CopyQuestionFromSearchPage",e);
		}
				
	}
	
	@Test(priority = 2, enabled = true)
	public void dropdownincopySelectedQuestionsTopopup() throws Exception
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();
			Thread.sleep(2000);
			new SelectCourse().selectcourse();
			Thread.sleep(2000);
			new SelectCourse().selectChapterByIndex(0);
			new SelectCourse().selectAssessmentByIndex(1);
			Thread.sleep(2000);
			new CMSActions().addQuestion("true-false", "Search true false Question in browse page - Test content");
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			Thread.sleep(4000);
			new CMSActions().searchQuestion("Search true false Question in browse page");
			String checkbox1id =  Driver.driver.findElement(By.className("content-search-qtn-chk")).getAttribute("id");       // Click on CheckBox
		    Driver.driver.findElement(By.xpath("//label[@id='"+checkbox1id+"']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Copy']")).click();
			Thread.sleep(2000);
			boolean selectacourse = Driver.driver.findElement(By.cssSelector("a[title='Select a course']")).isDisplayed();
			if(selectacourse==false)
			{
				//Driver.driver.findElement(By.cssSelector("a[title='Select a course']")).click();
				Assert.fail("Select a Course option is not present in Copy Selected Questions To popup");
			}
			boolean selectachapter = Driver.driver.findElement(By.cssSelector("a[title='Select a chapter']")).isDisplayed();
			if(selectachapter==false)
			{
				Assert.fail("Select a chapter option is not present in Copy Selected Questions To popup");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase dropdownincopySelectedQuestionsTopopup in class CopyQuestionFromSearchPage",e);
		}
				
	}
	
	@Test(priority = 3, enabled = true)
	public void selectacourseincopySelectedQuestionsTopopup() throws Exception
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();
			Thread.sleep(2000);
			new SelectCourse().selectcourse();
			Thread.sleep(2000);
			new SelectCourse().selectChapterByIndex(0);
			new SelectCourse().selectAssessmentByIndex(1);
			Thread.sleep(4000);
			new CMSActions().addQuestion("true-false", "Search true false Question in browse page - Test content");
			Thread.sleep(4000);
			Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			Thread.sleep(4000);
			new CMSActions().searchQuestion("Search true false Question in browse page");
			Thread.sleep(2000);
			String checkbox1id =  Driver.driver.findElement(By.className("content-search-qtn-chk")).getAttribute("id");       // Click on CheckBox
		    Driver.driver.findElement(By.xpath("//label[@id='"+checkbox1id+"']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Copy']")).click();
			Thread.sleep(2000);
			String selectacourse = Driver.driver.findElement(By.cssSelector("a[title='Select a course']")).getText();
			if(!selectacourse.equals("Select a course"))
			{
				Assert.fail("Select a course is not displayed by default in Course filter in Copy Selected Questions To popup");
			}			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase selectacourseincopySelectedQuestionsTopopup in class CopyQuestionFromSearchPage",e);
		}				
	}
	
	@Test(priority = 4, enabled = false) //pending
	public void courselistincopySelectedQuestionsTopopup() throws Exception //Database verification required
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();
			Thread.sleep(2000);
			new SelectCourse().selectcourse();
			Thread.sleep(2000);
			//new SelectCourse().selectChapterByIndex(0);
			//new SelectCourse().selectAssessmentByIndex(1);
			Thread.sleep(2000);
			//new CMSActions().addQuestion("true-false", "Search true false Question in browse page - Test content");
			Thread.sleep(2000);
			//Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			Thread.sleep(4000);
			new CMSActions().searchQuestion("Search true false Question in browse page");
			String checkbox1id =  Driver.driver.findElement(By.className("content-search-qtn-chk")).getAttribute("id");       // Click on CheckBox
		    Driver.driver.findElement(By.xpath("//label[@id='"+checkbox1id+"']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Copy']")).click();
			Thread.sleep(2000);	
			List<String> valuesFromDB = new ArrayList<String>() ;
			List<String> valuesFromUI = new ArrayList<String>();
			ResultSet rst =  DBConnect.st.executeQuery("SELECT name FROM t_course where id in (SELECT course_id FROM t_course_user where user_id in (select id from t_user where username = 'wiley1aaa'))");
			   while(rst.next())
				  valuesFromDB.add(rst.getString("name"));
			System.out.println(valuesFromDB);
			
			Driver.driver.findElement(By.cssSelector("a[title='Select a course']")).click();
			Thread.sleep(3000);
			//System.out.println(Driver.driver.findElement(By.className("sbOptions")).getText());
			
			List<WebElement> options = Driver.driver.findElements(By.className("sbOptions"));
			for(WebElement opt : options)
			{
				if(!opt.getText().equals(""))
				valuesFromUI.add(opt.getText());
			}
			List<String> valuesFormattedFromUI = new ArrayList<String>();
			for(String dropval : valuesFromUI)
				valuesFormattedFromUI.add(dropval.replaceAll("\\n", ", "));
			System.out.println("valuesFormattedFromUI:"+valuesFormattedFromUI);
			List<String> unmatchedList = new ArrayList<String>(valuesFromUI);
			unmatchedList.removeAll(valuesFromDB);
			System.out.println("unmatchedList:"+unmatchedList);
			if(unmatchedList.size() > 0) 
				Assert.fail("The list of courses available in the dropdown is not matching with the courses that are accessible to the logged in autho");
			/*String selectacourse = Driver.driver.findElement(By.cssSelector("a[title='Select a course']")).getText();
			 if(selectacourse.equals("Select a course"))
			{
				Assert.fail("Select a course is not displayed by default in Course filter in Copy Selected Questions To popup");				
			}*/			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase courselistincopySelectedQuestionsTopopup in class CopyQuestionFromSearchPage",e);
		}				
	}
	
	@Test(priority = 5, enabled = true)
	public void copyasreferenceonlycheckboxdeselected() throws Exception
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();
			Thread.sleep(2000);
			new SelectCourse().selectcourse();
			Thread.sleep(2000);
			new SelectCourse().selectChapterByIndex(0);
			Thread.sleep(2000);
			new SelectCourse().selectAssessmentByIndex(1);
			Thread.sleep(4000);
			new CMSActions().addQuestion("true-false", "Search true false Question in browse page - Test content");
			Thread.sleep(4000);
			Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			Thread.sleep(6000);
			new CMSActions().searchQuestion("Search true false Question in browse page");
			Thread.sleep(2000);
			String checkbox1id =  Driver.driver.findElement(By.className("content-search-qtn-chk")).getAttribute("id");       // Click on CheckBox
		    Driver.driver.findElement(By.xpath("//label[@id='"+checkbox1id+"']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Copy']")).click();
			Thread.sleep(2000);
			String selectacourse = Driver.driver.findElement(By.cssSelector("a[title='Select a course']")).getText();
			if(!selectacourse.equals("Select a course"))
			{
				Assert.fail("Select a course is not displayed by default in Course filter in Copy Selected Questions To popup");
			}	
			boolean selectacoursecheckboxdeselect = Driver.driver.findElement(By.cssSelector("label[disabled='disabled']")).isDisplayed();
			if(selectacoursecheckboxdeselect==false)
			{
				Assert.fail("Copy as reference only checkbox is not deselected and disabled for the no course");
			}
			Thread.sleep(4000);
			Driver.driver.findElement(By.cssSelector("a[title='Select a course']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(Config.course)).click();
			Thread.sleep(2000);
			boolean selectcurrentcoursecheckboxdeselect = Driver.driver.findElement(By.cssSelector("label[disabled='disabled']")).isDisplayed();
			if(selectcurrentcoursecheckboxdeselect==false)
			{
				Assert.fail("Copy as reference only checkbox is not deselected and disabled for the current course");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase copyasreferenceonlycheckboxdeselected in class CopyQuestionFromSearchPage",e);
		}				
	}
	
	@Test(priority = 6, enabled = true)
	public void defualtselectachapterincopySelectedQuestionsTopopup() throws Exception
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();
			Thread.sleep(2000);
			new SelectCourse().selectcourse();
			Thread.sleep(2000);
			new SelectCourse().selectChapterByIndex(0);
			new SelectCourse().selectAssessmentByIndex(1);
			Thread.sleep(4000);
			new CMSActions().addQuestion("true-false", "Search true false Question in browse page - Test content");
			Thread.sleep(4000);
			Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			Thread.sleep(6000);
			new CMSActions().searchQuestion("Search true false Question in browse page");
			Thread.sleep(2000);
			String checkbox1id =  Driver.driver.findElement(By.className("content-search-qtn-chk")).getAttribute("id");       // Click on CheckBox
		    Driver.driver.findElement(By.xpath("//label[@id='"+checkbox1id+"']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Copy']")).click();
			Thread.sleep(2000);
			String selectacourse = Driver.driver.findElement(By.cssSelector("a[title='Select a chapter']")).getText();
			if(!selectacourse.equals("Select a chapter"))
			{
				Assert.fail("Select a Chapter is not displayed by default in Chapter filter in Copy Selected Questions To popup");
			}			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase defualtselectachapterincopySelectedQuestionsTopopup in class CopyQuestionFromSearchPage",e);
		}				
	}
	
	@Test(priority = 7, enabled = true)
	public void enablechapterfilterbyselectingcourse() throws Exception
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();
			Thread.sleep(2000);
			new SelectCourse().selectcourse();
			Thread.sleep(2000);
			new SelectCourse().selectChapterByIndex(0);
			Thread.sleep(2000);
			new SelectCourse().selectAssessmentByIndex(1);
			Thread.sleep(4000);
			new CMSActions().addQuestion("true-false", "Search true false Question in browse page - Test content");
			Thread.sleep(4000);
			Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			Thread.sleep(6000);
			new CMSActions().searchQuestion("Search true false Question in browse page");
			Thread.sleep(2000);
			String checkbox1id =  Driver.driver.findElement(By.className("content-search-qtn-chk")).getAttribute("id");       // Click on CheckBox
		    Driver.driver.findElement(By.xpath("//label[@id='"+checkbox1id+"']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Copy']")).click();
			Thread.sleep(4000);
			Driver.driver.findElement(By.cssSelector("a[title='Select a course']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(Config.course)).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("a[title='Select a chapter']")).click();
			Thread.sleep(5000);
			Driver.driver.findElement(By.partialLinkText("Ch 1:")).click();
			//Driver.driver.findElement(By.xpath("//a[starts-with(@title, 'Ch 1:')]")).click();
			//((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[starts-with(@title, 'Ch 1: Introduction to Financial Statements')]")));
			Thread.sleep(4000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase enablechapterfilterbyselectingcourse in class CopyQuestionFromSearchPage",e);
		}				
	}
	
	@Test(priority = 8, enabled = true)
	public void assesmentlistdisplaybyselctingChapter() throws Exception
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();
			Thread.sleep(2000);
			new SelectCourse().selectcourse();
			Thread.sleep(2000);
			new SelectCourse().selectChapterByIndex(0);
			Thread.sleep(2000);
			new SelectCourse().selectAssessmentByIndex(1);
			Thread.sleep(4000);
			new CMSActions().addQuestion("true-false", "Search true false Question in browse page - Test content");
			Thread.sleep(4000);
			Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			Thread.sleep(6000);
			new CMSActions().searchQuestion("Search true false Question in browse page");
			Thread.sleep(2000);
			String checkbox1id =  Driver.driver.findElement(By.className("content-search-qtn-chk")).getAttribute("id");       // Click on CheckBox
		    Driver.driver.findElement(By.xpath("//label[@id='"+checkbox1id+"']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Copy']")).click();
			Thread.sleep(4000);
			Driver.driver.findElement(By.cssSelector("a[title='Select a course']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(Config.course)).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("a[title='Select a chapter']")).click();
			Thread.sleep(5000);
			Driver.driver.findElement(By.partialLinkText("Ch 1:")).click();
			Thread.sleep(4000);
			boolean assessmentlist = Driver.driver.findElement(By.className("content-right-assessment-section")).isDisplayed();
			if(assessmentlist==false)
			{
				Assert.fail("Assessment list is not present by selecting a Chapter");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase assesmentlistdisplaybyselctingChapter in class CopyQuestionFromSearchPage",e);
		}				
	}
	
	@Test(priority = 9, enabled = true)
	public void cancelanddonebuttons() throws Exception
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();
			Thread.sleep(2000);
			new SelectCourse().selectcourse();
			Thread.sleep(2000);
			new SelectCourse().selectChapterByIndex(0);
			Thread.sleep(2000);
			new SelectCourse().selectAssessmentByIndex(1);
			Thread.sleep(4000);
			new CMSActions().addQuestion("true-false", "Search true false Question in browse page - Test content");
			Thread.sleep(4000);
			Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			Thread.sleep(6000);
			new CMSActions().searchQuestion("Search true false Question in browse page");
			Thread.sleep(2000);
			String checkbox1id =  Driver.driver.findElement(By.className("content-search-qtn-chk")).getAttribute("id");       // Click on CheckBox
		    Driver.driver.findElement(By.xpath("//label[@id='"+checkbox1id+"']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Copy']")).click();
			Thread.sleep(4000);
			Driver.driver.findElement(By.cssSelector("a[title='Select a course']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(Config.course)).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("a[title='Select a chapter']")).click();
			Thread.sleep(5000);
			Driver.driver.findElement(By.partialLinkText("Ch 1:")).click();
			Thread.sleep(4000);
			boolean cancelbutton = Driver.driver.findElement(By.className("cancel-select-assement")).isDisplayed();
			if(cancelbutton==false)
			{
				Assert.fail("Cancel button is not present by selecting a Chapter in Copy Selected Questions To popup");
			}
			boolean donebutton = Driver.driver.findElement(By.className("add-assesments-in-contentSearch")).isDisplayed();
			if(donebutton==false)
			{
				Assert.fail("Done button is not present by selecting a Chapter in Copy Selected Questions To popup");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase cancelanddonebuttons in class CopyQuestionFromSearchPage",e);
		}				
	}
	
	@Test(priority = 10, enabled = true)
	public void clickoncancelbutton() throws Exception
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();
			Thread.sleep(2000);
			new SelectCourse().selectcourse();
			Thread.sleep(2000);
			new SelectCourse().selectChapterByIndex(0);
			Thread.sleep(2000);
			new SelectCourse().selectAssessmentByIndex(1);
			Thread.sleep(4000);
			new CMSActions().addQuestion("true-false", "Search true false Question in browse page - Test content");
			Thread.sleep(4000);
			Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			Thread.sleep(6000);
			new CMSActions().searchQuestion("Search true false Question in browse page");
			Thread.sleep(2000);
			String checkbox1id =  Driver.driver.findElement(By.className("content-search-qtn-chk")).getAttribute("id");       // Click on CheckBox
		    Driver.driver.findElement(By.xpath("//label[@id='"+checkbox1id+"']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Copy']")).click();
			Thread.sleep(4000);
			Driver.driver.findElement(By.cssSelector("a[title='Select a course']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(Config.course)).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("a[title='Select a chapter']")).click();
			Thread.sleep(5000);
			Driver.driver.findElement(By.partialLinkText("Ch 1:")).click();
			Thread.sleep(4000);
			Driver.driver.findElement(By.className("cancel-select-assement")).click();	
			Thread.sleep(2000);
			boolean closepopup = Driver.driver.findElement(By.className("content-search-select-title")).isDisplayed();
			if(closepopup==true)
			{
				Assert.fail("Copy Selected Questions To popup is not closed by clicking on Cancel button");
			}			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickoncancelbutton in class CopyQuestionFromSearchPage",e);
		}				
	}
	
	@AfterMethod
	 public void tearDown() throws Exception
	 {
	 Driver.driver.quit();
	 }

}
