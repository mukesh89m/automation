package com.snapwiz.selenium.tests.staf.learningspaces.testcases.assignmentflow.ls;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by root on 10/20/15.
 */
public class GradableAssignmentForOneFiftyStudent extends Driver {

    @Test(priority = 1)
    public void gradableAssignmentForOneFiftyStudent() {
        try {
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "1");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "1");
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
/*

          for(int i=1;i<=150;i++){
                ltiLogin("2",Integer.toString(i));//login as student
            }
            new LoginUsingLTI().ltiLogin("1");//login as instructor
            new Assignment().create(1);
            for(int i=1;i<=4;i++)
                new Assignment().addQuestions(1,"truefalse","");
            new LoginUsingLTI().ltiLogin("1");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//policy 1

            new LoginUsingLTI().ltiLogin("1");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(1);//assign to student


            for(int i=1;i<=50;i++){
                ltiLogin("2",Integer.toString(i));//login as student
                new Assignment().submitAssignmentAsStudent(1); //submit assignment
            }
            for(int i=63;i<=80;i++) {
                ltiLogin("2",Integer.toString(i));//login as student
                System.out.println(i);
                new Navigator().NavigateTo("Assignments"); //navigate to assignment
                new Assignment().clickonAssignment(assessmentname);
                new AttemptQuestion().trueFalse(false, "correct", 1);
                new Assignment  ().nextButtonInQuestionClick(); //click on the next button
                Thread.sleep(3000);
                new AttemptQuestion().trueFalse(false, "correct", 1);
                new Assignment().nextButtonInQuestionClick(); //click on the next button
            }
*/

            new LoginUsingLTI().ltiLogin("1");//login as instructor


        } catch (Exception e) {
            Assert.fail("Exception in TC gradableAssignmentForOneFiftyStudent of class GradableAssignmnetForOneFiftyStudent", e);
        }

    }

    @Test(priority = 2)
    public void assignDWGradableAssignment() {
        try {
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "3");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "3");
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);

            for(int i=151;i<=300;i++){
                ltiLogin("4",Integer.toString(i));//login as student
            }

            new LoginUsingLTI().ltiLogin("3"); //login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//policy 1

            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(3);


            for(int i=151;i<=200;i++){
                ltiLogin("4",Integer.toString(i));//login as student
                new Navigator().NavigateTo("Assignments");	//navigate to Assignment
                new Click().clickByclassname("learning-activity-title"); //click on DW
                String perspective = new RandomString().randomstring(2);
                new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignment
            }
            for(int i=201;i<=230;i++) {
                ltiLogin("4", Integer.toString(i));//login as student
                new Navigator().NavigateTo("Assignments");	//navigate to Assignment
                new Click().clickByclassname("learning-activity-title"); //click on DW
                new DiscussionWidget().openPerspectiveForDWAssignment(3);
            }

        } catch (Exception e) {
            Assert.fail("Exception in TC gradableAssignmentForOneFiftyStudent of class assignDWGradableAssignment", e);
        }

    }
    @Test(priority = 3)
    public void assignDWNonGradableAssignment() {
        try {
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "3");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "3");
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);

            for(int i=301;i<=450;i++){
                ltiLogin("6",Integer.toString(i));//login as student
            }

            new LoginUsingLTI().ltiLogin("5"); //login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(5);


            for(int i=301;i<=350;i++){
                ltiLogin("6",Integer.toString(i));//login as student
                new Navigator().NavigateTo("Assignments");	//navigate to Assignment
                new Click().clickByclassname("learning-activity-title"); //click on DW
                String perspective = new RandomString().randomstring(2);
                new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignment
            }
            for(int i=351;i<=380;i++) {
                ltiLogin("6",Integer.toString(i));//login as student
                new Navigator().NavigateTo("Assignments");	//navigate to Assignment
                new Click().clickByclassname("learning-activity-title"); //click on DW
                new DiscussionWidget().openPerspectiveForDWAssignment(5);
            }

        } catch (Exception e) {
            Assert.fail("Exception in TC gradableAssignmentForOneFiftyStudent of class assignDWNonGradableAssignment", e);
        }
    }

        @Test(priority = 4)
        public void assignImageWidget() {
            try {

               for(int i=451;i<=600;i++){
                    ltiLogin("8",Integer.toString(i));//login as student
                }

                new LoginUsingLTI().ltiLogin("7"); //login as instructor
                new Navigator().NavigateTo("e-Textbook");
                new TOCShow().tocHide();  //hide the TOC
                new AssignLesson().assignImageWidget("7");

               for(int i=451;i<=500;i++) {
                    ltiLogin("8", Integer.toString(i));//login as student
                    new Navigator().NavigateTo("Assignments");    //navigate to Assignment
                    new Click().clickByclassname("learning-activity-title"); //click on DW

               }

            } catch (Exception e) {
                Assert.fail("Exception in TC assignImageWidget of class assignDWNonGradableAssignment", e);
            }
    }
    public void ltiLogin(String dataIndex,String userid)
    {

        String user_id =  userid;
        String role =  ReadTestData.readDataByTagName("", "Role", dataIndex);
        String context_id = ReadTestData.readDataByTagName("", "context_id", dataIndex);
        String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
        String custom_courseid = ReadTestData.readDataByTagName("", "custom_courseid", dataIndex);
        String custom_destination = ReadTestData.readDataByTagName("", "custom_destination", dataIndex);
        String resource_link_id = ReadTestData.readDataByTagName("", "resource_link_id", dataIndex);
        String expectederror = ReadTestData.readDataByTagName("", "expectederror", dataIndex);
        String custom_course_number = ReadTestData.readDataByTagName("", "custom_course_number", dataIndex);
        String familyname =  ReadTestData.readDataByTagName("", "familyname", dataIndex);
        String givenname = ReadTestData.readDataByTagName("", "givenname", dataIndex);
        String custom_domain_name = ReadTestData.readDataByTagName("", "custom_domain_name", dataIndex);
        String custom_instructor_classlist = ReadTestData.readDataByTagName("", "custom_instructor_classlist", dataIndex);
        String course_type = ReadTestData.readDataByTagName("", "course_type", dataIndex);  // valid values - 'ls', 'adaptive'.
        String instance_guid = ReadTestData.readDataByTagName("", "instance_guid", dataIndex);  // valid values - 'ls', 'adaptive'.
        String custom_domainid = ReadTestData.readDataByTagName("", "custom_domainid", dataIndex);
        String custom_roster_changes = ReadTestData.readDataByTagName("", "custom_roster_changes", dataIndex);
        String launchURL = ReadTestData.readDataByTagName("", "launchURL", dataIndex);


        try
        {
            String appendChar="f";

            driver.get(Config.baseLTIURL + "/");

            driver.findElement(By.name("endpoint")).clear(); //Clear fields
            new UIElement().waitAndFindElement(By.name("endpoint"));

            if(launchURL == null) {
                driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);
            }
            else {
                driver.findElement(By.name("endpoint")).sendKeys(launchURL);
            }

            driver.findElement(By.name("key")).clear();
            new UIElement().waitAndFindElement(By.name("key"));
            driver.findElement(By.name("key")).sendKeys(Config.customerkey);

            driver.findElement(By.name("secret")).clear();
            new UIElement().waitAndFindElement(By.name("secret"));
            driver.findElement(By.name("secret")).sendKeys(Config.secretkey);

            driver.findElement(By.name("resource_link_id")).clear();
            if(resource_link_id == null)
                driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);
            else
                driver.findElement(By.name("resource_link_id")).sendKeys(resource_link_id);

            driver.findElement(By.name("user_id")).clear();
            new UIElement().waitAndFindElement(By.name("user_id"));
            driver.findElement(By.name("user_id")).sendKeys(user_id+appendChar);

            driver.findElement(By.name("roles")).clear();
            new UIElement().waitAndFindElement(By.name("roles"));
            driver.findElement(By.name("roles")).sendKeys(role);

            driver.findElement(By.name("lis_person_name_family")).clear();
            if(familyname == null)
                driver.findElement(By.name("lis_person_name_family")).sendKeys(Config.familyname);
            else
                driver.findElement(By.name("lis_person_name_family")).sendKeys(familyname);

            driver.findElement(By.name("lis_person_name_given")).clear();

            if(givenname == null)
                driver.findElement(By.name("lis_person_name_given")).sendKeys(Config.givenname);
            else
                driver.findElement(By.name("lis_person_name_given")).sendKeys(givenname);

            driver.findElement(By.name("lis_person_contact_email_primary")).clear();
            new UIElement().waitAndFindElement(By.name("lis_person_contact_email_primary"));
            driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);

            driver.findElement(By.name("context_id")).clear();
            if(context_id == null)
                driver.findElement(By.name("context_id")).sendKeys(Config.context_id+appendChar);
            else
                driver.findElement(By.name("context_id")).sendKeys(context_id+appendChar);

            driver.findElement(By.name("context_title")).clear();
            if(context_title == null)
                driver.findElement(By.name("context_title")).sendKeys(Config.context_title);
            else
                driver.findElement(By.name("context_title")).sendKeys(context_title);

            driver.findElement(By.name("tool_consumer_instance_guid")).clear();
            if(instance_guid == null)
                driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);
            else
                driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(instance_guid);

            driver.findElement(By.name("tool_consumer_instance_name")).clear();
            new UIElement().waitAndFindElement(By.name("tool_consumer_instance_name"));
            driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);

            driver.findElement(By.name("custom_courseid")).clear();
            if(custom_courseid == null)
            {
                if(course_type == null)
                    driver.findElement(By.name("custom_courseid")).sendKeys(Config.courseid);
                else if(course_type.equalsIgnoreCase("ls"))
                    driver.findElement(By.name("custom_courseid")).sendKeys(Config.lsCourseId);
                else if(course_type.equalsIgnoreCase("adaptive"))
                    driver.findElement(By.name("custom_courseid")).sendKeys(Config.adaptiveCourseID);
            }
            else
                driver.findElement(By.name("custom_courseid")).sendKeys(custom_courseid);

            driver.findElement(By.name("custom_roster_changes")).clear();
            if(custom_roster_changes != null){
                driver.findElement(By.name("custom_roster_changes")).sendKeys(custom_roster_changes);

            }

            driver.findElement(By.name("custom_destination")).clear();
            if(custom_destination == null)
                driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination);
            else
                driver.findElement(By.name("custom_destination")).sendKeys(custom_destination);

            if(custom_domainid == null){
                driver.findElement(By.name("custom_domainid")).clear();
                new UIElement().waitAndFindElement(By.name("custom_domainid"));
                driver.findElement(By.name("custom_domainid")).sendKeys(Config.custom_domainid+appendChar);
            }else
                driver.findElement(By.name("custom_domainid")).sendKeys(custom_domainid+appendChar);

            driver.findElement(By.name("custom_course_number")).clear();
            if(custom_course_number == null)
                driver.findElement(By.name("custom_course_number")).sendKeys(Config.custom_course_number);
            else
                driver.findElement(By.name("custom_course_number")).sendKeys(custom_course_number);

            if(custom_domain_name == null)
                driver.findElement(By.name("custom_domain_name")).sendKeys(Config.custom_domain_name+appendChar);
            else
                driver.findElement(By.name("custom_domain_name")).sendKeys(custom_domain_name+appendChar);
            if(custom_instructor_classlist == null)
                driver.findElement(By.name("custom_instructor_classlist")).sendKeys(Config.custom_instructor_classlist);
            else
                driver.findElement(By.name("custom_instructor_classlist")).sendKeys(custom_instructor_classlist);

            driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
            driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();
            try {
                String unExpected=driver.switchTo().alert().getText();
                System.out.println("unExpected Error:"+unExpected);
                driver.switchTo().alert().accept();
            } catch (Exception e) {
            }
//			new HelpDisable().helpDisable(user_id+appendChar);
            //Thread.sleep(3000);
            if(expectederror == null)
            {
                boolean textPresent = new TextValidate().IsTextPresent("Something went wrong in processing your request.");
                boolean woopsieText = new TextValidate().IsTextPresent("We encountered a problem while processing your request.");
                if(textPresent == true || woopsieText ==true)
                {
                    //driver.quit();
                    Assert.fail("LTI login with course id "+Config.courseid+" Failed");
                }

            }
            if(Config.browser.equals("ie"))
                driver.navigate().refresh();
            if(!Config.browser.equalsIgnoreCase("firefox")) {
                if(driver.findElements(By.className("swhelp-button-cancel")).size() > 0)
                    driver.findElement(By.className("swhelp-button-cancel")).click();
            /*if(driver.findElements(By.className("swhelp-button-cancel-info")).size() > 0)
                driver.findElement(By.className("swhelp-button-cancel-info")).click();*/
                //Thread.sleep(5000);
            }
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromAppHelper();
           // driver.quit();
            Assert.fail("Exception in LoginUsingLTI Application Helper",e);

        }

    }
}
