package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R55;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;

public class ReserveForAssignmentCheckBoxBehaviour extends Driver
{
	@Test(priority=1,enabled=true)
	public void reserveForAssignmentCheckBoxBehavaviour()
	{
		try
		{
			String course = Config.course;
			new DirectLogin().CMSLogin();
			driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
			driver.findElement(By.cssSelector("div.create-practice")).click();
			//128--
			boolean reservecheckbox=new BooleanValue().booleanbyclass("disable");			
			if(reservecheckbox==false)
			{
				Assert.fail("checked box is not disable");
			}
			//127--default question type
			String defaultsettype=new TextFetch().textfetchbyclass("practice-set-parameter-selectbox-wrapper");
			if(!defaultsettype.contains("Adaptive Component Diagnostic"))
			{
				Assert.fail("default question type not Adaptive Component Diagnostic");
			}
			//129--question type Adaptive Component Practice
			 driver.findElement(By.id("assessmentName")).click();
			 driver.findElement(By.id("assessmentName")).clear();
			 driver.findElement(By.id("assessmentName")).sendKeys("assessment name");
			 driver.findElement(By.id("questionSetName")).clear();
			 driver.findElement(By.id("questionSetName")).sendKeys("question set");
			 new ComboBox().selectValue(3, "Adaptive Component Practice");
			 boolean reservecheckbox1=new BooleanValue().booleanbyclass("disable");			
			 if(reservecheckbox1==false)
			 {
				Assert.fail("checked box is not disable after selecting Adaptive Component Practice");
			 }
			 //130
			 new ComboBox().selectValue(3, "Static Practice");
			 int reservecheckbox2=driver.findElements(By.className("disable")).size();
			 if(reservecheckbox2!=0)
			 {
				 Assert.fail("check box not enable for static practice test");
			 }
			 //131,132,136,137--publish assignment and try to edit question of static type
			 String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", "127");
			 new Assignment().create(127);
			 new Assignment().OpenAssignment(assignmentname, 127);
			 new MouseHover().mouserhover("question-container-header-assessment-edit");//mouse hover on assignment name
			 new Click().clickByclassname("assessment-edit-icon-wrapper");//click on pen --new pop up open
			 Thread.sleep(5000);
			 boolean assignmenttype=new BooleanValue().booleanbycssselector("div[class='sbHolder sbHolderDisabled']");//check assignment type is disable or not
			 if(assignmenttype==false)
				 Assert.fail("question type not disable");
			 boolean reserveforassignment=new BooleanValue().booleanbycssselector("label[class='checked disable']");//check check box is disable or not
			 if(reserveforassignment==false)
				 Assert.fail("check box not disable");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in class ReserveForAssignmentCheckBoxBehaviour in method eeserveForAssignmentCheckBoxBehaviour",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void reserveForAssignmentforUncheckedquestiontype()
	{
		try
		{
			String course = Config.course;
			new DirectLogin().CMSLogin();
			//create one assignment ---133
			driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
			driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
			driver.findElement(By.cssSelector("div.create-practice")).click();
			driver.findElement(By.id("assessmentName")).click();
			driver.findElement(By.id("assessmentName")).clear();
			driver.findElement(By.id("assessmentName")).sendKeys("assessment name");
			driver.findElement(By.id("questionSetName")).clear();
			driver.findElement(By.id("questionSetName")).sendKeys("question set");
			driver.findElement(By.id("qtn-type-true-false-img")).click();
			driver.findElement(By.id("question-raw-content")).click();
			driver.switchTo().frame("iframe-question-edit").findElement(By.xpath("/html/body")).sendKeys("question text");
			driver.switchTo().defaultContent();
		    Actions action = new Actions(driver);
		    WebElement we = driver.findElement(By.id("choice1"));
		    action.moveToElement(we).build().perform();	
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//img[@title='Set it as Correct Answer']")));
			Thread.sleep(3000);
			new ComboBox().selectValue(3, "Static Practice");
			driver.findElement(By.id("saveQuestionDetails1")).click();//save uqestion
			Thread.sleep(3000);
			//mouse hover on question
			new MouseHover().mouserhover("question-container-header-assessment-edit");//mouse hover on assignment name
			new Click().clickByclassname("assessment-edit-icon-wrapper");//click on pen --new pop up open
			Thread.sleep(3000);
			String classname=driver.findElement(By.xpath("//*[@id='practice-set-parameter-container']/div/div[3]/div[7]/div[2]/label")).getAttribute("class");//fetch class name of check box in pop up
			if(!classname.equals(""))
			{
				Assert.fail("check box is disable");
			}
			//134
			new ComboBox().selectValue(7, "Adaptive Component Diagnostic");
			String classname1=driver.findElement(By.xpath("//*[@id='practice-set-parameter-container']/div/div[3]/div[7]/div[2]/label")).getAttribute("class");//fetch class name of check box in pop up
			if(!classname1.contains("disable"))
			{
				Assert.fail("check box is enable"); 
			}
			//135
			new ComboBox().selectValue(7, "Static Practice");
			String classname2=driver.findElement(By.xpath("//*[@id='practice-set-parameter-container']/div/div[3]/div[7]/div[2]/label")).getAttribute("class");//fetch class name of check box in pop up
			if(!classname2.equals(""))
			{
				Assert.fail("check box is disable again select satic");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in class ReserveForAssignmentCheckBoxBehaviour in method reserveForAssignmentforUncheckedquestiontype",e);
		}
	}

}
