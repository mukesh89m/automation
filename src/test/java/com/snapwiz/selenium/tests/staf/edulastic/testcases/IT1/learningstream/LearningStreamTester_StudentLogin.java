package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.learningstream;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snapwiz on 28-Nov-14.
 */
public class LearningStreamTester_StudentLogin extends Driver {
    String actual = null;
    String expected = null;


    @Test(priority =1,enabled= true)
    public void ableToUseBoldAndItalicsTextFormatInPostWindowAsAuthor() {
        try {
            //Row No - 85
            //Expected - 'The following options should be displayed in the pop up menu
            String appendChar = "EEEEE";
            String appendCharSecondStudent = "FFFFF";
            new SignUp().teacher(appendChar, 85);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 85);//log in as teacher
            new School().createWithOnlyName(appendChar, 85);//create school
            String classCode = new Classes().createClass(appendChar, 85);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 85);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 85);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsStudent(appendChar, 85);//log in as student 1
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");
            List<WebElement> toolsList = driver.findElements(By.xpath("//ul[starts-with(@id,'redactor_toolbar_')]//li//a"));
            System.out.println("toolsList size : " + toolsList.size());
            List<String> textList = new ArrayList<String>();
            textList.add("re-icon re-bold");
            textList.add("re-icon re-italic");
            textList.add("re-icon re-underline");
            textList.add("re-icon re-unorderedlist");
            textList.add("re-icon re-orderedlist");
            textList.add("re-icon re-outdent");
            textList.add("re-icon re-indent");
            textList.add("re-icon re-link");
            textList.add("re-icon re-alignment");
            textList.add("re-icon re-subscript redactor-btn-image");
            textList.add("re-icon re-superscript redactor-btn-image");
            textList.add("re-icon re-uploadimage redactor-btn-image");
            textList.add("re-icon re-matheditor redactor-btn-image");
            textList.add("re-icon re-undo redactor-btn-image");
            textList.add("re-icon re-redo redactor-btn-image");
            for (int a = 0; a < toolsList.size() - 1; a++) {
                if (!((toolsList.get(a).isEnabled()) && (toolsList.get(a).getAttribute("class").equals(textList.get(a))))) {
                    Assert.fail("The following options should be displayed in the pop up menu in te row 10");
                }
            }

             /*Row No - 86 : "5. Select Bold ('B') from the menu below
              6. Type in some content"*/
            //Expected - The content should be typed in Bold font
            toolsList.get(0).click();
            new TextSend().textsendbyid("YesItIsBold", "html-editor-non-draggable");
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@id='html-editor-non-draggable']/b[text() = 'YesItIsBold']")) {
                Assert.fail("The content has not been typed in Bold font");
            }



                   /* Row 87 : "7. Unselect Bold and select Italics ('I')
                    8. Type in some content"*/
            //Expected : The content should be typed in Italics font
            driver.findElement(By.id("html-editor-non-draggable")).clear();
            toolsList.get(0).click();
            toolsList.get(1).click();
            new TextSend().textsendbyid("YesItIsItalics", "html-editor-non-draggable");
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@id='html-editor-non-draggable']/i[text() = 'YesItIsItalics']")) {
                Assert.fail("The content has not been typed in Italics font");
            }




                   /* Row No -  88: "9. Select Both Bold and italics icon
                    10. Type in some content"*/
            //Expected -  The content should be displayed in both Bold and Italics format


        } catch (Exception e) {
            Assert.fail("Exception in testcase 'ableToUseBoldAndItalicsTextFormatInPostWindowAsAuthor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }



    @Test(priority =2,enabled= false)
    public void useBulletsUnorderedAndOrderedListInPostWindowAsAuthor() {
        try {
            /*Row o - 89 :"1. Login as a student
            2. Click on learning stream
            3. Click on the ""post"" displayed on the right side top corner
            4. Click on  ""T"" icon
            5. Select 'Unordered list' icon
            6. Type in some content
            7. Press enter and type more content"*/
            //Expected - The text should appear in an unordered list format with Bullets
            String appendChar = "EEEEE";
            String appendCharSecondStudent = "FFFFF";
            new SignUp().teacher(appendChar, 89);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 89);//log in as teacher
            new School().createWithOnlyName(appendChar, 89);//create school
            String classCode = new Classes().createClass(appendChar, 89);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 89);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 89);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsStudent(appendChar, 89);//log in as student 1
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            Thread.sleep(5000);
            new Click().clickBycssselector("a.re-icon.re-unorderedlist");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//div[@id='html-editor-non-draggable']/ul/li")).sendKeys("YesItIsBoldUnOrderedList_1");
            driver.findElement(By.xpath("//body")).sendKeys(Keys.ENTER);
            new TextSend().textsendbyid("YesItIsBoldUnOrderedList_2", "html-editor-non-draggable");
            String text = driver.findElement(By.xpath("//div[@id='html-editor-non-draggable']/ul/li")).getText();
            System.out.println("text : " + text);
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@id='html-editor-non-draggable']/ul/li")) {
                Assert.fail("The text should is not appeared in an unordered list format with Bullets");
            }



            //Row - 15 : 90. Select 'Ordered list' icon
            //Expected - The 'Unordered list cont should be deselected
            new Click().clickBycssselector("a.re-icon.re-orderedlist");
            if (driver.findElement(By.cssSelector("a.re-icon.re-unorderedlist")).isSelected()) {
                Assert.fail("The 'Unordered list cont is not deselected");
            }



                    /*Row - 91 : "9.Type in some some content
                    10. Press enter and type in some more content"*/
            //Expected  - The text should appear in an ordered list format with numbers
            new TextSend().textsendbyid("YesItIsBoldOrderedList_1", "html-editor-non-draggable");
            driver.findElement(By.xpath("//div[@id='html-editor-non-draggable']/ol/li")).sendKeys(Keys.ENTER);
            new TextSend().textsendbyid("YesItIsBoldOrderedList_2", "html-editor-non-draggable");
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@id='html-editor-non-draggable']/ol/li")) {
                Assert.fail("The text should is not appeared in an ordered list format with numbers");
            }

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'useBulletsUnorderedAndOrderedListInPostWindowAsAuthor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }


    @Test(priority =3,enabled= true)
    public void insertURLInTextFieldOfPostAsAuthor() {
        try {
                    /*Row N0 - 93 : "1. Login as an Student
                    2. Click on learning stream
                    3. Click on the ""post"" displayed on the right side top corner
                    4. Click on  ""T"" icon
                    5. Click on ""Link"" button"*/

            String appendChar = "EEEEE";
            String appendCharSecondStudent = "FFFFF";
            new SignUp().teacher(appendChar, 93);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 93);//log in as teacher
            new School().createWithOnlyName(appendChar, 93);//create school
            String classCode = new Classes().createClass(appendChar, 93);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 93);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 93);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsStudent(appendChar, 93);//log in as student 1
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-link']");//Click on Link Icon
            Thread.sleep(1000);
            if (driver.findElements(By.xpath("//div[normalize-space(@class)='redactor_dropdown redactor_dropdown_box_link']")).size() != 0) {
                if (new BooleanValue().verifyNotElementPresentByXpath("//a[normalize-space(@class) = 'redactor_dropdown_link' and text() = 'Insert link']")) {
                    Assert.fail("The Insert Link is not appeared in an ordered list format with numbers");
                }

                if (new BooleanValue().verifyNotElementPresentByXpath("//a[normalize-space(@class) = 'redactor_dropdown_unlink' and text() = 'Unlink']")) {
                    Assert.fail("The Unlink Link is not appeared in an ordered list format with numbers");
                }
            } else {
                Assert.fail("popup has not appeared with the following option");

            }


            //Row No - 94 : 6. Select "Insert link"
                    /*Expected : "A pop up should be displayed with the following fields
                    1. Url
                    2. Text
                    3. ""Open link in new tab"" check box
                    4. Cancel button
                    5. Insert button"*/
            new Click().clickByXpath("//a[normalize-space(@class) = 'redactor_dropdown_link' and text() = 'Insert link']");
            if (new BooleanValue().verifyNotElementPresentByXpath("//section[@id='redactor-modal-link-insert']//label[text() = 'URL']")) {
                Assert.fail("Url Filed is not appeared in 'Link' Form");
            }
            if (new BooleanValue().verifyNotElementPresentByXpath("//section[@id='redactor-modal-link-insert']//label[text() = 'Text']")) {
                Assert.fail("Text Filed is not appeared in 'Link' Form");
            }
            if (driver.findElements(By.xpath("//section[@id='redactor-modal-link-insert' ]//label[3]")).size() != 0) {
                List<WebElement> checkBoxList = driver.findElements(By.xpath("//section[@id='redactor-modal-link-insert' ]//label[3]"));
                if (!(checkBoxList.get(0).findElement(By.tagName("input")).getAttribute("type").equals("checkbox"))) {
                    Assert.fail("Open link in new tab check box is not appeared");
                }
                actual = driver.findElement(By.xpath("//section[@id='redactor-modal-link-insert' ]//label[3]")).getText();
                expected = "Open link in new tab";
                Assert.assertEquals(actual.trim(), expected, "Open link in new tab check box is not appeared");
            } else {
                Assert.fail("Open link in new tab check box is not appeared");
            }
            if (new BooleanValue().verifyNotElementPresentByXpath("//button[normalize-space(@class) ='redactor_modal_btn redactor_btn_modal_close' and text() = 'Cancel']")) {
                Assert.fail("Cancel button is not appeared in 'Link' Form");
            }
            if (new BooleanValue().verifyNotElementPresentByXpath("//button[@id='redactor_insert_link_btn' and text() = 'Insert']")) {
                Assert.fail("Insert button is not appeared in 'Link' Form");
            }



                  /*  Row No  -95 : "7. Type in any url (www.google.com for example)
                    8. Type anything in text field that has to be displayed for the url ( 'Google' for example)
                    9. Click on ""Insert"" "*/
            //Expected  - The link text entered for the URL should be displayed in the post edit field with blue color font
            new TextSend().textsendbyid("https://www.google.co.in/", "redactor_link_url");
            new TextSend().textsendbyid("Google", "redactor_link_url_text");
            new Click().clickByid("redactor_insert_link_btn");
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@id  = 'html-editor-non-draggable']//a[text() = 'Google']")) {
                Assert.fail("The link text entered for the URL has not been displayed in the post edit field with blue color font");
            }



            //Row - 96 : 10. Click submit
            //Expected - The post should be displayed with the entered text for the URL
            new Click().clickByid("post-submit-button");
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@class='body-wrapper']//a[text() = 'Google']")) {
                Assert.fail("The post has not displayed with the entered text for the URL");
            }



            //Row No -97 &98 :  11. Click on the Inserted URL displayed on the post
            //Expected 1 - "1. Should navigate to the specified webpage whose link is provided"
            //expected 2 -  2. The web page should open in the same tab
            new Click().clickByXpath("//div[@class='body-wrapper']//a[text() = 'Google']");
            actual = driver.getCurrentUrl();
            expected = "https://www.google.co.in/";
            Assert.assertEquals(actual.trim(), expected, "The Posted Link is not navigated to the specified webpage whose link is provided");
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'insertURLInTextFieldOfPostAsAuthor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }


    @Test(priority =4,enabled= true)
    public void insertURLInTextFieldOfPostAndLinkToBeOpenedInNewTabAsAuthor() {
        try {
            //Row No - 99 & 100
            //Expected 1 - 1. Should navigate to the specified webpage whose link is provided
            //Expected 2 - 2. The web page should open in new tab

            String appendChar = "EEEEE";
            String appendCharSecondStudent = "FFFFF";
            new SignUp().teacher(appendChar, 99);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 99);//log in as teacher
            new School().createWithOnlyName(appendChar, 99);//create school
            String classCode = new Classes().createClass(appendChar, 99);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 99);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 99);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsStudent(appendChar, 99);//log in as student 1
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-link']");//Click on 'Link' icon
            new Click().clickByXpath("//a[normalize-space(@class) = 'redactor_dropdown_link' and text() = 'Insert link']");//Click on 'Insert Link'
            new TextSend().textsendbyid("https://www.google.co.in/", "redactor_link_url");//Type an URL in URL Text Field
            new TextSend().textsendbyid("GoogleIndia", "redactor_link_url_text");//Type Text 'GoogleIndia' in Text Text Field
            new Click().clickByid("redactor_link_blank");//Click on Checkbox 'Open link in new tab'
            new Click().clickByid("redactor_insert_link_btn");//Click on Insert Button
            new Click().clickByid("post-submit-button");
            new Click().clickByXpath("//div[@class='body-wrapper']//a[text() = 'GoogleIndia']");
            new Click().clickbylinkText("GoogleIndia");
            List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
            driver.switchTo().window(browserTabs .get(1));
            actual = driver.getCurrentUrl();
            expected = "https://www.google.co.in/";
            Assert.assertEquals(actual.trim(), expected, "The Webpage that the URL points is not opened in a new tab");


        } catch (Exception e) {
            Assert.fail("Exception in testcase 'insertURLInTextFieldOfPostAndLinkToBeOpenedInNewTabAsAuthor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }



    @Test(priority =5,enabled= true)
    public void unLinkAnyURLFromPostAsAuthor() {
        try {
            //Row NO - 101
            //Expected - The link text entered for the URL should be displayed in the post edit field with blue color font
            String appendChar = "A";
            String appendCharSecondStudent = "B";
            new SignUp().teacher(appendChar, 101);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 101);//log in as teacher
            new School().createWithOnlyName(appendChar, 101);//create school
            String classCode = new Classes().createClass(appendChar, 101);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 101);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 101);//create student2
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, 101);//log in as student 1
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-link']");//Click on 'Link' icon
            new Click().clickByXpath("//a[normalize-space(@class) = 'redactor_dropdown_link' and text() = 'Insert link']");//Click on 'Insert Link'
            new TextSend().textsendbyid("https://www.google.co.in/", "redactor_link_url");//Type an URL in URL Text Field
            new TextSend().textsendbyid("GoogleIndia", "redactor_link_url_text");//Type Text 'GoogleIndia' in Text Text Field
            new Click().clickByid("redactor_link_blank");//Click on Checkbox 'Open link in new tab'
            new Click().clickByid("redactor_insert_link_btn");//Click on Insert Button
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@id  = 'html-editor-non-draggable']//a[text() = 'GoogleIndia']")) {
                Assert.fail("The link text entered for the URL has not been displayed in the post edit field with blue color font");
            }
            Point location = driver.findElement(By.xpath("//a[contains(text(),'GoogleIndia')]")).getLocation();
            Dimension size = driver.findElement(By.xpath("//a[contains(text(),'GoogleIndia')]")).getSize();
            System.out.println("location : " + location);
            System.out.println("size : " + size);


            //Row No - 102 :Select the text of the URL and click "Link" Button from the menu below
            /*Expected - "A pop up must be displayed with the following options
            1. Insert Link
            2. Unlink"*/
            /*WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'GoogleIndia')]")));
            Actions actions = new Actions(driver);
            actions.moveToElement(element, 16, 16)
                    .clickAndHold()
                    .moveByOffset(76, 76)
                    .release()
                    .perform();
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-link']");//Click on Link Icon
            Thread.sleep(5000);

            if (driver.findElements(By.xpath("//div[normalize-space(@class)='redactor_dropdown redactor_dropdown_box_link']")).size() != 0) {
                if (new BooleanValue().verifyNotElementPresentByXpath("//a[normalize-space(@class) = 'redactor_dropdown_link' and text() = 'Edit link']")) {
                    Assert.fail("The Insert Link is not appeared in an ordered list format with numbers");
                }
                if (new BooleanValue().verifyNotElementPresentByXpath("//a[normalize-space(@class) = 'redactor_dropdown_unlink' and text() = 'Unlink']")) {
                    Assert.fail("The Unlink Link is not appeared in an ordered list format with numbers");
                }
            } else {
                Assert.fail("popup has not appeared with the following option");

            }




            //Row No  - 103 : Click on "Unlink"
            //Expected - The selected link text should no longer be in blue and the it should be unlinked
            new Click().clickByXpath("//a[normalize-space(@class) = 'redactor_dropdown_unlink' and text() = 'Unlink']");
            if (!(new BooleanValue().verifyNotElementPresentByXpath("//div[@id='html-editor-non-draggable'] / a[text() = 'GoogleIndia​']"))) {
                Assert.fail("The selected link text is in blue and the it is not unlinked");
            }*/

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'unLinkAnyURLFromPostAsAuthor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }






    @Test(priority =6,enabled= true)
    public void unLinkAnyURLFromPostUsingUnlinkOptionInPostEditFieldAsAuthor() {
        try {
            //Row No-104
            //Expected - The text entered for the URL should be displayed in the post edit field with blue color font
            String appendChar = "EEEEE";
            String appendCharSecondStudent = "FFFFF";
            new SignUp().teacher(appendChar, 104);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 104);//log in as teacher
            new School().createWithOnlyName(appendChar, 104);//create school
            String classCode = new Classes().createClass(appendChar, 104);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 104);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 104);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsStudent(appendChar, 104);//log in as student 1
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-link']");//Click on 'Link' icon
            new Click().clickByXpath("//a[normalize-space(@class) = 'redactor_dropdown_link' and text() = 'Insert link']");//Click on 'Insert Link'
            new TextSend().textsendbyid("https://www.google.co.in/", "redactor_link_url");//Type an URL in URL Text Field
            new TextSend().textsendbyid("GoogleIndia", "redactor_link_url_text");//Type Text 'GoogleIndia' in Text Text Field
            new Click().clickByid("redactor_link_blank");//Click on Checkbox 'Open link in new tab'
            new Click().clickByid("redactor_insert_link_btn");//Click on Insert Button
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@id  = 'html-editor-non-draggable']//a[text() = 'GoogleIndia']")) {
                Assert.fail("The link text entered for the URL has not been displayed in the post edit field with blue color font");
            }


          /* Row No - 105 : 11. Click on the Link text on the post edit field
           Expected - "It should display the following contents
            1. URL that was inserted
            2. Edit
            3. Unlink"*/
            new Click().clickByXpath("//div[@id= 'html-editor-non-draggable']//a[@href = 'https://www.google.co.in/']");
            Thread.sleep(5000);
            if (driver.findElements(By.xpath("//span[normalize-space(@class)='redactor-link-tooltip']")).size() != 0) {
                if (new BooleanValue().verifyNotElementPresentByXpath("//span[normalize-space(@class)='redactor-link-tooltip']//a[ text() = 'https://www.google.co.in...']")) {
                    Assert.fail("The URL that was inserted is not appeared");
                }
                if (new BooleanValue().verifyNotElementPresentByXpath("//span[normalize-space(@class)='redactor-link-tooltip']//a[ text() = 'Edit']")) {
                    Assert.fail("The Edit Link is not appeared");
                }
                if (new BooleanValue().verifyNotElementPresentByXpath("//span[normalize-space(@class)='redactor-link-tooltip']//a[ text() = 'Unlink']")) {
                    Assert.fail("The Unlink link is not appeared");
                }
            } else {
                Assert.fail("'It should display the following contents in the row no 31");

            }



            //Row No  - 106 : Click on "Unlink"
            //Expected - The selected link text should no longer be in blue and the it should be unlinked
            driver.findElement(By.xpath("//span[normalize-space(@class)='redactor-link-tooltip']//a[ text() = 'Unlink']")).click();
            Thread.sleep(2000);
            if (!(new BooleanValue().verifyNotElementPresentByXpath("//div[@id='html-editor-non-draggable'] / a[text() = 'GoogleIndia​']"))) {
                Assert.fail("The selected link text is in blue and the it is not unlinked");
            }

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'unLinkAnyURLFromPostUsingUnlinkOptionInPostEditFieldAsAuthor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }




    @Test(priority =7,enabled= true)
    public void removeLinkAnyURLFromPostUsingBackSpaceAsAuthor() {
        try {
            //Row No-107
            //Expected - The text entered for the URL should be displayed in the post edit field with blue color font
            String appendChar = "EEEEE";
            String appendCharSecondStudent = "FFFFF";
            new SignUp().teacher(appendChar, 107);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 107);//log in as teacher
            new School().createWithOnlyName(appendChar, 107);//create school
            String classCode = new Classes().createClass(appendChar, 107);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 107);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 107);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsStudent(appendChar, 107);//log in as student 1
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-link']");//Click on 'Link' icon
            new Click().clickByXpath("//a[normalize-space(@class) = 'redactor_dropdown_link' and text() = 'Insert link']");//Click on 'Insert Link'
            new TextSend().textsendbyid("https://www.google.co.in/", "redactor_link_url");//Type an URL in URL Text Field
            new TextSend().textsendbyid("GoogleIndia", "redactor_link_url_text");//Type Text 'GoogleIndia' in Text Text Field
            new Click().clickByid("redactor_link_blank");//Click on Checkbox 'Open link in new tab'
            new Click().clickByid("redactor_insert_link_btn");//Click on Insert Button
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@id  = 'html-editor-non-draggable']//a[text() = 'GoogleIndia']")) {
                Assert.fail("The link text entered for the URL has not been displayed in the post edit field with blue color font");
            }



            /*//Row No - 108 : 11. Backspace the Link text entirely
            //Exected - Link text should be deleted
            driver.findElement(By.id("html-editor-non-draggable")).clear();
            if(driver.findElements(By.xpath("//div[@id='html-editor-non-draggable'] / a[text() = 'GoogleIndia​']")).size()!=0){
                System.out.println("Link Present");
            }else{
                System.out.println("Link Not Present");

            }

            if (!(new BooleanValue().verifyNotElementPresentByXpath("//div[@id='html-editor-non-draggable'] / a[text() = 'GoogleIndia​']"))) {
                Assert.fail("The selected link text is in blue and the it is not unlinked");
            }
*/
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'removeLinkAnyURLFromPostUsingBackSpaceAsAuthor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }




    @Test(priority =8,enabled= true)
    public void editLinkFromPostUsingEditOptionAsAnAuthor() {
        try {
            //Row No-108
            //Expected - The text entered for the URL should be displayed in the post edit field with blue color font
            String appendChar = "EEEEEE";
            String appendCharSecondStudent = "FFFFFF";

            new SignUp().teacher(appendChar, 108);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 108);//log in as teacher
            new School().createWithOnlyName(appendChar, 108);//create school
            String classCode = new Classes().createClass(appendChar, 108);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 108);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 108);//create student2
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, 108);//log in as student 1
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-link']");//Click on 'Link' icon
            new Click().clickByXpath("//a[normalize-space(@class) = 'redactor_dropdown_link' and text() = 'Insert link']");//Click on 'Insert Link'
            new TextSend().textsendbyid("https://www.google.co.in/", "redactor_link_url");//Type an URL in URL Text Field
            new TextSend().textsendbyid("GoogleAmerica", "redactor_link_url_text");//Type Text 'GoogleAmerica' in Text Text Field
            new Click().clickByid("redactor_link_blank");//Click on Checkbox 'Open link in new tab'
            new Click().clickByid("redactor_insert_link_btn");//Click on Insert Button
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@id  = 'html-editor-non-draggable']//a[text() = 'GoogleAmerica']")) {
                Assert.fail("The link text entered for the URL has not been displayed in the post edit field with blue color font");
            }



             /* Row No - 109 : 11. Click on the Link text on the post edit field
           Expected - "It should display the following contents
            1. URL that was inserted
            2. Edit
            3. Unlink"*/
            new Click().clickByXpath("//div[@id= 'html-editor-non-draggable']//a[@href = 'https://www.google.co.in/']");
            Thread.sleep(5000);
            if (driver.findElements(By.xpath("//span[normalize-space(@class)='redactor-link-tooltip']")).size() != 0) {
                if (new BooleanValue().verifyNotElementPresentByXpath("//span[normalize-space(@class)='redactor-link-tooltip']//a[ text() = 'https://www.google.co.in...']")) {
                    Assert.fail("The URL that was inserted is not appeared");
                }
                if (new BooleanValue().verifyNotElementPresentByXpath("//span[normalize-space(@class)='redactor-link-tooltip']//a[ text() = 'Edit']")) {
                    Assert.fail("The Edit Link is not appeared");
                }
                if (new BooleanValue().verifyNotElementPresentByXpath("//span[normalize-space(@class)='redactor-link-tooltip']//a[ text() = 'Unlink']")) {
                    Assert.fail("The Unlink link is not appeared");
                }
            } else {
                Assert.fail("'It should display the following contents in the row no 36");
            }


            //Row No-110 : 12. Click on "Edit" option
            //Expected -  "A pop up should be displayed with the following fields
          /*  1. URL with the current url
            2. Text with the current link text
            3. ""Open link in new tab"" check box
            4. Cancel button
            5. Insert button"*/
            Thread.sleep(5000);
            //driver.findElement(By.xpath("//span[normalize-space(@class)='redactor-link-tooltip']//a[ text() = 'Edit']")).click();
            //new Click().clickBycssselector("span[class='redactor-link-tooltip']//a[ text() = 'Edit']");
            // new Click().clickbylinkText("Edit");
            driver.findElement(By.linkText("Edit")).click();
            Thread.sleep(5000);
            validateLinkFormFields();
            Thread.sleep(5000);



           /*Row No - 111 : "13. Make changes in any of the field
            14. Click on insert"*//*
            //Expected - The changes should reflect and in case of change of URL, It should now navigate to the new URL
            driver.findElement(By.id("redactor_link_url")).clear();
            Thread.sleep(5000);
            new TextSend().textsendbyid("www.gmail.com", "redactor_link_url");
            Thread.sleep(5000);
            new Click().clickByid("redactor_insert_link_btn");
            Thread.sleep(5000);
            new Click().clickByid("post-submit-button");
            Thread.sleep(10000);
            new Click().clickByXpath("//div[@class='body-wrapper']//a[text() = 'gmail']");
            Thread.sleep(5000);
            new Click().clickbylinkText("gmail");
            Thread.sleep(5000);
            List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
            driver.switchTo().window(browserTabs .get(1));
            actual = driver.getCurrentUrl();
            expected = "www.gmail.com";
            Assert.assertEquals(actual.trim(), expected, "The Webpage that the URL points is not opened in a new tab");*/

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'editLinkFromPostUsingEditOptionAsAnAuthor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }



    @Test(priority =9,enabled= true)
    public void navigateToURLWhenClickedLinkInPostEdditFieldAsAnAuthor() {
        try {
            //Row No-112
            //Expected - The text entered for the URL should be displayed in the post edit field with blue color font
            String appendChar = "EEEEE";
            String appendCharSecondStudent = "FFFFF";
            new SignUp().teacher(appendChar, 112);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 112);//log in as teacher
            new School().createWithOnlyName(appendChar, 112);//create school
            String classCode = new Classes().createClass(appendChar, 112);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 112);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 112);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsStudent(appendChar, 112);//log in as student 1
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-link']");//Click on 'Link' icon
            new Click().clickByXpath("//a[normalize-space(@class) = 'redactor_dropdown_link' and text() = 'Insert link']");//Click on 'Insert Link'
            new TextSend().textsendbyid("https://www.google.co.in/", "redactor_link_url");//Type an URL in URL Text Field
            new TextSend().textsendbyid("GoogleAmerica", "redactor_link_url_text");//Type Text 'GoogleAmerica' in Text Text Field
            new Click().clickByid("redactor_link_blank");//Click on Checkbox 'Open link in new tab'
            new Click().clickByid("redactor_insert_link_btn");//Click on Insert Button
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@id  = 'html-editor-non-draggable']//a[text() = 'GoogleAmerica']")) {
                Assert.fail("The link text entered for the URL has not been displayed in the post edit field with blue color font");
            }



             /* Row No - 113 : 11. Click on the Link text on the post edit field
           Expected - "It should display the following contents
            1. URL that was inserted
            2. Edit
            3. Unlink"*/
            new Click().clickByXpath("//div[@id= 'html-editor-non-draggable']//a[@href = 'https://www.google.co.in/']");
            Thread.sleep(5000);
            if (driver.findElements(By.xpath("//span[normalize-space(@class)='redactor-link-tooltip']")).size() != 0) {
                if (new BooleanValue().verifyNotElementPresentByXpath("//span[normalize-space(@class)='redactor-link-tooltip']//a[ text() = 'https://www.google.co.in...']")) {
                    Assert.fail("The URL that was inserted is not appeared");
                }
                if (new BooleanValue().verifyNotElementPresentByXpath("//span[normalize-space(@class)='redactor-link-tooltip']//a[ text() = 'Edit']")) {
                    Assert.fail("The Edit Link is not appeared");
                }
                if (new BooleanValue().verifyNotElementPresentByXpath("//span[normalize-space(@class)='redactor-link-tooltip']//a[ text() = 'Unlink']")) {
                    Assert.fail("The Unlink link is not appeared");
                }
            } else {
                Assert.fail("'It should display the following contents in the row no 36");
            }



            //RowNo - 114 : 12. Click on URL
            //Expected - The Webpage that the URL points to should be opened in a new tab
            driver.findElement(By.xpath("//span[normalize-space(@class)='redactor-link-tooltip']//a[ text() = 'https://www.google.co.in...']")).click();
            List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
            driver.switchTo().window(browserTabs .get(1));
            actual = driver.getCurrentUrl();
            expected = "https://www.google.co.in/";
            Assert.assertEquals(actual.trim(), expected, "The Webpage that the URL points is not opened in a new tab");
            System.out.println("Actual window URL : " + actual);
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'navigateToURLWhenClickedLinkInPostEdditFieldAsAnAuthor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }








    @Test(priority =10,enabled= true)
    public void cancelButtonOfLinkTextToBeWorkedOnInstructorsSide() {
        try {
            //Row No-115
            //Expected - The Link text should not appear on the post edit field and the pop up should be closed
            String appendChar = "EEEEE";
            String appendCharSecondStudent = "FFFFF";
            new SignUp().teacher(appendChar, 115);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 115);//log in as teacher
            new School().createWithOnlyName(appendChar, 115);//create school
            String classCode = new Classes().createClass(appendChar, 115);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 115);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 115);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsStudent(appendChar, 115);//log in as student 1
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-link']");//Click on 'Link' icon
            new Click().clickByXpath("//a[normalize-space(@class) = 'redactor_dropdown_link' and text() = 'Insert link']");//Click on 'Insert Link'
            new TextSend().textsendbyid("https://www.google.co.in/", "redactor_link_url");//Type an URL in URL Text Field
            new TextSend().textsendbyid("GoogleAmerica", "redactor_link_url_text");//Type Text 'GoogleAmerica' in Text Text Field
            new Click().clickByid("redactor_link_blank");//Click on Checkbox 'Open link in new tab'
            new Click().clickByXpath("//button[text() = 'Cancel']");
            if (!(new BooleanValue().verifyNotElementPresentByXpath("//div[@id  = 'html-editor-non-draggable']//a[text() = 'GoogleAmerica']"))) {
                Assert.fail("The Link text is not appeared on the post edit field");
            }

            if(driver.findElement(By.xpath("//header[@id='redactor_modal_header' and text() = 'Link']")).isDisplayed()){
                Assert.fail("The pop up is not closed");
            }
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'cancelButtonOfLinkTextToBeWorkedOnInstructorsSide' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }






    @Test(priority =11,enabled= true)
    public void useUndoAndRedoButtonOfTextFormatEditor() {
        try {
            //Row No-127
            //Expected - The text and the content should appear on the 'Text editor' field
            String appendChar = "EEEEEE";
            new SignUp().teacher(appendChar, 127);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 127);//log in as teacher
            new School().createWithOnlyName(appendChar, 127);//create school
            new Classes().createClass(appendChar, 127);//create class
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, 127);//log in as teacher
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            new TextSend().textsendbyid("TypeAnyContent","html-editor-non-draggable");
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-matheditor redactor-btn-image']");
            Thread.sleep(5000);
            /*new Click().clickByXpath("//img[normalize-space(@title)='Square root']");
            driver.findElement(By.xpath("//input[@class='wrs_focusElement']")).sendKeys("4");
            new Click().clickByXpath("//span[@class='ui-button-text' and text() = 'Save']");*/
            new QuestionCreate().enterValueInMathMLEditor("Square root","4");



            //Row No  - 128. Click on Undo button
            //Expected -    The last operation should be undone
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-undo redactor-btn-image']");




            //Row No  - 129. Click on Redo button
            //Expected -   The last undone operation must be redone
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-redo redactor-btn-image']");





        } catch (Exception e) {
            Assert.fail("Exception in testcase 'useUndoAndRedoButtonOfTextFormatEditor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }






    @Test(priority =12,enabled= true)
    public void useAllOptionsAvailableForMathEditorAsInstructor() {
        try {
            //Row No-130
            //Expected - 'The following options should be displayed in the pop up menu
            String appendChar = "EEEEEEEE";
            new SignUp().teacher(appendChar, 130);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 130);//log in as teacher
            new School().createWithOnlyName(appendChar, 130);//create school
            String classCode = new Classes().createClass(appendChar, 130);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 130);//create student1
            new Navigator().logout();
            new Login().loginAsStudent(appendChar, 130);//log in as student 1
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");
            List<WebElement> toolsList = driver.findElements(By.xpath("//ul[starts-with(@id,'redactor_toolbar_')]//li//a"));
            System.out.println("toolsList size : " + toolsList.size());
            List<String> textList = new ArrayList<String>();
            textList.add("re-icon re-bold");
            textList.add("re-icon re-italic");
            textList.add("re-icon re-underline");
            textList.add("re-icon re-unorderedlist");
            textList.add("re-icon re-orderedlist");
            textList.add("re-icon re-outdent");
            textList.add("re-icon re-indent");
            textList.add("re-icon re-link");
            textList.add("re-icon re-alignment");
            textList.add("re-icon re-subscript redactor-btn-image");
            textList.add("re-icon re-superscript redactor-btn-image");
            textList.add("re-icon re-uploadimage redactor-btn-image");
            textList.add("re-icon re-matheditor redactor-btn-image");
            textList.add("re-icon re-undo redactor-btn-image");
            textList.add("re-icon re-redo redactor-btn-image");
            for (int a = 0; a < toolsList.size() - 1; a++) {
                if (!((toolsList.get(a).isEnabled()) && (toolsList.get(a).getAttribute("class").equals(textList.get(a))))) {
                    Assert.fail("The following options should be displayed in the pop up menu in te row 10");
                }
            }



            //Row No -131 : 5. Click on Math editor
            /*Expected - "Math editor pop up should be opened with all the relevant mathematical tools
            The Following buttons should be displayed
            1. Save
            2. Delete
            3. Cancel"*/
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-matheditor redactor-btn-image']");
            if (new BooleanValue().verifyNotElementPresentByXpath("//span[@class='ui-button-text' and text() = 'Save']")) {
                Assert.fail("Save Button has not been displayed in Math Editor Popup");
            }
            if (new BooleanValue().verifyNotElementPresentByXpath("//span[@class='ui-button-text' and text() = 'Delete']")) {
                Assert.fail("Delete Button has not been displayed in Math Editor Popup");
            }
            if (new BooleanValue().verifyNotElementPresentByXpath("//span[@class='ui-button-text' and text() = 'Cancel']")) {
                Assert.fail("Cancel Button has not been displayed in Math Editor Popup");
            }
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'useAllOptionsAvailableForMathEditorAsInstructor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }




    @Test(priority =13,enabled= true)
    public void formulateAnyMathematicalExpressionsInEditorAndUseItInPostAsInstructor() {
        try {
            //Row No-132
            //Expected - 'The mathematical expression should be displayed on the post  edit field in the right format
            String appendChar = "A";
            new SignUp().teacher(appendChar, 132);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 132);//log in as teacher
            new School().createWithOnlyName(appendChar, 132);//create school
            String classCode = new Classes().createClass(appendChar, 132);//create class
            new Navigator().logout();
            new SignUp().studentDirectRegister(appendChar, classCode, 132);//create student1
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, 132);//log in as student 1
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-matheditor redactor-btn-image']");//Click on 'Math Editor'

            new QuestionCreate().enterValueInMathMLEditor("Square root","4");
            Thread.sleep(10000);
            actual = new TextFetch().textfetchbyid("html-editor-non-draggable");
            expected = "√4";
            //Assert.assertEquals(actual.trim(), expected, "The mathematical expression is not displayed on the post edit field in the right format");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'formulateAnyMathematicalExpressionsInEditorAndUseItInPostAsInstructor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }





    @Test(priority =14,enabled= true)
    public void deleteAnyMathematicalExpressionsInEditorAndUseItInPostAsInstructor() {
        try {
            //Row No-133
            //Expected - The formula in the math editor pop up should be deleted
            String appendChar = "EEEEEEE";
            new SignUp().teacher(appendChar, 133);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 133);//log in as teacher
            new School().createWithOnlyName(appendChar, 133);//create school
            String classCode = new Classes().createClass(appendChar, 133);//create class
            new Navigator().logout();//log out*/
            new SignUp().studentDirectRegister(appendChar, classCode, 133);//create student1
            new Navigator().logout();
            new Login().loginAsStudent(appendChar, 133);//log in as student 1
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-matheditor redactor-btn-image']");//Click on 'Math Editor'
            new Click().clickByXpath("//img[normalize-space(@title)='Square root']");//Click on  Square root Icon
            driver.findElement(By.xpath("//input[@class='wrs_focusElement']")).sendKeys("4");// Type 4
            Thread.sleep(3000);
            new Click().clickByXpath("(//span[@class='ui-button-text' and text() = 'Delete'])[1]");//Click on Delete Button
            Thread.sleep(5000);
            actual = new TextFetch().textFetchByXpath("//span[@class='wrs_container']");
            expected = "";
            System.out.println("Actual Square :" + actual);
            Assert.assertEquals(actual.trim(), expected, "The formula in the math editor pop up is not deleted");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'deleteAnyMathematicalExpressionsInEditorAndUseItInPostAsInstructor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }





    @Test(priority =15,enabled= true)
    public void cancelAnyMathematicalOperationsInEditUsingCancelButtonAsInstructor() {
        try {
            //Row No-134
            //Expected - The pop up should be closed and the expression formulated on math editor should not appear on the post edit field
            String appendChar = "EEEEEEEEEE";
            new SignUp().teacher(appendChar, 134);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 134);//log in as teacher
            new School().createWithOnlyName(appendChar, 134);//create school
            String classCode = new Classes().createClass(appendChar, 134);//create class
            new Navigator().logout();//log out*/
            new SignUp().studentDirectRegister(appendChar, classCode, 134);//create student1
            new Navigator().logout();
            new Login().loginAsStudent(appendChar, 134);//log in as student 1
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-matheditor redactor-btn-image']");//Click on 'Math Editor'
            new Click().clickByXpath("//img[normalize-space(@title)='Square root']");//Click on  Square root Icon
            driver.findElement(By.xpath("//input[@class='wrs_focusElement']")).sendKeys("4");// Type 4
            Thread.sleep(3000);
            new Click().clickByXpath("(//span[@class='ui-button-text' and text() = 'Cancel'])[1]");//Click on Cancel Button
            Thread.sleep(5000);
            if(driver.findElement(By.xpath("//div[@id='edit-math-dialog']")).isDisplayed()){
                System.out.println("Pop up is displayed");
            }
            if(driver.findElement(By.xpath("//div[@id='edit-math-dialog']")).isDisplayed()){
                Assert.fail("Math Editor pop is not getting closed even after clicking cancel button");
            }
            actual = new TextFetch().textfetchbyid("html-editor-non-draggable");
            expected = "Share your knowledge or seek answers...";
            System.out.println("Actual Square :" + actual);
            Assert.assertEquals(actual.trim(), expected, "The expression formulated on math editor should not appear on the post edit field");
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'cancelAnyMathematicalOperationsInEditUsingCancelButtonAsInstructor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }




    @Test(priority =16,enabled= true)
    public void useUndoAndRedoButtonInmathEditorPopUpAsInstructor() {
        try {
            //Row No-135
            //Expected - The last operation should be undone in the popup
            String appendChar = "EEEEEEE";
            new SignUp().teacher(appendChar, 135);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 135);//log in as teacher
            new School().createWithOnlyName(appendChar, 135);//create school
            String classCode = new Classes().createClass(appendChar, 135);//create class
            new Navigator().logout();//log out*/
            new SignUp().studentDirectRegister(appendChar, classCode, 135);//create student1
            new Navigator().logout();
            new Login().loginAsStudent(appendChar, 135);//log in as student 1
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-matheditor redactor-btn-image']");//Click on 'Math Editor'
            new Click().clickByXpath("//img[normalize-space(@title)='Square root']");//Click on  Square root Icon
            driver.findElement(By.xpath("//input[@class='wrs_focusElement']")).sendKeys("4");// Type 4
            Thread.sleep(3000);
            new Click().clickByXpath("//button[@title = 'Undo']");//Click on Undo Button
            Thread.sleep(5000);
            actual = new TextFetch().textFetchByXpath("//span[@class='wrs_container']");
            expected = "";
            System.out.println("Actual Square :" + actual);
            Assert.assertEquals(actual.trim(), expected, "The last operation should be undone in the popup");


        } catch (Exception e) {
            Assert.fail("Exception in testcase 'useUndoAndRedoButtonInmathEditorPopUpAsInstructor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }







    @Test(priority =17,enabled= true)
    public void studentToBeAbleToPostAnyContentToInstructor() {
        try {

            /*Row No - 136 : "1. Login as a student
            2. Click on learning stream page
            3. Click on post
            4. Type any content
            5. Click submit"*/
            //Expected - The post field should close and the updated post should be listed in the learning stream page

            String appendChar = "EEEEEEEE";
            new SignUp().teacher(appendChar, 136);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 136);//log in as teacher
            new School().createWithOnlyName(appendChar, 136);//create school
            String classCode = new Classes().createClass(appendChar, 136);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 136);//create student1
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, 136);//log in as student 1
            new Navigator().navigateTo("learningstream");
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");
            new TextSend().textsendbyid("Hi all, Finish XYZ assignment this week", "html-editor-non-draggable");
            new Click().clickByid("post-submit-button");
            if (!(new BooleanValue().verifyNotElementPresentByXpath("//aside[@class = 'share-to-ls share-to-ls-expand']"))) {
                Assert.fail("The post field should be closed");
            }
            actual = new TextFetch().textFetchByXpath("//div[@class = 'stream-body']");
            expected = "Hi all, Finish XYZ assignment this week";
            Assert.assertEquals(actual.trim(), expected, "The updated post should be listed in the learning stream page of the Student");



            /*Row No - 137 : "6. Logout of student's account
            7. Login as a relevant teacher to which the content was posted
            8. Click on Learning stream"*/
            //Expected - The post from the student must be listed in the Learning stream page
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, 136);//log in as student 1
            new Navigator().navigateTo("learningstream");
            actual = new TextFetch().textFetchByXpath("//div[@class = 'stream-body']");
            expected = "Hi all, Finish XYZ assignment this week";
            Assert.assertEquals(actual.trim(), expected, "The updated post by Instructor should be listed in the learning stream page of the Student");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'studentToBeAbleToPostAnyContentToInstructor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }



    @Test(priority =18,enabled= true)
    public void contentPostedByStudentToTeacherNotTobeVisibleToOtherStudent() {
        try {

           /* Row No - 138 : "1. Login as student A
            2. Click on learning stream page
            3. Click on post
            4. Type any content
            5. Click submit
            6. Logout of student A's account
            7. Login as another student B from the same class
            8. Click on Learning stream"*/
            //Expected - The post from the student A must not be listed in the Learning stream page of student B
            String appendChar = "EEEEEEEE";
            String appendCharSecondStudent = "FFFFFFF";
            new SignUp().teacher(appendChar, 138);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 138);//log in as teacher
            new School().createWithOnlyName(appendChar, 138);//create school
            String classCode = new Classes().createClass(appendChar, 138);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 138);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 138);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsStudent(appendChar, 138);//log in as student 1
            new Navigator().navigateTo("learningstream");
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");
            new TextSend().textsendbyid("Hi all, Finish XYZ assignment this week", "html-editor-non-draggable");
            new Click().clickByid("post-submit-button");
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendCharSecondStudent, 138);//log in as student 2
            new Navigator().navigateTo("learningstream");
            actual = new TextFetch().textFetchByXpath("//div[@class = 'stream-body']");
            expected = "Hi all, Finish XYZ assignment this week";
            Assert.assertNotEquals(actual.trim(), expected, "The post from the student A must not be listed in the Learning stream page of student B");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'studentToBeAbleToPostAnyContentToInstructor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }




    @Test(priority =19,enabled= true)
    public void contentPostedByStudentToTeacherNotTobeVisibleToOtherInstructor() {
        try {

            /*Row No - 139 : "1. Login as student A
            2. Click on learning stream page
            3. Click on post
            4. Type any content
            5. Click submit
            6. Logout from the student A's account
            7. Login as an instructor from another class"*/
            //Expected - The post from the student A must not be listed in the Learning stream page of the instructor from another class

            String appendChar = "EEEEEEEEEEEE";
            String appendCharSecondStudent = "FFFFFFFFFFF";
            new SignUp().teacher(appendChar, 139);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 139);//log in as teacher
            new School().createWithOnlyName(appendChar, 139);//create school
            String classCode = new Classes().createClass(appendChar, 139);//create class
            new Navigator().logout();//log out

            String appendChar_SecondClass = "MMMMMMM";
            new SignUp().teacher(appendChar_SecondClass, 139);//Sign up as teacher
            new Login().loginAsInstructor(appendChar_SecondClass, 139);//log in as teacher
            new School().createWithOnlyName(appendChar_SecondClass, 139);//create school
            new Classes().createClass(appendChar_SecondClass, 139);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, 139);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 139);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsStudent(appendChar, 139);//log in as student 1
            new Navigator().navigateTo("learningstream");
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");
            new TextSend().textsendbyid("Hi all, Finish XYZ assignment this week", "html-editor-non-draggable");
            new Click().clickByid("post-submit-button");
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar_SecondClass, 139);//log in as teacher
            new Navigator().navigateTo("learningstream");
            actual = new TextFetch().textFetchByXpath("//div[@class = 'stream-body']");
            expected = "Hi all, Finish XYZ assignment this week";
            Assert.assertNotEquals(actual.trim(), expected, "The post from the student A must not be listed in the Learning stream page of the instructor from another class");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'studentToBeAbleToPostAnyContentToInstructor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }







    @Test(priority =20,enabled= true)
    public void studentToBeaAbleToPostContentToOtherStudentOfSameClass() {
        try {

            /*Row No - 140 : "1. Login as student A
            2. Click on learning stream page
            3. Click on post
            4. In the ""share with"" field, click 'x' on the class name
            5. Enter any student's name from the same class, and select the Student
            6. Type any content
            7. Click submit"*/
            //Expected - The post field should close and the updated post should be listed in the learning stream page

            String appendChar = "EEEEEEEEEEEE";
            String appendCharSecondStudent = "FFFFFFFFFF";
            new SignUp().teacher(appendChar, 140);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 140);//log in as teacher
            new School().createWithOnlyName(appendChar, 140);//create school
            String classCode = new Classes().createClass(appendChar, 140);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 140);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 140);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsStudent(appendChar, 140);//log in as student 1
            new Navigator().navigateTo("learningstream");
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");
            new Click().clickByXpath("//a[@class = 'closebutton']");
            String firstStudent = "stu";
            new TextSend().textsendbyclass(firstStudent,"maininput");
            new Click().clickByXpath("//ul[@id='share-with_feed']/li");
            new TextSend().textsendbyid("Hi all, Finish XYZ assignment this week", "html-editor-non-draggable");
            new Click().clickByid("post-submit-button");
            if (!(new BooleanValue().verifyNotElementPresentByXpath("//aside[@class = 'share-to-ls share-to-ls-expand']"))) {
                Assert.fail("The post field should be closed");
            }

            actual = new TextFetch().textFetchByXpath("//div[@class = 'stream-body']");
            expected = "Hi all, Finish XYZ assignment this week";
            Assert.assertEquals(actual.trim(), expected, "The updated post should be listed in the learning stream page of the Student");

            //Row No - 141
            //Expected - The Content that was posted by Student A must be listed in Student B's learning stream page
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendCharSecondStudent, 140);//log in as student 2
            new Navigator().navigateTo("learningstream");
            actual = new TextFetch().textFetchByXpath("//div[@class = 'stream-body']");
            expected = "Hi all, Finish XYZ assignment this week";
            Assert.assertEquals(actual.trim(), expected, "The Content that was posted by Student A must be listed in Student B's learning stream page");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'studentToBeAbleToPostAnyContentToInstructor' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }





    @Test(priority =21,enabled= true)
    public void studentsPostToOtherStudentShouldBeVisibleToIntendentStudent() {
        try {

            //Row No - 142
            //Expected - The Post from Student A must not be listed in the Learning stream page

            String appendChar = "AAA";
            String appendCharSecondStudent = "BBB";
            String appendCharThirdStudent = "CCC";
            new SignUp().teacher(appendChar, 142);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 142);//log in as teacher
            new School().createWithOnlyName(appendChar, 142);//create school
            String classCode = new Classes().createClass(appendChar, 142);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 142);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 142);//create student2
            new Navigator().logout();//log out*/
            new SignUp().studentDirectRegister(appendCharThirdStudent, classCode, 142);//create student3
            new Navigator().logout();//log out*/
            new Login().loginAsStudent(appendChar, 142);//log in as student 1
            new Navigator().navigateTo("learningstream");
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");
            new Click().clickByXpath("//a[@class = 'closebutton']");
            String firstStudent = "studentsPostToOtherStudentShouldBeVisibleToIntendentStudentstBBB";
            new TextSend().textsendbyclass(firstStudent,"maininput");
            Thread.sleep(5000);
            new Click().clickByXpath("//ul[@id='share-with_feed']/li");
            new TextSend().textsendbyid("Hi all, Finish XYZ assignment this week", "html-editor-non-draggable");
            new Click().clickByid("post-submit-button");

            new Navigator().logout();//log out
            new Login().loginAsStudent(appendCharThirdStudent, 142);//log in as student 2
            new Navigator().navigateTo("learningstream");
            actual = new TextFetch().textFetchByXpath("//div[@class = 'stream-body']");
            expected = "Hi all, Finish XYZ assignment this week";
            Assert.assertNotEquals(actual.trim(), expected, "The Post from Student A must not be listed in the Learning stream page");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'studentsPostToOtherStudentShouldBeVisibleToIntendentStudent' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }





    @Test(priority =21,enabled= true)
    public void studentsPostToOtherStudentShouldBeVisibleToIntendentStudentRow143() {
        try {

            //Row No - 143
            //Expected - The Post from Student must not be listed in the Learning stream

            String appendChar = "A";
            String appendCharSecondStudent = "B";
            new SignUp().teacher(appendChar, 143);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 143);//log in as teacher
            new School().createWithOnlyName(appendChar, 143);//create school
            String classCode = new Classes().createClass(appendChar, 143);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 143);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 143);//create student2
            new Navigator().logout();//log out*/
            String appendChar_SecondClass = "C";
            new SignUp().teacher(appendChar_SecondClass, 143);//Sign up as teacher
            new Login().loginAsInstructor(appendChar_SecondClass, 143);//log in as teacher
            new School().createWithOnlyName(appendChar_SecondClass, 143);//create school
            new Classes().createClass(appendChar_SecondClass, 143);//create class
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, 143);//log in as student 1
            new Navigator().navigateTo("learningstream");
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");
            new Click().clickByXpath("//a[@class = 'closebutton']");
            String firstStudent = "stu";
            new TextSend().textsendbyclass(firstStudent,"maininput");
            Thread.sleep(5000);
            new Click().clickByXpath("//ul[@id='share-with_feed']/li");
            Thread.sleep(1000);
            new TextSend().textsendbyid("Hi all, Finish XYZ assignment this week", "html-editor-non-draggable");
            new Click().clickByid("post-submit-button");
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar_SecondClass, 143);//log in as teacher
            new Navigator().navigateTo("learningstream");
            actual = new TextFetch().textFetchByXpath("//div[@class = 'stream-body']");
            expected = "Hi all, Finish XYZ assignment this week";
            Assert.assertNotEquals(actual.trim(), expected, "The Post from Student must not be listed in the Learning stream ");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'studentsPostToOtherStudentShouldBeVisibleToIntendentStudentRow143' in class 'LearningStreamTester_StudentLogin'", e);
        }
    }





    /*@AfterMethod
    public void TearDown()throws Exception
    {
        driver.quit();
    }*/



    public void validateLinkFormFields(){
        try{
            if (new BooleanValue().verifyNotElementPresentByXpath("//section[@id='redactor-modal-link-insert']//label[text() = 'URL']")) {
                Assert.fail("Url Filed is not appeared in 'Link' Form");
            }
            if (new BooleanValue().verifyNotElementPresentByXpath("//section[@id='redactor-modal-link-insert']//label[text() = 'Text']")) {
                Assert.fail("Text Filed is not appeared in 'Link' Form");
            }
            if (driver.findElements(By.xpath("//section[@id='redactor-modal-link-insert' ]//label[3]")).size() != 0) {
                List<WebElement> checkBoxList = driver.findElements(By.xpath("//section[@id='redactor-modal-link-insert' ]//label[3]"));
                if (!(checkBoxList.get(0).findElement(By.tagName("input")).getAttribute("type").equals("checkbox"))) {
                    Assert.fail("Open link in new tab check box is not appeared");
                }
                actual = driver.findElement(By.xpath("//section[@id='redactor-modal-link-insert' ]//label[3]")).getText();
                expected = "Open link in new tab";
                Assert.assertEquals(actual.trim(), expected, "Open link in new tab check box is not appeared");
            } else {
                Assert.fail("Open link in new tab check box is not appeared");
            }
            if (new BooleanValue().verifyNotElementPresentByXpath("//button[normalize-space(@class) ='redactor_modal_btn redactor_btn_modal_close' and text() = 'Cancel']")) {
                Assert.fail("Cancel button is not appeared in 'Link' Form");
            }
            if (new BooleanValue().verifyNotElementPresentByXpath("//button[@id='redactor_insert_link_btn' and text() = 'Insert']")) {
                Assert.fail("Insert button is not appeared in 'Link' Form");
            }

        }catch(Exception e){
            Assert.fail("Exception in method 'validateLinkFormFields' in the class 'LearningStreamTester_StudentLogin'",e);
        }
    }


}
