package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import  java.util.*;
import com.snapwiz.selenium.tests.staf.dummies.Config;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.Click;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.MouseHover;

public class UserCreate {
	
	public void createInstitutionalStudent(String appendCharacter,String className)
    {
        try
        {
        	String methodName = new Exception().getStackTrace()[1].getMethodName();
			String username = methodName+appendCharacter;
			String email = methodName+appendCharacter+"@snapwiz.com";
			
			new DirectLogin().institutionAdminLogin();
			new Navigator().institutionAdminNavigateTo("Manage Users");
			Driver.driver.findElement(By.partialLinkText("BROWSE")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText("Select Course")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(Config.course)).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.linkText("Select Class Section")).click();
			Thread.sleep(2000);
			if(className.equals(Config.course) || className.equals(""))
			Driver.driver.findElements(By.linkText(Config.course)).get(1).click();
			else
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText(className)));	
				//Driver.driver.findElement(By.linkText(className)).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText("New user")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("fname")).sendKeys(username);
			Driver.driver.findElement(By.id("email")).clear();
			Driver.driver.findElement(By.id("email")).sendKeys(email);
			Driver.driver.findElement(By.id("password")).sendKeys("snapwiz");
			Driver.driver.findElement(By.id("passwordConf")).sendKeys("snapwiz");
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("save")).click();
			Thread.sleep(2000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in createInstitutionalStudent in class UserCreate",e);
        }
    }
	
	public void createInstitutionalInstructor(String appendCharacter,String className)
	{
		try
		{
			String methodName = new Exception().getStackTrace()[1].getMethodName();
			String username = methodName+"ins"+appendCharacter;
			String email = methodName+"ins"+appendCharacter+"@snapwiz.com";
			
			new DirectLogin().institutionAdminLogin();
			new Navigator().institutionAdminNavigateTo("Manage Users");
			Driver.driver.findElement(By.partialLinkText("BROWSE")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText("Select Course")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(Config.course)).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.linkText("Select Class Section")).click();
			Thread.sleep(2000);
			if(className.equals(Config.course) || className.equals(""))
				Driver.driver.findElements(By.linkText(Config.course)).get(1).click();
				else
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText(className)));	
					
					//Driver.driver.findElement(By.linkText(className)).click();
			Driver.driver.findElement(By.linkText("New user")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("fname")).sendKeys(username);
			Driver.driver.findElement(By.id("email")).clear();
			Driver.driver.findElement(By.id("email")).sendKeys(email);
			Driver.driver.findElement(By.id("password")).sendKeys("snapwiz");
			Driver.driver.findElement(By.id("passwordConf")).sendKeys("snapwiz");
			Driver.driver.findElement(By.linkText("Student")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText("Instructor")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("save")).click();
			Thread.sleep(2000);
			
			
		}
		catch(Exception e)
        {
            Assert.fail("Exception in createInstitutionInstructor in class UserCreate",e);
        }
	}
	
	public void classSectionCreate(int dataIndex)
    {
        try
        {     
            new DirectLogin().institutionAdminLogin();
            new Navigator().institutionAdminNavigateTo("Manage Class Sections");
            Driver.driver.findElement(By.linkText("All Courses")).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.linkText(Config.course)).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.linkText("Class section")).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.id("className")).sendKeys(Integer.toString(dataIndex)+"class");
            Driver.driver.findElement(By.partialLinkText("Select a course")).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.linkText(Config.course)).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.id("save-class-section-details")).click();
            Thread.sleep(2000);
           
        }
        catch(Exception e)
        {
            Assert.fail("Exception in createInstitutionalStudent in class UserCreate",e);
        }
    }
	
	public void CreateStudent(String appendCharacter,String className)
	{
		try
		{
			String methodName = new Exception().getStackTrace()[1].getMethodName();
			String username = methodName+appendCharacter;
			String email = methodName+appendCharacter+"@snapwiz.com";
			Driver.driver.get(Config.homeurl);
			Thread.sleep(4000);
            int courseindex=0;
            List<WebElement> allcourse=Driver.driver.findElements(By.xpath("//div[@class='courseBook-details-Section']/h3"));
            for(WebElement course: allcourse)
            {
                String coursename=course.getText();
                if(Config.course.contains(coursename))
                {
                    Driver.driver.findElements(By.cssSelector("input[value='Buy']")).get(courseindex).click();
                    break;
                }
                courseindex++;
            }

			Driver.driver.findElement(By.id("fname")).sendKeys(username);
			Driver.driver.findElement(By.id("email")).sendKeys(email);
			Driver.driver.findElement(By.id("password")).sendKeys(Config.institutionAdminPassword);

			Driver.driver.findElement(By.id("passwordConf")).sendKeys(Config.institutionAdminPassword);

			Driver.driver.findElement(By.id("signupBtn")).click();
			Thread.sleep(4000);
			Driver.driver.findElement(By.id("cardNumber")).sendKeys("4247983028851121");

			new ComboBox().selectValue(0, "VISA");
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("nameOnCard")).sendKeys(username);

			Driver.driver.findElement(By.id("expDateMonth")).sendKeys("11");

			Driver.driver.findElement(By.id("expDateYear")).sendKeys("2022");

			Driver.driver.findElement(By.id("cvvNumber")).sendKeys("000");

			Driver.driver.findElement(By.id("streetName")).sendKeys("koromangala");

			Driver.driver.findElement(By.id("city")).sendKeys("bangalore");

			Driver.driver.findElement(By.id("state")).sendKeys("karnatka");

			Driver.driver.findElement(By.id("postalCode")).sendKeys("560037");

			Driver.driver.findElement(By.id("paymentBtn")).click();
            WebElement we = (new WebDriverWait(Driver.driver,300000))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='nav nav--dropped user-nav nav--dropped__toggle']")));
			MouseHover.mouserhoverbywebelement(we);
			new Click().clickByclassname("logout");
			
		}
		catch(Exception e)
        {
            Assert.fail("Exception in CreateStudent in class UserCreate",e);
        }
	}
	

}
