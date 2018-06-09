package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by root on 5/11/15.
 */
public class Reports extends Driver{
    public void navigateToPerformanceReportPage(int dataIndex,int chapterNo){
        try{
            WebDriver driver=Driver.getWebDriver();
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Navigator().NavigateTo("e-Textbook");
            List<WebElement> chapterNamesElementsList = driver.findElements(By.cssSelector("h3[class = 'chapter-heading-label']"));
            new Click().clickByElement(chapterNamesElementsList.get(chapterNo-1));
            new Click().clickbylinkText(assessmentName);
        }catch(Exception e){
            Assert.fail("Exception in method 'navigateToPerformanceReportPage' in the class 'navigateToPerformanceReportPage'",e);
        }

    }
}
