package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R174;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.util.List;

/*
 * Created by Dharaneesha on 21-Oct-14.
 */
public class DisableDeleteOptionForResource_SME extends Driver {


    @Test(priority=1,enabled = true)
    public void checkSMEAbleToDeleteResource_CourseLevel(){
        String userName = "";
        WebElement element = null;
        try{
            //Row No - 2 : "1. Login as a CMS user (SME)  2. Select a course, click on ""Course"" in the Manage content page
            // 3. Mouse hover on the resource"
            //Expected : #1. "x" icon should not be showed
            //Driver.startDriver();
            String updatePassword = "6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab";
            userName = DBConnect.updateUserPasswordAndReturnUserName("ROLE_SUBJECT_MATTER_EXPERT", updatePassword);//update password in DataBase & Return UserName
            new ResourseCreate().createResourceAtCourseLevel(1,"Biology");
            new DirectLogin().directLoginWithCreditial(userName,"snapwiz",3); //Method call to login as a SME User
            //new Click().clickbyxpath("(//select[@name='product-dxrop-down']/following-sibling::div/a)[1]");//Click on 'All Course Types'
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new SelectCourse().selectcourse();//Select 'Biology Course'
            new Click().clickbylistxpath("//div[@class = 'course-label node']", 0);//Select 'Biology' Course to search for a resource Created
            Thread.sleep(1000);
            element = mouseOverOnCreatedResourceAndGetElement();//Method call to mouse over on Created resource
            String style = element.findElement(By.tagName("span")).getAttribute("style");
            if(style.equals("display: inline;")){
                Assert.fail("\"x\" icon should not be showed on the Resource Created at Courselevel");
            }
        }catch(Exception e){
            Assert.fail("Exception in testcase 'checkSMEAbleToDeleteResource_CourseLevel' in class 'DisableDeleteOptionForResource_SME'",e);
        }
    }


    @Test(priority=2,enabled = true)
    public void checkSMEAbleToDeleteResource_ChapterLevel(){
        String userName = "";
        WebElement element = null;
        try{
            //Row No - 3 : "1. Login as a CMS user (SME) 2. Select a course, select a ""chapter"" in the Manage content page
            // 3. Mouse hover on the resource"
            //Expected : #1. "x" icon should not be showed
            //Driver.startDriver();
            String updatePassword = "6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab";
            userName = DBConnect.updateUserPasswordAndReturnUserName("ROLE_SUBJECT_MATTER_EXPERT", updatePassword);//update password in DataBase & Return UserName
            new ResourseCreate().resourseCreate(3,0);// method call to Create a resource of Type 'image'
            new DirectLogin().directLoginWithCreditial(userName,"snapwiz",3); //Method call to login as a SME User
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new SelectCourse().selectcourse();//Select 'Biology Course'
            new SelectCourse().selectChapterByIndex(0);//Select first Chapter
            element = mouseOverOnCreatedResourceAndGetElement();//Method call to mouse over on Created resource
            String style = element.findElement(By.tagName("span")).getAttribute("style");
            if(style.equals("display: inline;")){
                Assert.fail("\"x\" icon should not be showed on the Resource Created at Chapter Level");
            }
        }catch(Exception e){
            Assert.fail("Exception in testcase 'checkSMEAbleToDeleteResource_ChapterLevel' in class 'DisableDeleteOptionForResource_SME'",e);
        }
    }


    @Test(priority=3,enabled = true)
    public void checkSMEAbleToDeleteResource_SectionLevel(){
        String userName = "";
        WebElement element = null;
        try{
            //Row No - 4 : "1. Login as a CMS user (SME) 2. Select a course, select a ""section"" in the Manage content page
            //3. Mouse hover on the resource"
            //Expected : #1. "x" icon should not be showed
            //Driver.startDriver();
            String updatePassword = "6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab";
            userName = DBConnect.updateUserPasswordAndReturnUserName("ROLE_SUBJECT_MATTER_EXPERT", updatePassword);//update password in DataBase & Return UserName
            new ResourseCreate().creatresourcesattopiclevel(4,0,0);// method call to Create a resource
            new DirectLogin().directLoginWithCreditial(userName,"snapwiz",4);//Method call to login as a SME User
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new SelectCourse().selectcourse();//Select 'Biology Course'
            new SelectCourse().expandChapterTreeByIndex(4,0);//Select first Chapter
            new SelectCourse().selectTopicByIndex(4,0);//Select the first topic in first Chapter
            element = mouseOverOnCreatedResourceAndGetElement();//Method call to mouse over on Created resource
            String style = element.findElement(By.tagName("span")).getAttribute("style");
            if(style.equals("display: inline;")){
                Assert.fail("\"x\" icon should not be showed on the Resource Created at Section Level");
            }

        }catch(Exception e){
            Assert.fail("Exception in testcase 'checkSMEAbleToDeleteResource_SectionLevel' in class 'DisableDeleteOptionForResource_SME'",e);

        }
    }




    @Test(priority=4,enabled = false)
    public void checkSMEAbleToDeleteResource_SubSectionLevel(){
        String userName = "";
        WebElement element = null;
        try{
            //Row No - 5 : "1. Login as a CMS user (SME) 2. Select a course, select a ""sub_section"" in the Manage content page
            // 3. Mouse hover on the resource"
            //Expected : #1. "x" icon should not be showed
            //Driver.startDriver();
            String updatePassword = "6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab";
            userName = DBConnect.updateUserPasswordAndReturnUserName("ROLE_SUBJECT_MATTER_EXPERT", updatePassword);//update password in DataBase & Return UserName
            new ResourseCreate().createresourcesatsubtopiclevel(5,0,0,0);// method call to Create a resource
            new DirectLogin().directLoginWithCreditial(userName,"snapwiz",5);//Method call to login as a SME User
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new SelectCourse().selectcourse();//Select 'Biology Course'
            new SelectCourse().expandChapterTreeByIndex(5,0);//Expand first Chapter
            new SelectCourse().expandTopicTreeByIndex(5,0);//Expand first Topic
            new SelectCourse().selectSubTopicByIndex(5,0);//Select first Subtopic
            element = mouseOverOnCreatedResourceAndGetElement();//Method call to mouse over on Created resource
            String style = element.findElement(By.tagName("span")).getAttribute("style");
            if(style.equals("display: inline;")){
                Assert.fail("\"x\" icon should not be showed on the Resource Created at Subsection Level");
            }

        }catch(Exception e){
            Assert.fail("Exception in testcase 'checkSMEAbleToDeleteResource_SubSectionLevel' in class 'DisableDeleteOptionForResource_SME'",e);

        }
    }




    @Test(priority=5,enabled = true)
    public void uploadResourceAsSuperAdminAndTryToDeleteAsSMEUser(){
        String userName = "";
        WebElement element = null;
        try{
            //Row No - 7 : "1. Login as a CMS user (super admin)  2. Select a course, select a ""chapter"" in the Manage content page
            // 3. Click on ""Add resource"" in the footer, upload a resource and logout 4. Login as SME user and mouse hover on the resource uploaded by super admin"
            //Expected : #1. "x" icon should not be showed

            //Driver.startDriver();
            String updatePassword = "6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab";
            userName = DBConnect.updateUserPasswordAndReturnUserName("ROLE_SUBJECT_MATTER_EXPERT", updatePassword);//update password in DataBase & Return UserName
            new ResourseCreate().resourseCreate(7,0);// method call to Create a resource as a Super Admin
            new DirectLogin().directLoginWithCreditial(userName,"snapwiz",7);//Method call to login as a SME User
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new SelectCourse().selectcourse();//Select 'Biology Course'
            new SelectCourse().selectChapterByIndex(0);//Select first Chapter
            element = mouseOverOnCreatedResourceAndGetElement();//Method call to mouse over on Created resource
            String style = element.findElement(By.tagName("span")).getAttribute("style");
            if(style.equals("display: inline;")){
                Assert.fail("\"x\" icon should not be showed in uploading Resource As Super Admin and Try To Delete As SME User");
            }
        }catch(Exception e){
            Assert.fail("Exception in testcase 'uploadResourceAsSuperAdminAndTryToDeleteAsSMEUser' in class 'DisableDeleteOptionForResource_SME'",e);

        }
    }



    @Test(priority=6,enabled = true)
    public void uploadResourceAsPDAndTryToDeleteAsSMEUser(){
        String userName = "";
        WebElement element = null;
        try{
            //Row No - 8 : "1. Login as a CMS user (PD)  2. Select a course, select a ""chapter"" in the Manage content page
            // 3. Click on ""Add resource"" in the footer, upload a resource and logout 4. Login as SME user and mouse hover on the resource uploaded by PD"
            //Expected : #1. "x" icon should not be showed
            //Driver.startDriver();
            String updatePassword = "6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab";
            DBConnect.updateUserPassword("ROLE_PRODUCT_DESIGNER", updatePassword);//update password in DataBase
            new ResourseCreate().resourseCreate(8,0);// method call to Create a resource as a Product Designer
            userName = DBConnect.updateUserPasswordAndReturnUserName("ROLE_SUBJECT_MATTER_EXPERT", updatePassword);//update password in DataBase & Return UserName
            new DirectLogin().directLoginWithCreditial(userName,"snapwiz",8);//Method call to login as a SME User
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new SelectCourse().selectcourse();//Select 'Biology Course'
            new SelectCourse().selectChapterByIndex(0);//Select first Chapter
            element = mouseOverOnCreatedResourceAndGetElement();//Method call to mouse over on Created resource
            String style = element.findElement(By.tagName("span")).getAttribute("style");
            if(style.equals("display: inline;")){
                Assert.fail("\"x\" icon should not be showed in uploading Resource As Product Designer and Try To Delete As SME User");
            }

        }catch(Exception e){
            Assert.fail("Exception in testcase 'uploadResourceAsPDAndTryToDeleteAsSMEUser' in class 'DisableDeleteOptionForResource_SME'",e);
        }
    }



    @Test(priority=7,enabled = true)
    public void tryToDeleteDifferentTypesOfResources_InstructorOnlyFalse(){
        String userName = "";
        WebElement element = null;
        String style = null;
        try{
            //Row No - 9 : "1. Login as a CMS user (SME) 2. Select a course, select a chapter
            //3. Mouse hover on the ""Image"" resource"
            //Expected : #1. "x" icon should not be showed

            //Driver.startDriver();
            String updatePassword = "6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab";
            userName = DBConnect.updateUserPasswordAndReturnUserName("ROLE_SUBJECT_MATTER_EXPERT", updatePassword);//update password in DataBase & return user name
            new ResourseCreate().resourseCreate(9,0);// method call to Create an image resource as a Subject Matter Expert
            new DirectLogin().directLoginWithCreditial(userName,"snapwiz",9);////Method call to login as a SME User
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new SelectCourse().selectcourse();//Select 'Biology Course'
            new SelectCourse().selectChapterByIndex(0);//Select first Chapter
            element = mouseOverOnCreatedResourceAndGetElement();//Method call to mouse over on Created resource
            style = element.findElement(By.tagName("span")).getAttribute("style");
            if(style.equals("display: inline;")){
                Assert.fail("\"x\" icon should not be showed when Mouse Over on 'Image'Resource by selecting InstructorOnly:False");
            }


            //Row No - 12 : 4. Mouse hover on the "Word" resource
            //Expected : 4. Mouse hover on the "Word" resource
            new ResourseCreate().resourseCreate(12,0);// // method call to Create a word document resource as a Subject Matter Expert
            new DirectLogin().directLoginWithCreditial(userName,"snapwiz",12);//Method call to login as a SME User
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new SelectCourse().selectcourse();//Select 'Biology Course'
            new SelectCourse().selectChapterByIndex(0);//Select first Chapter
            element = mouseOverOnCreatedResourceAndGetElement();//Method call to mouse over on Created resource
            style = element.findElement(By.tagName("span")).getAttribute("style");
            if(style.equals("display: inline;")){
                Assert.fail("\"x\" icon should not be showed when Mouse Over on 'Word Document' Resource by selecting InstructorOnly:False");
            }


            //Row No - 13 : 7. Mouse hover on the "PDF" resource
            //Expected : #1. "x" icon should not be showed
            new ResourseCreate().resourseCreate(13,0);// method call to Create a PDF resource as a Subject Matter Expert
            new DirectLogin().directLoginWithCreditial(userName,"snapwiz",13);//Method call to login as a SME User
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new SelectCourse().selectcourse();//Select 'Biology Course'
            new SelectCourse().selectChapterByIndex(0);//Select first Chapter
            element = mouseOverOnCreatedResourceAndGetElement();//Method call to mouse over on Created resource
            style = element.findElement(By.tagName("span")).getAttribute("style");
            if(style.equals("display: inline;")){
                Assert.fail("\"x\" icon should not be showed when Mouse Over on 'PDF' Resource by selecting InstructorOnly:False");
            }




            //Row No - 14 : 8. Mouse hover on the "Excel" resource
            //Expected : #1. "x" icon should not be showed
            new ResourseCreate().resourseCreate(14,0);// method call to Create a Excel resource as a Subject Matter Expert
            new DirectLogin().directLoginWithCreditial(userName,"snapwiz",14);//Method call to login as a SME User
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new SelectCourse().selectcourse();//Select 'Biology Course'
            new SelectCourse().selectChapterByIndex(0);//Select first Chapter
            element = mouseOverOnCreatedResourceAndGetElement();//Method call to mouse over on Created resource
            style = element.findElement(By.tagName("span")).getAttribute("style");
            if(style.equals("display: inline;")){
                Assert.fail("\"x\" icon should not be showed when Mouse Over on 'Excel' Resource by selecting InstructorOnly:False");
            }



            //Row No - 15 : 9. Mouse hover on the "PPT" resource
            //Expected : #1. "x" icon should not be showed
            new ResourseCreate().resourseCreate(15,0);// method call to Create a PPT resource as a Subject Matter Expert
            new DirectLogin().directLoginWithCreditial(userName,"snapwiz",15);//Method call to login as a SME User
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new SelectCourse().selectcourse();//Select 'Biology Course'
            new SelectCourse().selectChapterByIndex(0);//Select first Chapter
            element = mouseOverOnCreatedResourceAndGetElement();//Method call to mouse over on Created resource
            style = element.findElement(By.tagName("span")).getAttribute("style");
            if(style.equals("display: inline;")){
                Assert.fail("\"x\" icon should not be showed when Mouse Over on 'PPT' Resource by selecting InstructorOnly:False");
            }




        }catch(Exception e){
            Assert.fail("Exception in testcase 'tryToDeleteDifferentTypesOfResources_InstructorOnlyFalse' in class 'DisableDeleteOptionForResource_SME'",e);
        }
    }

    @Test(priority=8,enabled = true)
    public void tryToDeleteDifferentTypesOfResources_InstructorOnlyTrue(){
        String userName = "";
        WebElement element = null;
        String style = null;
        try{
            //Row No - 9 : "1. Login as a CMS user (SME) 2. Select a course, select a chapter
            //3. Mouse hover on the ""Image"" resource"
            //Expected : #1. "x" icon should not be showed

            //Driver.startDriver();
            new ResourseCreate().resourseCreate(16,0);// method call to Create an image resource as a Subject Matter Expert
            String updatePassword = "6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab";
            userName = DBConnect.updateUserPasswordAndReturnUserName("ROLE_SUBJECT_MATTER_EXPERT", updatePassword);//Update the Password in DataBase & return User Name
            new DirectLogin().directLoginWithCreditial(userName,"snapwiz",16);//Method call to login as a SME User
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new SelectCourse().selectcourse();//Select 'Biology Course'
            new SelectCourse().selectChapterByIndex(0);//Select first Chapter
            element = mouseOverOnCreatedResourceAndGetElement();//Method call to mouse over on Created resource
            style = element.findElement(By.tagName("span")).getAttribute("style");
            if(style.equals("display: inline;")){
                Assert.fail("\"x\" icon should not be showed when Mouse Over on 'image' Resource by selecting InstructorOnly:True");
            }


            //Row No - 12 : 4. Mouse hover on the "Word" resource
            //Expected : 4. Mouse hover on the "Word" resource
            new ResourseCreate().resourseCreate(17,0);// method call to Create a word document resource as a Subject Matter Expert
            new DirectLogin().directLoginWithCreditial(userName,"snapwiz",17);//Method call to login as a SME User
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new SelectCourse().selectcourse();//Select 'Biology Course'
            new SelectCourse().selectChapterByIndex(0);//Select first Chapter
            element = mouseOverOnCreatedResourceAndGetElement();//Method call to mouse over on Created resource
            style = element.findElement(By.tagName("span")).getAttribute("style");
            if(style.equals("display: inline;")){
                Assert.fail("\"x\" icon should not be showed when Mouse Over on 'Word Document' Resource by selecting InstructorOnly:True");
            }


            //Row No - 13 : 7. Mouse hover on the "PDF" resource
            //Expected : #1. "x" icon should not be showed
            new ResourseCreate().resourseCreate(18,0);// method call to Create a PDF resource as a Subject Matter Expert
            new DirectLogin().directLoginWithCreditial(userName,"snapwiz",18);//Method call to login as a SME User
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new SelectCourse().selectcourse();//Select 'Biology Course'
            new SelectCourse().selectChapterByIndex(0);//Select first Chapter
            element = mouseOverOnCreatedResourceAndGetElement();//Method call to mouse over on Created resource
            style = element.findElement(By.tagName("span")).getAttribute("style");
            if(style.equals("display: inline;")){
                Assert.fail("\"x\" icon should not be showed when Mouse Over on 'PDF' Resource by selecting InstructorOnly:True");
            }




            //Row No - 14 : 8. Mouse hover on the "Excel" resource
            //Expected : #1. "x" icon should not be showed
            new ResourseCreate().resourseCreate(19,0);// method call to Create a Excel resource as a Subject Matter Expert
            new DirectLogin().directLoginWithCreditial(userName,"snapwiz",19);//Method call to login as a SME User
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new SelectCourse().selectcourse();//Select 'Biology Course'
            new SelectCourse().selectChapterByIndex(0);//Select first Chapter
            element = mouseOverOnCreatedResourceAndGetElement();//Method call to mouse over on Created resource
            style = element.findElement(By.tagName("span")).getAttribute("style");
            if(style.equals("display: inline;")){
                Assert.fail("\"x\" icon should not be showed when Mouse Over on 'Excel' Resource by selecting InstructorOnly:True");
            }



            //Row No - 15 : 9. Mouse hover on the "PPT" resource
            //Expected : #1. "x" icon should not be showed
            new ResourseCreate().resourseCreate(20,0);// method call to Create a PPT resource as a Subject Matter Expert
            new DirectLogin().directLoginWithCreditial(userName,"snapwiz",20);// method call to Create a resource as a Subject Matter Expert
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new SelectCourse().selectcourse();//Select 'Biology Course'
            new SelectCourse().selectChapterByIndex(0);//Select first Chapter
            element = mouseOverOnCreatedResourceAndGetElement();//Method call to mouse over on Created resource
            style = element.findElement(By.tagName("span")).getAttribute("style");
            if(style.equals("display: inline;")){
                Assert.fail("\"x\" icon should not be showed when Mouse Over on 'PPT' Resource by selecting InstructorOnly:True");
            }



        }catch(Exception e){
            Assert.fail("Exception in testcase 'tryToDeleteDifferentTypesOfResources_InstructorOnlyTrue' in class 'DisableDeleteOptionForResource_SME'",e);
        }
    }


    /*@AfterMethod
    public void TearDown()throws Exception
    {
        driver.quit();
    }*/

    private WebElement mouseOverOnCreatedResourceAndGetElement(){//Method to Mouse Over on the Created Resource
        WebElement element = null;
        try {
            List<WebElement> assessmentElementsList = driver.findElements(By.xpath("//li[@id = 'resource']"));
            element = assessmentElementsList.get(assessmentElementsList.size() - 1);
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(element);
            Thread.sleep(2000);
        }catch(Exception e){
            Assert.fail("Exception in Method 'mouseOverOnCreatedResourceAndGetElement' in class DisableDeleteOptionForResource_SME",e);
        }
        return  element;
    }

}
