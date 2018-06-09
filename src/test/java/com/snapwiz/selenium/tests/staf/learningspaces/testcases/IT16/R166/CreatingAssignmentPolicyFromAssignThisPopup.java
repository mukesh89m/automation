package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R166;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentPolicy;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by Sumit on 8/12/2014.
 */
public class CreatingAssignmentPolicyFromAssignThisPopup extends Driver{

    @Test
    public void creatingAssignmentPolicyFromAssignThisPopup()
    {
        try
        {
            String policyname= ReadTestData.readDataByTagName("", "policyname", "20");
            String assessmentname= ReadTestData.readDataByTagName("", "assessmentname", "20");
            new Assignment().create(20);//create assignment

            new LoginUsingLTI().ltiLogin("20");//login as instructor

            //TC row no. 20...4. Select "Create a new assignment policy" in the assignment policy dropdown...Pop-up to create assignment policy on fly should appear
            new AssignmentPolicy().openAssignmentPolicy(assessmentname);//open assignment policy form instructor resources page
            String popUp = driver.findElement(By.className("assignment-policies-wrapper")).getText();
            if(popUp == null)
                Assert.fail("On clicking eye for on Assign This pop up the new pop-up to create the new Policy doesnt appear.");
            String otherPolicy = driver.findElement(By.cssSelector("div[class='ls-policy-config-questions-wrapper other-policy-wrapper']")).getText();

            //TC row no. 21......New option "Allow Collaboration" should be present under 'Other Policy Configuration'
            if(!otherPolicy.contains("Other Policy configuration"))
                Assert.fail("\"Other Policy configuration\" text is not present in creating assignment policy page.");
            String allowCollaboration = driver.findElement(By.cssSelector("div[class='ls-assignment-policy-row ls-assignment-policy-attempts']")).getText();
            if(!allowCollaboration.contains("Allow Collaboration"))
                Assert.fail("\"Allow Collaboration\" text is not present in creating assignment policy page.");

            //TC row no. 22  //By default , “Allow Collaboration” should be in “disabled” state
            List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@name, 'allowCollaborationupdate-tabId')]"));
            if(!allElements.get(1).getAttribute("checked").contains("true"))
                Assert.fail("By default, \"Allow Collaboration\" is not in \"disabled\" state");

            //TC row no. 23  //5. Click on "?" icon for Allow Collaboration option----Help text should display “Select Enable if you want students to discuss assignment questions after each attempt.”
            driver.findElement(By.id("allow-collaboration-icon")).click();//4. Click on "?"icon for Allow Collaboration policy
            Thread.sleep(2000);
            String helpText = driver.findElement(By.cssSelector("div[class='policy-help-data-container']")).getText();
            if(!helpText.contains("Select Enable if you want students to discuss assignment questions after each attempt."))
                Assert.fail("Help text for allow collaboration is not present in creating assignment policy page.");

            //TC row no. 24...//"6. Enable the Allow collaboration option"....Instructor should be able to select enable for Allow Collaboration option
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allElements.get(0));//click to enable the social collaboration

            //TC row no. 25...//"6. Enter a name for the policy and click on Save Policy button and Go to Assignment Policies tab"..New Assignment Policy should be created successfully
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon")));
            driver.switchTo().activeElement().sendKeys(policyname);
            driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
            Thread.sleep(2000);
            new Click().clickByclassname("ls-save-policy-btn");		//click on Save Policy
            Thread.sleep(2000);
            int size = driver.findElements(By.linkText(policyname)).size();
            if(size == 0)
                Assert.fail("New Assignment Policy is not created successfully on fly.");

            //TC row no. 26...8. Click on "eye" icon present besides the Assignment Policy label....View Policy pop-up should appear
            new Click().clickByid("assignment-policy-icons");//click on Eye icon
            String policy = driver.findElement(By.cssSelector("label[id='ls-ins-assignment-policy-name view-policy']")).getText();
            if(!policy.contains(policyname))
                Assert.fail("On clicking eye icon view policy pop-up doesnt appear.");

            //TC row no. 27......New option "Allow Collaboration" should be present under 'Other Policy Configuration'
            String otherPolicy1 = driver.findElement(By.cssSelector("div[class='ls-policy-config-questions-wrapper other-policy-wrapper']")).getText();
            if(!otherPolicy1.contains("Other Policy configuration"))
                Assert.fail("\"Other Policy configuration\" text is not present in view policy popup.");
            String allowCollaboration1 = driver.findElement(By.cssSelector("div[class='ls-assignment-policy-row ls-assignment-policy-attempts']")).getText();
            if(!allowCollaboration1.contains("Allow Collaboration"))
                Assert.fail("\"Allow Collaboration\" text is not present in view policy popup.");

            //TC row no. 28  //Instructor should not be able to change the option in View Policy pop-up
            String isDisabled = driver.findElement(By.cssSelector("input[name='allowCollaboration']")).getAttribute("disabled");
            if(!isDisabled.contains("true"))
                Assert.fail("Instructor is able to change the option in View Policy pop-up");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case creatingAssignmentPolicyFromAssignThisPopup in class CreatingAssignmentPolicyFromAssignThisPopup.", e);
        }
    }

}
