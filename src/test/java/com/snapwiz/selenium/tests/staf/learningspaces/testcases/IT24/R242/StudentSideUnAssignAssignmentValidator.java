package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT24.R242;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.MostChallengingReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Dharaneesha on 11/12/15.
 */
public class StudentSideUnAssignAssignmentValidator extends Driver{
    public static WebDriver webDriver;
    /*This Use case deals with - As an instructor/mentor, I should be able to un-assign an assignment even after
    grades are released till either due date expires or all students have submitted the assignment*/

    @Test(priority =1,enabled = true)
        public void instructorToBeAbleToUnAssignAssignmentInPolicy1(){
        try{

            //Pre Condition - CASE 1: Student side-- Class having multiple student, 1 student has submitted and the grades are released with Policy 1
            /*"1.Instructor should have assigned a Gradable/Gradable with policy/Non-Gradable  Assignment.
            2.Atleast One student should have submitted the assignment. and grades are released for the same"*/


            int dataIndex = 45;
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", ""+dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MostChallengingReport mostChallengingReport = PageFactory.initElements(driver, MostChallengingReport.class);

            new Assignment().create(dataIndex);//Create Assignment
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");

            new Assignment().create(dataIndex+1);//Create Assignment
            new Assignment().addQuestions(dataIndex+1,"truefalse","");
            new Assignment().addQuestions(dataIndex+1,"truefalse","");

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1

            new LoginUsingLTI().ltiLogin("45_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("45_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("45_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Assignment().assignToStudent(dataIndex);//Assign assignment 1 to entire class section
            new Assignment().assignToStudent(dataIndex+1);//Assign assignment 2 to entire class section


            new LoginUsingLTI().ltiLogin("45_1");//Login as the student1
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student1 attempt the assignment 1

            new Assignment().submitAssignmentAsStudent(dataIndex+1);//Let Student1 attempt the assignment 2
            unAssignAssignment(""+dataIndex);//Let Instructor unAssign the assignment
            validateStudSideFeatures(""+dataIndex);

        }catch(Exception e){
            Assert.fail("Exception in test method 'instructorToBeAbleToUnAssignAssignmentInPolicy1' in test class InstructorSideUnAssignAssignmentValidator",e);
        }

    }



    @Test(priority =2,enabled = true)
    public void instructorToBeAbleToUnAssignAssignmentInPolicy2(){
        try{

            //Pre Condition - CASE 2: Instructor side--Class having multiple student, 1 student has submitted with Policy 2
            int dataIndex = 87;
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", ""+dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(dataIndex);//Create Assignment
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");

            new Assignment().create(dataIndex+1);//Create Assignment
            new Assignment().addQuestions(dataIndex+1,"truefalse","");
            new Assignment().addQuestions(dataIndex+1,"truefalse","");

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1

            new LoginUsingLTI().ltiLogin("87_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("87_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("87_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Assignment().assignToStudent(dataIndex);//Assign assignment 1 to entire class section
            new Assignment().assignToStudent(dataIndex+1);//Assign assignment 2 to entire class section


            new LoginUsingLTI().ltiLogin("87_1");//Login as the student1
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student1 attempt the assignment 1

            new Assignment().submitAssignmentAsStudent(dataIndex+1);//Let Student1 attempt the assignment 2
            unAssignAssignment(""+dataIndex);
            validateStudSideFeatures(""+dataIndex);

        }catch(Exception e){
            Assert.fail("Exception in test method 'instructorToBeAbleToUnAssignAssignmentInPolicy2' in test class InstructorSideUnAssignAssignmentValidator",e);
        }
    }
    @Test(priority =3,enabled = true)
    public void instructorToBeAbleToUnAssignAssignmentInPolicy3(){
        try{

            //Pre Condition - CASE 3: Instructor side--Class having multiple student, 1 student has submitted and the grades are released with Policy 3 [Assignment with no Manually graded question]
            int dataIndex = 129;
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", ""+dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(dataIndex);//Create Assignment
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");

            new Assignment().create(dataIndex+1);//Create Assignment
            new Assignment().addQuestions(dataIndex+1,"truefalse","");
            new Assignment().addQuestions(dataIndex+1,"truefalse","");

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1

            new LoginUsingLTI().ltiLogin("129_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("129_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("129_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Assignment().assignToStudent(dataIndex);//Assign assignment 1 to entire class section
            new Assignment().assignToStudent(dataIndex+1);//Assign assignment 2 to entire class section


            new LoginUsingLTI().ltiLogin("129_1");//Login as the student1
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student1 attempt the assignment 1

            new Assignment().submitAssignmentAsStudent(dataIndex+1);//Let Student1 attempt the assignment 2
            unAssignAssignment(""+dataIndex);
            validateStudSideFeatures(""+dataIndex);
        }catch(Exception e){
            Assert.fail("Exception in test method 'instructorToBeAbleToUnAssignAssignmentInPolicy3' in test class InstructorSideUnAssignAssignmentValidator",e);
        }
    }


    @Test(priority =4,enabled = true)
    public void instructorToBeAbleToUnAssignAssignmentInPolicy4(){
        try{

            //Pre Condition - CASE 4: Instructor side--Class having multiple student, 1 student has submitted with Policy 4
            int dataIndex = 172;
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", ""+dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(dataIndex);//Create Assignment
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");

            new Assignment().create(dataIndex+1);//Create Assignment
            new Assignment().addQuestions(dataIndex+1,"truefalse","");
            new Assignment().addQuestions(dataIndex+1,"truefalse","");

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1

            new LoginUsingLTI().ltiLogin("172_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("172_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("172_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Assignment().assignToStudent(dataIndex);//Assign assignment 1 to entire class section
            new Assignment().assignToStudent(dataIndex+1);//Assign assignment 2 to entire class section


            new LoginUsingLTI().ltiLogin("172_1");//Login as the student1
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student1 attempt the assignment 1

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1
            new Assignment().provideGRadeToStudent(dataIndex);

            new Assignment().submitAssignmentAsStudent(dataIndex+1);//Let Student1 attempt the assignment 2
            unAssignAssignment(""+dataIndex);
            validateStudSideFeatures(""+dataIndex);
        }catch(Exception e){
            Assert.fail("Exception in test method 'instructorToBeAbleToUnAssignAssignmentInPolicy4' in test class InstructorSideUnAssignAssignmentValidator",e);
        }
    }


    @Test(priority =5,enabled = true)
    public void instructorToBeAbleToUnAssignAssignmentWhenAdaptivePracticeAttempted(){
        try{

            //Pre Condition - CASE 1: Instructor side--Class having multiple student, 1 student has submitted with Policy 1
            int dataIndex = 213;
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", ""+dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1
            new LoginUsingLTI().ltiLogin("213_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("213_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("213_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new PracticeTest().assignOrionAdaptivePractice(dataIndex, 0);// Assign Adaptive Practice Test 1
            new PracticeTest().assignOrionAdaptivePractice(dataIndex, 1);// Assign Adaptive Practice Test 2

            new LoginUsingLTI().ltiLogin("213_1");//Login as the student1
            attemptPracticeAssignment(dataIndex);
            attemptPracticeAssignment(dataIndex+1);
            unAssignAssignment(""+dataIndex,"ORION Ch 2: The Chemical Foundation of Life");
            validateStudSideFeaturesForAdapPractice(""+dataIndex,"ORION Ch 2: The Chemical Foundation of Life");

        }catch(Exception e){
            Assert.fail("Exception in test method 'instructorToBeAbleToUnAssignAssignmentWhenDWAttempted' in test class InstructorSideUnAssignAssignmentValidator",e);
        }
    }

    @Test(priority =6,enabled = true)
    public void instructorToBeAbleToUnAssignAssignmentWhenDWAttempted(){
        try{
            //Pre Condition - CASE 6: Student side-- Learning Activity Assignment/DW--Class having multiple student, 1 has submitted
            int dataIndex = 255;
            String assessmentName =  "D1 - What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?";
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1
            new LoginUsingLTI().ltiLogin("255_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("255_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("255_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1
            new Navigator().NavigateTo("eTextbook");
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidget(""+dataIndex);

            new LoginUsingLTI().ltiLogin("255_1");//Create an studentnew Navigator().NavigateTo("Assignments");
            new Navigator().NavigateTo("Assignments");
            new Click().clickByclassname("learning-activity-title"); //click on DW assignment
            String perspective = new RandomString().randomstring(10);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignemnt*/
            unAssignDWAssignment(""+dataIndex,"D1 - What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?");
            validateStudSideFeaturesForDW(""+dataIndex,"D1 - What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?");
        }catch(Exception e){
            Assert.fail("Exception in test method 'validateUnAssignOptionInStudentSideInAssignmentInPolicy1' in test class InstructorSideUnAssignAssignmentValidator",e);
        }

    }

    @Test(priority =6,enabled = true)
    public void instructorToBeAbleToUnAssignAssignmentWhenStudentInProgress(){
        try{

            //Pre Condition - CASE 2: Instructor side--Class having multiple student, 1 student has submitted with Policy 2
            int dataIndex = 278;
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", ""+dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);

            new Assignment().create(dataIndex);//Create Assignment
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");
            new Assignment().addQuestions(dataIndex,"truefalse","");

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Create an instructor1
            new LoginUsingLTI().ltiLogin("278_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("278_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("278_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Assignment().assignToStudent(dataIndex);//Assign assignment to entire class section*/

            ltiLogin("278_1");//Login as the student1
            Thread.sleep(5000);
            WebDriverWait wait = new WebDriverWait(webDriver,30);
            wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(".//*[@class='ls-site-nav-drop-down']/a"))));
            webDriver.findElement(By.xpath(".//*[@class='ls-site-nav-drop-down']/a")).click();
            wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.linkText("Assignments"))));
            webDriver.findElement(By.linkText("Assignments")).click();//click on Assignments
            webDriver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();//open lesson assignment
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            trueFalse(false, "incorrect", dataIndex);
            webDriver.findElement(By.xpath("//a[contains(@class,'btn btn--primary btn--large')]")).click();//open lesson assignment
            trueFalse(false, "incorrect", dataIndex);
            webDriver.findElement(By.xpath("//a[contains(@class,'btn btn--primary btn--large')]")).click();//open lesson assignment
            unAssignAssignment(""+dataIndex);
        }catch(Exception e){
            Assert.fail("Exception in test method 'instructorToBeAbleToUnAssignAssignmentWhenStudentInProgress' in test class InstructorSideUnAssignAssignmentValidator",e);
        }
    }

    public void validateStudSideFeatures(String dataIndex, String ... assessmentname){
        try{
            String assessmentName;
            if(assessmentname.length==0){
                assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            }else{
                assessmentName = assessmentname[0];
            }
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MostChallengingReport mostChallengingReport = PageFactory.initElements(driver, MostChallengingReport.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);



            //unAssignAssignment(dataIndex);//Let Instructor unAssign the assignment

            //Row No - 48 ; 3.Login as a Student and navigate to Assignments page.
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");//Create a student1 in the same class section

            String actualTimeSpent = dashboard.timeSpentTile.getText().trim();
            new Navigator().NavigateTo("Assignments");


            //Expected - 1 : The Assignment entry should be removed in the Assignment page.
            if(driver.findElements(By.cssSelector("span[title = '(shor)  "+assessmentName+"']")).size()!=0){
                Assert.fail("The Assignment entry should be removed in the Assignment page");
            }



            //Expected - 2 : The number count in Submitted tile should get updated with less count
            //Assert.assertEquals(assignments.gradedCount.getText(),"1","The number count in Submitted tile should get updated with less count");
            if(!driver.findElement(By.className("ls-graded-count")).getText().equals("1")){
                Assert.fail("The number count in Submitted tile should get updated with less count");
            }



            //Expected - 3 : The Percentage in the Overall Score tab should be updated accordingly
            Assert.assertEquals(assignments.overAllScore.getText(),"Overall Score: 67%","The Percentage in the Overall Score tab should be updated accordingly");


            /*Row No - 52 : "4. Navigate to e-Textbook from main navigator dropdown
            5. Select any chapter having its TLOs linked to the assignment
            6. Select the Assignment tab in right side column"*/
            new Navigator().NavigateTo("e-Textbook");

            //Row No - 54 : 7.Navigate to Study Plan and select that particular chapter to which the assignment was associated.
            //Expected - The assignment entry should be removed under that particular chapter in study plan.
            if(driver.findElements(By.cssSelector("a[title = '"+assessmentName+"']")).size()!=0){
                Assert.fail("The assignment entry should be removed under that particular chapter in study plan.");
            }

            WebElement we=driver.findElement(By.linkText("1.1: The Science of Biology"));;
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
            new Click().clickBycssselector("span[title = 'Assignments']");

            //Expected - The assignment entry in the assignment tab should be removed for that chapter.
            if(driver.findElements(By.linkText("(shor) "+assessmentName+"")).size()!=0){
                Assert.fail("The assignment entry in the assignment tab should be removed for that chapter.");
            }

            //Row No - 56 : 8.Navigate to Course stream Page.
            //Expected - The assignment card should be removed from the Course stream page.
            new Navigator().NavigateTo("Course Stream");
            Thread.sleep(1000);
            if(driver.findElements(By.cssSelector("span[assignmentname = '"+assessmentName+"']")).size()!=0){
                Assert.fail("The assignment card should be removed from the Course stream page.");
            }


            //Row No - 57 : Student should have liked/ added comments in the assignment
            //Expected -1 : Entry for 'like' should be removed
            if(courseStreamPage.likeLinkIn_CSPage.size()>1){
                Assert.fail("Entry for 'like' should be removed");
            }

            //Expected - 2 : Entry for 'comment' should be removed
            if(courseStreamPage.commentLinkIn_CSPage.size()>1){
                Assert.fail("Entry for 'comment' should be removed");
            }


            //Row No - 6 : 9.Navigate to "My Activity" page.
            //Expected - The assignment should NOT be present in "My Activity" page even if the assignment was Submitted/Attempted.
            new Navigator().NavigateTo("My Activity");
            Thread.sleep(1000);
            if(driver.findElements(By.linkText("\""+assessmentName+"\"")).size()!=0){
                Assert.fail("The assignment should NOT be present in \"My Activity\" page even if the assignment was Submitted/Attempted.");
            }


            /*Row No - 62 : "10.Navigate to Dashboard.
            11.Verify the ""Recent Activities"" section."*/
            //Expected - The Assignment entry should be removed even if the student has worked on last  TWO Activities for that assignment.
            new Navigator().NavigateTo("Dashboard");
            Thread.sleep(1000);
            if(driver.findElements(By.partialLinkText(assessmentName)).size()!=0){
                Assert.fail("The Assignment entry should be removed even if the student has worked on last  TWO Activities for that assignment.");
            }

            //12.Verify the "Graded Assignments" Section on Dashboard.
            //Expected 1- Overall score tile should get updated
            Assert.assertEquals(driver.findElement(By.id("highcharts-2")).getText().trim(),"Overall Score67%","Overall score tile should get updated");

            //Expected -2 : Recently graded tile should get updated
            Assert.assertEquals(proficiencyReport.getBarChart().isDisplayed(),true,"Recently graded tile should get updated");
            Assert.assertEquals(proficiencyReport.getBarChart().getAttribute("height"),"85","Recently graded tile should get updated");


            //Expected - 3 : Upcoming assignment tile should get updated
            Assert.assertEquals(dashboard.upcomingValue.getText(),"0","Upcoming assignment tile should get updated");



            //13.Verify the Course Stream section on Dashboard.
            //Expected - The assignment entry should be removed even if it is part of the last 3 activities.
            if(driver.findElements(By.xpath("//div[@class='middle']//p[contains(text(),'(shor) "+assessmentName+"')]")).size()!=0){
                Assert.fail("The assignment entry should be removed even if it is part of the last 3 activities.");
            }

            //14.Verify the "Question Attempted" Tile on Dashboard.
            //Expected - There should be NO change in the "Question Attempted" tile.
            Assert.assertEquals(dashboard.totalNumberOfQuestionAttempted.getText().trim(),"3","There should be NO change in the \"Question Attempted\" tile.");


            //15.Verify the "Question Performance" tile on Dashboard.
            //Expected - There should be NO change in the "Question Performance" tile.
            Assert.assertEquals(dashboard.questionPerformanceInPercentage.getText().trim(),"67\n"+"%","There should be NO change in the \"Question Attempted\" tile.");


            //16.Verify the "Participation Rating" tile on Dashboard.
            //Expected -There should be NO change in the "Participation Rating" tile.
            Assert.assertEquals(dashboard.participationRatingInPercentage.getText().trim(),"0\n"+"%","There should be NO change in the \"Participation Rating\" tile.");

            //17.Verify the "Time Spent" tile on Dashboard.
            //Expected - There should be NO change in the "Time Spent" tile.
            Assert.assertEquals(dashboard.timeSpentTile.getText().trim(),actualTimeSpent,"There should be NO change in the \"Participation Rating\" tile.");



           /*"18.Navigate to ""My Reports"" page and select ""Proficiency Report"".
            19.Verify the Course Proficiency Summary."*/
            //Expected 1- Questions card entry should be removed from the question cards in the right side column
            new Navigator().NavigateTo("Proficiency Report");
            new UIElement().waitAndFindElement(proficiencyReport.getBarChart());
            if(driver.findElements(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']")).size()>3){
                Assert.fail("Questions card entry should be removed from the question cards in the right side column");
            }


            //Expected - 2 ; Assignment entry as bars on the Course Proficiency By Chapter column should  not be removed
            Assert.assertEquals(proficiencyReport.getBarChart().isDisplayed(),true,"Assignment entry as bars on the Course Proficiency By Chapter column should  not be removed");
            Assert.assertEquals(proficiencyReport.getBarChart().getAttribute("height"),"132","Assignment entry as bars on the Course Proficiency By Chapter column should  not be removed");


            //Expected - 3 : Course Proficiency Summary should not get updated as per the removed assignment contribution
            Assert.assertEquals(proficiencyReport.proficiencySummaryValue.getText().trim(),"95%","Assignment entry as bars on the Course Proficiency By Chapter column should  not be removed");


            /*Row No - 75 : "20.Click on the chapter bar in Course Proficiency by Chapter section of Proficiency Report.
            21.Verify Chapter proficiency summary."*/
            //Expected - 1 : Questions card entry should be removed from the question cards in the right side column
            proficiencyReport.getBarChart().click();
            new UIElement().waitAndFindElement(proficiencyReport.getBarChart());
            if(driver.findElements(By.xpath("//div[contains(@id,'question_card_id_')]")).size()!=3){
                Assert.fail("Questions card entry should be removed from the question cards in the right side column");
            }



            //If the the only assignment is associated at the objective level

            //Expected -1 : Assignment entry as bars on the Chapter Proficiency By Objectives column should be not removed
            Assert.assertEquals(proficiencyReport.getBarChart().isDisplayed(),true,"Assignment entry as bars on the Chapter Proficiency By Objectives column should be not removed");
            Assert.assertEquals(proficiencyReport.getBarChart().getAttribute("height"),"133","Assignment entry as bars on the Chapter Proficiency By Objectives column should be not removed");


            //Expected - 2 : Chapter Proficiency Summary should not get updated as per the removed assignment contribution
            Assert.assertEquals(proficiencyReport.proficiencySummaryValue.getText().trim(),"94%","Assignment entry as bars on the Course Proficiency By Chapter column should  not be removed");


            //If more than one assignment as associated with the single objective
            //Row No - 78 : "22.Click on graph in Chapter Proficiency by Objectives.
            //Expected -1 : Questions card entry should be removed from the question cards in the right side column
            proficiencyReport.getBarChart().click();
            if(driver.findElements(By.xpath("//div[contains(@id,'question_card_id_')]")).size()!=3){
                Assert.fail("Questions card entry should be removed from the question cards in the right side column");
            }


            //Row No - 79 ; 23.Click on objective bar in Objective Proficiency by Questions.
            //Expected - 1 : Question entry as bars on the Objective Proficiency by Questions column should not be removed

            List<WebElement> rectElementList = driver.findElements(By.cssSelector("g.highcharts-series.highcharts-tracker > rect"));
            if(rectElementList.size()!=3){
                Assert.fail("Question entry as bars on the Objective Proficiency by Questions column should not be removed");
            }

            //Expected -2 : Objective Proficiency Summary should not get updated as per the removed assignment contribution
            Assert.assertEquals(proficiencyReport.proficiencySummaryValue.getText().trim(),"95%","Objective Proficiency Summary should not get updated as per the removed assignment contribution");

            /*"24.Navigate to ""Most Challenging Activities Report"".
            25.Click on ""View By Chapters"" tab."*/
            //Expected - 1: Proficiency value for the respective chapter should not get updated as per the removed assignment contribution
            new Navigator().NavigateTo("Most Challenging Activities Report");
            Assert.assertEquals(mostChallengingReport.getStudChapProficiency().get(0).getText().trim(),"94%","Proficiency value for the respective chapter should not get updated as per the removed assignment contribution");


            //Expected - 2: Numerator contribution of the assignment in the Performance value should get updated.
            //Expected - 3 : Denominator contribution of the assignment in the Performance value should get updated.
            Assert.assertEquals(mostChallengingReport.getStudChapPerformance().get(0).getText().trim(),"17/18","Numerator & Denominator contribution of the assignment in the Performance value should get updated.");

            //26.Click on "View by Objective" tab
            mostChallengingReport.getStudViewObjective_Tab().click();
            //Expected -1 : Proficiency value for the respective chapter should not get updated as per the removed assignment contribution
            Assert.assertEquals(mostChallengingReport.getStudChapProficiency().get(1).getText().trim(),"95%","Proficiency value for the respective chapter should not get updated as per the removed assignment contribution");

            //Expected - 2: Numerator contribution of the assignment in the Performance value should get updated.
            //Expected - 3 : Denominator contribution of the assignment in the Performance value should get updated.
            Assert.assertEquals(mostChallengingReport.getStudChapPerformance().get(1).getText().trim(),"2/3","Numerator & Denominator contribution of the assignment in the Performance value should get updated.");

        }catch(Exception e){
            Assert.fail("Exception in Method 'doSomething' in class 'StudentSideUnAssignAssignmentValidator'",e);
        }
    }




    public void validateStudSideFeaturesForDW(String dataIndex, String ... assessmentname){
        try{
            String assessmentName;
            if(assessmentname.length==0){
                assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            }else{
                assessmentName = assessmentname[0];
            }
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MostChallengingReport mostChallengingReport = PageFactory.initElements(driver, MostChallengingReport.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);



            //unAssignAssignment(dataIndex);//Let Instructor unAssign the assignment

            //Row No - 48 ; 3.Login as a Student and navigate to Assignments page.
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");//Create a student1 in the same class section

            String actualTimeSpent = dashboard.timeSpentTile.getText().trim();
            new Navigator().NavigateTo("Assignments");


            //Expected - 1 : The Assignment entry should be removed in the Assignment page.
            if(driver.findElements(By.cssSelector("span[title = '"+assessmentName+"']")).size()!=0){
                Assert.fail("The Assignment entry should be removed in the Assignment page");
            }



            //Expected - 2 : The number count in Submitted tile should get updated with less count
            //Assert.assertEquals(assignments.gradedCount.getText(),"1","The number count in Submitted tile should get updated with less count");
            if(!driver.findElement(By.className("ls-graded-count")).getText().equals("1")){
                Assert.fail("The number count in Submitted tile should get updated with less count");
            }



            //Expected - 3 : The Percentage in the Overall Score tab should be updated accordingly
            Assert.assertEquals(assignments.overAllScore.getText(),"Overall Score: 67%","The Percentage in the Overall Score tab should be updated accordingly");


            /*Row No - 52 : "4. Navigate to e-Textbook from main navigator dropdown
            5. Select any chapter having its TLOs linked to the assignment
            6. Select the Assignment tab in right side column"*/
            new Navigator().NavigateTo("e-Textbook");

            //Row No - 54 : 7.Navigate to Study Plan and select that particular chapter to which the assignment was associated.
            //Expected - The assignment entry should be removed under that particular chapter in study plan.
            if(driver.findElements(By.cssSelector("a[title = '"+assessmentName+"']")).size()!=0){
                Assert.fail("The assignment entry should be removed under that particular chapter in study plan.");
            }

            WebElement we=driver.findElement(By.linkText("1.1: The Science of Biology"));;
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
            new Click().clickBycssselector("span[title = 'Assignments']");

            //Expected - The assignment entry in the assignment tab should be removed for that chapter.
            if(driver.findElements(By.linkText(""+assessmentName+"")).size()!=0){
                Assert.fail("The assignment entry in the assignment tab should be removed for that chapter.");
            }

            //Row No - 56 : 8.Navigate to Course stream Page.
            //Expected - The assignment card should be removed from the Course stream page.
            new Navigator().NavigateTo("Course Stream");
            Thread.sleep(1000);
            if(driver.findElements(By.cssSelector("span[assignmentname = '"+assessmentName+"']")).size()!=0){
                Assert.fail("The assignment card should be removed from the Course stream page.");
            }


            //Row No - 57 : Student should have liked/ added comments in the assignment
            //Expected -1 : Entry for 'like' should be removed
            if(courseStreamPage.likeLinkIn_CSPage.size()>1){
                Assert.fail("Entry for 'like' should be removed");
            }

            //Expected - 2 : Entry for 'comment' should be removed
            if(courseStreamPage.commentLinkIn_CSPage.size()>1){
                Assert.fail("Entry for 'comment' should be removed");
            }


            //Row No - 6 : 9.Navigate to "My Activity" page.
            //Expected - The assignment should NOT be present in "My Activity" page even if the assignment was Submitted/Attempted.
            new Navigator().NavigateTo("My Activity");
            Thread.sleep(1000);
            if(driver.findElements(By.linkText("\""+assessmentName+"\"")).size()!=0){
                Assert.fail("The assignment should NOT be present in \"My Activity\" page even if the assignment was Submitted/Attempted.");
            }


            /*Row No - 62 : "10.Navigate to Dashboard.
            11.Verify the ""Recent Activities"" section."*/
            //Expected - The Assignment entry should be removed even if the student has worked on last  TWO Activities for that assignment.
            new Navigator().NavigateTo("Dashboard");
            Thread.sleep(1000);
            if(driver.findElements(By.partialLinkText(assessmentName)).size()!=0){
                Assert.fail("The Assignment entry should be removed even if the student has worked on last  TWO Activities for that assignment.");
            }

            //12.Verify the "Graded Assignments" Section on Dashboard.
            //Expected 1- Overall score tile should get updated
            Assert.assertEquals(driver.findElement(By.id("highcharts-2")).getText().trim(),"Overall Score67%","Overall score tile should get updated");

            //Expected -2 : Recently graded tile should get updated
            Assert.assertEquals(proficiencyReport.getBarChart().isDisplayed(),true,"Recently graded tile should get updated");
            Assert.assertEquals(proficiencyReport.getBarChart().getAttribute("height"),"85","Recently graded tile should get updated");


            //Expected - 3 : Upcoming assignment tile should get updated
            Assert.assertEquals(dashboard.upcomingValue.getText(),"0","Upcoming assignment tile should get updated");



            //13.Verify the Course Stream section on Dashboard.
            //Expected - The assignment entry should be removed even if it is part of the last 3 activities.
            if(driver.findElements(By.xpath("//div[@class='middle']//p[contains(text(),'"+assessmentName+"')]")).size()!=0){
                Assert.fail("The assignment entry should be removed even if it is part of the last 3 activities.");
            }

            //14.Verify the "Question Attempted" Tile on Dashboard.
            //Expected - There should be NO change in the "Question Attempted" tile.
            Assert.assertEquals(dashboard.totalNumberOfQuestionAttempted.getText().trim(),"3","There should be NO change in the \"Question Attempted\" tile.");


            //15.Verify the "Question Performance" tile on Dashboard.
            //Expected - There should be NO change in the "Question Performance" tile.
            Assert.assertEquals(dashboard.questionPerformanceInPercentage.getText().trim(),"67\n"+"%","There should be NO change in the \"Question Attempted\" tile.");


            //16.Verify the "Participation Rating" tile on Dashboard.
            //Expected -There should be NO change in the "Participation Rating" tile.
            Assert.assertEquals(dashboard.participationRatingInPercentage.getText().trim(),"0\n"+"%","There should be NO change in the \"Participation Rating\" tile.");

            //17.Verify the "Time Spent" tile on Dashboard.
            //Expected - There should be NO change in the "Time Spent" tile.
            Assert.assertEquals(dashboard.timeSpentTile.getText().trim(),actualTimeSpent,"There should be NO change in the \"Participation Rating\" tile.");



           /*"18.Navigate to ""My Reports"" page and select ""Proficiency Report"".
            19.Verify the Course Proficiency Summary."*/
            //Expected 1- Questions card entry should be removed from the question cards in the right side column
            new Navigator().NavigateTo("Proficiency Report");
            new UIElement().waitAndFindElement(proficiencyReport.getBarChart());
            if(driver.findElements(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']")).size()>3){
                Assert.fail("Questions card entry should be removed from the question cards in the right side column");
            }


            //Expected - 2 ; Assignment entry as bars on the Course Proficiency By Chapter column should  not be removed
            Assert.assertEquals(proficiencyReport.getBarChart().isDisplayed(),true,"Assignment entry as bars on the Course Proficiency By Chapter column should  not be removed");
            Assert.assertEquals(proficiencyReport.getBarChart().getAttribute("height"),"132","Assignment entry as bars on the Course Proficiency By Chapter column should  not be removed");


            //Expected - 3 : Course Proficiency Summary should not get updated as per the removed assignment contribution
            Assert.assertEquals(proficiencyReport.proficiencySummaryValue.getText().trim(),"95%","Assignment entry as bars on the Course Proficiency By Chapter column should  not be removed");


            /*Row No - 75 : "20.Click on the chapter bar in Course Proficiency by Chapter section of Proficiency Report.
            21.Verify Chapter proficiency summary."*/
            //Expected - 1 : Questions card entry should be removed from the question cards in the right side column
            proficiencyReport.getBarChart().click();
            new UIElement().waitAndFindElement(proficiencyReport.getBarChart());
            if(driver.findElements(By.xpath("//div[contains(@id,'question_card_id_')]")).size()!=3){
                Assert.fail("Questions card entry should be removed from the question cards in the right side column");
            }



            //If the the only assignment is associated at the objective level

            //Expected -1 : Assignment entry as bars on the Chapter Proficiency By Objectives column should be not removed
            Assert.assertEquals(proficiencyReport.getBarChart().isDisplayed(),true,"Assignment entry as bars on the Chapter Proficiency By Objectives column should be not removed");
            Assert.assertEquals(proficiencyReport.getBarChart().getAttribute("height"),"133","Assignment entry as bars on the Chapter Proficiency By Objectives column should be not removed");


            //Expected - 2 : Chapter Proficiency Summary should not get updated as per the removed assignment contribution
            Assert.assertEquals(proficiencyReport.proficiencySummaryValue.getText().trim(),"94%","Assignment entry as bars on the Course Proficiency By Chapter column should  not be removed");


            //If more than one assignment as associated with the single objective
            //Row No - 78 : "22.Click on graph in Chapter Proficiency by Objectives.
            //Expected -1 : Questions card entry should be removed from the question cards in the right side column
            proficiencyReport.getBarChart().click();
            if(driver.findElements(By.xpath("//div[contains(@id,'question_card_id_')]")).size()!=3){
                Assert.fail("Questions card entry should be removed from the question cards in the right side column");
            }


            //Row No - 79 ; 23.Click on objective bar in Objective Proficiency by Questions.
            //Expected - 1 : Question entry as bars on the Objective Proficiency by Questions column should not be removed

            List<WebElement> rectElementList = driver.findElements(By.cssSelector("g.highcharts-series.highcharts-tracker > rect"));
            if(rectElementList.size()!=3){
                Assert.fail("Question entry as bars on the Objective Proficiency by Questions column should not be removed");
            }

            //Expected -2 : Objective Proficiency Summary should not get updated as per the removed assignment contribution
            Assert.assertEquals(proficiencyReport.proficiencySummaryValue.getText().trim(),"95%","Objective Proficiency Summary should not get updated as per the removed assignment contribution");

            /*"24.Navigate to ""Most Challenging Activities Report"".
            25.Click on ""View By Chapters"" tab."*/
            //Expected - 1: Proficiency value for the respective chapter should not get updated as per the removed assignment contribution
            new Navigator().NavigateTo("Most Challenging Activities Report");
            Assert.assertEquals(mostChallengingReport.getStudChapProficiency().get(0).getText().trim(),"94%","Proficiency value for the respective chapter should not get updated as per the removed assignment contribution");


            //Expected - 2: Numerator contribution of the assignment in the Performance value should get updated.
            //Expected - 3 : Denominator contribution of the assignment in the Performance value should get updated.
            Assert.assertEquals(mostChallengingReport.getStudChapPerformance().get(0).getText().trim(),"17/18","Numerator & Denominator contribution of the assignment in the Performance value should get updated.");

            //26.Click on "View by Objective" tab
            mostChallengingReport.getStudViewObjective_Tab().click();
            //Expected -1 : Proficiency value for the respective chapter should not get updated as per the removed assignment contribution
            Assert.assertEquals(mostChallengingReport.getStudChapProficiency().get(1).getText().trim(),"95%","Proficiency value for the respective chapter should not get updated as per the removed assignment contribution");

            //Expected - 2: Numerator contribution of the assignment in the Performance value should get updated.
            //Expected - 3 : Denominator contribution of the assignment in the Performance value should get updated.
            Assert.assertEquals(mostChallengingReport.getStudChapPerformance().get(1).getText().trim(),"2/3","Numerator & Denominator contribution of the assignment in the Performance value should get updated.");

        }catch(Exception e){
            Assert.fail("Exception in Method 'doSomething' in class 'StudentSideUnAssignAssignmentValidator'",e);
        }
    }
    public void validateStudSideFeaturesForAdapPractice(String dataIndex, String ... assessmentname){
        try{
            String assessmentName;
            if(assessmentname.length==0){
                assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            }else{
                assessmentName = assessmentname[0];
            }
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MostChallengingReport mostChallengingReport = PageFactory.initElements(driver, MostChallengingReport.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);



            //unAssignAssignment(dataIndex);//Let Instructor unAssign the assignment

            //Row No - 48 ; 3.Login as a Student and navigate to Assignments page.
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");//Create a student1 in the same class section

            String actualTimeSpent = dashboard.timeSpentTile.getText().trim();
            new Navigator().NavigateTo("Assignments");


            //Expected - 1 : The Assignment entry should be removed in the Assignment page.
            if(driver.findElements(By.cssSelector("span[title = '(shor)  "+assessmentName+"']")).size()!=0){
                Assert.fail("The Assignment entry should be removed in the Assignment page");
            }



            //Expected - 2 : The number count in Submitted tile should get updated with less count
            //Assert.assertEquals(assignments.gradedCount.getText(),"1","The number count in Submitted tile should get updated with less count");
            if(!driver.findElement(By.className("ls-graded-count")).getText().equals("1")){
                Assert.fail("The number count in Submitted tile should get updated with less count");
            }



            //Expected - 3 : The Percentage in the Overall Score tab should be updated accordingly
            Assert.assertEquals(assignments.overAllScore.getText(),"Overall Score: 32%","The Percentage in the Overall Score tab should be updated accordingly");


            /*Row No - 52 : "4. Navigate to e-Textbook from main navigator dropdown
            5. Select any chapter having its TLOs linked to the assignment
            6. Select the Assignment tab in right side column"*/
            new Navigator().NavigateTo("e-Textbook");

            //Row No - 54 : 7.Navigate to Study Plan and select that particular chapter to which the assignment was associated.
            //Expected - The assignment entry should be removed under that particular chapter in study plan.
            if(driver.findElements(By.cssSelector("a[title = '"+assessmentName+"']")).size()!=0){
                Assert.fail("The assignment entry should be removed under that particular chapter in study plan.");
            }

            WebElement we=driver.findElement(By.linkText("1.1: The Science of Biology"));;
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
            new Click().clickBycssselector("span[title = 'Assignments']");

            //Expected - The assignment entry in the assignment tab should be removed for that chapter.
            if(driver.findElements(By.linkText("(shor) "+assessmentName+"")).size()!=0){
                Assert.fail("The assignment entry in the assignment tab should be removed for that chapter.");
            }

            //Row No - 56 : 8.Navigate to Course stream Page.
            //Expected - The assignment card should be removed from the Course stream page.
            new Navigator().NavigateTo("Course Stream");
            Thread.sleep(1000);
            if(driver.findElements(By.cssSelector("span[assignmentname = '"+assessmentName+"']")).size()!=0){
                Assert.fail("The assignment card should be removed from the Course stream page.");
            }


            //Row No - 57 : Student should have liked/ added comments in the assignment
            //Expected -1 : Entry for 'like' should be removed
            if(courseStreamPage.likeLinkIn_CSPage.size()>1){
                Assert.fail("Entry for 'like' should be removed");
            }

            //Expected - 2 : Entry for 'comment' should be removed
            if(courseStreamPage.commentLinkIn_CSPage.size()>1){
                Assert.fail("Entry for 'comment' should be removed");
            }


            //Row No - 6 : 9.Navigate to "My Activity" page.
            //Expected - The assignment should NOT be present in "My Activity" page even if the assignment was Submitted/Attempted.
            new Navigator().NavigateTo("My Activity");
            Thread.sleep(1000);
            if(driver.findElements(By.linkText("\""+assessmentName+"\"")).size()!=0){
                Assert.fail("The assignment should NOT be present in \"My Activity\" page even if the assignment was Submitted/Attempted.");
            }


            /*Row No - 62 : "10.Navigate to Dashboard.
            11.Verify the ""Recent Activities"" section."*/
            //Expected - The Assignment entry should be removed even if the student has worked on last  TWO Activities for that assignment.
            new Navigator().NavigateTo("Dashboard");
            Thread.sleep(1000);
            if(driver.findElements(By.partialLinkText(assessmentName)).size()!=0){
                Assert.fail("The Assignment entry should be removed even if the student has worked on last  TWO Activities for that assignment.");
            }

            //12.Verify the "Graded Assignments" Section on Dashboard.
            //Expected 1- Overall score tile should get updated
            Assert.assertEquals(driver.findElement(By.id("highcharts-2")).getText().trim(),"Overall Score32%","Overall score tile should get updated");

            //Expected -2 : Recently graded tile should get updated
            Assert.assertEquals(proficiencyReport.getBarChart().isDisplayed(),true,"Recently graded tile should get updated");
            Assert.assertEquals(proficiencyReport.getBarChart().getAttribute("height"),"41","Recently graded tile should get updated");


            //Expected - 3 : Upcoming assignment tile should get updated
            Assert.assertEquals(dashboard.upcomingValue.getText(),"0","Upcoming assignment tile should get updated");



            //13.Verify the Course Stream section on Dashboard.
            //Expected - The assignment entry should be removed even if it is part of the last 3 activities.
            if(driver.findElements(By.xpath("//div[@class='middle']//p[contains(text(),'(shor) "+assessmentName+"')]")).size()!=0){
                Assert.fail("The assignment entry should be removed even if it is part of the last 3 activities.");
            }

            //14.Verify the "Question Attempted" Tile on Dashboard.
            //Expected - There should be NO change in the "Question Attempted" tile.
            Assert.assertEquals(dashboard.totalNumberOfQuestionAttempted.getText().trim(),"76","There should be NO change in the \"Question Attempted\" tile.");


            //15.Verify the "Question Performance" tile on Dashboard.
            //Expected - There should be NO change in the "Question Performance" tile.
            Assert.assertEquals(dashboard.questionPerformanceInPercentage.getText().trim(),"27\n"+"%","There should be NO change in the \"Question Attempted\" tile.");


            //16.Verify the "Participation Rating" tile on Dashboard.
            //Expected -There should be NO change in the "Participation Rating" tile.
            Assert.assertEquals(dashboard.participationRatingInPercentage.getText().trim(),"0\n"+"%","There should be NO change in the \"Participation Rating\" tile.");

            //17.Verify the "Time Spent" tile on Dashboard.
            //Expected - There should be NO change in the "Time Spent" tile.
            Assert.assertEquals(dashboard.timeSpentTile.getText().trim(),actualTimeSpent,"There should be NO change in the \"Participation Rating\" tile.");



           /*"18.Navigate to ""My Reports"" page and select ""Proficiency Report"".
            19.Verify the Course Proficiency Summary."*/
            //Expected 1- Questions card entry should be removed from the question cards in the right side column
            new Navigator().NavigateTo("Proficiency Report");
            new UIElement().waitAndFindElement(proficiencyReport.getBarChart());
            if(driver.findElements(By.cssSelector("div[class='report-sidebar-question-card-sectn question-card-green']")).size()>3){
                Assert.fail("Questions card entry should be removed from the question cards in the right side column");
            }


            //Expected - 2 ; Assignment entry as bars on the Course Proficiency By Chapter column should  not be removed
            Assert.assertEquals(proficiencyReport.getBarChart().isDisplayed(),true,"Assignment entry as bars on the Course Proficiency By Chapter column should  not be removed");
            Assert.assertEquals(proficiencyReport.getBarChart().getAttribute("height"),"45","Assignment entry as bars on the Course Proficiency By Chapter column should  not be removed");


            //Expected - 3 : Course Proficiency Summary should not get updated as per the removed assignment contribution
            Assert.assertEquals(proficiencyReport.proficiencySummaryValue.getText().trim(),"31%","Assignment entry as bars on the Course Proficiency By Chapter column should  not be removed");


            /*Row No - 75 : "20.Click on the chapter bar in Course Proficiency by Chapter section of Proficiency Report.
            21.Verify Chapter proficiency summary."*/
            //Expected - 1 : Questions card entry should be removed from the question cards in the right side column
            proficiencyReport.getBarChart().click();
            new UIElement().waitAndFindElement(proficiencyReport.getBarChart());
            //Assert.assertEquals(driver.findElements(By.xpath("//div[@class='al-performance-report-sidebar-content']//div[contains(@id,'question_card_id_')]")).size(),60,"Questions card entry should be removed from the question cards in the right side column");




            //If the the only assignment is associated at the objective level

            //Expected -1 : Assignment entry as bars on the Chapter Proficiency By Objectives column should be not removed
            Assert.assertEquals(proficiencyReport.getBarChart().isDisplayed(),true,"Assignment entry as bars on the Chapter Proficiency By Objectives column should be not removed");
            Assert.assertEquals(proficiencyReport.getBarChart().getAttribute("height"),"45","Assignment entry as bars on the Chapter Proficiency By Objectives column should be not removed");


            //Expected - 2 : Chapter Proficiency Summary should not get updated as per the removed assignment contribution
            Assert.assertEquals(proficiencyReport.proficiencySummaryValue.getText().trim(),"32%","Assignment entry as bars on the Course Proficiency By Chapter column should  not be removed");


            //If more than one assignment as associated with the single objective
            //Row No - 78 : "22.Click on graph in Chapter Proficiency by Objectives.
            //Expected -1 : Questions card entry should be removed from the question cards in the right side column
            proficiencyReport.getBarChart().click();
            /*if(driver.findElements(By.xpath("//div[contains(@id,'question_card_id_')]")).size()!=3){
                Assert.fail("Questions card entry should be removed from the question cards in the right side column");
            }*/


            //Row No - 79 ; 23.Click on objective bar in Objective Proficiency by Questions.
            //Expected - 1 : Question entry as bars on the Objective Proficiency by Questions column should not be removed

            /*List<WebElement> rectElementList = driver.findElements(By.cssSelector("g.highcharts-series.highcharts-tracker > rect"));
            Assert.assertEquals(rectElementList.size(),13,"Question entry as bars on the Objective Proficiency by Questions column should not be removed");
*/
            /*if(rectElementList.size()!=3){
                Assert.fail("Question entry as bars on the Objective Proficiency by Questions column should not be removed");
            }*/

            //Expected -2 : Objective Proficiency Summary should not get updated as per the removed assignment contribution
            Assert.assertEquals(proficiencyReport.proficiencySummaryValue.getText().trim(),"32%","Objective Proficiency Summary should not get updated as per the removed assignment contribution");

            /*"24.Navigate to ""Most Challenging Activities Report"".
            25.Click on ""View By Chapters"" tab."*/
            //Expected - 1: Proficiency value for the respective chapter should not get updated as per the removed assignment contribution
            new Navigator().NavigateTo("Most Challenging Activities Report");
            Assert.assertEquals(mostChallengingReport.getStudChapProficiency().get(0).getText().trim(),"31%","Proficiency value for the respective chapter should not get updated as per the removed assignment contribution");
            Assert.assertEquals(mostChallengingReport.getStudChapProficiency().get(1).getText().trim(),"32%","Proficiency value for the respective chapter should not get updated as per the removed assignment contribution");


            //Expected - 2: Numerator contribution of the assignment in the Performance value should get updated.
            //Expected - 3 : Denominator contribution of the assignment in the Performance value should get updated.

            Assert.assertEquals(mostChallengingReport.getStudChapPerformance().get(0).getText().trim(),"12/40","Numerator & Denominator contribution of the assignment in the Performance value should get updated.");
            Assert.assertEquals(mostChallengingReport.getStudChapPerformance().get(1).getText().trim(),"13/56","Numerator & Denominator contribution of the assignment in the Performance value should get updated.");





            //26.Click on "View by Objective" tab
            mostChallengingReport.getStudViewObjective_Tab().click();
            //Expected -1 : Proficiency value for the respective chapter should not get updated as per the removed assignment contribution
            Assert.assertEquals(mostChallengingReport.getStudChapProficiency().get(2).getText().trim(),"21%","Proficiency value for the respective chapter should not get updated as per the removed assignment contribution");
            Assert.assertEquals(mostChallengingReport.getStudChapProficiency().get(3).getText().trim(),"27%","Proficiency value for the respective chapter should not get updated as per the removed assignment contribution");
            Assert.assertEquals(mostChallengingReport.getStudChapProficiency().get(4).getText().trim(),"32%","Proficiency value for the respective chapter should not get updated as per the removed assignment contribution");
            Assert.assertEquals(mostChallengingReport.getStudChapProficiency().get(5).getText().trim(),"33%","Proficiency value for the respective chapter should not get updated as per the removed assignment contribution");
            Assert.assertEquals(mostChallengingReport.getStudChapProficiency().get(6).getText().trim(),"34%","Proficiency value for the respective chapter should not get updated as per the removed assignment contribution");
            Assert.assertEquals(mostChallengingReport.getStudChapProficiency().get(7).getText().trim(),"36%","Proficiency value for the respective chapter should not get updated as per the removed assignment contribution");
            Assert.assertEquals(mostChallengingReport.getStudChapProficiency().get(8).getText().trim(),"41%","Proficiency value for the respective chapter should not get updated as per the removed assignment contribution");

            //Expected - 2: Numerator contribution of the assignment in the Performance value should get updated.
            //Expected - 3 : Denominator contribution of the assignment in the Performance value should get updated.
            Assert.assertEquals(mostChallengingReport.getStudChapPerformance().get(2).getText().trim(),"1/7","Numerator & Denominator contribution of the assignment in the Performance value should get updated.");
            Assert.assertEquals(mostChallengingReport.getStudChapPerformance().get(3).getText().trim(),"5/21","Numerator & Denominator contribution of the assignment in the Performance value should get updated.");
            Assert.assertEquals(mostChallengingReport.getStudChapPerformance().get(4).getText().trim(),"5/13","Numerator & Denominator contribution of the assignment in the Performance value should get updated.");
            Assert.assertEquals(mostChallengingReport.getStudChapPerformance().get(5).getText().trim(),"3/7","Numerator & Denominator contribution of the assignment in the Performance value should get updated.");
            Assert.assertEquals(mostChallengingReport.getStudChapPerformance().get(6).getText().trim(),"0/4","Numerator & Denominator contribution of the assignment in the Performance value should get updated.");
            Assert.assertEquals(mostChallengingReport.getStudChapPerformance().get(7).getText().trim(),"1/2","Numerator & Denominator contribution of the assignment in the Performance value should get updated.");
            Assert.assertEquals(mostChallengingReport.getStudChapPerformance().get(8).getText().trim(),"3/6","Numerator & Denominator contribution of the assignment in the Performance value should get updated.");

        }catch(Exception e){
            Assert.fail("Exception in Method 'doSomething' in class 'StudentSideUnAssignAssignmentValidator'",e);
        }
    }

    public void unAssignAssignment(String dataIndex, String ... assessmentname){
        try{
            String assessmentName;
            if(assessmentname.length==0){
                assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            }else{
                assessmentName = assessmentname[0];
            }
            System.out.println("assessmentName 2: " + assessmentName);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MostChallengingReport mostChallengingReport = PageFactory.initElements(driver, MostChallengingReport.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);



            /*Row No - 46 : "1.Login as a Instructor and navigate to Current Assignments page.
            2.Click on ""Un-assign assignment""."*/
            //Expected - Instructor should be able to un-assign the assignment

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Navigator().NavigateTo("Current Assignments");//Navigate to Current Assignments Page
            System.out.println("Assessment Name : " + assessmentName);
            if(!driver.findElement(By.cssSelector("span[title = '(shor) "+assessmentName+"']")).isDisplayed()&&driver.findElement(By.cssSelector("span[title = 'Un-assign Assignment']")).getText().equals("| Un-assign Assignment")){
                Assert.fail("Un-assign option should be displayed below the assignment name.");
            }
            List<WebElement> assessmentList = driver.findElements(By.cssSelector("span[class = 'ls-assignment-name instructor-assessment-review']"));
            System.out.println("assessmentList : " + assessmentList.size());
            for(int a=0;a<assessmentList.size();a++){
                if(assessmentList.get(a).getText().trim().equals("(shor) "+assessmentName+"")){
                    System.out.println("Yes: " +a);
                    driver.findElements(By.className("delete-assigned-task")).get(a).click();
                    Thread.sleep(2000);
                    break;
                }
            }
            currentAssignments.getYesOnUnAssignPopUp().click();//Click on 'Yes' button in UnAssign popup*//**//*

            new UIElement().waitTillInvisibleElement(By.cssSelector("span[title = '(shor) "+assessmentName+"']"));
            if(driver.findElements(By.cssSelector("span[title = '(shor) "+assessmentName+"']")).size()!=0){
                Assert.fail("Assignment should get removed from Current Assignment page");
            }
        }catch(Exception e){
            Assert.fail("Exception in method 'unAssignAssignment' in class 'StudentSideUnAssignAssignmentValidator'",e);
        }
    }


    public void unAssignDWAssignment(String dataIndex, String ... assessmentname){
        try{
            String assessmentName;
            if(assessmentname.length==0){
                assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            }else{
                assessmentName = assessmentname[0];
            }
            System.out.println("assessmentName 2: " + assessmentName);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MostChallengingReport mostChallengingReport = PageFactory.initElements(driver, MostChallengingReport.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);



            /*Row No - 46 : "1.Login as a Instructor and navigate to Current Assignments page.
            2.Click on ""Un-assign assignment""."*/
            //Expected - Instructor should be able to un-assign the assignment

            new LoginUsingLTI().ltiLogin(""+dataIndex);//Login as instructor1
            new Navigator().NavigateTo("Current Assignments");//Navigate to Current Assignments Page
            System.out.println("Assessment Name : " + assessmentName);
            /*if(!driver.findElement(By.cssSelector("span[title = '"+assessmentName+"']")).isDisplayed()&&driver.findElement(By.cssSelector("span[title = 'Un-assign Assignment']")).getText().equals("| Un-assign Assignment")){
                Assert.fail("Un-assign option should be displayed below the assignment name.");
            }
            List<WebElement> assessmentList = driver.findElements(By.cssSelector("span[class = 'ls-assignment-name instructor-assessment-review']"));
            System.out.println("assessmentList : " + assessmentList.size());
            for(int a=0;a<assessmentList.size();a++){
                if(assessmentList.get(a).getText().trim().equals(""+assessmentName+"")){
                    System.out.println("Yes: " +a);
                    driver.findElements(By.className("delete-assigned-task")).get(a).click();
                    Thread.sleep(2000);
                    break;
                }
            }
            currentAssignments.getYesOnUnAssignPopUp().click();//Click on 'Yes' button in UnAssign popup*//**//**//**//*

            new UIElement().waitTillInvisibleElement(By.cssSelector("span[title = '"+assessmentName+"']"));
            if(driver.findElements(By.cssSelector("span[title = '"+assessmentName+"']")).size()!=0){
                Assert.fail("Assignment should get removed from Current Assignment page");
            }*/
        }catch(Exception e){
            Assert.fail("Exception in method 'unAssignAssignment' in class 'StudentSideUnAssignAssignmentValidator'",e);
        }
    }
    public void attemptPracticeAssignment(int dataIndex){//Student Attempts Diag Test & then he attempts practice Test
        try{
            String assessmentname =  ReadTestData.readDataByTagName("", "assessmentname", ""+dataIndex);
            new Navigator().NavigateTo("Course Stream");
            Thread.sleep(9000);
            new UIElement().waitAndFindElement(By.cssSelector("span[assignmentname='" + assessmentname + ""));
            new Click().clickBycssselector("span[assignmentname='" + assessmentname + "']");//click on Assignment

            //new Click().clickbyxpath("span[assignmentname='" + assessmentname + "']");//click on Assignment

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


        }catch(Exception e){
            Assert.fail("Exception in Test method 'attemptPracticeAssignment' in class 'StudentSideUnAssignAssignmentValidator'",e);
        }
    }

    public void ltiLogin(String dataIndex)
    {

        String appendChar = System.getProperty("UCHAR");
        String user_id =  ReadTestData.readDataByTagName("", "user_id", dataIndex);
        String role =  ReadTestData.readDataByTagName("", "Role", dataIndex);
        String context_id = ReadTestData.readDataByTagName("", "context_id", dataIndex);
        String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
        String custom_courseid = ReadTestData.readDataByTagName("", "custom_courseid", dataIndex);
        String custom_destination = ReadTestData.readDataByTagName("", "custom_destination", dataIndex);
        String resource_link_id = ReadTestData.readDataByTagName("", "resource_link_id", dataIndex);
        String expectederror = ReadTestData.readDataByTagName("", "expectederror", dataIndex);
        String custom_course_number = ReadTestData.readDataByTagName("", "custom_course_number", dataIndex);
        String familyname =  ReadTestData.readDataByTagName("", "familyname", dataIndex);
        String givenname = ReadTestData.readDataByTagName("", "givenname", dataIndex);
        String custom_domain_name = ReadTestData.readDataByTagName("", "custom_domain_name", dataIndex);
        String custom_instructor_classlist = ReadTestData.readDataByTagName("", "custom_instructor_classlist", dataIndex);
        String course_type = ReadTestData.readDataByTagName("", "course_type", dataIndex);  // valid values - 'ls', 'adaptive'.
        String instance_guid = ReadTestData.readDataByTagName("", "instance_guid", dataIndex);  // valid values - 'ls', 'adaptive'.
        String custom_domainid = ReadTestData.readDataByTagName("", "custom_domainid", dataIndex);
        String custom_roster_changes = ReadTestData.readDataByTagName("", "custom_roster_changes", dataIndex);
        String launchURL = ReadTestData.readDataByTagName("", "launchURL", dataIndex);


        try
        {
            if(appendChar == null ) {
                appendChar = "yyy";

            }
            webDriver = new FirefoxDriver();
            webDriver.manage().window().maximize();
            //webDriver = webDriver;
            webDriver.get(Config.baseLTIURL + "/");

            webDriver.findElement(By.name("endpoint")).clear(); //Clear fields
            waitAndFindElement(By.name("endpoint"));

            if(launchURL == null) {
                webDriver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);
            }
            else {
                webDriver.findElement(By.name("endpoint")).sendKeys(launchURL);
            }

            webDriver.findElement(By.name("key")).clear();
            waitAndFindElement(By.name("key"));
            webDriver.findElement(By.name("key")).sendKeys(Config.customerkey);

            webDriver.findElement(By.name("secret")).clear();
            waitAndFindElement(By.name("secret"));
            webDriver.findElement(By.name("secret")).sendKeys(Config.secretkey);

            webDriver.findElement(By.name("resource_link_id")).clear();
            if(resource_link_id == null)
                webDriver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);
            else
                webDriver.findElement(By.name("resource_link_id")).sendKeys(resource_link_id);

            webDriver.findElement(By.name("user_id")).clear();
            waitAndFindElement(By.name("user_id"));
            webDriver.findElement(By.name("user_id")).sendKeys(user_id+appendChar);

            webDriver.findElement(By.name("roles")).clear();
            waitAndFindElement(By.name("roles"));
            webDriver.findElement(By.name("roles")).sendKeys(role);

            webDriver.findElement(By.name("lis_person_name_family")).clear();
            if(familyname == null)
                webDriver.findElement(By.name("lis_person_name_family")).sendKeys(Config.familyname);
            else
                webDriver.findElement(By.name("lis_person_name_family")).sendKeys(familyname);

            webDriver.findElement(By.name("lis_person_name_given")).clear();

            if(givenname == null)
                webDriver.findElement(By.name("lis_person_name_given")).sendKeys(Config.givenname);
            else
                webDriver.findElement(By.name("lis_person_name_given")).sendKeys(givenname);

            webDriver.findElement(By.name("lis_person_contact_email_primary")).clear();
            waitAndFindElement(By.name("lis_person_contact_email_primary"));
            webDriver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);

            webDriver.findElement(By.name("context_id")).clear();
            if(context_id == null)
                webDriver.findElement(By.name("context_id")).sendKeys(Config.context_id+appendChar);
            else
                webDriver.findElement(By.name("context_id")).sendKeys(context_id+appendChar);

            webDriver.findElement(By.name("context_title")).clear();
            if(context_title == null)
                webDriver.findElement(By.name("context_title")).sendKeys(Config.context_title);
            else
                webDriver.findElement(By.name("context_title")).sendKeys(context_title);

            webDriver.findElement(By.name("tool_consumer_instance_guid")).clear();
            if(instance_guid == null)
                webDriver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);
            else
                webDriver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(instance_guid);

            webDriver.findElement(By.name("tool_consumer_instance_name")).clear();
            waitAndFindElement(By.name("tool_consumer_instance_name"));
            webDriver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);

            webDriver.findElement(By.name("custom_courseid")).clear();
            if(custom_courseid == null)
            {
                if(course_type == null)
                    webDriver.findElement(By.name("custom_courseid")).sendKeys(Config.courseid);
                else if(course_type.equalsIgnoreCase("ls"))
                    webDriver.findElement(By.name("custom_courseid")).sendKeys(Config.lsCourseId);
                else if(course_type.equalsIgnoreCase("adaptive"))
                    webDriver.findElement(By.name("custom_courseid")).sendKeys(Config.adaptiveCourseID);
            }
            else
                webDriver.findElement(By.name("custom_courseid")).sendKeys(custom_courseid);

            webDriver.findElement(By.name("custom_roster_changes")).clear();
            if(custom_roster_changes != null){
                webDriver.findElement(By.name("custom_roster_changes")).sendKeys(custom_roster_changes);

            }

            webDriver.findElement(By.name("custom_destination")).clear();
            if(custom_destination == null)
                webDriver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination);
            else
                webDriver.findElement(By.name("custom_destination")).sendKeys(custom_destination);

            if(custom_domainid == null){
                webDriver.findElement(By.name("custom_domainid")).clear();
                waitAndFindElement(By.name("custom_domainid"));
                webDriver.findElement(By.name("custom_domainid")).sendKeys(Config.custom_domainid+appendChar);
            }else
                webDriver.findElement(By.name("custom_domainid")).sendKeys(custom_domainid+appendChar);

            webDriver.findElement(By.name("custom_course_number")).clear();
            if(custom_course_number == null)
                webDriver.findElement(By.name("custom_course_number")).sendKeys(Config.custom_course_number);
            else
                webDriver.findElement(By.name("custom_course_number")).sendKeys(custom_course_number);

            if(custom_domain_name == null)
                webDriver.findElement(By.name("custom_domain_name")).sendKeys(Config.custom_domain_name+appendChar);
            else
                webDriver.findElement(By.name("custom_domain_name")).sendKeys(custom_domain_name+appendChar);
            if(custom_instructor_classlist == null)
                webDriver.findElement(By.name("custom_instructor_classlist")).sendKeys(Config.custom_instructor_classlist);
            else
                webDriver.findElement(By.name("custom_instructor_classlist")).sendKeys(custom_instructor_classlist);

            webDriver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
            webDriver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();

            try {
                String unExpected=webDriver.switchTo().alert().getText();
                System.out.println("unExpected Error:"+unExpected);
                webDriver.switchTo().alert().accept();
            } catch (Exception e) {
            }

            //new HelpDisable().helpDisable(user_id+appendChar);
            //Thread.sleep(3000);
            if(expectederror == null)
            {
                boolean textPresent = new TextValidate().IsTextPresent("Something went wrong in processing your request.");
                boolean woopsieText = new TextValidate().IsTextPresent("We encountered a problem while processing your request.");

                if(textPresent == true || woopsieText ==true)
                {
                    //webDriver.quit();
                    Assert.fail("LTI login with course id "+Config.courseid+" Failed");
                }

            }
            if(Config.browser.equals("ie"))
                webDriver.navigate().refresh();
            if(!Config.browser.equalsIgnoreCase("firefox")) {
                if(webDriver.findElements(By.className("swhelp-button-cancel")).size() > 0)
                    webDriver.findElement(By.className("swhelp-button-cancel")).click();
            /*if(webDriver.findElements(By.className("swhelp-button-cancel-info")).size() > 0)
                webDriver.findElement(By.className("swhelp-button-cancel-info")).click();*/
                //Thread.sleep(5000);
            }
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromAppHelper();
            //webDriver.quit();
            Assert.fail("Exception in LoginUsingLTI Application Helper",e);

        }

    }

    public void trueFalse(boolean useHint, String answerChoice, int dataIndex)
    {
        try
        {
            (new WebDriverWait(webDriver, 400))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='true']/table/tbody/tr/td[2]/div/span")));
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct"))
                {
                   clickbyxpath(".//*[@id='true']/table/tbody/tr/td[2]/div/span");//click on A

                }
                if (answerChoice.equalsIgnoreCase("incorrect"))
                {
                    clickbyxpath(".//*[@id='false']/table/tbody/tr/td[2]/div/span");//click on B
                }
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method trueFalse.", e);
        }
    }

    public void clickbyxpath(String xpath)
    {
        try
        {
            WebElement we=webDriver.findElement(By.xpath(xpath));
            ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",we);
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            //new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in apphelper clickbyxpath in class Click",e);
        }
    }


    public WebElement waitAndFindElement(final By by) {

        WebElement element =  new WebDriverWait(webDriver,30)
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver webDriver) {
                        return webDriver.findElement(by);
                    }
                });
        return element;
    }

}
