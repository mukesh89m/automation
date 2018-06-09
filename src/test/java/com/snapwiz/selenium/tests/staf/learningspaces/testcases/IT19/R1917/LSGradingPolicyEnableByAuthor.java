package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1917;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Policies;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by Mukesh on 3/17/15.
 */
public class LSGradingPolicyEnableByAuthor extends Driver{
    @Test(priority = 1,enabled = true)
    public void gradingPolicyEnableByAuthor()
    {
        try {
            //Tc row no 193
            Policies policies = PageFactory.initElements(driver, Policies.class);

            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(193));
            String policyDescription = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(193));

            new AssignmentPolicy().CMSEnablePolicy(193);
            new LoginUsingLTI().ltiLogin("193"); //login as instructor

            new LoginUsingLTI().ltiLogin("193"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the +new Assignment policy link
            // Expected:"New Policy" tab should be displayed.
            Assert.assertEquals(driver.findElement(By.xpath("//span[text()='New Policy']")).isDisplayed(), true, "New Policy tab is displayed");
            //Expected2: The “Language accent” option should be shown there on the assignment policy page.

            languageAccentPresent(193);

            new AssignmentPolicy().policyCreation(assignmentPolicyName, policyDescription, 193); //create a policy
            //Expected 3:Copy policy link should be available for newly created policy
            new Navigator().navigateToTab("Assignment Policies"); //navigate to Assignment Policies tab
            Assert.assertEquals(policies.copyPolicy_link.isDisplayed(),true,"Copy policy link is not available for newly created policy");
            //Expected 4:Language accent option should not be available over copy policy page
            policies.copyPolicy_link.click(); //click on the copy policy link
            languageAccentPresent(193);

            //Expected 5:Language accent option should not be available over update policy page

            new Navigator().navigateToTab("Assignment Policies"); //navigate to Assignment Policies tab
            policies.updatePolicy_link.click(); //click on the update policy link
            languageAccentPresent(193);

            new Assignment().create(193);
            new Assignment().addQuestions(193,"truefalse","");

            new LoginUsingLTI().ltiLogin("193");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(193);//assign to student

            //Tc row no 96 Language accent option should be available over view policy page
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.viewPolicy_link.click();
            languageAccentPresent(193);


        } catch (Exception e) {
            Assert.fail("Exception in test case gradingPolicyEnableByAuthor of class LSGradingPolicyEnableByAuthor", e);
        }
    }
    @Test(priority = 2,enabled = true)
    public void policyDisableThenEnable()
    {
        try {
            //Tc row no 202
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            Policies policies = PageFactory.initElements(driver, Policies.class);

            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(202));
            String policyDescription = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(202));

            String assignmentPolicyName1 = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(203));
            String policyDescription1 = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(203));

           new AssignmentPolicy().CMSDisablePolicy(202);


            new LoginUsingLTI().ltiLogin("202"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the +new Assignment policy link

            new AssignmentPolicy().policyCreation(assignmentPolicyName, policyDescription, 202); //create a policy

           new AssignmentPolicy().CMSEnablePolicy(202);
            //Expected 3:Copy policy link should be available for newly created policy
            new LoginUsingLTI().ltiLogin("202"); //login as instructor
            new Navigator().NavigateTo("Policies");
            Assert.assertEquals(policies.copyPolicy_link.isDisplayed(),true,"Copy policy link is not available for newly created policy");
            //Expected 4:Language accent option should not be available over copy policy page
            policies.copyPolicy_link.click(); //click on the copy policy link
            languageAccentPresent(98);
            policies.none.click(); //click on the none

            //Tc row no 99 Click on "Update Policy" link.
            new Navigator().navigateToTab("Assignment Policies"); //navigate to Assignment Policies tab
            policies.updatePolicy_link.click(); //click on the update policy link
            languageAccentPresent(99);
            Thread.sleep(2000);

            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)","");
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='show-accent-type-wrapper']//a[2]")));////click on the none


            new Assignment().create(202);

            new LoginUsingLTI().ltiLogin("202");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(202);//assign to student

            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.viewPolicy_link.click();
            languageAccentPresent(92);


           new AssignmentPolicy().CMSDisablePolicy(202);


            new LoginUsingLTI().ltiLogin("202"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the +new Assignment policy link

            new AssignmentPolicy().policyCreation(assignmentPolicyName1, policyDescription1, 203); //create a policy

            new AssignmentPolicy().CMSEnablePolicy(202);

            new LoginUsingLTI().ltiLogin("202"); //login as instructor
            new Navigator().NavigateTo("Policies");
            policies.updatePolicy_link.click(); //click on the update policy link

            driver.findElement(By.xpath("//a[text()='None']")).click();
            driver.findElement(By.xpath("//a[contains(text(),'Accents must match exactly')]")).click();
            policies.save_button.click(); //click on the save button

            new Navigator().navigateToTab("Assignment Policies"); //navigate to Assignment Policies tab
            policies.updatePolicy_link.click(); //click on the update policy link
            driver.findElement(By.xpath("//a[contains(text(),'Accents must match exactly')]")).click();

            new Assignment().create(203);

            new LoginUsingLTI().ltiLogin("202");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(203);//assign to student

            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.viewPolicy_link.click();
            languageAccentPresent(203);



        } catch (Exception e) {
            Assert.fail("Exception in test case policyDisableThenEnable of class LSGradingPolicyEnableByAuthor", e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void disableThenEnableAssignmentFlow()
    {
        try {
            //Tc row no 211
            Policies policies = PageFactory.initElements(driver, Policies.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            CurrentAssignments currentAssignment=PageFactory.initElements(driver,CurrentAssignments.class);

            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(214));
            String policyDescription = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(214));

            new AssignmentPolicy().CMSDisablePolicy(214);

            new LoginUsingLTI().ltiLogin("214"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the +new Assignment policy link
            new AssignmentPolicy().policyCreation(assignmentPolicyName, policyDescription, 214); //create a policy


            new Assignment().create(214 );

            new LoginUsingLTI().ltiLogin("214");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(214);//assign to student

            new AssignmentPolicy().CMSEnablePolicy(214);

            new LoginUsingLTI().ltiLogin("214_1");//login as student

            new Navigator().NavigateTo("Assignments");
            assignments.getAssignmentName().click(); //click on the assignment name
            new AttemptQuestion().trueFalse(false,"correct",214);
            new Assignment().submitButtonInQuestionClick();
            Thread.sleep(2000);
            assignments.getGoToNextLinkOnPopUp().click(); //click on the finish assignment

            new LoginUsingLTI().ltiLogin("214"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignment.getViewGrade_link().click();//click on view student responses
            Thread.sleep(4000);

            String assigmentStatus1=driver.findElements(By.className("ls-assignment-status-grades-released")).get(1).getText();
            Assert.assertEquals(assigmentStatus1,"Graded","Assignment status not displaying graded");

            new MouseHover().mouserhoverbywebelement(driver.findElement(By.xpath("//div[@class='idb-gradebook-content-coloumn']"))); //mouse over view response link
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);

            Assert.assertEquals(currentAssignment.getPerformanceScoreBox().getAttribute("value").trim(),"1.0","No language accent policy should get considered and instructor not  able to release the grades");



        } catch (Exception e) {
            Assert.fail("Exception in test case disableThenEnableAssignmentFlow of class LSGradingPolicyEnableByAuthor", e);
        }
    }
    @Test(priority = 4,enabled = true)
    public void assignmentFlowWithUpdatedPolicy()
    {
        try {
            //Tc row no 214
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            Policies policies = PageFactory.initElements(driver, Policies.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            CurrentAssignments currentAssignment=PageFactory.initElements(driver,CurrentAssignments.class);



            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(217));
            String policyDescription = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(217));

            new AssignmentPolicy().CMSDisablePolicy(217);

            new LoginUsingLTI().ltiLogin("217"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the +new Assignment policy link
            new AssignmentPolicy().policyCreation(assignmentPolicyName, policyDescription, 217); //create a policy

            new AssignmentPolicy().CMSEnablePolicy(217);

            new LoginUsingLTI().ltiLogin("217");//login as instructor
            updatePolicy(218,"Accents must match exactly");

            new Assignment().create(217 );
            new Assignment().addQuestions(217,"textentry","");

            new LoginUsingLTI().ltiLogin("217");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(217);//assign to student

            new LoginUsingLTI().ltiLogin("217_1");//login as student
            new Navigator().NavigateTo("Assignments");
            assignments.getAssignmentName().click(); //click on the assignment name
            new AttemptQuestion().trueFalse(false,"correct",217);
            assignments.getSubmitAssignment().click();
            Thread.sleep(2000);
            assignments.getGoToNextLinkOnPopUp().click(); //click on the go to next question

            Thread.sleep(3000);
            new AttemptQuestion().textEntryExactMatch(false,"correct",217);
            new Assignment().submitButtonInQuestionClick();
            Thread.sleep(3000);
            assignments.getGoToNextLinkOnPopUp().click(); //click on the finish assignment



            new LoginUsingLTI().ltiLogin("217"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignment.getViewGrade_link().click();//click on view student responses
            Thread.sleep(4000);

            String assigmentStatus1=driver.findElements(By.className("ls-assignment-status-grades-released")).get(1).getText();
            Assert.assertEquals(assigmentStatus1,"Graded","Assignment status not displaying graded");

            new MouseHover().mouserhoverbywebelement(driver.findElement(By.xpath("(//div[@class='idb-gradebook-content-coloumn'])[2]"))); //mouse over view response link
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);

            Assert.assertEquals(currentAssignment.getPerformanceScoreBox().getAttribute("value").trim(),"1.0","No language accent policy should get considered and instructor not  able to release the grades");


        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentFlowWithUpdatedPolicy of class LSGradingPolicyEnableByAuthor", e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void assignmentCreatedInEnableState()
    {
        try {
            //Tc row no 217
            Policies policies = PageFactory.initElements(driver, Policies.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            CurrentAssignments currentAssignment=PageFactory.initElements(driver,CurrentAssignments.class);



            String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", Integer.toString(219));
            String policyDescription = ReadTestData.readDataByTagName("", "gradingpolicy", Integer.toString(219));

           new AssignmentPolicy().CMSEnablePolicy(219);

            new LoginUsingLTI().ltiLogin("219"); //login as instructor
            new Navigator().NavigateTo("Policies"); //navigate to the policy
            policies.policy_link.click(); //click on the +new Assignment policy link
            new AssignmentPolicy().policyCreation(assignmentPolicyName, policyDescription, 219); //create a policy


            new Assignment().create(219 );
            new Assignment().addQuestions(219,"textentry","");

            new LoginUsingLTI().ltiLogin("219");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(219);//assign to student

            new LoginUsingLTI().ltiLogin("219_1");//login as student
            new Navigator().NavigateTo("Assignments");
            assignments.getAssignmentName().click(); //click on the assignment name
            new AttemptQuestion().trueFalse(false,"correct",219);
            assignments.getSubmitAssignment().click();
            Thread.sleep(2000);
            assignments.getGoToNextLinkOnPopUp().click(); //click on the go to next question

            Thread.sleep(3000);
            new AttemptQuestion().textEntryExactMatch(false,"correct",219);
            new Assignment().submitButtonInQuestionClick();
            Thread.sleep(3000);
            assignments.getGoToNextLinkOnPopUp().click(); //click on the finish assignment



            new LoginUsingLTI().ltiLogin("219"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignment.getViewGrade_link().click();//click on view student responses
            Thread.sleep(4000);

            String assigmentStatus1=driver.findElements(By.className("ls-assignment-status-grades-released")).get(1).getText();
            Assert.assertEquals(assigmentStatus1,"Graded","Assignment status not displaying graded");

            new MouseHover().mouserhoverbywebelement(driver.findElement(By.xpath("(//div[@class='idb-gradebook-content-coloumn'])[2]"))); //mouse over view response link
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);

            Assert.assertEquals(currentAssignment.getPerformanceScoreBox().getAttribute("value").trim(),"1.0","No language accent policy should get considered and instructor not  able to release the grades");


        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentCreatedInEnableState of class LSGradingPolicyEnableByAuthor", e);
        }
    }

    public void languageAccentPresent(int dataIndex)
    {
        boolean languageFound=new BooleanValue().presenceOfElement(88,By.cssSelector("div[class='ls-assignment-policy-question allow-accent-question']"));
        Assert.assertEquals(languageFound,true,"The “Language accent” option is not shown there on the assignment policy page.");

    }

    public void updatePolicy(int dataIndex,String languageAccent ) throws InterruptedException {
        Policies policies = PageFactory.initElements(driver, Policies.class);
        new Navigator().NavigateTo("Policies");//navigate to Policies
        policies.updatePolicy_link.click(); //click on the update policy link
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='None']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+languageAccent+"')]")).click();
        policies.save_button.click();//click on the save button

    }



}
