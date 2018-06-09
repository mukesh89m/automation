package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;

import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Policies;

import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class AssignmentPolicy extends Driver{

    public void editPolicyNameIconClick () {
        WebDriver driver=Driver.getWebDriver();
        try {
            try {
                new MouseHover().mouserhoverbyid("ls-ins-assignment-policy-name");//mouse hover the name field
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon"))); //click on edit icon of policy name
            } catch (Exception e1) {
                new MouseHover().mouserhoverbyid("ls-ins-assignment-policy-name");//mouse hover the name field
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon"))); //click on edit icon of policy name
            }
        }
        catch (Exception e) {
            Assert.fail("Exception while click on edit icon of name field of Assignment Policy form");
        }
    }

    public void editPolicyDescriptionIconClick () {
        WebDriver driver=Driver.getWebDriver();
        try {
            try {
                new MouseHover().mouserhoverbyid("ls-ins-assignment-policy-desc");//mouse hover the description field
                List<WebElement> allEditIcon = driver.findElements(By.cssSelector("i[class='ls-policy-ls-assignment-image ls-ins-edit-assignment-policy-icon']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allEditIcon.get(1));	//click on edit icon to edit description
            } catch (Exception e1) {
                new MouseHover().mouserhoverbyid("ls-ins-assignment-policy-desc");//mouse hover the description field
                List<WebElement> allEditIcon = driver.findElements(By.cssSelector("i[class='ls-policy-ls-assignment-image ls-ins-edit-assignment-policy-icon']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allEditIcon.get(1));	//click on edit icon to edit description
            }
        }
        catch (Exception e) {
            Assert.fail("Exception while click on edit icon of name field of Assignment Policy form");
        }
    }

    public void enterPolicyName (String policyName) {
        WebDriver driver=Driver.getWebDriver();
        try {
            editPolicyNameIconClick();
            Thread.sleep(500);
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys(policyName);
            Thread.sleep(500);
            driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
        }
        catch (Exception e) {
            Assert.fail("Exception while enterning policy name",e);
        }
    }


    public void enterPolicyDescription (String policyDescription) {
        WebDriver driver=Driver.getWebDriver();
        try {
            editPolicyDescriptionIconClick();
            Thread.sleep(500);
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys(policyDescription);
            Thread.sleep(500);
            driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
        }
        catch (Exception e) {
            Assert.fail("Exception while enterning policy name",e);
        }
    }


    public void createAssignmentPolicy(String policyName, String policyDescription, String scorePerQuestion, String Ordering, boolean immediateFeedBack, String numberOfAttempt, String showAnswerAtAttemptNumber, String gradeReleaseOption, String showHintsAtAttemptNumber, String showReadingContentLinkAtAttemptNumber, String showSolutionAtAttemptNumber,String ...dataIndex)
    {

        WebDriver driver=Driver.getWebDriver();
        Policies policies=PageFactory.initElements(driver,Policies.class);
        try
        {
            if(driver.findElements(By.id("newAssignmentPolicy-link")).size()!=0){
                new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
            }
            try {
                new MouseHover().mouserhoverbyid("ls-ins-assignment-policy-name");//mouse hover the name field
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",  driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon"))); //click on edit icon
            }
            catch (Exception e) {
                new MouseHover().mouserhoverbyid("ls-ins-assignment-policy-name");//mouse hover the name field
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",  driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon"))); //click on edit icon
            }
            new UIElement().waitAndFindElement(By.cssSelector("body"));
            driver.switchTo().activeElement().sendKeys(policyName);
            driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
            new MouseHover().mouserhoverbyid("ls-ins-assignment-policy-desc");//mouse hover the description field
            List<WebElement> allEditIcon = driver.findElements(By.cssSelector("i[class='ls-policy-ls-assignment-image ls-ins-edit-assignment-policy-icon']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allEditIcon.get(1));	//click on edit icon to edit description
            new UIElement().waitAndFindElement(By.cssSelector("body"));
            driver.switchTo().activeElement().sendKeys(policyDescription);
            driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
            new UIElement().waitAndFindElement(By.id("score"));
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.id("score")));		//click on score per question field
            driver.findElement(By.id("score")).clear();
            new UIElement().waitAndFindElement(By.id("score"));
            driver.findElement(By.id("score")).sendKeys(scorePerQuestion);
            driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
            if(immediateFeedBack == true)
            {
                //list all the radio options for Immediate Feedback
                List<WebElement> allImmediateFeedback = driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(0));	//click on Enable radio button
            }
            if(immediateFeedBack == false)
            {
                //list all the radio options for Immediate Feedback
                List<WebElement> allImmediateFeedback = driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(1));	//click on Disable radio button
            }
            if(Ordering != null)
            {
                //list all the radio options for Ordering
                List<WebElement> allOrdering = driver.findElements(By.xpath("//*[starts-with(@name, 'orderingupdate-tabId')]"));
                if(Ordering.equals("Randomized"))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOrdering.get(0));	//click on Randomized radio button
                }
                if(Ordering.equals("Keep the assignment order"))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOrdering.get(1));	//click on Keep the assignment order radio button
                }
            }
            if(gradeReleaseOption != null)
            {
                //list all the radio options for Grade Release Option
                List<WebElement> allgradeReleaseOptions = driver.findElements(By.xpath("//*[starts-with(@name, 'gradeReleaseOptionsupdate-tabId')]"));
                if(gradeReleaseOption.equals("Auto-release on assignment submission"))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(0));	//click on Auto-release on assignment submission radio button
                }
                if(gradeReleaseOption.equals("Auto-release on due date"))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(1));	//click on Auto-release on due date radio button

                }
                if(gradeReleaseOption.equals("Release as they are being graded"))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(2));	//click on Release as they are being graded radio button

                }
                if(gradeReleaseOption.equals("Release explicitly by the instructor"))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(3));	//click on Auto-release on assignment submission radio button
                }
            }


            if(dataIndex.length>0){
                String extensionAfterDueDate=ReadTestData.readDataByTagName("","extensionAfterDueDate",dataIndex[0]);
                System.out.println("extensionAfterDueDate:"+extensionAfterDueDate);
                List<WebElement> list=driver.findElements(By.xpath("//*[starts-with(@name,'extendDueDateForStudentupdate-tabId')]"));
                if(extensionAfterDueDate !=null) {
                    if(extensionAfterDueDate.equals("disable")){
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", list.get(1));	//click on disable
                    }
                }
            }

            if(dataIndex.length>0){
                String timeBasedAssignment=ReadTestData.readDataByTagName("","timeBasedAssignment",dataIndex[0]);
                String timeDuration=ReadTestData.readDataByTagName("","timeDuration",dataIndex[0]);

                System.out.println("timeBasedAssignment:"+timeBasedAssignment);
                if(timeBasedAssignment !=null) {
                    if(timeBasedAssignment.equals("enable")){
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", policies.timeLimit_enable);	//click on enable
                    }else{
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", policies.timeLimit_disable);	//click on disable

                    }

                    WebDriverUtil.executeJavascript("arguments[0].value='"+timeDuration+"';", policies.timeDuration_textBox);
                }
            }

            if(!numberOfAttempt.equals("1"))
            {
                if(!numberOfAttempt.equalsIgnoreCase("Unlimited")) {
                    new ComboBox().selectValue(0, numberOfAttempt);    //select a value from the dropdown 'Number of attempts'
                }
                if(numberOfAttempt.equalsIgnoreCase("Unlimited"))
                {
                    driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]")).get(0) .click();
                    WebElement element=driver.findElement(By.xpath("//a[text()='Unlimited']"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
                    new UIElement().waitAndFindElement(By.linkText("Unlimited"));
                    new Click().clickbylinkText("Unlimited");
                }
            }

            if(!showHintsAtAttemptNumber.equals(""))
            {
                //list all the radio options for show hints
                List<WebElement> allshowHints = driver.findElements(By.xpath("//*[starts-with(@name, 'showHintsupdate-tabId')]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allshowHints.get(0));	//click on Yes radio button in Show hint
                List<WebElement> allElements = driver.findElements(By.className("sbSelector"));
                if(!allElements.get(1).getText().equals(showHintsAtAttemptNumber))
                {
                    new ComboBox().selectValue(2, showHintsAtAttemptNumber);	//click on dropdown 'At Attempt Number' for Show Hint and select a value
                }

            }
            if(!showReadingContentLinkAtAttemptNumber.equals(""))
            {
                //list all the radio options for show hints
                List<WebElement> allShowReadingContent = driver.findElements(By.xpath("//*[starts-with(@name, 'showReadContentLinkupdate-tabId')]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allShowReadingContent.get(0));	//click on Yes radio button in Show Reading Content
                List<WebElement> allElements = driver.findElements(By.className("sbSelector"));
                if(allElements.get(2).getText().equals(showReadingContentLinkAtAttemptNumber))
                {
                    List<WebElement> allElements1 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
                    allElements1.get(3).click();
                    List<WebElement> allOptions = driver.findElements(By.linkText(showReadingContentLinkAtAttemptNumber));
                    allOptions.get(1).click();
                }
                else
                {
                    new ComboBox().selectValue(3, showReadingContentLinkAtAttemptNumber);
                }

            }
            if(!showSolutionAtAttemptNumber.equals(""))
            {

                //list all the radio options for show hints
                List<WebElement> allshowSolution = driver.findElements(By.xpath("//*[starts-with(@name, 'showSolutionupdate-tabId')]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allshowSolution.get(0));	//click on Yes radio button in Show solution
                List<WebElement> allElements = driver.findElements(By.className("sbSelector"));
                if(allElements.get(3).getText().equals(showSolutionAtAttemptNumber))
                {
                    List<WebElement> allElements1 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
                    new UIElement().waitAndFindElement(allElements1.get(4));
                    allElements1.get(3).click();
                    // allElements1.get(4).click();
                    List<WebElement> allOptions = driver.findElements(By.linkText(showSolutionAtAttemptNumber));
                    allOptions.get(2).click();
                }
                else{
                    new ComboBox().selectValue(3, showSolutionAtAttemptNumber);
                    // new ComboBox().selectValue(4, showSolutionAtAttemptNumber);

                }

            }
            if(!showAnswerAtAttemptNumber.equals(""))
            {

                //list all the radio options for Show Answer
                List<WebElement> allShowAnswer = driver.findElements(By.xpath("//*[starts-with(@name, 'showAnswerupdate-tabId')]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allShowAnswer.get(0));	//click on Yes radio button
                List<WebElement> allElements = driver.findElements(By.className("sbSelector"));

                // if(allElements.get(5).getText().equals(showSolutionAtAttemptNumber))
                if(allElements.get(4).getText().equals(showSolutionAtAttemptNumber))
                {
                    List<WebElement> allElements1 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
                    new UIElement().waitAndFindElement(allElements1.get(4));
                    allElements1.get(4).click();
                    // allElements1.get(5).click();

                    List<WebElement> allOptions = driver.findElements(By.linkText(showAnswerAtAttemptNumber));
                    allOptions.get(1).click();

                }
                else
                {
                    //new ComboBox().selectValue(5, showAnswerAtAttemptNumber);
                    List<WebElement> allElements1 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
                    new UIElement().waitAndFindElement(allElements1.get(4));
                    allElements1.get(4).click();
                    //allElements1.get(5).click();
                    new UIElement().waitAndFindElement(By.xpath("//div[@id='show-answer-policy-attempts-wrapper']//li/a[text()='"+showAnswerAtAttemptNumber+"']"));
                    driver.findElement(By.xpath("//div[@id='show-answer-policy-attempts-wrapper']//li/a[text()='"+showAnswerAtAttemptNumber+"']")).click();

                }

            }

            driver.findElement(By.xpath("//*[starts-with(@name,'allowCollaborationupdate-tabId')]")).click();
            new Click().clickByclassname("ls-save-policy-btn");		//click on Save Policy
            new UIElement().waitAndFindElement(By.className("policy-notification-text-span"));
            String notification = new TextFetch().textfetchbyclass("policy-notification-text-span");
            if(!notification.contains("Saved New Assignment Policy Successfully."))
            {
                Assert.fail("Error in creating Assignment Policy.");
            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper createAssignmentPolicy in class AssignmentPolicy",e);
        }
    }






    public void createAssignmentPolicyWhileAssigning(String policyName, String policyDescription, String scorePerQuestion, String Ordering, boolean immediateFeedBack, String numberOfAttempt, String showAnswerAtAttemptNumber, String gradeReleaseOption, String showHintsAtAttemptNumber, String showReadingContentLinkAtAttemptNumber, String showSolutionAtAttemptNumber)
    {
        try
        {

            WebDriver driver=Driver.getWebDriver();

            try {
                new MouseHover().mouserhoverbyid("ls-ins-assignment-policy-name");//mouse hover the name field
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",  driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon"))); //click on edit icon
            }
            catch (Exception e) {
                new MouseHover().mouserhoverbyid("ls-ins-assignment-policy-name");//mouse hover the name field
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",  driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon"))); //click on edit icon
            }
            new UIElement().waitAndFindElement(By.cssSelector("body"));
            driver.switchTo().activeElement().sendKeys(policyName);
            driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
            new MouseHover().mouserhoverbyid("ls-ins-assignment-policy-desc");//mouse hover the description field
            List<WebElement> allEditIcon = driver.findElements(By.cssSelector("i[class='ls-policy-ls-assignment-image ls-ins-edit-assignment-policy-icon']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allEditIcon.get(1));	//click on edit icon to edit description
            new UIElement().waitAndFindElement(By.cssSelector("body"));
            driver.switchTo().activeElement().sendKeys(policyDescription);
            driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
            new UIElement().waitAndFindElement(By.id("score"));
            driver.findElement(By.id("score")).click();		//click on score per question field
            driver.findElement(By.id("score")).clear();
            new UIElement().waitAndFindElement(By.id("score"));
            driver.findElement(By.id("score")).sendKeys(scorePerQuestion);
            driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
            if(immediateFeedBack == true)
            {
                //list all the radio options for Immediate Feedback
                List<WebElement> allImmediateFeedback = driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(0));	//click on Enable radio button
            }
            if(immediateFeedBack == false)
            {
                //list all the radio options for Immediate Feedback
                List<WebElement> allImmediateFeedback = driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(1));	//click on Disable radio button
            }
            if(Ordering != null)
            {
                //list all the radio options for Ordering
                List<WebElement> allOrdering = driver.findElements(By.xpath("/*//*[starts-with(@name, 'orderingupdate-tabId')]"));
                if(Ordering.equals("Randomized"))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOrdering.get(0));	//click on Randomized radio button
                }
                if(Ordering.equals("Keep the assignment order"))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allOrdering.get(1));	//click on Keep the assignment order radio button
                }
            }
            if(gradeReleaseOption != null)
            {
                //list all the radio options for Grade Release Option
                List<WebElement> allgradeReleaseOptions = driver.findElements(By.xpath("//*[starts-with(@name, 'gradeReleaseOptionsupdate-tabId')]"));
                if(gradeReleaseOption.equals("Auto-release on assignment submission"))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(0));	//click on Auto-release on assignment submission radio button
                }
                if(gradeReleaseOption.equals("Auto-release on due date"))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(1));	//click on Auto-release on due date radio button

                }
                if(gradeReleaseOption.equals("Release as they are being graded"))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(2));	//click on Release as they are being graded radio button

                }
                if(gradeReleaseOption.equals("Release explicitly by the instructor"))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(3));	//click on Auto-release on assignment submission radio button
                }
            }
            if(!numberOfAttempt.equals("1"))
            {
                if(!numberOfAttempt.equalsIgnoreCase("Unlimited")) {
                    new ComboBox().selectValue(1, numberOfAttempt);    //select a value from the dropdown 'Number of attempts'
                }
                if(numberOfAttempt.equalsIgnoreCase("Unlimited"))
                {
                    driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]")).get(1) .click();
                    WebElement element=driver.findElement(By.xpath("//a[text()='Unlimited']"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
                    new UIElement().waitAndFindElement(By.linkText("Unlimited"));
                    new Click().clickbylinkText("Unlimited");
                }
            }

            if(!showHintsAtAttemptNumber.equals(""))
            {
                //list all the radio options for show hints
                List<WebElement> allshowHints = driver.findElements(By.xpath("/*//*[starts-with(@name, 'showHintsupdate-tabId')]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allshowHints.get(0));	//click on Yes radio button in Show hint
                List<WebElement> allElements = driver.findElements(By.className("sbSelector"));
                if(!allElements.get(1).getText().equals(showHintsAtAttemptNumber))
                {
                    new ComboBox().selectValue(2, showHintsAtAttemptNumber);	//click on dropdown 'At Attempt Number' for Show Hint and select a value
                }

            }
            if(!showReadingContentLinkAtAttemptNumber.equals(""))
            {
                //list all the radio options for show hints
                List<WebElement> allShowReadingContent = driver.findElements(By.xpath("/*//*[starts-with(@name, 'showReadContentLinkupdate-tabId')]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allShowReadingContent.get(0));	//click on Yes radio button in Show Reading Content
                List<WebElement> allElements = driver.findElements(By.className("sbSelector"));
                if(allElements.get(2).getText().equals(showReadingContentLinkAtAttemptNumber))
                {
                    List<WebElement> allElements1 = driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]"));
                    allElements1.get(3).click();
                    List<WebElement> allOptions = driver.findElements(By.linkText(showReadingContentLinkAtAttemptNumber));
                    allOptions.get(1).click();
                }
                else
                {
                    new ComboBox().selectValue(3, showReadingContentLinkAtAttemptNumber);
                }

            }
            if(!showSolutionAtAttemptNumber.equals(""))
            {

                //list all the radio options for show hints
                List<WebElement> allshowSolution = driver.findElements(By.xpath("/*//*[starts-with(@name, 'showSolutionupdate-tabId')]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allshowSolution.get(0));	//click on Yes radio button in Show solution
                List<WebElement> allElements = driver.findElements(By.className("sbSelector"));
                if(allElements.get(3).getText().equals(showSolutionAtAttemptNumber))
                {
                    List<WebElement> allElements1 = driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]"));
                    new UIElement().waitAndFindElement(allElements1.get(4));
                    allElements1.get(4).click();
                    List<WebElement> allOptions = driver.findElements(By.linkText(showSolutionAtAttemptNumber));
                    allOptions.get(2).click();
                }
                else{
                    new ComboBox().selectValue(4, showSolutionAtAttemptNumber);
                }

            }
            if(!showAnswerAtAttemptNumber.equals(""))
            {

                //list all the radio options for Show Answer
                List<WebElement> allShowAnswer = driver.findElements(By.xpath("//*[starts-with(@name, 'showAnswerupdate-tabId')]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allShowAnswer.get(0));	//click on Yes radio button
                List<WebElement> allElements = driver.findElements(By.className("sbSelector"));

                // if(allElements.get(5).getText().equals(showSolutionAtAttemptNumber))
                if(allElements.get(4).getText().equals(showSolutionAtAttemptNumber))
                {
                    List<WebElement> allElements1 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
                    new UIElement().waitAndFindElement(allElements1.get(4));
                    allElements1.get(4).click();
                    // allElements1.get(5).click();

                    List<WebElement> allOptions = driver.findElements(By.linkText(showAnswerAtAttemptNumber));
                    allOptions.get(1).click();

                }
                else
                {
                    //new ComboBox().selectValue(5, showAnswerAtAttemptNumber);
                    List<WebElement> allElements1 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
                    new UIElement().waitAndFindElement(allElements1.get(4));
                    WebDriverUtil.clickOnElementUsingJavascript(allElements1.get(4));
                    //allElements1.get(5).click();
                    new UIElement().waitAndFindElement(By.xpath("//div[@id='show-answer-policy-attempts-wrapper']//li/a[text()='"+showAnswerAtAttemptNumber+"']"));
                    driver.findElement(By.xpath("//div[@id='show-answer-policy-attempts-wrapper']//li/a[text()='"+showAnswerAtAttemptNumber+"']")).click();

                }

            }


            driver.findElement(By.xpath("//*[starts-with(@name,'allowCollaborationupdate-tabId')]")).click();
            new Click().clickByclassname("ls-save-policy-btn");		//click on Save Policy
            new UIElement().waitAndFindElement(By.linkText(policyName));
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper createAssignmentPolicyWhileAssigning in class AssignmentPolicy",e);
        }
    }

    public void createAssignmentReference(String dataIndex){
        try{
            WebDriver driver = Driver.getWebDriver();
            String assignmentReferenceName = ReadTestData.readDataByTagName("", "assignmentReferenceName", dataIndex);
            String assignmentDescription = ReadTestData.readDataByTagName("", "assignmentDescription", dataIndex);
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);

            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            //assignments.textField_assignmentReferenceName.click();
            Thread.sleep(9000);
            assignments.textField_assignmentReferenceName.sendKeys(assignmentReferenceName);
            assignments.textField_assignmentDescription.sendKeys(assignmentDescription);
            assignments.link_uploadFile.click();
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(9000);
            //WebDriverUtil.waitTillVisibilityOfElement(assignments.textField_assignmentReferenceName,15);

            assignments.button_save.click();
        }catch(Exception e){
            Assert.fail("Exception in the apphelper 'createAssignmentReference' in the class 'AssignmentPolicy' ",e);
        }
    }



    //open assignment plocy form instructor resources page
    public void openAssignmentPolicy(String assignemntname)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new Navigator().NavigateTo("My Question Bank");
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Question Banks']"));
            new Click().clickbyxpath("//span[@title='Question Banks']");
            Thread.sleep(2000);
            new TextSend().textsendbyid("\""+assignemntname+"\"", "all-resource-search-textarea");
            new Click().clickByid("all-resource-search-button");
            new Click().clickByclassname("assign-this");
            new Click().clickByclassname("ir-ls-assign-dialog-gradable-label-check");
            new Click().clickByid("assignment-policy-icons");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper openAssignmentPolicy in class AssignmentPolicy",e);
        }
    }

    //Author Sumit on 14/8/2014
    //It will update the Allow collaboration
    public void updateAllowCollaboration(String dataIndex, boolean allowCollaboration)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String policyname = ReadTestData.readDataByTagName("", "policyname", dataIndex);
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            int index = 0;
            List<WebElement> allPolicy = driver.findElements(By.className("assignment-policy-heading"));
            for(WebElement l: allPolicy)
            {
                if(l.getText().contains(policyname))
                {
                    break;
                }
                index++;
            }
            List<WebElement> allUpdateLink = driver.findElements(By.className("update-policy"));
            allUpdateLink.get(index).click();//click on update link
            List<WebElement> allowCollaborationRadioButton = driver.findElements(By.xpath("//*[starts-with(@name, 'allowCollaborationupdate-tabId')]"));
            if(allowCollaboration == true)
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allowCollaborationRadioButton.get(0));
            //allowCollaborationRadioButton.get(0).click();
            if(allowCollaboration == false)
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allowCollaborationRadioButton.get(1));
            //allowCollaborationRadioButton.get(1).click();
            new Click().clickByclassname("ls-save-policy-btn");		//click on Save Policy
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in apphelper updateAllowCollaboration in class AssignmentPolicy",e);
        }
    }

    //Create policy with langauge accent option

    public void policyCreation(String policyName, String policyDescription,int dataIndex) throws InterruptedException {
        String languageAccent = ReadTestData.readDataByTagName("", "languageAccent", Integer.toString(dataIndex));
        String immediateFeedBack = ReadTestData.readDataByTagName("", "immediateFeedBack", Integer.toString(dataIndex));

        System.out.println("languageAccent:"+languageAccent);
        WebDriver driver=Driver.getWebDriver();
        try {
            new MouseHover().mouserhoverbyid("ls-ins-assignment-policy-name");//mouse hover the name field
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon"))); //click on edit icon
        } catch (Exception e) {
            new MouseHover().mouserhoverbyid("ls-ins-assignment-policy-name");//mouse hover the name field
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon"))); //click on edit icon
        }
        Thread.sleep(2000);
        driver.switchTo().activeElement().sendKeys(policyName);
        driver.findElement(By.cssSelector("body")).click();    //click outside the textbox
        new MouseHover().mouserhoverbyid("ls-ins-assignment-policy-desc");//mouse hover the description field
        List<WebElement> allEditIcon = driver.findElements(By.cssSelector("i[class='ls-policy-ls-assignment-image ls-ins-edit-assignment-policy-icon']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allEditIcon.get(1));    //click on edit icon to edit description
        Thread.sleep(2000);
        driver.switchTo().activeElement().sendKeys(policyDescription);
        driver.findElement(By.cssSelector("body")).click();    //click outside the textbox
        Thread.sleep(4000);

        if(immediateFeedBack!=null) {
            if (immediateFeedBack.equals("true")) {
                //list all the radio options for Immediate Feedback
                List<WebElement> allImmediateFeedback = driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allImmediateFeedback.get(0));    //click on Enable radio button
                Thread.sleep(2000);
            }
        }
        if(languageAccent!=null && !languageAccent.equalsIgnoreCase("None"))
        {

            driver.findElement(By.xpath("//a[text()='None']")).click();
            driver.findElement(By.xpath("//a[contains(text(),'"+languageAccent+"')]")).click();

        }


        new Click().clickByclassname("ls-save-policy-btn");        //click on Save Policy
        Thread.sleep(2000);
        String notification = new TextFetch().textfetchbyclass("policy-notification-text-span");
        if (!notification.contains("Saved New Assignment Policy Successfully.")) {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Error in creating Assignment Policy.");
        }
    }

    /**
     *@Author Mukesh
     * This method will enable policy from cms
     */
    public void CMSEnablePolicy(int dataIndex)
    {
        String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
        WebDriver driver=Driver.getWebDriver();
        try {
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            new DirectLogin().CMSLogin();
            if(course_type==null) {
                new SelectCourse().selectcourse();
            }
            else{
                new SelectCourse().selectLSCourse();
            }
            courseOutline.courseDetails.click(); //click on the courseDetails icon
            Thread.sleep(3000);
            driver.findElement(By.xpath("//a[@rel='3']")).click();
            courseOutline.enable_radioButton.click();//click on the enable radio button
            courseOutline.save_button.click();
        } catch (InterruptedException e) {
            Assert.fail("Exception in method CMSEnablePolicy of class Assignment Policy,",e);
        }
    }

    public void CMSLangaugePaletteDisable(int dataIndex)
    {
        String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
        WebDriver driver=Driver.getWebDriver();
        try {
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            new DirectLogin().CMSLogin();
            if(course_type==null) {
                new SelectCourse().selectcourse();
            }
            else{
                new SelectCourse().selectLSCourse();
            }
            courseOutline.courseDetails.click(); //click on the courseDetails icon
            Thread.sleep(3000);
            driver.findElement(By.xpath("//a[@rel='3']")).click();
            new Click().clickBycssselector("input[class='courseLanguagePalettePolicy-radio-btm disable']");//click on the disable radio button
            courseOutline.save_button.click();
        } catch (InterruptedException e) {
            Assert.fail("Exception in method CMSLangaugePaletteDisable of class Assignment Policy,",e);
        }
    }
    public void CMSLangaugePaletteEnable(int dataIndex)
    {
        String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
        WebDriver driver=Driver.getWebDriver();
        try {
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            new DirectLogin().CMSLogin();
            if(course_type==null) {
                new SelectCourse().selectcourse();
            }
            else{
                new SelectCourse().selectLSCourse();
            }
            courseOutline.courseDetails.click(); //click on the courseDetails icon
            Thread.sleep(3000);
            driver.findElement(By.xpath("//a[@rel='3']")).click();
            new Click().clickBycssselector("input[class='courseLanguagePalettePolicy-radio-btm enable']");//click on the enable radio button
            courseOutline.save_button.click();
        } catch (InterruptedException e) {
            Assert.fail("Exception in method CMSLangaugePaletteEnable of class Assignment Policy,",e);
        }
    }
    //Author Mukesh
    // This method will disable policy from cms
    public void CMSDisablePolicy(int dataIndex)
    {
        String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
        WebDriver driver=Driver.getWebDriver();
        try {
            new DirectLogin().CMSLogin();
            if(course_type==null) {
                new SelectCourse().selectcourse();
            }
            else{
                new SelectCourse().selectLSCourse();
            }
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            courseOutline.courseDetails.click(); //click on the courseDetails icon
            Thread.sleep(3000);
            driver.findElement(By.xpath("//a[@rel='3']")).click();
            courseOutline.disable_radioButton.click();//click on the disable radio button
            courseOutline.save_button.click();
        } catch (InterruptedException e) {
            Assert.fail("Exception in method CMSDisablePolicy of class Assignment Policy,",e);
        }
    }
}
