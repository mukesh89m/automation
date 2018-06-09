package com.snapwiz.selenium.tests.staf.orion.testcases.IT15.R151;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by Sumit on 7/16/2014.
 */
public class AccessDashboardAfterInstructorHidingDiagnosticTestForChapter {

    @Test
    public void accessDashboardAfterInstructorHidingDiagnosticTestForChapter()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("83");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            Driver.driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("div.tloToggle.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();//click on OFF for TLO
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("div.diag-test.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();    //click OFF Diagnostic Test for Chapter
            Thread.sleep(2000);
            Driver.driver.findElement(By.linkText("Yes")).click();//click on Yes link on popup
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("83_1");        //login as student
            //TC row no. 83
            int tlo = Driver.driver.findElements(By.className("al-content-body-wrapper-enabled ")).size();
            if(tlo == 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking OFF the Diagnostic Test for Chapter, the TLOs are not shown to the student.");
            }
            //TC row no. 84
            int pin = Driver.driver.findElements(By.className("al-proficiency-pin")).size();
            if(pin != 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Pin is shown for the TLO in the dashboard ");
            }
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase studentAccessDashboardReportsAfterInstructorHidingChapterPractice in class StudentAccessDashboardReportsAfterInstructorHidingChapterPractice.", e);
        }
    }
    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
