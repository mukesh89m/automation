package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiscussionWidget;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class EnableDisableDiscussionWidget extends Driver {
	
	@Test
	public void enableDisableDiscussionWidget()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("24");//login a instructor
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			//TC row no. 25 
			String color = driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg selected']")).getCssValue("background-position");
			System.out.println("color: "+color);
            if(!color.equals("-25px -90px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Enabled tab doesnt appear in green color.");
			}
			//TC row no. 26
			int enableIcon = driver.findElements(By.cssSelector("div[class='select-question ls-publisherIcons-bg selected']")).size();
			if(enableIcon == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Each question tab doesnt have icon to enable.");
			}
			//TC row no. 27
			String enableIconColor = driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg selected']")).getCssValue("background-position");
            System.out.println("enableIconColor: "+enableIconColor);
			if(!enableIconColor.contains("-25px -90px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Icon to enable doesnt appear green for enabled tab.");
			}
			String str1 = new RandomString().randomstring(10);
			new DiscussionWidget().addTabInDW(str1);
			//TC row no. 28
			String disableIconColor = driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg']")).getCssValue("background-position");
            System.out.println("disableIconColor: "+disableIconColor);
			if(!disableIconColor.contains("2px -90px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Icon to disable doesnt appear grey for disabled tab.");
			}
			
			new DiscussionWidget().enableOrDisableDWQuestion(1);	//enable the 2nd tab DW question
			//TC row no. 30
			List<WebElement> color1 = driver.findElements(By.cssSelector("span[class='publisher-count ls-publisherIcons-bg ls-publisher-count-bg-holder']"));
            for(WebElement l: color1)
            {
                System.out.println("color1: "+l.getCssValue("background-position"));
            }
			if(!color1.get(1).getCssValue("background-position").equals("-5px -2px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Recently Enabled tab doesnt appear in green color.");
			}
			//TC row no. 31
			String enableIconColor1 = driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg selected']")).getCssValue("background-position");
			if(!enableIconColor1.contains("-25px -90px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Icon to enable doesnt appear green for recently enabled tab.");
			}
			//TC row no. 32
			String tooltip = driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg selected']")).getAttribute("title");
			if(tooltip.contains("Enable"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Tooltip is displayed for the icon in green.");
			}
			new DiscussionWidget().navigateToTab(0);//navigate to 1st tab
			//TC row no. 33
			String isEnabled = driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg']")).getAttribute("title");
			if(!isEnabled.contains("Enable"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After we enable 2tab the 1st tab doesnt become disabled.");
			}
			//TC row no. 35
			if(!isEnabled.contains("Enable"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The tooltip doesnt say \"Enable\" for the 1st tab which is disabled.");
			}
			//TC row no. 34
			String disableIconColor1 = driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg']")).getCssValue("background-position");
			if(!disableIconColor1.contains("2px -90px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Icon to disable doesnt appear grey for disabled 1st tab.");
			}
			//TC row no. 34
			String color2 = driver.findElement(By.cssSelector("span[class='publisher-count ls-publisherIcons-bg ls-publisher-count-bg-holder']")).getCssValue("background-position");
			if(color2.equals("-5px -2px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Disable tab doesnt appear in grey color.");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span.ls-discussion-widget-publisherIcons-bg.ls-discussion-widget-publisher-addCount-bg")));	//click on + icon to add tabs
			Thread.sleep(2000);
			List<WebElement> widgetdefaulttext = driver.findElements(By.cssSelector("div[class='widget-content']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", widgetdefaulttext.get(widgetdefaulttext.size()-1));//click on default content
			Thread.sleep(3000);
			String str = new RandomString().randomstring(25);
			WebElement t=driver.findElement(By.className("text-iframe"));
			driver.switchTo().frame(t);
			Actions ac = new Actions(driver);
			for(int i=0;i<10;i++)
				ac.sendKeys(Keys.DELETE);
			driver.findElement(By.xpath("/html/body")).sendKeys(str);
			driver.switchTo().defaultContent();
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html")).click();
			Thread.sleep(2000);
			new DiscussionWidget().enableOrDisableDWQuestion(2);//enable the recent tab
			//TC row no. 36
			String isEnabled1 = driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg']")).getAttribute("title");
			if(!isEnabled1.contains("Enable"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After we click on enable icon for the recently added tab then it doesnt become enable.");
			}
			//TC row no. 37
			List<WebElement> color3 = driver.findElements(By.cssSelector("span[class='publisher-count ls-publisherIcons-bg ls-publisher-count-bg-holder']"));
			if(!color3.get(2).getCssValue("background-position").equals("-5px -2px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Recently Enabled tab doesnt appear in green color.");
			}
			List<WebElement> disableIconColor2 = driver.findElements(By.xpath("//*[starts-with(@class, 'select-question ls-publisherIcons-bg')]"));
			//TC row no. 37
			if(!disableIconColor2.get(disableIconColor2.size()-1).getCssValue("background-position").contains("-25px -90px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Icon to enable doesnt appear green.");
			}
			new DiscussionWidget().navigateToTab(0); //navigate to tab 1
			//TC row no. 38
			String isEnabled2 = driver.findElement(By.cssSelector("div[class='select-question ls-publisherIcons-bg']")).getAttribute("title");
			if(!isEnabled2.contains("Enable"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After we enable recently added tab the previously enabled tab doesnt become disabled.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase enableDisableDiscussionWidget in class EnableDisableDiscussionWidget.",e);
		}
	}

}
