package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.learningstream;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Mukesh on 11/27/14.
 */
public class InstructorPostVisibleToHisClass extends Driver {
    String actual=null;
    String expected=null;
    @Test(priority = 1,enabled = true)
    public void instructorPostVisibleToHisClass()
    {
        try {
            //Row No-66
            //Expected - The post field should close and the updated post should be listed in the learning stream page

            String appendChar = "p";
            String appendCharSecondStudent = "q";
            String appendCharSecondClassStudent = "r";
            int index=67;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create first class
            System.out.println("classCode : " + classCode);

            new Classes().createNewClass(appendCharSecondStudent,index);//create second class
            Thread.sleep(30000);
            System.out.println("hi");
            String classCode1 = new TextFetch().textfetchbycssselector("span[class='as-manageClass-codeValue']");
            System.out.println("Class code1:"+classCode1);
            new Navigator().logout();//logout

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendCharSecondClassStudent, classCode1, index);//create second class student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            Select select2=new Select(driver.findElement(By.className("as-header-classes-selectbox")));//switch to class1
            select2.selectByIndex(0);

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
            new Navigator().logout();//logout

            new Login().loginAsStudent(appendCharSecondClassStudent,index);//login as student of different class
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'

            actual = new TextFetch().textFetchByXpath("//div[@class = 'stream-body']");
            System.out.println("Actual Content:"+actual);
            expected = "The content that was posted in previous class should not be seen here";
            Assert.assertNotEquals(actual.trim(), expected, "The content that was posted in previous class should not be seen in current class");


        } catch (Exception e) {
            Assert.fail("Exception in testcase 'instructorPostVisibleToHisClass' in class 'LearningStreamTester'", e);
        }

    }
    @Test(priority = 2,enabled = true)
    public void instructorPostVisibleToParticularStudent()
    {
        try {
            //Row No-68
            String appendChar = "s";
            String appendCharSecondStudent = "e";

            int index=68;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create first class
            System.out.println("classCode : " + classCode);
            new Navigator().logout();//logout

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, index);//create student2
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new TextSend().textsendbyid("The content that was posted in previous class should not be seen here", "html-editor-non-draggable");
            new Click().clickByclassname("closebutton");//click on the x icon of share with

            String firstStudent="instructorPostVisibleToParticularStudentsts ";
            new TextSend().textsendbyclass(firstStudent,"maininput");
            new Click().clickByXpath("//ul[@id='share-with_feed']/li");
            new Click().clickByid("post-submit-button");
            actual = new TextFetch().textFetchByXpath("//div[@class = 'stream-body']");
            expected = "The content that was posted in previous class should not be seen here";
            Assert.assertEquals(actual.trim(), expected, "The updated post should be listed in the learning stream page of the instructor");
            new Navigator().logout();//logout

            new Login().loginAsStudent(appendChar,index);
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            actual = new TextFetch().textFetchByXpath("//div[@class = 'stream-body']");
            expected = "The content that was posted in previous class should not be seen here";
            Assert.assertEquals(actual.trim(), expected, "The updated post should be listed in the learning stream page of the instructor");
            new Navigator().logout();//logout


        } catch (Exception e) {
            Assert.fail("Exception in testcase 'instructorPostVisibleToParticularStudent' in class 'LearningStreamTester'", e);
        }

    }
    @Test(priority = 2,enabled = true)
    public void postFieldVerification()
    {
        try {
            //Row No-74
            String appendChar = "e";
            int index=74;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create first class
            System.out.println("classCode : " + classCode);
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new TextSend().textsendbyid("This is new post", "html-editor-non-draggable");
            new Click().clickByid("post-submit-button");
            String nameOfPoster=new TextFetch().textfetchbycssselector("span[class='stream-poster-name ellipsis']");
            if(!nameOfPoster.contains("postFieldVerificationins"))
                Assert.fail("Name of the poster is not visible");
            String instructorTag=new TextFetch().textfetchbyclass("instructor-tag");
            Assert.assertEquals(instructorTag,"Instructor","Instructor Tag is not visible");
            String streamBody=new TextFetch().textfetchbyclass("stream-body");
            if(streamBody.equals("")||streamBody==null)
                Assert.fail("Content of the post is not visible");
            new Click().clickBycssselector("i[class='like-icon-img stream-like-icon']");//click on the like button
            new Click().clickByXpath("(//a[@title='Comments'])[1]");//click on comment button
            new TextSend().textsendbycssSelector("comment","textarea[class='stream-comment-textarea textarea-autogrow']");
            new Click().clickBycssselector("div[class='stream-bookmark as-bookmark-icon-sprite ']");//click on bookmark
            new Click().clickByclassname("as-stream-hideComment-toggle-arw");//click on the hide toggle



        } catch (Exception e) {
            Assert.fail("Exception in testcase 'postFieldVerification' in class 'LearningStreamTester'", e);
        }

    }
    @Test(priority = 3,enabled = true)
    public void likeVerification()
    {
        try {
            //Row No-75
            String appendChar = "f";
            int index=75;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create first class
            System.out.println("classCode : " + classCode);
            new Navigator().logout();//logout

            new SignUp().studentDirectRegister(appendChar,classCode,index);//signup as student
            new Navigator().logout();//logout

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new TextSend().textsendbyid("This is new post", "html-editor-non-draggable");
            new Click().clickByid("post-submit-button");
            new Click().clickBycssselector("i[class='like-icon-img stream-like-icon']");//click on the like button
            String likeCount=new TextFetch().textfetchbyclass("stream-post-like-count");
            if(likeCount.contains("0"))
                Assert.fail("click on like is failed");
            new Navigator().logout();

            //Tc row no 76
            new Login().loginAsStudent(appendChar,index);
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            String likeCountOfStudent=new TextFetch().textfetchbyclass("stream-post-like-count");
            System.out.println("likeCountOfStudent:"+likeCountOfStudent);
            if(likeCountOfStudent.contains("0"))
                Assert.fail("click on like is failed");
            new Navigator().logout();//logout

            //Tc row no 77
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByListClassName("as-stream-hideComment-toggle-arw", 0);//click on the hide toggle
            new Click().clickByListClassName("ls-hide-post",0);//click on the Hide post
            if(driver.findElements(By.xpath("(//div[@class='stream-body'])[1]/p[text() = 'This is new post']")).size()!=0)
                Assert.fail("Post is not deleted");
            new Navigator().logout();//logout

            //Tc row no 78
            new Login().loginAsStudent(appendChar,index);
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            if(driver.findElements(By.xpath("(//div[@class='stream-body'])[1]/p[text() = 'This is new post']")).size()!=0)
                Assert.fail("Post is not deleted");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'likeVerification' in class 'LearningStreamTester'", e);
        }

    }

    @Test(priority = 4,enabled = true)
    public void bookmarkAndCommentVerification()
    {
        try {
            //Row No-79
            String appendChar = "b";
            int index=79;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create first class
            System.out.println("classCode : " + classCode);
            new Navigator().logout();//logout

            new SignUp().studentDirectRegister(appendChar,classCode,index);//signup as student
            new Navigator().logout();//logout

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new TextSend().textsendbyid("This is new post", "html-editor-non-draggable");
            new Click().clickByid("post-submit-button");
            new Click().clickBycssselector("div[class='stream-bookmark as-bookmark-icon-sprite ']");//click on bookmark
            String removeBookmark=driver.findElement(By.cssSelector("div[class='stream-bookmark as-bookmark-icon-sprite  bookmarked']")).getAttribute("title");
            System.out.println("hi");
            System.out.println("Remove Bookmark:"+removeBookmark);
            if(!removeBookmark.equals("Remove bookmark"))
                Assert.fail("Bookmark is not highLighted");

           //Tc row no 80
            new Click().clickByXpath("(//a[@title='Comments'])[1]");//click on comment button
            new TextSend().textsendbycssSelector("comment","textarea[class='stream-comment-textarea textarea-autogrow']");
            driver.switchTo().activeElement().sendKeys(Keys.RETURN);
            String commentText=new TextFetch().textfetchbyclass("comment-text");
            if(commentText.equals("")||commentText==null)
                Assert.fail("comment are not posted");
            String commentCount=new TextFetch().textfetchbyclass("stream-post-comment-count");
            if(commentCount.equals("0"))
                Assert.fail("Comment count is not increased");

            //Tc row no 81
            new Navigator().logout();//logout
            new Login().loginAsStudent(appendChar,index);
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            String commentTexts=new TextFetch().textfetchbyclass("comment-text");
            if(!commentTexts.equals("comment"))
                Assert.fail("Student is not able to see instructor comment");


        } catch (Exception e) {
            Assert.fail("Exception in testcase 'bookmarkAndCommentVerification' in class 'LearningStreamTester'", e);
        }

    }
    @Test(priority = 5,enabled = true)
    public void studentLearningStream()
    {
        try {
            //Row No-82
            String appendChar = "a";
            int index=82;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create first class
            System.out.println("classCode : " + classCode);

            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new TextSend().textsendbyid("This is new post", "html-editor-non-draggable");
            new Click().clickByid("post-submit-button");

            new Navigator().logout();//logout

            new SignUp().studentDirectRegister(appendChar,classCode,index);//signup as student
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'

            String defaultMessage=new TextFetch().textfetchbyclass("stream-header");
            if(!defaultMessage.contains("posted a message"))
                Assert.fail("Learning Stream page is not  displayed with the default message posted by edulastic");

            //Tc row no 83
            actual = new TextFetch().textFetchByXpath("(//div[@class='stream-body'])[2]");
            expected = "This is new post";
            Assert.assertEquals(actual.trim(), expected, "The posted message is not displayed in student learning page");

            //Tc row no 84
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner


        } catch (Exception e) {
            Assert.fail("Exception in testcase 'studentLearningStream' in class 'LearningStreamTester'", e);
        }

    }

    @Test(priority = 6,enabled = true)
    public void studentPostFieldVerification()
    {
        try {
            //Row No-144
            String appendChar = "f";
            int index=144;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create first class
            new Navigator().logout();//logout

            new SignUp().studentDirectRegister(appendChar,classCode,index);//signup as student
            new Navigator().logout();//logout

            new Login().loginAsStudent(appendChar,index);//login as student
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new TextSend().textsendbyid("This is new post", "html-editor-non-draggable");
            new Click().clickByid("post-submit-button");
            String nameOfPoster=new TextFetch().textfetchbycssselector("span[class='stream-poster-name ellipsis']");
            if(!nameOfPoster.contains("studentPostFieldVerificationst"))
                Assert.fail("Name of the poster is not visible");
            String streamBody=new TextFetch().textfetchbyclass("stream-body");
            if(streamBody.equals("")||streamBody==null)
                Assert.fail("Content of the post is not visible");
            new Click().clickBycssselector("i[class='like-icon-img stream-like-icon']");//click on the like button
            new Click().clickByXpath("(//a[@title='Comments'])[1]");//click on comment button
            new TextSend().textsendbycssSelector("comment","textarea[class='stream-comment-textarea textarea-autogrow']");
            new Click().clickBycssselector("div[class='stream-bookmark as-bookmark-icon-sprite ']");//click on bookmark




        } catch (Exception e) {
            Assert.fail("Exception in testcase 'studentPostFieldVerification' in class 'LearningStreamTester'", e);
        }

    }
    @Test(priority = 7,enabled = true)
    public void studentLikeVerification()
    {
        try {
            //Row No-145
            String appendChar = "d";
            int index=145;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create first class
            new Navigator().logout();//logout

            new SignUp().studentDirectRegister(appendChar,classCode,index);//signup as student
            new Navigator().logout();//logout

            new Login().loginAsStudent(appendChar,index);//login as student
            new Navigator().navigateTo("learningstream");//Navigate to 'Learning Stream'
            new Click().clickByXpath("//div[normalize-space(@class) = 'mobile-hide as-stream-post-text' and text() = 'Post']");//Click on the "post" displayed on the right side top corner
            new TextSend().textsendbyid("This is new post", "html-editor-non-draggable");
            new Click().clickByid("post-submit-button");
            new Click().clickBycssselector("i[class='like-icon-img stream-like-icon']");//click on the like button
            String likeCount=new TextFetch().textfetchbyclass("stream-post-like-count");
            if(likeCount.contains("0"))
                Assert.fail("click on like is failed");

            //Tc row no 146

            new Click().clickBycssselector("div[class='stream-bookmark as-bookmark-icon-sprite ']");//click on bookmark
            String removeBookmark=driver.findElement(By.cssSelector("div[class='stream-bookmark as-bookmark-icon-sprite  bookmarked']")).getAttribute("title");
            System.out.println("hi");
            System.out.println("Remove Bookmark:"+removeBookmark);
            if(!removeBookmark.equals("Remove bookmark"))
                Assert.fail("Bookmark is not highLighted");

            //Tc row no 147
            new Click().clickByXpath("(//a[@title='Comments'])[1]");//click on comment button
            new TextSend().textsendbycssSelector("comment","textarea[class='stream-comment-textarea textarea-autogrow']");
            driver.switchTo().activeElement().sendKeys(Keys.RETURN);
            String commentText=new TextFetch().textfetchbyclass("comment-text");
            if(commentText.equals("")||commentText==null)
                Assert.fail("comment are not posted");
            String commentCount=new TextFetch().textfetchbyclass("stream-post-comment-count");
            if(commentCount.equals("0"))
                Assert.fail("Comment count is not increased");


        } catch (Exception e) {
            Assert.fail("Exception in testcase 'studentLikeVerification' in class 'LearningStreamTester'", e);
        }

    }


}
