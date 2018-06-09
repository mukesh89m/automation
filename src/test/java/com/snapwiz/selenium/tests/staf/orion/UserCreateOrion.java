package com.snapwiz.selenium.tests.staf.orion;

import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by root on 8/6/14.
 */
public class UserCreateOrion {

    @Test(priority=1,enabled=true)
    public  void userCreateOrion()
    {
        try
        {
            for(int i=1;i<=100;i++) {
                Driver.startDriver();
                createStudent(i,"978-0470531297");
                new DiagnosticTest().startTest(0,3);
                for(int j =0;j<15;j++)
                new DiagnosticTest().attemptCorrect(3);
                new DiagnosticTest().quitTestAndGoToDashboard();
                System.out.println("User Completed"+i);
                Driver.driver.quit();
            }
        }
        catch (Exception e)
        {
            Assert.fail("",e);
        }

    }

    public void createStudent(int userId,String courseID)
    {
        try
        {

            Driver.driver.get(Config.baseLTIURL + "/");

            Driver.driver.findElement(By.name("endpoint")).clear(); //Clear fields
            Thread.sleep(100);
            Driver.driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);

            Driver.driver.findElement(By.name("key")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("key")).sendKeys(Config.customerkey);

            Driver.driver.findElement(By.name("secret")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("secret")).sendKeys(Config.secretkey);

            Driver.driver.findElement(By.name("resource_link_id")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);


            Driver.driver.findElement(By.name("user_id")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("user_id")).sendKeys("orionstudent"+Integer.toString(userId));

            Driver.driver.findElement(By.name("roles")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("roles")).sendKeys("student");

            Driver.driver.findElement(By.name("lis_person_name_family")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("lis_person_name_family")).sendKeys("orionfamily"+Integer.toString(userId));

            Driver.driver.findElement(By.name("lis_person_name_given")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("lis_person_name_given")).sendKeys("oriongivenname"+Integer.toString(userId));

            Driver.driver.findElement(By.name("lis_person_contact_email_primary")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);

            Driver.driver.findElement(By.name("context_id")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("context_id")).sendKeys("studcontext");

            Driver.driver.findElement(By.name("context_title")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("context_title")).sendKeys("studtitle");

            Driver.driver.findElement(By.name("tool_consumer_instance_guid")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);

            Driver.driver.findElement(By.name("tool_consumer_instance_name")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);

            Driver.driver.findElement(By.name("custom_courseid")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("custom_courseid")).sendKeys(courseID);


            Driver.driver.findElement(By.name("custom_destination")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination);


            Driver.driver.findElement(By.name("custom_domainid")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("custom_domainid")).sendKeys(Config.custom_domainid);

            Driver.driver.findElement(By.name("custom_course_number")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("custom_course_number")).sendKeys(Config.custom_course_number);


            Driver.driver.findElement(By.name("custom_domain_name")).sendKeys(Config.custom_domain_name);


            Driver.driver.findElement(By.name("custom_instructor_classlist")).sendKeys(Config.custom_instructor_classlist);

            Driver.driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
            Driver.driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();
            Thread.sleep(3000);
            int closewelcomepage=Driver.driver.findElements(By.cssSelector("div[class='al-dialog-welcome-close-icon']")).size();
            if(closewelcomepage>=1)
            {
                Driver.driver.findElement(By.cssSelector("div[class='al-dialog-welcome-close-icon']")).click();
                Thread.sleep(2000);
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception while creating user in method",e);
        }
    }
}
