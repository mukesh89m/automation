package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R22;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ResourseCreate;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;

import java.util.List;

public class InstructorAbleToCustomizeAssessment extends Driver {
	
	@Test(priority=1,enabled=true)
	public void instructorAbleToCustomizeAssessment()
	{
		try
		{
			new ResourseCreate().resourseCreate(1, 0);//add resources
			new LoginUsingLTI().ltiLogin("1");//login as instructor
			new Navigator().NavigateTo("My Question Bank");//click on my question bank
			String opentabname=driver.findElement(By.cssSelector("div[class='tab active']")).getText();//fetch active tab name
			if(!opentabname.contains("My Question Bank"))
			{
				Assert.fail("open tab is not all resources tab");
			}
			String color=driver.findElement(By.id("customAssignment")).getCssValue("color");
			if(!color.contains("rgba(255, 255, 255, 1)"))
			{
				Assert.fail("create cstomeAssignment color not blue");
			}
			String createcustomassignment=new TextFetch().textfetchbyid("customAssignment");//fetch custome assignemnt link
			if(!createcustomassignment.contains("+ Create Custom Assignment"))
			{
				Assert.fail("create cstomeAssignment not shown with + sign");
			}
			new Click().clickByclassname("tab");//click on my resources tab
			Thread.sleep(2000);
			String color1=driver.findElement(By.id("customAssignment")).getCssValue("color");
			if(!color1.contains("rgba(255, 255, 255, 1)"))
			{
				Assert.fail("create cstomeAssignment color not blue");
			}
			String createcustomassignment1=new TextFetch().textfetchbyid("customAssignment");
			if(!createcustomassignment1.contains("+ Create Custom Assignment"))
			{
				Assert.fail("create cstomeAssignment not shown with + sign");
			}
		
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase InstructorAbleToCustomizeAssessment in method InstructorAbleToCustomizeAssessment ",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void clickFunctionalityofCreateCustomAssignment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1");//login as instructor
			new Navigator().NavigateTo("My Question Bank");//click on my question bank
			new Click().clickByid("customAssignment");//click on create custom assignment
			Thread.sleep(2000);
			String opentabname=driver.findElement(By.cssSelector("div[class='tab active']")).getText();//fetch active tab name
			if(!opentabname.contains("New Assignment"))
			{
				Assert.fail("open tab is not new-assignment tab");
			}
			boolean createcustumassignemnt=new BooleanValue().booleanbyid("customAssignment");//verify create custom assignemnt
			if(createcustumassignemnt==true)
			{
				Assert.fail("create custom assognment link shown");
			}
			new Click().clickbylist("tab", 1);//click on  all resources tab
			Thread.sleep(2000);
			boolean createcustumassignemnt1=new BooleanValue().booleanbyid("customAssignment");//verify create custom assignemnt
			if(createcustumassignemnt1==true)
			{
				Assert.fail("create custom assognment link shown");
			}
			new Click().clickbylist("tab", 2);//click on new assignment tab
			Thread.sleep(2000);
			new Click().clickByid("close-new-assignment");//close new assignment tab
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title='Create Custom Assignment']")));
            List<WebElement> createcustumassignemnt2=driver.findElements(By.xpath("//div[@title='Create Custom Assignment']"));
			if(createcustumassignemnt2.size()==0)
			{
				Assert.fail("after close the new assignment tab create custom assignemnt link not shown");
			}
			String opentabname1=driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!opentabname1.contains("Question Banks"))
			{
				Assert.fail("open tab is not All Resources tab");
			}
			new Click().clickByclassname("tab");//click on my resources tab
			Thread.sleep(2000);
			new Click().clickByid("customAssignment");
			Thread.sleep(2000);
			boolean createcustumassignemnt4=new BooleanValue().booleanbyid("customAssignment");
			if(createcustumassignemnt4==true)
			{
				Assert.fail("create custom assognment link shown");
			}
			new Click().clickbylist("tab", 2);
			Thread.sleep(2000);
			new Click().clickByid("close-new-assignment");//close new assignment tab
			boolean createcustumassignemnt3=new BooleanValue().booleanbyid("customAssignment");
			if(createcustumassignemnt3==false)
			{
				Assert.fail("after close the new assignment tab create custom assignemnt link not shown");
			}
			String opentabname2=driver.findElement(By.cssSelector("div[class='tab active']")).getText();
			if(!opentabname2.contains("My Question Bank"))
			{
				Assert.fail("open tab is not My Resources tab");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase InstructorAbleToCustomizeAssessment in method FunctionalityofCreateCustomAssignment ",e);
		}
	}
	@Test(priority=3,enabled=true)
	public void NewAssignmentTabNameDescrptionEditBox()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1");//login as instructor
			new Navigator().NavigateTo("My Question Bank");//click on my question bank
			new Click().clickByid("customAssignment");//click on create custom assignment
			Thread.sleep(2000);
			String nameofassignment=new TextFetch().textfetchbyid("ls-ins-assignment-name");
			if(!nameofassignment.contains("Click to enter assignment name..."))
			{
				Assert.fail("default name of assignment not shown");
			}
			String Descriptionofassignemnt=new TextFetch().textfetchbyid("ls-ins-assignment-desc");
			if(!Descriptionofassignemnt.contains("Click to add a description for students..."))
			{
				Assert.fail("default descrption of assignment not shown");
			}			
			String randomname=new RandomString().randomstring(0);
			String randomndescption=new RandomString().randomstring(0);
			String randomname1=new RandomString().randomstring(0);
			String randomndescption1=new RandomString().randomstring(0);
			String selector="i[class='ls-ins-custom-image ls-ins-edit-assignment-icon']";
			new MouseHover().mouserhoverbyid("ls-ins-assignment-name");//mouse hover on edit name
			new Click().clickbylistcssselector(selector, 0);//click on pen icon	
			new TextSend().textsendbyclasslist(randomname, "ls-ins-edit-assignment",0);//edit name
			new MouseHover().mouserhoverbyid("ls-ins-assignment-desc");//mouse hover on description
			new Click().clickbylistcssselector(selector, 1);//click on pen icon
			new TextSend().textsendbyclasslist(randomndescption, "ls-ins-edit-assignment",1);//edit description
			driver.findElement(By.xpath("/html/body")).click();//click on body
			String nameofassignment1=new TextFetch().textfetchbyid("ls-ins-assignment-name");
			if(!nameofassignment1.contains(randomname))
			{
				Assert.fail(" name of assignment not change");
			}
			String Descriptionofassignemnt1=new TextFetch().textfetchbyid("ls-ins-assignment-desc");
			if(!Descriptionofassignemnt1.contains(randomndescption))
			{
				Assert.fail("descrption of assignment not change");
			}
			new MouseHover().mouserhoverbyid("ls-ins-assignment-name");//mouse hover on edit name
			new Click().clickbylistcssselector(selector, 0);//click on pen icon	
			new TextSend().textsendbyclasslist(randomname1, "ls-ins-edit-assignment",0);//edit name
			new MouseHover().mouserhoverbyid("ls-ins-assignment-desc");//mouse hover on description
			new Click().clickbylistcssselector(selector, 1);//click on pen icon
			new TextSend().textsendbyclasslist(randomndescption1, "ls-ins-edit-assignment",1);//edit description
			driver.findElement(By.xpath("/html/body")).click();//click on body
			String nameofassignment2=new TextFetch().textfetchbyid("ls-ins-assignment-name");
			if(!nameofassignment2.contains(randomname1))
			{
				Assert.fail(" name of assignment not change");
			}
			String Descriptionofassignemnt2=new TextFetch().textfetchbyid("ls-ins-assignment-desc");
			if(!Descriptionofassignemnt2.contains(randomndescption1))
			{
				Assert.fail("descrption of assignment not change");
			}
			String tabname=driver.findElement(By.cssSelector("div[data-id='new-assignment']")).getText();
			if(!tabname.contains(randomname1))
			{
				Assert.fail("tab name not changed");
			}
			new Click().clickByclassname("ls-ins-save-assigment-btn");
			String notification=new TextFetch().textfetchbyclass("ls-notification-text-span");
			System.out.println(notification);
			if(!notification.contains("You have not added any questions for this custom assessment. Please add questions before saving the assessment"))
			{
				Assert.fail("notification text not shown while clikc on save without add question");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase InstructorAbleToCustomizeAssessment in method NewAssignmentTabNameDescrptionEditBox ",e);
		}
	}
	@Test(priority=4,enabled=true)
	public void newassignmenttab()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1");//login as instructor
			new Navigator().NavigateTo("My Question Bank");//click on my question bank
			new Click().clickByid("customAssignment");//click on create custom assignment
			Thread.sleep(2000);
			new Click().clickByclassname("ls-ins-save-assigment-btn");//click on save button
			Thread.sleep(2000);
			String colorofname=driver.findElement(By.className("ls-ins-edit-assignment-name")).getCssValue("border");
			System.out.println(colorofname);
			String serachrtext=ReadTestData.readDataByTagName("tocdata", "searchtext", "1");
			driver.findElement(By.className("ls-ins-search-icon")).click();
			new TextSend().textsendbycssSelector(serachrtext, "input[class='ls-ins-search-question-content ls-ins-search-question-content-selected']");
			new Click().clickByclassname("ls-ins-search-icon");
			Thread.sleep(2000);
			driver.findElement(By.className("ls-ins-customize-checkbox-small")).click();
			Thread.sleep(2000);
			new Click().clickByclassname("ls-ins-save-assigment-btn");//click on save button
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase InstructorAbleToCustomizeAssessment in method newassignmenttab ",e);
		}			
	}


}
