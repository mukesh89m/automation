package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R237;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Discussion;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 10/6/2015.
 */
public class AddCommentInCSPageForLs extends  Driver {

        @Test(priority = 1, enabled = true)
        public void addCommentInCSPage() {

            try {
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);

                new DirectLogin().CMSLogin();
                new SelectCourse().selectLSCourse();
                courseOutline.courseDetails.click(); //click on the courseDetails icon
                Thread.sleep(3000);
                driver.findElement(By.xpath("//a[@rel='3']")).click();
                Thread.sleep(3000);
                courseOutline.enable_radioButtonForLanguagePalette.click();//click on enable language palette radio button
                courseOutline.save_button.click();
                new LoginUsingLTI().ltiLogin("471_1");//login as student
                new LoginUsingLTI().ltiLogin("471"); //login as instructor
                new Assignment().create(471); //create assignment
                new LoginUsingLTI().ltiLogin("471");//login as instructor
                new Assignment().assignToStudent(471); //assign to the student
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                verificationForSpanishInCSPage();
                courseStreamPage.post_Link.get(0).click();


            } catch (Exception e) {
                Assert.fail("Exception in TC addCommentInCSPage of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 2, enabled = true)
        public void addCommentForItalianInCSPage() {

            try {
                new LoginUsingLTI().ltiLogin("471");//login as instructor
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                verificationForItalianInCSPage();

            } catch (Exception e) {
                Assert.fail("Exception in TC addCommentForItalianInCSPage of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 3, enabled = true)
        public void addCommentForFrenchInCSPage() {

            try {

                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                new LoginUsingLTI().ltiLogin("471");//login as instructor
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                verificationForFrenchInCSPage();

                new LoginUsingLTI().ltiLogin("471_1");//login as student
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
                Assert.assertTrue(courseStreamPage.postComment_content.get(0).getText().trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿"), "All the characters is not getting added to the question content area.");


                courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

                manageContent.langaugePalette_spanish.click(); //click on the spanish language
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                manageContent.langaugePalette_saveButton.click(); //click on the save button
                courseStreamPage.post_Link.get(0).click();
                new LoginUsingLTI().ltiLogin("471");//login as instructor
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
                Assert.assertTrue(courseStreamPage.postComment_content.get(1).getText().trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿"), "All the Comments added by the Student is not displaying at the instructor side.");


            } catch (Exception e) {
                Assert.fail("Exception in TC addCommentForFrenchInCSPage of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 4, enabled = true)
        public void commentOnPostForSpanish() {

            try {
                //TC row no 486
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                new LoginUsingLTI().ltiLogin("486_1");//login as student
                new LoginUsingLTI().ltiLogin("486");//login as instructor
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                String message = new RandomString().randomstring(4);
                new PostMessage().postmessage(message);
                boolean postfound = new PostMessageValidate().postMessageValidateForInstructor(message);
                if (postfound == false) {
                    Assert.fail("Instructor post is not displyed in Course stream.");
                }
                new UIElement().waitAndFindElement(courseStreamPage.commentLinkIn_CSPage.get(0));
                courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                manageContent.langaugePalette_spanish.click(); //click on the spanish language
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                int height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_spanish.click(); //click on the spanish language

                Actions action = new Actions(driver);
                WebElement From = driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
                action.dragAndDrop(manageContent.langaugePalette_header, From).click().build().perform();
                driver.findElement(By.xpath("//html/body")).click();

                courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_spanish.click(); //click on the spanish language

                manageContent.langaugePalette_textBox.sendKeys("abc123");
                Thread.sleep(2000);
                courseStreamPage.langaugePalette_saveButton.click();//click on save
                Thread.sleep(2000);
                Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(), "abc123\náÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");


            } catch (Exception e) {
                Assert.fail("Exception in TC commentOnPostForSpanish of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 5, enabled = true)
        public void commentOnPostForItalian() {

            try {
                //TC row no 486
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                new LoginUsingLTI().ltiLogin("486");//login as instructor
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                new UIElement().waitAndFindElement(courseStreamPage.commentLinkIn_CSPage.get(0));
                courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                manageContent.langaugePalette_Italian.click(); //click on the spanish language
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                // driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 22, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Italian Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                int height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "All the characters is not getting added to the question content area.");

                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_Italian.click(); //click on the spanish language

                Actions action = new Actions(driver);
                WebElement From = driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
                action.dragAndDrop(manageContent.langaugePalette_header, From).click().build().perform();
                driver.findElement(By.xpath("//html/body")).click();

                courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_Italian.click(); //click on the spanish language

                manageContent.langaugePalette_textBox.sendKeys("abc123");
                Thread.sleep(2000);
                courseStreamPage.langaugePalette_saveButton.click();//click on save
                Thread.sleep(2000);
                Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(), "abc123\nÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "All the characters is not getting added to the question content area.");


            } catch (Exception e) {
                Assert.fail("Exception in TC commentOnPostForItalian of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 6, enabled = true)
        public void commentOnPostForFrench() {

            try {
                //TC row no 486
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                new LoginUsingLTI().ltiLogin("486");//login as instructor
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                new UIElement().waitAndFindElement(courseStreamPage.commentLinkIn_CSPage.get(0));
                courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                manageContent.langaugePalette_French.click(); //click on the spanish language
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 24, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"French Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                int height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(), "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "All the characters is not getting added to the question content area.");

                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_French.click(); //click on the spanish language

                Actions action = new Actions(driver);
                WebElement From = driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
                action.dragAndDrop(manageContent.langaugePalette_header, From).click().build().perform();
                driver.findElement(By.xpath("//html/body")).click();

                courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_French.click(); //click on the spanish language

                manageContent.langaugePalette_textBox.sendKeys("abc123");
                Thread.sleep(2000);
                courseStreamPage.langaugePalette_saveButton.click();//click on save
                Thread.sleep(2000);
                Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(), "abc123\nÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "All the characters is not getting added to the question content area.");

                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_French.click(); //click on the spanish language
                manageContent.langaugePalette_cancelButton.click();
                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");

                courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

                manageContent.langaugePalette_French.click(); //click on the spanish language
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "French palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                courseStreamPage.langaugePalette_saveButton.click();//click on save
                courseStreamPage.post_Link.get(0).click();
                Assert.assertTrue(courseStreamPage.postComment_content.get(0).getText().trim().contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœabc123ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"), "All the Comments added by the Student is not displaying at the instructor side.");


            } catch (Exception e) {
                Assert.fail("Exception in TC commentOnPostForFrench of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 7, enabled = true)
        public void languagePaletteInDiscussionTab() {

            try {
                //TC row no 538
                com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                new LoginUsingLTI().ltiLogin("538");//login as instructor
                new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
                String inputStringValue = null;
                int height = 0;
                int height1 = 0;

                new TOCShow().tocHide();
                new UIElement().waitAndFindElement(discussion.newDiscussion_button);
                discussion.newDiscussion_button.click(); //click on the new discussion
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                manageContent.langaugePalette_spanish.click(); //click on the spanish language
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(discussion.discussion_text.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                //TC row no 24
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_spanish.click(); //click on the spanish language
                for (WebElement element : manageContent.langaugePalette_allinput) {
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
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button
                discussion.submit_button.click(); //click on the save button

                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LoginUsingLTI().ltiLogin("538");//login as instructor
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
                Assert.assertTrue(courseStreamPage.courseStream_content.getText().trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿"), "All the Comments added by the Student is not displaying at the instructor side.");
                verificationForSpanishInCSPage();


                new LoginUsingLTI().ltiLogin("538");//login as instructor
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                courseStreamPage.getJumpOut().click();//click on the jump out icon
                Thread.sleep(9000);
                new Click().clickbyxpath("//div[@class='prow editdiscussion-wrapper']//i[@class='ls-icon-img ls--comments-icon']");
                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                //TC row no 24
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_spanish.click(); //click on the spanish language
                for (WebElement element : manageContent.langaugePalette_allinput) {
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
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿", "All the characters is not getting added to the question content area.");


            } catch (Exception e) {
                Assert.fail("Exception in TC languagePaletteInDiscussionTab of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 8, enabled = true)
        public void languagePaletteInDiscussionTabInStudentSide() {

            try {
                //TC row no 544
                com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                String inputStringValue = null;
                int height = 0;
                int height1 = 0;


                new LoginUsingLTI().ltiLogin("538_1");//login as Student
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                courseStreamPage.getJumpOut().click();//click on the jump out icon
                Thread.sleep(9000);
                new Click().clickbyxpath("//div[@class='prow editdiscussion-wrapper']//i[@class='ls-icon-img ls--comments-icon']");
                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                //TC row no 24
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_spanish.click(); //click on the spanish language
                for (WebElement element : manageContent.langaugePalette_allinput) {
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
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿", "All the characters is not getting added to the question content area.");


            } catch (Exception e) {
                Assert.fail("Exception in TC languagePaletteInDiscussionTabInStudentSide of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 9, enabled = true)
        public void languagePaletteAfterHighlightingText() {

            try {
                //TC row no 544
                com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
                LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                new LoginUsingLTI().ltiLogin("554");//login as second instructor
                new LoginUsingLTI().ltiLogin("553_1");//login as student

                String inputStringValue = null;
                int height = 0;
                int height1 = 0;
                new LoginUsingLTI().ltiLogin("553");//login as student
                new Navigator().NavigateTo("eTextbook");
                new TOCShow().tocHide();
                Thread.sleep(6000);
                Actions actions = new Actions(driver);
                new CloseHelpPage().closehelppage();
                actions.moveToElement(driver.findElement(By.cssSelector(".noindent.default")), 5, 20)
                        .clickAndHold()
                        .moveByOffset(50, 50)
                        .release()
                        .perform();
                List<WebElement> allLink = lessonPage.addDiscussionLink_afterHighlight;
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allLink.get(2)); //click on the addDiscussion

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(lessonPage.addDiscussion_Text.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                //TC row no 24
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_spanish.click(); //click on the spanish language
                for (WebElement element : manageContent.langaugePalette_allinput) {
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
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(lessonPage.addDiscussion_Text.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿", "All the characters is not getting added to the question content area.");

                lessonPage.submit_Button.click(); //click on the submit

                new LoginUsingLTI().ltiLogin("553");//login as instructor
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
                Assert.assertTrue(courseStreamPage.courseStream_content.getText().trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿"), "All the Comments added by the Student is not displaying at the instructor side.");
                verificationForSpanishInCSPage();

                new LoginUsingLTI().ltiLogin("553");//login as instructor
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                courseStreamPage.getJumpOut().click();//click on the jump out icon
                Thread.sleep(9000);
                lessonPage.discussion_icon.click();
                new Click().clickbyxpath("//div[@class='prow editdiscussion-wrapper']//i[@class='ls-icon-img ls--comments-icon']");
                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                //TC row no 24
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_spanish.click(); //click on the spanish language
                for (WebElement element : manageContent.langaugePalette_allinput) {
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
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿", "All the characters is not getting added to the question content area.");


            } catch (Exception e) {
                Assert.fail("Exception in TC languagePaletteAfterHighlightingText of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 10, enabled = true)
        public void languagePaletteAfterHighlightingTextForOtherInstructor() {

            try {
                //TC row no 544
                com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
                LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                String inputStringValue = null;
                int height = 0;
                int height1 = 0;

                new LoginUsingLTI().ltiLogin("554");//login as instructor
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                courseStreamPage.getJumpOut().click();//click on the jump out icon
                Thread.sleep(9000);
                lessonPage.discussion_icon.click();
                new Click().clickbyxpath("//div[@class='prow editdiscussion-wrapper']//i[@class='ls-icon-img ls--comments-icon']");
                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                //TC row no 24
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_spanish.click(); //click on the spanish language
                for (WebElement element : manageContent.langaugePalette_allinput) {
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
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿", "All the characters is not getting added to the question content area.");


            } catch (Exception e) {
                Assert.fail("Exception in TC languagePaletteAfterHighlightingTextForOtherInstructor of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 10, enabled = true)
        public void languagePaletteAfterHighlightingTextForStudent() {

            try {
                //TC row no 544
                com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
                LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

                String inputStringValue = null;
                int height = 0;
                int height1 = 0;

                new LoginUsingLTI().ltiLogin("553_1");//login as student
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                courseStreamPage.getJumpOut().click();//click on the jump out icon
                Thread.sleep(9000);
                lessonPage.discussion_icon.click();
                new Click().clickbyxpath("//div[@class='prow editdiscussion-wrapper']//i[@class='ls-icon-img ls--comments-icon']");
                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                //TC row no 24
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_spanish.click(); //click on the spanish language
                for (WebElement element : manageContent.langaugePalette_allinput) {
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
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿", "All the characters is not getting added to the question content area.");


            } catch (Exception e) {
                Assert.fail("Exception in TC languagePaletteAfterHighlightingTextForStudent of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 11, enabled = true)
        public void languagePaletteAfterHighlightingTextForMentor() {

            try {
                //TC row no 544
                com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
                LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

                String inputStringValue = null;
                int height = 0;
                int height1 = 0;

                new LoginUsingLTI().ltiLogin("585");//login as student
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                courseStreamPage.getJumpOut().click();//click on the jump out icon
                Thread.sleep(9000);
                lessonPage.discussion_icon.click();
                new Click().clickbyxpath("//div[@class='prow editdiscussion-wrapper']//i[@class='ls-icon-img ls--comments-icon']");
                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                //TC row no 24
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_spanish.click(); //click on the spanish language
                for (WebElement element : manageContent.langaugePalette_allinput) {
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
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿", "All the characters is not getting added to the question content area.");

            } catch (Exception e) {
                Assert.fail("Exception in TC languagePaletteAfterHighlightingTextForMentor of class AddCommentInCSPage ", e);
            }
        }


        @Test(priority = 12, enabled = true)
        public void languagePaletteInEditDiscussionTab() {

            try {
                //TC row no 538
                com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
                String inputStringValue = null;
                int height = 0;
                int height1 = 0;

                new LoginUsingLTI().ltiLogin("538");//login as instructor
                new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
                new TOCShow().tocHide();
                lessonPage.discussion_icon.click();
                new Click().clickbyxpath("//div[@class='prow editdiscussion-wrapper']//i[@class='ls-icon-img ls--comments-icon']");

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(discussion.discussion_text.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                //TC row no 24
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_spanish.click(); //click on the spanish language
                for (WebElement element : manageContent.langaugePalette_allinput) {
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
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button
                discussion.submit_button.click(); //click on the save button

                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LoginUsingLTI().ltiLogin("538");//login as instructor
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
                Assert.assertTrue(courseStreamPage.courseStream_content.getText().trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿"), "All the Comments added by the Student is not displaying at the instructor side.");
                verificationForSpanishInCSPage();


                new LoginUsingLTI().ltiLogin("538");//login as instructor
                new Navigator().NavigateTo("Course Stream"); //navigate to course stream
                courseStreamPage.getJumpOut().click();//click on the jump out icon
                Thread.sleep(9000);
                new Click().clickbyxpath("//div[@class='prow editdiscussion-wrapper']//i[@class='ls-icon-img ls--comments-icon']");
                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                //TC row no 24
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_spanish.click(); //click on the spanish language
                for (WebElement element : manageContent.langaugePalette_allinput) {
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
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿", "All the characters is not getting added to the question content area.");


            } catch (Exception e) {
                Assert.fail("Exception in TC languagePaletteInEditDiscussionTab of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 13, enabled = true)
        public void languagePaletteInEditDiscussionTabInStudentSide() {

            try {
                //TC row no 544
                com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

                String inputStringValue = null;
                int height = 0;
                int height1 = 0;


                new LoginUsingLTI().ltiLogin("538_1");//login as instructor
                new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
                new TOCShow().tocHide();
                lessonPage.discussion_post.click();
                lessonPage.discussionPostEdit_link.click();
                discussion.discussion_text.clear();

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                //TC row no 24
                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                manageContent.langaugePalette_spanish.click(); //click on the spanish language
                for (WebElement element : manageContent.langaugePalette_allinput) {
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
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿", "All the characters is not getting added to the question content area.");


            } catch (Exception e) {
                Assert.fail("Exception in TC languagePaletteInEditDiscussionTabInStudentSide of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 14, enabled = true)
        public void addCommentToImageWidgetForItalian() {

            try {
                //TC row no 544
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

                String inputStringValue = null;
                int height = 0;
                int height1 = 0;


                new LoginUsingLTI().ltiLogin("538");//login as instructor
                new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
                new TOCShow().tocHide();
                new UIElement().waitAndFindElement(lessonPage.expandImage);
                lessonPage.expandImage.click();
                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheItalian();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 22, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(lessonPage.widgetComment_text.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "All the characters is not getting added to the question content area.");

                //TC row no 24

                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                new LangaugePaletteForQuestionCreation().clickOnTheItalian();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                }
                Thread.sleep(3000);
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheItalian();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(lessonPage.widgetComment_text.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤ÀÀÀÀÀàààààÁÁÁÁÁáááááÈÈÈÈÈèèèèèÉÉÉÉÉéééééÌÌÌÌÌìììììÍÍÍÍÍíííííÒÒÒÒÒòòòòòÓÓÓÓÓóóóóóÙÙÙÙÙùùùùùÚÚÚÚÚúúúúú€€€€€₤₤₤₤₤", "All the characters is not getting added to the question content area.");

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheItalian();

                Actions action = new Actions(driver);
                action.moveByOffset(10, -400).build().perform();
                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheItalian();

                lessonPage.widgetComment_text.get(0).sendKeys("abc123");
                Thread.sleep(2000);
                courseStreamPage.langaugePalette_saveButton.click();//click on save
                Thread.sleep(2000);
                Assert.assertEquals(lessonPage.widgetComment_text.get(0).getText().trim(), "abc123", "All the characters is not getting added to the question content area.");


            } catch (Exception e) {
                Assert.fail("Exception in TC addCommentToImageWidgetForItalian of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 15, enabled = true)
        public void addCommentToImageWidgetForSpanish() {

            try {
                //TC row no 544
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

                String inputStringValue = null;
                int height = 0;
                int height1 = 0;


                new LoginUsingLTI().ltiLogin("538");//login as instructor
                new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
                new TOCShow().tocHide();
                new UIElement().waitAndFindElement(lessonPage.expandImage);
                lessonPage.expandImage.click();
                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(lessonPage.widgetComment_text.get(0).getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                //TC row no 24

                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                }
                Thread.sleep(3000);
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheItalian();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(lessonPage.widgetComment_text.get(0).getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááÁÁÁÁÁéééééÉÉÉÉÉíííííÍÍÍÍÍñññññÑÑÑÑÑóóóóóÓÓÓÓÓúúúúúÚÚÚÚÚüüüüüÜÜÜÜÜ¡¡¡¡¡¿¿¿¿¿", "All the characters is not getting added to the question content area.");

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();

                Actions action = new Actions(driver);
                action.moveByOffset(10, 200).build().perform();
                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();

                lessonPage.widgetComment_text.get(0).sendKeys("abc123");
                Thread.sleep(2000);
                courseStreamPage.langaugePalette_saveButton.click();//click on save
                Thread.sleep(2000);
                Assert.assertEquals(lessonPage.widgetComment_text.get(0).getText().trim(), "abc123", "All the characters is not getting added to the question content area.");


            } catch (Exception e) {
                Assert.fail("Exception in TC addCommentToImageWidgetForSpanish of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 16, enabled = true)
        public void addCommentToAnimationWidget() {

            try {
                //TC row no 544
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
                TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);

                String inputStringValue = null;
                int height = 0;
                int height1 = 0;

                new LoginUsingLTI().ltiLogin("538");//login as instructor
                new TOCShow().goToSearch("Animation");
                tocSearch.tocSearchList.get(0).click();
                new UIElement().waitAndFindElement(By.cssSelector("div[class='widget-name html5-widget-name']"));

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(lessonPage.widgetComment_text.get(6).getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                //TC row no 24

                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                }
                Thread.sleep(3000);
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheItalian();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(lessonPage.widgetComment_text.get(6).getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááÁÁÁÁÁéééééÉÉÉÉÉíííííÍÍÍÍÍñññññÑÑÑÑÑóóóóóÓÓÓÓÓúúúúúÚÚÚÚÚüüüüüÜÜÜÜÜ¡¡¡¡¡¿¿¿¿¿", "All the characters is not getting added to the question content area.");

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();

                Actions action = new Actions(driver);
                action.moveByOffset(20, -400).build().perform();
                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();

                lessonPage.widgetComment_text.get(6).sendKeys("abc123");
                Thread.sleep(2000);
                courseStreamPage.langaugePalette_saveButton.click();//click on save
                Thread.sleep(2000);
                Assert.assertEquals(lessonPage.widgetComment_text.get(6).getText().trim(), "abc123\náÁéÉíÍñÑóÓúÚüÜ¡¿áááááÁÁÁÁÁéééééÉÉÉÉÉíííííÍÍÍÍÍñññññÑÑÑÑÑóóóóóÓÓÓÓÓúúúúúÚÚÚÚÚüüüüüÜÜÜÜÜ¡¡¡¡¡¿¿¿¿¿", "All the characters is not getting added to the question content area.");


            } catch (Exception e) {
                Assert.fail("Exception in TC addCommentToAnimationWidget of class AddCommentInCSPage ", e);
            }
        }


        @Test(priority = 17, enabled = true)
        public void assignAudioWidget() {

            try {
                //TC row no 731
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
                TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);

                String inputStringValue = null;
                int height = 0;
                int height1 = 0;

                new LoginUsingLTI().ltiLogin("726");//login as instructor
                new Navigator().NavigateTo("e-Textbook");
                new TopicOpen().chapterOpen(1);
                new SelectCourse().selectInvisibleAssignment("1A.10: Defining Europe: Difficult Choices");
                new UIElement().waitAndFindElement(lessonPage.play_button);
                new ScrollElement().scrollToViewOfElement(lessonPage.play_button);
                lessonPage.assignThisPop.get(4).click();
                lessonPage.assignThisText_link.click();
                new AssignLesson().assignTOCWithDefaultClassSection(726);
                new Assignment().openAssignmentFromAssignmentTab(0);

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(3).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(4).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(5).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(lessonPage.widgetComment_text.get(5).getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                //TC row no 24

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                }
                Thread.sleep(3000);
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheItalian();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(lessonPage.widgetComment_text.get(5).getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááÁÁÁÁÁéééééÉÉÉÉÉíííííÍÍÍÍÍñññññÑÑÑÑÑóóóóóÓÓÓÓÓúúúúúÚÚÚÚÚüüüüüÜÜÜÜÜ¡¡¡¡¡¿¿¿¿¿", "All the characters is not getting added to the question content area.");

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();

                Actions action = new Actions(driver);
                action.moveByOffset(10, -400).build().perform();
                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();

                lessonPage.widgetComment_text.get(5).sendKeys("abc123");
                Thread.sleep(2000);
                courseStreamPage.langaugePalette_saveButton.click();//click on save
                Thread.sleep(2000);
                Assert.assertEquals(lessonPage.widgetComment_text.get(5).getText().trim(), "abc123\náÁéÉíÍñÑóÓúÚüÜ¡¿áááááÁÁÁÁÁéééééÉÉÉÉÉíííííÍÍÍÍÍñññññÑÑÑÑÑóóóóóÓÓÓÓÓúúúúúÚÚÚÚÚüüüüüÜÜÜÜÜ¡¡¡¡¡¿¿¿¿¿", "All the characters is not getting added to the question content area.");


            } catch (Exception e) {
                Assert.fail("Exception in TC assignAudioWidget of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 18, enabled = true)
        public void assignImageWidget() {

            try {
                //TC row no 726
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
                TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);

                String inputStringValue = null;
                int height = 0;
                int height1 = 0;

                new LoginUsingLTI().ltiLogin("731");//login as instructor
                new Navigator().NavigateTo("e-Textbook");
                new TOCShow().tocHide();  //hide the TOC
                new AssignLesson().assignImageWidget("731");
                new Assignment().openAssignmentFromAssignmentTab(0);
                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(lessonPage.widgetComment_text.get(3).getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                //TC row no 24

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                for (WebElement element : manageContent.langaugePalette_allinput) {
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
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheItalian();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(lessonPage.widgetComment_text.get(3).getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿", "All the characters is not getting added to the question content area.");

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();

                Actions action = new Actions(driver);
                WebElement from = driver.findElement(By.className("resource-title-header"));
                action.dragAndDrop(manageContent.langaugePalette_header, from).build().perform();
                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();

                lessonPage.widgetComment_text.get(3).sendKeys("abc123");
                Thread.sleep(2000);
                courseStreamPage.langaugePalette_saveButton.click();//click on save
                Thread.sleep(2000);
                Assert.assertEquals(lessonPage.widgetComment_text.get(3).getText().trim(), "abc123\náÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿", "All the characters is not getting added to the question content area.");


            } catch (Exception e) {
                Assert.fail("Exception in TC assignImageWidget of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 19, enabled = true)
        public void assignAnimationWidget() {

            try {
                //TC row no 544
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
                TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);

                String inputStringValue = null;
                int height = 0;
                int height1 = 0;

                new LoginUsingLTI().ltiLogin("728");//login as instructor
                new TOCShow().goToSearch("Animation");
                tocSearch.tocSearchList.get(0).click();
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", lessonPage.assignThisPop.get(2));
                lessonPage.assignThisText_link.click();
                new AssignLesson().assignTOCWithDefaultClassSection(728);
                new Assignment().openAssignmentFromAssignmentTab(0);

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(lessonPage.widgetComment_text.get(7).getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                //TC row no 24

                courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                }
                Thread.sleep(3000);
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheItalian();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(lessonPage.widgetComment_text.get(7).getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááÁÁÁÁÁéééééÉÉÉÉÉíííííÍÍÍÍÍñññññÑÑÑÑÑóóóóóÓÓÓÓÓúúúúúÚÚÚÚÚüüüüüÜÜÜÜÜ¡¡¡¡¡¿¿¿¿¿", "All the characters is not getting added to the question content area.");

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();

                Actions action = new Actions(driver);
                WebElement from = driver.findElement(By.className("resource-title-header"));
                action.dragAndDrop(manageContent.langaugePalette_header, from).build().perform();
                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();

                lessonPage.widgetComment_text.get(9).sendKeys("abc123");
                Thread.sleep(2000);
                courseStreamPage.langaugePalette_saveButton.click();//click on save
                Thread.sleep(2000);
                Assert.assertEquals(lessonPage.widgetComment_text.get(7).getText().trim(), "abc123", "All the characters is not getting added to the question content area.");


            } catch (Exception e) {
                Assert.fail("Exception in TC assignAnimationWidget of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 20, enabled = true)
        public void langaugePaletteForPerspective() {

            try {
                //TC row no 544
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
                TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);

                String inputStringValue = null;
                int height = 0;
                int height1 = 0;

                new LoginUsingLTI().ltiLogin("728");//login as instructor
                new Navigator().NavigateTo("eTextbook");
                new TopicOpen().chapterOpen(15);
                new SelectCourse().selectInvisibleAssignment("8A.3: South Asia’s Physiography");

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                Thread.sleep(2000);
                Assert.assertEquals(manageContent.langaugePalette_option.get(3).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(4).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(5).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(lessonPage.perspective_Comment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

                //TC row no 24

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                }
                Thread.sleep(3000);
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheItalian();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(lessonPage.perspective_Comment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááÁÁÁÁÁéééééÉÉÉÉÉíííííÍÍÍÍÍñññññÑÑÑÑÑóóóóóÓÓÓÓÓúúúúúÚÚÚÚÚüüüüüÜÜÜÜÜ¡¡¡¡¡¿¿¿¿¿", "All the characters is not getting added to the question content area.");


                List<WebElement> perspectives = driver.findElements(By.name("perspective"));
                for (WebElement perspective : perspectives) {
                    if (perspective.isDisplayed()) {
                        new Click().clickByclassname("post-perspective");
                    }
                }
                new LoginUsingLTI().ltiLogin("728_1");//login as student
                new Navigator().NavigateTo("eTextbook");
                new TopicOpen().chapterOpen(15);
                new SelectCourse().selectInvisibleAssignment("8A.3: South Asia’s Physiography");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
                Assert.assertEquals(lessonPage.perspective_CommentInStudent.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááÁÁÁÁÁéééééÉÉÉÉÉíííííÍÍÍÍÍñññññÑÑÑÑÑóóóóóÓÓÓÓÓúúúúúÚÚÚÚÚüüüüüÜÜÜÜÜ¡¡¡¡¡¿¿¿¿¿", "All the characters is not getting added to the question content area.");


                ;

            } catch (Exception e) {
                Assert.fail("Exception in TC langaugePaletteForPerspective of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 21, enabled = true)
        public void langaugePaletteFrenchForPerspective() {

            try {
                //TC row no 752
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

                String inputStringValue = null;
                int height = 0;
                int height1 = 0;

                new LoginUsingLTI().ltiLogin("752");//login as instructor
                new Navigator().NavigateTo("eTextbook");
                new TopicOpen().chapterOpen(15);
                new SelectCourse().selectInvisibleAssignment("8A.3: South Asia’s Physiography");

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(3).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(4).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(5).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheFrench();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "French palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 24, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(lessonPage.perspective_Comment.getText().trim(), "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "All the characters is not getting added to the question content area.");

                //TC row no 24

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheFrench();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                }
                Thread.sleep(3000);
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheFrench();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(lessonPage.perspective_Comment.getText().trim(), "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœÀÀÀÀÀàààààÂÂÂÂÂâââââÆÆÆÆÆæææææÇÇÇÇÇçççççÈÈÈÈÈèèèèèÉÉÉÉÉéééééÊÊÊÊÊêêêêêËËËËËëëëëëÎÎÎÎÎîîîîîÏÏÏÏÏïïïïïÔÔÔÔÔôôôôôŒŒŒŒŒœœœœœ", "All the characters is not getting added to the question content area.");


                List<WebElement> perspectives = driver.findElements(By.name("perspective"));
                for (WebElement perspective : perspectives) {
                    if (perspective.isDisplayed()) {
                        new Click().clickByclassname("post-perspective");
                    }
                }

                new LoginUsingLTI().ltiLogin("752_1");//login as student
                new Navigator().NavigateTo("e-Textbook");
                new TopicOpen().openLessonWithDiscussionWidgetForStudent();
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
                Assert.assertEquals(lessonPage.perspective_CommentInStudent.getText().trim(), "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœÀÀÀÀÀàààààÂÂÂÂÂâââââÆÆÆÆÆæææææÇÇÇÇÇçççççÈÈÈÈÈèèèèèÉÉÉÉÉéééééÊÊÊÊÊêêêêêËËËËËëëëëëÎÎÎÎÎîîîîîÏÏÏÏÏïïïïïÔÔÔÔÔôôôôôŒŒŒŒŒœœœœœ", "All the characters is not getting added to the question content area.");


                ;

            } catch (Exception e) {
                Assert.fail("Exception in TC langaugePaletteFrenchForPerspective of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 22, enabled = true)
        public void langaugePaletteFrenchForPerspectiveComment() {

            try {
                //TC row no 752
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

                String inputStringValue = null;
                int height = 0;
                int height1 = 0;

                new LoginUsingLTI().ltiLogin("752");//login as instructor
                new Navigator().NavigateTo("eTextbook");
                new TopicOpen().chapterOpen(15);
                new SelectCourse().selectInvisibleAssignment("8A.3: South Asia’s Physiography");

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link
                List<WebElement> commentlink = driver.findElements(By.cssSelector("a[class='ls-content-post__footer-comment-link js-toggle-comments']"));
                commentlink.get(0).click();
                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheFrench();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "French palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 24, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(lessonPage.perspective_Comments.getText().trim(), "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "All the characters is not getting added to the question content area.");

                //TC row no 24

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheFrench();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                }
                Thread.sleep(3000);
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheFrench();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(lessonPage.perspective_Comments.getText().trim(), "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœÀÀÀÀÀàààààÂÂÂÂÂâââââÆÆÆÆÆæææææÇÇÇÇÇçççççÈÈÈÈÈèèèèèÉÉÉÉÉéééééÊÊÊÊÊêêêêêËËËËËëëëëëÎÎÎÎÎîîîîîÏÏÏÏÏïïïïïÔÔÔÔÔôôôôôŒŒŒŒŒœœœœœ", "All the characters is not getting added to the question content area.");

                List<WebElement> perspectives = lessonPage.postPerspective_Comment;
                for (WebElement perspective : perspectives) {
                    if (perspective.isDisplayed()) {
                        perspective.click();
                    }
                }
                new LoginUsingLTI().ltiLogin("752_1");//login as student
                new Navigator().NavigateTo("eTextbook");
                new TopicOpen().chapterOpen(15);
                new SelectCourse().selectInvisibleAssignment("8A.3: South Asia’s Physiography");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("Perspectives"))); //clicking on perspective link

                lessonPage.perspective_PostCommentInStudent.get(3).click();
                Assert.assertEquals(lessonPage.postPerspective_CommentPosted.get(0).getText().trim(), "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœÀÀÀÀÀàààààÂÂÂÂÂâââââÆÆÆÆÆæææææÇÇÇÇÇçççççÈÈÈÈÈèèèèèÉÉÉÉÉéééééÊÊÊÊÊêêêêêËËËËËëëëëëÎÎÎÎÎîîîîîÏÏÏÏÏïïïïïÔÔÔÔÔôôôôôŒŒŒŒŒœœœœœ", "All the characters is not getting added to the question content area.");




            } catch (Exception e) {
                Assert.fail("Exception in TC langaugePaletteFrenchForPerspectiveComment of class AddCommentInCSPage ", e);
            }
        }

        @Test(priority = 23, enabled = true)
        public void langaugePaletteForStudentSideWhileAttempting() {

            try {
                //TC row no 762
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
                LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

                String inputStringValue = null;
                int height = 0;
                int height1 = 0;

                new LoginUsingLTI().ltiLogin("762");//login as instructor
                new Navigator().NavigateTo("e-Textbook");
                new TopicOpen().chapterOpen(15);
                new SelectCourse().selectInvisibleAssignment("8A.3: South Asia’s Physiography");
                new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(762);

                new LoginUsingLTI().ltiLogin("762_1");//login a student
                new Navigator().NavigateTo("Assignments");    //navigate to Assignment
                new Click().clickByclassname("learning-activity-title"); //click on DW
                new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
                new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
                new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

                Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

                new LangaugePaletteForQuestionCreation().clickOnTheFrench();
                Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "French palette popup is not opened");

                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("input String value:" + inputStringValue);

                Assert.assertEquals(inputStringValue.length(), 24, "All the characters is not supported as existing Functionality.");

                //Tc row no 17
                Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

                //Tc row no 18
                Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
                //Tc row no 19
                Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
                Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

                //TC row no 20
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

                //TC row no 21
                Assert.assertEquals(inputStringValue, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "The selected characters is not displayed in the text box of the Palette. ");
                //TC row no 22
                height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

                manageContent.langaugePalette_saveButton.click(); //click on the save button

                //TC row no 23
                Assert.assertEquals(lessonPage.dw_add_perspective.getText().trim(), "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "All the characters is not getting added to the question content area.");

                //TC row no 24

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheFrench();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                    element.click();
                }
                Thread.sleep(3000);
                height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
                manageContent.langaugePalette_saveButton.click(); //click on the save button


                //Tc row no 106
                if (height > height1) {
                    Assert.fail("The height of the question textbox is not  increased accordingly");
                }

                new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
                new LangaugePaletteForQuestionCreation().clickOnTheFrench();
                for (WebElement element : manageContent.langaugePalette_allinput) {
                    element.click();
                }

                //TC row no 25
                manageContent.langaugePalette_cancelButton.click();//click on the cancel button

                Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
                Assert.assertEquals(lessonPage.dw_add_perspective.getText().trim(), "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœÀÀÀÀÀàààààÂÂÂÂÂâââââÆÆÆÆÆæææææÇÇÇÇÇçççççÈÈÈÈÈèèèèèÉÉÉÉÉéééééÊÊÊÊÊêêêêêËËËËËëëëëëÎÎÎÎÎîîîîîÏÏÏÏÏïïïïïÔÔÔÔÔôôôôôŒŒŒŒŒœœœœœ", "All the characters is not getting added to the question content area.");


            } catch (Exception e) {
                Assert.fail("Exception in TC langaugePaletteForStudentSideWhileAttempting of class AddCommentInCSPage ", e);
            }
        }


        public  void verificationForSpanishInCSPage() throws InterruptedException {
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            //TC row no 14
            new UIElement().waitAndFindElement(courseStreamPage.commentLinkIn_CSPage.get(0));
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

            manageContent.langaugePalette_spanish.click(); //click on the spanish language
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            int height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

            //TC row no 24
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_spanish.click(); //click on the spanish language
            for (WebElement element : manageContent.langaugePalette_allinput) {
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
            int height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
            manageContent.langaugePalette_saveButton.click(); //click on the save button


            //Tc row no 106
            if (height > height1) {
                Assert.fail("The height of the question textbox is not  increased accordingly");
            }

            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_spanish.click(); //click on the spanish language
            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
            Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿", "All the characters is not getting added to the question content area.");


        }

        public  void verificationForItalianInCSPage() throws InterruptedException {
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            //TC row no 14
            new UIElement().waitAndFindElement(courseStreamPage.commentLinkIn_CSPage.get(0));
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

            manageContent.langaugePalette_Italian.click(); //click on the spanish language
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(), 22, "All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Italian Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            int height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "All the characters is not getting added to the question content area.");

            //TC row no 24
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_Italian.click(); //click on the spanish language
            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
            Thread.sleep(3000);
            int height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //Tc row no 106
            if (height > height1) {
                Assert.fail("The height of the question textbox is not  increased accordingly");
            }

            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_Italian.click(); //click on the spanish language
            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
            Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤ÀÀÀÀÀÀààààààÁÁÁÁÁÁááááááÈÈÈÈÈÈèèèèèèÉÉÉÉÉÉééééééÌÌÌÌÌÌììììììÍÍÍÍÍÍííííííÒÒÒÒÒÒòòòòòòÓÓÓÓÓÓóóóóóóÙÙÙÙÙÙùùùùùùÚÚÚÚÚÚúúúúúú€€€€€€₤₤₤₤₤₤", "All the characters is not getting added to the question content area.");

        }

        public  void verificationForFrenchInCSPage() throws InterruptedException {
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            //TC row no 14
            new UIElement().waitAndFindElement(courseStreamPage.commentLinkIn_CSPage.get(0));
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

            manageContent.langaugePalette_French.click(); //click on the spanish language
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(), 24, "All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"French Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            int height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(), "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "All the characters is not getting added to the question content area.");

            //TC row no 24
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_French.click(); //click on the spanish language
            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
            Thread.sleep(3000);
            int height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //Tc row no 106
            if (height > height1) {
                Assert.fail("The height of the question textbox is not  increased accordingly");
            }

            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_French.click(); //click on the spanish language
            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
            Assert.assertEquals(courseStreamPage.commentBox.get(0).getText().trim(), "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœÀÀÀÀÀàààààÂÂÂÂÂâââââÆÆÆÆÆæææææÇÇÇÇÇçççççÈÈÈÈÈèèèèèÉÉÉÉÉéééééÊÊÊÊÊêêêêêËËËËËëëëëëÎÎÎÎÎîîîîîÏÏÏÏÏïïïïïÔÔÔÔÔôôôôôŒŒŒŒŒœœœœœ", "All the characters is not getting added to the question content area.");

        }

    }


