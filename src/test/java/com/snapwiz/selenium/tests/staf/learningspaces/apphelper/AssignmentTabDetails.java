package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class AssignmentTabDetails extends Driver
{
	public void assignmentdetailsatrightsideframe(String assignmentname)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			String alltext=driver.findElement(By.cssSelector("div[class='ls-right-user-content']")).getText();
			if(!alltext.contains("posted an assignment"))
				Assert.fail("-posted a assignment- not shown in assignment details");
			if(!alltext.contains("Due Date:"))
				Assert.fail("-Due Date:- not shown in assignment details");
			if(!alltext.contains("Instructor"))
				Assert.fail("-Instructor- not shown in assignment details");
			if(!alltext.contains("givenname family"))
				Assert.fail("-givenname family- not shown in assignment details");
			if(!alltext.contains("Like"))
				Assert.fail("-Like- not shown in assignment details");
			/*if(!alltext.contains("Not Started"))
				Assert.fail("-Not Started- not shown in assignment details");*/
			if(!alltext.contains("Comments"))
				Assert.fail("-Comments- not shown in assignment details");
			if(!alltext.contains("ago"))
				Assert.fail("-ago- not shown in assignment details");
			if(!alltext.contains(assignmentname))
				Assert.fail(assignmentname+"-Assignment name not shown in assignment details");
			boolean instructorimage=driver.findElement(By.cssSelector("div[class='ls-right-user-img']")).isDisplayed();
			if(instructorimage==false)
				Assert.fail("instructor image not dispalayed");
			boolean assignmentimage=driver.findElement(By.cssSelector("i[class='ls-right-section-sprites ls-right-section-status-icon ls-right-assignment-homework']")).isDisplayed();
			if(assignmentimage==false)
				Assert.fail("assignment image not shown");
			boolean bookmarkicon=driver.findElement(By.cssSelector("span[class='ls-assignment-bookmark not-bookmarked']")).isDisplayed();
			if(bookmarkicon==false)
				Assert.fail("bookemark icon not shown in assignemnt details");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper assignmentdetailsatrightsideframe in class AssignmentTabDetails.",e);
			
		}
	}

}
