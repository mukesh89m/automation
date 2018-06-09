package com.snapwiz.selenium.tests.staf.datacreation.apphelper;

import com.snapwiz.selenium.tests.staf.datacreation.Config;
import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by root on 27/10/14.
 */
public class WileyLogin extends Driver {

    public void login()
    {
        try {
            /*driver.get(Config.baseLTIURL + "/");
            driver.findElement(By.name("endpoint")).clear(); //Clear fields
            driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);

            driver.findElement(By.name("key")).clear();
            driver.findElement(By.name("key")).sendKeys(Config.customerkey);

            driver.findElement(By.name("secret")).clear();
            driver.findElement(By.name("secret")).sendKeys(Config.secretkey);

            driver.findElement(By.name("resource_link_id")).clear();


                driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);



            driver.findElement(By.name("user_id")).clear();
            driver.findElement(By.name("user_id")).sendKeys(userid);

            driver.findElement(By.name("roles")).clear();
            driver.findElement(By.name("roles")).sendKeys(role);

            driver.findElement(By.name("lis_person_name_family")).clear();
            driver.findElement(By.name("lis_person_name_family")).sendKeys(lastname);

            driver.findElement(By.name("lis_person_name_given")).clear();
            driver.findElement(By.name("lis_person_name_given")).sendKeys(firstname);

            driver.findElement(By.name("lis_person_contact_email_primary")).clear();
            driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);

            driver.findElement(By.name("context_id")).clear();
            driver.findElement(By.name("context_id")).sendKeys(contextid); //context id

            driver.findElement(By.name("context_title")).clear();
            driver.findElement(By.name("context_title")).sendKeys(contexttitle); //context title

            driver.findElement(By.name("tool_consumer_instance_guid")).clear();
            driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);

            driver.findElement(By.name("tool_consumer_instance_name")).clear();
            driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);

            driver.findElement(By.name("custom_courseid")).clear();
            driver.findElement(By.name("custom_courseid")).sendKeys(courseid);

            driver.findElement(By.name("custom_domain_name")).sendKeys(domainname); //domain name

            driver.findElement(By.name("custom_destination")).clear();

            if(custom_destination == null)
                driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination);
            else
                driver.findElement(By.name("custom_destination")).sendKeys(custom_destination);

            driver.findElement(By.name("custom_domainid")).clear();
            driver.findElement(By.name("custom_domainid")).sendKeys(domainid); //domain id


            driver.findElement(By.name("custom_course_number")).clear();
            if(custom_course_number == null)
                driver.findElement(By.name("custom_course_number")).sendKeys(Config.custom_course_number);
            else
                driver.findElement(By.name("custom_course_number")).sendKeys(custom_course_number);

            if(custom_instructor_classlist == null)
                driver.findElement(By.name("custom_instructor_classlist")).sendKeys(Config.custom_instructor_classlist);
            else
                driver.findElement(By.name("custom_instructor_classlist")).sendKeys(custom_instructor_classlist);

            driver.findElement(By.name("custom_lis_result_source_id")).clear();
            driver.findElement(By.name("custom_lis_result_source_id")).sendKeys(custom_lis_result_source_id);

            driver.findElement(By.name("lis_person_sourced_id")).clear();
            driver.findElement(By.name("lis_person_sourced_id")).sendKeys(lis_person_sourced_id);

            driver.findElement(By.name("lis_outcome_service_url")).clear();
            driver.findElement(By.name("lis_outcome_service_url")).sendKeys(Config.lis_outcome_service_url);

            driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
            driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();*/

        }
        catch (Exception e)
        {
            Assert.fail();
        }
    }
}
