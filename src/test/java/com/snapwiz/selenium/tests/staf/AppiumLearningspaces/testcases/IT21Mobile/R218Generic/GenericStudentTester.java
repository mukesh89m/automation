package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.testcases.IT21Mobile.R218Generic;


import com.google.gson.JsonObject;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Config;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelperAppium.*;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pageFactoryAppium.Dashboard_appiumPF;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.UIElement;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelperAppium.Tap;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.jopendocument.model.table.TableOperation;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
//import org.sikuli.api.Screen;
//import org.sikuli.api.robot.desktop.DesktopScreen;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Dharaneesha on 04/08/15.
 */
public class GenericStudentTester extends Driver  {
    //Dashboard_appiumPF dashBoard = PageFactory.initElements(appiumDriver, Dashboard_appiumPF.class);
    @Test(priority = 1)
    public void visibilityOfGradedAssignments1(){
        try{
            int dataIndex = 13;

            //Row NO - 10 :  Login as Student.
            //Expected 1-  The user as a student role should be loged in to the respective account and land to the Dashboard page.
            new Dashboard_appium().navigateToStudent1Dashboard();//Login as a  Student1
            dashBoard.icon_close.click();
            Thread.sleep(9000);

            //System.out.println("Page Source : " + appiumDriver.getPageSource());
            appiumDriver.tap(1, 6, 8, 1);
            Thread.sleep(3000);
            //appiumDriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[5]")).click();
            appiumDriver.findElement(By.xpath("//UIALink[5]")).click();

            Thread.sleep(3000);
            List<WebElement> wle = appiumDriver.findElements(By.className("UIAStaticText"));

            for(int a=0;a<wle.size();a++){
                System.out.println("Ele :" + wle.get(a).getText());
            }
            wle.get(4).click();
            Thread.sleep(5000);

            appiumDriver.tap(1, 6, 8, 1);

            System.out.println("Page Source : " + appiumDriver.getPageSource());


            appiumDriver.findElementByXPath("//UIALink[@name = 'Course Stream']").click();


            new Navigator_appium().navigateTo("");


            //appiumDriver.findElement(By.name("All Assignments")).click();
            //appiumDriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIALink[5]")).click();



            //appiumDriver.findElement(By.xpath(".//*[@name = 'iPad']")).click();



            /*appiumDriver.findElement(By.name("iPad")).click();



            String context = appiumDriver.getContext();
            System.out.println("context :" + context);
            appiumDriver.context(context);
            Set<String> contextNames = appiumDriver.getContextHandles();
            System.out.println("contextNames.size() :" + contextNames.size());
            for(String a:contextNames){
                appiumDriver.context(a);
                System.out.println("contextNames : " + contextNames);

            }





            try{
                appiumDriver.findElement(By.name("WileyPLUS Learning Space")).click();
            }catch(Exception e){
                e.printStackTrace();
                try {
                    appiumDriver.findElement(By.linkText("WileyPLUS Learning Space")).click();
                }catch(Exception f){
                    appiumDriver.findElement(By.xpath("//UIALink[3]")).click();
                    f.printStackTrace();
                }

            }






            appiumDriver.findElementByXPath("//div[@class='ls-site-nav-drop-down']//a").click();

            //List<WebElement> linkSizeList = appiumDriver.findElementsByTagName("UIALink");
            List<WebElement> linkSizeList = appiumDriver.findElements(By.tagName("UIALink"));

            System.out.println("linkSizeList size : " + linkSizeList.size());
            for(int a=0;a>linkSizeList.size();a++){
                linkSizeList.get(a).click();
                Thread.sleep(3000);
                System.out.println(a);
            }


            appiumDriver.findElementByCssSelector("a[href='javascript:;']").click();

            appiumDriver.findElementByCssSelector("div[class='ls-site-nav-drop-down']").click();

            appiumDriver.findElementByXPath("//div[class='ls-site-nav-drop-down']//a").click();



*/




            /*if(!dashBoard.icon_accountUser.isEnabled()){
                Assert.fail("The user as a student role is not loged in to the respective account and land to the Dashboard page.");
            }



            //Expected - 2 : " The tile should be visible in the top - right hand side of the dashboard with a header ""Graded Assignments"".
            dashBoard.icon_close.click();
            Assert.assertEquals(dashBoard.label_gradedAssignments.getText().trim(),"Graded Assignments","The tile should be visible in the top - right hand side of the dashboard with a header 'Graded Assignments'");



            *//*Precondition- Atlease one  assignment has been assigned to the class or student.
            Assignment associated with lesson "X"*//*
            //Row No- 13 :  Login as Student.
            //Expected -  The user as a student role should be loged in to the respective account and land to the Dashboard page.
            new Assignment().create(dataIndex);
            new DirectLogin().directLoginWithCreditial(Config.cmsUserName_Instructor, Config.cmsPassword, 0);//Login as an instructor
            new Assignment().assignToStudent(dataIndex);//Assign assignment to class Sectionnew Assignment().assignToStudent(dataIndex);//Assign assignment to class Section


            new Dashboard_appium().navigateToStudent1Dashboard();//Login as a Student1
            if(!dashBoard.icon_accountUser.isEnabled()){
                Assert.fail("The user as a student role should be loged in to the respective account and land to the Dashboard page.");
            }



            //Row No - 14 : " Verify the newly added tile ""Graded Assignments""
            //Expected -  " The tile should be visible in the top - right hand side of the dashboard with a header ""Graded Assignments""
            dashBoard.icon_close.click();//Close the help pop up
            Assert.assertEquals(dashBoard.label_gradedAssignments.getText().trim(),"Graded Assignments","The tile should be visible in the top - right hand side of the dashboard with a header 'Graded Assignments'");


            //Row No -15 : " The tile should have three sections with two blank section and third section having header as ""Upcoming""
            //Expected -" The tile should have three sections with two blank section and third section having header as ""Upcoming"".
            Assert.assertEquals(dashBoard.label_upcoming.getText(), "Upcoming", "There is no third section having header as \"\"Upcoming\"\".");



            //Expected -  " Section consists of upcoming assignments, followed by due date and time
            //Expected - " Assignments are sorted based on due date."
            Assert.assertEquals(dashBoard.link1_upcomingAssignment.getText(), "shor", "Section consists of upcoming assignments, followed by due date and time");
            //Assert.assertEquals(dashBoard.link2_upcomingAssignment.getText(), "shor", "Section consists of upcoming assignments, followed by due date and time");
            Assert.assertEquals(dashBoard.dueDate1.getText(), "Sep 28, 2015, 12:00 AM", "Section consists of upcoming assignments, followed by due date and time");
            //Assert.assertEquals(dashBoard.dueDate2.getText(), "Sep 28, 2015, 12:00 AM", "Section consists of upcoming assignments, followed by due date and time");

            //Row No - 18 :  Verify the overall score element.
            *//*" The first element should show the ""Overall score"" for a student.
            If the assignment is not graded the OVERALL score section should be blank."  *//*
            new DirectLogin().directLoginWithCreditial(Config.cmsUserName_Student1, Config.cmsPassword, 0);//Login as an Author
            new Assignment().submitAssignmentAsStudent(dataIndex);
            new DirectLogin().directLoginWithCreditial(Config.cmsUserName_Instructor, Config.cmsPassword, 0);//Login as an Author
            new Assignment().switchToAssignmentResponsePage(dataIndex);
            new Assignment().provideGradeToStudent(0,"0.6");

            //new Navigator().NavigateTo("Current Assignments");
            //new Click().clickByclassname("ls-grade-book-assessment");
            //new ResponsePage().openViewResponsePage();*/












        }catch(Exception e){
            Assert.fail("Exception in the test method 'visibilityOfGradedAssignments' in the class 'GenericStudentTester'",e);
        }
    }



    @Test(priority = 2)
    public void visibilityOfGradedAssignments(){
        try{
            String dataIndex = "13";
            ArrayList<String> instructorInfoList = new LoginUsingRandomUser_appium().loginAsRandomInstructor("" + dataIndex);
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent("" + dataIndex);
            //Row NO - 10 :  Login as StudentlogOutUser.
            //Expected 1-  The user as a student role should be loged in to the respective account and land to the Dashboard page.
            //new Dashboard_appium().navigateToStudent1Dashboard();//Login as a  Student1
            if(!dashBoard.icon_accountUser.isEnabled()){
                Assert.fail("The user as a student role is not loged in to the respective account and land to the Dashboard page.");
            }




            //Expected - 2 : " The tile should be visible in the top - right hand side of the dashboard with a header ""Graded Assignments"".
            dashBoard.icon_close.click();
            Assert.assertEquals(dashBoard.label_gradedAssignments.getText().trim(),"Graded Assignments","The tile should be visible in the top - right hand side of the dashboard with a header 'Graded Assignments'");



            /*Precondition- Atlease one  assignment has been assigned to the class or student.
            Assignment associated with lesson "X"*/
            //Row No- 13 :  Login as Student.
            //Expected -  The user as a student role should be loged in to the respective account and land to the Dashboard page.
            new Assignment().create(dataIndex);
            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), Config.cmsPassword, "0");//Login as an Author
            new Assignment().assignToStudent(dataIndex);//Assign assignment to class Sectionnew Assignment().assignToStudent(dataIndex);//Assign assignment to class Section


            //new Dashboard_appium().navigateToStudent1Dashboard();//Login as a Student1
            new LoginUsingRandomUser_appium().logOutUser();
            new LoginUsingRandomUser_appium().loginAsRandomStudent(""+dataIndex);//Login as a RandomStudent
            if(!dashBoard.icon_accountUser.isEnabled()){
                Assert.fail("The user as a student role should be loged in to the respective account and land to the Dashboard page.");
            }



            //Row No - 14 : " Verify the newly added tile ""Graded Assignments""
            //Expected -  " The tile should be visible in the top - right hand side of the dashboard with a header ""Graded Assignments""
            dashBoard.icon_close.click();//Close the help pop up
            Assert.assertEquals(dashBoard.label_gradedAssignments.getText().trim(),"Graded Assignments","The tile should be visible in the top - right hand side of the dashboard with a header 'Graded Assignments'");


            //Row No -15 : " The tile should have three sections with two blank section and third section having header as ""Upcoming""
            //Expected -" The tile should have three sections with two blank section and third section having header as ""Upcoming"".
            Assert.assertEquals(dashBoard.label_upcoming.getText(), "Upcoming", "There is no third section having header as \"\"Upcoming\"\".");



            //Expected -  " Section consists of upcoming assignments, followed by due date and time
            //Expected - " Assignments are sorted based on due date."
            Assert.assertEquals(dashBoard.link1_upcomingAssignment.getText(), "shor", "Section consists of upcoming assignments, followed by due date and time");
            //Assert.assertEquals(dashBoard.link2_upcomingAssignment.getText(), "shor", "Section consists of upcoming assignments, followed by due date and time");
            Assert.assertEquals(dashBoard.dueDate1.getText(), "Sep 28, 2015, 12:00 AM", "Section consists of upcoming assignments, followed by due date and time");
            //Assert.assertEquals(dashBoard.dueDate2.getText(), "Sep 28, 2015, 12:00 AM", "Section consists of upcoming assignments, followed by due date and time");

            //Row No - 18 :  Verify the overall score element.
            /*" The first element should show the ""Overall score"" for a student.
            If the assignment is not graded the OVERALL score section should be blank."  */
            new DirectLogin().directLoginWithCreditial(studentInfoList.get(0), Config.cmsPassword, "0");//Login as an Author
            new Assignment().submitAssignmentAsStudent(dataIndex);
            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), Config.cmsPassword, "0");//Login as an Author
            new Assignment().switchToAssignmentResponsePage(dataIndex);
            new Assignment().provideGradeToStudent("0", "0.6");
            new Assignment().releaseGradeForAll();

            new Navigator_appium().navigateTo("dashboard");
            studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent("" + dataIndex);
            dashBoard.icon_close.click();
            Thread.sleep(2000);


            //Row No- 19  It is the  average performance for all the gradable assignment (weitage) assigned to him/ her.
            //Row No - 20 : "The performance in the last 5 assignments (or available if less than 5) should be displayed as a bar chart. On click anywhere in the card, the student should navigate to the “Assignments” page.
           //Row No - 21 : "The number of assignments which are due in the next 14 days (and not yet submitted) should be shown with the due date of the earliest due assignment.

            Assert.assertEquals(dashBoard.label_overAllScore.getText(), "Overall Score", "Overall Score label is not appearing in dashboard");
            Assert.assertEquals(dashBoard.label_recentlyGraded.getText(), "Recently Graded","Recently Graded label is not appearing in dashboard");
            Assert.assertEquals(dashBoard.value_overAllScore.getText(),"60","Value for Overall Score is not appearing in dashboard");
            Assert.assertEquals(dashBoard.value_upComing.getText(),"0","Value for Upcoming  is not appearing in dashboard");

        }catch(Exception e){
            Assert.fail("Exception in the test method 'visibilityOfGradedAssignments' in the class 'GenericStudentTester'",e);
        }
    }







    @Test(priority = 2)
    public void visibilityOfAssignmentsinNavigationList1(){
        try{
            int dataIndex = 27;
            //Row No - 27 :  Click on the navigation button.
            /*Expected- "As a part of implementation user should see the Assignments in the main Navigation list.
            3rd in the position below the e-Textbook & above the Course Stream.
            "*/
            String instructorInfo = "Your instructor has not yet assigned you anything. Please check back at a later point of time.";
            new LoginUsingRandomUser_appium().loginAsRandomStudent("" + dataIndex);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Tap().tapMainNavigator();
            List<WebElement> navigationoptionsList = appiumDriver.findElements(By.className("UIALink"));
            if(!(navigationoptionsList.get(3).getText().equals("e-Textbook")&&navigationoptionsList.get(4).getText().equals("Assignments")&&navigationoptionsList.get(5).getText().equals("Course Stream"))) {
                Assert.fail("As a part of implementation user should see the Assignments in the main Navigation list.3rd in the position below the e-Textbook & above the Course Stream.");
            }

            //Row No - 28 :  Click on "Assignments"
            //Expected 1- " The click action navigates the user to the Assignments page.
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Tap().tapMainNavigator();
            new Navigator_appium().navigateTo("Assignments");


            /*Expected - 2 : " Page consists of a single dropdown with the following values:
                         - All Assignments (default)
                         - Question Assignment
                         - Question Practise
                         - Discussion Assignment
                         - Non Gradable learning activity.""*/

            dashBoard.link_allAsignments.click();
            List<WebElement> allAsignmentsList = appiumDriver.findElements(By.className("UIALink"));
            if(allAsignmentsList.get(4).getText().equals("All Assignments") && allAsignmentsList.get(5).getText().equals("Question Assignment") && allAsignmentsList.get(6).getText().equals("Question Practice") &&
                    allAsignmentsList.get(7).getText().equals("Discussion Assignment") &&allAsignmentsList.get(8).getText().equals("Non Gradable Learning Activity")){
            }

           for(int a=5;a<allAsignmentsList.size();a++){
               allAsignmentsList = appiumDriver.findElements(By.className("UIALink"));
               allAsignmentsList.get(a).click();
               assignments.link_allAssignemntsDownArrow.click();
           }

            //Expected - 3 : " Default message should be "" Your instructor has not yet assigned you anything. Please check back at a later point of time"".
            Assert.assertEquals(assignments.label_instructorMessage.getText(),instructorInfo,"Default message should be 'Your instructor has not yet assigned you anything. Please check back at a later point of time'");


        }catch(Exception e){
            Assert.fail("Exception in the test method 'visibilityOfAssignmentsinNavigationList' in the class 'GenericStudentTester'",e);
        }
    }




    @Test(priority = 3)
    public void visibilityOfAssignmentsinNavigationList2(){
        try{
            String dataIndex = "31";

                    /*Row No - 31 : "ROLE : Student
                    CONDITION :
                    - Atleast one assignment in Not Started status.
                    - Atleast one assignment in In-progress status.
                    - Atleast one assignment in Submitted status.
                    - Atleast one assignment in Graded status(Wtg).
                    - Atleast one assignment in Graded status(no Wtg).
                    - Atleast one assignment in Reviewed status."*/

            //Expected-   The user should see the assigned assignments.
            ArrayList<String> assessmentInfoList = new Assignment().create("31_1");
            ArrayList<String> instructorInfoList = new LoginUsingRandomUser_appium().loginAsRandomInstructor("" + dataIndex);

            /*new Assignment().create(Integer.parseInt("31_2"));
            new Assignment().create(Integer.parseInt("31_3"));
            new Assignment().create(Integer.parseInt("31_4"));
            new Assignment().create(Integer.parseInt("31_5"));
            new Assignment().create(Integer.parseInt("31_6"));*/
            for(int a=0;a<assessmentInfoList.size();a++){
                System.out.println("assessmentInfoList : " + assessmentInfoList.get(a));
            }

            new DirectLogin().directLoginWithCreditial(assessmentInfoList.get(0), Config.cmsPassword, "0");//Login as an Author
           //TO be Continued

        }catch(Exception e){
            Assert.fail("Exception in the test method 'visibilityOfAssignmentsinNavigationList' in the class 'GenericStudentTester'",e);
        }
    }









    @Test(priority = 3)
    public void visibilityOfAssignmentsinTOC(){
        try{
            String dataIndex = "41";
            //String abc



            ArrayList<String> instructorInfoList = new LoginUsingRandomUser_appium().loginAsRandomInstructor("" + dataIndex);
            new LoginUsingRandomUser_appium().logOutUser();
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent("" + dataIndex);
            for(int a=0;a<studentInfoList.size();a++){
                System.out.println("studentInfoList :" + studentInfoList.get(a));
            }


            //Row No - 41 : 03. Click on the navigation button.
            //Expecetd - User should not see any kind of assignment entry in the TOC.





            /*Row No - 42 : "ROLE : Student
            CONDITION : Atlease one  assignment has been assigned to the class or student.
            Assignment associated with lesson ""X"""*/
            //Expecetd1  - "User should see the assigned assignment entry in the TOC in the respective field."
            //Expected - 2 : Should be present for the respective lesson.
            ArrayList<String> assessmentInfoList = new Assignment().create(dataIndex,"noreserveAssignment");

            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            /*for(int a=0;a<assessmentInfoList.size();a++){
                System.out.println("assessmentInfoList : " + assessmentInfoList.get(a));
            }
            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), Config.cmsPassword, "0");//Login as an instructor
            new Assignment().assignToStudent(dataIndex);*/



            new Navigator_appium().navigateTo("etextbook");
            if(!appiumDriver.findElement(By.xpath("//UIALink[contains(@name,'"+assessmentInfoList.get(0)+"')]")).isDisplayed()){
                Assert.fail("User should see the assigned assignment entry in the TOC in the respective field");
            }





            /*Row No - 45 : "1.Login as student.
            2.Select etextbook from main navigator
            3.Select a chapter from TOC
            4.Navigate to Study Plan page*/
            //Expected 1 - Study should contain Blue color header.
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Study']")).isDisplayed(),true,"Study should contain Blue color header.");

            //Expeced  - 2 : "Header should contain chapter name ""Ch 'chapter number' : 'chapter name' "" Format in white color left side of header"
            //To be Automated


            //Expecetd - 3 : "Right side of header should contain ""cross icon"" in circle.
            //To be Automated


            //Expected - 4 : "Header for Study should not contain Performance percent.
            //To be Automated

            /*Row No - 49 : "5.Click on chapter static test
            6.Start chapter test
            7.Attempt few Questions
            8.cancel test""*/

            //Expected - Header for Study should not contain Performance percent.
            //appiumDriver.findElement(By.xpath("//UIALink[contains(@name,'"+assessmentInfoList.get(0)+"')]")).click();
            List<WebElement> wle = appiumDriver.findElements(By.xpath("//UIALink[contains(@name,'" + assessmentInfoList.get(0) + "')]"));
            System.out.println("wle size" + wle.size());
            wle.get(wle.size() - 1).click();

            Thread.sleep(2000);
            appiumDriver.findElement(By.xpath("//UIAStaticText[@name='A']")).click();//Click on True button
            Thread.sleep(2000);
            appiumDriver.findElement(By.xpath("//UIAButton[@name='Submit Answer']")).click();//Click on submit Answer button
            Thread.sleep(2000);
            appiumDriver.findElement(By.xpath("//UIAButton[@name='Next Question']")).click();//Click on Next Question button button




            /*Row No- 50 : "9.Select a chapter from TOC
            10.Click on same test
            11.Attempt all questons
            12.Finish test*/

            //Expecetd - Header for Study should contain Performance percent.
            //TO be Automated


            new Navigator_appium().navigateTo("eTextbook");
            wle = appiumDriver.findElements(By.xpath("//UIALink[contains(@name,'" + assessmentInfoList.get(0) + "')]"));
            System.out.println("wle size" + wle.size());
            wle.get(wle.size() - 1).click();

            //appiumDriver.findElement(By.xpath("//UIALink[contains(@name,'" + assessmentInfoList.get(0)+"')]")).click();

            for(int a=0;a<12;a++){
                System.out.println("aaaa: " +a);
                appiumDriver.findElement(By.xpath("//UIAStaticText[@name='A']")).click();//Click on True button
                Thread.sleep(1000);
                appiumDriver.findElement(By.xpath("//UIAButton[@name='Submit Answer']")).click();//Click on submit Answer button
                Thread.sleep(2000);

                if(a==10){
                    appiumDriver.findElement(By.xpath("//UIAButton[@name='Finish']")).click();//Click on Finish button
                    Thread.sleep(2000);
                }else{
                    appiumDriver.findElement(By.xpath("//UIAButton[@name='Next Question']")).click();//Click on Next Question button button
                    Thread.sleep(2000);
                }
            }




            //Expected -1 : Header for Study should contain Performance percent.
            //To be Automated
            new Navigator_appium().navigateTo("eTextbook");


            System.out.println("Page SOurce : " + appiumDriver.getPageSource()) ;


            //Expected - 2 : "Proficiency percent should show ""Performance : ""value %"".
            //Expected - 3 : "The Proficiency should be shown as a percentage in whole number format.
            String performancePercent = appiumDriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[19]")).getText();
            System.out.println("performancePercent : " + performancePercent);
            //x = 308;62




            /*Row No - 54 : "13.Select another chapter from TOC
            14.Navigate to Study Plan page
            15.Start test
            16.Skip all questions
            17.Finish test
            "*/


            dataIndex = "54";
            assessmentInfoList = new Assignment().create(dataIndex,"noreserveAssignment","Ch 5: Structure and Function of Plasma Membranes");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            for(int a=0;a<assessmentInfoList.size();a++){
                System.out.println("assessmentInfoList : " + assessmentInfoList.get(a));
            }


        }catch(Exception e){
            Assert.fail("Exception in the test method 'visibilityOfAssignmentsinTOC' in the class 'GenericStudentTester'",e);
        }
    }

















    @Test(priority = 1)
    public void visibilityOfGradedAssignments3(){
        try{
            int dataIndex = 13;
            new Dashboard_appium().navigateToStudent1Dashboard();//Login as a  Student1
            new Navigator_appium().navigateTo("etextbook");
            new Navigator_appium().navigateTo("assignmentS");
            new Navigator_appium().navigateTo("course Stream");
            new Navigator_appium().navigateTo("My notes");




            //new Navigator().NavigateTo("Current Assignments");
            //new Click().clickByclassname("ls-grade-book-assessment");
            //new ResponsePage().openViewResponsePage();
            /*loginPage.link_RandomUser.click();
            appiumDriver.hideKeyboard();
            System.out.println("Hide keyboard done");
            //appiumDriver.closeApp();
            //appiumDriver.context("");
            String appStrings = appiumDriver.getAppStrings();
            System.out.println("appStrings : " + appStrings);
            String context  = appiumDriver.getContext();
            System.out.println("context : " + context);
            //appiumDriver.getContextHandles();

            //appiumDriver.getExecuteMethod();
            ScreenOrientation so = appiumDriver.getOrientation();
            System.out.println("so :" + so);
            URL remoteAddress = appiumDriver.getRemoteAddress();
            System.out.println("remoteAddress : " + remoteAddress.getPath());
            //JsonObject settings = appiumDriver.getSettings();
            //System.out.println("settings : " + settings.getAsString());
            //appiumDriver.installApp("String appPath");
            boolean isAppInstalled = appiumDriver.isAppInstalled("String bundleId");
            System.out.println("isAppInstalled : " + isAppInstalled);
            *//*appiumDriver.launchApp();
            appiumDriver.location();*//*
            //appiumDriver.performMultiTouchAction("MultiTouchAction multiAction");
            //appiumDriver.lockScreen("int seconds");
            //appiumDriver.performTouchAction("TouchActions touchaction");
            //appiumDriver.pinch(WebElement element);
            appiumDriver.pullFile("String remotePath");
            appiumDriver.pullFolder("String remotePath");
            appiumDriver.removeApp("String bundleId");*/

        }catch(Exception e){
            Assert.fail("Exception in the test method 'visibilityOfGradedAssignments' in the class 'GenericStudentTester'",e);
        }
    }









    @Test(priority = 4)
    public void accessNewViewForStudyPlan(){
        try{
            String dataIndex = "97";


            /*Row No - 97 : "1.Login as student.
            2.Select etextbook from main navigator."*/

            //Expeced  1 - The student should be able to click on the e-Textbook element in the main navigator.
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent("" + dataIndex);
            for(int a=0;a<studentInfoList.size();a++){
                System.out.println("studentInfoList :" + studentInfoList.get(a));
            }
            new Navigator_appium().navigateTo("etextbook");

            //Expected -2 : "The chapter view should show chapter options in the column one.
            ArrayList<String> preDefinedchapterNames = new ArrayList<>();
            ArrayList<String> chapterNames = new ArrayList<>();

            preDefinedchapterNames.add("Chapter 1 The Study of Life");
            preDefinedchapterNames.add("Chapter 2 The Chemical Foundation of Life");
            preDefinedchapterNames.add("Chapter 3 Biological Macromolecules");
            preDefinedchapterNames.add("Chapter 4 Cell Structure");
            preDefinedchapterNames.add("Chapter 5 Structure and Function of Plasma Membranes");
            preDefinedchapterNames.add("Chapter 6 Metabolism");
            preDefinedchapterNames.add("Chapter 7 Cellular Respiration");
            preDefinedchapterNames.add("Chapter 8 Photosynthesis");
            preDefinedchapterNames.add("Chapter 9 Cell Communication");
            preDefinedchapterNames.add("Chapter 10 Cell Reproduction");


            List<WebElement> chaptersElementsList = appiumDriver.findElements(By.className("UIALink"));
            for(int a=3;a<23;a=a+2){
                String chapterName = chaptersElementsList.get(a).getText().trim();
                chapterNames.add(chapterName);
                System.out.println("chapterName : " + chapterName);
            }

            if(preDefinedchapterNames.containsAll(chaptersElementsList)){
                Assert.fail("The chapter view should show chapter options in the column one.");
            }

            if(preDefinedchapterNames.containsAll(chaptersElementsList)){
                Assert.fail("The chapter view should show chapter options in the column one.");
            }


           /*Row No - 99 : "3.Select a chapter
            4.Verify study plan*/
            //Expected 1 - Study plan should be displayed at right part of chapter view.
            //Expected - 2 : "Study tab should be opened by default for any chapter view.

            new SelectChapter_appium().selectChapter(2);
            if(!eTextbook.button_study.isDisplayed()){
                Assert.fail("Study tab should be opened by default for any chapter view.");
            }





            //Expected - 3 : "Study plan header should contain chapter name.
            //Not Automated



            //Expected - 4 : "Study plan should have two tabs ""Study"" and ""ORION Adaptive Practice”.
            if(!eTextbook.button_study.isDisplayed()  && eTextbook.button_OrionAdaptivePractice.isDisplayed()){
                Assert.fail("Study plan should have two tabs Study and ORION Adaptive Practice.");
            }




            //Expecetd - 5 : """ORION Adaptive Practice” should be placed at right side of ""Study"" tab.
            List<WebElement> tabsElementsList = appiumDriver.findElements(By.className("UIAButton"));
            if(!tabsElementsList.get(0).getText().equals("Study")&&tabsElementsList.get(1).getText().equals("ORION Adaptive Practice")){
                Assert.fail("ORION Adaptive Practice” should be placed at right side of Study tab");
            }



            //Expected - 6 : """Study"" tab should contain "" lesson, assignment and static assessment"".
            //Expected - 7 : "All the elements in study tab should be arranged section wise.
            List<WebElement> studyTabsElementsList = appiumDriver.findElements(By.className("UIAStaticText"));
            if(!studyTabsElementsList.get(47).getText().equals("2.1 Atoms, Isotopes, Ions, and Molecules: The Building Blocks") && studyTabsElementsList.get(51).getText().equals("2.2 Water") && studyTabsElementsList.get(56).getText().equals("2.3 Carbon") &&studyTabsElementsList.get(60).getText().equals("Chapter Summary")){
                Assert.fail("Study tab should contain lesson, assignment and static assessment");
            }



            List<WebElement> studyAssessmentsElementsList = appiumDriver.findElements(By.className("UIALink"));
           if(!studyAssessmentsElementsList.get(41).getText().equals("lesson : Introduction") && studyAssessmentsElementsList.get(42).getText().equals("lesson : 2.1: Atoms, Isotopes, Ions, and Molecules: The Building Blocks") && studyAssessmentsElementsList.get(43).getText().equals("Not Started static_assessment : 2.1 Concept Check") &&studyAssessmentsElementsList.get(48).getText().equals("lesson : 2.2: Water") &&studyAssessmentsElementsList.get(48).getText().equals("lesson : 2.2: Water")&&studyAssessmentsElementsList.get(49).getText().equals("Not Started static_assessment : 2.2 Concept Check")&&studyAssessmentsElementsList.get(54).getText().equals("lesson : 2.3: Carbon") &&studyAssessmentsElementsList.get(55).getText().equals("Not Started static_assessment : 2.3 Concept Check") &&studyAssessmentsElementsList.get(60).getText().equals("lesson : Chapter Summary")){
                Assert.fail("Study tab should contain lesson, assignment and static assessment");
            }



            //Row No - 106 -  5.Verify study tab
            //Expected - "Pre-Chapter should not be displayed
            /*boolean pre = appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Pre']")).isDisplayed();
            System.out.println("pre : " + pre);*/
            //Not Automated


            //Row No - 107 : 6.Click on introduction lesson on Study tab
            //Expected - Introduction lesson should get rendered
            new UIElement().waitAndFindElement(eTextbook.label_introduction);
            Thread.sleep(9000);
            eTextbook.label_introduction.click();
            new UIElement().waitAndFindElement(eTextbook.tab_introduction);
            System.out.println("tab_introduction : " + eTextbook.tab_introduction.isDisplayed());


            if(appiumDriver.findElements(By.xpath("//UIAButton[@name = 'Introduction']")).size()==0){
                Assert.fail("Introduction lesson should get rendered");
            }


            //EXpected - 2 : "Lesson should contain footer navigation for suggested next element
            System.out.println("eTextbook.link_footerNavigation : " + eTextbook.link_footerNavigation.getText());
            if(!eTextbook.link_footerNavigation.isDisplayed()){
                Assert.fail("Lesson should contain footer navigation for suggested next element");
            }


            //Row No - 109 : 7.Click on element link from footer navigator
            //Expected 1- "Next element should get rendered
            eTextbook.link_footerNavigation.click();
            new UIElement().waitAndFindElement(eTextbook.tab_lesson);
            System.out.println("eTextbook.tab_lesson.getText() : " + eTextbook.tab_lesson.isDisplayed());
            Assert.assertEquals(eTextbook.tab_lesson.isDisplayed(),true,"Next element should get rendered");



            //Expected 2 - "Only lesson and static assessment should appear as element link over footer
            if(!eTextbook.linkFooter_introduction.isDisplayed()&&eTextbook.linkFooter_conceptCheck.isDisplayed()){
                Assert.fail("Only lesson and static assessment should appear as element link over footer");
            }




            //Row No - 111 - 8.Navigate static practice test via footer
            //Expected - "Student should be able to navigate to static practice test by footer.
            eTextbook.linkFooter_conceptCheck.click();
            new UIElement().waitAndFindElement(eTextbook.tab_P21);
            System.out.println("eTextbook.tab_P21.isDisplayed() : " + eTextbook.tab_P21.isDisplayed());
            /*if(!eTextbook.tab_P21.isDisplayed()){
                Assert.fail("Student should be able to navigate to static practice test by footer.");
            }*/


            /*Row N0 - 112 : "9.Attempt all questions
            10.Finish the Static test"*/
            //Expected - "Footer navigator should be visible in Performance summary Page
            for(int a=0;a<3;a++){
                appiumDriver.findElement(By.xpath("//UIAStaticText[@name='A']")).click();//Click on True button
                Thread.sleep(1000);
                appiumDriver.findElement(By.xpath("//UIAButton[@name='Submit Answer']")).click();//Click on submit Answer button
                Thread.sleep(2000);
                if(a==2){
                    appiumDriver.findElement(By.xpath("//UIAButton[@name='Finish']")).click();//Click on Finish button
                    Thread.sleep(2000);
                    break;
                }else{
                    appiumDriver.findElement(By.xpath("//UIAButton[@name='Next Question']")).click();//Click on Next Question button button
                    Thread.sleep(2000);
                }
            }








            //Row No - 113 - 11.Click on Orion Adaptive Practice tab
            //To be continued




            //Row No -114 : """Orion Adaptive Practice"" should be contain diagnostic test .



            //Row No - 115 : "Student should be able to toggle between study and orion tab


            //Row N0 -116 : 12.Start Diagnostic test
            //Expected - Student should be able to start diagnostic test.


            //Row No - 117 : "13.Attempt all questions
            //Expected - Student should be able to attempt diagnostic Assessment .


            //Row No - 118 :14.Finish the diagnostic test

            /*Expected - "It should navigate to performance summary page.
            "
            "Footer navigater should not be shown on performance summary page.
            "
            "Chapter level adaptive practice with a ""Practice"" button will be displayed.
            "
            "End of the row of ""practice button"" should contain a ""arrow"".*/
            //Need to be Automated
            new Navigator_appium().navigateTo("etextbook");

            eTextbook.button_OrionAdaptivePractice.click();
            Thread.sleep(9000);
            appiumDriver.findElement(By.xpath("//UIALink[@name ='select confidence level, almost confident']")).click();
            eTextbook.button_beginDiagnostic.click();
            for(int a=0;a<20;a++){
                appiumDriver.findElement(By.xpath("//UIAStaticText[@name='A']")).click();//Click on True button
                Thread.sleep(1000);
                if(a==19){
                    appiumDriver.findElement(By.xpath("//UIAButton[@name='Finish']")).click();//Click on Finish button
                    Thread.sleep(1000);

                }else{
                    eTextbook.button_submit.click();
                    Thread.sleep(1000);
                }
            }

        }catch(Exception e){
            Assert.fail("Exception in the test method 'accessNewViewForStudyPlan' in the class 'GenericStudentTester'",e);
        }
    }






    @Test(priority = 4)
    public void completeDiagTestBeforePracticeTest(){
        try{

            /*Row No - 139 : "1.Login as student
            2.Select etextbook from main navigator
            3.Select a chapter
            4.Navigate to Study Plan page
            5.Click on Orion Adaptive Practice tab"*/


            String dataIndex = "139";
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent("" + dataIndex);
            for(int a=0;a<studentInfoList.size();a++){
                System.out.println("studentInfoList :" + studentInfoList.get(a));
            }
            new Navigator_appium().navigateTo("etextbook");
            new UIElement().waitAndFindElement(eTextbook.button_OrionAdaptivePractice);
            Thread.sleep(5000);


            //The student should be able to click on the “Orion Adaptive Practice” tab.

            //"First row should contain text "" First, indicate how confident you are about this chapter"".
            //"Second row should contain text ""Then continue to your quick diagnostic activity"".
            //"Thired row should be contain text ""Enter confidence level"".

            eTextbook.button_OrionAdaptivePractice.click();
            if(!appiumDriver.getPageSource().contains("First, indicate how confident you are about this chapter")){
                Assert.fail("First row should contain text \"\" First, indicate how confident you are about this chapter\"\".");
            }

            if(!appiumDriver.getPageSource().contains("Then continue to your quick diagnostic activity")){
                Assert.fail("Second row should contain text \"\"Then continue to your quick diagnostic activity\"\".");
            }

            if(!appiumDriver.getPageSource().contains("Enter Confidence Level")){
                Assert.fail("Third row should contain text 'Enter confidence level'");
            }


            //"It should be contain ""Begin Diagnostic"" button.
            Assert.assertEquals(eTextbook.button_beginDiagnostic.isDisplayed(), true, "It should be contain \"\"Begin Diagnostic\"\" button");



            //"User should not be able to view adaptive Practice test.
           if(appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Practice']")).isDisplayed()){
               Assert.fail("User should not be able to view adaptive Practice test.");
           }



            /*146 - 6.Select confidence level
            "User should be able to select confidence level.*/
            Thread.sleep(2000);
            appiumDriver.tap(1, 484, 256, 1);
            //Thread.sleep(9000);


            /*147 - .Click on "begin diagnostic"
            Student should be able to click on "begin diagnostic" button.*/
            new UIElement().waitAndFindElement(eTextbook.button_beginDiagnostic);
            eTextbook.button_beginDiagnostic.click();

            //Diagnostic test should be start.
            new UIElement().waitAndFindElement(eTextbook.tab_diagTest);
            try{
                appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Diagnostic - Ch 1: The Study of Life']"));
            }catch(Exception e){
               Assert.fail("Diagnostic test should be start.");
            }



           /*149 - "8.Attempt few questions
            9.Click on Continue later link*/

             /*Student should navigate to Study tab.
            "Student should be able to view all changes in ORION Adaptive Practice tab.
            "Text in First,Second row and third row should be invisible.
            "First row should be contain text ""<color flower>"" and text ""Orion Adaptive Practice"".
            "After text ""Continue Diagnostic"" button should be display.*/
            Thread.sleep(5000);
            for(int a=0; a < 4; a++) {
                Thread.sleep(1000);
                //new UIElement().waitAndFindElement(By.xpath("//UIAStaticText[@name='A']"));
                appiumDriver.findElement(By.xpath("//UIAStaticText[@name='A']")).click();//Click on True button
                //Thread.sleep(1000);
                eTextbook.button_submit.click();
                //Thread.sleep(1000);
            }

            List<WebElement> questiontextList = appiumDriver.findElements(By.className("UIAStaticText"));
            int pos = 0;
            for(int a=0;a<questiontextList.size();a++){
                if(questiontextList.get(a).getText().trim().equals("Q 1.4:")){
                    pos = a+1;
                    break;
                }
            }


            new UIElement().waitAndFindElement(eTextbook.icon_quitDiagTest);
            //Thread.sleep(15000);
            Thread.sleep(2000);
            new Tap_appium().tapQuitDiagTest();
            //Thread.sleep(9000);
            eTextbook.link_continueDiagtestLater.click();
            new UIElement().waitAndFindElement(eTextbook.button_OrionAdaptivePractice);
            Assert.assertEquals(eTextbook.button_study.isDisplayed(), true, "Student should be able to view all changes in ORION Adaptive Practice tab.");
            eTextbook.button_OrionAdaptivePractice.click();

            if(appiumDriver.getPageSource().contains("First, indicate how confident you are about this chapter")){
                Assert.fail("First row should contain text \"\" First, indicate how confident you are about this chapter\"\".");
            }

            if(appiumDriver.getPageSource().contains("Then continue to your quick diagnostic activity")){
                Assert.fail("Second row should contain text \"\"Then continue to your quick diagnostic activity\"\".");
            }

            if(!appiumDriver.getPageSource().contains("Enter Confidence Level")){
                Assert.fail("Third row should contain text 'Enter confidence level'");
            }

            Assert.assertEquals(eTextbook.label_OrionAdaptivePractice.getText().trim(), "ORION Adaptive Practice", "First row should be contain text \"\"<color flower>\"\" and text \"\"Orion Adaptive Practice\"\"");
            new UIElement().waitAndFindElement(eTextbook.button_continueDiagnostic);
            Assert.assertEquals(eTextbook.button_continueDiagnostic.isDisplayed(), true, "After text \"\"Continue Diagnostic\"\" button should be display.");




            //Row No - 154 - 11.Click on Continue Diagnostic
            /*"Student should be able to click on Continue Diagnostic button.
            "It should be navigate to last unattempt question in diagnostic test.*/
            eTextbook.button_continueDiagnostic.click();
            String expected = "A group of students are conducting a scientific study. The students derive a general conclusion based on the data collected and observations made. Which type of scientific study are they conducting?";
            //Assert.assertEquals(questiontextList.get(pos).getText().trim(), expected, "It should be navigate to last unattempt question in diagnostic test.");




           /*156 : "12.Attempt All questions
            13.Finish the diagnostic test*/
            /*EXPECTED - Student should be able to Finished diagnostic test.
            "The student should be navigated to the “Performance Summary” page for that diagnostic.*/

            for(int a=0;a<17;a++){
                Thread.sleep(1000);
                appiumDriver.findElement(By.xpath("//UIAStaticText[@name='A']")).click();//Click on True button
                //Thread.sleep(1000);
                if(a==16){
                    new UIElement().waitAndFindElement(By.xpath("//UIAButton[@name='Finish']"));
                    appiumDriver.findElement(By.xpath("//UIAButton[@name='Finish']")).click();//Click on Finish button
                    //Thread.sleep(2000);
                    break;
                }else{
                    eTextbook.button_submit.click();
                    //Thread.sleep(2000);
                }
            }




            /*158 - "14.Navigate to corresponding chapter
            15.Click on Orion Adaptive Practice tab*/
            /*Expected - Practice Button should display.
            All the section level adaptive practice elements should be display.*/
            new Navigator_appium().navigateTo("etextbook");
            new UIElement().waitAndFindElement(eTextbook.button_OrionAdaptivePractice);
            //Thread.sleep(3000);
            //Thread.sleep(1000);

            eTextbook.button_OrionAdaptivePractice.click();
            new UIElement().waitAndFindElement(eTextbook.button_practice);
            //System.out.println("eTextbook.button_practice is displayed: " + eTextbook.button_practice.isDisplayed());
            Assert.assertEquals(eTextbook.button_practice.isDisplayed(), true, "Practice Button should display.");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '1.1: Discuss the scientific basis for the study of biology - Practice']")).isDisplayed(), true, "All the section level adaptive practice elements should be display.");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '1.2: Describe the properties and levels of organization of living things - Practice']")).isDisplayed(), true, "All the section level adaptive practice elements should be display.");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '1.0: Analyze the process of science and the concepts of biology using critical thinking skills - Practice']")).isDisplayed(), true, "All the section level adaptive practice elements should be display.");



           /*Row No - 160 : "16.Navigate to another chapter
            17.Click on Orion adaptive practice tab
            18.Start diagnostic tast
            19.Attempt few Questions
            20.Quit Diagnostic test*/

            /*Expected - Student should be able to Quit Diagnostic test.
            "The student should be navigated to the “Performance Summary” page for that diagnostic.*/
            new SelectChapter_appium().selectChapter(2);
            new UIElement().waitAndFindElement(eTextbook.button_OrionAdaptivePractice);
            eTextbook.button_OrionAdaptivePractice.click();
            new Tap_appium().tapConfidenceLevel(1);
            eTextbook.button_beginDiagnostic.click();


            for(int a=0;a<4;a++){
                Thread.sleep(1000);
                new UIElement().waitAndFindElement(By.xpath("//UIAStaticText[@name='A']"));
                appiumDriver.findElement(By.xpath("//UIAStaticText[@name='A']")).click();//Click on True button
                //Thread.sleep(1000);
                new UIElement().waitAndFindElement(eTextbook.button_submit);
                eTextbook.button_submit.click();
                //Thread.sleep(1000);
            }
            new UIElement().waitAndFindElement(eTextbook.icon_quitDiagTest);
            new Tap_appium().tapQuitDiagTest();
            new UIElement().waitAndFindElement(eTextbook.link_quit);
            eTextbook.link_quit.click();


            /*162 - "21.Navigate to corresponding chapter
            22.Click on Orion Adaptive Practice tab*/

            /*Expected - Student should be able to view "practice" button.
            "All the section level adaptive practice elements should be display.*/

            new Navigator_appium().navigateTo("etextbook");
            new SelectChapter_appium().selectChapter(2);
            new UIElement().waitAndFindElement(eTextbook.button_OrionAdaptivePractice);
            eTextbook.button_OrionAdaptivePractice.click();
            new UIElement().waitAndFindElement(eTextbook.button_practice);
            Assert.assertEquals(eTextbook.button_practice.isDisplayed(), true, "Student should be able to view \"practice\" button.");

        }catch(Exception e){
            Assert.fail("Exception in the test method 'completeDiagTestBeforePracticeTest' in the class 'GenericStudentTester'",e);
        }
    }




    @Test(priority = 5)
    public void startPracticeAfterquittingDiagTest(){
        try{

            /*Row No - 164 : "1.Login as student
            2.Select etextbook from main navigator
            3.Select a chapter
            4.Navigate to Study Plan page
            5.Click on Orion Adaptive Practice tab"*/

           /* Expected- "Student should be view ""Begin Diagnostic"" Button to Start diagnostic test.*/

            String dataIndex = "164";
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent("" + dataIndex);
            for(int a=0;a<studentInfoList.size();a++){
                System.out.println("studentInfoList :" + studentInfoList.get(a));
            }
            new Navigator_appium().navigateTo("etextbook");
            new UIElement().waitAndFindElement(eTextbook.button_OrionAdaptivePractice);
            Thread.sleep(5000);
            eTextbook.button_OrionAdaptivePractice.click();
            Thread.sleep(2000);
            appiumDriver.tap(1, 484, 256, 1);
            //Thread.sleep(9000);
            new UIElement().waitAndFindElement(eTextbook.button_beginDiagnostic);
            eTextbook.button_beginDiagnostic.click();


            /*Expected - 165 :"6.Start diagnostic tast
            7.Attempt some questions
            8.Finish/quit the diagnostic test*/
            //Expected - The student should be navigated to the “Performance Summary” page for that diagnostic.

            for(int a=0;a<4;a++){
                Thread.sleep(1000);
                //new UIElement().waitAndFindElement(By.xpath("//UIAStaticText[@name='A']"));
                appiumDriver.findElement(By.xpath("//UIAStaticText[@name='A']")).click();//Click on True button
                //Thread.sleep(1000);
                //new UIElement().waitAndFindElement(eTextbook.button_submit);
                eTextbook.button_submit.click();
                //Thread.sleep(1000);
            }
            new UIElement().waitAndFindElement(eTextbook.icon_quitDiagTest);
            Thread.sleep(2000);
            new Tap_appium().tapQuitDiagTest();
            new UIElement().waitAndFindElement(eTextbook.link_quit);
            eTextbook.link_quit.click();
            //System.out.println("appiumDriver.getPageSource(): 1:  " + appiumDriver.getPageSource());
            //To be Automated







            /*Row No - 166 : "9.Navigate to corresponding chapter
            10.Click on Orion Adaptive Practice tab*/

            /*Expected  - Student should be able to view "practice" button.
            "Practice button should be display after ""Orion Adaptive Practice"" text.
            "All the section level adaptive practice elements should be display below.*/
            new Navigator_appium().navigateTo("etextbook");
            new UIElement().waitAndFindElement(eTextbook.button_OrionAdaptivePractice);
            Thread.sleep(2000);
            eTextbook.button_OrionAdaptivePractice.click();
            new UIElement().waitAndFindElement(eTextbook.button_practice);
            Assert.assertEquals(eTextbook.button_practice.isDisplayed(), true, "Student should be able to view \"practice\" button.");
            Assert.assertEquals(eTextbook.label_OrionAdaptivePractice.getText(), "ORION Adaptive Practice", "Practice button should be display after \"\"Orion Adaptive Practice\"\" text.");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '1.1: Discuss the scientific basis for the study of biology - Practice']")).isDisplayed(), true, "All the section level adaptive practice elements should be display.");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '1.2: Describe the properties and levels of organization of living things - Practice']")).isDisplayed(), true, "All the section level adaptive practice elements should be display.");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '1.0: Analyze the process of science and the concepts of biology using critical thinking skills - Practice']")).isDisplayed(), true, "All the section level adaptive practice elements should be display.");




            //169 - 11.Click on Practice button
            /*Expected - "Student should be able to click on ""Practice"" button.
            "Practice test should be started.*/

            /*"12.Start practice test
            13.attempt few questions
            14.Click on Close icon
            15.Click on View report*/
            //Expected - The student should be navigated to the “Performance Summary” page for that adaptive Practice.


            eTextbook.button_practice.click();
            Thread.sleep(5000);

            for(int a=0;a<1;a++){
                Thread.sleep(5000);
                //appiumDriver.findElement(By.xpath("//UIAButton[@name='Submit Answer']")).click();//Click on submit Answer button
                appiumDriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[3]")).click();//Click on submit Answer button
                Thread.sleep(2000);
                appiumDriver.findElement(By.xpath("//UIAButton[@name='Next Question']")).click();//Click on Next Question button button
            }
            Thread.sleep(2000);
            new Tap_appium().tapQuitDiagTest();
            new UIElement().waitAndFindElement(eTextbook.link_viewReport);
            eTextbook.link_viewReport.click();
            Thread.sleep(5000);
            //System.out.println("appiumDriver.getPageSource() for Practice Test" + appiumDriver.getPageSource());



           /*172 - "16.Navigate to corosponding chapter
            17.Click on Orion adaptive practice tab*/
            //Expecetd - Chapter should be contain section level adaptive practice test.
            new Navigator_appium().navigateTo("etextbook");
            Thread.sleep(5000);

            new UIElement().waitAndFindElement(By.xpath("//UIAButton[@name = 'ORION Adaptive Practice']"));
            eTextbook.button_OrionAdaptivePractice.click();
            new UIElement().waitAndFindElement(By.xpath("//UIAStaticText[@name = '1.1: Discuss the scientific basis for the study of biology - Practice']"));
            Thread.sleep(1000);
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '1.1: Discuss the scientific basis for the study of biology - Practice']")).isDisplayed(), true, "All the section level adaptive practice elements should be display.");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '1.2: Describe the properties and levels of organization of living things - Practice']")).isDisplayed(), true, "All the section level adaptive practice elements should be display.");
            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '1.0: Analyze the process of science and the concepts of biology using critical thinking skills - Practice']")).isDisplayed(), true, "All the section level adaptive practice elements should be display.");





            //173 - 18.Start adaptive practice section level test
            //Expected - "Student should be able to start section level adaptive practice test.


            /*"19.Attempt few questions
            20.Click on Close icon
            21.Click on View report*/
            /*Expecetd - Student should be able to attempt adaptive practice section level test.
            "The student should be navigated to the “Performance Summary” page for that adaptive Practice.*/
            appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '1.2: Describe the properties and levels of organization of living things - Practice']")).click();
            Thread.sleep(5000);
            for(int a=0;a<1;a++){
                Thread.sleep(1000);
               /* if(appiumDriver.findElements(By.xpath("//UIAStaticText[@name='A']")).size()!=0){
                    appiumDriver.findElement(By.xpath("//UIAStaticText[@name='A']")).click();//Click on True button
                }*/
                appiumDriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[3]")).click();//Click on submit Answer button

                //appiumDriver.findElement(By.xpath("//UIAButton[@name='Submit Answer']")).click();//Click on submit Answer button
                Thread.sleep(2000);
                appiumDriver.findElement(By.xpath("//UIAButton[@name='Next Question']")).click();//Click on Next Question button button
            }
            Thread.sleep(2000);
            new Tap_appium().tapQuitDiagTest();
            new UIElement().waitAndFindElement(eTextbook.link_viewReport);
            eTextbook.link_viewReport.click();
            Thread.sleep(5000);
            //System.out.println("appiumDriver.getPageSource() for Practice Test" + appiumDriver.getPageSource());


        }catch(Exception e){
            Assert.fail("Exception in the test method 'completeDiagTestBeforePracticeTest' in the class 'GenericStudentTester'",e);
        }
    }


    @Test(priority = 6)
    public void validateHelpPopup(){
        try {

            /*"1.Login as new user
            2.Select e-textbook from main navigator*/
            /*Expected - Student should be able to navigate Study plan page .
            "Help popup should be displayed.
            "This pop up should be point to ""Search e-textbook"" text box .
            "Pop up should be contain header text ""Search"" .
            "Pop up should be contain a button ""next"".
            "Pop up should be contain a cross button after header text right side.*/


            String dataIndex = "77";
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent("" + dataIndex);
            for (int a = 0; a < studentInfoList.size(); a++) {
                System.out.println("studentInfoList :" + studentInfoList.get(a));
            }
            new Navigator_appium().navigateTo("etextbook");
            //Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Study']")).isDisplayed(), true, "Study should contain Blue color header.");

            Thread.sleep(1000);
            System.out.println("study is Displayed : " + appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Study']")).isDisplayed());

            appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Study']")).click();
            Thread.sleep(1000);
            appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Study']")).sendKeys("abc");
            Thread.sleep(1000);
            //Assert.assertEquals(eTextbook.label_search.getText(), "Search", "Pop up should be contain header text \"\"Search\"\"");

            //System.out.println("Next button is Displayed : " + eTextbook.button_next.isDisplayed());
            /*try {
                eTextbook.button_next.click();
                System.out.println("3");
            } catch (Exception e){
                appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Next']")).click();
                System.out.println("4");

            }*/



            //83 - "3.Click on cross button
            //Expected - Pop up should be disable .
            appiumDriver.tap(1, 707, 73, 1);
            if(appiumDriver.findElements(By.xpath("//UIAButton[@name = 'Next']")).size()!=0){
                //Assert.fail("Pop up should be disable .");
                System.out.println("NNNNNN");

            }




            //"84 - 4.Again click on help button
            //Expected - Same pop up shold be display step 4 repeat.
            appiumDriver.tap(1, 759, 12, 1);
            System.out.println("study is Displayed : " + appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Study']")).isDisplayed());
            appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Study']")).click();
            Thread.sleep(1000);
            appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Study']")).sendKeys("abc");
            Thread.sleep(1000);
            //Assert.assertEquals(eTextbook.label_search.getText(), "Search", "Pop up should be contain header text \"\"Search\"\"");
            //System.out.println("Next button is Displayed : " + eTextbook.button_next.isDisplayed());
            appiumDriver.tap(1, 707, 73, 1);
            if(appiumDriver.findElements(By.xpath("//UIAButton[@name = 'Next']")).size()!=0){
                //Assert.fail("Pop up should be disable .");
                System.out.println("Pop up should be disable .");
            }
            appiumDriver.tap(1, 759, 12, 1);




            //85 - 5.click on next button
            /*Expected - "This pop up should be display in middle of the screen.
            "pop up should be contain header text ""Open in new tab"".
            "pop up should be contain a button ""next"" and cross button as same as seen befor.*/
            eTextbook.button_next.click();
            Thread.sleep(1000);
            System.out.println("eTextbook.label_openInNewTab.getText(): " + eTextbook.label_openInNewTab.getText());
            System.out.println("eTextbook.button_getStarted.getText(): " + eTextbook.button_getStarted.getText());
            System.out.println("eTextbook.button_getStarted.isDisplayed(): " + eTextbook.button_getStarted.isDisplayed());

            eTextbook.button_getStarted.click();

            if(appiumDriver.findElements(By.xpath("//UIAButton[@name = 'Get Started']")).size()!=0){
                System.out.println("TTTTTTTT");
            }








            //88 - 6.Click on next
            /*Expected - "Pop up should be displayed in center if no perfromance is displayed over study plan
            "This pop up should be navigate to proficiency level bar.
            "Pop up should be contain header text ""Section level Performance"".
            "It should be contain text in body ""Current Secton level Performance"".
            "Body of pop up should be contain Performance bar with percent value.
            "Body of pop up should be divided into two section by straight line .
            "After line body should be contain text ""View current level section Performance .
            "Pop up should be contain a button ""Get started"" and cross button as same as seen befor.*/

            //search , x= 484, y = 256
            //Next button x = 674, y = 255
            //close icon x = 707, y = 73
            //Help button - x= 759, y = 12

        }catch(Exception e){
            Assert.fail("Exception in the test method 'validateHelpPopup' in the class 'GenericStudentTester'",e);
        }
    }




    @Test(priority = 6)
    public void validateHelpPopup1(){
        try {

            /*"1.Login as new user
            2.Select e-textbook from main navigator*/
            /*Expected - Student should be able to navigate Study plan page .
            "Help popup should be displayed.
            "This pop up should be point to ""Search e-textbook"" text box .
            "Pop up should be contain header text ""Search"" .
            "Pop up should be contain a button ""next"".
            "Pop up should be contain a cross button after header text right side.*/


            String dataIndex = "77";
            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent("" + dataIndex);
            for (int a = 0; a < studentInfoList.size(); a++) {
                System.out.println("studentInfoList :" + studentInfoList.get(a));
            }
            new Navigator_appium().navigateTo("etextbook");
            Thread.sleep(5000);
            //Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Study']")).isDisplayed(), true, "Study should contain Blue color header.");

            //appiumDriver.tap(1, 759, 12, 1);


            Thread.sleep(1000);
            //System.out.println("study is Displayed : " + appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Study']")).isDisplayed());
            eTextbook.button_study.click();
            eTextbook.button_study.sendKeys("abc");

           /* appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Study']")).click();
            Thread.sleep(1000);
            appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Study']")).sendKeys("abc");
            Thread.sleep(1000);*/
            //Assert.assertEquals(eTextbook.label_search.getText(), "Search", "Pop up should be contain header text \"\"Search\"\"");

            //System.out.println("Next button is Displayed : " + eTextbook.button_next.isDisplayed());
            /*try {
                eTextbook.button_next.click();
                System.out.println("3");
            } catch (Exception e){
                appiumDriver.findElement(By.xpath("//UIAButton[@name = 'Next']")).click();
                System.out.println("4");

            }*/



            //83 - "3.Click on cross button
            //Expected - Pop up should be disable .
            appiumDriver.tap(1, 707, 73, 1);





            //"84 - 4.Again click on help button
            //Expected - Same pop up shold be display step 4 repeat.
            appiumDriver.tap(1, 759, 12, 1);
            Thread.sleep(1000);
            eTextbook.button_study.click();
            eTextbook.button_study.sendKeys("abc");
            Thread.sleep(1000);
            appiumDriver.tap(1, 707, 73, 1);
            Thread.sleep(1000);
            appiumDriver.tap(1, 759, 12, 1);
            Thread.sleep(1000);




            //85 - 5.click on next button
            /*Expected - "This pop up should be display in middle of the screen.
            "pop up should be contain header text ""Open in new tab"".
            "pop up should be contain a button ""next"" and cross button as same as seen befor.*/
            eTextbook.button_next.click();
            Thread.sleep(1000);
            /*System.out.println("eTextbook.label_openInNewTab.getText(): " + eTextbook.label_openInNewTab.getText());
            System.out.println("eTextbook.button_getStarted.getText(): " + eTextbook.button_getStarted.getText());
            System.out.println("eTextbook.button_getStarted.isDisplayed(): " + eTextbook.button_getStarted.isDisplayed());
*/
            eTextbook.button_getStarted.click();

            /*if(appiumDriver.findElements(By.xpath("//UIAButton[@name = 'Get Started']")).size()!=0){
                System.out.println("TTTTTTTT");
            }*/








            //88 - 6.Click on next
            /*Expected - "Pop up should be displayed in center if no perfromance is displayed over study plan
            "This pop up should be navigate to proficiency level bar.
            "Pop up should be contain header text ""Section level Performance"".
            "It should be contain text in body ""Current Secton level Performance"".
            "Body of pop up should be contain Performance bar with percent value.
            "Body of pop up should be divided into two section by straight line .
            "After line body should be contain text ""View current level section Performance .
            "Pop up should be contain a button ""Get started"" and cross button as same as seen befor.*/

            //search , x= 484, y = 256
            //Next button x = 674, y = 255
            //close icon x = 707, y = 73
            //Help button - x= 759, y = 12


        }catch(Exception e){
            Assert.fail("Exception in the test method 'validateHelpPopup' in the class 'GenericStudentTester'",e);
        }
    }



    @Test(priority = 6)
    public void visibilityOfAssignmentTabinLessonPage(){
        try {

            /*262 - "1.Login as student.
            2.Navigate to lesson page via TOC.
            3.Verify Assignments tab."*/

            /*Expected  - Assignments tab should not be displayed.
            Discussion tab should be displayed.
            Bookmark tab should be displayed.
            Resourse tab should be displayed.*/



            String dataIndex = "262";

            ArrayList<String> instructorInfoList = new LoginUsingRandomUser_appium().loginAsRandomInstructor("" + dataIndex);
            for (int a = 0; a < instructorInfoList.size(); a++) {
                System.out.println("instructorInfoList :" + instructorInfoList.get(a));
            }
            new LoginUsingRandomUser_appium().logOutUser();


            ArrayList<String> studentInfoList = new LoginUsingRandomUser_appium().loginAsRandomStudent("" + dataIndex);
            for (int a = 0; a < studentInfoList.size(); a++) {
                System.out.println("studentInfoList :" + studentInfoList.get(a));
            }
            new Navigator_appium().navigateTo("etextbook");
            Thread.sleep(5000);
            WebDriverWait wait = new WebDriverWait(appiumDriver,20);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[@name = 'lesson : Introduction']")));
            new UIElement().waitAndFindElement(eTextbook.link_introduction);
            eTextbook.link_introduction.click();
            Thread.sleep(5000);
            //new UIElement().waitAndFindElement(By.xpath("//UIAStaticText[@name = 'INTRODUCTION']"));
           /* Assert.assertEquals(eTextbook.tab_discussion.isDisplayed(), true, "Discussion tab should be displayed.");
            Assert.assertEquals(eTextbook.tab_bookMark.isDisplayed(), true, "Bookmark tab should be displayed.");
            Assert.assertEquals(eTextbook.tab_Resources.isDisplayed(), true, "Resourse tab should be displayed");*/
            /*if(appiumDriver.findElements(By.xpath("//UIAButton[@name = 'Assignments']")).size()!=0){
                Assert.fail("Assignments tab should not be displayed.");
            }*/

            System.out.println("1 : "+ appiumDriver.findElements(By.xpath("//UIAButton[@name = 'Assignments']")).size());
            Assert.assertEquals(eTextbook.tab_discussion.getText(),"Discussion","Discussion tab should be displayed.");
            Assert.assertEquals(eTextbook.tab_bookMark.getText(),"Add to My Notes","Bookmark tab should be displayed.");
            Assert.assertEquals(eTextbook.tab_Resources.getText(),"Resources","Resourse tab should be displayed");




            //Precondition - "When a gradable assignment is assigned & status is ""not started"".
            /*266 - "1.Login as student.
            2.Navigate to lesson page via TOC.
            3.Verify Assignments tab."*/

            /*Expected - Assignments tab should be displayed.
            Discussion tab should be displayed.
            Bookmark tab should be displayed.
            Resourse tab should be displayed.*/
            ArrayList<String> assessmentInfo =  new Assignment().create(dataIndex);
            new DirectLogin().directLoginWithCreditial(instructorInfoList.get(0), Config.cmsPassword, "0");//Login as an Author
            new Assignment().assignToStudent(dataIndex, "gradable");//Assign assignment to class Sectionnew Assignment().assignToStudent(dataIndex);//Assign assignment to class Section

            new Navigator_appium().navigateTo("etextbook");
            Thread.sleep(5000);
            new UIElement().waitAndFindElement(eTextbook.link_introduction);
            eTextbook.link_introduction1.click();
            Thread.sleep(5000);
            //new UIElement().waitAndFindElement(By.xpath("//UIAStaticText[@name = 'INTRODUCTION']"));
            Assert.assertEquals(eTextbook.tab_assignments.getText(),"Assignments","Assignments tab should be displayed");
            Assert.assertEquals(eTextbook.tab_discussion.getText(),"Discussion","Discussion tab should be displayed.");
            Assert.assertEquals(eTextbook.tab_bookMark.getText(),"Add to My Notes","Bookmark tab should be displayed.");
            Assert.assertEquals(eTextbook.tab_Resources.getText(),"Resources","Resourse tab should be displayed");

            //270 - 4.Click on the Assignments tab.
            /*Expected  - "Assignments for this Chapter " static text should be shown.
            Aftre the text "View All link" will be shown.
            Below the text "name of instructor with an image icon " should be displayed.
            Static text should be displayed as "posted an assignment"
            "After the text a ""star icon"" & ""Gradable label"" should be shown.
            Assignment name should be displayed.
            Due date should be shown.
            "Status:Not started" should be shown.
            """Like & comment "" icon should be displayed.*/
            eTextbook.tab_assignments.click();
            Assert.assertEquals(eTextbook.label_assignmentsForThis.getText(), "Assignments for this", "\"Assignments for this Chapter \" static text should be shown.");

            Assert.assertEquals(eTextbook.label_studentName.getText(), "Student, iPadIntegration", "Below the text \"name of instructor with an image icon \" should be displayed.");

            Assert.assertEquals(eTextbook.label_postedAnAssignment.getText(), "posted an assignment", "Static text should be displayed as \"posted an assignment\"");

            Assert.assertEquals(eTextbook.label_gradable.getText(), "Gradable", "After the text a \"\"star icon\"\" & \"\"Gradable label\"\" should be shown.");

            Assert.assertEquals(eTextbook.icon_star.getText(), "Bookmark", "After the text a \"\"star icon\"\" & \"\"Gradable label\"\" should be shown.");

            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'shor) "+assessmentInfo.get(0)+"']")).getText(), "(shor) "+dataIndex+"_Assessment_T21Mobile_R218Generic_GenericStudentTester_visibilityOfAssignmentTabinLessonPage_"+appendChar+"", "Assignment name should be displayed.");

            Assert.assertEquals(appiumDriver.findElement(By.xpath("//UIAStaticText[@name = 'Sep 28, 2015, 12:00 AM']")).getText(),"Sep 28, 2015, 12:00 AM", "Due date should be shown.");

            Assert.assertEquals(eTextbook.label_notStarted.getText(),"Not Started", "\"Status:Not started\" should be shown.");

            Assert.assertEquals(eTextbook.label_notStarted.getText(),"Not Started", "\"Status:Not started\" should be shown.");

        }catch(Exception e){
            Assert.fail("Exception in the test method 'visibilityOfAssignmentTabinLessonPage' in the class 'GenericStudentTester'",e);
        }
    }
}
