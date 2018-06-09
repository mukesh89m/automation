package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.*;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.*;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.TextSend;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;

public class AssignmentPolicy extends  Driver{

    public void editPolicyNameIconClick () {
        try {
            try {
                MouseHover.mouserhoverbyid("ls-ins-assignment-policy-name");//mouse hover the name field
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon"))); //click on edit icon of policy name
            } catch (Exception e1) {
                MouseHover.mouserhoverbyid("ls-ins-assignment-policy-name");//mouse hover the name field
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon"))); //click on edit icon of policy name
            }
        }
        catch (Exception e) {
            Assert.fail("Exception while click on edit icon of name field of Assignment Policy form");
        }
    }

    public void editPolicyDescriptionIconClick () {
        try {
            try {
                MouseHover.mouserhoverbyid("ls-ins-assignment-policy-desc");//mouse hover the description field
                List<WebElement> allEditIcon = Driver.driver.findElements(By.cssSelector("i[class='ls-policy-ls-assignment-image ls-ins-edit-assignment-policy-icon']"));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allEditIcon.get(1));	//click on edit icon to edit description
            } catch (Exception e1) {
                MouseHover.mouserhoverbyid("ls-ins-assignment-policy-desc");//mouse hover the description field
                List<WebElement> allEditIcon = Driver.driver.findElements(By.cssSelector("i[class='ls-policy-ls-assignment-image ls-ins-edit-assignment-policy-icon']"));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allEditIcon.get(1));	//click on edit icon to edit description
            }
        }
        catch (Exception e) {
            Assert.fail("Exception while click on edit icon of name field of Assignment Policy form");
        }
    }

    public void enterPolicyName (String policyName) {
        try {
            editPolicyNameIconClick();
            Thread.sleep(500);
            Driver.driver.switchTo().activeElement().clear();
            Driver.driver.switchTo().activeElement().sendKeys(policyName);
            Thread.sleep(500);
            Driver.driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
        }
        catch (Exception e) {
            Assert.fail("Exception while enterning policy name",e);
        }
    }


    public void enterPolicyDescription (String policyDescription) {
        try {
            editPolicyDescriptionIconClick();
            Thread.sleep(500);
            Driver.driver.switchTo().activeElement().clear();
            Driver.driver.switchTo().activeElement().sendKeys(policyDescription);
            Thread.sleep(500);
            Driver.driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
        }
        catch (Exception e) {
            Assert.fail("Exception while enterning policy name",e);
        }
    }


    public void createAssignmentPolicy(String policyName, String policyDescription, String scorePerQuestion, String Ordering, boolean immediateFeedBack, String numberOfAttempt, String showAnswerAtAttemptNumber, String gradeReleaseOption, String showHintsAtAttemptNumber, String showReadingContentLinkAtAttemptNumber, String showSolutionAtAttemptNumber)
    {
        try
        {
            new UIElement().waitAndFindElement(By.id("newAssignmentPolicy-link"));
            new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
            try {
                MouseHover.mouserhoverbyid("ls-ins-assignment-policy-name");//mouse hover the name field
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon"))); //click on edit icon
            }
            catch (Exception e) {
                MouseHover.mouserhoverbyid("ls-ins-assignment-policy-name");//mouse hover the name field
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",  Driver.driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon"))); //click on edit icon
            }
            new UIElement().waitAndFindElement(By.cssSelector("body"));
            Driver.driver.switchTo().activeElement().sendKeys(policyName);
            Driver.driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
            MouseHover.mouserhoverbyid("ls-ins-assignment-policy-desc");//mouse hover the description field
            List<WebElement> allEditIcon = Driver.driver.findElements(By.cssSelector("i[class='ls-policy-ls-assignment-image ls-ins-edit-assignment-policy-icon']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allEditIcon.get(1));	//click on edit icon to edit description
            new UIElement().waitAndFindElement(By.cssSelector("body"));
            Driver.driver.switchTo().activeElement().sendKeys(policyDescription);
            Driver.driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
            new UIElement().waitAndFindElement(By.id("score"));
            Driver.driver.findElement(By.id("score")).click();		//click on score per question field
            Driver.driver.findElement(By.id("score")).clear();
            new UIElement().waitAndFindElement(By.id("score"));
            Driver.driver.findElement(By.id("score")).sendKeys(scorePerQuestion);
            Driver.driver.findElement(By.cssSelector("body")).click();	//click outside the textbox
            if(immediateFeedBack == true)
            {
                //list all the radio options for Immediate Feedback
                List<WebElement> allImmediateFeedback = Driver.driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allImmediateFeedback.get(0));	//click on Enable radio button
            }
            if(immediateFeedBack == false)
            {
                //list all the radio options for Immediate Feedback
                List<WebElement> allImmediateFeedback = Driver.driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allImmediateFeedback.get(1));	//click on Disable radio button
            }
            if(Ordering != null)
            {
                //list all the radio options for Ordering
                List<WebElement> allOrdering = Driver.driver.findElements(By.xpath("/*//*[starts-with(@name, 'orderingupdate-tabId')]"));
                if(Ordering.equals("Randomized"))
                {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allOrdering.get(0));	//click on Randomized radio button
                }
                if(Ordering.equals("Keep the assignment order"))
                {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allOrdering.get(1));	//click on Keep the assignment order radio button
                }
            }
            if(gradeReleaseOption != null)
            {
                //list all the radio options for Grade Release Option
                List<WebElement> allgradeReleaseOptions = Driver.driver.findElements(By.xpath("//*[starts-with(@name, 'gradeReleaseOptionsupdate-tabId')]"));
                if(gradeReleaseOption.equals("Auto-release on assignment submission"))
                {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(0));	//click on Auto-release on assignment submission radio button
                 }
                if(gradeReleaseOption.equals("Auto-release on due date"))
                {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(1));	//click on Auto-release on due date radio button

                }
                if(gradeReleaseOption.equals("Release as they are being graded"))
                {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(2));	//click on Release as they are being graded radio button

                }
                if(gradeReleaseOption.equals("Release explicitly by the instructor"))
                {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allgradeReleaseOptions.get(3));	//click on Auto-release on assignment submission radio button
                }
            }
            if(!numberOfAttempt.equals("1"))
            {
                if(!numberOfAttempt.equalsIgnoreCase("Unlimited")) {
                    new ComboBox().selectValue(1, numberOfAttempt);    //select a value from the dropdown 'Number of attempts'
                }
                if(numberOfAttempt.equalsIgnoreCase("Unlimited"))
                {
                    Driver.driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]")).get(1) .click();
                    WebElement element=Driver.driver.findElement(By.xpath("//a[text()='Unlimited']"));
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);",element);
                    new UIElement().waitAndFindElement(By.linkText("Unlimited"));
                    new Click().clickbylinkText("Unlimited");
                }
            }

            if(!showHintsAtAttemptNumber.equals(""))
            {
                //list all the radio options for show hints
                List<WebElement> allshowHints = Driver.driver.findElements(By.xpath("/*//*[starts-with(@name, 'showHintsupdate-tabId')]"));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allshowHints.get(0));	//click on Yes radio button in Show hint
                List<WebElement> allElements = Driver.driver.findElements(By.className("sbSelector"));
                if(!allElements.get(1).getText().equals(showHintsAtAttemptNumber))
                {
                    new ComboBox().selectValue(2, showHintsAtAttemptNumber);	//click on dropdown 'At Attempt Number' for Show Hint and select a value
                }

            }
            if(!showReadingContentLinkAtAttemptNumber.equals(""))
            {
                //list all the radio options for show hints
                List<WebElement> allShowReadingContent = Driver.driver.findElements(By.xpath("/*//*[starts-with(@name, 'showReadContentLinkupdate-tabId')]"));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allShowReadingContent.get(0));	//click on Yes radio button in Show Reading Content
                List<WebElement> allElements = Driver.driver.findElements(By.className("sbSelector"));
                if(allElements.get(2).getText().equals(showReadingContentLinkAtAttemptNumber))
                {
                    List<WebElement> allElements1 = Driver.driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]"));
                    allElements1.get(3).click();
                    List<WebElement> allOptions = Driver.driver.findElements(By.linkText(showReadingContentLinkAtAttemptNumber));
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
                List<WebElement> allshowSolution = Driver.driver.findElements(By.xpath("/*//*[starts-with(@name, 'showSolutionupdate-tabId')]"));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allshowSolution.get(0));	//click on Yes radio button in Show solution
                List<WebElement> allElements = Driver.driver.findElements(By.className("sbSelector"));
                if(allElements.get(3).getText().equals(showSolutionAtAttemptNumber))
                {
                    List<WebElement> allElements1 = Driver.driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]"));
                    new UIElement().waitAndFindElement(allElements1.get(4));
                    allElements1.get(4).click();
                    List<WebElement> allOptions = Driver.driver.findElements(By.linkText(showSolutionAtAttemptNumber));
                    allOptions.get(2).click();
                }
                else{
                    new ComboBox().selectValue(4, showSolutionAtAttemptNumber);
                }

            }
            if(!showAnswerAtAttemptNumber.equals(""))
            {

                //list all the radio options for Show Answer
                List<WebElement> allShowAnswer = Driver.driver.findElements(By.xpath("/*//*[starts-with(@name, 'showAnswerupdate-tabId')]"));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allShowAnswer.get(0));	//click on Yes radio button
                List<WebElement> allElements = Driver.driver.findElements(By.className("sbSelector"));

                if(allElements.get(5).getText().equals(showSolutionAtAttemptNumber))
                {
                    List<WebElement> allElements1 = Driver.driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]"));
                    new UIElement().waitAndFindElement(allElements1.get(5));
                    allElements1.get(5).click();
                    List<WebElement> allOptions = Driver.driver.findElements(By.linkText(showAnswerAtAttemptNumber));
                    allOptions.get(1).click();
                }
                else
                {
                    //new ComboBox().selectValue(5, showAnswerAtAttemptNumber);
                    List<WebElement> allElements1 = Driver.driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]"));
                    new UIElement().waitAndFindElement(allElements1.get(5));
                    allElements1.get(5).click();
                    new UIElement().waitAndFindElement(By.xpath("//div[@id='show-answer-policy-attempts-wrapper']//li/a[text()='"+showAnswerAtAttemptNumber+"']"));
                    Driver.driver.findElement(By.xpath("//div[@id='show-answer-policy-attempts-wrapper']//li/a[text()='"+showAnswerAtAttemptNumber+"']")).click();

                }

            }
            Driver.driver.findElement(By.xpath("/*//*[starts-with(@name,'allowCollaborationupdate-tabId')]")).click();
            new Click().clickByclassname("ls-save-policy-btn");		//click on Save Policy
            new UIElement().waitAndFindElement(By.className("policy-notification-text-span"));
            String notification = new TextFetch().textfetchbyclass("policy-notification-text-span");
            if(!notification.contains("Saved New Assignment Policy Successfully."))
            {
                new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Error in creating Assignment Policy.");
            }

        }
        catch(Exception e)
        {
            //	new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in apphelper createAssignmentPolicy in class AssignmentPolicy",e);
        }
    }
    //open assignment plocy form instructor resources page
    public void openAssignmentPolicy(String assignemntname)
    {
        try
        {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("My Question Bank");
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
        try
        {
            String policyname = ReadTestData.readDataByTagName("", "policyname", dataIndex);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            int index = 0;
            List<WebElement> allPolicy = Driver.driver.findElements(By.className("assignment-policy-heading"));
            for(WebElement l: allPolicy)
            {
                if(l.getText().contains(policyname))
                {
                    break;
                }
                index++;
            }
            List<WebElement> allUpdateLink = Driver.driver.findElements(By.className("update-policy"));
            allUpdateLink.get(index).click();//click on update link
            List<WebElement> allowCollaborationRadioButton = Driver.driver.findElements(By.xpath("//*[starts-with(@name, 'allowCollaborationupdate-tabId')]"));
            if(allowCollaboration == true)
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allowCollaborationRadioButton.get(0));
            //allowCollaborationRadioButton.get(0).click();
            if(allowCollaboration == false)
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allowCollaborationRadioButton.get(1));
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

        try {
            MouseHover.mouserhoverbyid("ls-ins-assignment-policy-name");//mouse hover the name field
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon"))); //click on edit icon
        } catch (Exception e) {
            MouseHover.mouserhoverbyid("ls-ins-assignment-policy-name");//mouse hover the name field
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("i.ls-policy-ls-assignment-image.ls-ins-edit-assignment-policy-icon"))); //click on edit icon
        }
        Thread.sleep(2000);
        Driver.driver.switchTo().activeElement().sendKeys(policyName);
        Driver.driver.findElement(By.cssSelector("body")).click();    //click outside the textbox
        MouseHover.mouserhoverbyid("ls-ins-assignment-policy-desc");//mouse hover the description field
        List<WebElement> allEditIcon = Driver.driver.findElements(By.cssSelector("i[class='ls-policy-ls-assignment-image ls-ins-edit-assignment-policy-icon']"));
        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allEditIcon.get(1));    //click on edit icon to edit description
        Thread.sleep(2000);
        Driver.driver.switchTo().activeElement().sendKeys(policyDescription);
        Driver.driver.findElement(By.cssSelector("body")).click();    //click outside the textbox
        Thread.sleep(4000);

        if(immediateFeedBack!=null) {
            if (immediateFeedBack.equals("true")) {
                //list all the radio options for Immediate Feedback
                List<WebElement> allImmediateFeedback = Driver.driver.findElements(By.xpath("//*[starts-with(@name, 'immediateFeedbackupdate-tabId')]"));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allImmediateFeedback.get(0));    //click on Enable radio button
                Thread.sleep(2000);
            }
        }
        if(languageAccent!=null && !languageAccent.equalsIgnoreCase("None"))
        {

            Driver.driver.findElement(By.xpath("//a[text()='None']")).click();
            Driver.driver.findElement(By.xpath("//a[contains(text(),'"+languageAccent+"')]")).click();

        }


        new Click().clickByclassname("ls-save-policy-btn");        //click on Save Policy
        Thread.sleep(2000);
        String notification = new TextFetch().textfetchbyclass("policy-notification-text-span");
        if (!notification.contains("Saved New Assignment Policy Successfully.")) {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Error in creating Assignment Policy.");
        }
    }
    //Author Mukesh
    // This method will enable policy from cms
    public void CMSEnablePolicy(int dataIndex)
    {
        String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));

        try {
            CourseOutline courseOutline = PageFactory.initElements(Driver.driver, CourseOutline.class);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DirectLogin().CMSLogin();
            if(course_type==null) {
                new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.SelectCourse().selectcourse();
            }
            else{
                new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.SelectCourse().selectLSCourse();
            }
            courseOutline.courseDetails.click(); //click on the courseDetails icon
            Thread.sleep(3000);
            Driver.driver.findElement(By.xpath("//a[@rel='3']")).click();
            courseOutline.enable_radioButton.click();//click on the enable radio button
            courseOutline.save_button.click();
        } catch (InterruptedException e) {
            Assert.fail("Exception in method CMSEnablePolicy of class Assignment Policy,",e);
        }
    }

    //Author Mukesh
    // This method will disable policy from cms
    public void CMSDisablePolicy(int dataIndex)
    {
        String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));

        try {
            new DirectLogin().CMSLogin();
            if(course_type==null) {
                new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.SelectCourse().selectcourse();
            }
            else{
                new SelectCourse().selectLSCourse();
            }
            CourseOutline courseOutline = PageFactory.initElements(Driver.driver, CourseOutline.class);
            courseOutline.courseDetails.click(); //click on the courseDetails icon
            Thread.sleep(3000);
            Driver.driver.findElement(By.xpath("//a[@rel='3']")).click();
            courseOutline.disable_radioButton.click();//click on the disable radio button
            courseOutline.save_button.click();
        } catch (InterruptedException e) {
            Assert.fail("Exception in method CMSDisablePolicy of class Assignment Policy,",e);
        }
    }
}
