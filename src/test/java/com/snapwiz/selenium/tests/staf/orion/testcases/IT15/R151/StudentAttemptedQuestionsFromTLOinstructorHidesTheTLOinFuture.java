package com.snapwiz.selenium.tests.staf.orion.testcases.IT15.R151;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Sumit on 7/17/2014.
 */
public class StudentAttemptedQuestionsFromTLOinstructorHidesTheTLOinFuture {

    @Test
    public void studentAttemptedQuestionsFromTLOinstructorHidesTheTLOinFuture()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("86_1");        //login as student
            new SelectChapterForTest().selectchapterfortest(0, 4); //Start the Diagnostic test
            new DiagnosticTest().attemptAllCorrect(2, false, false);
            new Navigator().orionDashboard();
            new PracticeTest().startTest();//Start practice test
            new PracticeTest().practiceTestAttempt(2, 4, "correct", false, false);

            new LoginUsingLTI().ltiLogin("86");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            Driver.driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            String tlo = Driver.driver.findElement(By.className("al-terminal-objective-title")).getText();
            Driver.driver.findElement(By.cssSelector("div.tloToggle.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();//click on OFF for TLO
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("86_1");        //login as student
            //TC row no. 86
            String tlo1 = Driver.driver.findElement(By.className("al-terminal-objective-title")).getText();
            if(tlo.contains(tlo1))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("TLO which is OFF is still shown in the student dashboard.");
            }
            new Navigator().NavigateToStudentReport();
            Driver.driver.findElement(By.id("al-productivity-report")).click();     //login as productivity report
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("al-terminal-objective-title")).click();//click on Chapter name
            Thread.sleep(2000);
            boolean found = false;
            List<WebElement> allTlo = Driver.driver.findElements(By.className("al-terminal-objective-title"));
            for(WebElement l: allTlo)
            {
                if(l.getText().contains("1.1:"))
                {
                    found = true;
                    break;
                }
            }
            //TC row no. 87
            if(found == false)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("TLO which is OFF is not shown in the student side productivity report.");
            }
            //TC row no. 88
            int disabledTlo = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'al-link btn-disable al-tlo-start-practice-button al-tlo-start-practice-button-')]")).size();
            if(disabledTlo == 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("TLO which is OFF is not disabled in the student side productivity report.");
            }
            Driver.driver.findElement(By.id("al-metacognitive-report")).click();	//click on metacognitive Report
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("al-terminal-objective-title")).click();  //click on Chapter name
            Thread.sleep(2000);
            //TC row no. 92
            int size3 = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'al-link btn-disable al-tlo-start-practice-button al-tlo-start-practice-button-')]")).size();
            if(size3 != 1)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Practice\" button is not disabled for the TLO practice in metacognitive Report page.");
            }
            Driver.driver.findElement(By.id("al-most-challenging-activity")).click();	//click on most challenging activity
            Thread.sleep(2000);
            //TC row no. 96
            int size2 = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'al-link btn-disable al-tlo-start-practice-button al-tlo-start-practice-button-')]")).size();
            if(size2 != 1)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Practice\" button is not disabled for the TLO practice in most challenging activity page.");
            }
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase studentAttemptedQuestionsFromTLOinstructorHidesTheTLOinFuture in class StudentAttemptedQuestionsFromTLOinstructorHidesTheTLOinFuture.", e);
        }
    }
    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
