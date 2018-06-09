package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.snapwiz.selenium.tests.staf.learnon.Config;
import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.ComboBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateContentIndex {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learnon.apphelper.UpdateContentIndex");
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
				new ComboBox().selectValue(1, course);
				/*List<WebElement> allElements = Driver.driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]"));
			     Thread.sleep(3000);
			    allElements.get(1).click();      
			    Actions a = new Actions(Driver.driver);
			    for(int i=0;i<35;i++)
			    a.sendKeys(Keys.DOWN).perform();
			    a.sendKeys(Keys.RETURN).perform();*/
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
}
	
