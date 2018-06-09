package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

/**
 * Created by priyanka on 7/23/2015.
 */
public class SelectParticularQuestionFromCustomAssignment extends Driver {

    public void selectParticularQuestionFromCustomAssignment(String questionType)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            List<WebElement> allOpenLink=driver.findElements(By.xpath("//label[@class='ui-dropdownchecklist-text']"));
            allOpenLink.get(0).click();
            Thread.sleep(2000);
            for (int a = 0; a < allOpenLink.size(); a++) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allOpenLink.get(a));
                Thread.sleep(1000);
                System.out.println("allOpenLink:"+allOpenLink.get(a).getText());
                if ((allOpenLink.get(a).getText().contains(questionType))) {
                    System.out.println(allOpenLink.get(a).isDisplayed());
                    if (allOpenLink.get(a).isDisplayed()) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOpenLink.get(a));
                        break;
                    }
                }
            }
            Thread.sleep(3000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", myQuestionBank.getGo_Button());//click on go
            Thread.sleep(2000);
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollBy(0,-700)", "");
        }

        catch(Exception e)
        {
            Assert.fail("exception in app helper selectParticularQuestionFromCustomAssignment in method SelectParticularQuestionFromCustomAssignment", e);
        }
    }


}
