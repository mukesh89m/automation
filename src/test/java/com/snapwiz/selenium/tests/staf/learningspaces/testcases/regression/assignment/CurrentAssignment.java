package com.snapwiz.selenium.tests.staf.learningspaces.testcases.regression.assignment;

import com.snapwiz.selenium.tests.staf.learningspaces.CustomAssert;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentsDetails;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

public class CurrentAssignment extends Driver{



    /*"As a new instructor, I should be able to see the label "" No Assignment exists. + New Assignment
     "" in current assignments page & also I should be able to navigate to question banks by clicking
    ""New Assignment"" feature in right above corner in current assignments page"*/
    @Test(priority=1,enabled=true)
    public void checkContentsOfCurrentAssignmentPageBeforeInstructorAssigningAssignment()
    {
        try
        {
            String dataIndex = "1";
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new Assignment().create(Integer.parseInt(dataIndex));
            log("Description", "Validating the contents of Current Assignments Page before Assigning assignment", "info");
            new LoginUsingLTI().ltiLogin(dataIndex); //Creating user Instructor
            log("Step 1","Instructor is logged in","info");
            new Navigator().NavigateTo("Assignments");
            log("Step 2","Navigated to Current Assignments Page","info");


            //Checking Current Assignments tab
            String assignmentTab = currentAssignments.tab_currentAssignments.getText();
            if(!assignmentTab.trim().equals("Current Assignments"))
            {
                Assert.fail("On Clicking on 'Current Assignment' button its not taking to Current Assignments Tab");
            }
            log("Assertion 1", "On Clicking on 'Current Assignment' button its not taking to Current Assignments Tab", "pass");


            //Checking the content of Current Assignment Page before assignment
            String noAssignment = currentAssignments.noAssignmentExit.getText();
            String newAssignment = currentAssignments.button_createNewAssignment.getText();
            if(!noAssignment.trim().equals("No Assignment exists.") || !newAssignment.trim().equals("+ New Assignment")){
                Assert.fail("For a new instructor empty page is NOT present with message 'No Assignment exists' and '+ New Assignment' ");
            }
            log("Assertion 2","For a new instructor empty page is NOT present with message 'No Assignment exists' and '+ New Assignment'","pass");


            //Checking Question Banks Tab Navigation from New Assignment button
            currentAssignments.button_NewAssignment.click();
            log("Step 3","New Assignment' button is clicked","info");
            currentAssignments.getUsePreCreatedAssignment_button().click();
            log("Step 4","Clicked on 'Use Pre Created Assignment' button","info");
            String questionBanksTab = currentAssignments.tab_QuestionBanks.getText();
            if(!questionBanksTab.trim().equals("Question Banks"))
            {
                Assert.fail("On Clicking on 'Use Pre Created Assignment' button, its not taking to Question Banks Tab");
            }
            log("Assertion 3", "Clicking on 'Use Pre Created Assignment' button, its not taking to Question Banks Tab", "pass");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase actionOverAssignment in class ActionOverAssignments",e);
        }
    }



    /*As In instructor When I assign an assignment to a student or class section,
    I should be able to see the assignment with the basic details like assignment Name,
    label 'posted an assignment' bookmark icon, instructor name & image in the Current Assignments Page */
    @Test(priority=2,enabled=true)
    public void instructorAbleToViewAssignmentDetailsWhenHeAssignedItToStudent()
    {
        try
        {
            String dataIndex = "1";
            log("Description", "Validating the assignment details in Current Assignments Page after Assigning assignment", "info");
            new Assignment().create(Integer.parseInt(dataIndex));
            log("Step 1","Simple assignment is created","info");
            new LoginUsingLTI().ltiLogin(dataIndex); //Creating user Instructor
            log("Step 2","Instructor is logged in","info");
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");//Creating user student 1
            log("Step 3","Student 1 is logged in","info");
            new LoginUsingLTI().ltiLogin(dataIndex); //Logging in as instructor
            log("Step 4","Instructor again logged in","info");
            new Assignment().assignToStudent(Integer.parseInt(dataIndex));  //Assigning assignment to student 1
            log("Step 5","Instructor assigned assignment to the student","info");
            Thread.sleep(2000);
            new Assignment().assignmentDetailsValidate(1);
            log("Step 6","Assignment details are validated in Assignment page, when it is assigned","info");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase actionOverAssignment in class ActionOverAssignments",e);
        }
    }





    /*Continuation - As In instructor When I click assignment name in the current assignment page,
    I should be able to navigate to new tab with the assignment Name
    & also I should be able to preview the contents in the Assignment page*/

    @Test(priority=3,enabled=true)
    public void instructorAbleToViewAssignmentPageDetailsOnceItIsClicked()
    {

        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            String dataIndex = "1";
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            log("Description", "Instructor should be able to navigate to new tab with the assignment Name & also able to preview the contents in the Assignment page", "info");

            new LoginUsingLTI().ltiLogin(dataIndex); //Creating user Instructor 1
            new Navigator().NavigateTo("Current Assignments");
            log("Step 1","Instructor Navigated to Current Assignments page","info");
            String assignmentTab = driver.findElement(By.cssSelector("span[title='"+assessmentName+"']")).getText();
            driver.findElement(By.cssSelector("span[title='"+assessmentName+"']")).click();
            if(!assignmentTab.equals(assignmentTab))
                Assert.fail("On clicking assignment name it open the assessment in a new tab");
            log("Assertion 1","On clicking assignment name, it opened the assessment in a new tab","pass");

            currentAssignments.icon_rightGreen.click();
            List<WebElement> allElements = currentAssignments.assignmentPageContent;
            //Finding the 'Difficulty Level:' field
            String diffiecultyLevel = allElements.get(0).getText();
            if(!diffiecultyLevel.contains("Difficulty Level:"))
            {
                Assert.fail("'Diffculty level' is not displayed for each question");
            }
            log("Assertion 2","Diffculty level' is not displayed for each question","pass");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase actionOverAssignment in class ActionOverAssignments",e);
        }
    }




    /*Continuation - As In instructor I should be able to view the assignment details when it is attempted by student*/
	@Test(priority=4,enabled=true)
	public void instructorAbleToViewAssignmentDetailsWhenItIsAttemptedByStudent()
	{
		try
		{
        CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
        //Precondition - Instructor has already assigned an assignment to class section
        log("Description","As In instructor I should be able to view the assignment details when it is attempted by student","info");
        String dataIndex = "1";
        new LoginUsingLTI().ltiLogin(dataIndex+"_1");//Creating user student 1
        new Assignment().submitAssignmentAsStudent(1);
        log("Step 1","Student attempted the assignment","info");
		new LoginUsingLTI().ltiLogin(dataIndex); //Logging in as instructor
		new Navigator().NavigateTo("Assignments");
        log("Step 2","Instructor Navigated to Current Assignments page","info");

        if(currentAssignments.viewStudentResponses.size()==0)
			Assert.fail("View Student Responses' link not present in the current Assignments page");
            log("Assertion 1","View Student Responses' link not present in the current Assignments page","pass");

        if(currentAssignments.link_tryIt.size()==0)
            Assert.fail("'Try it' link not present in current Assignmet Page");
            log("Assertion 2","Try it' link not present in current Assignmet Page","pass");

        if(currentAssignments.link_like.size()==0)
            Assert.fail("'Like' link not present in current Assignmet Page");
            log("Assertion 3","'Like' link not present in current Assignmet Page","pass");

        if(currentAssignments.link_comments.size()==0)
            Assert.fail("'Comments' link not present in current Assignmet Page");
            log("Assertion 4","'Comments' link not present in current Assignmet Page","pass");
        }

		catch(Exception e)
		{
			Assert.fail("Exception in testcase submitAssignment in class ActionOverAssignments",e);
		}
	}





    /*As an Instructor I should be able to view the student status such as Not Started, In progress,
    Submitted & reviewed for an assignment in 'Current Assignments' page*/
    @Test(priority=5,enabled=true)
    public void instructorAbleToViewStudentStatusForAssignments()
    {
        /*
        1. When an instructor assigns an assignment to student, Not Started status count will be 1 & others status count will be 0
        2. when a student attempts few question in the assigned assignment, In Progress status count will be 1 & others status count will be 0
        3. when a student submits the assigned assignment, Submitted status count will be 1 & others status count will be 0
        */

        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            log("Description","Instructor I should be able to view the student status such as Not Started, In progress, Submitted & reviewed for an assignment in 'Current Assignments' page","info");
            String dataIndex = "2";
            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", dataIndex);

            new Assignment().create(Integer.parseInt(dataIndex));
            new Assignment().addQuestions(Integer.parseInt(dataIndex), "multiplechoice", "");
            new Assignment().addQuestions(Integer.parseInt(dataIndex), "multiplechoice", "");
            new Assignment().addQuestions(Integer.parseInt(dataIndex), "multiplechoice", "");
            log("Step 1","Simple assignment is created","info");

            new LoginUsingLTI().ltiLogin(dataIndex+"_1");//Creating user student 1

            //Check the "Not Started" status count when an assignment is asssigned to student
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Assignment().assignToStudent(Integer.parseInt(dataIndex));  //Assigning assignment to student 1
            log("Step 2","Instructor assigned assignment to the student","info");

            int notstarted = new Assignment().statusBoxCount(Integer.parseInt(dataIndex), "Not Started");
            int inprogress = new Assignment().statusBoxCount(Integer.parseInt(dataIndex), "In Progress");
            int submitted = new Assignment().statusBoxCount(Integer.parseInt(dataIndex), "Submitted");
            int reviewed = new Assignment().statusBoxCount(Integer.parseInt(dataIndex), "Reviewed");

            if(notstarted!=1 && inprogress!=0 && submitted!=0 && reviewed !=0){
                Assert.fail("'Not Started' status count of students for assignment not equal to 1 after assigning it to a student of the class");
            }
            log("Assertion 1","'Not Started' status count of students for assignment not equal to 1 after assigning it to a student of the class\"","pass");


            //Check the "In Progress" status count when a student attempts few questions in assignment
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");//Login as a student
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentName);
            new Assignment().submitAnswer("true",Integer.parseInt(dataIndex));

            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Navigator().NavigateTo("Current Assignments");
            notstarted = new Assignment().statusBoxCount(Integer.parseInt(dataIndex), "Not Started");
            inprogress = new Assignment().statusBoxCount(Integer.parseInt(dataIndex), "In Progress");
            submitted = new Assignment().statusBoxCount(Integer.parseInt(dataIndex), "Submitted");
            reviewed = new Assignment().statusBoxCount(Integer.parseInt(dataIndex), "Reviewed");

            if(notstarted!=0 && inprogress!=1 && submitted!=0 && reviewed !=0){
                Assert.fail("'In Progress' status count of students for assignment not equal to 1 after student attempts few questions in assignment");
            }
            log("Assertion 2","'In Progress' status count of students for assignment not equal to 1 after student attempts few questions in assignment","pass");

            //Check the "Submitted" status count when student attempts the assignment
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");//Login as a student
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentName);
            new Assignment().submitAnswer("true", Integer.parseInt(dataIndex));
            new Assignment().submitAnswer("true",Integer.parseInt(dataIndex));
            new Assignment().submitAnswer("true",Integer.parseInt(dataIndex));
            System.out.println("here u have to remove something that is not applicable");

            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Navigator().NavigateTo("Current Assignments");
            notstarted = new Assignment().statusBoxCount(Integer.parseInt(dataIndex), "Not Started");
            inprogress = new Assignment().statusBoxCount(Integer.parseInt(dataIndex), "In Progress");
            submitted = new Assignment().statusBoxCount(Integer.parseInt(dataIndex), "Submitted");
            reviewed = new Assignment().statusBoxCount(Integer.parseInt(dataIndex), "Reviewed");

            if(notstarted!=0 && inprogress!=0 && submitted!=1 && reviewed !=0){
                Assert.fail("'Submitted' status count of students for assignment not equal to 1 after student submitted the assignment");
            }
            log("Assertion 3","'Submitted' status count of students for assignment not equal to 1 after student submitted the assignment","pass");
            //Scripts for "Reviewed" & "Graded" status has been written in PolicyWithAssignments.java

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase instructorAbleToViewStudentStatusForAssignments in class ActionOverAssignments",e);
        }
    }



    /*As an instructor I should be able to like, unlike & comment the assignments
    & also the counts of the like, unlike & comments*/
    @Test(priority=6,enabled=true)
    public void instructorShouldBeABleToPerformActionsOnSocialElements()
    {
        try
        {
            String datandex = "3";
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", datandex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            log("Description","As an instructor I should be able to like, unlike & comment the assignments & also the counts of the like, unlike & comments","info");
            new Assignment().create(Integer.parseInt(datandex));
            new LoginUsingLTI().ltiLogin(datandex+"_1");  //Create a student
            new LoginUsingLTI().ltiLogin(datandex); // Login as an instructor
            new Assignment().assignToStudent(Integer.parseInt(datandex)); //Assigning assignment to student
            log("Step 1","Instructor assigned an assignment","info");
            new Assignment().likeAssignment(Integer.parseInt(datandex));// like the assignment
            log("Step 1","Instructor liked the assignment in current Assignments page","info");
            new Assignment().unlikeAssignment(Integer.parseInt(datandex));//unlike the assignment
            log("Step 1","Instructor unliked the assignment in current Assignments page","info");

            //Put first comment
            new Assignment().commentAssignment(Integer.parseInt(datandex));//Comment the assignment for first time
            int commentcount = new Assignment().commentCount(Integer.parseInt(datandex));//Get the comment count
            if(commentcount!= 1) Assert.fail("Comment count not got updated to 1 after commenting on the assignment");
            log("Assert 1","Comment count not got updated to 1 after commenting on the assignment","pass");

            //Put second comment
            new Assignment().commentAssignment(Integer.parseInt(datandex));//Comment the assignment for 2nd time
            commentcount = new Assignment().commentCount(Integer.parseInt(datandex));
            if(commentcount!= 2) Assert.fail("Comment count not got updated to 2 after commenting on the assignment");
            log("Assert 2","Comment count not got updated to 2 after commenting on the assignment","pass");

            //Put third comment
            new Assignment().commentAssignment(Integer.parseInt(datandex));//Comment the assignment for third time
            commentcount = new Assignment().commentCount(Integer.parseInt(datandex));
            if(commentcount!= 3) Assert.fail("Comment count not got updated to 3 after commenting on the assignment");
            log("Assert 3","Comment count not got updated to 2 after commenting on the assignment","pass");


            /*Check the 'View all comments & hide all comments' functionalities
            when instructor refreshes the current assignments page*/

            new Navigator().NavigateTo("Assignments");
            driver.navigate().refresh();
            int index = 0;
            List<WebElement> assignments =  currentAssignments.getList_assignmentName();//List of assignments in current assignment page
            for(WebElement element : assignments)
            {
                if(element.getText().contains(assessmentname))
                {
                    break;
                }
                index++;
            }
            List<WebElement> allComments = currentAssignments.button_comments;
            allComments.get(index).click();//click on Comment Link
            Thread.sleep(3000);

            String viewallcommentslinktext = currentAssignments.link_viewAllComments.getText();//Fetch "View all comments" text
            if(!viewallcommentslinktext.equals("View all comments"))
                Assert.fail("A link 'View all comments' not appearing if there are more than 2 comments.");
            log("Assert 4","A link 'View all comments' not appearing if there are more than 2 comments.","pass");

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",currentAssignments.comboBox_viewAllComments);
            Thread.sleep(2000);

            String hideallcommentslinktext = currentAssignments.link_viewAllComments.getText();//Fetch "Hide all comments" text
            if(!hideallcommentslinktext.equals("Hide all comments"))
                Assert.fail("View All Comments Link not getting changed to Hide All Comments after clicking on View All Comments");
            log("Assert 5","View All Comments Link not getting changed to Hide All Comments after clicking on View All Comments","pass");

        }

        catch(Exception e)
        {
            Assert.fail("Exception in testcase instructorShouldBeABleToPerformActionsOnSocialElements in class SocialElementsInstructorSide",e);
        }
    }


  /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

                                                'Class Status' Functionality


   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/




   /*As an instructor I should be able to see the class status 'Graded' in current assignments page*/
    @Test(priority=7,enabled=true)
    public void validateAssignmentStatus_scheduled()//Gradable
    {
        CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

          /*Description : When any assignment is assigned after today's date for accessible after while assigning, the class status would be 'Scheduled' &
          the student can see the assignment only after accessible date*/
        try
        {
            String dataIndex = "4";
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            log("Description","As an instructor I should be able to see the class status 'Graded' in current assignments page","Info");

            //Assign the gradable assignment to a student in a class section in which accessible after is tomorrow
            new Assignment().create(Integer.parseInt(dataIndex));
            new Assignment().addQuestions(Integer.parseInt(dataIndex),"multiplechoice","");
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");  //Creating study
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Assignment().assignToStudent(Integer.parseInt(dataIndex));  //Assigning assignment to student
            log("Step 1","Instructor assigned a gradable assignment to a student with accessible after date 2nd of next month","info");

            //Filter ths Scheduled Assignments in current assignments page
            new Navigator().NavigateTo("Assignments");
            currentAssignments.dropDown_allAssignmentStatus.click(); //clicking on All Assignment Status filter dropdown
            Thread.sleep(2000);
            currentAssignments.dropDownValue_Scheduled.click(); //Selecting filter as Scheduled
            Thread.sleep(3000);
            log("Step 2","Instructor filtered Scheduled assignments in 'All Assignment Status' dropdown","info");

            //Make sure that the scheduled assignment is as expected
            if(!currentAssignments.getAssignmentName().getText().equals(assessmentName))
                Assert.fail("The assignment name is not as expected");
            log("Assertion 1","The assignment name is not as expected","pass");

            //Make sure that the class status would be 'Scheduled'
            new Assignment().statusValidate(Integer.parseInt(dataIndex),"Class Status:  Scheduled");
            log("Assertion 2","The Class status is not 'Scheduled' in the right assignment section","pass");


            //Make sure that Student will not be able to see the assignment now ( Since Accessible Date After is not today)
            new LoginUsingLTI().ltiLogin(dataIndex+"_1"); //Logging in as student to check if the scheduled assignment is there on the student dashboard
            new Navigator().NavigateTo("Course Stream");
            if(currentAssignments.assignmentTitleInCourseStream.size()!=0)
                Assert.fail("The assessment scheduled for future is available for student");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase validateAssignmentStatus_graded in class StatusOfAssignments",e);
        }
    }

       /*As an instructor I should be able to see the class status 'Available for Students' in current assignments page*/
    @Test(priority=8,enabled=true)
    public void validateAssignmentStatus_availableForStudents()//Gradable
    {
        /*Description : When a gradable assignments is assigned to a class section, if all students in that class section has not attempted the assignment
        the class status would be 'Available for Students'*/
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String dataIndex = "5";
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            log("Description","As an instructor I should be able to see the class status 'Available for Students' in current assignments page","info");

            //Assign the gradable assignment to 2 student & let 2 students attempt the assignment(2 students in a class section)
            new Assignment().create(Integer.parseInt(dataIndex));
            new Assignment().addQuestions(Integer.parseInt(dataIndex),"all","");
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");  //Creating student with ID 17391student
            new LoginUsingLTI().ltiLogin(dataIndex+"_2");  //Creating student with ID 17392student
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Assignment().assignToStudent(Integer.parseInt(dataIndex));  //Assigning assignment to student 17391
            new Assignment().updateAssignment(Integer.parseInt(dataIndex+"_2"),true); // Assigning assignment to student 2
            log("Step 1","Instructor has assigned the gradable assignment to 2 students ","info");

            //Filter the 'Available for Students' in All Assignment status dropdown
            new Navigator().NavigateTo("Assignments");
            currentAssignments.dropDown_allAssignmentStatus.click();//clicking on All Assignment Status filter dropdown
            Thread.sleep(2000);
            currentAssignments.dropDownValue_AvailableForStudents.click(); //Selecting filter as Available for Students

            //Make sure that the scheduled assignment is as expected
            if(!currentAssignments.getAssignmentName().getText().equals(assessmentName))
                Assert.fail("The assignment name is not as expected");
            log("Assertion 1","The assignment name is not as expected","pass");



            //Let first student attempt the assignment
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");//Logging in as student
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(dataIndex));//submit assignment
            log("Step 2","First Student submitted the assignment","info");

            //Make sure that the class status would be 'Available for Students'
            new LoginUsingLTI().ltiLogin(dataIndex); //Logging in as instructor
            new Assignment().statusValidate(Integer.parseInt(dataIndex),"Status:  Available for Students");
            log("Assertion 2","The Class status is not 'Available for Students' in the right assignment section","pass");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase 'validateAssignmentStatus_availableForStudents' in class StatusOfAssignments",e);
        }
    }




    /*As an instructor I should be able to see the class status 'Needs Grading' in current assignments page*/
    @Test(priority=9,enabled=true)
    public void validateAssignmentStatus_needsGrading()//Gradable
    {
        /*Description : When a gradable assignments is assigned to a class section & if the all student in that class section attempts the assignment
        the class status would be 'Needs Grading'*/
        try
        {
            String dataIndex = "6";
            log("Description","As an instructor I should be able to see the class status 'Needs Grading' in current assignments page","info");

            //Assign the gradable assignment to a student in a class section
            new Assignment().create(Integer.parseInt(dataIndex));
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");  //Creating student with ID 17421student
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Assignment().assignToStudent(Integer.parseInt(dataIndex));  //Assigning assignment to student 17421
            log("Step 1","Instructor assigned the gradable assignment to a student","info");

            //Let first student attempts the assignment
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(dataIndex));
            log("Step 2","Student submitted the assignment","info");

            //Make sure that the class status would be 'Needs Grading'
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor to validate the status of assignment after the student attempts it
            new Assignment().statusValidate(Integer.parseInt(dataIndex),"Status:  Needs Grading");
            log("Assertion 1","The Class status is not 'Needs Grading' in the right assignment section","pass");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase gradeableAssignmentStatusAsGradingInProgress in class StatusOfAssignments",e);
        }
    }


    /*As an instructor I should be able to see the class status 'Graded' in current assignments page*/
    @Test(priority=10,enabled=true)
    public void validateAssignmentStatus_graded()//Gradable
    {
        /*Description :
        1 : When a gradable assignments is assigned to a class section & if the all student in that class section attempts the assignment the class status would be 'Needs Grading'
        2 : Then when the instructor release the grade, the class status would be 'graded'*/
        try
        {
            log("Description","As an instructor I should be able to see the class status 'Graded' in current assignments page","info");
            String dataIndex = "7";

            //Assign the gradable assignment to a student in a class section
            new Assignment().create(Integer.parseInt(dataIndex));
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");  //Creating student with ID 17441student
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Assignment().assignToStudent(Integer.parseInt(dataIndex));  //Assigning assignment to student 17441
            log("Step 1","Instructor assigned the gradable assignment to a student(Only one student in a class section)","info");

            //Let first student attempts the assignment
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(dataIndex));
            log("Step 2","Student submitted the gradable assignment","info");

            //Let instructor release the grade & refresh the page
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Assignment().releaseGrades(Integer.parseInt(dataIndex),"Release Grade for All");
            driver.navigate().refresh();
            log("Step 3","Instructor released the grade and refreshed the page","info");

            //Make sure that the class status would be 'Graded'
            new Assignment().statusValidate(Integer.parseInt(dataIndex),"Status:  Graded");
            log("Assertion 1","The Class status is not 'Graded' in the right assignment section","pass");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase gradeableAssignmentReleaseGrade in class StatusOfAssignments",e);
        }
    }


    /*As an instructor I should be able to see the class status 'Review in progress' for Non gradable Assignments in current assignments page*/
    @Test(priority=11,enabled=true)
    public void validateAssignmentStatus_reviewInProgress()//Nongradable Assignment
    {
        /*Description : When a non gradable assignments is assigned to a class section & if the all student in that class section attempts the assignment
        the class status would be 'Review in Progress'*/
        try
        {
            String dataIndex = "8";
            log("Description","As an instructor I should be able to see the class status 'Review in progress' for Non gradable Assignments in current assignments page","info");

            //Assign an assignment to a student
            new Assignment().create(Integer.parseInt(dataIndex));
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");  //Creating student with ID 17441student
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Assignment().assignToStudent(Integer.parseInt(dataIndex));  //Assigning assignment to student 17441
            log("Step 1","Instructor has assigned the non gradable assignment to a student","info");

            //Submit an assignment
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(dataIndex));
            log("Step 2","Student submitted the assignment","info");

            //Make sure that class status would be 'Review in Progress'
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor to validate the status of assignment after the student attempts it
            new Assignment().statusValidate(Integer.parseInt(dataIndex),"Status:  Review in Progress");
            log("Assertion 1","The Class status is not 'Review in Progress' in the right assignment section","pass");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase nonGradeableAssignmentStatusAsReviewInProgress in class StatusOfAssignments",e);
        }
    }


    /*As an instructor I should be able to see the class status 'Reviewed' for Non gradable Assignments in current assignments page*/
    @Test(priority=12,enabled=true)
    public void validateAssignmentStatus_reviewed()//Non Gradable
    {
        /*Description :
        1 : When a non gradable assignments is assigned to a class section & if the all student in that class section attempts the assignment the class status would be 'Review in Progress'
        2 : Then when the instructor release the feedback, the class status would be 'Reviewed'*/
        try
        {
            String dataIndex = "9";
            log("Description","As an instructor I should be able to see the class status 'Reviewed' for Non gradable Assignments in current assignments page","info");

            //Assign an assignment to a student in class section
            new Assignment().create(Integer.parseInt(dataIndex));
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");  //Creating student with ID 17481student
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Assignment().assignToStudent(Integer.parseInt(dataIndex));  //Assigning assignment to student 17481

            //Submit the assignment as a student
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(dataIndex));

            //Release the feedback & refresh the page as an instructor
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Assignment().releaseGrades(Integer.parseInt(dataIndex),"Release Feedback for All");
            driver.navigate().refresh();

            //Make sure that class status would be 'Reviewed'
            new Assignment().statusValidate(Integer.parseInt(dataIndex),"Status:  Reviewed");
            log("Assertion 1","The Class status is not 'Reviewed' in the right assignment section","pass");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase nonGradeableAssignmentReleaseFeedback in class StatusOfAssignments",e);
        }
    }


    /*As an instructor I should be able to see the Student status boxes with count when an assignment is assigned in current assignment page*/
    @Test(priority=13,enabled=true)
    public void verifyEachStatusBox()
    {
        try
        {
            String dataIndex = "10";
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            log("Description", "As an instructor I should be able to see the Student status boxes with count when an assignment is assigned in current assignment page", "info");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String notStartedCount = ReadTestData.readDataByTagName("", "notStarted", dataIndex);
            String inProgressCount = ReadTestData.readDataByTagName("", "inProgress", dataIndex);
            String submittedCount = ReadTestData.readDataByTagName("", "submitted", dataIndex);
            String reviewedCount = ReadTestData.readDataByTagName("", "reviewed", dataIndex);
            String gradedCount = ReadTestData.readDataByTagName("", "graded", dataIndex);
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);

            //Assign assignment to student
            new Assignment().create(Integer.parseInt(dataIndex));
            new LoginUsingLTI().ltiLogin(dataIndex);
            new Assignment().assignToStudent(Integer.parseInt(dataIndex));

            //Make sure that instructor can see the default status box with count in the current assignment page
            new LoginUsingLTI().ltiLogin(dataIndex);
            new Navigator().NavigateTo("Assignments");
            int index = 0;
            List<WebElement> assignments =  currentAssignments.getList_assignmentName();
            for(WebElement element : assignments)
            {
                if(element.getText().contains(assessmentname))
                {
                    break;
                }
                index++;
            }
            if(!currentAssignments.notStarted_boxWithCount.get(index).getText().replaceAll("[\n\r]", "").equals(notStartedCount+"Not Started"))
               CustomAssert.fail("Verify the Status Box", "Not Started box with the count is not displayed");

            if(!currentAssignments.inProgress_boxWithCount.get(index).getText().replaceAll("[\n\r]", "").contains(inProgressCount+"In Progress"))
               CustomAssert.fail("Verify the Status Box","In progress box with the count is not displayed");

            if(!currentAssignments.submitted_boxWithCount.get(index).getText().replaceAll("[\n\r]", "").contains(submittedCount+"Submitted"))
               CustomAssert.fail("Verify the Status Box","Submitted box with the count is not displayed");

            if(gradeable.equals("false"))
            {
                if(!currentAssignments.reviewed_boxWithCount.get(index).getText().replaceAll("[\n\r]", "").contains(reviewedCount+"Reviewed"))
                   CustomAssert.fail("Verify the Status Box","Reviewed box with the count is not displayed");
            }
            else
            {
                if(!currentAssignments.graded_boxWithCount.get(index).getText().replaceAll("[\n\r]", "").contains(gradedCount+"Graded"))
                   CustomAssert.fail("Verify the Status Box","Graded box with the count is not displayed");
            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception in verifyEachStatusBox in class VerifyEachStatusBox",e);
        }
    }


/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

                                                'Filter Assignments' Functionality


   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/





    @Test(priority=14,enabled=true)
    public void validateContentsOfAllAssignmentStatusDropdown()
    {

        try
        {
            String dataIndex = "11";

            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Navigator().NavigateTo("Current Assignments");
            WebElement allAssignmentStatusDropDown = driver.findElement(By.xpath("(//*[@class='sbHolder'])[2]//a[@class='sbToggle']"));
            allAssignmentStatusDropDown.click();
            List<WebElement> allAssignmentStatusDropdownOptionsList = driver.findElements(By.xpath("//ul[@class='sbOptions']//a[@title = 'All Assignment Status']//../..//a"));
            if(!(allAssignmentStatusDropdownOptionsList.get(1).getText().equals("Available for Students")
                    &&allAssignmentStatusDropdownOptionsList.get(2).getText().equals("Needs Grading")
                    &&allAssignmentStatusDropdownOptionsList.get(3).getText().equals("Review in progress")
                    &&allAssignmentStatusDropdownOptionsList.get(4).getText().equals("Graded")
                    &&allAssignmentStatusDropdownOptionsList.get(5).getText().equals("Reviewed")
                    &&allAssignmentStatusDropdownOptionsList.get(6).getText().equals("Scheduled")))
                     CustomAssert.fail("Verify the all the options ","All the options in 'All Assignment Status' dropdown are not displaying as per expected");
            allAssignmentStatusDropDown.click();
            traverseAllOptionsInDropDown(allAssignmentStatusDropDown,allAssignmentStatusDropdownOptionsList);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase validateContentsOfAllAssignmentStatusDropdown in class StatusOfAssignments",e);
        }
    }




    @Test(priority=15,enabled=true)
    public void validateContentsOfAllActivityDropdown()
    {

        try
        {
            String dataIndex = "11";
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Navigator().NavigateTo("Current Assignments");
            WebElement allAssignmentStatusDropDown = driver.findElement(By.xpath("(//*[@class='sbHolder'])[3]//a[@class='sbToggle']"));
            allAssignmentStatusDropDown.click();
            List<WebElement> allAssignmentStatusDropdownOptionsList = driver.findElements(By.xpath("//ul[@class='sbOptions']//a[@title = 'All Activity']//../..//a"));
            if(!(allAssignmentStatusDropdownOptionsList.get(1).getText().equals("Question Assignment")
                    &&allAssignmentStatusDropdownOptionsList.get(2).getText().equals("Question Practice")
                    &&allAssignmentStatusDropdownOptionsList.get(3).getText().equals("Discussion Assignment")
                    &&allAssignmentStatusDropdownOptionsList.get(4).getText().equals("Learning Activity")))
                CustomAssert.fail("Verify the all the options ","All the options in 'All Activity' dropdown are not displaying as per expected");
            allAssignmentStatusDropDown.click();
            traverseAllOptionsInDropDown(allAssignmentStatusDropDown,allAssignmentStatusDropdownOptionsList);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase validateContentsOfAllActivityDropdown in class StatusOfAssignments",e);
        }
    }









































    /*As an instructor I should be able to filter the assignments based on the class status 'Available for Students' in current assignments page*/
    @Test(priority=16,enabled=true)
    public void filterAssignmentsBasedOnAvailableForStudentsStatus()//Gradable
    {
        /*Description : When a gradable assignments is assigned to a class section, if all students in that class section has not attempted the assignment
        the class status would be 'Available for Students'*/
        try
        {
            String dataIndex = "12";
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(Integer.parseInt(dataIndex)));
            log("Description","As an instructor I should be able to filter the assignments based on the class status 'Available for Students' in current assignments page","info");

            //Assign the gradable assignment to 2 student & let 2 students attempt the assignment(2 students in a class section)
            new Assignment().create(Integer.parseInt(dataIndex));
            new Assignment().addQuestions(Integer.parseInt(dataIndex),"all","");
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");  //Creating student with ID 17391student
            new LoginUsingLTI().ltiLogin(dataIndex+"2");  //Creating student with ID 17392student
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Assignment().assignToStudent(Integer.parseInt(dataIndex));  //Assigning assignment to student 17391
            new Assignment().updateAssignment(Integer.parseInt(dataIndex+"2"),true); // Assigning assignment to student 2
            log("Step 1","Instructor has assigned the gradable assignment to 2 students ","info");

            //Filter the 'Available for Students' in All Assignment status dropdown
            new Navigator().NavigateTo("Assignments");
            currentAssignments.dropDown_allAssignmentStatus.click();//clicking on All Assignment Status filter dropdown
            Thread.sleep(2000);
            currentAssignments.dropDownValue_AvailableForStudents.click(); //Selecting filter as Available for Students

            //Make sure that the scheduled assignment is as expected
            if(!currentAssignments.getAssignmentName().getText().equals(assessmentName))
                Assert.fail("The assignment name is not as expected");
            log("Assertion 1","The assignment name is not as expected","pass");



            //Let first student attempt the assignment
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");//Logging in as student
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(dataIndex));//submit assignment
            log("Step 2","First Student submitted the assignment","info");


            //Make sure that the instructor can filter the assignments based on  status 'Available for Students'
            new LoginUsingLTI().ltiLogin(dataIndex); //Logging in as instructor
            new Assignment().filterAssignmentsInCurrentAssignmentsPage(0,"","","Available for Students");
            validateClassStatus(Integer.parseInt(dataIndex),"Status:  Available for Students");
            log("Step 3","The instructor can filter the assignments based on Available for Students Status in Current Assignment Page","pass");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase 'validateAssignmentStatus_availableForStudents' in class StatusOfAssignments",e);
        }
    }




    /*As an instructor I should be able to filter the assignments based on the class status 'Needs Grading' in current assignments page*/
    @Test(priority=17,enabled=true)
    public void filterAssignmentsBasedOnNeedsGradingStatus()//Gradable
    {
        /*Description : When a gradable assignments is assigned to a class section & if the all student in that class section attempts the assignment
        the class status would be 'Needs Grading'*/
        try
        {
            String dataIndex = "13";
            log("Description","As an instructor I should be able to filter the assignments based on the class status 'Needs Grading' in current assignments page","info");

            //Assign the gradable assignment to a student in a class section
            new Assignment().create(Integer.parseInt(dataIndex));
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");  //Creating student with ID 17421student
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Assignment().assignToStudent(Integer.parseInt(dataIndex));  //Assigning assignment to student 17421
            log("Step 1","Instructor assigned the gradable assignment to a student","info");

            //Let first student attempts the assignment
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(dataIndex));
            log("Step 2","Student submitted the assignment","info");

            //Make sure that the class status would be 'Needs Grading'
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor to validate the status of assignment after the student attempts it
            new Navigator().NavigateTo("Assignments");
            new Assignment().filterAssignmentsInCurrentAssignmentsPage(0,"","Needs Grading","");
            validateClassStatus(Integer.parseInt(dataIndex),"Status:  Needs Grading");
            log("Step 3","The instructor can filter the assignments based on 'Needs Grading' Status in Current Assignment Page","pass");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase gradeableAssignmentStatusAsGradingInProgress in class StatusOfAssignments",e);
        }
    }







    /*As an instructor I should be able to filter the assignments based on the class status 'Review in progress' in current assignments page*/
    @Test(priority=18,enabled=true)
    public void filterAssignmentsBasedOnReviewInProgressStatus()//Nongradable Assignment
    {
        /*Description : When a non gradable assignments is assigned to a class section & if the all student in that class section attempts the assignment
        the class status would be 'Review in Progress'*/
        try
        {
            String dataIndex = "14";
            log("Description","As an instructor I should be able to filter the assignments based on the class status 'Review in progress' in current assignments page","info");

            //Assign an assignment to a student
            new Assignment().create(Integer.parseInt(dataIndex));
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");  //Creating student with ID 17441student
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Assignment().assignToStudent(Integer.parseInt(dataIndex));  //Assigning assignment to student 17441
            log("Step 1","Instructor has assigned the non gradable assignment to a student","info");

            //Submit an assignment
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(dataIndex));
            log("Step 2","Student submitted the assignment","info");

            //Make sure that class status would be 'Review in Progress'
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor to validate the status of assignment after the student attempts it
            new Assignment().statusValidate(Integer.parseInt(dataIndex),"Status:  Review in Progress");
            log("Assertion 1","The Class status is not 'Review in Progress' in the right assignment section","pass");

            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor to validate the status of assignment after the student attempts it
            new Navigator().NavigateTo("Assignments");
            new Assignment().filterAssignmentsInCurrentAssignmentsPage(0,"","Review in progress","");// Filter 'Review in progress'
            validateClassStatus(Integer.parseInt(dataIndex),"Status:  Review in progress");// Validate the assignment has the class status 'Review in progress'
            log("Step 3","The instructor can filter the assignments based on 'Review in progress' Status in Current Assignment Page","pass");


        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase nonGradeableAssignmentStatusAsReviewInProgress in class StatusOfAssignments",e);
        }
    }



    /*As an instructor I should be able to filter the assignments based on the class status 'Graded' in current assignments page*/
    @Test(priority=19,enabled=true)
    public void filterAssignmentsBasedOnGradedStatus()//Gradable
    {
        /*Description :
        1 : When a gradable assignments is assigned to a class section & if the all student in that class section attempts the assignment the class status would be 'Needs Grading'
        2 : Then when the instructor release the grade, the class status would be 'graded'*/
        try
        {
            log("Description","As an instructor I should be able to filter the assignments based on the class status 'Graded' in current assignments page","info");
            String dataIndex = "15";

            //Assign the gradable assignment to a student in a class section
            new Assignment().create(Integer.parseInt(dataIndex));
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");  //Creating student with ID 17441student
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Assignment().assignToStudent(Integer.parseInt(dataIndex));  //Assigning assignment to student 17441
            log("Step 1","Instructor assigned the gradable assignment to a student(Only one student in a class section)","info");

            //Let first student attempts the assignment
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(dataIndex));
            log("Step 2","Student submitted the gradable assignment","info");

            //Let instructor release the grade & refresh the page
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Assignment().releaseGrades(Integer.parseInt(dataIndex),"Release Grade for All");
            driver.navigate().refresh();
            log("Step 3","Instructor released the grade and refreshed the page","info");

            //Make sure that the class status would be 'Graded'
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor to validate the status of assignment after the student attempts it
            new Navigator().NavigateTo("Assignments");
            new Assignment().filterAssignmentsInCurrentAssignmentsPage(0,"","Graded","");// Filter 'Graded' Assignments
            validateClassStatus(Integer.parseInt(dataIndex),"Status:  Graded");// Validate the assignment has the class status 'Graded'
            log("Step 3","The instructor can filter the assignments based on 'Graded' Status in Current Assignment Page","pass");


        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase gradeableAssignmentReleaseGrade in class StatusOfAssignments",e);
        }
    }




    /*As an instructor I should be able to filter the assignments based on the class status 'Reviewed' in current assignments page*/
    @Test(priority=20,enabled=true)
    public void filterAssignmentsBasedOnReviewedStatus()//Non Gradable
    {
        /*Description :
        1 : When a non gradable assignments is assigned to a class section & if the all student in that class section attempts the assignment the class status would be 'Review in Progress'
        2 : Then when the instructor release the feedback, the class status would be 'Reviewed'*/
        try
        {
            String dataIndex = "16";
            log("Description","    /*As an instructor I should be able to filter the assignments based on the class status 'Reviewed' in current assignments page*/\n","info");

            //Assign an assignment to a student in class section
            new Assignment().create(Integer.parseInt(dataIndex));
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");  //Creating student with ID 17481student
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Assignment().assignToStudent(Integer.parseInt(dataIndex));  //Assigning assignment to student 17481

            //Submit the assignment as a student
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");
            new Assignment().submitAssignmentAsStudent(Integer.parseInt(dataIndex));

            //Release the feedback & refresh the page as an instructor
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Assignment().releaseGrades(Integer.parseInt(dataIndex),"Release Feedback for All");
            driver.navigate().refresh();

            //Make sure that class status would be 'Reviewed'
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor to validate the status of assignment after the student attempts it
            new Navigator().NavigateTo("Assignments");
            new Assignment().filterAssignmentsInCurrentAssignmentsPage(0,"","Reviewed","");// Filter 'Reviewed' Assignments
            validateClassStatus(Integer.parseInt(dataIndex),"Status:  Reviewed");// Validate the assignment has the class status 'Reviewed'
            log("Step 3","The instructor can filter the assignments based on 'Reviewed' Status in Current Assignment Page","pass");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase nonGradeableAssignmentReleaseFeedback in class StatusOfAssignments",e);
        }
    }


    /*As an instructor I should be able to filter the assignments based on the class status 'Scheduled' in current assignments page*/
    @Test(priority=21,enabled=true)
    public void filterAssignmentsBasedOnScheduledStatus()//Gradable
    {
        CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

          /*Description : When any assignment is assigned after today's date for accessible after while assigning, the class status would be 'Scheduled' &
          the student can see the assignment only after accessible date*/
        try
        {
            String dataIndex = "17";
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            log("Description","As an instructor I should be able to filter the assignments based on the class status 'Scheduled' in current assignments page","Info");

            //Assign the gradable assignment to a student in a class section in which accessible after is tomorrow
            new Assignment().create(Integer.parseInt(dataIndex));
            new Assignment().addQuestions(Integer.parseInt(dataIndex),"multiplechoice","");
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");  //Creating study
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor
            new Assignment().assignToStudent(Integer.parseInt(dataIndex));  //Assigning assignment to student
            log("Step 1","Instructor assigned a gradable assignment to a student with accessible after date 2nd of next month","info");

            //Make sure that the class status would be 'Scheduled'
            new LoginUsingLTI().ltiLogin(dataIndex); //  Logging in as instructor to validate the status of assignment after the student attempts it
            new Navigator().NavigateTo("Assignments");
            new Assignment().filterAssignmentsInCurrentAssignmentsPage(0,"","Scheduled","");// Filter 'Scheduled' Assignments
            validateClassStatus(Integer.parseInt(dataIndex),"Status:  Scheduled");// Validate the assignment has the class status 'Scheduled'
            log("Step 3","The instructor can filter the assignments based on 'Scheduled' Status in Current Assignment Page","pass");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase validateAssignmentStatus_graded in class StatusOfAssignments",e);
        }
    }


    /**
     * This method traverse all the options one by one in a dropdown
     * @param allAssignmentStatusDropDownElement - dropdownElement
     * @param allAssignmentStatusDropdownOptionsElementsList - optionsList in a dropdown
     */
   public void traverseAllOptionsInDropDown(WebElement allAssignmentStatusDropDownElement, List<WebElement> allAssignmentStatusDropdownOptionsElementsList){
       try{
           for(int a=0;a<allAssignmentStatusDropdownOptionsElementsList.size()-1;a++){
               allAssignmentStatusDropDownElement.click();
               allAssignmentStatusDropdownOptionsElementsList.get(a+1).click();
           }
       }catch(Exception e){
           Assert.fail("Exception in method 'traverseDropDown' in class 'Current Assignment'",e);
       }
   }


    /**
     This method checks the Assignment with Class status such as 'Available for Students, Needs Grading, Review in progress & so on'
     * @param dataIndex
     * @param expectedStatus - Class status such as 'Available for Students, Needs Grading, Review in progress & so on
     */
    public void validateClassStatus(int dataIndex, String expectedStatus) {
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        int index = 0;
        List<WebElement> assignments = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
        for (WebElement element : assignments) {
            if (element.getText().contains(assessmentname)) {
                break;
            }
            index++;
        }
        List<WebElement> status = driver.findElements(By.className("ls-assignment-status"));
        if (!status.get(index).getText().contains(expectedStatus))
            CustomAssert.fail("Validate Class Status","Status of the asignment in Instructor Dashboard is " + status.get(index).getText() + " which is not equal to expected status: " + expectedStatus);

    }












	//For Gradable Assignment - Available for Students, Needs Grading, Graded
    //For Nongradable assignment - Review in Progress
    //For chapter assignment - Once that assignment is opened by student, the status will be 'Reviewed'
    //While assigning, if start date is not default one, then status will be 'Scheduled' in student side












    //In Filter Assignments functionality -
    //1. Check the contents of 'All Chapters' dropdown
    //2. Check the contents of 'All Sections' dropdown
    //3. Check the contents of 'All Assignment Status' dropdown - Completed
    //4. Check the contents of 'All activity' dropdown - Completed
    //5. Check whether an instructor can select all different options in All Assignment Status' dropdown - Completed
    //6. Check whether an instructor can select all different options in All Activity' dropdown - Completed
    //7. Check the functionality of 'All Chapters' dropdown(filterAssignmentsBasedOnChapters on 'All Chapters' dropdown)
    //8. Check the functionality of 'All Sections' dropdown(filterAssignmentsBasedOnSections on 'All Sections' dropdown)
    //9. Check the functionality of 'All Assignment Status' dropdown(filterAssignmentsBasedOnAssignmentStatuses on 'All Assignment Status' dropdown) - Completed
    //10. Check the functionality of 'All activity' dropdown(filterAssignmentsBasedOnActivities on 'All Activities' dropdown)


//In Copy Assignments in Current Assignment functionality -



    //While checking the contents of assignments only add below due date & accessible after values functionalities
    //	Due Date label functionality
    // Accessible After functionality
    /*1. Gradable & Non Gradable Assignments impacted areas in Current Assignments Page
    E.g : For Gradable assignments, it should show 'Gradable' label, For Nongradable, it should show nothing*/
    //2. "Filter" Assignments Functionality - check the functionality like All Chapters, All Sections, All Assignment Status & All Activity of all the combo boxes in filter assignments

    // Below are in seperate pages
    //View Student Response
    //Update Assignment
    //Unassign Assignment
    //Try
    //Class Status Functionality such as Available for Students, Needs Grading, Review in Progress, Graded, Reviewed & Scheduled






}
