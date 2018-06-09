package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;

public class VerifyFiltersOnAllAssignment extends Driver
{
    @Test
    public void verifyFiltersOnAllAssignment()
    {
        try
        {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "1876");
            String assessmentname1 = ReadTestData.readDataByTagName("", "assessmentname", "18761");

            new Assignment().create(1876);
            new Assignment().createAssignmentAtTopicLevel(18761);

            new LoginUsingLTI().ltiLogin("1876");
            new Navigator().NavigateTo("Assignments");

            //Finding the all dropdown
            List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
            String firstDropdown = allElements.get(1).getText();
            String secondDropdown = allElements.get(2).getText();
            String thirdDropdown = allElements.get(3).getText();
            String fourthDropdown = allElements.get(4).getText();
            if(!firstDropdown.equals("All Chapters") || !secondDropdown.equals("All Sections") || !thirdDropdown.equals("All Assignment Status") || !fourthDropdown.equals("All Activity"))
            {
                Assert.fail("All The DropDown for filter is not present");
            }

            allElements.get(1).click();
            Thread.sleep(3000);

            allElements.get(3).click();
            Thread.sleep(3000);
            List<WebElement> allElementsOfThirdDropdown = driver.findElements(By.xpath("//*[starts-with(@class, 'sbOptions')]"));
            String thirdDropDownOptions = allElementsOfThirdDropdown.get(3).getText().replaceAll("[\n\r]", "");

            if(!thirdDropDownOptions.equals("Available for StudentsNeeds GradingReview in progressGradedReviewedScheduled"))
            {
                Assert.fail("All statuses are not present under option 'All Status'");
            }

            allElements.get(4).click();
            Thread.sleep(3000);
            List<WebElement> allElementsOfFourthDropdown = driver.findElements(By.xpath("//*[starts-with(@class, 'sbOptions')]"));
            String fourthDropDownOptions = allElementsOfFourthDropdown.get(4).getText().replaceAll("[\n\r]", "");

            if(!fourthDropDownOptions.equals("Question AssignmentQuestion PracticeDiscussion AssignmentLearning Activity"))
            {
                Assert.fail("All activities are not present under option 'Assignment Status'");
            }
            new Assignment().assignToStudent(1876);
            new Assignment().assignToStudent(18761);
            driver.findElement(By.linkText("All Chapters")).click();
            Thread.sleep(3000);
            driver.findElement(By.partialLinkText("Ch 1:")).click(); //selecting first chapter from 'All chapters' dropdown
            Thread.sleep(3000);
            List<String> stringarray = new ArrayList<String>();
            List<WebElement> assignments =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for(WebElement element : assignments)
            {
                stringarray.add(element.getText());
            }
            String [] optionspresent = stringarray.toArray(new String[stringarray.size()]);
            boolean found = false;
            for (int i = 0; i < optionspresent.length; i++)
            {
                if(optionspresent[i].contains(assessmentname))
                {
                    found = true;
                }

            }
            if(found == false)
                Assert.fail("On filtering assignment is not displayed which is available under particular chapter");

            driver.findElement(By.linkText("All Sections")).click();
            Thread.sleep(3000);
            driver.findElement(By.linkText("Introduction")).click();  //select 1st option from 'All Sections' dropdown
            Thread.sleep(3000);
            stringarray.clear();
            List<WebElement> assignments1 =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for(WebElement element : assignments1)
            {
                stringarray.add(element.getText());
            }
            String [] optionspresent1 = stringarray.toArray(new String[stringarray.size()]);
            boolean found1 = false;
            boolean found_notpresentassignment = false;
            for (int i = 0; i < optionspresent1.length; i++)
            {
                if(optionspresent1[i].contains(assessmentname1))
                {
                    found1 = true;
                }
                if(optionspresent1[i].contains(assessmentname))
                {
                    found_notpresentassignment = true;
                }

            }
            if(found1 == false)
                Assert.fail("On filtering assignment is not displayed which is available under particular topic");
            if(found_notpresentassignment == true)
                Assert.fail("On filtering assignment which is not available under particular topic is also displayed");
            new ComboBox().selectValue(3, "Available for Students");   //click a status
            Thread.sleep(3000);
            stringarray.clear();
            List<WebElement> assignments2 =  driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for(WebElement element : assignments2)
            {
                stringarray.add(element.getText());
            }
            String [] optionspresent2 = stringarray.toArray(new String[stringarray.size()]);
            boolean found2 = false;
            boolean found2_notpresentassignment = false;
            for (int i = 0; i < optionspresent2.length; i++)
            {
                if(optionspresent2[i].contains(assessmentname1))
                    found2 = true;

                if(optionspresent2[i].contains(assessmentname))
                    found2_notpresentassignment = true;

            }
            if(found2 == false)
                Assert.fail("On selecting a status 'Available for Students' the assignment is not displayed.");
            if(found2_notpresentassignment == true)
                Assert.fail("On selecting a status 'Available for Students' the assignment which is not fulfilling the filter criteria is also displayed.");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in verifyFiltersOnAllAssignment in class VerifyFiltersOnAllAssignment", e);
        }
    }

}
