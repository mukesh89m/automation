package com.snapwiz.selenium.tests.staf.orion.testcases.IT12.R11;

import java.sql.ResultSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SearchQuestion;
import com.snapwiz.selenium.tests.staf.orion.uihelper.ComboBox;


public class AccessPassageQuestionInTheSearchPage {
	
	@Test(priority = 1, enabled = true)
	public void accessPassageQuestionInTheSearchPage()
	{
		try
		{
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "1");
			String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", "1");
			String questiontext = ReadTestData.readDataByTagName("", "questiontext", "1");
			Driver.startDriver();
			new Assignment().create(1);//create assignment
			new Assignment().addQuestions(1, "qtn-passage-based-img", assignmentname, false, false, difficultylevel, true);//add passage type question
			
			new SearchQuestion().searchquestion(questiontext);//search question
			//verify the passage based question icon
			String icon = Driver.driver.findElement(By.cssSelector("img[title='Reading comprehension question']")).getAttribute("src");
			if(!icon.contains("passagebased.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On searching passage based question it doesn't show a 'Visual indicator' icon below the checkbox indicating a passage type question.");
			}
			//list all the search result
			List<WebElement> allSearch = Driver.driver.findElements(By.className("content-search-title-section"));
			Actions action = new Actions(Driver.driver);
			WebElement we = allSearch.get(1);// the search result is at 1st index
			action.moveToElement(we).build().perform();
			Thread.sleep(3000);
			//click on Quick preview
			List<WebElement> allQuickPreview = Driver.driver.findElements(By.className("expand-question-content"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",allQuickPreview.get(1));
			Thread.sleep(3000);
			//verify the passage based question icon
			String icon1 = Driver.driver.findElement(By.cssSelector("img[title='Reading comprehension question']")).getAttribute("src");
			if(!icon1.contains("passagebased.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking quick preview it doesn't show a 'Visual indicator' icon below the checkbox indicating a passage type question.");
			}
			//verify the chapter name
			String chName = Driver.driver.findElement(By.id("search-chapter-blk")).getText();
			if(!chName.contains("Ch"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Quick Preview it doesnt show the Chapter name.");
			}
			//verify assessment name
			String assessmentName = Driver.driver.findElement(By.id("search-diff-level-blks")).getText();
			if(!assessmentName.contains("Assessment Name"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Quick Preview it doesnt show the Assessment name.");
			}
			//again list all the search results
			List<WebElement> allSearch1 = Driver.driver.findElements(By.className("content-search-title-section"));
			Actions action1 = new Actions(Driver.driver);
			WebElement we1 = allSearch1.get(1);
			action1.moveToElement(we1).build().perform();
			Thread.sleep(3000);
			//click to collapse icon
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.className("collapse-question-content")));
			Thread.sleep(3000);
			//verify the passage based question icon
			String icon2 = Driver.driver.findElement(By.cssSelector("img[title='Reading comprehension question']")).getAttribute("src");
			if(!icon2.contains("passagebased.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking collappse icon quick preview it doesn't show a 'Visual indicator' icon below the checkbox indicating a passage type question.");
			}
			//list all the checkbox
			List<WebElement> allCheckBox = Driver.driver.findElements(By.className("question-check-box-div"));
			allCheckBox.get(1).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("#content-search-review-btn > #content-search-icon")).click();
			Thread.sleep(2000);
			//verify the passage based question icon
			String icon3 = Driver.driver.findElement(By.cssSelector("img[title='Reading comprehension question']")).getAttribute("src");
			if(!icon3.contains("passagebased.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Selecting a checkbox and clicking 'Launch review' doesn't show a 'Visual indicator' icon below the checkbox indicating a passage type question.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase accessPassageQuestionInTheSearchPage in class AccessPassageQuestionInTheSearchPage",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void accessPassageQuestionInBrowsePage()
	{
		try
		{
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "6");
			String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", "6");
			String tloids = ReadTestData.readDataByTagName("tloids", "tlo", "0");
			Driver.startDriver();
			new DirectLogin().CMSLogin();
			Driver.driver.findElement(By.cssSelector("img[alt=\"Biology\"]")).click();
			new Assignment().createChapter(6);//create a chapter 
			new Assignment().create(6);//create a assignment
			int tloId = Integer.parseInt(tloids);
			new Assignment().addQuestions(6, "qtn-passage-based-img", assignmentname, false, false, difficultylevel, true, tloId);//add passage based question
			Driver.driver.findElement(By.id("content-search-icon")).click();//click on search icon
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("tab-browse")).click();//click on browse tab
			Thread.sleep(2000);
			new ComboBox().selectValuewithtitle(9, "Search Questions");
			//select the chapter
			List<WebElement> allElements1 = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
			Thread.sleep(3000);
			allElements1.get(10).click(); // click on select chapter dropdown
			Thread.sleep(2000);
			String id = Config.courseid;
			ResultSet n = DBConnect.st.executeQuery("select count(*) as chcnt from t_chapter where subject_id in (select id from t_subject where course_id = "+id+");");   
			String chcount = "";
			while(n.next())
		    {
		    	 chcount = n.getString("chcnt");
		    }
			int chNumber = Integer.parseInt(chcount);
			System.out.println("chNumber: "+chNumber);
			Actions a = new Actions(Driver.driver);
		    for(int i=0;i<chNumber;i++)	//select the chapter
		    	a.sendKeys(Keys.DOWN).perform();
		    	a.sendKeys(Keys.RETURN).perform();
		    Thread.sleep(3000);	
		    //verify the passage based question icon
		    String icon = Driver.driver.findElement(By.cssSelector("img[title='Reading comprehension question']")).getAttribute("src");
			if(!icon.contains("passagebased.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On searching passage based question (from Browse tab) it doesn't show a 'Visual indicator' icon below the checkbox indicating a passage type question.");
			}
			//list all the search result
			List<WebElement> allSearch = Driver.driver.findElements(By.className("content-search-title-section"));
			Actions action = new Actions(Driver.driver);
			WebElement we = allSearch.get(1);// the search result is at 1st index
			action.moveToElement(we).build().perform();
			Thread.sleep(3000);
			//click on Quick preview
			List<WebElement> allQuickPreview = Driver.driver.findElements(By.className("expand-question-content"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",allQuickPreview.get(1));
			Thread.sleep(3000);
			//verify the passage based question icon
			String icon1 = Driver.driver.findElement(By.cssSelector("img[title='Reading comprehension question']")).getAttribute("src");
			if(!icon1.contains("passagebased.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking quick preview it doesn't show a 'Visual indicator' icon below the checkbox indicating a passage type question(from Browse tab).");
			}
			//verify chapter name
			String chName = Driver.driver.findElement(By.id("search-chapter-blk")).getText();
			if(!chName.contains("Ch"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Quick Preview it doesnt show the Chapter name(from Browse tab).");
			}
			//verify assessmentName name
			String assessmentName = Driver.driver.findElement(By.id("search-diff-level-blks")).getText();
			if(!assessmentName.contains("Assessment Name"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Quick Preview it doesnt show the Assessment name(from Browse tab).");
			}
			//all search result
			List<WebElement> allSearch1 = Driver.driver.findElements(By.className("content-search-title-section"));
			Actions action1 = new Actions(Driver.driver);
			WebElement we1 = allSearch1.get(1);
			action1.moveToElement(we1).build().perform();
			Thread.sleep(3000);
			//click to collapse icon
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.className("collapse-question-content")));
			Thread.sleep(3000);
			//verify the passage based question icon
			String icon2 = Driver.driver.findElement(By.cssSelector("img[title='Reading comprehension question']")).getAttribute("src");
			if(!icon2.contains("passagebased.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking collappse icon quick preview it doesn't show a 'Visual indicator' icon below the checkbox indicating a passage type question(from Browse tab).");
			}
		    
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase accessPassageQuestionInBrowsePage in class AccessPassageQuestionInTheSearchPage",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
