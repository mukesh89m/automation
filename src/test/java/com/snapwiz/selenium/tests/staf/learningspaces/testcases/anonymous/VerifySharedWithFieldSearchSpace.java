package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;


import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;

public class VerifySharedWithFieldSearchSpace extends Driver
{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.VerifySharedWithFieldSearchSpace");
	/*
	 * 141-155
	 */
	@Test
	public void verifySharedWithFieldSearchSpace()
	{
		try
		{
			driver.manage().deleteAllCookies();
			new LoginUsingLTI().ltiLogin("141");
			try
			{
				boolean postvalue=driver.findElement(By.className("ls-post-tab")).isDisplayed();
				boolean linkvalue=driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/ul/li[2]/a")).isDisplayed();
				boolean uploadvalue=driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/ul/li[3]/a")).isDisplayed();
				if(postvalue==true && linkvalue==true && uploadvalue==true)
				{
					logger.log(Level.INFO,"post,link and uploadFile option Present ");
					boolean sharevalue=driver.findElement(By.className("text--nowrap")).isDisplayed();
					if(sharevalue==true)
					{
						logger.log(Level.INFO,"share with text present ");
						String [] testdata =  ReadTestData.readData("LoginUsingLTI", "141");
					
						String sharewithvalue=driver.findElement(By.className("item-text")).getAttribute("title");
					
						Thread.sleep(5000);
						if(testdata[9].equals((sharewithvalue).trim()))
						{
						logger.log(Level.INFO," ByDefault ClassSectionName presnet in share with option ");
						Thread.sleep(5000);
						driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div/form/footer/label/div/div/ul/li/a")).click();
						Thread.sleep(5000);
						String shrvalue=driver.findElement(By.className("holder")).getText();
						Thread.sleep(5000);
						if(shrvalue.equals(null))
						{
							driver.findElement(By.className("holder")).sendKeys(testdata[9]);
							Thread.sleep(5000);
							
						}
						
						}
						else
						{
							logger.log(Level.INFO," ByDefault ClassSectionName Not  presnet in share with option");
							TestCase.fail();
						}
					
					}
					else
					{
					logger.log(Level.INFO,"share with text not present ");
					TestCase.fail();
					}
				}
				else
				{
				
				}
			}
			catch(Exception e)
			  {
				  logger.log(Level.SEVERE,"Exception in Login page Application Helper",e);
				  driver.quit();
			  }
		}
		catch(Exception e)
		  {
			  logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper",e);
			  driver.quit();
		  }
	}
	

	
	}


