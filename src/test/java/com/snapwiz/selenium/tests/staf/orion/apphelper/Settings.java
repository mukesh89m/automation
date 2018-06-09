package com.snapwiz.selenium.tests.staf.orion.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by root on 8/13/14.
 */
public class Settings extends Driver{

    public void offTLOSOfChapter(int chapterNumber,int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            int count1 = 0;
            List<WebElement> allTLO = driver.findElements(By.className("al-terminal-objective-title"));

            for (WebElement l: allTLO) {
                if (l.getText().startsWith(Integer.toString(chapterNumber)+".")) {
                    count1++;
                }
            }
            List<WebElement> allOffButton = driver.findElements(By.cssSelector("div[class='al-customize-course-disabled al-chkbox']"));
            for(int i = 3; i<count1+3; i++)
            {
                allOffButton.get(i).click();//click on OFF for TLOs of First Chapter
                Thread.sleep(2000);
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception while switching off tlos from instructor settings page", e);
        }
    }

    public void tloOFF(int dataIndex,String chapterNumber)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            List<WebElement> tlobuttons = null;
            WebElement tloOffButton = null;

            if(chapterNumber.equals("all")){
                tlobuttons = driver.findElement(By.xpath("//div[@class='al-customize-course-content-wrapper']")).findElements(By.cssSelector("div[class='tloToggle al-chkbox-holder on-state']"));
                System.out.println("tlobuttons :" + tlobuttons.size());
                int count = 0;
                for(int a=0;a<tlobuttons.size();a++){
                    tloOffButton = tlobuttons.get(a).findElement(By.cssSelector("div[class='al-customize-course-disabled al-chkbox']"));
                    tloOffButton.click();
                    System.out.println(count++);
                }
            }else{
                tlobuttons = driver.findElement(By.xpath("(//div[@class='al-customize-course-content-wrapper'])["+chapterNumber+"]")).findElements(By.cssSelector("div[class='tloToggle al-chkbox-holder on-state']"));
                for(int a=0;a<tlobuttons.size();a++){
                    tloOffButton = tlobuttons.get(a).findElement(By.cssSelector("div[class='al-customize-course-disabled al-chkbox']"));
                    tloOffButton.click();
                }
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception while switching off tlos from instructor settings page", e);
        }
    }
}
