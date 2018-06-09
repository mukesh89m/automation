package com.snapwiz.selenium.tests.staf.edulastic.uihelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;


public class ShareWith extends Driver {
	
	public void share(int dataIndex, String shareName,boolean removeExistingShare)
	{
        WebDriver driver=Driver.getWebDriver();
		try
		{
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
			boolean sharefound = false;
//            new Click().clickByXpath("//input[@id='assign-now']/following-sibling::ins");//Click on Assign now radio button
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollBy(0,-250)", "");
            new Click().clickByclassname("holder");
			WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("maininput")));
            if(!new Exception().getStackTrace()[1].getMethodName().contains("updateAssignment")) {//removing of added student is not allowed while updating assignment
                if(removeExistingShare == true) {
                    WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("closebutton")));
                    driver.findElement(By.className("maininput")).sendKeys(shareName);
                    Thread.sleep(3000);
                    List<WebElement> suggestname;
                    if (shareWithClass.toUpperCase().equals("TRUE"))
                        suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'cls_')]"));
                    else
                        suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
                    for (WebElement name : suggestname) {
                        if (name.getText().trim().equals(shareName)) {
                            name.click();
                            sharefound = true;
                            break;
                        }
                    }
                    if (sharefound == false)
                        Assert.fail("No value selected from the Assign To field");
                }
            }


		}
		catch(Exception e)
		{
			Assert.fail("Exception in UI helper ShareWith",e);
		}
	}

}
