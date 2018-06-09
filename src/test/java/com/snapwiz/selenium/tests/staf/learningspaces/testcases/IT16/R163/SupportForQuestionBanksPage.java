package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R163;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignLesson;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Sumit on 8/22/2014.
 */
public class SupportForQuestionBanksPage extends Driver{

    @Test
    public void supportForQuestionBanksPage()
    {
        try
        {

            String assessment1 = ReadTestData.readDataByTagName("", "assessmentname", "42");
            String assessment2 = ReadTestData.readDataByTagName("", "assessmentname", "43");
            new Assignment().create(42);//create assignment
            new Assignment().create(43);//create assignment

            new LoginUsingLTI().ltiLogin("42");    //login as instructor
            new Navigator().NavigateTo("Question Banks");

            //TC row no. 43...By default, Question Banks tab should be opened
            String tab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
            if(!tab.contains("Question Banks"))
                Assert.fail("On clicking \"Question Banks\" from main navigator \"Question banks\" tab does not open by default.");

            //TC row no. 42 ..."Two tabs should be present : 1) My Library 2) Question Banks
            String tab1 = driver.findElement(By.cssSelector("div[class='tab']")).getText();
            if(!tab1.contains("My Question Bank"))
                Assert.fail("On clicking \"Question Banks\" from main navigator the \"My Library\" tab is absent.");

            //TC row no. 44...Only assessments should be available in the Question Banks tab
            boolean bool = true;
            List<WebElement> allAssessment = driver.findElements(By.className("resource-title"));
            for(WebElement assessment: allAssessment)
            {
                if(!assessment.getAttribute("type").equals("ASSESSMENT"))
                {
                    bool = false;
                    break;
                }
            }
            if(bool == false)
                Assert.fail("In the Question Banks tab only assessments are not present.");

            //TC row no 46...“View Class Assignments” link should present at top right side of the page
            String viewAssignment = driver.findElement(By.className("ls-view-class-assignments")).getText();
            if(!viewAssignment.contains("View Class Assignments"))
                Assert.fail("In \"Question Banks\" tab \"View Class Assignments\" text is absent.");

            //TC row no 47..."+Create Custom Assignment" link should not be present
            List<WebElement> customAssignment = driver.findElements(By.id("customAssignment"));
            if(!customAssignment.get(1).getText().contains("+ Create Custom Assignment"))
                Assert.fail("In \"Question Banks\" tab \"+ Create Custom Assignment\" text is absent.");

            //TC row no. 48..."+Upload Resource" option should not be present
            int uploadResource = driver.findElements(By.id("upload-resourse-button")).size();
            if(uploadResource != 0)
                Assert.fail("\"+ Upload Resource\" link is present under \"Question Banks\" tab");

            //TC row no. 49..."Assign This" and "Customize This"  links should be present under assessments
            driver.findElement(By.cssSelector("span[class='assign-this']")).click();//click on Assign This link

            driver.findElement(By.cssSelector("span[class='customize-this']")).click();//click on Assign This link
            String tab2 = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
            if(!tab2.contains("New Assignment"))
                Assert.fail("\"Customize This\" links is not present under assessments.");
            new Click().clickByid("close-new-assignment");//close the newly opened tab

            //TC row no. 51... Filters should be opened by default (All Chapters , All Sections & All Learning Objectives )
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
                Assert.fail("\"All Chapters\" filter is not opened by default under \"Question Banks\" tab");

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
                Assert.fail("\"All Sections\" filter is not opened by default under \"Question Banks\" tab");
            boolean isAllObjectivePresent = false;
            for(WebElement filter: allElements)
            {
                if(filter.getText().equals("All Learning Objectives"))
                {
                    isAllObjectivePresent = true;
                    break;
                }
            }
            if(!isAllObjectivePresent)
                Assert.fail("\"All Learning Objectives\" filter is not opened by default under \"Question Banks\" tab");


            //TC row no. 52... “Type" filter should be removed from "Question Bank" tab
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
                Assert.fail("\"Type\" filter is present under \"Question Banks\" tab");


            //TC row no. 53... "Hide Filter" option should be present
            List<WebElement> hideFilter = driver.findElements(By.className("hide-filter-text"));
            if(!hideFilter.get(1).getText().contains("- Hide Filters"))
                Assert.fail("In \"Question Banks\" tab \"Hide Filter\" option is absent.");

            driver.findElement(By.xpath("(//div[@id='view-resource-filter-button']/span[2])[2]")).click();//click on hide filter
            boolean isFilterPresent = false;
            //TC row no. 53.. Filter should get hidden
            List<WebElement> allElements1 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
            for(WebElement filter: allElements1)
            {
                if(filter.getText().contains("All Chapters")||filter.getText().contains("All Sections")||filter.getText().contains("All Learning Objectives"))
                {
                    isFilterPresent = true;
                    break;
                }
            }
            if(isFilterPresent)
                Assert.fail("On clicking Hide Filter the filter doesn't hide under \"Question Banks\" tab");

            //TC row no. 55...5. Click on 3 different assessments in Question Bank tab...Only three tabs should be displayed in the page including My library and Question Bank tabs
            List<WebElement> allAssessment1;
            allAssessment1 = driver.findElements(By.className("ls-preview-wrapper"));
            allAssessment1.get(0).click();//open 1st assessment
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[title='Question Banks']")).click();//go to Question Banks atb
            Thread.sleep(2000);
            allAssessment1 = driver.findElements(By.className("ls-preview-wrapper"));
            allAssessment1.get(1).click();//open 2nd assessment
            Thread.sleep(2000);
            int tabs = driver.findElements(By.cssSelector("div.hiddentabs")).size();
            if(tabs == 0)
                Assert.fail("More than three tabs is displayed in the page including My library and Question Bank tabs");

            //TC row no. 56...Jump-out tab icon should be present for additional tabs
            driver.findElement(By.cssSelector("div.hiddentabs")).click();//click on Hidden tabs
            String hiddenTab = driver.findElement(By.className("hidden-tab-data")).getText();
            if(hiddenTab.length() == 0)
                Assert.fail("Jump-out tab icon is not present for additional tabs.");

            //TC row no. 57....6. Select the Chapter filter and choose any chapter in which no assessment is present..."Message should be displayed “Unfortunately, there are no “question banks” available. You should either change you search/filter criteria or check with your system admin.”"
            driver.findElement(By.cssSelector("span[title='Question Banks']")).click();//go to Question Banks tab
            Thread.sleep(2000);
            List<WebElement> allElements2 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
            allElements2.get(4).click();//click on All Chapter
            Thread.sleep(2000);
            driver.findElement(By.partialLinkText("Ch 1")).click();//select Chapter 1
            Thread.sleep(2000);
            List<WebElement> allElements3 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
            allElements3.get(5).click();//click on All Section
            Thread.sleep(2000);
            driver.findElement(By.partialLinkText("1.1:")).click();//select section 1.1
            List<WebElement> noResourceMessage = driver.findElements(By.cssSelector("span[class='al-performace-report-sidebar-notification-text ls-ins-no-resources-found-message']"));
            if(!noResourceMessage.get(1).getText().contains("Unfortunately, there are no resources available. You should either change the search/filter criteria or check with your system admin."))
                Assert.fail("On selecting chapter which doesn't have a assessment the proper message is not displayed in Question Banks tab.");

            //TC row no. 58...7. Click on My Library tab...Default text in My library should be " “Nothing is available to show. You can add content to "My Library" section by clicking on the "Star" icon against any content in "All Resources" area."
            driver.findElement(By.cssSelector("span[title='My Question Bank']")).click();//click on My Library
            Thread.sleep(2000);
            String noResourceMessage1 = driver.findElement(By.cssSelector("span[class='al-performace-report-sidebar-notification-text ls-ins-no-resources-found-message']")).getText();
            if(!noResourceMessage1.contains("Nothing is available to show. You can add content to \"My Question Bank\" section by clicking on the \"Star\" icon against any content in \"Question Banks\" area. Any custom assignment created by you will be available in \"My Question Bank\" section automatically."))
                Assert.fail("If there is no resource in \"My Library\" then no proper message is displayed");

            //TC row no. 59...Bookmarked assessment should be present in My Library tab
            new Navigator().NavigateTo("Question Banks");
            driver.findElement(By.cssSelector("span[title='Add to My Question Bank']")).click();//bookmark first assessment
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[title='My Question Bank']")).click();//go to My Library tab
            Thread.sleep(2000);
            String resourceTitle1 = driver.findElement(By.className("resource-title")).getText();
            if(resourceTitle1.contains(assessment1))
                Assert.fail("After bookmarking a resource from All Resource tab it is not adding in \"My Library\" page.");

            //TC row no. 60...Ordering of items in the “My Library” tab should follow the latest one at the top.
            new Navigator().NavigateTo("Question Banks");
            driver.findElement(By.cssSelector("span[title='Add to My Question Bank']")).click();//bookmark second assessment
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[title='My Question Bank']")).click();//go to My Library tab
            Thread.sleep(2000);
            String resourceTitle2 = driver.findElement(By.className("resource-title")).getText();
            if(resourceTitle2.contains(assessment2))
                Assert.fail("After bookmarking a resource from All Resource tab it is not adding at top of \"My Library\" page.");

            //TC row no. 61...“View Class Assignments” link should present at top right side of the page
            String classAssignment = driver.findElement(By.className("ls-view-class-assignments")).getText();
            if(!classAssignment.contains("View Class Assignments"))
                Assert.fail("\"View Class Assignments\" link is present at top right side of the page.");

            //TC row no. 62...“+Create Custom Assignment” button should be present at the right side aligned with the tabs
            int customAssignment1 = driver.findElements(By.id("customAssignment")).size();
            if(customAssignment1 == 0)
                Assert.fail("\"+ Create Custom Assignment\" button is absent in \"My Question Bank\" tab.");

            //TC row no. 63..."+Upload Resource" option should not be present
            int uploadResource1 = driver.findElements(By.id("upload-resourse-button")).size();
            if(uploadResource1 != 0)
                Assert.fail("\"Upload Resource\" button is present in \"My Question Bank\" tab.");

            //TC row no. 64..."Assign This" link should be present under assessment
            driver.findElement(By.cssSelector("span[class='assign-this']")).click();//click on Assign This link
            int assignThis1 = driver.findElements(By.className("ir-ls-assign-dialog-content-wrapper")).size();
            if(assignThis1 == 0)
                Assert.fail("\"Assign This\" links is not present for assessments in My Library.");

            //TC row no. 65... Filters should be opened by default (All Chapters , All Sections & All Learning Objectives )
            List<WebElement> allFilters = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
            Thread.sleep(2000);
            boolean isAllChapterPresent1 = false;
            for(WebElement filter: allFilters)
            {
                if(filter.getText().equals("All Chapters"))
                {
                    isAllChapterPresent1 = true;
                    break;
                }
            }
            if(!isAllChapterPresent1)
                Assert.fail("\"All Chapters\" filter is not opened by default under \"My Library\" tab");

            boolean isAllSectionsPresent1 = false;
            for(WebElement filter: allFilters)
            {
                if(filter.getText().equals("All Sections"))
                {
                    isAllSectionsPresent1 = true;
                    break;
                }
            }
            if(!isAllSectionsPresent1)
                Assert.fail("\"All Sections\" filter is not opened by default under \"My Library\" tab");
            boolean isAllObjectivePresent1 = false;
            for(WebElement filter: allFilters)
            {
                if(filter.getText().equals("All Learning Objectives"))
                {
                    isAllObjectivePresent1 = true;
                    break;
                }
            }
            if(!isAllObjectivePresent1)
                Assert.fail("\"All Learning Objectives\" filter is not opened by default under \"My Library\" tab");


            //TC row no. 66... "Type" Filter should be removed from My Library tab
            boolean isTypePresent1 = false;
            for(WebElement filter: allFilters)
            {
                if(filter.getText().equals("Type"))
                {
                    isTypePresent1 = true;
                    break;
                }
            }
            if(isTypePresent1)
                Assert.fail("\"Type\" filter is present under \"My Library\" tab");

            //TC row no. 67... "Hide Filter" option should be present
            String hideFilter1 = driver.findElement(By.className("hide-filter-text")).getText();
            if(!hideFilter1.contains("- Hide Filters"))
                Assert.fail("In \"My Library\" tab \"Hide Filter\" option is absent.");

            //TC row no. 68..."10. Click on Assign This Link for the bookmarked assessment  11. Fill the details with new entries and assign it"....New entry should be created for the assignment in Assignment Summary page
            new AssignLesson().Assigncustomeassignemnt(43);//assign to student
            String assignment = driver.findElement(By.className("ls-assignment-post-label")).getText();
            if(!assignment.contains("posted an assignment."))
                Assert.fail("On assigning an assignment from My Library tab then assignment entry is not created.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case supportForQuestionBanksPage in class SupportForQuestionBanksPage.", e);
        }
    }

}
