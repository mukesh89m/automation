package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.manageclass;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Created by Sumit on 24/11/14.
 */
public class EditingClass extends Driver{

    @Test
    public void editingClass()
    {
        try
        {
            String appendChar = "a";
            String grade = ReadTestData.readDataByTagName("", "grade", "15");
            String subjectArea = ReadTestData.readDataByTagName("", "subjectArea", "15");
            String subject = ReadTestData.readDataByTagName("", "subject", "15");
            //TC row no. 15, 16
            new SignUp().teacher(appendChar, 15);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 15);//log in as teacher
            new School().createWithOnlyName(appendChar, 15);//create school
            new Classes().createClass(appendChar, 15);//create class
            new Navigator().navigateTo("manageclass");//navigate to manage class
            driver.findElement(By.className("as-manage-class-settings")).click();//click on Setting Icon
            new Click().clickByclassname("edit-class");//click on Edit class

            new TextSend().textsendbycssSelector("New Class Name", "input[class='as-manage-class-name class-name-input']");//giv new class name
            new TextSend().textsendbycssSelector("Description for new class section", "textarea[class='as-manage-class-description class-description-input']");//give description
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 15);//log in as teacher
            new Navigator().navigateTo("manageclass");//navigate to manage class
            driver.findElement(By.className("as-manage-class-settings")).click();//click on Setting Icon
            new Click().clickByclassname("edit-class");//click on Edit class
            new TextSend().textsendbycssSelector("New Class Name", "input[class='as-manage-class-name class-name-input']");//giv new class name
            new TextSend().textsendbycssSelector("Description for new class section", "textarea[class='as-manage-class-description class-description-input']");//give description
            new Click().clickBycssselector("input[class='as-blue-btn as-add-save-btn']");//click on Save button
            Thread.sleep(2000);
            String gradeInUI = new TextFetch().textfetchbyclass("as-manageClass-printed-firstRow");
            if(!gradeInUI.equals("Grade "+grade)){
                Assert.fail("Grade field in edit class page is absent.");
            }
            String subjectAreaLabel = new TextFetch().textFetchByXpath("//label[contains(.,'Subject Area')]");
            if(!subjectAreaLabel.equals("Subject Area")){
                Assert.fail("Subject Area label in edit class page is absent.");
            }
            String subjectAreaInUI = new TextFetch().textfetchbycssselector("span[class='as-manageClass-subjectArea ellipsis']");
            if(!subjectAreaInUI.equals(subjectArea)){
                Assert.fail("Subject Area field in edit class page is absent.");
            }
            String subjectInUI = new TextFetch().textfetchbycssselector("span[class='as-manageClass-subject ellipsis']");
            if(!subjectInUI.equals(subject)){
                Assert.fail("Subject in edit class page is absent.");
            }
            String classCode = new TextFetch().textfetchbycssselector("span[class='as-manageClass-codeValue']");
            if(classCode.length()==0){
                Assert.fail("Class code in edit class page is absent.");
            }
            String classUrl = new TextFetch().textfetchbycssselector("span[class='as-manageClass-codeValue as-manageClass-url ellipsis']");
            if(classUrl.length()==0){
                Assert.fail("Class URL is absent in edit class page.");
            }
            String classDesc = new TextFetch().textfetchbyclass("as-manageClass-descriptionContent");
            if(classDesc.length()==0){
                Assert.fail("Class URL is absent in edit class page.");
            }
            String studentCount = new TextFetch().textfetchbycssselector("span[class='as-manageClass-studentCount no-count']");
            if(!studentCount.equals("0")){
                Assert.fail("Student Count is absent in edit class page.");
            }

            //TC row no. 17, 18---Clicking Cancel while editing "Class Section"
            new Navigator().navigateTo("manageclass");//navigate to manage class
            driver.findElement(By.className("as-manage-class-settings")).click();//click on Setting Icon
            new Click().clickByclassname("edit-class");//click on Edit class
            new TextSend().textsendbycssSelector("New Class Name 2", "input[class='as-manage-class-name class-name-input']");//giv new class name
            new TextSend().textsendbycssSelector("Description for new class section", "textarea[class='as-manage-class-description class-description-input']");//give description
            new Click().clickBycssselector("a[class='as-add-back-btn']");//click on Cancel button
            String pageTitle = new TextFetch().textfetchbycssselector("div[class='center header-title']");
            if(!pageTitle.equalsIgnoreCase("Manage Class")){
                Assert.fail("On clicking Cancel button from edit class page the teacher doesn't navigate to Manage class page.");
            }
            String gradeInUI1 = new TextFetch().textfetchbyclass("as-manageClass-printed-firstRow");
            if(!gradeInUI1.equals("Grade "+grade)){
                Assert.fail("On clicking Cancel button from edit class page the Grade field is absent.");
            }
            String subjectAreaLabel1 = new TextFetch().textFetchByXpath("//label[contains(.,'Subject Area')]");
            if(!subjectAreaLabel1.equals("Subject Area")){
                Assert.fail("On clicking Cancel button from edit class page the Subject Area label is absent.");
            }
            String subjectAreaInUI1 = new TextFetch().textfetchbycssselector("span[class='as-manageClass-subjectArea ellipsis']");
            if(!subjectAreaInUI1.equals(subjectArea)){
                Assert.fail("On clicking Cancel button from edit class page the Subject Area field is absent.");
            }
            String subjectInUI1 = new TextFetch().textfetchbycssselector("span[class='as-manageClass-subject ellipsis']");
            if(!subjectInUI1.equals(subject)){
                Assert.fail("On clicking Cancel button from edit class page the Subject field is absent.");
            }
            String classCode1 = new TextFetch().textfetchbycssselector("span[class='as-manageClass-codeValue']");
            if(classCode1.length()==0){
                Assert.fail("On clicking Cancel button from edit class page the class code field is absent.");
            }
            String classUrl1 = new TextFetch().textfetchbycssselector("span[class='as-manageClass-codeValue as-manageClass-url ellipsis']");
            if(classUrl1.length()==0){
                Assert.fail("On clicking Cancel button from edit class page the class URL field is absent.");
            }
            String classDesc1 = new TextFetch().textfetchbyclass("as-manageClass-descriptionContent");
            if(classDesc1.length()==0){
                Assert.fail("On clicking Cancel button from edit class page the class description field is absent.");
            }
            String studentCount1 = new TextFetch().textfetchbycssselector("span[class='as-manageClass-studentCount no-count']");
            if(!studentCount1.equals("0")){
                Assert.fail("On clicking Cancel button from edit class page the student count field is absent.");
            }
            String classNameInUI2 = driver.findElement(By.cssSelector("span[class='as-manage-class-subject as-manage-class-title ellipsis']")).getAttribute("title");
            if(classNameInUI2.equalsIgnoreCase("New Class Name 2")){
                Assert.fail("On clicking cancel button from edit class page the new class name is shown.");
            }

            //TC row no. 19-21----Visiting a class
            new Navigator().navigateTo("manageclass");//navigate to manage class
            String studentCount2 = new TextFetch().textfetchbycssselector("span[class='as-manageClass-studentCount no-count']");
            if(!studentCount2.equals("0")){
                Assert.fail("Student Count is absent in manage class page.");
            }
            new Click().clickByclassname("as-manage-class-visit");//click on Visit class
            Thread.sleep(1000);
            String pageTitle1 = new TextFetch().textfetchbycssselector("div[class='center header-title']");
            if(!pageTitle1.equalsIgnoreCase("Dashboard")){
                Assert.fail("If we click on Visit class from Manage class page it does not navigate to Dashboard.");
            }
            String className = new TextFetch().textFetchByXpath("//select[@class='as-header-classes-selectbox']");
            if(!className.equals("New Class Name")){
                Assert.fail("In case of multiple class section, corresponding dashboard information of the class is not displayed.");
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase editingClass in class EditingClass.", e);
        }
    }
}
