package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R166;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by Sumit on 8/19/2014.
 */
public class CreatingAndEditingAssignmentPolicyAsMentor extends Driver {

    @Test
    public void creatingAndEditingAssignmentPolicyAsMentor()
    {
        try
        {
            String policyname = ReadTestData.readDataByTagName("", "policyname", "82");
            new LoginUsingLTI().ltiLogin("82");//login as mentor
            new Navigator().NavigateTo("Policies");//go to Policies
            driver.findElement(By.id("newAssignmentPolicy-link")).click();//Click on "+New Assignment Policy" button
            Thread.sleep(2000);
            String otherPolicy = driver.findElement(By.cssSelector("div[class='ls-policy-config-questions-wrapper other-policy-wrapper']")).getText();

            //TC row no. 82......3. Click on "+New Assignment Policy" button..New option "Allow Collaboration" should be present under 'Other Policy Configuration' at the bottom of the New policy tab
            if(!otherPolicy.contains("Other Policy configuration"))
                Assert.fail("\"Other Policy configuration\" text is not present in creating assignment policy page.");
            String allowCollaboration = driver.findElement(By.cssSelector("div[class='ls-assignment-policy-row ls-assignment-policy-attempts']")).getText();
            if(!allowCollaboration.contains("Allow Collaboration"))
                Assert.fail("\"Allow Collaboration\" text is not present in creating assignment policy page for Mentor.");

            //TC row no. 83  //By default , “Allow Collaboration” should be in “disabled” state
            List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@name, 'allowCollaborationupdate-tabId')]"));
            if(!allElements.get(1).getAttribute("checked").contains("true"))
                Assert.fail("By default, \"Allow Collaboration\" is not in \"disabled\" state for Mentor");

            //TC row no. 84  //4. Click on "?"icon for Allow Collaboration policy----Help text should display “Select Enable if you want students to discuss assignment questions after each attempt.”
            driver.findElement(By.id("allow-collaboration-icon")).click();//4. Click on "?"icon for Allow Collaboration policy
            Thread.sleep(2000);
            String helpText = driver.findElement(By.cssSelector("div[class='policy-help-data-container']")).getText();
            if(!helpText.contains("Select Enable if you want students to discuss assignment questions after each attempt."))
                Assert.fail("Help text for allow collaboration is not present in creating assignment policy page for Mentor.");

            //TC row no. 85...//5. Click on Enable option for Allow collaboration option....Instructor should be able to select enable for Allow Collaboration option
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allElements.get(0));//click to enable the social collaboration

            //TC row no. 86...//"6. Enter a name for the policy and click on Save Policy button and Go to Assignment Policies tab"..New Assignment Policy should be created successfully
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon")));
            driver.switchTo().activeElement().sendKeys(policyname);
            driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
            Thread.sleep(2000);
            new Click().clickByclassname("ls-save-policy-btn");		//click on Save Policy
            Thread.sleep(2000);
            new Navigator().NavigateTo("Policies");//go to Policies
            String name = driver.findElement(By.className("assignment-policy-heading")).getText();
            if(!name.contains(policyname))
                Assert.fail("New Assignment policy is not created successfully after enabling the \"Allow Collaboration\" for Mentor.");

            //TC row no. 87...//8. Click on Update Policy..."Allow Collaboration" should be present under 'Other Policy Configuration'
            driver.findElement(By.className("update-policy")).click();//click on Update Policy
            String allowCollaboration1 = driver.findElement(By.cssSelector("div[class='ls-assignment-policy-row ls-assignment-policy-attempts']")).getText();
            if(!allowCollaboration1.contains("Allow Collaboration"))
                Assert.fail("\"Allow Collaboration\" text is not present in creating assignment policy page on clicking update policy for Mentor.");

            //TC row no. 88...//8. Click on Update Policy..."Allow Collaboration" should be in enabled state
            List<WebElement> allElements1 = driver.findElements(By.xpath("//*[starts-with(@name, 'allowCollaborationupdate-tabId')]"));
            if(!allElements1.get(0).getAttribute("checked").contains("true"))
                Assert.fail("\"Allow Collaboration\" is not in \"enabled\" state for Mentor.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case creatingAndEditingAssignmentPolicyAsMentor in class CreatingAndEditingAssignmentPolicyAsMentor.", e);
        }
    }

}
