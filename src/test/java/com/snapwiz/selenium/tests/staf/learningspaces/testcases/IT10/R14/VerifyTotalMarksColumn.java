package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class  VerifyTotalMarksColumn extends Driver{
    @Test
    public void verifyTotalMarksColumn()
    {
        try
        {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "2029");
            String studentname = ReadTestData.readDataByTagName("", "studentname", "20293");
            new Assignment().create(2029);
            new Assignment().addQuestions(2029, "qtn-type-true-false-img", assessmentname);
            new LoginUsingLTI().ltiLogin("20291");//creating student with id 20291student
            new LoginUsingLTI().ltiLogin("20292");//creating student with id 20292student
            new LoginUsingLTI().ltiLogin("20293");
            new LoginUsingLTI().ltiLogin("20294");
            new LoginUsingLTI().ltiLogin("2029");
            new Assignment().assignToStudent(20291);
            new LoginUsingLTI().ltiLogin("2029");
            new Assignment().updateAssignment(20292, true);
            new LoginUsingLTI().ltiLogin("2029");
            new Assignment().updateAssignment(20293, true);
            new LoginUsingLTI().ltiLogin("2029");
            new Assignment().updateAssignment(20294, true);
            new LoginUsingLTI().ltiLogin("20291");
            new Assignment().submitAssignmentAsStudent(20291);
            new LoginUsingLTI().ltiLogin("20292");
            new Assignment().submitAssignmentAsStudent(20292);
            new LoginUsingLTI().ltiLogin("2029");
            new Navigator().NavigateTo("Assignments");
            new Assignment().provideGradeToStudentForMultipleQuestions(20291);
            new Click().clickByclassname("ls-grade-book-assessment");//clicking on view responses link
            //Verify the total marks
            float totalMark = 0;
            List<WebElement> allGradeBox = driver.findElements(By.cssSelector("span[class='idb-gradebook-question-content idb-question-partial-scored ']"));

            //adding the indiviual marks for each question
            totalMark = Float.parseFloat(allGradeBox.get(0).getText()) + Float.parseFloat(allGradeBox.get(1).getText());
            //TotalMark in the 'Total Mark' column
            String mark = driver.findElement(By.cssSelector("div[class='idb-gradebook-content-coloumn-total-score idb-gradebook-content-total']")).getText();
            float totalMarkFloat = Float.parseFloat(mark);
            if(totalMarkFloat != totalMark)
            {
                Assert.fail("The total marks is not equal to the sum of the marks for the individual questions.");
            }

            //login as a student and open the assessment but attempt none
            new LoginUsingLTI().ltiLogin("20293");
            new Navigator().NavigateTo("Course Stream");
            driver.findElement(By.cssSelector("span[class='ls-stream-assignment-title']")).click();
            Thread.sleep(3000);
            new LoginUsingLTI().ltiLogin("2029");
            new Assignment().clickViewResponse(assessmentname);
            //For student attempting the Assignment
            List<WebElement> allTotalMarkColumn = driver.findElements(By.cssSelector("div[class='idb-gradebook-content-coloumn-total-score idb-gradebook-content-total']"));
            //'Total Marks' column for student who is 'In Progess'
            String total = allTotalMarkColumn.get(3).getText();
            if(!total.equals("0.0"))
                Assert.fail("The total marks column value is not 0 for the student who 'In Progress'.");
            //'Total Marks' column for student who has 'Not Started'
            String total1 = allTotalMarkColumn.get(3).getText();
            if(!total1.equals("0.0"))
                Assert.fail("The total marks column value is not 0 for the student who has 'Not Started'.");

            //List the clickable element
            List<WebElement> menuitem = driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            for(WebElement user : menuitem)
            {
                if(user.getText().equals(studentname))
                {
                    Locatable hoverItem = (Locatable) user;
                    Mouse mouse = ((HasInputDevices) driver).getMouse();
                    mouse.mouseMove(hoverItem.getCoordinates());
                    List<WebElement> gradenowlinks = driver.findElements(By.id("idb-grade-now-link"));
                    int size = gradenowlinks.size();
                    if(size != 2)     //size != 2 has been used because there will be two 'idb-grade-now-link' element in this particular case
                        Assert.fail("The instructor is able to modify the marks for the student who is 'In progress'.");
                }
            }
            //Listing '% Complete' column
            List<WebElement> allPercentComplete = driver.findElements(By.cssSelector("div[class='idb-gradebook-content-coloumn-complete idb-gradebook-content-perc-complete']"));
            String percentage = allPercentComplete.get(0).getText();//Student who has submitted the assessment
            if(!percentage.equals("100 %"))
                Assert.fail("The % complete column value is not equal to the % age of attempted questions to the total no of questions.");

            String percentage1 = allPercentComplete.get(2).getText();//Student who is in progress
            if(!percentage1.equals("0 %"))
                Assert.fail("The % complete column value is not 0 for the 'In progress' student.");

            String percentage2 = allPercentComplete.get(3).getText();//Student who has 'Not Started'
            if(!percentage2.equals("0 %"))
                Assert.fail("The % complete column value is not 0 for the 'Not Started' student.");

            //List the DASH symbol for the unattempted questions
            List<WebElement> allDashSymbol = driver.findElements(By.cssSelector("span[class='idb-question-skipped']"));
            String dashForInProgess = allDashSymbol.get(0).getCssValue("background-color");//'In Progress' Assignment's element
            if(!dashForInProgess.equals("rgba(133, 133, 133, 1)"))
                Assert.fail("The '-' sign is not displayed for all the questions for 'In progess' assignment.");

            String dashForInProgess1 = allDashSymbol.get(4).getCssValue("background-color");//'Not Started' Assignment's element
            if(!dashForInProgess1.equals("rgba(133, 133, 133, 1)"))
                Assert.fail("The '-' sign is not displayed for all the questions for 'Not Started' assignment.");

            //List the Q1, Q2 etc
            List<WebElement> allQuestions = driver.findElements(By.cssSelector("div[class='idb-gradebook-content-coloumn idb-gradebook-question-coloumn']"));
            allQuestions.get(0).click();//click on 1st question link(Q1)
            Thread.sleep(3000);
            String activeTab = driver.findElement(By.cssSelector("div[class='question-label']")).getText();
            if(!activeTab.contains("Q1")) //This is changed to assignment name - Bug logged for it for displaying all the questions at once - 5896
                Assert.fail("Instructor does not navigate to the student view  with the specific question in the tab view.");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in verifyTotalMarksColumn in class VerifyTotalMarksColumn", e);
        }
    }

}
