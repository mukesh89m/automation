package com.snapwiz.selenium.tests.staf.learningspaces.uihelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class ClickOnResourcesToOpen extends Driver
{
	public void clickOnResourcesToOpen(String dataIndex)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			String resourcesName=ReadTestData.readDataByTagName("", "resourcesname", dataIndex);
			List<WebElement> allresources=driver.findElements(By.cssSelector("li[class='resource-content-posts-list']"));
			int index=1;
			for(WebElement resources:allresources)
			{
				String resourcesName1=resources.getText();						
                Thread.sleep(2000);
				if(resourcesName1.contains(resourcesName))
				{
					driver.findElements(By.className("ls-resource-show-assign-this-block")).get(index-1).click();
                    //new Click().clickbylist("ls-resource-show-assign-this-block",index-1);
					break;
				}
				index++;
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper ClickOnResourcesToOpen in method clickOnResourcesToOpen ",e);
		}
	}

}
