package com.snapwiz.selenium.tests.staf.edulastic.apphelpers;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.DropDown;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by root on 18/8/14.
 */
public class Classes extends Driver {

    public String createClass(String appendChar,int dataIndex,String ...params)
    {
        String appendCharacterBuild=System.getProperty("UCHAR");
        if (appendCharacterBuild!=null)
            appendChar=appendChar+appendCharacterBuild;
        String classCode = "";
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String googleuser = ReadTestData.readDataByTagName("", "googleuser", Integer.toString(dataIndex));

            String  className = new Exception().getStackTrace()[1].getMethodName()+"class"+appendChar;
            String  schoolName = new Exception().getStackTrace()[1].getMethodName()+"school"+appendChar;

            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String email = methodName+"ins"+appendChar+"@snapwiz.com";
            String grade,subjectArea,subject;

            if(params.length == 0)  grade = "Grade 1";
               else grade = params[0];

            if(params.length == 0) subjectArea = "Mathematics";
            else
                subjectArea = params[1];

            if(params.length == 0) subject = "Math - Common Core";
            else
                subject = params[2];

            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("class-name")));
            new TextSend().textsendbyid(className, "class-name");//Enter Class name
            new DropDown().selectValue("class", "as-add-grade-dropDown", grade);//Select a grade
            new DropDown().selectValue("class", "as-add-subjectArea-dropDown", subjectArea);//Select subject area
            new DropDown().selectValue("class", "as-add-subject-dropDown", subject);//select subject
            new Click().clickBycssselector("div[class='as-search-blue-btn btn btn-blue as-add-save-btn']");//Finish button
            Thread.sleep(2000);
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='as-search-blue-btn as-search-next btn btn-blue js-manageclass']")));
            new Click().clickBycssselector("a[class='as-search-blue-btn as-search-next btn btn-blue js-manageclass']");//Click on Manage class

            if(new TextFetch().textfetchbyid("select2-as-header-classes-selectbox-container").trim().equals("DEMO CLASS")){
                new Click().clickBycssselector("span[class='select2-selection__arrow']");//Click on arrow
                int index1 = 0;
                List<WebElement> listClasses = driver.findElements(By.xpath("//ul[@id='select2-as-header-classes-selectbox-results']/li"));
                for(WebElement classesName: listClasses){
                    if(classesName.getAttribute("title").equals(className)){
                        listClasses.get(index1).click();
                        break;

                    }
                    index1++;
                }

            }

            if(driver.findElements(By.className("as-manageClass-codeValue")).size()==0)
            {
              /*  DBConnect.Connect();
                ResultSet s = DBConnect.st.executeQuery("SELECT id FROM t_institution WHERE NAME ='" + schoolName + "';");
                s.next();
                String schooolId = s.getString(1);
                System.out.println("schoolId " + schooolId);*/
                new Navigator().logout();

                String schooolId = String.valueOf(((JavascriptExecutor)driver).executeScript("return userJSON.institutionId"));

                new Login().directLoginAsInstructor(dataIndex, "kiran.author@snapwiz.com");
                new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("logout")));
                driver.get(Config.baseURL + "/secure/manageAssignmentInRedis");
                new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("approve-district")));
                driver.findElement(By.id("approve-school")).click();//Select district radio button
                driver.findElement(By.id("entityId")).clear();
                driver.switchTo().activeElement().sendKeys(schooolId);//Enter district id to be approved
                driver.findElement(By.id("approve-submit")).click();//Click on approve
                new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("approve-submit-status-message")));
                Thread.sleep(1000);
                driver.findElement(By.id("logout")).click();//Click on log out button

                if(googleuser!=null)
                {
                 new Click().clickByid("google-openid-login");//click on Google button in login page
                }else {
                    new Login().directLoginAsInstructor(dataIndex, email);
                }

            }

            //driver.get("http://10.0.0.68/#manageclass/close");
            new Navigator().navigateTo("manageclass");
            driver.navigate().refresh();
            new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("as-manageClass-codeValue")));
            classCode = new TextFetch().textfetchbyclass("as-manageClass-codeValue");
            new Click().clickBycssselector("span.as-manage-class-visit");//Click on visit class
            Thread.sleep(3000);
            ReportUtil.log("Create Class", "Class creation is completed and class name is "+className, "pass");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper createClass in class Classes",e);
        }

        return classCode;
    }
    public String createNewClass(String appendChar,int dataIndex,String ...params) throws AWTException
    {
        Robot robot=new Robot();
        String appendCharacterBuild=System.getProperty("UCHAR");
        WebDriver driver=Driver.getWebDriver();
        if (appendCharacterBuild!=null)
            appendChar=appendChar+appendCharacterBuild;

        String classCode = "";

        try {
            String  className = new Exception().getStackTrace()[1].getMethodName()+"class"+appendChar;
            String grade,subjectArea,subject;

            if(params.length == 0)  grade = "Grade 1";
            else grade = params[0];

            if(params.length == 0) subjectArea = "Mathematics";
            else
                subjectArea = params[1];

            if(params.length == 0) subject = "Math - Common Core";
            else
                subject = params[2];

            new Navigator().navigateTo("manageclass");//navigate to manage class
            Thread.sleep(2000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_SUBTRACT);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(3000);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.cssSelector("div[class='as-manage-blue-btn as-add-newClass-btn']")));
            //new Click().clickBycssselector("div[class='as-manage-blue-btn as-add-newClass-btn']");//click on +New class button
            Thread.sleep(2000);
            new TextSend().textsendbycssSelector(className, "input[class='as-manage-class-name class-name-input']");//give the class name
            new DropDown().selectValue("class", "as-add-grade-dropDown", grade);//Select a grade
            new DropDown().selectValue("class","as-add-subjectArea-dropDown",subjectArea);//Select subject area
            new DropDown().selectValue("class","as-add-subject-dropDown",subject);//select subject
            new TextSend().textsendbycssSelector(new RandomString().randomstring(10), "textarea[class='as-manage-class-description class-description-input']");//give the description
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("(//input[@class='as-blue-btn as-add-save-btn'])[1]")));//click on save class
            //new Click().clickByXpath("(//input[@class='as-blue-btn as-add-save-btn'])[1]"); //click on save class
            Thread.sleep(2000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ADD);
            robot.keyRelease(KeyEvent.VK_ADD);
            robot.keyPress(KeyEvent.VK_ADD);
            robot.keyRelease(KeyEvent.VK_ADD);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            classCode = new TextFetch().textfetchbyclass("as-manageClass-codeValue");
            ReportUtil.log("Create Class", "New Class named as '"+className+"' is created", "pass");


        } catch (Exception e) {
            Assert.fail("Exception in app helper createNewClass in class Classes",e);
        }
        return  classCode;
    }
}
