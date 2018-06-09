package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.manageclass;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.DropDown;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Created by Sumit on 20/11/14.
 */
public class TeacherAbleToSignUpAndCreateClass extends Driver{

    @Test
    public void teacherAbleToSignUpAndCreateClass()
    {
        try
        {
            String appendChar = "a11";
            String  className = "Class name";
            String grade = ReadTestData.readDataByTagName("", "grade", "1");
            String subjectArea = ReadTestData.readDataByTagName("", "subjectArea", "1");
            String subject = ReadTestData.readDataByTagName("", "subject", "1");
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new TextSend().textsendbyid(className,"class-name");//Enter Class name
            new DropDown().selectValue("class", "as-add-grade-dropDown", grade);//Select a grade
            new DropDown().selectValue("class","as-add-subjectArea-dropDown",subjectArea);//Select subject area
            new DropDown().selectValue("class","as-add-subject-dropDown",subject);//select subject
            new Click().clickBycssselector("div[class='as-search-blue-btn btn btn-blue as-add-save-btn']"); //Finish button of step 3
            Thread.sleep(3000);
            /*//TC row no. 1
            Manage Class page should be displayed with the following fields
            1. Grade
            2. Subject Area
            3. Subject
            4. Code (class code)
            5. URL (url for the class)
            6. Description(optional)
            7. Current students count should be dispalyed*/
            driver.findElement(By.partialLinkText("Manage Class")).click();
            Thread.sleep(2000);
            String gradeInUI = new TextFetch().textfetchbyclass("as-manageClass-printed-firstRow");
            if(!gradeInUI.equals("Grade "+grade)){
                Assert.fail("Grade field in manage class page is absent.");
            }
            String subjectAreaLabel = new TextFetch().textFetchByXpath("//label[contains(.,'Subject Area')]");
            if(!subjectAreaLabel.equals("Subject Area")){
                Assert.fail("Subject Area label in manage class page is absent.");
            }
            String subjectAreaInUI = new TextFetch().textfetchbycssselector("span[class='as-manageClass-subjectArea ellipsis']");
            if(!subjectAreaInUI.equals(subjectArea)){
                Assert.fail("Subject Area field in manage class page is absent.");
            }
            String classCode = new TextFetch().textfetchbycssselector("span[class='as-manageClass-codeValue']");
            if(classCode.length()==0){
                Assert.fail("Class code in manage class page is absent.");
            }
            String classUrl = new TextFetch().textfetchbycssselector("div[class='as-manageClass-codeValue as-manageClass-url ellipsis share-link-label']");
            if(classUrl.length()==0){
                Assert.fail("Class URL is absent in manage class page.");
            }
            String studentCount = new TextFetch().textfetchbycssselector("span[class='as-manageClass-studentCount no-count']");
            if(!studentCount.equals("0")){
                Assert.fail("Student Count is absent in manage class page.");
            }

            //TC row no. 4
                /*The page should also display the following
            1. Visit class button
            2. ""+New"" class button
            3. Settings icon
*/
            String visitClass = new TextFetch().textfetchbyclass("as-manage-class-visit");
            if(!visitClass.equals("Visit Class")){
                Assert.fail("Visit Class button is absent in manage class page.");
            }
            String newClass = new TextFetch().textfetchbycssselector("div[class='as-manage-blue-btn as-add-newClass-btn']");
            if(!newClass.equals("New Class")){
                Assert.fail("New Class button is absent in manage class page.");
            }

            Thread.sleep(10000);
            driver.quit();

            //TC row no. 6
           /* 1. Login as a teacher
            2. Go to ""manage class"" page from navigator dropdown */
            // Expected: Manage Class page should be displayed. The header should display page title, Name of the active class with a list of others as drop down menu, help button and the name of the user.
            reInitDriver();
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new Navigator().navigateTo("manageclass");//navigate to manage class
            String classNameInUI = new TextFetch().textfetchbycssselector("span[class='as-manage-class-subject as-manage-class-title ellipsis']");
            if(!classNameInUI.equalsIgnoreCase(className)){
                Assert.fail("Class name in not displayed at top in Manage Class page.");
            }

            String gradeInUI1 = new TextFetch().textfetchbyclass("as-manageClass-printed-firstRow");
            if(!gradeInUI1.equals("Grade "+grade)){
                Assert.fail("Grade field in manage class page is absent.");
            }
            String subjectAreaLabel1 = new TextFetch().textFetchByXpath("//label[contains(.,'Subject Area')]");
            if(!subjectAreaLabel1.equals("Subject Area")){
                Assert.fail("Subject Area label in manage class page is absent.");
            }
            String subjectAreaInUI1 = new TextFetch().textfetchbycssselector("span[class='as-manageClass-subjectArea ellipsis']");
            if(!subjectAreaInUI1.equals(subjectArea)){
                Assert.fail("Subject Area field in manage class page is absent.");
            }
            String classCode1 = new TextFetch().textfetchbycssselector("span[class='as-manageClass-codeValue']");
            if(classCode1.length()==0){
                Assert.fail("Class code in manage class page is absent.");
            }
            String classUrl1 = new TextFetch().textFetchByXpath("//div[contains(@class,'as-manageClass-url')]");
            if(classUrl1.length()==0){
                Assert.fail("Class URL is absent in manage class page.");
            }
            String studentCount2 = new TextFetch().textfetchbycssselector("span[class='as-manageClass-studentCount no-count']");
            if(!studentCount2.equals("0")){
                Assert.fail("Student Count is absent in manage class page.");
            }
            String visitClass1 = new TextFetch().textfetchbyclass("as-manage-class-visit");
            if(!visitClass1.equals("Visit Class")){
                Assert.fail("Visit Class button is absent in manage class page.");
            }
            String newClass1 = new TextFetch().textfetchbycssselector("div[class='as-manage-blue-btn as-add-newClass-btn']");
            if(!newClass1.equals("New Class")){
                Assert.fail("New Class button is absent in manage class page.");
            }

            //TC row no. 9
            /*1. Login as a teacher
            2. Go to ""manage class"" page from navigator dropdown
            3.Click on +New Class button*/
            //Expected: Class creation page should be displayed.
            new Click().clickBycssselector("div[class='as-manage-blue-btn as-add-newClass-btn']");//click on +New class button
            String classNameField = driver.findElement(By.cssSelector("input[class='as-manage-class-name class-name-input']")).getAttribute("placeholder");
            if(!classNameField.contains("Enter Class name")){
                Assert.fail("After clicking new class button the class name text is absent.");
            }

            Select allGrades = new Select(driver.findElement(By.className("as-add-grade-dropDown")));
            allGrades.selectByIndex(2);//select a grade

            Select allSubjectArea = new Select(driver.findElement(By.className("as-add-subjectArea-dropDown")));
            allSubjectArea.selectByIndex(1);//select a Subject area

            Select allSubject = new Select(driver.findElement(By.className("as-add-subject-dropDown")));
            allSubject.selectByIndex(1);//select a Subject

            String emailLink = new TextFetch().textfetchbycssselector("a[class='as-add-link as-addStudent-msgLink add-students-link']");
            if(!emailLink.equals("Add students via e-mail")){
                Assert.fail("\"Add students via e-mail\" link is absent in edit class page.");
            }

            String studentCount3 = new TextFetch().textfetchbycssselector("span[class='as-manageClass-studentCount no-count']");
            if(!studentCount3.equals("0")){
                Assert.fail("Student count is absent in edit class page.");
            }

            //TC row no. 11-13,....Adding fields, when new class is created
            new TextSend().textsendbycssSelector("New Class Name", "input[class='as-manage-class-name class-name-input']");//giv new class name
            new TextSend().textsendbycssSelector("Description for new class section", "textarea[class='as-manage-class-description class-description-input']");//give description
            new Click().clickBycssselector("input[class='as-blue-btn as-add-save-btn']");//click on Save button
            Thread.sleep(2000);
            String classNameInUI1 = driver.findElement(By.cssSelector("span[class='as-manage-class-subject as-manage-class-title ellipsis']")).getAttribute("title");
            if(!classNameInUI1.equalsIgnoreCase("New Class Name")){
                Assert.fail("Class name in not displayed at top in Manage Class page.");
            }

            String gradeInUI2 = new TextFetch().textfetchbyclass("as-manageClass-printed-firstRow");
            if(!gradeInUI2.equals("Grade "+grade)){
                Assert.fail("Grade field in manage class page is absent.");
            }
            String subjectAreaLabel2 = new TextFetch().textFetchByXpath("//label[contains(.,'Subject Area')]");
            if(!subjectAreaLabel2.equals("Subject Area")){
                Assert.fail("Subject Area label in manage class page is absent.");
            }
            String subjectAreaInUI2 = new TextFetch().textfetchbycssselector("span[class='as-manageClass-subjectArea ellipsis']");
            if(!subjectAreaInUI2.equals(subjectArea)){
                Assert.fail("Subject Area field in manage class page is absent.");
            }
            String classCode2 = new TextFetch().textfetchbycssselector("span[class='as-manageClass-codeValue']");
            if(classCode2.length()==0){
                Assert.fail("Class code in manage class page is absent.");
            }
            String classUrl2 = new TextFetch().textFetchByXpath("//div[contains(@class,'as-manageClass-url')]");
            if(classUrl2.length()==0){
                Assert.fail("Class URL is absent in manage class page.");
            }
            String classDesc2 = new TextFetch().textfetchbyclass("as-manageClass-descriptionContent");
            if(classDesc2.length()==0){
                Assert.fail("Class URL is absent in manage class page.");
            }
            String studentCount4 = new TextFetch().textfetchbycssselector("span[class='as-manageClass-studentCount no-count']");
            if(!studentCount4.equals("0")){
                Assert.fail("Student Count is absent in manage class page.");
            }
            String visitClass2 = new TextFetch().textfetchbyclass("as-manage-class-visit");
            if(!visitClass2.equals("Visit Class")){
                Assert.fail("Visit Class button is absent in manage class page.");
            }
            String newClass2 = new TextFetch().textfetchbycssselector("div[class='as-manage-blue-btn as-add-newClass-btn']");
            if(!newClass2.equals("New Class")){
                Assert.fail("New Class button is absent in manage class page.");
            }


            Select allClasses = new Select(driver.findElement(By.className("as-header-classes-selectbox")));
            allClasses.selectByIndex(1);//select a another class
            driver.quit();

            //TC row no. 14--Checking the "cancel" functionality"
            reInitDriver();
            new Login().loginAsInstructor(appendChar, 1);//log in as teacher
            new Navigator().navigateTo("manageclass");//navigate to manage class
            new Click().clickBycssselector("div[class='as-manage-blue-btn as-add-newClass-btn']");//click on +New class button
            new Select(driver.findElement(By.className("as-add-grade-dropDown"))).selectByIndex(2);//select a grade

            new Select(driver.findElement(By.className("as-add-subjectArea-dropDown"))).selectByIndex(1);//select a Subject area

            new Select(driver.findElement(By.className("as-add-subject-dropDown"))).selectByIndex(1);//select a Subject
            new TextSend().textsendbycssSelector("New Class Name 2", "input[class='as-manage-class-name class-name-input']");//giv new class name
            new TextSend().textsendbycssSelector("Description for new class section", "textarea[class='as-manage-class-description class-description-input']");//give description
            new Click().clickByXpath("//a[contains(@class,'as-add-back-btn')]");//click on Cancel button
            Thread.sleep(2000);
            String classNameInUI2 = driver.findElement(By.cssSelector("span[class='as-manage-class-subject as-manage-class-title ellipsis']")).getAttribute("title");
            if(classNameInUI2.equalsIgnoreCase("New Class Name 2")){
                Assert.fail("On clicking cancel button the Class gets created .");
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase teacherAbleToSignUpAndCreateClass in class TeacherAbleToSignUpAndCreateClass.", e);
        }
    }
}
