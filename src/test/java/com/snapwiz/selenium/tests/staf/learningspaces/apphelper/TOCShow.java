package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class TOCShow extends Driver {

	public void tocShow()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
			driver.findElement(By.cssSelector("span[class='show-toc navigator-sprites']")).click();//TOC open
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper TOCShow",e);
		}
	}
	public void tocHide()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
             new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("i.close-study-plan-icon.close-study-plan")));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("i.close-study-plan-icon.close-study-plan")));

            Thread.sleep(2000);
			//driver.findElement(By.cssSelector("i.close-study-plan-icon.close-study-plan")).click();//click on cross symbol
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper TOCShow",e);
		}
	}
	public void chaptertree()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
            Thread.sleep(10000);
			driver.findElement(By.cssSelector("a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']")).click();
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper chaptertree",e);
		}
	}

	public void goToSearch(String name)
	{
		try {
			new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
			new Click().clickBycssselector("input[class='toc-sprite search-course-stream ui-autocomplete-input']");
			new Click().clickbyxpath("//div[@title='"+name+"']");

		} catch (Exception e) {
			Assert.fail("Exception in goToSearch in AppHelper class Navigator", e);
		}

	}
	
}
