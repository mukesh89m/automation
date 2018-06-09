package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R163;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by Sumit on 9/1/2014.
 */
public class InstructorAbleToViewAllResourcesQuestionBanksAcrossAllClassSections extends Driver{

    @Test
    public void instructorAbleToViewAllResourcesQuestionBanksAcrossAllClassSections()
    {
        try
        {

            String resourcePolicy = ReadTestData.readDataByTagName("", "resourcesname", "104_1");
            String resourceAssignment = ReadTestData.readDataByTagName("", "resourcesname", "104");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "104");
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", "104");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "104");
            new Assignment().create(104);//create assignment
            new Assignment().addQuestionsWithCustomizedQuestionText(104, "qtn-type-true-false-img", assessmentname, 2);

            /* "1. Login as an instructor
            2. Go to Assignment Summary page
            3. Click on ""+New Assignment"" page"
            */
            new LoginUsingLTI().ltiLogin("104");    //login as instructor
            new LoginUsingLTI().ltiLogin("104_1");    //login as same instructor of class section A
            new Navigator().NavigateTo("Summary");
            new Click().clickByclassname("instructor-assignment-new-txt");//3. Click on "+New Assignment" button
            new Click().clickBycssselector("div[class='ls-inst-dashboard-assignment-popup-button ls--question-banks-view']"); //click on "use precreated assignment" in create new assignment pop up
            //TC row no. 103... Instructor should get navigated to "Question Bank" page
            String tabName = new TextFetch().textfetchbycssselector("div[class='tab active']");
            if(!tabName.contains("Question Banks"))
                Assert.fail("Click on \"+New Assignment\" page Instructor doesn't get navigated to \"Question Bank\" page");


            new UploadResources().uploadResources("104", true, false, true);//upload resource as assignment

            new UploadResources().uploadResources("104_1", false, false, false);//upload resource as grading policy*/

            new Navigator().NavigateTo("Resources");//navigate to resource
            new Click().clickBycssselector("div[data-id='my-resources']");//click on my Library tab

            List<WebElement> allText = driver.findElements(By.className("resource-title"));
            //TC row no. 105...Grading Policy should be present in My Librar
            String appendResources=resourcePolicy+LoginUsingLTI.appendChar;
            if(!allText.get(0).getText().equals(appendResources))
                Assert.fail("Grading Policy is not present in My Library");

            //TC row no. 104...Uploaded Resource should appear in My library
            String appendResources1=resourceAssignment+LoginUsingLTI.appendChar;
            if(!allText.get(1).getText().equals(appendResources1))
                Assert.fail("Uploaded Resource should appear in My library");


            //TC row no. 106...Bookmarked resource should be present in My Library
            driver.findElement(By.cssSelector("span[title='All Resources']")).click();//go to All Resources tab
            new Click().clickBycssselector("span[class='addThisToMyResources bookmark-label']");//bookmark a resource
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
            new CreateCustomAssignment().createCustomAssignment(questiontext, "104");
            new Navigator().NavigateTo("Question Banks");//navigate to Question Banks
            new Click().clickBycssselector("div[data-id='my-resources']");//click on my Library tab
            String customAssignment = driver.findElement(By.className("resource-title")).getText();
            if(!customAssignment.equals(customassignmentname))
                Assert.fail("Custom assignment is not present in My Library.");

            //TC row no. 108...Bookmarked assessment should be present in My Library
            new Navigator().NavigateTo("Question Banks");//navigate to Question Banks
            new Click().clickBycssselector("span[class='addThisToMyResources bookmark-label']");//bookmark a resource
            new Click().clickBycssselector("div[data-id='my-resources']");//click on my Library tab
            Thread.sleep(2000);
            System.out.println(driver.findElements(By.cssSelector("span[class='removeThisFromMyResources bookmarked ls-resource-bookmark-icon']")).size());
            if(driver.findElements(By.cssSelector("i[class='removeThisFromMyResources bookmarked ls-resource-bookmark-icon']")).size() != 1)
                Assert.fail("Two Bookmarked assessment from Question bank is not present in My Library.");

            new LoginUsingLTI().ltiLogin("104");    //login as same instructor of class section B
            new Navigator().NavigateTo("Resources");//navigate to resource
            new Click().clickBycssselector("div[data-id='my-resources']");//click on my Library tab

            List<WebElement> allText1 = driver.findElements(By.className("resource-title"));
            //TC row no. 111...Resource bookmarked by instructor in class section A should appear in My library of class section B
            if(allText1.get(0).getText().equals(resourcePolicy) || allText1.get(0).getText().equals(resourceAssignment))
                Assert.fail("Resource bookmarked by instructor in class section A does not appear in My library of class section B");

            //TC row no. 110...Grading Policy created in class section A by instructor should appear in My library of class section B
            String appendResources2=resourcePolicy+LoginUsingLTI.appendChar;
            if(!allText1.get(1).getText().equals(appendResources2))
                Assert.fail("Grading Policy created in class section A by instructor does not appear in My library of class section B.");

            //TC row no. 109...Resource uploaded in class section A by instructor should appear in My library of class section B
            String appendResources3=resourceAssignment+LoginUsingLTI.appendChar;
            if(!allText1.get(2).getText().equals(appendResources3))
                Assert.fail("Resource uploaded in class section A by instructor does not appear in My library of class section B");

            new Navigator().NavigateTo("Question Banks");//navigate to Question Banks
            new Click().clickBycssselector("div[data-id='my-resources']");//click on my Library tab
            List<WebElement> allText2 = driver.findElements(By.className("resource-title"));
            //TC row no. 113...Assessment bookmarked by instructor in class section A should appear in My library of class section B
            /*if(driver.findElements(By.cssSelector("span[class='removeThisFromMyResources bookmarked ls-resource-bookmark-icon']")).size() != 1)
                Assert.fail("Assessment bookmarked by instructor in class section A does not appear in My library of class section B");*/

            //TC row no. 112...Custom assignment created by instructor in class section A should appear in My Library of class section B
            if(!allText2.get(1).getText().equals(customassignmentname))
                Assert.fail("Custom assignment created by instructor in class section A does not appear in My Library of class section B");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case instructorAbleToViewAllResourcesQuestionBanksAcrossAllClassSections in class InstructorAbleToViewAllResourcesQuestionBanksAcrossAllClassSections.", e);
        }
    }

}
