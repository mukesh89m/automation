package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.changingClass;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 02-03-2015.
 *
 */
public class ChangingClass extends Driver{

    @Test(priority = 1)
    public void changingClass()
    {
        try {
            String appendChar="a3";
            String appendChar1="b3";
            String appendCharacterBuild2;
            String appendCharacterBuild1;
            String appendCharacterBuild=System.getProperty("UCHAR");
            if (appendCharacterBuild!=null)
            {
                appendCharacterBuild1=appendChar+appendCharacterBuild;
                appendCharacterBuild2=appendChar1+appendCharacterBuild;
            }

            else
            {
                appendCharacterBuild2=appendChar1;
                appendCharacterBuild1=appendChar;
            }
            int dataIndex=30;
            ManageClass manageClass=PageFactory.initElements(driver,ManageClass.class);
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("manageclass");
            new Classes().createNewClass(appendChar1, dataIndex);//create class
            Thread.sleep(3000);
            String classCode1= manageClass.getclassCode().get(0).getText();
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().assignToStudent(dataIndex,appendChar1);
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar, classCode, 1119);//create student
            new Navigator().logout();//logout

            new Login().loginAsStudent(appendChar,dataIndex);//login as student
            new Navigator().navigateTo("manageclass");//navigate to manage class
            Assert.assertTrue(manageClass.getClassCountStudent().size() == 1, "Class count at student side is not displaying correctly");//Number of classes under one student
            Assert.assertTrue(manageClass.getNewClass().isDisplayed(),"New Class button is not present on the screen");//new class button should be display

            Assert.assertTrue(manageClass.getClassNameStudent().get(0).getText().contains("changingClassclass" +appendCharacterBuild1),"Class name is not displaying properly");//class name
            Assert.assertTrue(manageClass.getGrade().getText().contains("Grade 1"),"Grade is not displaying properly");//grade should be display properly
            Assert.assertTrue(manageClass.getclassCode().get(1).getText().contains("changingClassins"+appendCharacterBuild1),"Instructor name not displaying properly");//class code
            Assert.assertTrue(manageClass.getSubjectArea().getText().contains("Mathematics"),"Subject area not displaying properly");//subject area
            //navigate to visit class
            manageClass.getVisitClass().click();
            Assert.assertTrue(manageClass.started().isDisplayed(), "Get started button is not displaying");//get started button should be present on screen
            new Navigator().navigateTo("manageclass");//navigate to Manage class
            manageClass.getNewClass().click();
            manageClass.enterClassCodeWebElement().clear();
            //enter the class code for which student already registered
            manageClass.enterClassCodeWebElement().sendKeys(classCode);
            manageClass.buttonJoinClass().click();
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='as-errorMsg class-name-message']")));
            //display error message for already registered.
            Assert.assertTrue(manageClass.getClassCodeErrorMessage().getText().contains("You are already enrolled in the class."),"Not showing proper error message for wrong class code");
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(manageClass.enterClassCodeWebElement()));
            manageClass.enterClassCodeWebElement().clear();
            //enter invalid code
            manageClass.enterClassCodeWebElement().sendKeys("1212115");
            manageClass.buttonJoinClass().click();
            //display error message for invalid code
            Assert.assertTrue(manageClass.getClassCodeErrorMessage().getText().contains("Please provide a valid class code."),"Not showing proper error message for wrong class code");
            //click on cancel
            manageClass.getCancelButton().click();
            new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='as-manage-class-header-wrapper']/input")));
            manageClass.getNewClass().click();
            manageClass.enterClassCodeWebElement().clear();//clear class code input field
            Thread.sleep(2000);
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(manageClass.enterClassCodeWebElement()));
            manageClass.enterClassCodeWebElement().sendKeys(classCode1);//type valid class code in input field
            Thread.sleep(1000);
            manageClass.buttonJoinClass().click();//click on join class
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(manageClass.getNewClass()));
            //click on visit class
            manageClass.getVisitClass().click();
            //validate the instructor who created the assessment
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='select2-selection__rendered']")));
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getCreatedBy().getText().contains("changingClassclass"+appendCharacterBuild2));
            new Navigator().navigateTo("manageclass");//navigate to Manage class
            manageClass.getChangeClassDropDown().click();
            manageClass.getChangeClassSearchField().sendKeys("changingClassclass"+appendCharacterBuild1);
            Assert.assertEquals(manageClass.getClassNameList().size(),1,"Not dislpalying proper number of classes in dropdown");
            manageClass.getClassNameList().get(0).click();
            Thread.sleep(2000);
            Assert.assertEquals(manageClass.getChangeClassDropDown().getText(),"changingClassclass"+appendCharacterBuild1,"Class not changed");
    }
        catch (Exception e)
        {
            Assert.fail("Exception in ChangingClass Class in method changingClass",e);
        }

    }


    @Test(priority = 2)
    public void changingClassUsingURL()
    {
        try {
            String appendCharacterBuild=System.getProperty("UCHAR");
            String appendChar="a";
            String appendChar1="b";
            int dataIndex=30;
            if (appendCharacterBuild!=null) {
                appendCharacterBuild = appendChar1 + appendCharacterBuild;
            }
            else
            appendCharacterBuild=appendChar1;
            ManageClass manageClass=PageFactory.initElements(driver,ManageClass.class);
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar, classCode, 1119);//create student
            new Navigator().logout();
            new SignUp().teacher(appendChar1,dataIndex);//SignUp as a teacher*/
            new School().createWithOnlyName(appendChar1, dataIndex);//create school
            String classCodeSecond=new Classes().createClass(appendChar1, dataIndex);//create class
            new Navigator().navigateTo("manageclass");
            //fetch share url
            String shareUrl= manageClass.getUrl().getText();
            new Classes().createNewClass(appendChar1, dataIndex);//create class
            //fetch share url
            String shareUrl1= manageClass.getUrl().getText();
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);//login as student
            //navigate to share url shared by different instructor
            driver.navigate().to(shareUrl);
            String instrutorName=new TextFetch().textfetchbylistcssselector("div.as-manageClass-codeSectionRow",1);
            Assert.assertEquals(instrutorName,"InstructorchangingClassUsingURLins"+appendCharacterBuild,"Not able to join class as instructor is not correct");

            driver.navigate().to(shareUrl);
            Assert.assertEquals(driver.findElement(By.cssSelector("div[class='as-errorMsg class-name-message']")).getText(),"You are already enrolled in the class.","Error message not displaying when user try to add class in which student l=already enrolled");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in ChangingClass Class in method changingClass",e);
        }

    }

}
