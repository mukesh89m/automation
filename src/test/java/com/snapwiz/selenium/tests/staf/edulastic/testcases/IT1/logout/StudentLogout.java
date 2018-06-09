package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.logout;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Created by sumit on 26/11/14.
 */
public class StudentLogout extends Driver{

    @Test(priority = 1, enabled = true)
    public void studentLogout()
    {
        try
        {
            String appendChar = "a";
            new SignUp().teacher(appendChar, 0);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 0);//log in as teacher
            new School().createWithOnlyName(appendChar, 0);//create school
            String classCode = new Classes().createClass(appendChar, 0);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, 0);//create student1
            new Navigator().logout();//log out

            String url = driver.getCurrentUrl();

            if(!url.contains(Config.baseURL))
            {
                Assert.fail("The Sign in page of the application is not displayed after student logout.");
            }

            new Login().loginAsStudent(appendChar, 0);//log in as Student
            new Navigator().navigateTo("assignment");
            new Navigator().logout();//log out
            String url1 = driver.getCurrentUrl();
            if(!url1.contains(Config.baseURL))
            {
                Assert.fail("Student unable to logout from assignments page.");
            }

            new Login().loginAsStudent(appendChar, 0);//log in as Student
            new Navigator().navigateTo("learningstream");
            new Navigator().logout();//log out
            String url2 = driver.getCurrentUrl();
            if(!url2.contains(Config.baseURL))
            {
                Assert.fail("Student unable to logout from learning stream page.");
            }

            new Login().loginAsStudent(appendChar, 0);//log in as Student
            new Navigator().navigateTo("skillreport");
            new Navigator().logout();//log out
            String url3 = driver.getCurrentUrl();
            if(!url3.contains(Config.baseURL))
            {
                Assert.fail("Student unable to logout from my reports page.");
            }

            new Login().loginAsStudent(appendChar, 0);//log in as Student
            driver.quit();
            reInitDriver();
            driver.get(Config.baseURL);
            String url5 = driver.getCurrentUrl();
            if(url5.contains("dashboard"))
            {
                Assert.fail("student is not Logged out if we close the browser.");
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase studentLogout in class StudentLogout", e);

        }
    }
}
