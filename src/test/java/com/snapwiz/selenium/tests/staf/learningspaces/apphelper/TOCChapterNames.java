package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

import org.junit.Assert;

public class TOCChapterNames extends Driver {
	
	public String [] getTOCChapterNames()
	{
		String [] chapternames = new String[100];
		WebDriver driver=Driver.getWebDriver();
		try
		{
			int index = 0;
		
			List<WebElement> chapterelements = driver.findElements(By.className("chapter-heading"));
			//	List<WebElement> chapterelements = driver.findElements(By.xpath("//*[starts-with(@class, 'chapter-heading')]"));
				for (WebElement element: chapterelements) {	     
					String chaptername = element.getAttribute("title");
				//	 String chaptername = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",element);
					chapternames[index] = chaptername;
					index++;
			        
				}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Exception in Application Helper TOCChapterNames");
		}
		
		return chapternames;
	}

}
