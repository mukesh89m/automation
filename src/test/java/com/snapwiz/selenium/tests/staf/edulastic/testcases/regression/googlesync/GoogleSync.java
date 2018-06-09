package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.googlesync;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.StudentDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.googleclassroom.GoogleclassRoom;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.KeysSend;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Created by pragyas on 24-08-2016.
 */
public class GoogleSync extends Driver{

    WebDriver driver;
    SchoolPage schoolPage;
    ManageClass manageClass;

    @BeforeMethod()
    public void init(){
        driver = getWebDriver();
        schoolPage = PageFactory.initElements(driver, SchoolPage.class);
        manageClass = PageFactory.initElements(driver,ManageClass.class);

    }


    @Test(priority = 1,enabled = true)
    public void googleSyncSameDistrict(){
        try{
            ReportUtil.log("Description", "This case validates the google sync when edulastic ins and google classroom stu is in same district", "info");

            int dataIndex = 1;
            WebDriver driver = getWebDriver();

            String appendChar = StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA);
//            String appendChar = "cnyx";
            System.out.println("appendChar:"+appendChar);

            StudentDashboard studentDashboard = PageFactory.initElements(driver,StudentDashboard.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);

            String googleClassroomUrl = "https://classroom.google.com";

            String methodName = new Exception().getStackTrace()[0].getMethodName();

            String googleClassroomTeacher = "teacher_qoKs-n2jiAkoMVZQZvQD@classroom-dev.com";
            String googleClassroomPass = "SnapEdu123";

            String googleClass1 = "Automation Class1(Do not use this class)";
            String googleClass3 = "Automation Class3(Do not use this class)";

            String st1Name = methodName+"st"+appendChar;
            String googleSt1Name = "student1 User";
            String googleSt2Name = "student2 User";

            String googleSt1Email = "student1_IdCb_QXSlqn6x5HnQFH3@classroom-dev.com";


            //ryanda@snapwiz.com da is present in the same district of instructor (Google class checkbox is checked)
            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("987654963",dataIndex,false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode1 =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode1,dataIndex);//Sign up as a student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            new Assignment().create(dataIndex, "truefalse");//Create assessment
            new Assignment().assignToStudent(dataIndex, appendChar);//Assign to class
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            WebDriverUtil.waitForAjax(driver,60);
            driver.navigate().refresh();
            WebDriverUtil.waitTillVisibilityOfElement(assignments.more,60);
            WebDriverUtil.waitForAjax(driver,60);
            assignments.more.click();//Click on more

            // Verify "Sync with google classroom" & "Share with Google Class" is available for non-clever teacher when checkbox is selected
            CustomAssert.assertEquals(assignments.googleClassroom.getText(),"Google Classroom","Verify google classroom","google classroom is displayed as expected","Google classroom is not displayed");

            new Navigator().navigateTo("manageclass");//Navigate to manage class

            CustomAssert.assertEquals(manageClass.syncWithGoogleClass.get(0).getText(),"Sync with Google Classroom","Verify sync with google classroom","sync with google classroom is available","Sync with google classroom is not available");

            //canvas option should not be shown
            Boolean canvasSyncFound = new BooleanValue().presenceOfElementByWebElement(dataIndex,manageClass.syncWithCanvas);
            CustomAssert.assertEquals(canvasSyncFound,false,"Verify canvas sync option when google option is selected By DA","Canvas sync option is not displayed as expected","Canvas sync option is displayed");

            //Login in google classroom as a teacher
            GoogleclassRoom googleclassRoom = PageFactory.initElements(driver,GoogleclassRoom.class);

            new Login().directLoginInGoogleClassRoom(dataIndex, googleClassroomTeacher, googleClassroomPass);//Teacher login in google classroom

            List<WebElement> classes = googleclassRoom.className_onClassCard;

            for (WebElement c:classes)
            {
                if(c.getText().equals(googleClass1))
                {
                    c.click();//Select class1
                    break;
                }
            }
            new Click().clickIfDisplayed(dataIndex,googleclassRoom.studentTab);
            Thread.sleep(6000);
            String googleClassCode1="";
            for (WebElement ele:googleclassRoom.classCode){
                if (ele.isDisplayed()){
                    googleClassCode1=ele.getText().trim();
                }
            }
            Thread.sleep(2000);
            System.out.println("googleClassCode1 :"+googleClassCode1);
            googleclassRoom.navigator.click();//Click on navigator
            Thread.sleep(3000);
            googleclassRoom.classes.click();//click on classes

            List<WebElement> classesToSelectClass3 = googleclassRoom.className_onClassCard;

            for (WebElement c3:classesToSelectClass3)
            {
                if(c3.getText().equals(googleClass3))
                {
                    c3.click();//Select class3
                    break;
                }
            }
            Thread.sleep(8000);
            new Click().clickIfDisplayed(dataIndex,googleclassRoom.studentTab);

            Thread.sleep(2000);
            String googleClassCode3=null;
            for(WebElement c : googleclassRoom.classCode)
            {
                if(c.isDisplayed())
                {
                    googleClassCode3 = c.getText();
                    break;
                }
            }

            driver.get(Config.baseURL);//Will be logged in as an instructor in edulastic
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            manageClass.syncWithGoogleClass.get(0).click();//Click on sync with google class
            Thread.sleep(2000);
            //manageClass.textBox_googleClassCode.clear();//Enter google class1 code having 2 students
            manageClass.textBox_googleClassCode.sendKeys(googleClassCode1);//Enter google class1 code having 2 students
            Thread.sleep(1000);
            manageClass.sync.click();//Click on sync button
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.doneAfterSync,60);

            //Verify the list of students to be synced
            CustomAssert.assertEquals(String.valueOf(manageClass.studentsToBeSynced.size()),"2","Verify the number of students to be synced","No of students to be synced is correct","No of students to be synced is not correct");

            manageClass.doneAfterSync.click();//Click on done
            Thread.sleep(2000);

            //Upload should not be displayed once sync is done.
            boolean uploadDisplayed = new BooleanValue().presenceOfElementByWebElement(dataIndex,manageClass.uploadClassRoster);

            CustomAssert.assertEquals(uploadDisplayed,false,"Verify upload option after sync","Upload option is not displayed as expected","Upload option is displayed");

            //Verify Google Class code should be displayed in Manage Class for respective class after sync is completed
            CustomAssert.assertTrue(manageClass.classDetails.get(3).getText().contains("Google Classroom "+googleClassCode1),"Verify google class code after sync in manage class","Google class code is displayed as expected","Google class code is not displayed");

            //Verify that student should get added as it was in same district of ins
            CustomAssert.assertEquals(String.valueOf(manageClass.getStudentNames().size()),"3","Verify number of students","Students are not added as expected","Students are added even if the district were different");
            CustomAssert.assertEquals(manageClass.getStudentNames().get(0).getText(),st1Name,"Verify student name","Students are not added as expected","Students are added even if the district were different");
            CustomAssert.assertEquals(manageClass.getStudentNames().get(1).getText(),googleSt1Name,"Verify added google student","Student is added as expected","Student is not added even if it was in same district of edu ins");
            CustomAssert.assertEquals(manageClass.getStudentNames().get(2).getText(),googleSt2Name,"Verify added google student","Student is added as expected","Student is not added even if it was in same district of edu ins");

            //Change the password for google student1
            manageClass.checkBoxSelectStudent.get(1).click();//Select google student1
            manageClass.changePassword.click();//Click on change password
            manageClass.textBox_resetPassword.get(0).sendKeys("snapwiz123");//Enter new password
            manageClass.textBox_resetPassword.get(1).sendKeys("snapwiz123");//Confirm password
            manageClass.button_reset.click();//Click on reset button

            new Navigator().logout();//Instructor log out

            //Verify login of google sync student through Edulastic Credentials
            new Login().directLoginAsInstructor(dataIndex,googleSt1Email,"snapwiz123");//Login as google st1 in edu app after resetting password

            CustomAssert.assertEquals(studentDashboard.heading_dashboard.getText(),"Dashboard","Verify the google student log in in edu app","Google student is logged in as expected","Google student is not logged in");

            new Navigator().logout();//Instructor log out

            //Verify login of google sync student through google Credentials
            driver.get(googleClassroomUrl);//Open the google classroom link
            new Navigator().logoutGoogleClassRoom(dataIndex);//Log out
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.linkText("Sign in with a different account")),180);

            //Verify login of google sync student using google credentials
            new Login().directLoginInGoogleClassRoom(dataIndex,googleSt1Email,googleClassroomPass);//Login as student1 in google classroom

            WebDriverUtil.waitTillVisibilityOfElement(googleclassRoom.text_classroom,30);
            CustomAssert.assertEquals(googleclassRoom.text_classroom.getText(),"Classroom","Verify the login of google synced student","Google synced student is logged in","Google synced student is not logged in");

            new Navigator().logoutGoogleClassRoom(dataIndex);//log out
            try {
                WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.linkText("Sign in with a different account")),60);
            } catch (Exception e) {
                WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.linkText("Add account")),60);

            }

            new Login().directLoginInGoogleClassRoom(dataIndex,googleClassroomTeacher,googleClassroomPass);//Login as a teacher in google classroom

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor in edu app
            new Navigator().navigateTo("manageclass");//Navigate to manage class

            //Verify Edit of Google Class code
            manageClass.syncWithGoogleClass.get(0).click();//Click on sync with google class
            Thread.sleep(2000);
            manageClass.changeClassRoom.click();//Click on change class room
            new KeysSend().sendKeyBoardKeys("$");
            manageClass.textBox_googleClassCode.sendKeys(googleClassCode3);//Enter class code3 having 1 student
            Thread.sleep(1000);
            manageClass.sync.click();//Click on sync button
            manageClass.yesWhileSync.click();//Click on yes
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.doneAfterSync,60);
            manageClass.doneAfterSync.click();//Click on done
            Thread.sleep(1000);

            //One student should be displayed to be synced
            CustomAssert.assertEquals(String.valueOf(manageClass.studentsToBeSynced.size()),"1","Verify the number of students to be sunced","No of student s to be synced is correct","No of student s to be synced is not correct");

            // Old students should get disassociated from the class
            // New Students should get linked to the class
            CustomAssert.assertEquals(String.valueOf(manageClass.getStudentNames().size()),"3","Verify number of students after editing class code","Number of students are correct as expected","Number of students are not correct as expected");
            CustomAssert.assertEquals(manageClass.getStudentNames().get(0).getText(),st1Name,"Verify student name1","Students name is displayed as expected","Student name is not displayed correctly");
            CustomAssert.assertEquals(manageClass.getStudentNames().get(2).getText(),googleSt2Name,"Verify student name2","Students name is displayed as expected","Student name is not displayed correctly");

        }catch (Exception e)
        {
            Assert.fail("Exception in 'googleSyncSameDistrict' in 'GoogleSync' method", e);

        }
    }

    @Test(priority = 2,enabled = true)
    public void googleSyncDifferentDistrict(){
        try{

            ReportUtil.log("Description", "This case validates the google sync when edulastic ins and google classroom stu is in different district", "info");
            WebDriver driver = getWebDriver();

            int dataIndex = 1;
            String appendChar = StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA);
            //String appendChar = "bJsu";
            System.out.println("appendChar:"+appendChar);

            String googleClassroomTeacher = "teacher_qoKs-n2jiAkoMVZQZvQD@classroom-dev.com";
            String googleClassroomPass = "SnapEdu123";

            String googleClass1 = "Automation Class1(Do not use this class)";
            String googleClass2 = "Automation Class2(Do not use this class)";

            //ryanda@snapwiz.com da is present in the same district of instructor (Google class checkbox is checked)
            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor2
            new School().enterAndSelectSchool("1111111",dataIndex,false);//Create school in some other district (different from google student district)
            schoolPage.getContinueButton().click(); //clicking on save button
            new Classes().createClass(appendChar,dataIndex);//Create class

            //Login in google classroom as a teacher
            GoogleclassRoom googleclassRoom = PageFactory.initElements(driver,GoogleclassRoom.class);

            new Login().directLoginInGoogleClassRoom(dataIndex, googleClassroomTeacher, googleClassroomPass);//Teacher login in google classroom

            List<WebElement> classes = googleclassRoom.className_onClassCard;

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

            driver.get(Config.baseURL);//Will be logged in as an instructor in edulastic
            new Navigator().navigateTo("manageclass");//Navigate to manage class

            manageClass.syncWithGoogleClass.get(0).click();//Click on sync with google class
            System.out.println("GoogleClassCode2:"+googleClassCode2);
            manageClass.textBox_googleClassCode.sendKeys(googleClassCode2);//Enter google class code (No student in this class)
            Thread.sleep(1000);
            manageClass.sync.click();//Click on sync button

            WebDriverUtil.waitTillVisibilityOfElement(manageClass.doneAfterSync,60);

            //No student should be displayed for sync(As there is no google student in this google classroom)
            if(manageClass.studentsToBeSynced.size()>0)
            {
                CustomAssert.fail("Verify the pop up after sync of the class that has no student","Student is displayed");
            }

            manageClass.doneAfterSync.click();//Click on done
            Thread.sleep(2000);

            //Verify that no students should be displayed under this class
            if(manageClass.getStudentNames().size()>0)
            {
                CustomAssert.fail("Verify student in manage class","Student is added in manage class");
            }

            manageClass.syncWithGoogleClass.get(0).click();//Click on sync with google class
            Thread.sleep(2000);
            manageClass.changeClassRoom.click();//Click on change class room
            manageClass.textBox_googleClassCode.click();
            new KeysSend().sendKeyBoardKeys("$");
            manageClass.textBox_googleClassCode.sendKeys(googleClassCode1);//Enter class code that has students
            Thread.sleep(1000);
            manageClass.sync.click();//Click on sync button
            manageClass.yesWhileSync.click();//Click on yes
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.doneAfterSync,60);
            manageClass.doneAfterSync.click();//Click on done
            Thread.sleep(1000);

            //Two student should be displayed to be synced
            CustomAssert.assertEquals(String.valueOf(manageClass.studentsToBeSynced.size()),"2","Verify the number of students to be sunced","No of student s to be synced is correct","No of student s to be synced is not correct");


        }catch (Exception e){
            Assert.fail("Exception in 'googleSyncDifferentDistrict' in 'GoogleSync' method", e);

        }
    }

    @Test(priority = 3,enabled = true)
    public void googleSyncAfterResetAndDisable(){
        try{

            String googleClassroomUrl = "https://classroom.google.com";

            ReportUtil.log("Description", "This case validates the google sync after reset and disable the code and for two teachers", "info");
            GoogleclassRoom googleclassRoom = PageFactory.initElements(driver,GoogleclassRoom.class);

            int dataIndex = 1;
            WebDriver driver = getWebDriver();
            String appendChar1 = "a" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);

            //String appendChar1="aXw";
            // String appendChar2="bwp";

            System.out.println("appendChar1:"+appendChar1);
            System.out.println("appendChar2:"+appendChar2);

            String googleClassroomTeacher = "teacher_qoKs-n2jiAkoMVZQZvQD@classroom-dev.com";
            String googleClassroomPass = "SnapEdu123";

            String googleClass1 = "Automation Class1(Do not use this class)";

            //ryanda@snapwiz.com da is present in the same district of instructor (Google class checkbox is checked)
            new SignUp().teacher(appendChar1,dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("987654963", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            Thread.sleep(3000);
            new Classes().createClass(appendChar1,dataIndex);//Create class
            new Classes().createNewClass("class2",dataIndex,"Grade 2","Mathematics","Math - Common Core");//Create a grade2 class/
            new Navigator().logout();

            new SignUp().teacher(appendChar2,dataIndex);//Sign up as an instructor2
            new School().enterAndSelectSchool("987654963",dataIndex,false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            new Classes().createClass(appendChar1,dataIndex);//Create class

            //Login in google classroom as a teacher

            new Login().directLoginInGoogleClassRoom(dataIndex, googleClassroomTeacher, googleClassroomPass);//Teacher login in google classroom


            List<WebElement> classes = googleclassRoom.className_onClassCard;

            for (WebElement c:classes)
            {
                if(c.getText().equals(googleClass1))
                {
                    c.click();//Select class1
                    break;
                }
            }
            new Click().clickIfDisplayed(dataIndex,googleclassRoom.studentTab);

            Thread.sleep(9000);
            String googleClassCode1="";
            for (WebElement ele:googleclassRoom.classCode){
                if (ele.isDisplayed()){
                    googleClassCode1=ele.getText().trim();
                }
            }
            System.out.println("googleClassCode1:" + googleClassCode1);
            Thread.sleep(2000);

            driver.get(Config.baseURL);//Will be logged in as an instructor2 in edulastic
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            manageClass.syncWithGoogleClass.get(0).click();//Click on sync with google class
            manageClass.textBox_googleClassCode.sendKeys(googleClassCode1);//Enter google class3 code having 1 students (st2)
            Thread.sleep(1000);
            manageClass.sync.click();//Click on sync button
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.doneAfterSync, 60);

            manageClass.doneAfterSync.click();//Click on done
            Thread.sleep(1000);

            //Verify Google Class code should be displayed in Manage Class for respective class after sync is completed (for ins2)
            CustomAssert.assertTrue(manageClass.classDetails.get(3).getText().contains("Google Classroom "+googleClassCode1),"Verify google class code after sync in manage class for ins2","Google class code is displayed as expected","sync is not done as google class code is not displayed");

            new Navigator().logout();//log out ins2

            //Verify google sync when one ins uses same class code in different class
            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as an instructor1
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            manageClass.syncWithGoogleClass.get(0).click();//Click on sync with google class
            manageClass.textBox_googleClassCode.sendKeys(googleClassCode1);//Enter google class1 code having 2 students (st1 and st2) for class2 under ins1
            Thread.sleep(1000);
            manageClass.sync.click();//Click on sync button
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.doneAfterSync,60);
            manageClass.doneAfterSync.click();//Click on done
            Thread.sleep(1000);

            //Verify Google Class code should be displayed in Manage Class for respective class after sync is completed (for ins2)
            CustomAssert.assertTrue(manageClass.classDetails.get(3).getText().contains("Google Classroom "+googleClassCode1),"Verify google class code after sync in manage class for ins1 class2","Google class code is displayed as expected","Sync is not done as google class code is not displayed");

            manageClass.listVisitClass.get(1).click();//select class1
            new Navigator().navigateTo("manageclass");//Navigate to manage class

            for(WebElement sync : manageClass.syncWithGoogleClass)
            {
                if(sync.isDisplayed())
                {
                    sync.click();//Click on sync with google class
                    break;

                }
            }
            manageClass.textBox_googleClassCode.sendKeys(googleClassCode1);//Enter google class1 code having 2 students (st1 and st2) for class1 under ins1
            Thread.sleep(1000);
            manageClass.sync.click();//Click on sync button
            Thread.sleep(3000);
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.doneAfterSync,60);
            manageClass.doneAfterSync.click();//Click on done
            Thread.sleep(1000);

            //Verify Google Class code should be displayed in Manage Class for respective class after sync is completed (for ins2)
            CustomAssert.assertTrue(manageClass.classDetails.get(7).getText().contains("Google Classroom "+googleClassCode1),"Verify google class code after sync in manage class for ins1 class1","Google class code is displayed as expected","Sync is not done as google class code is not displayed");
            new Navigator().logout();//Log out

            //Check google sync after disabling and resetting the class code



            new Login().loginAsInstructor(appendChar2,dataIndex);//Login as an instructor2
            new Navigator().navigateTo("manageclass");//Navigate to manage class*/

            driver.get(googleClassroomUrl);
            //new Login().directLoginInGoogleClassRoom(dataIndex, googleClassroomTeacher, googleClassroomPass);//Teacher login in google classroom
           // List<WebElement> classes = googleclassRoom.className_onClassCard;

            Thread.sleep(4000);
            googleclassRoom.navigator.click();
            Thread.sleep(3000);
            googleclassRoom.classes.click();

            for (WebElement c:classes)
            {
                if(c.getText().equals(googleClass1))
                {
                    c.click();//Select class1
                    break;
                }
            }
            WebDriverUtil.waitForAjax(driver,60);
            new Click().clickIfDisplayed(dataIndex,googleclassRoom.studentTab);
            Thread.sleep(5000);
           // WebDriverUtil.waitUntilTextToBePresentInElement(driver.findElement(By.xpath("//h2[text()='Class code']")),"Class code",60);
            driver.findElement(By.xpath("//h2[text()='Class code']/..//div[@aria-expanded='false']")).click();
            new Actions(driver).sendKeys(Keys.ARROW_DOWN).build().perform();
            Thread.sleep(2000);
            new Actions(driver).sendKeys(Keys.ENTER).build().perform(); //click on the reset

            Thread.sleep(5000);
            String googleClassCode1AfterReset = googleclassRoom.classCode.get(0).getText();
            Thread.sleep(1000);
            System.out.println("googleClassCode1AfterReset "+googleClassCode1AfterReset );



            driver.get(Config.baseURL);
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            manageClass.syncWithGoogleClass.get(0).click();//Click on sync with google class
            Thread.sleep(2000);
            manageClass.changeClassRoom.click();//Click on change class room
            Thread.sleep(2000);
            manageClass.textBox_googleClassCode.click();
            new KeysSend().sendKeyBoardKeys("$");
            Thread.sleep(2000);
            manageClass.textBox_googleClassCode.sendKeys(googleClassCode1AfterReset);//Enter class code after resetting
            Thread.sleep(1000);
            manageClass.sync.click();//Click on sync button
            manageClass.yesWhileSync.click();//Click on yes
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.doneAfterSync,60);
            manageClass.doneAfterSync.click();//Click on done
            Thread.sleep(1000);

            //Resetted code should be displayed
            CustomAssert.assertTrue(manageClass.classDetails.get(3).getText().contains("Google Classroom "+googleClassCode1AfterReset),"Verify google class code after sync in manage class for ins2","Google resetted class code is displayed as expected","sync is not done as google resetted class code is not displayed");

            manageClass.syncWithGoogleClass.get(0).click();//Click on sync with google class
            Thread.sleep(2000);
            manageClass.changeClassRoom.click();//Click on change class room
            Thread.sleep(2000);
            manageClass.textBox_googleClassCode.click();
            new KeysSend().sendKeyBoardKeys("$");
            manageClass.textBox_googleClassCode.sendKeys(googleClassCode1);//Enter class code before reset
            Thread.sleep(1000);
            manageClass.sync.click();//Click on sync button
            manageClass.yesWhileSync.click();//Click on yes button

            WebDriverUtil.waitTillVisibilityOfElement(manageClass.sync,30);

            //Error message should appear
            CustomAssert.assertTrue(manageClass.syncPopUp.getText().contains("Enter a valid Google Classroom Code"),"Verify the error message on sync pop up","Error message is displayed as expected","Error message is not displayed");

            manageClass.cancelSync.click();//Click on cancel button

            //Verify sync after disabling the class code
            driver.get(googleClassroomUrl);
            googleclassRoom.navigator.click();
            Thread.sleep(3000);
            googleclassRoom.classes.click();

            for (WebElement c:classes)
            {
                if(c.getText().equals(googleClass1))
                {
                    c.click();//Select class1
                    break;
                }
            }
             WebDriverUtil.waitForAjax(driver,60);
            new Click().clickIfDisplayed(dataIndex,googleclassRoom.studentTab);
            Thread.sleep(5000);

            //WebDriverUtil.waitUntilTextToBePresentInElement(driver.findElement(By.xpath("//h2[text()='Class code']")),"CLASS CODE",60);
            driver.findElement(By.xpath("//h2[text()='Class code']/..//div[@aria-expanded='false']")).click();
            new Actions(driver).sendKeys(Keys.ARROW_DOWN).build().perform();
            Thread.sleep(2000);
            new Actions(driver).sendKeys(Keys.ARROW_DOWN).build().perform();
            Thread.sleep(2000);
            new Actions(driver).sendKeys(Keys.ENTER).build().perform(); //click on the disable
            Thread.sleep(5000);

            //Enter the the disabled class code
            driver.get(Config.baseURL);
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            manageClass.syncWithGoogleClass.get(0).click();//Click on sync with google class
            Thread.sleep(2000);
            manageClass.changeClassRoom.click();//Click on change class room
            Thread.sleep(2000);
            manageClass.textBox_googleClassCode.click();
            new KeysSend().sendKeyBoardKeys("$");
            manageClass.textBox_googleClassCode.sendKeys(googleClassCode1AfterReset);//Enter disabled class code
            Thread.sleep(1000);
            manageClass.sync.click();//Click on sync button

            WebDriverUtil.waitTillVisibilityOfElement(manageClass.sync,30);
            //Error message should appear while trying to sync with disabled class code
            CustomAssert.assertTrue(manageClass.syncPopUp.getText().contains("Enter a valid Google Classroom Code"),"Verify the error message on sync pop up","Error message is displayed as expected","Error message is not displayed");

            //Enable the disabled class code
            driver.get(googleClassroomUrl);

            for (WebElement c:classes)
            {
                if(c.getText().equals(googleClass1))
                {
                   WebDriverUtil.clickOnElementUsingJavascript(c);//Select class1
                    break;
                }
            }
            WebDriverUtil.waitForAjax(driver,60);
            new Click().clickIfDisplayed(dataIndex,googleclassRoom.studentTab);
            Thread.sleep(5000);
            //WebDriverUtil.waitUntilTextToBePresentInElement(driver.findElement(By.xpath("//h2[text()='Class code']")),"CLASS CODE",60);
            driver.findElement(By.xpath("//h2[text()='Class code']/..//div[@aria-expanded='false']")).click();
            new Actions(driver).sendKeys(Keys.ARROW_DOWN).build().perform();
            Thread.sleep(2000);
            new Actions(driver).sendKeys(Keys.ENTER).build().perform(); //click on the enable

            Thread.sleep(3000);
            for (WebElement ele:googleclassRoom.classCode){
                if (ele.isDisplayed()){
                    googleClassCode1=ele.getText().trim();
                }
            }
            System.out.println("googleClassCode1" + googleClassCode1);
            Thread.sleep(2000);
            CustomAssert.assertFalse(googleClassCode1.equals("Disabled"),"Verify disable class code","Able to click on enable","Not able to click on enable");


        }catch (Exception e)
        {
            Assert.fail("Exception in 'googleSyncAfterResetAndDisable' in 'GoogleSync' method", e);

        }
    }




}
