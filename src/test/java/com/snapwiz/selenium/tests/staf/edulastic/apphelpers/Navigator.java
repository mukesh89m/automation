package com.snapwiz.selenium.tests.staf.edulastic.apphelpers;

import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.googleclassroom.GoogleclassRoom;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.ExplicitWait;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/*
 * Created by root on 12/8/14.
 */
public class Navigator extends Driver{

    public void navigateTo(String pageName)
    {
        try
        {
            Thread.sleep(2000);
            new Click().clickByid(pageName);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper Navigation in method navigateTo",e);
        }
    }

    public void navigateTo(String pageName,WebDriver webDriver)
    {
        try
        {
            new WebDriverWait(webDriver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='navbar-minimalize minimalize-styl-2 btn btn-hollow-green ']")));
            try {
                webDriver.findElement(By.xpath("//a[@class='navbar-minimalize minimalize-styl-2 btn btn-hollow-green ']/i")).click();
                Thread.sleep(2000);
            }
            catch(Exception e)
            {
                webDriver.findElement(By.xpath("//a[@class='navbar-minimalize minimalize-styl-2 btn btn-hollow-green ']/i")).click();
                Thread.sleep(2000);
            }
            webDriver.findElement(By.id(pageName)).click();
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper Navigation in method navigateTo",e);
        }
    }

    public void createClassToFindSchoolTeacher()
    {
        try
        {
            new Click().clickByclassname("as-add-back-btn"); //Clicking back button present in 'create class page' to navigate to find school page
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper Navigation in method createClassToFindSchoolTeacher",e);
        }
    }

    public void findSchoolToCreateClassTeacher()
    {
        try
        {
            new Click().clickBycssselector("div[class='as-search-blue-btn as-search-next btn btn-blue pull-right m-t m-b']"); //Clicking Next Step button present in 'Find School' page to navigate to create class page
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper Navigation in method createClassToFindSchoolTeacher",e);
        }
    }

    public void finishButtonOfCreateClassPage()
    {
        try
        {
            new Click().clickBycssselector("div[class='as-search-blue-btn btn btn-blue as-add-save-btn']"); //Clicking Finish button present in create class page
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper Navigation in method createClassToFindSchoolTeacher",e);
        }
    }

    public void addNewSchoolTeacher()
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("add")));//Teacher Navigating to add new school page
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper Navigation in method createClassToFindSchoolTeacher",e);
        }
    }

    public void studentSignUp()
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            new ExplicitWait().explicitWaitByCssSelector("span[class='btn btn-primary center-block as-goto-signup-button']");
            driver.findElement(By.cssSelector("span[class='btn btn-primary center-block as-goto-signup-button']")).click();
            new ExplicitWait().explicitWaitbyxpath("//span[@mode='student']");
            driver.findElement(By.xpath("//span[@mode='student']")).click();
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper Navigation in method studentSignUp",e);
        }

    }
    public void logout()
    {
        try
        {
            Thread.sleep(5000);
            WebDriver driver=Driver.getWebDriver();
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("navbar-username")));
            try {
                driver.findElement(By.id("signout")).click();
            }
            catch (Exception e)
            {
                new Click().clickByclassname("navbar-username");
                driver.findElement(By.id("signout")).click();
                WebDriverUtil.waitForAjax(driver,60);
            }
            ReportUtil.log("log out","successfully logged out","pass");
            Thread.sleep(2000);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper Navigation in method navigateTo",e);
        }
    }

    public void logout(WebDriver webDriver )
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            Thread.sleep(5000);
            webDriver.findElement(By.className("navbar-username")).click();

            try {
                webDriver.findElement(By.id("signout")).click();
            }
            catch (Exception e)
            {
                webDriver.findElement(By.className("navbar-username")).click();
                webDriver.findElement(By.id("signout")).click();
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper Navigation in method navigateTo",e);
        }
    }

    public void profile()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            Thread.sleep(5000);
            new Click().clickByclassname("navbar-username");
            try {
                driver.findElement(By.className("username")).click();
            }
            catch (Exception e)
            {
                new Click().clickByclassname("navbar-username");
                driver.findElement(By.className("username")).click();
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper Navigation in method navigateTo",e);
        }
    }

    public void selectGradeAndSubject(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            Select sel=new Select(driver.findElement(By.id("as-header-grade-selectbox")));
            sel.selectByIndex(0);//select grade1
            sel=new Select(driver.findElement(By.id("as-header-subject-selectbox")));
            sel.selectByIndex(0);//select a subject
        } catch (Exception e) {
            Assert.fail("Exception in method selectGradeAndSubject of AppHelper Navigator",e);
        }
        // new Navigator().navigateTo("districtAssessments");//Navigate to assessment

    }

    public  void clickOnCreateNewAssignment(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            new Click().clickByid("new-assessment-button");//click on the +New Assessment
            new Click().clickByListClassName("as-assignment-flow-link-title",1);//click on the  create New Assignment
            Select sel=new Select(driver.findElement(By.xpath("//div[@id='lsm-createAssignment-subject-dropDown']/select")));
            sel.selectByIndex(0);//select subject
            sel=new Select(driver.findElement(By.xpath("//div[@id='lsm-createAssignment-grade-dropDown']/select")));
            sel.selectByIndex(0);//select a grade
        } catch (Exception e) {
            Assert.fail("Exception in method clickOnCreateNewAssignment of AppHelper Navigator",e);
        }
    }

    public  void clickOnModifyExistingAssessment(int dataIndex,String ownership)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            new Click().clickByid("new-assessment-button");//click on the +New Assessment
            new Click().clickByclassname("as-assignment-flow-link-title");//click on the  create New Assignment
            Select sel=new Select(driver.findElement(By.xpath("//div[@id='lsm-createAssignment-subject-dropDown']/select")));
            sel.selectByIndex(0);//select subject
            sel=new Select(driver.findElement(By.xpath("//div[@id='lsm-createAssignment-grade-dropDown']/select")));
            sel.selectByIndex(0);//select a grade
            new Click().clickByid(ownership);//click on the district
        } catch (Exception e) {
            Assert.fail("Exception in method clickOnModifyExistingAssessment of AppHelper Navigator", e);
        }
    }
    public  void clickOnUseExistingAssignment(int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            new Navigator().navigateTo("assignment"); //navigate to assignment
            new Click().clickByclassname("instructor-assignment-new-txt");//click on the new Assignment
            new Click().clickByclassname("as-assignment-flow-link-title");//click on the use assignment
            Select sel=new Select(driver.findElement(By.xpath("//div[@id='lsm-createAssignment-subject-dropDown']/select")));
            sel.selectByIndex(0);//select subject
            sel=new Select(driver.findElement(By.xpath("//div[@id='lsm-createAssignment-grade-dropDown']/select")));
            sel.selectByIndex(0);//select a grade
            new Click().clickByid("three");//click on the district
        } catch (Exception e) {
            Assert.fail("Exception in method clickOnUseExistingAssignment of AppHelper Navigator", e);
        }
    }


    public void logoutGoogleClassRoom(int dataIndex)
    {
        try{
            WebDriver driver = getWebDriver();

            GoogleclassRoom classroom = PageFactory.initElements(driver,GoogleclassRoom.class);

            classroom.dropdown_signout.click();//click on drop down
            WebDriverUtil.clickOnElementUsingJavascript(classroom.signOut);//Click on sign out

        }catch (Exception e){
            Assert.fail("Exception in method logoutGoogleClassRoom of AppHelper Navigator", e);

        }

    }

}
