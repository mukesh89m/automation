package com.snapwiz.selenium.tests.staf.orion.testcases.IT15.R151;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

/*
 * Created by Sumit on 7/21/2014.
 */
public class CustomizeOrionTopicsInMentorSettingsPage {

	@Test(priority = 1, enabled = true)
    public void customizeOrionTopicsInMentorSettingsPage()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("124");		//login as mentor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");
            //TC row no. 124
            int offState = Driver.driver.findElements(By.cssSelector("div[class='al-chkbox-holder off-state']")).size();
            if(offState == 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Default state of \"Customize Orion topics\" is not OFF.");
            }
            Driver.driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            String tlo = Driver.driver.findElement(By.className("al-terminal-objective-title")).getText();
            Driver.driver.findElement(By.cssSelector("div.tloToggle.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();//click on OFF for TLO
            Thread.sleep(2000);
            
            new LoginUsingLTI().ltiLogin("124_1");		//login as student
            new SelectChapterForTest().selectchapterfortest(0, 4); //Start the Diagnostic test
            new DiagnosticTest().attemptAllCorrect(2, false, false);
            new Navigator().orionDashboard();
            String tlo1 = Driver.driver.findElement(By.className("al-terminal-objective-title")).getText();
            //TC row no. 125
            if(tlo.contains(tlo1))
            {
                Assert.fail("Hidden TLO is shown in student dashboard.");
            }
            
            new LoginUsingLTI().ltiLogin("124");		//login as mentor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");
            Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("div.al-customize-course-disabled.al-chkbox")).click();    //click OFF button for chapter
            Thread.sleep(2000);
            
            new LoginUsingLTI().ltiLogin("124_1");        //login as student
            //TC row no. 126
            String chapter = Driver.driver.findElement(By.className("al-content-header-row")).getText();
            if(chapter.contains("Ch 1:"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking on OFF  first chapter the student still can see the first chapter.");
            }
            
            
            new LoginUsingLTI().ltiLogin("127");		//login as another mentor of same class section
            new Navigator().navigateFromProfileDropDownForOrion("Settings");
            //TC row no. 127
            int onState = Driver.driver.findElements(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).size();
            if(onState != 1)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("By default \"Customize Orion topics\" is not ON since mentor of the same class_section has made it ON.");
            }
            Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            //TC row no. 128
            int chOff = Driver.driver.findElements(By.cssSelector("div[class='chapterToggle al-chkbox-holder off-state']")).size();
            if(chOff != 1)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("The TLO and chapter which was OFF by another mentor gets changed");
            }
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase customizeOrionTopicsInMentorSettingsPage in class CustomizeOrionTopicsInMentorSettingsPage.", e);
        }
    }
	@Test(priority = 2, enabled = true)
    public void offTloAndDiag()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("129");		//login as mentor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");
            Driver.driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("div.tloToggle.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();//click on OFF for TLO
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("div.diag-test.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();    //click OFF Diagnostic Test for Chapter
            Thread.sleep(2000);
            Driver.driver.findElement(By.linkText("Yes")).click();	//click on YES in pop-up
            Thread.sleep(2000);
            
            new LoginUsingLTI().ltiLogin("129_1");		//login as student
            //TC row no. 129
            int practice = Driver.driver.findElements(By.xpath("/*//*[starts-with(@class, 'al-link al-start-practice-button al-chapter-start-practice-button-')]")).size();
            if(practice == 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Diagnostic test is not skipped with showing all the TLOs of the chapter.");
            }
            
            new LoginUsingLTI().ltiLogin("130");		//login as another mentor of same class section
            new Navigator().navigateFromProfileDropDownForOrion("Settings");
            Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            //TC row no. 130
            int diagOff = Driver.driver.findElements(By.cssSelector("div[class='diag-test al-chkbox-holder off-state']")).size();
            if(diagOff != 1)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("The diag which was OFF by another mentor gets changed");
            }
            
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase offTloAndDiag in class CustomizeOrionTopicsInMentorSettingsPage.", e);
        }
     }
            
    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
