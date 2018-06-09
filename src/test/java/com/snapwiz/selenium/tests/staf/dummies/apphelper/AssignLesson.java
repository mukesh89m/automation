package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.ReadTestData;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.Click;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.ShareWith;


public class AssignLesson
{
	
	/*
	 * @Brajesh
	 * Assign the lesson to  default class section name
	 */
	public void assignlessonwithdefaultclassection(int dataIndex)
	{
		try
		{
			String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
			String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
			String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
			Driver.driver.findElement(By.cssSelector("div[class='ls-right-tab-hover-sprite assign-lesson-bg']")).click();//click on assign this tab
			Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
			Thread.sleep(2000);

			Driver.driver.findElement(By.id("due-time")).click();//click on dur time
			List<WebElement> elements = Driver.driver.findElements(By.xpath("//li"));//fetch all time section
			for(WebElement time : elements)
			{
				if(time.getText().equals(duetime))
				{
					time.click();
					break;
				}
			}
			Driver.driver.findElement(By.id("due-date")).click();//click on due date
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(duedate)).click();//click on specified date
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("additional-notes")).clear();
		    Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
		    Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
		    Thread.sleep(5000);
			
		}
		catch(Exception e)
		{
			Assert.fail("Exceptiion in apphelper method assignlessonwithdefaultclassection in class  AssignLesson",e);
		}
	}
	
	public void assigncart(int dataIndex)
	{
		try
		{
			String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
			String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
			String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
			new Click().clickByclassname("submit-assignment-content");//click on assign on the cart
			Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
			Thread.sleep(2000);

			Driver.driver.findElement(By.id("due-time")).click();//click on dur time
			Thread.sleep(2000);
			List<WebElement> elements = Driver.driver.findElements(By.xpath("//li"));//fetch all time section
			for(WebElement time : elements)
			{
				if(time.getText().equals(duetime))
				{
					time.click();
					break;
				}
			}
			Driver.driver.findElement(By.id("due-date")).click();//click on due date
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(duedate)).click();//click on specified date
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("additional-notes")).clear();
		    Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
		    Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
		    Thread.sleep(5000);
			
		}
		catch(Exception e)
		{
			Assert.fail("Exceptiion in apphelper method assigncart in class  AssignLesson",e);
		}
	}
	
	public void assigncartwithclasssection(int dataIndex, String ...appendCharacter)
	{
		try
		{
			String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
			String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
			String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
			String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
			new Click().clickByclassname("submit-assignment-content");//click on assign on the cart
			Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
			Thread.sleep(2000);
			if(!shareWithClass.toUpperCase().equals("TRUE"))
            {
            	String methodName = new Exception().getStackTrace()[1].getMethodName();
    			String shareName = methodName+appendCharacter[0];
                Thread.sleep(3000);
		        new ShareWith().share(shareName,true);
            }
			//new ShareWith().share(shareWithInitialString, shareName, shareWithClass,true);
			Thread.sleep(3000);
			Driver.driver.findElement(By.id("due-time")).click();//click on dur time
			Thread.sleep(2000);
			List<WebElement> elements = Driver.driver.findElements(By.xpath("//li"));//fetch all time section
			for(WebElement time : elements)
			{
				if(time.getText().equals(duetime))
				{
					time.click();
					break;
				}
			}
			Driver.driver.findElement(By.id("due-date")).click();//click on due date
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(duedate)).click();//click on specified date
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("additional-notes")).clear();
		    Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
		    Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
		    Thread.sleep(5000);
			
		}
		catch(Exception e)
		{
			Assert.fail("Exceptiion in apphelper method assigncart in class  AssignLesson",e);
		}
	}
	
	public void assigncartwithclasssectionwithoutshorandwithdefaultclass(int dataIndex, String ...appendCharacter)
	{
		try
		{
			String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
			String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
			String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
			String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
			//new Click().clickByclassname("submit-assignment-content");//click on assign on the cart
			//Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
			Thread.sleep(2000);
			if(!shareWithClass.toUpperCase().equals("TRUE"))
            {
            	String methodName = new Exception().getStackTrace()[1].getMethodName();
    			String shareName = methodName+appendCharacter[0];
                Thread.sleep(3000);
		        new ShareWith().share(shareName,true);
            }
			//new ShareWith().share(shareWithInitialString, shareName, shareWithClass,false);
			Thread.sleep(3000);
			Driver.driver.findElement(By.id("due-time")).click();//click on dur time
			Thread.sleep(2000);
			List<WebElement> elements = Driver.driver.findElements(By.xpath("//li"));//fetch all time section
			for(WebElement time : elements)
			{
				if(time.getText().equals(duetime))
				{
					time.click();
					break;
				}
			}
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("due-date")).click();//click on due date
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(duedate)).click();//click on specified date
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("additional-notes")).clear();
		    Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
		    Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
		    Thread.sleep(5000);
			
		}
		catch(Exception e)
		{
			Assert.fail("Exceptiion in apphelper method assigncart in class  AssignLesson",e);
		}
	}
	
	public void Assigncustomeassignemnt(int dataIndex, String ...appendCharacter)
	{
		try
		{
			String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
			String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
			String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
			String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
			new Click().clickByclassname("assign-this");//click on assign this link
			if(!shareWithClass.toUpperCase().equals("TRUE"))
            {
            	String methodName = new Exception().getStackTrace()[1].getMethodName();
    			String shareName = methodName+appendCharacter[0];
                Thread.sleep(3000);
		        new ShareWith().share(shareName,true);
            }
			//new ShareWith().share(shareWithInitialString, shareName, shareWithClass,true);
			Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
			Thread.sleep(2000);

			Driver.driver.findElement(By.id("due-time")).click();//click on dur time
			Thread.sleep(2000);
			List<WebElement> elements = Driver.driver.findElements(By.xpath("//li"));//fetch all time section
			for(WebElement time : elements)
			{
				if(time.getText().equals(duetime))
				{
					time.click();
					break;
				}
			}
			Driver.driver.findElement(By.id("due-date")).click();//click on due date
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(duedate)).click();//click on specified date
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("additional-notes")).clear();
		    Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
		    Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
		    Thread.sleep(5000);
			
		}
		catch(Exception e)
		{
			Assert.fail("Exceptiion in apphelper method Assigncustomeassignemnt in class  AssignLesson",e);
		}
	}
	public void assignResourcesFormrightTab(int dataIndex)
	{
		try
		{
			
			String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
			String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
			String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", Integer.toString(dataIndex));
/*			String  shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", Integer.toString(dataIndex));
			String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(dataIndex));
			String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
			new ShareWith().share(shareWithInitialString, shareName, shareWithClass,true);
*/			Driver.driver.findElement(By.className("input-filed")).sendKeys("shor"); //short label
			Thread.sleep(2000);

			Driver.driver.findElement(By.id("due-time")).click();//click on dur time
			Thread.sleep(2000);
			List<WebElement> elements = Driver.driver.findElements(By.xpath("//li"));//fetch all time section
			for(WebElement time : elements)
			{
				if(time.getText().equals(duetime))
				{
					time.click();
					break;
				}
			}
			Driver.driver.findElement(By.id("due-date")).click();//click on due date
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("a[title='Next']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(duedate)).click();//click on specified date
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("additional-notes")).clear();
		    Driver.driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);//add additional note
		    Driver.driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")).click();//click on assign  button
		    Thread.sleep(5000);
			
		}
		catch(Exception e)
		{
			Assert.fail("Exceptiion in apphelper method assignResourcesFormrightTab in class  AssignLesson",e);
		}
				
	}



}
