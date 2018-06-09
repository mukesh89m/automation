package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R50;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Policies;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentPolicy;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class AssignmentPolicies extends  Driver{


    @Test(priority = 1, enabled = true)
    public void assignmentPolicies()
    {
        try
        {
            Policies assignmentPolicy = PageFactory.initElements(driver, Policies.class);
            new LoginUsingLTI().ltiLogin("135");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
            new AssignmentPolicy().enterPolicyName("Policy Name");
            new AssignmentPolicy().enterPolicyDescription("Policy description text.");
            assignmentPolicy.getAssignmentPolicySaveButton().click(); //click on Save Policy
            Thread.sleep(2000);
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            Thread.sleep(2000);
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            String savedPolicy = new TextFetch().textfetchbyclass("assignment-policy-heading");
            if(!savedPolicy.contains("Policy Name"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After saving the policy if we go to some other page and come back to Assignment Policies it does not remain in assignment policies page.");
            }

            String tab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
            if(!tab.equals("Assignment Policies"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("The assignment policies are not available in a tab whose tab name is \"Assignment Policies\".");
            }

            String tabIcon = driver.findElement(By.cssSelector("span[class='tab_icon policies-icon']")).getCssValue("background-image");
            if(!tabIcon.contains("assignment-policy-tab-icons.png"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In \"Assignment Policies\" tab the tab icon is missing.");
            }

            String policyHeader = new TextFetch().textfetchbyclass("assignment-policy-heading");
            if(!policyHeader.contains("Policy Name"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Policy name is not displayed in \"Assignment Policies\" tab.");
            }
            String policyWrapper = new TextFetch().textfetchbyclass("assignment-policy-wrapper");
            if(!policyWrapper.contains("Policy description text."))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Policy description is not displayed in \"Assignment Policies\" tab.");
            }

            String actions = new TextFetch().textfetchbyclass("assignment-policy-actions");
            if(!actions.contains("Actions :"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Actions : for the assignment policy is not displayed in \"Assignment Policies\" tab.");
            }

            if(!policyHeader.contains("Policy Name"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Policy name is not displayed as per it is provided by the instructor in \"Assignment Policies\" tab.");
            }

            String differentActions = new TextFetch().textfetchbyclass("assignment-actions-wrapper");
            //testcase row number 151
            if(!differentActions.contains("Update Policy"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Update Policy\" action is not available for policy in \"Assignment Policies\" tab.");
            }
            //testcase row number 174
            if(!differentActions.contains("Copy Policy"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Copy Policy\" action is not available for policy in \"Assignment Policies\" tab.");
            }
            //testcase row number 144 & 184
            if(!differentActions.contains("Delete Policy"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Delete Policy\" action is not available for policy in \"Assignment Policies\" tab.");
            }
            //testcase row number 149
            if(differentActions.contains("View Policy"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"View Policy\" action is available for policy which has not been associated with an assignment in \"Assignment Policies\" tab.");
            }
            driver.findElement(By.linkText("Update Policy")).click();
            Thread.sleep(2000);

            //testcase row number 152
            String tab1 = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
            if(!tab1.equals("Policy Name"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("On clicking Update policy link, policy details are not shown in the new tab.");
            }
            //testcase row number 153 and 154
            int policyNameEditIcon = driver.findElements(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon")).size();
            if(policyNameEditIcon != 2)		//there are two edit icon
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit the Policy name and policy description.");
            }
            //testcase row number 155
            String isDisabled = driver.findElement(By.cssSelector("input[id='score']")).getAttribute("disabled");
            if(isDisabled != null)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit Score per question field.");
            }
            //testcase row number 156
            List<WebElement> orderingIsDisabled = driver.findElements(By.xpath("//*[starts-with(@name, 'orderingupdate-tabId')]"));
            if(orderingIsDisabled.get(0).getAttribute("disabled") != null )
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit Ordering options.");
            }
            if(orderingIsDisabled.get(1).getAttribute("disabled") != null)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit Ordering options.");
            }
            //testcase row number 157
            List<WebElement> immediateFeedbackIsDisabled = driver.findElements(By.cssSelector("input[class='immediateFeedback']"));
            if(immediateFeedbackIsDisabled.get(0).getAttribute("disabled") != null )
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit immediate feedback optionts.");
            }
            if(immediateFeedbackIsDisabled.get(1).getAttribute("disabled") != null)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit immediate feedback optionts.");
            }
            //testcase row number 158
            List<WebElement> gradeReleaseOptionIsDisabled = driver.findElements(By.cssSelector("input[class='gradeReleaseOptions']"));
            if(gradeReleaseOptionIsDisabled.get(0).getAttribute("disabled") != null )
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit grade release option.");
            }
            if(gradeReleaseOptionIsDisabled.get(1).getAttribute("disabled") != null)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit grade release option.");
            }
            if(gradeReleaseOptionIsDisabled.get(2).getAttribute("disabled") != null )
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit grade release option.");
            }
            if(gradeReleaseOptionIsDisabled.get(3).getAttribute("disabled") != null)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit grade release option.");
            }

            //update the policy
            new AssignmentPolicy().enterPolicyName("New Policy Name");
            immediateFeedbackIsDisabled.get(0).click();//click to enable immediate feedback
            Thread.sleep(2000);
            new ComboBox().selectValue(0, "3");	//select a value from the dropdown 'Number of attempts' to check if it is editable or not
            //testcase row number 161
            List<WebElement> showHintIsDisabled = driver.findElements(By.cssSelector("input[class='showHints']"));
            if(showHintIsDisabled.get(0).getAttribute("disabled") != null )
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit show hint options.");
            }
            if(showHintIsDisabled.get(1).getAttribute("disabled") != null)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit show hint options.");
            }
            //testcase row number 162
            List<WebElement> showReadingContentLinkIsDisabled = driver.findElements(By.cssSelector("input[class='showReadContentLink']"));
            if(showReadingContentLinkIsDisabled.get(0).getAttribute("disabled") != null )
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit Show reading content Link.");
            }
            if(showReadingContentLinkIsDisabled.get(1).getAttribute("disabled") != null)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit Show reading content Link.");
            }
            //testcase row number 163
            List<WebElement> showSolutionIsDisabled = driver.findElements(By.cssSelector("input[class='showSolution']"));
            if(showSolutionIsDisabled.get(0).getAttribute("disabled") != null )
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit Show solution.");
            }
            if(showSolutionIsDisabled.get(1).getAttribute("disabled") != null)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit Show solution.");
            }
            //testcase row number 164
            List<WebElement> showAnswerIsDisabled = driver.findElements(By.cssSelector("input[class='showAnswer']"));
            if(showAnswerIsDisabled.get(0).getAttribute("disabled") != null )
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit Show answer.");
            }
            if(showAnswerIsDisabled.get(1).getAttribute("disabled") != null)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are unable to edit Show answer.");
            }

            assignmentPolicy.getAssignmentPolicySaveButton().click();	//click on Save Policy
            Thread.sleep(2000);
            //testcase row number 167
            String notification = new TextFetch().textfetchbyclass("policy-notification-text-span");
            if(!notification.contains("The updates to the policy has been saved"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After updating the policy the required notification doesnt appear.");
            }
            //testcase row number 168
            new TabClose().tabClose(1);		//close the tab
            String savedPolicy1 = new TextFetch().textfetchbyclass("assignment-policy-heading");
            if(!savedPolicy1.contains("New Policy Name"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After updating the policy and saving the policy doesnt get modified.");
            }
        }
        catch(Exception e)
        {
           new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase assignmentPolicies in class AssignmentPolicies.",e);
        }

    }

    @Test(priority = 2, enabled = true)
    public void viewPolicyAction()
    {
        try
        {
            Policies assignmentPolicy = PageFactory.initElements(driver, Policies.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "145");
            new Assignment().create(145);
            new Assignment().addQuestions(145,"multiplechoice","");
            new Assignment().addQuestions(145,"multipleselection","");
            new Assignment().addQuestions(145,"writeboard","");
            new LoginUsingLTI().ltiLogin("145");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
            new AssignmentPolicy().enterPolicyName(assignmentpolicyname);
            new AssignmentPolicy().enterPolicyDescription("Policy description text.");
            assignmentPolicy.getAssignmentPolicySaveButton().click();	//click on Save Policy
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(145);
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            //testcase row number 145
            String viewPolicy = new TextFetch().textfetchbyclass("view-policy");
            if(!viewPolicy.contains("View Policy"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"View Policy\" action is not available for policy which has been associated with an assignment.");
            }

            //testcase row number 174
            String copyPolicy = new TextFetch().textfetchbyclass("copy-policy");
            if(!copyPolicy.contains("Copy Policy"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Copy Policy\" action is not available for policy which has been associated with an assignment.");
            }
            driver.findElement(By.linkText("View Policy")).click();
            Thread.sleep(2000);
            //testcase row number 146
            String tab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
            if(!tab.equals(assignmentpolicyname))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("On clicking View policy link policy details are not shown in the new tab.");
            }
            //testcase row number 148
            int saveButton = driver.findElements(By.className("ls-save-policy-btn")).size();
            if(saveButton != 0 )
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Save button is available for policy when open this using view policy link.");
            }

            //testcase row number 150
            int updatePolicy = driver.findElements(By.className("update-policy")).size();
            if(updatePolicy != 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Update Policy\" action is available for policy which has been associated with an assignment.");
            }
            //testcase row number 182
            int deleteSize = driver.findElements(By.linkText("Delete Policy")).size();
            if(deleteSize != 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Delete Policy\" action is available for policy which has been associated with an assignment.");
            }
            String isDisabled = driver.findElement(By.cssSelector("input[id='score']")).getAttribute("disabled");

            if(!isDisabled.equals("true"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we click update we are able to edit Score per question field.");
            }
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase viewPolicyAction in class AssignmentPolicies.",e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void updateFourPolicies()
    {
        try
        {
            Policies assignmentPolicy = PageFactory.initElements(driver, Policies.class);
            new LoginUsingLTI().ltiLogin("169");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            for(int i = 1; i < 5; i++)
            {
                new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
                new AssignmentPolicy().enterPolicyName(i+" Policy");
                new AssignmentPolicy().enterPolicyDescription(i+" Policy description text.");
                assignmentPolicy.getAssignmentPolicySaveButton().click();		//click on Save Policy
                Thread.sleep(2000);
                new TabClose().tabClose(1);		//close the tab
            }
            List<WebElement> allUpdateLink = driver.findElements(By.className("update-policy"));
            for(int i = 0; i < 4; i++)
            {
                allUpdateLink.get(i).click();//click on update link
                Thread.sleep(2000);
                driver.findElement(By.cssSelector("span[title=\"Assignment Policies\"]")).click();//click on Assignment Policies tab
                Thread.sleep(2000);
            }
            //testcase row no. 169
            String hiddenTabs = new TextFetch().textfetchbyclass("hiddentabs");
            if(hiddenTabs == null)	//1 tab will be hidden
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Additional tab icon does not appear with tab count if we update four policies.");
            }
            new Click().clickByclassname("hiddentabs"); //to check we are able to click the hiden tab drop down / /testcase row no. 170
            //testcase row no. 171
            String tabIcon = driver.findElement(By.className("hiddentab-policy-data")).getCssValue("background-image");
            if(!tabIcon.contains("assignment-policy-tab-icons.png"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In the hidden tab dropdown the additional policies are not displayed with icon.");
            }
            driver.findElement(By.cssSelector("div.hidden-tab-data")).click();	//click to open hidden policy
            Thread.sleep(2000);
            //testcase row no. 172
            String tabName = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
            if(!tabName.equals("3 Policy"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Unable to open policy from hidden tab.");
            }
            //close all the tabs
            new TabClose().tabClose(2);		//close the tab
            driver.findElement(By.cssSelector("span[title='2 Policy']")).click();	//go to tab
            Thread.sleep(2000);
            new TabClose().tabClose(2);		//close the tab
            driver.findElement(By.cssSelector("span[title='1 Policy']")).click();	//go to tab
            Thread.sleep(2000);
            new TabClose().tabClose(1);		//close the tab
            driver.findElement(By.cssSelector("span[title='4 Policy']")).click();	//go to tab
            Thread.sleep(2000);
            new TabClose().tabClose(1);		//close the tab
            driver.findElement(By.className("update-policy")).click();	//click to update the 1st policy
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[title=\"Assignment Policies\"]")).click();//click on Assignment Policies tab
            Thread.sleep(2000);
            driver.findElement(By.className("delete-policy")).click();	//click to delete 1st policy
            Thread.sleep(2000);
            String notice = new TextFetch().textfetchbyclass("policy-notification-text-span");
            if(!notice.contains("Are you sure you want to delete the policy ?    Yes, Delete     No, Cancel"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("On clciking delete link the required noification does not appear.");
            }
            driver.findElement(By.cssSelector("img[alt=\"Close\"]")).click();	//click to close notification
            Thread.sleep(2000);
            int notification = driver.findElements(By.className("policy-notification-text-span")).size();
            if(notification != 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Unable to close the notification of delete policy.");
            }
            driver.findElement(By.className("delete-policy")).click();	//click to delete 1st policy
            Thread.sleep(2000);
            driver.findElement(By.linkText("No, Cancel")).click();	//click on No, Cancel link
            Thread.sleep(2000);
            //testcase row no. 188
            int notification1 = driver.findElements(By.className("policy-notification-text-span")).size();
            if(notification1 != 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Notification for delete policy doesnt closes on clicking 'No, Cnacel' link.");
            }
            driver.findElement(By.className("delete-policy")).click();	//click to delete 1st policy
            Thread.sleep(2000);
            new Click().clickByclassname("delete-policy-yes");
            //testcase row no. 194
            String notice1 = new TextFetch().textfetchbyclass("policy-notification-text-span");
            if(!notice1.contains("This policy has been deleted"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After deleting a policy the required notification is not shown.");
            }
            //testcase row no. 173
            int tabName1 = driver.findElements(By.cssSelector("div[class='tab']")).size();
            if(tabName1 != 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we delete the a policy which has been opened in a new tab then the opened tab does not disappear.");
            }

            //testcase row no. 189
            String deletedPolicy = new TextFetch().textfetchbyclass("assignment-policy-heading");
            if(deletedPolicy.contains("4 Policy"))	//4 policy has been deleted
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("If we delete the policy then it doesnt disappear from the Assignment Policy page.");
            }
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase updateFourPolicies in class AssignmentPolicies.",e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void copyPolicyAction()
    {
        try
        {
            Policies assignmentPolicy = PageFactory.initElements(driver, Policies.class);
            new LoginUsingLTI().ltiLogin("174");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy("Policy Name", "Policy Description text", "2", null, false, "1", "", null, "", "", "");
            driver.findElements(By.className("close_tab")).get(1).click();
            //driver.findElement(By.cssSelector("span[title=\"Assignment Policies\"]")).click();//click on Assignment Policies tab
            Thread.sleep(2000);
            driver.findElement(By.className("copy-policy")).click();	//click to delete 1st policy
            Thread.sleep(2000);
            //testcase row no 176
            String newTab = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
            if(!newTab.equals("New Policy"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Clicking Copy Policy link it doesnt open a new tab with name 'New Policy'.");
            }
            //testcase row no 177
            String policyButtonDisbaled = driver.findElement(By.id("newAssignmentPolicy-link")).getAttribute("class");
            if(!policyButtonDisbaled.equals("disabled"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("The link to add a new policy is not removed after Clicking on Copy Policy link.");
            }
            //testcase row no 175
            //String nameField = new TextFetch().textfetchbyid("ls-ins-assignment-policy-name");
            List<WebElement> nameField = driver.findElements(By.id("ls-ins-assignment-policy-name"));
            if(!nameField.get(0).getText().contains("Click to enter a name for this policy..."))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Original policy is present in Name Field if we click from Copy policy.");
            }
            //testcase row no 175
            //String descField = new TextFetch().textfetchbyid("ls-ins-assignment-policy-desc");
            List<WebElement> descField = driver.findElements(By.id("ls-ins-assignment-policy-desc"));
            if(!descField.get(0).getText().contains("Click to enter a description for this policy..."))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Original policy Description is present in Description Field if we click from Copy policy.");
            }

            new AssignmentPolicy().enterPolicyName("New Policy Name");
            new AssignmentPolicy().enterPolicyDescription("New Policy description text.");
            assignmentPolicy.getAssignmentPolicySaveButton().click();		//click on Save Policy
            //testcase row no 178 and 180
            String notification = new TextFetch().textfetchbyclass("policy-notification-text-span");
            if(!notification.contains("Saved New Assignment Policy Successfully."))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Unable to create new policy by copying a existing policy.");
            }
            new AssignmentPolicy().enterPolicyName("Edited Policy Name");
            new AssignmentPolicy().enterPolicyDescription("Edited Policy description text.");
            driver.findElement(By.cssSelector("span[title=\"Assignment Policies\"]")).click();//click on Assignment Policies tab
            Thread.sleep(2000);
            //testcase row no 181
            String savedPolicy = new TextFetch().textfetchbyclass("assignment-policy-heading");
            if(savedPolicy.contains("Edited Policy Name"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Policy is saved on closing the tab without save.");
            }
            driver.findElement(By.cssSelector("span[title='Edited Policy Name']")).click();//click on Assignment Policies tab
            Thread.sleep(2000);
            //list all the radio options for Ordering(click to see other fields are editable too)
            List<WebElement> allOrdering = driver.findElements(By.xpath("//*[starts-with(@name, 'orderingcopy-tabId')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOrdering.get(0));	//click on Randomized radio button
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOrdering.get(1));	//click on Keep the assignment order radio button
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase copyPolicyAction in class AssignmentPolicies.",e);
        }
    }

}
