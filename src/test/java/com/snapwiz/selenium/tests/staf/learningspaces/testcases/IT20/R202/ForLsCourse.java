package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT20.R202;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Profile.Profile;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 5/13/2015.
 */
public class ForLsCourse extends Driver {
    @Test(priority = 1,enabled = true)
    public void instructorShouldBeAbleToNavigateToCustomizeStudyItemsPage() {
        try {
            //tc row no 265
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            new LoginUsingLTI().ltiLogin("265");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            String settingTitle=profile.getSetting_Title().getText();
            Assert.assertEquals(settingTitle,"Settings","After click on setting it is not navigate to setting page");
            Assert.assertEquals(profile.heading.get(1).isDisplayed(),true,"\"Study Plan\" option is not displaying below Audio Recording option");
            profile.customButton.click();//click on custom button
            if(profile.notification_Message.size()!=0)
                Assert.fail("Custom should  not be by default in \"OFF\" state.");
            profile.customButton.click();//click on custom button
            Assert.assertEquals(profile.customizeNow_Link.getText(),"Customize Now >","\"Customize Now\" link is not displayed beside ON/OFF button for Study Plan option.");
            String saveButton= profile.getSave_Button().getText();
            Assert.assertEquals(saveButton,"Save","Save button is not displaying");
            String cancelButton= profile.getCancel_Button().getAttribute("title");
            Assert.assertEquals(cancelButton,"Cancel","Cancel button is not displaying");
            profile.customButton.click();//click on custom button
            profile.customButton.click();//click on custom button
            Thread.sleep(2000);
            if(profile.notification_Message.size()==0)
                Assert.fail("State not changed from \"OFF\" to \"ON\" status.");
            Assert.assertEquals(profile.customizeNow_Link.isEnabled(),true,"Customize Now link is not get enabled");
            profile.customButton.click();//click on custom button
            Assert.assertEquals(profile.notification_Message.get(0).getText(), "Yor are about to delete all the custom study plan settings? Yes | No", "notification message is not displaying");
            Assert.assertEquals(profile.topNotificationBar.getText(),"Yor are about to delete all the custom study plan settings? Yes | No","The validation is not displaying at top.");
            profile.noLinkOnNotificationMessage.click();//click on no
            if(profile.notification_Message.size()!=0)
                Assert.fail("The validation is not close without any change.");
            profile.customButton.click();//click on custom button
            profile.getCloseIcon().click();//click on 'cross icon'
            if(profile.notification_Message.size()!=0)
                Assert.fail("The validation is not close without any change.");
            profile.customButton.click();//click on custom button
            profile.yesLinkOnNotificationMessage.click();//click on 'yes icon'
            Assert.assertNotEquals(profile.customizeNow_Link.isEnabled(),false, "Customize Now link is not get enabled");
            profile.customButton.click();//click on custom button
            if(profile.notification_Message.size()!=0)
                Assert.fail("Instructor is not able to click on \"ON\"");
            if(profile.notification_Message.size()!=0)
                Assert.fail("Validation message is displaying");
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            Assert.assertEquals(profile.customizeStudyPlanTitle.isDisplayed(),true,"Instructor is not able to click on \"Customize Now\" link");
            Assert.assertEquals(profile.customizeStudyPlanTitle.getText(),"Customize Study Plan","Instructor is not navigated to Customize Study Plan page");
            if (profile.chapterLabelButton.size()==0)
                Assert.fail("All the Chapters is not get enabled");
            if (profile.topicLabelButton.size()==0)
                Assert.fail("All the sections is not get enabled");
            profile.customizeBackArrow.click();//click on back arrow
            new Navigator().NavigateTo("Dashboard");//navigate to dashboard
            dashboard.getProfileDropDown().click();//click on profile dropdown
            new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='instructor-settings-link']/a")));
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            if(profile.notification_Message.size()!=0)
                Assert.fail("Status of study plan is not changed to default status i.e \"OFF\".");
            profile.getSave_Button().click();//click on save
            new Navigator().NavigateTo("Dashboard");//navigate to dashboard
            Thread.sleep(2000);
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            if(profile.notification_Message.size()==0)
                Assert.fail("Saved status of Study Plan is not displayed i.e \"ON\"");
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);

            //off chapter button
            List<WebElement> chapterButtonOff=profile.chapterLabelButton;
            int sizeOff =chapterButtonOff.size();
            for(int i=0;i<sizeOff;i++){
                chapterButtonOff.get(0).click();
                Thread.sleep(1000);
                if(i==sizeOff-1)
                    Assert.assertEquals(profile.notification_Message.get(0).getText(),"You cannot disable the last chapter.","Validation message is not displaying");
            }
            profile.getCloseIcon().click();//click on close icon
            Thread.sleep(2000);

            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollBy(250,0)", "");
            Thread.sleep(2000);

            //on chapter button
            List<WebElement> chapterButtonOn=profile.chapterLabelButtonOff;
            int sizeOn =chapterButtonOn.size();
            for(int i=0;i<sizeOn;i++){
                chapterButtonOn.get(0).click();
                Thread.sleep(1000);
            }
            jse.executeScript("window.scrollBy(250,0)", "");
            Thread.sleep(2000);

            //off chapter button except 1st chapter
            for(int i=1;i<sizeOff;i++){
                chapterButtonOff.get(1).click();
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
                    Assert.assertEquals(profile.notification_Message.get(0).getText(),"You cannot disable the last section.","Validation message is not displaying");
            }

            Assert.assertEquals(profile.topNotificationBar.getText(),"You cannot disable the last section.","The validation is not displaying at top.");
            profile.getCloseIcon().click();//click on close icon
            Thread.sleep(2000);
            if(profile.notification_Message.size()!=0)
                Assert.fail("Validation message is displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToNavigateToCustomizeStudyItemsPage in class ForLsCourse", e);
        }
    }


    @Test(priority = 2,enabled = true)
    public void instructorShouldBeAbleToNavigateToCustomizeStudyItems() {
        try {
            //tc row no 298
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            new LoginUsingLTI().ltiLogin("298");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            if (profile.chapterLabelButton.size()==0)
                Assert.fail("All the Chapters is not get enabled");
            if (profile.topicLabelButton.size()==0)
                Assert.fail("All the sections is not get enabled");
            if (profile.chapterName.size()==0)
                Assert.fail("All the Chapters are not showing");
            profile.helpIconCustomizePage.click();//click on help icon
            Assert.assertEquals(profile.getHelp_Text().getText(), "You may hide an entire chapter or individual sections from your class section by disabling it.", "Help message is not displaying");
            Assert.assertEquals(profile.chapterLabelButton.get(0).isDisplayed(),true,"For each Chapter “On” vs “Off”. option is not available");
            Assert.assertEquals(profile.topicLabelButton.get(0).isDisplayed(),true,"For each section “On” vs “Off”. option is not available");
            profile.chapterLabelButton.get(0).click();//click on 1st chapter on button;
            profile.customizeBackArrow.click();//click on back arrow
            profile.customizeNow_Link.click();//click on customize now link
            if(profile.chapterLabelButtonOff.get(0).isDisplayed()==false){
                Assert.fail("The previous change has not been changed");
            }
            profile.customizeBackArrow.click();//click on back arrow
            profile.customButton.click();//click on custom button
            profile.yesLinkOnNotificationMessage.click();//click on 'yes icon'
            new Assignment().create(298);
            new LoginUsingLTI().ltiLogin("298");//log in as instructor
            new Assignment().assignToStudent(298);
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            Assert.assertEquals(profile.helpIconPost.get(0).isDisplayed(),true,"Help icon is not displaying for post chapter");
            profile.customizeBackArrow.click();//click on back arrow
            String settingTitle=profile.getSetting_Title().getText();
            Assert.assertEquals(settingTitle,"Settings","After click on back it is not navigate to setting page");
            if(profile.customizeNow_Link.isEnabled()==false)
                Assert.fail("The status of Study Plan is not auto-saved as \"On\"");
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.customizeBackArrow.click();//click on back arrow
            profile.getCancel_Button().click();//click on cancel
            Thread.sleep(2000);
            profile.customButton.click();//click on custom button
            profile.customButton.click();//click on custom button
            Thread.sleep(2000);
            if(profile.notification_Message.size()==0)
                Assert.fail("Custom is not changed to \"OFF\" state.");
            profile.customizeNow_Link.click();//click on customize now link
            profile.chapterLabelButton.get(0).click();//click on on button of chapterelevel
            profile.topicLabelButton.get(0).click();//click on on botton of section lebel
            profile.customizeBackArrow.click();//click on back arrow
            profile.customButton.click();//click on custom button
            if(profile.notification_Message.size()==0)
                Assert.fail("Validation message is not dispalying");
            profile.customButton.click();//click on custom button
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            profile.chapterLabelButton.get(0).click();//click on on button of chapterelevel
            profile.topicLabelButton.get(0).click();//click on on botton of section lebel
            profile.customizeBackArrow.click();//click on back arrow
            profile.customButton.click();//click on custom button
            profile.getCancel_Button().click();//click on cancel
            if(profile.notification_Message.size()==0)
                Assert.fail("Validation message is not displaying");
            profile.customizeNow_Link.click();//click on customize now link
            profile.chapterLabelButtonOff.get(0).click();//click on on button of 1st chapter
            profile.helpIconPost.get(0).click();//click on help icon of practice
            Assert.assertEquals(profile.getHelp_Text().getText(),"The post chapter section for a chapter cannot be disabled.","validation message for post chapter is not available");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToNavigateToCustomizeStudyItems in class ForLsCourse", e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void instructorShouldBeAbleT0EnableDisableStudyChaptersAndSection() {
        try {
            //tc row no 312
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("312");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            if (profile.chapterLabelButton.size()==0)
                Assert.fail("All the Chapters is not get enabled");
            if (profile.topicLabelButton.size()==0)
                Assert.fail("All the sections is not get enabled");
            profile.chapterLabelButton.get(0).click();//click on on of 1st chapter
            if(profile.chapterLabelButtonOff.get(0).isDisplayed()==false)
                Assert.fail("All the section within the deactivated chapter is not get deactivated");
            if (profile.chapterLabelButtonOff.get(0).isDisplayed()==false)
                Assert.fail("The \"ON/OFF\" status for deactivated chapter status is not Off.");
            boolean elementFound = false;
            try{
                driver.findElement(By.id("settings-save"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"save option is available");
            Thread.sleep(1000);
            try{
                driver.findElement(By.id("settings-cancel"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"cancel option is available");
            Thread.sleep(1000);
            profile.chapterLabelButtonOff.get(0).click();//click on off on 1st chapter
            Assert.assertEquals(profile.changeSavedStatusBox.isDisplayed(),true,"Saved change status box is not available");
            Thread.sleep(3000);

            try{
                driver.findElement(By.className("changes-saved-status-box"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"change saved status box is displaying after 3 second");
            profile.customizeBackArrow.click();//click on back arrow
            String settingTitle=profile.getSetting_Title().getText();
            Assert.assertEquals(settingTitle,"Settings","After click on back button it is not navigate to setting page");
            Assert.assertEquals(profile.customizeNow_Link.isEnabled(),true,"The status of Study Plan is not auto-saved as \"On\"");
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.chapterLabelButton.get(0).click();

            new LoginUsingLTI().ltiLogin("312_1");//log in as student
            new Navigator().NavigateTo("e-Textbook");
            if(lessonPage.chapterContainer.get(0).isDisplayed()==true)
                Assert.fail("The deactivated chapter is displayed to student in the Study Plan.");

            new LoginUsingLTI().ltiLogin("312");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.chapterLabelButtonOff.get(0).click();//click on off on 1st chapter
            Assert.assertEquals(profile.changeSavedStatusBox.isDisplayed(),true,"Saved change status box is not available");
            Thread.sleep(3000);
            boolean elementFound1 = false;
            try{
                driver.findElement(By.className("changes-saved-status-box"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1,false,"change saved status box is displaying after 3 second");

            try{
                driver.findElement(By.id("settings-save"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1,false,"save option is available");
            Thread.sleep(1000);
            try{
                driver.findElement(By.id("settings-cancel"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1,false,"cancel option is available");
            Thread.sleep(1000);
            profile.customizeBackArrow.click();//click on back arrow
            String settingTitle1=profile.getSetting_Title().getText();
            Assert.assertEquals(settingTitle1,"Settings","After click on back button it is not navigate to setting page");
            Assert.assertEquals(profile.customizeNow_Link.isEnabled(),true,"The status of Study Plan is not auto-saved as \"On\"");

            new LoginUsingLTI().ltiLogin("312_1");//log in as student
            new Navigator().NavigateTo("e-Textbook");
            if(lessonPage.chapterContainer.get(0).isDisplayed()==false)
                Assert.fail("The deactivated chapter is not displayed to student in the Study Plan.");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleT0EnableDisableStudyChaptersAndSection in class ForLsCourse", e);
        }
    }
    @Test(priority = 4,enabled = true)
    public void  disablingOneOrMoreSectionLevelForAnyChapter() {
        try {
            //tc row no 338
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            new LoginUsingLTI().ltiLogin("338");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.topicLabelButton.get(0).click();//click on ON on 1st section of chapter 1
            profile.topicLabelButton.get(0).click();//click on ON on 2nd  section of chapter 1
            Assert.assertEquals(profile.changeSavedStatusBox.isDisplayed(),true,"Saved change status box is not available");
            Thread.sleep(4000);
            boolean elementFound1 = false;
            try{
                driver.findElement(By.className("changes-saved-status-box"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1,false,"change saved status box is displaying after 3 second");

            try{
                driver.findElement(By.id("settings-save"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1,false,"save option is available");
            Thread.sleep(1000);
            try{
                driver.findElement(By.id("settings-cancel"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1,false,"cancel option is available");
            Thread.sleep(1000);
            Assert.assertEquals(profile.topicLabelButtonOff.get(0).isDisplayed(), true, "The status of the section/sections is not change from \"On\" to \"Off\".");
            profile.chapterLabelButton.get(0).click();//click on ON on 1st chapter
            profile.chapterLabelButtonOff.get(0).click();//click on off on 1st chapter
            profile.customizeBackArrow.click();//click on back arrow
            String settingTitle1=profile.getSetting_Title().getText();
            Assert.assertEquals(settingTitle1,"Settings","After click on back button it is not navigate to setting page");
            Assert.assertEquals(profile.customizeNow_Link.isEnabled(),true,"The status of Study Plan is not auto-saved as \"On\"");

        } catch (Exception e) {
            Assert.fail("Exception in test case disablingOneOrMoreSectionLevelForAnyChapter in class ForLsCourse", e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void  disablingAllTheSectionsOfAnEnabledChapter() {
        try {
            //tc row no 348
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);

            new LoginUsingLTI().ltiLogin("348");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            List<WebElement> topicButtonOff=profile.topicLabelButton;
            for(int i=0;i<12;i++){
                topicButtonOff.get(0).click();
                Thread.sleep(1000);
            }

            boolean elementFound = false;
            try{
                driver.findElement(By.id("settings-save"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"save option is available");
            Thread.sleep(1000);
            try{
                driver.findElement(By.id("settings-cancel"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"cancel option is available");
            Thread.sleep(1000);
            profile.chapterLabelButtonOff.get(0).click();//click on off on 1st chapter
            Assert.assertEquals(profile.changeSavedStatusBox.isDisplayed(),true,"Saved change status box is not available");
            Thread.sleep(3000);

            try{
                driver.findElement(By.className("changes-saved-status-box"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"change saved status box is displaying after 3 second");
            profile.customizeBackArrow.click();//click on back arrow
            String settingTitle1=profile.getSetting_Title().getText();
            Assert.assertEquals(settingTitle1,"Settings","After click on back button it is not navigate to setting page");
            Assert.assertEquals(profile.customizeNow_Link.isEnabled(),true,"The status of Study Plan is not auto-saved as \"On\"");
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            List<WebElement> topicButtonOff1=profile.topicLabelButton;
            for(int i=0;i<12;i++){
                topicButtonOff1.get(0).click();
                Thread.sleep(1000);
            }

            new LoginUsingLTI().ltiLogin("348_1");//log in as student
            new Navigator().NavigateTo("e-Textbook");
            if(lessonPage.chapterContainer.get(0).isDisplayed()==true)
                Assert.fail("The deactivated chapter is displayed to student in the Study Plan.");


            new LoginUsingLTI().ltiLogin("348");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.chapterLabelButtonOff.get(0).click();//click on off on 1st chapter
            Assert.assertEquals(profile.changeSavedStatusBox.isDisplayed(),true,"Saved change status box is not available");
            Thread.sleep(3000);
            boolean elementFound1 = false;
            try{
                driver.findElement(By.id("settings-save"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1,false,"save option is available");
            Thread.sleep(1000);
            try{
                driver.findElement(By.id("settings-cancel"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1,false,"cancel option is available");
            Thread.sleep(1000);
            profile.customizeBackArrow.click();//click on back arrow
            String settingTitle2=profile.getSetting_Title().getText();
            Assert.assertEquals(settingTitle2,"Settings","After click on back button it is not navigate to setting page");
            Assert.assertEquals(profile.customizeNow_Link.isEnabled(),true,"The status of Study Plan is not auto-saved as \"On\"");


        } catch (Exception e) {
            Assert.fail("Exception in test case disablingAllTheSectionsOfAnEnabledChapter in class ForLsCourse", e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void studentIsNotAbleToViewStudyItemsThatAreDeactivatedByInstructor() {
        try {
            //tc row no 367
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);

            new LoginUsingLTI().ltiLogin("367");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.chapterLabelButton.get(0).click();//click on ON on 1st chapter

            new LoginUsingLTI().ltiLogin("367_1");//log in as student
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            if(lessonPage.chapterContainer.get(0).isDisplayed()==true)
                Assert.fail("The deactivated chapter is displayed to student in the Study Plan.");

            // new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.al-notification-message-body")))
            //  new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.al-notification-message-body")));
            new Navigator().NavigateTo("Dashboard");//navigate to dashboard
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            boolean elementFound1 = false;
            try{
                driver.findElement(By.cssSelector("div.al-notification-message-body"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1,false,"Robo notification is displayed");
            if(lessonPage.chapterContainer.get(0).isDisplayed()==true)
                Assert.fail("The deactivated chapter is displayed to student in the Study Plan.");


        } catch (Exception e) {
            Assert.fail("Exception in test case studentIsNotAbleToViewStudyItemsThatAreDeactivatedByInstructor in class ForLsCourse", e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void OneOrMoreSectionsAreDeactivated() {
        try {
            //tc row no 380
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);

            new LoginUsingLTI().ltiLogin("380");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.topicLabelButton.get(0).click();//click on on 1st section of 1st chapter
            profile.topicLabelButton.get(0).click();//click on on 2nd section of 1st chapter

            new LoginUsingLTI().ltiLogin("380_1");//log in as student
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            driver.findElement(By.linkText("e-Textbook")).click();//click on Assignments
            if(lessonPage.chapterContainer.get(0).isDisplayed()==false)
                Assert.fail("The deactivated chapter is displayed to student in the Study Plan.");
            //  new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.al-notification-message-body")));
            new Navigator().NavigateTo("Dashboard");//navigate to dashboard
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            boolean elementFound1 = false;
            try{
                driver.findElement(By.cssSelector("div.al-notification-message-body"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1,false,"Robo notification is displayed");
            Assert.assertEquals(driver.findElement(By.xpath("//a[@title='0.2: Geography’s Perspective']")).isDisplayed(), true, "Enabled sections are not displaying");
            boolean introduction = new BooleanValue().presenceOfElement(380, By.linkText("Introduction"));
            Assert.assertEquals(introduction,false,"Deactivated section is displaying");
            boolean worldOnMaps = new BooleanValue().presenceOfElement(380, By.linkText("(//li[@title='0.1 A World on Maps'])[1]"));
            Assert.assertEquals(elementFound1,false,"Deactivated section is displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case OneOrMoreSectionsAreDeactivated in class ForLsCourse", e);
        }
    }

    @Test(priority = 8,enabled = true)
    public void  allTheSectionsAreDeactivated() {
        try {
            //tc row no 390
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);

            new LoginUsingLTI().ltiLogin("390");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            List<WebElement> topicButtonOff=profile.topicLabelButton;
            for(int i=0;i<12;i++){
                topicButtonOff.get(0).click();
                Thread.sleep(1000);
            }

            new LoginUsingLTI().ltiLogin("390_1");//log in as student
            new Click().clickbyxpath("//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            driver.findElement(By.linkText("e-Textbook")).click();//click on Assignments
            if(lessonPage.chapterContainer.get(0).isDisplayed()==true)
                Assert.fail("The deactivated chapter is displayed to student in the Study Plan.");
            new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.al-notification-message-body")));
            new Navigator().NavigateTo("Dashboard");//navigate to dashboard
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            boolean elementFound1 = false;
            try{
                driver.findElement(By.cssSelector("div.al-notification-message-body"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1,false,"Robo notification is displayed");
            if(lessonPage.chapterContainer.get(0).isDisplayed()==true)
                Assert.fail("The deactivated chapter is displayed to student in the Study Plan.");


        } catch (Exception e) {
            Assert.fail("Exception in test case allTheSectionsAreDeactivated in class ForLsCourse", e);
        }
    }

    @Test(priority = 9,enabled = true)
    public void  ChapterIsDeactivated() {
        try {
            //tc row no 403
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("403");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.chapterLabelButton.get(0).click();//click  on on 1st chapter
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            if(lessonPage.chapterContainer.get(0).isDisplayed()==false)
                Assert.fail("The deactivated chapter is not displayed to instructor in the Study Plan.");
            lessonPage.hiddenChapter.click();//click on 1st chapter
            Assert.assertEquals(lessonPage.deactivatedChapter.isDisplayed(), true, "Deactivated chapter sections are not available");
            boolean assign = new BooleanValue().presenceOfElement(403, By.linkText("assign-all-lessons"));
            Assert.assertEquals(assign,false,"Assign all lesson is available");
            Assert.assertEquals(lessonPage.tocTitleDiactivated.isDisplayed(),true,"\"Deactivated : 1 Chapter\" label is not displaying in the header.");

        } catch (Exception e) {
            Assert.fail("Exception in test case ChapterIsDeactivated in class ForLsCourse", e);
        }
    }

    @Test(priority = 10,enabled = true)
    public void  instructorShouldHaveDeactivatedOneMoreSectionsInAChapter() {
        try {
            //tc row no 415
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("415");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.topicLabelButton.get(0).click();//click on ON on 1st section of chapter 1
            profile.topicLabelButton.get(0).click();//click on ON on 2nd  section of chapter 1

            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            if(lessonPage.chapterContainer.get(0).isDisplayed()==false)
                Assert.fail("The deactivated chapter is not displayed to instructor in the Study Plan.");
            if(driver.findElements(By.xpath("//div[@class='blockUI blockOverlay']")).size()==0)
                Assert.fail("All the sections of the chapter is not displayed in the Study Plan.");
            Assert.assertEquals(lessonPage.tocTitleDiactivatedSection.getText(),"Deactivated: 2 Sections","\"Deactivated : 2 Sections\" label is not displaying in the header.");
            driver.findElement(By.className("assign-all-lessons")).click();//click on assign icon
            if (lessonPage.getAllLessonNames().size()!=10)
                Assert.fail("Pop up containing the section names is not displayed");
            Assert.assertEquals(lessonPage.assign_button.isDisplayed(),true,"Assign button is not available");
            Assert.assertEquals(lessonPage.cancel_button.isDisplayed(),true,"Cancel button is not available");
            lessonPage.cancel_button.click();//click on cancel


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldHaveDeactivatedOneMoreSectionsInAChapter in class ForLsCourse", e);
        }
    }

    @Test(priority = 11,enabled = true)
    public void chapterIsDeactivated() {
        try {
            //tc row no 427
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("427");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.chapterLabelButton.get(0).click();//click  on on 1st chapter
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            if(lessonPage.chapterContainer.get(0).isDisplayed()==false)
                Assert.fail("The deactivated chapter is not displayed to instructor in the Study Plan.");
            lessonPage.hiddenChapter.click();//click on 1st chapter
            Assert.assertEquals(lessonPage.deactivatedChapter.isDisplayed(), true, "Deactivated chapter sections are not available");
            boolean assign = new BooleanValue().presenceOfElement(427, By.linkText("assign-all-lessons"));
            Assert.assertEquals(assign,false,"Assign all lesson is available");

        } catch (Exception e) {
            Assert.fail("Exception in test case chapterIsDeactivated in class ForLsCourse", e);
        }
    }

    @Test(priority = 11,enabled = true)
    public void oneMoreSectionsInAChapterIsDeactivated() {
        try {
            //tc row no 434
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("434");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.topicLabelButton.get(0).click();//click on ON on 1st section of chapter 1
            profile.topicLabelButton.get(0).click();//click on ON on 2nd  section of chapter 1

            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            if(lessonPage.chapterContainer.get(0).isDisplayed()==false)
                Assert.fail("The deactivated chapter is not displayed to instructor in the Study Plan.");
            if(driver.findElements(By.xpath("//div[@class='blockUI blockOverlay']")).size()==0)
                Assert.fail("All the sections of the chapter is not displayed in the Study Plan.");

        } catch (Exception e) {
            Assert.fail("Exception in test case oneMoreSectionsInAChapterIsDeactivated in class ForLsCourse", e);
        }
    }


    @Test(priority = 12,enabled = true)
    public void whenDiscussionIsAddedUsingDiscussionTabInLessonPage() {
        try {
            //tc row no 212
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("440_1");//log in as student
            new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Discussion");
            String discussionText = new RandomString().randomstring(6);
            new Discussion().postDiscussion(discussionText);//post a discussion

            new LoginUsingLTI().ltiLogin("440");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.topicLabelButton.get(0).click();//click on ON on 1st section of chapter 1
            profile.topicLabelButton.get(0).click();//click on ON on 2nd  section of chapter 1
            Thread.sleep(1000);
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getJumpOut().click();//click on jumpout
            Thread.sleep(6000);
            String introduction=driver.findElement(By.xpath(".//*[@id='ht1']")).getText();
            if (!introduction.contains("INTRODUCTION")) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }

            new LoginUsingLTI().ltiLogin("440_1");//log in as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getJumpOut().click();//click on jumpout
            Thread.sleep(5000);
            String introduction1=driver.findElement(By.xpath(".//*[@id='ht1']")).getText();
            if (!introduction1.contains("INTRODUCTION")) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");

            }

        } catch (Exception e) {
            Assert.fail("Exception in test case whenDiscussionIsAddedUsingDiscussionTabInLessonPage in class ForLsCourse", e);
        }
    }


    @Test(priority = 13,enabled = true)
    public void whenDiscussionIsAddedByHighlightingTextInLessonPage() {
        try {
            //tc row no 445
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("445_1");//log in as student
            new Navigator().NavigateTo("eTextbook");
            new TOCShow().tocHide();
            Thread.sleep(6000);
            String commentPost=new RandomString().randomstring(6);
            Actions actions = new Actions(driver);
            new CloseHelpPage().closehelppage();
            actions.moveToElement(driver.findElement(By.cssSelector(".noindent.default")), 5, 20)
                    .clickAndHold()
                    .moveByOffset(50, 50)
                    .release()
                    .perform();
            new Discussion().addDiscussionAfterHighlightingText(commentPost);//add Discussion after highlight text

            new LoginUsingLTI().ltiLogin("445");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.chapterLabelButton.get(0).click();//click on ON on chapter 1
            Thread.sleep(1000);
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getJumpOut().click();//click on jumpout
            Thread.sleep(6000);
            String introduction=driver.findElement(By.xpath(".//*[@id='ht1']")).getText();
            if (!introduction.contains("INTRODUCTION")) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }

            new LoginUsingLTI().ltiLogin("445_1");//log in as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getJumpOut().click();//click on jumpout
            Thread.sleep(5000);
            String introduction1=driver.findElement(By.xpath(".//*[@id='ht1']")).getText();
            if (!introduction1.contains("INTRODUCTION")) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case whenDiscussionIsAddedByHighlightingTextInLessonPage in class ForLsCourse", e);
        }
    }

    @Test(priority = 14,enabled = true)
    public void whenCommentIsAddedInAnyMediaWidgetTypeInLessonPage() {
        try {
            //tc row no 449
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("449_1");//login as student
            new LoginUsingLTI().ltiLogin("449"); //login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(15);
            new SelectCourse().selectInvisibleAssignment("8A.3: South Asia’s Physiography");
            WebElement scroll=driver.findElement(By.cssSelector("span.assign-this-text"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(449);

            new LoginUsingLTI().ltiLogin("449_1");//login a student
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(15);
            new SelectCourse().selectInvisibleAssignment("8A.3: South Asia’s Physiography");
            String perspective = new RandomString().randomstring(2);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignemnt

            new LoginUsingLTI().ltiLogin("449");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.chapterLabelButton.get(15).click();//click on ON on chapter 10
            Thread.sleep(1000);
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getJumpOut().click();//click on jumpout
            Thread.sleep(6000);
            String lesson=driver.findElement(By.xpath("//h2[@id='sec-8A-2']")).getText();
            if (!lesson.contains("SOUTH ASIA’S PHYSIOGRAPHY")) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }

            new LoginUsingLTI().ltiLogin("449_1");//log in as student
            new Navigator().NavigateTo("Course Stream");
            Thread.sleep(5000);
            courseStreamPage.getJumpOut().click();//click on jumpout
            Thread.sleep(5000);
            String lesson1=driver.findElement(By.xpath("//h2[@id='sec-8A-2']")).getText();
            if (!lesson1.contains("SOUTH ASIA’S PHYSIOGRAPHY")) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case whenCommentIsAddedInAnyMediaWidgetTypeInLessonPage in class ForLsCourse", e);
        }
    }


    @Test(priority = 15,enabled = true)
    public void  bookmarkingLessonDiscussionNote() {
        try {
            //tc row no 454
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);

            new LoginUsingLTI().ltiLogin("454_1");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new TOCShow().tocHide();
            new Click().clickBycssselector("a[class='ls-bookmark-widget ls-widget-unbookmarked']");//bookmark image widget

            new LoginUsingLTI().ltiLogin("454");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.chapterLabelButton.get(0).click();//click on ON on chapter 1
            Thread.sleep(1000);

            new LoginUsingLTI().ltiLogin("454_1");//login as student
            new Navigator().NavigateTo("My Notes");//navigate to my notes
            new Click().clickByclassname("my-journal-media-popout");//click on jump out icon
            Thread.sleep(3000);
            driver.findElement(By.className("widget-close")).click();//click on 'x'
            String introduction=driver.findElement(By.xpath(".//*[@id='ht1']")).getText();
            if (!introduction.contains("INTRODUCTION")) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }
        } catch (Exception e) {
            Assert.fail("Exception in test case bookmarkingLessonDiscussionNote in class ForLsCourse", e);
        }
    }

    @Test(priority = 16,enabled = true)
    public void postedADisscussionOrNote() {
        try {
            //tc row no 456
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);

            new LoginUsingLTI().ltiLogin("456_1");//log in as student
            new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Discussion");
            String discussionText = new RandomString().randomstring(6);
            new Discussion().postDiscussion(discussionText);//post a discussion

            new LoginUsingLTI().ltiLogin("456");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.chapterLabelButton.get(0).click();//click on ON on chapter 1
            Thread.sleep(1000);

            new LoginUsingLTI().ltiLogin("456_1");//login as student
            new Navigator().NavigateTo("My Notes");//navigate to my notes
            new Click().clickByclassname("my-journal-media-popout");//click on jump out icon
            Thread.sleep(3000);
            String introduction=driver.findElement(By.xpath(".//*[@id='ht1']")).getText();
            if (!introduction.contains("INTRODUCTION")) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }
        } catch (Exception e) {
            Assert.fail("Exception in test case postedADisscussionOrNote in class ForLsCourse", e);
        }
    }


    @Test(priority = 17,enabled = true)
    public void highlightedAText() {
        try {
            //tc row no 458
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);

            new LoginUsingLTI().ltiLogin("458_1");//log in as student
            new Navigator().NavigateTo("eTextbook");
            new TOCShow().tocHide();
            Thread.sleep(6000);
            String commentPost=new RandomString().randomstring(6);
            Actions actions = new Actions(driver);
            new CloseHelpPage().closehelppage();
            actions.moveToElement(driver.findElement(By.cssSelector(".noindent.default")), 5, 20)
                    .clickAndHold()
                    .moveByOffset(50, 50)
                    .release()
                    .perform();
            new Discussion().addDiscussionAfterHighlightingText(commentPost);//add Discussion after highlight text

            new LoginUsingLTI().ltiLogin("458");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.chapterLabelButton.get(0).click();//click on ON on chapter 1
            Thread.sleep(1000);

            new LoginUsingLTI().ltiLogin("458_1");//login as student
            new Navigator().NavigateTo("My Notes");//navigate to my notes
            new Click().clickByclassname("my-journal-media-popout");//click on jump out icon
            Thread.sleep(3000);
            String introduction=driver.findElement(By.xpath(".//*[@id='ht1']")).getText();
            if (!introduction.contains("INTRODUCTION")) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case highlightedAText in class ForLsCourse", e);
        }
    }


    @Test(priority = 18,enabled = true)
    public void sharedPerspective() {
        try {
            //tc row no 460
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);

            new LoginUsingLTI().ltiLogin("460_1");//login as student
            new LoginUsingLTI().ltiLogin("460"); //login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(15);
            new SelectCourse().selectInvisibleAssignment("8A.3: South Asia’s Physiography");
            WebElement scroll=driver.findElement(By.cssSelector("span.assign-this-text"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(460);

            new LoginUsingLTI().ltiLogin("460_1");//login a student
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(15);
            new SelectCourse().selectInvisibleAssignment("8A.3: South Asia’s Physiography");
            String perspective = new RandomString().randomstring(2);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignemnt

            new LoginUsingLTI().ltiLogin("460");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.chapterLabelButton.get(15).click();//click on ON on chapter 10
            Thread.sleep(1000);

            new LoginUsingLTI().ltiLogin("460_1");//login as student
            new Navigator().NavigateTo("My Notes");//navigate to my notes
            new Click().clickByclassname("my-journal-media-popout");//click on jump out icon
            Thread.sleep(5000);
            String lesson1=driver.findElement(By.xpath("//h2[@id='sec-8A-2']")).getText();
            if (!lesson1.contains("SOUTH ASIA’S PHYSIOGRAPHY")) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }
        } catch (Exception e) {
            Assert.fail("Exception in test case sharedPerspective in class ForLsCourse", e);
        }
    }

    @Test(priority = 19,enabled = true)
    public void addingDiscussionToAnyQuestion() {
        try {
            //tc row no 463
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new Assignment().create(463);
            new LoginUsingLTI().ltiLogin("463_1");//login as student
            new LoginUsingLTI().ltiLogin("463"); //login as instructor
            new Assignment().assignToStudent(463);

            new LoginUsingLTI().ltiLogin("463_1");//login as student1
            new Assignment().submitAssignmentAsStudent(463); //submit assignment
            Thread.sleep(2000);
            new ClickOnquestionCard().clickonquestioncard(0);
            assignments.getDiscussionTab().click();
            assignments.getNewButton().click();
            assignments.getEditBox().sendKeys("This is discussion");
            assignments.getSubmit().click();

            new LoginUsingLTI().ltiLogin("463");//log in as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.topicLabelButton.get(0).click();//click on ON on 1st section of chapter 1
            profile.topicLabelButton.get(0).click();//click on ON on 2nd  section of chapter 1
            Thread.sleep(1000);
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getJumpOut().click();//click on jumpout
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("al-diag-test-chapter-label")));
            String discussion=driver.findElement(By.xpath("//div[@class='ls-right-user-post-body']")).getText();
            if (!discussion.contains("This is discussion")) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }

            new LoginUsingLTI().ltiLogin("463_1");//log in as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getJumpOut().click();//click on jumpout
            Thread.sleep(60000);
            String discussion1=driver.findElement(By.xpath("//div[@class='ls-right-user-post-body']")).getText();
            if (!discussion1.contains("This is discussion")) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }
        } catch (Exception e) {
            Assert.fail("Exception in test case addingDiscussionToAnyQuestion in class ForLsCourse", e);
        }
    }


    @Test(priority = 20,enabled = true)
    public void aboutTabInAssignmentAssessmentPage() {
        try {
            //tc row no 465
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver,AssignmentTab.class);

            new Assignment().create(465);
            new LoginUsingLTI().ltiLogin("465_1");//login as student
            new LoginUsingLTI().ltiLogin("465"); //login as instructor
            new Assignment().assignToStudent(465);
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.chapterLabelButton.get(0).click();//click on ON on chapter 1
            Thread.sleep(1000);

            new LoginUsingLTI().ltiLogin("465_1");//login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            Thread.sleep(30000);
            assignmentTab.associateTLO.click();//click on tlo
            Thread.sleep(5000);
            String lesson=driver.findElement(By.xpath("//h2[@id='ht12']")).getText();
            if (!lesson.contains("WORLD GEOGRAPHIC REALMS")) {
                Assert.fail("Instructor should not get navigated to deactivated lesson page");
            }

            new LoginUsingLTI().ltiLogin("465");//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getAssignmentName().click();//clcik on assignment
            Thread.sleep(4000);
            Assert.assertEquals(currentAssignments.associateTLO.isDisplayed(),true,"All the question including those associated with deactivated TLOs is not displayed in preview page");

        } catch (Exception e) {
            Assert.fail("Exception in test case aboutTabInAssignmentAssessmentPage in class ForLsCourse", e);
        }
    }


    @Test(priority = 21,enabled = true)
    public void assignmentTabInLessonPage() {
        try {
            //tc row no 467
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver,AssignmentTab.class);

            new Assignment().create(467);
            new LoginUsingLTI().ltiLogin("467"); //login as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.topicLabelButton.get(0).click();//click on ON on chapter 1
            profile.topicLabelButton.get(0).click();//click on ON on chapter 1
            Thread.sleep(1000);
            new Assignment().assignToStudent(467);
            new Navigator().NavigateTo("e-Textbook");
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments");
            if(!assignmentTab.assessmentName.getText().contains("(shor) IT20_R202_Assignment_467"))
                Assert.fail("All the assignment is not displayed including those having deactivated TLOs associated to that lesson");
            assignmentTab.rightArrow.click();//click on right arrow
            assignmentTab.open_button.click();//click on open
            Thread.sleep(6000);
            Assert.assertEquals(currentAssignments.associateTLO.isDisplayed(),true,"All the question including those associated with deactivated TLOs is not displayed in preview page");

            new LoginUsingLTI().ltiLogin("467_1"); //login as student
            new Navigator().NavigateTo("e-Textbook");
            new TOCShow().tocHide();
            Thread.sleep(4000);
            new Navigator().navigateToTab("Assignments");
            if(!assignmentTab.assessmentName.getText().contains("(shor) IT20_R202_Assignment_467"))
                Assert.fail("All the assignment is not displayed including those having deactivated TLOs associated to that lesson");
            assignmentTab.rightArrow.click();//click on right arrow
            assignmentTab.open_button.click();//click on open
            Thread.sleep(6000);
            Assert.assertEquals(assignmentTab.associateTLO.isDisplayed(),true,"All the question including those associated with deactivated TLOs is not displayed in preview page");

        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentTabInLessonPage in class ForLsCourse", e);
        }
    }

    @Test(priority = 22,enabled = true)
    public void  assignmentThroughCourseStream() {
        try {
            //tc row no 472
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver,AssignmentTab.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new Assignment().create(472);
            new LoginUsingLTI().ltiLogin("472"); //login as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.topicLabelButton.get(0).click();//click on ON on chapter 1
            profile.topicLabelButton.get(0).click();//click on ON on chapter 1
            Thread.sleep(1000);
            new Assignment().assignToStudent(472);
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.associateTLO.isDisplayed(),true,"All the question including those associated with deactivated TLOs is not displayed in preview page");

            new LoginUsingLTI().ltiLogin("472_1"); //login as student
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assessment
            Thread.sleep(3000);
            Assert.assertEquals(assignmentTab.associateTLO.isDisplayed(),true,"All the question including those associated with deactivated TLOs is not displayed in preview page");

        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentThroughCourseStream in class ForLsCourse", e);
        }
    }


    @Test(priority = 23,enabled = true)
    public void  assignmentThroughCurrentAssignmentPage() {
        try {
            //tc row no 475
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver,AssignmentTab.class);

            new Assignment().create(475);
            new LoginUsingLTI().ltiLogin("475"); //login as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.topicLabelButton.get(0).click();//click on ON on chapter 1
            profile.topicLabelButton.get(0).click();//click on ON on chapter 1
            Thread.sleep(1000);
            new Assignment().assignToStudent(475);
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getAssignmentName().click();//clcik on assignment
            Thread.sleep(4000);
            Assert.assertEquals(currentAssignments.associateTLO.isDisplayed(),true,"All the question including those associated with deactivated TLOs is not displayed in preview page");

            new LoginUsingLTI().ltiLogin("475_1"); //login as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            Thread.sleep(30000);
            Assert.assertEquals(assignmentTab.associateTLO.isDisplayed(),true,"All the question including those associated with deactivated TLOs is not displayed in preview page");

        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentThroughCurrentAssignmentPage in class ForLsCourse", e);
        }
    }

    @Test(priority = 24,enabled = true)
    public void  questionBankPage() {
        try {
            //tc row no 478
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver,QuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "478");

            new Assignment().create(478);
            new LoginUsingLTI().ltiLogin("478"); //login as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.topicLabelButton.get(0).click();//click on ON on chapter 1
            profile.topicLabelButton.get(0).click();//click on ON on chapter 1
            Thread.sleep(1000);
            new Navigator().NavigateTo("My Question Bank");
            questionBank.getQuestionBankTitle().click();//click on question bank
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(3000);
            questionBank.getPreviewButton().click();//click on preview
            Assert.assertEquals(currentAssignments.associateTLO.isDisplayed(),true,"All the question including those associated with deactivated TLOs is not displayed in preview page");

        } catch (Exception e) {
            Assert.fail("Exception in test case questionBankPage in class ForLsCourse", e);
        }
    }


    @Test(priority = 25,enabled = true)
    public void  eTextBookType() {
        try {
            //tc row no 479
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver, Profile.class);
            TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);

            new LoginUsingLTI().ltiLogin("479"); //login as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.topicLabelButton.get(1).click();//click on ON on chapter 1
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            tocSearch.getTocSearch().sendKeys("a world on maps");
            tocSearch.getSearch_Icon().click();//click on search
            tocSearch.getLessonIcon().click();//click on lesson icon
            Thread.sleep(2000);
            String text=tocSearch.getGlossaryHeading().getText();
            if(text.contains("a world on maps"))
                Assert.fail("It displaying Deactivated lessons data in the search.");

        } catch (Exception e) {
            Assert.fail("Exception in test case eTextBookType in class ForLsCourse", e);
        }
    }

    @Test(priority = 26,enabled = true)
    public void oneInstructorHavingMultipleClassSections() {
        try {
            //tc row no 481
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);

            new LoginUsingLTI().ltiLogin("481"); //login as instructor
            lessonPage.getClassSectionDropDown().click();
            lessonPage.getClassName().click();// click on biology
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.topicLabelButton.get(0).click();//click on ON on chapter 1
            profile.topicLabelButton.get(0).click();//click on ON on chapter 1
            Thread.sleep(1000);
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);
            Assert.assertEquals(lessonPage.deactivatedChapter.isDisplayed(), true, "Deactivated chapter sections are not available");
            lessonPage.getClassSectionDropDown().click();
            driver.findElement(By.linkText("IT20_R202_studtitle_481")).click();
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook

            boolean elementFound = false;
            try{
                driver.findElement(By.cssSelector("div[class='blockUI blockOverlay']"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"The deactivated Study for class1 is affect the Study for class2");


        } catch (Exception e) {
            Assert.fail("Exception in test case oneInstructorHavingMultipleClassSections in class ForLsCourse", e);
        }
    }


    @Test(priority = 27 ,enabled = true)
    public void oneClassSectionHavingMultipleInstructor() {
        try {
            //tc row no 485
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);

            new LoginUsingLTI().ltiLogin("485"); //login as instructor1
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customButton.click();//click on custom button
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            profile.topicLabelButton.get(0).click();//click on ON on chapter 1
            profile.topicLabelButton.get(0).click();//click on ON on chapter 1
            Thread.sleep(1000);
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);
            Assert.assertEquals(lessonPage.deactivatedChapter.isDisplayed(), true, "Deactivated chapter sections are not available");
            new LoginUsingLTI().ltiLogin("486"); //login as instructor2
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);
            Assert.assertEquals(lessonPage.deactivatedChapter.isDisplayed(), true, "Deactivated chapter sections are not available");
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.customizeNow_Link.click();//click on customize now link
            Thread.sleep(2000);
            Assert.assertEquals(profile.customizeStudyPlanTitle.getText(),"Customize Study Plan","Instructor is not navigated to Customize Study Plan page");
            if(profile.topicLabelButtonOff.get(0).isDisplayed()==false)
                Assert.fail("Instructor2 is not able to see the changes made by Instructor in the Customize Study");


        } catch (Exception e) {
            Assert.fail("Exception in test case oneClassSectionHavingMultipleInstructor in class ForLsCourse", e);
        }
    }
}
