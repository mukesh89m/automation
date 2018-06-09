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
 * Created by Sumit on 8/28/2014.
 */
public class MentorAbleToNavigateToGradeBook extends Driver {

    @Test(priority = 1, enabled = true)
    public void mentorAbleToNavigateToGradeBook()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("40");    //login as mentor
            new Navigator().NavigateTo("Gradebook");  //go to Gradebook
            String url = driver.getCurrentUrl();

            //TC row no 40...3. Select Gradebook option...Mentor should be able to navigate to the “Gradebook” page
            if(!url.contains("gradebookSummary"))
                Assert.fail("For a mentor, On clicking \"Gradebook\" from the main navigator the mentor does not land on Grade book page");

            //TC row no 41..."Default page should display ""It seems no student has been assigned any gradable assignment." and "Please check the Gradebook after assigning at least one gradable assignment."""
            String firstMessage = driver.findElement(By.className("gradebook-summary-default-text-first")).getText();
            if(!firstMessage.equals("It seems no student has been assigned any gradable assignment."))
                Assert.fail("For a mentor, \"It seems no student has been assigned any gradable assignment.\" message is not displayed in gradebook page.");

            String secondMessage = driver.findElement(By.className("gradebook-summary-default-text-second")).getText();
            if(!secondMessage.equals("Please check the Gradebook after assigning at least one gradable assignment."))
                Assert.fail("For a mentor, \"Please check the Gradebook after assigning at least one gradable assignment.\" message is not displayed in gradebook page.");

            //TC row no 42...It should show "Gradebook" text at the top of the screen
            String pageHeader = driver.findElement(By.className("ins-gradebook-summary-header-title")).getText();
            if(!pageHeader.equals("Gradebook"))
                Assert.fail("For a mentor, Page header \"Gradebook\" is not displayed in gradebook page.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case mentorAbleToNavigateToGradeBook in class MentorAbleToNavigateToGradeBook.", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void mentorAbleToViewGradesForAllStudents()
    {
        try
        {
            String nonGradableAssignment = ReadTestData.readDataByTagName("", "assessmentname", "47");
            String assessmentname = ReadTestData.readDataByTagName("","assessmentname", "44");
            String student1Name = ReadTestData.readDataByTagName("","shareName", "44");
            String y[]=student1Name.split(" ");
            student1Name = y[1] + ", " + y[0];//reverse the name with comma in between
            String student2Name = ReadTestData.readDataByTagName("","shareName", "45");
            String b[]=student2Name.split(" ");
            student2Name = b[1] + ", " + b[0];//reverse the name with comma in between
            String student3Name = ReadTestData.readDataByTagName("","shareName", "46");
            String c[]=student3Name.split(" ");
            student3Name = c[1] + ", " + c[0];//reverse the name with comma in between
            new Assignment().create(43);//create assignment
            new Assignment().create(47);//create assignment

            new LoginUsingLTI().ltiLogin("44");    //create student1
            new LoginUsingLTI().ltiLogin("45");    //create student2
            new LoginUsingLTI().ltiLogin("46");    //create student3

            new LoginUsingLTI().ltiLogin("43");    //login as mentor

            new Assignment().assignToStudent(47);//assign to student1 as non-gradable assignment
            new Assignment().updateAssignment(48, true);//assign to student2 as non-gradable assignment

            new Assignment().assignToStudent(44);//assign to student1 as gradable assignment
            new Assignment().updateAssignment(45, true);//assign to student2 as gradable assignment



            //TC row no. 43...Gradebook should show the tabular structure to view the grades
            new Navigator().NavigateTo("Gradebook");    //go to Gradebook
            String tableHeader = new TextFetch().textfetchbyclass("ls-ins-gradebook-activity-table-header");
            if(tableHeader.indexOf("Names")>tableHeader.indexOf("Overall Score"))
                Assert.fail("For a mentor the Gradebook doesn't show the tabular structure to view the grades.");

            //TC row no. 44...Only gradeable assignment name should be displayed. Learning activity assignment should not be present
            String assignment = new TextFetch().textfetchbyclass("ls-ins-gradebook-activity-table-wrapper");
            if(assignment.contains(nonGradableAssignment))
                Assert.fail("For a mentor, Non-gradable assignment name is also displayed in Gradebook page.");

            //TC row no. 45...mentor should be able to view the students name to whom gradeable assignment is assigned
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
                Assert.fail("Mentor is not able to view the students name to whom gradeable assignment is assigned.");

            //TC row no. 46...Student who has not been assigned any gradeable assignment, name of that student should not appear in gradebook,if he/she is first student.
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
                Assert.fail("For a mentor, Student who has not been assigned any gradeable assignment, name of that student still appears in grade book,if he/she is first student.");

            //TC row no. 47...Overall Score should be displayed as "NA" for a student in the gradebook if no assignment is graded.
            List<WebElement> allOverallScore = driver.findElements(By.className("ls-ins-gradebook-overall-score"));
            if(!allOverallScore.get(3).getText().equals("NA"))
                Assert.fail("For a mentor, Overall Score is not displayed as \"NA\" for a student in the gradebook if no assignment is graded.");

            //TC row no. 48...Assignment cell should show "Grades Not Released" text which assigned to a student.
            List<WebElement> allStatus = driver.findElements(By.cssSelector("div[class='student-assignment-score-cell']"));
            if(!allStatus.get(0).getText().equals("Grades Not Released"))
                Assert.fail("For a mentor, Assignment cell does not show \"Grades Not Released\" text for those students to whom gradable assignment is assigned and Not attempted yet.");

            new LoginUsingLTI().ltiLogin("44");  //login as student1
            new Assignment().submitAssignmentAsStudent(44);//finish the assignment

            new LoginUsingLTI().ltiLogin("43");    //login as mentor
            new Navigator().NavigateTo("Gradebook");    //go to Gradebook

            List<WebElement> allStatus1 = driver.findElements(By.cssSelector("div[class='student-assignment-score-cell']"));
            if(!allStatus1.get(0).getText().equals("Grades Not Released"))
                Assert.fail("For a mentor, Assignment cell does not show \"Grades Not Released\" text for those students to whom gradable assignment is assigned and \"Completed\".");

            //TC row no. 49...Name of the students should appear in ascending order(A-Z) by default
            List<WebElement> allStudents1 = driver.findElements(By.className("ls-ins-gradebook-activity-student"));
            if(!allStudents1.get(0).getText().equals(student1Name) || !allStudents1.get(1).getText().equals(student2Name) || !allStudents1.get(2).getText().equals(student3Name))
                Assert.fail("For a mentor, Name of the students does not appear in ascending order(A-Z) by default");

            //TC row no 50...4. Click on arrow present near the "Name" text...Name of the student should sort in descending order
            driver.findElement(By.cssSelector("span[class='ls-gradebook-students-sort-icon sorted-ascending-icon']")).click();//4. Click on arrow present near the "Name" text
            List<WebElement> allStudents2 = driver.findElements(By.className("ls-ins-gradebook-activity-student"));
            if(!allStudents2.get(0).getText().equals(student3Name) || !allStudents2.get(1).getText().equals(student2Name) || !allStudents2.get(2).getText().equals(student1Name))
                Assert.fail("For a mentor, Name of the students does not appear in descending order on clicikng arrow icon near \"Name\" text.");

            //TC row no 51...5. Click on "?" icon of Overall Score..Help Text for Overall Score should display "Overall score for a student shows the average performance for ALL gradable assignments assigned to him/her. "
            driver.findElement(By.cssSelector("div[class='ls-ins-gradebook-activity-table-icons ls-ins-gradebook-help-img']")).click();//5. Click on "?" icon of Overall Score
            String helpData = new TextFetch().textfetchbyclass("help-data-container");
            if(!helpData.contains("Overall score for a student shows the average performance for ALL gradable assignments assigned to him/her."))
                Assert.fail("For a mentor, Help text does not appear properly once we click on \"?\" icon of Overall Score.");


            new LoginUsingLTI().ltiLogin("45");  //login as student2
            new Assignment().submitAssignmentAsStudent(45);//finish the assignment

            new LoginUsingLTI().ltiLogin("43");    //login as mentor
            new Assignment().releaseGrades(43, "Release Grade for All");  //release the grades
            new Navigator().NavigateTo("Gradebook");    //go to Gradebook

            //TC row no. 52..."Each cell for assignment should have the format: student score / Total score"
            List<WebElement> allScore = driver.findElements(By.cssSelector("div[class='student-assignment-score-cell']"));
            if(!allScore.get(0).getText().equals("1/1"))
                Assert.fail("For a mentor, Each cell for assignment should have the format: student score / Total score in Gradebook page.");

            List<WebElement> overallScores = driver.findElements(By.cssSelector("span[class='ls-ins-gradebook-overall-score']"));
            //TC row no. 54...Overall Score should have "%" sign
            if(!overallScores.get(1).getText().equals("100%"))
                Assert.fail("For a mentor, Each cell for assignment should have the format: student score / Total score in Gradebook page.");

            //TC row no. 56...9. Click on arrow present near the "Overall Score" text for sorting..Overall Score should sort in ascending/descending order
            driver.findElement(By.cssSelector("span[class='ls-gradebook-students-sort-icon sorted-ascending-icon']")).click();//9. Click on arrow present near the "Overall Score" text for sorting
            List<WebElement> overallScores1 = driver.findElements(By.cssSelector("span[class='ls-ins-gradebook-overall-score']"));
            if(!overallScores1.get(1).getText().equals("NA"))
                Assert.fail("For a mentor, Overall Score does not sort in ascending/descending order on clicking arrow present near the \"Overall Score\" text for sorting.");

            //TC row no. 57...10. Click on any Assignment name...mentor should navigate to Assignment Responses page
            driver.findElement(By.cssSelector("span[class='ls-ins-gradebook-activity-midterm ellipsis']")).click();//click on Assignment name
            Thread.sleep(2000);
            String tab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
            if(!tab.contains("Response - "+assessmentname))
                Assert.fail("For a mentor, Clicking on Assignment name from Gradebook page the mentor does not navigate to Assignment Responses page.");

            //TC row no. 59...11. Click on the checkbox of specific student...“Post a message” button should appear.
            List<WebElement> allCheckBox = driver.findElements(By.xpath("//*[starts-with(@class, 'idb-Reviewed-icon-checkbox idb-Reviewed-')]"));
            allCheckBox.get(0).click();//check the first checkbox

            //TC row no. 60...12. Click on Post a message button..."Post a message" pop-up should appear...
            new Click().clickByclassname("ls-post-a-message");//click on Post a Message
            int messagePopUp = driver.findElements(By.id("sendMailDialog")).size();
            if(messagePopUp == 0)
                Assert.fail("For a mentor, Clicking on Post a message button the \"Post a message\" pop-up does not appear.");

            //TC row no. 62...13. Enter a text and send a message to a student...Message entry should appear in Course Stream
            String text = new RandomString().randomstring(10);
            driver.findElement(By.className("email-message-content")).sendKeys(text);//type message
            new Click().clickByid("send-mail");//click on Send button
            new Navigator().NavigateTo("Course Stream");
            String messageEntry = driver.findElement(By.className("ls-stream-post__action")).getText();
            if(!messageEntry.contains("posted a message"))
                Assert.fail("On posting a message by the mentor for a particular student the message entry does appear in Course Stream.");

            //TC row no. 61...Message should sent successfully
            new LoginUsingLTI().ltiLogin("44");  //login as student1
            new Navigator().NavigateTo("Course Stream");
            String messageEntry1 = driver.findElement(By.className("ls-stream-post__action")).getText();
            if(!messageEntry1.contains("posted a message"))
                Assert.fail("On posting a message by the mentor for a particular student the message entry does not appear to the student.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case mentorAbleToViewGradesForAllStudents in class MentorAbleToNavigateToGradeBook.", e);
        }
    }

}
