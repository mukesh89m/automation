package com.snapwiz.selenium.tests.staf.orion.testcases.regression.orion;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.orion.apphelper.*;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.Settings.Setting;
import com.snapwiz.selenium.tests.staf.orion.uihelper.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by root on 2/1/16.
 */
public class Settings extends Driver{

    @Test(priority = 1, enabled = true)
    public void customizeOrionTopics()
    {
        WebDriver driver=null;
        try
        {
            driver=Driver.getWebDriver();
            ReportUtil.log("Validating the Customize Orion Topics details","Validating the Customize Orion Topics details","info");
            Setting setting = PageFactory.initElements(driver,Setting.class);
            new LoginUsingLTI().ltiLogin("2");		//login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");
            List<WebElement> text;
            text = setting.instructorSettingsDiscussionLabelList;
            //TC row no. 2
            if(!text.get(2).getText().contains("Customize Orion topics"))
            {
                CustomAssert.fail("Validating 'Customize Orion Topics' label", "\"Customize Orion topics\" is not present in Settings page of Orion course.");

            }
            if(text.get(2).getText().contains("More options"))
            {
                CustomAssert.fail("Validating 'More Options' link","\"More options\" is present in Settings page of Orion course.");
            }
            //TC row no. 3
            int offState = driver.findElements(By.cssSelector("div[class='al-customize-course-disabled customize-topic-disabled al-chkbox']")).size();
            if(offState == 0)
            {
                CustomAssert.fail("Default state of 'Customize Orion Topics'","Default state of \"Customize Orion topics\" is not OFF.");
            }
            setting.button_orionTopicsON.click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            text = driver.findElements(By.className("instructor-settings-discussion-label"));
            //TC row no. 4
            if(!text.get(2).getText().contains("More options"))
            {
                CustomAssert.fail("Validating 'More Options' link","After clicking ON for \"Customize Orion topics\" it doesn't show \"More options\" link next to \"Customize Orion topics\" text.");
            }
            //TC row no. 5
            String icon = setting.instructorMoreOptionsIcon.getCssValue("background-image");
            if(!icon.contains("ins-next-btn.png"))
            {
                CustomAssert.fail("Validaing 'More Options' link","\"More options\" text doesn't have a icon nearby.");
            }
            setting.link_moreOptions.click();    //click on More option
            Thread.sleep(2000);
            //TC row no. 6
            String name = setting.allCustomizeCourseText.getText();
            if(!name.contains("Customize Orion Topics"))
            {
                CustomAssert.fail("Functionality of More options link","On clicking \"More option\" it doesn't take to \"Customize the course\" page with title as \"Customize Orion Topics\".");
            }
            //TC row no. 7
            int backIcon = setting.orionBackIconList.size();
            if(backIcon == 0)
            {
                CustomAssert.fail("Validating Back icon","\"Customize the course\" page doesn't show app back icon before \"Customize Orion Topics\" title.");
            }
            //TC row no. 9
            int chapterOnIcon = setting.chapterToggleONButtonList.size();
            if(chapterOnIcon == 0)
            {
                CustomAssert.fail("Validating ON/FF in the right side for Chapter","In \"Customize the course\" page Parallel to each chapter name there is no settings to ON/FF in the right side.");
            }
            //TC row no. 10
            int TLOOnIcon = setting.ONButtonList.size();
            if(TLOOnIcon == 0)
            {
                CustomAssert.fail("Validating ON/FF in the right side for TLOs","In \"Customize the course\" page Parallel to each TLO name there is no settings to ON/FF in the right side.");
            }
            setting.helpImage.click();    //click on Help Icon
            Thread.sleep(2000);
            //TC row no. 11
            String helpData = setting.chapterPracticeHelpIconContainer.getText();
            if(!helpData.contains("You may hide an entire chapter or individual objectives / sections from your class section by turning it OFF."))
            {
                CustomAssert.fail("Validating help Pop up","On clicking help icon near \"Customize Orion Topics\" the required popup doesn't appear.");
            }
            driver.findElement(By.xpath("/html/body")).click();
            Thread.sleep(3000);
            //TC row no. 12
            int helpData1 = setting.chapterPracticeHelpIconContainerList.size();
            if(helpData1 != 0)
            {
                CustomAssert.fail("Functionality of help icon","After clicking outside the help popup doesn't close.");
            }
            //TC row no. 13
            int offState1 = setting.chapterOFFButton.size();
            if(offState1 != 0)
            {
                CustomAssert.fail("Validating Chapters TLO","In \"Customize the course\" page, By default for all chapter/TLO is not ON.");
            }
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase customizeOrionTopics in class CustomizeOrionTopics", e);
        }
    }




    @Test(priority = 2, enabled = true)
    public void  showDiagChapterTLOPracticeSettingsBlock()
    {
        WebDriver driver=null;
        try
        {
            driver=Driver.getWebDriver();
            ReportUtil.log("Validating Diagnostic Chapter Practice Settings Block","Validating Diagnostic Test for Chapter & Chapter Practice Settings Block when it is OFF","info");
            Setting setting = PageFactory.initElements(driver, Setting.class);


            new LoginUsingLTI().ltiLogin("14");		//login as instructor
            navigateToCustomizeOrionTopics();
            //TC row no. 15

            String practiceBlock0 = setting.courseToggleBlock.getText();//Getting block text
            System.out.println("practiceBlock0 : " + practiceBlock0);
            if(practiceBlock0.length()>0)
            {
                CustomAssert.fail("Validating the entire Orion Topics","In \"Customize Orion Topics\" doesn't show block to show Diag/chapter practice.");
            }

            setting.tloOffButton.click();//click on OFF for TLO
            Thread.sleep(2000);
            //TC row no. 16
            String practiceBlock = setting.courseToggleBlock.getText();//Getting block text
            if(practiceBlock == null)
            {
                CustomAssert.fail("Validating chapter practice block","After clicking ON for TLO the chapter practice block doesn't appear as a pop-up.");
            }

            //TC row no. 14
            int chapterPractice = setting.chapterPracticeOFFButton.size();
            if(chapterPractice != 0)
            {
                CustomAssert.fail("Validating Customize Orion Topics","By default the chapter practice is not ON.");
            }


            int diagPractice = setting.diagnosticTestOFFButtonList.size();
            if(diagPractice != 0)
            {
                CustomAssert.fail("Validating Customize Orion Topics","By default the Diagnostic Test for Chapter is not ON.");
            }
            setting.chapterPracticeHelpIcon.click();//click on chapter practice help icon
            Thread.sleep(2000);
            //TC row no. 17
            String helpData = setting.chapterPracticeHelpIconContainer.getText();
            if(!helpData.contains("If you turn OFF one or more learning objectives, then Questions from hidden learning objectives will still be delivered through \"Chapter Practice\"."))
            {
                CustomAssert.fail("Validating Customize Orion Topics","After clicking help icon near Chapter Practice the help data is not shown correctly");

            }
            setting.diagnosticTestHelpIcon.click();//click on diag practice help icon
            Thread.sleep(2000);
            //TC row no. 17
            String helpData1 = setting.chapterPracticeHelpIconContainer.getText();
            if(!helpData1.contains("If you turn OFF one or more learning objectives, then Questions from hidden learning objectives will still be delivered through \"Diagnostic Test\"."))
            {
                CustomAssert.fail("Validating Customize Orion Topics","After clicking help icon near Diag Practice the help data is not shown correctly.");

            }
            //TC row no. 18
            String chapterPracticeOnOff = setting.chapterPracticeONOFFButton.getText();
            if(chapterPracticeOnOff == null)
            {
                CustomAssert.fail("Validating Customize Orion Topics","no ON/OFF button near \"Chapter practice\" block.");
            }
            //TC row no. 19
            String diagPracticeOnOff = setting.diagnosticTestONOFFButton.getText();
            if(diagPracticeOnOff == null)
            {
                CustomAssert.fail("Validating Customize Orion Topics","no ON/OFF button near \"Diagnostic Test for Chapter\" block.");
            }
            setting.orionBackIcon.click();//click on back button
            Thread.sleep(2000);
            setting.link_moreOptions.click();//click on More option
            Thread.sleep(2000);
            //TC row no. 20
            String practiceBlock1 = setting.courseToggleBlock.getText();
            if(practiceBlock1 == null)
            {
                CustomAssert.fail("Validating Customize Orion Topics","Navigate any other page and come back to same page the changes made is not correctly shown.");

            }
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase showDiagChapterTLOPracticeSettingsBlock in class ShowDiagChapterPracticeSettingsBlock.", e);
        }
    }
    @Test(priority = 3, enabled = true)
    public void  OFFallTheTLOsForChapter()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Validating if all the TLOs are off","Validating if all the TLO for chapter are off the tlos content should be OFF","info");
            Setting setting = PageFactory.initElements(driver,Setting.class);
            new LoginUsingLTI().ltiLogin("21");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            setting.button_orionTopicsON.click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            setting.link_moreOptions.click();    //click on More option
            Thread.sleep(2000);
            new com.snapwiz.selenium.tests.staf.orion.apphelper.Settings().tloOFF(1, "1");
            //TC row no. 21
            int chapterOff = setting.chapterOFFButton.size();
            if(chapterOff != 1)
            {
                CustomAssert.fail("Functionality OFF Button","After clicking on OFF for all TLOs of first chapter the first chapter doesn't set to OFF.");
            }

            new LoginUsingLTI().ltiLogin("22");        //login as student
            //TC row no. 22
            String chapter = setting.chapterLabel.getText();
            if(chapter.contains("Ch 1:"))
            {
                CustomAssert.fail("Functionality OFF Button","After clicking on OFF for all TLOs of first chapter the student still can see the first chapter.");
            }
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase OFFallTheTLOsForChapter in class ShowDiagChapterPracticeSettingsBlock.", e);
        }
    }
    @Test(priority = 4, enabled = true)
    public  void impactsOnSwitchingOFFAndONOfChapter()
    {
        WebDriver driver=null;
        try
        {
            driver=Driver.getWebDriver();
            ReportUtil.log("Impacts On Switching OFF And ON Of Chapter","Validaing Impacts On Switching OFF And ON Of Chapter","info");
            Setting setting = PageFactory.initElements(driver,Setting.class);
            new LoginUsingLTI().ltiLogin("23");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            setting.button_orionTopicsON.click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            setting.link_moreOptions.click();    //click on More option
            Thread.sleep(2000);
            setting.chapterOFFbutton.click();    //click OFF button for chapter
            Thread.sleep(2000);
            boolean isPresent = false;
            List<WebElement> allTLO = setting.terminalObjectiveTitle;
            for (WebElement l: allTLO) {
                if (l.getText().startsWith("1.sdfds")) {
                    isPresent = true;
                }
            }
            //TC row no. 23
            if(isPresent == true)
            {
                CustomAssert.fail("Validating functionality of OFF Chapter Button","After clicking on OFF for first chapter the TLOs under first chapter doesn't set to OFF.");
            }
            setting.chapterONButton.click();   //click ON button for chapter
            Thread.sleep(2000);
            //TC row no. 24
            int tloOff = setting.chapterOFFState.size();
            if(tloOff != 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking on ON for first chapter all the TLOs under first chapter doesn't set to ON.");
                CustomAssert.fail("Validating functionality of ON Chapter Button","After clicking on ON for first chapter all the TLOs under first chapter doesn't set to ON.");
            }
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase impactsOnSwitchingOFFAndONOfChapter in class ShowDiagChapterPracticeSettingsBlock.", e);
        }
    }
    @Test(priority = 5, enabled = true)
    public  void impactsOnSwitchingOFFAndONOfChapterPractice()
    {
        WebDriver driver=null;
        try
        {
            driver=Driver.getWebDriver();
            ReportUtil.log("Impacts On Switching OFF And ON Of Chapter Practice","Validating Impacts On Switching OFF And ON Of Chapter Practice","info");
            Setting setting = PageFactory.initElements(driver,Setting.class);
            new LoginUsingLTI().ltiLogin("25");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            setting.button_orionTopicsON.click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            setting.link_moreOptions.click();  //click on More option
            Thread.sleep(2000);
            setting.tloOffButton.click();//click on OFF for TLO
            Thread.sleep(2000);
            //TC row no. 25
            int chapterPractice = setting.chapterPracticeOFFButton.size();
            if(chapterPractice != 0)
            {
                CustomAssert.fail("Validating Chapter Practice","By default the chapter practice is not ON.");
            }
            setting.chapterPractice.click();    //click OFF Chapter practice
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("26");        //login as student
            new SelectChapterForTest().selectchapterfortest(0, 4); //Start the Diagnostic test
            new DiagnosticTest().attemptAllCorrect(2, false, false);
            new Navigator().orionDashboard();
            //TC row no. 26
            String isPracticeDisabled = setting.practiceLink.getAttribute("allowpractice");
            if(!isPracticeDisabled.contains("false"))
            {
                CustomAssert.fail("Validating Chapter Practice","After Switching OFF settings for chapter practice the Practice button is not disabled in student side.");
            }

            WebDriverUtil.mouseHover(By.cssSelector("div[class='al-preformance-text']"));
            //TC row no. 27
            String practiceButton = setting.practiceLink.getText();
            if(!practiceButton.contains("Practice"))
            {
                CustomAssert.fail("Validating Chapter Practice","After Switching OFF settings for chapter practice the Practice button is not enabled for other TLOs in student side.");
            }

            new LoginUsingLTI().ltiLogin("25");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            setting.link_moreOptions.click();    //click on More option
            Thread.sleep(2000);
            setting.tloButton.click();//ON the TLO
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("26");        //login as student
            //TC row no. 28
            String isPracticeDisabled1 = setting.practiceLink.getAttribute("allowpractice");
            if(isPracticeDisabled1 != null)
            {
                CustomAssert.fail("Validating Chapter Practice","After Switching ON settings for chapter practice the Practice button is disabled in student side.");
            }
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase impactsOnSwitchingOFFAndONOfChapter in class ShowDiagChapterPracticeSettingsBlock.", e);
        }
    }


    @Test(priority = 6, enabled = true)
    public  void impactsOnSwitchingOFFAndONDiagnosticTest()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            ReportUtil.log("Impacts On Switching OFF And ON Of Diagnostic Test","Validating Impacts On Switching OFF And ON Of Diagnostic test","info");
            Setting setting = PageFactory.initElements(driver,Setting.class);
            new LoginUsingLTI().ltiLogin("29");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            setting.button_orionTopicsON.click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            setting.link_moreOptions.click();    //click on More option
            Thread.sleep(2000);
            setting.tloOffButton.click();//click on OFF for TLO
            Thread.sleep(2000);
            //TC row no. 29
            int diagPractice = setting.diagnosticTestOFFButtonList.size();
            if(diagPractice != 0)
            {
                CustomAssert.fail("Impacts On Switching OFF And ON Of Diagnostic Test","By default the Diagnostic Test for Chapter is not ON.");
            }
            setting.diagnosticChapterOFFButton.click();    //click OFF Diagnostic Test for Chapter
            Thread.sleep(2000);
            //TC row no. 30
            String notice = setting.notificationMessage.getText();
            if(!notice.contains("If you switch off diagnostic test for this chapter, you will NOT be able to switch it on in future again."))
            {
                CustomAssert.fail("Impacts On Switching OFF And ON Of Diagnostic Test","clicking OFF for Diagnostic Test for Chapter the required pop_up doesnt appear.");
            }
            setting.yesLink.click();	//click on YES in pop-up
            Thread.sleep(2000);
            //TC row no. 31
            int diagPractice1 = setting.diagnosticTestOFFButtonList.size();
            if(diagPractice1 != 1)
            {
                CustomAssert.fail("Impacts On Switching OFF And ON Of Diagnostic Test","instructor in unable to OFF the Diagnostic Test for Chapter.");
            }


            new LoginUsingLTI().ltiLogin("32");        //login as student
            //TC row no. 32
            int tlo = setting.contentBodyWrapper.size();
            if(tlo == 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                CustomAssert.fail("Impacts On Switching OFF And ON Of Diagnostic Test","After clicking OFF the Diagnostic Test for Chapter, the TLOs are not shown to the student.");
            }
            //TC row no. 33
            int pin = setting.proficiencyPin.size();
            if(pin != 0)
            {
                CustomAssert.fail("Impacts On Switching OFF And ON Of Diagnostic Test","Pin in shown for the TLO in the dashboard ");
            }

            new LoginUsingLTI().ltiLogin("29");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            setting.link_moreOptions.click();    //click on More option
            Thread.sleep(2000);
            setting.tloButton.click();//click on ON for TLO
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("32");        //login as student
            //TC row no. 34
            int tlo1 = setting.contentBodyWrapper.size();
            if(tlo1 == 0)
            {
                CustomAssert.fail("Impacts On Switching OFF And ON Of Diagnostic Test","After clicking ON the Diagnostic Test for Chapter, there is changes, diag are not skipped.");
            }

            new LoginUsingLTI().ltiLogin("29");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            setting.link_moreOptions.click();    //click on More option
            Thread.sleep(2000);
            setting.tloOffButton.click();//click on OFF for TLO
            Thread.sleep(2000);
            //TC row no. 35
            int diagPractice2 = setting.diagnosticTestOFFButtonList.size();
            if(diagPractice2 != 1)
            {
                CustomAssert.fail("Impacts On Switching OFF And ON Of Diagnostic Test","In the block it doesnt show diagnostic test as OFF.");
            }
            setting.oFFDiagnosticChapter.click();    //click OFF Diagnostic Test for Chapter
            Thread.sleep(2000);
            //TC row no. 36
            int diagPractice3 = setting.diagnosticTestOFFButtonList.size();
            if(diagPractice3 == 0)
            {
                CustomAssert.fail("Impacts On Switching OFF And ON Of Diagnostic Test","Instructor is able ON again the Diagnostic Test for Chapter.");
            }
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase impactsOnSwitchingOFFAndONOfChapter in class ShowDiagChapterPracticeSettingsBlock.", e);
        }
    }
    @Test(priority = 7, enabled = true)
    public void clickOnNOlink()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            ReportUtil.log("Functionality of 'NO' ink","Validating Functionality of 'NO' link in pop up when it is clicked","info");
            Setting setting = PageFactory.initElements(driver,Setting.class);
            new LoginUsingLTI().ltiLogin("37");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            setting.button_orionTopicsON.click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            setting.link_moreOptions.click();    //click on More option
            Thread.sleep(2000);
            setting.tloOffButton.click();//click on OFF for TLO
            Thread.sleep(2000);
            setting.diagnosticChapterOFFButton.click();    //click OFF Diagnostic Test for Chapter
            Thread.sleep(2000);
            setting.noLink.click();	//click on NO in pop-up
            Thread.sleep(2000);
            //TC row no. 37
            int diagPractice1 = setting.diagnosticTestOFFButtonList.size();
            if(diagPractice1 == 1)
            {
                new Screenshot().captureScreenshotFromTestCase();
                CustomAssert.fail("Functionality of 'NO' link","Clicking on NO link in pop-up for setting OFF in diagnostic test for Chapter.");
            }
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase clickOnNOlink in class ShowDiagChapterPracticeSettingsBlock.", e);
        }
    }
    @Test(priority = 8, enabled = true)
    public void impactsOnSwitchingOFFAndONofTLO()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            ReportUtil.log("Impacts On Switching OFF And ON of TLO","Validating Impacts On Switching OFF And ON of TLO","info");
            Setting setting = PageFactory.initElements(driver,Setting.class);
            new LoginUsingLTI().ltiLogin("38");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            setting.button_orionTopicsON.click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            setting.link_moreOptions.click();    //click on More option
            Thread.sleep(2000);
            setting.tloOffButton.click();//click on OFF for TLO
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("38_1");        //login as student
            new SelectChapterForTest().selectchapterfortest(0, 4); //Start the Diagnostic test
            new DiagnosticTest().attemptAllCorrect(2, false, false);
            new Navigator().orionDashboard();

            new LoginUsingLTI().ltiLogin("38");        //login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            setting.link_moreOptions.click();    //click on More option
            Thread.sleep(2000);
            new com.snapwiz.selenium.tests.staf.orion.apphelper.Settings().tloOFF(1, "1");

            new LoginUsingLTI().ltiLogin("38_1");        //login as student
            //TC row no. 40
            String chapter = setting.chapterLabel.getText();
            if(chapter.contains("Ch 1:"))
            {
                CustomAssert.fail("Impacts On Switching OFF And ON of TLO","After clicking on OFF for all TLOs of first chapter the student still can see the first chapter.");
            }
            //TC row no. 41
            String summaryBlock = driver.findElement(By.id("al-preformance-top-7-time-spent-content")).getCssValue("background-image");
            if(!summaryBlock.contains("time-spent-graph-chart.png"))
            {
                CustomAssert.fail("Impacts On Switching OFF And ON of TLO","After clicking on OFF for all TLOs of first chapter the student still can see It under \"Summary\" block in the right side.");
            }
            //TC row no. 42
            String leastProfCh = driver.findElement(By.className("al-recommended-focus-area-chart-section")).getCssValue("background-image");
            if(!leastProfCh.contains("recommended-focus-area-graph-chart.png"))
            {
                CustomAssert.fail("Impacts On Switching OFF And ON of TLO","After clicking on OFF for all TLOs of first chapter the student still can see It under \"Least Proficiency Chapter\" block in the right side.");
            }

            new LoginUsingLTI().ltiLogin("38");        //login as instructor
            new Navigator().navigateToReport("Metacognitive Report");
            setting.studentName.click();	//click on student name
            Thread.sleep(2000);
            //TC row no. 43
            String chName = setting.terminalObjectiveTitles.getText();
            if(!chName.contains("Ch 1:"))
            {
                CustomAssert.fail("Impacts On Switching OFF And ON of TLO","In \"Metacognitive Report\" it doesnt show all the data even if the chapter is OFF.");
            }
            setting.performanceReport.click();		//click on performance report
            WebDriverUtil.waitForAjax(driver,20);

            //close notification dialog if any
            try {
                WebElement btnNotiDiagClose=driver.findElement(By.id("close-al-notification-dialog"));
                WebDriverUtil.waitTillVisibilityOfElement(btnNotiDiagClose,20);
                if(btnNotiDiagClose.isDisplayed())
                    btnNotiDiagClose.click();
            }
            catch (NoSuchElementException e){}
            catch(TimeoutException ex){}

            //click on student name in class performance by student section
            setting.studentFullName.click();  //click on student name
            Thread.sleep(5000);
            //TC row no. 44
            String chName1 = setting.chapterNameLabel.getText();
            if(!chName1.contains("Ch1"))
            {
                CustomAssert.fail("Impacts On Switching OFF And ON of TLO","In \"performance Report\" it doesnt show all the data even if the chapter is OFF.");

            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase impactsOnSwitchingOFFAndONofTLO in class ShowDiagChapterPracticeSettingsBlock.", e);
        }
    }

    public void navigateToCustomizeOrionTopics(){
        try{
            WebDriver driver=Driver.getWebDriver();
            Setting setting = PageFactory.initElements(driver, Setting.class);
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            setting.button_orionTopicsON.click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            setting.link_moreOptions.click();//click on More option
            Thread.sleep(2000);
            ReportUtil.log("Navigation to Customize Orion Topics","Navigated to 'Customize Orion Topics' Page","info");
        }catch(Exception e){
            Assert.fail("Exception in the method 'navigateToCustomizeOrionTopics' in the class 'Settings'",e);
        }
    }
}
