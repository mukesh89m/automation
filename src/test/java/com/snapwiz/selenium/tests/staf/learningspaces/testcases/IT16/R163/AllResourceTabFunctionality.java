package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R163;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by Sumit on 8/22/2014.
 */
public class AllResourceTabFunctionality extends Driver{

    @Test(priority = 1, enabled = true)
    public void allResourceTabFunctionality()
    {
        try
        {

            String resourcesname1 = ReadTestData.readDataByTagName("", "resourcesname", "23_1");
            new LoginUsingLTI().ltiLogin("23");    //login as instructor
            new UploadResources().uploadResources("23", false, true, false);//upload resource1
            new UploadResources().uploadResources("23_1", false, true, false);//upload resource1
            Thread.sleep(5000);
            //TC row no. 24...Ordering of items in the “My Resources” tab should follow the latest one at the top.
            String resourceTitle = driver.findElement(By.className("resource-title")).getText();
            System.out.println("resourceTitle: "+resourceTitle);
            System.out.println("resourcesname1: "+resourcesname1);
            if(!resourceTitle.contains(resourcesname1))
                Assert.fail("Newly added resource is not adding at the top of \"My Resources\" page.");

            new Navigator().NavigateTo("Resources");//go to Resources

            //TC row no. 23...Bookmarked resource should be present in My Resources tab
            driver.findElement(By.cssSelector("span[class='addThisToMyResources bookmark-label']")).click();//bookmark a resource
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[title='My Resources']")).click();//go to My Resources tab
            Thread.sleep(2000);
            String resourceTitle1 = driver.findElement(By.className("resource-title")).getText();
            if(resourceTitle1.contains(resourcesname1))
                Assert.fail("After bookmarking a resource from All Resource tab it is not adding at the top of \"My Resources\" page.");

            //TC row no. 25...“View Class Assignments” link should present at top right side of the page
            String classAssignment = driver.findElement(By.className("ls-view-class-assignments")).getText();
            if(!classAssignment.contains("View Class Assignments"))
                Assert.fail("\"View Class Assignments\" link is present at top right side of the page.");

            //TC row no. 26...“Upload Resource” button should be aligned with View/Hide FIlters options
            String uploadResource = driver.findElement(By.id("upload-resourse-button")).getText();
            if(!uploadResource.contains("+ Upload Resource"))
                Assert.fail("\"Upload Resource\" button is absent in \"My Resources\" tab.");

            //TC row no. 27..."+Create Custom Assignment" option should not be present
            int customAssignment = driver.findElements(By.id("customAssignment")).size();
            if(customAssignment != 0)
                Assert.fail("\"+ Create Custom Assignment\" button is present in \"My Resources\" tab.");


            //TC row no. 28... Filters should be opened by default
            List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
            boolean isTypesPresent = false;
            for(WebElement filter: allElements)
            {
                if(filter.getText().equals("Type"))
                {
                    isTypesPresent = true;
                    break;
                }
            }
            if(!isTypesPresent)
                Assert.fail("Filter are not opened by default under \"My Resources\" tab");

            //TC row no. 29..."Type", "All Chapters" and "All Sections" filters should be present
            if(!isTypesPresent)
                Assert.fail("\"Type\" filter is not present under \"My Resources\" tab");

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
                Assert.fail("\"All Chapters\" filter is not present under \"My Resources\" tab");

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
                Assert.fail("\"All Sections\" filter is not present under \"My Resources\" tab");

            //TC row no. 30... "All Learning Objectives" filter should be removed
            boolean isAllObjectivesPresent = false;
            for(WebElement filter: allElements)
            {
                if(filter.getText().equals("All Learning Objectives"))
                {
                    isAllObjectivesPresent = true;
                    break;
                }
            }
            if(isAllObjectivesPresent)
                Assert.fail("\"All Learning Objectives\" filter is present under \"My Resources\" tab");

            //TC row no. 31... "Hide Filter" option should be present
            List<WebElement> hideFilter = driver.findElements(By.className("hide-filter-text"));
            if(!hideFilter.get(0).getText().contains("- Hide Filters"))
                Assert.fail("In \"My Resources\" tab \"Hide Filter\" option is absent.");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case allResourceTabFunctionality in class AllResourceTabFunctionality.", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void uploadAndDeleteResource()
    {
        try
        {

            String resourcesname1 = ReadTestData.readDataByTagName("", "resourcesname", "32");
            new LoginUsingLTI().ltiLogin("32_1");    //create a student
            new LoginUsingLTI().ltiLogin("32");    //login as instructor
            new UploadResources().uploadResources("32", true, false, true);//upload resource
            Thread.sleep(3000);
            //TC row no. 32...Ordering of items in the “My Resources” tab should follow the latest one at the top.
            String resourceTitle = driver.findElement(By.className("resource-title")).getText();
            System.out.println("resourceTitle: "+resourceTitle);
            if(!resourceTitle.contains(resourcesname1))
                Assert.fail("Newly added resource is not adding at the top of \"My Resources\" page.");

            //TC row no. 34..."Assign This" and "Delete This" link should be present
            int assignThis = driver.findElements(By.cssSelector("span[title='Assign This']")).size();
            if(assignThis == 0)
                Assert.fail("\"Assign This\" link is not present in \"My Resources\" page.");

            int deleteThis = driver.findElements(By.cssSelector("span[title='Delete This']")).size();
            if(deleteThis == 0)
                Assert.fail("\"Delete This\" link is not present in \"My Resources\" page .");

            //TC row no. 35...Resource should get deleted
            driver.findElement(By.cssSelector("span[title='Delete This']")).click();//delete the resource
            new Navigator().NavigateTo("My Resources");//go to MY Resources
            String noResourceMessage1 = driver.findElement(By.cssSelector("span[class='al-performace-report-sidebar-notification-text ls-ins-no-resources-found-message']")).getText();
            if(!noResourceMessage1.contains("Nothing is available to show. You can add content to \"My Resources\" section by clicking on the \"Star\" icon against any content in \"All Resources\" area."))
                Assert.fail("Unable to delete the resource from \"My Resources\" page.");

            //TC row no. 37..."13. Upload the new resource again...14. Assign the Resource"...Instructor should get navigated to Assignment Summary page
            new UploadResources().uploadResources("32", true, false, true);//upload resource
            new AssignLesson().assignResourceFromMyResources(32);
            String url = driver.getCurrentUrl();
            if(!url.contains("assignment"))
                Assert.fail("Instructor does not navigated to Assignment Summary page after assigning a resource from \"My Resources\".");

            //TC row no. 36....Resource should get Assigned as an assignment to students
            new LoginUsingLTI().ltiLogin("32_1");    //login as student
            new Navigator().NavigateTo("Assignments");      //go to Assignments
            String assignment = new TextFetch().textfetchbyclass("ls-assignment-post-label");
            if(!assignment.contains("posted an assignment."))
                Assert.fail("Instructor assigns a resource from \"My Resources\" and its not visible to students.");

            new LoginUsingLTI().ltiLogin("32");    //login as instructor
            new Navigator().NavigateTo("Resources");
            driver.findElement(By.cssSelector("div[data-id='my-resources']")).click();

            //TC row no. 38..."Assign This" link should be present
            int assignThis1 = driver.findElements(By.cssSelector("span[title='Assign This']")).size();
            if(assignThis1 == 0)
                Assert.fail("\"Assign This\" link is not present in \"My Resources\" page after assigning the resource.");

            //TC row no. 40..."Delete This" link should be present
            int deleteThis1 = driver.findElements(By.cssSelector("span[title='Delete This']")).size();
            if(deleteThis1 != 0)
                Assert.fail("\"Delete This\" link is present in \"My Resources\" page after assigning the resource.");

            //TC row no. 41..."17. Click on Assign This Link for the same resource...18. Fill the details with new entries and assign it"...New entry should be created for the assignment in Assignment Summary page
            new AssignLesson().assignResourceFromMyResources(32);
            int assignment1 = driver.findElements(By.cssSelector("div[class='ls-assignment-item-wrapper']")).size();
            if(assignment1 != 2)
                Assert.fail("After assigning resource, the instructor is unable to assign the resource again.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case uploadAndDeleteResource in class AllResourceTabFunctionality.", e);
        }
    }

}
