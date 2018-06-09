package com.snapwiz.selenium.tests.staf.orion.testcases.IT15.R151;

import java.util.List;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.uihelper.MouseHover;

/*
 * Created by Sumit on 7/11/2014.
 */
public class ShowDiagChapterPracticeSettingsBlock extends Driver {

    @Test(priority = 1, enabled = true)
    public void  showDiagChapterPracticeSettingsBlock()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new LoginUsingLTI().ltiLogin("14");		//login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            //TC row no. 15
            String practiceBlock0 = driver.findElement(By.className("al-customize-course-toggle-block")).getText();
            System.out.println("practiceBlock0 : " + practiceBlock0);
            if(practiceBlock0.length()>0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In \"Customize Orion Topics\" shows block to show Diag/chapter practice.");
            }
            driver.findElement(By.cssSelector("div.tloToggle.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();//click on OFF for TLO
            Thread.sleep(2000);
            //TC row no. 16
            String practiceBlock = driver.findElement(By.className("al-customize-course-toggle-block")).getText();
            if(practiceBlock == null)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking ON for TLO the chapter practice block doesn't appear as a pop-up.");
            }
            //TC row no. 14
            int chapterPractice = driver.findElements(By.cssSelector("div[class='chapter-practice al-chkbox-holder off-state']")).size();
            if(chapterPractice != 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("By default the chapter practice is not ON.");
            }
            int diagPractice = driver.findElements(By.cssSelector("div[class='diag-test al-chkbox-holder off-state']")).size();
            if(diagPractice != 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("By default the Diagnostic Test for Chapter is not ON.");
            }

            driver.findElement(By.cssSelector("span[class='al-question-help-img al-chapter-practice-help-img']")).click();//click on chapter practice help icon
            Thread.sleep(2000);
            //TC row no. 17
            String helpData = driver.findElement(By.className("help-data-container")).getText();
            if(!helpData.contains("If you turn OFF one or more learning objectives, then Questions from hidden learning objectives will still be delivered through \"Chapter Practice\"."))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking help icon near Chapter Practice the help data is not shown correctly.");
            }
            driver.findElement(By.cssSelector("span[class='al-question-help-img al-diag-practice-help-img']")).click();//click on diag practice help icon
            Thread.sleep(2000);
            //TC row no. 17
            String helpData1 = driver.findElement(By.className("help-data-container")).getText();
            if(!helpData1.contains("If you turn OFF one or more learning objectives, then Questions from hidden learning objectives will still be delivered through \"Diagnostic Test\"."))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking help icon near Diag Practice the help data is not shown correctly.");
            }
            //TC row no. 18
            String chapterPracticeOnOff = driver.findElement(By.cssSelector("div[class='chapter-practice al-chkbox-holder on-state']")).getText();
            if(chapterPracticeOnOff == null)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("no ON/OFF button near \"Chapter practice\" block.");
            }
            //TC row no. 19
            String diagPracticeOnOff = driver.findElement(By.cssSelector("div[class='diag-test al-chkbox-holder on-state']")).getText();
            if(diagPracticeOnOff == null)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("no ON/OFF button near \"Diagnostic Test for Chapter\" block.");
            }
            driver.findElement(By.className("al-back-btn-icon")).click();//click on back button
            Thread.sleep(2000);
            driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            //TC row no. 20
            String practiceBlock1 = driver.findElement(By.className("al-customize-course-toggle-block")).getText();
            if(practiceBlock1 == null)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Navigate any other page and come back to same page the changes made is not correctly shown.");
            }
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase showDiagChapterPracticeSettingsBlock in class ShowDiagChapterPracticeSettingsBlock.", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void  OFFallTheTLOsForChapter()
    {
        WebDriver driver=Driver.getWebDriver();
        try {

                
                new LoginUsingLTI().ltiLogin("21");        //login as instructor
                new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
                driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
                Thread.sleep(2000);
                driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
                Thread.sleep(2000);
                new Settings().offTLOSOfChapter(1,0);
                //TC row no. 21
                int chapterOff = driver.findElements(By.cssSelector("div[class='chapterToggle al-chkbox-holder off-state']")).size();
                if(chapterOff != 1)
                {
                    new Screenshot().captureScreenshotFromTestCase();
                    Assert.fail("After clicking on OFF for all TLOs of first chapter the first chapter doesn't set to OFF.");
                }

                new LoginUsingLTI().ltiLogin("22");        //login as student
                //TC row no. 22
                String chapter = driver.findElement(By.className("al-content-header-row")).getText();
                if(chapter.contains("Ch 1:"))
                {
                    new Screenshot().captureScreenshotFromTestCase();
                    Assert.fail("After clicking on OFF for all TLOs of first chapter the student still can see the first chapter.");
                }
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase OFFallTheTLOsForChapter in class ShowDiagChapterPracticeSettingsBlock.", e);
        }
    }
    @Test(priority = 3, enabled = true)
    public  void impactsOnSwitchingOFFAndONOfChapter()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new LoginUsingLTI().ltiLogin("23");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("div.al-customize-course-disabled.al-chkbox")).click();    //click OFF button for chapter
            Thread.sleep(2000);
            boolean isPresent = false;
            List<WebElement> allTLO = driver.findElements(By.className("al-terminal-objective-title"));
            for (WebElement l: allTLO) {
                if (l.getText().startsWith("1.")) {
                     isPresent = true;
                }
            }
            //TC row no. 23
            if(isPresent == true)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking on OFF for first chapter the TLOs under first chapter doesn't set to OFF.");
            }
            driver.findElement(By.cssSelector("div.al-customize-course-enabled.al-chkbox1")).click();   //click ON button for chapter
            Thread.sleep(2000);
            //TC row no. 24
            int tloOff = driver.findElements(By.cssSelector("div[class='tloToggle al-chkbox-holder off-state']")).size();
            if(tloOff != 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking on ON for first chapter all the TLOs under first chapter doesn't set to ON.");
            }
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase impactsOnSwitchingOFFAndONOfChapter in class ShowDiagChapterPracticeSettingsBlock.", e);
        }
    }
    @Test(priority = 4, enabled = true)
    public  void impactsOnSwitchingOFFAndONOfChapterPractice()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new LoginUsingLTI().ltiLogin("25");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("div.tloToggle.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();//click on OFF for TLO
            Thread.sleep(2000);
            //TC row no. 25
            int chapterPractice = driver.findElements(By.cssSelector("div[class='chapter-practice al-chkbox-holder off-state']")).size();
            if(chapterPractice != 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("By default the chapter practice is not ON.");
            }
            driver.findElement(By.cssSelector("div.chapter-practice.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();    //click OFF Chapter practice
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("26");        //login as student
            new SelectChapterForTest().selectchapterfortest(0, 4); //Start the Diagnostic test
            new DiagnosticTest().attemptAllCorrect(2, false, false);
			new Navigator().orionDashboard();
            //TC row no. 26
			String isPracticeDisabled = driver.findElement(By.cssSelector("span[title='Practice']")).getAttribute("allowpractice");
			if(!isPracticeDisabled.contains("false"))
			{
				new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After Switching OFF settings for chapter practice the Practice button is not disabled in student side.");
			}
			WebElement we = driver.findElement(By.cssSelector("div[class='al-preformance-text']"));
			new MouseHover().mouserhoverbywebelement(we);
			//TC row no. 27
			String practiceButton = driver.findElement(By.cssSelector("div[title='Practice']")).getText();
			if(!practiceButton.contains("Practice"))
			{
				new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After Switching OFF settings for chapter practice the Practice button is not enabled for other TLOs in student side.");
			}
            
            new LoginUsingLTI().ltiLogin("25");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("div.tloToggle.al-chkbox-holder > div.al-customize-course-enabled.al-chkbox1")).click();//ON the TLO
            Thread.sleep(2000);
            
            new LoginUsingLTI().ltiLogin("26");        //login as student
            //TC row no. 28
			String isPracticeDisabled1 = driver.findElement(By.cssSelector("span[title='Practice']")).getAttribute("allowpractice");
			if(isPracticeDisabled1 != null)
			{
				new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After Switching ON settings for chapter practice the Practice button is disabled in student side.");
			}
            
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase impactsOnSwitchingOFFAndONOfChapter in class ShowDiagChapterPracticeSettingsBlock.", e);
        }
    }
    @Test(priority = 5, enabled = true)
    public  void impactsOnSwitchingOFFAndONDiagnosticTest()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new LoginUsingLTI().ltiLogin("29");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("div.tloToggle.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();//click on OFF for TLO
            Thread.sleep(2000);
            //TC row no. 29
            int diagPractice = driver.findElements(By.cssSelector("div[class='diag-test al-chkbox-holder off-state']")).size();
            if(diagPractice != 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("By default the Diagnostic Test for Chapter is not ON.");
            }
            driver.findElement(By.cssSelector("div.diag-test.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();    //click OFF Diagnostic Test for Chapter
            Thread.sleep(2000);
            //TC row no. 30
            String notice = driver.findElement(By.className("al-notification-message-body")).getText();
            if(!notice.contains("If you switch off diagnostic test for this chapter, you will NOT be able to switch it on in future again."))
            {
            	new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("clicking OFF for Diagnostic Test for Chapter the required pop_up doesnt appear.");
            }
            driver.findElement(By.linkText("Yes")).click();	//click on YES in pop-up
            Thread.sleep(2000);
            //TC row no. 31
            int diagPractice1 = driver.findElements(By.cssSelector("div[class='diag-test al-chkbox-holder off-state']")).size();
            if(diagPractice1 != 1)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("instructor in unable to OFF the Diagnostic Test for Chapter.");
            }
            
            
            new LoginUsingLTI().ltiLogin("32");        //login as student
            //TC row no. 32
            int tlo = driver.findElements(By.className("al-content-body-wrapper-enabled ")).size();
            if(tlo == 0)
            {
            	new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking OFF the Diagnostic Test for Chapter, the TLOs are not shown to the student.");
            }
            //TC row no. 33
            int pin = driver.findElements(By.className("al-proficiency-pin")).size();
            if(pin != 0)
            {
            	new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Pin in shown for the TLO in the dashboard ");
            }
            
            new LoginUsingLTI().ltiLogin("29");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("div.tloToggle.al-chkbox-holder > div.al-customize-course-enabled.al-chkbox1")).click();//click on ON for TLO
            Thread.sleep(2000);
            
            new LoginUsingLTI().ltiLogin("32");        //login as student
            //TC row no. 34
            int tlo1 = driver.findElements(By.className("al-content-body-wrapper-enabled ")).size();
            if(tlo1 == 0)
            {
            	new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking ON the Diagnostic Test for Chapter, there is changes, diag are not skipped.");
            }
            
            new LoginUsingLTI().ltiLogin("29");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("div.tloToggle.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();//click on OFF for TLO
            Thread.sleep(2000);
            //TC row no. 35
            int diagPractice2 = driver.findElements(By.cssSelector("div[class='diag-test al-chkbox-holder off-state']")).size();
            if(diagPractice2 != 1)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In the block it doesnt show diagnostic test as OFF.");
            }
            driver.findElement(By.cssSelector("div.diag-test.al-chkbox-holder > div.al-customize-course-enabled.al-chkbox1")).click();    //click OFF Diagnostic Test for Chapter
            Thread.sleep(2000);
            //TC row no. 36
            int diagPractice3 = driver.findElements(By.cssSelector("div[class='diag-test al-chkbox-holder off-state']")).size();
            if(diagPractice3 == 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Instructor is able ON again the Diagnostic Test for Chapter.");
            }
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase impactsOnSwitchingOFFAndONOfChapter in class ShowDiagChapterPracticeSettingsBlock.", e);
        }
    }
    @Test(priority = 6, enabled = true)
    public void clickOnNOlink()
    {
        WebDriver driver=Driver.getWebDriver();
    	try
    	{
             new LoginUsingLTI().ltiLogin("37");        //login as instructor
             new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
             driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
             Thread.sleep(2000);
             driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
             Thread.sleep(2000);
             driver.findElement(By.cssSelector("div.tloToggle.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();//click on OFF for TLO
             Thread.sleep(2000);
             driver.findElement(By.cssSelector("div.diag-test.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();    //click OFF Diagnostic Test for Chapter
             Thread.sleep(2000);
             driver.findElement(By.linkText("No")).click();	//click on NO in pop-up
             Thread.sleep(2000);
             //TC row no. 37
             int diagPractice1 = driver.findElements(By.cssSelector("div[class='diag-test al-chkbox-holder off-state']")).size();
             if(diagPractice1 == 1)
             {
                 new Screenshot().captureScreenshotFromTestCase();
                 Assert.fail("Clicking on NO link in pop-up for setting OFF in diagnostic test for Chapter.");
             }
    	}
    	catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase clickOnNOlink in class ShowDiagChapterPracticeSettingsBlock.", e);
        }
    }
    @Test(priority = 7, enabled = true)
    public void impactsOnSwitchingOFFAndONofTLO()
    {
        WebDriver driver=Driver.getWebDriver();
    	try
    	{
    		 new LoginUsingLTI().ltiLogin("38");        //login as instructor
             new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
             driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
             Thread.sleep(2000);
             driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
             Thread.sleep(2000);
             driver.findElement(By.cssSelector("div.tloToggle.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();//click on OFF for TLO
             Thread.sleep(2000);
             
             new LoginUsingLTI().ltiLogin("38_1");        //login as student
             new SelectChapterForTest().selectchapterfortest(0, 4); //Start the Diagnostic test
             new DiagnosticTest().attemptAllCorrect(2, false, false);
 			 new Navigator().orionDashboard();
    		 
    		 new LoginUsingLTI().ltiLogin("38");        //login as instructor
    		 new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
    		 driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
             Thread.sleep(2000);
             new Settings().offTLOSOfChapter(1,0);
             
             new LoginUsingLTI().ltiLogin("38_1");        //login as student
             //TC row no. 40
             String chapter = driver.findElement(By.className("al-content-header-row")).getText();
             if(chapter.contains("Ch 1:"))
             {
                 new Screenshot().captureScreenshotFromTestCase();
                 Assert.fail("After clicking on OFF for all TLOs of first chapter the student still can see the first chapter.");
             }
             //TC row no. 41
             String summaryBlock = driver.findElement(By.id("al-preformance-top-7-time-spent-content")).getCssValue("background-image");
             if(!summaryBlock.contains("time-spent-graph-chart.png"))
             {
                 new Screenshot().captureScreenshotFromTestCase();
                 Assert.fail("After clicking on OFF for all TLOs of first chapter the student still can see It under \"Summary\" block in the right side.");
             }
             //TC row no. 42
             String leastProfCh = driver.findElement(By.className("al-recommended-focus-area-chart-section")).getCssValue("background-image");
             if(!leastProfCh.contains("recommended-focus-area-graph-chart.png"))
             {
                 new Screenshot().captureScreenshotFromTestCase();
                 Assert.fail("After clicking on OFF for all TLOs of first chapter the student still can see It under \"Least Proficiency Chapter\" block in the right side.");
             }
    		 
    		 new LoginUsingLTI().ltiLogin("38");        //login as instructor
    		 new Navigator().navigateToReport("Metacognitive Report"); 
    		 driver.findElement(By.cssSelector("span[class='al-terminal-objective-title  student-metacognitive-report']")).click();	//click on student name
    		 Thread.sleep(2000);
    		 //TC row no. 43
    		 String chName = driver.findElement(By.className("al-terminal-objective-title")).getText();
             if(!chName.contains("Ch 1:"))
             {
                 new Screenshot().captureScreenshotFromTestCase();
                 Assert.fail("In \"Metacognitive Report\" it doesnt show all the data even if the chapter is OFF.");
             }
             driver.findElement(By.id("al-performance-report")).click();		//click on performance report
             Thread.sleep(2000);
             driver.findElement(By.className("al-terminal-objective-title")).click();  //click on student name
             Thread.sleep(2000);
             //TC row no. 44
             String chName1 = driver.findElement(By.className("coursePerformanceByChaptersForStudent-xAxisLabel")).getText();
             if(!chName1.contains("Ch1"))
             {
                 new Screenshot().captureScreenshotFromTestCase();
                 Assert.fail("In \"performance Report\" it doesnt show all the data even if the chapter is OFF.");
             }
             
    	}
    	catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase impactsOnSwitchingOFFAndONofTLO in class ShowDiagChapterPracticeSettingsBlock.", e);
        }
    }
}
