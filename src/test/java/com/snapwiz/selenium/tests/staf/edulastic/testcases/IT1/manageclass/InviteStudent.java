package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.manageclass;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Created by sumit on 24/11/14.
 */
public class InviteStudent extends Driver{

    @Test
    public void inviteStudentViaEmail()
    {
        try
        {
            String appendChar = "a";
            String appendCharacterBuild=System.getProperty("UCHAR");
            if(appendCharacterBuild!=null)
                appendCharacterBuild=appendChar+appendCharacterBuild;
            else
            appendCharacterBuild=appendChar;
            String instructorEmail = "inviteStudentViaEmailins"+appendCharacterBuild+"@snapwiz.com";
            //TC row no. 23-29
            new SignUp().teacher(appendChar, 23);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 23);//create school
            String code = new Classes().createClass(appendChar, 23);//create class
            new Navigator().navigateTo("manageclass");//navigate to manage class
            new Click().clickbylinkText("Add students via e-mail");//click on Add students via e-mail
            String text = new TextFetch().textfetchbyclass("as-inviteStudents-messageBlock");
            Assert.assertEquals(text.replaceAll("\n", " "),"Hi Student ,  Your teacher "+"inviteStudentViaEmailins"+appendCharacterBuild+" has invited you to use Edulastic - a simple formative assessment platform designed to take the stress out of tests and help you learn better.  To join your "+"inviteStudentViaEmailins"+appendCharacterBuild+" 's class "+"inviteStudentViaEmailclass"+appendCharacterBuild+"            1. Go to "+Config.baseURL+"regcd/"+code+"           2. Type in your registration details and sign in to the application.           3. Start learning with your teacher and peers.  If you need help with anything, please don't hesitate to ask. You can reach us at support@edulastic.com. Thank you, Edulastic Team","fail");

            String text1 = new TextFetch().textfetchbyclass("as-new-students-text");
            Assert.assertEquals(text1, "Invite students for your class", "\"Invite Students For your Class\" pop up is not displayed after clicking on \"Add students via e-mail\".");
            String email = driver.findElement(By.cssSelector("textarea[class='as-add-students invite-students-input']")).getAttribute("placeholder");
            Assert.assertEquals(email, "Enter Student email...", "Field to enter email address is not displayed in pop-up after clicking on \"Add students via e-mail\".");
            String defaultMessage = new TextFetch().textfetchbyclass("as-inviteStudents-messageBlock");
            System.out.println("defaultMessage: "+defaultMessage);
            if(defaultMessage.length()==0)
                Assert.fail("Default Edulastic message is not present in pop-up" );

            String cancelButton = new TextFetch().textfetchbyclass("as-add-students-back-btn");
            Assert.assertEquals(cancelButton, "Cancel", "Cancel button is not displayed in pop-up after clicking on \"Add students via e-mail\".");

            String inviteButton = driver.findElement(By.cssSelector("input[class='as-blue-btn as-invite-studentsBtn']")).getAttribute("value");
            Assert.assertEquals(inviteButton, "Invite", "Invite button is not displayed in pop-up after clicking on \"Add students via e-mail\".");
            new Navigator().navigateTo("manageclass");//navigate to manage class*/
            String accessURL = Config.baseURL+"regcd/"+new TextFetch().textfetchbyclass("as-manageClass-codeValue");
            System.out.println("accessurl"+accessURL);
            new SignUp().studentRegisterViaEmail(appendChar, instructorEmail, accessURL, 23);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase inviteStudentViaEmail in class InviteStudent.", e);
        }
    }


}
