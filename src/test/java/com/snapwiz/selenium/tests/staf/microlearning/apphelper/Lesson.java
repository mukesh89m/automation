package com.snapwiz.selenium.tests.staf.microlearning.apphelper;

import com.snapwiz.selenium.tests.staf.framework.utility.*;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.AuthoringPage;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.LessonPage;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.SideMenuLink;
import com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions.CucumberDriver;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by mukesh on 24/8/16.
 */
public class Lesson {

    /**
     * This function will create lesson
     *
     * @param lessonName,level,tag,uploadImage
     * @author Mukesh
     */
    LessonPage lesson = PageFactory.initElements(CucumberDriver.getWebDriver(), LessonPage.class);
    AuthoringPage authoringPage = PageFactory.initElements(CucumberDriver.getWebDriver(), AuthoringPage.class);

    public String
    createLesson(String lessonName, String level, String tag, boolean uploadImage, String filename, String description) {
        LessonPage lesson = PageFactory.initElements(CucumberDriver.getWebDriver(), LessonPage.class);
        SideMenuLink sideMenuLink = PageFactory.initElements(CucumberDriver.getWebDriver(), SideMenuLink.class);
        AuthoringPage authoringPage = PageFactory.initElements(CucumberDriver.getWebDriver(), AuthoringPage.class);


        String name = "";
        try {
            WebDriverUtil.clickOnElementUsingJavascript(sideMenuLink.authoring_link); //click on the authoring  link
            Thread.sleep(2000);
            WebDriverUtil.clickOnElementUsingJavascript(authoringPage.createNew_link);
            name = lessonName + StringUtil.generateRandomString(5, StringUtil.Mode.ALPHANUMERIC);
            System.out.println(name);
            lesson.lessonName_textbox.clear();
            lesson.lessonName_textbox.sendKeys(name);  //enter text in lesson name text box
            lesson.lessonDescription_textbox.sendKeys(description); // enter text in Lesson description text box

            if (level != null) {
                if (level.equals("Beginner Level")) {
                    lesson.beginner_radio_button.click();
                } else if (level.equals("Intermediate Level")) {
                    lesson.intermediate_radio_button.click();
                } else if (level.equals("Expert Level")) {
                    lesson.expert_radio_button.click();
                } else {
                    System.out.println("!!!No Level has selected!!!");
                }
            }

            if (tag != null) {
                String[] value = tag.split(",");
                for (String ele : value) {
                    lesson.enterTag_textbox.sendKeys(ele.toUpperCase());
                    WebDriverUtil.waitForAjax(CucumberDriver.getWebDriver(),60);
                    WebDriverUtil.executeJavascript("$('#select2-lesson-tags-results li:eq(0)').trigger('mousemove')");
                    Thread.sleep(2000);
                    WebDriverUtil.executeJavascript("$('#select2-lesson-tags-results li:eq(0)').trigger('mouseup')");
                    Thread.sleep(2000);
//                    new RobotHelper().sendKeyBoardKeys("^");
                }
            }
            if (uploadImage == true) {
                WebDriverUtil.clickOnElementUsingJavascript(lesson.upload_image_link);
                WebDriverUtil.clickOnElementUsingJavascript(lesson.pickFile_link);
                String fileUploadPath = System.getProperty("user.dir") + "/" + Properties.getPropertyValue("FileUploadPath");
                new RobotHelper().sendKeyBoardKeys("$" + fileUploadPath + filename + "^");
                Thread.sleep(10000);
                WebDriverUtil.clickOnElementUsingJavascript(lesson.upload_button);
                WebDriverUtil.waitTillVisibilityOfElement(lesson.succussfulImage_link, 60);
                Assert.assertEquals("Image not uploaded successfully", "Image uploaded successfully.", lesson.uploadImage_message.getText().trim());


            }

            lesson.lesson_save_button.click();  //click on the save button


            Thread.sleep(3000);
            WebDriverUtil.waitForAjax(CucumberDriver.getWebDriver(), 60);
            WebDriverUtil.waitTillVisibilityOfElement(lesson.edit_lesson_link, 60);
            Assert.assertEquals("Lesson not created successfully!!", name, lesson.lessonName_edit_page.getText().trim());


        } catch (Throwable e) {
            Assert.fail("Exception in method createLesson of class Lesson" + e);
        }
        return name;
    }


}
