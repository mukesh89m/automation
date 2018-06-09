package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.snapwiz.selenium.tests.staf.orion.Driver;

import org.junit.Assert;

public class TOCChapterNames {
	
	public String [] getTOCChapterNames()
	{
		String [] chapternames = new String[100];
		try
		{
			int index = 0;
		
			List<WebElement> chapterelements = Driver.driver.findElements(By.className("chapter-heading"));
			//	List<WebElement> chapterelements = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'chapter-heading')]")); 
				for (WebElement element: chapterelements) {	     
					String chaptername = element.getAttribute("title");
				//	 String chaptername = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",element);
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
