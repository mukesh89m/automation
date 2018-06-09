package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R149;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignLesson;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.CreateCustomAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PerformanceProficencyPercent;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.StaticAssignmentSubmit;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class CustomeAssignmentCourseProficency extends Driver
{
    @Test(priority=1,enabled=true)
    public void customeAssignmentCourseProficency()
    {
        try
        {
            String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", "44");
            String questiontext=ReadTestData.readDataByTagName("", "questiontext", "44");
            new Assignment().create(44);
            for(int i=1;i<=10;i++)
            {
                new Assignment().addQuestions(44, "qtn-type-true-false-img", assignmentname);
            }
            for(int i=1;i<=10;i++)
            {
                new Assignment().addQuestions(441, "qtn-type-true-false-img", assignmentname);
            }
            new LoginUsingLTI().ltiLogin("441");
            new LoginUsingLTI().ltiLogin("44");
            //44
            new CreateCustomAssignment().createcustomassignmentatcourselevelwithmultiplaequestion(questiontext,"44");
            new Navigator().myLibraryTab();
            new AssignLesson().Assigncustomeassignemnt(44);
            new LoginUsingLTI().ltiLogin("441");//login as student
            new Navigator().NavigateTo("Assignments");
            new Click().clickBycssselector("span[class='ls-assignment-name instructor-assessment-review']");
            new StaticAssignmentSubmit().staticAssignement();//complet custom assignment test
            new Navigator().NavigateTo("Proficiency Report");
            Thread.sleep(3000);
            int proficency=new PerformanceProficencyPercent().performanceProficencyPercent("441");//fetch proficency report of above user from database
            String proficencytext=driver.findElement(By.id("al-cource-proficiency-summary")).getText();//fetch proficency report of user from pro
            int pageproficency=Integer.parseInt(proficencytext.substring(0, 2));
            if(proficency!=pageproficency)
                Assert.fail("database proficency not equal to diplayed proficency on proficency report page forcustom assignment");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase CustomeAssignmentCourseProficency in testmethod customeAssignmentCourseProficency",e);
        }
    }

    @Test(priority=2,enabled=true,dependsOnMethods = {"customeAssignmentCourseProficency"})
    public void GradblecustomeAssignmentCourseProficency()
    {
        try
        {
            String questiontext=ReadTestData.readDataByTagName("", "questiontext", "45");
            new LoginUsingLTI().ltiLogin("451"); //create student
            new LoginUsingLTI().ltiLogin("45");
            new CreateCustomAssignment().createcustomassignmentatcourselevelwithmultiplaequestion(questiontext,"45");
            new Navigator().myLibraryTab();
            new AssignLesson().Assigncustomeassignemnt(45);
            new LoginUsingLTI().ltiLogin("451");//login as student
            new Navigator().NavigateTo("Assignments");
            new Click().clickBycssselector("span[class='ls-assignment-name instructor-assessment-review']");
            new StaticAssignmentSubmit().staticAssignement();//complet custom assignment test
            new LoginUsingLTI().ltiLogin("451");//login as student
            new Navigator().NavigateTo("Proficiency Report");
            Thread.sleep(3000);
            //45,46,47
            boolean noDataNotification=new BooleanValue().booleanbyclass("no-data-notification-message-block");
            if(noDataNotification==false)
                Assert.fail("no data notification not shown even grad not release by instructor");
            new LoginUsingLTI().ltiLogin("45");
            new Assignment().provideGRadeToStudent(45);
            new Assignment().releaseGrades(45,"Release Grade for All");
            new LoginUsingLTI().ltiLogin("451");//login as  student
            new Navigator().NavigateTo("Proficiency Report");
            Thread.sleep(3000);
            int proficency=new PerformanceProficencyPercent().performanceProficencyPercent("451");//fetch proficency report of above user from database
            String proficencytext=driver.findElement(By.id("al-cource-proficiency-summary")).getText();//fetch proficency report of user from pro
            int pageproficency=Integer.parseInt(proficencytext.substring(0, 2));//
            if(proficency!=pageproficency)
                Assert.fail("database proficency not equal to diplayed proficency on proficency report page for diagonstic test");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase CustomeAssignmentCourseProficency in testmethod GradblecustomeAssignmentCourseProficency",e);
        }
    }

}
