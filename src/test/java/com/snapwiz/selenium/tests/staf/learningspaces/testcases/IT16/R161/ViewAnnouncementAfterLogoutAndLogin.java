package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R161;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AddAnnouncement;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by Sumit on 8/21/2014.
 */
public class ViewAnnouncementAfterLogoutAndLogin extends Driver{

    @Test
    public void viewAnnouncementAfterLogoutAndLogin()
    {
        try
        {
            new AddAnnouncement().addAnnouncement("141", "descrption");//create announcement

            //TC row 142
            new LoginUsingLTI().ltiLogin("141");//login As an instructor to LS course
            String announcement = "";
            announcement = driver.findElement(By.className("announcements-data")).getText();
            if(!announcement.equals("descrption")) {
                Assert.fail("All the active Announcements are not shown on the dashboard for instructor of LS course.");
            }

            driver.quit();
            startDriver("firefox");
            //TC row 143
            new LoginUsingLTI().ltiLogin("141");//login As an instructor to LS course
            announcement = driver.findElement(By.className("announcements-data")).getText();
            if(!announcement.equals("descrption"))
                Assert.fail("All the active Announcements are not shown on the dashboard for instructor of LS course after he logs out and login again.");

            //TC row 144
            new LoginUsingLTI().ltiLogin("144");//login As an student to Orion course
            announcement = driver.findElement(By.className("announcements-data")).getText();
            if(!announcement.equals("descrption")) {
                Assert.fail("All the active Announcements are not shown on the dashboard for student of Orion course.");
            }

            driver.quit();
            startDriver("firefox");
            //TC row 145
            new LoginUsingLTI().ltiLogin("144");//login As an student to Orion course
            announcement = driver.findElement(By.className("announcements-data")).getText();
            if(!announcement.equals("descrption"))
                Assert.fail("All the active Announcements are not shown on the dashboard for student of Orion course after he logs out and login again.");

            //TC row 146
            new LoginUsingLTI().ltiLogin("146");//login As an instructor to Orion course
            announcement = driver.findElement(By.className("announcements-data")).getText();
            if(!announcement.equals("descrption")) {
                Assert.fail("All the active Announcements are not shown on the dashboard for instructor of Orion course.");
            }

            driver.quit();
            startDriver("firefox");
            //TC row 147
            new LoginUsingLTI().ltiLogin("146");//login As an instructor to Orion course
            announcement = driver.findElement(By.className("announcements-data")).getText();
            if(!announcement.equals("descrption"))
                Assert.fail("All the active Announcements are not shown on the dashboard for instructor of Orion course after he logs out and login again.");

            //TC row 148
            new LoginUsingLTI().ltiLogin("148");//login As an student to Orion course
            announcement = driver.findElement(By.className("announcements-data")).getText();
            if(!announcement.equals("descrption")) {
                Assert.fail("All the active Announcements are not shown on the dashboard for student of Orion course.");
            }

            driver.quit();
            startDriver("firefox");
            //TC row 149
            new LoginUsingLTI().ltiLogin("148");//login As a student to Orion course
            announcement = driver.findElement(By.className("announcements-data")).getText();
            if(!announcement.equals("descrption"))
                Assert.fail("All the active Announcements are not shown on the dashboard for student of Orion course after he logs out and login again.");

            //TC row 150
            new LoginUsingLTI().ltiLogin("150");//login As a Mentor to Orion  course
            announcement = driver.findElement(By.className("announcements-data")).getText();
            if(!announcement.equals("descrption")) {
                Assert.fail("All the active Announcements are not shown on the dashboard for mentor of Orion course.");
            }

            driver.quit();
            startDriver("firefox");
            //TC row 151
            new LoginUsingLTI().ltiLogin("150");//login As a Mentor to Orion  course
            announcement = driver.findElement(By.className("announcements-data")).getText();
            if(!announcement.equals("descrption"))
                Assert.fail("All the active Announcements are not shown on the dashboard for mentor for Orion course after he logs out and login again.");

            //TC row no. 152
            new LoginUsingLTI().ltiLogin("152");//login As a Mentor to LS  course
            announcement = driver.findElement(By.className("announcements-data")).getText();
            if(!announcement.equals("descrption")) {
                Assert.fail("All the active Announcements are not shown on the dashboard for mentor of LS course.");
            }

            driver.quit();
            startDriver("firefox");
            //TC row no. 153
            new LoginUsingLTI().ltiLogin("152");//login As a Mentor to LS  course
            announcement = driver.findElement(By.className("announcements-data")).getText();
            if(!announcement.equals("descrption"))
                Assert.fail("All the active Announcements are not shown on the dashboard for mentor for LS course after he logs out and login again.");

            //TC row no. 154
            new DirectLogin().CMSLogin();// login As an author LS/Orion Course
            announcement = driver.findElement(By.className("announcements-data")).getText();
            if(!announcement.equals("descrption")) {
                Assert.fail("All the active Announcements are not shown on the dashboard for mentor of LS course.");
            }

            driver.quit();
            startDriver("firefox");
            //TC row no. 155
            new DirectLogin().CMSLogin();// login As an author LS/Orion Course
            announcement = driver.findElement(By.className("announcements-data")).getText();
            if(!announcement.equals("descrption"))
                Assert.fail("All the active Announcements are not shown on the dashboard for mentor for LS course after he logs out and login again.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class ViewAnnouncementAfterLogoutAndLogin in method viewAnnouncementAfterLogoutAndLogin", e);
        }
    }

}
