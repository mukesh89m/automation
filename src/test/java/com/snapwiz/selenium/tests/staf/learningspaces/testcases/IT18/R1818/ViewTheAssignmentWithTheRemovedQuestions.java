package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1818;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity.MyActivity;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.MostChallengingReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 1/19/2015.
 */
public class ViewTheAssignmentWithTheRemovedQuestions extends Driver {

    @Test(priority = 1, enabled = true)
    public void  ViewTheAssignmentWithTheRemovedQuestions() {
        try {
            //tc row no 141
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "141");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "141");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            Dashboard dashboard = PageFactory.initElements(driver,Dashboard.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver,ProficiencyReport.class);
            MostChallengingReport mostChallengingReport = PageFactory.initElements(driver,MostChallengingReport.class);

            new Assignment().create(141);
            new Assignment().addQuestions(141, "truefalse", "");
            new Assignment().addQuestions(141, "truefalse", "");
            new LoginUsingLTI().ltiLogin("141_1");//create student1
            new LoginUsingLTI().ltiLogin("141_2");//create student2
            new LoginUsingLTI().ltiLogin("141_3");//create student3
            new LoginUsingLTI().ltiLogin("141_3");//create student4
            new LoginUsingLTI().ltiLogin("141");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("141");//select three question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(141);//assign assignment

            new LoginUsingLTI().ltiLogin("141_2");//login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Thread.sleep(12000);

            new LoginUsingLTI().ltiLogin("141_3");//login as student3
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false,"incorrect",141);
            new Assignment().nextButtonInQuestionClick();
            new AttemptQuestion().trueFalse(false,"incorrect",141);
            new LoginUsingLTI().ltiLogin("141_4");//login as student4
            new Assignment().submitAssignmentAsStudent(141); //submit assignment

            new LoginUsingLTI().ltiLogin("141");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getDeleteIconTwo().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getExtendDueDate().click();
            driver.findElement(By.id("extended-due-time")).click();
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals("12:15 AM")) {
                    time.click();
                    break;
                }
            }
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)","");
            Thread.sleep(2000);
            currentAssignments.getUpdate().click();
            driver.findElement(By.className("extend-due-submit-yes")).click();
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("141_1");//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false,"incorrect",141);
            assignments.getArrowDropDown().click();
            int questionCount = assignments.getQuestionCount().size();
            if(questionCount > 2){
                Assert.fail("summary dropdown is showing the deleted question");
            }

            new Assignment().nextButtonInQuestionClick();
            new AttemptQuestion().trueFalse(false,"incorrect",141);
            assignments.getFinishAssignment().click();
            int questionCount1 = assignments.getQuestionCart().size();
            if(questionCount1 > 2){
                Assert.fail("question cart for deleted question is displaying");
            }
            int question=assignments.getWidth().size();
            if(question > 2){
                Assert.fail("scoreover assignment page is not included deleted question");
            }
            new LoginUsingLTI().ltiLogin("141_2");//login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false,"incorrect",141);
            assignments.getArrowDropDown().click();
            int questionCount2 = assignments.getQuestionCount().size();
            if(questionCount2 > 2){
                Assert.fail("summary dropdown is showing the deleted question");
            }

            new Assignment().nextButtonInQuestionClick();
            new AttemptQuestion().trueFalse(false,"incorrect",141);
            assignments.getFinishAssignment().click();
            int questionCount3 = assignments.getQuestionCart().size();
            if(questionCount3 > 2){
                Assert.fail("question cart for deleted question is displaying");
            }
            int question1=assignments.getWidth().size();
            if(question1 > 2){
                Assert.fail("scoreover assignment page is not included deleted question");
            }

            new LoginUsingLTI().ltiLogin("141_3");//login as student3
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false,"incorrect",141);
            assignments.getArrowDropDown().click();
            int questionCount4 = assignments.getQuestionCount().size();
            if(questionCount4 > 2){
                Assert.fail("summary dropdown is showing the deleted question");
            }

            assignments.getFinishAssignment().click();
            int questionCount5 = assignments.getQuestionCart().size();
            if(questionCount5 > 2){
                Assert.fail("question cart for deleted question is not displaying");
            }

            int question2=assignments.getWidth().size();
            if(question2 > 2){
                Assert.fail("scoreover assignment page is not included deleted question");
            }
            new LoginUsingLTI().ltiLogin("141_4");//login as student4
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            int questionCount6 = assignments.getQuestionCart().size();
            if(questionCount6 > 3){
                Assert.fail("question cart for deleted question is not displaying");
            }
            Thread.sleep(2000);
            assignments.getSecondquestioncart().click();
            String message=assignments.getNotificationMessage().getText();
            Assert.assertEquals(message,"This question is deleted and is no longer part of this assignment.","notification message for deleted question is not displaying");
            new LoginUsingLTI().ltiLogin("141");//log in as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getCurrentAssignmentTitle().click();//click on current assignment
            currentAssignments.getViewGrade_link().click();//click on view student responses
            new Assignment().provideGradeToStudentForMultipleQuestions(141);
            new Assignment().releaseGrades(141,"Release Grade for All");
            new LoginUsingLTI().ltiLogin("141_1");//login as student1
            String questionAttempt=dashboard.getQuestionAttempted().getText();
            Assert.assertEquals(questionAttempt,"2","Questions attempted count over dashboard is included deleted question");
            new Navigator().NavigateTo("Proficiency Report");
            int questionCountt = proficiencyReport.getQuestionCart().size();
            if(questionCountt > 2){
                Assert.fail("question cart for deleted question is displaying");
            }

            new LoginUsingLTI().ltiLogin("141_2");//login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            String score=assignments.getScore().getText();
            Assert.assertEquals(score,"Score (1.6/2)","Score for assignment over assignment page is included deleted question");
           /* currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[title='Assignments']")));


            new Navigator().navigateToTab("Assignments");
            String score1=assignments.getMarks().getText();
            Thread.sleep(9000);
            Assert.assertEquals(score1,"Score (1.6/2)","Score for assignment in assignment tab at right is included deleted question");
            Thread.sleep(2000);*/
            new Navigator().NavigateTo("Dashboard");
            String questionAttempt1=dashboard.getQuestionAttempted().getText();
            Assert.assertEquals(questionAttempt1,"2","Questions attempted count over dashboard is included deleted question");
            new Navigator().NavigateTo("Proficiency Report");
            int questionCountt1 = proficiencyReport.getQuestionCart().size();
            if(questionCountt1 > 2){
                Assert.fail("question cart for deleted question is displaying");
            }
            new LoginUsingLTI().ltiLogin("141_3");//login as student3
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            String score2=assignments.getScore().getText();
            Assert.assertEquals(score2,"Score (1.6/2)","Score for assignment over assignment page is included deleted question");
           /* currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[title='Assignments']")));

            driver.findElement(By.cssSelector("span[title='Assignments']")).click();
            String score3=assignments.getMarks().getText();
            Assert.assertEquals(score3,"Score (1.6/2)","Score for assignment in assignment tab at right is included deleted question");
            Thread.sleep(2000);*/
            new Navigator().NavigateTo("Dashboard");
            String questionAttempt2=dashboard.getQuestionAttempted().getText();
            Assert.assertEquals(questionAttempt2,"2","Questions attempted count over dashboard is included deleted question");
            new Navigator().NavigateTo("Proficiency Report");
            int questionCountt2 = proficiencyReport.getQuestionCart().size();
            if(questionCountt2 > 2){
                Assert.fail("question cart for deleted question is displaying");
            }
            new LoginUsingLTI().ltiLogin("141_4");//login as student4
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            String score4=assignments.getScore().getText();
            Assert.assertEquals(score4,"Score (2.4/3)","Score for assignment over assignment page is included deleted question");
           /* currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            new Navigator().navigateToTab("Assignments");
            String score5=assignments.getMarks().getText();
            Assert.assertEquals(score5,"Score (2.4/3)","Score for assignment in assignment tab at right is included deleted question");
            Thread.sleep(2000);*/
            new Navigator().NavigateTo("Dashboard");
            String questionAttempt3=dashboard.getQuestionAttempted().getText();
            Assert.assertEquals(questionAttempt3,"3","Questions attempted count over dashboard is included deleted question");
            new Navigator().NavigateTo("Proficiency Report");
            int questionCountt3 = proficiencyReport.getQuestionCart().size();
            if(questionCountt3 > 3){
                Assert.fail("question cart for deleted question is not displaying");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case ViewTheAssignmentWithTheRemovedQuestions in class ViewTheAssignmentWithTheRemovedQuestions", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void  ViewTheAssignmentWithTheRemovedQuestionsNonGradable() {
         try {
              //tc row no 192
          String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "192");
          String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "192");
          MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
          CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
          Assignments assignments = PageFactory.initElements(driver,Assignments.class);
          Dashboard dashboard = PageFactory.initElements(driver,Dashboard.class);
          ProficiencyReport proficiencyReport = PageFactory.initElements(driver,ProficiencyReport.class);
          MostChallengingReport mostChallengingReport = PageFactory.initElements(driver,MostChallengingReport.class);
          new LoginUsingLTI().ltiLogin("192_1");//create student1
          new LoginUsingLTI().ltiLogin("192_2");//create student2
          new LoginUsingLTI().ltiLogin("192_3");//create student3
          new LoginUsingLTI().ltiLogin("192");//log in as instructor
          new Navigator().NavigateTo("My Question Bank");//click on my question bank
          myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
          Thread.sleep(2000);
          new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
          myQuestionBank.getFilterArrow().click();//click on filter
          myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
          new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
          new AssignLesson().selectQuestionForCustomAssignment("192");//select three question
          myQuestionBank.getAssignmentNameField().click();//click on name field
          myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
          myQuestionBank.getSaveForLater().click();//click on save for later
          Thread.sleep(5000);
          myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
          new AssignLesson().Assigncustomeassignemnt(192);//assign assignment
          new LoginUsingLTI().ltiLogin("192_2");//login as student2
          new Navigator().NavigateTo("Assignments");  //navigate to Assignments
          currentAssignments.getAssessmentName().click();
          new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
          Thread.sleep(12000);
          new LoginUsingLTI().ltiLogin("192_3");//login as student3
          new Navigator().NavigateTo("Assignments");  //navigate to Assignments
          currentAssignments.getAssessmentName().click();
          new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new LoginUsingLTI().ltiLogin("192");//log in as instructor
          new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
          currentAssignments.getUpdateAssignment_button().click();//click on update assignment
          currentAssignments.getDeleteIconTwo().click();//click on delete
          currentAssignments.getYesOnNotificationMessage().click();//click on yes
          Thread.sleep(2000);
          currentAssignments.getReassign_button().click();//click on reassign
          currentAssignments.getExtendDueDate().click();//click on extend due date
          driver.findElement(By.id("extended-due-time")).click();//enter time
          List<WebElement> elements = driver.findElements(By.xpath("//li"));
          for (WebElement time : elements) {
           if (time.getText().equals("12:15 AM")) {
            time.click();
            break;
           }
          }
          ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)","");
          Thread.sleep(2000);
          currentAssignments.getUpdate().click();
          driver.findElement(By.className("extend-due-submit-yes")).click();
          Thread.sleep(2000);
          new LoginUsingLTI().ltiLogin("192_1");//login as student1
          new Navigator().NavigateTo("Assignments");  //navigate to Assignments
          currentAssignments.getAssessmentName().click();
          new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));

          new AttemptQuestion().trueFalse(false,"incorrect",192);
          assignments.getArrowDropDown().click();
          int questionCount = assignments.getQuestionCount().size();
          if(questionCount > 13){
           Assert.fail("summary dropdown is showing the deleted question");
          }

          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          assignments.getFinishAssignment().click();
          int questionCount1 = assignments.getQuestionCart().size();
          if(questionCount1 > 13){
           Assert.fail("question cart for deleted question is displaying");
          }
          int question=assignments.getWidth().size();
          if(question > 13){
           Assert.fail("scoreover assignment page is not included deleted question");
          }
          new Navigator().NavigateTo("Dashboard");
          String questionAttempt1=dashboard.getQuestionAttempted().getText();
          Assert.assertEquals(questionAttempt1,"13","Questions attempted count over dashboard is included deleted question");
          new Navigator().NavigateTo("Proficiency Report");
          int questionCount2 = proficiencyReport.getQuestionCart().size();
          if(questionCount2 > 13){
           Assert.fail("question cart for deleted question is not displaying");
          }
          /*new Navigator().NavigateTo("Most Challenging Activities Report");
          String questionCount3 = mostChallengingReport.getStudChapPerformance().get(0).getText();
          Assert.assertEquals(questionCount3,"13/13","Most challenging report is including deleted question count");
*/
          //tc row no 202
          new LoginUsingLTI().ltiLogin("192_2");//login as student2
          new Navigator().NavigateTo("Assignments");  //navigate to Assignments
          currentAssignments.getAssessmentName().click();
          new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          assignments.getArrowDropDown().click();
          int questionCount7 = assignments.getQuestionCount().size();
          if(questionCount7 > 13){
           Assert.fail("summary dropdown is showing the deleted question");
          }

          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          assignments.getFinishAssignment().click();
          int questionCount4 = assignments.getQuestionCart().size();
          if(questionCount4 > 13){
           Assert.fail("question cart for deleted question is displaying");
          }
          int question1=assignments.getWidth().size();
          if(question1 > 13){
           Assert.fail("scoreover assignment page is not included deleted question");
          }
          new Navigator().NavigateTo("Dashboard");
          String questionAttempt2=dashboard.getQuestionAttempted().getText();
          Assert.assertEquals(questionAttempt2,"13","Questions attempted count over dashboard is included deleted question");
          new Navigator().NavigateTo("Proficiency Report");
          int questionCount5 = proficiencyReport.getQuestionCart().size();
          if(questionCount5 > 13){
           Assert.fail("question cart for deleted question is not displaying");
          }
         /* new Navigator().NavigateTo("Most Challenging Activities Report");
          String questionCount6 = mostChallengingReport.getStudChapPerformance().get(0).getText();
          Assert.assertEquals(questionCount6,"13/13","Most challenging report is including deleted question count");*/

          //tc row no 212
          new LoginUsingLTI().ltiLogin("192_3");//login as student3
          new Navigator().NavigateTo("Assignments");  //navigate to Assignments
          currentAssignments.getAssessmentName().click();
             new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
             new AttemptQuestion().trueFalse(false,"incorrect",192);
          assignments.getArrowDropDown().click();
          int questionCount9 = assignments.getQuestionCount().size();
          if(questionCount9 > 13){
           Assert.fail("summary dropdown is showing the deleted question");
          }

          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          new Assignment().nextButtonInQuestionClick();
          new AttemptQuestion().trueFalse(false,"incorrect",192);
          assignments.getFinishAssignment().click();
          int questionCount10 = assignments.getQuestionCart().size();
          if(questionCount10 > 13){
           Assert.fail("question cart for deleted question is not displaying");
          }
          Thread.sleep(3000);
          assignments.getSecondquestioncartt().click();
          String message=assignments.getNotificationMessage().getText();
          Assert.assertEquals(message,"This question is deleted and is no longer part of this assignment.","notification message for deleted question is not displaying");
          new Navigator().NavigateTo("Dashboard");
          String questionAttempt3=dashboard.getQuestionAttempted().getText();
          Assert.assertEquals(questionAttempt3,"14","Questions attempted count over dashboard is included deleted question");
          new Navigator().NavigateTo("Proficiency Report");
          int questionCountt3 = proficiencyReport.getQuestionCart().size();
          if(questionCountt3 > 13){
           Assert.fail("question cart for deleted question is not displaying");
          }

         } catch (Exception e) {
              Assert.fail("Exception in test case ViewTheAssignmentWithTheRemovedQuestionsNonGradable in class ViewTheAssignmentWithTheRemovedQuestions", e);
     }
}

    @Test(priority = 3, enabled = true)
    public void  courseStreamEntryForDiscussionAddedToDeletedQuestion() {
     try {
      //tc row no 227
      String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "227");
      String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "227");
      CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
      MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
      CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
      Assignments assignments = PageFactory.initElements(driver, Assignments.class);
      new LoginUsingLTI().ltiLogin("227_1");//create student1
      new LoginUsingLTI().ltiLogin("227_2");//create student2
      new LoginUsingLTI().ltiLogin("227");//log in as instructor
      new Navigator().NavigateTo("My Question Bank");//click on my question bank
      myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
      Thread.sleep(2000);
      new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
      new AssignLesson().selectQuestionForCustomAssignment("227");//select two question
      myQuestionBank.getAssignmentNameField().click();//click on name field
      myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
      myQuestionBank.getSaveForLater().click();//click on save for later
      Thread.sleep(5000);
      myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
      new AssignLesson().Assigncustomeassignemnt(227);//assign assignment
      new LoginUsingLTI().ltiLogin("227_1");//login as student1
      new Navigator().NavigateTo("Assignments");
      new Assignment().submitAssignmentAsStudent(227); //submit assignment
      Thread.sleep(2000);
      new ClickOnquestionCard().clickonquestioncard(0);
      assignments.getDiscussionTab().click();
      assignments.getNewButton().click();
      assignments.getEditBox().sendKeys("aassdfdghh asdfgjj");
      assignments.getSubmit().click();
      new LoginUsingLTI().ltiLogin("227");//log in as instructor
      new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
      currentAssignments.getUpdateAssignment_button().click();//click on update assignment
      currentAssignments.getThreeDots().click();//click on three dots
      currentAssignments.getExpendQuestion().click();//click on expand icon
      String text=currentAssignments.getQuestionLabel().getText();
      currentAssignments.getDeleteIcon().click();//click on delete
      currentAssignments.getYesOnNotificationMessage().click();//click on yes
      Thread.sleep(2000);
      currentAssignments.getReassign_button().click();//click on reassign
      currentAssignments.getExtendDueDate().click();//click on extend due date
      driver.findElement(By.id("extended-due-time")).click();//enter time
      List<WebElement> elements = driver.findElements(By.xpath("//li"));
      for (WebElement time : elements) {
          if (time.getText().equals("12:15 AM")) {
            time.click();
            break;
           }
          }
      ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)","");
      Thread.sleep(2000);
      currentAssignments.getUpdate().click();
      Thread.sleep(2000);
      new Navigator().NavigateTo("Course Stream");
      courseStreamPage.getJumpOut().click();//click on jumpout
      Thread.sleep(4000);
      String question=courseStreamPage.getQuestion().getText();
      if (!text.contains(question)){
       Assert.fail("Instructor should not get navigated to deleted question");
      }

      new LoginUsingLTI().ltiLogin("227_1");//login as student1
      new Navigator().NavigateTo("Assignments");  //navigate to Assignments
      currentAssignments.getAssessmentName().click();
      new WebDriverWait(driver,180).until(ExpectedConditions.visibilityOf(driver.findElement(By.className("resource-title"))));
      new ClickOnquestionCard().clickonquestioncard(0);
      String message=assignments.getNotificationMessage().getText();
      Assert.assertEquals(message,"This question is deleted and is no longer part of this assignment.","notification message for deleted question is not displaying");

     } catch (Exception e) {
      Assert.fail("Exception in test case courseStreamEntryForDiscussionAddedToDeletedQuestion in class ViewTheAssignmentWithTheRemovedQuestions", e);
     }

    }
    @Test(priority = 4, enabled = true)
    public void  discussionEntryOverMyActivity() {
     try {
      //tc row no 229
      String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "229");
      String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "229");
      MyActivity myActivity = PageFactory.initElements(driver,MyActivity.class);
      MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
      CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
      Assignments assignments = PageFactory.initElements(driver, Assignments.class);
      new LoginUsingLTI().ltiLogin("229_1");//create student1
      new LoginUsingLTI().ltiLogin("229_2");//create student2
      new LoginUsingLTI().ltiLogin("229");//log in as instructor
      new Navigator().NavigateTo("My Question Bank");//click on my question bank
      myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
      Thread.sleep(2000);
      new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
      new AssignLesson().selectQuestionForCustomAssignment("229");//select two question
      myQuestionBank.getAssignmentNameField().click();//click on name field
      myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
      myQuestionBank.getSaveForLater().click();//click on save for later
      Thread.sleep(5000);
      myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
      new AssignLesson().Assigncustomeassignemnt(229);//assign assignment
      new LoginUsingLTI().ltiLogin("229_1");//login as student1
      new Navigator().NavigateTo("Assignments");
      new Assignment().submitAssignmentAsStudent(229); //submit assignment
      Thread.sleep(2000);
      new ClickOnquestionCard().clickonquestioncard(0);
      assignments.getDiscussionTab().click();
      assignments.getNewButton().click();
      assignments.getEditBox().sendKeys("aassdfdghh asdfgjj");
      assignments.getSubmit().click();
      new LoginUsingLTI().ltiLogin("229");//log in as instructor
      new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
      currentAssignments.getUpdateAssignment_button().click();//click on update assignment
      currentAssignments.getDeleteIcon().click();//click on delete
      currentAssignments.getYesOnNotificationMessage().click();//click on yes
      Thread.sleep(2000);
      currentAssignments.getReassign_button().click();//click on reassign
      currentAssignments.getExtendDueDate().click();//click on extend due date
      driver.findElement(By.id("extended-due-time")).click();//enter time
      List<WebElement> elements = driver.findElements(By.xpath("//li"));
      for (WebElement time : elements) {
       if (time.getText().equals("12:15 AM")) {
        time.click();
        break;
       }
      }
      ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)","");
      Thread.sleep(2000);
      currentAssignments.getUpdate().click();
      Thread.sleep(2000);
      new LoginUsingLTI().ltiLogin("229_1");//login as student1
      new Navigator().NavigateTo("My Activity");
      myActivity.getDiscussion_Link().click();//click on discussions
      myActivity.getDiscussion_Link().click();//click on enter a discussion
         new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
         String message=assignments.getNotificationMessage().getText();
      Assert.assertEquals(message,"This question is deleted and is no longer part of this assignment.","notification message for deleted question is not displaying");

     } catch (Exception e) {
      Assert.fail("Exception in test case discussionEntryOverMyActivity in class ViewTheAssignmentWithTheRemovedQuestions", e);
     }

    }

}
