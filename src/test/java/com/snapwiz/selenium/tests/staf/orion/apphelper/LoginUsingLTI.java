package com.snapwiz.selenium.tests.staf.orion.apphelper;

import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;



public class LoginUsingLTI extends Driver{
    public static String appendChar;
    public WebDriver driver=Driver.getWebDriver();
    public LoginUsingLTI(){
        Config.readconfiguration();
    }
    /* Method for LTI login for learning spaces. This method enters LTI login details like end point and keys. The login will fail is the text verified
       * is not present on the loaded page after clicking the launch button. */
    public void ltiLogin(String dataIndex)
    {
        //String baseUrlLTI = "http://lti.snapwiz.net";
        appendChar = System.getProperty("UCHAR");

        String user_id =  ReadTestData.readDataByTagName("", "user_id", dataIndex);
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
        String custom_domainid=ReadTestData.readDataByTagName("", "custom_domainid", dataIndex);
        String custom_domain_name = ReadTestData.readDataByTagName("", "custom_domain_name", dataIndex);
        String custom_instructor_classlist = ReadTestData.readDataByTagName("", "custom_instructor_classlist", dataIndex);
        String custom_lis_result_source_id = ReadTestData.readDataByTagName("", "custom_lis_result_source_id", dataIndex);
        String lis_person_sourced_id = ReadTestData.readDataByTagName("", "lis_person_sourced_id", dataIndex);
        String custom_class_state = ReadTestData.readDataByTagName("", "custom_class_state", dataIndex);
        String lis_outcome_service_url = ReadTestData.readDataByTagName("", "lis_outcome_service_url", dataIndex);


        try
        {

            if(appendChar ==null){
                appendChar = "dd5";
            }


            driver.get(Config.baseLTIURL + "/");
            driver.findElement(By.name("endpoint")).clear(); //Clear fields
            driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);

            driver.findElement(By.name("key")).clear();
            driver.findElement(By.name("key")).sendKeys(Config.customerkey);

            driver.findElement(By.name("secret")).clear();
            driver.findElement(By.name("secret")).sendKeys(Config.secretkey);

            driver.findElement(By.name("resource_link_id")).clear();

            if(resource_link_id == null)
                driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);
            else
                driver.findElement(By.name("resource_link_id")).sendKeys(resource_link_id);

            driver.findElement(By.name("user_id")).clear();
            driver.findElement(By.name("user_id")).sendKeys(user_id+appendChar);

            driver.findElement(By.name("roles")).clear();
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
            driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);

            driver.findElement(By.name("context_id")).clear();

            if(context_id == null) {
                driver.findElement(By.name("context_id")).sendKeys(Config.context_id);
            }
            else {
                driver.findElement(By.name("context_id")).sendKeys(context_id+appendChar);
            }

            driver.findElement(By.name("context_title")).clear();

            if(context_title == null)
                driver.findElement(By.name("context_title")).sendKeys(Config.context_title);
            else
                driver.findElement(By.name("context_title")).sendKeys(context_title);

            driver.findElement(By.name("tool_consumer_instance_guid")).clear();
            driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);

            driver.findElement(By.name("tool_consumer_instance_name")).clear();
            driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);

            driver.findElement(By.name("custom_courseid")).clear();

            if(custom_courseid == null)
                driver.findElement(By.name("custom_courseid")).sendKeys(Config.courseid);
            else
                driver.findElement(By.name("custom_courseid")).sendKeys(custom_courseid);

            if(custom_domain_name == null)
                driver.findElement(By.name("custom_domain_name")).sendKeys(Config.custom_domain_name);
            else {
                if(!custom_domain_name.equals("null")) {
                    driver.findElement(By.name("custom_domain_name")).sendKeys(custom_domain_name);
                }
            }

            driver.findElement(By.name("custom_destination")).clear();

            if(custom_destination == null)
                driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination);
            else
                driver.findElement(By.name("custom_destination")).sendKeys(custom_destination);

            driver.findElement(By.name("custom_domainid")).clear();
            if(custom_domainid==null)
                driver.findElement(By.name("custom_domainid")).sendKeys(Config.custom_domainid);
            else
                driver.findElement(By.name("custom_domainid")).sendKeys(custom_domainid);

            driver.findElement(By.name("custom_course_number")).clear();
            if(custom_course_number == null)
                driver.findElement(By.name("custom_course_number")).sendKeys(Config.custom_course_number);
            else {
                if(!custom_course_number.equals("null")){
                    driver.findElement(By.name("custom_course_number")).sendKeys(custom_course_number);
                }
            }

            if(custom_instructor_classlist == null)
                driver.findElement(By.name("custom_instructor_classlist")).sendKeys(Config.custom_instructor_classlist);
            else {
                if(custom_instructor_classlist.equals("null")){
                    driver.findElement(By.name("custom_instructor_classlist")).sendKeys("[]");
                }else{
                    driver.findElement(By.name("custom_instructor_classlist")).sendKeys(custom_instructor_classlist);
                }
            }

            if(custom_class_state != null) {

                driver.findElement(By.name("custom_class_state")).clear();
                driver.findElement(By.name("custom_class_state")).sendKeys(custom_class_state);
            }

            driver.findElement(By.name("custom_lis_result_source_id")).clear();
            driver.findElement(By.name("custom_lis_result_source_id")).sendKeys(custom_lis_result_source_id);

            driver.findElement(By.name("lis_person_sourced_id")).clear();
            driver.findElement(By.name("lis_person_sourced_id")).sendKeys(lis_person_sourced_id);

            driver.findElement(By.name("lis_outcome_service_url")).clear();


            if(lis_outcome_service_url ==null){
                driver.findElement(By.name("lis_outcome_service_url")).sendKeys(Config.lis_outcome_service_url);
            }else{
                if(!lis_outcome_service_url.equals("null")){
                    driver.findElement(By.name("lis_outcome_service_url")).sendKeys(Config.lis_outcome_service_url);
                }
            }


            driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
            driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();
            Thread.sleep(2000);
            int closewelcomepage=driver.findElements(By.cssSelector("div[class='al-dialog-welcome-close-icon']")).size();
            if(closewelcomepage>=1)
            {
                driver.findElement(By.cssSelector("div[class='al-dialog-welcome-close-icon']")).click();
                Thread.sleep(2000);
            }
            WebDriverUtil.waitForAjax(driver,60);
            if(expectederror == null)
            {
                int backtowilyplus1=driver.findElements(By.cssSelector("[title='ORION Dashboard']")).size();
                if(backtowilyplus1==0)
                {
                    new Screenshot().captureScreenshotFromAppHelper();
                    Assert.fail("Login to Orion Failed");
                }
            }
            else
            {
                Boolean textPresent = new TextValidate().IsTextPresent(expectederror);
                if(textPresent == false)
                {
                    new Screenshot().captureScreenshotFromAppHelper();
                    driver.quit();
                    Assert.fail("Expected error message not shown while logging in Orion");
                }
            }
            if(Config.browser.equals("ie"))
                driver.navigate().refresh();
            ReportUtil.log("LTI Login", ""+role+" logged in ", "info");
        }
        catch(Exception e)
        {
            //driver.quit();
            Assert.fail("Exception in LoginUsingLTI Application Helper",e);

        }

    }


    public void ltiLogin(String dataIndex,WebDriver driver )
    {
        //String baseUrlLTI = "http://lti.snapwiz.net";
        appendChar = System.getProperty("UCHAR");
        String user_id =  ReadTestData.readDataByTagName("", "user_id", dataIndex);
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
        String custom_domainid=ReadTestData.readDataByTagName("", "custom_domainid", dataIndex);
        String custom_domain_name = ReadTestData.readDataByTagName("", "custom_domain_name", dataIndex);
        String custom_instructor_classlist = ReadTestData.readDataByTagName("", "custom_instructor_classlist", dataIndex);
        String custom_lis_result_source_id = ReadTestData.readDataByTagName("", "custom_lis_result_source_id", dataIndex);
        String lis_person_sourced_id = ReadTestData.readDataByTagName("", "lis_person_sourced_id", dataIndex);
        String custom_class_state = ReadTestData.readDataByTagName("", "custom_class_state", dataIndex);
        String lis_outcome_service_url = ReadTestData.readDataByTagName("", "lis_outcome_service_url", dataIndex);


        try
        {

            if(appendChar ==null){
                appendChar = "dasd";
            }



            driver.get(Config.baseLTIURL + "/");
            driver.findElement(By.name("endpoint")).clear(); //Clear fields
            driver.findElement(By.name("endpoint")).sendKeys(Config.launchURL);

            driver.findElement(By.name("key")).clear();
            driver.findElement(By.name("key")).sendKeys(Config.customerkey);

            driver.findElement(By.name("secret")).clear();
            driver.findElement(By.name("secret")).sendKeys(Config.secretkey);

            driver.findElement(By.name("resource_link_id")).clear();

            if(resource_link_id == null)
                driver.findElement(By.name("resource_link_id")).sendKeys(Config.resourselinkid);
            else
                driver.findElement(By.name("resource_link_id")).sendKeys(resource_link_id);

            driver.findElement(By.name("user_id")).clear();
            driver.findElement(By.name("user_id")).sendKeys(user_id+appendChar);

            driver.findElement(By.name("roles")).clear();
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
            driver.findElement(By.name("lis_person_contact_email_primary")).sendKeys(Config.email);

            driver.findElement(By.name("context_id")).clear();

            if(context_id == null) {
                driver.findElement(By.name("context_id")).sendKeys(Config.context_id);
            }
            else {
                driver.findElement(By.name("context_id")).sendKeys(context_id);
            }

            driver.findElement(By.name("context_title")).clear();

            if(context_title == null)
                driver.findElement(By.name("context_title")).sendKeys(Config.context_title);
            else
                driver.findElement(By.name("context_title")).sendKeys(context_title);

            driver.findElement(By.name("tool_consumer_instance_guid")).clear();
            driver.findElement(By.name("tool_consumer_instance_guid")).sendKeys(Config.instance_guid);

            driver.findElement(By.name("tool_consumer_instance_name")).clear();
            driver.findElement(By.name("tool_consumer_instance_name")).sendKeys(Config.instance_name);

            driver.findElement(By.name("custom_courseid")).clear();

            if(custom_courseid == null)
                driver.findElement(By.name("custom_courseid")).sendKeys(Config.courseid);
            else
                driver.findElement(By.name("custom_courseid")).sendKeys(custom_courseid);

            if(custom_domain_name == null)
                driver.findElement(By.name("custom_domain_name")).sendKeys(Config.custom_domain_name);
            else {
                if(!custom_domain_name.equals("null")) {
                    driver.findElement(By.name("custom_domain_name")).sendKeys(custom_domain_name);
                }
            }

            driver.findElement(By.name("custom_destination")).clear();

            if(custom_destination == null)
                driver.findElement(By.name("custom_destination")).sendKeys(Config.custom_destination);
            else
                driver.findElement(By.name("custom_destination")).sendKeys(custom_destination);

            driver.findElement(By.name("custom_domainid")).clear();
            if(custom_domainid==null)
                driver.findElement(By.name("custom_domainid")).sendKeys(Config.custom_domainid);
            else
                driver.findElement(By.name("custom_domainid")).sendKeys(custom_domainid);

            driver.findElement(By.name("custom_course_number")).clear();
            if(custom_course_number == null)
                driver.findElement(By.name("custom_course_number")).sendKeys(Config.custom_course_number);
            else {
                if(!custom_course_number.equals("null")){
                    driver.findElement(By.name("custom_course_number")).sendKeys(custom_course_number);
                }
            }

            if(custom_instructor_classlist == null)
                driver.findElement(By.name("custom_instructor_classlist")).sendKeys(Config.custom_instructor_classlist);
            else {
                if(custom_instructor_classlist.equals("null")){
                    driver.findElement(By.name("custom_instructor_classlist")).sendKeys("[]");
                }else{
                    driver.findElement(By.name("custom_instructor_classlist")).sendKeys(custom_instructor_classlist);
                }
            }

            if(custom_class_state != null) {

                driver.findElement(By.name("custom_class_state")).clear();
                driver.findElement(By.name("custom_class_state")).sendKeys(custom_class_state);
            }

            driver.findElement(By.name("custom_lis_result_source_id")).clear();
            driver.findElement(By.name("custom_lis_result_source_id")).sendKeys(custom_lis_result_source_id);

            driver.findElement(By.name("lis_person_sourced_id")).clear();
            driver.findElement(By.name("lis_person_sourced_id")).sendKeys(lis_person_sourced_id);

            driver.findElement(By.name("lis_outcome_service_url")).clear();


            if(lis_outcome_service_url ==null){
                driver.findElement(By.name("lis_outcome_service_url")).sendKeys(Config.lis_outcome_service_url);
            }else{
                if(!lis_outcome_service_url.equals("null")){
                   driver.findElement(By.name("lis_outcome_service_url")).sendKeys(Config.lis_outcome_service_url);
                }
            }


            driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
            driver.findElement(By.cssSelector("form > input[type=\"submit\"]")).click();
            Thread.sleep(2000);
            int closewelcomepage=driver.findElements(By.cssSelector("div[class='al-dialog-welcome-close-icon']")).size();
            if(closewelcomepage>=1)
            {
                driver.findElement(By.cssSelector("div[class='al-dialog-welcome-close-icon']")).click();
                Thread.sleep(2000);
            }
            WebDriverUtil.waitForAjax(driver,60);
            if(expectederror == null)
            {
                int backtowilyplus1=driver.findElements(By.cssSelector("*[title='ORION Dashboard']")).size();
                if(backtowilyplus1==0)
                {
                    new Screenshot().captureScreenshotFromAppHelper();
                    Assert.fail("Login to Orion Failed");
                }
            }
            else
            {
                Boolean textPresent = new TextValidate().IsTextPresent(expectederror);
                if(textPresent == false)
                {
                    new Screenshot().captureScreenshotFromAppHelper();
                    driver.quit();
                    Assert.fail("Expected error message not shown while logging in Orion");
                }
            }
            if(Config.browser.equals("ie"))
                driver.navigate().refresh();
            ReportUtil.log("Login", ""+role+" logged in ", "info");
        }

        catch(Exception e)
        {
            driver.quit();
            Assert.fail("Exception in LoginUsingLTI Application Helper",e);

        }

    }

}
