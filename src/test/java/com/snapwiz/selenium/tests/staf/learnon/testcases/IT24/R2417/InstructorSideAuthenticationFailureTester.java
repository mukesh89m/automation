package com.snapwiz.selenium.tests.staf.learnon.testcases.IT24.R2417;

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Settings;
import com.snapwiz.selenium.tests.staf.learnon.pageFactory.DashBoard.LoginErrorPage;
import com.snapwiz.selenium.tests.staf.learnon.pageFactory.assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.UIElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by root on 12/22/15.
 */
public class InstructorSideAuthenticationFailureTester extends Driver {
    @Test(priority = 1)
    public void testInstructorSideAuthenticationFailure() {
        try {
            String dataIndex = "13";
new LoginUsingLTI().ltiLogin(dataIndex);


            LoginErrorPage loginErrorPage = PageFactory.initElements(driver, LoginErrorPage.class);


            //# Error page should be displayed
            System.out.println("1: " + loginErrorPage.loginErrorMessage.getText());


            //# Header  arrow icon or “Return to Jacplus” link should be displayed
            String returnUrl = "http://edugen.wiley.com/edugen/rs-callback";
            String arrowIconSource = "http://10.0.0.64/webresources/images/al/return-to-wp-icon.png";
            if (!(loginErrorPage.returnToJackPlusArrowIcon.getAttribute("returnurl").equals(returnUrl) && loginErrorPage.returnToJackPlusArrowIcon.getAttribute("src").equals(arrowIconSource))) {
                Assert.fail("# Header  arrow icon or “Return to Jacplus” link should be displayed");
            }


            //# "To return to Jacplus, click here" message should be displayed in between the error messages
            Assert.assertEquals(loginErrorPage.link_clickHere.isDisplayed(), true, "//# \"To return to Jacplus, click here\" message should be displayed in between the error messages");

            //"Jacaranda|LearnOn" should be displayed beside header arrow icon
            Assert.assertEquals(loginErrorPage.icon_jarcarandaLearnOn.isDisplayed(), true, "//\"Jacaranda|LearnOn\" should be displayed beside header arrow icon");


            //Color of the text "Jacarande|Learn" should be changed to white


            //Color of the header of the page is changed form white to grey


            //"Report the issue" text should be removed


            //Row No - 20 : 5. Hover over the header arrow icon
            //Expected - # "Return to jacplus" should be displayed
            new MouseHover().mouserhoverbywebelement(loginErrorPage.returnToJackPlusArrowIcon);
            new UIElement().waitAndFindElement(loginErrorPage.label_returnToJackPlus);
            Assert.assertEquals(loginErrorPage.label_returnToJackPlus.isDisplayed(), true, "Return to jacplus\" should be displayed");


            //Row No - 21 : 6. Click on the header arrow icon
            //Expected - Instructor should be able to return to the Jacplus product
            loginErrorPage.returnToJackPlusArrowIcon.click();
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.urlContains("http://edugen.wiley.com/edugen/rs-callback"));

            Assert.assertEquals(driver.getCurrentUrl(), returnUrl, "Instructor should be able to return to the Jacplus product");


            //Row No - 22 : 7. Click on "Click here" link beside Return to jacplus
            //Expected - User should be able to return to the Jacplus product
            new LoginUsingLTI().ltiLogin(dataIndex);

            loginErrorPage.link_clickHere.click();
            wait.until(ExpectedConditions.urlContains("http://edugen.wiley.com/edugen/rs-callback"));

            Assert.assertEquals(driver.getCurrentUrl(), returnUrl, "User should be able to return to the Jacplus product");
        } catch (Exception e) {
            Assert.fail("Exception in the class 'InstructorSideAuthenticationFailureTester' in the test method 'testInstructorSideAuthenticationFailure'", e);

        }
    }

    @Test(priority = 2)
    public void testStudentSideAuthenticationFailure() {
        try {
            String dataIndex = "13";
            new LoginUsingLTI().ltiLogin(dataIndex);


            LoginErrorPage loginErrorPage = PageFactory.initElements(driver, LoginErrorPage.class);


            //# Error page should be displayed
            System.out.println("1: " + loginErrorPage.loginErrorMessage.getText());


            //# Header  arrow icon or “Return to Jacplus” link should be displayed
            String returnUrl = "http://edugen.wiley.com/edugen/rs-callback";
            System.out.println("2 : " + loginErrorPage.returnToJackPlusArrowIcon.getAttribute("src"));
            String arrowIconSource = "http://10.0.0.64/webresources/images/al/return-to-wp-icon.png";
            if (!(loginErrorPage.returnToJackPlusArrowIcon.getAttribute("returnurl").equals(returnUrl) && loginErrorPage.returnToJackPlusArrowIcon.getAttribute("src").equals(arrowIconSource))) {
                Assert.fail("# Header  arrow icon or “Return to Jacplus” link should be displayed");
            }


            //# "To return to Jacplus, click here" message should be displayed in between the error messages
            Assert.assertEquals(loginErrorPage.link_clickHere.isDisplayed(), true, "//# \"To return to Jacplus, click here\" message should be displayed in between the error messages");

            //"Jacaranda|LearnOn" should be displayed beside header arrow icon
            Assert.assertEquals(loginErrorPage.icon_jarcarandaLearnOn.isDisplayed(), true, "//\"Jacaranda|LearnOn\" should be displayed beside header arrow icon");


            //Color of the text "Jacarande|Learn" should be changed to white


            //Color of the header of the page is changed form white to grey


            //"Report the issue" text should be removed


            //Row No - 20 : 5. Hover over the header arrow icon
            //Expected - # "Return to jacplus" should be displayed
            new MouseHover().mouserhoverbywebelement(loginErrorPage.returnToJackPlusArrowIcon);
            new UIElement().waitAndFindElement(loginErrorPage.label_returnToJackPlus);
            Assert.assertEquals(loginErrorPage.label_returnToJackPlus.isDisplayed(), true, "Return to jacplus\" should be displayed");


            //Row No - 21 : 6. Click on the header arrow icon
            //Expected - Instructor should be able to return to the Jacplus product
            loginErrorPage.returnToJackPlusArrowIcon.click();
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.urlContains("http://edugen.wiley.com/edugen/rs-callback"));

            Assert.assertEquals(driver.getCurrentUrl(), returnUrl, "Instructor should be able to return to the Jacplus product");


            //Row No - 22 : 7. Click on "Click here" link beside Return to jacplus
            //Expected - User should be able to return to the Jacplus product
            new LoginUsingLTI().ltiLogin(dataIndex);

            loginErrorPage.link_clickHere.click();
            wait.until(ExpectedConditions.urlContains("http://edugen.wiley.com/edugen/rs-callback"));

            Assert.assertEquals(driver.getCurrentUrl(), returnUrl, "User should be able to return to the Jacplus product");


        } catch (Exception e) {
            Assert.fail("Exception in the class 'InstructorSideAuthenticationFailureTester' in the test method 'testInstructorSideAuthenticationFailure'", e);

        }
    }

    @Test(priority = 3)
    public void enhancementToAssignmentFlow() {
        try {
            String dataIndex = "53";
            new LoginUsingLTI().ltiLogin(dataIndex);
            new Navigator().NavigateTo("Current Assignments");

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            System.out.println("1: " + currentAssignments.NoAssignmentsMessage.getText());
            Assert.assertEquals(currentAssignments.NoAssignmentsMessage.getText(),"You have not created any assignments yet.\n" +
                    "Select the \"+ New Assignment\" button in the top right corner of your screen to get started.","“You have not created any assignment yet”. Select the “+ New Assignment” button in the top right corner of your screen to get started.”message should be displayed");

        } catch (Exception e) {
            Assert.fail("Exception in the class 'InstructorSideAuthenticationFailureTester' in the test method 'testInstructorSideAuthenticationFailure'", e);

        }
    }

    @Test(priority = 4)
    public void enhancementToRoboNotification() {
        try {
            String dataIndex = "53";
            new LoginUsingLTI().ltiLogin(dataIndex);

            new Settings().disableTopic(0);
            new LoginUsingLTI().ltiLogin("59");

            new Navigator().NavigateTo("Course");

        } catch (Exception e) {
            Assert.fail("Exception in the class 'InstructorSideAuthenticationFailureTester' in the test method 'testInstructorSideAuthenticationFailure'", e);

        }
    }


    @Test
    public void assda() {
        try {
            int dataIndex = 61;
            new Assignment().create(dataIndex);






        } catch (Exception e) {
            Assert.fail("Exception in the class 'InstructorSideAuthenticationFailureTester' in the test method 'testInstructorSideAuthenticationFailure'", e);

        }
    }
}
