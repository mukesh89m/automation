package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import com.snapwiz.selenium.tests.staf.learningspaces.Redis;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class InstructorAbleToViewDetailsOfEachAssignment extends Driver{
	@Test
	public void instructorAbleToViewDetailsOfEachAssignment()
	{
		try
		{
			String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "1936");
			String assessmentname1 = ReadTestData.readDataByTagName("", "assessmentname", "19361");
			String description = ReadTestData.readDataByTagName("", "description", "1936");
			String description1 = ReadTestData.readDataByTagName("", "description", "19361");
			String shareName = ReadTestData.readDataByTagName("", "shareName", "19361");
			new Assignment().create(1936);
			new Assignment().create(19361);
			//providing description for the assessments through back-end
			DBConnect.Connect();
			DBConnect.st.executeUpdate("UPDATE t_assessment set description = '"+description+"' where name like '"+assessmentname+"'");
			DBConnect.st.executeUpdate("UPDATE t_assessment set description = '"+description1+"' where name like '"+assessmentname1+"'");
            new Redis().deleteKeys();//clear redis
			new LoginUsingLTI().ltiLogin("19361");
			new LoginUsingLTI().ltiLogin("1936");
			new Navigator().NavigateTo("Question Banks");
			
			//Adding assignment to search
			driver.findElement(By.id("all-resource-search-textarea")).clear();
			driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\""+assessmentname+"\"");
			driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
			Thread.sleep(3000);
			 //Verify the status of the Assignment
			 WebElement statusOfAssignment =  driver.findElement(By.className("ls-assessment-item-sectn-links-ul"));
			 String status = statusOfAssignment.getText();
			 System.out.println("status: "+status);
			 if(!status.contains("Assign This Customize This"))
			 {
				 Assert.fail("The status for the assignment not assigned is not 'Assign This'");
			 }
			 //Assigning a resource to a student
			 new Assignment().assignToStudent(19361);
			 new Navigator().NavigateTo("Question Banks");
			//Adding assignment to search
			 driver.findElement(By.id("all-resource-search-textarea")).clear();
			 driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\""+assessmentname1+"\"");
			 driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
			 Thread.sleep(3000);
			 driver.findElement(By.cssSelector("span[class='assign-this']")).click();
			 Thread.sleep(3000);
			//Checking whether the name of student to which assignment is assigned

		}
		catch(Exception e)
		{
			Assert.fail("Exception in instructorAbleToViewDetailsOfEachAssignment in class InstructorAbleToViewDetailsOfEachAssignment",e);
		}
	}

}
