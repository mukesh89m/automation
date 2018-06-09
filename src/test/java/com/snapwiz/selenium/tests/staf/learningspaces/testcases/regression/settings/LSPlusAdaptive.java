package com.snapwiz.selenium.tests.staf.learningspaces.testcases.regression.settings;

import com.snapwiz.selenium.tests.staf.learningspaces.CustomAssert;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Profile.Profile;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by pragyas on 08-01-2016.
 */
public class LSPlusAdaptive extends Driver {

    @Test(priority = 1,enabled = true)
    public void instructorShouldBeAbleToNavigateToCustomizeStudyItemsPage() {
        try {
            log("Description","Verifying setting page functionality","info");
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            new LoginUsingLTI().ltiLogin("10");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            String settingTitle=profile.getSetting_Title().getText();
            CustomAssert.assertEquals(settingTitle, "Settings","Verify 'Settings' label on Setting page ","After click on setting it is navigated to setting page","After click on setting it is not navigate to setting page");

            CustomAssert.assertEquals(profile.heading.get(1).isDisplayed(),true,"verify 'Study Plan' label on the setting page","Study Plan option is displaying below Audio Recording option","Study Plan option is not displaying below Audio Recording option");

            profile.customButton.click();//click on custom button
            if(profile.notification_Message.size()!=0)
                CustomAssert.fail("Verify custom status","Custom should not be in OFF state.");

            CustomAssert.assertEquals(profile.customizeNow_Link.getText(),"Customize Now >","verify customize link","Customize Now link is displayed beside ON/OFF button for Study Plan option.","Customize Now link is not displayed beside ON/OFF button for Study Plan option.");

            String saveButton= profile.getSave_Button().getText();
            CustomAssert.assertEquals(saveButton,"Save","verify save button","Save button is displaying","Save button is not displaying");

            String cancelButton= profile.getCancel_Button().getAttribute("title");
            CustomAssert.assertEquals(cancelButton,"Cancel","verify cancel button","Cancel button is displaying","Cancel button is not displaying");

            profile.helpIcon_CustomizeSetting.click();//click on help icon of custom

            CustomAssert.assertEquals(profile.getHelp_Text().isDisplayed(),true,"verify custom help pop up on setting page","Help pop up is displayed","Help pop up is not displayed");

            CustomAssert.assertEquals(profile.getHelp_Text().getText(),"Turn customize study plan settings on or off. Disabled section or chapter will not be shown to all the students of the current class-section.","Verify help message","Help message is displaying","Help message is not displaying");

            profile.customButton.click();//click on custom button
            profile.customButton.click();//click on custom button
            Thread.sleep(2000);
            if(profile.notification_Message.size()==0)
                CustomAssert.fail("Verify custom status","Status is changed from ON to OFF");

            CustomAssert.assertEquals(profile.customizeNow_Link.isEnabled(),true,"verify Customize Now link","Customize Now link is enabled","Customize Now link is not get enabled");

            profile.customButton.click();//click on custom button
            CustomAssert.assertEquals(profile.notification_Message.get(0).getText(),"You are about to delete all the custom study plan settings? Yes | No","verify the notification message","notification message is displaying","notification message is not displaying");

            CustomAssert.assertEquals(profile.topNotificationBar.getText(),"You are about to delete all the custom study plan settings? Yes | No","Verify notification on the top","The validation is displaying at top.","The validation is not displaying at top.");

            profile.noLinkOnNotificationMessage.click();//click on No
            if(profile.notification_Message.size()!=0)
                CustomAssert.fail("verify the notification pop up","The validation is not closed without any changes.");

            profile.customButton.click();//click on custom button
            profile.getCloseIcon().click();//click on 'cross icon'
            if(profile.notification_Message.size()!=0)
                CustomAssert.fail("verify the notification pop up","The validation is not closed without any changes.");

            profile.customButton.click();//click on custom button
            profile.yesLinkOnNotificationMessage.click();//click on 'yes icon'
            CustomAssert.assertNotEquals(profile.customizeNow_Link.isEnabled(),false,"verify the customize now link", "Customize Now link is enabled", "Customize Now link is not get enabled");

            profile.customButton.click();//click on custom button
            if(profile.notification_Message.size()!=0)
                CustomAssert.fail("Verify validation message","Validation message is displaying");
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            CustomAssert.assertEquals(profile.customizeStudyPlanTitle.isDisplayed(),true,"verify 'Customize Study Plan' label","Instructor is able to click on Customize Now link","Instructor is not able to click on Customize Now link");
            CustomAssert.assertEquals(profile.customizeStudyPlanTitle.getText(),"Customize Study Plan","verify 'Customize Study Plan' label","Instructor is able to click on Customize Now link","Instructor is not navigated to Customize Study Plan page");

            if (profile.chapterLabelButton.size()==0)
                CustomAssert.fail("verify the chapter's button","All the Chapters is not get enabled");

            if (profile.topicLabelButton.size()==0)
                CustomAssert.fail("verify the chapter's section button","All the sections is not get enabled");

            profile.customizeBackArrow.click();//click on back arrow
            profile.customButton.click();//click on custom button
            if(profile.notification_Message.size()==0)
                CustomAssert.fail("verify the custom button","Status of study plan is not displayed as ON");

            profile.getSave_Button().click();//click on save
            new Navigator().NavigateTo("Dashboard");//navigate to dashboard
            Thread.sleep(2000);
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            if(profile.notification_Message.size()==0)
                CustomAssert.fail("verify the study plan button","Saved status of Study Plan is not displayed i.e ON");

            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);

            //off chapter button
            List<WebElement> chapterButtonOff=profile.chapterLabelButton;
            int sizeOff =chapterButtonOff.size();
            for(int i=0;i<sizeOff;i++){
                chapterButtonOff.get(0).click();
                Thread.sleep(1000);
                if(i==sizeOff-1)
                    CustomAssert.assertEquals(profile.notification_Message.get(0).getText(),"You cannot disable the last chapter.","verify the notification message on 'Customize Study Plan' page","Validation message is displaying","Validation message is not displaying");

            }
            profile.getCloseIcon().click();//click on close icon
            Thread.sleep(2000);

            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollBy(250,0)", "");
            Thread.sleep(2000);

            //on chapter button
            List<WebElement> chapterButtonOn=profile.chapterLabelButtonOff;
            int sizeOn =chapterButtonOn.size();
            for(WebElement element:chapterButtonOn){
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",element);
                Thread.sleep(3000);

            }

            //off chapter button except 1st chapter
            for(int i=1;i<sizeOff;i++){
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",chapterButtonOff.get(1));
                Thread.sleep(1000);
            }
            jse.executeScript("window.scrollBy(250,0)", "");
            Thread.sleep(2000);

            //off section button of 1st chapter
            List<WebElement> topicButtonOff=profile.topicLabelButton;
            int topicSizeOn =topicButtonOff.size();
            for(int i=0;i<topicSizeOn;i++){
                topicButtonOff.get(0).click();
                Thread.sleep(1000);
                if(i==topicSizeOn-1)
                    CustomAssert.assertEquals(profile.notification_Message.get(0).getText(),"You cannot disable the last section.","verify the notification message on 'Customize Study Plan' page","Validation message is displaying","Validation message is not displaying");
            }

            CustomAssert.assertEquals(profile.topNotificationBar.getText(),"You cannot disable the last section.","Verify notification message","The validation is displaying at top.","The validation is not displaying at top.");
            profile.getCloseIcon().click();//click on close icon
            Thread.sleep(2000);
            if(profile.notification_Message.size()!=0)
                CustomAssert.fail("Verify validation message","Validation message is displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case 'instructorShouldBeAbleToNavigateToCustomizeStudyItemsPage' in class LsPlusAdaptiveCourse", e);
        }
    }


    @Test(priority = 2,enabled = true)
    public void instructorShouldBeAbleToNavigateToCustomizeStudyItems() {
        try {
            log("Description","Verifying Customize Study Items navigating from settings page","info");
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            new LoginUsingLTI().ltiLogin("45");//log in as instructor
            Thread.sleep(2000);
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            if (profile.chapterName.size()==0)
                CustomAssert.fail("verify chapters on Customize study plan page'","All the Chapters are not showing");

            profile.helpIconCustomizePage.click();//click on help icon
            CustomAssert.assertEquals(profile.getHelp_Text().getText(), "You may hide an entire chapter or individual sections from your class section by disabling it.","verify help pop up of customize study plan", "Help message is displaying","Help message is not displaying");

            CustomAssert.assertEquals(profile.chapterLabelButton.get(0).isDisplayed(),true,"verify “On” vs “Off” button for chapter level","For each Chapter “On” vs “Off” option is available","For each Chapter “On” vs “Off” option is not available");

            CustomAssert.assertEquals(profile.topicLabelButton.get(0).isDisplayed(),true,"verify “On” vs “Off” button for topic level","For each section “On” vs “Off” option is available","For each section “On” vs “Off” option is not available");

            profile.chapterLabelButton.get(0).click();//click on 1st chapter on button;
            profile.customizeBackArrow.click();//click on back arrow
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            if(profile.chapterLabelButtonOff.get(0).isDisplayed()==false){
                CustomAssert.fail("verify 1st chapter button","The previous change has not been changed");

            }
            CustomAssert.assertEquals(profile.cannotBeDisabledLink.get(2).getText(),"Cannot be disabled","verify 'can not be disabled' label for Diagnostic","Cannot be disabled is displaying for any Diagnostic for any particular chapter","Cannot be disabled is not displaying for any Diagnostic for any particular chapter");

            CustomAssert.assertEquals(profile.cannotBeDisabledLink.get(3).getText(),"Cannot be disabled","verify 'can not be disabled' label for Adaptive Practice","Cannot be disabled is displaying for any Adaptive practice at chapter level","Cannot be disabled is not displaying for any Adaptive practice at chapter level");

            profile.customizeBackArrow.click();//click on back arrow
            profile.customButton.click();//click on custom button
            profile.yesLinkOnNotificationMessage.click();//click on 'yes icon'
            new Assignment().create(45);
            new LoginUsingLTI().ltiLogin("45");//log in as instructor
            new Assignment().assignToStudent(45);
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            CustomAssert.assertEquals(profile.cannotBeDisabledLink.get(2).isDisplayed(), true,"verify 'can not be disabled' label for Post Chapter","Cannot be disabled is displaying for Post Chapter Section", "Cannot be disabled is not displaying for Post Chapter Section");

            CustomAssert.assertEquals(profile.helpIconDiagnostic.get(0).isDisplayed(),true,"verify help icon for diagnostic","Help icon is displaying for diagnostic","Help icon is not displaying for diagnostic");

            CustomAssert.assertEquals(profile.helpIconPractice.get(0).isDisplayed(),true,"verify help icon for practice","Help icon is displaying for practice","Help icon is not displaying for practice");

            CustomAssert.assertEquals(profile.helpIconPost.get(0).isDisplayed(),true,"verify help icon for post chapter","Help icon is displaying for post chapter","Help icon is not displaying for post chapter");

            profile.customizeBackArrow.click();//click on back arrow
            String settingTitle=profile.getSetting_Title().getText();
            CustomAssert.assertEquals(settingTitle,"Settings","verify help icon for post chapter","After click on back it is navigated to setting page","After click on back it is not navigated to setting page");

            if(profile.customizeNow_Link.isEnabled()==false)
                CustomAssert.fail("verify the status of study plan","The status of Study Plan is not auto-saved as On");

            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.customizeBackArrow.click();//click on back arrow
            profile.getCancel_Button().click();//click on cancel
            Thread.sleep(2000);
            profile.customButton.click();//click on custom button
            profile.customButton.click();//click on custom button
            Thread.sleep(2000);
            if(profile.notification_Message.size()==0)
                CustomAssert.fail("Verify the custom button","Custom is not changed to OFF state.");

            profile.customizeNow_Link.click();//click on customize now link
            profile.chapterLabelButton.get(0).click();//click on on button of chapter level
            profile.topicLabelButton.get(0).click();//click on on button of section lebel
            profile.customizeBackArrow.click();//click on back arrow
            profile.customButton.click();//click on custom button
            if(profile.notification_Message.size()==0)
                CustomAssert.fail("Verify validation message","Validation message is not displaying");

            profile.customButton.click();//click on custom button
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            profile.chapterLabelButton.get(0).click();//click on on button of chapter level
            profile.topicLabelButton.get(0).click();//click on on button of section label
            profile.customizeBackArrow.click();//click on back arrow
            profile.customButton.click();//click on custom button
            profile.getCancel_Button().click();//click on cancel
            if(profile.notification_Message.size()==0)
                CustomAssert.fail("Verify validation message","Validation message is not displaying");
            profile.customizeNow_Link.click();//click on customize now link
            profile.helpIconDiagnostic.get(2).click();//click on help icon of diagnostic
            CustomAssert.assertEquals(profile.getHelp_Text().getText(),"The diagnostic for a chapter cannot be disabled.","verify help pop up text on diagnostic","validation message for diagnostic is available","validation message for diagnostic is not available");

            profile.helpIconPractice.get(2).click();//click on help icon of practice
            CustomAssert.assertEquals(profile.getHelp_Text().getText(), "The adaptive practice for a chapter cannot be disabled.","verify help pop up text on adaptive practice", "validation message for adaptive practice is available", "validation message for adaptive practice is not available");

            profile.chapterLabelButtonOff.get(0).click();//click on on button of 1st chapter
            profile.helpIconPost.get(0).click();//click on help icon of practice
            CustomAssert.assertEquals(profile.getHelp_Text().getText(), "The post chapter section for a chapter cannot be disabled.", "verify help pop up text on post chapter","validation message for post chapter is available","validation message for post chapter is not available");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToNavigateToCustomizeStudyItems in class LsPlusAdaptiveCourse", e);
        }
    }


    @Test(priority = 3,enabled = true)
    public void instructorShouldBeAbleToEnableDisableStudyChaptersAndSection() {
        try {
            log("Description","Verifying study plan after enabling/disabling chapter/section","info");
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("63");//log in as instructor
            Thread.sleep(2000);
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.chapterLabelButton.get(0).click();//click on on of 1st chapter
            if(profile.helpIconDiagnostic.get(0).isDisplayed()==true)
                CustomAssert.fail("verify all the sections within a deactivated chapter","All the section within the deactivated chapter is not get deactivated");

            if (profile.chapterLabelButtonOff.get(0).isDisplayed()==false)
                CustomAssert.fail("Verify chapter level button","The ON/OFF status for deactivated chapter status is not Off");
            boolean elementFound = false;
            try{
                driver.findElement(By.id("settings-save"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound,false,"Verify save option","Save option is not available","save option is available");
            Thread.sleep(1000);
            boolean elementFoundCancel = false;
            try{
                driver.findElement(By.id("settings-cancel"));
                elementFoundCancel = true;
            }
            catch (Exception e){
                //empty catch block
            }
            CustomAssert.assertEquals(elementFoundCancel,false,"Verify cancel option","Cancel option is not available","cancel option is available");
            Thread.sleep(1000);
            profile.chapterLabelButtonOff.get(0).click();//click on off on 1st chapter
            CustomAssert.assertEquals(profile.changeSavedStatusBox.isDisplayed(),true,"Verify status box","Saved change status box is available","Saved change status box is not available");
            Thread.sleep(3000);

            try{
                profile.changeSavedStatusBox.isDisplayed();
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound,false,"Verify saved status box","change saved status box is not displaying after 3 second","change saved status box is displaying after 3 second");
            profile.customizeBackArrow.click();//click on back arrow
            CustomAssert.assertEquals(profile.customizeNow_Link.isEnabled(),true,"Verify the status of study plan","The status of Study Plan is auto-saved as On","The status of Study Plan is not auto-saved as On");
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(1000);
            profile.chapterLabelButton.get(0).click();//click on ON on 1st chapter

            new LoginUsingLTI().ltiLogin("63_1");//log in as student
            new Navigator().NavigateTo("e-Textbook");
            if(lessonPage.chapterContainer.get(0).isDisplayed()==true)
                CustomAssert.fail("Verify for deactivated chapter","The deactivated chapter is displayed to student in the Study Plan.");

            new LoginUsingLTI().ltiLogin("63");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.chapterLabelButtonOff.get(0).click();//click on off on 1st chapter
            CustomAssert.assertEquals(profile.changeSavedStatusBox.isDisplayed(),true,"Verify saved change status box","Saved change status box is available","Saved change status box is not available");
            Thread.sleep(3000);
            boolean elementFound1 = false;
            try{
                driver.findElement(By.className("changes-saved-status-box"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound1,false,"Verify change saved status box","change saved status box is not displaying after 3 second","change saved status box is displaying after 3 second");

            try{
                driver.findElement(By.id("settings-save"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound,false,"Verify save option","Save option is not available","save option is available");
            Thread.sleep(1000);
            try{
                driver.findElement(By.id("settings-cancel"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            CustomAssert.assertEquals(elementFoundCancel,false,"Verify cancel option","Cancel option is not available","cancel option is available");
            Thread.sleep(1000);
            if(profile.helpIconDiagnostic.get(0).isDisplayed()!=true)
                CustomAssert.fail("Verify all the hidden sections of the chapter","All the hidden sections of the deactivated chapter is not displaying");
            profile.customizeBackArrow.click();//click on back arrow
            CustomAssert.assertEquals(profile.customizeNow_Link.isEnabled(),true,"Verify study plan status","The status of Study Plan is auto-saved as On","The status of Study Plan is not auto-saved as On");

            new LoginUsingLTI().ltiLogin("63_1");//log in as student
            new Navigator().NavigateTo("e-Textbook");
            if(lessonPage.chapterContainer.get(0).isDisplayed()!=true)
                Assert.fail("The deactivated chapter is not displayed to student in the Study Plan.");
            lessonPage.orionAdaptive_link.get(1).click();//click on orion adaptive link
            CustomAssert.assertEquals(lessonPage.beginDiagnostic.get(0).isDisplayed(),true,"Verify diagnostic test","diagnostic test is displaying to student","diagnostic test is not displaying to student");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToEnableDisableStudyChaptersAndSection in class LsPlusAdaptiveCourse", e);
        }
    }


    @Test(priority = 4,enabled = true)
    public void  disablingOneOrMoreSectionLevelForAnyChapter() {
        try {
            log("Description","Verifying study plan buttons under section level","info");
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            new LoginUsingLTI().ltiLogin("89");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.topicLabelButton.get(0).click();//click on ON on 1st section of chapter 1
            profile.topicLabelButton.get(1).click();//click on ON on 2nd  section of chapter 1
            CustomAssert.assertEquals(profile.changeSavedStatusBox.isDisplayed(),true,"Verify change saved status box","Saved change status box is available","Saved change status box is not available");
            CustomAssert.assertEquals(profile.topicLabelButtonOff.get(0).isDisplayed(), true,"Verify the section level button","The status of the section/sections is changed from \"On\" to \"Off\".", "The status of the section/sections is not changed from \"On\" to \"Off\".");
            profile.chapterLabelButton.get(0).click();//click on ON on 1st chapter
            profile.chapterLabelButtonOff.get(0).click();//click on off on 1st chapter
            CustomAssert.assertEquals(profile.topicLabelButton.get(0).isDisplayed(), true,"Verify the topic level button of chapter1","All the sections areopened up", "All the sections are not get opened up");
            CustomAssert.assertEquals(profile.topicLabelButton.get(1).isDisplayed(),true,"Verify the topic level button of chapter1","All the sections areopened up","All the sections are not get opened up");
            CustomAssert.assertEquals(profile.topicLabelButton.get(0).isDisplayed(), true,"Verify the topic topic level button","Status of all the section are changed from \"Off\" to \"On\".", "Status of all the section not changed from \"Off\" to \"On\".");
            CustomAssert.assertEquals(profile.topicLabelButton.get(1).isDisplayed(),true,"Verify the topic topic level button","Status of all the section are changed from \"Off\" to \"On\".","All the sections not changed from \"Off\" to \"On\".");
            profile.customizeBackArrow.click();//click on back arrow
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.topicLabelButton.get(0).click();//click on ON on 1st section of chapter 1
            profile.topicLabelButton.get(1).click();//click on ON on 2nd  section of chapter 1
            new LoginUsingLTI().ltiLogin("89_1");//log in as student
            new Navigator().NavigateTo("e-Textbook");
            new DiagnosticTest().startTest(1); //start Diagnostic test
            new DiagnosticTest().attemptAllCorrect(0, false, false); //attempt all correct question

            new Navigator().NavigateTo("e-Textbook");
            new PracticeTest().startTest();
            for (int i = 0; i < 11; i++) {
                boolean tloFound = false;
                try{
                    driver.findElement(By.xpath("//span[contains(@title,'Discuss the scientific basis')]"));
                    CustomAssert.fail("Verify tlo","Tlo is found");
                }
                catch (Exception e){
                    //empty catch block
                }
                new PracticeTest().AttemptCorrectAnswer(2, "89");
            }
            new PracticeTest().quitThePracticeTest();
            Thread.sleep(3000);


        } catch (Exception e) {
            Assert.fail("Exception in test case disablingOneOrMoreSectionLevelForAnyChapter in class LsPlusAdaptiveCourse", e);
        }
    }























}
