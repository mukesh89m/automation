package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TextValidate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by root on 7/3/14.
 */
public class UsersCreateLS extends Driver{

    @Test(priority=1,enabled=true)
    public void createLSStudentUsers()
    {
        try
        {

            for(int i=1;i<=100;i++) {
                new UsersCreateLS().createStudent(i, Config.courseid);
                new Assignment().submitAssignmentAsStudent(123);
                driver.quit();
            }
            //Thread.sleep(5000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in creating ls user",e);
        }

    }

    public void createStudent(int userId,String courseID)
    {
        try
        {

            driver.get(Config.baseLTIURL + "/");

            driver.findElement(By.name("endpoint")).clear(); //Clear fields
            Thread.sleep(100);
            driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);

            driver.findElement(By.name("key")).clear();
            Thread.sleep(100);
            driver.findElement(By.name("key")).sendKeys(Config.customerkey);

            driver.findElement(By.name("secret")).clear();
            Thread.sleep(100);
            driver.findElement(By.name("secret")).sendKeys(Config.secretkey);

            driver.findElement(By.name("resource_link_id")).clear();
            Thread.sleep(100);
            driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);


            driver.findElement(By.name("user_id")).clear();
            Thread.sleep(100);
            driver.findElement(By.name("user_id")).sendKeys("student"+Integer.toString(userId));

            driver.findElement(By.name("roles")).clear();
            Thread.sleep(100);
            driver.findElement(By.name("roles")).sendKeys("student");

            driver.findElement(By.name("lis_person_name_family")).clear();
            Thread.sleep(100);
            driver.findElement(By.name("lis_person_name_family")).sendKeys("family"+Integer.toString(userId));

            driver.findElement(By.name("lis_person_name_given")).clear();
            Thread.sleep(100);
            driver.findElement(By.name("lis_person_name_given")).sendKeys("givenname"+Integer.toString(userId));

            driver.findElement(By.name("lis_person_contact_email_primary")).clear();
            Thread.sleep(100);
            driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);

            driver.findElement(By.name("context_id")).clear();
            Thread.sleep(100);
            driver.findElement(By.name("context_id")).sendKeys("studcontext");

            driver.findElement(By.name("context_title")).clear();
            Thread.sleep(100);
            driver.findElement(By.name("context_title")).sendKeys("studtitle");

            driver.findElement(By.name("tool_consumer_instance_guid")).clear();
            Thread.sleep(100);
            driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);

            driver.findElement(By.name("tool_consumer_instance_name")).clear();
            Thread.sleep(100);
            driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);

            driver.findElement(By.name("custom_courseid")).clear();
            Thread.sleep(100);
            driver.findElement(By.name("custom_courseid")).sendKeys(courseID);


            driver.findElement(By.name("custom_destination")).clear();
            Thread.sleep(100);
            driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination);


            driver.findElement(By.name("custom_domainid")).clear();
            Thread.sleep(100);
            driver.findElement(By.name("custom_domainid")).sendKeys(Config.custom_domainid);

            driver.findElement(By.name("custom_course_number")).clear();
            Thread.sleep(100);
                driver.findElement(By.name("custom_course_number")).sendKeys(Config.custom_course_number);


                driver.findElement(By.name("custom_domain_name")).sendKeys(Config.custom_domain_name);


                driver.findElement(By.name("custom_instructor_classlist")).sendKeys(Config.custom_instructor_classlist);

            driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
            driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();
            Thread.sleep(3000);

                Boolean textPresent = new TextValidate().IsTextPresent("Something went wrong in processing your request.");
                if(textPresent == true)
                {
                    driver.quit();
                    Assert.fail("LTI login with course id "+Config.courseid+" Failed");
                }

        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating user in method",e);
        }
    }


}
