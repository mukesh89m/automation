package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;

public class InstructorAbleToFeedbackForNonGradableAssignment extends Driver{
    @Test(priority = 1, enabled = true)
    public void instructorAbleToFeedbackForNonGradableAssignment()
    {
        try
        {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "2122");
            new Assignment().create(2122);
            new Assignment().addQuestions(2122, "qtn-type-true-false-img", assessmentname);
            new Assignment().addQuestions(2122, "qtn-type-true-false-img", assessmentname);
            new Assignment().addQuestions(2122, "qtn-type-true-false-img", assessmentname);
            new Assignment().addQuestions(2122, "qtn-type-true-false-img", assessmentname);
            new Assignment().addQuestions(2122, "qtn-type-true-false-img", assessmentname);
            new Assignment().addQuestions(2122, "qtn-type-true-false-img", assessmentname);
            new LoginUsingLTI().ltiLogin("21221");  //create student 21221student
            new LoginUsingLTI().ltiLogin("21222");  //create student 21222student
            new LoginUsingLTI().ltiLogin("21223");  //create student 21223student
            new LoginUsingLTI().ltiLogin("21224");  //create student 21224student
            new LoginUsingLTI().ltiLogin("21225");  //create student 21225student
            new LoginUsingLTI().ltiLogin("2122");
            new Assignment().assignToStudent(21221);
            new LoginUsingLTI().ltiLogin("2122");  //login as instructor
            new Assignment().updateAssignment(21222, true);
            new LoginUsingLTI().ltiLogin("2122"); //login as instructor
            new Assignment().updateAssignment(21223, true);
            new LoginUsingLTI().ltiLogin("2122");  //login as instructor
            new Assignment().updateAssignment(21224, true);
            new LoginUsingLTI().ltiLogin("2122");  //login as instructor
            new Assignment().updateAssignment(21225, true);

            new LoginUsingLTI().ltiLogin("21221");
            new Assignment().submitAssignmentAsStudent(21221);

            new LoginUsingLTI().ltiLogin("21222"); //login as 21222student
            new Assignment().openAssignmentFromCourseStream("2122");
            //submit the assignment by skipping all the questions
            int helppage = driver.findElements(By.className("close-help-page")).size();
            if(helppage == 1)
                driver.findElement(By.className("close-help-page")).click();

            int timer = 1;
            while(timer != 0)
            {
                if (driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).size() > 0) //Finish Assignment
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")));
                } else if (driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).size() > 0)//click on Finish Assignment
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")));
                } else if (driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large next-or-submit-link']")).size() > 0)//click on Finish Assignment
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large next-or-submit-link']")));
                }
                else
                {

                    if (driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).size() > 0) //next
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")));

                    else if (driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).size() > 0) //next
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")));

                }
                timer=driver.findElements(By.id("assessmentTimer")).size();
            }

            new LoginUsingLTI().ltiLogin("21223");  //login as student
            new Assignment().submitAssignmentAsStudent(21223); //in progress assignment(attempt all but not last question)

            new LoginUsingLTI().ltiLogin("21225"); //login as student
            //open the assignment and quit(attempt none) (in progress)
            new Assignment().openAssignmentFromCourseStream("2122");

            new LoginUsingLTI().ltiLogin("2122");  //login as instructor
            new Assignment().clickViewResponse(assessmentname);

            //check for total Mark Column
            int totalMarkColumn = driver.findElements(By.cssSelector("div[class='idb-gradebook-content-coloumn idb-gradebook-content-total']")).size();
            if(totalMarkColumn != 0)
                Assert.fail("'Total Mark column is present for non gradable assignments.");

            //check for tick mark
            int tickMark = driver.findElements(By.className("idb-question-manually-graded")).size();
            if(tickMark == 0)
                Assert.fail("Checkmark is not displayed where user has attempted a manually gradable question but the instructor has not marked the question");

            //click on 'view response'
            new Click().clickByclassname("idb-gradebook-question-content");
            Thread.sleep(2000);


            driver.findElement(By.id("view-user-question-performance-feedback-box")).click();
            String feedbacktext = new RandomString().randomstring(5);
            driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys(feedbacktext);
            driver.findElement(By.cssSelector("span.view-user-question-performance-save-btn")).click();
            Thread.sleep(2000);
            new TabClose().tabClose(2); //close the response page
            //refresh button click
            driver.findElement(By.cssSelector("#ls-gradebook-refresh-icon > span.ins-assignment-button-sprite.instructor-assignment-refresh")).click();
            Thread.sleep(2000);
            //check for comment icon
            WebElement WE = driver.findElement(By.cssSelector("span[class='idb-question-feedback-icon']"));
            String feedbackIcon = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",WE);
            if(!feedbackIcon.contains("feedback-notification-icon.png"))
                Assert.fail("The 'Comments' icon is not added beside the grades.");

            //check % complete
            List<WebElement> percentComplete = driver.findElements(By.cssSelector("div[class='idb-gradebook-content-coloumn-complete idb-gradebook-content-perc-complete']"));

            String percComplete = percentComplete.get(0).getText();
            if(!percComplete.equals("100 %")) //checked 100% beacuse the 1st student atempted all the questions
                Assert.fail("The % complete column value is not equal to the % age of attempted questions to the total no of questions.");

            //check - mark for skipped questions
            int skipped = driver.findElements(By.className("idb-question-skipped")).size();
            if(skipped == 0)
                Assert.fail("- symbol is not present for skipped questions.");

            //% complete for in progress assignment(here no question is attempted or skipped)
            String percComplete1 = percentComplete.get(4).getText();
            if(!percComplete1.equals("0 %"))
                Assert.fail("% column is not showing 0% for skipped questions.");

            //check - mark for not started questions
            int notAttempted = driver.findElements(By.className("idb-gradebook-content-coloumn-not-attempted")).size();
            if(notAttempted == 0)
                Assert.fail("- symbol is not present for not started questions");

            // check % complete  for not started assignment
            String percComplete4 = percentComplete.get(1).getText();
            if(!percComplete4.equals("0 %"))
                Assert.fail("% column is not showing 0% for not started questions.");

            //List the student names available in response page
            List<WebElement> allStudents = driver.findElements(By.className("idb-gradebook-assignment-username"));

            //check the hover on student who has submitted the question
            Locatable hoverItem1 = (Locatable) allStudents.get(0);//student @ 0th index has submitted the assignment
            Mouse mouse1 = ((HasInputDevices) driver).getMouse();
            mouse1.mouseMove(hoverItem1.getCoordinates());
            int studentHover = driver.findElements(By.id("idb-grade-now-link")).size();
            Thread.sleep(2000);
            if(studentHover != 0)
                Assert.fail("On hovering over student name who has submitted the assignmnet 'Enter Grade' option is coming for non-gradable question.");

            //check the hover on student who is in progress
            Locatable hoverItem2 = (Locatable) allStudents.get(2);//student @ 2nd and 3rd index is an in progress assignment
            Mouse mouse2 = ((HasInputDevices) driver).getMouse();
            mouse2.mouseMove(hoverItem2.getCoordinates());
            int studentHover2 = driver.findElements(By.id("idb-grade-now-link")).size();
            Thread.sleep(2000);
            if(studentHover2 != 0)
                Assert.fail("On hovering over student name who is in progress 'Enter Grade' option is coming for non-gradable question.");

            //check the hover on student who has not started
            Locatable hoverItem4 = (Locatable) allStudents.get(2);//student @ 4th index is an not started assignment
            Mouse mouse4 = ((HasInputDevices) driver).getMouse();
            mouse4.mouseMove(hoverItem4.getCoordinates());
            int studentHover4 = driver.findElements(By.id("idb-grade-now-link")).size();
            Thread.sleep(2000);
            if(studentHover4 != 0)
                Assert.fail("On hovering over student name who has not started 'Enter Grade' option is coming for non-gradable question.");

            new LoginUsingLTI().ltiLogin("21221"); //login as 21221student
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentname);
            //click on question card on RHS to view the feedback
            Thread.sleep(60000);
            driver.findElement(By.className("question-card-content")).click();
            Thread.sleep(2000);
            int feedbackBlock = driver.findElements(By.className("feedback-block")).size();
            if(feedbackBlock == 0)
                Assert.fail("The entered feedback is not reflected on student side even before the feed back is released.");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in instructorAbleToFeedbackForNonGradableAssignment in class InstructorAbleToFeedbackForNonGradableAssignment", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void releaseFeedBackForAllButtonVerfication()
    {
        try
        {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "2160");
            new Assignment().create(2160);
            new Assignment().addQuestions(2160, "qtn-type-true-false-img", assessmentname);
            new LoginUsingLTI().ltiLogin("21601"); //create student21601
            new LoginUsingLTI().ltiLogin("21602"); //create student21602
            new LoginUsingLTI().ltiLogin("2160");  //login as instructor
            new Assignment().assignToStudent(21601);
            new LoginUsingLTI().ltiLogin("2160");  //login as instructor
            new Assignment().updateAssignment(21602, true);

            new LoginUsingLTI().ltiLogin("21601"); //login as student21601
            new Assignment().submitAssignmentAsStudent(21601);

            new LoginUsingLTI().ltiLogin("21602");  //login as student21601
            new Assignment().submitAssignmentAsStudent(21602);

            new LoginUsingLTI().ltiLogin("2160");
            new Assignment().clickViewResponse(assessmentname);
            //providing feedback for all students
            List<WebElement> allViewResonse = driver.findElements(By.className("idb-gradebook-question-content"));
            for(WebElement viewResponse: allViewResonse)
            {
                new MouseHover().mouserhoverbywebelement(viewResponse);
                new Click().clickByclassname("ls-view-response-link");
                Thread.sleep(2000);

                driver.findElement(By.id("view-user-question-performance-feedback-box")).click();
                String feedbacktext = new RandomString().randomstring(5);
                driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys(feedbacktext);
                driver.findElement(By.cssSelector("span.view-user-question-performance-save-btn")).click();
                Thread.sleep(2000);
                new TabClose().tabClose(2);
                Thread.sleep(2000);
            }

            //check the color for 'Release Feedback for All'
            String color = driver.findElement(By.cssSelector("div[class='idb-gradeBook-feedback-section-release idb-gradeBook-release-feedback-section idb-gradeBook-feedback-release']")).getCssValue("background-image");
            if(!color.equals("linear-gradient(rgb(129, 194, 98), rgb(98, 175, 75))"))
                Assert.fail("The color of the 'Release feedback For all' button is not green.");

            //click on ''Release Feedback for All''
            driver.findElement(By.cssSelector("div[class='idb-gradeBook-feedback-section-release idb-gradeBook-release-feedback-section idb-gradeBook-feedback-release']")).click();
            Thread.sleep(2000);

            String text = driver.findElement(By.cssSelector("div[class='idb-gradeBook-feedback-section-release idb-gradeBook-release-feedback-section idb-gradeBook-feedback-released']")).getText();
            if(!text.equals("Feedback Released"))
                Assert.fail("The status of the assignment doesnot changed to “Feedback released” after clicking on 'Release Feedback for All'.");

            //count the 'Reviewed' status box count
            int count = new Assignment().statusBoxCount(2160, "Reviewed");
            if(count != 2)
                Assert.fail("The total student count present in the 'Reviewed' tile is not correct.");

            new LoginUsingLTI().ltiLogin("21601"); //login as a student
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentname);
            //click on question card on RHS to view the feedback
            driver.findElement(By.className("question-card-content")).click();
            Thread.sleep(2000);
            int feedbackBlock = driver.findElements(By.className("feedback-block")).size();
            if(feedbackBlock == 0)
                Assert.fail("The released feedback is not available for the student in the performance screen.");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in releaseFeedBackForAllButtonVerfication in class InstructorAbleToFeedbackForNonGradableAssignment", e);
        }
    }

}
