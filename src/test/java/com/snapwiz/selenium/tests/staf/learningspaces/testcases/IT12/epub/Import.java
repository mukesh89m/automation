package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.epub;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;

public class Import extends Driver {
	
	@Test(priority = 1, enabled = false)
	public void importCourse()
	{
		try
		{

			new DirectLogin().CMSLogin();
			driver.findElement(By.id("import-epub3s-course")).click();
			(new WebDriverWait(driver,300))
		    		  .until(ExpectedConditions.presenceOfElementLocated(By.id("start-epub-import-button")));
			
			new ComboBox().selectValue(2, "Biology - @Version(65)");
			new ComboBox().selectValue(3, "Learning Space");
			Thread.sleep(3000);
			driver.findElement(By.id("start-epub-import-button")).click();
			Thread.sleep(3000);
			long start = System.nanoTime();
			driver.findElement(By.id("import-epub-progress-container")).click();
			(new WebDriverWait(driver,7200))
  		    .until(ExpectedConditions.presenceOfElementLocated(By.className("success")));
			long diff = System.nanoTime() - start;
			System.out.println("Time Taken(nano seconds)" +diff);
			System.out.println("Time Taken "+ NANOSECONDS.toSeconds(diff));
			System.out.println(driver.findElement(By.className("report-heading")).getText());
			
			if(!driver.findElement(By.className("report-heading")).getText().contains("Import Report"))
				Assert.fail("The heading of the import completion pop-up not showing heading as Import Report");			
			
			if(!driver.findElement(By.className("success")).getText().contains("Success")) //
				Assert.fail("Success word not present");
		
			if(!driver.findElement(By.className("success")).getCssValue("color").equals("rgba(26, 122, 5, 1)")) //colour of the word success
				Assert.fail("The color of the word Success is not green");
			
			if(driver.findElement(By.id("import-report-success-count")).getText().length() == 0) //success count
				Assert.fail("The count of number of success is not shown next to the word success");			
			
			if(!driver.findElement(By.className("error")).getText().contains("Warnings"))
				Assert.fail("Warning word not present");
			
			if(!driver.findElement(By.className("error")).getCssValue("color").equals("rgba(177, 14, 12, 1)")) //colour of the word warning
				Assert.fail("The color of the word Warning is not red");
			
			if(driver.findElement(By.id("#import-report-warnings-error-count")).getText().length() == 0)
				Assert.fail("The count of number of failures is not shown next to the word Warnings");
			
			if(!driver.findElement(By.cssSelector("div[class='report-sub-heading success']")).getText().equals("Sucessfully imported"))
				Assert.fail("The word 'Successfully imported' not present in the import report");
			
			if(!driver.findElement(By.cssSelector("div[class='report-sub-heading success']")).getCssValue("color").equals("rgba(26, 122, 5, 1)")) //colour of the word successfully imported
				Assert.fail("The color of the word Successfully imported is not green");
			
			if(!driver.findElement(By.cssSelector("div[class='report-sub-heading error']")).getText().equals("Warning")) //warning label below successfully imported section
				Assert.fail("The word 'Warning' not present in the import report below successfully imported section");
			
			System.out.println("Download link" +driver.findElement(By.linkText("Download Report")).getText());
			
			if(!driver.findElement(By.id("import-report-download")).getText().equals("Download Report"))  //checking download report link
				Assert.fail("Download link not present in the import report");		     
		     
		     if(!driver.findElement(By.id("import-report-continue")).getText().contains("Continue"))  // checking continue button
		    	 Assert.fail("Continue button not present at the bottom of the import report");
		     
		     driver.findElement(By.linkText("Download Report")).click(); //clicking on download report link
			 Thread.sleep(3000);
			 Actions ac = new Actions(driver);
			 ac.sendKeys(Keys.ESCAPE);
			 
			 driver.findElement(By.linkText("Continue >")).click();
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception while importing course from CMS",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void previewImportedLesson()
	{
		try
		{
			new DirectLogin().CMSLogin();
			new SelectCourse().selectcourse();
			if(!driver.findElement(By.className("header-content-label")).getText().equals("COURSE OUTLINE"))
				Assert.fail("Not navigated to Manage Content after selecting course from CMS");
			driver.findElement(By.className("expand-chapter-tree expand")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("course-topic-label-1")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("collection-lesson-name")).click();
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase previewImportedLesson in class Import",e);
		}
	}

}
