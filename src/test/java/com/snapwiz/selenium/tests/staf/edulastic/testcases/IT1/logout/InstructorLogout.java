package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.logout;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Created by sumit on 26/11/14.
 */
public class InstructorLogout extends Driver{

    @Test(priority = 1, enabled = true)
    public void instructorLogout()
    {
        try
        {
            String appendChar = "a";
            new SignUp().teacher(appendChar, 0);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 0);//log in as teacher
            new School().createWithOnlyName(appendChar, 0);//create school
            new Classes().createClass(appendChar, 0);//create class
            new Navigator().logout();//log out
            String url = driver.getCurrentUrl();

            if(!url.contains(Config.baseURL))
            {
                Assert.fail("The Sign in page of the application is not displayed after instructor logout.");
            }

            new Login().loginAsInstructor(appendChar, 0);//log in as teacher
            new Navigator().navigateTo("assignment");
            new Navigator().logout();//log out
            String url1 = driver.getCurrentUrl();
            if(!url1.contains(Config.baseURL))
            {
                Assert.fail("Teacher unable to logout from assignments page.");
            }

            new Login().loginAsInstructor(appendChar, 0);//log in as teacher
            new Navigator().navigateTo("learningstream");
            new Navigator().logout();//log out
            String url2 = driver.getCurrentUrl();
            if(!url2.contains(Config.baseURL))
            {
                Assert.fail("Teacher unable to logout from learning stream page.");
            }

            new Login().loginAsInstructor(appendChar, 0);//log in as teacher
            new Navigator().navigateTo("myReports");
            new Navigator().logout();//log out
            String url3 = driver.getCurrentUrl();
            if(!url3.contains(Config.baseURL))
            {
                Assert.fail("Teacher unable to logout from my reports page.");
            }

            new Login().loginAsInstructor(appendChar, 0);//log in as teacher
            new Navigator().navigateTo("manageclass");
            new Navigator().logout();//log out
            String url4 = driver.getCurrentUrl();
            if(!url4.contains(Config.baseURL))
            {
                Assert.fail("Teacher unable to logout from manage class page.");
            }

            new Login().loginAsInstructor(appendChar, 0);//log in as teacher
            driver.quit();
            reInitDriver();
            driver.get(Config.baseURL);
            String url5 = driver.getCurrentUrl();
            if(url5.contains("dashboard"))
            {
                Assert.fail("Instructor is not Logged out if we close the browser.");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase instructorLogout in class InstructorLogout", e);
        }
    }
}
