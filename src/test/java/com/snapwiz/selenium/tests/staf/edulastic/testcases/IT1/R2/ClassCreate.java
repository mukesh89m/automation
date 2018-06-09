package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.R2;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.StudentSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.sql.ResultSet;

/*
 * Created by root on 12/8/14.
 */
public class ClassCreate extends Driver {

    @Test(priority = 1,enabled = true)
    public void createClassUI()
    {
        try
        {
            String appendChar = "a";
            String appendCharacterBuild=System.getProperty("UCHAR");
            new SignUp().teacher(appendChar, 0);
           String zipcode= new School().createWithOnlyName(appendChar,52);
            if (appendCharacterBuild!=null)
                appendCharacterBuild=appendChar+appendCharacterBuild;
            else
            {
                appendCharacterBuild=appendChar;
            }
            String schoolName = "createClassUIschool"+appendChar;

            //Validating if the page lands on Create your class page after creating a school
            if(!(new TextFetch().textfetchbycssselector("div[class='form-group as-add-new-class-row m-b']").contains("Name of the class")))
                Assert.fail("Teacher not directed to 'Create your class' page after creating school");

            new Navigator().createClassToFindSchoolTeacher();
            Thread.sleep(3000);
            //Row no 47 2.The newly created school should be added by default in school edit box.
            if(!driver.findElement(By.className("item-text")).getText().contains(schoolName))
                Assert.fail("The newly created school is not added by default in school edit box when teacher created presses back button in 'Create your class' page after creating a new school from 'create school page''");

            //Row no 48 10.Remove the school and search for the newly added school.
            new School().enterAndSelectSchool(zipcode,52,true);
            new Navigator().findSchoolToCreateClassTeacher();
            //Row no 52 3.Click on "Finish" button on create class page without entering anything. "1.The following validation messages should be dispalyed
            //-Please provide a valid class name.
            //-Grade is not selected
            //-Subject Area is not selected
            //-Subject is not selected"
            String defaultClassName = new Exception().getStackTrace()[0].getMethodName()+"ins"+appendCharacterBuild+"'"+"s class";
            Assert.assertEquals(driver.findElement(By.id("class-name")).getAttribute("placeholder"), ""+defaultClassName,"Default class name is not displayed");
            driver.findElement(By.id("class-name")).clear();
            new Navigator().finishButtonOfCreateClassPage(); //Click on finish button on create class page without entering any data
            if(!new TextFetch().textfetchbycssselector("div[class='as-errorMsg grade-message']").equalsIgnoreCase("Grade is not selected"))
                Assert.fail("Error Message to provide a Grade not coming when user clicks on finish button without selecting any grade in create class page");

            if(!new TextFetch().textfetchbycssselector("div[class='as-errorMsg subject-area-message']").equalsIgnoreCase("Subject Area is not selected"))
                Assert.fail("Error Message to provide a valid subject are not coming when user clicks on finish button without selecting any value in subject area field in create class page");

            if(!new TextFetch().textfetchbycssselector("div[class='as-errorMsg subject-message']").equalsIgnoreCase("Subject is not selected"))
                Assert.fail("Error Message to provide a valid subject not coming when user clicks on finish button without selecting any value in subject field in create class page");

            //Row no 53 4.Enter special characters for the class name and change the focus out of it. 1.The validation message "Please provide a valid class name" should not be hidden.
            new TextSend().textsendbyid("!@#$%^&*()","class-name");
            new Click().clickByid("class-name"); //changing the focus by clicking on description text area

             boolean errorFoundInClassName = new BooleanValue().presenceOfElement(52,By.cssSelector("div[class='as-errorMsg class-name-message']"));
             Assert.assertEquals(errorFoundInClassName,false,"Error message is appearing in class name field");

            //Row no 55 6.Enter any non existing class name and change the focus out of it. 1.The validation message "Please provide a valid class name" should get hidden.
            new Click().clickByid("class-name"); //Bringing back the focus to class name field
            new TextSend().textsendbyid("createClassUIclass","class-name");
            new Click().clickBycssselector("div[class='as-search-blue-btn btn btn-blue as-add-save-btn']"); //changing the focus by clicking on description text area
            if(!(new Size().getSizeofElement("cssselector","div[class='as-errorMsg class-name-message']") == 0))
                       Assert.fail("The error message shown for not entering proper value of class name while creating a class is visible even if user enters valid name");

            //Row no 56 7.Select any grade from the grade drop down. 1.The validation message "Grade is not selected" should get hidden.
            new SelectDropDown().selectByText(driver.findElement(By.cssSelector("select[class='form-control as-add-grade-dropDown']")),"Grade 1");
            if(new Size().getSizeofElement("cssselector","div[class='as-errorMsg grade-message']") != 0)
                Assert.fail("Even after selecting grade using grade drop-down in create class page the error message to select grade is coming");

            //Row no 57 8.Select any subject area from the subject area drop down. 1.The validation message "Subject Area is not selected" should get hidden.
            new SelectDropDown().selectByText(driver.findElement(By.cssSelector("select[class='form-control as-add-subjectArea-dropDown']")),"Mathematics");
            if(new Size().getSizeofElement("cssselector","div[class='as-errorMsg subject-area-message']") != 0)
                Assert.fail("Even after selecting subject area using subject area drop-down in create class page the error message to select subject area is coming");

            //Row no 58 9.Select any subject  from the subject drop down. 1.The validation message "Subject is not selected" should get hidden.
            new SelectDropDown().selectByText(driver.findElement(By.cssSelector("select[class='form-control as-add-subject-dropDown']")),"Math - Common Core");
            if(new Size().getSizeofElement("cssselector","div[class='as-errorMsg subject-message']") != 0)
                Assert.fail("Even after selecting subject using subject drop-down in create class page the error message to select subject is coming");

            //Row No 59 10.Verify the subject drop down is dependent on subject area drop down. 1.The subject drop down should be dependent on subject area drop down.
            new SelectDropDown().selectByText(driver.findElement(By.cssSelector("select[class='form-control as-add-subject-dropDown']")),"Select Subject");//Resetting the value of subject area drowdown
            if((!(new SelectDropDown().getSelectedValue(driver.findElement(By.cssSelector("select[class='form-control as-add-subject-dropDown']")))).equalsIgnoreCase("Select Subject")))
                Assert.fail("After resetting subject area drop down in create class page the value of subject dropdown is not getting re-set to default value");
            //Row no 60 2.The subject drop dwon values should be populated based on the selection of subject area drop down.
            new SelectDropDown().selectByText(driver.findElement(By.cssSelector("select[class='form-control as-add-subjectArea-dropDown']")),"ELA");
            new SelectDropDown().selectByText(driver.findElement(By.cssSelector("select[class='form-control as-add-subject-dropDown']")),"ELA - Common Core");
            driver.get(Config.baseURL);
            if(!new TextFetch().textfetchbycssselector("div[class='form-group as-add-new-class-row m-b']").contains("Name of the class"))
                Assert.fail("The instructor is not re-directed to create class page after re-directing in to the system after logging out from create class page");
            //Row no 65 17.Log in with the previously registered credentials. 1.The user should be redirected to  "Create Your class" page.
            driver.quit();
            reInitDriver();
            new Login().loginAsInstructor(appendChar, 0);
            if(!new TextFetch().textfetchbycssselector("div[class='form-group as-add-new-class-row m-b']").contains("Name of the class"))
                Assert.fail("The instructor is not re-directed to create class page after re-logging in to the system after logging out from create class page");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createClassUI in class ClassCreate",e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void createClass()
    {
        try {
            String appendCharacterBuild=System.getProperty("UCHAR");
            String appendChar = "a";
            if (appendCharacterBuild!=null)
                appendCharacterBuild=appendChar+appendCharacterBuild;
            else
            {
                appendCharacterBuild=appendChar;
            }
            String  className = new Exception().getStackTrace()[0].getMethodName()+"class"+appendCharacterBuild;
            String  grade = ReadTestData.readDataByTagName("", "grade", Integer.toString(66));
            String  subjectArea = ReadTestData.readDataByTagName("", "subjectArea", Integer.toString(66));
            String  subject = ReadTestData.readDataByTagName("", "subject", Integer.toString(66));
            System.out.println("class Name : " + className);

            new SignUp().teacher(appendChar, 66);
            new School().createWithOnlyName(appendChar,66);

            //Row no 66 18.Enter all the values or only required values and click on "Save" button. 1.The user should be redirected to "Manage class" page.
            new TextSend().textsendbyid(className,"class-name");//Enter Class name
            new SelectDropDown().selectByText(driver.findElement(By.cssSelector("select[class='form-control as-add-grade-dropDown']")),grade);//Select a grade
            new SelectDropDown().selectByText(driver.findElement(By.cssSelector("select[class='form-control as-add-subjectArea-dropDown']")),subjectArea);//Select subject area
            new SelectDropDown().selectByText(driver.findElement(By.cssSelector("select[class='form-control as-add-subject-dropDown']")),subject);//select subject
            new Click().clickBycssselector("div[class='as-search-blue-btn btn btn-blue as-add-save-btn']"); //Finish button of step 3
            Thread.sleep(3000);
            new Click().clickBycssselector("a[class='as-search-blue-btn as-search-next btn btn-blue js-manageclass']");//Click on Manage class
            if(!(new TextFetch().textfetchbycssselector("span[class='as-manage-class-subject as-manage-class-title ellipsis']").contains(className)))
                Assert.fail("Instructor not landed to manage class page after creating a new class");

            //Row no 67 2.The class should be created for the selected subject and subject area combination and saved in DB.
            ResultSet rst =  DBConnect.st.executeQuery("select name as classname from t_class_section where name like '"+className+"';");
            boolean classFound = false;
            while(rst.next())
            {
                if(rst.getString("classname").equals(className))
                    classFound = true;
                if(classFound == true) break;

            }
            if(classFound == false) Assert.fail("The new class created by teacher in not present in the database inside t_class_section table");

            //manage class page UI verification
            //Row no 68 1.Verify the manage class page elements.
            if(!(new TextFetch().textfetchbyid("as-manage-class-header-wrapper")).contains(className)) Assert.fail("Class name doesn't exists on manage class page of instructor");
            if(!(new TextFetch().textfetchbyid("as-manage-class-header-wrapper")).contains("Visit Class")) Assert.fail("View Class button doesn't exists on manage class page of instructor");

            if(!(new TextFetch().textfetchbyclass("as-manageClass-printed")).contains("Grade")) Assert.fail("Grade Label doesn't exists on manage class page of instructor");
            if(!(new TextFetch().textfetchbyclass("as-manageClass-printed")).contains(grade)) Assert.fail("Grade name doesn't exists on manage class page of instructor");

            if(!(new TextFetch().textfetchbyclass("as-manageClass-printed")).contains("Subject Area")) Assert.fail("Subject Area Label doesn't exists on manage class page of instructor");
            if(!(new TextFetch().textfetchbyclass("as-manageClass-printed")).contains(subjectArea)) Assert.fail("Subject Area doesn't exists on manage class page of instructor");

            if(!(new TextFetch().textfetchbyclass("as-manageClass-printed")).contains("Subject")) Assert.fail("Subject Label doesn't exists on manage class page of instructor");
            if(!(new TextFetch().textfetchbyclass("as-manageClass-printed")).contains(subject)) Assert.fail("Subject doesn't exists on manage class page of instructor");

            if(!(new TextFetch().textfetchbyclass("as-manageClass-descriptionWrapper")).contains("Description")) Assert.fail("Description Label doesn't exists on manage class page of instructor");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createClass in class ClassCreate",e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void addStudentsViaEmail()
    {
        try
        {
            String appendCharacterBuild=System.getProperty("UCHAR");

            String appendChar = "a";
            if (appendCharacterBuild!=null) {
                appendCharacterBuild = appendChar + appendCharacterBuild;
            }
            else
            {
                appendCharacterBuild=appendChar;
            }
            String  className = "addStudentsViaEmailclass"+appendCharacterBuild;
            String instructorEmail = "addStudentsViaEmailins"+appendCharacterBuild+"@snapwiz.com";
            new SignUp().teacher(appendChar, 0);
            new School().createWithOnlyName(appendChar,72);
            String classCode = new Classes().createClass(appendChar,72);
            String accessURL = Config.baseURL+"regcd/"+classCode;
            new Navigator().navigateTo("manageclass");//navigate to manage class
            driver.findElement(By.xpath("//span[@title='"+className+"']")).click();

            //Row no 72 5.Click on "Add students via e-mail" link. 1.A form should open up to invite the students.
            new Click().clickbylinkText("Add students via e-mail"); //clicking on add students via email link
            //Row no 74 "2.The form should have the following elements

            //-""Invite students for your class"" text
            //    -""Message""text
            //    -Email editbox(Default-Enter Student Email..)
            //--""Cancel"" link
            //   --""+Invite"" button"
            if(!(new TextFetch().textfetchbyclass("as-new-students-text")).equalsIgnoreCase("Invite students for your class"))
                Assert.fail("The text 'Invite students for your class' not present on header of box 'Add students via email'");
            if(!(driver.findElement(By.cssSelector("textarea[class='as-add-students invite-students-input']")).getAttribute("placeholder").equalsIgnoreCase("Enter Student email...")))
                Assert.fail("Default text 'Enter Student email...' not present inside textbox to enter email id of students in 'Add students via email' box");
            new Click().clickBycssselector("input[class='as-blue-btn as-invite-studentsBtn']"); //clicking on invite button
            new Click().clickbylinkText("Cancel"); //clicking on cancel button
            new Click().clickbylinkText("Add students via e-mail"); //clicking on add students via email link
            //Row no 75 3.The message should contain the invitation message.

            //Row no 77 6.Click on "+Invite" button without entering any student email. 1.Some validation message should be dispalyed.
            new Click().clickBycssselector("input[class='as-blue-btn as-invite-studentsBtn']"); //clicking on invite button
            new Click().clickbylinkText("Cancel"); //clicking on cancel button
            new Click().clickbylinkText("Add students via e-mail"); //clicking on add students via email link
            new TextSend().textsendbycssSelector(Config.studentEmail,"textarea[class='as-add-students invite-students-input']");
            new Click().clickBycssselector("input[class='as-blue-btn as-invite-studentsBtn']"); //clicking on invite button

            new Click().clickbylinkText("Add students via e-mail"); //clicking on add students via email link
            //Row no 78 7.Enter student emails separated by comma and click on "+invite" button.
            new TextSend().textsendbycssSelector(Config.studentEmail+", sumit@gmail.com","textarea[class='as-add-students invite-students-input']");
            new Click().clickBycssselector("input[class='as-blue-btn as-invite-studentsBtn']"); //clicking on invite button

            int size = new Size().getSizeofElement("class", "as-manage-add-new-students-wrapper");
            if(size != 0)
            {
                Assert.fail("Pop-up does not closes when invite is multiple students through email.");
            }

            //Row no 89 1.Click on the link provided in the invitaion mail. 1.The student will be redirected to the student registraion page with the class code prepopulated.
            new Gmail().login();
            new Gmail().openEmail(instructorEmail,accessURL,0);
            driver.quit();
            reInitDriver();
            driver.get(accessURL);
            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver,StudentSignUpPage.class);

            Thread.sleep(3000);
            //Row no 90 2.The class code should not be editable.
            if(!(driver.findElement(By.id("class-code")).getAttribute("disabled").equals("true")))
                Assert.fail("The class code is editable while student is trying to sign up through email");

            driver.findElement(By.id("first-name")).clear();
            driver.findElement(By.id("first-name")).sendKeys("firstname");

            driver.findElement(By.id("user-email")).clear();
            driver.findElement(By.id("user-email")).sendKeys("email@email.com");

            driver.findElement(By.id("user-password")).clear();
            driver.findElement(By.id("user-password")).sendKeys("snapwiz");

            driver.findElement(By.id("retype-password")).clear();
            driver.findElement(By.id("retype-password")).sendKeys("snapwiz");

            //Verifying sign up button
            Assert.assertEquals(studentSignUpPage.getButton_signUp().getText(),"Sign Up","Sign up button is not displayed");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase addStudentsViaEmail in class ClassCreate",e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void studentSignUpValidationMessages()
    {
        try {
            String appendCharacterBuild=System.getProperty("UCHAR");
            String appendChar = "a";
            if (appendCharacterBuild!=null)
                appendCharacterBuild=appendChar+appendCharacterBuild;
            else
            {
                appendCharacterBuild=appendChar;
            }
            new SignUp().teacher(appendChar, 0);
            new School().createWithOnlyName(appendChar,93);
            String classCode = new Classes().createClass(appendChar,93);
            new Navigator().navigateTo("manageclass");
            String accessURL = Config.baseURL+"regcd/"+classCode;
            String instructorEmail = "studentSignUpValidationMessagesins"+appendCharacterBuild+"@snapwiz.com";
            new SignUp().addAndInviteStudentViaEmail(instructorEmail,accessURL,0);
            //Row no 93 4.Click on "Sign Up" button without entering anything in any of th fields(Except class code field). The validation messages should appear

            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver,StudentSignUpPage.class);

            studentSignUpPage.getButton_signUp().click(); //clicking on sign-up button present on student registration page
            if(!(new TextFetch().textfetchbycssselector("div[class='as-errorMsg first-name-message']")).equalsIgnoreCase("Please provide your full name."))
                Assert.fail("The validation message to provide first is not coming or not as expected when student presses sign up button without entering any value in first name field");

            if(!(new TextFetch().textfetchbycssselector("div[class='as-errorMsg email-message']")).equalsIgnoreCase("Please provide valid Username or Email id."))
                Assert.fail("The validation message to provide username or email is not coming or not as expected when student presses sign up button without entering any value in first name field");

            if(!(new TextFetch().textfetchbycssselector("div[class='as-errorMsg password-message']")).equalsIgnoreCase("Please provide a valid password."))
                Assert.fail("The validation message to provide a valid password is not coming or not as expected when student presses sign up button without entering any value in first name field");

            //Row no 94 5.Enter some value in the first name field and change focus. 1.The validation message should disappear.
            new TextSend().textsendbyid("studentSignUpValidationMessagesname"+appendCharacterBuild,"first-name");
            new Click().clickByid("user-email"); //changing focus to email field
            if(new Size().getSizeofElement("cssselector","div[class='as-errorMsg first-name-message']") !=0)
                Assert.fail("The validation error message for providing an invalid first name is coming even after student enters a valid first name while registering");

            new TextSend().textsendbyid("usernameasid","user-email"); //enter invalid email address
            new Click().clickByid("user-password"); //changing focus to password field
            if(new Size().getSizeofElement("cssselector","div[class='as-errorMsg email-message']") !=0)
                Assert.fail("The validation error message for email is coming even after student enters a valid name as id while registering");


            new TextSend().textsendbyid("studentSignUpValidationMessagesins"+appendCharacterBuild+"@snapwiz.com","user-email");
            new Click().clickByid("user-password"); //changing focus to password field
            if(new Size().getSizeofElement("cssselector","div[class='as-errorMsg email-message']") !=0)
                Assert.fail("The validation error message for email is coming even after student enters a valid email while registering");

            new TextSend().textsendbyid("snapwiz","user-password");
            new Click().clickByid("user-email"); //changing focus to email field
            if(new Size().getSizeofElement("cssselector","div[class='as-errorMsg password-message']") !=0)
                Assert.fail("The validation error message for password is coming even after student enters a valid password while registering");

            studentSignUpPage.getButton_signUp().click(); //clicking on sign-up button present on student registration page
            Thread.sleep(2000);
            if(!(new TextFetch().textfetchbycssselector("div[class='as-errorMsg email-message as-errorMsg-right']")).equalsIgnoreCase("You are a registered user. Please sign in in to your account before joining the new class."))
                Assert.fail("The validation message or existing email or password is not coming or not as expected when student tries to register using an already existing email");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase studentSignUpValidationMessages in class ClassCreate",e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void registerAlreadyRegisteredStudentViaEmail()
    {
        try {
            String appendCharacterBuild = System.getProperty("UCHAR");
            driver.get(Config.baseURL);
            String appendChar = "a";
            if (appendCharacterBuild != null)
                appendCharacterBuild = appendChar + appendCharacterBuild;
            else {
                appendCharacterBuild = appendChar;
            }
            String instructorEmail = "registerAlreadyRegisteredStudentViaEmailins" + appendCharacterBuild + "@snapwiz.com";
            new SignUp().teacher(appendChar, 0);
            new School().createWithOnlyName(appendChar,98);
            String classCode = new Classes().createClass(appendChar,98);
            String accessURL = Config.baseURL+"regcd/"+classCode;
            new Navigator().navigateTo("manageclass");
            new SignUp().studentRegisterViaEmail(appendChar,instructorEmail,accessURL,0);
            new Navigator().logout();
            driver.get(accessURL);

            driver.findElement(By.id("first-name")).clear();
            driver.findElement(By.id("first-name")).sendKeys("registerAlreadyRegisteredStudentViaEmailins" + appendCharacterBuild);
            driver.findElement(By.id("user-email")).clear();
            driver.findElement(By.id("user-email")).sendKeys("registerAlreadyRegisteredStudentViaEmailins" + appendCharacterBuild + "@snapwiz.com");
            driver.findElement(By.id("user-password")).clear();
            driver.findElement(By.id("user-password")).sendKeys("snapwiz");

            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver, StudentSignUpPage.class);

            studentSignUpPage.getButton_signUp().click();//clicking on submit button present on student sign up page
            if (!driver.findElement(By.cssSelector("div[class='as-errorMsg email-message as-errorMsg-right']")).getText().replaceAll("[\\t\\n\\r]", " ").contains("You are a registered user. Please sign in in to your account before joining the new class.")) {
                Assert.fail("Error Message saying username or email already exists not coming when user clicks on submit button after entering details of an already registered user");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase registerAlreadyRegisteredStudentViaEmail in class ClassCreate",e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void alreadyRegisteredStudentViaEmailNotifications()
    {
        try
        {
            String appendCharacterBuild=System.getProperty("UCHAR");
            driver.get(Config.baseURL);
            String appendChar = "a";
            if (appendCharacterBuild!=null) {
                appendCharacterBuild = appendChar + appendCharacterBuild;
            }
            else
            {
                appendCharacterBuild=appendChar;
            }
            String instructorEmail = "registerAlreadyRegisteredStudentViaEmailins"+appendCharacterBuild+"@snapwiz.com";
            new SignUp().teacher(appendChar, 0);
            new School().createWithOnlyName(appendChar,96);
            String classCode = new Classes().createClass(appendChar,96);
            String accessURL = Config.baseURL+"regcd/"+classCode;
            new Navigator().navigateTo("manageclass");
            new SignUp().studentRegisterViaEmail(appendChar,instructorEmail,accessURL,0);
            new Navigator().logout();
            driver.get(accessURL);

            StudentSignUpPage studentSignUpPage= PageFactory.initElements(driver,StudentSignUpPage.class);

            //Row no 96 7.Enter some existing email id in mail id field and change the focus outside of it. 1."Please provide a valid Email id." validation message should get hidden.//Validation message should not get hidden as discussed with samarth
            studentSignUpPage.getButton_signUp().click();//clicking on submit button present on student sign up page
            driver.findElement(By.id("user-email")).sendKeys("alreadyRegisteredStudentViaEmailNotificationsins"+appendCharacterBuild+"@snapwiz.com");
            new Click().clickByid("user-password"); //changing focus to password field
            if((new Size().getSizeofElement("cssselector","div[class='as-errorMsg email-message as-errorMsg-right']") == 0))
                Assert.fail("The error message shown for not entering proper value of username or email while student register via email if user enters an existing value of email");
            //Row no 97 8.Enter the same password for the existing email id and change the focus out side of it. 1."Please provide a valid password" validation message should disappear.
            new TextSend().textsendbyid("snapwiz","user-password"); //password
            new Click().clickByid("first-name"); //changing focus
            if((new Size().getSizeofElement("cssselector","div[class='as-errorMsg password-message']") != 0))
                Assert.fail("The error message shown for not entering proper value of username or email while student register via email is visible even if user enters an existing value of email");

            new TextSend().textsendbyid("alreadyRegisteredStudentViaEmailNotificationsinsnew"+appendCharacterBuild,"first-name");
            studentSignUpPage.getButton_signUp().click();//clicking on submit button present on student sign up page
            if(!new TextFetch().textfetchbycssselector("div[class='as-errorMsg email-message as-errorMsg-right']").contains("You are a registered user. Please sign in in to your account before joining the new class."))
                Assert.fail("Error Message saying username or email already exists not coming when user clicks on submit button after entering details of an already registered user via email");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase alreadyRegisteredStudentViaEmailNotifications in class ClassCreate",e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void studentListInManageClassPage()
    {
        try
        {
            String appendCharacterBuild=System.getProperty("UCHAR");
            String appendChar = "a";
            if (appendCharacterBuild!=null)
                appendCharacterBuild=appendChar+appendCharacterBuild;
            else
            {
                appendCharacterBuild=appendChar;
            }
            String firstName = "studentListInManageClassPagest"+appendCharacterBuild;
            String instructorEmail = "registerAlreadyRegisteredStudentViaEmailins"+appendCharacterBuild+"@snapwiz.com";
            driver.get(Config.baseURL);
            new SignUp().teacher(appendChar, 0);

            new School().createWithOnlyName(appendChar,79);
            String classCode = new Classes().createClass(appendChar,79);
            String accessURL = Config.baseURL+"regcd/"+classCode;
            new Navigator().navigateTo("manageclass");
            new SignUp().studentRegisterViaEmail(appendChar,instructorEmail,accessURL,0);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar, 0);
            new Navigator().navigateTo("manageclass");
            if(!(new TextFetch().textFetchByXpath("//table/tbody/tr/td[2]")).equals(firstName))
                Assert.fail("The Add students via e-mail block not displaying the username of the students registered in the class section.");
            if(!(new TextFetch().textfetchbycssselector("span[class='as-manageClass-studentCount ']").equals("1")))
                Assert.fail("Student count on add students via email block not displayed as 1, one student has registered in the class");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase studentListInManageClassPage in class ClassCreate",e);
        }
    }
}
