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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by snapwiz on 25-Nov-14.
 */
public class LearningStreamTester extends Driver {
    String actual = null;
    String expected = null;

    @Test(priority =1,enabled= true)
    public void signUpAndviewLearningStreamPageAsAuthor() {
        try {
            String appendChar = "NMBV";
            new SignUp().teacher(appendChar, 3);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 3);//log in as teacher
            new School().createWithOnlyName(appendChar, 3);//create school
            new Classes().createClass(appendChar, 3);//create class
            new Navigator().navigateTo("learningstream");
            actual = new TextFetch().textfetchbyclass("discussion-details");
            if(!(actual.contains("Welcome to Edulastic!"))){
                Assert.fail("Learning Stream page should be displayed with the default message posted by edulastic");
            }

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'signUpAndviewLearningStreamPageAsAuthor' in class 'LearningStreamTester'", e);
        }
    }


    @Test(priority =2,enabled= true)
    public void loginAndNavigteToLSPageAndWritePostAsAuthor() {
        try {
                    /*Steps : "1. Login as an instructor
                    2. Click on learning stream"*/
            //Expected - "1.Learning stream page should be displayed.
            String appendChar = "j";
            String appendCharSecondStudent = "k";
            new SignUp().teacher(appendChar, 3);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 3);//log in as teacher
            new School().createWithOnlyName(appendChar, 3);//create school
            String classCode = new Classes().createClass(appendChar, 3);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 3);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 3);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsInstructor(appendChar, 3);//log in as teacher
            new Navigator().navigateTo("learningstream");
            actual = new TextFetch().textFetchByXpath("//div[normalize-space(@class) ='center header-title']");
            expected = "LEARNING STREAM";
            Assert.assertEquals(actual.trim(), expected, "Learning stream page is not displayed");

            //Expected - 2. Older posts for the selected class should be listed (if any).
            //Expected - 3.Post filed should be displayed on the right side top corner of the page
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']")) {
                Assert.fail("Post filed is not displayed on the right side top corner of the page");
            }

            ///Steps : 3. Click on the "post" displayed on the right side top corner
                   /* Expected "The following fields should be displayed
                    1.""Post field"" should be expanded and the instructor should be able to enter
                    2.content into the text field.
                    3.Test input option selection must be displayed
                    4.""Share with"" field should be displayed with current class as the default selection
                    5. Cancel button
                    6. Submit button"*/


            //1.""Post field"" should be expanded and the instructor should be able to enter
            //2.content into the text field.
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");
            if (new BooleanValue().verifyNotElementPresentByXpath("//aside[@class = 'share-to-ls share-to-ls-expand']")) {
                Assert.fail("Post fieled is not displayed on the right side top corner of the page");
            }


            new TextSend().textsendbyid("The instructor should be able to enter the content into the text field", "html-editor-non-draggable");
            actual = new TextFetch().textFetchByXpath("//div[@id='html-editor-non-draggable']/p");
            expected = "The instructor should be able to enter the content into the text field";
            Assert.assertEquals(actual.trim(), expected, "The instructor is not able to enter the content into the text field");


            //Expected -  3.Test input option selection must be displayed
            new Click().clickByclassname("ls-shareImg");
            if (new BooleanValue().verifyNotElementPresentByXpath("//ul[starts-with(@id,'redactor_toolbar_')]")) {
                Assert.fail("Test input option selection must be displayed");
            }


            //Expected - 4.""Share with"" field should be displayed with current class as the default selection
            actual = new TextFetch().textfetchbyclass("item-text");
            String appendCharacterBuild=System.getProperty("UCHAR");
            expected = "loginAndNavigteToLSPageAndWritePostAsAuthorclass" + appendChar+appendCharacterBuild;
            Assert.assertEquals(actual.trim(), expected, "Share with field should is not displayed with current class as the default selection");


            //Expected -  5. Cancel button
            if (new BooleanValue().verifyNotElementPresentByXpath("//a[@id='post-submit-cancel-button']")) {
                Assert.fail("Cancel Button not found in Post Form");
            }


            //Expected -   6. Submit button"*/
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@id='post-submit-button']")) {
                Assert.fail("Cancel Button not found in Post Form");
            }

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'loginAndNavigteToLSPageAndWritePostAsAuthor' in class 'LearningStreamTester'", e);
        }
    }


    @Test(priority =3,enabled= true)
    public void loginAndNavigteToLSPageAndAbleToSubmitPostAsAuthor() {
        try {

           /* Row No - 7 : "1. Login as an instructor
            2. Click on learning stream
            3. Click on the ""post"" displayed on the right side top corner.
            4. Write any content in the text field
            5. Let the ""Share with"" be default class
                6. click on submit
                "*/
            //Expected - The post field should close and the updated post should be listed in the learning stream page of the instructor
            String appendChar = "j";
            String appendCharSecondStudent = "k";
            new SignUp().teacher(appendChar, 7);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 7);//log in as teacher
            new School().createWithOnlyName(appendChar, 7);//create school
            String classCode = new Classes().createClass(appendChar, 7);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 7);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 7);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsInstructor(appendChar, 7);//log in as teacher
            new Navigator().navigateTo("learningstream");
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");
            new TextSend().textsendbyid("Hi all, Finish XYZ assignment this week", "html-editor-non-draggable");
            new Click().clickByid("post-submit-button");
            if (!(new BooleanValue().verifyNotElementPresentByXpath("//aside[@class = 'share-to-ls share-to-ls-expand']"))) {
                Assert.fail("The post field should be closed");
            }
            actual = new TextFetch().textFetchByXpath("//div[@class = 'stream-body']");
            expected = "Hi all, Finish XYZ assignment this week";
            Assert.assertEquals(actual.trim(), expected, "The updated post should be listed in the learning stream page of the instructor");



                    /*Row - 8  : "7. Logout as instructor
                    8. Login as student A of the relevant class
                    9. click on learning stream"*/
            //Expected - The content that was posted by the instructor should be listed on student A's learning stream page
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, 7);//log in as student 1
            new Navigator().navigateTo("learningstream");
            actual = new TextFetch().textFetchByXpath("//div[@class = 'stream-body']");
            expected = "Hi all, Finish XYZ assignment this week";
            Assert.assertEquals(actual.trim(), expected, "The updated post by Instructor should be listed in the learning stream page of the Student");




                   /* Row 9 -  "10. Logout and login as another student B of the same class
                    11. Click on learning stream"*/
            //Expected - The content that was posted by the instructor should be listed on student B's learning stream page
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendCharSecondStudent, 7);//log in as student 1
            new Navigator().navigateTo("learningstream");
            actual = new TextFetch().textFetchByXpath("//div[@class = 'stream-body']");
            expected = "Hi all, Finish XYZ assignment this week";
            Assert.assertEquals(actual.trim(), expected, "The updated post by Instructor should be listed in the learning stream page of the Student");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'loginAndNavigteToLSPininstructorageAndAbleToSubmitPostAsAuthor' in class 'LearningStreamTester'", e);
        }
    }


    @Test(priority =4,enabled= true)
    public void useBoldAndItalicsTextFormatInPostWindowAsAuthor() {
        try {
                   /* Row - 10 :"1. Login as an instructor
                    2. Click on learning stream
                    3. Click on the ""post"" displayed on the right side top corner
                    4. Click on  ""T"" icon
                    "*/
                   /* Expected - "The following options should be displayed in the pop up menu
                    1. Bold
                    2. Italic
                    3. Unordered list
                    4. Ordered list
                    5. Left indent
                    6. Right indent
                    7. Link
                    8. Alignment
                    9. Subscript
                    10. Superscript
                    11. Math editor
                    12. Undo
                    13. Redo"*/

            String appendChar = "jj";
            String appendCharSecondStudent = "kk";
            new SignUp().teacher(appendChar, 10);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 10);//log in as teacher
            new School().createWithOnlyName(appendChar, 10);//create school
            String classCode = new Classes().createClass(appendChar, 10);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 10);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 10);//create student2
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, 10);//log in as teacher
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





             /*Row No - 11 : "5. Select Bold ('B') from the menu below
             6. Type in some content"*/
             //Expected - The content should be typed in Bold font
            toolsList.get(0).click();
            new TextSend().textsendbyid("YesItIsBold", "html-editor-non-draggable");
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@id='html-editor-non-draggable']/b[text() = 'YesItIsBold']")) {
                Assert.fail("The content has not been typed in Bold font");
            }







                   /* Row 12 : "7. Unselect Bold and select Italics ('I')
                    8. Type in some content"*/
            //Expected : The content should be typed in Italics font
            driver.findElement(By.id("html-editor-non-draggable")).clear();
            toolsList.get(0).click();
            toolsList.get(1).click();
            new TextSend().textsendbyid("YesItIsItalics", "html-editor-non-draggable");
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@id='html-editor-non-draggable']/i[text() = 'YesItIsItalics']")) {
                Assert.fail("The content has not been typed in Italics font");
            }


                   /* Row No - 13 : "9. Select Both Bold and italics icon
                    10. Type in some content"*/
            //Expected -  The content should be displayed in both Bold and Italics format

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'useBoldAndItalicsTextFormatInPostWindowAsAuthor' in class 'LearningStreamTester'", e);
        }
    }


    @Test(priority =5,enabled= true)
    public void useBulletsUnorderedAndOrderedListInPostWindowAsAuthor() {
        try {
                        /*Row - 14 : "1. Login as an instructor
                    2. Click on learning stream
                    3. Click on the ""post"" displayed on the right side top corner
                    4. Click on  ""T"" icon
                    5. Select 'Unordered list' icon
                    6. Type in some content
                    7. Press enter and type more content"*/
            //Expected - The text should appear in an unordered list format with Bullets

            String appendChar = "Alter";
            String appendCharSecondStudent = "tetlA";
            new SignUp().teacher(appendChar, 14);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 14);//log in as teacher
            new School().createWithOnlyName(appendChar, 14);//create school
            String classCode = new Classes().createClass(appendChar, 14);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 14);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 14);//create student2
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, 14);//log in as teacher
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            Thread.sleep(5000);
            new Click().clickBycssselector("a.re-icon.re-unorderedlist");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//div[@id='html-editor-non-draggable']/ul/li")).sendKeys("YesItIsBoldUnOrderedList_1");
            driver.findElement(By.xpath("//body")).sendKeys(Keys.ENTER);
            new TextSend().textsendbyid("YesItIsBoldUnOrderedList_2", "html-editor-non-draggable");
            String text = driver.findElement(By.xpath("//div[@id='html-editor-non-draggable']/p")).getText();
            System.out.println("text : " + text);
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@id='html-editor-non-draggable']/p")) {
                Assert.fail("The text should is not appeared in an unordered list format with Bullets");
            }



            //Row - 15 : 8. Select 'Ordered list' icon
            //Expected - The 'Unordered list cont should be deselected
            new Click().clickBycssselector("a.re-icon.re-orderedlist");
            if (driver.findElement(By.cssSelector("a.re-icon.re-unorderedlist")).isSelected()) {
                Assert.fail("The 'Unordered list cont is not deselected");
            }



                    /*Row - 16 : "9.Type in some some content
                    10. Press enter and type in some more content"*/
            //Expected  - The text should appear in an ordered list format with numbers
//            new TextSend().textsendbyid("YesItIsBoldOrderedList_1", "html-editor-non-draggable");
//            driver.findElement(By.xpath("//div[@id='html-editor-non-draggable']/ol/li")).sendKeys(Keys.ENTER);
//            new TextSend().textsendbyid("YesItIsBoldOrderedList_2", "html-editor-non-draggable");
//            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@id='html-editor-non-draggable']/ol/li")) {
//                Assert.fail("The text should is not appeared in an ordered list format with numbers");
//            }

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'useBulletsUnorderedAndOrderedListInPostWindowAsAuthor' in class 'LearningStreamTester'", e);
        }
    }


    @Test(priority =6,enabled= true)
    public void insertURLInTextFieldOfPostAsAuthor() {
        try {
                    /*Row N0 - 19 : "1. Login as an instructor
                    2. Click on learning stream
                    3. Click on the ""post"" displayed on the right side top corner
                    4. Click on  ""T"" icon
                    5. Click on ""Link"" button"*/

            String appendChar = "j";
            String appendCharSecondStudent = "k";
            new SignUp().teacher(appendChar, 19);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 19);//log in as teacher
            new School().createWithOnlyName(appendChar, 19);//create school
            String classCode = new Classes().createClass(appendChar, 19);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 19);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 19);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsInstructor(appendChar, 19);//log in as teacher
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


            //Row No - 20 : 6. Select "Insert link"
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



                  /*  Row No  -21 : "7. Type in any url (www.google.com for example)
                    8. Type anything in text field that has to be displayed for the url ( 'Google' for example)
                    9. Click on ""Insert"" "*/
            //Expected  - The link text entered for the URL should be displayed in the post edit field with blue color font
            new TextSend().textsendbyid("https://www.google.co.in/", "redactor_link_url");
            new TextSend().textsendbyid("Google", "redactor_link_url_text");
            new Click().clickByid("redactor_insert_link_btn");
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@id  = 'html-editor-non-draggable']//a[text() = 'Google']")) {
                Assert.fail("The link text entered for the URL has not been displayed in the post edit field with blue color font");
            }



            //Row - 22 : 10. Click submit
            //Expected - The post should be displayed with the entered text for the URL
            new Click().clickByid("post-submit-button");
            if (new BooleanValue().verifyNotElementPresentByXpath("//div[@class='body-wrapper']//a[text() = 'Google']")) {
                Assert.fail("The post has not displayed with the entered text for the URL");
            }



            //Row No -23 :  11. Click on the Inserted URL displayed on the post
            //Expected 1 - "1. Should navigate to the specified webpage whose link is provided"
            //expected 2 -  2. The web page should open in the same tab
            new Click().clickByXpath("//div[@class='body-wrapper']//a[text() = 'Google']");
            actual = driver.getCurrentUrl();
            expected = "https://www.google.co.in/";
            Assert.assertEquals(actual.trim(), expected, "The Posted Link is not navigated to the specified webpage whose link is provided");
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'insertURLInTextFieldOfPostAsAuthor' in class 'LearningStreamTester'", e);
        }
    }


    @Test(priority =7,enabled= true)
    public void insertURLInTextFieldOfPostAndLinkToBeOpenedInNewTabAsAuthor() {
        try {
            //Row No - 25
            //Expected 1 - 1. Should navigate to the specified webpage whose link is provided
            //Expected 2 - 2. The web page should open in new tab

            String appendChar = "j";
            String appendCharSecondStudent = "k";
            new SignUp().teacher(appendChar, 25);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 25);//log in as teacher
            new School().createWithOnlyName(appendChar, 25);//create school
            String classCode = new Classes().createClass(appendChar, 25);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 25);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 25);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsInstructor(appendChar, 25);//log in as teacher
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
            Assert.fail("Exception in testcase 'insertURLInTextFieldOfPostAndLinkToBeOpenedInNewTabAsAuthor' in class 'LearningStreamTester'", e);
        }
    }


    @Test(priority =8,enabled= true)
    public void unLinkAnyURLFromPostAsAuthor() {
        try {
            //Row NO - 27
            //Expected - The link text entered for the URL should be displayed in the post edit field with blue color font
            String appendChar = "jjj";
            String appendCharSecondStudent = "kkk";
            new SignUp().teacher(appendChar, 27);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 27);//log in as teacher
            new School().createWithOnlyName(appendChar, 27);//create school
            String classCode = new Classes().createClass(appendChar, 27);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 27);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 27);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsInstructor(appendChar, 27);//log in as teacher
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
            /*Point location = driver.findElement(By.xpath("//a[contains(text(),'GoogleIndia')]")).getLocation();
            Dimension size = driver.findElement(By.xpath("//a[contains(text(),'GoogleIndia')]")).getSize();
            System.out.println("location : " + location);
            System.out.println("size : " + size);*/


            //Row No - 28 :Select the text of the URL and click "Link" Button from the menu below
            /*Expected - "A pop up must be displayed with the following options
            1. Insert Link
            2. Unlink"*/
//            WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'GoogleIndia')]")));
//            Actions actions = new Actions(driver);
//            actions.moveToElement(element, 16, 16)
//                    .clickAndHold()
//                    .moveByOffset(76, 76)
//                    .release()
//                    .perform();
//            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-link']");//Click on Link Icon
//            Thread.sleep(5000);
//
//            if (driver.findElements(By.xpath("//div[normalize-space(@class)='redactor_dropdown redactor_dropdown_box_link']")).size() != 0) {
//                if (new BooleanValue().verifyNotElementPresentByXpath("//a[normalize-space(@class) = 'redactor_dropdown_link' and text() = 'Edit link']")) {
//                    Assert.fail("The Insert Link is not appeared in an ordered list format with numbers");
//                }
//                if (new BooleanValue().verifyNotElementPresentByXpath("//a[normalize-space(@class) = 'redactor_dropdown_unlink' and text() = 'Unlink']")) {
//                    Assert.fail("The Unlink Link is not appeared in an ordered list format with numbers");
//                }
//            } else {
//                Assert.fail("popup has not appeared with the following option");
//
//            }
//
//
//
//
//            //Row No  - 29 : Click on "Unlink"
//            //Expected - The selected link text should no longer be in blue and the it should be unlinked
//            new Click().clickByXpath("//a[normalize-space(@class) = 'redactor_dropdown_unlink' and text() = 'Unlink']");
//            if (!(new BooleanValue().verifyNotElementPresentByXpath("//div[@id='html-editor-non-draggable'] / a[text() = 'GoogleIndia​']"))) {
//                Assert.fail("The selected link text is in blue and the it is not unlinked");
//            }

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'unLinkAnyURLFromPostAsAuthor' in class 'LearningStreamTester'", e);
        }
    }






    @Test(priority =9,enabled= true)
    public void unLinkAnyURLFromPostUsingUnlinkOptionInPostEditFieldAsAuthor() {
        try {
            //Row No-30
            //Expected - The text entered for the URL should be displayed in the post edit field with blue color font
            String appendChar = "j";
            String appendCharSecondStudent = "k";
            new SignUp().teacher(appendChar, 30);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 30);//log in as teacher
            new School().createWithOnlyName(appendChar, 30);//create school
            String classCode = new Classes().createClass(appendChar, 30);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 30);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 30);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsInstructor(appendChar, 30);//log in as teacher
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


          /* Row No - 31 : 11. Click on the Link text on the post edit field
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



            //Row No  - 32 : Click on "Unlink"
            //Expected - The selected link text should no longer be in blue and the it should be unlinked
            driver.findElement(By.xpath("//span[normalize-space(@class)='redactor-link-tooltip']//a[ text() = 'Unlink']")).click();
            Thread.sleep(2000);
            if (!(new BooleanValue().verifyNotElementPresentByXpath("//div[@id='html-editor-non-draggable'] / a[text() = 'GoogleIndia​']"))) {
                Assert.fail("The selected link text is in blue and the it is not unlinked");
            }

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'unLinkAnyURLFromPostUsingUnlinkOptionInPostEditFieldAsAuthor' in class 'LearningStreamTester'", e);
        }
    }




    @Test(priority =10,enabled= true)
    public void removeLinkAnyURLFromPostUsingBackSpaceAsAuthor() {
        try {
            //Row No-33
            //Expected - The text entered for the URL should be displayed in the post edit field with blue color font
            String appendChar = "j";
            String appendCharSecondStudent = "k";
            new SignUp().teacher(appendChar, 33);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 33);//log in as teacher
            new School().createWithOnlyName(appendChar, 33);//create school
            String classCode = new Classes().createClass(appendChar, 33);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 33);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 33);//create student2
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, 33);//log in as teacher
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



            //Row No - 34 : 11. Backspace the Link text entirely
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

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'removeLinkAnyURLFromPostUsingBackSpaceAsAuthor' in class 'LearningStreamTester'", e);
        }
    }




    @Test(priority =11,enabled= true)
    public void editLinkFromPostUsingEditOptionAsAnAuthor() {
        try {
            //Row No-35
            //Expected - The text entered for the URL should be displayed in the post edit field with blue color font
            String appendChar = "jjj";
            String appendCharSecondStudent = "kkk";

            new SignUp().teacher(appendChar, 35);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 35);//log in as teacher
            new School().createWithOnlyName(appendChar, 35);//create school
            String classCode = new Classes().createClass(appendChar, 35);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 35);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 35);//create student2
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, 35);//log in as teacher*/
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



             /* Row No - 36 : 11. Click on the Link text on the post edit field
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


            //Row No-37 : 12. Click on "Edit" option
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



           /*Row No - 38 : "13. Make changes in any of the field
            14. Click on insert"*/
            //Expected - The changes should reflect and in case of change of URL, It should now navigate to the new URL
            /*driver.findElement(By.id("redactor_link_url")).clear();
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
            Assert.fail("Exception in testcase 'editLinkFromPostUsingEditOptionAsAnAuthor' in class 'LearningStreamTester'", e);
        }
    }



    @Test(priority =12,enabled= true)
    public void navigateToURLWhenClickedLinkInPostEdditFieldAsAnAuthor() {
        try {
            //Row No-39
            //Expected - The text entered for the URL should be displayed in the post edit field with blue color font
            String appendChar = "j";
            String appendCharSecondStudent = "k";
            new SignUp().teacher(appendChar, 39);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 39);//log in as teacher
            new School().createWithOnlyName(appendChar, 39);//create school
            String classCode = new Classes().createClass(appendChar, 39);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 39);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 39);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsInstructor(appendChar, 39);//log in as teacher*/
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



             /* Row No - 40 : 11. Click on the Link text on the post edit field
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



            //RowNo - 41 : 12. Click on URL
            //Expected - The Webpage that the URL points to should be opened in a new tab
            driver.findElement(By.xpath("//span[normalize-space(@class)='redactor-link-tooltip']//a[ text() = 'https://www.google.co.in...']")).click();
            List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
            driver.switchTo().window(browserTabs .get(1));
            actual = driver.getCurrentUrl();
            expected = "https://www.google.co.in/";
            Assert.assertEquals(actual.trim(), expected, "The Webpage that the URL points is not opened in a new tab");
            System.out.println("Actual window URL : " + actual);
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'navigateToURLWhenClickedLinkInPostEdditFieldAsAnAuthor' in class 'LearningStreamTester'", e);
        }
    }








    @Test(priority =13,enabled= true)
    public void cancelButtonOfLinkTextToBeWorkedOnInstructorsSide() {
        try {
            //Row No-42
            //Expected - The Link text should not appear on the post edit field and the pop up should be closed
            String appendChar = "j";
            String appendCharSecondStudent = "k";
            new SignUp().teacher(appendChar, 42);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 42);//log in as teacher
            new School().createWithOnlyName(appendChar, 42);//create school
            String classCode = new Classes().createClass(appendChar, 42);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 42);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 42);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsInstructor(appendChar, 42);//log in as teacher*/
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
            Assert.fail("Exception in testcase 'cancelButtonOfLinkTextToBeWorkedOnInstructorsSide' in class 'LearningStreamTester'", e);
        }
    }






    @Test(priority =14,enabled= true)
    public void useUndoAndRedoButtonOfTextFormatEditor() {
        try {
            //Row No-54
            //Expected - The text and the content should appear on the 'Text editor' field
            String appendChar = "j";
            new SignUp().teacher(appendChar, 54);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 54);//log in as teacher
            new School().createWithOnlyName(appendChar, 54);//create schoolS
            new Classes().createClass(appendChar, 54);//create class
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, 54);//log in as teacher
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            new TextSend().textsendbyid("TypeAnyContent","html-editor-non-draggable");
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-matheditor redactor-btn-image']");
            Thread.sleep(5000);
            new QuestionCreate().enterValueInMathMLEditor("Square root","4");



            //Row No  - 7. Click on Undo button
            //Expected -    The last operation should be undone
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-undo redactor-btn-image']");




            //Row No  - 8. Click on Redo button
            //Expected -   The last undone operation must be redone
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-redo redactor-btn-image']");





        } catch (Exception e) {
            Assert.fail("Exception in testcase 'useUndoAndRedoButtonOfTextFormatEditor' in class 'LearningStreamTester'", e);
        }
    }


    @Test(priority =15,enabled= true)
    public void useAllOptionsAvailableForMathEditorAsInstructor() {
        try {
            //Row No-57
            //Expected - 'The following options should be displayed in the pop up menu
            String appendChar = "j";
            new SignUp().teacher(appendChar, 57);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 57);//log in as teacher
            new School().createWithOnlyName(appendChar, 57);//create school
            new Classes().createClass(appendChar, 57);//create class
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, 57);//log in as teacher*/
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



            //Row No -58 : 5. Click on Math editor
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
            Assert.fail("Exception in testcase 'useAllOptionsAvailableForMathEditorAsInstructor' in class 'LearningStreamTester'", e);
        }
    }




    @Test(priority =16,enabled= false)
    public void formulateAnyMathematicalExpressionsInEditorAndUseItInPostAsInstructor() {
        try {
            //Row No-59
            //Expected - 'The mathematical expression should be displayed on the post  edit field in the right format
            String appendChar = "jJ";
            new SignUp().teacher(appendChar, 59);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 59);//log in as teacher
            new School().createWithOnlyName(appendChar, 59);//create school
            new Classes().createClass(appendChar, 59);//create class
            new Navigator().logout();//log out*/
            new Login().loginAsInstructor(appendChar, 59);//log in as teacher
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            new Click().clickByXpath("//a[normalize-space(@class) = 're-icon re-matheditor redactor-btn-image']");//Click on 'Math Editor'
            new Click().clickByXpath("//img[normalize-space(@title)='Square root']");//Click on  Square root Icon
            driver.findElement(By.xpath("//input[@class='wrs_focusElement']")).sendKeys("4");// Type 4
            Thread.sleep(3000);
            new Click().clickByXpath("(//span[@class='ui-button-text' and text() = 'Save'])[1]");//Click on Save Button
            //driver.findElement(By.xpath("(//span[@class='ui-button-text' and text() = 'Save'])[1]")).click();;

            /*WebElement we=driver.findElement(By.xpath("(//span[@class='ui-button-text' and text() = 'Save'])[1]"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);

            Thread.sleep(2000);*/
            //new QuestionCreate().enterValueInMathMLEditor("Square root","4");
            Thread.sleep(5000);
            actual = new TextFetch().textfetchbyid("html-editor-non-draggable");
            expected = "√4";
            System.out.println("Actual Square :" + actual);
            Assert.assertEquals(actual.trim(), expected, "The mathematical expression is not displayed on the post edit field in the right format");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'formulateAnyMathematicalExpressionsInEditorAndUseItInPostAsInstructor' in class 'LearningStreamTester'", e);
        }
    }





    @Test(priority =17,enabled= true)
    public void deleteAnyMathematicalExpressionsInEditorAndUseItInPostAsInstructor() {
        try {
            //Row No-60
            //Expected - The formula in the math editor pop up should be deleted
            String appendChar = "j";
            new SignUp().teacher(appendChar, 60);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 60);//log in as teacher
            new School().createWithOnlyName(appendChar, 60);//create school
            new Classes().createClass(appendChar, 60);//create class
            new Navigator().logout();//log out*/
            new Login().loginAsInstructor(appendChar, 60);//log in as teacher
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
            Assert.fail("Exception in testcase 'deleteAnyMathematicalExpressionsInEditorAndUseItInPostAsInstructor' in class 'LearningStreamTester'", e);
        }
    }





    @Test(priority =18,enabled= true)
    public void cancelAnyMathematicalOperationsInEditUsingCancelButtonAsInstructor() {
        try {
            //Row No-61
            //Expected - The pop up should be closed and the expression formulated on math editor should not appear on the post edit field
            String appendChar = "j";
            new SignUp().teacher(appendChar, 61);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 61);//log in as teacher
            new School().createWithOnlyName(appendChar, 61);//create school
            new Classes().createClass(appendChar, 61);//create class
            new Navigator().logout();//log out*/
            new Login().loginAsInstructor(appendChar, 61);//log in as teacher
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
            Assert.fail("Exception in testcase 'cancelAnyMathematicalOperationsInEditUsingCancelButtonAsInstructor' in class 'LearningStreamTester'", e);
        }
    }




    @Test(priority =19,enabled= true)
    public void useUndoAndRedoButtonInmathEditorPopUpAsInstructor() {
        try {
            //Row No-62
            //Expected - The last operation should be undone in the popup
            String appendChar = "j";
            new SignUp().teacher(appendChar, 62);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 62);//log in as teacher
            new School().createWithOnlyName(appendChar, 62);//create school
            new Classes().createClass(appendChar, 62);//create class
            new Navigator().logout();//log out*/
            new Login().loginAsInstructor(appendChar, 62);//log in as teacher
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


            //Row N0 - 63 :8. Click on "Redo" option
            //Expected  - The last undone operation must be redone
            new Click().clickByXpath("//button[@title = 'Redo']");//Click on Redo Button
            Thread.sleep(5000);
            actual = new TextFetch().textFetchByXpath("//span[@class='wrs_container']");
            expected = "4";
            System.out.println("Actual Square :" + actual);
            Assert.assertEquals(actual.trim(), expected, "The last undone operation must be redone");
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'useUndoAndRedoButtonInmathEditorPopUpAsInstructor' in class 'LearningStreamTester'", e);
        }
    }




    @Test(priority =20,enabled= true)
    public void postShouldBeLimitedToParticularClassIfHePostsToClassAsInstructor() {
        try {
            //Row No-64
            //Expected - The post field should close and the updated post should be listed in the learning stream page
            String appendChar = "j";
            String appendCharSecondStudent = "k";
            new SignUp().teacher(appendChar, 64);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 64);//log in as teacher
            new School().createWithOnlyName(appendChar, 64);//create school
            String classCode = new Classes().createClass(appendChar, 64);//create class
            new Classes().createNewClass(appendCharSecondStudent,64);
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 64);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 64);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsInstructor(appendChar, 64);//log in as teacher*
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new TextSend().textsendbyid("The content that was posted in previous class should not be seen here", "html-editor-non-draggable");
            new Click().clickByid("post-submit-button");
            if (!(new BooleanValue().verifyNotElementPresentByXpath("//aside[@class = 'share-to-ls share-to-ls-expand']"))) {
                Assert.fail("The post field should be closed");
            }
            actual = new TextFetch().textFetchByXpath("//div[@class = 'stream-body']");
            expected = "The content that was posted in previous class should not be seen here";
            Assert.assertEquals(actual.trim(), expected, "The updated post should be listed in the learning stream page of the instructor");


            //Row no- 65 : 7. Change the class and open the learning stream for that class
            //Expected - The content that was posted in previous class should not be seen here
            String appendCharacterBuild=System.getProperty("UCHAR");
            String newClassName = "postShouldBeLimitedToParticularClassIfHePostsToClassAsInstructorclass"+appendChar+appendCharacterBuild;
            new Select(driver.findElement(By.className("as-header-classes-selectbox"))).selectByVisibleText(newClassName);
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'"Class9"


            actual = new TextFetch().textFetchByXpath("//div[@class = 'stream-body']");
            expected = "The content that was posted in previous class should not be seen here";
            Assert.assertNotEquals(actual.trim(), expected, "The content that was posted in previous class should not be seen in current class");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'postShouldBeLimitedToParticularClassIfHePostsToClassAsInstructor' in class 'LearningStreamTester'", e);
        }
    }





    @Test(priority =21,enabled= true)
    public void  ableToUseBoldAndItalicsTextFormatInPostWindowAsAuthor() {
        try {
            //Row No - 85
            //Expected - 'The following options should be displayed in the pop up menu
            String appendChar = "j";
            String appendCharSecondStudent = "k";
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
            Assert.fail("Exception in testcase 'ableToUseBoldAndItalicsTextFormatInPostWindowAsAuthor' in class 'LearningStreamTester'", e);
        }
    }






    @Test(priority =22,enabled= false)
    public void instructorToBeAbleToUseSubscripText() {
        try {
            /*Row No - 48 : "1. Login as an instructor
            2. Click on learning stream
            3. Click on the ""post"" displayed on the right side top corner
            4. Click on  ""T"" icon
            5. Type in some content
            6. Select Subscript Button
            7. Continue typing"*/
            //Expected - The Content typed after selecting "Subscript" Button should appear as a subscript to the content typed prior to selecting subscript
            String appendChar = "j";
            String appendCharSecondStudent = "k";
            new SignUp().teacher(appendChar, 48);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 48);//log in as teacher
            new School().createWithOnlyName(appendChar, 48);//create school
            String classCode = new Classes().createClass(appendChar, 48);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 48);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 48);//create student2
            new Navigator().logout();//log out*/
            new Login().loginAsInstructor(appendChar, 48);//log in as teacher*/
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new Click().clickByclassname("ls-shareImg");//Click on 'T' Icon
            new TextSend().textsendbyid("BeforeUsingSubScript", "html-editor-non-draggable");
            Thread.sleep(5000);
            //new Click().clickBycssselector("a.re-icon.re-subscript redactor-btn-image");
            List<WebElement> toolsList = driver.findElements(By.xpath("//ul[starts-with(@id,'redactor_toolbar_')]//li//a"));
            toolsList.get(8).click();
            new TextSend().textsendbyid("AfterUsingSubScript", "html-editor-non-draggable");

            if(new BooleanValue().verifyNotElementPresentByXpath("//div[@id='html-editor-non-draggable']//p//sub[contains(text(),'AfterUsingSubScript')]")){
                Assert.fail("The Content typed after selecting \"Subscript\" Button should appear as a subscript to the content typed prior to selecting subscript");
            }
        } catch (Exception e) {
            Assert.fail("Exception in testcase 'instructorToBeAbleToUseSubscripText' in class 'LearningStreamTester'", e);
        }
    }














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
            Assert.fail("Exception in method 'validateLinkFormFields' in the class 'LearningStreamTester'",e);
        }
    }
}