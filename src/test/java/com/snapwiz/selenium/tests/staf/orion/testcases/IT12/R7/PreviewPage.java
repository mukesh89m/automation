package com.snapwiz.selenium.tests.staf.orion.testcases.IT12.R7;

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
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SearchQuestion;
import com.snapwiz.selenium.tests.staf.orion.uihelper.ComboBox;

public class PreviewPage {
	
	@Test(priority = 1, enabled = true)
	public void previewPageForTrueFalseQuestion()
	{
		try
		{
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "129");
			String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", "129");
			String questiontext = ReadTestData.readDataByTagName("", "questiontext", "129");
			String tloids = ReadTestData.readDataByTagName("tloids", "tlo", "0");
			int tloId = Integer.parseInt(tloids);
			Driver.startDriver();
			new Assignment().create(129);//create a assignment
			new Assignment().addQuestions(129, "qtn-type-true-false-img", assignmentname, false, false, difficultylevel, true, tloId);//add true false type question
			
			//Store the current window handle
			String winHandleBefore = Driver.driver.getWindowHandle();

			//Perform the click operation that opens new window
			Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
			Thread.sleep(3000);
			//Switch to new window opened
			for(String winHandle : Driver.driver.getWindowHandles()){
			    Driver.driver.switchTo().window(winHandle);
			}
			int rightFrame = Driver.driver.findElements(By.id("cms-question-preview-question-skill-graph-wapper")).size();
			if(rightFrame == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In preview page the right side frame is not coming, for true false type question.");
			}
			String title = Driver.driver.findElement(By.className("cms-question-preview-sidebar-title-sectn")).getText();
			if(!title.equals("Performance in Last 10 Qs"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In preview page in the right side frame 'Performance in Last 10 Qs' is not coming, for true false type question.");
			}
			String graphimg = Driver.driver.findElement(By.id("cms-question-difficulty-img")).getCssValue("background-image");
			if(!graphimg.contains("cms-question-difficulty.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the right frame under Performance in last 10 Qs doesn't show a blank screen, for true false type question.");
			}
			String aboutThis = Driver.driver.findElement(By.className("cms-about-this-question-title")).getText();
			if(!aboutThis.equals("About this Question"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In preview page in the right side frame 'About this Question' is not coming, for true false type question.");
			}
			String difficulty = Driver.driver.findElement(By.className("cms-content-box-title")).getText();
			if(!difficulty.contains("Question Difficulty"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In preview page in the right side frame the actual difficulty is not shown, for true false type question.");
			}
			String str = Driver.driver.findElement(By.className("cms-diagtest-score-view")).getText();
			if(!str.contains("%"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In preview page in the right side frame the actual percentage that how many student did correctly is absent, for true false type question.");
			}
			if(!str.contains("Students got it correct"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In preview page in the right side frame the 'Students got it correct' label is absent, for true false type question.");
			}
			String qObjective = Driver.driver.findElement(By.cssSelector("div[class='cms-content-box-title cms-content-box-performance-title']")).getText();
			if(!qObjective.equals("Question Objectives"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In preview page in the right side frame the 'Question Objectives' label is absent, for true false type question.");
			}
			String tlo = Driver.driver.findElement(By.className("cms-diagtest-skills-evaluted-ul")).getText();
			if(tlo == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In preview page in the right side frame Under 'Question objectives' doesn't show TLOs associated with the question, for true false type question.");
			}
			//Close the new window, if that window no more required
			Driver.driver.close();

			//Switch back to original browser (first window)

			Driver.driver.switchTo().window(winHandleBefore);
			new SearchQuestion().searchquestion(questiontext);//search question
			//list all the search result
			List<WebElement> allSearch = Driver.driver.findElements(By.className("content-search-title-section"));
			Actions action = new Actions(Driver.driver);
			WebElement we = allSearch.get(1);// the search result is at 1st index
			action.moveToElement(we).build().perform();
			Thread.sleep(3000);
			//click on Full preview
			List<WebElement> allFullPreview = Driver.driver.findElements(By.className("preview-question-content"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",allFullPreview.get(1)); 
			//Switch to new window opened
			for(String winHandle : Driver.driver.getWindowHandles()){
			    Driver.driver.switchTo().window(winHandle);
			}
			//check for right side frame
			int rightFrame1 = Driver.driver.findElements(By.id("cms-question-preview-question-skill-graph-wapper")).size();
			if(rightFrame1 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Full Preview from search page, the right side frame is not coming, for true false type question.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase previewPageForTrueFalseQuestion in class PreviewPage",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void clickFullPreviewFromBrowseTabForTruefalseQuestion()
	{
		try
		{
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "137");
			String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", "137");
			String tloids = ReadTestData.readDataByTagName("tloids", "tlo", "0");
			Driver.startDriver();
			new Assignment().createChapter(137);//create a chapter 
			new Assignment().create(137);//create a assignment
			int tloId = Integer.parseInt(tloids);
			new Assignment().addQuestions(137, "qtn-type-true-false-img", assignmentname, false, false, difficultylevel, true, tloId);//add true false type question
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
			Actions a = new Actions(Driver.driver);
		    for(int i=0;i<chNumber;i++)	//select the chapter
		    	a.sendKeys(Keys.DOWN).perform();
		    	a.sendKeys(Keys.RETURN).perform();
		    Thread.sleep(3000);	
			//list all the search result
			List<WebElement> allSearch = Driver.driver.findElements(By.className("content-search-title-section"));
			Actions action = new Actions(Driver.driver);
			WebElement we = allSearch.get(1);// the search result is at 1st index
			action.moveToElement(we).build().perform();
			Thread.sleep(3000);
			//click on Full preview
			List<WebElement> allFullPreview = Driver.driver.findElements(By.className("preview-question-content"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",allFullPreview.get(1)); 
			//Switch to new window opened
			for(String winHandle : Driver.driver.getWindowHandles()){
			    Driver.driver.switchTo().window(winHandle);
			}
			//check for right side frame
			int rightFrame1 = Driver.driver.findElements(By.id("cms-question-preview-question-skill-graph-wapper")).size();
			if(rightFrame1 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Full Preview from browse page, the right side frame is not coming, for true false type question.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickFullPreviewFromBrowseTabForTruefalseQuestion in class PreviewPage",e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void previewPageForTrueFalseQuestionWithPassage()
	{
		try
		{
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "138");
			String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", "138");
			String questiontext = ReadTestData.readDataByTagName("", "questiontext", "138");
			String tloids = ReadTestData.readDataByTagName("tloids", "tlo", "0");
			Driver.startDriver();
			new Assignment().create(138);//create a assignment
			int tloId = Integer.parseInt(tloids);
			new Assignment().addQuestions(138, "qtn-passage-based-img", assignmentname, false, false, difficultylevel, true, tloId);//add passage based question
			//Store the current window handle
			String winHandleBefore = Driver.driver.getWindowHandle();

			//Perform the click operation that opens new window
			Driver.driver.findElement(By.id("preview-the-image-quiz")).click();
			Thread.sleep(3000);
			//Switch to new window opened
			for(String winHandle : Driver.driver.getWindowHandles()){
			    Driver.driver.switchTo().window(winHandle);
			}
			//click to enlarge the right side frame
			Driver.driver.findElement(By.cssSelector("img[title='Expand']")).click();
			Thread.sleep(2000);
			int rightFrame = Driver.driver.findElements(By.id("cms-question-preview-question-skill-graph-wapper")).size();
			if(rightFrame == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In preview page the right side frame is not coming for passage based question.");
			}
			String title = Driver.driver.findElement(By.className("cms-question-preview-sidebar-title-sectn")).getText();
			if(!title.equals("Performance in Last 10 Qs"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In preview page in the right side frame 'Performance in Last 10 Qs' is not coming for passage based question.");
			}
			String graphimg = Driver.driver.findElement(By.id("cms-question-difficulty-img")).getCssValue("background-image");
			if(!graphimg.contains("cms-question-difficulty.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In the right frame under Performance in last 10 Qs doesn't show a blank screen for passage based question.");
			}
			String aboutThis = Driver.driver.findElement(By.className("cms-about-this-question-title")).getText();
			if(!aboutThis.equals("About this Question"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In preview page in the right side frame 'About this Question' is not coming for passage based question.");
			}
			String difficulty = Driver.driver.findElement(By.className("cms-content-box-title")).getText();
			if(!difficulty.contains("Question Difficulty"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In preview page in the right side frame the actual difficulty is not shown for passage based question.");
			}
			String str = Driver.driver.findElement(By.className("cms-diagtest-score-view")).getText();
			if(!str.contains("%"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In preview page in the right side frame the actual percentage that how many student did correctly is absent for passage based question.");
			}
			if(!str.contains("Students got it correct"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In preview page in the right side frame the 'Students got it correct' label is absent for passage based question.");
			}
			String qObjective = Driver.driver.findElement(By.cssSelector("div[class='cms-content-box-title cms-content-box-performance-title']")).getText();
			if(!qObjective.equals("Question Objectives"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In preview page in the right side frame the 'Question Objectives' label is absent for passage based question.");
			}
			String tlo = Driver.driver.findElement(By.className("cms-diagtest-skills-evaluted-ul")).getText();
			if(tlo == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In preview page in the right side frame Under 'Question objectives' doesn't show TLOs associated with the question for passage based question.");
			}
			//Close the new window, if that window no more required
			Driver.driver.close();

			//Switch back to original browser (first window)

			Driver.driver.switchTo().window(winHandleBefore);
			new SearchQuestion().searchquestion(questiontext);//search question
			//list all the search result
			List<WebElement> allSearch = Driver.driver.findElements(By.className("content-search-title-section"));
			Actions action = new Actions(Driver.driver);
			WebElement we = allSearch.get(1);// the search result is at 1st index
			action.moveToElement(we).build().perform();
			Thread.sleep(3000);
			//click on Full preview
			List<WebElement> allFullPreview = Driver.driver.findElements(By.className("preview-question-content"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",allFullPreview.get(1)); 
			//Switch to new window opened
			for(String winHandle : Driver.driver.getWindowHandles()){
			    Driver.driver.switchTo().window(winHandle);
			}
			//check for right side frame
			int rightFrame1 = Driver.driver.findElements(By.id("cms-question-preview-question-skill-graph-wapper")).size();
			if(rightFrame1 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Full Preview from search page, the right side frame is not coming for passage based question.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase previewPageForTrueFalseQuestionWithPassage in class PreviewPage",e);
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void clickFullPreviewFromBrowseTabForPassageBasedQuestion()
	{
		try
		{
			String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "146");
			String difficultylevel = ReadTestData.readDataByTagName("", "difficultylevel", "146");
			String tloids = ReadTestData.readDataByTagName("tloids", "tlo", "0");
			Driver.startDriver();
			new Assignment().createChapter(146);//create a chapter 
			new Assignment().create(146);//create a assignment
			int tloId = Integer.parseInt(tloids);
			new Assignment().addQuestions(146, "qtn-passage-based-img", assignmentname, false, false, difficultylevel, true, tloId);//add passage based question
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
			Actions a = new Actions(Driver.driver);
		    for(int i=0;i<chNumber;i++)	//select the chapter
		    	a.sendKeys(Keys.DOWN).perform();
		    	a.sendKeys(Keys.RETURN).perform();
		    Thread.sleep(3000);	
			//list all the search result
			List<WebElement> allSearch = Driver.driver.findElements(By.className("content-search-title-section"));
			Actions action = new Actions(Driver.driver);
			WebElement we = allSearch.get(1);// the search result is at 1st index
			action.moveToElement(we).build().perform();
			Thread.sleep(3000);
			//click on Full preview
			List<WebElement> allFullPreview = Driver.driver.findElements(By.className("preview-question-content"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",allFullPreview.get(1)); 
			//Switch to new window opened
			for(String winHandle : Driver.driver.getWindowHandles()){
			    Driver.driver.switchTo().window(winHandle);
			}
			//click to enlarge the right side frame
			Driver.driver.findElement(By.cssSelector("img[title='Expand']")).click();
			Thread.sleep(2000);
			//check for right side frame
			int rightFrame1 = Driver.driver.findElements(By.id("cms-question-preview-question-skill-graph-wapper")).size();
			if(rightFrame1 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Full Preview from browse page, the right side frame is not coming, for true false type question.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase clickFullPreviewFromBrowseTabForPassageBasedQuestion in class PreviewPage",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
