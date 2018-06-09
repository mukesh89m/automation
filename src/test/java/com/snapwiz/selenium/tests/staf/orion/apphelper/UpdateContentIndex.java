package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.uihelper.ComboBox;

public class UpdateContentIndex {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.orion.apphelper.UpdateContentIndex");
	//Its update the course update Content index
	public void updatecontentindex(String dataIndex)
	{
		try
		{
			String course =Config.course;
			new DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))
			{
				logger.log(Level.INFO,"CMSHome  open");
				Driver.driver.get(Config.baseURL+"/secure/updateContentIndex");
				Actions actionObject = new Actions(Driver.driver);
			       actionObject.sendKeys(Keys.F11).perform();
				new ComboBox().selectValue(1, course);
			   
			     Thread.sleep(3000);			     
			     Driver.driver.findElement(By.id("content-type-radio-button")).click();
			     Driver.driver.findElement(By.id("remove-from-solr-index")).click();
			     WebElement myDynamicElement = (new WebDriverWait(Driver.driver,100))
			    		  .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
			     Thread.sleep(3000);
			     System.out.println(myDynamicElement.getText());
			     if(!myDynamicElement.getText().equals("Successfully removed from Solr index.")) Assert.fail("Solr index not removed successfully");
			     Driver.driver.findElement(By.id("add-to-solr-index")).click();
			     myDynamicElement = (new WebDriverWait(Driver.driver,100))
			    		  .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
			     Thread.sleep(3000);
			     System.out.println(myDynamicElement.getText());
			     if(!myDynamicElement.getText().equals("Successfully added to Solr index.")) Assert.fail("Solr index not added successfully");
			   /*  Driver.driver.findElement(By.id("entity-radio-button")).click();
			     Driver.driver.findElement(By.id("remove-from-solr-index")).click();
			     myDynamicElement = (new WebDriverWait(Driver.driver,100))
			    		  .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
			     Thread.sleep(3000);
			     System.out.println(myDynamicElement.getText());
			     if(!myDynamicElement.getText().equals("Successfully removed from Solr index.")) Assert.fail("Solr index not removed successfully");
			     Driver.driver.findElement(By.id("add-to-solr-index")).click();
			     myDynamicElement = (new WebDriverWait(Driver.driver,100))
			    		  .until(ExpectedConditions.presenceOfElementLocated(By.className("cms-notification-message-wrapper")));
			     Thread.sleep(3000);
			     System.out.println(myDynamicElement.getText());
			     if(!myDynamicElement.getText().equals("Successfully added to Solr index.")) Assert.fail("Solr index not added successfully");*/
			     Driver.driver.quit();
			     Driver.startDriver();
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
	
