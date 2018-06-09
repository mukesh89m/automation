package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R163;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UploadResources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by Sumit on 9/3/2014.
 */
public class RestructuringOfMainNavigatorForMentor extends Driver{

    @Test(priority = 1, enabled = true)
    public void restructuringOfMainNavigator()
    {
        try
        {

            new LoginUsingLTI().ltiLogin("2");    //login as mentor

            //TC row no. 4..mentor should navigate to the current “Assignments” page.
            new Navigator().NavigateTo("Summary");//to verify "Summary" is present
            String summaryUrl = driver.getCurrentUrl();
            if(!summaryUrl.contains("assignment"))
                Assert.fail("On clicking \"Summary\" mentor does not navigate to the current \"Assignments\" page.");

            //TC row no. 5..mentor should navigate to a new page which contains ONLY assessments.
            new Navigator().NavigateTo("Question Banks");//to verify "Question Banks" is present
            String qBanks = driver.getCurrentUrl();
            if(!qBanks.contains("myQuestionBanks"))
                Assert.fail("On clicking \"Question Banks\" mentor does not navigate to the  \"Question Banks\" page.");

            //TC row no. 6...mentor should navigate to the current “Assignment Policies” page.
            new Navigator().NavigateTo("Policies");//to verify "Policies" is present
            String policies = driver.getCurrentUrl();
            if(!policies.contains("assignmentPolicy"))
                Assert.fail("On clicking \"Policies\" mentor does not navigate to the  \" Assignment Policies\" page.");

            //TC row no. 3...mentor should navigate to the current Resources page
            new Navigator().NavigateTo("Resources");//to verify "Resources" is present
            String resource = driver.getCurrentUrl();
            if(!resource.contains("resources"))
                Assert.fail("On clicking \"Resources\" mentor does not navigate to the  current Resources page.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case mentorAbleToViewGradesForAllStudents in class mentorAbleToNavigateToGradeBook.", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void enhancementToResourcePage()
    {
        try
        {

            String resourcesname1 = ReadTestData.readDataByTagName("", "resourcesname", "7");
            new LoginUsingLTI().ltiLogin("7");    //login as mentor
            new UploadResources().uploadResources("7", true, false, true);//upload resource

            //TC row no. 7...3. Click on Resources..."All Resources" tab should get opened by default
            new Navigator().NavigateTo("Resources");
            String tab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
            if(!tab.contains("All Resources"))
                Assert.fail("On clicking \"Resource\" from main navigator \"All Resources\" tab does not open by default.");

            //TC row no. 8..."My Resources" tab should be renamed as "My Resources"
            String tab1 = driver.findElement(By.cssSelector("div[class='tab']")).getText();
            System.out.println("tab1: "+tab1);
            if(!tab1.contains("My Resources"))
                Assert.fail("On clicking \"Resource\" from main navigator the \"My Resources\" tab is absent.");

            //TC row no. 10..."Assign This" link should be present under resources
            int assignThis = driver.findElements(By.cssSelector("span[title='Assign This']")).size();
            if(assignThis == 0)
                Assert.fail("\"Assign This\" link is absent under resources");

            //TC row no 12...“View Class Assignments” link should present at top right side of the page
            String viewAssignment = driver.findElement(By.className("ls-view-class-assignments")).getText();
            if(!viewAssignment.contains("View Class Assignments"))
                Assert.fail("In \"All Resources\" tab \"View Class Assignments\" text is absent.");

            //TC row no. 13..."+Create Custom Assignment" link should not be present
            int customAssignment = driver.findElements(By.id("customAssignment")).size();
            if(customAssignment != 0)
                Assert.fail("\"+ Create Custom Assignment\" link is present under \"All Resources\" tab");

            //TC row no. 14... Filters should be opened by default (All Chapters & All Sections)
            List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
            Thread.sleep(2000);
            boolean isAllChapterPresent = false;
            for(WebElement filter: allElements)
            {
                if(filter.getText().equals("All Chapters"))
                {
                    isAllChapterPresent = true;
                    break;
                }
            }
            if(!isAllChapterPresent)
                Assert.fail("\"All Chapters\" filter is not opened by default under \"All Resources\" tab");

            boolean isAllSectionsPresent = false;
            for(WebElement filter: allElements)
            {
                if(filter.getText().equals("All Sections"))
                {
                    isAllSectionsPresent = true;
                    break;
                }
            }
            if(!isAllSectionsPresent)
                Assert.fail("\"All Sections\" filter is not opened by default under \"All Resources\" tab");

            //TC row no. 15... “Type” & "All Learning Objectives" filter should be removed from "All Resources" tab
            boolean isTypePresent = false;
            for(WebElement filter: allElements)
            {
                if(filter.getText().equals("Type"))
                {
                    isTypePresent = true;
                    break;
                }
            }
            if(isTypePresent)
                Assert.fail("\"Type\" filter is present under \"All Resources\" tab");

            boolean isObjectivePresent = false;
            for(WebElement filter: allElements)
            {
                if(filter.getText().equals("All Learning Objectives"))
                {
                    isObjectivePresent = true;
                    break;
                }
            }
            if(isObjectivePresent)
                Assert.fail("\"All Learning Objectives\" filter is present under \"All Resources\" tab");

            //TC row no. 16... "Hide Filter" option should be present
            List<WebElement> hideFilter = driver.findElements(By.className("hide-filter-text"));
            if(!hideFilter.get(1).getText().contains("- Hide Filters"))
                Assert.fail("In \"All Resources\" tab \"Hide Filter\" option is absent.");

            driver.findElement(By.xpath("(//div[@id='view-resource-filter-button']/span[2])[2]")).click();//click on hide filter
            boolean isFilterPresent = false;

            List<WebElement> allElements1 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
            for(WebElement filter: allElements1)
            {
                if(filter.getText().contains("All Chapters")||filter.getText().contains("All Sections"))
                {
                    isFilterPresent = true;
                    break;
                }
            }
            if(isFilterPresent)
                Assert.fail("On clicking Hide Filter the filter doesn't hide under \"All Resources\" tab");

            //TC row no. 21...6. Select the Chapter filter and choose any chapter in which no resource is present..."Message should be displayed “Unfortunately, there are no resources available. You should either change you search/filter criteria or check with your system admin.”"
            new Navigator().NavigateTo("Resources");
            driver.findElement(By.linkText("All Chapters")).click(); //Click on All Chapter
            Thread.sleep(2000);
            driver.findElement(By.partialLinkText("Ch 1")).click();//select Chapter 1
            Thread.sleep(2000);
            driver.findElement(By.linkText("All Sections")).click();//click on All Section
            Thread.sleep(2000);
            driver.findElement(By.partialLinkText("1.1:")).click();//select section 1.1
            List<WebElement> noResourceMessage = driver.findElements(By.cssSelector("span[class='al-performace-report-sidebar-notification-text ls-ins-no-resources-found-message']"));
            if(!noResourceMessage.get(0).getText().contains("Unfortunately, there are no resources available. You should either change the search/filter criteria or check with your system admin."))
                Assert.fail("On selecting chapter which doesn't have a resource the proper message is not displayed.");

            //TC row no. 22...7. Click on My Resources tab...Default text in My Resources should be " “Nothing is available to show. You can add content to "My Resources" section by clicking on the "Star" icon against any content in "All Resources" area."
            driver.findElement(By.cssSelector("span[title='My Resources']")).click();//click on My Resources
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[title='Delete This']")).click();//delete the resource
            new Navigator().NavigateTo("Resources");
            driver.findElement(By.cssSelector("span[title='My Resources']")).click();//click on My Resources
            Thread.sleep(2000);
            String noResourceMessage1 = driver.findElement(By.cssSelector("span[class='al-performace-report-sidebar-notification-text ls-ins-no-resources-found-message']")).getText();
            if(!noResourceMessage1.contains("Nothing is available to show. You can add content to \"My Resources\" section by clicking on the \"Star\" icon against any content in \"All Resources\" area."))
                Assert.fail("If there is no resource in \"My Resources\" then no proper message is displayed");



        } catch (Exception e)
        {
            Assert.fail("Exception in test case enhancementToResourcePage in class mentorAbleToNavigateToGradeBook.", e);
        }
    }

}
