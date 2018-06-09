package com.snapwiz.selenium.tests.staf.orion.testcases;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by root on 7/24/14.
 */
public class HistoricalData {

    @Test
    public void historicalData()
    {
        try
        {
            for(int i=1;i<=25;i++) {
                Driver.startDriver();
                createStudent(i, "978-0470531297");
                Runtime.getRuntime().exec("./set_date" + i + ".sh");
                new DiagnosticTest().startTest(0, 3);
                new DiagnosticTest().attemptAllCorrect(3, false, false);
                new Navigator().orionDashboard(); //Go to Orion Dashboard
                Thread.sleep(5000);
                new PracticeTest().startTest();
                new PracticeTest().attemptQuestion("correct",2,false,false);
                new PracticeTest().attemptQuestion("correct",2,false,false);
                new PracticeTest().quitTestAndGoToDashboard();
                new Navigator().navigateFromProfileDropDownForOrion("My Reports");
                new Navigator().NavigateToReport("Performance Report");
                Driver.driver.findElement(By.className("report-sidebar-question-card-right")).click();
                Thread.sleep(1000);
                Driver.driver.findElement(By.className("al-question-notes-discussion-text")).click();
                Thread.sleep(2000);
                Driver.driver.findElement(By.className("al-question-discussion-input-section")).sendKeys("Test Comment from user "+i+Keys.ENTER);

                Thread.sleep(2000);
                Driver.driver.quit();
                Thread.sleep(2000);
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception",e);

        }
        finally
        {
           // Driver.driver.quit();
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
            Driver.driver.findElement(By.name("user_id")).sendKeys("student"+Integer.toString(userId));

            Driver.driver.findElement(By.name("roles")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("roles")).sendKeys("student");

            Driver.driver.findElement(By.name("lis_person_name_family")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("lis_person_name_family")).sendKeys("family"+Integer.toString(userId));

            Driver.driver.findElement(By.name("lis_person_name_given")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("lis_person_name_given")).sendKeys("givenname"+Integer.toString(userId));

            Driver.driver.findElement(By.name("lis_person_contact_email_primary")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);

            Driver.driver.findElement(By.name("context_id")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("context_id")).sendKeys("studcontext1");

            Driver.driver.findElement(By.name("context_title")).clear();
            Thread.sleep(100);
            Driver.driver.findElement(By.name("context_title")).sendKeys("studtitle1");

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

            Thread.sleep(2000);
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
