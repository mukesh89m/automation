package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R165;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Sumit on 8/20/2014.
 */
public class InstructorAbleToNavigateToGradeBook extends Driver {

    @Test(priority = 1, enabled = true)
    public void instructorAbleToNavigateToGradeBook()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("2");    //login as instructor

            //TC row no 2...Gradebook option should be present in the main navigator drodpwon
            new Navigator().NavigateTo("Gradebook");  //go to Gradebook
            String url = driver.getCurrentUrl();

            //TC row no 3...3. Select Gradebook option...Instructor should be able to navigate to the “Gradebook” page
            if(!url.contains("gradebookSummary"))
                Assert.fail("On clicking \"Gradebook\" from the main navigator the instructor does not land on Grade book page");

            //TC row no 4..."Default page should display ""It seems no student has been assigned any gradable assignment." and "Please check the Gradebook after assigning at least one gradable assignment."""
            String firstMessage = driver.findElement(By.className("gradebook-summary-default-text-first")).getText();
            if(!firstMessage.equals("It seems no student has been assigned any gradable assignment."))
                Assert.fail("\"It seems no student has been assigned any gradable assignment.\" message is not displayed in gradebook page.");

            String secondMessage = driver.findElement(By.className("gradebook-summary-default-text-second")).getText();
            if(!secondMessage.equals("Please check the Gradebook after assigning at least one gradable assignment."))
                Assert.fail("\"Please check the Gradebook after assigning at least one gradable assignment.\" message is not displayed in gradebook page.");

            //TC row no 4...It should show "Gradebook" text at the top of the screen
            String pageHeader = driver.findElement(By.className("ins-gradebook-summary-header-title")).getText();
            if(!pageHeader.equals("Gradebook"))
                Assert.fail("Page header \"Gradebook\" is not displayed in gradebook page.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case instructorAbleToNavigateToGradeBook in class InstructorAbleToNavigateToGradeBook.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void instructorAbleToViewGradesForAllStudents()
    {
        try
        {
            String nonGradableAssignment = ReadTestData.readDataByTagName("","assessmentname", "10");
            String assessmentname = ReadTestData.readDataByTagName("","assessmentname", "7");
            String student1Name = ReadTestData.readDataByTagName("","shareName", "7");
            String y[]=student1Name.split(" ");
            student1Name = y[1] + ", " + y[0];//reverse the name with comma in between

            String student2Name = ReadTestData.readDataByTagName("","shareName", "8");
            String b[]=student2Name.split(" ");
            student2Name = b[1] + ", " + b[0];//reverse the name with comma in between
            String student3Name = ReadTestData.readDataByTagName("","shareName", "9");
            String c[]=student3Name.split(" ");
            student3Name = c[1] + ", " + c[0];//reverse the name with comma in between
            new Assignment().create(6);//create assignment
            new Assignment().create(10);//create assignment

            new LoginUsingLTI().ltiLogin("7");    //create student1
            new LoginUsingLTI().ltiLogin("8");    //create student2
            new LoginUsingLTI().ltiLogin("9");    //create student3

            new LoginUsingLTI().ltiLogin("6");    //login as instructor

            new Assignment().assignToStudent(10);//assign to student1 as non-gradable assignment
            new Assignment().updateAssignment(11, true);//assign to student2 as non-gradable assignment

            new Assignment().assignToStudent(7);//assign to student1 as gradable assignment
            new Assignment().updateAssignment(8, true);//assign to student2 as gradable assignment



            //TC row no. 6...Gradebook should show the tabular structure to view the grades
            new Navigator().NavigateTo("Gradebook");    //go to Gradebook
            String tableHeader = new TextFetch().textfetchbyclass("ls-ins-gradebook-activity-table-header");
            if(tableHeader.indexOf("Names")>tableHeader.indexOf("Overall Score"))
                Assert.fail("Gradebook doesnt show the tabular structure to view the grades");

            //TC row no. 7...Only gradeable assignment name should be displayed. Learning activity assignment should not be present
            String assignment = new TextFetch().textfetchbyclass("ls-ins-gradebook-activity-table-wrapper");
            if(assignment.contains(nonGradableAssignment))
                Assert.fail("Non-gradable assignment name is also displayed in Gradebook page.");

            //TC row no. 8...Instructor should be able to view the students name to whom gradeable assignment is assigned
            boolean namePresent1 = false;
            List<WebElement> allStudents = driver.findElements(By.className("ls-ins-gradebook-activity-student"));
            for(WebElement student1: allStudents)
            {
                if(student1.getText().equals(student1Name))//look for student 1 in the list
                {
                    namePresent1 = true;
                    break;
                }
            }
            boolean namePresent2 = false;
            for(WebElement student2: allStudents)
            {
                if(student2.getText().equals(student2Name))//look for student 2 in the list
                {
                    namePresent2 = true;
                    break;
                }
            }
            if(!namePresent1 || !namePresent2)
                Assert.fail("Instructor is not able to view the students name to whom gradeable assignment is assigned.");

            //TC row no. 9...Name of all the students should appear in the Gradebook page as soon as atleast one assignment is assigned to any one student or also for all students.
            boolean namePresent3 = false;
            for(WebElement student3: allStudents)
            {
                if(student3.getText().equals(student3Name))//look for student 3 in the list to whom no assignment is assigned
                {
                    namePresent3 = true;
                    break;
                }
            }
            if(!namePresent3)
                Assert.fail("Name of all the students does not appear in the Gradebook page as soon as atleast one assignment is assigned to any one student or also for all students.");

            //TC row no. 10...Overall Score should be displayed as "NA" for each student in the gradebook if that assignment is not assigned for that student.
            List<WebElement> allOverallScore = driver.findElements(By.className("ls-ins-gradebook-overall-score"));
            if(!allOverallScore.get(3).getText().equals("NA"))
                Assert.fail("Overall Score is not displayed as \"NA\" for those student in the gradebook if that assignment is not assigned for that student.");

            //TC row no. 11...Assignment cell should show "Not Graded" text for those students to whom gradable assignment is assigned and Not attempted yet.
            List<WebElement> allStatus = driver.findElements(By.cssSelector("div[class='student-assignment-score-cell']"));
            if(!allStatus.get(0).getText().equals("Grades Not Released"))
                Assert.fail("Assignment cell does not show \"Grades Not Released\" text for those students to whom gradable assignment is assigned and Not attempted yet.");

            new LoginUsingLTI().ltiLogin("7");  //login as student1
            new Assignment().submitAssignmentAsStudent(7);//finish the assignment

            new LoginUsingLTI().ltiLogin("6");    //login as instructor
            new Navigator().NavigateTo("Gradebook");    //go to Gradebook

            //TC row no. 12...Assignment cell should show "Not Graded" text for those students to whom gradable assignment is assigned and "Completed".
            List<WebElement> allStatus1 = driver.findElements(By.cssSelector("div[class='student-assignment-score-cell']"));
            if(!allStatus1.get(0).getText().equals("Grades Not Released"))
                Assert.fail("Assignment cell does not show \"Grades Not Released\" text for those students to whom gradable assignment is assigned and \"Completed\".");

            //TC row no. 14...Name of the students should appear in ascending order(A-Z) by default
            List<WebElement> allStudents1 = driver.findElements(By.className("ls-ins-gradebook-activity-student"));
            if(!allStudents1.get(0).getText().equals(student1Name) || !allStudents1.get(1).getText().equals(student2Name) || !allStudents1.get(2).getText().equals(student3Name))
                Assert.fail("Name of the students does not appear in ascending order(A-Z) by default");

            //TC row no 15...4. Click on arrow present near the "Name" text...Name of the student should sort in descending order
            driver.findElement(By.cssSelector("span[class='ls-gradebook-students-sort-icon sorted-ascending-icon']")).click();//4. Click on arrow present near the "Name" text
            List<WebElement> allStudents2 = driver.findElements(By.className("ls-ins-gradebook-activity-student"));
            if(!allStudents2.get(0).getText().equals(student3Name) || !allStudents2.get(1).getText().equals(student2Name) || !allStudents2.get(2).getText().equals(student1Name))
                Assert.fail("Name of the students does not appear in descending order on clicikng arrow icon near \"Name\" text.");

            //TC row no 16...5. Click on "?" icon of Overall Score..Help Text for Overall Score should display "Overall score for a student shows the average performance for ALL gradable assignments assigned to him/her. "
            driver.findElement(By.cssSelector("div[class='ls-ins-gradebook-activity-table-icons ls-ins-gradebook-help-img']")).click();//5. Click on "?" icon of Overall Score
            String helpData = new TextFetch().textfetchbyclass("help-data-container");
            if(!helpData.contains("Overall score for a student shows the average performance for ALL gradable assignments assigned to him/her."))
                Assert.fail("Help text does not appear properly once we click on \"?\" icon of Overall Score.");


            new LoginUsingLTI().ltiLogin("8");  //login as student2
            new Assignment().submitAssignmentAsStudent(8);//finish the assignment

            new LoginUsingLTI().ltiLogin("6");    //login as instructor
            new Assignment().releaseGrades(7, "Release Grade for All");  //release the grades
            new Navigator().NavigateTo("Gradebook");    //go to Gradebook
            //TC row no. 17..."Each cell for assignment should have the format: student score / Total score"
            List<WebElement> allScore = driver.findElements(By.cssSelector("div[class='student-assignment-score-cell']"));
            if(!allScore.get(0).getText().equals("1/1"))
                Assert.fail("Each cell for assignment should have the format: student score / Total score in Gradebook page.");

            List<WebElement> overallScores = driver.findElements(By.cssSelector("span[class='ls-ins-gradebook-overall-score']"));
            //TC row no. 19...Overall Score should have "%" sign
            if(!overallScores.get(1).getText().equals("100%"))
                Assert.fail("Each cell for assignment should have the format: student score / Total score in Gradebook page.");

            //TC row no. 21...9. Click on arrow present near the "Overall Score" text for sorting..Overall Score should sort in ascending/descending order
            driver.findElement(By.cssSelector("span[class='ls-gradebook-students-sort-icon sorted-ascending-icon']")).click();//9. Click on arrow present near the "Overall Score" text for sorting
            List<WebElement> overallScores1 = driver.findElements(By.cssSelector("span[class='ls-ins-gradebook-overall-score']"));
            if(!overallScores1.get(1).getText().equals("NA"))
                Assert.fail("Overall Score does not sort in ascending/descending order on clicking arrow present near the \"Overall Score\" text for sorting.");

            //TC row no. 22...10. Click on any Assignment name...Instructor should navigate to Assignment Responses page
            driver.findElement(By.cssSelector("span[class='ls-ins-gradebook-activity-midterm ellipsis']")).click();//click on Assignment name
            Thread.sleep(3000);
            String tab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
            if(!tab.contains("Response - "+assessmentname))
                Assert.fail("Clicking on Assignment name from Gradebook page the instructor does not navigate to Assignment Responses page.");

            //TC row no. 24...11. Click on the checkbox of specific student...“Post a message” button should appear.
            List<WebElement> allCheckBox = driver.findElements(By.xpath("//*[starts-with(@class, 'idb-Reviewed-icon-checkbox idb-Reviewed-')]"));
            allCheckBox.get(0).click();//check the first checkbox

            //TC row no. 25..."Post a message" pop-up should appear...
            new Click().clickByclassname("ls-post-a-message");//click on Post a Message
            int messagePopUp = driver.findElements(By.id("sendMailDialog")).size();
            if(messagePopUp == 0)
                Assert.fail("Clicking on Post a message button the \"Post a message\" pop-up does not appear.");

            //TC row no. 27...13. Enter a text and send a message to a student...Message entry should appear in Course Stream
            String text = new RandomString().randomstring(10);
            driver.findElement(By.className("email-message-content")).sendKeys(text);//type message
            new Click().clickByid("send-mail");//click on Send button
            new Navigator().NavigateTo("Course Stream");
            String messageEntry = driver.findElement(By.className("ls-stream-post__action")).getText();
            if(!messageEntry.contains("posted a message"))
                Assert.fail("On posting a message for a particular student the message entry does appear in Course Stream.");

            //TC row no. 26...Message should sent successfully
            new LoginUsingLTI().ltiLogin("7");  //login as student1
            new Navigator().NavigateTo("Course Stream");
            String messageEntry1 = driver.findElement(By.className("ls-stream-post__action")).getText();
            if(!messageEntry1.contains("posted a message"))
                Assert.fail("On posting a message for a particular student the message entry does not appear to the student.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case instructorAbleToViewGradesForAllStudents in class InstructorAbleToNavigateToGradeBook.", e);
        }
    }

}
