package com.snapwiz.selenium.tests.staf.learnon.testcases.IT22.R2213;

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.UIElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by durgapathi on 19/8/15.
 */
public class LocalizationForMarkbookPage {

    @Test(priority = 1,enabled = true)
    public void localizationChangesInMarkbookPage()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("72");
            new UIElement().waitAndFindElement(By.xpath(".//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            String markBookInNav = Driver.driver.findElement(By.cssSelector("span[data-localize='Gradebook']")).getText();
            System.out.println("markBookInNav::" + markBookInNav);
            if(!markBookInNav.trim().equals("Markbook"))
            {
                Assert.fail("\"Gradebook\" lable is not changed to \"Markbook\" lable in fourth row in the main navigator.");
            }
            new Click().clickBycssselector("span[data-localize='Gradebook']");
            // Verify Navigation of Markbook
            // Verify Markbook page label
            String markBookPage = Driver.driver.findElement(By.className("ins-gradebook-summary-header-title")).getText();
            System.out.println("markBookPage::"+markBookPage);
            if(!markBookPage.trim().equals("Markbook"))
            {
                Assert.fail("By clicking on the \"Markbook\" element in the main navigation is not navigate to \"Markbook\" page");
            }
            String markBookWeighting = Driver.driver.findElement(By.className("ls-ins-gradebook-weighting")).getText();
            System.out.println("markBookWeighting::"+markBookWeighting);
            if(!markBookWeighting.trim().equals("Markbook Weighting"))
            {
                Assert.fail("The \"Gradebook Weighting\" button is not changed to \"Markbook Weighting\" in right side of page.");
            }
            if(Driver.driver.getPageSource().contains("Gardebook"))
            {
                Assert.fail("The \"Gradebook Weighting\" button is not changed to \"Markbook Weighting\" in right side of page.");
            }
            new Click().clickByclassname("ls-ins-gradebook-weighting");
            boolean markbookWeightingpopup = Driver.driver.findElement(By.className("ls-ins-gradebook-weighting-header")).isDisplayed();
            if(markbookWeightingpopup==false)
            {
                Assert.fail("Click on \"Markbook Weighting\" button is not displayed the popup");
            }
            String markbookWeightingpopupText = Driver.driver.findElement(By.className("ls-ins-gradebook-weighting-header")).getText();
            System.out.println("markbookWeightingpopupText::" + markbookWeightingpopupText);
            if(!markbookWeightingpopupText.trim().equals("Enter Markbook Weighting(%)"))
            {
                Assert.fail("\"Markbook Weighting\" popup header text \"Enter Gradebook Weighting(%)\" is not changed \"Enter Markbook Weighting(%)\".");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase localizationChangesInMarkbookPage in class LocalizationForMarkbookPage", e);
        }
    }
}
