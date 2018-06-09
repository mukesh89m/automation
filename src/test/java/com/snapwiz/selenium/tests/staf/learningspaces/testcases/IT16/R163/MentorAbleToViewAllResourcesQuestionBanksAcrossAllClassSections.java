package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R163;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by Sumit on 9/3/2014.
 */
public class MentorAbleToViewAllResourcesQuestionBanksAcrossAllClassSections extends Driver{

    @Test
    public void mentorAbleToViewAllResourcesQuestionBanksAcrossAllClassSections()
    {
        try
        {

            String resourcePolicy = ReadTestData.readDataByTagName("", "resourcesname", "105_1");
            String resourceAssignment = ReadTestData.readDataByTagName("", "resourcesname", "105");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "105");
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", "105");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "105");
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new Assignment().create(105);//create assignment
            new Assignment().addQuestionsWithCustomizedQuestionText(105, "qtn-type-true-false-img", assessmentname, 2);
            /*
            "1. Login as an instructor
            2. Go to Assignment Summary page
            3. Click on ""+New Assignment"" page"
            */
            new LoginUsingLTI().ltiLogin("105");    //login as mentor
            new LoginUsingLTI().ltiLogin("105_1");    //login as same mentor of class section A
            new Navigator().NavigateTo("Summary");
            new Click().clickByclassname("instructor-assignment-new-txt");//3. Click on "+New Assignment" button
            currentAssignments.getUsePreCreatedAssignment_button().click();//click on Use Pre-created Assignment on Create New assignment pop-up

            //TC row no. 103... mentor should get navigated to "Question Bank" page
            String tabName = new TextFetch().textfetchbycssselector("div[class='tab active']");
            if(!tabName.contains("Question Banks"))
                Assert.fail("Click on \"+New Assignment\" page mentor doesn't get navigated to \"Question Bank\" page");


            new UploadResources().uploadResources("105", true, false, true);//upload resource as assignment

            new UploadResources().uploadResources("105_1", false, false, false);//upload resource as grading policy

            new Navigator().NavigateTo("Resources");//navigate to resource
            new Click().clickBycssselector("div[data-id='my-resources']");//click on my Library tab

            List<WebElement> allText = driver.findElements(By.className("resource-title"));
            //TC row no. 105...Grading Policy should be present in My Library
            String appendResources=resourcePolicy+LoginUsingLTI.appendChar;
            if(!allText.get(0).getText().equals(appendResources))
                Assert.fail("Grading Policy is not present in My Library");

            //TC row no. 105...Uploaded Resource should appear in My library
            String appendResources1=resourceAssignment+LoginUsingLTI.appendChar;
            if(!allText.get(1).getText().equals(appendResources1))
                Assert.fail("Uploaded Resource should appear in My library");


            //TC row no. 106...Bookmarked resource should be present in My Library
            driver.findElement(By.cssSelector("span[title='All Resources']")).click();//go to All Resources tab
            new Click().clickBycssselector("span[title='Add to My Resources']");//bookmark a resource
            new Click().clickBycssselector("div[data-id='my-resources']");//click on my Library tab
            String resource = driver.findElement(By.className("resource-title")).getText();
            if(resource.equals(resourcePolicy))
                Assert.fail("Bookmarked resource is not present in My Library.");
            /*
            "7. Go to Assignment->Question Bank page
            8. Create a custom assignment
            9. Bookmark a assessment present in the Question Ban tab
            10. Go to My Library tab of question banks page"
             */

            //TC row no. 107...Custom assignment should be present in My Library
            new CreateCustomAssignment().createCustomAssignment(questiontext, "105");
            new Navigator().NavigateTo("Question Banks");//navigate to Question Banks
            new Click().clickBycssselector("div[data-id='my-resources']");//click on my Library tab
            String customAssignment = driver.findElement(By.className("resource-title")).getText();
            if(!customAssignment.equals(customassignmentname))
                Assert.fail("Custom assignment is not present in My Library.");

            //TC row no. 108...Bookmarked assessment should be present in My Library
            new Navigator().NavigateTo("Question Banks");//navigate to Question Banks
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assessmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(3000);
            new Click().clickBycssselector("span[class='addThisToMyResources bookmark-label']");//bookmark a assessment
            new Click().clickBycssselector("div[data-id='my-resources']");//click on my Library tab
            String bookmarkedAssignment = driver.findElement(By.className("resource-title")).getText();
            if(!bookmarkedAssignment.equals(assessmentname))
                Assert.fail("Bookmarked assessment from Question bank is not present in My Library.");


            new LoginUsingLTI().ltiLogin("105");    //login as same mentor of class section B
            new Navigator().NavigateTo("Resources");//navigate to resource
            new Click().clickBycssselector("div[data-id='my-resources']");//click on my Library tab

            List<WebElement> allText1 = driver.findElements(By.className("resource-title"));
            //TC row no. 111...Resource bookmarked by mentor in class section A should appear in My library of class section B
            if(allText1.get(0).getText().equals(resourcePolicy) || allText1.get(0).getText().equals(resourceAssignment))
                Assert.fail("Resource bookmarked by mentor in class section A does not appear in My library of class section B");

            //TC row no. 110...Grading Policy created in class section A by mentor should appear in My library of class section B
            String appendResources2=resourcePolicy+LoginUsingLTI.appendChar;
            if(!allText1.get(1).getText().equals(appendResources2))
                Assert.fail("Grading Policy created in class section A by mentor does not appear in My library of class section B.");

            //TC row no. 109...Resource uploaded in class section A by mentor should appear in My library of class section B
            String appendResources3=resourceAssignment+LoginUsingLTI.appendChar;
            if(!allText1.get(2).getText().equals(appendResources3))
                Assert.fail("Resource uploaded in class section A by mentor does not appear in My library of class section B");

            new Navigator().NavigateTo("Question Banks");//navigate to Question Banks
            new Click().clickBycssselector("div[data-id='my-resources']");//click on my Library tab
            List<WebElement> allText2 = driver.findElements(By.className("resource-title"));
            //TC row no. 113...Assessment bookmarked by mentor in class section A should appear in My library of class section B
            if(!allText2.get(0).getText().equals(assessmentname))
                Assert.fail("Assessment bookmarked by mentor in class section A does not appear in My library of class section B");

            //TC row no. 112...Custom assignment created by mentor in class section A should appear in My Library of class section B
            if(!allText2.get(1).getText().equals(customassignmentname))
                Assert.fail("Custom assignment created by mentor in class section A does not appear in My Library of class section B");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case mentorAbleToViewAllResourcesQuestionBanksAcrossAllClassSections in class MentorAbleToViewAllResourcesQuestionBanksAcrossAllClassSections.", e);
        }
    }

}
