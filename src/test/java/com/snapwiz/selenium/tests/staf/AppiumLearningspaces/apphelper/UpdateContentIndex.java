package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Config;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;

public class UpdateContentIndex {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.UpdateContentIndex");
	//Its update the course update Content index
	public void updatecontentindex(String dataIndex)
	{
		try
		{
			String course =Config.course;
			new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))
			{
				logger.log(Level.INFO,"CMSHome  open");
				Driver.driver.get(Config.baseURL+"/secure/updateContentIndex");
				//new ComboBox().selectValue(1, course);
                Driver.driver.findElement(By.xpath("//*[@id='course-drop-down-selectbox']/div/a")).click();

                WebElement element=Driver.driver.findElement(By.cssSelector("a[title='"+course+"']"));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);",element);
                Driver.driver.findElement(By.cssSelector("a[title='"+course+"']")).click();

			     Thread.sleep(3000);			     
			     Driver.driver.findElement(By.id("content-type-radio-button")).click();
			     Driver.driver.findElement(By.id("remove-from-solr-index")).click();
			     WebElement myDynamicElement = (new WebDriverWait(Driver.driver,3600000))
			    		  .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
			     Thread.sleep(3000);
			     System.out.println(myDynamicElement.getText());
			     if(!myDynamicElement.getText().equals("Successfully removed from Solr index.")) Assert.fail("Solr index not removed successfully");
			     Driver.driver.findElement(By.id("add-to-solr-index")).click();
			     myDynamicElement = (new WebDriverWait(Driver.driver,3600000))
			    		  .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
			     Thread.sleep(3000);
			     System.out.println(myDynamicElement.getText());
			     if(!myDynamicElement.getText().equals("Successfully added to Solr index.")) Assert.fail("Solr index not added successfully");
			     Driver.driver.findElement(By.id("entity-radio-button")).click();
			     Driver.driver.findElement(By.id("remove-from-solr-index")).click();
			     myDynamicElement = (new WebDriverWait(Driver.driver,3600000))
			    		  .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
			     Thread.sleep(3000);
			     System.out.println(myDynamicElement.getText());
			     if(!myDynamicElement.getText().equals("Successfully removed from Solr index.")) Assert.fail("Solr index not removed successfully");
			     Driver.driver.findElement(By.id("add-to-solr-index")).click();
			     myDynamicElement = (new WebDriverWait(Driver.driver,3600000))
			    		  .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
			     Thread.sleep(3000);
			     System.out.println(myDynamicElement.getText());
			     if(!myDynamicElement.getText().equals("Successfully added to Solr index.")) Assert.fail("Solr index not added successfully");
			}
			else
			{
				logger.log(Level.INFO,"CMSHome  NOT open");
				Assert.fail("CMS home not opened");
			}					
		}
		catch(Exception e)
		{			
			Assert.fail("Exception in App Helper UpdateContentIndex",e);
		}
	}



    public void updateContentIndexForAllCourses(String dataIndex)
    {
        try
        {
            String course =Config.course;
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DirectLogin().CMSLogin();
            String title=Driver.driver.getTitle();
            if(title.equals("Course Content Details"))
            {
                logger.log(Level.INFO,"CMSHome  open");
                Driver.driver.get(Config.baseURL+"/secure/updateContentIndex");
                //new ComboBox().selectValue(1, course);
               // Driver.driver.findElement(By.xpath("//*[@id='course-drop-down-selectbox']/div/a")).click();
                List<WebElement> courseList=Driver.driver.findElements(By.cssSelector("div[class='overview'] >li>a"));

                for(WebElement we : courseList)
                {
                    if(we.getText()!="") {
                      //  Driver.driver.findElement(By.xpath("//*[@id='course-drop-down-selectbox']/div/a")).click();
                        WebElement element = Driver.driver.findElement(By.cssSelector("a[title='" + we.getAttribute("title") + "']"));
                        System.out.println("Title "+element.getAttribute("title"));
                       // ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
                        Driver.driver.findElement(By.xpath("//*[@id='course-drop-down-selectbox']/div/a")).click();
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);",element);
                        element.click();
                        Thread.sleep(1000);
                        Driver.driver.findElement(By.id("content-type-radio-button")).click();
                        Thread.sleep(1000);
                        if(!Driver.driver.findElement(By.xpath("//div[@id='content-drop-down-wrapper']/div/a[2]")).getAttribute("selectedid").equals("ASSESSMENT"))
                        {
                            Driver.driver.findElement(By.xpath("//div[@id='content-drop-down-wrapper']/div/a")).click();
                            Driver.driver.findElement(By.xpath("//a[@title='ASSESSMENT']")).click();
                        }


                    Driver.driver.findElement(By.id("remove-from-solr-index")).click();
                    WebElement myDynamicElement = (new WebDriverWait(Driver.driver,3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(3000);
                    if(!myDynamicElement.getText().equals("Successfully removed from Solr index.")) Assert.fail("Solr index not removed successfully");
                    Driver.driver.findElement(By.id("add-to-solr-index")).click();
                    myDynamicElement = (new WebDriverWait(Driver.driver,3600000))
                            .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
                    Thread.sleep(3000);
                    if(!myDynamicElement.getText().equals("Successfully added to Solr index.")) Assert.fail("Solr index not added successfully");

                    }
                }




            }
            else
            {
                logger.log(Level.INFO,"CMSHome  NOT open");
                Assert.fail("CMS home not opened");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in App Helper UpdateContentIndex",e);
        }
    }
}
	
