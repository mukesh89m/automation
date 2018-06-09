package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT24.R242;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by root on 11/9/15.
 */
public class InstructorSideUnAssignAssignmentValidator extends Driver{
    @Test(priority =1,enabled = true)
    public void instructorToBeAbleToUnAssignAssignmentInPolicy1(){
        try{

            //Pre Condition - CASE 1: Instructor side--Class having multiple student, 1 student has submitted with Policy 1
            int dataIndex = 12;
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", ""+dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(dataIndex);//Create Assignment
            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1

            new LoginUsingLTI().ltiLogin("12_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("12_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("12_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Assignment().assignToStudent(dataIndex);//Assign assignment to entire class section

            new LoginUsingLTI().ltiLogin("12_1");//Login as the student1
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student1 attempt the assignment

            /*"1. Login as instructor
            2. Navigate to Current Assignment page
            3. Go to the assignment need to be un-assign"*/
            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Navigator().NavigateTo("Current Assignments");//Navigate to Current Assignments Page


            //Expected 1 - Un-assign option should be displayed below the assignment name.
            if(!driver.findElement(By.cssSelector("span[title = '(shor) "+assessmentName+"']")).isDisplayed()&&driver.findElement(By.cssSelector("span[title = 'Un-assign Assignment']")).getText().equals("| Un-assign Assignment")){
                Assert.fail("Un-assign option should be displayed below the assignment name.");
            }


            //4. Click on un-assign button
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();//Click on 'Un-assign Assignment' link
            currentAssignments.getYesOnUnAssignPopUp().click();//Click on 'Yes' button in UnAssign popup
            //Expected 2 - Assignment should get removed from Current Assignment page
            if(!(currentAssignments.getNoAssignmentMessage().getText().contains("No Assignment exists")&&driver.findElements(By.cssSelector("span[title = '(shor) "+assessmentName+"']")).size()==0)){
                Assert.fail("Assignment should get removed from Current Assignment page");
            }

        }catch(Exception e){
            Assert.fail("Exception in test method 'instructorToBeAbleToUnAssignAssignmentInPolicy1' in test class InstructorSideUnAssignAssignmentValidator",e);
        }

    }



    @Test(priority =2,enabled = true)
    public void instructorToBeAbleToUnAssignAssignmentInPolicy2(){
        try{

            //Pre Condition - CASE 2: Instructor side--Class having multiple student, 1 student has submitted with Policy 2
            int dataIndex = 15;
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", ""+dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(dataIndex);//Create Assignment
            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1

            new LoginUsingLTI().ltiLogin("15_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("15_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("15_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Assignment().assignToStudent(dataIndex);//Assign assignment to entire class section

            new LoginUsingLTI().ltiLogin("15_1");//Login as the student1
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student1 attempt the assignment

            /*"1. Login as instructor
            2. Navigate to Current Assignment page
            3. Go to the assignment need to be un-assign"*/
            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Navigator().NavigateTo("Current Assignments");//Navigate to Current Assignments Page


            //Expected 1 - Un-assign option should be displayed below the assignment name.
            if(!driver.findElement(By.cssSelector("span[title = '(shor) "+assessmentName+"']")).isDisplayed()&&driver.findElement(By.cssSelector("span[title = 'Un-assign Assignment']")).getText().equals("| Un-assign Assignment")){
                Assert.fail("Un-assign option should be displayed below the assignment name.");
            }


            //4. Click on un-assign button
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();//Click on 'Un-assign Assignment' link
            currentAssignments.getYesOnUnAssignPopUp().click();//Click on 'Yes' button in UnAssign popup
            //Expected 2 - Assignment should get removed from Current Assignment page
            if(!(currentAssignments.getNoAssignmentMessage().getText().contains("No Assignment exists")&&driver.findElements(By.cssSelector("span[title = '(shor) "+assessmentName+"']")).size()==0)){
                Assert.fail("Assignment should get removed from Current Assignment page");
            }

        }catch(Exception e){
            Assert.fail("Exception in test method 'instructorToBeAbleToUnAssignAssignmentInPolicy1' in test class InstructorSideUnAssignAssignmentValidator",e);
        }
    }


    @Test(priority =3,enabled = true)
    public void instructorToBeAbleToUnAssignAssignmentInPolicy3(){
        try{

            //Pre Condition - CASE 3: Instructor side--Class having multiple student, 1 student has submitted and the grades are released with Policy 3 [Assignment with no Manually graded question]
            int dataIndex = 18;
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", ""+dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(dataIndex);//Create Assignment
            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1

            new LoginUsingLTI().ltiLogin("18_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("18_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("18_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Assignment().assignToStudent(dataIndex);//Assign assignment to entire class section

            new LoginUsingLTI().ltiLogin("18_1");//Login as the student1
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student1 attempt the assignment

            /*"1. Login as instructor
            2. Navigate to Current Assignment page
            3. Go to the assignment need to be un-assign"*/
            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Navigator().NavigateTo("Current Assignments");//Navigate to Current Assignments Page


            //Expected 1 - Un-assign option should be displayed below the assignment name.
            if(!driver.findElement(By.cssSelector("span[title = '(shor) "+assessmentName+"']")).isDisplayed()&&driver.findElement(By.cssSelector("span[title = 'Un-assign Assignment']")).getText().equals("| Un-assign Assignment")){
                Assert.fail("Un-assign option should be displayed below the assignment name.");
            }


            //4. Click on un-assign button
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();//Click on 'Un-assign Assignment' link
            currentAssignments.getYesOnUnAssignPopUp().click();//Click on 'Yes' button in UnAssign popup
            //Expected 2 - Assignment should get removed from Current Assignment page
            if(!(currentAssignments.getNoAssignmentMessage().getText().contains("No Assignment exists")&&driver.findElements(By.cssSelector("span[title = '(shor) "+assessmentName+"']")).size()==0)){
                Assert.fail("Assignment should get removed from Current Assignment page");
            }

        }catch(Exception e){
            Assert.fail("Exception in test method 'instructorToBeAbleToUnAssignAssignmentInPolicy1' in test class InstructorSideUnAssignAssignmentValidator",e);
        }
    }


    @Test(priority =4,enabled = true)
    public void instructorToBeAbleToUnAssignAssignmentInPolicy4(){
        try{

            //Pre Condition - CASE 4: Instructor side--Class having multiple student, 1 student has submitted with Policy 4
            int dataIndex = 21;
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", ""+dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(dataIndex);//Create Assignment
            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1

            new LoginUsingLTI().ltiLogin("21_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("21_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("21_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Assignment().assignToStudent(dataIndex);//Assign assignment to entire class section

            new LoginUsingLTI().ltiLogin("21_1");//Login as the student1
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student1 attempt the assignment

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Assignment().provideGRadeToStudent(dataIndex);
            //new Assignment().releaseGrades(dataIndex,"Release Grade for All");




            /*"1. Login as instructor
            2. Navigate to Current Assignment page
            3. Go to the assignment need to be un-assign"*/
            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Navigator().NavigateTo("Current Assignments");//Navigate to Current Assignments Page


            //Expected 1 - Un-assign option should be displayed below the assignment name.
            if(!driver.findElement(By.cssSelector("span[title = '(shor) "+assessmentName+"']")).isDisplayed()&&driver.findElement(By.cssSelector("span[title = 'Un-assign Assignment']")).getText().equals("| Un-assign Assignment")){
                Assert.fail("Un-assign option should be displayed below the assignment name.");
            }


            //4. Click on un-assign button
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();//Click on 'Un-assign Assignment' link
            currentAssignments.getYesOnUnAssignPopUp().click();//Click on 'Yes' button in UnAssign popup
            //Expected 2 - Assignment should get removed from Current Assignment page
            if(!(currentAssignments.getNoAssignmentMessage().getText().contains("No Assignment exists")&&driver.findElements(By.cssSelector("span[title = '(shor) "+assessmentName+"']")).size()==0)){
                Assert.fail("Assignment should get removed from Current Assignment page");
            }

        }catch(Exception e){
            Assert.fail("Exception in test method 'instructorToBeAbleToUnAssignAssignmentInPolicy1' in test class InstructorSideUnAssignAssignmentValidator",e);
        }
    }



    @Test(priority =5,enabled = true)
    public void instructorToBeAbleToUnAssignAssignmentWhenAdaptivePracticeAttempted(){
        try{

            //Pre Condition - CASE 1: Instructor side--Class having multiple student, 1 student has submitted with Policy 1
            int dataIndex = 24;
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", ""+dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1
            new LoginUsingLTI().ltiLogin("24_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("24_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("24_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new PracticeTest().assignOrionAdaptivePractice(dataIndex, 0);
            new LoginUsingLTI().ltiLogin("24_1");//Login as the student1
            new Navigator().NavigateTo("Course Stream");
            new UIElement().waitAndFindElement(By.cssSelector("span[assignmentname='" + assessmentname + ""));
            new Click().clickBycssselector("span[assignmentname='" + assessmentname + "']");//click on Assignment
            new UIElement().waitAndFindElement(By.cssSelector("span[class='diagnostic-test-attempt-button btn btn--primary']"));
            new Click().clickBycssselector("span[class='diagnostic-test-attempt-button btn btn--primary']");
            List<WebElement> conf =  driver.findElements(By.id("1"));
            for(WebElement c : conf) {
                if(c.isDisplayed()) {
                    c.click();
                    break;
                }
            }
            driver.findElement(By.className("ls-assessment-continue-btn")).click();
            Thread.sleep(5000);
            new DiagnosticTest().attemptAllCorrect(0,false,false);
            new Navigator().NavigateTo("Course Stream");
            int helppage = driver.findElements(By.className("close-help-page")).size();
            if (helppage == 1)
                driver.findElement(By.className("close-help-page")).click();
            new Click().clickBycssselector("span[assignmentname='" + assessmentname + "']");//click on Assignment
            Thread.sleep(2000);

            for(int i = 1;i<= 20;i++){
                if(driver.findElements(By.className("preview-multiple-select-answer-choice")).size()>0)
                    new AttemptDiagnosticQuestion().attemptMultipleSelection("correct", false, false, "1", "");
                else if(driver.findElements(By.className("preview-single-selection-answer-choices")).size() > 0)
                    new AttemptDiagnosticQuestion().attemptMultipleChoice("correct", false, false, "1", "");

                else if(driver.findElements(By.className("true-false-answer-choices")).size() > 0)
                    new AttemptDiagnosticQuestion().attemptTrueFalse("correct", false, false, "1", "");
                driver.findElement(By.id("1")).click();
                try {
                    driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).click();
                }catch(Exception e){
                    driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button adaptive-assignment-finish-button']")).click();

                }
            }
            new Click().clickbyxpath("//div[@id = 'al-notification-dialog']//span");




            /*"1. Login as instructor
            2. Navigate to Current Assignment page
            3. Go to the assignment need to be un-assign"*/
            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Navigator().NavigateTo("Current Assignments");//Navigate to Current Assignments Page


            //Expected 1 - Un-assign option should be displayed below the assignment name.
            if(!driver.findElement(By.cssSelector("span[title = '(shor) "+assessmentName+"']")).isDisplayed()&&driver.findElement(By.cssSelector("span[title = 'Un-assign Assignment']")).getText().equals("| Un-assign Assignment")){
                Assert.fail("Un-assign option should be displayed below the assignment name.");
            }


            //4. Click on un-assign button
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();//Click on 'Un-assign Assignment' link
            currentAssignments.getYesOnUnAssignPopUp().click();//Click on 'Yes' button in UnAssign popup
            //Expected 2 - Assignment should get removed from Current Assignment page
            if(!(currentAssignments.getNoAssignmentMessage().getText().contains("You have not created any assignments yet.")&&driver.findElements(By.cssSelector("span[title = '(shor) "+assessmentName+"']")).size()==0)){
                Assert.fail("Assignment should get removed from Current Assignment page");
            }

        }catch(Exception e){
            Assert.fail("Exception in test method 'instructorToBeAbleToUnAssignAssignmentWhenDWAttempted' in test class InstructorSideUnAssignAssignmentValidator",e);
        }

    }



    @Test(priority =6,enabled = true)
    public void instructorToBeAbleToUnAssignAssignmentWhenDWAttempted(){
        try{

            //Pre Condition - CASE 1: Instructor side--Class having multiple student, 1 student has submitted with Policy 1
            int dataIndex = 27;
            String assessmentName =  "D1 - What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?";
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1
            new LoginUsingLTI().ltiLogin("27_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("27_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("27_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidget(""+dataIndex);



            new LoginUsingLTI().ltiLogin("27_1");//Create an studentnew Navigator().NavigateTo("Assignments");	//navigate to Assignment
            new Navigator().NavigateTo("Assignments");
            new Click().clickByclassname("learning-activity-title"); //click on DW assignment
            String perspective = new RandomString().randomstring(10);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignemnt

            /*"1. Login as instructor
            2. Navigate to Current Assignment page
            3. Go to the assignment need to be un-assign"*/
            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Navigator().NavigateTo("Current Assignments");//Navigate to Current Assignments Page


            //Expected 1 - Un-assign option should be displayed below the assignment name.
            if(!driver.findElement(By.cssSelector("span[title = '"+assessmentName+"']")).isDisplayed()&&driver.findElement(By.cssSelector("span[title = 'Un-assign Assignment']")).getText().equals("| Un-assign Assignment")){
                Assert.fail("Un-assign option should be displayed below the assignment name.");
            }


            //4. Click on un-assign button
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();//Click on 'Un-assign Assignment' link
            currentAssignments.getYesOnUnAssignPopUp().click();//Click on 'Yes' button in UnAssign popup
            //Expected 2 - Assignment should get removed from Current Assignment page
            if(!(currentAssignments.getNoAssignmentMessage().getText().contains("No Assignment exists")&&driver.findElements(By.cssSelector("span[title = '(shor) "+assessmentName+"']")).size()==0)){
                Assert.fail("Assignment should get removed from Current Assignment page");
            }
        }catch(Exception e){
            Assert.fail("Exception in test method 'validateUnAssignOptionInStudentSideInAssignmentInPolicy1' in test class InstructorSideUnAssignAssignmentValidator",e);
        }

    }



    @Test(priority =7,enabled = true)
    public void instructorNotToBeAbleToUnAssignAssignmentInPolicy1(){
        try{

            //Pre Condition - CASE 7: Instructor side--Class having multiple student, All student has submitted with Policy 1
            int dataIndex = 30;
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", ""+dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(dataIndex);//Create Assignment
            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1

            new LoginUsingLTI().ltiLogin("30_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("30_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("30_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Assignment().assignToStudent(dataIndex);//Assign assignment to entire class section

            new LoginUsingLTI().ltiLogin("30_1");//Login as the student1
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student1 attempt the assignment

            new LoginUsingLTI().ltiLogin("30_2");//Login as the student2
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student2 attempt the assignment

            new LoginUsingLTI().ltiLogin("30_3");//Login as the student3
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student3 attempt the assignment


            /*"1. Login as instructor
            2. Navigate to Current Assignment page
            3. Go to the assignment need to be un-assign"*/
            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Navigator().NavigateTo("Current Assignments");//Navigate to Current Assignments Page


            //Expected 1 - Un-assign option should not be displayed below the assignment name.
            if(driver.findElements(By.cssSelector("span[title = 'Un-assign Assignment']")).size()!=0){
                Assert.fail("Un-assign option should not be displayed below the assignment name.");
            }
        }catch(Exception e){
            Assert.fail("Exception in test method 'instructorNotToBeAbleToUnAssignAssignmentInPolicy1' in test class InstructorSideUnAssignAssignmentValidator",e);
        }

    }



    @Test(priority =8,enabled = true)
    public void instructorNotToBeAbleToUnAssignAssignmentInPolicy2(){
        try{

            //Pre Condition - CASE 8: Instructor side--Class having multiple student, All student has submitted with Policy 2
            int dataIndex = 32;
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", ""+dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(dataIndex);//Create Assignment
            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1

            new LoginUsingLTI().ltiLogin("32_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("32_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("32_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Assignment().assignToStudent(dataIndex);//Assign assignment to entire class section

            new LoginUsingLTI().ltiLogin("32_1");//Login as the student1
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student1 attempt the assignment

            new LoginUsingLTI().ltiLogin("32_2");//Login as the student2
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student2 attempt the assignment

            new LoginUsingLTI().ltiLogin("32_3");//Login as the student3
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student3 attempt the assignment


            /*"1. Login as instructor
            2. Navigate to Current Assignment page
            3. Go to the assignment need to be un-assign"*/
            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Navigator().NavigateTo("Current Assignments");//Navigate to Current Assignments Page


            //Expected 1 - Un-assign option should not be displayed below the assignment name.
            if(driver.findElements(By.cssSelector("span[title = 'Un-assign Assignment']")).size()!=0){
                Assert.fail("Un-assign option should not be displayed below the assignment name.");
            }
        }catch(Exception e){
            Assert.fail("Exception in test method 'instructorNotToBeAbleToUnAssignAssignmentInPolicy2' in test class InstructorSideUnAssignAssignmentValidator",e);
        }
    }


    @Test(priority =9,enabled = true)
    public void instructorNotToBeAbleToUnAssignAssignmentInPolicy3(){
        try{

            //Pre Condition - CASE 9: Instructor side--Class having multiple student, All student has submitted with Policy 3 [Assignment with no Manually graded question]
            int dataIndex = 34;
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", ""+dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(dataIndex);//Create Assignment
            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1

            new LoginUsingLTI().ltiLogin("34_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("34_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("34_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Assignment().assignToStudent(dataIndex);//Assign assignment to entire class section

            new LoginUsingLTI().ltiLogin("34_1");//Login as the student1
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student1 attempt the assignment

            new LoginUsingLTI().ltiLogin("34_2");//Login as the student1
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student1 attempt the assignment

            new LoginUsingLTI().ltiLogin("34_3");//Login as the student2
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student2 attempt the assignment


            /*"1. Login as instructor
            2. Navigate to Current Assignment page
            3. Go to the assignment need to be un-assign"*/
            new LoginUsingLTI().ltiLogin("" + dataIndex);//Login as instructor1
            new Navigator().NavigateTo("Current Assignments");//Navigate to Current Assignments Page


            //Expected 1 - Un-assign option should not be displayed below the assignment name.
            if(driver.findElements(By.cssSelector("span[title = 'Un-assign Assignment']")).size()!=0){
                Assert.fail("Un-assign option should not be displayed below the assignment name.");
            }

        }catch(Exception e){
            Assert.fail("Exception in test method 'instructorNotToBeAbleToUnAssignAssignmentInPolicy3' in test class InstructorSideUnAssignAssignmentValidator",e);
        }
    }

    @Test(priority =10,enabled = true)
    public void instructorNotToBeAbleToUnAssignAssignmentInPolicy4(){
        try{

            //Pre Condition - CASE 10: Instructor side--Class having multiple student, All student has submitted with Policy 4
            int dataIndex = 36;
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", ""+dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(dataIndex);//Create Assignment
            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1

            new LoginUsingLTI().ltiLogin("36_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("36_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("36_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Assignment().assignToStudent(dataIndex);//Assign assignment to entire class section

            new LoginUsingLTI().ltiLogin("36_1");//Login as the student1
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student1 attempt the assignment

            new LoginUsingLTI().ltiLogin("36_2");//Login as the student2
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student2 attempt the assignment

            new LoginUsingLTI().ltiLogin("36_3");//Login as the student3
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student3 attempt the assignment

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Assignment().provideGRadeToStudent(dataIndex);

           /*"1. Login as instructor
            2. Navigate to Current Assignment page
            3. Go to the assignment need to be un-assign"*/
            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Navigator().NavigateTo("Current Assignments");//Navigate to Current Assignments Page


            //Expected 1 - Un-assign option should not be displayed below the assignment name.
            if(driver.findElements(By.cssSelector("span[title = 'Un-assign Assignment']")).size()!=0){
                Assert.fail("Un-assign option should not be displayed below the assignment name.");
            }


        }catch(Exception e){
            Assert.fail("Exception in test method 'instructorNotToBeAbleToUnAssignAssignmentInPolicy4' in test class InstructorSideUnAssignAssignmentValidator",e);
        }
    }


}
