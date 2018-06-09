package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;

import java.util.ArrayList;
import java.util.List;

public class CreateCustomAssignment
{
    public void createCustomAssignment(String searchtext, String dataIndex)
    {
        try
        {
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", dataIndex);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Question Banks");//Navigate to resources
            new Click().clickByid("customAssignment");//click on create custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
            Thread.sleep(4000);
            String noOfQuestions = ReadTestData.readDataByTagName("", "noOfQuestions", dataIndex);
            List<WebElement> allCheckbox =  Driver.driver.findElements(By.className("ls-ins-question-wrapper"));       // list all the question check box
            ArrayList<String> allCheckboxId = new ArrayList<>();
            for(WebElement checkbox: allCheckbox)
            {
                String checkBox = checkbox.getAttribute("questionid");
                allCheckboxId.add(checkBox);
            }
            for(int i = 0; i < Integer.parseInt(noOfQuestions); i++)
            {
                Driver.driver.findElement(By.xpath("//label[@id='"+allCheckboxId.get(i)+"']")).click();
            }
            String selector="i[class='ls-ins-custom-image ls-ins-edit-assignment-icon']";
            String randomndescription=new RandomString().randomstring(2);
            MouseHover.mouserhoverbyid("ls-ins-assignment-name");//mouse hover on edit name
            new UIElement().waitAndFindElement(By.cssSelector(selector));
            new Click().clickbylistcssselector(selector, 0);//click on pen icon
            new TextSend().textsendbyclasslist(customassignmentname, "ls-ins-edit-assignment", 0);//edit name
            MouseHover.mouserhoverbyid("ls-ins-assignment-desc");//mouse hover on description
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

    public void createcustomassignmentatcourselevelwithmultiplaequestion(String searchtext,String dataindex)
    {
        try
        {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Question Banks");//Navigate to resources
            new Click().clickByid("customAssignment");//click on create custom assignment
            Thread.sleep(2000);
            String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", dataindex);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
            Thread.sleep(4000);
            String selector="i[class='ls-ins-custom-image ls-ins-edit-assignment-icon']";
            String randomndescption=new RandomString().randomstring(2);
            MouseHover.mouserhoverbyid("ls-ins-assignment-name");//mouse hover on edit name
            new Click().clickbylistcssselector(selector, 0);//click on pen icon
            new TextSend().textsendbyclasslist(assignmentname, "ls-ins-edit-assignment",0);//edit name
            MouseHover.mouserhoverbyid("ls-ins-assignment-desc");//mouse hover on description
            new Click().clickbylistcssselector(selector, 1);//click on pen icon
            new TextSend().textsendbyclasslist(randomndescption, "ls-ins-edit-assignment",1);//edit description
            List<WebElement>allcheckbox=Driver.driver.findElements(By.xpath("//div[@class='ls-ins-customize-checkbox-small']/label"));
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
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Question Banks");//Navigate to Question Banks
            new Click().clickByid("customAssignment");//click on create custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
            String selector="i[class='ls-ins-custom-image ls-ins-edit-assignment-icon']";
            String randomname=new RandomString().randomstring(2);
            String randomndescption=new RandomString().randomstring(2);
            MouseHover.mouserhoverbyid("ls-ins-assignment-name");//mouse hover on edit name
            new Click().clickbylistcssselector(selector, 0);//click on pen icon
            new TextSend().textsendbyclasslist(randomname, "ls-ins-edit-assignment",0);//edit name
            MouseHover.mouserhoverbyid("ls-ins-assignment-desc");//mouse hover on description
            new Click().clickbylistcssselector(selector, 1);//click on pen icon
            new TextSend().textsendbyclasslist(randomndescption, "ls-ins-edit-assignment",1);//edit description
            new Click().clickByclassname("ls-ins-view-filters");//click on filter
            Driver.driver.findElement(By.linkText("All Chapters")).click();//clikc on chapter
            Thread.sleep(2000);
            Driver.driver.findElement(By.partialLinkText("Ch 1")).click();//select chapter
            Thread.sleep(2000);
            new Click().clickByclassname("ls-ins-browse-go");//click on go button
            Driver.driver.findElement(By.xpath("//div[@class='ls-ins-customize-checkbox-small']/label")).click();
            Driver.driver.findElements(By.className("ls-ins-save-assigment-btn")).get(1).click();
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
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator().NavigateTo("Question Banks");//Navigate to Question Banks
            new Click().clickByid("customAssignment");//click on create custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
            new Click().clickbylist("ls-ins-customize-checkbox-small", 0);//checked check box
            String selector="i[class='ls-ins-custom-image ls-ins-edit-assignment-icon']";
            String randomname=new RandomString().randomstring(2);
            String randomndescption=new RandomString().randomstring(2);
            MouseHover.mouserhoverbyid("ls-ins-assignment-name");//mouse hover on edit name
            new Click().clickbylistcssselector(selector, 0);//click on pen icon
            new TextSend().textsendbyclasslist(randomname, "ls-ins-edit-assignment",0);//edit name
            MouseHover.mouserhoverbyid("ls-ins-assignment-desc");//mouse hover on description
            new Click().clickbylistcssselector(selector, 1);//click on pen icon
            new TextSend().textsendbyclasslist(randomndescption, "ls-ins-edit-assignment",1);//edit description
            new Click().clickByclassname("ls-ins-view-filters");//click on filter
            Driver.driver.findElement(By.linkText("All Chapters")).click();//clikc on chapter
            Thread.sleep(2000);
            Driver.driver.findElement(By.partialLinkText("Ch 1")).click();//select chapter
            Thread.sleep(2000);
            new Click().clickByclassname("ls-ins-browse-go");//click on go button
            Driver.driver.findElement(By.xpath("//div[@class='ls-ins-customize-checkbox-small']/label")).click();
            Driver.driver.findElements(By.className("ls-ins-save-assigment-btn")).get(1).click();//click on save button
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
