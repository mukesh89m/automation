package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.dummies.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Config;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.ComboBox;

public class UpdateContentIndex {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.apphelper.UpdateContentIndex");
	//Its update the course update Content index
	public void updatecontentindex(String dataIndex)
	{
		try
		{
			String course =Config.course;
			new com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))
			{
				logger.log(Level.INFO,"CMSHome  open");
                Driver.driver.findElement(By.cssSelector("img[title='"+Config.course+"']")).click();
				Driver.driver.get(Config.baseURL+"/secure/updateContentIndex");
				new ComboBox().selectValue(1, course);
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
	
