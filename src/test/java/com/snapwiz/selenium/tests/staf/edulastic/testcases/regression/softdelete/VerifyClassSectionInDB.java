package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.softdelete;
import com.mongodb.DBCollection;
import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.googleclassroom.GoogleclassRoom;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.managedistrict.ClassEnrollment;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.KeysSend;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import  org.testng.annotations.*;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.List;


/**
 * Created by mukesh on 13/12/16.
 */
public class VerifyClassSectionInDB extends Driver {
    AssignmentReview assignmentReview;
    Assignments assignments;
    StudentResponse studentResponse;
    AssignmentDetails assignmentDetails;
    AddQuestion addQuestion;
    SchoolPage schoolPage;
    ClassEnrollment classEnrollment;
    ManageClass manageClass;
    Performance performance;
    String actual;
    String expected;

    @BeforeMethod
    public void init() {
        WebDriver driver = Driver.getWebDriver();
        assignmentReview = PageFactory.initElements(driver, AssignmentReview.class);
        assignments = PageFactory.initElements(driver, Assignments.class);
        studentResponse = PageFactory.initElements(driver, StudentResponse.class);
        assignmentDetails = PageFactory.initElements(driver, AssignmentDetails.class);
        schoolPage = PageFactory.initElements(driver, SchoolPage.class);
        addQuestion = PageFactory.initElements(driver, AddQuestion.class);
        classEnrollment = PageFactory.initElements(driver, ClassEnrollment.class);
        manageClass = PageFactory.initElements(driver, ManageClass.class);
        performance = PageFactory.initElements(driver, Performance.class);

    }

    @Test(priority = 1, enabled = true)
    public void verifyClassSectionInDB() {
        try {

            ReportUtil.log("Description", "This test case will verify Class section in mysql db when student is moved to other class by DA", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 1;
            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String email = "testda@snapwiz.com";

            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar3 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

           /* String appendChar1="aCPF";
            String appendChar2="boRK";
            String appendChar3="ckdv";*/

            System.out.println("appendChar1:" + appendChar1);
            System.out.println("appendChar2:" + appendChar2);
            System.out.println("appendChar3:" + appendChar3);

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String stuemail = methodName + "st" + appendChar1 + "@snapwiz.com";
            String instTitle = methodName + "class" + appendChar1;

            //Teacher signup
            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369", dataIndex, false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create class
            System.out.println("classCode :" + classCode);
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1, classCode, dataIndex);//Sign up as a student1
            new Navigator().logout();//log out


            new Login().loginAsInstructor(appendChar1, dataIndex);
            String classCode2 = new Classes().createNewClass(appendChar2, dataIndex, "Grade 3", "Mathematics", "Math - Common Core");//Create a grade3 class
            System.out.println("classcode2:" + classCode2);
            new Assignment().addStudentFromManageClass(stuemail);

            String classCode3 = new Classes().createNewClass(appendChar3, dataIndex, "Grade 4", "Mathematics", "Math - Common Core");//Create a grade3 class
            System.out.println("classCode3:" + classCode3);
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1, dataIndex);
            long userID = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.userId");
            long classSectionId = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.loggedInClassSectionId");
            BigInteger user_Id = BigInteger.valueOf(userID);
            BigInteger classSection_Id = BigInteger.valueOf(classSectionId);
            DBConnect.Connect();
            ResultSet result = DBConnect.st.executeQuery("SELECT * FROM t_class_section_permission WHERE class_section_id = " + classSection_Id + " AND user_id = " + user_Id + ";");
            String allowAccess = null;
            while (result.next()) {
                System.out.println("Values:" + result.getString("allow_access"));
                allowAccess = result.getString("allow_access");
            }
            Thread.sleep(2000);
            if (!allowAccess.equals("1")) {
                CustomAssert.fail("Verify allow access", "Allow access is not 1");
            }
            new Navigator().logout();

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Navigator().navigateTo("manageDistrict");
            classEnrollment.classEnrollmentTab.click();//click on class enrollment tab
            selectColumnAndValue("Class Code", "Equals", classCode);
            moveUser(stuemail, classCode3);
            selectColumnAndValue("Class Code", "Equals", classCode);
            boolean movedStudentEntry = WebDriverUtil.isElementPresent(By.xpath("//*[text()='" + stuemail + "']"));
            CustomAssert.assertEquals(movedStudentEntry, false, "Verify moved student entry in previous class", "Student is moved to other class successfully", "Student is not moved to other class successfully");
            new Navigator().logout();

            new Login().loginAsStudent(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");
            boolean notEnrolled = driver.findElement(By.xpath("//span[text()='Not Enrolled']/../div/span[@title='" + instTitle + "']")).isDisplayed();
            CustomAssert.assertTrue(notEnrolled, "Verify not enrolled tag in removed class", "Removed Class section is present on Student side with a tag as Not Enrolled", "Removed Class section is not present on Student side with a tag as Not Enrolled");
            manageClass.join_Class.click();//click on join class;
            manageClass.classCodeTextBox.sendKeys(classCode);
            manageClass.join_ClassButton.click();//click on join class button
            actual = manageClass.alreadyEnrolledErrorMessage.getText();
            expected = "You are already enrolled in the class. Please contact your instructor to activate again.";
            CustomAssert.assertEquals(actual, expected, "Verify already enrolled notification message", "Notification message is as per expected", "Notification message is not as per expected");
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");
            driver.findElement(By.xpath("//span[@title='" + instTitle + "']")).click();//click on that class section from where student got removed
            String notEnrolledStatus = driver.findElement(By.xpath("//td[text()='" + stuemail + "']//..//td[5]//span[@class='text-not-enrolled']")).getAttribute("title");
            CustomAssert.assertEquals(notEnrolledStatus, "Not Enrolled", "Verify not enrolled status for moved student", "Status is as per expected", "Staus is not as per expected");
            new Navigator().logout();

            new Login().loginAsStudent(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");//Navigate to manageclass page
            assignments.dropdown_defaultClassName.click();//Click on class drop down

            for (int i = 0; i < assignments.classSectionDrpDownOptions.size(); i++) {
                if (assignments.classSectionDrpDownOptions.get(i).getText().equals(instTitle)) {
                    CustomAssert.fail("Verify class section from where student got removed", "Removed class section is displayed");
                }
            }
            driver.findElement(By.xpath("//span[@title='" + instTitle + "']/../../div[2]/following-sibling::span[text()='Visit Class']")).click();
            Thread.sleep(5000);
            assignments.dropdown_defaultClassName.click();//Click on class drop down
            Thread.sleep(5000);
            boolean found = false;
            for (int i = 0; i < assignments.classSectionDrpDownOptions.size(); i++) {
                if (assignments.classSectionDrpDownOptions.get(i).getText().equals(instTitle)) {
                    found = true;
                    break;
                }
            }
            if (found == false) {
                CustomAssert.fail("Verify class section from where student got removed", "Removed class section is not displayed");
            }

            long userID2 = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.userId");
            long classSectionId2 = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.loggedInClassSectionId");
            BigInteger user_Id2 = BigInteger.valueOf(userID2);
            System.out.println(user_Id2);
            BigInteger classSection_Id2 = BigInteger.valueOf(classSectionId2);
            System.out.println(classSection_Id2);
            DBConnect.Connect();
            ResultSet result2 = DBConnect.st.executeQuery("SELECT * FROM t_class_section_permission WHERE class_section_id = " + classSection_Id2 + " AND user_id = " + user_Id2 + ";");
            String allowAccess2 = null;
            while (result2.next()) {
                System.out.println("Values:" + result2.getString("allow_access"));
                allowAccess2 = result2.getString("allow_access");
            }
            if (!allowAccess2.equals("0")) {
                CustomAssert.fail("Verify allow access", "Allow access is not 0");
            }
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");
            driver.findElement(By.xpath("//span[@title='" + instTitle + "']")).click();//click on that class section from where student got removed
            Thread.sleep(2000);

            for (WebElement more : manageClass.moreLink) {
                if (more.isDisplayed()) {
                    more.click();//click on action dropdown
                    break;
                }
            }

            manageClass.addStudentLink.get(2).click();
            manageClass.userNameEmail.sendKeys(stuemail);
            Thread.sleep(2000);
            Driver.getWebDriver().findElement(By.cssSelector("body")).click();
            manageClass.userPassword.sendKeys("snapwiz");
            manageClass.retypePassword.sendKeys("snapwiz");
            manageClass.yesCreate.click();
            new Navigator().logout();

            new Login().loginAsStudent(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");//Navigate to manageclass page
            driver.findElement(By.xpath("//span[@title='" + instTitle + "']/../../div[2]/following-sibling::span[text()='Visit Class']")).click();
            Thread.sleep(5000);
            long userID1 = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.userId");
            long classSectionId1 = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.loggedInClassSectionId");
            BigInteger user_Id1 = BigInteger.valueOf(userID1);
            System.out.println(user_Id1);
            BigInteger classSection_Id1 = BigInteger.valueOf(classSectionId1);
            System.out.println(classSection_Id1);
            DBConnect.Connect();
            ResultSet result1 = DBConnect.st.executeQuery("SELECT * FROM t_class_section_permission WHERE class_section_id = " + classSection_Id1 + " AND user_id = " + user_Id1 + ";");
            String allowAccess1 = null;
            while (result1.next()) {
                System.out.println("Values:" + result1.getString("allow_access"));
                allowAccess1 = result1.getString("allow_access");
            }
            if (!allowAccess1.equals("1")) {
                CustomAssert.fail("Verify allow access", "Allow access is not 1");
            }
            new Navigator().logout();

        } catch (Exception e) {
            Assert.fail("Exception in TC verifyClassSectionInDB of class VerifyClassSectionInDB", e);
        }
    }


    @Test(priority = 2, enabled = true)
    public void studentMovedByDAAndAddBySA() {
        try {

            ReportUtil.log("Description", "This test case will verify Class section in mysql db when student is moved to other class by DA", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 1;
            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String email = "testda@snapwiz.com";
            String saEmail = "testsa@snapwiz.com";

            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar3 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

           /* String appendChar1="aQMQ";
            String appendChar2="bRWi";
            String appendChar3="cAqO";*/

            System.out.println("appendChar1:" + appendChar1);
            System.out.println("appendChar2:" + appendChar2);
            System.out.println("appendChar3:" + appendChar3);

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String stuemail = methodName + "st" + appendChar1 + "@snapwiz.com";
            String stuName = methodName + "st" + appendChar1;
            String instTitle = methodName + "class" + appendChar1;

            //Teacher signup
            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369", dataIndex, false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create class
            System.out.println("classCode :" + classCode);
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1, classCode, dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);
            String classCode2 = new Classes().createNewClass(appendChar2, dataIndex, "Grade 3", "Mathematics", "Math - Common Core");//Create a grade3 class
            System.out.println("classcode2:" + classCode2);
            new Assignment().addStudentFromManageClass(stuemail);

            String classCode3 = new Classes().createNewClass(appendChar3, dataIndex, "Grade 4", "Mathematics", "Math - Common Core");//Create a grade3 class
            System.out.println("classCode3:" + classCode3);
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1, dataIndex);
            long userID = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.userId");
            long classSectionId = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.loggedInClassSectionId");
            BigInteger user_Id = BigInteger.valueOf(userID);
            BigInteger classSection_Id = BigInteger.valueOf(classSectionId);
            DBConnect.Connect();
            ResultSet result = DBConnect.st.executeQuery("SELECT * FROM t_class_section_permission WHERE class_section_id = " + classSection_Id + " AND user_id = " + user_Id + ";");
            String allowAccess = null;
            while (result.next()) {
                System.out.println("Values:" + result.getString("allow_access"));
                allowAccess = result.getString("allow_access");
            }
            Thread.sleep(2000);
            if (!allowAccess.equals("1")) {
                CustomAssert.fail("Verify allow access", "Allow access is not 1");
            }
            new Navigator().logout();

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Navigator().navigateTo("manageDistrict");
            classEnrollment.classEnrollmentTab.click();//click on class enrollment tab
            selectColumnAndValue("Class Code", "Equals", classCode);
            moveUser(stuemail, classCode3);
            selectColumnAndValue("Class Code", "Equals", classCode);
            boolean movedStudentEntry = WebDriverUtil.isElementPresent(By.xpath("//*[text()='" + stuemail + "']"));
            CustomAssert.assertEquals(movedStudentEntry, false, "Verify moved student entry in previous class", "Student is moved to other class successfully", "Student is not moved to other class successfully");
            new Navigator().logout();

            new Login().loginAsStudent(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");
            boolean notEnrolled = driver.findElement(By.xpath("//span[text()='Not Enrolled']/../div/span[@title='" + instTitle + "']")).isDisplayed();
            CustomAssert.assertTrue(notEnrolled, "Verify not enrolled tag in removed class", "Removed Class section is present on Student side with a tag as Not Enrolled", "Removed Class section is not present on Student side with a tag as Not Enrolled");
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");
            driver.findElement(By.xpath("//span[@title='" + instTitle + "']")).click();//click on that class section from where student got removed
            String notEnrolledStatus = driver.findElement(By.xpath("//td[text()='" + stuemail + "']//..//td[5]//span[@class='text-not-enrolled']")).getAttribute("title");
            CustomAssert.assertEquals(notEnrolledStatus, "Not Enrolled", "Verify not enrolled status for moved student", "Status is as per expected", "Staus is not as per expected");
            new Navigator().logout();

            new Login().loginAsStudent(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");//Navigate to manageclass page
            driver.findElement(By.xpath("//span[@title='" + instTitle + "']/../../div[2]/following-sibling::span[text()='Visit Class']")).click();
            Thread.sleep(5000);
            long userID2 = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.userId");
            long classSectionId2 = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.loggedInClassSectionId");
            BigInteger user_Id2 = BigInteger.valueOf(userID2);
            System.out.println(user_Id2);
            BigInteger classSection_Id2 = BigInteger.valueOf(classSectionId2);
            System.out.println(classSection_Id2);
            DBConnect.Connect();
            ResultSet result2 = DBConnect.st.executeQuery("SELECT * FROM t_class_section_permission WHERE class_section_id = " + classSection_Id2 + " AND user_id = " + user_Id2 + ";");
            String allowAccess2 = null;
            while (result2.next()) {
                System.out.println("Values:" + result2.getString("allow_access"));
                allowAccess2 = result2.getString("allow_access");
            }
            if (!allowAccess2.equals("0")) {
                CustomAssert.fail("Verify allow access", "Allow access is not 0");
            }
            new Navigator().logout();

            new Login().loginAsDA(dataIndex, saEmail); //login as School Admin
            addUserAtSaSide(classCode, stuemail, stuName);
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");
            driver.findElement(By.xpath("//span[@title='" + instTitle + "']")).click();//click on that class section from where student got removed
            Thread.sleep(3000);
            List<WebElement> activeUser = driver.findElements(By.xpath("//td[text()='" + stuemail + "']//..//td[5]"));
            String user = activeUser.get(1).getText().trim();
            if (!user.contains("Last signed")) {
                CustomAssert.fail("Verify the active user", "User is not activated");
            }
            new Navigator().logout();

            new Login().loginAsStudent(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");//Navigate to manageclass page
            driver.findElement(By.xpath("//span[@title='" + instTitle + "']/../../div[2]/following-sibling::span[text()='Visit Class']")).click();
            Thread.sleep(5000);
            long userID1 = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.userId");
            long classSectionId1 = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.loggedInClassSectionId");
            BigInteger user_Id1 = BigInteger.valueOf(userID1);
            System.out.println(user_Id1);
            BigInteger classSection_Id1 = BigInteger.valueOf(classSectionId1);
            System.out.println(classSection_Id1);
            DBConnect.Connect();
            ResultSet result1 = DBConnect.st.executeQuery("SELECT * FROM t_class_section_permission WHERE class_section_id = " + classSection_Id1 + " AND user_id = " + user_Id1 + ";");
            String allowAccess1 = null;
            while (result1.next()) {
                System.out.println("Values:" + result1.getString("allow_access"));
                allowAccess1 = result1.getString("allow_access");
            }
            if (!allowAccess1.equals("1")) {
                CustomAssert.fail("Verify allow access", "Allow access is not 1");
            }
            new Navigator().logout();

        } catch (Exception e) {
            Assert.fail("Exception in TC studentMovedByDAAndAddBySA of class VerifyClassSectionInDB", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void studentMovedToOtherClassUnderSameSchoolBySA() {
        try {

            ReportUtil.log("Description", "This test case will verify when Student is moved to other class under same school by SA", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 1;
            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String email = "testda@snapwiz.com";
            String saEmail = "testsa@snapwiz.com";

            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar3 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

           /* String appendChar1 = "agKT";
            String appendChar2 = "bSrH";
            String appendChar3 = "cJyr";
*/
            System.out.println("appendChar1:" + appendChar1);
            System.out.println("appendChar2:" + appendChar2);
            System.out.println("appendChar3:" + appendChar3);

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String stuemail = methodName + "st" + appendChar1 + "@snapwiz.com";
            String instTitle = methodName + "class" + appendChar1;

            //Teacher signup
            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369", dataIndex, false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create class
            System.out.println("classCode :" + classCode);
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1, classCode, dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);
            String classCode2 = new Classes().createNewClass(appendChar2, dataIndex, "Grade 3", "Mathematics", "Math - Common Core");//Create a grade3 class
            System.out.println("classcode2:" + classCode2);
            new Assignment().addStudentFromManageClass(stuemail);

            String classCode3 = new Classes().createNewClass(appendChar3, dataIndex, "Grade 4", "Mathematics", "Math - Common Core");//Create a grade3 class
            System.out.println("classCode3:" + classCode3);
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1, dataIndex);
            long userID = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.userId");
            long classSectionId = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.loggedInClassSectionId");
            BigInteger user_Id = BigInteger.valueOf(userID);
            BigInteger classSection_Id = BigInteger.valueOf(classSectionId);
            DBConnect.Connect();
            ResultSet result = DBConnect.st.executeQuery("SELECT * FROM t_class_section_permission WHERE class_section_id = " + classSection_Id + " AND user_id = " + user_Id + ";");
            String allowAccess = null;
            while (result.next()) {
                System.out.println("Values:" + result.getString("allow_access"));
                allowAccess = result.getString("allow_access");
            }
            Thread.sleep(2000);
            if (!allowAccess.equals("1")) {
                CustomAssert.fail("Verify allow access", "Allow access is not 1");
            }
            new Navigator().logout();

            new Login().loginAsDA(dataIndex, saEmail); //login as School Admin
            new Navigator().navigateTo("manageSchool");
            classEnrollment.classEnrollmentTab.click();
            Thread.sleep(2000);
            selectColumnAndValue("Class Code", "Equals", classCode);
            moveUser(stuemail, classCode3);
            selectColumnAndValue("Class Code", "Equals", classCode);
            boolean movedStudentEntry = WebDriverUtil.isElementPresent(By.xpath("//*[text()='" + stuemail + "']"));
            CustomAssert.assertEquals(movedStudentEntry, false, "Verify moved student entry in previous class", "Student is moved to other class successfully", "Student is not moved to other class successfully");
            new Navigator().logout();

            new Login().loginAsStudent(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");
            boolean notEnrolled = driver.findElement(By.xpath("//span[text()='Not Enrolled']/../div/span[@title='" + instTitle + "']")).isDisplayed();
            CustomAssert.assertTrue(notEnrolled, "Verify not enrolled tag in removed class", "Removed Class section is present on Student side with a tag as Not Enrolled", "Removed Class section is not present on Student side with a tag as Not Enrolled");
            new Navigator().logout();

            new Login().loginAsStudent(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");//Navigate to manage class page
            driver.findElement(By.xpath("//span[@title='" + instTitle + "']/../../div[2]/following-sibling::span[text()='Visit Class']")).click();
            Thread.sleep(5000);
            long userID2 = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.userId");
            long classSectionId2 = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.loggedInClassSectionId");
            BigInteger user_Id2 = BigInteger.valueOf(userID2);
            System.out.println(user_Id2);
            BigInteger classSection_Id2 = BigInteger.valueOf(classSectionId2);
            System.out.println(classSection_Id2);
            DBConnect.Connect();
            ResultSet result2 = DBConnect.st.executeQuery("SELECT * FROM t_class_section_permission WHERE class_section_id = " + classSection_Id2 + " AND user_id = " + user_Id2 + ";");
            String allowAccess2 = null;
            while (result2.next()) {
                System.out.println("Values:" + result2.getString("allow_access"));
                allowAccess2 = result2.getString("allow_access");
            }
            if (!allowAccess2.equals("0")) {
                CustomAssert.fail("Verify allow access", "Allow access is not 0");
            }
            new Navigator().logout();

            new Login().loginAsDA(dataIndex, saEmail); //login as School Admin
            new Navigator().navigateTo("manageSchool");
            classEnrollment.classEnrollmentTab.click();
            Thread.sleep(2000);
            selectColumnAndValue("Class Code", "Equals", classCode3);
            moveUser(stuemail, classCode);
            selectColumnAndValue("Class Code", "Equals", classCode3);
            boolean movedStudentEntry1 = WebDriverUtil.isElementPresent(By.xpath("//*[text()='" + stuemail + "']"));
            CustomAssert.assertEquals(movedStudentEntry1, false, "Verify moved student entry in previous class", "Student is moved to other class successfully", "Student is not moved to other class successfully");
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");
            driver.findElement(By.xpath("//span[@title='" + instTitle + "']")).click();//click on that class section from where student got removed
            Thread.sleep(3000);
            List<WebElement> activeUser = driver.findElements(By.xpath("//td[text()='" + stuemail + "']//..//td[5]"));
            String user = activeUser.get(1).getText().trim();
            if (!user.contains("Last signed")) {
                CustomAssert.fail("Verify the active user", "User is not activated");
            }
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");
            Thread.sleep(3000);
            String notEnrolledStatus = driver.findElement(By.xpath("//td[text()='" + stuemail + "']//..//td[5]//span[@class='text-not-enrolled']")).getAttribute("title");
            CustomAssert.assertEquals(notEnrolledStatus, "Not Enrolled", "Verify not enrolled status for moved student", "Status is as per expected", "Staus is not as per expected");
            new Navigator().logout();

            new Login().loginAsStudent(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");//Navigate to manageclass page
            driver.findElement(By.xpath("//span[@title='" + instTitle + "']/../../div[2]/following-sibling::span[text()='Visit Class']")).click();
            Thread.sleep(5000);
            long userID1 = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.userId");
            long classSectionId1 = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.loggedInClassSectionId");
            BigInteger user_Id1 = BigInteger.valueOf(userID1);
            System.out.println(user_Id1);
            BigInteger classSection_Id1 = BigInteger.valueOf(classSectionId1);
            System.out.println(classSection_Id1);
            DBConnect.Connect();
            ResultSet result1 = DBConnect.st.executeQuery("SELECT * FROM t_class_section_permission WHERE class_section_id = " + classSection_Id1 + " AND user_id = " + user_Id1 + ";");
            String allowAccess1 = null;
            while (result1.next()) {
                System.out.println("Values:" + result1.getString("allow_access"));
                allowAccess1 = result1.getString("allow_access");
            }
            if (!allowAccess1.equals("1")) {
                CustomAssert.fail("Verify allow access", "Allow access is not 1");
            }
            new Navigator().logout();

        } catch (Exception e) {
            Assert.fail("Exception in TC studentMovedToOtherClassUnderSameSchoolBySA of class VerifyClassSectionInDB", e);
        }
    }

    WebDriver firefoxdriver;
    @Test(priority = 4, enabled = true)
    public void verifyStudentIsAbleToAttemptNotOpenActiveAssignemnts() {
        try {

            ReportUtil.log("Description", "This test case will verify student is able to attempt Not Open active assignemnts", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 1;
            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String email = "testda@snapwiz.com";

            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar3 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

          /*  String appendChar1="amSY";
            String appendChar2="bQmH";
            String appendChar3="cOSR";*/

            System.out.println("appendChar1:" + appendChar1);
            System.out.println("appendChar2:" + appendChar2);
            System.out.println("appendChar3:" + appendChar3);

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String stuemail = methodName + "st" + appendChar1 + "@snapwiz.com";
            String insemail = methodName + "ins" + appendChar1 + "@snapwiz.com";

            String instTitle = methodName + "class" + appendChar1;

            //Teacher signup
            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369", dataIndex, false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create class
            System.out.println("classCode :" + classCode);
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1, classCode, dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);
            String classCode2 = new Classes().createNewClass(appendChar2, dataIndex, "Grade 3", "Mathematics", "Math - Common Core");//Create a grade3 class
            System.out.println("classcode2:" + classCode2);
            new Assignment().addStudentFromManageClass(stuemail);

            String classCode3 = new Classes().createNewClass(appendChar3, dataIndex, "Grade 4", "Mathematics", "Math - Common Core");//Create a grade3 class
            System.out.println("classCode3:" + classCode3);

            new Assignment().create(dataIndex, "truefalse");//Create an assignment1
            new Assignment().assignToStudent(dataIndex, appendChar1);//Assign assignment2 to class1
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Navigator().logout();//log out

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Navigator().navigateTo("manageDistrict");
            classEnrollment.classEnrollmentTab.click();//click on class enrollment tab
            selectColumnAndValue("Class Code", "Equals", classCode);
            moveUser(stuemail, classCode3);
            new Navigator().logout();

            new Login().loginAsStudent(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");
            driver.findElement(By.xpath("//span[@title='" + instTitle + "']/../../div[2]/following-sibling::span[text()='Visit Class']")).click();
            Thread.sleep(5000);
            driver.findElement(By.cssSelector("h4.as-title")).click();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.className("true-false-student-content-text")), 900);
            List<WebElement> allOptions = driver.findElements(By.className("true-false-student-content-text"));
            int index = 0;
            for (WebElement option : allOptions) {
                if (option.getText().equals("True")) {
                    break;
                }
                index++;
            }

            firefoxdriver = new ReinitDriver().startDriver(firefoxdriver);
            new Login().directLoginAsInstructor(1, insemail, firefoxdriver);
            firefoxdriver.findElement(By.id("assignment")).click();
            WebDriverUtil.waitForAjax(firefoxdriver, 60);
            firefoxdriver.findElement(By.id("select2-as-header-classes-selectbox-container")).click();//Click on class drop down
            firefoxdriver.findElement(By.cssSelector(".select2-search__field")).sendKeys(methodName + "class" + appendChar1);
            new KeysSend().sendKeyBoardKeys("^");
            Thread.sleep(5000);
            firefoxdriver.findElements(By.cssSelector("span.as-response")).get(0).click();
            Thread.sleep(1000);
            String notStarted = firefoxdriver.findElement(By.cssSelector("span[class='text-not-enrolled']")).getAttribute("title");
            Assert.assertEquals(notStarted, "Not Enrolled");
            if(firefoxdriver!=null)
                firefoxdriver.quit();

            new Click().clickbylist("true-false-student-answer-label", index);//click on correct option
            driver.findElement(By.id("as-take-next-question")).click();//click on next
            new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'btn sty-blue submit')]")));
            driver.findElement(By.xpath("//span[contains(@class,'btn sty-blue submit')]")).click();//click on Submit
            Thread.sleep(2000);
            performance.backArrow.click();//click on back arrow
            CustomAssert.assertEquals(assignments.assignment_Status.getText().trim(), "Grade Released", "Verify assignment status", "Assignment status is greade released", "Assignment status is not as per expected");
            new Navigator().logout();//log out


        } catch (Exception e) {
            if(firefoxdriver!=null)
                firefoxdriver.quit();
            Assert.fail("Exception in TC verifyStudentIsAbleToAttemptNotOpenActiveAssignemnts of class VerifyClassSectionInDB", e);
        }
    }


    @Test(priority = 5, enabled = true)
    public void studentIsAbleToAttemptInProgressCommonAssignemnt() {
        try {

            ReportUtil.log("Description", "This test case will verify student is able to attempt In Progress common assignment", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 5;
            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String email = "testsa@snapwiz.com";

            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar3 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            /*String appendChar1="aTSY";
            String appendChar2="bNnx";
            String appendChar3="ckLx";
*/
            System.out.println("appendChar1:" + appendChar1);
            System.out.println("appendChar2:" + appendChar2);
            System.out.println("appendChar3:" + appendChar3);

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String stuemail = methodName + "st" + appendChar1 + "@snapwiz.com";
            String insemail = methodName + "ins" + appendChar1 + "@snapwiz.com";

            String instTitle = methodName + "class" + appendChar1;

            //Teacher signup
            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Assignment().createByDA(dataIndex, "truefalse");// includes true false and essay

            new Common().waitForToastBlockerToClose(120);
            WebDriverUtil.clickOnElementUsingJavascript(addQuestion.reviewAssessment);
            WebDriverUtil.waitForAjax(getWebDriver(), 60);
            assignmentReview.btn_AssignOrPublish.click();
            WebDriverUtil.waitForAjax(getWebDriver(), 60);

            getWebDriver().findElement(By.cssSelector(".p-l-md>.radio>div")).click();
            WebDriverUtil.executeJavascript("$('#school+ins').click()"); //click on the ryan international
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#publish-to-district-btn"))); //click on the publish
            new Navigator().navigateTo("dashboard");
            new Navigator().logout();//log out

            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369", dataIndex, false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create class
            System.out.println("classCode :" + classCode);
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1, classCode, dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);
            String classCode2 = new Classes().createNewClass(appendChar2, dataIndex, "Grade 3", "Mathematics", "Math - Common Core");//Create a grade3 class
            System.out.println("classcode2:" + classCode2);
            new Assignment().addStudentFromManageClass(stuemail);

            String classCode3 = new Classes().createNewClass(appendChar3, dataIndex, "Grade 4", "Mathematics", "Math - Common Core");//Create a grade3 class
            System.out.println("classCode3:" + classCode3);

            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as an instructor
            new Assignment().useExistingAssignment(dataIndex, appendChar1);//Assign assessment to class created by DAAdmin i above test case
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.dropdown_defaultClassName.click();//Click on class drop down
            assignments.classSection_textbox.sendKeys(methodName + "class" + appendChar1);
            new KeysSend().sendKeyBoardKeys("^");
            Thread.sleep(5000);
            assignments.getAssessmentName().click();//Click on open
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");
            driver.findElement(By.xpath("//span[@title='" + instTitle + "']/../../div[2]/following-sibling::span[text()='Visit Class']")).click();
            Thread.sleep(5000);
            driver.findElement(By.cssSelector("h4.as-title")).click();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.className("true-false-student-content-text")), 900);
            new Navigator().navigateTo("manageclass");
            new Navigator().logout();

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Navigator().navigateTo("manageSchool");
            classEnrollment.classEnrollmentTab.click();//click on class enrollment tab
            selectColumnAndValue("Class Code", "Equals", classCode);
            moveUser(stuemail, classCode3);
            new Navigator().logout();

            new Login().loginAsStudent(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");
            driver.findElement(By.xpath("//span[@title='" + instTitle + "']/../../div[2]/following-sibling::span[text()='Visit Class']")).click();
            Thread.sleep(5000);
            driver.findElement(By.cssSelector("h4.as-title")).click();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.className("true-false-student-content-text")), 900);
            List<WebElement> allOptions = driver.findElements(By.className("true-false-student-content-text"));
            int index = 0;
            for (WebElement option : allOptions) {
                if (option.getText().equals("True")) {
                    break;
                }
                index++;
            }

            firefoxdriver = new ReinitDriver().startDriver(firefoxdriver);
            new Login().directLoginAsInstructor(1, insemail, firefoxdriver);
            firefoxdriver.findElement(By.id("assignment")).click();
            Thread.sleep(1000);
            firefoxdriver.findElements(By.cssSelector("span.as-response")).get(0).click();
            Thread.sleep(1000);
            String notStarted = firefoxdriver.findElement(By.cssSelector("span[class='text-not-enrolled']")).getAttribute("title");
            Assert.assertEquals(notStarted, "Not Enrolled");
            if(firefoxdriver!=null)
                firefoxdriver.quit();
            new Click().clickbylist("true-false-student-answer-label", index);//click on correct option
            driver.findElement(By.id("as-take-next-question")).click();//click on next
            new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'btn sty-blue submit')]")));
            driver.findElement(By.xpath("//span[contains(@class,'btn sty-blue submit')]")).click();//click on Submit
            Thread.sleep(2000);
            performance.backArrow.click();//click on back arrow
            CustomAssert.assertEquals(assignments.assignment_Status.getText().trim(), "Grade Released", "Verify assignment status", "Assignment status is greade released", "Assignment status is not as per expected");
            new Navigator().logout();//log out


        } catch (Exception e) {
            if(firefoxdriver!=null)
                firefoxdriver.quit();
            Assert.fail("Exception in TC studentIsAbleToAttemptInProgressCommonAssignemnt of class VerifyClassSectionInDB", e);
        }
    }


    @Test(priority = 6, enabled = true)
    public void verifyClassSectionInMongoDB() {
        try {

            ReportUtil.log("Description", "This test case will verify Class section in mysql db when student is moved to other class by DA", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 6;
            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String email = "testda@snapwiz.com";
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));


            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar3 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

         /*   String appendChar1="aTaT";
            String appendChar2="bApr";
            String appendChar3="crDS";*/

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String stuemail = methodName + "st" + appendChar1 + "@snapwiz.com";
            String instTitle = methodName + "class" + appendChar1;

            System.out.println("appendChar1:" + appendChar1);
            System.out.println("appendChar2:" + appendChar2);
            System.out.println("appendChar3:" + appendChar3);

            //Teacher signup
            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369", dataIndex, false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create class
            System.out.println("classCode :" + classCode);
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1, classCode, dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar2, classCode, dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar3, classCode, dataIndex);//Sign up as a student1
            new Navigator().logout();//log out


            new SignUp().teacher(appendChar2, dataIndex);//Sign up as an instructor1
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("share-with_annoninput")));
            new Click().clickByid("share-with_annoninput");
            new TextSend().textsendbycssSelector("654321369","input[class='maininput maininput-center-placeholder']"); //adding first three characters to the school name
            Thread.sleep(2000);
            List<WebElement> suggestedNames =  driver.findElements(By.xpath("/*//*[starts-with(@rel, 'inst_')]"));
            suggestedNames.get(1).click();
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode2 = new Classes().createClass(appendChar2, dataIndex);//Create class
            System.out.println("classCode2 :" + classCode2);
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);
            new Assignment().create(dataIndex, "truefalse");//Create an assignment1
            new Assignment().assignToStudent(dataIndex, appendChar1);//Assign assignment2 to class1
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1, dataIndex);
            long userID = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.userId");
            long classSectionId = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.loggedInClassSectionId");
            BigInteger user_Id = BigInteger.valueOf(userID);
            BigInteger classSection_Id = BigInteger.valueOf(classSectionId);
            DBConnect.Connect();
            ResultSet result = DBConnect.st.executeQuery("SELECT * FROM t_class_section_permission WHERE class_section_id = " + classSection_Id + " AND user_id = " + user_Id + ";");
            String allowAccess = null;
            while (result.next()) {
                System.out.println("Values:" + result.getString("allow_access"));
                allowAccess = result.getString("allow_access");

            }
            Thread.sleep(2000);
            if (!allowAccess.equals("1")) {
                CustomAssert.fail("Verify allow access", "Allow access is not 1");
            }

            DBConnect.Connect();
            ResultSet result1 = DBConnect.st.executeQuery("select * from t_assignment_details where assignment_name='"+assessmentname+"';");
            String assignmentID=null;
            while (result1.next()) {
                assignmentID = result1.getString("assignment_id");

            }
            System.out.println("assignmentID:"+assignmentID);
            DBCollection collection=DBConnect.mongoConnect("assignments");
            Long csId= DBConnect.collectionValue(collection,assignmentID,"csId");
            Long sId= DBConnect.collectionValue(collection,assignmentID,"sId");

            System.out.println("csId:"+csId);
            System.out.println("sId:"+sId);
            new Navigator().logout();//log out

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Navigator().navigateTo("manageDistrict");
            classEnrollment.classEnrollmentTab.click();//click on class enrollment tab
            selectColumnAndValue("Class Code", "Equals", classCode);
            moveUser(stuemail, classCode2);
            new Navigator().logout();

            new Login().loginAsStudent(appendChar1, dataIndex);
            new Navigator().navigateTo("manageclass");
            boolean notEnrolled = driver.findElement(By.xpath("//span[text()='Not Enrolled']/../div/span[@title='" + instTitle + "']")).isDisplayed();
            CustomAssert.assertTrue(notEnrolled, "Verify not enrolled tag in removed class", "Removed Class section is present on Student side with a tag as Not Enrolled", "Removed Class section is not present on Student side with a tag as Not Enrolled");
            driver.findElement(By.xpath("//span[@title='" + instTitle + "']/../../div[2]/following-sibling::span[text()='Visit Class']")).click();
            Thread.sleep(5000);
            long userID2 = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.userId");
            long classSectionId2 = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.loggedInClassSectionId");
            BigInteger user_Id2 = BigInteger.valueOf(userID2);
            System.out.println(user_Id2);
            BigInteger classSection_Id2 = BigInteger.valueOf(classSectionId2);
            System.out.println(classSection_Id2);
            DBConnect.Connect();
            ResultSet result2 = DBConnect.st.executeQuery("SELECT * FROM t_class_section_permission WHERE class_section_id = " + classSection_Id2 + " AND user_id = " + user_Id2 + ";");
            String allowAccess2 = null;
            while (result2.next()) {
                System.out.println("Values:" + result2.getString("allow_access"));
                allowAccess2 = result2.getString("allow_access");
            }
            if (!allowAccess2.equals("0")) {
                CustomAssert.fail("Verify allow access", "Allow access is not 0");
            }

            new Navigator().navigateTo("dashboard");
            new Navigator().navigateTo("assignment");
            long userID1 = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.userId");
            long classSectionId1 = (Long) ((JavascriptExecutor) driver).executeScript("return userJSON.loggedInClassSectionId");
            BigInteger user_Id1 = BigInteger.valueOf(userID1);
            BigInteger classSection_Id1 = BigInteger.valueOf(classSectionId1);
            DBConnect.Connect();
            ResultSet result3 = DBConnect.st.executeQuery("SELECT * FROM t_class_section_permission WHERE class_section_id = " + classSection_Id1 + " AND user_id = " + user_Id1 + ";");
            String allowAccess3 = null;
            while (result3.next()) {
                System.out.println("Values:" + result3.getString("allow_access"));
                allowAccess3 = result3.getString("allow_access");
            }
            Thread.sleep(2000);
            if (!allowAccess3.equals("1")) {
                CustomAssert.fail("Verify allow access", "Allow access is not 1");
            }

            DBConnect.Connect();
            ResultSet result4 = DBConnect.st.executeQuery("select * from t_assignment_details where assignment_name='"+assessmentname+"';");
            String assignmentID2=null;
            while (result4.next()) {
                assignmentID2 = result4.getString("assignment_id");

            }
            System.out.println("assignmentID:"+assignmentID2);
            DBCollection collection3=DBConnect.mongoConnect("assignments");
            Long csId1= DBConnect.collectionValue(collection3,assignmentID2,"csId");
            Long sId1= DBConnect.collectionValue(collection3,assignmentID2,"sId");
            System.out.println("csId1:"+csId1);
            System.out.println("sId1:"+sId1);
            String csid3 = String.valueOf(csId);
            String csid4 = String.valueOf(csId1);
            String sid3 = String.valueOf(sId);
            String sid4 = String.valueOf(sId1);
            if(!csid3.equals(csid4)){
                CustomAssert.fail("Verify csid","csid got changed");
            }
            if(!sid3.equals(sid4)){
                CustomAssert.fail("Verify sId1","sId1 got changed");
            }

        } catch (Exception e) {
            Assert.fail("Exception in TC verifyClassSectionInMongoDB of class VerifyClassSectionInDB", e);
        }
    }


    @Test(priority = 7, enabled = true)
    public void verifyExistingStudentsWhenGoogleClassroomCodeIsChanged() {
        try {

            ReportUtil.log("Description", "This test case will Verify existing students when google classroom code is changed", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 7;
            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String email = "testda@snapwiz.com";
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            //String appendChar = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar="aGPT";

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String stuemail = methodName + "st" + appendChar + "@snapwiz.com";
            String instTitle = methodName + "class" + appendChar;

            System.out.println("appendChar1:" + appendChar);

            //Teacher signup
            String googleClassroomUrl = "https://classroom.google.com";
            String googleClassroomTeacher = "teacher_qoKs-n2jiAkoMVZQZvQD@classroom-dev.com";
            String googleClassroomPass = "SnapEdu123";

            String googleClass1 = "Automation Class1(Do not use this class)";
            String googleClass2 = "Automation Class3(Do not use this class)";

            String st1Name = methodName+"st"+appendChar;
            String googleSt1Name = "student1 User";
            String googleSt2Name = "student2 User";

            String googleSt1Email = "student1_IdCb_QXSlqn6x5HnQFH3@classroom-dev.com";

            //ryanda@snapwiz.com da is present in the same district of instructor (Google class checkbox is checked)
            /*new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("987654963",dataIndex,false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode1 =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out
*/
           // new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor

            //Login in google classroom as a teacher
            GoogleclassRoom googleclassRoom = PageFactory.initElements(driver,GoogleclassRoom.class);

            new Login().directLoginInGoogleClassRoom(dataIndex, googleClassroomTeacher, googleClassroomPass);//Teacher login in google classroom
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
       /*     List<WebElement> classes = googleclassRoom.className_onClassCard;

            for (WebElement c:classes)
            {
                if(c.getText().equals(googleClass1))
                {
                    c.click();//Select class1
                    break;
                }
            }

            new Click().clickIfDisplayed(dataIndex,googleclassRoom.studentTab);
            Thread.sleep(2000);
            String googleClassCode1="";
            for (WebElement ele:googleclassRoom.classCode){
                if (ele.isDisplayed()){
                    googleClassCode1=ele.getText().trim();
                }
            }
            System.out.println("googleClassCode1"+googleClassCode1);
            Thread.sleep(2000);
            googleclassRoom.navigator.click();//Click on navigator
            Thread.sleep(2000);
            googleclassRoom.classes.click();//click on classes

            for (WebElement c:classes)
            {
                if(c.getText().equals(googleClass2))
                {
                    c.click();//Select class2
                    break;
                }
            }

            new Click().clickIfDisplayed(dataIndex,googleclassRoom.studentTab);

            Thread.sleep(9000);
            String googleClassCode2=null;
            for(WebElement c : googleclassRoom.classCode)
            {
                if(c.isDisplayed())
                {
                    googleClassCode2 = c.getText().trim();
                    break;
                }
            }
            System.out.println("googleClassCode2"+googleClassCode2);
            driver.get(Config.baseURL);//Will be logged in as an instructor in edulastic
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            manageClass.syncWithGoogleClass.get(0).click();//Click on sync with google class
            Thread.sleep(2000);
            manageClass.textBox_googleClassCode.sendKeys(googleClassCode1);//Enter google class1 code having 2 students
            Thread.sleep(1000);
            manageClass.sync.click();//Click on sync button
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.doneAfterSync,60);*/
//k3j7tvx
//2jbaspo
           /* Thread.sleep(3000);
            new Assignment().create(dataIndex, "truefalse");//Create assessment
            new Assignment().assignToStudent(dataIndex, appendChar);//Assign to class*/
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            WebDriverUtil.waitForAjax(driver,60);
            assignments.more.click();//Click on more
            String parentWindow = driver.getWindowHandle();
            assignments.googleClassroom.click();//google class room
            Thread.sleep(6000);
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            googleclassRoom.chooseClass.get(0).click();
            driver.findElements(By.xpath("//div[text()='Automation Class1(Do not use this class)']")).get(1).click();
            googleclassRoom.chooseClass.get(1).click();
            googleclassRoom.createAssignment.get(1).click();
            googleclassRoom.goButton.click();
            googleclassRoom.assignButton.click();
            Thread.sleep(3000);
            driver.get(Config.baseURL);

        } catch (Exception e) {
            Assert.fail("Exception in TC verifyClassSectionInMongoDB of class VerifyClassSectionInDB", e);
        }
    }




    public void selectColumnAndValue(String column, String value, String inputTex) {
        try {
            WebDriver driver = getWebDriver();
            classEnrollment.selectAColumn.click();
            driver.findElement(By.xpath("//li[text()=' " + column + "']")).click();
            classEnrollment.selectAValue.click();
            driver.findElement(By.xpath("//li[text()=' " + value + "']")).click();
            classEnrollment.inputText.click();
            Thread.sleep(3000);
            classEnrollment.inputText.sendKeys(inputTex+ Keys.ENTER);
            WebDriverUtil.waitForAjax(driver, 60);
//            new KeysSend().sendKeyBoardKeys("^");
//            Thread.sleep(5000);
        } catch (Exception e) {
            Assert.fail("Exception in apphelper selectColumnAndValue", e);
        }
    }

    public void moveUser(String username, String classCode) {
        try {

            WebDriver driver = getWebDriver();
            int foundIndex = 0;
            for (WebElement usernames : classEnrollment.userName) {
                if (usernames.getAttribute("username").trim().equals(username.trim())) {
                    break;
                }
                foundIndex++;
            }
            Thread.sleep(5000);
            WebDriverUtil.clickOnElementUsingJavascript(classEnrollment.checkBox.get(foundIndex));
            Thread.sleep(5000);
            for (WebElement action : classEnrollment.actionDropDown) {
                if (action.isDisplayed()) {
                    action.click();//click on action dropdown
                    break;
                }
            }
            classEnrollment.moveUser.click();//click on move user
            Thread.sleep(10000);
            classEnrollment.classCode.sendKeys(classCode);
            driver.findElement(By.xpath("//html//body"));
            classEnrollment.move.click();//click on move
            classEnrollment.moveTextBox.sendKeys("MOVE");
            classEnrollment.yesMove.click();//click on yes
            Thread.sleep(5000);


        } catch (Exception e) {
            Assert.fail("Exception in apphelper selectColumnAndValue", e);
        }
    }


    public void addUserAtSaSide(String classCode, String email, String name) {
        try {
            WebDriver driver = getWebDriver();
            new Navigator().navigateTo("manageSchool");
            classEnrollment.classEnrollmentTab.click();
            Thread.sleep(2000);
            classEnrollment.actionDropDown.get(1).click();
            classEnrollment.addNewUser.click();//click on add new user
            classEnrollment.classCode.sendKeys(classCode);
            driver.findElement(By.xpath("//html//body"));
            Thread.sleep(2000);
            classEnrollment.userEmail.sendKeys(email);
            Thread.sleep(4000);
            WebDriverUtil.scrollIntoView(classEnrollment.yesCreate, false);
            WebDriverUtil.clickOnElementUsingJavascript(classEnrollment.yesCreate);
            Thread.sleep(5000);

        } catch (Exception e) {
            Assert.fail("Exception in method addUserAtSaSide in Assignment apphelper", e);
        }
    }


}
