package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT24.R242;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.MostChallengingReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by root on 11/23/15.
 */
public class InstructorUnAssignAssignmentValidator extends Driver{
     /*This Use case deals with - As an instructor/mentor, I should be able to un-assign an assignment even after
    grades are released till either due date expires or all students have submitted the assignment*/

    @Test(priority =1,enabled = true)
    public void instructorToBeAbleToUnAssignAssignmentInPolicy1() {
        try {

            //Pre Condition - CASE 1: Student side-- Class having multiple student, 1 student has submitted and the grades are released with Policy 1
            /*"1.Instructor should have assigned a Gradable/Gradable with policy/Non-Gradable  Assignment.
            2.Atleast One student should have submitted the assignment. and grades are released for the same"*/


            int dataIndex = 321;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "" + dataIndex);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MostChallengingReport mostChallengingReport = PageFactory.initElements(driver, MostChallengingReport.class);


            new Assignment().create(dataIndex);//Create Assignment
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
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");

            new Assignment().create(dataIndex + 1);//Create Assignment
            new Assignment().addQuestions(dataIndex + 1, "truefalse", "");
            new Assignment().addQuestions(dataIndex + 1, "truefalse", "");

            new LoginUsingLTI().ltiLogin("" + dataIndex);//Create an instructor1

            new LoginUsingLTI().ltiLogin("321_1");//Create a student1 in the same class section
            new LoginUsingLTI().ltiLogin("321_2");//Create a student2 in the same class section
            new LoginUsingLTI().ltiLogin("321_3");//Create a student3 in the same class section

            new LoginUsingLTI().ltiLogin("" + dataIndex);//Login as instructor1
            new Assignment().assignToStudent(dataIndex);//Assign assignment 1 to entire class section
            new Assignment().assignToStudent(dataIndex + 1);//Assign assignment 2 to entire class section


            new LoginUsingLTI().ltiLogin("321_1");//Login as the student1
            new Assignment().submitAssignmentAsStudent(dataIndex);//Let Student1 attempt the assignment 1

            new Assignment().submitAssignmentAsStudent(dataIndex + 1);//Let Student1 attempt the assignment 2
            unAssignAssignment(""+dataIndex);//Let Instructor unAssign the assignment
            validateStudSideFeatures("" + dataIndex);
        } catch (Exception e) {
            Assert.fail("Exception in test method 'instructorToBeAbleToUnAssignAssignmentInPolicy1' in test class InstructorSideUnAssignAssignmentValidator", e);
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
            new LoginUsingLTI().ltiLogin(dataIndex);//Create an instructor in the same class section

            String actualTimeSpent = dashboard.timeSpentTile.getText().trim();
            new Navigator().NavigateTo("Assignments");


            //Expected - 1 : The Assignment entry should be removed in the Assignment page.
            if(driver.findElements(By.cssSelector("span[title = '(shor)  " + assessmentName + "']")).size()!=0){
                Assert.fail("The Assignment entry should be removed in the Assignment page");
            }



            /*//Expected - 2 : The number count in Submitted tile should get updated with less count
            //Assert.assertEquals(assignments.gradedCount.getText(),"1","The number count in Submitted tile should get updated with less count");
            if(!driver.findElement(By.className("ls-graded-count")).getText().equals("1")){
                Assert.fail("The number count in Submitted tile should get updated with less count");
            }



            //Expected - 3 : The Percentage in the Overall Score tab should be updated accordingly
            Assert.assertEquals(assignments.overAllScore.getText(),"Overall Score: 67%","The Percentage in the Overall Score tab should be updated accordingly");
*/

            /*Row No - 52 : "4. Navigate to e-Textbook from main navigator dropdown
            5. Select any chapter having its TLOs linked to the assignment
            6. Select the Assignment tab in right side column"*/
            /*new Navigator().NavigateTo("e-Textbook");

            //Row No - 54 : 7.Navigate to Study Plan and select that particular chapter to which the assignment was associated.
            //Expected - The assignment entry should be removed under that particular chapter in study plan.
            Thread.sleep(5000);
            new MouseHover().mouserhover("toc-icon");
            Thread.sleep(5000);
            String assessName =  ReadTestData.readDataByTagName("", "assessmentname", dataIndex+1);
            List<WebElement> eleList = driver.findElements(By.className("ls-inner-arw"));
            for(int a=0;a<eleList.size();a++){
                if(eleList.get(a).isDisplayed()){
                    new MouseHover().mouserhoverbywebelement(eleList.get(a));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",eleList.get(0));
                    Thread.sleep(200);
                    if(driver.findElements(By.cssSelector("a[title = '"+assessName+"']")).size()!=0){
                        break;
                    }
                }

            }


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
*/
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
/*


            //Row No - 6 : 9.Navigate to "My Activity" page.
            //Expected - The assignment should NOT be present in "My Activity" page even if the assignment was Submitted/Attempted.
            new Navigator().NavigateTo("My Activity");
            Thread.sleep(1000);
            if(driver.findElements(By.linkText("\""+assessmentName+"\"")).size()!=0){
                Assert.fail("The assignment should NOT be present in \"My Activity\" page even if the assignment was Submitted/Attempted.");
            }
*/


            /*Row No - 62 : "10.Navigate to Dashboard.
            11.Verify the ""Recent Activities"" section."*/
            //Expected - The Assignment entry should be removed even if the student has worked on last  TWO Activities for that assignment.
            new Navigator().NavigateTo("Dashboard");
            Thread.sleep(1000);
            if(driver.findElements(By.partialLinkText(assessmentName)).size()!=0){
                Assert.fail("The Assignment entry should be removed even if the student has worked on last  TWO Activities for that assignment.");
            }

            //12.10.Verify the "Assignments" Section on Dashboard.
            //Expected 1- Average Performance tile should get updated
            Assert.assertEquals(driver.findElement(By.id("highcharts-2")).getText().trim(),"Average Performance33%","Overall score tile should get updated");

            //Expected -2 : Recently graded tile should get updated
            Assert.assertEquals(proficiencyReport.getBarChart().isDisplayed(),true,"Recently graded tile should get updated");
            Assert.assertEquals(proficiencyReport.getBarChart().getAttribute("height"),"42","Recently graded tile should get updated");


            //Expected - 3 : Total Assignment number tile should get updated
            //Assert.assertEquals(dashboard.upcomingValue.getText(),"0","Upcoming assignment tile should get updated");



            //13.11.Verify the Class Activity card section on Dashboard.
            //Expected - The assignment entry should be removed even if it is part of the last 3 activities.
            if(driver.findElements(By.xpath("//div[@class='middle']//p[contains(text(),'(shor) "+assessmentName+"')]")).size()!=0){
                Assert.fail("The assignment entry should be removed even if it is part of the last 3 activities.");
            }

            //12.Verify the "Average Class Participation" Tile on Dashboard.
            //Expected -  "Question Attempted" tile should get updated
            Assert.assertEquals(dashboard.totalNumberOfQuestionAttempted.getText().trim(),"1","Question Attempted\" tile should get updated");


            //13.Verify the "Average Question Performance" tile on Dashboard.
            //Expected - "Question Performance" tile should get updated
            Assert.assertEquals(dashboard.questionPerformanceInPercentage.getText().trim(),"33%","There should be NO change in the \"Question Attempted\" tile.");


            //16.14.Verify the "Average Question Attempted" tile on Dashboard.
            //Expected -"Participation Rating" tile should get updated
            //Assert.assertEquals(dashboard.participationRatingInPercentage.getText().trim(),"0\n"+"%","There should be NO change in the \"Participation Rating\" tile.");

            //15.Verify the "Average Time Spent" tile on Dashboard.
            //Expected - "Time Spent" tile should get updated
            Assert.assertEquals(dashboard.timeSpentTile.getText().trim(),actualTimeSpent," \"Time Spent\" tile should get updated");

            //16. Navigate to Gradebook page
            //Expected - The entry for the assignment should be removed
            if(driver.findElements(By.cssSelector("span[class='ls-ins-gradebook-activity-midterm ellipsis']")).size()!=0){
               Assert.fail("The entry for the assignment should be removed");
            }




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
            Assert.assertEquals(proficiencyReport.getBarChart().getAttribute("height"),"220","Assignment entry as bars on the Course Proficiency By Chapter column should  not be removed");


            //Expected - 3 : Course Proficiency Summary should not get updated as per the removed assignment contribution
            Assert.assertEquals(proficiencyReport.getStudProficiency().getText().trim(),"95%","Assignment entry as bars on the Course Proficiency By Chapter column should  not be removed");


            /*Row No - 75 : "20.Click on the chapter bar in Course Proficiency by Chapter section of Proficiency Report.
            21.Verify Chapter proficiency summary."*/
            //Expected - 1 : Questions card entry should be removed from the question cards in the right side column
            //proficiencyReport.getBarChart().click();
            new Click().clickByid("Mjg4NDg0Nzk3");
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
            currentAssignments.getYesOnUnAssignPopUp().click();//Click on 'Yes' button in UnAssign popup*/

            new UIElement().waitTillInvisibleElement(By.cssSelector("span[title = '(shor) "+assessmentName+"']"));
            if(driver.findElements(By.cssSelector("span[title = '(shor) "+assessmentName+"']")).size()!=0){
                Assert.fail("Assignment should get removed from Current Assignment page");
            }
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

            //new UIElement().waitAndFindElement(By.cssSelector("//span[@class='diagnostic-test-attempt-button btn btn--primary']//span"));
            //new Click().clickBycssselector("span[class='diagnostic-test-attempt-button btn btn--primary']");
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

    }



