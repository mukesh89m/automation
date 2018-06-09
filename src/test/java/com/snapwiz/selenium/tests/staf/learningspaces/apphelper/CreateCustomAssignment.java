package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CreateCustomAssignment extends Driver
{
    public void createCustomAssignment(String searchtext, String dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", dataIndex);
            new Navigator().NavigateTo("Question Banks");//Navigate to resources
            new Click().clickByid("customAssignment");//click on create custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
            Thread.sleep(4000);
            String noOfQuestions = ReadTestData.readDataByTagName("", "noOfQuestions", dataIndex);
            List<WebElement> allCheckbox =  driver.findElements(By.className("ls-ins-question-wrapper"));       // list all the question check box
            ArrayList<String> allCheckboxId = new ArrayList<>();
            for(WebElement checkbox: allCheckbox)
            {
                String checkBox = checkbox.getAttribute("questionid");
                allCheckboxId.add(checkBox);
            }
            for(int i = 0; i < Integer.parseInt(noOfQuestions); i++)
            {
                driver.findElement(By.xpath("//label[@id='"+allCheckboxId.get(i)+"']")).click();
            }
            String selector="i[class='ls-ins-custom-image ls-ins-edit-assignment-icon']";
            String randomndescription=new RandomString().randomstring(2);
            new MouseHover().mouserhoverbyid("ls-ins-assignment-name");//mouse hover on edit name
            new UIElement().waitAndFindElement(By.cssSelector(selector));
            new Click().clickbylistcssselector(selector, 0);//click on pen icon
            new TextSend().textsendbyclasslist(customassignmentname, "ls-ins-edit-assignment", 0);//edit name
            new MouseHover().mouserhoverbyid("ls-ins-assignment-desc");//mouse hover on description
            new Click().clickbylistcssselector(selector, 1);//click on pen icon
            new TextSend().textsendbyclasslist(randomndescription, "ls-ins-edit-assignment", 1);//edit description

            new Click().clickByid("ls-ins-save-assigment-btn");//click on save button
            Thread.sleep(2000);

        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper createcustomassignmentatcourselevel ",e);
        }
    }


    public void assignByCreatingCustomAssignment(String searchtext, String dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", dataIndex);
            String gradable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);
            String gradingpolicy = ReadTestData.readDataByTagName("", "gradingpolicy", dataIndex);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String gradeBookWeighting = ReadTestData.readDataByTagName("", "gradeBookWeighting", dataIndex);
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", dataIndex);
            String policyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            String policyDescription = ReadTestData.readDataByTagName("", "policyDescription", dataIndex);
            String scorePerQuestion = ReadTestData.readDataByTagName("", "scorePerQuestion", dataIndex);
            String ordering = ReadTestData.readDataByTagName("", "ordering", dataIndex);
            String immediateFeedBack = ReadTestData.readDataByTagName("", "immediateFeedBack", dataIndex);
            String numberOfAttempt = ReadTestData.readDataByTagName("", "numberOfAttempt", dataIndex);
            String showAnswerAtAttemptNumber = ReadTestData.readDataByTagName("", "showAnswerAtAttemptNumber", dataIndex);
            String gradeReleaseOption = ReadTestData.readDataByTagName("", "gradeReleaseOption", dataIndex);
            String showHintsAtAttemptNumber = ReadTestData.readDataByTagName("", "showHintsAtAttemptNumber", dataIndex);
            String showReadingContentLinkAtAttemptNumber = ReadTestData.readDataByTagName("", "showReadingContentLinkAtAttemptNumber", dataIndex);
            String showSolutionAtAttemptNumber = ReadTestData.readDataByTagName("", "showSolutionAtAttemptNumber", dataIndex);


            System.out.println("policyName:"+policyName);
            if(policyName==null){
                System.out.println("Inside Policy Name");
                policyName = "ByDefault Policy Name";
            }

            if(policyDescription==null){
                policyDescription = "Policy Description text";
            }
            if(scorePerQuestion==null){
                scorePerQuestion = "1";
            }

            if(immediateFeedBack==null){
                immediateFeedBack = "false";
            }


            if(numberOfAttempt==null){
                numberOfAttempt = "1";
            }
            if(showAnswerAtAttemptNumber==null){
                showAnswerAtAttemptNumber = "";
            }
            if(gradeReleaseOption==null){
                gradeReleaseOption = "Release explicitly by the instructor";
            }
            if(showHintsAtAttemptNumber==null){
                showHintsAtAttemptNumber = "";
            }
            if(showReadingContentLinkAtAttemptNumber==null){
                showReadingContentLinkAtAttemptNumber = "";
            }
            if(showSolutionAtAttemptNumber==null){
                showSolutionAtAttemptNumber = "";
            }


            new Navigator().NavigateTo("Question Banks");//Navigate to resources
            new Click().clickByid("customAssignment");//click on create custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
            Thread.sleep(4000);
            String noOfQuestions = ReadTestData.readDataByTagName("", "noOfQuestions", dataIndex);
            List<WebElement> allCheckbox =  driver.findElements(By.className("ls-ins-question-wrapper"));       // list all the question check box
            ArrayList<String> allCheckboxId = new ArrayList<>();
            for(WebElement checkbox: allCheckbox)
            {
                String checkBox = checkbox.getAttribute("questionid");
                allCheckboxId.add(checkBox);
            }
            for(int i = 0; i < Integer.parseInt(noOfQuestions); i++)
            {
                driver.findElement(By.xpath("//label[@id='"+allCheckboxId.get(i)+"']")).click();
            }
            String selector="i[class='ls-ins-custom-image ls-ins-edit-assignment-icon']";
            String randomndescription=new RandomString().randomstring(2);
            new MouseHover().mouserhoverbyid("ls-ins-assignment-name");//mouse hover on edit name
            new UIElement().waitAndFindElement(By.cssSelector(selector));
            new Click().clickbylistcssselector(selector, 0);//click on pen icon
            new TextSend().textsendbyclasslist(customassignmentname, "ls-ins-edit-assignment", 0);//edit name
            new MouseHover().mouserhoverbyid("ls-ins-assignment-desc");//mouse hover on description
            new Click().clickbylistcssselector(selector, 1);//click on pen icon
            new TextSend().textsendbyclasslist(randomndescription, "ls-ins-edit-assignment", 1);//edit description


            new Click().clickByid("ls-assign-now-assigment-btn");

            new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, true);

            new UIElement().waitAndFindElement(By.id("due-time"));

            driver.findElement(By.id("due-time")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[text()='07:30 PM']")));

            driver.findElement(By.id("due-date")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            WebElement we=driver.findElement(By.linkText(duedate));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);


            if (gradeable.equals("false")) {
                new UIElement().waitAndFindElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check selected']")));
            }

            if (gradable.equals("true") && assignmentpolicy != null) {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
                driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            }
            if(gradeable.equals("true")) {
                if(policyName.equals("ByDefault Policy Name")) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
                    new Click().clickbyxpath("//a[@rel='addNewAssignmentPolicy']");
                    new AssignmentPolicy().createAssignmentPolicyWhileAssigning(policyName, policyDescription, scorePerQuestion, ordering, Boolean.parseBoolean(immediateFeedBack), numberOfAttempt, showAnswerAtAttemptNumber, gradeReleaseOption, showHintsAtAttemptNumber, showReadingContentLinkAtAttemptNumber, showSolutionAtAttemptNumber);
                }

            }

            if (accessibleafter != null) {
                driver.findElement(By.id("accessible-date")).click();
                driver.findElement(By.xpath("//a[@title='Next']")).click();
                driver.findElement(By.linkText(accessibleafter)).click();
            }

            new UIElement().waitAndFindElement(By.id("additional-notes"));
            driver.findElement(By.id("additional-notes")).clear();
            driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);

            if(gradeBookWeighting!=null){
                if(gradeBookWeighting.equals("true")){
                    new Click().clickbylinkText("Uncategorized");
                    new UIElement().waitAndFindElement(By.linkText("No Assignment Category"));
                    new Click().clickbylinkText("Practice");
                }
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign assign-custom-assignment']")));
            new UIElement().waitTillInvisibleElement(By.cssSelector("span[class='btn sty-green submit-assign']"));
            ReportUtil.log("Assigning by creating Custom Assignment", "Custom Assignment is Assigned","info");


        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper createcustomassignmentatcourselevel ",e);
        }
    }


    public void assignByCreatingDiscussionAssignment(String dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String gradable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", dataIndex);
            String shareName = ReadTestData.readDataByTagName("", "shareName", dataIndex);
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", dataIndex);
            String duedate = ReadTestData.readDataByTagName("", "duedate", dataIndex);
            String duetime = ReadTestData.readDataByTagName("", "duetime", dataIndex);
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", dataIndex);
            String additionalnote = ReadTestData.readDataByTagName("", "additionalnote", dataIndex);
            String gradeable = ReadTestData.readDataByTagName("", "gradeable", dataIndex);
            String gradeBookWeighting = ReadTestData.readDataByTagName("", "gradeBookWeighting", dataIndex);
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", dataIndex);
            String policyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", dataIndex);
            String totalPoints = ReadTestData.readDataByTagName("", "totalPoints", dataIndex);

            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String discussionAssignment = ReadTestData.readDataByTagName("", "discussionAssignment", dataIndex);
            String description = ReadTestData.readDataByTagName("", "description", dataIndex);

            if(policyName==null){
                policyName = "ByDefault Policy Name";
            }

            new Navigator().NavigateTo("New Assignment");//Navigate to new assignment
            WebDriverUtil.clickOnElementUsingJavascript(newAssignment.discussionAssignment);//click on discussion assignment
            newAssignment.assessmentNameTextBox.click();//click on Name field
            newAssignment.assessmentName_TextBox.sendKeys(discussionAssignment);//enter name of assignment
            driver.findElement(By.cssSelector("body")).click();//click out side
            WebDriverUtil.clickOnElementUsingJavascript(newAssignment.questionContent);//click on question for discussion
            Thread.sleep(3000);
            driver.switchTo().activeElement().sendKeys(description);
            newAssignment.assignNowButton.click();
            new ShareWith().share(shareWithInitialString, shareName, shareWithClass, context_title, true);
            new UIElement().waitAndFindElement(By.id("due-time"));
            new TextSend().textsendbyid(totalPoints,"total-points");
            driver.findElement(By.id("due-time")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[text()='07:30 PM']")));
            driver.findElement(By.id("due-date")).click();
            new UIElement().waitAndFindElement(By.linkText(duedate));
            driver.findElement(By.cssSelector("a[title='Next']")).click();
            Thread.sleep(2000);
            WebElement we=driver.findElement(By.linkText(duedate));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);


            if (gradeable.equals("false")) {
                new UIElement().waitAndFindElement(By.cssSelector("div[class='ir-ls-assign-dialog-dw-gradable-label-check selected']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-dw-gradable-label-check selected']")));
            }

            if (gradable.equals("true") && assignmentpolicy != null) {

                //click on  Choose your assignment policy dropdown
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select your Assignment Reference']")));
                new UIElement().waitAndFindElement(By.linkText(assignmentpolicy));
                driver.findElement(By.linkText(assignmentpolicy)).click();//select a policy
            }
            if(gradeable.equals("true")) {
                if(policyName.equals("ByDefault Policy Name")){
                    /*((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select your Assignment Reference']")));
                    new Click().clickbyxpath("//a[@rel='createNewAssignmentReference']");
                    new AssignmentPolicy().createAssignmentReference(dataIndex);*/
                }

            }
            if (accessibleafter != null) {
                driver.findElement(By.id("accessible-date")).click();
                driver.findElement(By.xpath("//a[@title='Next']")).click();
                driver.findElement(By.linkText(accessibleafter)).click();
            }

            new UIElement().waitAndFindElement(By.id("additional-notes"));
            driver.findElement(By.id("additional-notes")).clear();
            driver.findElement(By.id("additional-notes")).sendKeys(additionalnote);

            if(gradeBookWeighting!=null){
                if(gradeBookWeighting.equals("true")){
                    new Click().clickbylinkText("Uncategorized");
                    new UIElement().waitAndFindElement(By.linkText("No Assignment Category"));
                    new Click().clickbylinkText("Practice");
                }
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign assign-custom-assignment']")));
            new UIElement().waitTillInvisibleElement(By.cssSelector("span[class='btn sty-green submit-assign']"));
            ReportUtil.log("Assigning by creating Discussion Assignment", "Discussion Assignment is Assigned","info");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper assignByCreatingDiscussionAssignment ",e);
        }
    }

    public void createcustomassignmentatcourselevelwithmultiplaequestion(String searchtext,String dataindex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            new Navigator().NavigateTo("Question Banks");//Navigate to resources
            new Click().clickByid("customAssignment");//click on create custom assignment
            Thread.sleep(2000);
            String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", dataindex);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
            Thread.sleep(4000);
            String selector="i[class='ls-ins-custom-image ls-ins-edit-assignment-icon']";
            String randomndescption=new RandomString().randomstring(2);
            new MouseHover().mouserhoverbyid("ls-ins-assignment-name");//mouse hover on edit name
            new Click().clickbylistcssselector(selector, 0);//click on pen icon
            new TextSend().textsendbyclasslist(assignmentname, "ls-ins-edit-assignment",0);//edit name
            new MouseHover().mouserhoverbyid("ls-ins-assignment-desc");//mouse hover on description
            new Click().clickbylistcssselector(selector, 1);//click on pen icon
            new TextSend().textsendbyclasslist(randomndescption, "ls-ins-edit-assignment",1);//edit description
            List<WebElement>allcheckbox=driver.findElements(By.xpath("//div[@class='ls-ins-customize-checkbox-small']/label"));
            for(int i=3;i<18;i++)
            {
                allcheckbox.get(i).click();//select multiple question
            }

            new Click().clickByid("ls-ins-save-assigment-btn");//click on save button
            Thread.sleep(2000);
            String notificationtext=new TextFetch().textfetchbyid("top-notification-Bar");
            System.out.println(notificationtext);
            if(!notificationtext.contains("Saved Custom Assessment Successfully."))
                Assert.fail("custom assignment not created");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper createcustomassignmentatcourselevelwithmultiplaequestion ",e);
        }
    }
    public void createcustomassignmentatchapterlevel(String searchtext)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            new Navigator().NavigateTo("Question Banks");//Navigate to Question Banks
            new Click().clickByid("customAssignment");//click on create custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
            String selector="i[class='ls-ins-custom-image ls-ins-edit-assignment-icon']";
            String randomname=new RandomString().randomstring(2);
            String randomndescption=new RandomString().randomstring(2);
            new MouseHover().mouserhoverbyid("ls-ins-assignment-name");//mouse hover on edit name
            new Click().clickbylistcssselector(selector, 0);//click on pen icon
            new TextSend().textsendbyclasslist(randomname, "ls-ins-edit-assignment",0);//edit name
            new MouseHover().mouserhoverbyid("ls-ins-assignment-desc");//mouse hover on description
            new Click().clickbylistcssselector(selector, 1);//click on pen icon
            new TextSend().textsendbyclasslist(randomndescption, "ls-ins-edit-assignment",1);//edit description
            new Click().clickByclassname("ls-ins-view-filters");//click on filter
            driver.findElement(By.linkText("All Chapters")).click();//clikc on chapter
            Thread.sleep(2000);
            driver.findElement(By.partialLinkText("Ch 1")).click();//select chapter
            Thread.sleep(2000);
            new Click().clickByclassname("ls-ins-browse-go");//click on go button
            driver.findElement(By.xpath("//div[@class='ls-ins-customize-checkbox-small']/label")).click();
            driver.findElements(By.className("ls-ins-save-assigment-btn")).get(1).click();
            Thread.sleep(2000);
            String notificationtext=new TextFetch().textfetchbyid("top-notification-Bar");
            if(!notificationtext.contains("Saved Custom Assessment Successfully."))
                Assert.fail("custom assignment not created");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper createcustomassignmentatchapterlevel ",e);
        }
    }
    public void createcustomassignmentattopiclevel(String searchtext)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            new Navigator().NavigateTo("Question Banks");//Navigate to Question Banks
            new Click().clickByid("customAssignment");//click on create custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
            new Click().clickbylist("ls-ins-customize-checkbox-small", 0);//checked check box
            String selector="i[class='ls-ins-custom-image ls-ins-edit-assignment-icon']";
            String randomname=new RandomString().randomstring(2);
            String randomndescption=new RandomString().randomstring(2);
            new MouseHover().mouserhoverbyid("ls-ins-assignment-name");//mouse hover on edit name
            new Click().clickbylistcssselector(selector, 0);//click on pen icon
            new TextSend().textsendbyclasslist(randomname, "ls-ins-edit-assignment",0);//edit name
            new MouseHover().mouserhoverbyid("ls-ins-assignment-desc");//mouse hover on description
            new Click().clickbylistcssselector(selector, 1);//click on pen icon
            new TextSend().textsendbyclasslist(randomndescption, "ls-ins-edit-assignment",1);//edit description
            new Click().clickByclassname("ls-ins-view-filters");//click on filter
            driver.findElement(By.linkText("All Chapters")).click();//clikc on chapter
            Thread.sleep(2000);
            driver.findElement(By.partialLinkText("Ch 1")).click();//select chapter
            Thread.sleep(2000);
            new Click().clickByclassname("ls-ins-browse-go");//click on go button
            driver.findElement(By.xpath("//div[@class='ls-ins-customize-checkbox-small']/label")).click();
            driver.findElements(By.className("ls-ins-save-assigment-btn")).get(1).click();//click on save button
            Thread.sleep(2000);
            String notificationtext=new TextFetch().textfetchbyid("top-notification-Bar");
            if(!notificationtext.contains("Saved Custom Assessment Successfully."))
                Assert.fail("custom assignment not created");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper createcustomassignmentattopiclevel ",e);
        }
    }
}
