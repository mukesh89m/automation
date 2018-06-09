package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R237;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Discussion;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Profile.Profile;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 10/6/2015.
 */
public class CommentOnCourseStreamForLs extends Driver{

    @Test(priority = 1,enabled = true)
    public void commentOnPostForSpanish(){

        try {
            //TC row no 1144
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            CourseStreamPage courseStreamPage= PageFactory.initElements(driver, CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            new LoginUsingLTI().ltiLogin("486");//login as instructor
            new LoginUsingLTI().ltiLogin("486_1");//login as student
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            String message = new RandomString().randomstring(4);
            new PostMessage().postmessage(message);
            boolean postfound = new PostMessageValidate().postMessageValidate(message);
            if(postfound == false)
            {
                Assert.fail("Instructor post is not displyed in Course stream.");
            }
            new UIElement().waitAndFindElement(courseStreamPage.commentLinkIn_CSPage.get(0));
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

            manageContent.langaugePalette_spanish.click(); //click on the spanish language
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(),16,"All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue,"áÁéÉíÍñÑóÓúÚüÜ¡¿","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            int height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_spanish.click(); //click on the spanish language

            Actions action=new Actions(driver);
            WebElement From=driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
            action.dragAndDrop(manageContent.langaugePalette_header,From).click().build().perform();
            driver.findElement(By.xpath("//html/body")).click();

            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_spanish.click(); //click on the spanish language

            manageContent.langaugePalette_textBox.sendKeys("abc123");
            Thread.sleep(2000);
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            Thread.sleep(2000);
            Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(),"abc123\náÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");


        } catch (Exception e) {
            Assert.fail("Exception in TC commentOnPostForSpanish of class CommentOnCourseStream ",e);
        }
    }
    @Test(priority = 2,enabled = true)
    public void commentOnPostForItalian(){

        try {
            //TC row no 1170
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new LoginUsingLTI().ltiLogin("486_1");//login as student
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new UIElement().waitAndFindElement(courseStreamPage.commentLinkIn_CSPage.get(0));
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

            manageContent.langaugePalette_Italian.click(); //click on the spanish language
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(),22,"All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Italian Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue,"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            int height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(),"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","All the characters is not getting added to the question content area.");

            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_Italian.click(); //click on the spanish language

            Actions action=new Actions(driver);
            WebElement From=driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
            action.dragAndDrop(manageContent.langaugePalette_header,From).click().build().perform();
            driver.findElement(By.xpath("//html/body")).click();

            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_Italian.click(); //click on the spanish language

            manageContent.langaugePalette_textBox.sendKeys("abc123");
            Thread.sleep(2000);
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            Thread.sleep(2000);
            Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(),"abc123\nÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","All the characters is not getting added to the question content area.");


        } catch (Exception e) {
            Assert.fail("Exception in TC commentOnPostForItalian of class CommentOnCourseStream ",e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void commentOnPostForFrench(){

        try {
            //TC row no 1155
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new LoginUsingLTI().ltiLogin("486_1");//login as student
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new UIElement().waitAndFindElement(courseStreamPage.commentLinkIn_CSPage.get(0));
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

            manageContent.langaugePalette_French.click(); //click on the spanish language
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(), 24, "All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"French Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue,"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            int height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(),"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the characters is not getting added to the question content area.");

            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_French.click(); //click on the spanish language

            Actions action=new Actions(driver);
            WebElement From=driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
            action.dragAndDrop(manageContent.langaugePalette_header,From).click().build().perform();
            driver.findElement(By.xpath("//html/body")).click();

            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_French.click(); //click on the spanish language

            manageContent.langaugePalette_textBox.sendKeys("abc123");
            Thread.sleep(2000);
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            Thread.sleep(2000);
            Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(),"abc123\nÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the characters is not getting added to the question content area.");

            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_French.click(); //click on the spanish language

            manageContent.langaugePalette_cancelButton.click(); //click on the cancel button
            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");

            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

            manageContent.langaugePalette_French.click(); //click on the spanish language
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"French palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            courseStreamPage.langaugePalette_saveButton.click();//click on save
            courseStreamPage.post_Link.get(0).click();
            Assert.assertTrue(courseStreamPage.postComment_content.get(0).getText().trim().contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœabc123ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"), "All the Comments added by the Student is not displaying at the instructor side.");


        } catch (Exception e) {
            Assert.fail("Exception in TC commentOnPostForFrench of class CommentOnCourseStream ",e);
        }
    }


    @Test(priority = 4,enabled = true)
    public void languagePaletteInDiscussionTabInStudentSide(){

        try {
            //TC row no 1187
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion=PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new LoginUsingLTI().ltiLogin("1187_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            String inputStringValue=null;
            int height=0;
            int height1=0;

            new TOCShow().tocHide();
            new UIElement().waitAndFindElement(discussion.newDiscussion_button);
            discussion.newDiscussion_button.click(); //click on the new discussion
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

            manageContent.langaugePalette_spanish.click(); //click on the spanish language
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(),16,"All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue,"áÁéÉíÍñÑóÓúÚüÜ¡¿","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(discussion.discussion_text.getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

            //TC row no 24
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_spanish.click(); //click on the spanish language
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
            height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            discussion.submit_button.click(); //click on the save button

            //Tc row no 106
            if(height>height1){
                Assert.fail("The height of the question textbox is not  increased accordingly");
            }

            new LoginUsingLTI().ltiLogin("1187_1");//login as student
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertTrue(courseStreamPage.courseStream_content.getText().trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿"), "All the Comments added by the Student is not displaying at the instructor side.");
            new AddCommentInCSPage().verificationForSpanishInCSPage();


            new LoginUsingLTI().ltiLogin("1187_1");//login as student
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.getJumpOut().click();//click on the jump out icon
            Thread.sleep(9000);
            new Click().clickbyxpath("//div[@class='prow editdiscussion-wrapper']//i[@class='ls-icon-img ls--comments-icon']");
            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(),16,"All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue,"áÁéÉíÍñÑóÓúÚüÜ¡¿","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

            //TC row no 24
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_spanish.click(); //click on the spanish language
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
            Thread.sleep(3000);
            height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));
            manageContent.langaugePalette_saveButton.click(); //click on the save button


            //Tc row no 106
            if(height>height1){
                Assert.fail("The height of the question textbox is not  increased accordingly");
            }

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");
            Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿","All the characters is not getting added to the question content area.");


        } catch (Exception e) {
            Assert.fail("Exception in TC languagePaletteInDiscussionTabInStudentSide of class CommentOnCourseStream ",e);
        }
    }
    @Test(priority = 5,enabled = true)
    public void languagePaletteInDiscussionTabInInstructor(){

        try {
            //TC row no 1206
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion=PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String inputStringValue=null;
            int height=0;
            int height1=0;


            new LoginUsingLTI().ltiLogin("1187");//login as Student
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.getJumpOut().click();//click on the jump out icon
            Thread.sleep(9000);
            new Click().clickbyxpath("//div[@class='prow editdiscussion-wrapper']//i[@class='ls-icon-img ls--comments-icon']");
            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(),16,"All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue,"áÁéÉíÍñÑóÓúÚüÜ¡¿","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

            //TC row no 24
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_spanish.click(); //click on the spanish language
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
            Thread.sleep(3000);
            height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));
            manageContent.langaugePalette_saveButton.click(); //click on the save button


            //Tc row no 106
            if(height>height1){
                Assert.fail("The height of the question textbox is not  increased accordingly");
            }

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");
            Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿","All the characters is not getting added to the question content area.");


        } catch (Exception e) {
            Assert.fail("Exception in TC languagePaletteInDiscussionTabInInstructor of class CommentOnCourseStream ",e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void languagePaletteInEditDiscussionTabInStudentSide(){

        try {
            //TC row no 538
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion=PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            String inputStringValue=null;
            int height=0;
            int height1=0;

            new LoginUsingLTI().ltiLogin("1187_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TOCShow().tocHide();
            new Click().clickByclassname("ls-right-user-img");
            new Click().clickbyxpath("//div[@class='prow editdiscussion-wrapper']//i[@class='ls-icon-img ls--comments-icon']");

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(),16,"All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue,"áÁéÉíÍñÑóÓúÚüÜ¡¿","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(discussion.StudentDiscussion_text.getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

            //TC row no 24
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_spanish.click(); //click on the spanish language
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
            height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            Thread.sleep(2000);
            discussion.post_link.click(); //click on the save button

            //Tc row no 106
            if(height>height1){
                Assert.fail("The height of the question textbox is not  increased accordingly");
            }

            new LoginUsingLTI().ltiLogin("1187_1");//login as student
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertTrue(courseStreamPage.courseStream_content.getText().trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿"), "All the Comments added by the Student is not displaying at the instructor side.");
            new AddCommentInCSPage().verificationForSpanishInCSPage();


            new LoginUsingLTI().ltiLogin("1187_1");//login as student
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.getJumpOut().click();//click on the jump out icon
            Thread.sleep(9000);
            new Click().clickbyxpath("//div[@class='prow editdiscussion-wrapper']//i[@class='ls-icon-img ls--comments-icon']");
            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(),16,"All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue,"áÁéÉíÍñÑóÓúÚüÜ¡¿","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

            //TC row no 24
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_spanish.click(); //click on the spanish language
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
            Thread.sleep(3000);
            height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));
            manageContent.langaugePalette_saveButton.click(); //click on the save button


            //Tc row no 106
            if(height>height1){
                Assert.fail("The height of the question textbox is not  increased accordingly");
            }

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");
            Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿","All the characters is not getting added to the question content area.");


        } catch (Exception e) {
            Assert.fail("Exception in TC languagePaletteInEditDiscussionTabInStudentSide of class CommentOnCourseStream ",e);
        }
    }
    @Test(priority = 7,enabled = true)
    public void languagePaletteInEditDiscussionTabInInstructor(){

        try {
            //TC row no 544
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion=PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            String inputStringValue=null;
            int height=0;
            int height1=0;


            new LoginUsingLTI().ltiLogin("1187");//login as instructor
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TOCShow().tocHide();
            lessonPage.discussion_post.click();
            new Click().clickbyxpath("//div[@class='prow editdiscussion-wrapper']//i[@class='ls-icon-img ls--comments-icon']");


            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(),16,"All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue,"áÁéÉíÍñÑóÓúÚüÜ¡¿","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

            //TC row no 24
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_spanish.click(); //click on the spanish language
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
            Thread.sleep(3000);
            height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));
            manageContent.langaugePalette_saveButton.click(); //click on the save button


            //Tc row no 106
            if(height>height1){
                Assert.fail("The height of the question textbox is not  increased accordingly");
            }

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");
            Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿","All the characters is not getting added to the question content area.");


        } catch (Exception e) {
            Assert.fail("Exception in TC languagePaletteInEditDiscussionTabInInstructor of class CommentOnCourseStream ",e);
        }
    }
    @Test(priority = 8,enabled = true)
    public void addCommentToImageWidgetForItalian(){

        try {
            //TC row no 544
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            String inputStringValue=null;
            int height=0;
            int height1=0;


            new LoginUsingLTI().ltiLogin("1322_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TOCShow().tocHide();
            new UIElement().waitAndFindElement(lessonPage.expandImage);
            lessonPage.expandImage.click();
            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

            new LangaugePaletteForQuestionCreation().clickOnTheItalian();
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(),22,"All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue,"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(lessonPage.widgetComment_text.get(0).getText().trim(),"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","All the characters is not getting added to the question content area.");

            //TC row no 24

            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            new LangaugePaletteForQuestionCreation().clickOnTheItalian();
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
            Thread.sleep(3000);
            height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));
            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //Tc row no 106
            if(height>height1){
                Assert.fail("The height of the question textbox is not  increased accordingly");
            }

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheItalian();
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");
            Assert.assertEquals(lessonPage.widgetComment_text.get(0).getText().trim(),"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤ÀÀÀÀÀàààààÁÁÁÁÁáááááÈÈÈÈÈèèèèèÉÉÉÉÉéééééÌÌÌÌÌìììììÍÍÍÍÍíííííÒÒÒÒÒòòòòòÓÓÓÓÓóóóóóÙÙÙÙÙùùùùùÚÚÚÚÚúúúúú€€€€€₤₤₤₤₤","All the characters is not getting added to the question content area.");

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheItalian();

            Actions action=new Actions(driver);
            action.moveByOffset(10,-400).build().perform();
            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheItalian();

            lessonPage.widgetComment_text.get(0).sendKeys("abc123");
            Thread.sleep(2000);
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.widgetComment_text.get(0).getText().trim(),"abc123\nÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤ÀÀÀÀÀàààààÁÁÁÁÁáááááÈÈÈÈÈèèèèèÉÉÉÉÉéééééÌÌÌÌÌìììììÍÍÍÍÍíííííÒÒÒÒÒòòòòòÓÓÓÓÓóóóóóÙÙÙÙÙùùùùùÚÚÚÚÚúúúúú€€€€€₤₤₤₤₤","All the characters is not getting added to the question content area.");


        } catch (Exception e) {
            Assert.fail("Exception in TC addCommentToImageWidgetForItalian of class CommentOnCourseStream ",e);
        }
    }
    @Test(priority = 9,enabled = true)
    public void addCommentToImageWidgetForSpanish(){

        try {
            //TC row no 544
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            String inputStringValue=null;
            int height=0;
            int height1=0;


            new LoginUsingLTI().ltiLogin("1322_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TOCShow().tocHide();
            new UIElement().waitAndFindElement(lessonPage.expandImage);
            lessonPage.expandImage.click();
            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(),16,"All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue,"áÁéÉíÍñÑóÓúÚüÜ¡¿","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(lessonPage.widgetComment_text.get(0).getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

            //TC row no 24

            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
            Thread.sleep(3000);
            height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));
            manageContent.langaugePalette_saveButton.click(); //click on the save button


            //Tc row no 106
            if(height>height1){
                Assert.fail("The height of the question textbox is not  increased accordingly");
            }

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheItalian();
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");
            Assert.assertEquals(lessonPage.widgetComment_text.get(0).getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿áááááÁÁÁÁÁéééééÉÉÉÉÉíííííÍÍÍÍÍñññññÑÑÑÑÑóóóóóÓÓÓÓÓúúúúúÚÚÚÚÚüüüüüÜÜÜÜÜ¡¡¡¡¡¿¿¿¿¿","All the characters is not getting added to the question content area.");

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();

            Actions action=new Actions(driver);
            action.moveByOffset(10,200).build().perform();
            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();

            lessonPage.widgetComment_text.get(0).sendKeys("abc123");
            Thread.sleep(2000);
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.widgetComment_text.get(0).getText().trim(),"abc123\náÁéÉíÍñÑóÓúÚüÜ¡¿áááááÁÁÁÁÁéééééÉÉÉÉÉíííííÍÍÍÍÍñññññÑÑÑÑÑóóóóóÓÓÓÓÓúúúúúÚÚÚÚÚüüüüüÜÜÜÜÜ¡¡¡¡¡¿¿¿¿¿","All the characters is not getting added to the question content area.");


        } catch (Exception e) {
            Assert.fail("Exception in TC addCommentToImageWidgetForSpanish of class AddCommentInCSPage ",e);
        }
    }

    @Test(priority = 10,enabled = true)
    public void addCommentToAnimationWidget(){

        try {
            //TC row no 1385
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            TocSearch tocSearch =PageFactory.initElements(driver,TocSearch.class);

            String inputStringValue=null;
            int height=0;
            int height1=0;

            new LoginUsingLTI().ltiLogin("1322_1");//login as student
            new TOCShow().goToSearch("Animation");
            tocSearch.tocSearchList.get(0).click();
            new UIElement().waitAndFindElement(By.cssSelector("div[class='widget-name html5-widget-name']"));

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(),16,"All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue,"áÁéÉíÍñÑóÓúÚüÜ¡¿","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(lessonPage.widgetComment_text.get(6).getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

            //TC row no 24

            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
            Thread.sleep(3000);
            height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));
            manageContent.langaugePalette_saveButton.click(); //click on the save button


            //Tc row no 106
            if(height>height1){
                Assert.fail("The height of the question textbox is not  increased accordingly");
            }

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheItalian();
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");
            Assert.assertEquals(lessonPage.widgetComment_text.get(6).getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿áááááÁÁÁÁÁéééééÉÉÉÉÉíííííÍÍÍÍÍñññññÑÑÑÑÑóóóóóÓÓÓÓÓúúúúúÚÚÚÚÚüüüüüÜÜÜÜÜ¡¡¡¡¡¿¿¿¿¿","All the characters is not getting added to the question content area.");

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();

            Actions action=new Actions(driver);
            action.moveByOffset(20,-400).build().perform();
            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();

            lessonPage.widgetComment_text.get(6).sendKeys("abc123");
            Thread.sleep(2000);
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.widgetComment_text.get(6).getText().trim(),"abc123\náÁéÉíÍñÑóÓúÚüÜ¡¿áááááÁÁÁÁÁéééééÉÉÉÉÉíííííÍÍÍÍÍñññññÑÑÑÑÑóóóóóÓÓÓÓÓúúúúúÚÚÚÚÚüüüüüÜÜÜÜÜ¡¡¡¡¡¿¿¿¿¿","All the characters is not getting added to the question content area.");


        } catch (Exception e) {
            Assert.fail("Exception in TC addCommentToAnimationWidget of class CommentOnCourseStream ",e);
        }
    }

    @Test(priority = 11,enabled = true)
    public void assignAudioWidget(){

        try {
            //TC row no 1389
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            TocSearch tocSearch =PageFactory.initElements(driver,TocSearch.class);

            String inputStringValue=null;
            int height=0;
            int height1=0;

            new LoginUsingLTI().ltiLogin("1389");//login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("1A.10: Defining Europe: Difficult Choices");
            new UIElement().waitAndFindElement(lessonPage.play_button);
            new ScrollElement().scrollToViewOfElement(lessonPage.play_button);
            lessonPage.assignThisPop.get(4).click();
            lessonPage.assignThisText_link.click();
            new AssignLesson().assignTOCWithDefaultClassSection(1389);

            new LoginUsingLTI().ltiLogin("1389_1");//login as instructor
            new Assignment().openAssignmentFromAssignmentTab(0);

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(),16,"All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue,"áÁéÉíÍñÑóÓúÚüÜ¡¿","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(lessonPage.widgetComment_text.get(3).getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

            //TC row no 24

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
            Thread.sleep(3000);
            height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));
            manageContent.langaugePalette_saveButton.click(); //click on the save button


            //Tc row no 106
            if(height>height1){
                Assert.fail("The height of the question textbox is not  increased accordingly");
            }

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheItalian();
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");
            Assert.assertEquals(lessonPage.widgetComment_text.get(3).getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿áááááÁÁÁÁÁéééééÉÉÉÉÉíííííÍÍÍÍÍñññññÑÑÑÑÑóóóóóÓÓÓÓÓúúúúúÚÚÚÚÚüüüüüÜÜÜÜÜ¡¡¡¡¡¿¿¿¿¿","All the characters is not getting added to the question content area.");

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();

            System.out.println("x:"+manageContent.langaugePalette_header.getLocation().getX());
            System.out.println("y:"+manageContent.langaugePalette_header.getLocation().getY());

            Actions action=new Actions(driver);
            action.moveToElement(manageContent.langaugePalette_header).moveByOffset(420, -100).build().perform();
            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();

            lessonPage.widgetComment_text.get(3).sendKeys("abc123");
            Thread.sleep(2000);
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.widgetComment_text.get(3).getText().trim(),"abc123","All the characters is not getting added to the question content area.");


        } catch (Exception e) {
            Assert.fail("Exception in TC assignAudioWidget of class CommentOnCourseStream ",e);
        }
    }

    @Test(priority = 12,enabled = true)
    public void langaugePaletteForPerspective(){

        try {
            //TC row no 1428
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            TocSearch tocSearch =PageFactory.initElements(driver,TocSearch.class);

            String inputStringValue=null;
            int height=0;
            int height1=0;

            new LoginUsingLTI().ltiLogin("1428_1");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidgetForStudent();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(),16,"All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue,"áÁéÉíÍñÑóÓúÚüÜ¡¿","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(lessonPage.perspective_Comment.getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

            //TC row no 24

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
            Thread.sleep(3000);
            height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));
            manageContent.langaugePalette_saveButton.click(); //click on the save button


            //Tc row no 106
            if(height>height1){
                Assert.fail("The height of the question textbox is not  increased accordingly");
            }

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheItalian();
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");
            Assert.assertEquals(lessonPage.perspective_Comment.getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿áááááÁÁÁÁÁéééééÉÉÉÉÉíííííÍÍÍÍÍñññññÑÑÑÑÑóóóóóÓÓÓÓÓúúúúúÚÚÚÚÚüüüüüÜÜÜÜÜ¡¡¡¡¡¿¿¿¿¿","All the characters is not getting added to the question content area.");


            List<WebElement> perspectives = driver.findElements(By.name("perspective"));
            for(WebElement perspective : perspectives)
            {
                if(perspective.isDisplayed())
                {
                    new Click().clickByclassname("post-perspective");
                }
            }

            new LoginUsingLTI().ltiLogin("1428");//login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidgetForStudent();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
            Assert.assertEquals(lessonPage.perspective_CommentInStudent.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááÁÁÁÁÁéééééÉÉÉÉÉíííííÍÍÍÍÍñññññÑÑÑÑÑóóóóóÓÓÓÓÓúúúúúÚÚÚÚÚüüüüüÜÜÜÜÜ¡¡¡¡¡¿¿¿¿¿", "All the characters is not getting added to the question content area.");



        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForPerspective of class CommentOnCourseStream ",e);
        }
    }

    @Test(priority = 13,enabled = true)
    public void langaugePaletteFrenchForPerspectiveComment(){

        try {
            //TC row no 752
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            String inputStringValue=null;
            int height=0;
            int height1=0;

            new LoginUsingLTI().ltiLogin("1444_1");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

            new LangaugePaletteForQuestionCreation().clickOnTheFrench();
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"French palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(),24,"All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue,"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(lessonPage.perspective_Comments.getText().trim(),"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the characters is not getting added to the question content area.");

            //TC row no 24

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheFrench();
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
            Thread.sleep(3000);
            height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, manageContent.langaugePalette_textBox.getCssValue("height").lastIndexOf("px")));
            manageContent.langaugePalette_saveButton.click(); //click on the save button

            System.out.println("height:"+height);
            System.out.println("height1:"+height1);

            //Tc row no 106
            if(height>height1){
                Assert.fail("The height of the question textbox is not  increased accordingly");
            }

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheFrench();
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");
            Assert.assertEquals(lessonPage.perspective_Comments.getText().trim(),"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœÀÀÀÀÀàààààÂÂÂÂÂâââââÆÆÆÆÆæææææÇÇÇÇÇçççççÈÈÈÈÈèèèèèÉÉÉÉÉéééééÊÊÊÊÊêêêêêËËËËËëëëëëÎÎÎÎÎîîîîîÏÏÏÏÏïïïïïÔÔÔÔÔôôôôôŒŒŒŒŒœœœœœ","All the characters is not getting added to the question content area.");

            List<WebElement> perspectives =lessonPage.postPerspective_Comment;
            for(WebElement perspective : perspectives)
            {
                if(perspective.isDisplayed())
                {
                    perspective.click();
                }
            }
            new LoginUsingLTI().ltiLogin("1444");//login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidgetForStudent();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link

            lessonPage.perspective_PostCommentInStudent.get(3).click();
            Assert.assertEquals(lessonPage.postPerspective_CommentPosted.get(0).getText().trim(), "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœÀÀÀÀÀàààààÂÂÂÂÂâââââÆÆÆÆÆæææææÇÇÇÇÇçççççÈÈÈÈÈèèèèèÉÉÉÉÉéééééÊÊÊÊÊêêêêêËËËËËëëëëëÎÎÎÎÎîîîîîÏÏÏÏÏïïïïïÔÔÔÔÔôôôôôŒŒŒŒŒœœœœœ", "All the characters is not getting added to the question content area.");



        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteFrenchForPerspectiveComment of class AddCommentInCSPage ",e);
        }
    }

    @Test(priority = 14,enabled = true)
    public void enableAudioEnableLangaugePalette(){

        try {
            //TC row no 1449
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion=PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            Dashboard dashboard =PageFactory.initElements(driver,Dashboard.class);
            Profile profile=PageFactory.initElements(driver,Profile.class);

            new LoginUsingLTI().ltiLogin("1187");//login as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.getEnable_RadioButton().click();//click on enable;
            profile.getSave_Button().click();//click on save
            profile.getCloseIcon().click();//click on close

            new LoginUsingLTI().ltiLogin("1187_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TOCShow().tocHide();
            new UIElement().waitAndFindElement(discussion.newDiscussion_button);
            discussion.newDiscussion_button.click(); //click on the new discussion
            Assert.assertTrue(discussion.audio_icon.isDisplayed());

            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new UIElement().waitAndFindElement(courseStreamPage.commentLinkIn_CSPage.get(0));
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertTrue(courseStreamPage.getMicrophoneIcon().isDisplayed());

            new LoginUsingLTI().ltiLogin("1322_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TOCShow().tocHide();
            new UIElement().waitAndFindElement(lessonPage.expandImage);
            lessonPage.expandImage.click();
            Assert.assertEquals(new BooleanValue().isElementPresent(lessonPage.dw_audioIcon),true,"Audio icon is not displaying after language palatte icon");

            new UploadResources().uploadResources("663", false, false, true);//upload chapterlevel resource

            new LoginUsingLTI().ltiLogin("663_1");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToResourceTab();//navigate to resource tab
            new Navigator().openParticularResourceFromResourceTab(663);
            List<WebElement> allOpenLink = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-forward-bg']"));
            for(WebElement link:allOpenLink){
                if(link.isDisplayed()){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",link);
                }
            }
            new UIElement().waitAndFindElement(courseStreamPage.getMicrophoneIcon());
            Assert.assertTrue(courseStreamPage.getMicrophoneIcon().isDisplayed());


        } catch (Exception e) {
            Assert.fail("Exception in TC enableAudioEnableLangaugePalette of class AddCommentInCSPage ",e);
        }
    }


    @Test(priority = 15,enabled = true)
    public void enableAudioDisableLangaugePalette(){

        try {
            //TC row no 1449
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion=PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            Dashboard dashboard =PageFactory.initElements(driver,Dashboard.class);
            Profile profile=PageFactory.initElements(driver,Profile.class);

            new LoginUsingLTI().ltiLogin("1187");//login as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.getEnable_RadioButton().click();//click on enable;
            profile.getSave_Button().click();//click on save
            profile.getCloseIcon().click();//click on close

            new AssignmentPolicy().CMSLangaugePaletteDisable(1187);


            new LoginUsingLTI().ltiLogin("1187_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TOCShow().tocHide();
            new UIElement().waitAndFindElement(discussion.newDiscussion_button);
            discussion.newDiscussion_button.click(); //click on the new discussion
            Assert.assertTrue(discussion.audio_icon.isDisplayed());
            Assert.assertEquals(courseStreamPage.languagePaletteIcon_CSPage.size(), 0, "Language palette icon is displayed.");

            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new UIElement().waitAndFindElement(courseStreamPage.commentLinkIn_CSPage.get(0));
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertTrue(courseStreamPage.getMicrophoneIcon().isDisplayed());
            Assert.assertEquals(manageContent.langaugePalettes.size(), 0, "Language palette icon is displayed.");

            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TOCShow().tocHide();
            new UIElement().waitAndFindElement(lessonPage.expandImage);
            lessonPage.expandImage.click();
            Assert.assertEquals(new BooleanValue().isElementPresent(lessonPage.dw_audioIcon), true, "Audio icon is not displaying after language palatte icon");
            Assert.assertEquals(manageContent.langaugePalettes.size(), 0, "Language palette icon is displayed.");


            new LoginUsingLTI().ltiLogin("663");//login as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.getEnable_RadioButton().click();//click on enable;
            profile.getSave_Button().click();//click on save
            profile.getCloseIcon().click();//click on close
            new UploadResources().uploadResources("663", false, false, true);//upload chapterlevel resource
            new LoginUsingLTI().ltiLogin("663_1");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToResourceTab();//navigate to resource tab
            new Navigator().openParticularResourceFromResourceTab(663);
            List<WebElement> allOpenLink = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-forward-bg']"));
            for(WebElement link:allOpenLink){
                if(link.isDisplayed()){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",link);
                }
            }
            new UIElement().waitAndFindElement(courseStreamPage.getMicrophoneIcon());
            Assert.assertTrue(courseStreamPage.audioIconInResources.isDisplayed());
            Assert.assertEquals(manageContent.langaugePalettes.size(), 0, "Language palette icon is displayed.");


        } catch (Exception e) {
            Assert.fail("Exception in TC enableAudioDisableLangaugePalette of class AddCommentInCSPage ",e);
        }
    }


    @Test(priority = 16,enabled = true)
    public void disableAudioEnableLangaugePalette(){

        try {
            //TC row no 1449
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion=PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            Dashboard dashboard =PageFactory.initElements(driver,Dashboard.class);
            Profile profile=PageFactory.initElements(driver,Profile.class);

            new LoginUsingLTI().ltiLogin("1187");//login as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.getEnable_RadioButton().click();//click on disable;
            profile.getSave_Button().click();//click on save
            profile.getCloseIcon().click();//click on close

            new AssignmentPolicy().CMSLangaugePaletteEnable(1187);


            new LoginUsingLTI().ltiLogin("1187_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TOCShow().tocHide();
            new UIElement().waitAndFindElement(discussion.newDiscussion_button);
            discussion.newDiscussion_button.click(); //click on the new discussion
            Assert.assertEquals(new BooleanValue().presenceOfElement(1499, By.cssSelector("div[class='ls-post-comment-redactor-toolbar active-toolbar']+i")), false);
            Assert.assertEquals(courseStreamPage.languagePaletteIcon_CSPage.size(), 1, "Language palette icon is displayed.");

            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new UIElement().waitAndFindElement(courseStreamPage.commentLinkIn_CSPage.get(0));
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertEquals(new BooleanValue().presenceOfElement(1499,By.xpath("//div[@class='audio-content-recorder-icon']")), false);
            Assert.assertEquals(manageContent.langaugePalettes.size(), 1, "Language palette icon is displayed.");

            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TOCShow().tocHide();
            new UIElement().waitAndFindElement(lessonPage.expandImage);
            lessonPage.expandImage.click();
            Assert.assertEquals(new BooleanValue().presenceOfElement(1426,By.cssSelector("i[class='audio-content-recorder-icon performance-audio-feedback-icon']")), false, "Audio icon is  displaying after language palatte icon");
            Assert.assertEquals(manageContent.langaugePalettes.size(), 1, "Language palette icon is displayed.");


            new LoginUsingLTI().ltiLogin("663");//login as instructor
            dashboard.getProfileDropDown().click();//click on profile dropdown
            profile.getSettings().click();//click on setting
            profile.getEnable_RadioButton().click();//click on enable;
            profile.getSave_Button().click();//click on save
            profile.getCloseIcon().click();//click on close

            new LoginUsingLTI().ltiLogin("663_1");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToResourceTab();//navigate to resource tab
            new Navigator().openParticularResourceFromResourceTab(663);
            List<WebElement> allOpenLink = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-forward-bg']"));
            for(WebElement link:allOpenLink){
                if(link.isDisplayed()){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",link);
                }
            }

            Assert.assertEquals(new BooleanValue().presenceOfElement(1426,By.xpath("//*[@class='audio-content-recorder-icon']")), false, "Audio icon is  displaying after language palatte icon");
            Assert.assertEquals(manageContent.langaugePalettes.size(), 1, "Language palette icon is displayed.");


        } catch (Exception e) {
            Assert.fail("Exception in TC enableAudioDisableLangaugePalette of class AddCommentInCSPage ",e);
        }
    }

    @Test(priority = 17,enabled = true)
    public void disableAudioDisableLangaugePalette(){

        try {
            //TC row no 1449
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion=PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            Dashboard dashboard =PageFactory.initElements(driver,Dashboard.class);
            Profile profile=PageFactory.initElements(driver,Profile.class);

            new AssignmentPolicy().CMSLangaugePaletteDisable(1458);


            new LoginUsingLTI().ltiLogin("1187");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TOCShow().tocHide();
            new UIElement().waitAndFindElement(discussion.newDiscussion_button);
            discussion.newDiscussion_button.click(); //click on the new discussion
            Assert.assertEquals(new BooleanValue().presenceOfElement(1458, By.cssSelector("div[class='ls-post-comment-redactor-toolbar active-toolbar']+i")), false);
            Assert.assertEquals(courseStreamPage.languagePaletteIcon_CSPage.size(), 0, "Language palette icon is displayed.");

            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new UIElement().waitAndFindElement(courseStreamPage.commentLinkIn_CSPage.get(0));
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertEquals(new BooleanValue().presenceOfElement(1458,By.xpath("//div[@class='audio-content-recorder-icon']")), false);
            Assert.assertEquals(manageContent.langaugePalettes.size(), 0, "Language palette icon is displayed.");

            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TOCShow().tocHide();
            new UIElement().waitAndFindElement(lessonPage.expandImage);
            lessonPage.expandImage.click();
            Assert.assertEquals(new BooleanValue().presenceOfElement(1458,By.cssSelector("i[class='audio-content-recorder-icon performance-audio-feedback-icon']")), false, "Audio icon is  displaying after language palatte icon");
            Assert.assertEquals(manageContent.langaugePalettes.size(), 0, "Language palette icon is displayed.");


            new LoginUsingLTI().ltiLogin("663_1");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToResourceTab();//navigate to resource tab
            new Navigator().openParticularResourceFromResourceTab(663);
            List<WebElement> allOpenLink = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-forward-bg']"));
            for(WebElement link:allOpenLink){
                if(link.isDisplayed()){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",link);
                }
            }

            Assert.assertEquals(new BooleanValue().presenceOfElement(1426,By.xpath("//*[@class='audio-content-recorder-icon']")), false, "Audio icon is  displaying after language palatte icon");
            Assert.assertEquals(manageContent.langaugePalettes.size(), 0, "Language palette icon is displayed.");


        } catch (Exception e) {
            Assert.fail("Exception in TC disableAudioDisableLangaugePalette of class AddCommentInCSPage ",e);
        }
    }




}
